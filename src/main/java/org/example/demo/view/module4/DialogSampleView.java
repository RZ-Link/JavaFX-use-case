package org.example.demo.view.module4;

import de.saxsys.mvvmfx.FxmlView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.demo.view.module3.ReadImageViewModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * DialogSampleView
 *
 * @author ruiqi
 * @date 2024/7/26 9:49
 */
public class DialogSampleView implements FxmlView<DialogSampleViewModel>, Initializable {

    @FXML
    private VBox 必填项输入;

    @FXML
    private HBox inputHbox;

    @FXML
    private RequiredInputControl requiredInputControl;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(List.of("a","b"));
        TextArea textArea = new TextArea();
        // TreeView treeView = new TreeView();
        // treeView.setRoot(new TreeItem("aaa"));




        CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>("Root");
        CheckBoxTreeItem<String> child1 = new CheckBoxTreeItem<>("Child 1");
        CheckBoxTreeItem<String> child2 = new CheckBoxTreeItem<>("Child 2");

        rootItem.getChildren().addAll(child1, child2);

        // Select all children when parent is selected
        rootItem.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                rootItem.getChildren().forEach(child -> ((CheckBoxTreeItem<String>) child).setSelected(true));
            }
        });

        TreeView<String> treeView = new TreeView<>(rootItem);
        必填项输入.getChildren().add(treeView);

        // requiredInputControl.resetControl();
        // requiredInputControl.resetControl(textArea);
        // requiredInputControl.getValueProperty().bindBidirectional(new SimpleStringProperty("acbd"));
    }

    private String getValue(){
        for(Node node:必填项输入.getChildren()){
            if(node instanceof TextField){
                return ((TextField) node).getText();
            }
        }
        return null;
    }

    private void setRequiredInputControlState(boolean isDanger){
        for(Node node:必填项输入.getChildren()){
            if(node instanceof Control) {
                node.pseudoClassStateChanged(PseudoClass.getPseudoClass("danger"), isDanger);
            }
            if(node instanceof Label){
                node.setVisible(isDanger);
            }
        }
    }

    @FXML
    private void onCheckBtnAction(){
        //检查输入项
        if(getValue()==null||getValue().length()==0){
            System.out.println("输入项为空");
            setRequiredInputControlState(true);
        }else{
            setRequiredInputControlState(false);
        }
        if(requiredInputControl.getControl() instanceof TextField){
            if(((TextField) requiredInputControl.getControl()).getText()==null||((TextField) requiredInputControl.getControl()).getText().length()==0){
                System.out.println("输入项为空");
                requiredInputControl.setRequiredInputControlState(true);
            }else{
                requiredInputControl.setRequiredInputControlState(false);
            }
        }else if(requiredInputControl.getControl() instanceof ComboBox){
            if(((ComboBox) requiredInputControl.getControl()).getValue()==null){
                System.out.println("输入项为空");
                requiredInputControl.setRequiredInputControlState(true);
            }else{
                requiredInputControl.setRequiredInputControlState(false);
            }
        }
    }

    @FXML
    private void onSuccessBtnAction(){
        TipsDialogView.create("提示","操作成功！", TipsDialogView.TipsDialogType.SUCCESS,result->{
            System.out.println("操作成功！");
        });
    }
    @FXML
    private void onInfoBtnAction(){
        TipsDialogView.create("提示","提示消息1", TipsDialogView.TipsDialogType.SUCCESS,result->{
            System.out.println("提示消息1");
        });
    }
    @FXML
    private void onWarnBtnAction(){
        TipsDialogView.create("提示","警告消息1", TipsDialogView.TipsDialogType.SUCCESS,result->{
            System.out.println("警告消息1");
        });
    }
    @FXML
    private void onErrorBtnAction(){
        TipsDialogView.create("提示","操作失败！", TipsDialogView.TipsDialogType.SUCCESS,result->{
            System.out.println("操作失败！");
        });
    }
}
