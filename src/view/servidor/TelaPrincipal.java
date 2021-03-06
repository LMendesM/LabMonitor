package view.servidor;

import java.awt.BorderLayout;

public class TelaPrincipal extends javax.swing.JFrame {

    AbasEstacao abasEstacao;
    ListaEstacoes listaEstacoes;
    EnvioArquivo envioArquivo;
    Configuracoes configuracoes;
    
    public TelaPrincipal() {
        initComponents();
        panelCorpo.setLayout(new BorderLayout());
        
        listaEstacoes = new ListaEstacoes(this);
        
        panelCorpo.invalidate();
        panelCorpo.removeAll();
        panelCorpo.add(listaEstacoes, BorderLayout.NORTH);
        panelCorpo.revalidate();
        panelCorpo.repaint();
    }
    
    public void mostraEstacoes(){
        panelCorpo.invalidate();
        panelCorpo.removeAll();
        panelCorpo.add(listaEstacoes, BorderLayout.NORTH);
        panelCorpo.revalidate();
        panelCorpo.repaint();
    }
    
    public void mostraProcessos(String nome){
        panelCorpo.invalidate();
        panelCorpo.removeAll();
        abasEstacao = new AbasEstacao(this, nome);
        panelCorpo.add(abasEstacao, BorderLayout.NORTH);
        panelCorpo.revalidate();
        panelCorpo.repaint();
    }
    
    public void mostraEnvioArquivo(){
        panelCorpo.invalidate();
        panelCorpo.removeAll();
        envioArquivo = new EnvioArquivo();
        panelCorpo.add(envioArquivo, BorderLayout.NORTH);
        panelCorpo.revalidate();
        panelCorpo.repaint();
    }
    
    public void mostraConfiguracoes(){
        panelCorpo.invalidate();
        panelCorpo.removeAll();
        configuracoes = new Configuracoes();
        panelCorpo.add(configuracoes, BorderLayout.NORTH);
        panelCorpo.revalidate();
        panelCorpo.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonEstacoes = new javax.swing.JButton();
        buttonConfiguracoes = new javax.swing.JButton();
        buttonSair = new javax.swing.JButton();
        buttonArquivos = new javax.swing.JButton();
        panelCorpo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonEstacoes.setText("Estações");
        buttonEstacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEstacoesActionPerformed(evt);
            }
        });

        buttonConfiguracoes.setText("Configurações");
        buttonConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfiguracoesActionPerformed(evt);
            }
        });

        buttonSair.setText("Sair");
        buttonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSairActionPerformed(evt);
            }
        });

        buttonArquivos.setText("Enviar arquivo");
        buttonArquivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonArquivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonSair)
                    .addComponent(buttonConfiguracoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEstacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonArquivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEstacoes)
                .addGap(18, 18, 18)
                .addComponent(buttonArquivos)
                .addGap(18, 18, 18)
                .addComponent(buttonConfiguracoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSair)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCorpoLayout = new javax.swing.GroupLayout(panelCorpo);
        panelCorpo.setLayout(panelCorpoLayout);
        panelCorpoLayout.setHorizontalGroup(
            panelCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        panelCorpoLayout.setVerticalGroup(
            panelCorpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCorpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCorpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSairActionPerformed
        dispose();
    }//GEN-LAST:event_buttonSairActionPerformed

    private void buttonEstacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEstacoesActionPerformed
        mostraEstacoes();
    }//GEN-LAST:event_buttonEstacoesActionPerformed

    private void buttonArquivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonArquivosActionPerformed
        mostraEnvioArquivo();
    }//GEN-LAST:event_buttonArquivosActionPerformed

    private void buttonConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfiguracoesActionPerformed
        mostraConfiguracoes();
    }//GEN-LAST:event_buttonConfiguracoesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonArquivos;
    private javax.swing.JButton buttonConfiguracoes;
    private javax.swing.JButton buttonEstacoes;
    private javax.swing.JButton buttonSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelCorpo;
    // End of variables declaration//GEN-END:variables
}
