import sys
import vaderSentiment

inf = open(sys.argv[1], 'r')
ouf = open(sys.argv[1] + '-vader', 'w')
ouf.write('user.name\tuser.location\ttweet.location\ttweet.retweet\ttweet.content\t')
ouf.write('vader.negative\tvader.neutral\tvader.positive\tvader.compound\n')

for line in inf:
    clean = line.rstrip()
    split = clean.split('\t')
    tweet = split[-1]
    result = vaderSentiment.sentiment(tweet)
    ouf.write(clean + '\t' + str(result['neg']) + '\t' + str(result['neu']) + '\t' + str(result['pos']) + '\t' + str(result['compound']) + '\n')

ouf.close()
inf.close()