package org.example.demo.view.feedback;

import atlantafx.base.controls.Message;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.example.demo.view.module1.TableViewModel;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2OutlinedAL;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageView implements FxmlView<MessageViewModel>, Initializable {

    @InjectViewModel
    private MessageViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSuccessButtonClick(ActionEvent actionEvent) {
        MessageUtils.success("成功消息");
    }
}
