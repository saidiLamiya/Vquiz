package com.gi.controllers;

import animatefx.animation.Bounce;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
import animatefx.animation.Swing;
import com.gi.connection.ConnnectionUtil;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

@Component
public class newQuestion1Controller implements Initializable {
    @FXML
    Label labelId;
    @FXML
    JFXTextArea quest,opt1,opt2,opt3,ans;
    @FXML
    AnchorPane error;
    @FXML
    Button clearBtn,saveBtn;
    public void saveQuestion()throws Exception{
        String id=labelId.getText();
        String question = quest.getText();
        String option1 = opt1.getText();
        String option2 = opt2.getText();
        String option3 = opt3.getText();
        String answer = ans.getText();
        if(question.equals("") || option1.equals("") || option2.equals("") || option3.equals("") || answer.equals("")){
            error.setVisible(true);
            new SlideInDown(error).play();
        }
        else{
            ConnnectionUtil connection = new ConnnectionUtil();
            Connection con = connection.conDB();


            PreparedStatement ps= con.prepareStatement("INSERT INTO question VALUES(?,?,?,?,?,?)");
            ps.setString(1,id);
            ps.setString(2,question);
            ps.setString(3,option1);
            ps.setString(4,option2);
            ps.setString(5,option3);
            ps.setString(6,answer);
            ps.executeUpdate();
            new Swing(saveBtn).play();
            clear();
            setStuff();
        }
    }
    public void closeE()throws Exception{
        error.setVisible(false);

    }



    public void clear() throws Exception{
        quest.setText("");
        opt1.setText("");
        opt2.setText("");
        opt3.setText("");
        ans.setText("");
        new Swing(clearBtn).play();
    }
    public void setStuff()throws Exception{

        ConnnectionUtil connection = new ConnnectionUtil();
        Connection con = null;
        try {
            con = connection.conDB();
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select count(id) from question");
            if(rs.first()){
                int id = rs.getInt(1);
                id = id +1;
                String str=String.valueOf(id);
                labelId.setText(str);

            }else labelId.setText("1");
            new Bounce(labelId).play();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setStuff();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
