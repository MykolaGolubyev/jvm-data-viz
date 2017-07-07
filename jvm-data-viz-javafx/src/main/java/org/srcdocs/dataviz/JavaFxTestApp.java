package org.srcdocs.dataviz;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.srcdocs.dataviz.reactjs.ReactJsAppHtmlGenerator;

import java.io.IOException;

/**
 * @author mykola
 */
public class JavaFxTestApp extends Application {
    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.opengl", "false");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double initialWidth = primaryScreenBounds.getWidth();
        double initialHeight = primaryScreenBounds.getHeight();
        Scene scene = new Scene(root, initialWidth, initialHeight);

        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();

        webView.setPrefWidth(initialWidth);
        webView.setPrefHeight(initialHeight);

        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) ->
                webView.setPrefWidth(newSceneWidth.doubleValue()));

        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) ->
                webView.setPrefHeight(newSceneHeight.doubleValue()));

        ReactJsAppHtmlGenerator appHtmlGenerator = new ReactJsAppHtmlGenerator();
        String html = appHtmlGenerator.generateHtml();

        Button debugButton = new Button("debug");
        debugButton.setOnAction(event -> {
            webEngine.executeScript("testDirection()");
        });

        root.getChildren().add(webView);
        root.getChildren().add(debugButton);

        webEngine.loadContent(html);

        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
