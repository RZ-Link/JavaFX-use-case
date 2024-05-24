package org.example.demo;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.example.demo.entity.PersonEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class Module1View implements FxmlView<Module1ViewModel>, Initializable {

    @InjectViewModel
    private Module1ViewModel viewModel;

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
