/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package simrskhanza;

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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author firka
 */
public class DlgPaket extends javax.swing.JDialog {
private ResultSet rs;
public int tot,totNoDes, jml;
private validasi Valid = new validasi();
private sekuel sequel = new sekuel();
private Connection koneksi = koneksiDB.condb();
private PreparedStatement ps;
private String NULL;
private int i;
public int ii;
private DefaultTableModel tabMode,tabMode2,tabMode3,tabMode4,tabMode5;
private String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
private String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    /**
     * Creates new form DlgPaket
     */
    public DlgPaket(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new String[]{
             "Kode Produk", "Nama Produk","Biaya"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbList.setModel(tabMode);
        tbList.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbList.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }
        }
        
        tabMode2=new DefaultTableModel(null,new String[]{
             "Kode Produk", "Nama Produk","Biaya"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbPeriksa.setModel(tabMode2);
        tbPeriksa.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPeriksa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbPeriksa.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }
        }
         tabMode3=new DefaultTableModel(null,new String[]{
             "Kode Produk", "Nama Produk","Biaya"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbLab.setModel(tabMode3);
        tbLab.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbLab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbLab.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
            }
        }
        tabMode4=new DefaultTableModel(null,new String[]{
             "Kode Produk", "Nama Produk","Biaya"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRadiologi.setModel(tabMode4);
        tbRadiologi.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbRadiologi.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(80);
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

        frPeriksa = new javax.swing.JFrame();
        internalFrame2 = new widget.InternalFrame();
        tabCari = new widget.TabPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPeriksa = new widget.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLab = new widget.Table();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbRadiologi = new widget.Table();
        panelBiasa4 = new widget.PanelBiasa();
        label4 = new widget.Label();
        TCari = new widget.TextBox();
        button8 = new widget.Button();
        panelisi1 = new widget.panelisi();
        button4 = new widget.Button();
        button7 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelBiasa1 = new widget.PanelBiasa();
        label1 = new widget.Label();
        KdPeriksa = new widget.TextBox();
        label2 = new widget.Label();
        NmPeriksa = new widget.TextBox();
        label3 = new widget.Label();
        Harga = new widget.TextBox();
        panelBiasa2 = new widget.PanelBiasa();
        btCari = new widget.Button();
        panelisi2 = new widget.panelisi();
        btBaru = new widget.Button();
        btHapus = new widget.Button();
        btSimpan = new widget.Button();
        btKeluar = new widget.Button();
        panelBiasa3 = new widget.PanelBiasa();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbList = new widget.Table();

        frPeriksa.setUndecorated(true);
        frPeriksa.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                frPeriksaWindowOpened(evt);
            }
        });

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Cari Produk ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11))); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        tabCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabCariMousePressed(evt);
            }
        });

        tbPeriksa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPeriksaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbPeriksa);

        tabCari.addTab("Pemeriksaan", jScrollPane2);

        tbLab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLabKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbLab);

        tabCari.addTab("Laboratorium", jScrollPane3);

        tbRadiologi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRadiologiKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbRadiologi);

        tabCari.addTab("Radiologi", jScrollPane4);

        internalFrame2.add(tabCari, java.awt.BorderLayout.CENTER);

        label4.setText("Cari");
        label4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelBiasa4.add(label4);

        TCari.setPreferredSize(new java.awt.Dimension(200, 24));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TCariKeyReleased(evt);
            }
        });
        panelBiasa4.add(TCari);

        button8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        panelBiasa4.add(button8);

        panelisi1.setPreferredSize(new java.awt.Dimension(150, 12));
        panelBiasa4.add(panelisi1);

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        button4.setText("Ambil");
        panelBiasa4.add(button4);

        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button7.setText("Keluar");
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        panelBiasa4.add(button7);

        internalFrame2.add(panelBiasa4, java.awt.BorderLayout.PAGE_END);

        frPeriksa.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Paket Pemeriksaan ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11))); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        label1.setText("Kode Pemeriksaan");
        label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelBiasa1.add(label1);

        KdPeriksa.setPreferredSize(new java.awt.Dimension(150, 24));
        panelBiasa1.add(KdPeriksa);

        label2.setText("Nama Pemeriksaan");
        label2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelBiasa1.add(label2);

        NmPeriksa.setPreferredSize(new java.awt.Dimension(200, 24));
        panelBiasa1.add(NmPeriksa);

        label3.setText("Biaya");
        label3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelBiasa1.add(label3);

        Harga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Harga.setPreferredSize(new java.awt.Dimension(120, 24));
        panelBiasa1.add(Harga);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_START);

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btCari.setText("Pilih Produk");
        btCari.setActionCommand("Pemeriksaan");
        btCari.setPreferredSize(new java.awt.Dimension(150, 16));
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        panelBiasa2.add(btCari);

        panelisi2.setPreferredSize(new java.awt.Dimension(150, 12));
        panelBiasa2.add(panelisi2);

        btBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        btBaru.setText("Baru");
        btBaru.setPreferredSize(new java.awt.Dimension(100, 30));
        btBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBaruActionPerformed(evt);
            }
        });
        panelBiasa2.add(btBaru);

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        btHapus.setText("Hapus");
        panelBiasa2.add(btHapus);

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/095.png"))); // NOI18N
        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        panelBiasa2.add(btSimpan);

        btKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        btKeluar.setText("Keluar");
        btKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKeluarActionPerformed(evt);
            }
        });
        panelBiasa2.add(btKeluar);

        internalFrame1.add(panelBiasa2, java.awt.BorderLayout.PAGE_END);

        panelBiasa3.setLayout(new java.awt.BorderLayout());

        tbList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbList);

        panelBiasa3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(panelBiasa3, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btKeluarActionPerformed

    private void btBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBaruActionPerformed
       try{
        ps=koneksi.prepareStatement("select ifnull(count(*),0) as no from jns_perawatan_paket");
        rs = ps.executeQuery();
        rs.next();
        ii = rs.getInt("no")+1;                    
            KdPeriksa.setText("P000"+ii);       
       }catch(Exception e){
           System.out.println("Error #1 :"+e.getMessage());
       }
    }//GEN-LAST:event_btBaruActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        frPeriksa.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        frPeriksa.setLocationRelativeTo(internalFrame2);
        tabCari.setSelectedIndex(0);
        frPeriksa.setVisible(true);
    }//GEN-LAST:event_btCariActionPerformed

    private void pemeriksaan(){
        try{
        ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total_byrdrpr from jns_perawatan order by nm_perawatan asc");
        rs=ps.executeQuery();
        while(rs.next()){
            tabMode2.addRow(new Object []{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total_byrdrpr")
            });
        }
        }catch(Exception e){
            System.out.println("Error #pemeriksaan : "+e.getMessage());
        }
    }
    
    private void lab(){
        try{
        ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total_byr from jns_perawatan_lab order by nm_perawatan asc");
        rs=ps.executeQuery();
        while(rs.next()){
            tabMode3.addRow(new Object []{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total_byr")
            });
        }
        }catch(Exception e){
            System.out.println("Error #lab : "+e.getMessage());
        }
    }
    
    private void radiologi(){
        try{
        ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total_byr from jns_perawatan_radiologi order by nm_perawatan asc");
        rs=ps.executeQuery();
        while(rs.next()){
            tabMode4.addRow(new Object []{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total_byr")
            });
        }
        }catch(Exception e){
            System.out.println("Error #rad : "+e.getMessage());
        }
    }
    
    private void frPeriksaWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frPeriksaWindowOpened
        if(tabCari.getSelectedIndex()==0){
            pemeriksaan();
        }else if(tabCari.getSelectedIndex()==1){
            lab();
        }else if(tabCari.getSelectedIndex()==2){
            radiologi();
        }
    }//GEN-LAST:event_frPeriksaWindowOpened

    private void tbPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPeriksaKeyPressed
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                tabMode.addRow(new Object []{
		tbPeriksa.getValueAt(tbPeriksa.getSelectedRow(), 0),tbPeriksa.getValueAt(tbPeriksa.getSelectedRow(), 1),
                tbPeriksa.getValueAt(tbPeriksa.getSelectedRow(), 2)});
                frPeriksa.dispose();
            }
    }//GEN-LAST:event_tbPeriksaKeyPressed

    private void sum_hrg(){
        tot =0;
        int n = tbList.getRowCount();
       for (i=0;i<n;i++){
        jml = Integer.parseInt(tbList.getValueAt(i, 2).toString());
        tot = jml+tot;   
        }
    }
    
    private void tabCariMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCariMousePressed
        if(tabCari.getSelectedIndex()==0){
            pemeriksaan();
        }else if(tabCari.getSelectedIndex()==1){
            lab();
        }else if(tabCari.getSelectedIndex()==2){
            radiologi();
        }
    }//GEN-LAST:event_tabCariMousePressed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        frPeriksa.dispose();
    }//GEN-LAST:event_button7ActionPerformed

    private void tbLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLabKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        tabMode.addRow(new Object []{
	tbLab.getValueAt(tbLab.getSelectedRow(), 0),tbLab.getValueAt(tbLab.getSelectedRow(), 1),
        tbLab.getValueAt(tbLab.getSelectedRow(), 2)});
        frPeriksa.dispose();
        }
    }//GEN-LAST:event_tbLabKeyPressed

    private void tbRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRadiologiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        tabMode.addRow(new Object []{			
	tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 0),tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 1),
        tbRadiologi.getValueAt(tbRadiologi.getSelectedRow(), 2)});
        frPeriksa.dispose();
        }
    }//GEN-LAST:event_tbRadiologiKeyPressed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
       int n = tbList.getRowCount();
        for(i=0;i<n;i++){
          try{
           ps=koneksi.prepareStatement("insert into detil_periksa_paket values(?,?,?,?,?)");
           ps.setString(1, KdPeriksa.getText());
           ps.setString(2, tbList.getValueAt(i, 0).toString());
           ps.setString(3, NULL);
           ps.setString(4, tbList.getValueAt(i, 1).toString());
           ps.setString(5, tbList.getValueAt(i, 2).toString());
           ps.executeUpdate();
            }catch(Exception e){
                System.out.println("Error #Simpan : "+e.getMessage());
            }
       }
        try{
            sum_hrg();
            totNoDes=tot;
            ps=koneksi.prepareStatement("insert into jns_perawatan_paket values(?,?,?)");
            ps.setString(1, KdPeriksa.getText());
            ps.setString(2, NmPeriksa.getText());
            ps.setString(3, Integer.toString(totNoDes));
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error #Simpan Head : "+e.getMessage());
        }

        for(int iii=0; i<n ;i++){
            jml = 0;
            tot = Integer.parseInt(tbList.getValueAt(iii, 2)+"");
            tot = jml + tot;
        }
        tabMode.addRow(new Object[]{
            "", "Total :", tot
            });
        Harga.setText(String.valueOf(totNoDes));
    }//GEN-LAST:event_btSimpanActionPerformed

    private void cari_periksa(){
        try{
        ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total_byrdrpr from jns_perawatan where nm_perawatan like ?");
        ps.setString(1, "%"+TCari.getText()+"%");
        rs=ps.executeQuery();
        tabMode2.setRowCount(0);
        while(rs.next()){
            tabMode2.addRow(new Object []{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total_byrdrpr")
            });
        }
        }catch(Exception e){
            System.out.println("Cari Error : "+e.getMessage());
        }
    }
    
    private void cari_lab(){
        try{
        ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total_byr from jns_perawatan_lab where nm_perawatan like ?");
        ps.setString(1, "%"+TCari.getText()+"%");
        rs=ps.executeQuery();
        tabMode3.setRowCount(0);
        while(rs.next()){
            tabMode3.addRow(new Object []{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total_byr")
            });
        }
        }catch(Exception e){
            System.out.println("Cari Error : "+e.getMessage());
        }
    }
    
    private void cari_rad(){
        try{
        ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total_byr from jns_perawatan_radiologi where nm_perawatan like ?");
        ps.setString(1, "%"+TCari.getText()+"%");
        rs=ps.executeQuery();
        tabMode4.setRowCount(0);
        while(rs.next()){
            tabMode4.addRow(new Object []{
            rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total_byr")
            });
        }
        }catch(Exception e){
            System.out.println("Cari Error : "+e.getMessage());
        }
    }
    
    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
       
    }//GEN-LAST:event_TCariKeyPressed

    private void TCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(tabCari.getSelectedIndex()==0){
                cari_periksa();
            }else if(tabCari.getSelectedIndex()==1){
                cari_lab();
            }else if(tabCari.getSelectedIndex()==2){
                cari_rad();
        }
     }
    }//GEN-LAST:event_TCariKeyReleased

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
            java.util.logging.Logger.getLogger(DlgPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgPaket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgPaket dialog = new DlgPaket(new javax.swing.JFrame(), true);
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
    private widget.TextBox Harga;
    private widget.TextBox KdPeriksa;
    private widget.TextBox NmPeriksa;
    private widget.TextBox TCari;
    private widget.Button btBaru;
    private widget.Button btCari;
    private widget.Button btHapus;
    private widget.Button btKeluar;
    private widget.Button btSimpan;
    private widget.Button button4;
    private widget.Button button7;
    private widget.Button button8;
    private javax.swing.JFrame frPeriksa;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private widget.Label label1;
    private widget.Label label2;
    private widget.Label label3;
    private widget.Label label4;
    private widget.PanelBiasa panelBiasa1;
    private widget.PanelBiasa panelBiasa2;
    private widget.PanelBiasa panelBiasa3;
    private widget.PanelBiasa panelBiasa4;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.TabPane tabCari;
    private widget.Table tbLab;
    private widget.Table tbList;
    private widget.Table tbPeriksa;
    private widget.Table tbRadiologi;
    // End of variables declaration//GEN-END:variables
}
