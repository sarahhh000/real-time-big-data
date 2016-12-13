   #!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Dec  2 19:31:11 2016

@author: jlwang
"""
import csv

with open("yelp_business_clean.csv", mode="rU") as infile:
    reader = csv.reader(infile, dialect="excel")
    with open("yelp_business_clean_tab.csv", mode="w") as outfile:
        writer = csv.writer(outfile, delimiter='\t')
        for row in reader:
            writer.writerow(row)
