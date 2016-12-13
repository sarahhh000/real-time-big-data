   #!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Dec  2 19:31:11 2016
Add location column to business file

@author: jlwang
"""
import csv
import math

NORTH = 33.920215
SOUTH = 33.290604
EAST = -111.960540
WEST = -112.323217

LATNUM = 20
LONGNUM = 35

with open("yelp_business_clean.csv", mode="rU") as infile:
    reader = csv.reader(infile, dialect="excel")    
    with open("yelp_business_location.csv", mode="w") as outfile:
            writer = csv.writer(outfile, delimiter='\t')
            
            latunit = (EAST - WEST) / 20
            longunit = (NORTH - SOUTH) / 35
            row = next(reader)
            row.append('location')
            writer.writerow(row)
            
            locdis = [[0 for i in range(20)] for j in range(35)]
            loc = [0] * 700
            
            for row in reader:
                latitude = float(row[66])
                longitude = float(row[69])
                x = int(math.ceil((longitude - WEST) / latunit))
                y = int(math.ceil((latitude - SOUTH) / longunit))
                if x >= 1 and x <= LATNUM and y >= 1 and y <= 35:
                    location = LATNUM * (y - 1) + x
                    loc[int(location) - 1] = loc[int(location) - 1] + 1
                    locdis[y - 1][x - 1] = locdis[y - 1][x - 1] + 1 
                    row.append(location)
                    writer.writerow(row)
    
    with open("blockNum.txt", mode = 'w') as outfile2:
        
        for i in range(35):
            for j in range (19):
                outfile2.write("%s\t" % locdis[i][j])
            outfile2.write("%s\n" % locdis[i][j + 1])
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            