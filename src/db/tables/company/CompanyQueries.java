package db.tables.company;

/**
 * Created by Dima on 25.04.2016.
 */
public class CompanyQueries {
    public static String SHOW_ALL_COMPANIES = "SELECT id, Название, `Полный адрес`, Штат, `Ежегодный доход`,`Уровень привелегий`" +
            " FROM компания WHERE `Уровень привелегий` != 'Администратор БД';";
    public static String INSERT_NEW_COMPANY = "INSERT INTO компания (id, Название, `Полный адрес`, Штат, `Ежегодный доход`, `Пароль`, `Уровень привелегий`) values (?,?,?,?,?,?,?)";
    public static String UPDATE_COMPANY = "UPDATE компания SET `id`=?, Название = ?, `Полный адрес`=?, Штат = ?, `Ежегодный доход` = ?  WHERE `id` = ?;";
    public static String DELETE_COMPANY = "DELETE from компания WHERE `id` = ?";

    public static String SHOW_ALL_ID = "Select id from компания";

    public static String GET_PASSWORD_BY_NAME = "Select пароль from компания where название = ?";

    public static String GET_PRIVILEGES_BY_NAME = "Select `Уровень привелегий` from компания where название = ?";

    public static String GET_LAST_INSERTED_ID = "Select id from компания GROUP BY id DESC LIMIT 1;";

    public static String NUMBER_OF_ROWS = "SELECT COUNT(*) FROM компания";

    public static String CHANGE_COMPANY_PRIVILEGE = "UPDATE компания set `Уровень привелегий` = ? where id = ?;";
}
