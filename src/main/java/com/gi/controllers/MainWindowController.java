package com.gi.controllers;

import animatefx.animation.*;

import com.gi.Data;
import com.gi.connection.ConnnectionUtil;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {
    @FXML
    Button btn,btn1,stdBtn2;
    @FXML
    ImageView tae,right;
    @FXML
    JFXTextField username,stdUser;
    @FXML
    JFXPasswordField pass,stdPass;
    @FXML
    AnchorPane error,error1;
    @FXML
    Line left;
    @FXML
    Label down;

    public void taeMove()throws Exception{
        
    }




    //student Register

    public void goStudent()throws Exception{


            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentDetails1.fxml"));

            Stage window = (Stage)stdBtn2.getScene().getWindow();
            window.setScene(new Scene(root,900,550));

    }
    public void closeE()throws Exception{

        error.setVisible(false);
        username.setUnFocusColor(Paint.valueOf("#000"));
        stdUser.setUnFocusColor(Paint.valueOf("#000"));
        pass.setUnFocusColor(Paint.valueOf("#000"));
        stdPass.setUnFocusColor(Paint.valueOf("#000"));
        error1.setVisible(false);

    }


    // Teacher Login :
    public void teacherLogin() throws Exception {
        String user = username.getText();
        String pass1 = pass.getText();

        //connection
        ConnnectionUtil connection = new ConnnectionUtil();
        Connection con = connection.conDB();

        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

        ResultSet rs= st.executeQuery("SELECT * FROM teacher WHERE username='"+user+"' AND password='"+pass1+"'");


        if (rs.first()) {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/SideBarNext.fxml"));
            Stage window = (Stage) btn1.getScene().getWindow();
            window.setScene(new Scene(root, 900, 550));
        }
        else{
            username.setUnFocusColor(Paint.valueOf("#E32B24"));
            pass.setUnFocusColor(Paint.valueOf("#E32B24"));
            error.setVisible(true);
            new SlideInUp(error).play();
        }
    }

    // Student login :
    public void studentLogin() throws Exception {
        String sUser = stdUser.getText();
        String sPass = stdPass.getText();

        ConnnectionUtil connection = new ConnnectionUtil();
        Connection con = connection.conDB();

        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs =  st.executeQuery("SELECT * FROM student WHERE apogee='"+sUser+"' AND password='"+sPass+"'");




        if(rs.first()) {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentMode.fxml"));
            Stage window = (Stage)btn.getScene().getWindow();
            window.setScene(new Scene(root, 900, 550));
            Data.text = sUser;
        }else{
            stdUser.setUnFocusColor(Paint.valueOf("#E32B24"));
            stdPass.setUnFocusColor(Paint.valueOf("#E32B24"));
            error1.setVisible(true);
            new SlideInUp(error1).play();

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInRight(right).play();
        new SlideInUp(down).play();
        new SlideInLeft(left).play();
        new SlideInUp(tae).play();
    }
}
