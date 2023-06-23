package services;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import Entidades.Sistema;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import Entidades.Tweet;
import Entidades.User;
import au.com.bytecode.opencsv.CSVReader;

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

                    System.out.println("id: " + nextLine[0]
                            + ",user_name: " + nextLine[1]
                            + ", user_location: " + nextLine[2]
                            + ", user_description: " + nextLine[3]
                            + ", user_created: " + nextLine[4]
                            + ", user_followers: " + nextLine[5]
                            + ", user_friends: " + nextLine[6]
                            + ", user_favourites: " + nextLine[7]
                            + ", user_verified: " + nextLine[8]
                            + ", date: " + nextLine[9]
                            + ", text: " + nextLine[10]
                            + ", hashtags: " + nextLine[11]
                            + ", source: " + nextLine[12]
                            + ", is_retweet: " + nextLine[13]);



                    //mapeo usuario y tweet
                    User user = new User(Long.parseLong(nextLine[0]), nextLine[1]);
                    user.setLocation(nextLine[2]);
                    user.setDescription(nextLine[3]);
                    user.setCreated(nextLine[4]);

                    int followers = Integer.parseInt(nextLine[5].replace(".", ""));

                    user.setFollowers(Integer.parseInt(String.valueOf(followers)));

                    int friends = Integer.parseInt(nextLine[6].replace(".", ""));

                    user.setFriends(Integer.parseInt(String.valueOf(friends)));

                    int favorites = Integer.parseInt(nextLine[7].replace(".", ""));

                    user.setFavoritesCount(Integer.parseInt(String.valueOf(favorites)));




                    user.setVerified(Boolean.parseBoolean(nextLine[8]));

                    int number = Integer.parseInt(nextLine[7].replace(".", ""));


                    Tweet tweet = new Tweet(Long.parseLong(String.valueOf(number)), nextLine[10], nextLine[11], Boolean.parseBoolean(nextLine[12]));

                    tweet.setUser(user);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(nextLine[9], formatter);
                    tweet.setDate(dateTime);

                    List<User> usuarios = Sistema.getUsers();
                    boolean existeUsuario = usuarios.contains(user);

                    if (!existeUsuario) {
                        System.out.println("no existe usuario");
                        System.out.println("user" + user);
                        Set<Tweet> tweets = user.getTweets();
                        if (tweets == null) {
                            tweets = new HashSet<>();
                        }
                        tweets.add(tweet);
                        user.setTweets(tweets);
                        Sistema.addUser(user);
                    }

                    if (existeUsuario) {
                        System.out.println("existe usuario");
                        System.out.println("user" + user);
                        User usuarioencontado =
                                usuarios.stream()
                                        .filter(usuario -> usuario.getId() == user.getId())
                                        .findFirst().get();
                        Set<Tweet> tweets = usuarioencontado.getTweets();
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

    public Set<Tweet> getTweets() {
        return null;
    }


    public Set<User> getUsers() {
        return null;
    }
}