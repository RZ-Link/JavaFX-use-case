package org.example.demo;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Child2View implements FxmlView<Child2ViewModel>, Initializable {

    @InjectViewModel
    private Child2ViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
