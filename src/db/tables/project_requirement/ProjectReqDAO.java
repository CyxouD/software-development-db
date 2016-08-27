package db.tables.project_requirement;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import entities.ProjectReq;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProjectReqDAO implements Resultable{
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet projectReqSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.SHOW_ALL_PROJECTS_REQ);
        projectReqSet = preparedStatement.executeQuery();
        return showAllResultSetToList(projectReqSet);
    }

    public void addProjectReq(ProjectReq projectReq) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.INSERT_NEW_PROJECT_REq);

        preparedStatement.setString(1,projectReq.getProject());
        DBUtils.setNullIntInDb(2, projectReq.getRequirement(), preparedStatement);
        DBUtils.setNullBooleanInDb(3, projectReq.isInTime(), preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void updateProjectReq(ProjectReq projectReq) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.UPDATE_PROJECT_REQ);

        preparedStatement.setString(1,projectReq.getProject());
        DBUtils.setNullIntInDb(2, projectReq.getRequirement(), preparedStatement);
        preparedStatement.setString(3,projectReq.getProject());
        DBUtils.setNullIntInDb(4, projectReq.getRequirement(), preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void deleteProjectReq(String project, int requirement) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.DELETE_PROJECT_REQ);

        preparedStatement.setString(1,project);
        DBUtils.setNullIntInDb(2, requirement, preparedStatement);
        preparedStatement.executeUpdate();
    }

    public ArrayList getTopRequirementAtWrongTime() throws SQLException {
        ResultSet projectReqSet;
        String[] headers = {"Требования не в срок"};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.GET_TOP_REQUIREMENT_AT_WRONG_TIME);
        projectReqSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(projectReqSet, headers);
    }

    public ArrayList getTopRequirementOnTime() throws SQLException {
        ResultSet projectReqSet;
        String[] headers = {"Требование в срок"};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.GET_TOP_REQUIREMENT_ON_TIME);
        projectReqSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(projectReqSet, headers);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProjectReqQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }


    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String project = resultSet.getString(TableConstants.ProjectReqFields.Проект.toString());
            String req = resultSet.getString(TableConstants.ProjectReqFields.Требование.toString());
            String inTime = resultSet.getString(TableConstants.ProjectReqFields.В_срок.toString());

            stringList.add(project);
            stringList.add(req);
            stringList.add(inTime);
        }
        return stringList;
    }
}
