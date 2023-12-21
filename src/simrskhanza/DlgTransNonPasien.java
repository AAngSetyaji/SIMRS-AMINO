/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package simrskhanza;

import fungsi.akses;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author firka
 */
public class DlgTransNonPasien extends javax.swing.JDialog {
private ResultSet rs;
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
     * Creates new form DlgTransNonPasien
     */
    public DlgTransNonPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabMode=new DefaultTableModel(null,new String[]{
             "No Trans", "Kode Produk", "Nama Produk","Biaya"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbTrans.setModel(tabMode);
        tbTrans.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbTrans.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbTrans.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setPreferredWidth(100);
            }
        }
        tabMode2=new DefaultTableModel(null,new String[]{
            "Kode Unit","Nama Unit"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbUnit.setModel(tabMode2);
        tbUnit.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbUnit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 2; i++) {
            TableColumn column = tbUnit.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(300);
            }
        }
        tabMode3=new DefaultTableModel(null,new String[]{
            "NIP","Nama Petugas"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbPelaksana.setModel(tabMode3);
        tbPelaksana.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPelaksana.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 2; i++) {
            TableColumn column = tbPelaksana.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(300);
            }
        }
        tabMode4=new DefaultTableModel(null,new String[]{
            "Kode","Nama Perawatan","Biaya"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbProduk.setModel(tabMode4);
        tbProduk.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbProduk.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbProduk.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(300);
            }else if(i==2){
                column.setPreferredWidth(150);
            }
        }
        tabMode5=new DefaultTableModel(null,new String[]{
             "No Trans", "Tanggal" ,"A/N", "Alamat","Keterangan", "Total Biaya"
            
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbList.setModel(tabMode5);
        tbList.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 5; i++) {
            TableColumn column = tbList.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
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

        CariTindakan = new javax.swing.JFrame();
        internalFrame2 = new widget.InternalFrame();
        panelGray6 = new widget.PanelGray();
        label19 = new widget.Label();
        TCari = new widget.TextBox();
        button7 = new widget.Button();
        button5 = new widget.Button();
        btAmbil = new widget.Button();
        button9 = new widget.Button();
        tabCari = new widget.TabPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbUnit = new widget.Table();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbPelaksana = new widget.Table();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbProduk = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        tabTrans = new widget.TabPane();
        scrollPane1 = new widget.ScrollPane();
        panelGray3 = new widget.PanelGray();
        label13 = new widget.Label();
        label14 = new widget.Label();
        An = new widget.TextBox();
        NoTrans = new widget.TextBox();
        label15 = new widget.Label();
        jScrollPane3 = new javax.swing.JScrollPane();
        Alamat = new widget.TextArea();
        label16 = new widget.Label();
        NmUnit = new widget.TextBox();
        btCariUnit = new widget.Button();
        label17 = new widget.Label();
        KdUnit = new widget.TextBox();
        btCariPelaksana = new widget.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        Ket = new widget.TextArea();
        label18 = new widget.Label();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbTrans = new widget.Table();
        NmPl = new widget.TextBox();
        KdPl = new widget.TextBox();
        btTindakan = new widget.Button();
        scrollPane2 = new widget.ScrollPane();
        tbList = new widget.Table();
        panelGray2 = new widget.PanelGray();
        cekTgl = new widget.CekBox();
        label21 = new widget.Label();
        tglCari = new widget.Tanggal();
        label20 = new widget.Label();
        Cari = new widget.TextBox();
        TCariUni = new widget.Button();
        btBaru = new widget.Button();
        btHapus = new widget.Button();
        btSimpan = new widget.Button();
        btCetak = new widget.Button();
        btKeluar = new widget.Button();

        CariTindakan.setUndecorated(true);
        CariTindakan.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                CariTindakanWindowOpened(evt);
            }
        });

        internalFrame2.setLayout(new java.awt.BorderLayout());

        label19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label19.setText("Cari");
        label19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelGray6.add(label19);

        TCari.setPreferredSize(new java.awt.Dimension(350, 24));
        panelGray6.add(TCari);

        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        panelGray6.add(button7);

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        button5.setText("Semua");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        panelGray6.add(button5);

        btAmbil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/36.png"))); // NOI18N
        btAmbil.setText("Ambil");
        btAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAmbilActionPerformed(evt);
            }
        });
        panelGray6.add(btAmbil);

        button9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button9.setText("Keluar");
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });
        panelGray6.add(button9);

        internalFrame2.add(panelGray6, java.awt.BorderLayout.PAGE_END);

        tbUnit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUnitKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(tbUnit);

        tabCari.addTab("Unit", jScrollPane7);

        tbPelaksana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbPelaksana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPelaksanaKeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(tbPelaksana);

        tabCari.addTab("Pelaksana", jScrollPane8);

        tbProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProdukKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tbProduk);

        tabCari.addTab("Produk", jScrollPane6);

        internalFrame2.add(tabCari, java.awt.BorderLayout.CENTER);

        CariTindakan.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "..:: Transaksi Non Pasien ::..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 11))); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        tabTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabTransMousePressed(evt);
            }
        });

        panelGray3.setLayout(null);

        label13.setText("No Transaksi");
        panelGray3.add(label13);
        label13.setBounds(10, 20, 70, 20);

        label14.setText("Atas Nama");
        panelGray3.add(label14);
        label14.setBounds(20, 50, 60, 20);
        panelGray3.add(An);
        An.setBounds(90, 50, 170, 24);

        NoTrans.setEditable(false);
        panelGray3.add(NoTrans);
        NoTrans.setBounds(90, 20, 170, 24);

        label15.setText("Alamat");
        panelGray3.add(label15);
        label15.setBounds(20, 80, 60, 20);

        Alamat.setColumns(20);
        Alamat.setRows(5);
        jScrollPane3.setViewportView(Alamat);

        panelGray3.add(jScrollPane3);
        jScrollPane3.setBounds(90, 80, 240, 60);

        label16.setText("Unit");
        panelGray3.add(label16);
        label16.setBounds(370, 20, 60, 20);

        NmUnit.setEditable(false);
        panelGray3.add(NmUnit);
        NmUnit.setBounds(530, 20, 210, 24);

        btCariUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btCariUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariUnitActionPerformed(evt);
            }
        });
        panelGray3.add(btCariUnit);
        btCariUnit.setBounds(750, 20, 30, 30);

        label17.setText("Ptgs Kasir");
        panelGray3.add(label17);
        label17.setBounds(370, 50, 60, 20);

        KdUnit.setEditable(false);
        panelGray3.add(KdUnit);
        KdUnit.setBounds(440, 20, 90, 24);

        btCariPelaksana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btCariPelaksana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariPelaksanaActionPerformed(evt);
            }
        });
        panelGray3.add(btCariPelaksana);
        btCariPelaksana.setBounds(750, 50, 30, 30);

        Ket.setColumns(20);
        Ket.setRows(5);
        jScrollPane4.setViewportView(Ket);

        panelGray3.add(jScrollPane4);
        jScrollPane4.setBounds(440, 80, 340, 60);

        label18.setText("Keterangan");
        panelGray3.add(label18);
        label18.setBounds(370, 80, 60, 20);

        tbTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tbTrans);

        panelGray3.add(jScrollPane5);
        jScrollPane5.setBounds(0, 160, 800, 170);

        NmPl.setEditable(false);
        panelGray3.add(NmPl);
        NmPl.setBounds(530, 50, 210, 24);

        KdPl.setEditable(false);
        panelGray3.add(KdPl);
        KdPl.setBounds(440, 50, 90, 24);

        btTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/36.png"))); // NOI18N
        btTindakan.setText("Input Produk");
        btTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTindakanActionPerformed(evt);
            }
        });
        panelGray3.add(btTindakan);
        btTindakan.setBounds(0, 340, 800, 30);

        scrollPane1.setViewportView(panelGray3);

        tabTrans.addTab("Transaksi Non Pasien", scrollPane1);

        tbList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPane2.setViewportView(tbList);

        tabTrans.addTab("List Transaksi", scrollPane2);

        internalFrame1.add(tabTrans, java.awt.BorderLayout.CENTER);

        panelGray2.add(cekTgl);

        label21.setText("Lihat Per Tgl");
        label21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelGray2.add(label21);

        tglCari.setDate(new java.util.Date(1700723991000L));
        tglCari.setDisplayFormat("dd-MM-yyyy");
        panelGray2.add(tglCari);

        label20.setText("Cari");
        label20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        panelGray2.add(label20);

        Cari.setPreferredSize(new java.awt.Dimension(250, 24));
        panelGray2.add(Cari);

        TCariUni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        TCariUni.setPreferredSize(new java.awt.Dimension(35, 30));
        TCariUni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariUniActionPerformed(evt);
            }
        });
        panelGray2.add(TCariUni);

        btBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Edit.png"))); // NOI18N
        btBaru.setText("Baru");
        btBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBaruActionPerformed(evt);
            }
        });
        panelGray2.add(btBaru);

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        panelGray2.add(btHapus);

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/095.png"))); // NOI18N
        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        panelGray2.add(btSimpan);

        btCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        btCetak.setText("Cetak");
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });
        panelGray2.add(btCetak);

        btKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        btKeluar.setText("Keluar");
        btKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKeluarActionPerformed(evt);
            }
        });
        panelGray2.add(btKeluar);

        internalFrame1.add(panelGray2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cekProduk(){
        try{
            ps=koneksi.prepareStatement("select kd_jenis_prw,nm_perawatan,total from jns_perawatan_non_pas where nm_perawatan like ? or kd_jenis_prw like ?");
            ps.setString(1, "%"+TCari.getText()+"%");
            ps.setString(2, "%"+TCari.getText()+"%");
            rs=ps.executeQuery();
            tabMode4.setRowCount(0);
            while(rs.next()){
                tabMode4.addRow(new Object []{
                rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total")
                });
            }
            }catch(Exception e){
                System.out.println("Error I: "+e.getMessage());
            }
    }
    private void btTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTindakanActionPerformed
        cekProduk(); 
        CariTindakan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        CariTindakan.setLocationRelativeTo(internalFrame2);
        tabCari.setSelectedIndex(2);
        CariTindakan.setVisible(true);
    }//GEN-LAST:event_btTindakanActionPerformed

    private void autoNo(){
        try{
        ps = koneksi.prepareStatement("select ifnull(count(*),0) as urut from trans_non_pas");
        rs = ps.executeQuery();
            while(rs.next()){
                ii = rs.getInt("urut")+1;               
            }
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        
    }
    
    private void btBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBaruActionPerformed
      autoNo();
        NoTrans.setText(dateStamp+"/000"+ii);
        An.setFocusable(true);
    }//GEN-LAST:event_btBaruActionPerformed

    private void btKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKeluarActionPerformed
       dispose();
    }//GEN-LAST:event_btKeluarActionPerformed

    private void simpan(){
        int n = tbTrans.getRowCount();
//        int m = tbTrans.getSelectedRow();
        
        try{     
        ps = koneksi.prepareStatement("insert into trans_non_pas values(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, NoTrans.getText());
        ps.setString(2, dateStamp);
        ps.setString(3, timeStamp);
        ps.setString(4, Alamat.getText());
        ps.setString(5, Ket.getText());
        ps.setString(6, KdUnit.getText());
        ps.setString(7, NmUnit.getText());
        ps.setString(8, KdPl.getText());
        ps.setString(9, NmPl.getText());       
        ps.setString(10, An.getText());
        ps.executeUpdate();
        for(int i=0; i < n; i++){ 
            ps = koneksi.prepareStatement("insert into detil_non_pas values(?,?,?,?,?)");
            ps.setString(1, NoTrans.getText());
            ps.setString(2, NULL);
            ps.setString(3, tbTrans.getValueAt(i, 2).toString());
            ps.setString(4, tbTrans.getValueAt(i, 3).toString());
            ps.setString(5, tbTrans.getValueAt(i, 1).toString());
            ps.executeUpdate();         
        }
//        ps= koneksi.prepareStatement("SELECT no_trans, tanggal, jam, alamat, ket, kd_unit, nm_unit, kd_pl, nm_pl FROM trans_non_pas where no_trans like ? ");
//        ps.setString(1, NoTrans.getText());
//        rs=ps.executeQuery();
//        while(rs.next()){
//        tabMode.setRowCount(0);
//        tabMode.addRow(new Object[]{
//        rs.getString("no_trans"),rs.getString("tanggal"),rs.getString("jam"),rs.getString("alamat"),rs.getString("ket"),
//            rs.getString("nm_unit"),rs.getString("nm_pl")
//        });}
        JOptionPane.showMessageDialog(null, "Tersimpan");
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        
    }
    
    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
       simpan();
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btCariUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariUnitActionPerformed
        if(NoTrans.getText().isEmpty()==true){
                JOptionPane.showMessageDialog(null, "No trans kosong, silahkan klik baru");
        }else{ 
        try{
            ps=koneksi.prepareStatement("select kd_bangsal, nm_bangsal from bangsal");
            rs=ps.executeQuery();
            tabMode2.setRowCount(0);
            while(rs.next()){
                tabMode2.addRow(new Object []{
                rs.getString("kd_bangsal"),rs.getString("nm_bangsal")
                });
            }
            }catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        CariTindakan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        CariTindakan.setLocationRelativeTo(internalFrame2);
        tabCari.setSelectedIndex(0);
        TCari.setText("");
        CariTindakan.setVisible(true);}
        
    }//GEN-LAST:event_btCariUnitActionPerformed

    private void btCariPelaksanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariPelaksanaActionPerformed
        if(NoTrans.getText().isEmpty()==true){
                JOptionPane.showMessageDialog(null, "No trans kosong, silahkan klik baru");
        }else{
        try{
            ps=koneksi.prepareStatement("select nip,nama from petugas");
            rs=ps.executeQuery();
            tabMode3.setRowCount(0);
            while(rs.next()){
                tabMode3.addRow(new Object []{
                rs.getString("nip"),rs.getString("nama")
                });
            }
            }catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        CariTindakan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        CariTindakan.setLocationRelativeTo(internalFrame2);
        tabCari.setSelectedIndex(1);
        TCari.setText("");
        CariTindakan.setVisible(true);
        }
    }//GEN-LAST:event_btCariPelaksanaActionPerformed

    private void CariTindakanWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CariTindakanWindowOpened
        

