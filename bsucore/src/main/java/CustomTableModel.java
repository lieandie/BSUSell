import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Created by Кирилл on 13.04.2017.
 */
public class CustomTableModel extends DefaultTableModel {
    public CustomTableModel(Vector data, Vector columnName) {
        super(data, columnName);
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}
