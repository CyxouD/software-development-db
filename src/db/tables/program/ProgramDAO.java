package db.tables.program;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.TableConstants.ProgramFields;
import db.tables.client.ClientQueries;
import entities.Program;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 21.04.2016.
 */
public class ProgramDAO implements Resultable {

    @Override
    public ArrayList getList() throws SQLException {
        ResultSet programsSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.SHOW_ALL_PROGRAMS);
        programsSet = preparedStatement.executeQuery();
        return showAllResultSetToList(programsSet);
    }

    public void addProgram(Program program) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.INSERT_NEW_PROGRAM);

        preparedStatement.setString(1,program.getName());
        preparedStatement.setString(2,program.getProject());
        preparedStatement.setString(3,program.getPurpose());
        preparedStatement.executeUpdate();
    }

    public void updateProgram(Program program) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.UPDATE_PROGRAM);

        preparedStatement.setString(1,program.getName());
        preparedStatement.setString(2,program.getProject());
        preparedStatement.setString(3,program.getPurpose());
        preparedStatement.setString(4,program.getName());
        preparedStatement.executeUpdate();
    }

    public void deleteProgram(String programName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.DELETE_PROGRAM);

        preparedStatement.setString(1,programName);
        preparedStatement.executeUpdate();
    }

    public ArrayList getNamesList() throws SQLException {
        ResultSet programNameSet;
        String[] fields = {TableConstants.ProgramFields.Название.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.SHOW_ALL_NAMES);
        programNameSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(programNameSet, fields);
    }

    public int getPercentOfProgramThatUseTwoOrMoreOs() throws  SQLException {
        ResultSet percent;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.PERCENT_OF_PROGRAM_THAT_USE_TWO_OR_MORE_OS);
        percent = preparedStatement.executeQuery();
        percent.next();
        return percent.getInt("Процент программ требующих кроссплатформенность");
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgramQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }


    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String programName = resultSet.getString(ProgramFields.Название.toString());
            String programGoal = resultSet.getString(ProgramFields.Проект.toString());
            String programProject = resultSet.getString(ProgramFields.Предназначение.toString());
            stringList.add(programName);
            stringList.add(programGoal);
            stringList.add(programProject);
        }
        return stringList;
    }
}
