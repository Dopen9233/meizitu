package top.dopen.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Map<String ,String > direct = new HashMap<>();

    @FXML
    private Button sub ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }
    public void getLocalPath(ActionEvent event){

    }

    public void chooseFilePath(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择上传路径");
        directoryChooser.setInitialDirectory(new File("."));//默认当前路径
        File file = directoryChooser.showDialog(new Stage());
        if(file != null){
            direct.put("localPath",file.getPath());
        }

    }

    public void getType(ActionEvent event){
        System.out.println("修改提交按钮");
        sub.setDisable(false);
    }



    /*public void chooseFilePath(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择上传路径");
        directoryChooser.setInitialDirectory(new File("."));
        *//*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件夹");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("选择路径", "/");
        fileChooser.getExtensionFilters().add(extFilter);*//*
        File file = directoryChooser.showDialog(new Stage());
        *//*System.out.println(file);*//*
        if(file != null){
            direct.put("localPath",file.getPath());
        }

    }*/
}
