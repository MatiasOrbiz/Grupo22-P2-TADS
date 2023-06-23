import Entidades.Reporter;
import Entidades.Sistema;
import Entidades.Tweet;
import Entidades.User;
import services.CSVDataLoader;
import java.util.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        CSVDataLoader loader = new CSVDataLoader();
        loader.loadCSVData("f1_dataset_test.csv");
        Set<Tweet> tweets = Sistema.getTweets(); // Asume que tienes un método para obtener los tweets.
        Set<User> users = loader.getUsers(); // Asume que tienes un método para obtener los usuarios.
        Reporter reporter = new Reporter();
        Scanner s = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de reportes de Twitter.");
        System.out.println("1-topPilots");
        System.out.println("2-topUsers");
        System.out.println("3-differentHashtags");
        System.out.println("4-mostUsedHashtag");
        System.out.println("5-mostActiveUser");
        System.out.println("6-tweetsCount");
        String input = s.nextLine();

        switch (input) {
            case "1":
                System.out.println("Ingrese el mes y el año (en formato 'MM yyyy'):");
                String monthYear = s.nextLine();
                String[] parts = monthYear.split(" ");
                HashMap<String, Integer> topPilots = reporter.getTopPilots(tweets, parts[0], parts[1]);
                System.out.println(Arrays.toString(new HashMap[]{topPilots}));
                break;
            case "2":
                List<User> topUsers = reporter.getTopUsers();
                for (User user : topUsers) {
                    System.out.println(user.getName() + ", tweets: " + user.getTweetsCount() + ", verified: " + user.isVerified());
                }
                break;

            case "3":
                /*System.out.println("Ingrese la fecha (en formato 'yyyy-MM-dd'):");
                String date = s.nextLine();
                int count = reporter.getDifferentHashtags(tweets, date);
                System.out.println("Cantidad de hashtags distintos: " + count);*/
                break;

            case "4":
              /*  System.out.println("Ingrese la fecha (en formato 'yyyy-MM-dd'):");
                date = s.nextLine();
                String hashtag = reporter.getMostUsedHashtag(tweets, date);
                System.out.println("Hashtag más usado: " + hashtag);*/
                break;

            case "5":
//                String[] activeUsers = reporter.getMostActiveUser(users.toArray(new User[0]));
//                for (String activeUser : activeUsers) {
//                    System.out.println(activeUser);
//                }
                break;

            case "6":
//                System.out.println("Ingrese la palabra o frase a buscar:");
//                String search = s.nextLine();
//                int tweetsCount = reporter.getTweetsCount(tweets, search);
//                System.out.println("Cantidad de tweets que contienen '" + search + "': " + tweetsCount);
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }

        s.close();
    }


}