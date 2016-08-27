package gui;

import db.CryptWithMD5;
import db.DBConnection;
import db.DBOperations;
import db.tests.DBDataPreparing;
import entities.*;
import utils.IntegerCheck;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminGui {
	private final int TABLES_NUMBER = 16;
	private final String TABLE_STATS = "Card with Table stats";
	private final String TABLE_WORK = "Card with Table work";

	private MainTasks mainTasks;

    private static final int COLUMN_TABLE_NAMES = 0;
    private static final int COLUMN_TABLE_NUMBER_ROWS = 1;
    private static final int COLUMN_NUMBER = 2;
	private JPanel tablesPanel;
	private JTable dataTablesTable;

	private JPanel usersPanel;
	private JTable dataUsersTable;

	private final String[] tables = {"Version",
			"Edition",
			"OS edition",
			"Client",
			"Team",
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

	private JPanel logsPanel;

	private JPanel testsPanel;

	private static Logger logger = Logger.getLogger("gui");


	/*
		TABLE WORK PANEL - OPERATIONS ETC
	 */

	private JPanel mainWorkPanel;
	private JPanel recordPanel;
	private JPanel tablePanel;
	private JPanel tableOperationsPanel;
	private JPanel dataWorkPanel;

    private JToggleButton tableViewButton;
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

	private static final String RECORDS_PANEL = "Card with Record panel";
	private static final String TABLE_PANEL = "Card with Table panel";

	private static final String PROGRAMS_PANEL = "Program";

	private DBOperations dbOperations;

	/******************************************************************************/


	public AdminGui() {
		DBConnection.init();
		dbOperations = new DBOperations();
		mainTasks = new MainTasks();
	}

	public JPanel getGuiPanel(){
		JPanel mainPanel = new JPanel(new BorderLayout());

		JMenuBar jMenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem makeBackupItem = new JMenuItem("Make database backup");
		makeBackupItem.addActionListener(new MakeBackupItemListener());
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ExitMenuItemListener());
		fileMenu.add(makeBackupItem);
		fileMenu.add(exitMenuItem);
		jMenuBar.add(fileMenu);

		JTabbedPane tabbedPane = new JTabbedPane();
		tablesPanel = new JPanel(new CardLayout());

		loadTableStatsPanel();
		loadTableWorkPanel();
		tabbedPane.addTab("Tables", tablesPanel);

		loadUsersPanel();
		tabbedPane.addTab("Users",usersPanel);

		loadLogsPanel();
		tabbedPane.addTab("Logs", logsPanel);

		loadTestsPanel();
		tabbedPane.addTab("DB performance tests", testsPanel);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		mainPanel.add(jMenuBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		
		return mainPanel;
	}

	private void loadTableStatsPanel(){
		JPanel tablesStatsPanel = new JPanel();
		tablesStatsPanel.setLayout(new BorderLayout());
		String[] columnNamesTables = {"Table",
				"Num of Rows"
		};
		Object[][] dataTables = new Object[TABLES_NUMBER][COLUMN_NUMBER];
		setTablesNames(dataTables, tables);
		setNumberOfTableRows(dataTables, tables);
		dataTablesTable = new JTable(dataTables,columnNamesTables);
		JScrollPane tablesScrollPane = new JScrollPane(dataTablesTable);
		JButton editTableButton = new JButton("Edit Table");
		editTableButton.addActionListener(new EditTableButtonListener());
		JButton updateTableButton = new JButton("Update");
		updateTableButton.addActionListener(new UpdateTableButtonListener());
		JPanel tableOperationsPanel = new JPanel();
		tableOperationsPanel.add(editTableButton);
		tableOperationsPanel.add(updateTableButton);
		tablesStatsPanel.add(tablesScrollPane, BorderLayout.CENTER);
		tablesStatsPanel.add(tableOperationsPanel, BorderLayout.SOUTH);

		tablesPanel.add(tablesStatsPanel, TABLE_STATS);
	}

	private void loadUsersPanel(){
		usersPanel = new JPanel();
		usersPanel.setLayout(new BorderLayout());
		String[] usersHeaders = new String[]{"id", "Название", "Полный адрес", "Штат", "Ежегодный доход", "Уровень привелегий"};
		Object[][] data = dbOperations.getCompanies();
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		defaultTableModel.setDataVector(data, usersHeaders);
		dataUsersTable = new JTable(defaultTableModel);
		dataUsersTable.setFillsViewportHeight(true);
		JScrollPane usersScrollPane = new JScrollPane(dataUsersTable);
		usersPanel.add(usersScrollPane,BorderLayout.CENTER);
		JButton editPrivilegesButton = new JButton("Change Privileges");
		editPrivilegesButton.addActionListener(new EditPrivilegesButtonListener());
		JButton deleteUserButton = new JButton("Delete user");
		deleteUserButton.addActionListener(new DeleteUserButtonListener());
		JButton updateUserButton = new JButton("Update user");
		updateUserButton.addActionListener(new UpdateUserButtonListener());
		JButton refreshUsersButton = new JButton("Refresh");
		JPanel userOperationsPanel = new JPanel();
		userOperationsPanel.add(editPrivilegesButton);
		userOperationsPanel.add(deleteUserButton);
		userOperationsPanel.add(updateUserButton);
		userOperationsPanel.add(refreshUsersButton);
		usersPanel.add(userOperationsPanel, BorderLayout.SOUTH);
	}

	private void loadTableWorkPanel(){
		tablesPanel.add(getTableWorkPanel(), TABLE_WORK);
	}

	private void loadLogsPanel(){
		logsPanel = DatabaseBrowser.windowHandler.getPanel();
	}

	private void loadTestsPanel(){
		testsPanel = new JPanel();
        testsPanel.setLayout(new BoxLayout(testsPanel, BoxLayout.Y_AXIS));
		JButton testCrossplatformTaskTimeBtn = new JButton("Test crossplatform task");
		testCrossplatformTaskTimeBtn.addActionListener(new TestCrossplatformTaskTimeBtnListener());
		JButton testLanguagePopularityTaskTimeBtn = new JButton("Test language popularity task");
		testLanguagePopularityTaskTimeBtn.addActionListener(new TestLanguagePopularityTaskTimeBtnListener());
        JButton testSuccessfulProjectMethodologyTaskTimeBtn = new JButton("Test successful project's methodology task");
        testSuccessfulProjectMethodologyTaskTimeBtn.addActionListener(new TestSuccessfulProjectMethodologyTaskTimeBtnListener());
        JButton testWhatRequirementMostlyDoneOnTimeTimeBtn = new JButton("Test mostly done on time requirement task");
        testWhatRequirementMostlyDoneOnTimeTimeBtn.addActionListener(new TestWhatRequirementMostlyDoneOnTimeTimeBtnListener());
        JButton testWhatRequirementMostlyDoneAtWrongTimeBtn = new JButton("Test mostly done at wrong time requirement task");
        testWhatRequirementMostlyDoneAtWrongTimeBtn.addActionListener(new TestWhatRequirementMostlyDoneAtWrongTimeBtnListener());

		JButton clearTableButton = new JButton();
		clearTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DBConnection.getConnection().setAutoCommit(false);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		testsPanel.add(testCrossplatformTaskTimeBtn);
		testsPanel.add(testLanguagePopularityTaskTimeBtn);
        testsPanel.add(testSuccessfulProjectMethodologyTaskTimeBtn);
        testsPanel.add(testWhatRequirementMostlyDoneOnTimeTimeBtn);
        testsPanel.add(testWhatRequirementMostlyDoneAtWrongTimeBtn);
	}

	private void setTablesNames(Object[][] data, String[] tables){
		for (int i = 0; i < tables.length; i ++){
			data[i][COLUMN_TABLE_NAMES] = tables[i];
		}
	}

	private void setNumberOfTableRows(Object[][] data, String[] tables){
		for (int i = 0; i < tables.length; i++){
			data[i][COLUMN_TABLE_NUMBER_ROWS] = dbOperations.getNumberRows(tables[i]);
		}
	}

	class EditTableButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String tableName = getSelectedFromTable(dataTablesTable);

			resetAllButtons();

			CardLayout cardLayout = (CardLayout) (tableOperationsPanel.getLayout());
			cardLayout.show(tableOperationsPanel, tableName);

			DefaultTableModel defaultTableModel = new DefaultTableModel(){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			Object[][] data = null;
			String[] headers = null;

			if (tableName.equals("Program")) {
				data = dbOperations.getPrograms();
				headers = new String[]{"Название", "Проект", "Предназначение"};
				setRowRecordPanel(headers, "Program");
			}
			else if (tableName.equals("Version")){
				data = dbOperations.getVersion();
				headers = new String[]{"Дата выпуска", "Номер", "Программа"};
				setRowRecordPanel(headers, "Version");
			}
			else if (tableName.equals("Edition")){
				data = dbOperations.getEditions();
				headers = new String[]{"Название", "Особенности"};
				setRowRecordPanel(headers, "Edition");
			}
			else if (tableName.equals("OS edition")){
				data = dbOperations.getOsEdition();
				headers = new String[]{"Операционная система", "Издание операционная система"};
				setRowRecordPanel(headers, "OS edition");
			}
			else if (tableName.equals("Client")){
				data = dbOperations.getClient();
				headers = new String[]{"Название фирмы", "Оплата"};
				setRowRecordPanel(headers, "Client");
			}
			else if (tableName.equals("Team")){
				data = dbOperations.getTeam();
				headers = new String[]{"Название", "Глава команды", "Компания"};
				setRowRecordPanel(headers, "Team");
			}
			else if (tableName.equals("Programming methodology")){
				data = dbOperations.getProgMethodology();
				headers = new String[]{"Название", "Количество человек в команде", "Особенности"};
				setRowRecordPanel(headers, "Programming methodology");
			}
			else if (tableName.equals("OS")){
				data = dbOperations.getOs();
				headers = new String[]{"Дата выпуска", "Название", "Предназначение", "Разрядность", "Тип интерфейса", "Тип задачности"};
				setRowRecordPanel(headers, "OS");
			}
			else if (tableName.equals("Program-OS")){
				data = dbOperations.getProgramOs();
				headers = new String[]{"Программа", "Операционная система"};
				setRowRecordPanel(headers, "Program-OS");
			}
			else if (tableName.equals("Programming")){
				data = dbOperations.getProgramming();
				headers = new String[]{"Программный инженер", "Его программа"};
				setRowRecordPanel(headers, "Programming");
			}
			else if (tableName.equals("Software Engineer")){
				data = dbOperations.getSoftwareEngineer();
				headers = new String[]{"ФИО", "Принадлежность команде", "Звание"};
				setRowRecordPanel(headers, "Software Engineer");
			}
			else if (tableName.equals("Project")){
				data = dbOperations.getProject();
				headers = new String[]{"Название", "Команда", "Клиент", "Методология", "Бюджет", "Закончен"};
				setRowRecordPanel(headers, "Project");
			}
			else if (tableName.equals("Project - Requirement")){
				data = dbOperations.getProjectReq();
				headers = new String[]{"Проект", "Требование", "В срок"};
				setRowRecordPanel(headers, "Project - Requirement");
			}
			else if (tableName.equals("Implementation")){
				data = dbOperations.getImpl();
				headers = new String[]{"Программа", "Пишется на языке программирования"};
				setRowRecordPanel(headers, "Implementation");
			}
			else if (tableName.equals("Requirement")){
				data = dbOperations.getReq();
				headers = new String[]{"id", "Требование"};
				setRowRecordPanel(headers, "Requirement");
			}
			else if (tableName.equals("Programming Language")){
				data = dbOperations.getProgLang();
				headers = new String[]{"Название", "Предназначение", "Версия", "Наиболее частая ОС"};
				setRowRecordPanel(headers, "Programming Language");
			}

			dataTableColumnNumber = headers.length;
			defaultTableModel.setDataVector(data, headers);

			dataTable.setModel(defaultTableModel);
			System.out.println(dataTable.getRowCount());

			firstRecordButton.doClick();

			CardLayout cardLayout2 = (CardLayout) (tablesPanel.getLayout());
			cardLayout2.show(tablesPanel, TABLE_WORK);

		}
	}

	private String getSelectedFromTable(JTable table){
		int i = table.getSelectedRow();

		String tableName = (String) table.getValueAt(i,0);
		return tableName;
	}

	class DeleteUserButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = {"Да",
					"Нет"};
			String selectedUser = (String) dataUsersTable.getValueAt(dataUsersTable.getSelectedRow(), 1);
			int n = JOptionPane.showOptionDialog(usersPanel,
					"Are you sure want to delete user \"" + selectedUser + "\" ?",
					"Warning!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[1]); //default button title
			if (n == 0) { // Answer is "YES""
				boolean isCorrect = checkPassword();
				if (isCorrect) {
					String message;
					int id = Integer.parseInt(getSelectedFromTable(dataUsersTable));
					//message = dbOperations.deleteCompany(id);
					message = DBOperations.NO_ERROR_MESSAGE;

					if (message.equals(DBOperations.NO_ERROR_MESSAGE)) {
						DefaultTableModel dataTableModel = (DefaultTableModel) dataUsersTable.getModel();
						dataTableModel.removeRow(dataUsersTable.getSelectedRow());
					} else {
						JOptionPane.showMessageDialog(usersPanel,
								message, "Ошибка при удалении пользователя из БД",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(usersPanel,
							"Неправильный пароль", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private boolean checkPassword(){
		String password = JOptionPane.showInputDialog(usersPanel, "Введите пароль для подтверждения");

		String passwordToCheck = null;
		try {
			passwordToCheck = dbOperations.getCompanyDAO().getPasswordByName("НИИПО");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (passwordToCheck != null && passwordToCheck.equals(CryptWithMD5.cryptWithMD5(password))) {
			return true;
		}
		return false;
	}

	class UpdateUserButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Object> list = collectDataFromSelectedUserRow();

			String message = dbOperations.updateCompany(new Company(list));

			if (message.equals(DBOperations.NO_ERROR_MESSAGE)){
				DefaultTableModel dataTableModel = (DefaultTableModel) dataUsersTable.getModel();
				for (int i = 0; i < list.size(); i++) {
					dataTableModel.setValueAt(list.get(i), dataUsersTable.getSelectedRow(), i);
				}
			}
			else {
				JOptionPane.showMessageDialog (usersPanel,
						message, "Ошибка при обновлении пользователя в БД",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private ArrayList<Object> collectDataFromSelectedUserRow(){
			if (dataUsersTable.isEditing()) {
				dataUsersTable.getCellEditor().stopCellEditing();
			}

			DefaultTableModel defaultTableModel = (DefaultTableModel) dataUsersTable.getModel();

			ArrayList<Object> list = new ArrayList<>();
			int selectedRow = dataUsersTable.getSelectedRow();
			for (int i = 0; i < dataUsersTable.getColumnCount(); i++){
				String value = (String) defaultTableModel.getValueAt(selectedRow, i);
				if (value != null) {
					list.add((value));
				}
				else{
					list.add("");
				}
			}

			return list;
		}

	class EditPrivilegesButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] options = {SignInForm.PRIVELEGE_ADMIN, SignInForm.PRIVELEGE_USER};
			String input = (String) JOptionPane.showInputDialog(null,"Изменение привилегии", "Укажите новую привилегию",
					JOptionPane.QUESTION_MESSAGE, null, // Use
					// default
					// icon
					options, // Array of choices
					options[0]); // Initial choice

	        if (input != null) {
				Object[] yesNoOption = {"Да",
						"Нет"};
				int answer = JOptionPane.showOptionDialog(usersPanel,
						"Are you sure want to change level of privilege ? It can be dangerous",
						"Warning!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						yesNoOption,
						yesNoOption[1]);

				if (answer == 0) {
					dbOperations.updateCompanyPrivilege(input, Integer.parseInt((String) dataUsersTable.
							getValueAt(dataUsersTable.getSelectedRow(), 0)));

					dataUsersTable.setValueAt(input, dataUsersTable.getSelectedRow(), 5);
				}
			}
		}
	}

	class UpdateTableButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < tables.length; i++){
				int nRow = dbOperations.getNumberRows(tables[i]);
				dataTablesTable.setValueAt(nRow, i, COLUMN_TABLE_NUMBER_ROWS);
			}
		}
	}

	class MakeBackupItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File file = new File("dumps");
			if (!file.exists()){
				file.mkdir();
			}
			try {
				JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "sql", "sql");
                chooser.setFileFilter(filter);
				chooser.setDialogTitle("Выбери папку для сохранение бэкапа");
				String workingDir = System.getProperty("user.dir");
				chooser.setCurrentDirectory(new File(workingDir + "/dumps"));
				int returnVal = chooser.showSaveDialog(null);
				String dirToSave = null;
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					dirToSave = chooser.getSelectedFile().getAbsolutePath();
					String command = "cmd /c start dump.bat";
					String commandWithParams = command + " " + "\"" + dirToSave + "\"";
					System.out.println(commandWithParams);

					Runtime.getRuntime().exec(commandWithParams);
					JOptionPane.showMessageDialog(mainWorkPanel,"Резервное копирование было успешно осуществлено в " + commandWithParams);
					logger.log(Level.INFO, "Dump successfully made in /dumps");
				}
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "Error while trying make dump" );
				e1.printStackTrace();
			}
		}
	}


	class ExitMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DatabaseBrowser.loadMenu(new SignInForm().getGuiPanel());
		}
	}

	class TestCrossplatformTaskTimeBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String inputValue = showInputRowsMessage();
                if (inputValue != null) {
                    int nAffectedRows = DBDataPreparing.prepareRandomDataProgram_ProgramOsTables(Integer.parseInt(inputValue));
                    long start = System.currentTimeMillis();
                    mainTasks.findIfCrossplatformingActual(testsPanel);
                    long timeInMillis = System.currentTimeMillis() - start;

                    showMessageWithParam("CROSSPLATFORM TASK", timeInMillis, nAffectedRows);


                    DBDataPreparing.rollBackAndSetAutoCommitInfo();
                }
			} catch (SQLException e1) {
				logger.log(Level.FINE, "explanation", e1);
				e1.printStackTrace();
			}
		}
	}


	class TestLanguagePopularityTaskTimeBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String inputValue = showInputRowsMessage();
                if (inputValue != null) {
                    int nAffectedRows = DBDataPreparing.prepareRandomDataProgram_RealisationTables(Integer.parseInt(inputValue));
                    long start = System.currentTimeMillis();
                    mainTasks.findPopularityOfEachEachLang(testsPanel);
                    long timeInMillis = System.currentTimeMillis() - start;

                    showMessageWithParam("LANGUAGE POPULARITY TASK", timeInMillis, nAffectedRows);

                    DBDataPreparing.rollBackAndSetAutoCommitInfo();
                }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	class TestSuccessfulProjectMethodologyTaskTimeBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String inputValue = showInputRowsMessage();
                if (inputValue != null) {
                    int nAffectedRows = DBDataPreparing.prepareRandomDataProject_ProjectReqTables(Integer.parseInt(inputValue));
                    long start = System.currentTimeMillis();
                    mainTasks.findWhatMethodologySuccesfulProjectsUse(testsPanel);
                    long timeInMillis = System.currentTimeMillis() - start;

                    showMessageWithParam("SUCCESSFUL PROJECT METHODOLOGY TASK", timeInMillis, nAffectedRows);

                    DBDataPreparing.rollBackAndSetAutoCommitInfo();
                }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

    class TestWhatRequirementMostlyDoneOnTimeTimeBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
				String inputValue = showInputRowsMessage();
                if (inputValue != null) {
                    int nAffectedRows = DBDataPreparing.prepareRandomDataProject_ProjectReqTables(Integer.parseInt(inputValue));
                    long start = System.currentTimeMillis();
                    mainTasks.findWhatRequirementsAreDoneOnTime(testsPanel);
                    long timeInMillis = System.currentTimeMillis() - start;

                    showMessageWithParam("MOSTLY DONE ON TIME REQUIREMENT TASK", timeInMillis, nAffectedRows);

                    DBDataPreparing.rollBackAndSetAutoCommitInfo();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    class TestWhatRequirementMostlyDoneAtWrongTimeBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String inputValue = showInputRowsMessage();
                if (inputValue != null) {
                    int nAffectedRows = DBDataPreparing.prepareRandomDataProject_ProjectReqTables(Integer.parseInt(inputValue));
                    long start = System.currentTimeMillis();
                    mainTasks.findWhatRequirementsAreDoneAtWrongTime(testsPanel);
                    long timeInMillis = System.currentTimeMillis() - start;

                    showMessageWithParam("MOSTLY DONE AT WRONG TIME REQUIREMENT TASK", timeInMillis, nAffectedRows);

                    DBDataPreparing.rollBackAndSetAutoCommitInfo();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String showInputRowsMessage(){
        String inputValue = JOptionPane
                .showInputDialog("Введите количество строк для добавления в таблицы, участвующие в запросы." +
                        "\n(Количество добавленных строк будет примерно равно введенному)");
        if (!IntegerCheck.isPositiveInteger(inputValue)){
            JOptionPane.showMessageDialog(null, "Введеное значение должно быть целым числом >= 0",
                    "Неверное введенное значение", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return inputValue;
    }

    private void showMessageWithParam(String task, long timeInMillis, int nAffectedRows){
		logger.log(Level.INFO, "TEST:" + task +". Executed in " + timeInMillis + " millis. Created rows: " + nAffectedRows);

		JOptionPane.showMessageDialog(testsPanel,task + " duration " + timeInMillis / 1000 +
				" seconds(" + timeInMillis + " milliseconds). " + nAffectedRows + " rows created.");
	}

	/*
	*******************************	TABLE WORK METHODS,COMPONENTS,ETC  ***************************************************
	 */

	public JPanel getTableWorkPanel(){
		mainWorkPanel = new JPanel(new BorderLayout());


		JPanel northPanel = new JPanel(new BorderLayout());
        tableViewButton = new JToggleButton("Table view");
		tableViewButton.addActionListener(new TableViewButtonListener());
		recordViewButton = new JToggleButton("Record view");
		recordViewButton.addActionListener(new RecordViewButtonListener());
		JButton backToStatsButton = new JButton("Back to stats");
		backToStatsButton.addActionListener(new BackToStatsButtonListner());
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(tableViewButton);
		buttonGroup.add(recordViewButton);
		JPanel tablesListPanel = new JPanel();
		tablesListPanel.add(tableViewButton);
		tablesListPanel.add(recordViewButton);
		tablesListPanel.add(backToStatsButton);
		northPanel.add(tablesListPanel, BorderLayout.CENTER);

		dataWorkPanel = new JPanel(new CardLayout());


		mainWorkPanel.add(northPanel, BorderLayout.NORTH);
		mainWorkPanel.add(dataWorkPanel, BorderLayout.CENTER);

		loadFirstScreen();
		loadRecordsMenu();
		loadTableMenu();

		return mainWorkPanel;
	}

	private void loadFirstScreen(){
		dataWorkPanel.add(new JPanel(), "DEFAULT");
	}

	private void loadRecordsMenu(){
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
		currentPointerNumberTF.addActionListener( new CurrentPointerNumberTFListener());
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
		recordOperationsPanel.add(deleteRecordButton);
		recordOperationsPanel.add(updateRecordButton);
		recordOperationsPanel.add(createRecordButton);
		recordOperationsPanel.add(nextRecordButton);
		recordOperationsPanel.add(lastRecordButton);
		recordPanel.add(recordPane, BorderLayout.CENTER);
		recordPanel.add(recordOperationsPanel,BorderLayout.SOUTH);

		dataWorkPanel.add(recordPanel,RECORDS_PANEL);
	}

	private void showRecordMenu(){
		CardLayout cardLayout = (CardLayout) (dataWorkPanel.getLayout());
		cardLayout.show(dataWorkPanel, RECORDS_PANEL);
	}

	private void loadTableMenu(){
		tablePanel = new JPanel(new BorderLayout());

		dataTable = new JTable();
		JScrollPane tablePane = new JScrollPane(dataTable);

		tableOperationsPanel = new JPanel(new CardLayout());
		loadProgramsPanel();

		tablePanel.add(tablePane, BorderLayout.CENTER);
		//tablePanel.add(tableOperationsPanel, BorderLayout.EAST);

		dataWorkPanel.add(tablePanel,TABLE_PANEL);
	}

	private void loadProgramsPanel(){
		JPanel programsPanel = new JPanel();
		JButton addProgramBtn = new JButton("Add program");
		JButton deleteProgramBtn = new JButton("Delete program");
		programsPanel.add(addProgramBtn);
		programsPanel.add(deleteProgramBtn);

		tableOperationsPanel.add(programsPanel, PROGRAMS_PANEL);
	}

	private void showTableMenu(){
		CardLayout cardLayout = (CardLayout) (dataWorkPanel.getLayout());
		cardLayout.show(dataWorkPanel, TABLE_PANEL);
	}

	private void resetAllButtons(){
        recordViewButton.doClick();

		firstRecordButton.setEnabled(true);
		previousRecordButton.setEnabled(true);
		updateRecordButton.setEnabled(true);
		deleteRecordButton.setEnabled(true);
		createRecordButton.setEnabled(true);
		nextRecordButton.setEnabled(true);
		lastRecordButton.setEnabled(true);

	}

	private void setRowRecordPanel(Object[] headers, String tableName){
		setRecordRowOperationsDefaults();

		setRecordRowTableDefaults(headers, tableName);
	}

	private void setRecordRowTableDefaults(Object[] headers, String tableName){
		DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
		recordRowTableModel.setDataVector(null, headers);
		//recordRowTableModel.addRow(getRowFromDataTableAtPointer());

		setComboBoxes(tableName);
	}

	private void setComboBoxes(String tableName){
		TableColumn checkBoxColumn = null;
		JComboBox<String> comboBox = null;
		if (tableName.equals("Program")) {
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getProjectNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Version")) {
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(2);
			comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("OS edition")) {
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
			comboBox = new JComboBox(dbOperations.getOsNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getEditionNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Team")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(2);
			comboBox = new JComboBox(dbOperations.getCompanyIdList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Program-OS")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
			comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getOsNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Programming")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
			comboBox = new JComboBox(dbOperations.getSEInitialsList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Software Engineer")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getTeamNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Project")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getTeamNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Project - Requirement")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
			comboBox = new JComboBox(dbOperations.getProjectNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getReqIdList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Implementation")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(0);
			comboBox = new JComboBox(dbOperations.getProgramNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));

			checkBoxColumn = recordRowTable.getColumnModel().getColumn(1);
			comboBox = new JComboBox(dbOperations.getProgLangNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}
		else if (tableName.equals("Programming Language")){
			checkBoxColumn = recordRowTable.getColumnModel().getColumn(3);
			comboBox = new JComboBox(dbOperations.getOsNameList().toArray());
			checkBoxColumn.setCellEditor(new DefaultCellEditor(comboBox));
		}


	}

	private void setRecordRowOperationsDefaults(){
		showRecordMenu();

		rowPointer = 0;
		previousRecordButton.setEnabled(false);
		createRecordButton.setEnabled(false);
		currentPointerNumberTF.setText("1 from " + dataTable.getRowCount(
		));
		recordRowTable.repaint();
	}

	boolean newRow;

	private Object[] getRowFromDataTableAtPointer(){
		newRow = false;
		if (rowPointer >= dataTable.getRowCount()){ // new row
			newRow = true;
			updateRecordRowOperationsPanel();
			return createEmptyRow();
		}

		Object[] row = new String[dataTableColumnNumber];
		for (int i = 0; i < dataTableColumnNumber; i++){
			row[i] = dataTable.getValueAt(rowPointer,i);
		}

		return row;
	}

	private Object[] createEmptyRow(){
		Object[] row = new String[dataTableColumnNumber];
		for (int i = 0; i < dataTableColumnNumber; i++){
			row[i] = "";
		}
		return row;
	}

	private void clearRecordRowTable(){
		DefaultTableModel defaultTableModel = (DefaultTableModel) recordRowTable.getModel();
		defaultTableModel.setRowCount(0);
	}

	class TableViewButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			showTableMenu();
		}
	}

	class RecordViewButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			showRecordMenu();
		}
	}

	private void checkRecordButtons(){
		//System.out.println("ROW POINTER = " + rowPointer);
		//System.out.println("REGIM NEW ROW  ? " + newRow);
		if (dataTable.getModel().getRowCount() == 0){
			previousRecordButton.setEnabled(false);
			firstRecordButton.setEnabled(false);
			if (newRow){
				nextRecordButton.setEnabled(false);
				lastRecordButton.setEnabled(false);
				createRecordButton.setEnabled(true);
			}
			else {
				nextRecordButton.setEnabled(true);
				lastRecordButton.setEnabled(false);
				createRecordButton.setEnabled(false);
			}
			deleteRecordButton.setEnabled(false);
			updateRecordButton.setEnabled(false);
		}
		else {
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

	private void updateRecordRowOperationsPanel(){
		setField();

		checkRecordButtons();
	}

	private void setField(){
		if (dataTable.getRowCount() != 0) {
			currentPointerNumberTF.setText((rowPointer + 1) + " from " + dataTable.getRowCount());
		}
		else {
			currentPointerNumberTF.setText("1 from 1");
		}
	}

	// private void performOperations(String operation){
	///
	//}


	class FirstRecordButtonListener implements ActionListener{
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

	class PreviousRecordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (previousRecordButton.isEnabled()){
				rowPointer--;
				updateRecordRowOperationsPanel();

				clearRecordRowTable();
				DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
				recordRowTableModel.addRow(getRowFromDataTableAtPointer());
			}
		}
	}

	class DeleteRecordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			deleteRecord();
		}
	}

	private void deleteRecord(){
		if (recordRowTable.isEditing()) {
			recordRowTable.getCellEditor().stopCellEditing();
		}

		String selectedItem = getSelectedFromTable(dataTablesTable);
		String message = null;
		if (selectedItem.equals("Program")) {
			String programName = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteProgram(programName);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Version")){
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parsed = null;
			try {
				parsed = format.parse((String) dataTable.getModel().getValueAt(rowPointer,0));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date releaseDate = new java.sql.Date(parsed.getTime());
			//message = dbOperations.deleteVersion(releaseDate);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Edition")){
			String name = (String) dataTable.getModel().getValueAt(rowPointer,0);
			// message = dbOperations.deleteEdition(name);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("OS edition")){
			String os = (String) dataTable.getModel().getValueAt(rowPointer,0);
			String edition = (String) dataTable.getModel().getValueAt(rowPointer,1);
			//message = dbOperations.deleteOsEdition(os, edition);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Client")){
			String firmName = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteClient(firmName);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Team")){
			String name = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteTeam(name);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Programming methodology")){
			String name = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteProgMethodology(name);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("OS")){
			String name = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteOs(name);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Program-OS")){
			String program = (String) dataTable.getModel().getValueAt(rowPointer,0);
			String os = (String) dataTable.getModel().getValueAt(rowPointer,1);
			//message = dbOperations.deleteProgramOs(program, os);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Programming")){
			String initials = (String) dataTable.getModel().getValueAt(rowPointer,0);
			String program = (String) dataTable.getModel().getValueAt(rowPointer,1);
			//message = dbOperations.deleteProgramming(initials, program);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Software Engineer")){
			String initials = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteSoftwareEngineer(initials);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Project")){
			String name = (String) dataTable.getModel().getValueAt(rowPointer,0);
			//message = dbOperations.deleteProject(name);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Project - Requirement")){
			String project = (String) dataTable.getModel().getValueAt(rowPointer,0);
			int req = Integer.parseInt((String) dataTable.getModel().getValueAt(rowPointer,1));
			//message = dbOperations.deleteProjectReq(project, req);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Implementation")){
			String program = (String) dataTable.getModel().getValueAt(rowPointer,0);
			String language = (String) dataTable.getModel().getValueAt(rowPointer,1);
			//message = dbOperations.deleteImpl(program, language);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Requirement")){
			System.out.println(dataTable.getModel().getValueAt(rowPointer,0));
			int id = Integer.parseInt((String) dataTable.getModel().getValueAt(rowPointer,0));
			//message = dbOperations.deleteReq(id);
			message = DBOperations.NO_ERROR_MESSAGE;
		}
		else if (selectedItem.equals("Programming Language")){
			String name = (String) dataTable.getModel().getValueAt(rowPointer,0);
//            message = dbOperations.deleteProgLang(name);
			message = DBOperations.NO_ERROR_MESSAGE;
		}

		if (message.equals(DBOperations.NO_ERROR_MESSAGE)){
			DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
			dataTableModel.removeRow(rowPointer);
		}
		else {
			JOptionPane.showMessageDialog (currentPointerNumberTF,
					message, "Ошибка при удалении из БД",
					JOptionPane.ERROR_MESSAGE);
		}

		clearRecordRowTable();
		DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
		if (rowPointer < dataTable.getRowCount() - 1) {
			recordRowTableModel.addRow(getRowFromDataTableAtPointer());
		}
		else {
			if (rowPointer != 0) {
				rowPointer--;
			}

			if (dataTable.getRowCount() != 0) {
				recordRowTableModel.addRow(getRowFromDataTableAtPointer());
			}
			else {
				JOptionPane.showMessageDialog (currentPointerNumberTF,
						"Таблица пуста", "Ошибка при удалении из БД",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		updateRecordRowOperationsPanel();
	}

	class UpdateRecordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			updateRecord();
		}
	}

	private void updateRecord(){
		ArrayList<Object> list = collectDataFromRecordField();

		String selectedItem = getSelectedFromTable(dataTablesTable);
		String message = null;
		if (selectedItem.equals("Program")) {
			message = dbOperations.updateProgram(new Program(list));
		}
		else if (selectedItem.equals("Version")){
			message = dbOperations.updateVersion(new Version(list));
		}
		else if (selectedItem.equals("Edition")){
			message = dbOperations.updateEdition(new Edition(list));
		}
		else if (selectedItem.equals("OS edition")){
			message = dbOperations.updateOsEdition(new OsEdition(list));
		}
		else if (selectedItem.equals("Client")){
			message = dbOperations.updateClient(new Client(list));
		}
		else if (selectedItem.equals("Team")){
			message = dbOperations.updateTeam(new Team(list));
		}
		else if (selectedItem.equals("Programming methodology")){
			message = dbOperations.updateProgMethodology(new ProgrammingMethodology(list));
		}
		else if (selectedItem.equals("OS")){
			message = dbOperations.updateOs(new Os(list));
		}
		else if (selectedItem.equals("Program-OS")){
			message = dbOperations.updateProgramOs(new ProgramOs(list));
		}
		else if (selectedItem.equals("Programming")){
			message = dbOperations.updateProgramming(new Programming(list));
		}
		else if (selectedItem.equals("Software Engineer")){
			message = dbOperations.updateSoftwareEngineer(new SoftwareEngineer(list));
		}
		else if (selectedItem.equals("Project")){
			message = dbOperations.updateProject(new Project(list));
		}
		else if (selectedItem.equals("Project - Requirement")){
			message = dbOperations.updateProjectReq(new ProjectReq(list));
		}
		else if (selectedItem.equals("Implementation")){
			message = dbOperations.updateImpl(new Implementation(list));
		}
		else if (selectedItem.equals("Requirement")){
			message = dbOperations.updateReq(new Req(list));
		}
		else if (selectedItem.equals("Programming Language")){
			message = dbOperations.updateProgLang(new ProgLang(list));
		}


		if (message.equals(DBOperations.NO_ERROR_MESSAGE)){
			DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
			for (int i = 0; i < list.size(); i++) {
				dataTableModel.setValueAt(list.get(i), rowPointer, i);
			}
		}
		else {
			JOptionPane.showMessageDialog (currentPointerNumberTF,
					message, "Ошибка при обновлении в БД",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	class CreateRecordButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			createRecord();
			updateRecordRowOperationsPanel();
		}
	}

	private void createRecord(){
		ArrayList<Object> list = collectDataFromRecordField();

		String selectedItem = getSelectedFromTable(dataTablesTable);
		String message = null;
		if (selectedItem.equals("Program")) {
			message = dbOperations.addProgram(new Program(list));
		}
		else if (selectedItem.equals("Version")){
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parsed = null;
			try {
				String stringDate = (String) list.get(0);
				//stringDate = stringDate.replace("", "");
				parsed = format.parse(stringDate);
				list.set(0, stringDate);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog (currentPointerNumberTF,
						"Правильный формат даты: YYYYMMDD", "Неправильный формат даты",
						JOptionPane.ERROR_MESSAGE);
			}
			message = dbOperations.addVersion(new Version(list));
		}
		else if (selectedItem.equals("Edition")){
			message = dbOperations.addEdition(new Edition(list));
		}
		else if (selectedItem.equals("OS edition")){
			message = dbOperations.addOsEdition(new OsEdition(list));
		}
		else if (selectedItem.equals("Client")){
			message = dbOperations.addClient(new Client(list));
		}
		else if (selectedItem.equals("Team")){
			message = dbOperations.addTeam(new Team(list));
		}
		else if (selectedItem.equals("Programming methodology")){
			message = dbOperations.addProgMethodology(new ProgrammingMethodology(list));
		}
		else if (selectedItem.equals("OS")){
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parsed = null;
			try {
				String stringDate = (String) list.get(0);
				//stringDate = stringDate.replace("", "");
				parsed = format.parse(stringDate);
				list.set(0, stringDate);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog (currentPointerNumberTF,
						"Правильный формат даты: YYYYMMDD", "Неправильный формат даты",
						JOptionPane.ERROR_MESSAGE);
			}
			message = dbOperations.addOs(new Os(list));
		}
		else if (selectedItem.equals("Program-OS")){
			message = dbOperations.addProgramOs(new ProgramOs(list));
		}
		else if (selectedItem.equals("Programming")){
			message = dbOperations.addProgramming(new Programming(list));
		}
		else if (selectedItem.equals("Software Engineer")){
			message = dbOperations.addSoftwareEnginner(new SoftwareEngineer(list));
		}
		else if (selectedItem.equals("Project")){
			message = dbOperations.addProject(new Project(list));
		}
		else if (selectedItem.equals("Project - Requirement")){
			message = dbOperations.addProjectReq(new ProjectReq(list));
		}
		else if (selectedItem.equals("Implementation")){
			message = dbOperations.addImpl(new Implementation(list));
		}
		else if (selectedItem.equals("Requirement")){
			message = dbOperations.addReq(new Req(list));
		}
		else if (selectedItem.equals("Programming Language")){
			message = dbOperations.addProgLang(new ProgLang(list));
		}

		if (message.equals(DBOperations.NO_ERROR_MESSAGE)){
			DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
			dataTableModel.addRow(list.toArray());
		}
		else {
			JOptionPane.showMessageDialog (currentPointerNumberTF,
					message, "Ошибка при добавлении в БД",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private ArrayList collectDataFromRecordField(){
		if (recordRowTable.isEditing()) {
			recordRowTable.getCellEditor().stopCellEditing();
		}

		DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();

		ArrayList<Object> list = new ArrayList<>();
		for (int i = 0; i < recordRowTableModel.getColumnCount(); i++){
			String value = (String) recordRowTableModel.getValueAt(0, i);
			if (value != null) {
				list.add((value));
			}
			else{
				list.add("");
			}
		}

		return list;
	}

	class NextRecordButtonListener implements ActionListener{
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

	class LastRecordButtonListener implements ActionListener{
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

	class CurrentPointerNumberTFListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			currentPointerNumberTF.setFocus();
			int indexToGo = Integer.parseInt(currentPointerNumberTF.getText()) - 1;

			if (0 <= indexToGo && indexToGo < dataTable.getRowCount()){
				rowPointer = indexToGo;
				updateRecordRowOperationsPanel();

				clearRecordRowTable();
				DefaultTableModel recordRowTableModel = (DefaultTableModel) recordRowTable.getModel();
				recordRowTableModel.addRow(getRowFromDataTableAtPointer());
			}
			else{
				JOptionPane.showMessageDialog (currentPointerNumberTF,
						"Возможно, достигнут конец набора записей", "Невозможен переход к указанной записи",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void createDialog(Object[][] data, String[] fields){
		JTable jTable = new JTable(data, fields);
		JScrollPane jScrollPane = new JScrollPane(jTable);

		JDialog jDialog = new JDialog();
		jDialog.setSize(500,400);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - jDialog.getWidth()) / 2;
		final int y = (screenSize.height - jDialog.getHeight()) / 2;
		jDialog.setLocation(x, y);
		jDialog.add(jScrollPane);
		jDialog.setVisible(true);
	}

	class BackToStatsButtonListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) tablesPanel.getLayout();
			cardLayout.show(tablesPanel, TABLE_STATS);
		}
	}


/****************************************************************************************************************/



}
