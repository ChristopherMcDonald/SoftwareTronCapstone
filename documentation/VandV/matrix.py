# RUN with python2
import csv

with open('data-f.csv', 'rb') as csvfile:
    spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    for row in spamreader:
        print "&".join(row[0].split(",")) + " \\\ \hline";
