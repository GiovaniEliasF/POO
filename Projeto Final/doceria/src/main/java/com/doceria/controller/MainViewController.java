package com.doceria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    /**
     * Troca a cena atual pela nova cena especificada pelo caminho do arquivo FXML.
     *
     * @param event Evento do botão pressionado.
     * @param fxmlPath Caminho do arquivo FXML.
     * @throws IOException Caso o arquivo FXML não seja encontrado.
     */
    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        // Carrega o novo FXML
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

        // Obtém o Stage atual a partir do evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Define a nova cena no Stage
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void home(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/mainView.fxml");
    }

    public void typesView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/typesView.fxml");
    }

    public void ingredientsView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/ingredientsView.fxml");
    }

    public void productsView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/productsView.fxml");
    }

    public void loginView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/loginView.fxml");
    }
}
