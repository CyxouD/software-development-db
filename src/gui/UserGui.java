package gui;

import db.DBConnection;
import db.DBOperations;
import entities.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class UserGui extends JFrame {
    private JPanel mainPanel;
    private JPanel recordPanel;
    private JPanel tablePanel;
    private JPanel tableOperationsPanel;
    private JPanel dataWorkPanel;

    private MainTasks mainTasks;

    private JToggleButton recordViewButton;

    private JTable dataTable;
    private JTable recordRowTable;
    private JScrollPane recordPane;
    private JButton firstRecordButton;
    private JButton previousRecordButton;
    private JButton deleteRecordButton;
    private JButton updateRecordButton;
    private JButton createRecordButton;
    private JButton nextRecordButton;
    private JButton lastRecordButton;
    private HintTextField currentPointerNumberTF;


    private int rowPointer;
    private int dataTableColumnNumber;

    private JComboBox<String> tablesList;

    private static final String RECORDS_PANEL = "Card with Record panel";
    private static final String TABLE_PANEL = "Card with Table panel";

    private static final String PROGRAMS_PANEL = "Program";

    private DBOperations dbOperations;

    private String userPrivilige;
    private final String USER_PRIVILIGE = "Пользователь";

    public UserGui(String privilige) {
        DBConnection.init();
        dbOperations = new DBOperations();
        mainTasks = new MainTasks();
        userPrivilige = privilige;

        getGuiPanel();
    }

    public JPanel getGuiPanel() {
        mainPanel = new JPanel(new BorderLayout());
        String[] tables = {"Version",
                "Edition",
                "OS edition",
                "Client",
                "Team",
                "Company",
                "Programming methodology",
                "OS",
                "Program",
                "Program-OS",
                "Programming",
                "Software Engineer",
                "Project",
                "Project - Requirement",
                "Implementation",
                "Requirement",
                "Programming Language"};

        tablesList = new JComboBox<String>(tables);


        JPanel northPanel = new JPanel(new BorderLayout());
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitMenuItemListener());
        fileMenu.add(exitMenuItem);
        JMenu supportMenu = new JMenu("Help");
        JMenuItem onlineHelpItem = new JMenuItem("Online help");
        JMenuItem offlineHelpItem = new JMenuItem("Offline help");
        JMenu mainTasksMenu = new JMenu("Задачи");
        JMenu requirementTaskMenu = new JMenu("Выяснить какие требования клиентов чаще всего выполняются");
        JMenu reportsMenu = new JMenu("Отчеты");
        JMenuItem findIfCrossplatformingActualItem = new JMenuItem("Определить требуется ли программная кросплатформенность");
        findIfCrossplatformingActualItem.addActionListener(new FindIfCrossplatformingActualItemListener());
        JMenuItem findHowMuchPopularEachLangItem = new JMenuItem("Понять насколько популярен каждый ЯП");
        findHowMuchPopularEachLangItem.addActionListener(new FindPopularityOfEachEachLangItemListener());
        JMenuItem findWhatMethodologySuccesfulProjectsUseItem = new JMenuItem("Узнать какие методология используется в самых успешных проектах");
        findWhatMethodologySuccesfulProjectsUseItem.addActionListener(new FindWhatMethodologySuccesfulProjectsUseItemListener());
        JMenuItem findWhatRequirementsAreDoneOnTime = new JMenuItem("в срок");
        findWhatRequirementsAreDoneOnTime.addActionListener(new FindWhatRequirementsAreDoneOnTimeListener());
        JMenuItem findWhatRequirementsAreDoneAtWrongTime = new JMenuItem("с опозданием");
        findWhatRequirementsAreDoneAtWrongTime.addActionListener(new FindWhatRequirementsAreDoneAtWrongTimeListener());

        JMenuItem findIfCrossplatformingActualReportItem =
                new JMenuItem("Отчет по задаче \"Определить требуется ли программная кросплатформенность\"");
        findIfCrossplatformingActualReportItem.addActionListener(new FindIfCrossplatformingActualReportItemListener());
        JMenuItem findHowMuchPopularEachLangReportItem =
                new JMenuItem("Отчет по задаче \"Понять насколько популярен каждый ЯП\"");
        findHowMuchPopularEachLangReportItem.addActionListener(new findHowMuchPopularEachLangReportItemListener());
        JMenuItem findWhatMethodologySuccesfulProjectsUseReportItem =
                new JMenuItem("Отчет по задаче \"Узнать какие методология используется в самых успешных проектах\"");
        findWhatMethodologySuccesfulProjectsUseReportItem.addActionListener(new findWhatMethodologySuccessfulProjectsUseReportItemListener());
        JMenuItem findWhatRequirementsAreDoneOnTimeReportItem =
                new JMenuItem("Отчет по задаче \"Выяснить какие требования клиентов чаще всего выполняются в срок\"");
        findWhatRequirementsAreDoneOnTimeReportItem.addActionListener(new findWhatRequirementsAreDoneOnTimeReportItemListener());
        JMenuItem findWhatRequirementsAreDoneAtWrongTimeReportItem =
                new JMenuItem("Отчет по задаче \"Выяснить какие требования клиентов чаще всего выполняются с опозданием\"");
        findWhatRequirementsAreDoneAtWrongTimeReportItem.addActionListener(new findWhatRequirementsAreDoneAtWrongTimeReportItemListener());

        requirementTaskMenu.add(findWhatRequirementsAreDoneOnTime);
        requirementTaskMenu.add(findWhatRequirementsAreDoneAtWrongTime);
        mainTasksMenu.add(findIfCrossplatformingActualItem);
        mainTasksMenu.add(findHowMuchPopularEachLangItem);
        mainTasksMenu.add(findWhatMethodologySuccesfulProjectsUseItem);
        mainTasksMenu.add(requirementTaskMenu);
        reportsMenu.add(findIfCrossplatformingActualReportItem);
        reportsMenu.add(findHowMuchPopularEachLangReportItem);
        reportsMenu.add(findWhatMethodologySuccesfulProjectsUseReportItem);
        reportsMenu.add(findWhatRequirementsAreDoneOnTimeReportItem);
        reportsMenu.add(findWhatRequirementsAreDoneAtWrongTimeReportItem);
        JToggleButton tableViewButton = new JToggleButton("Table view");
        tableViewButton.addActionListener(new TableViewButtonListener());
        recordViewButton = new JToggleButton("Record view");
        recordViewButton.addActionListener(new RecordViewButtonListener());
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(tableViewButton);
        buttonGroup.add(recordViewButton);
        JPanel tablesListPanel = new JPanel();
        tablesListPanel.add(tablesList);
        tablesListPanel.add(tableViewButton);
        tablesListPanel.add(recordViewButton);
        supportMenu.add(offlineHelpItem);
        supportMenu.add(onlineHelpItem);
        menu.add(fileMenu);
        menu.add(mainTasksMenu);
        menu.add(reportsMenu);
        menu.add(supportMenu);
        northPanel.add(tablesListPanel, BorderLayout.CENTER);
        tablesList.addActionListener(new MyListListener());

        dataWorkPanel = new JPanel(new CardLayout());

        JPanel tablesPanel = new JPanel(new BorderLayout());
        tablesPanel.add(northPanel, BorderLayout.NORTH);
        tablesPanel.add(dataWorkPanel, BorderLayout.CENTER);

        JPanel queriesPanel = new JPanel();
        queriesPanel.setLayout(new BoxLayout(queriesPanel, BoxLayout.Y_AXIS));
        JButton theMostPopularOsButton = new JButton("Наиболее популярные ОС");
        theMostPopularOsButton.addActionListener(new TheMostPopularOsButtonListener());

        JButton finishedProjectsButton = new JButton("Законченные проекты");
        finishedProjectsButton.addActionListener(new FinishedProjectsButtonListener());

        JButton percentOfPerformationEachRequirementButton = new JButton("Процент выполняемости каждого требования в законченных проектах");
        percentOfPerformationEachRequirementButton.addActionListener(new PercentOfPerformationEachRequirementButtonListener());

        JButton programNamesAndWhatNumberOsTheyUseButton = new JButton("Все программы и количество ОС, для которых они написаны");
        programNamesAndWhatNumberOsTheyUseButton.addActionListener(new ProgramNamesAndWhatNumberOsTheyUseButtonListener());

        JButton whatNumberOfOsUseProgramButton = new JButton("Количество ОС, на которых написана определенная команда");
        whatNumberOfOsUseProgramButton.addActionListener(new WhatNumberOsUseProgramButtonListener());

        JButton percentRatioNumberOfUsingOsInProgramButton = new JButton("Процент количества используемых ОС в программах");
        percentRatioNumberOfUsingOsInProgramButton.addActionListener(new PercentRatioNumberOfUsingOsInProgramButtonListener());

        JButton percentOfProgramsThatNeedCrossplatformingButton = new JButton("Процент программ, требующих кроссплатформенность");
        percentOfProgramsThatNeedCrossplatformingButton.addActionListener(new PercentOfProgramsThatNeedCrossplatformingButtonListener());

        JButton programsOfObjectWithTheHighestBudgetButton = new JButton("Программы проекта с наибольшим бюджетом");
        programsOfObjectWithTheHighestBudgetButton.addActionListener(new ProgramsOfObjectWithTheHighestBudgetButtonListener());

        JButton programsOfObjectWithTheLowestBudgetButton = new JButton("Программы проекта с наименьшим бюджетом");
        programsOfObjectWithTheLowestBudgetButton.addActionListener(new ProgramsOfObjectWithTheLowestBudgetButtonListener());

        JButton projectWithTheHighestBudgetButton = new JButton("Проект с наибольшим бюджетом");
        projectWithTheHighestBudgetButton.addActionListener(new ProjectWithTheHighestBudgetButtonListener());

        JButton projectWithTheLowestBudgetButton = new JButton("Проект с наименьшим бюджетом");
        projectWithTheLowestBudgetButton.addActionListener(new ProjectWithTheLowestBudgetButtonListener());

        JButton whatProgLanguagesOfProgramButton = new JButton("Узнать на каком языке пишется определенная программа");
        whatProgLanguagesOfProgramButton.addActionListener(new WhatProgLanguagesOfProgramButtonListener());

        JButton whatProgLangUsedWithOsAsTheMostPopularButton =
                new JButton("Определить какие языки программирования используются с определенной ОС, как с самой популярной");
        whatProgLangUsedWithOsAsTheMostPopularButton.addActionListener(new WhatProgLangUsedWithOsAsTheMostPopularButtonListener());

        JButton atFinishedProjectsNumberOfRequirementAtAllAndPerformedButton =
                new JButton("Отобразить у завершенных проектов общее количество требований и количество выполненных");
        atFinishedProjectsNumberOfRequirementAtAllAndPerformedButton
                .addActionListener(new AtFinishedProjectsNumberOfRequirementAtAllAndPerformedButtonListener());

        JButton atProjectNumberOfRequirementAtAllAndPerformedButton =
                new JButton("Отобразить у определенного проекта общее количество требований и количество выполненных");
        atProjectNumberOfRequirementAtAllAndPerformedButton
                .addActionListener(new AtProjectNumberOfRequirementAtAllAndPerformedButtonListener());

        JButton finishedProjectWithPercentRatioOfRequirementsPerformedButton =
                new JButton("Отобразить законченные проекты с заданным процентным соотношением выполненных требований");
        finishedProjectWithPercentRatioOfRequirementsPerformedButton
                .addActionListener(new FinishedProjectWithPercentRatioOfRequirementsPerformedButtonListener());

        queriesPanel.add(theMostPopularOsButton);
        queriesPanel.add(finishedProjectsButton);
        queriesPanel.add(percentOfPerformationEachRequirementButton);
        queriesPanel.add(programNamesAndWhatNumberOsTheyUseButton);
        queriesPanel.add(whatNumberOfOsUseProgramButton);
        queriesPanel.add(percentRatioNumberOfUsingOsInProgramButton);
        queriesPanel.add(percentOfProgramsThatNeedCrossplatformingButton);
        queriesPanel.add(programsOfObjectWithTheHighestBudgetButton);
        queriesPanel.add(programsOfObjectWithTheLowestBudgetButton);
        queriesPanel.add(projectWithTheHighestBudgetButton);
        queriesPanel.add(projectWithTheLowestBudgetButton);
        queriesPanel.add(whatProgLanguagesOfProgramButton);
        queriesPanel.add(whatProgLangUsedWithOsAsTheMostPopularButton);
        queriesPanel.add(atFinishedProjectsNumberOfRequirementAtAllAndPerformedButton);
        queriesPanel.add(atProjectNumberOfRequirementAtAllAndPerformedButton);
        queriesPanel.add(finishedProjectWithPercentRatioOfRequirementsPerformedButton);

        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Таблицы", tablesPanel);
        jTabbedPane.addTab("Запросы", queriesPanel);


        mainPanel.add(menu, BorderLayout.NORTH);
        mainPanel.add(jTabbedPane, BorderLayout.CENTER);

        loadFirstScreen();
        loadRecordsMenu();
        loadTableMenu();


        return mainPanel;
    }

    private void loadFirstScreen() {
        dataWorkPanel.add(new JPanel(), "DEFAULT");
    }

    private void loadRecordsMenu() {
        recordPanel = new JPanel(new BorderLayout());

        recordRowTable = new JTable();

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setRowCount(1);
        recordRowTable.setModel(defaultTableModel);
        recordPane = new JScrollPane(recordRowTable);

        JPanel recordOperationsPanel = new JPanel();
        firstRecordButton = new JButton("Go to first record");
        firstRecordButton.addActionListener(new FirstRecordButtonListener());
        previousRecordButton = new JButton("Go to previous record");
        previousRecordButton.addActionListener(new PreviousRecordButtonListener());
        currentPointerNumberTF = new HintTextField("");
        currentPointerNumberTF.addActionListener(new CurrentPointerNumberTFListener());
        deleteRecordButton = new JButton("Delete record");
        deleteRecordButton.addActionListener(new DeleteRecordButtonListener());
        updateRecordButton = new JButton("Update record");
        updateRecordButton.addActionListener(new UpdateRecordButtonListener());
        createRecordButton = new JButton("Create record");
        createRecordButton.addActionListener(new CreateRecordButtonListener());
        nextRecordButton = new JButton("Go to next record");
        nextRecordButton.addActionListener(new NextRecordButtonListener());
        lastRecordButton = new JButton("Go to last record");
        lastRecordButton.addActionListener(new LastRecordButtonListener());

        recordOperationsPanel.add(firstRecordButton);
        recordOperationsPanel.add(previousRecordButton);
        recordOperationsPanel.add(currentPointerNumberTF);
        if (!userPrivilige.equals(USER_PRIVILIGE)) {
            recordOperationsPanel.add(deleteRecordButton);
            recordOperationsPanel.add(updateRecordButton);
            recordOperationsPanel.add(createRecordButton);
        }
        recordOperationsPanel.add(nextRecordButton);
        recordOperationsPanel.add(lastRecordButton);
        recordPanel.add(recordPane, BorderLayout.CENTER);
        recordPanel.add(recordOperationsPanel, BorderLayout.SOUTH);

        dataWorkPanel.add(recordPanel, RECORDS_PANEL);
    }

    private void showRecordMenu() {
        CardLayout cardLayout = (CardLayout) (dataWorkPanel.getLayout());
        cardLayout.show(dataWorkPanel, RECORDS_PANEL);
    }

    private void loadTableMenu() {
        tablePanel = new JPanel(new BorderLayout());

        dataTable = new JTable();
        JScrollPane tablePane = new JScrollPane(dataTable);

        tableOperationsPanel = new JPanel(new CardLayout());
        loadProgramsPanel();

        tablePanel.add(tablePane, BorderLayout.CENTER);
        //tablePanel.add(tableOperationsPanel, BorderLayout.EAST);

        dataWorkPanel.add(tablePanel, TABLE_PANEL);
    }

    private void loadProgramsPanel() {
        JPanel programsPanel = new JPanel();
        JButton addProgramBtn = new JButton("Add program");
        JButton deleteProgramBtn = new JButton("Delete program");
        programsPanel.add(addProgramBtn);
        programsPanel.add(deleteProgramBtn);

        tableOperationsPanel.add(programsPanel, PROGRAMS_PANEL);
    }

    private void showTableMenu() {
        CardLayout cardLayout = (CardLayout) (dataWorkPanel.getLayout());
        cardLayout.show(dataWorkPanel, TABLE_PANEL);
    }

    class MyListListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resetAllButtons();

            String selectedItem = (String) tablesList.getSelectedItem();
            CardLayout cardLayout = (CardLayout) (tableOperationsPanel.getLayout());
            cardLayout.show(tableOperationsPanel, selectedItem);

            DefaultTableModel defaultTableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            Object[][] data = null;
            String[] headers = null;

            if (selectedItem.equals("Program")) {
                data = dbOperations.getPrograms();
                headers = new String[]{"Название", "Проект", "Предназначение"};
                setRowRecordPanel(headers, "Program");
            } else if (selectedItem.equals("Version")) {
                data = dbOperations.getVersion();
                headers = new String[]{"Дата выпуска", "Номер", "Программа"};
                setRowRecordPanel(headers, "Version");
            } else if (selectedItem.equals("Edition")) {
                data = dbOperations.getEditions();
                headers = new String[]{"Название", "Особенности"};
                setRowRecordPanel(headers, "Edition");
            } else if (selectedItem.equals("OS edition")) {
                data = dbOperations.getOsEdition();
                headers = new String[]{"Операционная система", "Издание операционная система"};
                setRowRecordPanel(headers, "OS edition");
            } else if (selectedItem.equals("Client")) {
                data = dbOperations.getClient();
                headers = new String[]{"Название фирмы", "Оплата"};
                setRowRecordPanel(headers, "Client");
            } else if (selectedItem.equals("Team")) {
                data = dbOperations.getTeam();
                headers = new String[]{"Название", "Глава команды", "Компания"};
                setRowRecordPanel(headers, "Team");
            } else if (selectedItem.equals("Company")) {
                data = dbOperations.getCompanies();
                headers = new String[]{"id", "Название", "Полный адрес", "Штат", "Ежегодный доход"};
                setRowRecordPanel(headers, "Company");
            } else if (selectedItem.equals("Programming methodology")) {
                data = dbOperations.getProgMethodology();
                headers = new String[]{"Название", "Количество человек в команде", "Особенности"};
                setRowRecordPanel(headers, "Programming methodology");
            } else if (selectedItem.equals("OS")) {
                data = dbOperations.getOs();
                headers = new String[]{"Дата выпуска", "Название", "Предназначение", "Разрядность", "Тип интерфейса", "Тип задачности"};
                setRowRecordPanel(headers, "OS");
            } else if (selectedItem.equals("Program-OS")) {
                data = dbOperations.getProgramOs();
                headers = new String[]{"Программа", "Операционная система"};
                setRowRecordPanel(headers, "Program-OS");
            } else if (selectedItem.equals("Programming")) {
                data = dbOperations.getProgramming();
                headers = new String[]{"Программный инженер", "Его программа"};
                setRowRecordPanel(headers, "Programming");
            } else if (selectedItem.equals("Software Engineer")) {
                data = dbOperations.getSoftwareEngineer();
                headers = new String[]{"ФИО", "Принадлежность команде", "Звание"};
                setRowRecordPanel(headers, "Software Engineer");
            } else if (selectedItem.equals("Project")) {
                data = dbOperations.getProject();
                headers = new String[]{"Название", "Команда", "Клиент", "Методология", "Бюджет", "Закончен"};
                setRowRecordPanel(headers, "Project");
            } else if (selectedItem.equals("Project - Requirement")) {
                data = dbOperations.getProjectReq();
                headers = new String[]{"Проект", "Требование", "В срок"};
                setRowRecordPanel(headers, "Project - Requirement");
            } else if (selectedItem.equals("Implementation")) {
                data = dbOperations.getImpl();
                headers = new String[]{"Программа", "Пишется на языке программирования"};
                setRowRecordPanel(headers, "Implementation");
            } else if (selectedItem.equals("Requirement")) {
                data = dbOperations.getReq();
                headers = new String[]{"id", "Требование"};
                setRowRecordPanel(headers, "Requirement");
            } else if (selectedItem.equals("Programming Language")) {
                data = dbOperations.getProgLang();
                headers = new String[]{"Название", "Предназначение", "Версия", "Наиболее частая ОС"};
                setRowRecordPanel(headers, "Programming Language");
            }

            dataTableColumnNumber = headers.length;
            defaultTableModel.setDataVector(data, headers);

            dataTable.setModel(defaultTableModel);
            System.out.println(dataTable.getRowCount());

            firstRecordButton.doClick();
        }
    }

    private void resetAllButtons() {
        firstRecordButton.setEnabled(true);
        previousRecordButton.setEnabled(true);
        updateRecordButton.setEnabled(true);
        deleteRecordButton.setEnabled(true);
        createRecordButton.setEnabled(true);
        nextRecordButton.setEnabled(true);
        lastRecordButton.setEnabled(true);

    }

    private void setRowRecordPanel(Object[] headers, String tableName) {
        setRecordRowOperationsDefaults();

        setRecordRowTableDefaults(headers, tableName);
    }

    private void setRecordRowTableDefaults(Object[] headers, String tableName) {
        DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
        recordRowTableModel.setDataVector(null, headers);
        //recordRowTableModel.addRow(getRowFromDataTableAtPointer());

        setComboBoxes(tableName);
    }

    private void setComboBoxes(String tableName) {
        TableColumn checkBoxColumn = null;
        JComboBox<String> comboBox = null;
        if (tableName.equals("Program")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getProjectNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Version")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(2);
            comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("OS edition")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
            comboBox = new JComboBox(dbOperations.getOsNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getEditionNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Team")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(2);
            comboBox = new JComboBox(dbOperations.getCompanyIdList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Program-OS")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
            comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getOsNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Programming")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
            comboBox = new JComboBox(dbOperations.getSEInitialsList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Software Engineer")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getTeamNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Project")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getTeamNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Project - Requirement")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
            comboBox = new JComboBox(dbOperations.getProjectNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getReqIdList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Implementation")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
            comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

            checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
            comboBox = new JComboBox(dbOperations.getProgLangNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        } else if (tableName.equals("Programming Language")) {
            checkBoxColumn = recordRowTable.getColumnModel().getColumn(3);
            comboBox = new JComboBox(dbOperations.getOsNameList().toArray());
            checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
        }


    }

    private void setRecordRowOperationsDefaults() {
        showRecordMenu();

        rowPointer = 0;
        previousRecordButton.setEnabled(false);
        createRecordButton.setEnabled(false);
        currentPointerNumberTF.setText("1 from " + dataTable.getRowCount(
        ));
        recordRowTable.repaint();
    }

    boolean newRow;

    private Object[] getRowFromDataTableAtPointer() {
        newRow = false;
        if (rowPointer >= dataTable.getRowCount()) { // new row
            newRow = true;
            updateRecordRowOperationsPanel();
            return createEmptyRow();
        }

        Object[] row = new String[dataTableColumnNumber];
        for (int i = 0; i < dataTableColumnNumber; i++) {
            row[i] = dataTable.getValueAt(rowPointer, i);
        }

        return row;
    }

    private Object[] createEmptyRow() {
        Object[] row = new String[dataTableColumnNumber];
        for (int i = 0; i < dataTableColumnNumber; i++) {
            row[i] = "";
        }
        return row;
    }

    private void clearRecordRowTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) recordRowTable.getModel();
        defaultTableModel.setRowCount(0);
    }

    class TableViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showTableMenu();
        }
    }

    class RecordViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showRecordMenu();
        }
    }

    private void checkRecordButtons() {
        //System.out.println("ROW POINTER = " + rowPointer);
        //System.out.println("REGIM NEW ROW  ? " + newRow);
        if (dataTable.getModel().getRowCount() == 0) {
            previousRecordButton.setEnabled(false);
            firstRecordButton.setEnabled(false);
            if (newRow) {
                nextRecordButton.setEnabled(false);
                lastRecordButton.setEnabled(false);
                createRecordButton.setEnabled(true);
            } else {
                nextRecordButton.setEnabled(true);
                lastRecordButton.setEnabled(false);
                createRecordButton.setEnabled(false);
            }
            deleteRecordButton.setEnabled(false);
            updateRecordButton.setEnabled(false);
        } else {
            if (rowPointer == 0) {
                firstRecordButton.setEnabled(false);
                previousRecordButton.setEnabled(false);
                nextRecordButton.setEnabled(true);
                lastRecordButton.setEnabled(true);
                createRecordButton.setEnabled(false);
                updateRecordButton.setEnabled(true);
                deleteRecordButton.setEnabled(true);
            } else if (0 < rowPointer && rowPointer < dataTable.getModel().getRowCount()) {
//                if (dataTable.getRowCount() != 0) {
//                    previousRecordButton.setEnabled(true);
//            }
//                else {
//                    previousRecordButton.setEnabled(false);
//                }
                firstRecordButton.setEnabled(true);
                previousRecordButton.setEnabled(true);
                nextRecordButton.setEnabled(true);
                lastRecordButton.setEnabled(true);
                createRecordButton.setEnabled(false);
                updateRecordButton.setEnabled(true);
                deleteRecordButton.setEnabled(true);
            } else {
                nextRecordButton.setEnabled(false);
                lastRecordButton.setEnabled(false);
                firstRecordButton.setEnabled(true);
                previousRecordButton.setEnabled(true);
                createRecordButton.setEnabled(true);
                updateRecordButton.setEnabled(false);
                deleteRecordButton.setEnabled(false);
            }
        }
    }

    private void updateRecordRowOperationsPanel() {
        setField();

        checkRecordButtons();
    }

    private void setField() {
        if (dataTable.getRowCount() != 0) {
            currentPointerNumberTF.setText((rowPointer + 1) + " from " + dataTable.getRowCount());
        } else {
            currentPointerNumberTF.setText("1 from 1");
        }
    }

    // private void performOperations(String operation){
    ///
    //}


    class FirstRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearRecordRowTable();

            DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
            int indexFirstDataTableRow = 0;
            rowPointer = indexFirstDataTableRow;

            recordRowTableModel.addRow(getRowFromDataTableAtPointer());
            recordRowTableModel.fireTableDataChanged();
            updateRecordRowOperationsPanel();
        }
    }

    class PreviousRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (previousRecordButton.isEnabled()) {
                rowPointer--;
                updateRecordRowOperationsPanel();

                clearRecordRowTable();
                DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
                recordRowTableModel.addRow(getRowFromDataTableAtPointer());
            }
        }
    }

    class DeleteRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteRecord();
        }
    }

    private void deleteRecord() {
        if (recordRowTable.isEditing()) {
            recordRowTable.getCellEditor().stopCellEditing();
        }

        String selectedItem = (String) tablesList.getSelectedItem();
        String message = null;
        if (selectedItem.equals("Program")) {
            String programName = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteProgram(programName);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Version")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            java.util.Date parsed = null;
            try {
                parsed = format.parse((String) dataTable.getModel().getValueAt(rowPointer, 0));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date releaseDate = new java.sql.Date(parsed.getTime());
            //message = dbOperations.deleteVersion(releaseDate);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Edition")) {
            String name = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            // message = dbOperations.deleteEdition(name);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("OS edition")) {
            String os = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            String edition = (String) dataTable.getModel().getValueAt(rowPointer, 1);
            //message = dbOperations.deleteOsEdition(os, edition);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Client")) {
            String firmName = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteClient(firmName);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Team")) {
            String name = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteTeam(name);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Company")) {
            int id = Integer.parseInt((String) dataTable.getModel().getValueAt(rowPointer, 0));
            //message = dbOperations.deleteCompany(id);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Programming methodology")) {
            String name = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteProgMethodology(name);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("OS")) {
            String name = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteOs(name);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Program-OS")) {
            String program = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            String os = (String) dataTable.getModel().getValueAt(rowPointer, 1);
            //message = dbOperations.deleteProgramOs(program, os);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Programming")) {
            String initials = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            String program = (String) dataTable.getModel().getValueAt(rowPointer, 1);
            //message = dbOperations.deleteProgramming(initials, program);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Software Engineer")) {
            String initials = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteSoftwareEngineer(initials);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Project")) {
            String name = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            //message = dbOperations.deleteProject(name);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Project - Requirement")) {
            String project = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            int req = Integer.parseInt((String) dataTable.getModel().getValueAt(rowPointer, 1));
            //message = dbOperations.deleteProjectReq(project, req);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Implementation")) {
            String program = (String) dataTable.getModel().getValueAt(rowPointer, 0);
            String language = (String) dataTable.getModel().getValueAt(rowPointer, 1);
            //message = dbOperations.deleteImpl(program, language);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Requirement")) {
            System.out.println(dataTable.getModel().getValueAt(rowPointer, 0));
            int id = Integer.parseInt((String) dataTable.getModel().getValueAt(rowPointer, 0));
            //message = dbOperations.deleteReq(id);
            message = DBOperations.NO_ERROR_MESSAGE;
        } else if (selectedItem.equals("Programming Language")) {
            String name = (String) dataTable.getModel().getValueAt(rowPointer, 0);
//            message = dbOperations.deleteProgLang(name);
            message = DBOperations.NO_ERROR_MESSAGE;
        }


        if (message.equals(DBOperations.NO_ERROR_MESSAGE)) {
            DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
            dataTableModel.removeRow(rowPointer);
        } else {
            JOptionPane.showMessageDialog(currentPointerNumberTF,
                    message, "Ошибка при удалении из БД",
                    JOptionPane.ERROR_MESSAGE);
        }

        clearRecordRowTable();
        DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
        if (rowPointer < dataTable.getRowCount() - 1) {
            recordRowTableModel.addRow(getRowFromDataTableAtPointer());
        } else {
            if (rowPointer != 0) {
                rowPointer--;
            }

            if (dataTable.getRowCount() != 0) {
                recordRowTableModel.addRow(getRowFromDataTableAtPointer());
            } else {
                JOptionPane.showMessageDialog(currentPointerNumberTF,
                        "Таблица пуста", "Ошибка при удалении из БД",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        updateRecordRowOperationsPanel();
    }

    class UpdateRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateRecord();
        }
    }

    private void updateRecord() {
        ArrayList<Object> list = collectDataFromRecordField();

        String selectedItem = (String) tablesList.getSelectedItem();
        String message = null;
        if (selectedItem.equals("Program")) {
            message = dbOperations.updateProgram(new Program(list));
        } else if (selectedItem.equals("Version")) {
            message = dbOperations.updateVersion(new Version(list));
        } else if (selectedItem.equals("Edition")) {
            message = dbOperations.updateEdition(new Edition(list));
        } else if (selectedItem.equals("OS edition")) {
            message = dbOperations.updateOsEdition(new OsEdition(list));
        } else if (selectedItem.equals("Client")) {
            message = dbOperations.updateClient(new Client(list));
        } else if (selectedItem.equals("Team")) {
            message = dbOperations.updateTeam(new Team(list));
        } else if (selectedItem.equals("Company")) {
            message = dbOperations.updateCompany(new Company(list));
        } else if (selectedItem.equals("Programming methodology")) {
            message = dbOperations.updateProgMethodology(new ProgrammingMethodology(list));
        } else if (selectedItem.equals("OS")) {
            message = dbOperations.updateOs(new Os(list));
        } else if (selectedItem.equals("Program-OS")) {
            message = dbOperations.updateProgramOs(new ProgramOs(list));
        } else if (selectedItem.equals("Programming")) {
            message = dbOperations.updateProgramming(new Programming(list));
        } else if (selectedItem.equals("Software Engineer")) {
            message = dbOperations.updateSoftwareEngineer(new SoftwareEngineer(list));
        } else if (selectedItem.equals("Project")) {
            message = dbOperations.updateProject(new Project(list));
        } else if (selectedItem.equals("Project - Requirement")) {
            message = dbOperations.updateProjectReq(new ProjectReq(list));
        } else if (selectedItem.equals("Implementation")) {
            message = dbOperations.updateImpl(new Implementation(list));
        } else if (selectedItem.equals("Requirement")) {
            message = dbOperations.updateReq(new Req(list));
        } else if (selectedItem.equals("Programming Language")) {
            message = dbOperations.updateProgLang(new ProgLang(list));
        }


        if (message.equals(DBOperations.NO_ERROR_MESSAGE)) {
            DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
            for (int i = 0; i < list.size(); i++) {
                dataTableModel.setValueAt(list.get(i), rowPointer, i);
            }
        } else {
            JOptionPane.showMessageDialog(currentPointerNumberTF,
                    message, "Ошибка при обновлении в БД",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    class CreateRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createRecord();
            updateRecordRowOperationsPanel();
        }
    }

    private void createRecord() {
        ArrayList<Object> list = collectDataFromRecordField();

        String selectedItem = (String) tablesList.getSelectedItem();
        String message = null;
        if (selectedItem.equals("Program")) {
            message = dbOperations.addProgram(new Program(list));
        } else if (selectedItem.equals("Version")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            java.util.Date parsed = null;
            try {
                String stringDate = (String) list.get(0);
                //stringDate = stringDate.replace("", "");
                parsed = format.parse(stringDate);
                list.set(0, stringDate);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(currentPointerNumberTF,
                        "Правильный формат даты: YYYYMMDD", "Неправильный формат даты",
                        JOptionPane.ERROR_MESSAGE);
            }
            message = dbOperations.addVersion(new Version(list));
        } else if (selectedItem.equals("Edition")) {
            message = dbOperations.addEdition(new Edition(list));
        } else if (selectedItem.equals("OS edition")) {
            message = dbOperations.addOsEdition(new OsEdition(list));
        } else if (selectedItem.equals("Client")) {
            message = dbOperations.addClient(new Client(list));
        } else if (selectedItem.equals("Team")) {
            message = dbOperations.addTeam(new Team(list));
        } else if (selectedItem.equals("Company")) {
            message = dbOperations.addCompany(new Company(list));
        } else if (selectedItem.equals("Programming methodology")) {
            message = dbOperations.addProgMethodology(new ProgrammingMethodology(list));
        } else if (selectedItem.equals("OS")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            java.util.Date parsed = null;
            try {
                String stringDate = (String) list.get(0);
                //stringDate = stringDate.replace("", "");
                parsed = format.parse(stringDate);
                list.set(0, stringDate);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(currentPointerNumberTF,
                        "Правильный формат даты: YYYYMMDD", "Неправильный формат даты",
                        JOptionPane.ERROR_MESSAGE);
            }
            message = dbOperations.addOs(new Os(list));
        } else if (selectedItem.equals("Program-OS")) {
            message = dbOperations.addProgramOs(new ProgramOs(list));
        } else if (selectedItem.equals("Programming")) {
            message = dbOperations.addProgramming(new Programming(list));
        } else if (selectedItem.equals("Software Engineer")) {
            message = dbOperations.addSoftwareEnginner(new SoftwareEngineer(list));
        } else if (selectedItem.equals("Project")) {
            message = dbOperations.addProject(new Project(list));
        } else if (selectedItem.equals("Project - Requirement")) {
            message = dbOperations.addProjectReq(new ProjectReq(list));
        } else if (selectedItem.equals("Implementation")) {
            message = dbOperations.addImpl(new Implementation(list));
        } else if (selectedItem.equals("Requirement")) {
            message = dbOperations.addReq(new Req(list));
        } else if (selectedItem.equals("Programming Language")) {
            message = dbOperations.addProgLang(new ProgLang(list));
        }

        if (message.equals(DBOperations.NO_ERROR_MESSAGE)) {
            DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
            dataTableModel.addRow(list.toArray());
        } else {
            JOptionPane.showMessageDialog(currentPointerNumberTF,
                    message, "Ошибка при добавлении в БД",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private ArrayList collectDataFromRecordField() {
        if (recordRowTable.isEditing()) {
            recordRowTable.getCellEditor().stopCellEditing();
        }

        DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();

        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < recordRowTableModel.getColumnCount(); i++) {
            String value = (String) recordRowTableModel.getValueAt(0, i);
            if (value != null) {
                list.add((value));
            } else {
                list.add("");
            }
        }

        return list;
    }

    class NextRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nextRecordButton.isEnabled()) {
                if (dataTable.getRowCount() != 0) {
                    rowPointer++;
                }
                updateRecordRowOperationsPanel();

                clearRecordRowTable();
                DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
                recordRowTableModel.addRow(getRowFromDataTableAtPointer());
            }
        }
    }

    class LastRecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearRecordRowTable();

            DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
            int indexLastDataTableRow = dataTable.getModel().getRowCount() - 1;
            rowPointer = indexLastDataTableRow;
            updateRecordRowOperationsPanel();

            recordRowTableModel.addRow(getRowFromDataTableAtPointer());
        }
    }

    class CurrentPointerNumberTFListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentPointerNumberTF.setFocus();
            int indexToGo = Integer.parseInt(currentPointerNumberTF.getText()) - 1;

            if (0 <= indexToGo && indexToGo < dataTable.getRowCount()) {
                rowPointer = indexToGo;
                updateRecordRowOperationsPanel();

                clearRecordRowTable();
                DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
                recordRowTableModel.addRow(getRowFromDataTableAtPointer());
            } else {
                JOptionPane.showMessageDialog(currentPointerNumberTF,
                        "Возможно, достигнут конец набора записей", "Невозможен переход к указанной записи",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class ExitMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DatabaseBrowser.loadMenu(new SignInForm().getGuiPanel());
        }
    }

    class FindIfCrossplatformingActualItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainTasks.findIfCrossplatformingActual(currentPointerNumberTF);
        }
    }

    class FindPopularityOfEachEachLangItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JasperReportFrame.showPopularityOfEachLangTaskReport();
            mainTasks.findPopularityOfEachEachLang(currentPointerNumberTF);
        }
    }

    class FindWhatMethodologySuccesfulProjectsUseItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainTasks.findWhatMethodologySuccesfulProjectsUse(currentPointerNumberTF);
        }
    }

    class FindWhatRequirementsAreDoneOnTimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainTasks.findWhatRequirementsAreDoneOnTime(currentPointerNumberTF);
        }
    }

    class FindWhatRequirementsAreDoneAtWrongTimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainTasks.findWhatRequirementsAreDoneAtWrongTime(currentPointerNumberTF);
        }
    }

    private class findHowMuchPopularEachLangReportItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JasperReportFrame.showPopularityOfEachLangTaskReport();
        }
    }

    private class findWhatMethodologySuccessfulProjectsUseReportItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JasperReportFrame.showTheMostSuccessfulProjectsMethodologyReport();
        }
    }

    private class findWhatRequirementsAreDoneOnTimeReportItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JasperReportFrame.showRequirementsOnTimeTaskReport();
        }
    }

    private class findWhatRequirementsAreDoneAtWrongTimeReportItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JasperReportFrame.showRequirementsAtWrongTimeTaskReport();
        }
    }

    private class FindIfCrossplatformingActualReportItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JasperReportFrame.showPopularityOfEachOsReport();
        }
    }

    /*
    ******************************QUERIES************************************************
     */

    private class TheMostPopularOsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getOsFrequencyOfUse();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст. Скорее всего нет связей между операционной системой и программой");
            }
            String[] fields = {"Операционная система", "Количество использования"};
            new Dialog(data, fields);
        }
    }

    private class FinishedProjectsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getFinishedProjects();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"Название", "Команда", "Клиент", "Методология", "Бюджет"};
            new Dialog(data, fields);
        }
    }

    private class PercentOfPerformationEachRequirementButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getPercentOfPerformationEachRequirementInFinishedProjects();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"Требование", "Процент выполняемости в законченных проектах"};
            new Dialog(data, fields);
        }
    }

    private class ProgramNamesAndWhatNumberOsTheyUseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getProgramNamesAndWhatNumberOsTheyUse();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"Программа", "Количество ОС"};
            new Dialog(data, fields);
        }
    }

    private class WhatNumberOsUseProgramButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String program = JOptionPane.showInputDialog(null, "Введите название программы");
            if (program != null) {
                Object[][] data = dbOperations.getWhatNumberOsUseProgram(program);
                if (data.length == 0) {
                    JOptionPane.showMessageDialog(null, "Список данных пуст.");
                }
                String[] fields = {"Программа", "Количество ОС"};
                new Dialog(data, fields);
            }
        }
    }

    private class PercentRatioNumberOfUsingOsInProgramButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getPercentRatioNumberOfUsingOsInPrograms();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"Количество ОС", "Процентное соотношение количества ОС"};
            new Dialog(data, fields);
        }
    }

    private class PercentOfProgramsThatNeedCrossplatformingButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int percentOfUsageOfTwoOrMoreOs = dbOperations.getPercentOfProgramThatUseTwoOrMoreOs();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null,
                            "Программм требующих кросплатформенность(используют 2 или более ОС) -  " +
                                    "" + percentOfUsageOfTwoOrMoreOs + "% программ", "Результат",
                            JOptionPane.PLAIN_MESSAGE);
                }
            });
            t.start();
        }
    }


    private class ProgramsOfObjectWithTheHighestBudgetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getProgramsOfProjectWithTheHighestBudget();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"название", "бюджет"};
            new Dialog(data, fields);
        }
    }

    private class ProgramsOfObjectWithTheLowestBudgetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getProgramsOfProjectWithTheLowestBudget();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"название", "бюджет"};
            new Dialog(data, fields);
        }
    }

    private class ProjectWithTheHighestBudgetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String project = dbOperations.getProjectWithTheHighestBudget();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null,
                            "Проект с наибольшим бюджетом: " + project, "Результат",
                            JOptionPane.PLAIN_MESSAGE);
                }
            });
            t.start();
        }
    }

    private class ProjectWithTheLowestBudgetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String project = dbOperations.getProjectWithTheLowestBudget();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null,
                            "Проект с наименьшим бюджетом: " + project, "Результат",
                            JOptionPane.PLAIN_MESSAGE);
                }
            });
            t.start();
        }
    }

    private class WhatProgLanguagesOfProgramButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String program = JOptionPane.showInputDialog(null, "Введите название программы");
            if (program != null) {
                Object[][] data = dbOperations.getWhatProgLanguagesOfProgram(program);
                if (data.length == 0) {
                    JOptionPane.showMessageDialog(null, "Список данных пуст.");
                }
                String[] fields = {"Название", "Пишется на языке программирования"};
                new Dialog(data, fields);
            }
        }
    }

    private class WhatProgLangUsedWithOsAsTheMostPopularButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String os = JOptionPane.showInputDialog(null, "Введите название операционной системы");
            if (os != null) {
                Object[][] data = dbOperations.getWhatProgLanguagesUsedWithOsAsTheMostPopular(os);
                if (data.length == 0) {
                    JOptionPane.showMessageDialog(null, "Список данных пуст.");
                }
                String[] fields = {"Название"};
                new Dialog(data, fields);
            }
        }
    }

    private class AtFinishedProjectsNumberOfRequirementAtAllAndPerformedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] data = dbOperations.getAtFinishedProjectsNumberOfRequirementsAtAllAndPerformed();
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"Проект", "Выполненных требований", "Общее количество требований"};
            new Dialog(data, fields);
        }
    }

    private class AtProjectNumberOfRequirementAtAllAndPerformedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String project = JOptionPane.showInputDialog(null, "Введите название проекта");
            if (project != null) {
                Object[][] data = dbOperations.getAtProjectNumberOfRequirementsAtAllAndPerformed(project);
                if (data.length == 0) {
                    JOptionPane.showMessageDialog(null, "Список данных пуст.");
                }
                String[] fields = {"Проект", "Выполненных требований", "Общее количество требований"};
                new Dialog(data, fields);
            }
        }
    }

    private class FinishedProjectWithPercentRatioOfRequirementsPerformedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String percent = JOptionPane.showInputDialog(null, "Введите процент отношения выполненных требований к общим, начиная с которого будут искаться проекты");
            Object[][] data = dbOperations.getFinishedProjectsWithPercentRatioOfRequirementsPerformed(Integer.parseInt(percent));
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "Список данных пуст.");
            }
            String[] fields = {"Проект", "Процентное соотношение выполненых требований", "Методология"};
            new Dialog(data, fields);
        }
    }

    class HintTextField extends JTextField implements FocusListener {

        private String hint;

        private boolean showingHint;

        public HintTextField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText("");
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(hint);
                showingHint = true;
            }
        }

        public void setFocus() {
            showingHint = false;
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }


        @Override
        public void setText(String t) {
            super.setText(t);
            this.hint = t;
            this.showingHint = true;
        }

    }

}

