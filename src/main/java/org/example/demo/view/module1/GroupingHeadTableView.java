package org.example.demo.view.module1;

import atlantafx.base.theme.Tweaks;
import de.saxsys.mvvmfx.FxmlView;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.example.demo.entity.TableItemEntity;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

public class GroupingHeadTableView implements FxmlView<GroupingHeadTableViewModel>, Initializable {
    public TableView<TableItemEntity> tableView;
    public TableColumn<TableItemEntity, String> column1;
    public TableColumn<TableItemEntity, String> column2;
    public TableColumn<TableItemEntity, Long> column3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column1.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3.setCellValueFactory(new PropertyValueFactory<>("column3"));

        var tableItemEntity = new TableItemEntity();
        tableItemEntity.setColumn1("1");
        tableItemEntity.setColumn2("2");
        tableItemEntity.setColumn3(3L);
        tableView.getItems().add(tableItemEntity);
    }
}
