package org.example.demo;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloWorldView implements FxmlView<HelloWorldViewModel>, Initializable {

    @FXML
    private Label helloLabel;

    @InjectViewModel
    private HelloWorldViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloLabel.textProperty().bind(viewModel.helloMessage());
    }

    @FXML
    protected void onHelloButtonClick() {
        viewModel.setHelloMessage("Welcome to JavaFX Application!");
        throw new RuntimeException("测试DefaultExceptionHandler");
    }

}
