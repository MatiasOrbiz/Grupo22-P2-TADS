import Entidades.*;
import services.CSVDataLoader;
import uy.edu.um.prog2.adt.LinkedList.MyList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Runnable loadDataOperation = () -> {
            CSVDataLoader loader = new CSVDataLoader();
            System.out.println("Cargando datos...");
            loader.loadCSVData("f1_dataset_test.csv");
        };

        calcularEficiencia(loadDataOperation);

        Reporter reporter = new Reporter();
        Scanner s = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de reportes de Twitter.");

        do {
            System.out.println("1-topPilots");
            System.out.println("2-topUsers");
            System.out.println("3-differentHashtags");
            System.out.println("4-mostUsedHashtag");
            System.out.println("5-mostActiveUser");
            System.out.println("6-tweetsCount");
            System.out.println("0-Salir");
            String input = s.nextLine();


            switch (input) {
                case "0":
                    System.out.println("Gracias por usar el sistema de reportes de Twitter.");
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Ingrese el mes y el año (en formato 'MM yyyy'):");
                    String monthYear = s.nextLine();
                    String[] parts = monthYear.split(" ");
                    MyList<Driver> topPilots = reporter.getTopPilots(Sistema.getTweets(), parts[0], parts[1]);
                    for (int i = 0; i <15 ; i++) {
                        System.out.println(topPilots.get(i).getNombre() + ", tweets: " + topPilots.get(i).getCantidad() );
                    }
                    break;
                case "2":
                    MyList<User> topUsers = reporter.getTopUsers();
                    for (int i = 0; i <15; i++) {
                        System.out.println(topUsers.get(i).getName() + ", tweets: " + topUsers.get(i).getTweetsCount() + ", verified: " + topUsers.get(i).isVerified());
                    }

                    break;

                case "3":
                    System.out.println("Ingrese la fecha (en formato 'yyyy-MM-dd'):");
                    String date = s.nextLine();
                    int count = reporter.getDifferentHashtags(Sistema.getTweets(), date);
                    System.out.println("Cantidad de hashtags distintos: " + count);
                    break;

                case "4":
                    System.out.println("Ingrese la fecha (en formato 'yyyy-MM-dd'):");
                    date = s.nextLine();
                    String hashtag = reporter.getMostUsedHashtag(Sistema.getTweets(), date);
                    System.out.println("Hashtag más usado: " + hashtag);
                    break;

                case "5":
                    MyList<User> activeUsers = reporter.getMostActiveUser();
                    for (int i = 0; i <7; i++) {
                        System.out.println(activeUsers.get(i).getName() + "Favourites : " + activeUsers.get(i).getFavoritesCount() );
                    }
                    break;

                case "6":
                    System.out.println("Ingrese la palabra o frase a buscar:");
                    String search = s.nextLine();
                    int tweetsCount = reporter.getTweetsCount(Sistema.getTweets(), search);
                    System.out.println("Cantidad de tweets que contienen '" + search + "': " + tweetsCount);
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }


        }while (true);

    }
    public static void calcularEficiencia(Runnable code) {
        // Ejecuta el recolector de basura
        System.gc();
        Runtime.getRuntime().gc();

        // Tiempo de inicio
        long startTime = System.nanoTime();

        // Ejecuta el código
        code.run();

        // Tiempo de finalización
        long endTime = System.nanoTime();

        // Calcula la duración
        long duration = endTime - startTime;

        // Recopila estadísticas de memoria
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        long usedMemory = totalMemory - rt.freeMemory();

        // Imprime los resultados
        System.out.println("Cantidad de memoria RAM consumida: " + usedMemory + " bytes");
        System.out.println("Tiempo de ejecución: " + (duration / 1e6) + " milisegundos");
    }
}
