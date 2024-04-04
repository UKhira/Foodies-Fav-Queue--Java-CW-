/**
 * Customer management
 */
public class Customer {
    // Once any changes done via Main class it must affect to all objects in FoodQueue class, so we use static
    private static String fullName;
    private static String customers [][] = new String[5][3];

    /*
    * to get and customer full name
    * @param fname - first name , lname - last name
    * @return full name
    */
    public static String getFullName(String fname, String lname){
        fullName =  fname + " " +  lname;
        return fullName;
    }
    public static void addCustomerNames(int row, int queueNo){
      customers[row][queueNo] = fullName;
    }

    /*
    * to return customer names array
    * @return customers array
    */
   public static String[][]  getCustomerList(){
        return customers;
   }
    /*
    * to move queue forward after a customer was served
    * @param customerPos - customer queue number
    */
    public static void moveCustomerNamesForward(int customerPos){
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
        for(String [] i: customers){
            for(String l : i){
                System.out.print(l + "\t");
            }
            System.out.println();
        }
    }

    public static void sortCustomerNames() {
        String[] sortCustomer = new String[10];                    // put all elements in customerName(2D) array to 1D array to lower confusions
        int index = 0;
        for (int rows = 0; rows < customers.length; rows++) {
            for (int cols = 0; cols < customers[rows].length; cols++) {
                if (customers[rows][cols] != null) {
                    sortCustomer[index] = customers[rows][cols];
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

    public static void removeCustomer(int positionNum, int queueNum){
        String memberName = customers[positionNum - 1][queueNum - 1];
        System.out.println(memberName + " has successfully removed from Queue no " + (queueNum) + " place " + (positionNum));
        moveCustomerNamesForward(queueNum,positionNum);
    }

    // overloading the method to use it with 2 parameters after removeCustomer method
    private static void moveCustomerNamesForward(int queueNo,int customerPos){
        for (int rows = customerPos-1; rows <customers.length;rows++){
            if(customers[rows][queueNo-1]!=null){
                if(customers[rows+1][queueNo-1]!=null){
                    customers[rows][queueNo-1] = customers[rows+1][queueNo-1];
                }
                else {
                    customers[rows][queueNo-1]=null;
                }
            }
        }
    }

}
