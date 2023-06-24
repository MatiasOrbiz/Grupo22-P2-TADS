package Entidades;

import uy.edu.um.prog2.adt.Hash.MyHash;
import uy.edu.um.prog2.adt.Hash.MyHashImpl;
import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.LinkedList.MyList;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Reporter {
    private boolean isDriver(String driver, String description) {
        String[] words = description.split(" ");
        String[] drivers = driver.split(" ");

        for (String word : words) {
            if (word.equalsIgnoreCase(drivers[0]) || word.equalsIgnoreCase(drivers[1])) {
                return true;
            }
        }
        return false;
    }

    public MyList<Driver> getTopPilots(MyList<Tweet> tweets, String month, String year) {
        MyHash<String,Integer> counts = new MyHashImpl<>();

        try {
            File myFile = new File("drivers.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                counts.put(data, 0);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i = 0; i < tweets.size(); i++) {
            LocalDateTime date = tweets.get(i).getDate();
            if (date.getMonthValue() == Integer.parseInt(month) && date.getYear() == Integer.parseInt(year)) {
                String description = tweets.get(i).getContent();
                Iterator<String> iter = counts.iterator();

                while (iter.hasNext()) {
                    String entrada = iter.next();

                   if (isDriver(entrada, description)) {
                       counts.put(entrada, counts.get(entrada) + 1);
                   }
                }

            }
        }

        Iterator<String> iter = counts.iterator();
        MyList<Driver> drivers = new MiLinkedList<>();

        while (iter.hasNext()) {
            String entrada = iter.next();
            drivers.add(new Driver(entrada, counts.get(entrada)));
        }
        drivers.sort();

        return drivers;
    }


    public MyList<User> getTopUsers() {

        MyList<User> topUsers = Sistema.getUsers();
        UserTweetsComparator userComparator = new UserTweetsComparator();
        topUsers.sort(userComparator);

        return topUsers;
    }

    public MyList<User> getMostActiveUser() {

        MyList<User> topUsers = Sistema.getUsers();
        UserFavouritesComparator userComparator = new UserFavouritesComparator();
        topUsers.sort(userComparator);

        return topUsers;

    }
    public int getTweetsCount(MyList<Tweet> tweets, String search) {
        int count = 0;

        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getContent().toLowerCase().contains(search.toLowerCase())) {
                count++;
            }
        }

        return count;
    }


    public int getDifferentHashtags(MyList<Tweet> tweets, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        MyHash<String, Integer> uniqueHashtags = new MyHashImpl<>();

        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getDate().getDayOfMonth() == inputDate.getDayOfMonth()
                    && tweets.get(i).getDate().getMonthValue() == inputDate.getMonthValue()
                    && tweets.get(i).getDate().getYear() == inputDate.getYear()) {

                for (int j = 0; j < tweets.get(i).getHashTags().size(); j++) {
                    String hashtag = tweets.get(i).getHashTags().get(j).getText();
                    uniqueHashtags.put(hashtag, 1);
                }
            }
        }

        return uniqueHashtags.size();
    }

    public String getMostUsedHashtag(MyList<Tweet> tweets, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        MyHash<String, Integer> counts = new MyHashImpl<>();

        String mostUsedHashtag = "";
        Integer mostUsedCount = 0;
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getDate().getDayOfMonth() == inputDate.getDayOfMonth()
                    && tweets.get(i).getDate().getMonthValue() == inputDate.getMonthValue()
                    && tweets.get(i).getDate().getYear() == inputDate.getYear()) {

                for (int j = 0; j < tweets.get(i).getHashTags().size(); j++) {
                    String hashTag = tweets.get(i).getHashTags().get(j).getText();
                    if (!hashTag.equalsIgnoreCase("f1")) {
                        Integer oldValue = counts.get(hashTag);
                        if (oldValue == null) {
                            oldValue = 0;
                        }
                        counts.put(hashTag,  oldValue+ 1);
                        if (mostUsedCount < oldValue+ 1) {
                            mostUsedCount = oldValue+ 1;
                            mostUsedHashtag = hashTag;
                        }
                    }
                }
            }
        }

        return mostUsedHashtag;
    }

    }


