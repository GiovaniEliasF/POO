package com.doceria.controller;

import com.doceria.repository.DBConnection;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Label loginMensageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e) {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            validateLogin();
        } else {
            loginMensageLabel.setText("    Preencha com Login e Senha!");
        }
    }

    public void validateLogin(){
        DBConnection connection = new DBConnection();
        Connection connectDB = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() +"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switchScene();
                } else {
                    loginMensageLabel.setText("Login incorreto, tente novamente!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchScene() {
        try {
            // Carrega o FXML da segunda tela
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/doceria/mainView.fxml"));
            Parent switchScene = fxmlLoader.load();

            // Obtém o Stage atual
            Stage stage = (Stage) usernameTextField.getScene().getWindow();

            // Define a nova cena
            stage.setScene(new Scene(switchScene));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}