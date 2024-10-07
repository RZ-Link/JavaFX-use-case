package org.example.demo.view.bug;

import de.saxsys.mvvmfx.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.demo.entity.PersonEntity;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class TableBugView implements FxmlView<TableBugViewModel>, Initializable {
    public TableView<PersonEntity> beforeFixTableView;
    public TableColumn<PersonEntity, String> beforeFixNameColumn;
    public TableColumn<PersonEntity, Long> beforeFixAgeColumn;
    public TableColumn<PersonEntity, Long> beforeFixOperationColumn;

    public TableView<PersonEntity> afterFixTableView;
    public TableColumn<PersonEntity, String> afterFixNameColumn;
    public TableColumn<PersonEntity, Long> afterFixAgeColumn;
    public TableColumn<PersonEntity, Long> afterFixOperationColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        beforeFixNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        beforeFixAgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        beforeFixOperationColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        beforeFixOperationColumn.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button deleteButton = new Button("删除", FontIcon.of(Feather.TRASH));
                        HBox box = new HBox(deleteButton);
                        box.setAlignment(Pos.CENTER_LEFT);
                        setGraphic(box);
                    }
                }
            };
        });

        ControlBugFixUtils.setOnScrollToFixBug(afterFixTableView);
        afterFixNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        afterFixAgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        afterFixOperationColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        afterFixOperationColumn.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button deleteButton = new Button("删除", FontIcon.of(Feather.TRASH));
                        HBox box = new HBox(deleteButton);
                        box.setAlignment(Pos.CENTER_LEFT);
                        setGraphic(box);
                    }
                }
            };
        });

        ObservableList<PersonEntity> data = FXCollections.observableArrayList(
                new PersonEntity(1L, "曹操", 30L),
                new PersonEntity(2L, "刘备", 30L)
        );
        beforeFixTableView.setItems(data);
        afterFixTableView.setItems(data);
    }
}
