package db.tables.edition;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Edition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class EditionDAO implements Resultable {


    public void addEditon(Edition edition) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(EditionQueries.INSERT_NEW_EDITION);

        preparedStatement.setString(1,edition.getName());
        preparedStatement.setString(2,edition.getDiffrences());
        preparedStatement.executeUpdate();
    }

    public void updateEdition(Edition edition) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(EditionQueries.UPDATE_EDITION);

        preparedStatement.setString(1,edition.getName());
        preparedStatement.setString(2,edition.getDiffrences());
        preparedStatement.setString(3,edition.getName());
        preparedStatement.executeUpdate();
    }

    public void deleteEdition(String editionName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(EditionQueries.DELETE_EDITION);

        preparedStatement.setString(1,editionName);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String editionName = resultSet.getString(TableConstants.EditionFields.Название.toString());
            String editionDiff = resultSet.getString(TableConstants.EditionFields.Особенности.toString());
            stringList.add(editionName);
            stringList.add(editionDiff);
        }
        return stringList;
    }

    public ArrayList getNamesList() throws SQLException {
        ResultSet editionNameSet;
        String[] fields = {TableConstants.EditionFields.Название.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(EditionQueries.SHOW_ALL_NAMES);
        editionNameSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(editionNameSet, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(EditionQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList getList() throws SQLException {
        ResultSet editionsSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(EditionQueries.SHOW_ALL_EDITIONS);
        editionsSet = preparedStatement.executeQuery();
        return showAllResultSetToList(editionsSet);
    }
}
