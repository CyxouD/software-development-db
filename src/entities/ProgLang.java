package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgLang extends Entity {
    private String name;
    private String purpose;
    private String version;
    private String the_most_popular_OS;

    public ProgLang(List<Object> list) {
        super(list);
    }

    public String getThe_most_popular_OS() {
        return the_most_popular_OS;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        name = list.get(0).equals("")? null : (String) list.get(0);
        purpose = list.get(1).equals("")? null : (String) list.get(1);
        version = list.get(2).equals("")? null : (String) list.get(2);
        the_most_popular_OS = list.get(3).equals("")? null : (String) list.get(3);
    }
}
