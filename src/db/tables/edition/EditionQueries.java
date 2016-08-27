package db.tables.edition;

/**
 * Created by Dima on 25.04.2016.
 */
public class EditionQueries {
    public static String SHOW_ALL_EDITIONS = "SELECT * FROM издание;";
    public static String INSERT_NEW_EDITION = "INSERT INTO издание (Название, Особенности) values (?,?)";
    public static String UPDATE_EDITION = "UPDATE издание SET `Название`=?, Особенности=? WHERE `Название` = ?;";
    public static String DELETE_EDITION = "DELETE from издание WHERE `Название` = ?";

    public static String SHOW_ALL_NAMES = "SELECT название from издание;";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM издание";
}
