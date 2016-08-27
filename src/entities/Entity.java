package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public abstract class Entity implements Common {
    public Entity(List<Object> list) {
        createObjectFromList(list);
    }
}
