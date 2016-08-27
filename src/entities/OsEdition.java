package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class OsEdition extends Entity {
    private String osName;
    private String osEdition;

    public OsEdition(List<Object> list) {
        super(list);
    }

    public String getOsName() {
        return osName;
    }

    public String getOsEdition() {
        return osEdition;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        osName = list.get(0).equals("")? null : (String) list.get(0);
        osEdition = list.get(1).equals("")? null : (String) list.get(1);
    }
}
