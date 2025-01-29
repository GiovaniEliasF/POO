package com.doceria.controller;

import com.doceria.model.Type;
import com.doceria.repository.TypeDAO;
import com.doceria.repository.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TypesController {

    @FXML
    private TextField nameField;

    @FXML
    private TableView<Type> typeTable;

    @FXML
    private TableColumn<Type, Integer> idColumn;

    @FXML
    private TableColumn<Type, String> nameColumn;

    private ObservableList<Type> typeList;
    private TypeDAO typeDAO;

    public TypesController() {
        Connection connection = DBConnection.getConnection();
        typeDAO = new TypeDAO(connection);
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadTypes();
    }

    private void loadTypes() {
        try {
            typeList = FXCollections.observableArrayList(typeDAO.listAll());
            typeTable.setItems(typeList);
        } catch (SQLException e) {
            showError("Error Loading Data", "Could not load types from the database.");
        }
    }

    @FXML
    public void addType() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showError("Validation Error", "Name cannot be empty.");
            return;
        }

        try {
            Type newType = new Type(0, name);
            typeDAO.create(newType);
            typeList.add(newType);
            nameField.clear();
        } catch (SQLException e) {
            showError("Error Adding Type", "Could not add the new type to the database.");
        }
    }

    @FXML
    public void updateType() {
        Type selectedType = typeTable.getSelectionModel().getSelectedItem();
        if (selectedType == null) {
            showError("Selection Error", "No type selected.");
            return;
        }

        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showError("Validation Error", "Name cannot be empty.");
            return;
        }

        try {
            selectedType.setName(name);
            typeDAO.update(selectedType);
            typeTable.refresh();
        } catch (SQLException e) {
            showError("Error Updating Type", "Could not update the type in the database.");
        }
    }

    @FXML
    public void deleteType() {
        Type selectedType = typeTable.getSelectionModel().getSelectedItem();
        if (selectedType == null) {
            showError("Selection Error", "No type selected.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Are you sure?");
        confirmationAlert.setContentText("This action will delete the selected type.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                typeDAO.delete(selectedType.getId());
                typeList.remove(selectedType);
            } catch (SQLException e) {
                showError("Error Deleting Type", "Could not delete the type from the database.");
            }
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Navegação
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

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) typeTable.getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource(fxmlPath)));
    }
}
