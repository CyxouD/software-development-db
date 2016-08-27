package db.tables.project;

/**
 * Created by Dima on 23.04.2016.
 */
public class ProjectQueries {
    public static String SHOW_ALL_PROJECTS = "SELECT * FROM проект;";
    public static String INSERT_NEW_PROJECT = "INSERT INTO проект (`Название`, `Команда`, `Клиент`, `Методология`, `Бюджет`, `Закончен`)" +
            " values (?,?,?,?,?,?)";
    public static String UPDATE_PROJECT = "UPDATE проект SET `Название`=?, `Команда`=?," +
            "`Клиент`=?, `Методология`=?,`Бюджет`=?, `Закончен`=?" +
            " WHERE Название = ?;";
    public static String DELETE_PROJECT = "DELETE from проект WHERE `Название`=?";

    public static String SHOW_ALL_NAMES = "SELECT название FROM проект;";

    public static String GET_SUCCESFUL_PROJECTS_METHODOLOGY = "SELECT * \n" +
            " FROM \n" +
            "   (SELECT Проект,  (`Выполненных требований` / `Общее количество требований` *100) as `Процентное соотношение выполненых требований`, `Методология` \n" +
            "    FROM \t\n" +
            "      (SELECT Проект,sum(Abs(`В срок`)) as `Выполненных требований`, Count(`В срок`) as `Общее количество требований`, `Методология`  \n" +
            "       FROM \n" +
            "        (SELECT Проект, Требование, `В срок`, `Методология` \n" +
            " \t\tFROM `Проект-Требование` \n" +
            "         INNER JOIN Проект \n" +
            "         ON `Проект-Требование`.Проект = Проект.Название\n" +
            " \t\tWHERE Проект.Закончен = True) as firstT\n" +
            " \t  GROUP BY Проект) as secondT\n" +
            " \t) as thirdT\n" +
            " WHERE `Процентное соотношение выполненых требований` >= 60 \n" +
            " AND `Процентное соотношение выполненых требований` = \n" +
            " (SELECT Max(`Процентное соотношение выполненых требований`) AS Выражение1\n" +
            "    FROM \n" +
            "      (SELECT Проект,  (`Выполненных требований` / `Общее количество требований` *100) as `Процентное соотношение выполненых требований` \n" +
            "       FROM \n" +
            "         (SELECT Проект,sum(Abs(`В срок`)) as `Выполненных требований`, Count(`В срок`) as `Общее количество требований` \n" +
            "          FROM \n" +
            "            (SELECT Проект, Требование, `В срок`\n" +
            " \t\t\tFROM `Проект-Требование` \n" +
            "             INNER JOIN Проект \n" +
            "             ON `Проект-Требование`.Проект = Проект.Название\n" +
            " \t\t\tWHERE Проект.Закончен = True) as fouthT\n" +
            "           GROUP BY Проект) as fifthT\n" +
            "       ) as sixthT\n" +
            " \n" +
            " );\n";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM проект";
}
