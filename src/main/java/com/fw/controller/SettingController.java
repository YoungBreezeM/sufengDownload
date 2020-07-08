package com.fw.controller;

import com.fw.domain.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yqf
 */
public class SettingController {

    @FXML
    public TextField savePath;
    @FXML
    public Button chooserPath;
    @FXML
    public TextField workThread;
    @FXML
    public TextField corePoolSize;
    @FXML
    public TextField maximumPoolSize;
    @FXML
    public TextField numberOfBytes;
    @FXML
    public Button save;
    @FXML
    private AnchorPane settingPage;

    /**
     * 初始化数据
     */
    public void initData() {
        System.out.println("设置页面数据初始化成功");
        initSetting();
    }

    private void initSetting() {
        Yaml yaml = new Yaml();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.yml");
        Config config = yaml.loadAs(resourceAsStream, Config.class);

        savePath.setText(config.getSavePath());
        workThread.setText(config.getWorkThread());
        corePoolSize.setText(config.getCorePoolSize());
        numberOfBytes.setText(config.getNumberOfBytes());
        maximumPoolSize.setText(config.getMaximumPoolSize());
    }

    @FXML
    public void toChooserPath(MouseEvent mouseEvent) {
        System.out.println("选择路径");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("择存文件储路径");
        Stage stage = (Stage) settingPage.getScene().getWindow();
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            savePath.setText(directory.toString());
        }
    }

    public void saveSetting(MouseEvent mouseEvent) throws IOException {
        Yaml yaml = new Yaml();
        String filePath = getClass().getClassLoader().getResource("config.yml").getFile();

        Config config = new Config(
                savePath.getText(),
                workThread.getText(),
                numberOfBytes.getText(),
                corePoolSize.getText(),
                maximumPoolSize.getText(),
                "5"
        );

        System.out.println(config);

        yaml.dump(config, new FileWriter(filePath));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("信息");
        alert.headerTextProperty().set("保存成功");
        alert.showAndWait();
    }
}
