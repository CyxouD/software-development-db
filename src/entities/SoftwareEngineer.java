package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class SoftwareEngineer extends Entity {
    private String initials;
    private String team;
    private String rank;

    public SoftwareEngineer(List<Object> list) {
        super(list);
    }

    public String getInitials() {
        return initials;
    }

    public String getTeam() {
        return team;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        initials = list.get(0).equals("")? null : (String) list.get(0);
        team = list.get(1).equals("")? null : (String) list.get(1);
        rank = list.get(2).equals("")? null : (String) list.get(2);
    }
}
