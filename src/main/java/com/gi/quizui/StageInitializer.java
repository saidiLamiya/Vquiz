package com.gi.quizui;

import com.gi.quizui.QuizApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Value("classpath:fxml/MainWindow.fxml")
    private Resource quizResource;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    private String applicationTitle;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        ConfigurableApplicationContext springContext = event.getAppContext();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(quizResource.getURL());
            fxmlLoader.setControllerFactory(springContext::getBean);
            Parent parent = fxmlLoader.load();
            Image image = new Image("/fxml/icons8-fivem-120 (1).png");
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent, 730, 550));
            stage.setTitle(applicationTitle);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}