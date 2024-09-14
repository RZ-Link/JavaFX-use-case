package org.example.demo.view.fixdefect;

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

/**
 * TableDefectFixView
 *
 * @author ruiqi
 * @date 2024/9/14 13:47
 */
public class TableDefectFixView implements FxmlView<TableDefectFixViewModel>, Initializable {
    public TableView<PersonEntity> beforeFixTableView;
    public TableColumn<PersonEntity, String> nameColumn;
    public TableColumn<PersonEntity, Long> ageColumn;
    public TableColumn<PersonEntity, Long> operationColumn;
    public TableView<PersonEntity> afterFixTableView;
    public TableColumn<PersonEntity, String> nameColumn1;
    public TableColumn<PersonEntity, Long> ageColumn1;
    public TableColumn<PersonEntity, Long> operationColumn1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXNativeComponentDefectFixUtil.fixTableViewScrollDefect(afterFixTableView);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        operationColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        operationColumn.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button deleteButton = new Button("删除", FontIcon.of(Feather.TRASH));
                        deleteButton.setOnAction(event -> {
                            System.out.println("删除" + item + getTableRow().getItem().getName());
                        });
                        HBox box = new HBox(deleteButton);
                        box.setAlignment(Pos.CENTER);
                        box.setSpacing(5.0);
                        setGraphic(box);
                    }
                }
            };
        });
        nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn1.setCellValueFactory(new PropertyValueFactory<>("age"));
        operationColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
        operationColumn1.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button deleteButton = new Button("删除", FontIcon.of(Feather.TRASH));
                        deleteButton.setOnAction(event -> {
                            System.out.println("删除" + item + getTableRow().getItem().getName());
                        });
                        HBox box = new HBox(deleteButton);
                        box.setAlignment(Pos.CENTER);
                        box.setSpacing(5.0);
                        setGraphic(box);
                    }
                }
            };
        });

        ObservableList<PersonEntity> data = FXCollections.observableArrayList(
                new PersonEntity(1L, "曹操", 30L),
                new PersonEntity(2L, "刘备", 30L)
                // new PersonEntity(3L, "司马懿", 30L)
        );
        beforeFixTableView.setItems(data);
        afterFixTableView.setItems(data);
    }
}
