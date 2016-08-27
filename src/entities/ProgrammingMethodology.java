package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgrammingMethodology extends Entity {
    private String name;
    private String numberOfMembers;
    private String diffrences;

    public ProgrammingMethodology(List<Object> list) {
        super(list);
    }

    public String getName() {
        return name;
    }

    public String getNumberOfMembers() {
        return numberOfMembers;
    }

    public String getDiffrences() {
        return diffrences;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        name = list.get(0).equals("")? null : (String) list.get(0);
        numberOfMembers = list.get(1).equals("")? null : (String) list.get(1);
        diffrences = list.get(2).equals("")? null : (String) list.get(2);
    }
}
