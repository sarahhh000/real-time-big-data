import sys
import vaderSentiment

inf = open(sys.argv[1], 'r')
ouf = open(sys.argv[1] + '_senti', 'w')
ouf.write('user.name\ttweet.location\ttweet.geoLocation\ttweet.retweet\ttweet.content\tblock.num')
ouf.write('senti.negative\tsenti.neutral\tsenti.positive\tsenti.compound\n')

for line in inf:
    clean = line.rstrip()
    split = clean.split('\t')
    tweet = split[4]
    result = vaderSentiment.sentiment(tweet)
    ouf.write(clean + '\t' + str(result['neg']) + '\t' + str(result['neu']) + '\t' + str(result['pos']) + '\t' + str(result['compound']) + '\n')

ouf.close()
inf.close()