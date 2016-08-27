package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Project extends Entity {
    private String name;
    private String team;
    private String client;
    private String methodology;
    private Double budget;
    private Boolean finished;

    public Project(List<Object> list) {
        super(list);
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getClient() {
        return client;
    }

    public String getMethodology() {
        return methodology;
    }

    public Double getBudget() {
        return budget;
    }

    public Boolean isFinished() {
        return finished;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        name = list.get(0).equals("")? null : (String) list.get(0);
        team = list.get(1).equals("")? null : (String) list.get(1);
        client = list.get(2).equals("")? null : (String) list.get(2);
        methodology = list.get(3).equals("")? null : (String) list.get(3);
        budget = list.get(4).equals("")? null : Double.parseDouble((String) list.get(4));
        finished = list.get(5).equals("")? null : Boolean.parseBoolean((String) list.get(5));
    }
}
