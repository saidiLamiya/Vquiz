package com.gi.controllers;

import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import com.gi.Data;
import com.gi.connection.ConnnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class StudentMode2Controller implements Initializable {
    @FXML
    Label labelId,userLabel,dateLabel,timerLabel;
    @FXML
    JFXTextArea quest;
    @FXML
    RadioButton opt1,opt2,opt3;
    @FXML
    Button nextBtn;
    @FXML
    AnchorPane ap;
    int questionId=2;
    String answer;
    int mark = 0;
    int numOfQuestions = 0;
    int nextCount = 0;
    int id = 2;

    public void next()throws Exception{
        nextCount++;

        ConnnectionUtil connection = new ConnnectionUtil();
        Connection con = connection.conDB();

        Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=st.executeQuery("SELECT * FROM question WHERE id='"+questionId+"'");


        String answer1 = "";
        if(opt1.isSelected()){
            answer1 = opt1.getText();
        }else if(opt2.isSelected()){
            answer1 = opt2.getText();
        }else {
            answer1 = opt3.getText();
        }


        if(answer1.equals(answer)){
            mark = mark + 1;

        }

        if (nextCount == numOfQuestions){
            System.out.println("mark"+" "+mark);
            Data.note = String.valueOf(mark);
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentMode3.fxml"));

            Stage window = (Stage)nextBtn.getScene().getWindow();
            window.setScene(new Scene(root,900,550));
        }

        while (rs.next()){
            labelId.setText(String.valueOf(id));
            quest.setText(rs.getString(2));
            opt1.setText(rs.getString(3));
            opt2.setText(rs.getString(4));
            opt3.setText(rs.getString(5));
            answer = rs.getString(6);
            id++;
            questionId++;
        }

        System.out.println("mark"+" "+mark);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInDown(ap).play();
        labelId.setText("1");

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        dateLabel.setText(df.format(date));
        try{

            ConnnectionUtil connection = new ConnnectionUtil();
            Connection con = connection.conDB();

            Statement st1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs1 = st1.executeQuery("select * from student where apogee='"+Data.text+"'");
            while(rs1.next()){
                userLabel.setText(rs1.getString(2));
            }
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("SELECT * FROM question WHERE id='"+1+"'");

            Statement st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2=st2.executeQuery("SELECT * FROM question");

            while (rs2.next()){
                numOfQuestions++;
                System.out.println(numOfQuestions);
            }

            while (rs.next()){
                quest.setText(rs.getString(2));
                opt1.setText(rs.getString(3));
                opt2.setText(rs.getString(4));
                opt3.setText(rs.getString(5));
                answer = rs.getString(6);
            }

            String answer1 = "";
            if(opt1.isSelected()){
                answer1 = opt1.getText();
            }else if(opt2.isSelected()){
                answer1 = opt2.getText();
            }else {
                answer1 = opt3.getText();
            }

            if(answer1.equals(answer)){
                mark = mark + 1;

            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}