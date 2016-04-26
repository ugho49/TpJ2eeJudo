package utils;

import bean.Competition;
import bean.Grade;
import bean.Information;
import bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

public class Service {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

	public static LinkedList<Grade> getGrades() {
		LinkedList<Grade> grades = new LinkedList<Grade>();
		grades.add(new Grade("b","Blanche" ));
		grades.add(new Grade("J","Jaune" ));
		grades.add(new Grade("O","Orange" ));
		grades.add(new Grade("V","Verte" ));
		grades.add(new Grade("B","Bleue" ));
		grades.add(new Grade("M","Marron" ));
		
		grades.add(new Grade("1","Noire 1er Dan" ));
		grades.add(new Grade("2","Noire 2eme Dan" ));
		grades.add(new Grade("3","Noire 3eme Dan" ));
		grades.add(new Grade("4","Noire 4eme Dan" ));
		grades.add(new Grade("5","Noire 5eme Dan" ));
		grades.add(new Grade("6","Noire 6eme Dan" ));
		
		return grades ;
	}
	
	public static LinkedList<Competition> getCompetitions() {
		int id = 1 ;
		LinkedList<Competition> list = new LinkedList<Competition>();
		list.add(new Competition(id++, new Date(), "Open ", "Nantes", "Palais des sports", "44" ));
		list.add(new Competition(id++, new Date(), "Shiai", "Rennes", "Dojo régional", "35" ));
		list.add(new Competition(id++, new Date(), "Shiai", "Nantes", "Dojo du croissant", "44" ));
		list.add(new Competition(id++, new Date(), "Tournoi", "Lyon", "Halle des sports", "69" ));
		return list ;
	}

	/**
	 * Liste des utilisateurs,
	 * normalement remplacé par une base de données
	 * @return
	 */
	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();

		try {
			users.add(new User("LEGARDINIER", "Ronan", dateFormat.parse("30/05/1995"), new Grade("1","Noire 1er Dan"), 70, "roro", "1234"));
			users.add(new User("STEPHAN", "Ugho", dateFormat.parse("15/11/1993"), new Grade("3","Noire 3eme Dan"), 72, "ugho", "1234"));
			users.add(new User("BOURREAU", "Valentin", dateFormat.parse("26/04/1995"), new Grade("b","Blanche"), 70, "valentin", "1234"));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}

		return users;
	}

    /**
     * Liste des informations,
     * normalement remplacé par une base de données
     * @return
     */
    public static ArrayList<Information> getInformations() {
        ArrayList<Information> informations = new ArrayList<>();
        int id = 1 ;

        try {
            informations.add(new Information(id++, dateFormat.parse("01/04/2016"), "De nouvelles compétitions sont à prévoir prochainement"));
            informations.add(new Information(id++, dateFormat.parse("01/01/2015"), "Bienvenue sur le nouveau site"));
            informations.add(new Information(id++, dateFormat.parse("25/12/2015"), "Joyeux noël à tout les judokas"));
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        // tri par ordre du plus récent au plus ancien
        Collections.sort(informations, Collections.reverseOrder());

        return informations;
    }
}
