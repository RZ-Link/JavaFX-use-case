package org.example.demo.view.bug;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;

public class FXControlsBugFixUtils {

    /**
     * 设置ButtonCell，修复重置不显示PromptText的bug
     */
    public static <T> void setButtonCellToFixBug(ComboBox<T> comboBox) {
        comboBox.setButtonCell(new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(comboBox.getPromptText());
                } else {
                    setText(item.toString());
                }
            }
        });
    }

    /**
     * 设置OnScroll事件，修复滚动条场景可能出现的数据显示bug
     */
    public static <T> void setOnScrollToFixBug(TableView<T> tableView) {
        tableView.setOnScroll(e -> {
            if (e.getDeltaY() > 0) {
                tableView.scrollTo(0);
            } else if (e.getDeltaY() < 0) {
                tableView.scrollTo(tableView.getItems().size() - 1);
            }
        });
    }

}
