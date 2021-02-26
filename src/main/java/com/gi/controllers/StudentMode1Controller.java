package com.gi.controllers;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class StudentMode1Controller implements Initializable {
    @FXML
    Button homeBtn,contBtn;
    @FXML
    AnchorPane info;





    public void home()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        Stage window = (Stage)homeBtn.getScene().getWindow();
        window.setScene(new Scene(root,730,550));

    }
    public void contin()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentMode2.fxml"));

        Stage window = (Stage)homeBtn.getScene().getWindow();
        window.setScene(new Scene(root,900,550));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInDown(info).play();

    }
}
