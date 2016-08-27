package db.tables.client;

/**
 * Created by Dima on 25.04.2016.
 */
public class ClientQueries {
    public static String SHOW_ALL_CLIENTS = "SELECT * FROM клиент;";
    public static String INSERT_NEW_CLIENT = "INSERT INTO клиент (`Название фирмы`, Оплата) values (?,?)";
    public static String UPDATE_CLIENT = "UPDATE клиент SET `Название фирмы`=?, Оплата=? WHERE `Название фирмы` = ?;";
    public static String DELETE_CLIENT = "DELETE from клиент WHERE `Название фирмы` = ?";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM клиент";
}
