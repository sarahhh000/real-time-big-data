/*
 * Author: Yicong Tao
 * Reference: David Crowley: Simple Tweets collection using Twitter Stream API and Java using Twitter4j
 * http://davidcrowley.me/?p=435
 */
package twitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.Place;

public class TweetsFetcher {

    public void getLatestTweet(String[] keywords) {

            TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            StatusListener listener = new StatusListener() {

                private int tweetCount = 0;
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

                @Override
                public void onException(Exception arg0) {
                    arg0.printStackTrace();
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

                    System.out.print(user.getScreenName());
                    System.out.print("\t" + user.getLocation());

                    Place place = status.getPlace();
                    if (place != null) {
                        System.out.print("\t" + place.getFullName());
                    } else {
                        System.out.print("\t" + "null");
                    }

                    System.out.print("\t" + Boolean.toString(status.isRetweeted()));

                    String content = status.getText();
                    content = content.replaceAll("\\s+", " ");
                    System.out.print("\t" + content);

                    System.out.println();

                    if ((++tweetCount) % 50 == 0) {
                        Calendar calobj = Calendar.getInstance();
                        System.err.println("[" + df.format(calobj.getTime()) + "] Acquired " + tweetCount + " tweets.");
                    }
                }

                @Override
                public void onTrackLimitationNotice(int arg0) {
                    System.err.println("Limit notices: stream predicate matches more Tweets than its current rate limit.");
                    System.err.println("Current number of undelivered Tweets: " + Integer.toString(arg0));
                }

                @Override
                public void onStallWarning(StallWarning arg0) {
                    System.err.println(arg0.getMessage());
                }
            };

            FilterQuery fq = new FilterQuery();

            fq.track(keywords);
            fq.language("en");

            twitterStream.addListener(listener);
            twitterStream.filter(fq);

    }

    public static void main(String[] args) {
        System.err.println("Working Directory = " + System.getProperty("user.dir"));
        System.err.println("Keywords = " + Arrays.toString(args));
        TweetsFetcher test = new TweetsFetcher();
        String[] keywords = args;
        test.getLatestTweet(keywords);
    }

}
