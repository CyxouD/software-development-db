package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dima on 02.05.2016.
 */
public class Dialog {

    public Dialog(Object[][] data, String[] fields){
        createDialog(data, fields);

    }

    public void createDialog(Object[][] data, String[] fields){
                JTable jTable = new JTable(data, fields);
                JScrollPane jScrollPane = new JScrollPane(jTable);

                JDialog jDialog = new JDialog();
                jDialog.setSize(500, 400);
                final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - jDialog.getWidth()) / 2;
                final int y = (screenSize.height - jDialog.getHeight()) / 2;
                jDialog.setLocation(x, y);
                jDialog.add(jScrollPane);
                jDialog.setVisible(true);

    }
}
