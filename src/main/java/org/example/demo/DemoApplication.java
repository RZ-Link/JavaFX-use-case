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
    public void start(Stage stage) throws IOException {
        // AtlantaFX主题
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());

        Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler(stage));

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