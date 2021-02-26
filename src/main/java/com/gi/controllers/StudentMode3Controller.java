package com.gi.controllers;

import animatefx.animation.Bounce;
import animatefx.animation.RotateInDownLeft;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
import com.gi.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ResourceBundle;

@Component
public class StudentMode3Controller  implements Initializable {
    @FXML
    ImageView done;
    @FXML
    AnchorPane info;
    @FXML
    Label mark;
    @FXML
    Button bBtn;
    public void back() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        Stage window = (Stage)bBtn.getScene().getWindow();
        window.setScene(new Scene(root,730,550));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInUp(info).play();
        new Bounce(done).play();

        mark.setText(Data.note);


    }
}
