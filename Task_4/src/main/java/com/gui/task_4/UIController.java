package com.gui.task_4;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Label;

public class UIController {

    @FXML
    private Label c11;

    @FXML
    private Label c12;

    @FXML
    private Label c21;

    @FXML
    private Label c22;

    @FXML
    private Label c23;

    @FXML
    private Label c31;

    @FXML
    private Label c32;

    @FXML
    private Label c33;

    @FXML
    private Label c34;

    @FXML
    private Label c35;

    @FXML
    private Label stock;

    @FXML
    private Label w1;

    @FXML
    private Label w2;

    @FXML
    private Label w3;

    @FXML
    private Label w4;

    @FXML
    private Label w5;

    @FXML
    private Label w6;

    @FXML
    private Label w7;

    @FXML
    private Label w8;

    @FXML
    private Label w9;

    @FXML
    private Label w10;

    @FXML
    void updateButtonClick() {
        String count = String.valueOf(FoodQueue.getBurgerCount());                  // reference - https://stackoverflow.com/questions/5071040/java-convert-integer-to-string
        stock.setText(count);

        // Cashier 1 Queue
        c11.setText(Customer.getCustomerNameByIndex(0,0));
        c12.setText(Customer.getCustomerNameByIndex(1,0));

        // Cashier 2 Queue
        c21.setText(Customer.getCustomerNameByIndex(0,1));
        c22.setText(Customer.getCustomerNameByIndex(1,1));
        c23.setText(Customer.getCustomerNameByIndex(2,1));

        // Cashier 3 Queue
        c31.setText(Customer.getCustomerNameByIndex(0,2));
        c32.setText(Customer.getCustomerNameByIndex(1,2));
        c33.setText(Customer.getCustomerNameByIndex(2,2));
        c34.setText(Customer.getCustomerNameByIndex(3,2));
        c35.setText(Customer.getCustomerNameByIndex(4,2));

        // Waiting Queue
        w1.setText(Customer.getWaitingQueue(0));
        w2.setText(Customer.getWaitingQueue(1));
        w3.setText(Customer.getWaitingQueue(2));
        w4.setText(Customer.getWaitingQueue(3));
        w5.setText(Customer.getWaitingQueue(4));
        w6.setText(Customer.getWaitingQueue(5));
        w7.setText(Customer.getWaitingQueue(6));
        w8.setText(Customer.getWaitingQueue(7));
        w9.setText(Customer.getWaitingQueue(8));
        w10.setText(Customer.getWaitingQueue(9));
    }

}

