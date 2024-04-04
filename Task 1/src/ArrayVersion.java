import java.util.Scanner;           // Import Scanner for user inputs
import java.io.File;                // Import the File class
import java.io.IOException;         // Import the IOException class to handle errors
import java.io.FileWriter;          // Import the FileWriter class
import java.io.FileReader;          // Import the FileReader Class
import java.io.BufferedWriter;      // Import the BufferedWriter class for Writer Instance
import java.io.BufferedReader;      // Import the BufferedWriter class for Reader Instance
import java.lang.Math;              // to use 'Min'

// Commenting in each method is done according to the way in "Java for EveryOne" EBook.

public class ArrayVersion {
    static Scanner userInput = new Scanner(System.in);      // Create a Scanner object as static to use inside all methods
    private static final int MAX_BURGERS_IN_STOCK = 50;
    private static int currentBurgersInStock = MAX_BURGERS_IN_STOCK;

    /*
    * Display the menu
    * @param None
    */
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

    /*
    * Display the queue
    * @param queue - member view in queues
     */
    private static void viewQueues(String [][] queue) {
        for (int asterisk = 1; asterisk <= 17; asterisk++) {
            System.out.print("*");
        }
        System.out.println("\n*    Cashier    *");
        for (int asterisk = 1; asterisk <= 17; asterisk++) {
            System.out.print("*");
        }
        System.out.println();

        // use enhanced for loop to traverse through and print 2D array code reference - https://stackoverflow.com/questions/22272946/can-i-use-an-enhanced-for-loop-to-print-two-dimensional-arrays

        for (String [] row : queue) {
            for (String column : row) {
                System.out.printf("%4s",column);
            }
            System.out.println();
        }
        System.out.println("O - Occupied \nX - Unoccupied");
    }

    /*
    *  View Empty Queues
    *  @param queue - to check available spaces in queues and manage empty queue view according to that
    */
    private static void viewAllEmptyQueues(String [][] queue) {
        // count the avaialable spaces in queue 1
        int emptySpaces = 0;
        for (int row = 0; row < queue.length; row++) {
            if (queue[row][0].equals("X")) {
                emptySpaces++;
            } 
        }
        System.out.println("Empty Spaces in Queue  1: " + emptySpaces);
        emptySpaces = 0;            // assign it to 0 so next queue spaces can count starting from 0

        // count the avaialable spaces in queue 2
        for (int row = 0; row < queue.length; row++) {
            if (queue[row][1].equals("X")) {
                emptySpaces++;
            } 
        }
        System.out.println("Empty Spaces in Queue  2: " + emptySpaces);
        emptySpaces = 0;

        // count the avaialable spaces in queue 3
        for (int row = 0; row < queue.length; row++) {
            if (queue[row][2].equals("X")) {
                emptySpaces++;
            } 
        }
        System.out.println("Empty Spaces in Queue  3: " + emptySpaces);
    }

