/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutilisateur;

import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import gestionutilisateur.FXMLDocumentController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class LOGINController implements Initializable {

    @FXML
    private TextField fxid;

    @FXML
    private TextField fxnom;

    @FXML
    private TextField fxprenom;

    @FXML
    private TextField fxaddresse;

    @FXML
    private TextField fxmdp;

    @FXML
    private Button btninscription;

    @FXML
    private Button btnconnecter;

    @FXML
    private TextField logaddresse;

    @FXML
    private TextField logmdp;
    @FXML
    private Parent fxml;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        if (event.getSource() == btninscription) {
            insertRecordd();
            JOptionPane.showMessageDialog(null, "utilisateur ajouter avec success...");
        }

    }

    @FXML
    private void Login(ActionEvent event) throws SQLException {
       
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utilisateurs", "root", "");
        //conn = mysqlconnect.ConnectDb();
        String sql = "Select * from utilisateur where addresse = ? and numtel = ? ";

        try {
            System.out.println("aaaa");
            pst = conn.prepareStatement(sql);
            pst.setString(1, logaddresse.getText());
            pst.setString(2, logmdp.getText());
            rs = pst.executeQuery();
            System.out.println("eeeeeee");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "addresse and password is correct");
                int id = rs.getInt("id");
                if (id < 100) {
                    
                    Parent page1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    System.out.println(page1);
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Parent page1 = FXMLLoader.load(getClass().getResource("interface.fxml"));
                    System.out.println(page1);
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
              
                }

            } else {
                JOptionPane.showMessageDialog(null, " invalid addresse or password ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Connection getConnection() {
        Connection cnn;
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utilisateurs", "root", "");
            return cnn;
        } catch (Exception ex) {
            System.out.println("Error khaled" + ex.getMessage());
            return null;
        }
    }

    private ObservableList<Utilisateur> getUtilisateurlist() {
        ObservableList<Utilisateur> utilisateurlist = FXCollections.observableArrayList();
        Connection cnn = getConnection();
        String query = "SELECT * FROM utilisateur";
        Statement st;
        ResultSet rs;
        try {
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            Utilisateur utilisateur;
            while (rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("id"), rs.getNString("nom"), rs.getNString("prenom"), rs.getNString("addresse"), rs.getNString("numtel"));
                utilisateurlist.add(utilisateur);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return utilisateurlist;
    }

    private void insertRecordd() throws SQLException {
        // tchouf requeste mel base est ce que fxid.getText() maoujouda walla
        // ken maoujouda => affichi already exist => tkharrej alert 
        // mahish maoujpouda taamel l ajout yaani String query 
        //" select id from utilsateur where id= "
        String query = "INSERT INTO utilisateur VALUES ( " + fxid.getText() + ",'" + fxnom.getText() + "','" + fxprenom.getText() + "',"
                + fxaddresse.getText() + "," + fxmdp.getText() + ")";
        executeQuery(query);
        

    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
