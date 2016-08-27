package gui;

import db.CryptWithMD5;
import db.DBConnection;
import db.tables.company.CompanyDAO;
import entities.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SignInForm {
	JPanel mainPanel;
	JPanel signInPanel;
	JButton signInButton;
	JTextField companyField;
	JTextField passwordField;
	JButton signUpButton;
	JButton forgotPasswordButton;
	JCheckBox rememberPasswordBox;
	JLabel rememberPasswordLabel;

	public static String PRIVELEGE_ADMIN = "Администратор БД";
	public static String PRIVELEGE_USER = "Пользователь";

	JTextField companyNameTF = new JTextField();
	JTextField companyAdressTF = new JTextField();
	JTextField companyStaffTF = new JTextField();
	JTextField companyEveryYearProfitTF = new JTextField();
	JTextField companyPasswordFirstTimeTF = new JTextField();
	JTextField companyPasswordSecondTimeTF = new JTextField();

	private JDialog jDialog = new JDialog();

	private CompanyDAO companyDAO;

	private static Logger logger = Logger.getLogger("gui");


	public JPanel getGuiPanel(){
		mainPanel = new JPanel(new BorderLayout());
		signInPanel = new JPanel();
		signInPanel.setLayout(new BoxLayout(signInPanel,BoxLayout.Y_AXIS));
		companyField = new JTextField(16);
		passwordField = new JPasswordField(16);
		signInPanel.add(companyField);
		signInPanel.add(passwordField);
		signInButton = new JButton("Sign in");
		signInButton.addActionListener(new SignInButtonListener());
		signUpButton = new JButton("Sign up");
		signUpButton.addActionListener(new SignUpButtonListener());
		JPanel signOperationsPanel = new JPanel();
		signOperationsPanel.add(signInButton);
		signOperationsPanel.add(signUpButton);
		mainPanel.add(signInPanel,BorderLayout.NORTH);
		mainPanel.add(signOperationsPanel, BorderLayout.CENTER);

		DBConnection.init();
		companyDAO = new CompanyDAO();
		
		return mainPanel;
	}
	
	
	public class SignInButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String company = companyField.getText();
			String password = passwordField.getText();

			if (company.equals("") || password.equals("")){
				JOptionPane.showMessageDialog (signInButton,
						"Не введен логин или пароль", "Не указаны все данные",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				String passwordToCheck = null;
				try {
					passwordToCheck = companyDAO.getPasswordByName(company);

				} catch (SQLException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog (signInButton,
							message, "Ошибка",
							JOptionPane.ERROR_MESSAGE);
				}
				if (passwordToCheck != null && passwordToCheck.equals(CryptWithMD5.cryptWithMD5(password))){
					try {
						String privilege = companyDAO.getPrivelegesByName(company);
						if (privilege.equals(PRIVELEGE_USER)) {
							JOptionPane.showMessageDialog (signInButton,
									 "Добро пожаловать, " + company);
							DatabaseBrowser.loadMenu(new UserGui(PRIVELEGE_USER).getGuiPanel());
							logger.fine("USER " + company + " signed in");
						}
						else if (privilege.equals(PRIVELEGE_ADMIN)){
							DatabaseBrowser.loadMenu(new AdminGui().getGuiPanel());
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog (signInButton,
							"Не правильно указан логин или пароль", "Неправильные данные",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		
	}

	class SignUpButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			jDialog = new JDialog();
			jDialog.setTitle("Регистрация пользователя");
			jDialog.setSize(500,400);
			final Toolkit toolkit = Toolkit.getDefaultToolkit();
			final Dimension screenSize = toolkit.getScreenSize();
			final int x = (screenSize.width - jDialog.getWidth()) / 2;
			final int y = (screenSize.height - jDialog.getHeight()) / 2;
			jDialog.setLocation(x, y);

			JPanel signUpPanel = null;
			signUpPanel = new JPanel();
			signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
			JPanel companyNamePanel = new JPanel();
			JLabel companyNameLabel = new JLabel("Company name*");
			companyNameTF = new JTextField(20);
			companyNamePanel.add(companyNameLabel);
			companyNamePanel.add(companyNameTF);
			JPanel companyAdressPanel = new JPanel();
			JLabel companyAdressLabel = new JLabel("Company address");
			companyAdressTF = new JTextField(20);
			companyAdressPanel.add(companyAdressLabel);
			companyAdressPanel.add(companyAdressTF);
			JPanel companyStaffPanel = new JPanel();
			JLabel companyStaffLabel = new JLabel("Company staff");
			companyStaffTF = new JTextField(20);
			companyStaffPanel.add(companyStaffLabel);
			companyStaffPanel.add(companyStaffTF);
			JPanel companyAnnualYearProfitPanel = new JPanel();
			JLabel companyAnnualYearProfitLabel = new JLabel("Company annual year profit");
			companyEveryYearProfitTF = new JTextField(20);
			companyAnnualYearProfitPanel.add(companyAnnualYearProfitLabel);
			companyAnnualYearProfitPanel.add(companyEveryYearProfitTF);
			JPanel passwordFirstTimePanel = new JPanel();
			JLabel firstTimePassLabel = new JLabel("Enter password*");
			companyPasswordFirstTimeTF = new JPasswordField(20);
			passwordFirstTimePanel.add(firstTimePassLabel);
			passwordFirstTimePanel.add(companyPasswordFirstTimeTF);
			JPanel passwordSecondTimePanel = new JPanel();
			JLabel secondTimePassLabel = new JLabel("Repeat password*");
			companyPasswordSecondTimeTF = new JPasswordField(20);
			passwordSecondTimePanel.add(secondTimePassLabel);
			passwordSecondTimePanel.add(companyPasswordSecondTimeTF);
			JLabel label = new JLabel("* - обов'язкові для заповнення");
			JButton registerButton = new JButton("Sign up");
			registerButton.addActionListener(new RegisterButtonListener());
			signUpPanel.add(companyNamePanel);
			signUpPanel.add(companyAdressPanel);
			signUpPanel.add(companyStaffPanel);
			signUpPanel.add(companyAnnualYearProfitPanel);
			signUpPanel.add(passwordFirstTimePanel);
			signUpPanel.add(passwordSecondTimePanel);
			signUpPanel.add(label);
			signUpPanel.add(registerButton);


			jDialog.add(signUpPanel);
			jDialog.setVisible(true);
		}
	}

	class RegisterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int companyId = 0;
			try {
				companyId = companyDAO.getLastInsertedId() + 1;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String companyName = companyNameTF.getText();
			String companyAdress = companyAdressTF.getText();
			String companyStaff = companyStaffTF.getText();
			String companyAnnualProfit = companyEveryYearProfitTF.getText();
			String companyPasswordFirstTime = companyPasswordFirstTimeTF.getText();
			String companyPasswordSecondTime = companyPasswordSecondTimeTF.getText();

			if (companyPasswordFirstTime.equals("") || companyPasswordSecondTime.equals("") || companyName.equals("")){
				JOptionPane.showMessageDialog (signInButton,
						"Не введены обязательные данные", "Ошибка",
						JOptionPane.ERROR_MESSAGE);
			}
			else if (!companyPasswordFirstTime.equals(companyPasswordSecondTime)){
				JOptionPane.showMessageDialog (signInButton,
						"Пароли не совпадают", "Ошибка",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				ArrayList<Object> arrayList = new ArrayList<>();
				arrayList.add(Integer.toString(companyId));
				arrayList.add(companyName);
				arrayList.add(companyAdress);
				arrayList.add(companyStaff);
				arrayList.add(companyAnnualProfit);
				arrayList.add(companyPasswordFirstTime);
				arrayList.add(PRIVELEGE_USER);
				Company newCompany = new Company(arrayList);

				try {
					companyDAO.addCompany(newCompany);
					jDialog.dispose();
					JOptionPane.showMessageDialog (signInButton,
							"Вы успешно зарегистрировались", "Поздравляем",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (SQLException e1) {
					e1.printStackTrace();
					if (e1.getErrorCode() == 1062){
						JOptionPane.showMessageDialog (signInButton,
								"Компания с таким именем уже существует", "Ошибка при регистрации",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		}
	}
}
