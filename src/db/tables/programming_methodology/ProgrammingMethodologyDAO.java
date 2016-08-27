package db.tables.programming_methodology;

import db.DBConnection;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.ProgrammingMethodology;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgrammingMethodologyDAO implements Resultable{
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet progMethodologySet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingMethodologyQueries.SHOW_ALL_PM);
        progMethodologySet = preparedStatement.executeQuery();
        return showAllResultSetToList(progMethodologySet);
    }

    public void addProgMethodology(ProgrammingMethodology programmingMethodology) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingMethodologyQueries.INSERT_NEW_PM);

        preparedStatement.setString(1,programmingMethodology.getName());
        preparedStatement.setString(2,programmingMethodology.getNumberOfMembers());
        preparedStatement.setString(3,programmingMethodology.getDiffrences());
        preparedStatement.executeUpdate();
    }

    public void updateProgMethodology(ProgrammingMethodology programmingMethodology) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingMethodologyQueries.UPDATE_PM);

        preparedStatement.setString(1,programmingMethodology.getName());
        preparedStatement.setString(2,programmingMethodology.getNumberOfMembers());
        preparedStatement.setString(3,programmingMethodology.getDiffrences());
        preparedStatement.setString(4,programmingMethodology.getName());
        preparedStatement.executeUpdate();
    }

    public void deleteProgMethodology(String methodName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingMethodologyQueries.DELETE_PM);

        preparedStatement.setString(1,methodName);
        preparedStatement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgrammingMethodologyQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String companyId = resultSet.getString(TableConstants.ProgMethodologyFields.Название.toString());
            String companyName = resultSet.getString(TableConstants.ProgMethodologyFields.Количество_человек_в_команде.toString());
            String companyAddress = resultSet.getString(TableConstants.ProgMethodologyFields.Особенности.toString());

            stringList.add(companyId);
            stringList.add(companyName);
            stringList.add(companyAddress);

        }
        return stringList;
    }
}
