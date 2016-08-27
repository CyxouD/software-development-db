package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Os extends Entity {
    private Date releaseDate;
    private String name;
    private String purpose;
    private String capacity;
    private String interfaceType;
    private String taskType;



    public Os(List<Object> list) {
        super(list);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        releaseDate = list.get(0).equals("")? null : stringToReleaseDate((String) list.get(0));
        name = list.get(1).equals("")? null : (String) list.get(1);
        purpose = list.get(2).equals("")? null : (String) list.get(2);
        capacity = list.get(3).equals("")? null : (String) list.get(3);
        interfaceType = list.get(4).equals("")? null : (String) list.get(4);
        taskType = list.get(5).equals("")? null : (String) list.get(5);
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
