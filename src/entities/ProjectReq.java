package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProjectReq extends Entity {
    private String project;
    private Integer requirement;
    private Boolean inTime;

    public ProjectReq(List<Object> list) {
        super(list);
    }

    public String getProject() {
        return project;
    }

    public Integer getRequirement() {
        return requirement;
    }

    public Boolean isInTime() {
        return inTime;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        project = list.get(0).equals("")? null : (String) list.get(0);
        requirement = list.get(1).equals("")? null : Integer.parseInt((String) list.get(1));
        inTime = list.get(2).equals("")? null : Boolean.parseBoolean((String) list.get(2));
    }
}
