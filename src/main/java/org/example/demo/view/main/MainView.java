package org.example.demo.view.main;

import cn.hutool.core.util.StrUtil;
import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.demo.view.module1.EditableTableView;
import org.example.demo.view.module1.GroupingHeadTableView;
import org.example.demo.view.module1.TableView;
import org.example.demo.view.module2.PaginationView;
import org.example.demo.component.SideMenu;
import org.example.demo.view.module3.ReadImageView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable {

    @FXML
    private BorderPane borderPane;

    private SideMenu sideMenu;
    private TabPane tabPane;

    @InjectViewModel
    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 左侧菜单
        sideMenu = new SideMenu();
        sideMenu.init(this);
        // 右侧标签
        tabPane = new TabPane();

        borderPane.setLeft(sideMenu);
        borderPane.setCenter(tabPane);
    }

    public void updateTabPane(String tabTitle) {
        Tab tab;
        // 查询TabPane是否已经存在需要展示的Tab。如果已经存在，直接切换。
        Optional<Tab> tabOptional = tabPane.getTabs().stream()
                .filter(t -> StrUtil.equals(t.getText(), tabTitle))
                .findFirst();
        if (tabOptional.isPresent()) {
            tab = tabOptional.get();
        } else {
            tab = new Tab(tabTitle);
            switch (tabTitle) {
                case "表格demo":
                    tab.setContent(FluentViewLoader.fxmlView(TableView.class).load().getView());
                    break;
                case "可编辑表格demo":
                    tab.setContent(FluentViewLoader.fxmlView(EditableTableView.class).load().getView());
                    break;
                case "表头分组表格demo":
                    tab.setContent(FluentViewLoader.fxmlView(GroupingHeadTableView.class).load().getView());
                    break;
                case "分页组件demo":
                    tab.setContent(FluentViewLoader.fxmlView(PaginationView.class).load().getView());
                    break;
                case "图片读取demo":
                    tab.setContent(FluentViewLoader.fxmlView(ReadImageView.class).load().getView());
                    break;
                default:
                    return;
            }
            tabPane.getTabs().add(tab);
        }
        tabPane.getSelectionModel().select(tab);
    }
}
