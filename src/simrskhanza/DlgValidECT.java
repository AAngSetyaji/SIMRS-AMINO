/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package simrskhanza;
import fungsi.akses;
import fungsi.sekuel;
import fungsi.koneksiDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import fungsi.validasi;
import javax.swing.JOptionPane;
import simrskhanza.DlgCariECT;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jónï ïNdÖ
 */
public class DlgValidECT extends javax.swing.JDialog {
    private sekuel sequel = new sekuel();
    DateFormat Tgl = new SimpleDateFormat("yyyy-MM-dd");
    Date TglT = new Date();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private validasi valid = new validasi();
    private ResultSet rs;

    /**
     * Creates new form DlgValidECT
     */
    public DlgValidECT(java.awt.Frame parent, boolean modal) {
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
        panelBiasa1 = new widget.PanelBiasa();
        button1 = new widget.Button();
        button3 = new widget.Button();
        tgl1 = new widget.Tanggal();
        label1 = new widget.Label();
        tgl2 = new widget.Tanggal();
        button4 = new widget.Button();
        panelBiasa2 = new widget.PanelBiasa();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTrans = new widget.Table();
        panelBiasa4 = new widget.PanelBiasa();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbTransDet = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder("..:: Validasi Dokter ::.."));

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        button1.setText("Input Dokter / Petugas");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button3.setText("Keluar");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-10-2023" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");

        label1.setText("s/d");

        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-10-2023" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBiasa1Layout = new javax.swing.GroupLayout(panelBiasa1);
        panelBiasa1.setLayout(panelBiasa1Layout);
        panelBiasa1Layout.setHorizontalGroup(
            panelBiasa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBiasa1Layout.createSequentialGroup()
                .addComponent(tgl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBiasa1Layout.setVerticalGroup(
            panelBiasa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBiasa1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBiasa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBiasa1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelBiasa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tgl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBiasa1Layout.createSequentialGroup()
                        .addGroup(panelBiasa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tbTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Rawat", "Tgl Periksa", "Jam", "Nama Pasien"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbTransMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbTrans);
        if (tbTrans.getColumnModel().getColumnCount() > 0) {
            tbTrans.getColumnModel().getColumn(0).setMinWidth(45);
            tbTrans.getColumnModel().getColumn(0).setPreferredWidth(120);
            tbTrans.getColumnModel().getColumn(0).setMaxWidth(120);
            tbTrans.getColumnModel().getColumn(1).setMinWidth(15);
            tbTrans.getColumnModel().getColumn(1).setPreferredWidth(65);
            tbTrans.getColumnModel().getColumn(1).setMaxWidth(65);
            tbTrans.getColumnModel().getColumn(2).setMinWidth(15);
            tbTrans.getColumnModel().getColumn(2).setPreferredWidth(55);
            tbTrans.getColumnModel().getColumn(2).setMaxWidth(55);
            tbTrans.getColumnModel().getColumn(3).setMinWidth(25);
            tbTrans.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbTrans.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        javax.swing.GroupLayout panelBiasa2Layout = new javax.swing.GroupLayout(panelBiasa2);
        panelBiasa2.setLayout(panelBiasa2Layout);
        panelBiasa2Layout.setHorizontalGroup(
            panelBiasa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
        );
        panelBiasa2Layout.setVerticalGroup(
            panelBiasa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tbTransDet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Rawat", "No RM", "Tgl Periksa", "Jam", "Nama Tindakan", "Kd Tindakan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbTransDet);
        if (tbTransDet.getColumnModel().getColumnCount() > 0) {
            tbTransDet.getColumnModel().getColumn(0).setMinWidth(45);
            tbTransDet.getColumnModel().getColumn(0).setPreferredWidth(120);
            tbTransDet.getColumnModel().getColumn(0).setMaxWidth(120);
            tbTransDet.getColumnModel().getColumn(1).setMinWidth(25);
            tbTransDet.getColumnModel().getColumn(1).setPreferredWidth(85);
            tbTransDet.getColumnModel().getColumn(1).setMaxWidth(85);
            tbTransDet.getColumnModel().getColumn(2).setMinWidth(15);
            tbTransDet.getColumnModel().getColumn(2).setPreferredWidth(65);
            tbTransDet.getColumnModel().getColumn(2).setMaxWidth(65);
            tbTransDet.getColumnModel().getColumn(3).setMinWidth(15);
            tbTransDet.getColumnModel().getColumn(3).setPreferredWidth(40);
            tbTransDet.getColumnModel().getColumn(3).setMaxWidth(40);
            tbTransDet.getColumnModel().getColumn(4).setMinWidth(45);
            tbTransDet.getColumnModel().getColumn(4).setPreferredWidth(250);
            tbTransDet.getColumnModel().getColumn(4).setMaxWidth(250);
            tbTransDet.getColumnModel().getColumn(5).setPreferredWidth(85);
            tbTransDet.getColumnModel().getColumn(5).setMaxWidth(85);
        }

        javax.swing.GroupLayout panelBiasa4Layout = new javax.swing.GroupLayout(panelBiasa4);
        panelBiasa4.setLayout(panelBiasa4Layout);
        panelBiasa4Layout.setHorizontalGroup(
            panelBiasa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        panelBiasa4Layout.setVerticalGroup(
            panelBiasa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBiasa4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout internalFrame1Layout = new javax.swing.GroupLayout(internalFrame1);
        internalFrame1.setLayout(internalFrame1Layout);
        internalFrame1Layout.setHorizontalGroup(
            internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBiasa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBiasa2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(internalFrame1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(panelBiasa4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(1, 1, 1)))
        );
        internalFrame1Layout.setVerticalGroup(
            internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, internalFrame1Layout.createSequentialGroup()
                .addComponent(panelBiasa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(panelBiasa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(internalFrame1Layout.createSequentialGroup()
                    .addGap(141, 141, 141)
                    .addComponent(panelBiasa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil_awal();
    }//GEN-LAST:event_formWindowOpened

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        tampil_awal();
    }//GEN-LAST:event_button4ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_button3ActionPerformed

    private void tbTransMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransMousePressed
        try {
           int sr = tbTrans.getSelectedRow();
        ps = koneksi.prepareStatement("SELECT permintaan_ect.no_rm, permintaan_ect.no_rawat, permintaan_ect.tgl_periksa, permintaan_ect.jam, jns_perawatan_ect.nm_perawatan,\n" +
        "jns_perawatan_ect.total, permintaan_ect.kd_tindakan FROM permintaan_ect INNER JOIN jns_perawatan_ect ON permintaan_ect.kd_tindakan = jns_perawatan_ect.kd_jenis_prw\n" +
        "WHERE permintaan_ect.no_rawat like ?");
        ps.setString(1, tbTrans.getValueAt(sr, 0).toString());
        rs = ps.executeQuery();
            DefaultTableModel LsPasDet = (DefaultTableModel)tbTransDet.getModel();
            LsPasDet.setRowCount(0);
            while(rs.next()){
                LsPasDet.addRow(new Object[]{
                    rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(7)
                });
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tbTransMousePressed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        int sr = tbTrans.getSelectedRow();
        int sr2 = tbTransDet.getSelectedRow();
        if (tbTrans.getSelectionModel().isSelectionEmpty()|| tbTransDet.getSelectionModel().isSelectionEmpty()){
        JOptionPane.showMessageDialog(null, "Data belum dipilih");
        }else{
            DlgCariECT CariECT = new DlgCariECT(null,false);
        CariECT.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        CariECT.setLocationRelativeTo(internalFrame1);
        CariECT.setRM(tbTrans.getValueAt(sr, 0).toString(), 
                tbTrans.getValueAt(sr, 3).toString(),tbTransDet.getValueAt(sr2, 5).toString());
        CariECT.setVisible(true);
        }
    }//GEN-LAST:event_button1ActionPerformed

    public void isCek(){
        button1.setEnabled(akses.gettindakan_ect());
    }
       
    private void tampil_awal(){
        try {
        ps = koneksi.prepareStatement("select pasien.nm_pasien, reg_periksa.no_rawat, reg_periksa.no_reg, reg_periksa.tgl_registrasi, reg_periksa.jam_reg FROM reg_periksa" +
        " inner join pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
        "where reg_periksa.tgl_registrasi between ? and ?");
        ps.setString(1, valid.SetTgl(tgl1.getSelectedItem()+""));
        ps.setString(2, valid.SetTgl(tgl2.getSelectedItem()+""));
        rs = ps.executeQuery();
            DefaultTableModel LsPasien = (DefaultTableModel)tbTrans.getModel();
            LsPasien.setRowCount(0);
            while(rs.next()){
                LsPasien.addRow(new Object[]{
                    rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(1)
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
            java.util.logging.Logger.getLogger(DlgValidECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgValidECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgValidECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgValidECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgValidECT dialog = new DlgValidECT(new javax.swing.JFrame(), true);
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
    private widget.Button button1;
    private widget.Button button3;
    private widget.Button button4;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private widget.Label label1;
    private widget.PanelBiasa panelBiasa1;
    private widget.PanelBiasa panelBiasa2;
    private widget.PanelBiasa panelBiasa4;
    private widget.Table tbTrans;
    private widget.Table tbTransDet;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    // End of variables declaration//GEN-END:variables
}
