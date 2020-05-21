package com.ermolin.timesheet.view;

import com.ermolin.timesheet.beans.DayType;
import com.ermolin.timesheet.beans.Department;
import com.ermolin.timesheet.beans.PersonCalendar;
import com.ermolin.timesheet.dao.DepartmentDAOJDBC;
import com.ermolin.timesheet.dao.PersonalCalendarDAO;
import com.ermolin.timesheet.dao.PersonalCalendarDAOJDBC;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.List;

public class TableFrame extends JFrame {
    private Department department;
    private int index;
    private JTable tbl;
    private List<Department> departmentList;
    private int monthIndex;
    private JPanel jan;
    private JPanel feb;
    private JPanel mar;
    private JPanel apr;
    private JPanel may;
    private JPanel jun;
    private JPanel jul;
    private JPanel aug;
    private JPanel sep;
    private JPanel oct;
    private JPanel nov;
    private JPanel dec;

    public TableFrame() throws HeadlessException {
        super("Табель");
        build();
    }


    private void build(){

        /*JMenuBar menuBar = new JMenuBar();
        JMenu window = new JMenu("Окно");
        JMenuItem tableman = new JMenuItem("Табельщик");
        JMenuItem adminDep = new JMenuItem("Администратор департаментов");
        adminDep.addActionListener(e -> new AdminDepFrame());
        JMenuItem adminEmployee = new JMenuItem("Администратор работников");
        adminEmployee.addActionListener(e -> new AdminEmployee());
        JMenuItem save = new JMenuItem("Сохранить");
        save.setEnabled(false);
        window.add(tableman);
        window.add(adminDep);
        window.add(adminEmployee);
        window.add(save);
        menuBar.add(window);
        setJMenuBar(menuBar);*/

        JPanel middle = new JPanel();
        middle.setAlignmentX(CENTER_ALIGNMENT);
        middle.setAlignmentY(Component.TOP_ALIGNMENT);
        JPanel depPanel = new JPanel();

        depPanel.setPreferredSize(new Dimension(150,50));

        Font font = new Font("Verdana", Font.CENTER_BASELINE, 12);

        depPanel.setLayout(new BoxLayout(depPanel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Департаменты");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(font);
        depPanel.add(label);


        departmentList = new DepartmentDAOJDBC().getAllDepartments();
        department = departmentList.get(0);
        String[] items = new String[departmentList.size()];
        for (int i = 0; i <items.length ; i++) items[i] = departmentList.get(i).getName();

        JComboBox comboBox = new JComboBox(items);
        comboBox.setAlignmentX(CENTER_ALIGNMENT);

        depPanel.add(comboBox);
        middle.add(depPanel);

        tbl = new JTable(new DAOColumnTableModel()){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width,
                        tableColumn.getPreferredWidth()));
                return component;
            }
        };


        tbl.setEnabled(false);
        tbl.setAutoscrolls(true);
        tbl.setFillsViewportHeight(true);
        tbl.setGridColor(Color.BLACK);
        tbl.setAutoCreateRowSorter(true);
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        comboBox.addActionListener(e -> {
                    JComboBox box = (JComboBox)e.getSource();
                    index = box.getSelectedIndex();
                    department = departmentList.get(index);
                    tbl.setModel(new DAOColumnTableModel());
        });

        JTabbedPane monthsPanel = new JTabbedPane();
        monthsPanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        jan = new JPanel();
        jan.setLayout(new GridLayout());
        feb = new JPanel();
        feb.setLayout(new GridLayout());
        mar = new JPanel();
        mar.setLayout(new GridLayout());
        apr = new JPanel();
        apr.setLayout(new GridLayout());
        may = new JPanel();
        may.setLayout(new GridLayout());
        jun = new JPanel();
        jun.setLayout(new GridLayout());
        jul = new JPanel();
        jul.setLayout(new GridLayout());
        aug = new JPanel();
        aug.setLayout(new GridLayout());
        sep = new JPanel();
        sep.setLayout(new GridLayout());
        oct = new JPanel();
        oct.setLayout(new GridLayout());
        nov = new JPanel();
        nov.setLayout(new GridLayout());
        dec = new JPanel();
        dec.setLayout(new GridLayout());

        jan.add(new JScrollPane(tbl));


        monthsPanel.addTab("Январь" , jan);
        monthsPanel.addTab("Февраль", feb);
        monthsPanel.addTab("Март", mar);
        monthsPanel.addTab("Апрель", apr);
        monthsPanel.addTab("Май", may);
        monthsPanel.addTab("Июнь", jun);
        monthsPanel.addTab("Июль", jul);
        monthsPanel.addTab("Август", aug);
        monthsPanel.addTab("Сентябрь", sep);
        monthsPanel.addTab("Октябрь", oct);
        monthsPanel.addTab("Ноябрь", nov);
        monthsPanel.addTab("Декабрь", dec);

        monthsPanel.setBackground(Color.white);
        monthsPanel.setForeground(Color.black);

        monthsPanel.setFont(font);

