package org.example.demo.component;

import de.saxsys.mvvmfx.MvvmFX;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.example.demo.event.EventConsts;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

// 左侧菜单
public class SideMenu extends StackPane {

    public SideMenu() {
        // 创建根节点
        TreeItem<String> rootItem = new TreeItem<>();
        // 展开根节点
        rootItem.setExpanded(true);
        // 创建子节点
        TreeItem<String> child1 = new TreeItem<>("模块1");
        TreeItem<String> child2 = new TreeItem<>("模块2");
        TreeItem<String> child3 = new TreeItem<>("模块3");
        TreeItem<String> subChild31 = new TreeItem<>("模块3-1");
        TreeItem<String> subChild32 = new TreeItem<>("模块3-2");
        child3.getChildren().addAll(subChild31, subChild32);
        // 根节点添加子节点
        rootItem.getChildren().addAll(child1, child2, child3);
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
                            setText(null);

                            Label label = new Label(item);
                            label.setGraphic(FontIcon.of(Feather.CHEVRON_DOWN)); // 设置图标
                            label.setGraphicTextGap(10);
                            setGraphic(label);
                        }
                    }
                };
                // 如果当前模块存在子模块，点击展开/折叠菜单
                treeCell.setOnMouseClicked(event -> {
                    if (!treeCell.isEmpty()) {
                        treeCell.getTreeItem().setExpanded(!treeCell.getTreeItem().isExpanded());
                    }
                });
                return treeCell;
            }
        });

        // 监听用户选择模块变化，推送事件（更新窗口右侧页面）
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isLeaf()) {
                MvvmFX.getNotificationCenter().publish(EventConsts.SideMenuSelected.getKey(), newValue.getValue());
            }
        });

        // StackPane添加TreeView
        getChildren().addAll(treeView);
    }
}
