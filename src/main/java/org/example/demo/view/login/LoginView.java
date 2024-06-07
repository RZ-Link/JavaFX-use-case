package org.example.demo.view.login;

import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.demo.event.EventConsts;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements FxmlView<LoginViewModel>, Initializable {

    @InjectViewModel
    private LoginViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void onLoginButtonClick() {
        // 推送事件，展示管理页面
        MvvmFX.getNotificationCenter().publish(EventConsts.ShowMainView.getKey());
    }
}
