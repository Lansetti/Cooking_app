import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        App console = new App();
        console = console.mainMenu(console);
        System.out.println("Application has been shut down");
    }

    private App mainMenu(App console) {
        System.out.println("Welcome to the console application");
        Scanner scan = new Scanner(System.in);
        int selection = 0;

        do {
            System.out.println("[1] Create New recipe");
            System.out.println("[2] Modify recipe");
            System.out.println("[3] Access All the recipes");
            System.out.println("[4] Quit");

            System.out.print("Insert selection: ");
            selection = scan.nextInt();
            switch (selection) {
            case 1: return console.submenu1(console);
            case 2: return console.submenu2(console);
            case 3: return console.submenu3(console);
            case 4: return console;
            default:
                System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        scan.close();
        return console;
    }

    private App submenu1(App console) {
        System.out.println("Welcome to the SUBMENU");
        Scanner scan = new Scanner(System.in);
        int selection = 0;

        do {
            System.out.println("[1] SUBMENU_1");
            System.out.println("[2] SUBMENU_2");
            System.out.println("[3] SUBMENU_3");
            System.out.println("[4] Return");

            System.out.print("Insert selection: ");
            selection = scan.nextInt();

            switch (selection) {
            case 1: return console.submenu1(console);
            case 2: return console.submenu1(console);
            case 3: return console.submenu1(console);
            case 4: return console.mainMenu(console);
            default:
                System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        scan.close();
        return console;
    }

    private App submenu2(App console) {
        System.out.println("Welcome to the SUBMENU");
        Scanner scan = new Scanner(System.in);
        int selection = 0;

        do {
            System.out.println("[1] SUBMENU_21");
            System.out.println("[2] SUBMENU_22");
            System.out.println("[3] SUBMENU_23");
            System.out.println("[4] Return");

            System.out.print("Insert selection: ");
            selection = scan.nextInt();

            switch (selection) {
            case 1: return console.submenu2(console);
            case 2: return console.submenu2(console);
            case 3: return console.submenu2(console);
            case 4: return console.mainMenu(console);
            default:
                System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        scan.close();
        return console;
    }
    
    private App submenu3(App console) {
        System.out.println("Welcome to the SUBMENU");
        Scanner scan = new Scanner(System.in);
        int selection = 0;

        do {
            System.out.println("[1] SUBMENU_31");
            System.out.println("[2] SUBMENU_32");
            System.out.println("[3] SUBMENU_33");
            System.out.println("[4] Return");

            System.out.print("Insert selection: ");
            selection = scan.nextInt();

            switch (selection) {
            case 1: return console.submenu2(console);
            case 2: return console.submenu2(console);
            case 3: return console.submenu2(console);
            case 4: return console.mainMenu(console);
            default:
                System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        scan.close();
        return console;
    }
}

// ⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣶⣿⣿⣿⣿⣿⣿⠿⠷⣶⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀
// ⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣯⣀⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀
// ⠀⠀⠀⢠⣿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣁⣈⣽⣿⣷⡀⠀⠀⠀
// ⠀⠀⠀⣿⣿⣶⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⢿⣧⠀⠀⠀
// ⠀⠀⠀⠛⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠧⠤⠾⠿⠿⠿⠿⠿⠷⠶⠾⠟⠀⠀⠀
// ⠀⠀⠀⢶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⠶⠶⠀⠀⠀
// ⠀⠀⣠⣤⣤⣤⣤⣤⣤⣄⣀⣀⣈⣉⣉⣉⣀⣀⣀⣀⣀⣠⣤⣤⣤⣤⣤⣄⠀⠀
// ⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀
// ⠀⠀⢀⣤⣭⠉⠉⠉⢉⣉⡉⠉⠉⠉⣉⣉⠉⠉⠉⢉⣉⠉⠉⠉⢉⣭⣄⠀⠀⠀
// ⠀⠰⡟⠁⠈⢷⣤⣴⠟⠉⠻⣄⣠⡾⠋⠙⠳⣤⣴⠟⠉⠳⣦⣠⡾⠃⠙⢷⡄⠀
// ⠀⠀⠀⢀⣀⣀⣉⡀⠀⠀⠀⠈⠉⠀⠀⠀⣀⣈⣁⣀⣀⣀⣀⣉⣀⣀⠀⠀⠀⠀
// ⠀⠀⠀⠛⠛⠛⠛⠛⠛⠻⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠛⠛⠛⠛⠛⠛⠛⠃⠀⠀
// ⠀⠀⠀⢸⣿⣿⣿⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀
                                    