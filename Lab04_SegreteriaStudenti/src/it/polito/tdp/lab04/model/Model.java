package it.polito.tdp.lab04.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<Corso> restituisciCorsi(){
		
		CorsoDAO dao = new CorsoDAO();
		List <Corso> corsi =  dao.getTuttiICorsi();
		Collections.sort(corsi);
		return corsi;
	}
	
	public Studente restituisciStudente(int matricola) {
		StudenteDAO dao = new StudenteDAO();
		return dao.getStudentePerMatricola(matricola);
		
	}
	
	public List<Studente> restituisciStudentiPerCorso(Corso corso){
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}

	public List<Corso> restituisciCorsiPerStudente(int matricola){
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsiPerStudente(matricola);
	}
	
	public boolean studenteSegueCorso(Studente s, Corso c) {
		if(this.restituisciStudentiPerCorso(c).contains(s) && this.restituisciCorsiPerStudente(s.getMatricola()).contains(c))
			return true;
		else return false;
	}
	
	public boolean iscrivi(Studente s, Corso c) {
		CorsoDAO dao = new CorsoDAO();
		dao.inscriviStudenteACorso(s, c);
		return dao.inscriviStudenteACorso(s, c);
	}
}
