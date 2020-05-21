package com.ermolin.timesheet.view;

import javax.swing.*;
import java.awt.*;

public class AdminEmployee extends JFrame {
    public AdminEmployee() throws HeadlessException {
        super("Администровтор работников");
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