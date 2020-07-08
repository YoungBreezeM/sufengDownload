package com.fw.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yqf
 */
public class Controller  {


    @FXML
    public WebView browser;
    @FXML
    private DownloadController downloadController;
    @FXML
    private SettingController settingController;

    @FXML
    private void initDownload(Event event) {
        System.out.println("初始化下载页面");
        downloadController.initData();
    }

    @FXML
    public void initSetting(Event event) {
        System.out.println("初始化设置页面");
        settingController.initData();
    }



    private void toBrowser(String url) {

    }


    public void toStart(MouseEvent mouseEvent) {

    }
}
