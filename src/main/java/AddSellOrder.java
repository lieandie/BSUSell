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
 * Created by Кирилл on 14.04.2017.
 */
public class AddSellOrder extends JFrame {
    private JPanel panel1;
    private JComboBox clients;
    private JTable itemsTable;
    private JTextField sum;
    private JButton subm;
    private JButton addButton;
    private JButton deleteButton;
    private JButton refreshTableButton;
    private Controller controller;
    private List itemsList;
    private List clientQuery;

    public AddSellOrder(Controller controller) {
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

        initComboBox();

        itemsList = new ArrayList();

        setContentPane(panel1);

        updateItemTable();

        refreshTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemTable();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItemToDeal addItemToDeal = new AddItemToDeal(controller, itemsList);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemsList.remove(itemsTable.getSelectedRow());
                updateItemTable();
            }
        });
        subm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellOrder sellOrder = new SellOrder();
                Client tmp = (Client) clientQuery.get(Integer.valueOf(clients.getSelectedIndex()));
                sellOrder.setClientId(tmp.getId());
                sellOrder.setSum(Double.parseDouble(sum.getText()));
                int sellId = add(sellOrder);
                for (Object o : itemsList) {
                    Item o1 = (Item) o;
                    List<Item> tmpItems = controller.getHibernate().get("Item where id=" + o1.getId());
                    Item up = tmpItems.get(0);
                    up.setQuanity(up.getQuanity() - o1.getQuanity());
                    controller.getHibernate().add(up);
                    SellOrderItem oi = new SellOrderItem();
                    oi.setSellOrdersId(sellId);
                    oi.setItemsId(o1.getId());
                    oi.setQuanity(o1.getQuanity());
                    controller.getHibernate().add(oi);
                }
            }
        });

        pack();

        setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setResizable(false);

        setLocationRelativeTo(null);

        setTitle("Добавить заказ на продажу");
    }

    public int add(SellOrder sellOrder) {
        Session session = controller.getHibernate().getSession();
        session.getTransaction().begin();
        session.save(sellOrder);
        session.getTransaction().commit();
        session.close();
        return sellOrder.getId();
    }

    public void updateItemTable() {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Номенклатура");
        tableHeaders.add("Склад");
        tableHeaders.add("Количество в заказе");
        double sumTmp = 0;
        for (Object o : itemsList) {
            Item o1 = (Item) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(o1.getId());
            ArrayList nom = controller.getHibernate().get("Nomenclature where id=" + o1.getNomenclatureId());
            Nomenclature nomenclature = (Nomenclature) nom.get(0);
            sumTmp += nomenclature.getWholesalePrice();
            oneRow.add(nomenclature.getName());
            ArrayList stor = controller.getHibernate().get("Storage where id=" + o1.getStorageId());
            Storage storage = (Storage) stor.get(0);
            oneRow.add(storage.getName());
            oneRow.add(o1.getQuanity());
            tableData.add(oneRow);
        }
        sum.setText(Double.toString(sumTmp));
        itemsTable.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    private void initComboBox() {
        clientQuery = controller.getHibernate().get("Client");
        for (Object o : clientQuery) {
            Client o1 = (Client) o;
            clients.addItem(o1.getName());
        }
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
        panel1.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:454px:noGrow,left:4dlu:noGrow,fill:8px:noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:102dlu:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        final JLabel label1 = new JLabel();
        label1.setText("Клиент");
        CellConstraints cc = new CellConstraints();
        panel1.add(label1, cc.xy(1, 3));
        clients = new JComboBox();
        panel1.add(clients, cc.xy(3, 3));
        final JLabel label2 = new JLabel();
        label2.setText("Товары");
        panel1.add(label2, cc.xy(1, 5));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, cc.xy(3, 6, CellConstraints.FILL, CellConstraints.FILL));
        itemsTable = new JTable();
        scrollPane1.setViewportView(itemsTable);
        final JLabel label3 = new JLabel();
        label3.setText("Сумма");
        panel1.add(label3, cc.xy(1, 9));
        sum = new JTextField();
        panel1.add(sum, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        subm = new JButton();
        subm.setText("Записать");
        panel1.add(subm, cc.xy(3, 11));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        panel1.add(toolBar1, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
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
