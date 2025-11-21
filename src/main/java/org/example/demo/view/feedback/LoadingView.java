package org.example.demo.view.feedback;

import cn.hutool.core.thread.ThreadUtil;
import de.saxsys.mvvmfx.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.example.demo.view.window.WindowView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingView implements FxmlView<LoadingViewModel>, Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onAddLoadingButtonClick(ActionEvent actionEvent) {
        WindowView.windowView.addLoading();
        ThreadUtil.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception ignored) {
            }
            Platform.runLater(() -> {
                WindowView.windowView.removeLoading();
            });
        });

        // 重复添加遮罩层，测试效果
        WindowView.windowView.addLoading();
        ThreadUtil.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception ignored) {
            }
            Platform.runLater(() -> {
                WindowView.windowView.removeLoading();
            });
        });
    }
}
