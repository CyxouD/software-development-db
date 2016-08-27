package gui;

/**
 * Created by Dima on 07.05.2016.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

/**
 * A handler for displaying log records in a window.
 */
class WindowHandler extends StreamHandler
{
    private JPanel frame;
    private JTable logsTable;

    public WindowHandler()
    {
        frame = new JPanel();
        frame.setLayout(new BorderLayout());
        String[] columnNamesLogs = {"Level",
                "Date", "Message"
        };
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][] {}, columnNamesLogs){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        logsTable = new JTable(defaultTableModel);
        JScrollPane logsScrollPane = new JScrollPane(logsTable);
        JButton changeLogDefaultDirectory = new JButton("Изменить папку для сохранения журнала");
        changeLogDefaultDirectory.addActionListener(new ChangeLogDefaultFolder());
        //frame.add(changeLogDefaultDirectory,BorderLayout.NORTH);
        frame.add(logsScrollPane,BorderLayout.CENTER);
    }

    public JPanel getPanel(){
        return frame;
    }

    public void publish(LogRecord record)
    {
        DefaultTableModel defaultTableModel = (DefaultTableModel) logsTable.getModel();
        Object[] row = new Object[] { record.getLevel(), new Date(record.getMillis()).toString(), record.getMessage()};
        defaultTableModel.addRow(row);
        logsTable.setModel(defaultTableModel);

        super.publish(record);
        //flush();
    }

    class ChangeLogDefaultFolder implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            File file = new File("logs");
            if (!file.exists()){
                file.mkdir();
            }


            JFileChooser chooser = new JFileChooser();

            final int LOG_ROTATION_COUNT = 1;
            Handler handler = null;
            String dirToSave = "%h";
            try {
                chooser.setDialogTitle("Выбери папку для сохранения логов");
                String workingDir = System.getProperty("user.dir");
                chooser.setCurrentDirectory(new File(workingDir + "/logs"));

                int returnVal = chooser.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    dirToSave = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println(dirToSave.substring(0, dirToSave.indexOf(chooser.getName())));
                    chooser.getName();
                    handler = new FileHandler("D:\\На пути к программисту\\Базы данных\\Лаб 2\\SoftwareDevelopmentDatabase\\logs" + "\\LoggingViewer.log", 0, LOG_ROTATION_COUNT, true);
                    Logger.getLogger("gui").addHandler(handler);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
