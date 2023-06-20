package Entidades;

import java.util.Set;

public class User {
    private long id;

    public int getFavoritesCount() {
        return favoritesCount;
    }

    private String name;
    private int tweetsCount;
    private int favoritesCount;
    private boolean isVerified;
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

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
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

    public boolean isVerified() {
        return isVerified;
    }

    public int getTweetsCount() {
        return tweetsCount;
    }

    public void setTweetsCount(int tweetsCount) {
        this.tweetsCount = tweetsCount;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
