package twitter;
import java.util.Arrays;

public class FetchOnLocation {
    public static void main(String[] args) {
        System.err.println("Working Directory = " + System.getProperty("user.dir"));
        System.err.println("Locations = " + Arrays.toString(args));

        double[][] locations = new double[args.length][2];
        for (int i = 0; i < args.length; i++) {
            String[] split = args[i].split(",");
            locations[i][0] = Double.parseDouble(split[0]);
            locations[i][1] = Double.parseDouble(split[1]);
        }

        TweetsFetcher fetcher = new TweetsFetcher();
        fetcher.locations = locations;
        fetcher.getLatestTweet();
    }
}