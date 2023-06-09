package Entidades;

import uy.edu.um.prog2.adt.LinkedList.MyList;

import java.time.LocalDateTime;
import java.util.Set;

public class Tweet {
    public Tweet(long id, String content, String source, boolean isRetweet, LocalDateTime date, MyList<HashTag> hashTags, User user) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.date = date;
        this.hashTags = hashTags;
        this.user = user;
    }
    private long id;
    private String content;
    private String source;
    private boolean isRetweet;
    private LocalDateTime date;
    private MyList<HashTag> hashTags;
    private User user;


    public Tweet(long id, String content, String source, boolean isRetweet) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public MyList<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(MyList<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

