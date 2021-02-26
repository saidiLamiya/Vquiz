package com.gi.controllers;

import com.gi.connection.ConnnectionUtil;
import animatefx.animation.Bounce;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

@Component
public class StudentDetailsController implements Initializable {
    @FXML
    JFXTextField nom,prenom,email,tel,num;
    @FXML
    AnchorPane error,info,pass,error2,error1,valid;
    @FXML
    JFXButton next1Btn;

    @FXML
    JFXPasswordField mdp,mdp1;
    @FXML
    Button homeBtn;
    public void home()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        Stage window = (Stage)homeBtn.getScene().getWindow();
        window.setScene(new Scene(root,730,550));

    }

    public void next1()throws Exception{
        String c1=nom.getText();
        String c2=prenom.getText();
        String c3=email.getText();
        String c4=tel.getText();
        String c5 = num.getText();
        String c6 = mdp.getText();
        String c7 = mdp1.getText();
        if(c5.equals("") || c6.equals("") || c7.equals("")){
            error.setVisible(true);
            new SlideInDown(error).play();
        }else{
            ConnnectionUtil connection = new ConnnectionUtil();
            Connection con = connection.conDB();


            PreparedStatement ps= con.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?)");
            ps.setString(1,c1);
            ps.setString(2,c2);
            ps.setString(3,c3);
            ps.setString(4,c4);
            ps.setString(5,c5);
            ps.setString(6,c7);
            ps.executeUpdate();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

            Stage window = (Stage)next1Btn.getScene().getWindow();
            window.setScene(new Scene(root,730,550));
        }
    }
    public void verification(){
        String c1 = mdp.getText();
        String c2 = mdp1.getText();
        if(c1.equals(c2)){

            error1.setVisible(false);
            valid.setVisible(true);
            new Bounce(valid).play();

        }else{
            error1.setVisible(true);
            valid.setVisible(false);


        }
    }
    public void next()throws Exception{
        String c1=nom.getText();
        String c2=prenom.getText();
        String c3=email.getText();
        String c4=tel.getText();

        if(c1.equals("") || c2.equals("") || c3.equals("") || c4.equals("")){
            error2.setVisible(true);
            new SlideInDown(error2).play();



        }
        else{
            pass.setVisible(true);
            pass.setDisable(false);
            info.setDisable(true);
            info.setVisible(false);

        }

    }

    public void errorGo(){
        error2.setVisible(false);
        error1.setVisible(false);
        error.setVisible(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInRight(info).play();
    }
}
