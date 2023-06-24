package Entidades;

import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.LinkedList.MyList;



public class Sistema {
    private static MyList<Tweet> tweets = new MiLinkedList<>();
    private static MyList<User> users = new MiLinkedList<>();


    public static void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static MyList<Tweet> getTweets() {
        return tweets;
    }

    public static MyList<User> getUsers() {
        return users;
    }
}
