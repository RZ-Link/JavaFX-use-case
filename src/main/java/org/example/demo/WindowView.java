package org.example.demo;

import de.saxsys.mvvmfx.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.demo.event.EventConsts;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowView implements FxmlView<WindowViewModel>, Initializable {
    @FXML
    private StackPane rootPane;

    @InjectViewModel
    private WindowViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 订阅事件，打开管理页面
        MvvmFX.getNotificationCenter().subscribe(EventConsts.ShowMainView.getKey(), (key, payload) -> {
            showMainView();
        });

        // 打开登录页面
        showLoginView();
    }

    private void showLoginView() {
        rootPane.getChildren().clear();
        ViewTuple<LoginView, LoginViewModel> viewTuple = FluentViewLoader.fxmlView(LoginView.class).load();
        rootPane.getChildren().add(viewTuple.getView());
    }

    private void showMainView() {
        rootPane.getChildren().clear();
        ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();
        rootPane.getChildren().add(viewTuple.getView());
    }

}
