package db.tables.programming;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgrammingQueries {
    public static String SHOW_ALL_PROGRAMMING = "SELECT * FROM программирование;";
    public static String INSERT_NEW_PROGRAMMING = "INSERT INTO программирование (`Программный инженер`, `Его программа`)" +
            " values (?,?)";
    public static String UPDATE_PROGRAMMING = "UPDATE `программирование` SET `Программный инженер`=?, `Его программа`=?" +
            " WHERE `Программный инженер` = ? AND `Его программа`=?;";
    public static String DELETE_PROGRAMMING = "DELETE from программирование WHERE `Программный инженер`=? AND `Его программа`=? ";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM программирование";
}
