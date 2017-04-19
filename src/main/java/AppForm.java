import entity.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Кирилл on 12.04.2017.
 */
public class AppForm extends JFrame {
    private Controller controller;
    private JTabbedPane menu;
    private JPanel wrapPanel;
    private JTable clientsTable;
    private JPanel clientsTab;
    private JPanel shipOrdersTab;
    private JPanel dealsTab;
    private JPanel shippersTab;
    private JPanel nomenclatureTab;
    private JPanel storageTab;
    private JPanel itemsInStorageTab;
    private JTable shipOrdersTable;
    private JTable shippersTable;
    private JTable nomenclatureTable;
    private JTable storageTable;
    private JTable itemsInStorageTable;
    private JTable dealsTable;
    private JButton addClientBtn;
    private JToolBar clientsToolbar;
    private JButton addClientButton;
    private JButton deleteClientButton;
    private JButton refreshClientTableButton;
    private JScrollPane clientTableWrap;
    private JScrollPane shipOrderTableWrap;
    private JScrollPane shippersTableWrap;
    private JScrollPane nomenclatureTableWrap;
    private JScrollPane storageTableWrap;
    private JScrollPane itemsInStorageTableWrap;
    private JScrollPane dealsTableWrap;
    private JToolBar shipOrdersToolbar;
    private JToolBar shippersToolbar;
    private JToolBar nomenclatureToolbar;
    private JToolBar storageToolbar;
    private JToolBar itemsInStorageToolbar;
    private JToolBar dealsToolbar;
    private JButton addShipOrder;
    private JButton deleteShipOrder;
    private JButton refreshShipOrder;
    private JButton addShipper;
    private JButton deleteShipper;
    private JButton refreshShipper;
    private JButton addNomenclature;
    private JButton deleteNomenclature;
    private JButton refreshNomenclature;
    private JButton addStorage;
    private JButton deleteStorage;
    private JButton refreshStorage;
    private JButton addItemsInStorage;
    private JButton deleteItemsInStorage;
    private JButton refreshItemsInStorage;
    private JButton addDeals;
    private JButton deleteDeals;
    private JButton refreshDeals;


