import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KuludeTabel extends JFrame {

    private String[] pealkirjad;
    private DefaultTableModel mudel;
    private JTable tabel;

    public KuludeTabel(Object[][] andmed) {
        pealkirjad = new String[]{"", "Planeeritud", "Tegelik kulu", "Protsent eelarvest"};
        mudel = new DefaultTableModel(andmed, pealkirjad) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabel = new JTable(mudel);
        this.add(new JScrollPane(tabel));
        this.setTitle("Eelarve");
        this.setAlwaysOnTop(true);
        this.pack();
        this.setVisible(true);
        this.setBounds(400, 200, 500, 250);
    }
}
