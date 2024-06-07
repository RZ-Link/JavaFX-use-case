package org.example.demo.view.main;

import cn.hutool.core.util.StrUtil;
import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.demo.view.module1.Module1View;
import org.example.demo.view.module2.Module2View;
import org.example.demo.component.SideMenu;
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
            Tab tab;
            // 查询TabPane是否已经存在需要展示的Tab。如果已经存在，直接切换。
            Optional<Tab> tabOptional = tabPane.getTabs().stream()
                    .filter(t -> StrUtil.equals(t.getText(), (String) payload[0]))
                    .findFirst();
            if (tabOptional.isPresent()) {
                tab = tabOptional.get();
            } else {
                tab = new Tab((String) payload[0]);
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
            tabPane.getSelectionModel().select(tab);
        });
    }
}
