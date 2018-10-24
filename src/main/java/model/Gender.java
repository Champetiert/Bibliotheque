package model;

public enum Gender {
	M("M"), F("F");
	private String genre;

	private Gender(String nom) {
		this.genre = nom;
	}

	public String getNom() {
		return this.genre;
	}

	public void setNom(String nom) {
		this.genre = nom;
	}
	
	
}