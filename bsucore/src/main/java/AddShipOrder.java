import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import entity.*;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Кирилл on 19.04.2017.
 */
public class AddShipOrder extends JFrame {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable itemsTable;
    private JTextField sum;
    private JButton subm;
    private JButton addButton;
    private JButton deleteButton;
    private JButton refreshTableButton;
    private Controller controller;
    private ArrayList items = new ArrayList();
    private ArrayList queryShippers;
    private ArrayList queryStorages;

    public AddShipOrder(Controller controller) {
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
        $$$setupUI$$$();
        this.controller = controller;
        setContentPane(panel1);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Добавить заказ поставщику");
        initCombos();
        refreshTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.remove(itemsTable.getSelectedRow());
                updateItemTable();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idxStorage = comboBox2.getSelectedIndex();
                AddItemToShip addItemToShip = new AddItemToShip(controller, items, idxStorage);
            }
        });
        subm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShipOrder shipOrder = new ShipOrder();
                Shipper tmp = (Shipper) queryShippers.get(Integer.valueOf(comboBox1.getSelectedIndex()));
                shipOrder.setShipper_id(tmp.getId());
                Storage storage = (Storage) queryStorages.get(Integer.valueOf(comboBox2.getSelectedIndex()));
                shipOrder.setStorage_id(storage.getId());
                shipOrder.setSum(Double.parseDouble(sum.getText()));
                int sellId = add(shipOrder);
                for (Object o : items) {
                    Item o1 = (Item) o;
                    List<Item> tmpItems = controller.getHibernate().get("Item where id=" + o1.getId());
                    Item up = tmpItems.get(0);
                    up.setQuanity(up.getQuanity() + o1.getQuanity());
                    controller.getHibernate().add(up);
                    ShipOrderItem oi = new ShipOrderItem();
                    oi.setShipOrdersId(sellId);
                    oi.setItemsId(o1.getId());
                    controller.getHibernate().add(oi);
                }
            }
        });
    }

    public int add(ShipOrder sellOrder) {
        Session session = controller.getHibernate().getSession();
        session.getTransaction().begin();
        session.save(sellOrder);
        session.getTransaction().commit();
        session.close();
        return sellOrder.getId();
    }

    public void initCombos() {
        queryShippers = controller.getHibernate().get("Shipper");
        for (Object o : queryShippers) {
            Shipper o1 = (Shipper) o;
            comboBox1.addItem(o1.getName());
        }
        queryStorages = controller.getHibernate().get("Storage");
        for (Object o : queryStorages) {
            Storage o1 = (Storage) o;
            comboBox2.addItem(o1.getName());
        }
    }

    public void updateItemTable() {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номенклатура");
        tableHeaders.add("Количество в заказе");
        double sumTmp = 0;
        for (Object o : items) {
            Item o1 = (Item) o;
            Vector<Object> oneRow = new Vector<Object>();
            ArrayList nom = controller.getHibernate().get("Nomenclature where id=" + o1.getNomenclatureId());
            Nomenclature nomenclature = (Nomenclature) nom.get(0);
            sumTmp += nomenclature.getBasePrice();
            oneRow.add(nomenclature.getName());
            oneRow.add(o1.getQuanity());
            tableData.add(oneRow);
        }
        sum.setText(Double.toString(sumTmp));
        itemsTable.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:372px:noGrow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:148dlu:noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        final JLabel label1 = new JLabel();
        label1.setText("Поставщик");
        CellConstraints cc = new CellConstraints();
        panel1.add(label1, cc.xy(3, 1));
        comboBox1 = new JComboBox();
        panel1.add(comboBox1, cc.xy(5, 1));
        final JLabel label2 = new JLabel();
        label2.setText("Склад");
        panel1.add(label2, cc.xy(3, 3));
        comboBox2 = new JComboBox();
        panel1.add(comboBox2, cc.xy(5, 3));
        final JLabel label3 = new JLabel();
        label3.setText("Товары");
        panel1.add(label3, cc.xy(3, 5));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, cc.xy(5, 6, CellConstraints.FILL, CellConstraints.FILL));
        itemsTable = new JTable();
        scrollPane1.setViewportView(itemsTable);
        final JLabel label4 = new JLabel();
        label4.setText("Сумма");
        panel1.add(label4, cc.xy(3, 8));
        sum = new JTextField();
        panel1.add(sum, cc.xy(5, 8, CellConstraints.FILL, CellConstraints.DEFAULT));
        subm = new JButton();
        subm.setText("Записать");
        panel1.add(subm, cc.xy(5, 10));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        panel1.add(toolBar1, cc.xy(5, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        addButton = new JButton();
        addButton.setBorderPainted(false);
        addButton.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addButton.setMultiClickThreshhold(0L);
        addButton.setText("");
        addButton.setToolTipText("Добавить");
        toolBar1.add(addButton);
        deleteButton = new JButton();
        deleteButton.setBorderPainted(false);
        deleteButton.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteButton.setText("");
        deleteButton.setToolTipText("Удалить");
        toolBar1.add(deleteButton);
        refreshTableButton = new JButton();
        refreshTableButton.setBorderPainted(false);
        refreshTableButton.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshTableButton.setMultiClickThreshhold(0L);
        refreshTableButton.setText("");
        refreshTableButton.setToolTipText("Обновить");
        toolBar1.add(refreshTableButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