        monthsPanel.addChangeListener(e -> {
            JPanel find = (JPanel)monthsPanel.getSelectedComponent();
            setMonthIndex(find);
        });

        JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(middle),
                new JScrollPane(monthsPanel));

        mainPanel.setDividerLocation(170);

        setContentPane(mainPanel);
        setLocation(50, 100);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void setMonthIndex(JPanel find){
        if(find == null) return;
        if (find.equals(feb)) {
            monthIndex = 1;
            tbl.setModel(new DAOColumnTableModel());
            if (feb.getComponentCount()!= 0)feb.remove(0);
            feb.add(new JScrollPane(tbl));
        }
        else if (find.equals(jan)) {
            monthIndex = 0;
            tbl.setModel(new DAOColumnTableModel());
            jan.remove(0);
            jan.add(new JScrollPane(tbl));
        }
        else if (find.equals(mar)) {
            monthIndex = 2;
            tbl.setModel(new DAOColumnTableModel());
            if (mar.getComponentCount()!= 0)mar.remove(0);
            mar.add(new JScrollPane(tbl));
        }
        else if (find.equals(apr)) {
            monthIndex = 3;
            tbl.setModel(new DAOColumnTableModel());
            if (apr.getComponentCount()!= 0)apr.remove(0);
            apr.add(new JScrollPane(tbl));
        }
        else if (find.equals(may)) {
            monthIndex = 4;
            tbl.setModel(new DAOColumnTableModel());
            if (may.getComponentCount()!= 0)may.remove(0);
            may.add(new JScrollPane(tbl));
        }
        else if (find.equals(jun)) {
            monthIndex = 5;
            tbl.setModel(new DAOColumnTableModel());
            if (jun.getComponentCount()!= 0)jun.remove(0);
            jun.add(new JScrollPane(tbl));
        }
        else if (find.equals(jul)) {
            monthIndex = 6;
            tbl.setModel(new DAOColumnTableModel());
            if (jul.getComponentCount()!= 0)jul.remove(0);
            jul.add(new JScrollPane(tbl));
        }
        else if (find.equals(aug)) {
            monthIndex = 7;
            tbl.setModel(new DAOColumnTableModel());
            if (aug.getComponentCount()!= 0)aug.remove(0);
            aug.add(new JScrollPane(tbl));
        }
        else if (find.equals(sep)) {
            monthIndex = 8;
            tbl.setModel(new DAOColumnTableModel());
            if (sep.getComponentCount()!= 0)sep.remove(0);
            sep.add(new JScrollPane(tbl));
        }
        else if (find.equals(oct)) {
            monthIndex = 9;
            tbl.setModel(new DAOColumnTableModel());
            if (oct.getComponentCount()!= 0)oct.remove(0);
            oct.add(new JScrollPane(tbl));
        }
        else if (find.equals(nov)) {
            monthIndex = 10;
            tbl.setModel(new DAOColumnTableModel());
            if (nov.getComponentCount()!= 0)nov.remove(0);
            nov.add(new JScrollPane(tbl));
        }
        else if (find.equals(dec)) {
            monthIndex = 11;
            tbl.setModel(new DAOColumnTableModel());
            if (dec.getComponentCount()!= 0)dec.remove(0);
            dec.add(new JScrollPane(tbl));
        }
    }

    private class DAOColumnTableModel extends AbstractTableModel {
        private Object[][] data = dataForModel();
        private String[] columnNames = dataForNames();

        private DAOColumnTableModel() { super();}

        public int getColumnCount(){
            return columnNames.length;
        }
        public int getRowCount(){
            return data.length;
        }
        public String getColumnName(int col){
            return columnNames[col];
        }
        public Object getValueAt(int row, int col){
            return data[row][col];
        }
        public Class getColumnClass(int col){
            return getValueAt(0, col).getClass();
        }
        public boolean isCellEditable(int row, int col){
            return true;
        }
        public void setValueAt(Object value, int row, int col){
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        private String[] showCalendar(){
            switch (monthIndex){
                case 0 : return nums(31);
                case 1 : return nums(29);
                case 2: return nums(31);
                case 3: return nums(30);
                case 4: return nums(31);
                case 5: return nums(30);
                case 6: return nums(31);
                case 7: return nums(31);
                case 8: return nums(30);
                case 9: return nums(31);
                case 10: return nums(30);
                case 11: return nums(31);
            }
            return null;
        }

        private String[] nums(int count){
            String[] res= new String[count];
            for (int i = 0; i < count; i++) {
                res[i] = String.valueOf(i +1);
            }
            return res;
        }

        private Object[] fillWorkDays(int count){
            Object[] res = new Object[count];
            for (int i = 0; i < res.length; i++) {
                res[i] = "";
            }
            return res;
        }


        private Object[] fillWorkDays(int count, int id){
            Object[] res = new Object[count + 1];
            PersonalCalendarDAO personalCalendarDAO = new PersonalCalendarDAOJDBC();
            PersonCalendar calendar = personalCalendarDAO.getCalendar(count, id, monthIndex, index);
            DayType[] dayTypeList = calendar.getDays();
            if (dayTypeList.length == 0){
                return fillWorkDays(count +1);
            }
            int countWork = 0;
            int countSick = 0;
            int countAbsent = 0;
            int countWeekend = 0;
            int countBT = 0;
            int countVacation = 0;
            int countFur = 0;
            int countSL = 0;
            int countLFC = 0;
            int countWOW =0;
            int countBD = 0;
            for (int i = 0; i < count; i++) {
                if (dayTypeList[i] != null){
                    DayType dayType = dayTypeList[i];
                    res[i] = dayType.getName();
                    switch (dayType){
                        case WORK: countWork++;
                            break;
                        case WEEKEND: countWeekend++;
                            break;
                        case WORK_ON_WEEKEND: countWOW++;
                            break;
                        case SICK: countSick++;
                            break;
                        case ABSENT:  countAbsent++;
                            break;
                        case BUSINESS_TRIP: countBT++;
                            break;
                        case VACATION: countVacation++;
                            break;
                        case FURLOUGH: countFur++;
                            break;
                        case BUSINESS_DAY: countBD++;
                            break;
                        case STUDY_LEAVE: countSL++;
                            break;
                        case LEAVE_FOR_CHILDREN: countLFC++;
                            break;
                    }
                }else {
                    res[i] = "";
                }
            }
            StringBuilder sb = new StringBuilder();
            if (countWork != 0 ) sb.append(DayType.WORK.getName()).append(" = ").append(countWork).append(", ");
            if (countWeekend != 0 ) sb.append(DayType.WEEKEND.getName()).append(" = ").append(countWeekend).append(", ");
            if (countWOW != 0 ) sb.append(DayType.WORK_ON_WEEKEND.getName()).append(" = ").append(countWOW).append(", ");
            if (countSick != 0 ) sb.append(DayType.SICK.getName()).append(" = ").append(countSick).append(", ");
            if (countAbsent != 0 ) sb.append(DayType.ABSENT.getName()).append(" = ").append(countAbsent).append(", ");
            if (countBT != 0 ) sb.append(DayType.BUSINESS_TRIP.getName()).append(" = ").append(countBT).append(", ");
            if (countVacation != 0 ) sb.append(DayType.VACATION.getName()).append(" = ").append(countVacation).append(", ");
            if (countFur != 0 ) sb.append(DayType.FURLOUGH.getName()).append(" = ").append(countFur).append(", ");
            if (countBD != 0 ) sb.append(DayType.BUSINESS_DAY.getName()).append(" = ").append(countBD).append(", ");
            if (countSL != 0 ) sb.append(DayType.STUDY_LEAVE.getName()).append(" = ").append(countSL).append(", ");
            if (countLFC != 0 ) sb.append(DayType.LEAVE_FOR_CHILDREN.getName()).append(" = ").append(countLFC).append(", ");
            if (sb.length()>0)sb.replace(sb.length()-2, sb.length(), ".");
            res[count] = sb.toString();
            return res;
        }

        private Object[] fillCalendar(int id){
            switch (monthIndex){
                case 0 : return fillWorkDays(31, id);
                case 1 : return fillWorkDays(29, id);
                case 2 : return fillWorkDays(31, id);
                case 3 : return fillWorkDays(30, id);
                case 4 : return fillWorkDays(31, id);
                case 5 : return fillWorkDays(30, id);
                case 6 : return fillWorkDays(31, id);
                case 7 : return fillWorkDays(31, id);
                case 8 : return fillWorkDays(30, id);
                case 9 : return fillWorkDays(31, id);
                case 10: return fillWorkDays(30, id);
                case 11: return fillWorkDays(31, id);
            }
            return null;
        }

        private Object[][] dataForModel(){
            int count = departmentList.get(index).getEmployees().size();
            Object[][] data = new Object[count][];
            for(int i = 0 ; i < count; i++){
                Object[] tmp1 = {
                        departmentList.get(index).getEmployees().get(i).getName() + " " +
                                departmentList.get(index).getEmployees().get(i).getSurname(),
                        departmentList.get(index).getEmployees().get(i).getPosition(),
                        departmentList.get(index).getEmployees().get(i).getId()};
                Object[] tmp2 = fillCalendar(department.getEmployees().get(i).getId());

                data[i]=new Object[tmp1.length + tmp2.length + 1];
                for (int j = 0; j < tmp1.length; j++) data[i][j] = tmp1[j];
                for (int j = 0; j < tmp2.length; j++) data[i][j + tmp1.length] = tmp2[j];
            }
            return data;
        }

        private String[] dataForNames(){
            String[] monDates = showCalendar();
            String[] names = { "ФИО", "Должность", "Табельный номер", "Итого" };
            String[] columnNames = new String[names.length + monDates.length];
            for (int i = 0; i < names.length; i++)
                columnNames[i] = names[i];
            for (int i = 0; i < monDates.length ; i++)
                columnNames[i+3] = monDates[i];
            return columnNames;
        }

    }


}
