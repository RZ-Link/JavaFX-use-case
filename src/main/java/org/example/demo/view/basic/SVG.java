package org.example.demo.view.basic;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;

public class SVG extends Region {

    private SimpleStringProperty paths;

    public SVG() {
        paths = new SimpleStringProperty();
        paths.addListener((observable, oldValue, newValue) -> {
            SVGPath svgPath = new SVGPath();
            svgPath.setContent(newValue);

            this.setShape(svgPath);
            this.setPrefSize(14, 14);
            this.setStyle("-fx-background-color: #ffffff;");
        });
    }

    public SVG(String paths) {
        this();
        this.paths.setValue(paths);
    }

    public String getPaths() {
        return paths.get();
    }

    public void setPaths(String paths) {
        this.paths.set(paths);
    }
}
