package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Programming extends Entity{
    private String softwareEngineer;
    private String program;

    public Programming(List<Object> list) {
        super(list);
    }

    public String getSoftwareEngineer() {
        return softwareEngineer;
    }

    public String getProgram() {
        return program;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        softwareEngineer = list.get(0).equals("")? null : (String) list.get(0);
        program = list.get(1).equals("")? null : (String) list.get(1);
    }
}
