module com.gui.task_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gui.task_4 to javafx.fxml;
    exports com.gui.task_4;
}