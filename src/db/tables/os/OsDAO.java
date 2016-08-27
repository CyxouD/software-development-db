package db.tables.os;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Os;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class OsDAO implements Resultable{

    @Override
    public ArrayList getList() throws SQLException {
        ResultSet osSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsQueries.SHOW_ALL_OS);
        osSet = preparedStatement.executeQuery();
        return showAllResultSetToList(osSet);
    }

    public void addOs(Os os) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsQueries.INSERT_NEW_OS);

        preparedStatement.setDate(1, (Date) os.getReleaseDate());
        preparedStatement.setString(2,os.getName());
        preparedStatement.setString(3,os.getPurpose());
        preparedStatement.setString(4,os.getCapacity());
        preparedStatement.setString(5,os.getInterfaceType());
        preparedStatement.setString(6,os.getTaskType());
        preparedStatement.executeUpdate();
    }

    public void updateOs(Os os) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsQueries.UPDATE_OS);

        preparedStatement.setDate(1, (Date) os.getReleaseDate());
        preparedStatement.setString(2,os.getName());
        preparedStatement.setString(3,os.getPurpose());
        preparedStatement.setString(4,os.getCapacity());
        preparedStatement.setString(5,os.getInterfaceType());
        preparedStatement.setString(6,os.getTaskType());
        preparedStatement.setString(7,os.getName());
        preparedStatement.executeUpdate();
    }

    public void deleteOs(String osName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsQueries.DELETE_OS);

        preparedStatement.setString(1,osName);
        preparedStatement.executeUpdate();
    }

    public ArrayList getNamesList() throws SQLException {
        ResultSet osNameSet;
        String[] fields = {TableConstants.OsFields.Название.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsQueries.SHOW_ALL_NAMES);
        osNameSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(osNameSet, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }


    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String osRelease = resultSet.getString(String.valueOf(TableConstants.OsFields.Дата_выпуска));
            String osName = resultSet.getString(TableConstants.OsFields.Название.toString());
            String osPurpose = resultSet.getString(TableConstants.OsFields.Предназначение.toString());
            String osCapacity = resultSet.getString(String.valueOf(TableConstants.OsFields.Разрядность.toString()));
            String osInterfaceType = resultSet.getString(TableConstants.OsFields.Тип_интерфейса.toString());
            String osTaskType = resultSet.getString(String.valueOf(TableConstants.OsFields.Тип_задачности.toString()));
            stringList.add(osRelease);
            stringList.add(osName);
            stringList.add(osPurpose);
            stringList.add(osCapacity);
            stringList.add(osInterfaceType);
            stringList.add(osTaskType);
        }
        return stringList;
    }


}
