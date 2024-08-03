package org.example.demo.view.feedback;

import cn.hutool.core.thread.ThreadUtil;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import org.example.demo.DemoApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageUtils {

    private static final List<Popup> stack = Collections.synchronizedList(new ArrayList<>());

    // 弹出成功消息
    public static void success(String message) {
        Platform.runLater(() -> {
            // 创建消息
            Popup popup = new Popup();

            HBox hbox = new HBox();
            hbox.setPadding(new Insets(5));
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(5);
            hbox.setMinWidth(0);
            hbox.setMaxWidth(500);
            hbox.setStyle("-fx-background-color: #f4f4f5; -fx-background-radius: 4px; -fx-border-color: #e9e9eb; -fx-border-radius: 4px; -fx-border-width: 1px;");
            // 消息图标
            ImageView icon = new ImageView("/org/example/demo/image/message/成功.png");
            icon.setFitWidth(15);
            icon.setFitHeight(15);
            // 消息内容
            Label content = new Label(message);
            content.setWrapText(true);
            content.setTextFill(Paint.valueOf("#909399"));

            hbox.getChildren().addAll(icon, content);

            popup.getContent().add(hbox);

            // 水平居中
            hbox.widthProperty().addListener((obs, old, val) -> {
                popup.setX(DemoApplication.stage.getX() + (DemoApplication.stage.getWidth() - popup.getWidth()) / 2);
            });
            // 垂直偏移
            if (stack.isEmpty()) {
                popup.setY(DemoApplication.stage.getY() + 5);
            } else {
                var previousPopup = stack.get(stack.size() - 1);
                popup.setY(previousPopup.getY() + previousPopup.getHeight() + 5);
            }

            // 弹出消息
            stack.add(popup);
            popup.show(DemoApplication.stage);

            // 消息3秒后自动消失
            ThreadUtil.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {

                } finally {
                    Platform.runLater(() -> {
                        stack.remove(popup);
                        popup.hide();
                    });
                }
            });

        });
    }
}


