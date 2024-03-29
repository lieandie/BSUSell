import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import entity.Shipper;
import entity.Storage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Кирилл on 14.04.2017.
 */
public class AddStorage extends JFrame {

    private Controller controller;
    private JPanel panel1;
    private JTextField desc;
    private JButton add;
    private JTextField textField1;

    public AddStorage(final Controller controller, JTable table) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.controller = controller;

        $$$setupUI$$$();

        setContentPane(panel1);

        pack();

        setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);

        setLocationRelativeTo(null);

        setTitle("Добавить склад");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Storage o = new Storage();
                o.setName(textField1.getText());
                o.setDescription(desc.getText());
                controller.getHibernate().add(o);
            }
        });
        controller.updateStorageTable(controller.getHibernate().get("Storage"), table);
    }

    public AddStorage(final Controller controller, Storage storage) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.controller = controller;

        $$$setupUI$$$();

        setContentPane(panel1);

        pack();

        setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);

        setLocationRelativeTo(null);
        textField1.setText(storage.getName());
        desc.setText(storage.getDescription());
        setTitle("Добавить склад");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storage.setName(textField1.getText());
                storage.setDescription(desc.getText());
                controller.getHibernate().add(storage);
            }
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FormLayout("fill:d:noGrow", "center:d:noGrow"));
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:11dlu:noGrow,center:d:noGrow,left:4dlu:noGrow,fill:229px:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        CellConstraints cc = new CellConstraints();
        panel2.add(panel1, cc.xy(1, 1));
        final JLabel label1 = new JLabel();
        label1.setText("Название");
        panel1.add(label1, cc.xy(3, 3));
        final JLabel label2 = new JLabel();
        label2.setText("Описание");
        panel1.add(label2, cc.xy(3, 5));
        desc = new JTextField();
        panel1.add(desc, cc.xy(5, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        add = new JButton();
        add.setText("Добавить");
        panel1.add(add, cc.xy(3, 7));
        textField1 = new JTextField();
        panel1.add(textField1, cc.xy(5, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
    }
}
