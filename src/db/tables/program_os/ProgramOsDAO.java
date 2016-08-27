package db.tables.program_os;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import entities.ProgramOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgramOsDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet programOsSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramOsQueries.SHOW_ALL_PROGRAM_OS);
        programOsSet = preparedStatement.executeQuery();
        return showAllResultSetToList(programOsSet);
    }


    public void addProgramOs(ProgramOs programOs) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramOsQueries.INSERT_NEW_PROGRAM_OS);

        preparedStatement.setString(1,programOs.getProgram());
        preparedStatement.setString(2,programOs.getOs());
        preparedStatement.executeUpdate();
    }

    public void updateProgramOs(ProgramOs programOs) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramOsQueries.UPDATE_PROGRAM_OS);

        preparedStatement.setString(1,programOs.getProgram());
        preparedStatement.setString(2,programOs.getOs());
        preparedStatement.setString(3,programOs.getProgram());
        preparedStatement.setString(4,programOs.getOs());
        preparedStatement.executeUpdate();
    }

    public void deleteProgramOs(String program, String os) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramOsQueries.DELETE_PROGRAM_OS);

        preparedStatement.setString(1,program);
        preparedStatement.setString(2,os);
        preparedStatement.executeUpdate();
    }

    public ArrayList getOsFrequencyOfUse() throws SQLException {
        ResultSet osNumberSet;
        String[] fields = {"Операционная система", "Количество использования"};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramOsQueries.SHOW_HOW_MUCH_EACH_OS_USED);
        osNumberSet = preparedStatement.executeQuery();

        return DBUtils.resultSetWithFieldsToList(osNumberSet, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramOsQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }


    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String program = resultSet.getString(TableConstants.ProgramOsFields.Программа.toString());
            String os = resultSet.getString(TableConstants.ProgramOsFields.Операционная_система.toString());

            stringList.add(program);
            stringList.add(os);
        }
        return stringList;
    }
}
