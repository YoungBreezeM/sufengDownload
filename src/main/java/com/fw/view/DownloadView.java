package com.fw.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * @author yqf
 */
public class DownloadView extends Pane {

    public void DownloadView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("downloadView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
