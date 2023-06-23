package Entidades;
import java.time.*;
import java.util.*;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class Reporter {
    private boolean isDriver (String driver, String description ) {
        String[] words = description.split(" ");
        System.out.println(description);
        String[] drivers = driver.split(" ");

        for (String word : words) {
            if (word.toLowerCase().equals(drivers[0].toLowerCase()) || word.toLowerCase().equals(drivers[1].toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> getTopPilots(Set<Tweet> tweets, String month, String year){
        // HashMap to count occurrences of users
        HashMap<String, Integer> counts = new HashMap<>();
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


        for (Tweet tweet : tweets) {
            LocalDateTime date = tweet.getDate();
            if (date.getMonthValue() == Integer.parseInt(month) && date.getYear() == Integer.parseInt(year)) {
                //System.out.println(tweet.getContent());
                String description = tweet.getContent();
                for (String driver : counts.keySet()) {
                   // System.out.println(driver.length());
                    if (isDriver(driver, description)) {
                       // System.out.println(driver.length());
                        counts.put(driver, counts.get(driver) + 1);
                    }
                }

            }
        }
        return counts;
    }


    public List<User> getTopUsers(){

        List<User> topUsers = Sistema.getUsers();
        Collections.sort(topUsers);

        return topUsers.subList(topUsers.size()-15, topUsers.size());


        //return topUsers.toArray(new User[0]);
    }
    /*
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
        /
     */
}

