package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Implementation extends Entity {
    private String programName;
    private String programLanguage;

    public Implementation(List<Object> list) {
        super(list);
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramLanguage() {
        return programLanguage;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        programName = list.get(0).equals("")? null : (String) list.get(1);
        programLanguage = list.get(1).equals("")? null : (String) list.get(2);
    }
}
