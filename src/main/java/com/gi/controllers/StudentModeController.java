package com.gi.controllers;

import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
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
public class StudentModeController implements Initializable {
    @FXML
    Button homeBtn;
    @FXML
    JFXButton quizBtn;
    @FXML
    AnchorPane ap,ap1,ap2,ap3;


    public void home()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        Stage window = (Stage)homeBtn.getScene().getWindow();
        window.setScene(new Scene(root,730,550));

    }
    public void quiz()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentMode1.fxml"));

        Stage window = (Stage)quizBtn.getScene().getWindow();
        window.setScene(new Scene(root,900,550));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInDown(ap3).play();
        new SlideInDown(ap2).play();
        new SlideInUp(ap).play();
        new SlideInUp(ap1).play();

    }
}