    public AppForm(Controller controller) {
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
        setTitle("Продажа радиодеталей");
        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        this.setIconImage(icon);
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Файл");
        JMenu menu1 = new JMenu("Правка");
        JMenu menu2 = new JMenu("Сервис");
        JMenu menu3 = new JMenu("Словари");
        JMenu menu4 = new JMenu("Помощь");
        JMenu otchet = new JMenu("Отчеты");
        otchet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Report report = new Report(controller);
            }
        });
        JMenuItem itm = new JMenuItem("Сохранить");
        menu.add(itm);
        itm = new JMenuItem("Открыть");
        menu.add(itm);
        itm = new JMenuItem("Закрыть");
        menu.add(itm);
        menu.add(new JSeparator());
        JMenu submenu = new JMenu("Дополнительно");
        itm = new JMenuItem("Печать");
        submenu.add(itm);
        itm = new JMenuItem("Экспорт");
        submenu.add(itm);
        menu.add(submenu);
        menubar.add(menu);
        itm = new JMenuItem("Найти");
        menu1.add(itm);
        itm = new JMenuItem("Вставить");
        menu1.add(itm);
        itm = new JMenuItem("Изменить");
        menu1.add(itm);
        menubar.add(menu1);
        itm = new JMenuItem("Настройки");
        menu2.add(itm);
        itm = new JMenuItem("Отображение");
        menu2.add(itm);
        itm = new JMenuItem("Нагрузка системы");
        menu2.add(itm);
        itm = new JMenuItem("Текущие сессии");
        menu2.add(itm);
        menubar.add(menu2);
        itm = new JMenuItem("Редактирование словарей");
        menu3.add(itm);
        itm = new JMenuItem("Загрузка словарей");
        menu3.add(itm);
        itm = new JMenuItem("Обновление базы");
        menu3.add(itm);
        menubar.add(menu3);
        menubar.add(menu4);
        menubar.add(otchet);
        this.setJMenuBar(menubar);
        initListeners();
        setContentPane(wrapPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setMinimumSize(new Dimension(600, 600));
        controller.updateClientTable(controller.getHibernate().get("Client"), clientsTable);
    }


    private void initListeners() {
        refreshShipper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.updateShipperTable(controller.getHibernate().get("Shipper"), shippersTable);
            }
        });
        refreshStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.updateStorageTable(controller.getHibernate().get("Storage"), storageTable);
            }
        });
        refreshClientTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.updateClientTable(controller.getHibernate().get("Client"), clientsTable);
            }
        });
        refreshItemsInStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.updateItemTable(controller.getHibernate().get("Item"), itemsInStorageTable);
            }
        });
        deleteItemsInStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) itemsInStorageTable.getModel().getValueAt(itemsInStorageTable.getSelectedRow(), 0);
                Item o = new Item();
                o.setId(idx);
                controller.getHibernate().delete(o);
                controller.updateClientTable(controller.getHibernate().get("Item"), itemsInStorageTable);
            }
        });
        deleteClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) clientsTable.getModel().getValueAt(clientsTable.getSelectedRow(), 0);
                Client client = new Client();
                client.setId(idx);
                controller.getHibernate().delete(client);
                controller.updateClientTable(controller.getHibernate().get("Client"), clientsTable);
            }
        });
        deleteNomenclature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) nomenclatureTable.getModel().getValueAt(nomenclatureTable.getSelectedRow(), 0);
                Nomenclature o = new Nomenclature();
                o.setId(idx);
                controller.getHibernate().delete(o);
                controller.updateNomenclatureTable(controller.getHibernate().get("Nomenclature"), nomenclatureTable);
            }
        });
        deleteStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) storageTable.getModel().getValueAt(storageTable.getSelectedRow(), 0);
                Storage o = new Storage();
                o.setId(idx);
                controller.getHibernate().delete(o);
                controller.updateStorageTable(controller.getHibernate().get("Storage"), storageTable);
            }
        });
        deleteShipper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) shippersTable.getModel().getValueAt(shippersTable.getSelectedRow(), 0);
                Shipper o = new Shipper();
                o.setId(idx);
                controller.getHibernate().delete(o);
                controller.updateShipperTable(controller.getHibernate().get("Shipper"), shippersTable);
            }
        });
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddClient addFrame = new AddClient(controller, clientsTable);
            }
        });

        refreshNomenclature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.updateNomenclatureTable(controller.getHibernate().get("Nomenclature"), nomenclatureTable);
            }
        });
        addNomenclature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddNomenclature addNomenclature = new AddNomenclature(controller, nomenclatureTable);
            }
        });
        addShipper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddShipper addShipper = new AddShipper(controller, shippersTable);
            }
        });
        addItemsInStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddItem addItem = new AddItem(controller, itemsInStorageTable);
            }
        });
        addStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStorage addStorage = new AddStorage(controller, storageTable);
            }
        });
        addDeals.addActionListener((e) -> {
            AddSellOrder addSellOrder = new AddSellOrder(controller);
        });
        refreshDeals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.updateSellOrdersTable(controller.getHibernate().get("SellOrder"), dealsTable);
            }
        });
        deleteDeals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) dealsTable.getModel().getValueAt(dealsTable.getSelectedRow(), 0);
                ArrayList list = controller.getHibernate().get("SellOrderItem where sellOrdersId=" + idx);
                for (Object o : list) {
                    controller.getHibernate().delete(o);
                }
                SellOrder o = new SellOrder();
                o.setId(idx);
                controller.getHibernate().delete(o);
                controller.updateSellOrdersTable(controller.getHibernate().get("SellOrder"), dealsTable);
            }
        });
        refreshShipOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.updateShipOrderTable(controller.getHibernate().get("ShipOrder"), shipOrdersTable);
            }
        });
        addShipOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddShipOrder addShipOrder = new AddShipOrder(controller);
            }
        });
        deleteShipOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer idx = (Integer) shipOrdersTable.getModel().getValueAt(shipOrdersTable.getSelectedRow(), 0);
                ArrayList list = controller.getHibernate().get("ShipOrderItem where shipOrdersId=" + idx);
                for (Object o : list) {
                    controller.getHibernate().delete(o);
                }
                ShipOrder o = new ShipOrder();
                o.setId(idx);
                controller.getHibernate().delete(o);
                controller.updateSellOrdersTable(controller.getHibernate().get("ShipOrder"), shipOrdersTable);
            }
        });
        menu.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                switch (menu.getSelectedIndex()) {
                    case 0: {
                        controller.updateClientTable(controller.getHibernate().get("Client"), clientsTable);
                        break;
                    }
                    case 1: {
                        controller.updateNomenclatureTable(controller.getHibernate().get("Nomenclature"), nomenclatureTable);
                        break;
                    }
                    case 2: {
                        controller.updateStorageTable(controller.getHibernate().get("Storage"), storageTable);
                        break;
                    }
                    case 3: {
                        controller.updateShipperTable(controller.getHibernate().get("Shipper"), shippersTable);
                        break;
                    }
                    case 4: {
                        controller.updateItemTable(controller.getHibernate().get("Item"), itemsInStorageTable);
                        break;
                    }
                    case 5: {
                        controller.updateShipOrderTable(controller.getHibernate().get("ShipOrder"), shipOrdersTable);
                        break;
                    }
                    case 6: {
                        controller.updateSellOrdersTable(controller.getHibernate().get("SellOrder"), dealsTable);
                        break;
                    }
                }
            }
        });
        clientsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int id = (Integer) clientsTable.getValueAt(clientsTable.getSelectedRow(), 0);
                    ArrayList list = controller.getHibernate().get("Client where id=" + id);
                    AddClient addClient = new AddClient(controller, (Client) list.get(0));
                }
            }
        });
        nomenclatureTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int id = (Integer) nomenclatureTable.getValueAt(nomenclatureTable.getSelectedRow(), 0);
                    ArrayList list = controller.getHibernate().get("Nomenclature where id=" + id);
                    AddNomenclature addNomenclature = new AddNomenclature(controller, (Nomenclature) list.get(0));
                }
            }
        });
        itemsInStorageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int id = (Integer) itemsInStorageTable.getValueAt(itemsInStorageTable.getSelectedRow(), 0);
                    ArrayList list = controller.getHibernate().get("Item where id=" + id);
                    AddItem addItem = new AddItem(controller, (Item) list.get(0));
                }
            }
        });
        storageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int id = (Integer) storageTable.getValueAt(storageTable.getSelectedRow(), 0);
                    ArrayList list = controller.getHibernate().get("Storage where id=" + id);
                    AddStorage addStorage = new AddStorage(controller, (Storage) list.get(0));
                }
            }
        });
        shippersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int id = (Integer) shippersTable.getValueAt(shippersTable.getSelectedRow(), 0);
                    ArrayList list = controller.getHibernate().get("Shipper where id=" + id);
                    AddShipper addShipper = new AddShipper(controller, (Shipper) list.get(0));
                }
            }
        });
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        wrapPanel = new JPanel();
        wrapPanel.setLayout(new BorderLayout(0, 0));
        menu = new JTabbedPane();
        menu.setTabLayoutPolicy(1);
        wrapPanel.add(menu, BorderLayout.CENTER);
        clientsTab = new JPanel();
        clientsTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Клиенты", clientsTab);
        clientsToolbar = new JToolBar();
        clientsToolbar.setEnabled(true);
        clientsToolbar.setFloatable(false);
        clientsTab.add(clientsToolbar, BorderLayout.NORTH);
        addClientButton = new JButton();
        addClientButton.setBorderPainted(false);
        addClientButton.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addClientButton.setMultiClickThreshhold(0L);
        addClientButton.setText("");
        addClientButton.setToolTipText("Добавить");
        clientsToolbar.add(addClientButton);
        deleteClientButton = new JButton();
        deleteClientButton.setBorderPainted(false);
        deleteClientButton.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteClientButton.setText("");
        deleteClientButton.setToolTipText("Удалить");
        clientsToolbar.add(deleteClientButton);
        refreshClientTableButton = new JButton();
        refreshClientTableButton.setBorderPainted(false);
        refreshClientTableButton.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshClientTableButton.setMultiClickThreshhold(0L);
        refreshClientTableButton.setText("");
        refreshClientTableButton.setToolTipText("Обновить");
        clientsToolbar.add(refreshClientTableButton);
        clientTableWrap = new JScrollPane();
        clientsTab.add(clientTableWrap, BorderLayout.CENTER);
        clientsTable = new JTable();
        clientsTable.setAutoCreateColumnsFromModel(true);
        clientsTable.setEditingColumn(-1);
        clientsTable.setEditingRow(-1);
        clientsTable.setEnabled(true);
        clientTableWrap.setViewportView(clientsTable);
        nomenclatureTab = new JPanel();
        nomenclatureTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Номенклатура", nomenclatureTab);
        nomenclatureTableWrap = new JScrollPane();
        nomenclatureTab.add(nomenclatureTableWrap, BorderLayout.CENTER);
        nomenclatureTable = new JTable();
        nomenclatureTableWrap.setViewportView(nomenclatureTable);
        nomenclatureToolbar = new JToolBar();
        nomenclatureToolbar.setFloatable(false);
        nomenclatureTab.add(nomenclatureToolbar, BorderLayout.NORTH);
        addNomenclature = new JButton();
        addNomenclature.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addNomenclature.setText("");
        nomenclatureToolbar.add(addNomenclature);
        deleteNomenclature = new JButton();
        deleteNomenclature.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteNomenclature.setText("");
        nomenclatureToolbar.add(deleteNomenclature);
        refreshNomenclature = new JButton();
        refreshNomenclature.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshNomenclature.setText("");
        nomenclatureToolbar.add(refreshNomenclature);
        storageTab = new JPanel();
        storageTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Склады", storageTab);
        storageTableWrap = new JScrollPane();
        storageTab.add(storageTableWrap, BorderLayout.CENTER);
        storageTable = new JTable();
        storageTableWrap.setViewportView(storageTable);
        storageToolbar = new JToolBar();
        storageToolbar.setFloatable(false);
        storageTab.add(storageToolbar, BorderLayout.NORTH);
        addStorage = new JButton();
        addStorage.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addStorage.setText("");
        storageToolbar.add(addStorage);
        deleteStorage = new JButton();
        deleteStorage.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteStorage.setText("");
        storageToolbar.add(deleteStorage);
        refreshStorage = new JButton();
        refreshStorage.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshStorage.setText("");
        storageToolbar.add(refreshStorage);
        shippersTab = new JPanel();
        shippersTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Поставщики", shippersTab);
        shippersTableWrap = new JScrollPane();
        shippersTab.add(shippersTableWrap, BorderLayout.CENTER);
        shippersTable = new JTable();
        shippersTableWrap.setViewportView(shippersTable);
        shippersToolbar = new JToolBar();
        shippersToolbar.setFloatable(false);
        shippersTab.add(shippersToolbar, BorderLayout.NORTH);
        addShipper = new JButton();
        addShipper.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addShipper.setText("");
        shippersToolbar.add(addShipper);
        deleteShipper = new JButton();
        deleteShipper.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteShipper.setText("");
        shippersToolbar.add(deleteShipper);
        refreshShipper = new JButton();
        refreshShipper.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshShipper.setText("");
        shippersToolbar.add(refreshShipper);
        itemsInStorageTab = new JPanel();
        itemsInStorageTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Товары на складах", itemsInStorageTab);
        itemsInStorageTableWrap = new JScrollPane();
        itemsInStorageTab.add(itemsInStorageTableWrap, BorderLayout.CENTER);
        itemsInStorageTable = new JTable();
        itemsInStorageTableWrap.setViewportView(itemsInStorageTable);
        itemsInStorageToolbar = new JToolBar();
        itemsInStorageToolbar.setFloatable(false);
        itemsInStorageTab.add(itemsInStorageToolbar, BorderLayout.NORTH);
        addItemsInStorage = new JButton();
        addItemsInStorage.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addItemsInStorage.setText("");
        itemsInStorageToolbar.add(addItemsInStorage);
        deleteItemsInStorage = new JButton();
        deleteItemsInStorage.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteItemsInStorage.setText("");
        itemsInStorageToolbar.add(deleteItemsInStorage);
        refreshItemsInStorage = new JButton();
        refreshItemsInStorage.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshItemsInStorage.setText("");
        itemsInStorageToolbar.add(refreshItemsInStorage);
        shipOrdersTab = new JPanel();
        shipOrdersTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Заказы поставщикам", shipOrdersTab);
        shipOrderTableWrap = new JScrollPane();
        shipOrdersTab.add(shipOrderTableWrap, BorderLayout.CENTER);
        shipOrdersTable = new JTable();
        shipOrderTableWrap.setViewportView(shipOrdersTable);
        shipOrdersToolbar = new JToolBar();
        shipOrdersToolbar.setFloatable(false);
        shipOrdersTab.add(shipOrdersToolbar, BorderLayout.NORTH);
        addShipOrder = new JButton();
        addShipOrder.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addShipOrder.setText("");
        shipOrdersToolbar.add(addShipOrder);
        deleteShipOrder = new JButton();
        deleteShipOrder.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteShipOrder.setText("");
        shipOrdersToolbar.add(deleteShipOrder);
        refreshShipOrder = new JButton();
        refreshShipOrder.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshShipOrder.setText("");
        shipOrdersToolbar.add(refreshShipOrder);
        dealsTab = new JPanel();
        dealsTab.setLayout(new BorderLayout(0, 0));
        menu.addTab("Заказы покупателей", dealsTab);
        dealsTableWrap = new JScrollPane();
        dealsTab.add(dealsTableWrap, BorderLayout.CENTER);
        dealsTable = new JTable();
        dealsTableWrap.setViewportView(dealsTable);
        dealsToolbar = new JToolBar();
        dealsToolbar.setFloatable(false);
        dealsTab.add(dealsToolbar, BorderLayout.NORTH);
        addDeals = new JButton();
        addDeals.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        addDeals.setText("");
        dealsToolbar.add(addDeals);
        deleteDeals = new JButton();
        deleteDeals.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        deleteDeals.setText("");
        dealsToolbar.add(deleteDeals);
        refreshDeals = new JButton();
        refreshDeals.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refreshDeals.setText("");
        dealsToolbar.add(refreshDeals);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return wrapPanel;
    }
}
