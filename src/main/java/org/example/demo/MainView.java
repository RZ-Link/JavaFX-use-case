package org.example.demo;

import cn.hutool.core.util.StrUtil;
import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.demo.event.EventConsts;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable {

    @FXML
    private BorderPane borderPane;

    @InjectViewModel
    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 左侧菜单
        SideMenu sideMenu = new SideMenu();
        // 右侧标签
        TabPane tabPane = new TabPane();

        borderPane.setLeft(sideMenu);
        borderPane.setCenter(tabPane);

        // 订阅左侧菜单选择事件，右侧标签展示模块页面
        MvvmFX.getNotificationCenter().subscribe(EventConsts.SideMenuSelected.getKey(), (key, payload) -> {
            Tab tab = new Tab((String) payload[0]);
            //查询tabpane中是否已存在待显示tab，存在则直接切换到tab界面
            Optional<Tab> tabOptional = tabPane.getTabs().stream()
                    .filter(t -> StrUtil.equals(t.getText(), (String)payload[0]))
                    .findFirst();
            if (tabOptional.isPresent()) {
                tab = tabOptional.get();
            } else {
                switch ((String) payload[0]) {
                    case "模块1":
                        tab.setContent(FluentViewLoader.fxmlView(Module1View.class).load().getView());
                        break;
                    case "模块2":
                        tab.setContent(FluentViewLoader.fxmlView(Module2View.class).load().getView());
                        break;
                    default:
                        return;
                }
                tabPane.getTabs().add(tab);
            }
            // tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
    }
}
