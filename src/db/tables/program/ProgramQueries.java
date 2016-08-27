package db.tables.program;

/**
 * Created by Dima on 21.04.2016.
 */
public class ProgramQueries {
    public static String SHOW_ALL_PROGRAMS = "SELECT * FROM программа;";
    public static String INSERT_NEW_PROGRAM = "INSERT INTO программа (Название, Проект, Предназначение) values (?,?,?)";
    public static String UPDATE_PROGRAM = "UPDATE программа SET Название=?, Проект=?, Предназначение=? WHERE Название = ?;";
    public static String DELETE_PROGRAM = "DELETE from программа WHERE Название=?";

    public static String SHOW_ALL_NAMES = "Select название from программа;";


    public static String PERCENT_OF_PROGRAM_THAT_USE_TWO_OR_MORE_OS = "SELECT sum(`Процентное соотношение количества ОС`) as `Процент программ требующих кроссплатформенность` FROM (\n" +
            "SELECT  `Количество ОС`, (count(*) * 100.0 / \n" +
            "  (SELECT count(*) FROM ( SELECT Программа,Count(*)  as `Количество ОС`\n" +
            "   From `Программа-Операционная система`\n" +
            "   GROUP BY Программа) as firstT)) as `Процентное соотношение количества ОС`\n" +
            "FROM  \n" +
            "  (SELECT Программа,Count(*)  as `Количество ОС`\n" +
            "   From `Программа-Операционная система`\n" +
            "   GROUP BY Программа) secondT\n" +
            "\n" +
            "GROUP BY  `Количество ОС` ) as thirdT\n" +
            "WHERE `Количество ОС` > 1;";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM программа";


    public static String CREATE_RANDOM_RROGRAM = "INSERT INTO программа (Название,Проект) values (?,'Download Technologies');";


}
