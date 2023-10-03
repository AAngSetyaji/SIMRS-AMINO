/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package simrskhanza;
import fungsi.koneksiDB;
import javax.swing.table.TableColumn;
import fungsi.validasi;
import fungsi.sekuel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import simrskhanza.DlgValidECT;

/**
 *
 * @author jónï ïNdÖ
 */
public class DlgCariECT extends javax.swing.JDialog {
     private sekuel sequel = new sekuel();
     private PreparedStatement ps, ps2;
     private ResultSet rs, rs2;
     private Connection koneksi=koneksiDB.condb();
     private validasi Valid=new validasi();
     private String kdTindakan;
    
    
    /**
     * Creates new form DlgCariECT
     */
    public DlgCariECT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        tbPilih = new widget.TabPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPetugas = new widget.Table();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDok = new widget.Table();
        label1 = new widget.Label();
        txNoRW = new widget.TextBox();
        label2 = new widget.Label();
        txNmPas = new widget.TextBox();
        Tindakan = new widget.Label();
        txTindakan = new widget.TextBox();
        panelisi2 = new widget.panelisi();
        textBox1 = new widget.TextBox();
        button1 = new widget.Button();
        button2 = new widget.Button();
        button3 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder("..:: Cari Dokter & Petugas ECT ::.."));

        tbPetugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kode", "Nama Perawat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPetugas);
        if (tbPetugas.getColumnModel().getColumnCount() > 0) {
            tbPetugas.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbPetugas.getColumnModel().getColumn(0).setMaxWidth(100);
            tbPetugas.getColumnModel().getColumn(1).setPreferredWidth(300);
            tbPetugas.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        tbPilih.addTab("Perawat", jScrollPane2);

        tbDok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kode Dokter", "Nama Dokter"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbDok);
        if (tbDok.getColumnModel().getColumnCount() > 0) {
            tbDok.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbDok.getColumnModel().getColumn(0).setMaxWidth(100);
            tbDok.getColumnModel().getColumn(1).setPreferredWidth(300);
            tbDok.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        tbPilih.addTab("Dokter", jScrollPane1);

        label1.setText("No Rawat");

        label2.setText("Nama Pasien");

        Tindakan.setText("Tindakan");

        javax.swing.GroupLayout panelisi1Layout = new javax.swing.GroupLayout(panelisi1);
        panelisi1.setLayout(panelisi1Layout);
        panelisi1Layout.setHorizontalGroup(
            panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelisi1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbPilih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelisi1Layout.createSequentialGroup()
                        .addGroup(panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelisi1Layout.createSequentialGroup()
                                .addComponent(txNoRW, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txNmPas, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(158, Short.MAX_VALUE))))
        );
        panelisi1Layout.setVerticalGroup(
            panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelisi1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNoRW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNmPas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(tbPilih, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/129.png"))); // NOI18N
        button2.setText("Ambil");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button3.setText("Keluar");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelisi2Layout = new javax.swing.GroupLayout(panelisi2);
        panelisi2.setLayout(panelisi2Layout);
        panelisi2Layout.setHorizontalGroup(
            panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelisi2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(textBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelisi2Layout.setVerticalGroup(
            panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelisi2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout internalFrame1Layout = new javax.swing.GroupLayout(internalFrame1);
        internalFrame1.setLayout(internalFrame1Layout);
        internalFrame1Layout.setHorizontalGroup(
            internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame1Layout.createSequentialGroup()
                .addComponent(panelisi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelisi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        internalFrame1Layout.setVerticalGroup(
            internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame1Layout.createSequentialGroup()
                .addComponent(panelisi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(panelisi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(internalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        dispose();
    }//GEN-LAST:event_button3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        if (tbPilih.getSelectedIndex()==0){
            try {
                int sr = tbPetugas.getSelectedRow();
                int Up;
        ps = koneksi.prepareStatement("update permintaan_ect set kd_petugas=? where kd_tindakan like ? and no_rawat like ?");
        ps.setString(1, tbPetugas.getValueAt(sr, 0).toString());
        ps.setString(2, "%"+txTindakan.getText()+"%");
        ps.setString(3, "%"+txNoRW.getText()+"%");
        Up = ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Tersimpan");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else if (tbPilih.getSelectedIndex()==1){
            try {
                int sr = tbDok.getSelectedRow();
                int Up;
        ps = koneksi.prepareStatement("update permintaan_ect set kd_dok=? where kd_tindakan like ? and no_rawat like ?");
        ps.setString(1, tbDok.getValueAt(sr, 0).toString());
        ps.setString(2, "%"+txTindakan.getText()+"%");
        ps.setString(3, "%"+txNoRW.getText()+"%");
        Up = ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Tersimpan");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }//GEN-LAST:event_button2ActionPerformed

    public void setRM (String noRWT, String NmPas, String kdTindakan){
        txNoRW.setText(noRWT);
        txNmPas.setText(NmPas);
        txTindakan.setText(kdTindakan);
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       tampil_awal(); 
    }//GEN-LAST:event_formWindowOpened

    private void tampil_awal(){
        try {
        ps = koneksi.prepareStatement("select kd_dokter, nm_dokter from dokter order by nm_dokter asc");
        ps2 = koneksi.prepareStatement("select nip, nama from petugas order by nama asc");
        rs = ps.executeQuery();
        rs2 = ps2.executeQuery();
            DefaultTableModel LsDok = (DefaultTableModel)tbDok.getModel();
            DefaultTableModel LsPtgs = (DefaultTableModel)tbPetugas.getModel();
            LsDok.setRowCount(0);
            LsPtgs.setRowCount(0);
        while(rs.next()){
            LsDok.addRow(new Object []{
            rs.getString(1),rs.getString(2)
            });
        }
        while(rs2.next()){
            LsPtgs.addRow(new Object []{
            rs2.getString(1),rs2.getString(2)
            });
        }
        }catch (Exception e){
         System.out.println(e.getMessage());
        }
        
    }
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
            java.util.logging.Logger.getLogger(DlgCariECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgCariECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgCariECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgCariECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgCariECT dialog = new DlgCariECT(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Label Tindakan;
    private widget.Button button1;
    private widget.Button button2;
    private widget.Button button3;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private widget.Label label1;
    private widget.Label label2;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.Table tbDok;
    private widget.Table tbPetugas;
    private widget.TabPane tbPilih;
    private widget.TextBox textBox1;
    private widget.TextBox txNmPas;
    private widget.TextBox txNoRW;
    private widget.TextBox txTindakan;
    // End of variables declaration//GEN-END:variables
}