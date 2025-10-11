package org.example.demo.view.feedback;

import cn.hutool.json.JSONUtil;
import de.saxsys.mvvmfx.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogView implements FxmlView<DialogViewModel>, Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onConfirmButtonClick(ActionEvent actionEvent) {
        ConfirmDialog.create("标题", "正文内容", () -> {
            System.out.println("确认");
        });
    }

    public void onPromptButtonClick(ActionEvent actionEvent) {
        PromptDialog.create("标题", "正文内容", (input) -> {
            System.out.println(input);
        });
    }

    public void onTreeViewButtonClick(ActionEvent actionEvent) {
        TreeViewDialog.create("标题", (directoryVO) -> {
            System.out.println(JSONUtil.toJsonStr(directoryVO));
        });
    }
}
