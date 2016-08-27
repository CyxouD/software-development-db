package db.tables.team;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class TeamDAO implements Resultable{
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet teamSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(TeamQueries.SHOW_ALL_TEAMS);
        teamSet = preparedStatement.executeQuery();
        return showAllResultSetToList(teamSet);
    }

    public void addTeam(Team team) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(TeamQueries.INSERT_NEW_TEAM);

        preparedStatement.setString(1,team.getTeam());
        preparedStatement.setString(2,team.getTeamLeader());
        preparedStatement.setString(3,team.getCompany());
        preparedStatement.executeUpdate();
    }

    public void updateTeam(Team team) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(TeamQueries.UPDATE_TEAM);

        preparedStatement.setString(1,team.getTeam());
        preparedStatement.setString(2,team.getTeamLeader());
        preparedStatement.setString(3,team.getCompany());
        preparedStatement.setString(4,team.getTeam());
        preparedStatement.executeUpdate();
    }

    public void deleteTeam(String teamName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(TeamQueries.DELETE_TEAM);

        preparedStatement.setString(1,teamName);
        preparedStatement.executeUpdate();
    }

    public ArrayList getNamesList() throws SQLException {
        ResultSet teamNameList;
        String[] fields = {TableConstants.TeamFields.Название.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(TeamQueries.SHOW_ALL_NAMES);
        teamNameList = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(teamNameList, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(TeamQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String teamName = resultSet.getString(String.valueOf(TableConstants.TeamFields.Название));
            String teamLead = resultSet.getString(String.valueOf(TableConstants.TeamFields.Глава_команды));
            String teamCompany = resultSet.getString(String.valueOf(TableConstants.TeamFields.Компания));

            stringList.add(teamName);
            stringList.add(teamLead);
            stringList.add(teamCompany);
        }
        return stringList;
    }
}
