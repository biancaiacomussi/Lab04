package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	Model model = new Model();
	private List <Corso> corsi;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnRestituisciStudente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    public void setModel(Model model) {
    	this.model = model;
    	this.comboBox.getItems().add(new Corso("",0,"",0));
    	this.comboBox.getItems().addAll(model.restituisciCorsi());
    	
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	     	
    	int matr;
    	
    	if(txtMatricola.getText().equals("")) {
    		txtResult.appendText("Inserisci matricola\n");
    	} else {
    		String matricola = txtMatricola.getText();
    	try {
    		matr = Integer.parseInt(matricola);
    		List <Corso> corsi = model.restituisciCorsiPerStudente(matr);
    		if(model.restituisciStudente(matr)==null)
    			txtResult.appendText("Studente non presente\n");
    		if(comboBox.getValue()==null || comboBox.getValue().getCodins().equals("")) {
    		for(Corso c : corsi) {
        		
        		txtResult.appendText(c.toString2()+"\n");
        	}
    		}else if(model.studenteSegueCorso(model.restituisciStudente(matr), comboBox.getValue()))
        			txtResult.appendText("Studente già iscritto a questo corso\n");
    		else if(model.restituisciStudente(matr)==null)
    			txtResult.appendText("Studente non presente\n");
    		else if(model.restituisciCorsiPerStudente(matr).isEmpty())
    			txtResult.appendText("Studente non iscritto a nessun corso\n");
    		else
    			txtResult.appendText("Studente non iscritto a questo corso\n");
    		
    		
    		
    	}catch(NumberFormatException e) {
    		System.out.println("Caratteri non permessi nel campo matricola");
    		txtResult.appendText("Caratteri non permessi nel campo matricola\n");
    	}
    	       	
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    	txtResult.clear();
   	
    	if(comboBox.getValue()==null || comboBox.getValue().getCodins().equals("")) {
    		txtResult.setText("Seleziona un corso\n");
    		
    	}else {
    		
    		Corso c = comboBox.getValue();
    		List <Studente> studenti = model.restituisciStudentiPerCorso(c);
    		for(Studente s : studenti) {
    		
    		txtResult.appendText(s.toString()+"\n");
    	}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.clear();
int matr;
    	
    	if(txtMatricola.getText().equals("")) {
    		txtResult.appendText("Inserisci matricola\n");
    	} else {
    		String matricola = txtMatricola.getText();
    	try {
    		matr = Integer.parseInt(matricola);
    		
    		if(model.restituisciStudente(matr)==null)
    			txtResult.appendText("Studente non presente\n");
    		if(comboBox.getValue()!=null || comboBox.getValue()!=null) {
    			model.iscrivi(model.restituisciStudente(matr), comboBox.getValue());
    				
    				txtResult.appendText("Studente iscritto al corso!");
    				
    			
    		}else if(model.studenteSegueCorso(model.restituisciStudente(matr), comboBox.getValue()))
        			txtResult.appendText("Studente già iscritto a questo corso\n");
    		else if(model.restituisciStudente(matr)==null)
    			txtResult.appendText("Studente non presente\n");
    		else if(model.restituisciCorsiPerStudente(matr).isEmpty())
    			txtResult.appendText("Studente non iscritto a nessun corso\n");
    		
    		
    		
    	}catch(NumberFormatException e) {
    		System.out.println("Caratteri non permessi nel campo matricola");
    		txtResult.appendText("Caratteri non permessi nel campo matricola\n");
    	}
    	       	
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtCognome.clear();
    	txtNome.clear();
    	comboBox.setValue(null);

    }

    @FXML
    void giveStudent(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	String matricola = txtMatricola.getText();
    	int matr;
    	try {
    		matr = Integer.parseInt(matricola);
    		Studente s = model.restituisciStudente(matr);
    		if(s!=null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    		} else {
    			txtResult.appendText("Numero matricola non presente nel database\n");
    		}
    		
    	}catch(NumberFormatException e) {
    		System.out.println("Inserire numero matricola valido");
    		txtResult.appendText("Inserire numero matricola valido\n");
    	}
    	
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnRestituisciStudente != null : "fx:id=\"btnRestituisciStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
}
