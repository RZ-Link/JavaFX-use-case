package org.example.demo.view.main;

import cn.hutool.core.util.StrUtil;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.example.demo.view.basic.ReadIconView;
import org.example.demo.view.feedback.DialogView;
import org.example.demo.view.feedback.LoadingView;
import org.example.demo.view.feedback.MessageView;
import org.example.demo.view.bug.ComboBoxBugView;
import org.example.demo.view.bug.TableBugView;
import org.example.demo.view.global.ThemeView;
import org.example.demo.view.table.*;
import org.example.demo.view.pagination.PaginationView;
import org.example.demo.view.global.ReadFontView;
import org.example.demo.view.image.ReadImageView;
import org.example.demo.view.form.FormValidateView;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable {


    public StackPane menu;
    public TabPane tabPane;

    @InjectViewModel
    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenu();
    }

    public void initMenu() {
        HashMap<TreeItem<String>, Ikon> treeItemIconMap = new HashMap<>();
        HashMap<TreeItem<String>, Class> treeItemClassMap = new HashMap<>();

        TreeItem<String> global = new TreeItem<>("全局配置");
        treeItemIconMap.put(global, Feather.GLOBE);
        TreeItem<String> theme = new TreeItem<>("主题");
        treeItemClassMap.put(theme, ThemeView.class);
        TreeItem<String> font = new TreeItem<>("字体");
        treeItemClassMap.put(font, ReadFontView.class);
        global.getChildren().addAll(theme, font);
        global.setExpanded(true);

        TreeItem<String> basic = new TreeItem<>("基础组件");
        treeItemIconMap.put(basic, Feather.COMMAND);
        TreeItem<String> icon = new TreeItem<>("图标");
        treeItemClassMap.put(icon, ReadIconView.class);
        basic.getChildren().addAll(icon);
        basic.setExpanded(true);

        TreeItem<String> form = new TreeItem<>("表单");
        treeItemIconMap.put(form, Feather.FILE_TEXT);
        TreeItem<String> validate = new TreeItem<>("表单校验");
        treeItemClassMap.put(validate, FormValidateView.class);
        form.getChildren().addAll(validate);
        form.setExpanded(true);

        TreeItem<String> image = new TreeItem<>("图片");
        treeItemIconMap.put(image, Feather.IMAGE);
        treeItemClassMap.put(image, ReadImageView.class);

        TreeItem<String> fxPagination = new TreeItem<>("分页");
        treeItemIconMap.put(fxPagination, Feather.ARROW_RIGHT_CIRCLE);
        treeItemClassMap.put(fxPagination, PaginationView.class);

        TreeItem<String> table = new TreeItem<>("表格");
        treeItemIconMap.put(table, Feather.COLUMNS);
        TreeItem<String> basicTable = new TreeItem<>("基础表格");
        treeItemClassMap.put(basicTable, BasicTableView.class);
        TreeItem<String> editableTable = new TreeItem<>("可编辑表格");
        treeItemClassMap.put(editableTable, EditableTableView.class);
        TreeItem<String> groupingHeadTable = new TreeItem<>("表头分组表格");
        treeItemClassMap.put(groupingHeadTable, GroupingHeadTableView.class);
        TreeItem<String> centerTable = new TreeItem<>("居中表格");
        treeItemClassMap.put(centerTable, CenterTableView.class);
        TreeItem<String> selectableTable = new TreeItem<>("多选表格");
        treeItemClassMap.put(selectableTable, SelectableTableView.class);
        table.getChildren().addAll(basicTable, editableTable, groupingHeadTable, centerTable, selectableTable);
        table.setExpanded(true);

        TreeItem<String> feedback = new TreeItem<>("反馈组件");
        treeItemIconMap.put(feedback, Feather.MESSAGE_SQUARE);
        TreeItem<String> dialog = new TreeItem<>("对话框");
        treeItemClassMap.put(dialog, DialogView.class);
        TreeItem<String> loading = new TreeItem<>("加载");
        treeItemClassMap.put(loading, LoadingView.class);
        TreeItem<String> message = new TreeItem<>("消息提示");
        treeItemClassMap.put(message, MessageView.class);
        feedback.getChildren().addAll(dialog, loading, message);
        feedback.setExpanded(true);

        TreeItem<String> bug = new TreeItem<>("JavaFX UI Bug");
        treeItemIconMap.put(bug, Feather.TOOL);
        TreeItem<String> tableBug = new TreeItem<>("Table Bug");
        treeItemClassMap.put(tableBug, TableBugView.class);
        TreeItem<String> comboBoxBug = new TreeItem<>("ComboBox Bug");
        treeItemClassMap.put(comboBoxBug, ComboBoxBugView.class);
        bug.getChildren().addAll(tableBug, comboBoxBug);
        bug.setExpanded(true);

        TreeItem<String> rootItem = new TreeItem<>();
        rootItem.getChildren().addAll(global, basic, form, image, fxPagination, table, feedback, bug);
        rootItem.setExpanded(true);

        // 创建TreeView，设置节点
        TreeView<String> treeView = new TreeView<>(rootItem);
        // 隐藏根节点
        treeView.setShowRoot(false);
        // 设置TreeCell
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> stringTreeView) {
                TreeCell<String> treeCell = new TreeCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        // 隐藏折叠图标
                        setDisclosureNode(null);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label label = new Label(item);
                            label.getStyleClass().add("main-view-left-tree-cell-label");
                            if (treeItemIconMap.containsKey(getTreeItem())) {
                                label.setGraphic(FontIcon.of(treeItemIconMap.get(getTreeItem())));
                            }
                            label.setGraphicTextGap(10);
                            setGraphic(label);
                        }
                    }
                };
                treeCell.getStyleClass().add("main-view-left-tree-cell");
                return treeCell;
            }
        });

        // 监听用户选择模块变化，更新窗口右侧页面
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isLeaf()) {
                String tabTitle = newValue.getValue();

                Tab tab;
                // 查询TabPane是否已经存在需要展示的Tab。如果已经存在，直接切换。
                Optional<Tab> tabOptional = tabPane.getTabs().stream()
                        .filter(t -> StrUtil.equals(t.getText(), tabTitle))
                        .findFirst();
                if (tabOptional.isPresent()) {
                    tab = tabOptional.get();
                } else {
                    Parent parent;
                    if (treeItemClassMap.containsKey(newValue)) {
                        parent = FluentViewLoader.fxmlView(treeItemClassMap.get(newValue)).load().getView();
                    } else {
                        return;
                    }

                    StackPane stackPane = new StackPane();
                    stackPane.getStyleClass().add("main-view-center-tab-pane");
                    stackPane.getChildren().add(parent);

                    tab = new Tab(tabTitle);
                    tab.setContent(stackPane);

                    tabPane.getTabs().add(tab);
                }
                tabPane.getSelectionModel().select(tab);
            }
        });
        menu.getChildren().add(treeView);
    }
}
