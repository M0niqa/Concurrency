package concurrency;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class StatusCellRenderer extends JLabel implements TableCellRenderer {

    public StatusCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus, int row, int column) {
        Status status = (Status) object;
        setText(status.name());
        switch (status) {
            case Running -> {
                setBackground(Color.BLUE);
                setForeground(Color.WHITE);
            }
            case Accomplished -> {
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
            }
            case Pending -> {
                setBackground(Color.YELLOW);
                setForeground(Color.BLACK);
            }
            default -> {
                setBackground(Color.RED);
                setForeground(Color.WHITE);
            }
        }
        return this;
    }

}
