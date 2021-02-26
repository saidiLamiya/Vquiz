package com.gi.controllers;

import animatefx.animation.Bounce;
import animatefx.animation.BounceInDown;
import animatefx.animation.SlideInDown;
import animatefx.animation.Swing;
import com.gi.connection.ConnnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

@Component

public class updateQuestionController implements Initializable {
    @FXML
    JFXTextArea quest,opt1,opt2,opt3,ans;
    @FXML
    Button searchBtn;
    @FXML
    Label nb;
    @FXML
    JFXTextField search;
    @FXML
    JFXButton clearBtn,saveBtn;
    @FXML
    AnchorPane error,succ,error1;
    public void search()throws Exception {
        succ.setVisible(false);

        String id=search.getText();




        ConnnectionUtil connection = new ConnnectionUtil();
        Connection con = connection.conDB();

        Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=st.executeQuery("SELECT * FROM question WHERE id='"+id+"'");
        if(rs.first()){
            quest.setVisible(true);
            new SlideInDown(quest).play();
            opt1.setVisible(true);
            new SlideInDown(opt1).play();
            opt2.setVisible(true);
            new SlideInDown(opt2).play();
            opt3.setVisible(true);
            new SlideInDown(opt3).play();
            ans.setVisible(true);
            new SlideInDown(ans).play();
            nb.setVisible(true);
            new SlideInDown(nb).play();
            saveBtn.setVisible(true);
            clearBtn.setVisible(true);
            new BounceInDown(saveBtn).play();
            new BounceInDown(clearBtn).play();
            quest.setText(rs.getString(2));
            opt1.setText(rs.getString(3));
            opt2.setText(rs.getString(4));
            opt3.setText(rs.getString(5));
            ans.setText(rs.getString(6));
            search.setEditable(false);


        }else{

            error1.setVisible(true);
            new SlideInDown(error1).play();
        }
    }
    
    public void updateQuestion()throws Exception{
        String id=search.getText();
        String question = quest.getText();
        String option1 = opt1.getText();
        String option2 = opt2.getText();
        String option3 = opt3.getText();
        String answer = ans.getText();
        if(question.equals("") || option1.equals("") || option2.equals("") || option3.equals("") || answer.equals("")){
            error.setVisible(true);
            new SlideInDown(error).play();
        }
        else {
            ConnnectionUtil connection = new ConnnectionUtil();
            Connection con = connection.conDB();


            PreparedStatement ps = con.prepareStatement("UPDATE question SET question=?,option1=?,option2=?,option3=?,answer=? WHERE id=?");
            ps.setString(1, question);
            ps.setString(2, option1);
            ps.setString(3, option2);
            ps.setString(4, option3);
            ps.setString(5, answer);
            ps.setString(6, id);
            ps.executeUpdate();
            succ.setVisible(true);
            new Bounce(succ).play();
            clear();
            succ.setVisible(true);
            new Bounce(succ).play();
            clear();
        }

    }
    public void closeE()throws Exception{
        error.setVisible(false);
        error1.setVisible(false);

    }
    public void clear() throws Exception{
        search.setText("");
        quest.setVisible(false);
        opt1.setVisible(false);
        opt2.setVisible(false);
        opt3.setVisible(false);
        ans.setVisible(false);
        saveBtn.setVisible(false);
        clearBtn.setVisible(false);
        nb.setVisible(false);

        search.setEditable(true);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInDown(search).play();
        new SlideInDown(searchBtn).play();

    }


}
