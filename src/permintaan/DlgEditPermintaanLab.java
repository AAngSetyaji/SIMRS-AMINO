/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package permintaan;

import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author firka
 */
public class DlgEditPermintaanLab extends javax.swing.JDialog {
private ResultSet rs;
private validasi Valid = new validasi();
private sekuel sequel = new sekuel();
private Connection koneksi = koneksiDB.condb();
private PreparedStatement ps;
private String NULL, mus;
private int i;
public int ii;
private DefaultTableModel tabMode,tabMode2,tabMode3,tabMode4,tabMode5;
private String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
private String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    /**
     * Creates new form DlgEditPermintaanLab
     */
    public DlgEditPermintaanLab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new String[]{
             "No Order", "Tanggal", "No Rawat","Nama Pasien"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbList.setModel(tabMode);
        tbList.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 4; i++) {
            TableColumn column = tbList.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(250);
            }
        }
        tabMode2=new DefaultTableModel(null,new String[]{
             "Kode", "Tindakan Lab"
                }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDetil.setModel(tabMode2);
        tbDetil.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDetil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 2; i++) {
            TableColumn column = tbDetil.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(200);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frDetil = new javax.swing.JFrame();
        internalFrame2 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        button6 = new widget.Button();
        panelisi3 = new widget.panelisi();
        button5 = new widget.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetil = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        panelBiasa1 = new widget.PanelBiasa();
        label2 = new widget.Label();
        tgl1 = new widget.Tanggal();
        label3 = new widget.Label();
        tgl2 = new widget.Tanggal();
        button3 = new widget.Button();
        panelisi2 = new widget.panelisi();
        label1 = new widget.Label();
        TCari = new widget.TextBox();
        button1 = new widget.Button();
        panelisi1 = new widget.panelisi();
        button2 = new widget.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbList = new widget.Table();

        frDetil.setUndecorated(true);
        frDetil.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                frDetilWindowOpened(evt);
            }
        });

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Detil Permintaan Lab ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11))); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/130.png"))); // NOI18N
        button6.setText("Hapus");
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        panelBiasa2.add(button6);

        panelisi3.setPreferredSize(new java.awt.Dimension(500, 24));

        javax.swing.GroupLayout panelisi3Layout = new javax.swing.GroupLayout(panelisi3);
        panelisi3.setLayout(panelisi3Layout);
        panelisi3Layout.setHorizontalGroup(
            panelisi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        panelisi3Layout.setVerticalGroup(
            panelisi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        panelBiasa2.add(panelisi3);

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button5.setText("Keluar");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        panelBiasa2.add(button5);

        internalFrame2.add(panelBiasa2, java.awt.BorderLayout.PAGE_END);

        tbDetil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbDetil);

        internalFrame2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        frDetil.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Edit Permintaan Lab ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11))); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        label2.setText("Periode : ");
        label2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelBiasa1.add(label2);

        tgl1.setDisplayFormat("dd/MM/yyyy");
        panelBiasa1.add(tgl1);

        label3.setText("s/d");
        label3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelBiasa1.add(label3);

        tgl2.setDisplayFormat("dd/MM/yyyy");
        panelBiasa1.add(tgl2);

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        button3.setPreferredSize(new java.awt.Dimension(45, 32));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        panelBiasa1.add(button3);

        panelisi2.setPreferredSize(new java.awt.Dimension(25, 24));

        javax.swing.GroupLayout panelisi2Layout = new javax.swing.GroupLayout(panelisi2);
        panelisi2.setLayout(panelisi2Layout);
        panelisi2Layout.setHorizontalGroup(
            panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );
        panelisi2Layout.setVerticalGroup(
            panelisi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        panelBiasa1.add(panelisi2);

        label1.setText("Cari");
        label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelBiasa1.add(label1);

        TCari.setPreferredSize(new java.awt.Dimension(200, 24));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelBiasa1.add(TCari);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(45, 32));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelBiasa1.add(button1);

        panelisi1.setPreferredSize(new java.awt.Dimension(100, 24));

        javax.swing.GroupLayout panelisi1Layout = new javax.swing.GroupLayout(panelisi1);
        panelisi1.setLayout(panelisi1Layout);
        panelisi1Layout.setHorizontalGroup(
            panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        panelisi1Layout.setVerticalGroup(
            panelisi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        panelBiasa1.add(panelisi1);

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button2.setText("Keluar");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panelBiasa1.add(button2);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_END);

        tbList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbListMousePressed(evt);
            }
        });
        tbList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbList);

        internalFrame1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        dispose();
    }//GEN-LAST:event_button2ActionPerformed

    private void tampil(){
        try{
        ps=koneksi.prepareStatement("SELECT permintaan_lab.noorder, reg_periksa.tgl_registrasi, reg_periksa.no_rawat, pasien.nm_pasien" +
        " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis" +
        " INNER JOIN permintaan_lab ON permintaan_lab.no_rawat = reg_periksa.no_rawat where reg_periksa.tgl_registrasi like ?");
        ps.setString(1, dateStamp);
        rs=ps.executeQuery();
        while(rs.next()){
            tabMode.addRow(new Object[]{
            rs.getString("noorder"),rs.getString("tgl_registrasi"),rs.getString("no_rawat"),rs.getString("nm_pasien")
            });
        }
        }catch(Exception e){
            System.out.println("Gagal tampil : "+e.getMessage());
        }
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        frDetil.dispose();
    }//GEN-LAST:event_button5ActionPerformed

    private void frDetilWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frDetilWindowOpened
        try{
        ps=koneksi.prepareStatement("SELECT permintaan_pemeriksaan_lab.kd_jenis_prw, jns_perawatan_lab.nm_perawatan FROM permintaan_pemeriksaan_lab" +
        " INNER JOIN jns_perawatan_lab ON permintaan_pemeriksaan_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw" +
        " WHERE permintaan_pemeriksaan_lab.noorder like ? ");
        ps.setString(1, tbList.getValueAt(tbList.getSelectedRow(), 0).toString());
        rs=ps.executeQuery();
        while(rs.next()){
            tabMode2.addRow(new Object[]{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan")
            });
        }
        }catch(Exception e){
            System.out.println("Error Detil : "+e.getMessage());
        }
    }//GEN-LAST:event_frDetilWindowOpened
    
    private void cari(){
        try{
        ps=koneksi.prepareStatement("SELECT permintaan_lab.noorder, reg_periksa.tgl_registrasi, reg_periksa.no_rawat, pasien.nm_pasien" +
        " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis" +
        " INNER JOIN permintaan_lab ON permintaan_lab.no_rawat = reg_periksa.no_rawat where pasien.nm_pasien like ? or permintaan_lab.noorder like ? "
        + "or reg_periksa.no_rawat like ?");
        ps.setString(1, "%"+TCari.getText()+"%");
        ps.setString(2, "%"+TCari.getText()+"%");
        ps.setString(3, "%"+TCari.getText()+"%");
        rs=ps.executeQuery();
        tabMode.setRowCount(0);
        while(rs.next()){
            tabMode.addRow(new Object[]{
            rs.getString("noorder"),rs.getString("tgl_registrasi"),rs.getString("no_rawat"),rs.getString("nm_pasien")
            });
        }
        }catch(Exception e){
            System.out.println("Query Cari Gagal : "+e.getMessage());
        }
    }
    
    private void cari_tgl(){
        try{
        ps=koneksi.prepareStatement("SELECT permintaan_lab.noorder, reg_periksa.tgl_registrasi, reg_periksa.no_rawat, pasien.nm_pasien" +
        " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis" +
        " INNER JOIN permintaan_lab ON permintaan_lab.no_rawat = reg_periksa.no_rawat where reg_periksa.tgl_registrasi between ? and ?");
        ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem()+""));
        ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem()+""));
        rs=ps.executeQuery();
        tabMode.setRowCount(0);
        while(rs.next()){
            tabMode.addRow(new Object[]{
            rs.getString("noorder"),rs.getString("tgl_registrasi"),rs.getString("no_rawat"),rs.getString("nm_pasien")
            });
        }
        }catch(Exception e){
            System.out.println("Query Cari Gagal : "+e.getMessage());
        }
    }
    
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
    cari();    
    }//GEN-LAST:event_button1ActionPerformed

    
