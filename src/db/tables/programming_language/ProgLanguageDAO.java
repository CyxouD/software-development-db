package db.tables.programming_language;

import db.DBConnection;
import db.DBUtils;
import db.Resultable;
import db.tables.TableConstants;
import entities.ProgLang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgLanguageDAO implements Resultable {
    @Override
    public ArrayList getList() throws SQLException {
        ResultSet progLangSet;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.SHOW_ALL_PROG_LANGS);
        progLangSet = preparedStatement.executeQuery();
        return showAllResultSetToList(progLangSet);
    }

    public void addProgLang(ProgLang progLang) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.INSERT_NEW_PROG_LANG);

        preparedStatement.setString(1,progLang.getName());
        preparedStatement.setString(2,progLang.getPurpose());
        preparedStatement.setString(3, progLang.getVersion());
        preparedStatement.setString(4, progLang.getThe_most_popular_OS());
        preparedStatement.executeUpdate();
    }

    public void updateProgLang(ProgLang progLang) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.UPDATE_PROG_LANG);

        preparedStatement.setString(1,progLang.getName());
        preparedStatement.setString(2,progLang.getPurpose());
        preparedStatement.setString(3,progLang.getVersion());
        preparedStatement.setString(4,progLang.getThe_most_popular_OS());
        preparedStatement.setString(5,progLang.getName());
        preparedStatement.executeUpdate();
    }

    public void deleteProgLangs(String progName) throws  SQLException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.DELETE_PROG_LANG);

        preparedStatement.setString(1,progName);
        preparedStatement.executeUpdate();
    }

    public ArrayList getPopularityOfProgramLanguagesInProgram() throws SQLException {
        ResultSet progLangSet;
        String[] fields = {"Язык", "Общий бюджет проектов", "Наиболее частая ОС языка", "Количество программ"};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.GET_POPULARITY_OF_PROG_LANG_IN_PROGRAMS);
        progLangSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(progLangSet, fields);
    }



    public ArrayList getNamesList() throws SQLException {
        ResultSet progLangNameSet;
        String[] fields = {TableConstants.ProgLangFields.Название.toString()};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.SHOW_ALL_NAMES);
        progLangNameSet = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(progLangNameSet, fields);
    }

    public int getNumberOfRows() throws SQLException {
        ResultSet nRows;
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(ProgLanguageQueries.NUMBER_OF_ROWS);
        nRows = preparedStatement.executeQuery();
        nRows.next();
        return nRows.getInt("COUNT(*)");
    }

    @Override
    public ArrayList showAllResultSetToList(ResultSet resultSet) throws SQLException {
        ArrayList stringList = new ArrayList<>();
        while (resultSet.next()){
            String progName = resultSet.getString(TableConstants.ProgLangFields.Название.toString());
            String progPurpose = resultSet.getString(TableConstants.ProgLangFields.Предназначение.toString());
            String progVersion = resultSet.getString(TableConstants.ProgLangFields.Версия.toString());
            String progPopOs = resultSet.getString(TableConstants.ProgLangFields.Наиболее_частая_ОС.toString());

            stringList.add(progName);
            stringList.add(progPurpose);
            stringList.add(progVersion);
            stringList.add(progPopOs);
        }
        return stringList;
    }
}
