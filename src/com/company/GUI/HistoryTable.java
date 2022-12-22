package com.company.GUI;

import com.company.Model.History;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HistoryTable extends JFrame{
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<History> history = new ArrayList<>();;

    public HistoryTable(ArrayList<History> hist) {
        history = hist;

        TableModel model = new TableModel() {
            @Override
            public int getRowCount() {
                return history.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int columnIndex) {
                if(columnIndex==0) return "Reservation ID";
                if(columnIndex==1) return "Item ID";
                if(columnIndex==2) return "Student ID";
                if(columnIndex==3) return "Reservation Date";
               // if(columnIndex==4) return "Due Date";
                //if(columnIndex==5) return "Overdue Days";
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex==0) return int.class;
                if(columnIndex==1) return int.class;
                if(columnIndex==2) return int.class;
                if(columnIndex==3) return Date.class;
                //if(columnIndex==4) return Date.class;
                //if(columnIndex==5) return int.class;

                return null;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if(columnIndex==0) return history.get(rowIndex).getHistoryID();
                if(columnIndex==1) return history.get(rowIndex).getItemID();
                if(columnIndex==2) return history.get(rowIndex).getStudentID();
                if(columnIndex==3) return history.get(rowIndex).getResTime();
                //if(columnIndex==4) return history.get(rowIndex).getResTime().
               // if(columnIndex==5) return "Overdue Days";
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


    //test
    public static void main(String[] args) {

        History hist = new History(1,2,3, new Date());
        ArrayList<History> h = new ArrayList<>();
        h.add(hist);
        new HistoryTable(h);
    }
}
