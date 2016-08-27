package db.tables.programming_methodology;

/**
 * Created by Dima on 25.04.2016.
 */
public class ProgrammingMethodologyQueries {
    public static String SHOW_ALL_PM = "SELECT * FROM `методология программирования`;";
    public static String INSERT_NEW_PM = "INSERT INTO `методология программирования` (Название, `Количество человек в команде`, Особенности) values (?,?,?)";
    public static String UPDATE_PM = "UPDATE `методология программирования` SET Название=?, `Количество человек в команде`=?, Особенности=? WHERE Название = ?;";
    public static String DELETE_PM = "DELETE from `методология программирования` WHERE Название=?";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `методология программирования`";

}
