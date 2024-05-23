package org.example.demo;

import atlantafx.base.theme.Tweaks;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;
import org.example.demo.event.EventConsts;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Optional;

// 侧边菜单
public class SideMenu extends StackPane {

    public SideMenu() {

        // 创建根节点
        TreeItem<String> rootItem = new TreeItem<>("Root");
        // 展开根节点
        rootItem.setExpanded(true);
        // 创建子节点
        TreeItem<String> child1 = new TreeItem<>("Child 1");
        TreeItem<String> child2 = new TreeItem<>("Child 2");
        // 将子节点添加到根节点
        rootItem.getChildren().addAll(child1, child2);
        // 创建TreeView并设置根节点
        TreeView<String> treeView = new TreeView<>(rootItem);
        // 不显示根节点
        treeView.setShowRoot(false);

        // TreeCell设置自定义的样式
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> stringTreeView) {
                TreeCell treeCell = new TreeCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item);
                        }
                    }
                };
                return treeCell;
            }
        });

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isLeaf()) {
                MvvmFX.getNotificationCenter().publish(EventConsts.SideMenuSelected.getKey(), newValue.getValue());
            }
        });

        getChildren().addAll(treeView);
    }
}
