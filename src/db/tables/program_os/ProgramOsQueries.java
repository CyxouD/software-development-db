package db.tables.program_os;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgramOsQueries {
    public static String SHOW_ALL_PROGRAM_OS = "SELECT * FROM `программа-операционная система`;";
    public static String INSERT_NEW_PROGRAM_OS = "INSERT INTO `программа-операционная система` (`Программа`, `Операционная система`)" +
            " values (?,?)";
    public static String UPDATE_PROGRAM_OS = "UPDATE `программа-операционная система` SET Программа=?, `Операционная система`=?" +
            " WHERE Программа = ? AND `Операционная система`=?;";
    public static String DELETE_PROGRAM_OS = "DELETE from `программа-операционная система` WHERE Программа = ? AND `Операционная система`=?";

    public static String SHOW_HOW_MUCH_EACH_OS_USED = "SELECT  `Операционная система`, `Количество использования` FROM (\n" +
            "SELECT `Операционная система`, Count(`Операционная система`)  as `Количество использования`\n" +
            "FROM `Программа-Операционная система`\n" +
            "GROUP BY `Операционная система` ) as Table1\n" +
            "ORDER BY `Количество использования` DESC;";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `программа-операционная система`";
}
