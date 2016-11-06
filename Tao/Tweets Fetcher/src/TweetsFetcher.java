/*
 * Author: Yicong Tao
 * Reference: David Crowley: Simple Tweets collection using Twitter¡¯s Stream API and Java using Twitter4j
 * http://davidcrowley.me/?p=435
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    private String outputFileName = "tweets.txt";
    
    public void setOAuthConfig(String OAuthConfigFile) {
        
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        try {
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
    
    public void setOutputFile(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    
    public void getLatestTweet(int timeout) {
        try {
            TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
            PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");

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
                    
                    String username = status.getUser().getScreenName();
                    writer.println(username);
                    
                    String profileLocation = user.getLocation();
                    writer.println(profileLocation);
                    
                    long tweetId = status.getId(); 
                    writer.println(tweetId);
                    
                    String content = status.getText();
                    writer.println(content);
                    
                    writer.println();
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
//            FilterQuery fq = new FilterQuery();
        
//            String keywords[] = {"Donald Trump", "Hillary Clinton"};

//            fq.track(keywords);

//            twitterStream.filter(fq);  
            
            twitterStream.addListener(listener);
            twitterStream.sample();
            
            TimeUnit.SECONDS.sleep(timeout);
            
            twitterStream.shutdown();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        TweetsFetcher test = new TweetsFetcher();
        test.setOAuthConfig("OAuthConfig.txt");
        test.setOutputFile("tweets.txt");
        test.getLatestTweet(10);    // Get latest 10 seconds tweets
    }

}
