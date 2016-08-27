package db.tables.version;

/**
 * Created by Dima on 25.04.2016.
 */
public class VersionQueries {
    public static String SHOW_ALL_VERSIONS = "SELECT * FROM версия;";
    public static String INSERT_NEW_VERSION = "INSERT INTO версия (`Дата выпуска`, Номер, Программа) values (?,?,?)";
    public static String UPDATE_VERSION = "UPDATE версия SET `Дата выпуска`=?, Номер=?, Программа=? WHERE `Дата выпуска` = ?;";
    public static String DELETE_VERSION = "DELETE from версия WHERE `Дата выпуска`=?";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM версия";
}
