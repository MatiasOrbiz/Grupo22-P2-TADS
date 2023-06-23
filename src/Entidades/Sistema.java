package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Sistema {
    private static Set<Tweet> tweets = new HashSet<>();
    private static List<User> users = new ArrayList<>();;


    public static void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static Set<Tweet> getTweets() {
        return tweets;
    }

    public static List<User> getUsers() {
        return users;
    }
}
