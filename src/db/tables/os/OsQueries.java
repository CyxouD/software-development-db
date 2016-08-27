package db.tables.os;

/**
 * Created by Dima on 25.04.2016.
 */
public class OsQueries {
    public static String SHOW_ALL_OS = "SELECT * FROM `операционная система`;";
    public static String INSERT_NEW_OS = "INSERT INTO `операционная система` (`Дата выпуска`, Название, Предназначение," +
            " Разрядность, `Тип интерфейса`, `Тип задачности`) values (?,?,?,?,?,?)";
    public static String UPDATE_OS = "UPDATE `операционная система` SET `Дата выпуска`=?, Название=?,`Предназначение`=?," +
            " Разрядность=?,`Тип интерфейса`=?, `Тип задачности`=? WHERE `Название` = ?;";
    public static String DELETE_OS = "DELETE from `операционная система` WHERE `Название`=?";

    public static String SHOW_ALL_NAMES = "Select Название from `операционная система`";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `операционная система`";
}
