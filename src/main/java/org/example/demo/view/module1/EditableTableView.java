package org.example.demo.view.module1;

import atlantafx.base.theme.Styles;
import cn.hutool.core.util.ReflectUtil;
import com.sun.javafx.PlatformUtil;
import de.saxsys.mvvmfx.FxmlView;
import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.tableview2.cell.TextField2TableCell;
import org.example.demo.entity.PersonEntity;
import org.example.demo.entity.TableItemEntity;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class EditableTableView implements FxmlView<EditableTableViewModel>, Initializable {
    public TableView<TableItemEntity> tableView;
    public TableColumn<TableItemEntity, String> column1;
    public TableColumn<TableItemEntity, String> column2;
    public TableColumn<TableItemEntity, Long> column3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Styles.toggleStyleClass(tableView, Styles.BORDERED);

        column1.setCellFactory(TextField2TableCell.forTableColumn());
        column1.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column1.setOnEditCommit(e -> {
            e.getRowValue().setColumn1(e.getNewValue());
            tableView.refresh();
        });

        column2.setCellFactory(TextField2TableCell.forTableColumn());
        column2.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column2.setOnEditCommit(e -> {
            e.getRowValue().setColumn2(e.getNewValue());
            tableView.refresh();
        });

        column3.setCellFactory(TextField2TableCell.forTableColumn(new FXLongStringConverter()));
        column3.setCellValueFactory(new PropertyValueFactory<>("column3"));
        column3.setOnEditCommit(e -> {
            e.getRowValue().setColumn3(e.getNewValue());
            tableView.refresh();
        });
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        tableView.getItems().add(new TableItemEntity());
    }

    public void onSaveButtonClick(ActionEvent actionEvent) {
        System.out.println(tableView.getItems());
    }
}

class FXLongStringConverter extends LongStringConverter {
    @Override
    public Long fromString(String value) {
        try {
            return super.fromString(value);
        } catch (Exception e) {
            return null;
        }
    }
}
