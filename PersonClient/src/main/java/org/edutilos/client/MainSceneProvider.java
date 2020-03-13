package org.edutilos.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.edutilos.model.Person;
import org.edutilos.register.GlobalRegister;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */
public class MainSceneProvider {
    public static Scene getMainScene() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(evt-> {
            System.out.println("Hello World!");
        });

        ListView<Person> listView = new ListView<>(FXCollections.observableArrayList(GlobalRegister.getPersonService().findAll()));
        listView.setCellFactory(param -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(btn, listView);
        return new Scene(root, 300, 250);
    }
}