//    private void detil_lab(){
//        try{
//        ps=koneksi.prepareStatement("SELECT permintaan_pemeriksaan_lab.kd_jenis_prw, jns_perawatan_lab.nm_perawatan FROM permintaan_pemeriksaan_lab\n" +
//        "INNER JOIN jns_perawatan_lab ON permintaan_pemeriksaan_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw");
//        ps.setString(1, tbList.getValueAt(tbList.getSelectedRow(), 0).toString());
//        rs=ps.executeQuery();
//        while(rs.next()){
//            
//        }
//        }catch(Exception e){
//            System.out.println("Error Detil Lab : "+e.getMessage());
//        }
//    }
            
    private void tbListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_SPACE){
       frDetil.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
       frDetil.setLocationRelativeTo(internalFrame2);
       frDetil.setVisible(true);
       }
    }//GEN-LAST:event_tbListKeyPressed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        if(tbDetil.getSelectedRow()>-1){
//            JOptionPane.showMessageDialog(null, mus+" "+tbDetil.getValueAt(tbDetil.getSelectedRow(), 0).toString());    
            try{
            ps=koneksi.prepareStatement("delete from permintaan_pemeriksaan_lab where noorder like ? and kd_jenis_prw like ?");
            ps.setString(1, mus);
            ps.setString(2, tbDetil.getValueAt(tbDetil.getSelectedRow(), 0).toString());
            ps.executeUpdate();
            tabMode2.removeRow(tbDetil.getSelectedRow());
            JOptionPane.showMessageDialog(frDetil,"Terhapus");
            }catch(Exception e){
                System.out.println("Error hapus : "+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(frDetil, "Pilih data untuk dihapus");
        }
    }//GEN-LAST:event_button6ActionPerformed

    private void tbListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListMousePressed
        mus=tbList.getValueAt(tbList.getSelectedRow(), 0).toString();
    }//GEN-LAST:event_tbListMousePressed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        cari_tgl();
    }//GEN-LAST:event_button3ActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cari();
        }
    }//GEN-LAST:event_TCariKeyPressed

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
            java.util.logging.Logger.getLogger(DlgEditPermintaanLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgEditPermintaanLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgEditPermintaanLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgEditPermintaanLab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgEditPermintaanLab dialog = new DlgEditPermintaanLab(new javax.swing.JFrame(), true);
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
    private widget.TextBox TCari;
    private widget.Button button1;
    private widget.Button button2;
    private widget.Button button3;
    private widget.Button button5;
    private widget.Button button6;
    private javax.swing.JFrame frDetil;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private widget.Label label1;
    private widget.Label label2;
    private widget.Label label3;
    private widget.PanelBiasa panelBiasa1;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.Table tbDetil;
    private widget.Table tbList;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    // End of variables declaration//GEN-END:variables
}