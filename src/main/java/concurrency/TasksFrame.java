package concurrency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TasksFrame extends JFrame {
    private static final int REFRESH_RATE = 200;
    private final TasksTableModel model;
    private final JButton addButton;

    public TasksFrame(TasksTableModel model) {
        super("Task generator");
        this.model = model;

        JTable table = new JTable(this.model);
        table.getColumn("Status").setCellRenderer(new StatusCellRenderer());
        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll);

        addButton = new JButton("Add task");
        add(addButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        Timer timer = new Timer(REFRESH_RATE, e -> this.model.fireTableDataChanged());
        timer.start();
    }

    public void addNewTaskListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
}