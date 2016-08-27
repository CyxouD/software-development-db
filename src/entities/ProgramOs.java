package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgramOs extends Entity {
    private String program;
    private String os;

    public ProgramOs(List<Object> list) {
        super(list);
    }

    public String getProgram() {
        return program;
    }

    public String getOs() {
        return os;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        program = list.get(0).equals("")? null : (String) list.get(0);
        os = list.get(1).equals("")? null : (String) list.get(1);
    }
}
