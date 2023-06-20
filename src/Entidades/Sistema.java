package Entidades;

import java.util.Set;
import java.util.HashSet;

public class Sistema {
    private static Set<Tweet> tweets = new HashSet<>();
    private static Set<User> users = new HashSet<>();;


    public static void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static Set<Tweet> getTweets() {
        return tweets;
    }

    public static Set<User> getUsers() {
        return users;
    }
}
