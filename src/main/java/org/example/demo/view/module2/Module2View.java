package org.example.demo.view.module2;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.MvvmFX;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.example.demo.component.FXPagination;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Module2View implements FxmlView<Module2ViewModel>, Initializable {

    @InjectViewModel
    private Module2ViewModel viewModel;

    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXPagination fxPagination = new FXPagination(1000L,
                10L,
                1L,
                new ArrayList<>(Arrays.asList(10L, 20L)),
                "prevButtonClickEvent",
                "nextButtonClickEvent",
                "gotoEnterPressedEvent");

        // 用户点击上一页按钮事件
        MvvmFX.getNotificationCenter().subscribe("prevButtonClickEvent", (key, payload) -> {
            Long pageSize = (long) payload[0];
            Long gotoPage = (long) payload[1];
            fxPagination.update(1000L, pageSize, gotoPage);
        });
        // 用户点击下一页按钮事件
        MvvmFX.getNotificationCenter().subscribe("nextButtonClickEvent", (key, payload) -> {
            Long pageSize = (long) payload[0];
            Long gotoPage = (long) payload[1];
            fxPagination.update(1000L, pageSize, gotoPage);
        });
        // 用户回车跳转事件
        MvvmFX.getNotificationCenter().subscribe("gotoEnterPressedEvent", (key, payload) -> {
            Long pageSize = (long) payload[0];
            Long gotoPage = (long) payload[1];
            fxPagination.update(1000L, pageSize, gotoPage);
        });

        vbox.getChildren().add(fxPagination);
    }
}
