package db.tables.requirement;

/**
 * Created by Dima on 25.04.2016.
 */
public class ReqQueries {
    public static String SHOW_ALL_REQ = "SELECT * FROM `требования`;";
    public static String INSERT_NEW_REq = "INSERT INTO `требования` (id, Требование)" +
            " values (?,?)";
    public static String UPDATE_REQ = "UPDATE `требования` SET `id`=?, `Требование`=?," +
            " WHERE id = ?;";
    public static String DELETE_REQ = "DELETE from `требования` WHERE id = ?";


    public static String SHOW_ALL_ID = "Select id from требования";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM `требования`";
}
