package org.example.demo.view.fixdefect;

import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
import javafx.scene.control.skin.TableViewSkin;

import java.util.function.Function;

import static javafx.scene.AccessibleAttribute.VERTICAL_SCROLLBAR;

/**
 * FXNativeComponentDefectFixUtil
 * FX原生组件缺陷修复工具类
 * @author ruiqi
 * @date 2024/8/21 15:47
 */
public class FXNativeComponentDefectFixUtil {
    /**
     * 为ComboBox设置ListCell，使得当无选择时显示promptText，有选择时则自定义显示逻辑。
     *
     * @param comboBox 需要设置的ComboBox
     * @param displayFunction 当选择非空时，如何显示项的自定义逻辑（item -> String）
     */
    public static <T> void setComboBoxPromptTextCellFactory(ComboBox<T> comboBox, Function<T, String> displayFunction) {
        comboBox.setButtonCell( new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(comboBox.getPromptText());
                } else {
                    setText(displayFunction.apply(item));
                }
            }
        });
    }

    /**
     * 为原生tableview修复滚动条缺陷
     * （解决当两条以上滚动条同时出现时使用鼠标滚轮后点击表格存在数据消失的问题）
     * @param tableView
     */
    public static void fixTableViewScrollDefect(TableView tableView){
        tableView.setOnScroll(e->{
            // 处理滚动事件
            if (e.getDeltaY() > 0) {
                // 向上滚动
                tableView.scrollTo(0);
            } else {
                // 向下滚动
                tableView.scrollTo(tableView.getItems().size());
            }
        });

        // 监听 TableView 的 skin 属性变化
        tableView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            if (newSkin instanceof TableViewSkin) {
                TableViewSkin<?> skin = (TableViewSkin<?>) newSkin;
                ScrollBar vBar = (ScrollBar) ((TableViewSkin<?>) newSkin).queryAccessibleAttribute(VERTICAL_SCROLLBAR,null);
                if (vBar != null) {
                    vBar.visibleProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            Platform.runLater(() -> tableView.refresh());
                        }
                    });
                }
            }
        });
    }

}
