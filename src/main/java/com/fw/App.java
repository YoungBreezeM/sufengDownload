package com.fw;

import com.fw.controller.Controller;
import com.fw.domain.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Objects;


/**
 * @author yqf
 */
public class App extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/application.fxml")));
        primaryStage.setTitle("速蜂下载器");
        Scene scene = new Scene(root, 1000, 575);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("style/main.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
