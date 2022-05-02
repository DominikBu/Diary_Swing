import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableStudentModel extends AbstractTableModel {

    public String[] columns;
    public List<Student> rows;

    public TableStudentModel(List<Student> studentList){

        rows = studentList;
        columns = new String[]{"Name", "Surname", "Condition" , "Year", "Points"};
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = rows.get(rowIndex);
        if(columnIndex == 0)
            return s.getName();
        if(columnIndex == 1)
            return s.getSname();
        if(columnIndex == 2)
            return s.getStudent_con().toString();
        if(columnIndex == 3)
            return String.valueOf(s.getYear());
        if(columnIndex == 4)
            return String.valueOf(s.getPoints());

        return null;
    }
}