package db.tables.team;

/**
 * Created by Dima on 25.04.2016.
 */
public class TeamQueries {
    public static String SHOW_ALL_TEAMS = "SELECT * FROM команда;";
    public static String INSERT_NEW_TEAM = "INSERT INTO команда (Название, `Глава команды`, Компания) values (?,?,?)";
    public static String UPDATE_TEAM = "UPDATE команда SET `Название`=?, `Глава команды`=?, Компания=? WHERE `Название` = ?;";
    public static String DELETE_TEAM = "DELETE from команда WHERE `Название`=?";

    public static String SHOW_ALL_NAMES = "Select Название from команда";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM команда";
}
