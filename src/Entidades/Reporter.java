package Entidades;
import java.time.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Set;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Reporter {
    public String[] getTopPilots(Set<Tweet> tweets,String month, String year){
        // Convert month and year to LocalDate
        LocalDate inputDate = LocalDate.of(Integer.parseInt(year), Month.valueOf(month.toUpperCase()), 1);
        // DateTimeFormatter to parse tweet dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // HashMap to count occurrences of pilots
        HashMap<String, Integer> counts = new HashMap<>();

        for (Tweet tweet : tweets) {
            // Parse tweet's date string to LocalDate
            LocalDate tweetDate = LocalDate.parse(tweet.getDate(), formatter);

            // Filter tweets from the given month and year
            if (tweetDate.getMonth() == inputDate.getMonth() && tweetDate.getYear() == inputDate.getYear()) {
                String pilot = tweet.getUser().getName(); // Assuming the pilot's name is in the name field of User
                counts.put(pilot, counts.getOrDefault(pilot, 0) + 1);
            }
        }

        // Sort the map by value in descending order
        List<Map.Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // Create an array of the top 10 (or fewer) pilots
        String[] topPilots = new String[Math.min(10, list.size())];
        for (int i = 0; i < topPilots.length; i++) {
            topPilots[i] = list.get(i).getKey() + ": " + list.get(i).getValue();
        }

        return topPilots;
    }

    public User[] getTopUsers(Set<Tweet> tweets){
        // HashMap to count occurrences of users
        HashMap<User, Integer> counts = new HashMap<>();

        for (Tweet tweet : tweets) {
            User user = tweet.getUser();
            counts.put(user, counts.getOrDefault(user, 0) + 1);
        }

        // Sort the map by value in descending order
        List<Map.Entry<User, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort(Map.Entry.<User, Integer>comparingByValue().reversed());

        // Create an array of the top 15 (or fewer) users
        List<User> topUsers = list.stream().limit(15).map(Map.Entry::getKey).collect(Collectors.toList());

        return topUsers.toArray(new User[0]);
    }
    public int getDifferentHashtags(Set<Tweet> tweets, String date){
        // Convert the string date to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        // HashSet to count unique hashtags
        HashSet<HashTag> uniqueHashtags = new HashSet<>();

        for (Tweet tweet : tweets) {
            // Parse tweet's date string to LocalDate
            LocalDate tweetDate = LocalDate.parse(tweet.getDate(), formatter);

            // Filter tweets from the given date
            if (tweetDate.isEqual(inputDate)) {
                uniqueHashtags.addAll(tweet.getHashTags());
            }
        }

        return uniqueHashtags.size();
    }
    public String getMostUsedHashtag(Set<Tweet> tweets, String date){
        // Convert the string date to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        // HashMap to count occurrences of hashtags
        HashMap<String, Integer> counts = new HashMap<>();

        for (Tweet tweet : tweets) {
            // Parse tweet's date string to LocalDate
            LocalDate tweetDate = LocalDate.parse(tweet.getDate(), formatter);

            // Filter tweets from the given date
            if (tweetDate.isEqual(inputDate)) {
                for (HashTag hashTag : tweet.getHashTags()) {
                    String hashTagText = hashTag.getText(); // assuming the text of the HashTag is gotten with getText()

                    // If the hashtag is not "#f1", count it
                    if (!hashTagText.equalsIgnoreCase("#f1")) {
                        counts.put(hashTagText, counts.getOrDefault(hashTagText, 0) + 1);
                    }
                }
            }
        }

        // Find the most used hashtag
        Map.Entry<String, Integer> mostUsed = null;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (mostUsed == null || mostUsed.getValue() < entry.getValue()) {
                mostUsed = entry;
            }
        }

        // If no hashtags were found, return an empty string
        if (mostUsed == null) {
            return "";
        }

        // Otherwise, return the most used hashtag
        return mostUsed.getKey();
    }
    public String[] getMostActiveUser(User[] users){
        // Create a priority queue with a custom comparator to sort users by favorites
        PriorityQueue<User> pq = new PriorityQueue<>(7, Comparator.comparing(User::getFavoritesCount).reversed());

        for (User user : users) {
            pq.offer(user);

            // Maintain the size of the queue to 7
            if (pq.size() > 7) {
                pq.poll();
            }
        }

        // Convert the queue to an array
        String[] topUsers = new String[pq.size()];
        for (int i = pq.size() - 1; i >= 0; i--) {
            User user = pq.poll();
            topUsers[i] = user.getName() + ": " + user.getFavoritesCount();
        }

        return topUsers;
    }
        public int getTweetsCount(Set<Tweet> tweets, String search){
            int count = 0;

            for (Tweet tweet : tweets) {
                // Check if the tweet's content contains the search string
                if (tweet.getContent().toLowerCase().contains(search.toLowerCase())) {
                    count++;
                }
            }

            return count;
        }
}

