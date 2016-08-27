package db.tests;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Dima on 02.05.2016.
 */
public class DBDataPreparing {


    public static int prepareRandomDataProgram_ProgramOsTables(int nRowsToAdd) throws SQLException {
        int nCreatedPrograms = nRowsToAdd / 3;

        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatementInsertRandomProg = connection.prepareStatement("INSERT INTO программа (Название,Проект) " +
                "values (?,'Download Technologies');");
        PreparedStatement preparedStatementInsertRandomProgWithRandomOs = connection.prepareStatement("INSERT INTO `программа-операционная система`" +
                "(Программа, `Операционная система`) values (?,?);");


        RandomString randomString = new RandomString(12);
        Random randomInt = new Random();
        ArrayList<String> OsArray = new ArrayList<>();
        OsArray.add("Ios");
        OsArray.add("Windows");
        OsArray.add("Linux");
        OsArray.add("МамаПапаДНК");

        for (int i = 0; i < nCreatedPrograms; i++) {
            String randomProg = randomString.nextString();
            preparedStatementInsertRandomProg.setString(1, randomProg);
            preparedStatementInsertRandomProg.addBatch();

            Collections.shuffle(OsArray);
            int n = randomInt.nextInt(4) + 1;
            preparedStatementInsertRandomProgWithRandomOs.setString(1, randomProg);
            for (int j = 0; j < n; j++){
                String randomOs = OsArray.get(j);
                preparedStatementInsertRandomProgWithRandomOs.setString(2, randomOs);
                preparedStatementInsertRandomProgWithRandomOs.addBatch();
            }
        }


        int[] records1Affected = preparedStatementInsertRandomProg.executeBatch();
        int[] records2Affected = preparedStatementInsertRandomProgWithRandomOs.executeBatch();

        return records1Affected.length + records2Affected.length;
    }

    public static int prepareRandomDataProgram_RealisationTables(int nRowsToAdd) throws SQLException {
        int nCreatedPrograms = nRowsToAdd / 3;

        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatementInsertRandomProg = connection.prepareStatement("INSERT INTO программа (Название,Проект) " +
                "values (?,'Download Technologies');");
        PreparedStatement preparedStatementInsertRandomProgWithRandomLanguage = connection.prepareStatement("INSERT INTO реализация" +
                " (Программа,`пишется на языке программирования`) values (?,?);");

        RandomString randomString = new RandomString(12);
        Random randomInt = new Random();
        ArrayList<String> progLangArray = new ArrayList<>();
        progLangArray.add("C#");
        progLangArray.add("Java");
        progLangArray.add("Javascript");
        progLangArray.add("Sex");
        progLangArray.add("C++");
        progLangArray.add("Java EE");

        for (int i = 0; i < nCreatedPrograms; i++) {
            String randomProgram = randomString.nextString();
            preparedStatementInsertRandomProg.setString(1, randomProgram);
            preparedStatementInsertRandomProg.addBatch();

            Collections.shuffle(progLangArray);
            int n = randomInt.nextInt(progLangArray.size()) + 1;
            preparedStatementInsertRandomProgWithRandomLanguage.setString(1, randomProgram);
            for (int j = 0; j < n; j++){
                String randomLang = progLangArray.get(j);
                preparedStatementInsertRandomProgWithRandomLanguage.setString(2, randomLang);
                preparedStatementInsertRandomProgWithRandomLanguage.addBatch();
            }
        }

        int[] records1Affected = preparedStatementInsertRandomProg.executeBatch();
        int[] records2Affected = preparedStatementInsertRandomProgWithRandomLanguage.executeBatch();

        return records1Affected.length + records2Affected.length;
    }

    public static int prepareRandomDataProject_ProjectReqTables(int nRowsToAdd) throws SQLException {
        int nCreatedProjects = nRowsToAdd / 3;

        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatementInsertRandomProject = connection.prepareStatement("INSERT INTO проект (Название,Команда,Методология,Закончен) " +
                "values (?,'SyperTeam',?,1);");
        PreparedStatement preparedStatementInsertRandomProjectWithRandomRequirement = connection.prepareStatement("INSERT INTO `проект-требование`" +
                " (Проект,`Требование`,`В срок`) values (?,?,?);");

        RandomString randomString = new RandomString(12);
        Random randomInt = new Random();
        ArrayList<Integer> reqArray = new ArrayList<>();
        reqArray.add(1);
        reqArray.add(2);
        reqArray.add(3);
        reqArray.add(4);
        reqArray.add(5);

        ArrayList<String> methodologyArray = new ArrayList<>();
        methodologyArray.add("KANBAN ");
        methodologyArray.add("SCRUM");

        Random randomBoolean = new Random();
        for (int i = 0; i < nCreatedProjects; i++) {
            String randomProject = randomString.nextString();
            preparedStatementInsertRandomProject.setString(1, randomProject);
            String randomMethodology = methodologyArray.get(randomBoolean.nextBoolean()?1:0);
            preparedStatementInsertRandomProject.setString(2, randomMethodology);
            preparedStatementInsertRandomProject.addBatch();

            Collections.shuffle(reqArray);
            int n = randomInt.nextInt(reqArray.size()) + 1;
            preparedStatementInsertRandomProjectWithRandomRequirement.setString(1, randomProject);
            for (int j = 0; j < n; j++){
                Integer randomReq = reqArray.get(j);
                preparedStatementInsertRandomProjectWithRandomRequirement.setInt(2, randomReq);
                preparedStatementInsertRandomProjectWithRandomRequirement.setInt(3, (randomBoolean.nextBoolean())?1:0);
                preparedStatementInsertRandomProjectWithRandomRequirement.addBatch();
            }
        }

        int[] records1Affected = preparedStatementInsertRandomProject.executeBatch();
        int[] records2Affected = preparedStatementInsertRandomProjectWithRandomRequirement.executeBatch();

        return records1Affected.length + records2Affected.length;
    }

    public static void rollBackAndSetAutoCommitInfo() throws SQLException {
        DBConnection.getConnection().rollback();
        DBConnection.getConnection().setAutoCommit(true);
    }

    public static void main(String[] args){
    }

}
