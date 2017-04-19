import entity.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Кирилл on 12.04.2017.
 */
public class Controller {

    private AppForm frame;
    private HibernateHolder hibernate;

    public void initModel() {
        hibernate = new HibernateHolder();
    }

    public void initModel(String url,String dbUrl, String user, String psswd) {
        hibernate = new HibernateHolder(url,dbUrl,user,psswd);
    }

    public void initView() {
        frame = new AppForm(this);
    }

    public void updateClientTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("ФИО");
        tableHeaders.add("Номер телефона");
        tableHeaders.add("Дата рождения");
        for (Object o : data) {
            Client client = (Client) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(client.getId());
            oneRow.add(client.getName());
            oneRow.add(client.getNumber());
            oneRow.add(client.getBirthDate());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    public void updateSellOrdersTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Клиент");
        tableHeaders.add("Сумма");
        for (Object o : data) {
            SellOrder order = (SellOrder) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(order.getId());
            int clientId = order.getClientId();
            ArrayList<Client> clients = hibernate.get("Client where id=" + clientId);
            oneRow.add(clients.get(0).getName());
            oneRow.add(order.getSum());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    public void updateNomenclatureTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Наименование");
        tableHeaders.add("Артикул");
        tableHeaders.add("Базовая цена");
        tableHeaders.add("Оптовая цена");
        tableHeaders.add("Розничная цена");
        for (Object o : data) {
            Nomenclature client = (Nomenclature) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(client.getId());
            oneRow.add(client.getName());
            oneRow.add(client.getArticle());
            oneRow.add(client.getBasePrice());
            oneRow.add(client.getWholesalePrice());
            oneRow.add(client.getRetailPrice());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    public void updateStorageTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Название");
        tableHeaders.add("Описание");
        for (Object o : data) {
            Storage storage = (Storage) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(storage.getId());
            oneRow.add(storage.getName());
            oneRow.add(storage.getDescription());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    public void updateShipOrderTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Сумма");
        tableHeaders.add("Поставщик");
        tableHeaders.add("Склад");
        for (Object o : data) {
            ShipOrder o1 = (ShipOrder) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(o1.getId());
            oneRow.add(o1.getSum());
            ArrayList shipper = hibernate.get("Shipper where id=" + o1.getShipper_id());
            Shipper shipper1 = (Shipper) shipper.get(0);
            oneRow.add(shipper1.getName());
            ArrayList storages = hibernate.get("Storage where id=" + o1.getStorage_id());
            Storage storage = (Storage) storages.get(0);
            oneRow.add(storage.getName());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }

    public void updateShipperTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Наименование");
        tableHeaders.add("Номер телефона");
        tableHeaders.add("Адрес");
        for (Object o : data) {
            Shipper o1 = (Shipper) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(o1.getId());
            oneRow.add(o1.getName());
            oneRow.add(o1.getNumber());
            oneRow.add(o1.getAdress());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }


    public AppForm getFrame() {
        return frame;
    }

    public void setFrame(AppForm frame) {
        this.frame = frame;
    }

    public HibernateHolder getHibernate() {
        return hibernate;
    }

    public void setHibernate(HibernateHolder hibernate) {
        this.hibernate = hibernate;
    }

    public void updateItemTable(ArrayList data, JTable table) {
        Vector<String> tableHeaders = new Vector<String>();
        final Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Номенклатура");
        tableHeaders.add("Склад");
        tableHeaders.add("Количество");
        for (Object o : data) {
            Item o1 = (Item) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(o1.getId());
            ArrayList nom = hibernate.get("Nomenclature where id=" + o1.getNomenclatureId());
            Nomenclature nomenclature = (Nomenclature) nom.get(0);
            oneRow.add(nomenclature.getName());
            ArrayList stor = hibernate.get("Storage where id=" + o1.getStorageId());
            Storage storage = (Storage) stor.get(0);
            oneRow.add(storage.getName());
            oneRow.add(o1.getQuanity());
            tableData.add(oneRow);
        }
        table.setModel(new CustomTableModel(tableData, tableHeaders));
    }
}