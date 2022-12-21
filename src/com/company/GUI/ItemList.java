package com.company.GUI;

import com.company.Model.History;
import com.company.Model.Item;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class ItemList extends JFrame {
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panel;
    private ArrayList<Item> items = new ArrayList<>();;

    public ItemList(ArrayList<Item> items) {
        this.items = items;

        TableModel model = new TableModel() {
            @Override
            public int getRowCount() {
                return items.size();
            }

            @Override
            public int getColumnCount() {
                return 7;
            }

            @Override
            public String getColumnName(int columnIndex) {
                if(columnIndex==0) return "Item ID";
                if(columnIndex==1) return "Name";
                if(columnIndex==2) return "Author";
                if(columnIndex==3) return "Year";
                if(columnIndex==4) return "Pages";
                if(columnIndex==5) return "Status";
                if(columnIndex==6) return "Location";
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex==0) return int.class;
                if(columnIndex==1) return String.class;
                if(columnIndex==2) return String.class;
                if(columnIndex==3) return int.class;
                if(columnIndex==4) return int.class;
                if(columnIndex==5) return boolean.class;
                if(columnIndex==6) return String.class;



                return null;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if(columnIndex==0) return items.get(rowIndex).getItemId();
                if(columnIndex==1) return items.get(rowIndex).getName();
                if(columnIndex==2) return items.get(rowIndex).getAuthor();
                if(columnIndex==3) return items.get(rowIndex).getYear();
                if(columnIndex==4) return items.get(rowIndex).getPages();
                if(columnIndex==5){
                    if(items.get(rowIndex).isAvailable()) return "Available";
                    return "Not Available";
                }
                if(columnIndex==6) return items.get(rowIndex).getLocation();
                return null;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        };

        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}
