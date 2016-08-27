package db.tables;

import db.DBConnection;
import db.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.tables.CombinedQueriers.*;

/**
 * Created by Dima on 19.05.2016.
 */
public class CombinedQueriesImplementation {

    public ArrayList getFinishedProjects() throws SQLException {
        ResultSet set;
        String[] fields = {"Название", "Команда", "Клиент", "Методология", "Бюджет"};
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(GET_FINISHED_PROJECTS);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getPercentOfPerformationEachRequirementInFinishedProjects() throws SQLException {
        ResultSet set;
        String[] fields = {"Требование", "Процент выполняемости в законченных проектах"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PERCENTS_OF_PERFOMATION_EACH_REQUIREMENT_IN_FINISHED_PROJECTS);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getProgramNamesAndWhatNumberOsTheyUse() throws SQLException {
        ResultSet set;
        String[] fields = {"Программа", "Количество ОС"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PROGRAM_NAMES_AND_WHAT_NUMBER_OS_THEY_USE);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getWhatNumberOsUseProgram(String program) throws SQLException {
        ResultSet set;
        String[] fields = {"Программа", "Количество ОС"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_WHAT_NUMBER_OS_USE_PROGRAM);
        preparedStatement.setString(1, program);

        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getPercentRatioNumberOfUsingOsInPrograms() throws SQLException {
        ResultSet set;
        String[] fields = {"Количество ОС", "Процентное соотношение количества ОС"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PERCENT_RATIO_NUMBER_OF_USING_OS_IN_PROGRAM);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getProgramsOfProjectWithTheHighestBudget() throws SQLException {
        ResultSet set;
        String[] fields = {"название", "бюджет"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PROGRAMS_OF_PROJECT_WITH_THE_HIGHEST_BUDGET);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getProgramsOfProjectWithTheLowestBudget() throws SQLException {
        ResultSet set;
        String[] fields = {"название", "бюджет"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PROGRAMS_OF_PROJECT_WITH_THE_LOWEST_BUDGET);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public String getProjectWithTheHighestBudget() throws SQLException {
        ResultSet set;
        String[] fields = {"Название"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PROJECT_WITH_THE_HIGHEST_BUDGET);
        set = preparedStatement.executeQuery();
        set.next();
        return set.getString("Название");
    }

    public String getProjectWithTheLowestBudget() throws SQLException {
        ResultSet set;
        String[] fields = {"Название"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_PROJECT_WITH_THE_LOWEST_BUDGET);
        set = preparedStatement.executeQuery();
        set.next();
        return set.getString("Название");
    }

    public ArrayList getWhatProgLanguagesOfProgram(String program) throws SQLException {
        ResultSet set;
        String[] fields = {"название", "Пишется на языке программирования"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_WHAT_PROG_LANGUAGES_OF_PROGRAM);
        preparedStatement.setString(1, program);

        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getWhatProgLanguagesUsedWithOsAsTheMostPopular(String os) throws SQLException {
        ResultSet set;
        String[] fields = {"Название"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_WHAT_PROG_LANGUAGES_USED_WITH_OS_AS_THE_MOST_POPULAR);
        preparedStatement.setString(1, os);

        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getAtFinishedProjectsNumberOfRequirementsAtAllAndPerformed() throws SQLException {
        ResultSet set;
        String[] fields = {"Проект", "Выполненных требований", "Общее количество требований"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_AT_FINISHED_PROJECTS_NUMBER_OF_REQUIREMENTS_AT_ALL_AND_PERFORMED);
        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getAtProjectNumberOfRequirementsAtAllAndPerformed(String project) throws SQLException {
        ResultSet set;
        String[] fields = {"Проект", "Выполненных требований", "Общее количество требований"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_AT_PROJECT_NUMBER_OF_REQUIREMENTS_AT_ALL_AND_PERFORMED);
        preparedStatement.setString(1, project);

        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }

    public ArrayList getFinishedProjectsWithPercentRatioOfRequirementsPerformed(int percent) throws SQLException {
        ResultSet set;
        String[] fields = {"Проект", "Процентное соотношение выполненых требований", "Методология"};
        PreparedStatement preparedStatement = DBConnection.getConnection()
                .prepareStatement(GET_FINISHED_PROJECTS_WITH_PERCENT_RATIO_OF_REQUIREMENTS_PERFORMED);
        preparedStatement.setInt(1, percent);

        set = preparedStatement.executeQuery();
        return DBUtils.resultSetWithFieldsToList(set, fields);
    }





}


