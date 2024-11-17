package Presentation;

import java.util.Scanner;

public class Input {
    static private Scanner sc;

    public Input() {
        sc = new Scanner(System.in);
    }

    public int askInteger(String prompt){
        int integer;
        try{
            System.out.print(prompt);
            integer = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            System.out.println("\tInvalid option!!\n");
            return askInteger(prompt);
        }
        return integer;
    }

    public static String askString(String prompt){
        String string;
        System.out.print(prompt);
        string = sc.nextLine();
        return string;
    }

    public void pressAnyKeyToContinue(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n<Press any key to continue...>");
        scanner.nextLine(); // Espera a que el usuario presione Enter
    }
}
