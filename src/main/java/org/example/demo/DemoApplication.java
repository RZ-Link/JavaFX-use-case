package org.example.demo;

import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DemoApplication extends Application {

    @Override
    public void start(Stage stage) {
        // 设置AtlantaFX主题
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        // 线程未捕获异常处理，对话框提示
        Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler());
        // 启动窗口
        final ViewTuple<WindowView, WindowViewModel> viewTuple = FluentViewLoader.fxmlView(WindowView.class).load();
        final Parent root = viewTuple.getView();
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Demo Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}