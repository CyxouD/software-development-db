package db.tables.os_edition;

import db.DBConnection;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.OsEdition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class OsEditionDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet osEditionSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsEditionQueries.SHOW_ALL_OS_EDITIONS);
        osEditionSet = preparedStatement.executeQuery();
        return showAllResultSetToList(osEditionSet);
    }


    public void addOsEdition(OsEdition osEdition) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsEditionQueries.SHOW_ALL_OS_EDITIONS);

        preparedStatement.setString(1,osEdition.getOsName());
        preparedStatement.setString(2,osEdition.getOsEdition());
        preparedStatement.executeUpdate();
    }

    public void updateOsEdition(OsEdition osEdition) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsEditionQueries.UPDATE_OS_EDITION);

        preparedStatement.setString(1,osEdition.getOsName());
        preparedStatement.setString(2,osEdition.getOsEdition());
        preparedStatement.setString(3,osEdition.getOsName());
        preparedStatement.setString(4,osEdition.getOsEdition());
        preparedStatement.executeUpdate();
    }

    public void deleteOsEdition(String os, String osEdition) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsEditionQueries.DELETE_OS_EDITION);

        preparedStatement.setString(1,os);
        preparedStatement.setString(2,osEdition);
        preparedStatement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(OsEditionQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String os = resultSet.getString(TableConstants.OsEditionFields.Операционная_система.toString());
            String osEdition = resultSet.getString(TableConstants.OsEditionFields.Издание_операционная_система.toString());

            stringList.add(os);
            stringList.add(osEdition);
        }
        return stringList;
    }
}
