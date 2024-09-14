package org.example.demo.view.fixdefect;

import de.saxsys.mvvmfx.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * InputControlFixView
 *
 * @author ruiqi
 * @date 2024/9/14 11:11
 */
public class InputControlFixView implements FxmlView<InputControlFixViewModel>, Initializable {


    public ComboBox beforeFixComboBox;
    public ComboBox afterFixComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXNativeComponentDefectFixUtil.setComboBoxPromptTextCellFactory(afterFixComboBox, Function.identity());
    }

    public void onResetButtonClick(ActionEvent actionEvent) {
        beforeFixComboBox.getSelectionModel().clearSelection();
        afterFixComboBox.getSelectionModel().clearSelection();
    }
}
