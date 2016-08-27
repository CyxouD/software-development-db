package db.tables.software_engineer;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.SoftwareEngineer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class SoftwareEngineerDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet seSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(SoftwareEngineerQueries.SHOW_ALL_SE);
        seSet = preparedStatement.executeQuery();
        return showAllResultSetToList(seSet);
    }

    public void addSe(SoftwareEngineer softwareEngineer) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(SoftwareEngineerQueries.INSERT_NEW_SE);

        preparedStatement.setString(1,softwareEngineer.getInitials());
        preparedStatement.setString(2,softwareEngineer.getTeam());
        preparedStatement.setString(3,softwareEngineer.getRank());
        preparedStatement.executeUpdate();
    }

    public void updateSe(SoftwareEngineer softwareEngineer) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(SoftwareEngineerQueries.UPDATE_SE);

        preparedStatement.setString(1,softwareEngineer.getInitials());
        preparedStatement.setString(2,softwareEngineer.getTeam());
        preparedStatement.setString(3,softwareEngineer.getRank());
        preparedStatement.setString(4,softwareEngineer.getInitials());
        preparedStatement.executeUpdate();
    }

    public void deleteSe(String initials) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(SoftwareEngineerQueries.DELETE_SE);

        preparedStatement.setString(1,initials);
        preparedStatement.executeUpdate();
    }

    public ArrayList getInitialsList() throws SQLException {
        ResultSet initialsSet;
        String[] fields = {TableConstants.SoftwareEngineerFields.ФИО.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(SoftwareEngineerQueries.SHOW_ALL_INITIALS);
        initialsSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(initialsSet, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(SoftwareEngineerQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String seInit = resultSet.getString(String.valueOf(TableConstants.SoftwareEngineerFields.ФИО));
            String seTeam = resultSet.getString(String.valueOf(TableConstants.SoftwareEngineerFields.Принадлежность_команде));
            String seRank = resultSet.getString(String.valueOf(TableConstants.SoftwareEngineerFields.Звание));

            stringList.add(seInit);
            stringList.add(seTeam);
            stringList.add(seRank);
        }
        return stringList;
    }
}
