package db.tables.programming_language;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgLanguageQueries {
    public static String SHOW_ALL_PROG_LANGS = "SELECT * FROM `язык программирования`;";
    public static String INSERT_NEW_PROG_LANG = "INSERT INTO `язык программирования` (`Название`, `Предназначение`, " +
            "`Версия`, `Наиболее частая ОС`)" +
            " values (?,?,?,?)";
    public static String UPDATE_PROG_LANG = "UPDATE `язык программирования` SET `Название`=?, `Предназначение`=?," +
            "`Версия`=?, `Наиболее частая ОС`=?" +
            " WHERE Название = ?;";
    public static String DELETE_PROG_LANG = "DELETE from `язык программирования` WHERE `Название`=?";

    public static String SHOW_ALL_NAMES = "Select Название from `язык программирования`";


    public static String GET_POPULARITY_OF_PROG_LANG_IN_PROGRAMS = "SELECT Язык,`Общий бюджет проектов`, `Наиболее частая ОС языка`, `Количество программ`\n" +
            "FROM \n" +
            "(SELECT `Пишется на языке программирования` AS Язык, sum(бюджет) AS `Общий бюджет проектов`, `Наиболее частая ОС` AS `Наиболее частая ОС языка`, count(*) AS `Количество программ` FROM\n" +
            " (SELECT secondT.название, бюджет, `Пишется на языке программирования`, `Наиболее частая ОС` FROM \n" +
            "   (SELECT название, бюджет, `Пишется на языке программирования` FROM \n" +
            "     (SELECT Программа.название, бюджет FROM Программа INNER JOIN Проект ON Программа.проект = Проект.название)  AS firstT\n" +
            "       INNER JOIN Реализация ON firstT.название = Реализация.Программа)  AS secondT \n" +
            "         INNER JOIN `Язык программирования` ON `Язык программирования`.Название = secondT.`Пишется на языке программирования`) as thirdT\n" +
            "         GROUP BY `Пишется на языке программирования`, `Наиболее частая ОС`)  AS fouthT\n" +
            "ORDER BY `Количество программ` DESC;";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `язык программирования`";
}
