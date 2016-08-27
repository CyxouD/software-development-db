package db.tables;

/**
 * Created by Dima on 19.05.2016.
 */
public class CombinedQueriers {

    public static final String GET_OS_POPULARITY = "SELECT * FROM (\n" +
            " SELECT `Операционная система`, Count(`Операционная система`)  as `Количество использования`\n" +
            " FROM `Программа-Операционная система`\n" +
            " GROUP BY `Операционная система`) as firstT\n" +
            " ORDER BY `Количество использования` DESC;";

    public static final String GET_FINISHED_PROJECTS = "SELECT Название, Команда, Клиент, Методология, Бюджет from проект where закончен = true";

    public static final String GET_PERCENTS_OF_PERFOMATION_EACH_REQUIREMENT_IN_FINISHED_PROJECTS =
            "SELECT Требования.Требование, `Процент выполняемости в законченных проектах`\n" +
            "  From Требования\n" +
            "  INNER JOIN \n" +
            "    (SELECT Требование,`Процент выполняемости в законченных проектах`\n" +
            "     From \n" +
            "        (SELECT Требование, (count(*) * 100.0 / \n" +
            "          (SELECT count(*) FROM  \n" +
            "  \t\t   (SELECT Требование FROM `Проект-Требование` \n" +
            "              INNER JOIN Проект \n" +
            "  \t\t    ON `Проект-Требование`.Проект = Проект.Название \n" +
            "              WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = True) as firstT)) AS `Процент выполняемости в законченных проектах`\n" +
            "        FROM \n" +
            "           (SELECT Требование \n" +
            "            FROM `Проект-Требование` \n" +
            "            INNER JOIN Проект \n" +
            "            ON `Проект-Требование`.Проект = Проект.Название \n" +
            "            WHERE Проект.Закончен = True AND `Проект-требование`.`В срок` = True) as secondT\n" +
            "            GROUP BY Требование) as tt\n" +
            "     ) as onTable\n" +
            "  ON Требования.id = onTable.Требование";

    public static final String GET_PROGRAM_NAMES_AND_WHAT_NUMBER_OS_THEY_USE =
            "SELECT Программа,Count(*)  as `Количество ОС`\n" +
            " From `Программа-Операционная система`\n" +
            " GROUP BY Программа;";

    public static final String GET_WHAT_NUMBER_OS_USE_PROGRAM = "SELECT Программа,Count(*)  as `Количество ОС`\n" +
            " From `Программа-Операционная система`\n" +
            " WHERE Программа = ?\n" +
            " GROUP BY Программа;";

    public static final String GET_PERCENT_RATIO_NUMBER_OF_USING_OS_IN_PROGRAM = " SELECT  `Количество ОС`, (count(*) * 100.0 / \n" +
            "    (SELECT count(*) FROM ( SELECT Программа,Count(*)  as `Количество ОС`\n" +
            "     From `Программа-Операционная система`\n" +
            "     GROUP BY Программа) as firstT)) as `Процентное соотношение количества ОС`\n" +
            "  FROM  \n" +
            "    (SELECT Программа,Count(*)  as `Количество ОС`\n" +
            "     From `Программа-Операционная система`\n" +
            "     GROUP BY Программа) secondT\n" +
            "  \n" +
            "  GROUP BY  `Количество ОС`";

    public static final String GET_PERCENT_OF_PROGRAMS_THAT_NEED_CROSPLATFORMING = " SELECT sum(`Процентное соотношение количества ОС`) as `Процент программ требующих кроссплатформенность` FROM (\n" +
            " SELECT  `Количество ОС`, (count(*) * 100.0 / \n" +
            "   (SELECT count(*) FROM ( SELECT Программа,Count(*)  as `Количество ОС`\n" +
            "    From `Программа-Операционная система`\n" +
            "    GROUP BY Программа) as firstT)) as `Процентное соотношение количества ОС`\n" +
            " FROM  \n" +
            "   (SELECT Программа,Count(*)  as `Количество ОС`\n" +
            "    From `Программа-Операционная система`\n" +
            "    GROUP BY Программа) secondT\n" +
            " \n" +
            " GROUP BY  `Количество ОС` ) as thirdT\n" +
            " WHERE `Количество ОС` > 1;";

