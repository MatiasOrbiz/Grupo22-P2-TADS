import Entidades.Reporter;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
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
    }
}
