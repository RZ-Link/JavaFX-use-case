package org.example.demo.view.table;

import cn.hutool.json.JSONUtil;
import de.saxsys.mvvmfx.FxmlView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import org.example.demo.entity.SelectableTableItemEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectableTableView implements FxmlView<SelectableTableViewModel>, Initializable {
    public TableView<SelectableTableItemEntity> tableView;
    public TableColumn<SelectableTableItemEntity, Boolean> selectableColumn;
    public TableColumn<SelectableTableItemEntity, String> column1;
    public TableColumn<SelectableTableItemEntity, String> column2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.setEditable(true);

        selectableColumn.setCellValueFactory(cellData -> cellData.getValue().getSelect());
        selectableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectableColumn));
        column1.setCellValueFactory(cellData -> cellData.getValue().getColumn1());
        column2.setCellValueFactory(cellData -> cellData.getValue().getColumn2());

        var selectableTableItemEntity = new SelectableTableItemEntity();
        selectableTableItemEntity.setSelect(new SimpleBooleanProperty(false));
        selectableTableItemEntity.setColumn1(new SimpleStringProperty("1"));
        selectableTableItemEntity.setColumn2(new SimpleStringProperty("2"));
        tableView.getItems().add(selectableTableItemEntity);
    }

    public void onReadButtonClick(ActionEvent actionEvent) {
        for (var item : tableView.getItems()) {
            System.out.println(JSONUtil.toJsonStr(item));
        }
    }
}
