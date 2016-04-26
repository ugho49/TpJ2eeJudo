package utils;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class Flash {
	
	private final static String SESSION_FLASH = "flash";
	
	public final static String LEVEL_SUCCESS = "success";
	public final static String LEVEL_WARNING = "warning";
	public final static String LEVEL_DANGER = "danger";
	
	public static void setMessage(HttpSession session, final String level, final String message) {
		
		HashMap<String, ArrayList<String>> flash = new HashMap<>();
		
		if (session == null) {
			System.out.println("[FLASH] setMessage : session is null");
		} else {
			if (session.getAttribute(SESSION_FLASH) != null) {
				flash = (HashMap<String, ArrayList<String>>) session.getAttribute(SESSION_FLASH);
			}
			
			if (StringUtils.isNotEmpty(level) && StringUtils.isNotEmpty(message)) {
				if (flash.containsKey(level)) {
					flash.get(level).add(message);
				} else {
					ArrayList<String> m = new ArrayList<>();
					m.add(message);
					flash.put(level, m);
				}
				
				session.removeAttribute(SESSION_FLASH);
				session.setAttribute(SESSION_FLASH, flash);
			} else {
				System.out.println("[FLASH] setMessage : level or message is empty");
			}
		}
	}
	
	public static HashMap<String, ArrayList<String>> getMessages(HttpSession session) {		
		HashMap<String, ArrayList<String>> flash = new HashMap<>();
		
		if (session == null) {
			System.out.println("[FLASH] getMessages : session is null");
		} else {
			if (session.getAttribute(SESSION_FLASH) != null) {
				// get messages
				flash = (HashMap<String, ArrayList<String>>) session.getAttribute(SESSION_FLASH);
				// remove all messages
				session.removeAttribute(SESSION_FLASH);
			}
		}
		
		return flash;
	}
}
