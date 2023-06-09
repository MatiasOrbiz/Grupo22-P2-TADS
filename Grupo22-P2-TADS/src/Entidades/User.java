package Entidades;

import uy.edu.um.prog2.adt.LinkedList.MyList;



public class User  {

    private long id;
    private String location;
    private String description;
    private String created;


    public int getFavoritesCount() {
        return favoritesCount;
    }

    private String name;
    private int tweetsCount;
    private int favoritesCount;
    private boolean isVerified;
    private MyList<Tweet> tweets;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public MyList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public int getTweetsCount() {
        return tweetsCount;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setLocation(String s) {
        this.location = s ;
    }

    public void setDescription(String s) { this.description = s; }

    public void setCreated(String s) { this.created = s; }

    public void setFollowers(int parseInt) { this.tweetsCount = parseInt; }

    public void setFriends(int parseInt) { this.tweetsCount = parseInt; }



}


