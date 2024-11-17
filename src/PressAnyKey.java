import java.util.Scanner;

public class PressAnyKey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Espera a que el usuario presione Enter
        System.out.println("Continuando el programa...");
        scanner.close();
    }
}

