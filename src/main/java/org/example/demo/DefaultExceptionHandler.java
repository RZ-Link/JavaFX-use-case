package org.example.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;


public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        // 创建弹窗
        var dialog = createExceptionDialog(e);
        // 展示弹窗
        if (dialog != null) {
            dialog.showAndWait();
        }
    }

    private Alert createExceptionDialog(Throwable throwable) {
        var alert = new Alert(AlertType.ERROR);
        try (var sw = new StringWriter(); var pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            var textArea = new TextArea(sw.toString());
            var content = new VBox(textArea);
            alert.getDialogPane().setExpandableContent(content);
            return alert;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
