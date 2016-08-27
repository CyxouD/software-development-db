package db.tables.programming;

import db.DBConnection;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Programming;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgrammingDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet programmingSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingQueries.SHOW_ALL_PROGRAMMING);
        programmingSet = preparedStatement.executeQuery();
        return showAllResultSetToList(programmingSet);
    }


    public void addProgramming(Programming programming) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingQueries.INSERT_NEW_PROGRAMMING);

        preparedStatement.setString(1,programming.getSoftwareEngineer());
        preparedStatement.setString(2,programming.getProgram());
        preparedStatement.executeUpdate();
    }

    public void updateProgramming(Programming programming) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingQueries.UPDATE_PROGRAMMING);

        preparedStatement.setString(1,programming.getSoftwareEngineer());
        preparedStatement.setString(2,programming.getProgram());
        preparedStatement.setString(3,programming.getSoftwareEngineer());
        preparedStatement.setString(4,programming.getProgram());
        preparedStatement.executeUpdate();
    }

    public void deleteProgramming(String softwareEngineer, String program) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingQueries.DELETE_PROGRAMMING);

        preparedStatement.setString(1,softwareEngineer);
        preparedStatement.setString(2,program);
        preparedStatement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String softwareEngineer = resultSet.getString(TableConstants.ProgrammingFields.Программный_инженер.toString());
            String program = resultSet.getString(TableConstants.ProgrammingFields.Его_программа.toString());

            stringList.add(softwareEngineer);
            stringList.add(program);
        }
        return stringList;
    }
}
