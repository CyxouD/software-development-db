package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.*;


public class DatabaseBrowser extends JFrame {
		static JPanel mainPanel;
	    static WindowHandler windowHandler;
		
		public void buildGui(){
			setTitle("Database Client");
			mainPanel = new JPanel(new BorderLayout());
            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (JOptionPane.showConfirmDialog(mainPanel,
                            "Are you sure to close this window?", "Really Closing?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        DBConnection.closeConnection();
                        System.exit(0);
                    }
                }
            });


			getContentPane().add(BorderLayout.CENTER,mainPanel);
			
			setSize(1024,780);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setVisible(true);
		}
	


		static void loadMenu(JPanel panel){
			mainPanel.removeAll();
			mainPanel.add(BorderLayout.CENTER, panel);
			mainPanel.validate();
			mainPanel.repaint();
		}

	
	
	public static void main(String[] args){
		if (System.getProperty("java.util.logging.config.class") == null
				&& System.getProperty("java.util.logging.config.file") == null)
		{
			try
			{
				Logger.getLogger("gui").setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 1;
				//Handler handler = new FileHandler("%h/LoggingViewer.log", 0, LOG_ROTATION_COUNT);
				Handler handler = new FileHandler("%h/LoggingViewer.log", 0, LOG_ROTATION_COUNT, true);
				Logger.getLogger("gui").addHandler(handler);
				SimpleFormatter formatter = new SimpleFormatter();
				handler.setFormatter(formatter);
			}
			catch (IOException e)
			{
				Logger.getLogger("gui").log(Level.SEVERE,
						"Can't create log file handler", e);
			}
		}

		EventQueue.invokeLater(() ->
		{
			windowHandler = new WindowHandler();
			windowHandler.setLevel(Level.ALL);
			Logger.getLogger("gui").addHandler(windowHandler);
		});

		DatabaseBrowser browser = new DatabaseBrowser();
		browser.buildGui();
		browser.loadMenu(new SignInForm().getGuiPanel());
	}

}
