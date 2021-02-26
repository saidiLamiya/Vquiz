package com.gi.controllers;

import animatefx.animation.Bounce;
import animatefx.animation.BounceInDown;
import animatefx.animation.SlideInDown;
import com.gi.connection.ConnnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

@Component
public class deleteQuestionController implements Initializable {
    @FXML
    JFXTextArea quest,opt1,opt2,opt3,ans;
    @FXML
    Button searchBtn;

    @FXML
    JFXTextField search;
    @FXML
    JFXButton clearBtn,saveBtn,canBtn;
    @FXML
    AnchorPane error,succ,ap2;
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
            error.setVisible(true);
            new SlideInDown(error).play();

        }
    }
    public void deleteQuestion()throws Exception{
        ap2.setVisible(true);
        new Bounce(ap2).play();
        clear();
    }
    public void contin()throws Exception{
        ap2.setVisible(false);
        String id=search.getText();


        ConnnectionUtil connection = new ConnnectionUtil();
        Connection con = connection.conDB();


        PreparedStatement ps= con.prepareStatement("DELETE FROM question WHERE id=?");
        ps.setString(1,id);
        ps.executeUpdate();
        search.setText("");
        succ.setVisible(true);
        new Bounce(succ).play();


    }
    public void cancel()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SideBarNext.fxml"));
        Stage window = (Stage)canBtn.getScene().getWindow();
        window.setScene(new Scene(root, 900, 550));
    }
    public void closeE()throws Exception{
        error.setVisible(false);

    }
    public void clear() throws Exception{

        quest.setVisible(false);
        opt1.setVisible(false);
        opt2.setVisible(false);
        opt3.setVisible(false);
        ans.setVisible(false);
        saveBtn.setVisible(false);
        clearBtn.setVisible(false);


        search.setEditable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInDown(search).play();
        new SlideInDown(searchBtn).play();

    }
}
