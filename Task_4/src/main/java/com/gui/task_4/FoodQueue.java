package com.gui.task_4;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodQueue {
    // Once any changes done via FoodQueue class it must affect to all objects,methods in class, so we use static
    private static Scanner userInput = new Scanner(System.in);
    private static int burgerStock = 50;
    private static ArrayList<Integer> cashier1Burger = new ArrayList<Integer>();
    private static ArrayList<Integer> cashier2Burger = new ArrayList<Integer>();
    private static ArrayList<Integer> cashier3Burger = new ArrayList<Integer>();

    public static void viewQueue(String[][] queue) {
        for (String[] row : queue) {
            for (String column : row) {
                System.out.printf("%4s", column);
            }
            System.out.println();
        }
        System.out.println("O - Occupied \nX - Unoccupied");
    }

    public static void addMembers(String[][] queue) {
        try {
            int col = 0, row = 0;
            while (true) {
                if (queue[row][col].equals("X")) {
                    System.out.print("Enter Customer First Name: ");
                    String firstName = userInput.next();
                    System.out.print("Enter Customer Last Name: ");
                    String lastName = userInput.next();
                    System.out.print("Enter burgers required: ");
                    if (userInput.hasNextInt()) {
                        int burgerCount = userInput.nextInt();
                        if(burgerStock - burgerCount < 0){
                            System.out.println("Low storage. Can maximumly sell " + burgerStock + " burgers \nPlease add more to stock");
                            break;
                        }
                        else {
                            storePrice(burgerCount, col);
                            setBurgerCount(burgerCount);
                            System.out.println(Customer.getFullName(firstName, lastName) + " successfully added to the queue no." + (col + 1) + " place no. " + (row + 1));
                            Customer.addCustomerNames(row, col);
                            queue[row][col] = "O";
                            break;
                        }
                    } 
                    else {
                        System.out.println("Enter a number");
                        col = 0;
                        break;
                    }
                } else if (queue[row][col].equals("O")) {
                    row++;
                } else if (queue[row][col].equals(" ")) {
                    col++;
                    row = 0;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("All Queues are full adding customer to waiting queue");
            Customer.addCustomerToWaitingQueue();
        }
    }

    public static void removeServedCustomers(String[][] queue) {
        System.out.println("Enter the queue (1, 2, 3) : ");
        if (userInput.hasNextInt()) {
            int servedCustomerQueue = userInput.nextInt();
            if (servedCustomerQueue < 4 && servedCustomerQueue > 0) {
                if (queue[0][servedCustomerQueue - 1].equals("O")) {
                    queue[0][servedCustomerQueue - 1] = "X";
                    System.out.println("Successfully Removed");
                    moveQueueForward(servedCustomerQueue,queue);
                } else if (queue[servedCustomerQueue - 1][0].equals("X")) {
                    System.out.println("This place is unoccupied");
                } else {
                    System.out.println("Unavailable");
                }
            } else {
                System.out.println("Queue Number must be between 1-3");
            }
        } else {
            System.out.println("Invalid input");
            userInput.nextLine();
        }
    }

    private static void moveQueueForward (int customerPos, String [][] queue) {
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
        Customer.moveCustomerNamesForward(customerPos);
    }

    public static void setBurgerCount(int count) {
        burgerStock -= count;
    }

    public static int getBurgerCount() {
        return burgerStock;
    }

    private static void storePrice(int count, int cashier) {
        if (cashier == 0) {
            cashier1Burger.add(count);
        } else if (cashier == 1) {
            cashier2Burger.add(count);
        } else {
            cashier3Burger.add(count);
        }
    }

    public static void calculateIncome() {
        int cashier1total = 0, cashier2Total = 0, cashier3Total = 0;
        for (int count : cashier1Burger) {
            cashier1total += count;
        }
        for (int count : cashier2Burger) {
            cashier2Total += count;
        }
        for (int count : cashier3Burger) {
            cashier3Total += count;
        }
        System.out.printf("Total Income from Cashier 1 = %d %nTotal Income from Cashier 2 = %d %nTotal Income from Cashier 3 = %d %n", cashier1total * 650, cashier2Total * 650, cashier3Total * 650);
    }
    public static void removeCustomers(String [][] queue) {
        try {
            System.out.print("Enter the queue number you need to remove the customer from (1, 2, 3) :" );
            int queueNum = userInput.nextInt();
            if (queueNum > 0 &&  queueNum < 4) {
                System.out.print("Enter the position of Customer: ");
                int positionNum = userInput.nextInt();
                if (positionNum < 6){
                    if (queue[positionNum-1][queueNum-1].equals("O")) {
                        Customer.removeCustomer(positionNum, queueNum);
                        queue[positionNum - 1][queueNum - 1] = "X";
                        moveQueueForward(queue, positionNum, queueNum);
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
    private static void moveQueueForward (String [][] queue, int customerPos,int queueNum) {
        for (int rows = customerPos - 1; rows < queue.length - 1; rows++) {
            String preValue = queue[rows][queueNum - 1];
            if ((preValue.equals("X")) || (preValue.equals("O"))) {
                if ((queue[rows + 1][queueNum - 1].equals("X")) || (queue[rows + 1][queueNum - 1].equals("O"))) {
                    queue[rows][queueNum - 1] = queue[rows + 1][customerPos - 1];
                } else {
                    queue[rows][queueNum - 1] = "X";
                }
            }
        }
    }
    public static void viewAllEmptyQueues(String [][] queue){
        int emptySpaces = 0;
        // count the available spaces in queue 1
        for (int row = 0; row < queue.length; row++) {
            if (queue[row][0].equals("X")) {
                emptySpaces++;
            }
        }
        System.out.println("Empty Spaces in Queue  1: " + emptySpaces);
        emptySpaces = 0;

        // count the available spaces in queue 2
        for (int row = 0; row < queue.length; row++) {
            if (queue[row][1].equals("X")) {
                emptySpaces++;
            }
        }
        System.out.println("Empty Spaces in Queue  2: " + emptySpaces);
        emptySpaces = 0;

        // count the available spaces in queue 1
        for (int row = 0; row < queue.length; row++) {
            if (queue[row][2].equals("X")) {
                emptySpaces++;
            }
        }
        System.out.println("Empty Spaces in Queue  3: " + emptySpaces);
    }
}
