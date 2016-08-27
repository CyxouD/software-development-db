package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 18.04.2016.
 */
public class DBUtils {
        public static ArrayList resultSetWithFieldsToList(ResultSet resultSet, String[] fields) throws SQLException {
            ArrayList stringList = new ArrayList<>();
            while (resultSet.next()){
                for (String fieldName : fields){
                    stringList.add(resultSet.getString(fieldName));
                }
            }
            return stringList;
        }

    public static void setNullIntInDb(int index, Integer intToCheck, PreparedStatement preparedStatement) throws SQLException {
        if (intToCheck != null){
            preparedStatement.setInt(index,(int) intToCheck);
        }
        else {
            preparedStatement.setNull(index, Types.INTEGER);
        }
    }

    public static void setNullDoubleInDb(int index, Double doubleToCheck, PreparedStatement preparedStatement) throws SQLException {
        if (doubleToCheck != null){
            preparedStatement.setDouble(index, doubleToCheck);
        }
        else {
            preparedStatement.setNull(index, Types.DOUBLE);
        }
    }

    public static void setNullBooleanInDb(int index, Boolean booleanToCheck, PreparedStatement preparedStatement) throws SQLException {
        if (booleanToCheck != null){
            preparedStatement.setBoolean(index, booleanToCheck);
        }
        else {
            preparedStatement.setNull(index, Types.BOOLEAN);
        }
    }

    public static Object[][] listToObjectArray(List list, int nColumns){
        Object[][] data = new Object[list.size () / nColumns][nColumns];

        int icolumn = 0;
        for (int i = 0; i < list.size (); i ++){
            if (icolumn == nColumns) icolumn = 0;
            data[i / nColumns][icolumn] = list.get (i);
            icolumn++;
        }
        return data;
    }
}
