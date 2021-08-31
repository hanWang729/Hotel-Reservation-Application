package menu;

import java.util.Scanner;

public class MainMenu {
    public void getStart(){
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println("Main Menu\n---------------------------------------");
            System.out.println("1. Find and reserve a room\n"+
                    "2. See my reservation\n"+
                    "3. Create an account\n" +
                    "4. Admin\n"+
                    "5. Exit\n");

            Scanner scanner = new Scanner(System.in);
            int selection = Integer.parseInt(scanner.next());
            switch (selection){
                case 1:
                    System.out.println("Find and reserve a room");
                    break;
                case 2:
                    System.out.println("See my reservation");
                    break;
                case 3:
                    System.out.println("Create an account");
                    break;
                case 4:
                    System.out.println("Admin");
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.getStart(scanner);
                    break;
                case 5:
                    System.out.println("Exit");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Error, please input an interger between 1 to 5");
            }
        }

    }
}
