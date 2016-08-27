package db.tables;

/**
 * Created by Dima on 17.04.2016.
 */
public class TableConstants {
    public static int PROGRAM_NUMBER_OF_COLUMNS = ProgramFields.values().length;
    public static int COMPANY_NUMBER_OF_COLUMNS = CompanyFields.values().length;
    public static int EDITION_NUMBER_OF_COLUMNS = EditionFields.values().length;
    public static int IMPLEMENTATION_NUMBER_OF_COLUMNS = ImplementationFields.values().length;
    public static int OS_NUMBER_OF_COLUMNS = OsFields.values().length;
    public static int OS_EDITION_NUMBER_OF_COLUMNS = OsEditionFields.values().length;
    public static int PROGRAM_OS_NUMBER_OF_COLUMNS = ProgramOsFields.values().length;
    public static int PROGRAMMING_NUMBER_OF_COLUMNS = ProgrammingFields.values().length;
    public static int PROG_LANG_NUMBER_OF_COLUMNS = ProgLangFields.values().length;
    public static int PROGRAMMING_METH_NUMBER_OF_COLUMNS = ProgMethodologyFields.values().length;
    public static int PROJECT_NUMBER_OF_COLUMNS = ProjectFields.values().length;
    public static int PROJECT_REQ_NUMBER_OF_COLUMNS = ProjectReqFields.values().length;
    public static int REQ_NUMBER_OF_COLUMNS = ReqFields.values().length;
    public static int SOFTWARE_ENGINEER_NUMBER_OF_COLUMNS = SoftwareEngineerFields.values().length;
    public static int TEAM_NUMBER_OF_COLUMNS = TeamFields.values().length;
    public static int VERSION_NUMBER_OF_COLUMNS = VersionFields.values().length;
    public static int CLIENT_NUMBER_OF_COLUMNS = ClientFields.values().length;


    public enum ProgramFields {
        Название,
        Проект,
        Предназначение
    }

    public enum ProjectFields {
        Название,
        Команда,
        Клиент,
        Методология,
        Бюджет,
        Закончен
    }

    public enum ClientFields {
        Название_фирмы("Название фирмы"),
        Оплата("Оплата");

        private String displayName;

        ClientFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum EditionFields {
        Название,
        Особенности
    }

    public enum CompanyFields {
        id("id"),
        Название("Название"),
        Полный_адрес("Полный адрес"),
        Штат("Штат"),
        Ежегодный_доход("Ежегодный доход"),
        Уровень_привилегий("Уровень привелегий");

        private String displayName;

        CompanyFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ImplementationFields{
        Программа("Программа"),
        Пишется_на_языке_программирования("Пишется на языке программирования");

        private String displayName;

        ImplementationFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum OsFields{
        Дата_выпуска("Дата выпуска"),
        Название("Название"),
        Предназначение("Предназначение"),
        Разрядность("Разрядность"),
        Тип_интерфейса("Тип интерфейса"),
        Тип_задачности("Тип задачности");

        private String displayName;

        OsFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum OsEditionFields{
        Операционная_система("Операционная система"),
        Издание_операционная_система("Издание операционная система");

        private String displayName;

        OsEditionFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ProgramOsFields{
        Программа("Программа"),
        Операционная_система("Операционная система");

        private String displayName;

        ProgramOsFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum ProgrammingFields{
        Программный_инженер("Программный инженер"),
        Его_программа("Его программа");

        private String displayName;

        ProgrammingFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ProgLangFields{
        Название("Название"),
        Предназначение("Предназначение"),
        Версия("Версия"),
        Наиболее_частая_ОС("Наиболее частая ОС");

        private String displayName;

        ProgLangFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ProgMethodologyFields{
        Название("Название"),
        Количество_человек_в_команде("Количество человек в команде"),
        Особенности("Особенности");

        private String displayName;

        ProgMethodologyFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ProjectReqFields{
        Проект("Проект"),
        Требование("Требование"),
        В_срок("В срок");

        private String displayName;

        ProjectReqFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }

    public enum ReqFields{
        id,
        Требование
    }

    public enum SoftwareEngineerFields{
        ФИО("ФИО"),
        Принадлежность_команде("Принадлежность команде"),
        Звание("Звание");

        private String displayName;

        SoftwareEngineerFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }


    }

    public enum TeamFields{
        Название("Название"),
        Глава_команды("Глава команды"),
        Компания("Компания");

        private String displayName;

        TeamFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }

    public enum VersionFields{
        Дата_выпуска("Дата выпуска"),
        Номер("Номер"),
        Программа("Программа");

        private String displayName;

        VersionFields(String displayName) {
            this.displayName = displayName;
        }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

    }



}
