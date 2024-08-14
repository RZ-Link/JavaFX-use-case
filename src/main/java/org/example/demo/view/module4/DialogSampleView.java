package org.example.demo.view.module4;

import de.saxsys.mvvmfx.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogSampleView implements FxmlView<DialogSampleViewModel>, Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onSuccessButtonClick(ActionEvent actionEvent) {
        ConfirmDialog.create("标题", "正文内容", () -> {
            System.out.println("确认");
        });
    }
}
