package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			//conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codins) {
		
		List<Corso> corsi = this.getTuttiICorsi();
		Corso ris=null;
		
		for(Corso c : corsi) {
			if(c.getCodins().equals(codins)) {
				ris = c;
			}
		}
		return ris;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT matricola FROM iscrizione WHERE codins LIKE ?";
		List <Studente> studenti = new LinkedList<Studente>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matr = rs.getInt("matricola");
				StudenteDAO dao = new StudenteDAO();
				Studente stud = dao.getStudentePerMatricola(matr);
				
				studenti.add(stud);
				
				}
			//conn.close();
			}catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		return studenti;
		
	}
	
	
	public List<Corso> getCorsiPerStudente(int matricola) {
		final String sql = "SELECT codins FROM iscrizione WHERE matricola LIKE ?";
		List <Corso> corsi = new LinkedList<Corso>();
		Corso c;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				c = this.getCorso(codins);
				
				corsi.add(c);
				
				}
			//conn.close();
			}catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		return corsi;
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		final String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?, ?)";
		StudenteDAO dao = new StudenteDAO();
		boolean riuscito=false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			if(dao.getStudentePerMatricola(studente.getMatricola()).equals(studente) &&
				this.getCorso(corso.getCodins()).equals(corso)) {
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			riuscito = st.execute(); //genera RuntimeException
			}conn.close();
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		// ritorna true se l'iscrizione e' avvenuta con successo
		
		return riuscito;
	}
}
