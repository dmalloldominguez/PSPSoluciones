package ejemplos;

import javafx.collections.ListChangeListener;

public class School implements ListChangeListener<String> {
    @Override
    public void onChanged(Change<? extends String> change) {
        while (change.next()) {
            if (change.wasRemoved()) {
                System.out.println("Se desmatricula: " + change.getRemoved().get(0));
            } else if (change.wasAdded()) {
                System.out.println("Se matricula: " + change.getAddedSubList().get(0));
            }
        }
    }
}
