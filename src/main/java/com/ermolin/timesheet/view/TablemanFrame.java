package com.ermolin.timesheet.view;

import javax.swing.*;
import java.awt.*;

public class TablemanFrame extends JFrame {
    public TablemanFrame() throws HeadlessException {
        super("Табельщик");
        build();
    }

    private void build(){

        JPanel tmp = new JPanel();
        setContentPane(tmp);
        setLocation(50, 100);
        setSize(800, 600);
        setVisible(true);
    }
}