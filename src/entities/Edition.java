package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Edition extends Entity{
    private String name;
    private String diffrences;



    public Edition(List<Object> list) {
        super(list);
    }

    public String getName() {
        return name;
    }

    public String getDiffrences() {
        return diffrences;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        name = list.get(0).equals("")? null : (String) list.get(0);
        diffrences = list.get(1).equals("")? null : (String) list.get(1);
    }
}
