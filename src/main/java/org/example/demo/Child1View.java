package org.example.demo;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.demo.entity.PersonEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class Child1View implements FxmlView<Child1ViewModel>, Initializable {

    @InjectViewModel
    private Child1ViewModel viewModel;

    @FXML
    private TableView<PersonEntity> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<PersonEntity> data = FXCollections.observableArrayList(
                new PersonEntity("曹操", 30),
                new PersonEntity("刘备", 30),
                new PersonEntity("司马懿", 30)
//                new PersonEntity("曹操", 30),
//                new PersonEntity("刘备", 30),
//                new PersonEntity("司马懿", 30),
//                new PersonEntity("曹操", 30),
//                new PersonEntity("刘备", 30),
//                new PersonEntity("司马懿", 30),
//                new PersonEntity("曹操", 30),
//                new PersonEntity("刘备", 30),
//                new PersonEntity("司马懿", 30),
//                new PersonEntity("曹操", 30),
//                new PersonEntity("刘备", 30),
//                new PersonEntity("司马懿", 30),
//                new PersonEntity("曹操", 30),
//                new PersonEntity("刘备", 30),
//                new PersonEntity("司马懿", 30)

        );
        tableView.setItems(data);
    }


}
