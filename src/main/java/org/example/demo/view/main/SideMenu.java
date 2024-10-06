package org.example.demo.view.main;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashMap;

// 左侧菜单
public class SideMenu extends StackPane {

    private MainView mainView;

    public SideMenu() {

        HashMap<TreeItem<String>, Ikon> treeItemIconMap = new HashMap<>();

        // 创建根节点
        TreeItem<String> rootItem = new TreeItem<>();

        // 创建子节点
        TreeItem<String> global = new TreeItem<>("全局配置");
        treeItemIconMap.put(global, Feather.APERTURE);
        TreeItem<String> theme = new TreeItem<>("主题");
        TreeItem<String> font = new TreeItem<>("字体");
        global.getChildren().addAll(theme, font);
        global.setExpanded(true);

        TreeItem<String> basic = new TreeItem<>("基础组件");
        treeItemIconMap.put(basic, Feather.AWARD);
        TreeItem<String> icon = new TreeItem<>("图标");
        basic.getChildren().addAll(icon);
        basic.setExpanded(true);

        TreeItem<String> form = new TreeItem<>("表单");
        treeItemIconMap.put(form, Feather.FILE_TEXT);
        TreeItem<String> validate = new TreeItem<>("表单校验");
        form.getChildren().addAll(validate);
        form.setExpanded(true);

        TreeItem<String> image = new TreeItem<>("图片");
        treeItemIconMap.put(image, Feather.IMAGE);

        TreeItem<String> fxPagination = new TreeItem<>("分页");
        treeItemIconMap.put(fxPagination, Feather.FAST_FORWARD);

        TreeItem<String> table = new TreeItem<>("表格");
        treeItemIconMap.put(table, Feather.COLUMNS);
        TreeItem<String> basicTable = new TreeItem<>("基础表格");
        TreeItem<String> editableTable = new TreeItem<>("可编辑表格");
        TreeItem<String> groupingHeadTable = new TreeItem<>("表头分组表格");
        TreeItem<String> centerTable = new TreeItem<>("居中表格");
        table.getChildren().addAll(basicTable, editableTable, groupingHeadTable, centerTable);
        table.setExpanded(true);

        TreeItem<String> feedback = new TreeItem<>("反馈组件");
        treeItemIconMap.put(feedback, Feather.MESSAGE_CIRCLE);
        TreeItem<String> loading = new TreeItem<>("加载");
        TreeItem<String> message = new TreeItem<>("消息提示");
        TreeItem<String> messageBox = new TreeItem<>("消息弹出框");
        feedback.getChildren().addAll(loading, message, messageBox);
        feedback.setExpanded(true);

        TreeItem<String> bug = new TreeItem<>("JavaFX UI组件Bug");
        treeItemIconMap.put(bug, Feather.TOOL);
        TreeItem<String> tableBug = new TreeItem<>("表格组件Bug");
        TreeItem<String> comboBoxBug = new TreeItem<>("输入组件Bug");
        bug.getChildren().addAll(tableBug, comboBoxBug);
        bug.setExpanded(true);

        // 根节点添加子节点
        rootItem.getChildren().addAll(global, basic, form, image, fxPagination, table, feedback, bug);
        rootItem.setExpanded(true);

        // 创建TreeView，设置根节点
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
                            if (treeItemIconMap.containsKey(this.getTreeItem())) {
                                label.setGraphic(FontIcon.of(treeItemIconMap.get(this.getTreeItem()))); // 设置图标
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
                if (mainView != null) {
                    mainView.updateTabPane(newValue.getValue());
                }
            }
        });

        // StackPane添加TreeView
        getChildren().addAll(treeView);
    }

    public void init(MainView mainView) {
        this.mainView = mainView;
    }

}
