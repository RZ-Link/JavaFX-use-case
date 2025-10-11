package org.example.demo.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class SelectableTableItemEntity {
    private SimpleBooleanProperty select;
    private SimpleStringProperty column1;
    private SimpleStringProperty column2;
}
