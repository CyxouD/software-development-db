package entities;

import java.util.List;

/**
 * Created by Dima on 22.04.2016.
 */
public class Program extends Entity{
    private String name;
    private String project;
    private String purpose;

    public Program(List<Object> list) {
        super(list);
    }

    public String getName() {
        return name;
    }

    public String getProject() {
        return project;
    }

    public String getPurpose() {
        return purpose;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        name = list.get(0).equals("")? null : (String) list.get(0);
        project = list.get(1).equals("")? null : (String) list.get(1);
        purpose = list.get(2).equals("")? null : (String) list.get(2);
    }
}