//        if(tabCari.getSelectedIndex()==0){
//            try{
//            ps=koneksi.prepareStatement("select kd_bangsal, nm_bangsal from bangsal");
//            rs=ps.executeQuery();
//            tabMode2.setRowCount(0);
//            while(rs.next()){
//                tabMode2.addRow(new Object []{
//                rs.getString("kd_bangsal"),rs.getString("nm_bangsal")
//                });
//            }
//            }catch(Exception e){
//                System.out.println("Error : "+e.getMessage());
//            }
//        }else if (tabCari.getSelectedIndex()==1){
//            try{
//                JOptionPane.showMessageDialog(null, "1");
//            ps=koneksi.prepareStatement("select nip,nama from petugas");
//            rs=ps.executeQuery();
//            tabMode3.setRowCount(0);
//            while(rs.next()){
//                tabMode3.addRow(new Object []{
//                rs.getString("nip"),rs.getString("nama")
//                });
//            }
//            }catch(Exception e){
//                System.out.println("Error : "+e.getMessage());
//            }
//        }
    }//GEN-LAST:event_CariTindakanWindowOpened

    private void cari(){
        if(tabCari.getSelectedIndex()==0){
            try{
            ps=koneksi.prepareStatement("select kd_bangsal, nm_bangsal from bangsal where nm_bangsal like ? or kd_bangsal like ?");
            ps.setString(1, "%"+TCari.getText()+"%");
            ps.setString(2, "%"+TCari.getText()+"%");
            rs=ps.executeQuery();
            tabMode2.setRowCount(0);
            while(rs.next()){
                tabMode2.addRow(new Object []{
                rs.getString("kd_bangsal"),rs.getString("nm_bangsal")
                });
            }
            }catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        }else if(tabCari.getSelectedIndex()==1){
            try{
            ps=koneksi.prepareStatement("select nip,nama from petugas where nip like ? or nama like ?");
            ps.setString(1, "%"+TCari.getText()+"%");
            ps.setString(2, "%"+TCari.getText()+"%");
            rs=ps.executeQuery();
            tabMode3.setRowCount(0);
            while(rs.next()){
                tabMode3.addRow(new Object []{
                rs.getString("nip"),rs.getString("nama")
                });
            }
            }catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        }else if(tabCari.getSelectedIndex()==2){
            cekProduk();
        }
    }
    private void cari_semua(){
        if(tabCari.getSelectedIndex()==0){
            try{
            ps=koneksi.prepareStatement("select kd_bangsal, nm_bangsal from bangsal");
            rs=ps.executeQuery();
            tabMode2.setRowCount(0);
            while(rs.next()){
                tabMode2.addRow(new Object []{
                rs.getString("kd_bangsal"),rs.getString("nm_bangsal")
                });
            }
            }catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        }else if(tabCari.getSelectedIndex()==1){
            try{
            ps=koneksi.prepareStatement("select nip,nama from petugas");
//            ps.setString(1, TCari.getText());
//            ps.setString(2, TCari.getText());
            rs=ps.executeQuery();
            tabMode3.setRowCount(0);
            while(rs.next()){
                tabMode3.addRow(new Object []{
                rs.getString("nip"),rs.getString("nama")
                });
            }
            }catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        }else if(tabCari.getSelectedIndex()==2){
            cekProduk();
        }
    }
    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        CariTindakan.dispose();
    }//GEN-LAST:event_button9ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        cari();
    }//GEN-LAST:event_button7ActionPerformed

    private void btAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAmbilActionPerformed
     
        if(tabCari.getSelectedIndex()==0){
        KdUnit.setText(tbUnit.getValueAt(tbUnit.getSelectedRow(), 0).toString());
        NmUnit.setText(tbUnit.getValueAt(tbUnit.getSelectedRow(), 1).toString());            
        }else if(tabCari.getSelectedIndex()==1){
        KdPl.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 0).toString());
        NmPl.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 1).toString());  
        }else if(tabCari.getSelectedIndex()==2){
            tabMode.addRow(new Object[]{      
            NoTrans.getText(),
            tbProduk.getValueAt(tbProduk.getSelectedRow(), 0),
            tbProduk.getValueAt(tbProduk.getSelectedRow(), 1),
            tbProduk.getValueAt(tbProduk.getSelectedRow(), 2)    
            });
        }
    }//GEN-LAST:event_btAmbilActionPerformed

    
    
    private void tbPelaksanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPelaksanaKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                KdPl.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 0).toString());
                NmPl.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 1).toString());
                CariTindakan.dispose();}
            
    }//GEN-LAST:event_tbPelaksanaKeyPressed

    private void tbUnitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUnitKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            if(tabCari.getSelectedIndex()==0){
                KdUnit.setText(tbUnit.getValueAt(tbUnit.getSelectedRow(), 0).toString());
                NmUnit.setText(tbUnit.getValueAt(tbUnit.getSelectedRow(), 1).toString());
                CariTindakan.dispose();
            }
        }
    }//GEN-LAST:event_tbUnitKeyPressed

    private void tbProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProdukKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            if(tabCari.getSelectedIndex()==2){
//                String tot = String.format("%,d",tbProduk.getValueAt(tbProduk.getSelectedRow(), 2));
                tabMode.addRow(new Object[]{      
            NoTrans.getText(),
            tbProduk.getValueAt(tbProduk.getSelectedRow(), 0),
            tbProduk.getValueAt(tbProduk.getSelectedRow(), 1),
            tbProduk.getValueAt(tbProduk.getSelectedRow(), 2)
            });  
                CariTindakan.dispose();
            }
        }
    }//GEN-LAST:event_tbProdukKeyPressed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
       tabMode.removeRow(tbTrans.getSelectedRow());
    }//GEN-LAST:event_btHapusActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        cari_semua();
    }//GEN-LAST:event_button5ActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        if (tabTrans.getSelectedIndex()==1){
        String qrkode = "Dikeluarkan RSJD dr. Amino Gondohutomo pada "+dateStamp+" Jam:"+timeStamp+" Oleh : "+NmPl.getText();
        Map<String, Object> param = new HashMap<>();
        param.put("qr", qrkode);
        param.put("logo",sequel.cariGambar("select setting.logo from setting"));
        Valid.MyReportqry("rptTransNonPas.jasper", "report", "::[ Bukti Bayar Non Pasien ]::", 
        "SELECT trans_non_pas.no_trans, trans_non_pas.an,(SELECT SUM(hrg) FROM detil_non_pas WHERE no_trans=trans_non_pas.no_trans) AS harga,trans_non_pas.tanggal, trans_non_pas.jam, trans_non_pas.alamat, trans_non_pas.ket, trans_non_pas.nm_unit, trans_non_pas.nm_pl,\n" +
        "detil_non_pas.nama_produk, detil_non_pas.hrg FROM detil_non_pas \n" +
        "INNER JOIN trans_non_pas ON detil_non_pas.no_trans = trans_non_pas.no_trans "
        + "where trans_non_pas.no_trans='"+tbList.getValueAt(tbList.getSelectedRow(), 0)+"'",param);
        }else{
        String qrkode = "Dikeluarkan RSJD dr. Amino Gondohutomo pada "+dateStamp+" Jam:"+timeStamp+" Oleh : "+NmPl.getText();
        Map<String, Object> param = new HashMap<>();
        param.put("qr", qrkode);
        param.put("logo",sequel.cariGambar("select setting.logo from setting"));
        Valid.MyReportqry("rptTransNonPas.jasper", "report", "::[ Bukti Bayar Non Pasien ]::", 
        "SELECT trans_non_pas.no_trans, trans_non_pas.an,(SELECT SUM(hrg) FROM detil_non_pas WHERE no_trans=trans_non_pas.no_trans) AS harga,trans_non_pas.tanggal, trans_non_pas.jam, trans_non_pas.alamat, trans_non_pas.ket, trans_non_pas.nm_unit, trans_non_pas.nm_pl,\n" +
        "detil_non_pas.nama_produk, detil_non_pas.hrg FROM detil_non_pas \n" +
        "INNER JOIN trans_non_pas ON detil_non_pas.no_trans = trans_non_pas.no_trans "
        + "where trans_non_pas.no_trans='"+NoTrans.getText()+"'",param);
        }
    }//GEN-LAST:event_btCetakActionPerformed

    private void tampil_list(){
        // "No Trans", "Tanggal" ,"A/N", "Alamat","Keterangan", "Total Biaya"
        try{
            ps = koneksi.prepareStatement("SELECT trans_non_pas.no_trans, trans_non_pas.tanggal, trans_non_pas.an, trans_non_pas.ket, "
                    + "SUM(detil_non_pas.hrg) AS harga, trans_non_pas.alamat FROM detil_non_pas\n" +
            " INNER JOIN trans_non_pas ON detil_non_pas.no_trans = trans_non_pas.no_trans" +
            " WHERE trans_non_pas.tanggal LIKE ?");
            ps.setString(1, "%"+dateStamp+"%");
            rs=ps.executeQuery();
            tabMode5.setRowCount(0);
            while(rs.next()){
                String tot = String.format("%,d",rs.getInt("harga"));
                  tabMode5.addRow(new Object []{
                  rs.getString("no_trans"),rs.getString("tanggal"),rs.getString("an"),rs.getString("alamat"),
                      rs.getString("ket"),tot
                  });
            }
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
    }
    
    private void tabTransMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTransMousePressed

//        if(tabCari.getSelectedIndex()==1){
            tampil_list();
//        }
    }//GEN-LAST:event_tabTransMousePressed

    private void TCariUniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariUniActionPerformed
        if(tabTrans.getSelectedIndex()==1){
            if(cekTgl.isSelected()==true){
                try{
                    ps = koneksi.prepareStatement("SELECT DISTINCT trans_non_pas.no_trans, trans_non_pas.tanggal, trans_non_pas.an, trans_non_pas.ket,\n" +
                    "sum(hrg) AS harga,\n" +
                    "trans_non_pas.alamat FROM trans_non_pas INNER JOIN detil_non_pas ON trans_non_pas.no_trans = detil_non_pas.no_trans\n" +
                    "WHERE trans_non_pas.tanggal = ? GROUP BY trans_non_pas.no_trans");
                    ps.setString(1, Valid.SetTgl(tglCari.getSelectedItem()+""));
                    rs=ps.executeQuery();
                    tabMode5.setRowCount(0);
                    while(rs.next()){
                        String tot = String.format("%,d",rs.getInt("harga"));
                          tabMode5.addRow(new Object []{
                          rs.getString("no_trans"),rs.getString("tanggal"),rs.getString("an"),rs.getString("alamat"),
                              rs.getString("ket"),tot
                          });
                        }
                        }catch(Exception e){
                            System.out.println("Error : "+e.getMessage());
                        }
            }else{
                try {
                ps = koneksi.prepareStatement("SELECT trans_non_pas.no_trans, trans_non_pas.tanggal, trans_non_pas.an, trans_non_pas.ket, "
                    + "SUM(detil_non_pas.hrg) AS harga, trans_non_pas.alamat FROM detil_non_pas\n" +
                    " INNER JOIN trans_non_pas ON detil_non_pas.no_trans = trans_non_pas.no_trans" +
                    " WHERE trans_non_pas.an LIKE ? or trans_non_pas.no_trans like ? or trans_non_pas.alamat like ?");
                    ps.setString(1, "%"+TCariUni.getText()+"%");
                    ps.setString(2, "%"+TCariUni.getText()+"%");
                    ps.setString(3, "%"+TCariUni.getText()+"%");
                    rs=ps.executeQuery();
                    tabMode5.setRowCount(0);
                    while(rs.next()){
                        String tot = String.format("%,d",rs.getInt("harga"));
                          tabMode5.addRow(new Object []{
                          rs.getString("no_trans"),rs.getString("tanggal"),rs.getString("an"),rs.getString("alamat"),
                              rs.getString("ket"),tot
                          });
                        }
                        }catch(Exception e){
                            System.out.println("Error : "+e.getMessage());
                        }
            }
        }
    }//GEN-LAST:event_TCariUniActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(DlgTransNonPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgTransNonPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgTransNonPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgTransNonPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgTransNonPasien dialog = new DlgTransNonPasien(new javax.swing.JFrame(), true);
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
    private widget.TextArea Alamat;
    private widget.TextBox An;
    private widget.TextBox Cari;
    private javax.swing.JFrame CariTindakan;
    private widget.TextBox KdPl;
    private widget.TextBox KdUnit;
    private widget.TextArea Ket;
    private widget.TextBox NmPl;
    private widget.TextBox NmUnit;
    private widget.TextBox NoTrans;
    private widget.TextBox TCari;
    private widget.Button TCariUni;
    private widget.Button btAmbil;
    private widget.Button btBaru;
    private widget.Button btCariPelaksana;
    private widget.Button btCariUnit;
    private widget.Button btCetak;
    private widget.Button btHapus;
    private widget.Button btKeluar;
    private widget.Button btSimpan;
    private widget.Button btTindakan;
    private widget.Button button5;
    private widget.Button button7;
    private widget.Button button9;
    private widget.CekBox cekTgl;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.PanelGray panelGray2;
    private widget.PanelGray panelGray3;
    private widget.PanelGray panelGray6;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.TabPane tabCari;
    private widget.TabPane tabTrans;
    private widget.Table tbList;
    private widget.Table tbPelaksana;
    private widget.Table tbProduk;
    private widget.Table tbTrans;
    private widget.Table tbUnit;
    private widget.Tanggal tglCari;
    // End of variables declaration//GEN-END:variables
}
