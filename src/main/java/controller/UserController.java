package controller;

import bean.Grade;
import bean.User;
import org.apache.commons.lang3.StringUtils;
import utils.Service;
import utils.Flash;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController {
	
	private final static String SESSION_USER = "user";
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	/**
	 * Enregistre l'utilisateur en session si les paramètres matchent
	 * @param session
	 * @param request
	 */
	public void login(HttpSession session, HttpServletRequest request) {
		if (!isLogged(session)) {
			
			final String login = request.getParameter("login");
			final String password = request.getParameter("password");
			
			if (StringUtils.isNotEmpty(login) && StringUtils.isNotEmpty(password)) {
				User loggedUser = null;
				
				for (final User u : Service.getUsers()) {
					if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
						loggedUser = u;
						break;
					}
				}
				
				if (loggedUser != null) {
					session.setAttribute(SESSION_USER, loggedUser);
					Flash.setMessage(session, Flash.LEVEL_SUCCESS, "Utilisateur connecté avec succès");
				} else {
					Flash.setMessage(session, Flash.LEVEL_DANGER, "Erreur Login ou Password");
				}
			}
		}
	}
	
	/**
	 * Déconnecte l'utilisateur si connecté
	 * @param session
	 */
	public void logout(HttpSession session) {
		if (isLogged(session)) {
			session.removeAttribute(SESSION_USER);
			Flash.setMessage(session, Flash.LEVEL_SUCCESS, "Vous êtes déconnecté !");
		} else {
			Flash.setMessage(session, Flash.LEVEL_WARNING, "Vous n'êtes pas connecté !");
		}
	}
	
	/**
	 * Récupérer l'utilisateur courant en session
	 * @param session
	 * @return
	 */
	public User getCurrentUser(HttpSession session) {
		if (isLogged(session)) {
			return (User) session.getAttribute(SESSION_USER);
		}
		
		return null;
	}
	
	/**
	 * Retourne vrai si l'utilisateur est connecté en session, faux sinon
	 * @param session
	 * @return
	 */
	public boolean isLogged(HttpSession session) {
		return session.getAttribute(SESSION_USER) != null;
	}
	
	/**
	 * Méthode qui met à jour l'utilisateur en session
	 * @param session
	 * @param request
	 * @return
	 */
	public void updateUser(HttpSession session, HttpServletRequest request) {
		if (isLogged(session)) {
			final String nom = request.getParameter("nom");
			final String prenom = request.getParameter("prenom");
			final String str_birthday = request.getParameter("dateOfBirth");
			final String str_grade = request.getParameter("grade");
			final String str_poids = request.getParameter("poids");
			final String login = request.getParameter("login");
			final String password = request.getParameter("password");
            final String password_confirm = request.getParameter("password-confirm");

            if (StringUtils.isEmpty(nom) || StringUtils.isEmpty(prenom) || StringUtils.isEmpty(str_birthday) || StringUtils.isEmpty(str_grade) || StringUtils.isEmpty(str_poids) || StringUtils.isEmpty(login)) {
                Flash.setMessage(session, Flash.LEVEL_WARNING, "Des champs sont vides");
            } else {

                // Vérification sur le password
                if (StringUtils.isNotEmpty(password)) {
                    if (!password.equals(password_confirm)) {
                        Flash.setMessage(session, Flash.LEVEL_WARNING, "Le password n'est pas modifié car la confirmation est incorrect");
                    } else {
                        // Récupération de l'utilisateur en session
                        User currentUser = getCurrentUser(session);
                        // Mise à jour du password
                        currentUser.setPassword(password);
                        // Mise à jour de l'utilisateur en session
                        updateUserInSession(session, currentUser);
                        // Affichage à l'utilisateur
                        Flash.setMessage(session, Flash.LEVEL_SUCCESS, "Password modifié avec succès");
                    }
                }

                // Autres vérifications
                Date birthday = new Date();
                int poids;
                Grade grade = new Grade();
                boolean error = false;

                // Vérification date
                try {
                    birthday = dateFormat.parse(str_birthday);
                } catch (ParseException e) {
                    error = true;
                    Flash.setMessage(session, Flash.LEVEL_WARNING, "La date est incorrect");
                }

                // Vérifications poids
                try {
                    poids = Integer.valueOf(str_poids);

                    if (poids < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    error = true;
                    Flash.setMessage(session, Flash.LEVEL_WARNING, "Le poids est incorrect");
                }

                // Vérification grade
                boolean gradeFound = false;

                for (Grade g : Service.getGrades()) {
                    if (str_grade.equals(g.getCode())) {
                        gradeFound = true;
                        grade = g;
                        break;
                    }
                }

                // si aucun grade trouvé, il y a une erreur
                if (!gradeFound) {
                    error = true;
                }

                // Si aucune erreur, on met à jour les données de l'utilisateur
                if (!error) {
                    // Récupération de l'utilisateur en session
                    User currentUser = getCurrentUser(session);
                    // Mise à jour des information
                    currentUser.setNom(nom);
                    currentUser.setPrenom(prenom);
                    currentUser.setBirthday(birthday);
                    currentUser.setGrade(grade);
                    currentUser.setLogin(login);
                    // Mise à jour de l'utilisateur en session
                    updateUserInSession(session, currentUser);
                    // Affichage à l'utilisateur
                    Flash.setMessage(session, Flash.LEVEL_SUCCESS, "Les informations sont modifiées avec succès");
                }
            }
		}
	}

    /**
     * Méthode qui remplace l'utilisateur en session par celui passé en paramètre
     * @param session
     * @param user
     */
    private void updateUserInSession(HttpSession session, final User user) {
        // remove user in session
        session.removeAttribute(SESSION_USER);
        // set new user in session
        session.setAttribute(SESSION_USER, user);
    }
}
