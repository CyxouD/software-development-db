package db.tables.os_edition;

/**
 * Created by Dima on 25.04.2016.
 */
public class OsEditionQueries {
    public static String SHOW_ALL_OS_EDITIONS = "SELECT * FROM `издание операционной системы`;";
    public static String INSERT_NEW_OS_EDITION = "INSERT INTO `издание операционной системы` (`Операционная система`," +
            "`Издание операционная система`) values (?,?)";
    public static String UPDATE_OS_EDITION = "UPDATE `издание операционной системы` SET `Операционная система`=?," +
            " `Издание операционная система`=? WHERE `Операционная система` = ? AND `Издание операционная система`=?;";
    public static String DELETE_OS_EDITION = "DELETE from `издание операционной системы` " +
            "WHERE `Операционная система` = ? AND `Издание операционная система`";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `издание операционной системы`";
}
