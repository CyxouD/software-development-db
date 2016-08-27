package db.tables.project;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants.ProjectFields;
import entities.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 23.04.2016.
 */
public class ProjectDAO implements Resultable {

    public ArrayList getNamesList() throws SQLException {
        ResultSet projectNameSet;
        String[] fields = {ProjectFields.Название.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.SHOW_ALL_NAMES);
        projectNameSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(projectNameSet, fields);
    }

    @Override
    public ArrayList getList() throws SQLException {
        ResultSet projectsSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.SHOW_ALL_PROJECTS);
        projectsSet = preparedStatement.executeQuery();
        return showAllResultSetToList(projectsSet);
    }

    public void addProject(Project project) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.INSERT_NEW_PROJECT);

        preparedStatement.setString(1,project.getName());
        preparedStatement.setString(2,project.getTeam());
        preparedStatement.setString(3,project.getClient());
        preparedStatement.setString(4,project.getMethodology());
        DBUtils.setNullDoubleInDb(5, project.getBudget(), preparedStatement);
        DBUtils.setNullBooleanInDb(6, project.isFinished(), preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void updateProject(Project project) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.UPDATE_PROJECT);

        preparedStatement.setString(1,project.getName());
        preparedStatement.setString(2,project.getTeam());
        preparedStatement.setString(3,project.getClient());
        preparedStatement.setString(4,project.getMethodology());
        DBUtils.setNullDoubleInDb(5, project.getBudget(), preparedStatement);
        DBUtils.setNullBooleanInDb(6, project.isFinished(), preparedStatement);
        preparedStatement.setString(7,project.getName());
        preparedStatement.executeUpdate();
    }

    public void deleteProject(String projectName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.DELETE_PROJECT);

        preparedStatement.setString(1,projectName);
        preparedStatement.executeUpdate();
    }

    public ArrayList getSuccessfulProjectMethodology() throws SQLException {
        ResultSet projectsSet;
        String[] headers = {"Проект", "Процентное соотношение выполненых требований", "Методология"};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.GET_SUCCESFUL_PROJECTS_METHODOLOGY);
        projectsSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(projectsSet, headers);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString(ProjectFields.Название.toString());
            String team = resultSet.getString(ProjectFields.Команда.toString());
            String client = resultSet.getString(ProjectFields.Клиент.toString());
            String methodology = resultSet.getString(ProjectFields.Методология.toString());
            String budget = resultSet.getString(ProjectFields.Бюджет.toString());
            String finished = resultSet.getString(ProjectFields.Закончен.toString());

            stringList.add(name);
            stringList.add(team);
            stringList.add(client);
            stringList.add(methodology);
            stringList.add(budget);
            stringList.add(finished);
        }
        return stringList;
    }
}
