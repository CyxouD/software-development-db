package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Req extends Entity {
    private Integer id;
    private String requirement;

    public Req(List<Object> list) {
        super(list);
    }

    public Integer getId() {
        return id;
    }

    public String getRequirement() {
        return requirement;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        id = list.get(0).equals("")? null : Integer.parseInt((String) list.get(0));
        requirement = list.get(1).equals("")? null : (String) list.get(1);
    }
}
