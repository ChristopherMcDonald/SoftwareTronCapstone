# RUN with python2
import csv

with open('data-nf.csv', 'rb') as csvfile:
    spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    for row in spamreader:
        print "} & \\tiny{".join(row[0].split(",")) + " \\\ \hline";