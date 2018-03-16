from random import random;
import mysql.connector;
from mysql.connector import Error;

## This class is a key->value store where keys are shotIDs and values
## are tuples (e.g. (12,53)) which represents range of possible values
## which COULD be the ACTUAL return of the player
##
## Both arrays are kept in sync
##
## To get the next shot, the MINIMUM mins for range types are considered
## so if we have (0,12), (0,42) and (1,12) as ranges, the first two are randomly
## picked
class BinarySearch:
    keys = []; # holds all shot IDs
    vals = []; # holds min/max return type, same order as above

    def __init__(self):
        conn = mysql.connector.connect(host='localhost', database='smartserve', user='root', password='smarTserve91', port=3306);
        cursor = conn.cursor();

        query = ("SELECT shot_id from shot_type");

        cursor.execute(query);

        # fills keys with every possible shot ID
        for shotID in cursor:
            self.keys.append(shotID[0]);

        cursor.close()
        conn.close();

        # inits vals with min/max for return rate
        self.vals = [(0,100)] * len(self.keys);

    def update(self, key, returned):
        # get position for key, to be used in vals
        i = self.keys.index(key);

        # check if we can increment max return rate
        maxed  = self.vals[i][1] == 100;
        # check if we can decrement min return rate
        minned = self.vals[i][0] == 0;

        # if min/max are same value, i.e. we have honed in on return rate of player
        if(self.vals[i][0] == self.vals[i][1]):
            # if successful return and return rate isn't 100
            if returned and not maxed:
                # increment both
                self.vals[i] = (self.vals[i][0] + 1, self.vals[i][1] + 1);
            # if not successful and return rate isn't 0
            elif not returned and not minned:
                self.vals[i] = (self.vals[i][0] - 1, self.vals[i][1] - 1);
        else:
            # if successful and min range isn't maxed
            if returned and self.vals[i][0] != 100:
                # increment min!
                self.vals[i] = (self.vals[i][0] + 1, self.vals[i][1]);
            elif not returned and self.vals[i][1] != 0:
                # decrement max!
                self.vals[i] = (self.vals[i][0], self.vals[i][1] - 1);

        # move the changed value up/down to order values based on
        # increasing min value
        if returned:
            while(i + 1 < len(self.vals) and self.vals[i][0] > self.vals[i + 1][0]): # while next min is GREATER than current min
                tempV = self.vals[i];
                tempK = self.keys[i];
                self.vals[i] = self.vals[i + 1];
                self.keys[i] = self.keys[i + 1];
                self.vals[i + 1] = tempV;
                self.keys[i + 1] = tempK;
                i += 1;
        else:
            while(i - 1 > -1 and self.vals[i - 1][0] > self.vals[i][0]): # while previous min is GREATER than current min
                # TODO: this isn't working as intended!
                tempV = self.vals[i];
                tempK = self.keys[i];
                self.vals[i] = self.vals[i - 1];
                self.keys[i] = self.keys[i - 1];
                self.vals[i - 1] = tempV;
                self.keys[i - 1] = tempK;
                i -= 1;

    def getNext(self):
        min = self.vals[0][0]; # get smallest value in vals
        options = [];
        i = 0;
        # while options have same min as global minimum
        while((i + 1 < len(self.vals)) and self.vals[i][0] == min):
            # add option to options
            options.append(self.keys[i]);
            i += 1;
        # return random option
        return options[int(random()*len(options))];
