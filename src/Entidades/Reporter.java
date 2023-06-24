package Entidades;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class Reporter {
    private boolean isDriver(String driver, String description) {
        String[] words = description.split(" ");
        String[] drivers = driver.split(" ");

        for (String word : words) {
            if (word.toLowerCase().equals(drivers[0].toLowerCase()) || word.toLowerCase().equals(drivers[1].toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> getTopPilots(Set<Tweet> tweets, String month, String year) {
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
                String description = tweet.getContent();
                for (String driver : counts.keySet()) {
                    if (isDriver(driver, description)) {
                        counts.put(driver, counts.get(driver) + 1);
                    }
                }

            }
        }
        List<String, Integer> list = new ArrayList<>() ;
        for (String driver : counts.keySet()) {
            list.add(driver, counts.get(driver));
        }

    }


    public List<User> getTopUsers() {

        List<User> topUsers = Sistema.getUsers();
        UserTweetsComparator userComparator = new UserTweetsComparator();
        Collections.sort(topUsers, userComparator);
        return topUsers.subList(topUsers.size() - 15, topUsers.size());


    }

    public String[] getMostActiveUser(List<User> users) {
        PriorityQueue<User> pq = new PriorityQueue<>(7, Comparator.comparing(User::getFavoritesCount));

        for (User user : users) {
            pq.offer(user);

            if (pq.size() > 7) {
                pq.poll();
            }
        }
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
            if (tweet.getContent().toLowerCase().contains(search.toLowerCase())) {
                count++;
            }
        }

        return count;
    }


    public int getDifferentHashtags(Set<Tweet> tweets, String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        HashSet<HashTag> uniqueHashtags = new HashSet<>();

        for (Tweet tweet : tweets) {
            if (tweet.getDate().getDayOfMonth() == inputDate.getDayOfMonth()
                    && tweet.getDate().getMonthValue() == inputDate.getMonthValue()
                    && tweet.getDate().getYear() == inputDate.getYear()) {

                uniqueHashtags.addAll(tweet.getHashTags());
            }
        }

        return uniqueHashtags.size();
    }


    public String getMostUsedHashtag(Set<Tweet> tweets, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        HashMap<String, Integer> counts = new HashMap<>();

        for (Tweet tweet : tweets) {

            if (tweet.getDate().getDayOfMonth() == inputDate.getDayOfMonth()
                    && tweet.getDate().getMonthValue() == inputDate.getMonthValue()
                    && tweet.getDate().getYear() == inputDate.getYear()) {
                for (HashTag hashTag : tweet.getHashTags()) {
                    String hashTagText = hashTag.getText();

                    if (!hashTagText.equalsIgnoreCase("f1")) {
                        counts.put(hashTagText, counts.getOrDefault(hashTagText, 0) + 1);
                    }
                }
            }
        }
        Map.Entry<String, Integer> mostUsed = null;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (mostUsed == null || mostUsed.getValue() < entry.getValue()) {
                mostUsed = entry;
            }
        }
        if (mostUsed == null) {
            return "";
        }

        return mostUsed.getKey();
    }

    }


