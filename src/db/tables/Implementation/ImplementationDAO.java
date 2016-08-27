package db.tables.Implementation;

import db.DBConnection;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ImplementationDAO implements Resultable{
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet implementationsSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ImplementationQueries.SHOW_ALL_IMPL);
        implementationsSet = preparedStatement.executeQuery();
        return showAllResultSetToList(implementationsSet);
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String program = resultSet.getString(TableConstants.ImplementationFields.Программа.toString());
            String language = resultSet.getString(TableConstants.ImplementationFields.Пишется_на_языке_программирования.toString());
            stringList.add(program);
            stringList.add(language);
        }
        return stringList;
    }

    public void addImpl(Implementation implementation) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ImplementationQueries.SHOW_ALL_IMPL);

        preparedStatement.setString(1,implementation.getProgramName());
        preparedStatement.setString(2,implementation.getProgramLanguage());
        preparedStatement.executeUpdate();
    }

    public void updateImpl(Implementation implementation) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ImplementationQueries.UPDATE_IMPL);

        preparedStatement.setString(1,implementation.getProgramName());
        preparedStatement.setString(2,implementation.getProgramLanguage());
        preparedStatement.setString(3,implementation.getProgramName());
        preparedStatement.setString(4,implementation.getProgramLanguage());
    }

    public void deleteImpl(String program, String language) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ImplementationQueries.DELETE_IMPL);

        preparedStatement.setString(1,program);
        preparedStatement.setString(2,language);
        preparedStatement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ImplementationQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }
}
