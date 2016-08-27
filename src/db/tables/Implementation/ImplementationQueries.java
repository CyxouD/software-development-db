package db.tables.Implementation;

/**
 * Created by Dima on 25.04.2016.
 */
public class ImplementationQueries {
    public static String SHOW_ALL_IMPL = "SELECT * FROM реализация;";
    public static String INSERT_NEW_IMPL = "INSERT INTO реализация (`Программа`, `Пишется на языке программирования`) values (?,?)";
    public static String UPDATE_IMPL = "UPDATE реализация SET `Программа`=?, `Пишется на языке программирования`=? " +
            "WHERE `Программа` = ? AND `Пишется на языке программирования`=?;";
    public static String DELETE_IMPL = "DELETE from реализация WHERE `Программа` = ? AND `Пишется на языке программирования`=?";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM реализация";
}