    public static final String GET_PROGRAMS_OF_PROJECT_WITH_THE_HIGHEST_BUDGET = "SELECT Программа.название, бюджет\n" +
            " FROM Программа INNER JOIN Проект ON Программа.проект = Проект.название\n" +
            " Where Бюджет = (Select max(Бюджет) from Проект);";

    public static final String GET_PROGRAMS_OF_PROJECT_WITH_THE_LOWEST_BUDGET = " SELECT Программа.название, бюджет\n" +
            "  FROM Программа INNER JOIN Проект ON Программа.проект = Проект.название\n" +
            "  Where Бюджет = (Select min(Бюджет) from Проект);";

    public static final String GET_PROJECT_WITH_THE_HIGHEST_BUDGET = "SELECT Название from Проект\n" +
            " Where Бюджет = (Select max(Бюджет) from Проект);";

    public static final String GET_PROJECT_WITH_THE_LOWEST_BUDGET = "SELECT Название from Проект\n" +
            " Where Бюджет = (Select min(Бюджет) from Проект);";

    public static final String GET_WHAT_PROG_LANGUAGES_OF_PROGRAM = "SELECT название, `Пишется на языке программирования` FROM \n" +
            "       (SELECT Программа.название, бюджет FROM Программа INNER JOIN Проект ON Программа.проект = Проект.название)  AS firstT\n" +
            "         INNER JOIN Реализация ON firstT.название = Реализация.Программа\n" +
            "         WHERE Программа = ?;";

    public static final String GET_WHAT_PROG_LANGUAGES_USED_WITH_OS_AS_THE_MOST_POPULAR = "SELECT Название\n" +
            " FROM `Язык программирования` \n" +
            " Where `Наиболее частая ОС` = ?";

    public static final String GET_AT_FINISHED_PROJECTS_NUMBER_OF_REQUIREMENTS_AT_ALL_AND_PERFORMED = "SELECT Проект,sum(Abs(`В срок`)) as `Выполненных требований`, Count(`В срок`) as `Общее количество требований` \n" +
            "        FROM \n" +
            "         (SELECT Проект, Требование, `В срок`, `Методология` \n" +
            "  \t\tFROM `Проект-Требование` \n" +
            "          INNER JOIN Проект \n" +
            "          ON `Проект-Требование`.Проект = Проект.Название\n" +
            "  \t\tWHERE Проект.Закончен = True) as firstT\n" +
            "  \t  GROUP BY Проект";

    public static final String GET_AT_PROJECT_NUMBER_OF_REQUIREMENTS_AT_ALL_AND_PERFORMED = "SELECT Проект,sum(Abs(`В срок`)) as `Выполненных требований`, Count(`В срок`) as `Общее количество требований` \n" +
            "        FROM \n" +
            "         (SELECT Проект, Требование, `В срок`, `Методология` \n" +
            "  \t\tFROM `Проект-Требование` \n" +
            "          INNER JOIN Проект \n" +
            "          ON `Проект-Требование`.Проект = Проект.Название\n" +
            "          Where Проект.Название = ?) as firstT\n" +
            "  \t  GROUP BY Проект";

    public static final String GET_FINISHED_PROJECTS_WITH_PERCENT_RATIO_OF_REQUIREMENTS_PERFORMED = "SELECT * \n" +
            "  FROM \n" +
            "    (SELECT Проект,  (`Выполненных требований` / `Общее количество требований` *100) as `Процентное соотношение выполненых требований`, `Методология` \n" +
            "     FROM \n" +
            "       (SELECT Проект,sum(Abs(`В срок`)) as `Выполненных требований`, Count(`В срок`) as `Общее количество требований`, `Методология`  \n" +
            "        FROM \n" +
            "         (SELECT Проект, Требование, `В срок`, `Методология` \n" +
            "  \t\tFROM `Проект-Требование` \n" +
            "          INNER JOIN Проект \n" +
            "          ON `Проект-Требование`.Проект = Проект.Название\n" +
            "  \t\tWHERE Проект.Закончен = True) as firstT\n" +
            "  \t  GROUP BY Проект) as secondT\n" +
            "  \t) as thirdT\n" +
            "  WHERE `Процентное соотношение выполненых требований` >= ?";

}
