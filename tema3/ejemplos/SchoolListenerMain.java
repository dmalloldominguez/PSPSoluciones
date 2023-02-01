package ejemplos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchoolListenerMain {

    public static void main(String[] args) {
        ObservableList<String> students = FXCollections.observableArrayList();
        School school = new School();
        students.addListener(school);
        students.add("David");
        students.add("Juan");
        students.remove("David");
    }
}