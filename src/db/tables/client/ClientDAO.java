package db.tables.client;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import entities.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ClientDAO implements Resultable {

    @Override
    public ArrayList getList() throws SQLException {
        ResultSet clientsSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ClientQueries.SHOW_ALL_CLIENTS);
        clientsSet = preparedStatement.executeQuery();
        return showAllResultSetToList(clientsSet);
    }

    public void addClient(Client client) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ClientQueries.INSERT_NEW_CLIENT);

        preparedStatement.setString(1,client.getClientName());
        DBUtils.setNullDoubleInDb(2, client.getPayment(), preparedStatement);

        preparedStatement.executeUpdate();
    }

    public void updateClient(Client client) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ClientQueries.UPDATE_CLIENT);

        preparedStatement.setString(1,client.getClientName());
        DBUtils.setNullDoubleInDb(2, client.getPayment(), preparedStatement);

        preparedStatement.setString(3,client.getClientName());
        preparedStatement.executeUpdate();
    }

    public void deleteClient(String clientName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ClientQueries.DELETE_CLIENT);

        preparedStatement.setString(1,clientName);
        preparedStatement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ClientQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }


    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String clientName = resultSet.getString(TableConstants.ClientFields.Название_фирмы.toString());
            String clientPayment = resultSet.getString(TableConstants.ClientFields.Оплата.toString());
            stringList.add(clientName);
            stringList.add(clientPayment);
        }
        return stringList;
    }

}
