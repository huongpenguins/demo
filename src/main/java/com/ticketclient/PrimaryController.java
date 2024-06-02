package com.ticketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

public class PrimaryController {
    @FXML 
    GridPane gridPane;
    String i=null;
    String idTicket=null;
    private  InputStream inputStream;
    private  OutputStream outputStream;
    public App app;
    int[] seatStatus=new int[24];
    public void updateSeatStatus(int[] seatStatus) {
        int row = 0;
        int col = 0;
        for (Node node : gridPane.getChildren()) {
            
                Button button = (Button) node;
                int seatIndex = row * 6 + col ; 
                if (seatStatus[seatIndex]==0) {
                    button.setDisable(true);
                } else {
                    button.setDisable(false);
                }
                col++;
                if (col == 6) {
                    col = 0;
                    row++;
                }
            
        }
    }
    public void choose(ActionEvent event){
        Button button = (Button)event.getSource();
        i=button.getText();
        
    }
    public void buy(ActionEvent event) throws IOException{
        for(int i =0;i<24;i++){
            seatStatus[i]=inputStream.read();
           updateSeatStatus(seatStatus);
        }
        if(i==null){
            outputStream.write(-1);
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("chua chon cho");
        alert.showAndWait();
        }
        else{
             try {
                outputStream.write(Integer.parseInt(i));
                outputStream.flush();
                int t= inputStream.read();
                if(t==0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Het ve nay");
                    alert.showAndWait();
                }
                if(t==1){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Mua ve thanh cong");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public App getApp() {
        return this.app;
    }

    public void setApp(App app) {
        this.app = app;
    }






}
