package db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 17.04.2016.
 */
public interface Resultable {
    public ArrayList getList() throws SQLException;

    ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException;

}
