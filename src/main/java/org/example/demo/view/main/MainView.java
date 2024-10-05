package org.example.demo.view.main;

import atlantafx.base.theme.Styles;
import cn.hutool.core.util.StrUtil;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.example.demo.component.SideMenu;
import org.example.demo.view.feedback.DialogSampleView;
import org.example.demo.view.feedback.LoadingView;
import org.example.demo.view.feedback.MessageView;
import org.example.demo.view.bug.InputControlBugView;
import org.example.demo.view.bug.TableViewBugView;
import org.example.demo.view.global.ThemeView;
import org.example.demo.view.table.CenterTableView;
import org.example.demo.view.table.EditableTableView;
import org.example.demo.view.table.GroupingHeadTableView;
import org.example.demo.view.table.BasicTableView;
import org.example.demo.view.module2.PaginationView;
import org.example.demo.view.module3.FileIconView;
import org.example.demo.view.module3.ReadFontView;
import org.example.demo.view.module3.ReadImageView;
import org.example.demo.view.module4.FormValidateView;

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
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        tabPane.getStyleClass().add(Styles.TABS_CLASSIC);

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
            Parent parent;
            switch (tabTitle) {
                case "基础表格":
                    parent = FluentViewLoader.fxmlView(BasicTableView.class).load().getView();
                    break;
                case "可编辑表格":
                    parent = FluentViewLoader.fxmlView(EditableTableView.class).load().getView();
                    break;
                case "表头分组表格":
                    parent = FluentViewLoader.fxmlView(GroupingHeadTableView.class).load().getView();
                    break;
                case "居中表格":
                    parent = FluentViewLoader.fxmlView(CenterTableView.class).load().getView();
                    break;
                case "分页组件":
                    parent = FluentViewLoader.fxmlView(PaginationView.class).load().getView();
                    break;
                case "图片读取":
                    parent = FluentViewLoader.fxmlView(ReadImageView.class).load().getView();
                    break;
                case "字体读取":
                    parent = FluentViewLoader.fxmlView(ReadFontView.class).load().getView();
                    break;
                case "文件图标读取":
                    parent = FluentViewLoader.fxmlView(FileIconView.class).load().getView();
                    break;
                case "消息提示":
                    parent = FluentViewLoader.fxmlView(MessageView.class).load().getView();
                    break;
                case "消息弹出框":
                    parent = FluentViewLoader.fxmlView(DialogSampleView.class).load().getView();
                    break;
                case "加载":
                    parent = FluentViewLoader.fxmlView(LoadingView.class).load().getView();
                    break;
                case "表单校验":
                    parent = FluentViewLoader.fxmlView(FormValidateView.class).load().getView();
                    break;
                case "表格组件Bug":
                    parent = FluentViewLoader.fxmlView(TableViewBugView.class).load().getView();
                    break;
                case "输入组件Bug":
                    parent = FluentViewLoader.fxmlView(InputControlBugView.class).load().getView();
                    break;
                case "主题":
                    parent = FluentViewLoader.fxmlView(ThemeView.class).load().getView();
                    break;
                default:
                    return;
            }
            StackPane stackPane = new StackPane();
            stackPane.setId("main-view-center-tab-pane");
            stackPane.getChildren().add(parent);

            tab = new Tab(tabTitle);
            tab.setContent(stackPane);

            tabPane.getTabs().add(tab);
        }
        tabPane.getSelectionModel().select(tab);
    }
}
