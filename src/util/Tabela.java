package util;

import javax.swing.table.DefaultTableModel;

public class Tabela {

    public static void limparTabela(DefaultTableModel dtm) {
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                dtm.removeRow(i);
            }
        }
    }
}
