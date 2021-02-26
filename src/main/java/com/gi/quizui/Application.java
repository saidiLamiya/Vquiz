package com.gi.quizui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.gi.quizui","com.gi.controllers"})
public class Application {

	public static void main(String[] args) {

		javafx.application.Application.launch(QuizApplication.class, args);
	}
}
