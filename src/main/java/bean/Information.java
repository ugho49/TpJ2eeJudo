package bean;

import java.util.Date;

/**
 * Created by ughostephan on 02/04/2016.
 */
public class Information implements Comparable<Information> {
    private int code;
    private Date date;
    private String message;

    public Information() {
    }

    public Information(int code, Date date, String message) {
        this.code = code;
        this.date = date;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int compareTo(Information i) {
        return getDate().compareTo(i.getDate());
    }
}
