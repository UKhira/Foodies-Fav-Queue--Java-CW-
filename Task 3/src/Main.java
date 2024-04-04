import java.io.*;
import java.util.Scanner;
public class Main {
    static Scanner Userinput = new Scanner(System.in);
    public static void main(String[] args) {
        String [][] cashier = {
                {"X", "X", "X"},
                {"X", "X", "X"},
                {" ", "X", "X"},
                {" ", " ", "X"},
                {" ", " ", "X"}
        };
        boolean maintenance = true;

        while (maintenance) {
            consoleMenu();
            System.out.print("Enter Your Choice: ");
            String selectedOption = Userinput.next();
            switch (selectedOption.toUpperCase()) {
                case "100":
                case "VFQ":
                    FoodQueue.viewQueue(cashier);
                    break;
                case "101":
                case "VEQ":
                    FoodQueue.viewAllEmptyQueues(cashier);
                    break;
                case "102":
                case "ACQ":
                    FoodQueue.addMembers(cashier);
                    break;
                case "103":
                case "RCQ":
                    FoodQueue.removeCustomers(cashier);
                    break;
                case "104":
                case "PCQ":
                    FoodQueue.removeServedCustomers(cashier);
                    break;
                case "105":
                case "VCS":
                    Customer.sortCustomerNames();
                    break;
                case "106":
                case "SPD":
                    createFile();
                    saveDataToFile(cashier,Customer.getCustomerList());
                    break;
                case "107":
                case "LPD":
                    loadProgramDataFromFile();
                    break;
                case "108":
                case "STK":
                    System.out.println("Burgers in Stock = " + FoodQueue.getBurgerCount());
                    break;
                case "109":
                case "AFS":
                    addBurgersToStock();
                    break;
                case "110":
                case "IFQ":
                    FoodQueue.calculateIncome();
                    break;
                case "999":
                case "EXT":
                    System.out.println("Thank You !");
                    maintenance = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }

        }
    }

    private static void consoleMenu() {
        final int MENU_WIDTH = 29;
        for(int  rows = 0 ; rows < MENU_WIDTH; rows++) {
            System.out.print("_-");
        }
        System.out.printf("%n|%40s %17s %n","============ Menu ============", "|");
        String [] menuOptions = {"| 100 or VFQ: View all Queues.                            |",
                "| 101 or VEQ: View all Empty Queues.                      |",
                "| 102 or ACQ: Add customer to a Queue.                    |",
                "| 103 or RCQ: Remove a customer from a Queue.             |",
                "| 104 or PCQ: Remove a served customer.                   |",
                "| 105 or VCS: View Customers Sorted in alphabetical order |",
                "| 106 or SPD: Store Program Data into file.               |",
                "| 107 or LPD: Load Program Data from file.                |",
                "| 108 or STK: View Remaining burgers Stock.               |",
                "| 109 or AFS: Add burgers to Stock.                       |",
                "| 110 or IFQ: View the income of each queue               |",
                "| 999 or EXT: Exit the Program.                           |"};
        for (String options: menuOptions){
            System.out.println(options);
        }
//        System.out.println();
        for(int rows = 0 ; rows < MENU_WIDTH; rows++) {
            System.out.print("_-");
        }
        System.out.println();
    }

    private static void addBurgersToStock(){
        int currentStock = FoodQueue.getBurgerCount();
        if(currentStock == 50){
            System.out.println("Stock is full. cannot add more");
        }
        else {
            System.out.println("Enter the amount of burgers you want to add");
            int count = Userinput.nextInt();
            if (count + currentStock > 50){
                System.out.println("Exceeding stock. You can maximumly add " + (50 - currentStock) + " burgers only");
            }
            else{
                System.out.println("Successfully added " + count + " burgers to stock");
                FoodQueue.setBurgerCount(count);
            }
        }
    }

    private static void createFile(){
        try {
            File fileObj = new File("Foodies_Data.txt");
            if (fileObj.createNewFile()) {
                System.out.println("File created: " + fileObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }

    private static void saveDataToFile(String[][] queue, String [][] customerList) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Foodies_Data.txt"));
            fileWriter.write("This is how Foodies Fav Queues Current Cashier Queues look like");
            fileWriter.newLine();
            for (int rows = 0; rows < queue.length; rows++) {
                for (int columns = 0; columns < queue[rows].length; columns++) {
                    fileWriter.write(queue[rows][columns] + "   ");
                }
                fileWriter.newLine();
            }
            fileWriter.write("O - Occupied");
            fileWriter.newLine();
            fileWriter.write("X - Unoccupied");
            for (int blankLines = 0; blankLines < 5; blankLines++){             // to leave some blank spaces after storing one category of data
                fileWriter.newLine();
            }
            fileWriter.write("Currently " + FoodQueue.getBurgerCount() +" burgers in Stock");
            for (int blankLines = 0; blankLines < 5; blankLines++){
                fileWriter.newLine();
            }
            fileWriter.write("Customer Names View");
            fileWriter.newLine();
            for (int rows = 0; rows < customerList.length; rows++){
                for (int columns = 0; columns < customerList[rows].length; columns++){
                    if(customerList[rows][columns] != null){
                        fileWriter.write(" "+ customerList[rows][columns]);
                    }
                }
                fileWriter.newLine();
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /*
     * Read data from file
     */

    private static void loadProgramDataFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader("Foodies_Data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred while loading data from the file. \nSelect \" Store Program Data into file\" and try again");
        }
    }
}
