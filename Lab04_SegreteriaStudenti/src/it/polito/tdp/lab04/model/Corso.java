package it.polito.tdp.lab04.model;

public class Corso implements Comparable <Corso>{

	private String codins;
	private int crediti;
	private String nome;
	private int periodoDidattico;
	

	public Corso(String codins, int crediti, String nome, int periodoDidattico) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}
	
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public int getCrediti() {
		return crediti;
	}
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPeriodoDidattico() {
		return periodoDidattico;
	}
	public void setPeriodoDidattico(int periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String toString2() {
		return codins+" "+crediti+" "+nome+" "+periodoDidattico;
	}
	
	@Override
	public int compareTo(Corso altro) {
		
		return this.nome.compareTo(altro.nome);
	}
	
	
	
	
	
}
