package db.tables.software_engineer;

/**
 * Created by Dima on 25.04.2016.
 */
public class SoftwareEngineerQueries {
    public static String SHOW_ALL_SE = "SELECT * FROM `программный инженер`;";
    public static String INSERT_NEW_SE = "INSERT INTO `программный инженер` (ФИО, `Принадлежность команде`, Звание) values (?,?,?)";
    public static String UPDATE_SE = "UPDATE `программный инженер` SET `ФИО`=?, `Принадлежность команде`=?, Звание=? WHERE `ФИО` = ?;";
    public static String DELETE_SE = "DELETE from `программный инженер` WHERE `ФИО`=?";

    public static String SHOW_ALL_INITIALS = "Select ФИО from `программный инженер`";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `программный инженер`";
}
