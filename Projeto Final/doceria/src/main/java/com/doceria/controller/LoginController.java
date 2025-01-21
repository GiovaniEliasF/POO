package com.doceria.controller;

import com.doceria.model.DBConnection;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
                    loginMensageLabel.setText("Bem vindo!");
                } else {
                    loginMensageLabel.setText("Login incorreto, tente novamente!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}