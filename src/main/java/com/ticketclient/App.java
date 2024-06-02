package com.ticketclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {
     final static int SERVER_PORT=8083;
    static InputStream inputStream;
    static OutputStream outputStream;
    private static Scene scene;
    Socket socketClient;
    PrimaryController primaryController;
    @Override
    public void start(Stage stage) throws IOException {
        socketClient= new Socket("127.0.0.1",SERVER_PORT);
        inputStream=socketClient.getInputStream();
        outputStream=socketClient.getOutputStream();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        primaryController = fxmlLoader.getController();
        primaryController.setApp(this);
        primaryController.setOutputStream(outputStream);
        primaryController.setInputStream(inputStream);
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    
}
    
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}