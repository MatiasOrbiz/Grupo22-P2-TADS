package Entidades;

import java.util.Set;

public class Reporter {
    public String[] getTopPilots(Set<Tweet> tweets,String month, String year){
        return new String[0];

        //
    }
    public User[] getTopUsers(Set<Tweet> tweets){
        return new User[0];
    }
    public int getDifferentHashtags(Set<Tweet> tweets, String date){
        return 0;
    }
    public String getMostUsedHashtag(Set<Tweet> tweets, String date){
        return "";
    }
    public String[] getMostActiveUser(User[] users){
        return new String[0];
        //buscar users que tengan mas favoritos.
    }
    public int getTweetsCount(Set<Tweet> tweets, String search){
        return 0;
        //devolver cantidad de tweets que contengan la palabra search
    }
}

