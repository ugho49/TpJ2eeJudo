package bean;

import java.util.Date;

public class User {
	
	private String nom;
	private String prenom;
	private Date birthday;
	private Grade grade;
	private int poids;
	private String login;
	private String password;
	
	public User() {
		// Empty constructor
	}
	
	public User(String nom, String prenom, Date birthday, Grade grade, int poids, String login, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.birthday = birthday;
		this.grade = grade;
		this.poids = poids;
		this.login = login;
		this.password = password;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}
}
