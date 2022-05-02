import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableClassModel extends AbstractTableModel {

    public String[] columns;
    public List<Class> rows;

    public TableClassModel(List<Class> classList){

        rows = classList;
        columns = new String[]{"Name", "Size", "Filling"};

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
        Class c = rows.get(rowIndex);
        if(columnIndex == 0)
            return c.getGroup_name();
        if(columnIndex == 1)
            return String.valueOf(c.getMax_student());
        if(columnIndex == 2) {
            return String.valueOf(c.studentlist.size());
        }
        return null;
    }
}
