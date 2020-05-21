package com.ermolin.timesheet.view;

import javax.swing.*;
import java.awt.*;

public class AdminDepFrame extends JFrame {
    public AdminDepFrame() throws HeadlessException {
        super("Администровтор департаментов");
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