    /*
    *Add members to queue
    *@param queue - view members in queue , customerName - customer name array to save customer names
     */
    private static void addMembers(String [][] queue, String [][] customerName){
            try {
                System.out.println("Enter Queue Number (1, 2, 3)");
                int queueNumber = userInput.nextInt();
                if(queueNumber < 4 && queueNumber > 0) {
                    for (int rows = 0; rows < queue.length; rows++ ) {
                        if (queue[rows][queueNumber - 1].equals("X")) {
                            queue[rows][queueNumber - 1] = "O";
                            System.out.print("Enter Customer Name: ");
                            customerName[rows][queueNumber - 1] = userInput.next();
                            System.out.println(customerName[rows][queueNumber - 1] + " successfully added to the queue");
                            break;
                        } else if (queue[rows][queueNumber - 1].equals(" ")) {
                            System.out.println("This queue is full");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Cashier number must be between 1-3");

                }
            }
            catch (Exception e) {
                System.out.println("Invalid input");
            }
    }

    /*
    *Remove Served Customers from queue
    *@param queue - to manage customer occupations, customers - to manage names in customerName array
     */
    private static void removeServedCustomers(String [][] queue, String [][] customers) {
        System.out.println("Enter the queue (1, 2, 3) : ");
        if(userInput.hasNextInt()) {
            int servedCustomerQueue = userInput.nextInt();
            if(servedCustomerQueue < 4 && servedCustomerQueue > 0){
                if (queue [0][servedCustomerQueue - 1].equals("O")) {
                    System.out.println(customers[0][servedCustomerQueue -1] + " has successfully served and removed from the queue");
                    queue [0][servedCustomerQueue - 1] = "X";
                    moveQueueForward(queue,servedCustomerQueue,customers);
                    if(currentBurgersInStock >= 10) {
                        currentBurgersInStock -= 5;
                    }
                    else {
                        System.out.println("Low Storage");
                    }
                }
                else if (queue[servedCustomerQueue - 1][0].equals("X")) {
                    System.out.println("This place is unoccupied");
                }
                else {
                    System.out.println("Unavailable");
                }
            }
            else {
                System.out.println("Queue Number must be between 1-3");
            }
        }
        else{
            System.out.println("Invalid input");
            userInput.nextLine();
        }
    }

    /*
    *To move queue once customer has being removed or served in queue
    *@param queue - to manage customer occupation, customerPos - to specify the queue which need to be changed, customers - to manage customer names
     */
    private static void moveQueueForward (String [][] queue, int customerPos, String[][] customers) {
        for (int rows = 0; rows < queue.length - 1; rows++) {
            String preValue = queue[rows][customerPos - 1];
            if ((preValue.equals("X")) || (preValue.equals("O"))) {
                if ( (queue[rows+1][customerPos-1].equals("X")) || (queue[rows+1][customerPos-1].equals("O")) ) {
                    queue[rows][customerPos-1] = queue[rows+1][customerPos-1];
                }
                else{
                    queue[rows][customerPos-1] = "X";
                }
            }
        }
        for (int rows = 0; rows <customers.length;rows++){
            if(customers[rows][customerPos-1]!=null){
                if(customers[rows+1][customerPos-1]!=null){
                    customers[rows][customerPos-1] = customers[rows+1][customerPos-1];
                }
                else {
                    customers[rows][customerPos-1]=null;
                }
            }
        }
    }
    // overloading method to use after removing customers from queue
    /*
    *To move queue once customer has being removed or served in queue
    *@param queue - to manage customer occupation, customerPos - to specify the queue which need to be changed, queueNum - to specify the queue which need to be changed, customers - to manage customer names
     */
    private static void moveQueueForward (String [][] queue, int customerPos,int queueNum, String[][] customers) {
        for (int rows = customerPos-1; rows < queue.length - 1; rows++) {
            String preValue = queue[rows][queueNum - 1];
            if ((preValue.equals("X")) || (preValue.equals("O"))) {
                if ( (queue[rows+1][queueNum-1].equals("X")) || (queue[rows+1][queueNum-1].equals("O")) ) {
                    queue[rows][queueNum-1] = queue[rows+1][customerPos-1];
                }
                else {
                    queue[rows][queueNum-1] = "X";
                }
            }
        }
        for (int rows = customerPos-1; rows <customers.length;rows++){
            if(customers[rows][queueNum-1]!=null){
                if(customers[rows+1][queueNum-1]!=null){
                    customers[rows][queueNum-1] = customers[rows+1][queueNum-1];
                }
                else {
                    customers[rows][queueNum-1]=null;
                }
            }
        }
    }

    /*
    *To remove a customer from specific location
    *@param queue - to manage occupation, members - to manage saved customer names
     */
    private static void removeCustomers(String [][] queue, String [][]members) {
        try {
            System.out.print("Enter the queue number you need to remove the customer from (1, 2, 3) :" );
            int queueNum = userInput.nextInt();
            if (queueNum > 0 &&  queueNum < 4) {
                System.out.print("Enter the position of Customer: ");
                int positionNum = userInput.nextInt();
                if (positionNum < 6){
                    String memberName = members[positionNum - 1][queueNum - 1];
                    if (memberName != null) {
                        System.out.println(memberName + " has successfully removed from Queue");
                        queue[positionNum - 1][queueNum - 1] = "X";
                        moveQueueForward(queue,positionNum,queueNum,members);
                    }
                    else {
                        System.out.println("Unoccupied place");
                    }
                }
                else {
                    System.out.println("Out of range");
                }
            }
            else {
                System.out.println("Queue number is out of range");
            }
        }
        catch (Exception err){
            System.out.println("Invalid Input");
        }
    }
    /*
     * Sort customer names in alphabetical order
     * @param customerList - array containing customer names
     */
    private static void sortCustomerNames(String[][] customerList) {
        String[] sortCustomer = new String[10];                    // put all elements in customerName(2D) array to 1D array to lower confusions
        int index = 0;
        for (int rows = 0; rows < customerList.length; rows++) {
            for (int cols = 0; cols < customerList[rows].length; cols++) {
                if (customerList[rows][cols] != null) {
                    sortCustomer[index] = customerList[rows][cols];
                    index++;
                }
            }
        }
        bubbleSort(sortCustomer);                   // bubble sort algorithm theory

        System.out.println("Sorted Customer Name List: ");
        for (String element : sortCustomer) {
            if (element != null) {
                System.out.println(element);
            }
        }
    }

    private static void bubbleSort(String[] subArray) {
        boolean toSwap;

        for (int i = 0; i < subArray.length - 1; i++) {
        toSwap = false;

        for (int j = 0; j < subArray.length - i - 1; j++) {
            if (compareStrings(subArray[j], subArray[j + 1]) > 0) {
                swapElements(subArray, j, j + 1);
                toSwap = true;
            }
        }

                // If no elements were swapped in the inner loop, the array is already sorted
            if (!toSwap) {
                    break;
            }
        }
    }

    private static int compareStrings(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 0;           // if both are null (no names in both position queue)
        }
        if (str1 == null) {
            return 1;           // if str1 is null (no name in first position subArray) 
        }
        if (str2 == null) {
            return -1;        // if str2 is null (no name in second position subArray) 
        }

        int minLengthOfStr = Math.min(str1.length(), str2.length());
        for (int index = 0; index < minLengthOfStr; index++) {
            int difference = str1.toUpperCase().charAt(index) - str2.toUpperCase().charAt(index);
            if (difference != 0) {
                return difference;
            }
        }

        return str1.length() - str2.length();
    }

    /*
        To Swap Elements under bubble sort algorithm
        @param String [] array - the customer name list, indexOne,indexTwo - value at certain index(under traversing)
     */
    private static void swapElements(String[] array, int indexOne, int indexTwo) {
        String storeVal = array[indexOne];      // store current value at first index in a variable
        array[indexOne] = array[indexTwo];      // assign value at second index to first index
        array[indexTwo] = storeVal;             // assign stored value to second index
    }

    private static void addBurgersToStock(){
        if(currentBurgersInStock < MAX_BURGERS_IN_STOCK){
            System.out.println("Enter the burger amount: ");
            if(userInput.hasNextInt()){
                int newBurger = userInput.nextInt();
                if(newBurger + currentBurgersInStock > MAX_BURGERS_IN_STOCK){
                    int availableAmount = MAX_BURGERS_IN_STOCK - currentBurgersInStock;
                    System.out.println("Max storage reached you can only add maximumly " + availableAmount + " burgers");
                }
                else {
                    currentBurgersInStock += newBurger;
                    System.out.println("Successfully added to stock");
                }
            }
            else{
                System.out.println("Burger amount must be an integer");
            }
        }
        else {
            System.out.println("Stock is full. Cannot add more burgers");
        }
    }

    /*
    * Create a file to store program data
    */

    // code reference : https://www.w3schools.com/java/java_files_create.asp

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

    /*
    * Write data to file
    * @param queue - to store queue data, customerList - to store customer names
    */
    
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
            fileWriter.write("Currently " + currentBurgersInStock +" burgers in Stock");
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

    // code refeerence : https://stackoverflow.com/questions/16104616/using-bufferedreader-to-read-text-file

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


    /*
    * main Method
     */
    public static void main(String[] args) {
        String [][] queue = {
                {"X", "X", "X"},
                {"X", "X", "X"},
                {" ", "X", "X"},
                {" ", " ", "X"},
                {" ", " ", "X"}
        };
        String [][]customerList = new String[6][3];
        boolean maintenance = true;
        while (maintenance){
            consoleMenu();
            System.out.print("Enter Your Choice: ");
            String selectedOption = userInput.next();
            if (currentBurgersInStock > 10) {
                switch (selectedOption.toUpperCase()) {
                    case "100": case "VFQ":
                        viewQueues(queue);
                        break;
                    case "101": case "VEQ":
                        viewAllEmptyQueues(queue);
                        break;
                    case "102": case "ACQ":
                        addMembers(queue, customerList);
                        break;
                    case "103": case "RCQ":
                        removeCustomers(queue, customerList);
                        break;
                    case "104": case "PCQ":
                        removeServedCustomers(queue, customerList);
                        break;
                    case "105": case "VCS":
                        sortCustomerNames(customerList);
                        break;
                    case "106": case "SPD":
                        createFile();
                        saveDataToFile(queue,customerList);
                        break;
                    case "107": case "LPD":
                        loadProgramDataFromFile();
                        break;
                    case "108": case "STK":
                        System.out.println(currentBurgersInStock + " burgers remaining");
                        break;
                    case "109": case "AFS":
                        addBurgersToStock();
                        break;
                    case "999": case "EXT":
                        System.out.println("Thank You !");
                        maintenance = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            }
            else {
                System.out.println("You are running out of Burger Stock Please Add more Burgers to stock");
            }
            userInput.nextLine();
        }
    }
}