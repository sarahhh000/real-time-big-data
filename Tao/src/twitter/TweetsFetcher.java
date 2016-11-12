/*
 * Author: Yicong Tao
 * Reference: David Crowley: Simple Tweets collection using Twitter Stream API and Java using Twitter4j
 * http://davidcrowley.me/?p=435
 */
package twitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TweetsFetcher {

    private ConfigurationBuilder cb;

    public void setOAuthConfig(String OAuthConfigFile) {

        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        try {

            System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
            Scanner sc = new Scanner(new File(OAuthConfigFile));
            while (sc.hasNextLine()) {
                switch (sc.next()) {
                case "OAuthConsumerKey":
                    cb.setOAuthConsumerKey(sc.next());
                    break;

                case "OAuthConsumerSecret":
                    cb.setOAuthConsumerSecret(sc.next());
                    break;

                case "OAuthAccessToken":
                    cb.setOAuthAccessToken(sc.next());
                    break;

                case "OAuthAccessTokenSecret":
                    cb.setOAuthAccessTokenSecret(sc.next());
                    break;

                default:
                    System.err.println("The configuration file is not correct!");
                    System.exit(1);
                    break;
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void getLatestTweet(String[] keywords) {

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();

                String username = user.getScreenName();
                System.out.print(username);

                String profileLocation = user.getLocation();
                System.out.print("\t" + profileLocation);

                long tweetId = status.getId();
                System.out.print("\t" + tweetId);

                String content = status.getText();
                content.replace("\n", " ");
                content.replace("\r", "");
                System.out.print("\t" + content);

                System.out.println();
            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub
            }

        };

        FilterQuery fq = new FilterQuery();

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);

    }

    public static void main(String[] args) {
        TweetsFetcher test = new TweetsFetcher();
        String[] keywords = {"restaurant"};
        test.setOAuthConfig("OAuthConfig");
        test.getLatestTweet(keywords);    // Get latest 10 seconds tweets
    }

}
