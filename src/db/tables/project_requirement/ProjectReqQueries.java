package db.tables.project_requirement;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProjectReqQueries {
    public static String SHOW_ALL_PROJECTS_REQ = "SELECT * FROM `проект-требование`;";
    public static String INSERT_NEW_PROJECT_REq = "INSERT INTO `проект-требование` (Проект, Требование, `В срок`)" +
            " values (?,?,?)";
    public static String UPDATE_PROJECT_REQ = "UPDATE `проект-требование` SET `Проект`=?, `Требование`=?" +
            " WHERE Проект = ? AND `Требование`=?;";
    public static String DELETE_PROJECT_REQ = "DELETE from `проект-требование` WHERE Проект = ? AND `Требование`=?";


    public static String GET_TOP_REQUIREMENT_AT_WRONG_TIME = " SELECT Требования.Требование as `Требования не в срок` \n" +
            " From Требования\n" +
            " INNER JOIN \n" +
            "   (SELECT Требование,`Процент выполняемости в проектах`\n" +
            "    From \n" +
            "       (SELECT Требование, (count(*) * 100.0 / \n" +
            "         (SELECT count(*) FROM  \n" +
            " \t\t   (SELECT Требование FROM `Проект-Требование` \n" +
            "             INNER JOIN Проект \n" +
            " \t\t    ON `Проект-Требование`.Проект = Проект.Название \n" +
            "             WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = False) as firstT)) AS `Процент выполняемости в проектах`\n" +
            "       FROM \n" +
            "          (SELECT Требование \n" +
            "           FROM `Проект-Требование` \n" +
            "           INNER JOIN Проект \n" +
            "           ON `Проект-Требование`.Проект = Проект.Название \n" +
            "           WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = False) as secondT\n" +
            "           GROUP BY Требование) as tt\n" +
            "    ) as onTable\n" +
            " ON Требования.id = onTable.Требование\n" +
            " WHERE `Процент выполняемости в проектах` = (\n" +
            "   SELECT max(`Процент выполняемости в проектах`) FROM (\n" +
            "     SELECT Требование, (count(*) * 100.0 / \n" +
            "        (SELECT count(*) FROM (\n" +
            "          SELECT Требование \n" +
            "           FROM `Проект-Требование` \n" +
            "           INNER JOIN Проект \n" +
            "           ON `Проект-Требование`.Проект = Проект.Название \n" +
            "           WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = False) as tttrrt)) AS `Процент выполняемости в проектах`\n" +
            " \t FROM (\n" +
            " \t   SELECT Требование \n" +
            " \t    FROM `Проект-Требование` \n" +
            "         INNER JOIN Проект \n" +
            "         ON `Проект-Требование`.Проект = Проект.Название \n" +
            "         WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = False) as fouthT\n" +
            "    GROUP BY Требование) as lastT)";


    public static String GET_TOP_REQUIREMENT_ON_TIME = "SELECT Требования.Требование as `Требование в срок`\n" +
            " From Требования\n" +
            " INNER JOIN \n" +
            "   (SELECT Требование,`Процент выполняемости в проектах`\n" +
            "    From \n" +
            "       (SELECT Требование, (count(*) * 100.0 / \n" +
            "         (SELECT count(*) FROM  \n" +
            " \t\t   (SELECT Требование FROM `Проект-Требование` \n" +
            "             INNER JOIN Проект \n" +
            " \t\t    ON `Проект-Требование`.Проект = Проект.Название \n" +
            "             WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = True) as firstT)) AS `Процент выполняемости в проектах`\n" +
            "       FROM \n" +
            "          (SELECT Требование \n" +
            "           FROM `Проект-Требование` \n" +
            "           INNER JOIN Проект \n" +
            "           ON `Проект-Требование`.Проект = Проект.Название \n" +
            "           WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = True) as secondT\n" +
            "           GROUP BY Требование) as tt\n" +
            "    ) as onTable\n" +
            " ON Требования.id = onTable.Требование\n" +
            " WHERE `Процент выполняемости в проектах` = (\n" +
            "   SELECT max(`Процент выполняемости в проектах`) FROM (\n" +
            "     SELECT Требование, (count(*) * 100.0 / \n" +
            "        (SELECT count(*) FROM (\n" +
            "          SELECT Требование \n" +
            "           FROM `Проект-Требование` \n" +
            "           INNER JOIN Проект \n" +
            "           ON `Проект-Требование`.Проект = Проект.Название \n" +
            "           WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = True) as tttrrt)) AS `Процент выполняемости в проектах`\n" +
            " \t FROM (\n" +
            " \t   SELECT Требование \n" +
            " \t    FROM `Проект-Требование` \n" +
            "         INNER JOIN Проект \n" +
            "         ON `Проект-Требование`.Проект = Проект.Название \n" +
            "         WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = True) as fouthT\n" +
            "    GROUP BY Требование) as lastT)";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `проект-требование`";
}

