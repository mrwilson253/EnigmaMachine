/*
Jordan Wilson
wilsonjordan61@gmail.com
Enigma Machine
Controller.java
 */

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    //you will have list of your variables from the form
    //TextFields and RadioButtons

    Enigma enigma = new Enigma();

    //variables I can use
    private int key = 1;
    private String message, codedMessage;

    @FXML
    private MenuBar menu;

    @FXML
    private MenuItem menuOpen;

    @FXML
    private MenuItem menuSave;

    @FXML
    private MenuItem menuAbout;

    @FXML
    private RadioButton rbtnGenKey;

    @FXML
    private ToggleGroup rbtnGrp1;

    @FXML
    private RadioButton rbtnCustKey;

    @FXML
    private Button btnEncode;

    @FXML
    private Button btnDecode;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtCustom;

    @FXML
    private TextField txtCoded;

    @FXML
    private TextField txtKey;

    @FXML
    private TextField txtMessage;

    public void clear() {
        txtCustom.setText(null);
        txtCoded.setText(null);
        txtKey.setText(null);
        txtMessage.setText(null);
        rbtnGenKey.setSelected(true);
    }

    @FXML
    void onActionClear(ActionEvent event) {
        //clear all of the textFields and reset the radio button to their initial value
        clear();
    }

    @FXML
    void onActionDecode(ActionEvent event) {
        //will have codedMessage and key, set these into enigma.
        enigma.setCodedMessage(codedMessage,key);
        //get the message and display
        txtMessage.setText(enigma.getMessage());
    }

    @FXML
    void onActionEncode(ActionEvent event) {
        //Get the message from the form and assign into message
        try {
            message = txtMessage.getText();
            //check if radio button for enterKey isSelected()
            //if yes, then get the key and parse it and then you can
            if (rbtnCustKey.isSelected()) {
                key = Integer.parseInt(txtCustom.getText());
                //call enigma.setMessage(message, key);
                enigma.setMessage(message, key);
            } else {
                //otherwise, just call enigma.setMessage(message);
                enigma.setMessage(message);
            }
            //now get the coded message
            codedMessage = enigma.getCodedMessage();
            //set codedMessage and key into the display
            txtCoded.setText(enigma.getCodedMessage());
            String sKey = Integer.toString(enigma.getKey());
            txtKey.setText(sKey);
        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(null, "Error!\n\"Custom Key\" must have an integer value if selected.");
            clear();
        }
    }

    @FXML
    void onActionMenuAbout(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "A modern and simplified twist based on the Enigma Machine of WWII.");
    }

    @FXML
    void onActionMenuOpen(ActionEvent event) {
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);

        if(file != null)
        {
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
                codedMessage = inputFile.nextLine();
                key = inputFile.nextInt();
                inputFile.close();
                txtCoded.setText(codedMessage);
                String sKey = Integer.toString(key);
                txtKey.setText(sKey);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    void onActionMenuSave(ActionEvent event) {
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Save File Dialog
        File file = fileChooser.showSaveDialog(null);

        if(file != null)
        {
            PrintWriter outputFile = null;
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(filename);
                outputFile.println(enigma.getCodedMessage());
                outputFile.println(enigma.getKey());

                outputFile.close();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clear();
    }
}
