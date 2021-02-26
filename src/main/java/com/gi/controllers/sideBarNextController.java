package com.gi.controllers;

import animatefx.animation.SlideInRight;
import com.gi.FxmlLoader;
import com.gi.connection.ConnnectionUtil;
import com.gi.tableModel;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

@Component
public class sideBarNextController implements Initializable {
    @FXML
    private TableView<tableModel> tab;
    @FXML
    private TableColumn<tableModel,String> col_id,col_quest,col_opt1,col_opt2,col_opt3,col_ans;

    ObservableList<tableModel> oblist = FXCollections.observableArrayList();


    @FXML
    BorderPane bp;
    @FXML
    AnchorPane succ;

    @FXML
    JFXButton Abtn,Nbtn,Ubtn,Dbtn ;
    @FXML
    Button homeBtn;

    public void home()throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        Stage window = (Stage)homeBtn.getScene().getWindow();
        window.setScene(new Scene(root,730,550));


    }

    public void back()throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SideBarNext.fxml"));
        Stage window = (Stage)Abtn.getScene().getWindow();
        window.setScene(new Scene(root, 900, 550));

    }

    public void newQ()throws Exception {
        Nbtn.setOpacity(1);
        Ubtn.setOpacity(0.6);
        Abtn.setOpacity(0.6);
        Dbtn.setOpacity(0.6);
        Nbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #F97B31;-fx-border-width:0 0 0 5");
        Ubtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Dbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Abtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Question");
        bp.setCenter(view);

    }

    public void updateQ()throws Exception {
        Ubtn.setStyle("-fx-background-color:transparent;-fx-border-color: #F97B31;-fx-border-width:0 0 0 5");
        Nbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Abtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Dbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");

        Ubtn.setOpacity(1);
        Nbtn.setOpacity(0.6);
        Abtn.setOpacity(0.6);
        Dbtn.setOpacity(0.6);
        FxmlLoader object = new FxmlLoader();
        Pane view1 = object.getPage("updateQuestion");
        bp.setCenter(view1);


    }

    public void deleteQ()throws Exception {
        Ubtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Dbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #F97B31;-fx-border-width:0 0 0 5");
        Nbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Abtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Abtn.setOpacity(0.6);
        Nbtn.setOpacity(0.6);
        Ubtn.setOpacity(0.6);
        Dbtn.setOpacity(1);
        FxmlLoader object = new FxmlLoader();
        Pane view3 = object.getPage("deleteQuestion");
        bp.setCenter(view3);


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Ubtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Dbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Nbtn.setStyle("-fx-background-color:transparent;-fx-border-color: #000;-fx-border-width:0 0 0 5");
        Abtn.setStyle("-fx-background-color:transparent;-fx-border-color: #F97B31;-fx-border-width:0 0 0 5");
        Abtn.setOpacity(1);
        Nbtn.setOpacity(0.6);
        Ubtn.setOpacity(0.6);
        Dbtn.setOpacity(0.6);
        new SlideInRight(tab).play();
        try{
            ConnnectionUtil connection = new ConnnectionUtil();
            Connection con = connection.conDB();
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM question");

            while(rs.next()){
                oblist.add(new tableModel(rs.getString("id"),rs.getString("question"),rs.getString("option1"),rs.getString("option2"),rs.getString("option3"),rs.getString("answer")));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_quest.setCellValueFactory(new PropertyValueFactory<>("question"));
        col_opt1.setCellValueFactory(new PropertyValueFactory<>("option1"));
        col_opt2.setCellValueFactory(new PropertyValueFactory<>("option2"));
        col_opt3.setCellValueFactory(new PropertyValueFactory<>("option3"));
        col_ans.setCellValueFactory(new PropertyValueFactory<>("answer"));
        tab.setItems(oblist);

    }


}
