package org.example.demo.view.bug;

import de.saxsys.mvvmfx.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ComboBoxBugView implements FxmlView<ComboBoxBugViewModel>, Initializable {


    public ComboBox<String> beforeFixComboBox;
    public ComboBox<String> afterFixComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControlBugFixUtils.setButtonCellToFixBug(afterFixComboBox);
    }

    public void onResetButtonClick(ActionEvent actionEvent) {
        beforeFixComboBox.setValue(null);
        afterFixComboBox.setValue(null);
    }
}
