package db.tables.requirement;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import db.tables.client.ClientQueries;
import entities.Req;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ReqDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet reqSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ReqQueries.SHOW_ALL_REQ);
        reqSet = preparedStatement.executeQuery();
        return showAllResultSetToList(reqSet);
    }

    public void addReq(Req req) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ReqQueries.SHOW_ALL_REQ);

        DBUtils.setNullIntInDb(1, req.getId(), preparedStatement);
        preparedStatement.setString(2, req.getRequirement());
        preparedStatement.executeUpdate();
    }

    public void updateReq(Req req) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ReqQueries.UPDATE_REQ);

        preparedStatement.setInt(1, req.getId());
        preparedStatement.setString(2, req.getRequirement());
        DBUtils.setNullIntInDb(3, req.getId(), preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void deleteReq(int id) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ReqQueries.DELETE_REQ);

        DBUtils.setNullIntInDb(1, id, preparedStatement);
        preparedStatement.executeUpdate();
    }

    public ArrayList getIdList() throws SQLException {
        ResultSet reqIdSet;
        String[] fields = {TableConstants.ReqFields.id.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ReqQueries.SHOW_ALL_ID);
        reqIdSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(reqIdSet, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ReqQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()) {
            String reqId = resultSet.getString(TableConstants.ReqFields.id.toString());
            String req = resultSet.getString(TableConstants.ReqFields.Требование.toString());

            stringList.add(reqId);
            stringList.add(req);
        }
        return stringList;
    }
}
