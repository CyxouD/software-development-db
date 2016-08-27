package db.tables.company;

import db.CryptWithMD5;
import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import entities.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class CompanyDAO implements Resultable {

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String companyId = resultSet.getString(TableConstants.CompanyFields.id.toString());
            String companyName = resultSet.getString(TableConstants.CompanyFields.Название.toString());
            String companyAddress = resultSet.getString(TableConstants.CompanyFields.Полный_адрес.toString());
            String companyStuff = resultSet.getString(TableConstants.CompanyFields.Штат.toString());
            String companyAnnualProf = resultSet.getString(TableConstants.CompanyFields.Ежегодный_доход.toString());
            String oompanyLevelPrivileges = resultSet.getString(TableConstants.CompanyFields.Уровень_привилегий.toString());

            stringList.add(companyId);
            stringList.add(companyName);
            stringList.add(companyAddress);
            stringList.add(companyStuff);
            stringList.add(companyAnnualProf);
            stringList.add(oompanyLevelPrivileges);
        }
        return stringList;
    }

    public void addCompany(Company company) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.INSERT_NEW_COMPANY);

        System.out.println(preparedStatement.toString());
        DBUtils.setNullIntInDb(1, company.getId(), preparedStatement);
        preparedStatement.setString(2,company.getName());
        preparedStatement.setString(3,company.getFullAddress());
        DBUtils.setNullIntInDb(4, company.getStaff(), preparedStatement);
        DBUtils.setNullDoubleInDb(5, company.getAnnualProfit(), preparedStatement);
        preparedStatement.setString(6, CryptWithMD5.cryptWithMD5(company.getPassword()));
        preparedStatement.setString(7, company.getPrivilege_level());

        preparedStatement.executeUpdate();
    }

    public void updateCompany(Company company) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.UPDATE_COMPANY);

        DBUtils.setNullIntInDb(1, company.getId(), preparedStatement);
        preparedStatement.setString(2,company.getName());
        preparedStatement.setString(3,company.getFullAddress());
        DBUtils.setNullIntInDb(4, company.getStaff(), preparedStatement);
        DBUtils.setNullDoubleInDb(5, company.getAnnualProfit(), preparedStatement);
        DBUtils.setNullIntInDb(6, company.getId(), preparedStatement);

        preparedStatement.executeUpdate();
    }

    public void deleteCompany(int companyId) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.DELETE_COMPANY);

        DBUtils.setNullIntInDb(1, companyId, preparedStatement);
        preparedStatement.executeUpdate();
    }

    public ArrayList getIdList() throws SQLException {
        ResultSet companyIdSet;
        String[] fields = {TableConstants.CompanyFields.id.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.SHOW_ALL_ID);
        companyIdSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(companyIdSet, fields);
    }

    public String getPasswordByName(String companyName) throws  SQLException {
        ResultSet pass;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.GET_PASSWORD_BY_NAME);

        preparedStatement.setString(1, companyName);
        pass = preparedStatement.executeQuery();
        while (pass.next()) {
            return pass.getString("Пароль");
        }
        return null;
    }

    public String getPrivelegesByName(String companyName) throws  SQLException {
        ResultSet pass;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.GET_PRIVILEGES_BY_NAME);

        preparedStatement.setString(1, companyName);
        pass = preparedStatement.executeQuery();
        while (pass.next()) {
            return pass.getString("Уровень привелегий");
        }
        return null;
    }

    public int getLastInsertedId() throws SQLException {
        ResultSet id;

        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.GET_LAST_INSERTED_ID);
        id = preparedStatement.executeQuery();
        id.next();
        return id.getInt("id");
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    public void updateCompanyPrivilege(String privilege, int companyId) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.CHANGE_COMPANY_PRIVILEGE);

        preparedStatement.setString(1, privilege);
        preparedStatement.setInt(2, companyId);

        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList getList() throws SQLException {
        ResultSet companiesSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(CompanyQueries.SHOW_ALL_COMPANIES);
        companiesSet = preparedStatement.executeQuery();
        return showAllResultSetToList(companiesSet);
    }
}
