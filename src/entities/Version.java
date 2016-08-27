package entities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Version extends Entity {
    private Date releaseDate;
    private String number;
    private String program;

    public Version(List<Object> list) {
        super(list);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getNumber() {
        return number;
    }

    public String getProgram() {
        return program;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        releaseDate = list.get(0).equals("")? null : stringToReleaseDate((String) list.get(0));
        number = list.get(1).equals("")? null : (String) list.get(1);
        program = list.get(2).equals("")? null : (String) list.get(2);
    }

    private java.sql.Date stringToReleaseDate(String dateString){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date releaseDate = new java.sql.Date(parsed.getTime());

        return releaseDate;
    }
}
