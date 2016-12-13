#This code is adpted from one of project team member Yicong Tao's code
import sys
import vaderSentiment

inf = open(sys.argv[1], 'r')
ouf = open(sys.argv[1] + '_senti', 'w')

count = 0
for line in inf:
    clean = line.rstrip()
    split = clean.split('\t')
    review = split[7]
    result = vaderSentiment.sentiment(review)
    ouf.write(clean + '\t' + str(result['neg']) + '\t' + str(result['neu']) + '\t' + str(result['pos']) + '\t' + str(result['compound']) + '\n')
    count += 1
    if count % 1000 == 0:
        print count

ouf.close()
inf.close()