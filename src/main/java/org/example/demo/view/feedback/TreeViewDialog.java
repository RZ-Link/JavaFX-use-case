package org.example.demo.view.feedback;

import atlantafx.base.theme.Tweaks;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.demo.entity.DirectoryVO;

import java.io.IOException;
import java.util.function.Consumer;

public class TreeViewDialog {
    public Button confirmButton;
    public Button cancelButton;

    public TreeView<DirectoryVO> treeView;

    private void initialize(Consumer<DirectoryVO> confirmAction, Stage stage) {
        confirmButton.setOnAction(e -> {
            TreeItem<DirectoryVO> treeItem = treeView.getSelectionModel().getSelectedItem();
            if (treeItem != null) {
                confirmAction.accept(treeItem.getValue());
            }
            stage.close();
        });
        cancelButton.setOnAction(e -> {
            stage.close();
        });

        var item1 = new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d1")));
        item1.getChildren().setAll(
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d1-1"))),
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d1-2"))),
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d1-3")))
        );

        var item2 = new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d2")));
        item2.getChildren().setAll(
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d2-1"))),
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d2-2"))),
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d2-3")))
        );

        var item3 = new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d3")));
        item3.getChildren().setAll(
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d3-1"))),
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d3-2"))),
                new TreeItem<>(new DirectoryVO(new SimpleStringProperty("d3-3")))
        );

        var root = new TreeItem<>(new DirectoryVO(new SimpleStringProperty("root")));
        root.setExpanded(true);
        root.getChildren().addAll(item1, item2, item3);

        treeView.setRoot(root);
        treeView.getStyleClass().add(Tweaks.ALT_ICON);

        treeView.setCellFactory(new Callback<TreeView<DirectoryVO>, TreeCell<DirectoryVO>>() {
            @Override
            public TreeCell<DirectoryVO> call(TreeView<DirectoryVO> stringTreeView) {
                TreeCell<DirectoryVO> treeCell = new TreeCell<DirectoryVO>() {
                    @Override
                    protected void updateItem(DirectoryVO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label label = new Label(item.getName().getValue());
                            setGraphic(label);
                        }
                    }
                };
                return treeCell;
            }
        });

    }

    /**
     * @param title         标题
     * @param confirmAction 确认执行的方法
     */
    public static void create(String title, Consumer<DirectoryVO> confirmAction) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ConfirmDialog.class.getResource("/org/example/demo/view/feedback/TreeViewDialog.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BorderPane root = loader.getRoot();
        TreeViewDialog controller = loader.getController();
        controller.initialize(confirmAction, stage);
        // 设置标题
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
