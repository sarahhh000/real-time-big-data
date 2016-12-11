import sys
import re

inf = open(sys.argv[1], 'r')
ouf = open(sys.argv[1] + '-geoConvert', 'w')

for line in inf:
    clean = line.rstrip()
    split = clean.split('\t')
    if len(split) != 5:
        continue
    geoLoc = split[2]
    if geoLoc == 'null':
        continue
    split[2] = re.sub('[^0-9.,\-]', '', geoLoc)
    result = '\t'.join(split) + '\n'
    ouf.write(result)

ouf.close()
inf.close()