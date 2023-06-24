package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import Entidades.HashTag;
import Entidades.Sistema;
import java.io.FileReader;
import java.io.IOException;


import Entidades.Tweet;
import Entidades.User;
import au.com.bytecode.opencsv.CSVReader;
import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.LinkedList.MyList;

public class CSVDataLoader {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void loadCSVData(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath));) {
            String[] nextLine;
            boolean firstLine = true;

            while ((nextLine = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (nextLine.length == 14) {
                    if (!isNumeric(nextLine[0])) {
                        continue;
                    }
                    User user = new User(Long.parseLong(nextLine[0]), nextLine[1]);
                    user.setLocation(nextLine[2]);
                    user.setDescription(nextLine[3]);
                    user.setCreated(nextLine[4]);

                    int followers = Integer.parseInt(nextLine[5].replace(".", ""));

                    user.setFollowers(Integer.parseInt(String.valueOf(followers)));

                    if (!isNumeric(nextLine[6])) {
                        continue;
                    }

                    int friends = Integer.parseInt(nextLine[6].replace(".", ""));

                    user.setFriends(Integer.parseInt(String.valueOf(friends)));

                    int favorites = Integer.parseInt(nextLine[7].replace(".", ""));

                    user.setFavoritesCount(Integer.parseInt(String.valueOf(favorites)));

                    user.setVerified(Boolean.parseBoolean(nextLine[8]));

                    int number = Integer.parseInt(nextLine[7].replace(".", ""));

                    Tweet tweet = new Tweet(Long.parseLong(String.valueOf(number)), nextLine[10], nextLine[11], Boolean.parseBoolean(nextLine[12]));
                    nextLine[11] = nextLine[11].replace("[", "").replace("]", "").replace("'", "").replace(" ", "");
                    String[] arr = nextLine[11].split(",");

                    MyList<HashTag> set = new MiLinkedList<>();

                    for (int i = 0; i < arr.length; i++) {
                        HashTag hashTag = new HashTag();
                        hashTag.setText(arr[i]);
                        set.add(hashTag);
                    }
                    tweet.setHashTags(set);

                    tweet.setUser(user);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(nextLine[9], formatter);
                    tweet.setDate(dateTime);

                    MyList<User> usuarios = Sistema.getUsers();
                    boolean existeUsuario = usuarios.contains(user);

                    if (!existeUsuario) {
                        MyList<Tweet> tweets = user.getTweets();
                        if (tweets == null) {
                            tweets = new MiLinkedList<>();
                        }
                        tweets.add(tweet);
                        user.setTweets(tweets);
                        Sistema.addUser(user);
                    }

                    if (existeUsuario) {

                        User usuarioencontado = null;
                        for (int i = 0; i < Sistema.getUsers().size(); i++) {
                            if (Sistema.getUsers().get(i).getId() == user.getId()) {
                                usuarioencontado  = Sistema.getUsers().get(i);
                                break;
                            }
                        }

                        MyList<Tweet> tweets = usuarioencontado.getTweets();
                        tweets.add(tweet);
                        usuarioencontado.setTweets(tweets);
                    }


                    Sistema.addTweet(tweet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}