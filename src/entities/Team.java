package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Team extends Entity {
    private String team;
    private String teamLeader;
    private String company;

    public Team(List<Object> list) {
        super(list);
    }

    public String getTeam() {
        return team;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        team = list.get(0).equals("")? null : (String) list.get(0);
        teamLeader = list.get(1).equals("")? null : (String) list.get(1);
        company = list.get(2).equals("")? null : (String) list.get(2);

    }
}
