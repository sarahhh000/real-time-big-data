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
import twitter4j.GeoLocation;

public class TweetsFetcher {

    public String[] language;
    public double[][] locations;
    public String[] keywords;

    public TweetsFetcher() {
        language = new String[1];
        language[0] = "en";
    }

    public void getLatestTweet() {

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

                    Place place = status.getPlace();
                    if (place != null) {
                        System.out.print("\t" + place.getFullName());
                    } else {
                        System.out.print("\t" + "null");
                    }

                    GeoLocation geoloc = status.getGeoLocation();
                    if (geoloc != null) {
                        System.out.print("\t" + geoloc.getLongitude() + "," + geoloc.getLatitude());
                    } else {
                        System.out.print("\t" + "null");
                    }

                    System.out.print("\t" + Boolean.toString(status.isRetweeted()));

                    String content = status.getText();
                    // remove whitespace characters (etc. \n, \r, \t) inside the tweets
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

            fq.language(language);
            if (locations != null) fq.locations(locations);
            if (keywords != null) fq.track(keywords);

            twitterStream.addListener(listener);
            twitterStream.filter(fq);

    }

}
