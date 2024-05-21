package org.example.demo;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler(stage));

        final ViewTuple<HelloWorldView, HelloWorldViewModel> viewTuple = FluentViewLoader.fxmlView(HelloWorldView.class).load();
        final Parent root = viewTuple.getView();
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello World Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}