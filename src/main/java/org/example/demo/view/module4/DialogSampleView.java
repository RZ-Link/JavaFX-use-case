package org.example.demo.view.module4;

import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.example.demo.view.module3.ReadImageViewModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * DialogSampleView
 *
 * @author ruiqi
 * @date 2024/7/26 9:49
 */
public class DialogSampleView implements FxmlView<DialogSampleViewModel>, Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
