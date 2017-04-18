import entity.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

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
        initListeners();
        setContentPane(menu);
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
                AddClient addFrame = new AddClient(controller);
            }
        });
        clientsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println(clientsTable.getSelectedRow());
                }
            }
        });
        refreshNomenclature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.updateNomenclatureTable(controller.getHibernate().get("Nomenclature"), nomenclatureTable);
            }
        });
        addNomenclature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddNomenclature addNomenclature = new AddNomenclature(controller);
            }
        });
        addShipper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddShipper addShipper = new AddShipper(controller);
            }
        });
        addItemsInStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddItem addItem = new AddItem(controller);
            }
        });
        addStorage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStorage addStorage = new AddStorage(controller);
            }
        });
        clientsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println(clientsTable.getSelectedRow());
                }
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
                         //controller.updateClientTable(controller.getHibernate().get("Client"));
                        break;
                    }
                    case 6: {
                         controller.updateSellOrdersTable(controller.getHibernate().get("SellOrder"), dealsTable);
                        break;
                    }
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

    public JTabbedPane getMenu() {
        return menu;
    }

    public void setMenu(JTabbedPane menu) {
        this.menu = menu;
    }

    public JPanel getWrapPanel() {
        return wrapPanel;
    }

    public void setWrapPanel(JPanel wrapPanel) {
        this.wrapPanel = wrapPanel;
    }

    public JTable getClientsTable() {
        return clientsTable;
    }

    public void setClientsTable(JTable clientsTable) {
        this.clientsTable = clientsTable;
    }

    public JPanel getClientsTab() {
        return clientsTab;
    }

    public void setClientsTab(JPanel clientsTab) {
        this.clientsTab = clientsTab;
    }

    public JPanel getShipOrdersTab() {
        return shipOrdersTab;
    }

    public void setShipOrdersTab(JPanel shipOrdersTab) {
        this.shipOrdersTab = shipOrdersTab;
    }

    public JPanel getDealsTab() {
        return dealsTab;
    }

    public void setDealsTab(JPanel dealsTab) {
        this.dealsTab = dealsTab;
    }

    public JPanel getShippersTab() {
        return shippersTab;
    }

    public void setShippersTab(JPanel shippersTab) {
        this.shippersTab = shippersTab;
    }

    public JPanel getNomenclatureTab() {
        return nomenclatureTab;
    }

    public void setNomenclatureTab(JPanel nomenclatureTab) {
        this.nomenclatureTab = nomenclatureTab;
    }

    public JPanel getStorageTab() {
        return storageTab;
    }

    public void setStorageTab(JPanel storageTab) {
        this.storageTab = storageTab;
    }

    public JPanel getItemsInStorageTab() {
        return itemsInStorageTab;
    }

    public void setItemsInStorageTab(JPanel itemsInStorageTab) {
        this.itemsInStorageTab = itemsInStorageTab;
    }

    public JTable getShipOrdersTable() {
        return shipOrdersTable;
    }

    public void setShipOrdersTable(JTable shipOrdersTable) {
        this.shipOrdersTable = shipOrdersTable;
    }

    public JTable getShippersTable() {
        return shippersTable;
    }

    public void setShippersTable(JTable shippersTable) {
        this.shippersTable = shippersTable;
    }

    public JTable getNomenclatureTable() {
        return nomenclatureTable;
    }

    public void setNomenclatureTable(JTable nomenclatureTable) {
        this.nomenclatureTable = nomenclatureTable;
    }

    public JTable getStorageTable() {
        return storageTable;
    }

    public void setStorageTable(JTable storageTable) {
        this.storageTable = storageTable;
    }

    public JTable getItemsInStorageTable() {
        return itemsInStorageTable;
    }

    public void setItemsInStorageTable(JTable itemsInStorageTable) {
        this.itemsInStorageTable = itemsInStorageTable;
    }

    public JTable getDealsTable() {
        return dealsTable;
    }

    public void setDealsTable(JTable dealsTable) {
        this.dealsTable = dealsTable;
    }

    public JButton getAddClientBtn() {
        return addClientBtn;
    }

    public void setAddClientBtn(JButton addClientBtn) {
        this.addClientBtn = addClientBtn;
    }

    public JToolBar getClientsToolbar() {
        return clientsToolbar;
    }

    public void setClientsToolbar(JToolBar clientsToolbar) {
        this.clientsToolbar = clientsToolbar;
    }

    public JButton getAddClientButton() {
        return addClientButton;
    }

    public void setAddClientButton(JButton addClientButton) {
        this.addClientButton = addClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public void setDeleteClientButton(JButton deleteClientButton) {
        this.deleteClientButton = deleteClientButton;
    }

    public JButton getRefreshClientTableButton() {
        return refreshClientTableButton;
    }

    public void setRefreshClientTableButton(JButton refreshClientTableButton) {
        this.refreshClientTableButton = refreshClientTableButton;
    }

    public JScrollPane getClientTableWrap() {
        return clientTableWrap;
    }

    public void setClientTableWrap(JScrollPane clientTableWrap) {
        this.clientTableWrap = clientTableWrap;
    }

    public JScrollPane getShipOrderTableWrap() {
        return shipOrderTableWrap;
    }

    public void setShipOrderTableWrap(JScrollPane shipOrderTableWrap) {
        this.shipOrderTableWrap = shipOrderTableWrap;
    }

    public JScrollPane getShippersTableWrap() {
        return shippersTableWrap;
    }

    public void setShippersTableWrap(JScrollPane shippersTableWrap) {
        this.shippersTableWrap = shippersTableWrap;
    }

    public JScrollPane getNomenclatureTableWrap() {
        return nomenclatureTableWrap;
    }

    public void setNomenclatureTableWrap(JScrollPane nomenclatureTableWrap) {
        this.nomenclatureTableWrap = nomenclatureTableWrap;
    }

    public JScrollPane getStorageTableWrap() {
        return storageTableWrap;
    }

    public void setStorageTableWrap(JScrollPane storageTableWrap) {
        this.storageTableWrap = storageTableWrap;
    }

    public JScrollPane getItemsInStorageTableWrap() {
        return itemsInStorageTableWrap;
    }

    public void setItemsInStorageTableWrap(JScrollPane itemsInStorageTableWrap) {
        this.itemsInStorageTableWrap = itemsInStorageTableWrap;
    }

    public JScrollPane getDealsTableWrap() {
        return dealsTableWrap;
    }

    public void setDealsTableWrap(JScrollPane dealsTableWrap) {
        this.dealsTableWrap = dealsTableWrap;
    }

    public JToolBar getShipOrdersToolbar() {
        return shipOrdersToolbar;
    }

    public void setShipOrdersToolbar(JToolBar shipOrdersToolbar) {
        this.shipOrdersToolbar = shipOrdersToolbar;
    }

    public JToolBar getShippersToolbar() {
        return shippersToolbar;
    }

    public void setShippersToolbar(JToolBar shippersToolbar) {
        this.shippersToolbar = shippersToolbar;
    }

    public JToolBar getNomenclatureToolbar() {
        return nomenclatureToolbar;
    }

    public void setNomenclatureToolbar(JToolBar nomenclatureToolbar) {
        this.nomenclatureToolbar = nomenclatureToolbar;
    }

    public JToolBar getStorageToolbar() {
        return storageToolbar;
    }

    public void setStorageToolbar(JToolBar storageToolbar) {
        this.storageToolbar = storageToolbar;
    }

    public JToolBar getItemsInStorageToolbar() {
        return itemsInStorageToolbar;
    }

    public void setItemsInStorageToolbar(JToolBar itemsInStorageToolbar) {
        this.itemsInStorageToolbar = itemsInStorageToolbar;
    }

    public JToolBar getDealsToolbar() {
        return dealsToolbar;
    }

    public void setDealsToolbar(JToolBar dealsToolbar) {
        this.dealsToolbar = dealsToolbar;
    }

    public JButton getAddShipOrder() {
        return addShipOrder;
    }

    public void setAddShipOrder(JButton addShipOrder) {
        this.addShipOrder = addShipOrder;
    }

    public JButton getDeleteShipOrder() {
        return deleteShipOrder;
    }

    public void setDeleteShipOrder(JButton deleteShipOrder) {
        this.deleteShipOrder = deleteShipOrder;
    }

    public JButton getRefreshShipOrder() {
        return refreshShipOrder;
    }

    public void setRefreshShipOrder(JButton refreshShipOrder) {
        this.refreshShipOrder = refreshShipOrder;
    }

    public JButton getAddShipper() {
        return addShipper;
    }

    public void setAddShipper(JButton addShipper) {
        this.addShipper = addShipper;
    }

    public JButton getDeleteShipper() {
        return deleteShipper;
    }

    public void setDeleteShipper(JButton deleteShipper) {
        this.deleteShipper = deleteShipper;
    }

    public JButton getRefreshShipper() {
        return refreshShipper;
    }

    public void setRefreshShipper(JButton refreshShipper) {
        this.refreshShipper = refreshShipper;
    }

    public JButton getAddNomenclature() {
        return addNomenclature;
    }

    public void setAddNomenclature(JButton addNomenclature) {
        this.addNomenclature = addNomenclature;
    }

    public JButton getDeleteNomenclature() {
        return deleteNomenclature;
    }

    public void setDeleteNomenclature(JButton deleteNomenclature) {
        this.deleteNomenclature = deleteNomenclature;
    }

    public JButton getRefreshNomenclature() {
        return refreshNomenclature;
    }

    public void setRefreshNomenclature(JButton refreshNomenclature) {
        this.refreshNomenclature = refreshNomenclature;
    }

    public JButton getAddStorage() {
        return addStorage;
    }

    public void setAddStorage(JButton addStorage) {
        this.addStorage = addStorage;
    }

    public JButton getDeleteStorage() {
        return deleteStorage;
    }

    public void setDeleteStorage(JButton deleteStorage) {
        this.deleteStorage = deleteStorage;
    }

    public JButton getRefreshStorage() {
        return refreshStorage;
    }

    public void setRefreshStorage(JButton refreshStorage) {
        this.refreshStorage = refreshStorage;
    }

    public JButton getAddItemsInStorage() {
        return addItemsInStorage;
    }

    public void setAddItemsInStorage(JButton addItemsInStorage) {
        this.addItemsInStorage = addItemsInStorage;
    }

    public JButton getDeleteItemsInStorage() {
        return deleteItemsInStorage;
    }

    public void setDeleteItemsInStorage(JButton deleteItemsInStorage) {
        this.deleteItemsInStorage = deleteItemsInStorage;
    }

    public JButton getRefreshItemsInStorage() {
        return refreshItemsInStorage;
    }

    public void setRefreshItemsInStorage(JButton refreshItemsInStorage) {
        this.refreshItemsInStorage = refreshItemsInStorage;
    }

    public JButton getAddDeals() {
        return addDeals;
    }

    public void setAddDeals(JButton addDeals) {
        this.addDeals = addDeals;
    }

    public JButton getDeleteDeals() {
        return deleteDeals;
    }

    public void setDeleteDeals(JButton deleteDeals) {
        this.deleteDeals = deleteDeals;
    }

    public JButton getRefreshDeals() {
        return refreshDeals;
    }

    public void setRefreshDeals(JButton refreshDeals) {
        this.refreshDeals = refreshDeals;
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
