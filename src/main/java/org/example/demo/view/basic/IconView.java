package org.example.demo.view.basic;

import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class IconView implements FxmlView<IconViewModel>, Initializable {
    public Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SVG svg = new SVG("M838.860702 214.969081c-57.319153-57.323246-155.996186-57.323246-213.320456 0L201.080104 639.430247l0 212.593874 213.320456 0 424.461165-424.461165C897.631996 368.790638 897.631996 273.740376 838.860702 214.969081zM383.927086 779.466184 273.637017 779.466184 273.637017 669.181232l306.917554-306.196088 110.289046 110.289046L383.927086 779.466184zM787.348067 376.045204l-45.712257 45.711234L631.346764 311.471485l45.712257-45.711234c29.746891-29.024403 80.538061-29.024403 110.289046 0C817.820517 296.232701 817.820517 345.57173 787.348067 376.045204z");
        button.setGraphic(svg);
    }
}
