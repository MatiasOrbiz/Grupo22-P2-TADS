package Entidades;

import java.util.Set;

public class User {
    private long id;
    private String name;
    private Set<Tweet> tweets;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }
}