package org.example.demo;

import de.saxsys.mvvmfx.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

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
        MvvmFX.getNotificationCenter().publish("showMainView");
    }
}
