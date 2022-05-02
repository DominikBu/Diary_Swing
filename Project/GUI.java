import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;

public class GUI extends JFrame{
    private JPanel mainPanel;
    private JTextField textField;
    private JButton button_point_up;
    private JButton buttonRemoveStudent;
    private JButton buttonAddStudent;
    private JButton button_point_down;
    private JButton buttonAddClass;
    private JButton buttonRemoveClass;
    private JButton buttonCondition;
    private JTable tableClass;
    private JTable tableStudent;
    private JLabel labelClass;
    private JLabel labelStudent;
    private JButton buttonChangeClass;
    private JButton buttonChangeStudent;
    private JButton buttonFilter;
    private JLabel labelTextfield;
    private ClassContainer group;
    private TableClassModel tableClassModel;
    private TableStudentModel tableStudentModel;
    private Class global_class;
    private Student global_student;
    private int filterStatus;

    public GUI(String title, ClassContainer g)
    {
        super(title);

        filterStatus=0;
        this.group = g;

        tableStudent.setAutoCreateRowSorter(true);
        tableStudent.setFillsViewportHeight(true);
        tableStudent.setPreferredScrollableViewportSize(new Dimension(550, 200));

        tableStudent.setRowSelectionAllowed(true);
        ListSelectionModel cellSelectionStudentModel = tableStudent.getSelectionModel();
        cellSelectionStudentModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        tableClassModel = new TableClassModel(getClassList(group));
        tableClass.setAutoCreateRowSorter(true);
        tableClass.setFillsViewportHeight(true);
        tableClass.setPreferredScrollableViewportSize(new Dimension(550, 200));
        tableClass.setModel(tableClassModel);

        tableClass.setRowSelectionAllowed(true);//lSelectionEnabled(true);
        ListSelectionModel cellSelectionClassModel = tableClass.getSelectionModel();

        refreshClassTable();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        cellSelectionStudentModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String name="";
                String surname="";
                String condition="";
                String year="";
                String points="";

                int[] selectedRow = tableStudent.getSelectedRows();

                for (int i = 0; i < selectedRow.length; i++) {

                    name = (String) tableStudent.getValueAt(selectedRow[i], 0);
                    surname = (String) tableStudent.getValueAt(selectedRow[i], 1);
                    condition = (String) tableStudent.getValueAt(selectedRow[i], 2);
                    year = (String) tableStudent.getValueAt(selectedRow[i], 3);
                    points = (String) tableStudent.getValueAt(selectedRow[i], 4);
                }


                for(Student s: global_class.getStudentlist())
                {
                    if(name.equals(s.getName()))
                        if(surname.equals(s.getSname()))
                            if(condition.equals(s.getStudent_con().toString()))
                                if(year.equals(String.valueOf(s.getYear())))
                                    if(points.equals(String.valueOf(s.getPoints())))
                                        global_student = s;

                }

            }

        });
        cellSelectionClassModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                global_student=null;
                String name="";
                String size="";
                String fill="";

                int[] selectedRow = tableClass.getSelectedRows();

                for (int i = 0; i < selectedRow.length; i++) {

                    name = (String) tableClass.getValueAt(selectedRow[i], 0);
                    size = (String) tableClass.getValueAt(selectedRow[i], 1);
                    fill = (String) tableClass.getValueAt(selectedRow[i], 2);

                }

                for(Class cl : group.m.values())
                {
                    if(cl.getGroup_name().equals(name))
                    {
                        global_class=cl;
                        break;
                    }
                }
                try {
                    refreshStudentTable();
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
            }


        });
        buttonAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                String name = "";

                int i;
                for(i=0; i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    name += text.charAt(i);
                }

                if(name.equals(""))
                    name="null";

                String surname = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    surname += text.charAt(i);
                }

                if(surname.equals(""))
                    surname="null";

                String condition = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    condition += text.charAt(i);
                }

                StudentCondition cond;

                if(condition.equals("chory"))
                    cond=StudentCondition.CHORY;
                else if(condition.equals("odrabiający"))
                    cond=StudentCondition.ODRABIAJACY;
                else
                    cond=StudentCondition.NIEOBECNY;

                String year = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    year += text.charAt(i);
                }

                int y;

                try {
                    y = Integer.parseInt(year);
                }catch(NumberFormatException w) {
                    y=0;
                };

                String points = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    points += text.charAt(i);
                }

                double p;

                try {
                    p = Double.parseDouble(points);
                }catch(NumberFormatException w) {
                    p=0;
                };

                if(p<0.0)
                    p=0.0;

                Student s = new Student(name, surname, cond, y, p);
                try {
                    addStudentToList(s);
                }catch (MyExceptionArth x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                };
            }
        });

        buttonAddClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                String name = "";

                int i;
                for(i=0; i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    name += text.charAt(i);
                }

                String count = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    count += text.charAt(i);
                }

                int c;

                if(name.equals(""))
                    name="null";

                try {
                    c = Integer.parseInt(count);
                }catch(NumberFormatException w) {
                    c=0;
                };
                Class cl = new Class(name, c);
                addClassToList(cl);
            }
        });
        buttonRemoveClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(global_class==null)
                        throw new MyException("Nie wybrano klasy.");
                    group.removeClass(global_class.getGroup_name());
                    refreshClassTable();
                    global_class = null;
                    List<Student> empty_list = new ArrayList<>();
                    tableStudentModel = new TableStudentModel(empty_list);
                    tableStudent.setModel(tableStudentModel);
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }



            }
        });
        buttonRemoveStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(global_student==null)
                        throw new MyException("Nie wybrano studenta.");

                    global_class.remove(global_student);
                    global_student=null;
                    refreshStudentTable();
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }

                refreshClassTable();
            }
        });
        button_point_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(global_student==null)
                        throw new MyException("Nie wybrano studenta.");

                    global_class.addPoints(global_student, 1.0);

                    refreshStudentTable();
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        button_point_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(global_student==null)
                        throw new MyException("Nie wybrano studenta.");
                    global_class.removePoints(global_student, 1.0);

                    refreshStudentTable();
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
                refreshClassTable();
            }
        });
        buttonCondition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StudentCondition cond;
                try{
                    if(global_student==null)
                        throw new MyException("Nie wybrano studenta.");

                    if (global_student.getStudent_con() == StudentCondition.NIEOBECNY)
                        cond = StudentCondition.ODRABIAJACY;
                    else if (global_student.getStudent_con() == StudentCondition.ODRABIAJACY)
                        cond = StudentCondition.CHORY;
                    else
                        cond = StudentCondition.NIEOBECNY;

                    global_class.changeCondition(global_student, cond);

                    refreshStudentTable();
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonChangeClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                String name = "";

                int i;
                for(i=0; i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    name += text.charAt(i);
                }

                String count = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    count += text.charAt(i);
                }

                int c;

                if(name.equals(""))
                    name="null";

                try {
                    c = Integer.parseInt(count);
                }catch(NumberFormatException w) {
                    c=0;
                };
                try{
                    if(global_class==null)
                        throw new MyException("Nie wybrano klasy.");

                    if(c<global_class.getStudentlist().size())
                        c = global_class.getStudentlist().size();

                    group.removeClass(global_class.getGroup_name());
                    for(int j=0; j==0;)
                    {
                        j++;
                        for(Class cl: group.m.values())
                        {
                            if(name.equals(cl.group_name)) {
                                name += "*";
                                j--;
                                break;
                            }
                        }
                    }
                    global_class.setMax_student(c);
                    global_class.setGroup_name(name);
                    group.addClass(name, c);
                    group.setClass(global_class);
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
                refreshClassTable();
            }
        });
        buttonChangeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                String name = "";

                int i;
                for(i=0; i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    name += text.charAt(i);
                }

                if(name.equals(""))
                    name="null";

                String surname = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    surname += text.charAt(i);
                }

                if(surname.equals(""))
                    surname="null";

                String condition = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    condition += text.charAt(i);
                }

                StudentCondition cond;

                if(condition.equals("chory"))
                    cond=StudentCondition.CHORY;
                else if(condition.equals("odrabiający"))
                    cond=StudentCondition.ODRABIAJACY;
                else
                    cond=StudentCondition.NIEOBECNY;

                String year = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    year += text.charAt(i);
                }

                int y;

                try {
                    y = Integer.parseInt(year);
                }catch(NumberFormatException w) {
                    y=0;
                };

                String points = "";

                i++;
                for(;i<text.length(); i++)
                {
                    if(text.charAt(i)==' ')
                        break;

                    points += text.charAt(i);
                }

                double p;

                try {
                    p = Double.parseDouble(points);
                }catch(NumberFormatException w) {
                    p=0;
                };

                if(p<0.0)
                    p=0.0;
                try{
                    if(global_student==null)
                        throw new MyException("Nie wybrano studenta.");
                    global_student.setName(name);
                    global_student.setSname(surname);
                    global_student.setStudent_con(cond);
                    global_student.setYear(y);
                    global_student.setPoints(p);

                    refreshStudentTable();
                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(global_class==null)
                        throw new MyException("Nie wybrano klasy.");
                    if (filterStatus == 0) {
                        String text = textField.getText();
                        if (text == null)
                            return;

                        List<Student> local_list = new ArrayList<>();
                        for (Student s : global_class.searchPartial(text)) {

                            local_list.add(s);
                        }

                        tableStudentModel = new TableStudentModel(local_list);
                        tableStudent.setModel(tableStudentModel);


                        filterStatus = 1;
                    } else {
                        refreshStudentTable();
                        filterStatus = 0;
                    }

                }catch(MyException x){
                    JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public void refreshClassTable(){

        tableClassModel = new TableClassModel(getClassList(group));
        tableClass.setModel(tableClassModel);
        filterStatus = 0;
    }

    public List<Class> getClassList(ClassContainer group)
    {
        List<Class> lista = new ArrayList<>();

        for(Class cl : group.m.values())
        {
            lista.add(cl);
        }

        return lista;
    }

    public void refreshStudentTable() throws MyException{
        if(global_class == null)
            throw new MyException("Nie wybrano klasy.");
        tableStudentModel = new TableStudentModel(getStudentList(global_class));
        tableStudent.setModel(tableStudentModel);

        filterStatus = 0;
    }

    public List<Student> getStudentList(Class cl)
    {
        List<Student> lista = new ArrayList<>();

        for(Student s : cl.studentlist)
        {
            lista.add(s);
        }

        return lista;
    }

    public void addClassToList(Class c)
    {
        for(int j=0; j==0;)
        {
            j++;
            for(Class cl: group.m.values())
            {
                if(c.getGroup_name().equals(cl.getGroup_name())) {
                    c.setGroup_name(c.getGroup_name()+"*");
                    j--;
                    break;
                }
            }
        }
        group.setClass(c);
        refreshClassTable();
    }

    public void addStudentToList(Student s)
    {
        try {
            if(global_class==null)
                throw new MyException("Nie wybrano klasy.");
            global_class.addStudent(s);
            refreshStudentTable();
        }catch(MyException x){
            JOptionPane.showMessageDialog(null, x, "Błąd operacji!", JOptionPane.WARNING_MESSAGE);
        }
        refreshClassTable();
    }
}
