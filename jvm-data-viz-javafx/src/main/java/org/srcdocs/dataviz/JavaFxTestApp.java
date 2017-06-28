package org.srcdocs.dataviz;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.srcdocs.dataviz.reactjs.ReactJsAppHtmlGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author mykola
 */
public class JavaFxTestApp extends Application {
    public static void main(String[] args) throws IOException {
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

        root.getChildren().add(webView);

        ReactJsAppHtmlGenerator appHtmlGenerator = new ReactJsAppHtmlGenerator();
        String html = appHtmlGenerator.generateHtml();
        Files.write(Paths.get("viz.html"), html.getBytes());
        webEngine.loadContent(html);

        Button debugButton = new Button("debug");

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(debugButton);
        borderPane.setCenter(webView);

        debugButton.setOnAction(event -> {
            webEngine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");
            webEngine.executeScript(appHtmlGenerator.getBundleJavaScript());
        });

        root.getChildren().add(borderPane);

        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
