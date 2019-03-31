package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudentePerMatricola(int matricola) {
		
		final String sql = "SELECT * FROM STUDENTE WHERE matricola LIKE ?";
		String nome;
		String cognome;
		String corsoDiStudi;
		Studente s;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			
			if(!rs.next()) {
				//conn.close();
				return null;
			} else {
				
				nome = rs.getString("nome");
				cognome = rs.getString("cognome");
				corsoDiStudi = rs.getString("CDS");
				
				s = new Studente(matricola, cognome, nome, corsoDiStudi);
				//conn.close();
			}
			
			//conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		return s;
		
	}
}
