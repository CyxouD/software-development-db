package db.tables.version;

import db.DBConnection;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Version;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class VersionDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet versionSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(VersionQueries.SHOW_ALL_VERSIONS);
        versionSet = preparedStatement.executeQuery();
        return showAllResultSetToList(versionSet);
    }

    public void addVersion(Version version) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(VersionQueries.INSERT_NEW_VERSION);

        preparedStatement.setDate(1,version.getReleaseDate());
        preparedStatement.setString(2,version.getNumber());
        preparedStatement.setString(3,version.getProgram());
        preparedStatement.executeUpdate();
    }

    public void updateVersion(Version version) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(VersionQueries.UPDATE_VERSION);

        preparedStatement.setDate(1,version.getReleaseDate());
        preparedStatement.setString(2,version.getNumber());
        preparedStatement.setString(3,version.getProgram());
        preparedStatement.setDate(4,version.getReleaseDate());
        preparedStatement.executeUpdate();
    }

    public void deleteVersion(Date date) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(VersionQueries.DELETE_VERSION);

        preparedStatement.setDate(1,date);
        preparedStatement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(VersionQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String releaseDate = resultSet.getString(TableConstants.VersionFields.Дата_выпуска.toString());
            String number = resultSet.getString(TableConstants.VersionFields.Номер.toString());
            String program = resultSet.getString(TableConstants.VersionFields.Программа.toString());

            stringList.add(releaseDate);
            stringList.add(number);
            stringList.add(program);
        }
        return stringList;
    }
}
