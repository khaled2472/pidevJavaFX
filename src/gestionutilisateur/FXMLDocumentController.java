/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author khaled
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfaddresse;
    @FXML
    private TextField tfnum;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<Utilisateur> information;
    @FXML
    private TableColumn<Utilisateur, Integer> colid;
    @FXML
    private TableColumn<Utilisateur, String> colnom;
    @FXML
    private TableColumn<Utilisateur, String> colprenom;
    @FXML
    private TableColumn<Utilisateur, String> coladdresse;
    @FXML
    private TableColumn<Utilisateur, String> colnum;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnajouter){
            insertRecord();
        }else if (event.getSource() == btnmodifier){
            updateRecord();
        }else if(event.getSource() == btnsupprimer){
            deleteButton();
        }
            
    }
     @FXML
    void removeCustomer(ActionEvent event) {
        int selectedID =information.getSelectionModel().getSelectedIndex();
        information.getItems().remove(selectedID);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUtilisateur();
    }   
    public Connection getConnection(){
        Connection cnn;
        try{
            cnn= DriverManager.getConnection( "jdbc:mysql://localhost:3306/utilisateurs", "root", "");
            return cnn;  
        }
        catch (Exception ex) {
            System.out.println("Error" +  ex.getMessage());
            return null;
        }
    }
    public void showUtilisateur(){
        ObservableList<Utilisateur> list= getUtilisateurlist();
        colid.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        coladdresse.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("addresse"));
        colnum.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("numtel"));
        information.setItems(list);
    }
    private ObservableList<Utilisateur> getUtilisateurlist(){
        ObservableList<Utilisateur> utilisateurlist = FXCollections.observableArrayList();
        Connection cnn = getConnection();
        String query = "SELECT * FROM utilisateur";
        Statement st ;
        ResultSet rs;
        try{
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            Utilisateur utilisateur ;
            while(rs.next()){
                utilisateur = new Utilisateur( rs.getInt("id"), rs.getNString("nom"), rs.getNString("prenom"), rs.getString("addresse"), rs.getString("numtel"));
                utilisateurlist.add(utilisateur);
            }
        }catch(Exception ex){
        ex.printStackTrace();   
    }
return utilisateurlist;
}
    private void insertRecord(){
        String query = "INSERT INTO utilisateur VALUES ( " + tfid.getText() + ",'" + tfnom.getText() + "','" + tfprenom.getText() + "',"
                + tfaddresse.getText() + "," + tfnum.getText() + ")";
        executeQuery(query); 
        showUtilisateur();
    }
    private void updateRecord(){
        String query = "UPDATE  utilisateur SET nom  = '" + tfnom.getText() + "', prenom = '" + tfprenom.getText() + "', addresse = " +
                tfaddresse.getText() + ", numtel = " + tfnum.getText() + " WHERE id = " + tfid.getText() + "";
        executeQuery(query);
        showUtilisateur();
    }
    private void deleteButton(){
        String query = "DELETE FROM utilisateur WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showUtilisateur();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
   
    }
    