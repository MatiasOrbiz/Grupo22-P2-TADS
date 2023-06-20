package services;

import Entidades.Sistema;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import Entidades.Tweet;
import Entidades.User;
import au.com.bytecode.opencsv.CSVReader;

public class CSVDataLoader {

    public void loadCSVData(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath));) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 13) {
                    User user = new User(Long.parseLong(nextLine[1]), nextLine[2]);
                    user.setVerified(Boolean.parseBoolean(nextLine[9]));
                    Sistema.getUsers().add(user);

                    Tweet tweet = new Tweet(Long.parseLong(nextLine[8]), nextLine[10], nextLine[12], Boolean.parseBoolean(nextLine[13]));
                    tweet.setUser(user);
                    tweet.setDate(nextLine[9]);
                    Sistema.getTweets().add(tweet);

                    user.getTweets().add(tweet);
                }   else {
                    System.out.println("user_name: " + nextLine[0]
                            + ", user_location: " + nextLine[1]
                            + ", user_description: " + nextLine[2]
                            + ", user_created: " + nextLine[3]
                            + ", user_followers: " + nextLine[4]
                            + ", user_friends: " + nextLine[5]
                            + ", user_favourites: " + nextLine[6]
                            + ", user_verified: " + nextLine[7]
                            + ", date: " + nextLine[8]
                            + ", text: " + nextLine[9]
                            + ", hashtags: " + nextLine[10]
                            + ", source: " + nextLine[11]
                            + ", is_retweet: " + nextLine[12]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Tweet> getTweets() {
        return null;
    }

    public Set<User> getUsers() {
        return null;
    }
}