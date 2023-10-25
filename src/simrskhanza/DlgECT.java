/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package simrskhanza;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import javax.swing.table.TableColumn;
import fungsi.validasi;
import fungsi.sekuel;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jónï ïNdÖ
 */
public class DlgECT extends javax.swing.JDialog {
    private String NULL;
    public String kdDokter;
    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
//    String dateStamp = new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime()); 
    private final DefaultTableModel tabMode;
     DateFormat Tgl = new SimpleDateFormat("yyyy-MM-dd");
     DateFormat Jam = new SimpleDateFormat("HH:mm:ss");
     Date JamT = new Date();
     Date TglT = new Date();
     private final DefaultTableModel tabMode2;
     Calendar kalender = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
     private int i = 0;
     private sekuel sequel = new sekuel();
     private PreparedStatement ps;
     private ResultSet rs;
     private Connection koneksi=koneksiDB.condb();
     private validasi Valid=new validasi();

    /**
     * Creates new form DlgECT
     */
    public DlgECT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabMode=new DefaultTableModel(null,new Object[]{
                "Kode","Nama Tindakan", "Biaya Tindakan"
            });
        tabMode2=new DefaultTableModel(null,new Object[]{
                "Kode Rawat","No RM","Tanggal","Jam","Nama Tindakan", "Biaya Tindakan", "Dokter", "Petugas", "Status",
                "No"
            });
        
        tbECTPlh.setModel(tabMode2);
        tbECTPlh.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbECTPlh.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 10; i++) {
            TableColumn column = tbECTPlh.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(120);
            }else if(i==5){
                column.setPreferredWidth(120);
            }else if(i==6){
                column.setPreferredWidth(120);
            }else if(i==7){
                column.setPreferredWidth(120);
            }else if(i==8){
                column.setPreferredWidth(120);
            }else if(i==9){
                column.setPreferredWidth(1);
            }
        }
        tbECT.setModel(tabMode);
        tbECT.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbECT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 3; i++) {
            TableColumn column = tbECT.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(250);
            }else if(i==2){
                column.setPreferredWidth(250);
            }
        }
        tbECT.setDefaultRenderer(Object.class, new WarnaTable());
        
//        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
//        jScrollPane3.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
//        jScrollPane3.setComponentPopupMenu(MnECT);
//        jScrollPane3.setName("jScrollPane3"); // NOI18N
//        jScrollPane3.setOpaque(true);
        
//        tbECTPlh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
//        tbECTPlh.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
//        tbECTPlh.setComponentPopupMenu(MnECT);
//        tbECTPlh.setName("tbECTPlh"); // NOI18N
//        tbECTPlh.setOpaque(true);
    }
    
    public void setNoRm(String NoRwt,String NoRM,String Pasien, String kdDok){
        TNoRw.setText(NoRwt);
        TNoRM.setText(NoRM) ;
        TPasien.setText(Pasien);
        kdDokter = kdDok;
    }
    
    public void isCek(){
        btSimpan.setEnabled(akses.getpermintaan_ect());
//        if(akses.getjml2()>=1){
//            KdPetugas.setEditable(false);
//            BtnPetugas.setEnabled(false);
//            KdPetugas.setText(akses.getkode());
//            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
//        }  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MnECT = new javax.swing.JPopupMenu();
        MnValid = new javax.swing.JMenuItem();
        MnHapus = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi21 = new widget.panelisi2();
        label1 = new widget.Label();
        TNoRw = new widget.TextBox();
        label2 = new widget.Label();
        TNoRM = new widget.TextBox();
        label3 = new widget.Label();
        TPasien = new widget.TextBox();
        label4 = new widget.Label();
        tgl = new widget.Tanggal();
        panelisi22 = new widget.panelisi2();
        btSimpan = new widget.Button();
        button3 = new widget.Button();
        TCari = new widget.TextBox();
        button4 = new widget.Button();
        button5 = new widget.Button();
        tabReqECT = new widget.TabPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbECT = new widget.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbECTPlh = new widget.Table();

        MnValid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnValid.setText("Valid");
        MnValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnValidActionPerformed(evt);
            }
        });
        MnECT.add(MnValid);

        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        MnHapus.setText("Hapus");
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        MnECT.add(MnHapus);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Permintaan ECT"));

        label1.setText("No Rawat");

        TNoRw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNoRwActionPerformed(evt);
            }
        });

        label2.setText("No RM");

        label3.setText("Nama Pasien");

        label4.setText("Tanggal");

        javax.swing.GroupLayout panelisi21Layout = new javax.swing.GroupLayout(panelisi21);
        panelisi21.setLayout(panelisi21Layout);
        panelisi21Layout.setHorizontalGroup(
            panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelisi21Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelisi21Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TNoRM, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelisi21Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TNoRw, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelisi21Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelisi21Layout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        panelisi21Layout.setVerticalGroup(
            panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelisi21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TNoRw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelisi21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TNoRM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(panelisi21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelisi21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/129.png"))); // NOI18N
        btSimpan.setText("Tambah");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        button3.setText("Keluar");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelisi22Layout = new javax.swing.GroupLayout(panelisi22);
        panelisi22.setLayout(panelisi22Layout);
        panelisi22Layout.setHorizontalGroup(
            panelisi22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelisi22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(TCari, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelisi22Layout.setVerticalGroup(
            panelisi22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelisi22Layout.createSequentialGroup()
                .addGroup(panelisi22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelisi22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelisi22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelisi22Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelisi22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tabReqECT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabReqECTMousePressed(evt);
            }
        });

        tbECT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbECT);

        tabReqECT.addTab("Jenis Tindakan", jScrollPane2);

        tbECTPlh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbECTPlh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbECTPlhMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbECTPlh);

        tabReqECT.addTab("Tindakan", jScrollPane3);

        javax.swing.GroupLayout internalFrame1Layout = new javax.swing.GroupLayout(internalFrame1);
        internalFrame1.setLayout(internalFrame1Layout);
        internalFrame1Layout.setHorizontalGroup(
            internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelisi21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabReqECT, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelisi22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        internalFrame1Layout.setVerticalGroup(
            internalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame1Layout.createSequentialGroup()
                .addComponent(panelisi21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(tabReqECT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(panelisi22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(internalFrame1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//         Valid.tabelKosong(tabMode);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tampil(){
        try {
            ps = koneksi.prepareStatement("SELECT jns_perawatan_ect.kd_jenis_prw, jns_perawatan_ect.nm_perawatan, jns_perawatan_ect.sarana, jns_perawatan_ect.pelayanan, jns_perawatan_ect.sekretariat," +
            "jns_perawatan_ect.struktural, jns_perawatan_ect.total, jns_perawatan_ect.js_medis, jns_perawatan_ect.js_perawat, jns_perawatan_ect.tak_lgsg," +
            "jns_perawatan_ect.js_pelayanan, jns_perawatan_ect.js_penunjang FROM jns_perawatan_ect order by nm_perawatan asc");
            rs = ps.executeQuery();
            tabMode.setRowCount(0);
            while(rs.next()){
                tabMode.addRow(new Object[]{
                    rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getDouble("total") 
                });
            }
        }catch (Exception e){
            System.out.println("Notifikasi : "+e);
        }
            
    }
    
    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
                dispose();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        try {
        ps = koneksi.prepareStatement("SELECT jns_perawatan_ect.kd_jenis_prw, jns_perawatan_ect.nm_perawatan, jns_perawatan_ect.sarana, jns_perawatan_ect.pelayanan, jns_perawatan_ect.sekretariat,"+
            "jns_perawatan_ect.struktural, jns_perawatan_ect.total, jns_perawatan_ect.js_medis, jns_perawatan_ect.js_perawat, jns_perawatan_ect.tak_lgsg," +
            "jns_perawatan_ect.js_pelayanan, jns_perawatan_ect.js_penunjang FROM jns_perawatan_ect where jns_perawatan_ect.nm_perawatan like ? order by nm_perawatan asc");
        ps.setString(1, "%"+TCari.getText()+"%");
        rs = ps.executeQuery();
        tabMode.setRowCount(0);
        while (rs.next()){
//             tbECT.setModel(tabMode);
            
            tabMode.addRow(new Object[]{
                    rs.getString("kd_jenis_prw"),rs.getString("nm_perawatan"),rs.getString("total") 
                });
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        tampil();
    }//GEN-LAST:event_button5ActionPerformed

    private void simpan(){
        int sr = tbECT.getSelectedRow();
        try {
        ps = koneksi.prepareStatement("insert into permintaan_ect values (?,?,?,?,?,?,?,?,?)");
        ps.setString(1, TNoRw.getText());
        ps.setString(2, TNoRM.getText());
        ps.setString(3, (tbECT.getValueAt(sr, 0)).toString());
        ps.setString(4, "-");
        ps.setString(5, "Belum");
        ps.setString(6, NULL);
        ps.setString(7, (Tgl.format(JamT)));
        ps.setString(8, timeStamp);
        ps.setString(9, "-");
        ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Error :" +e.getMessage());
        }
    }
    
    
    
    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        simpan();
//        JOptionPane.showMessageDialog(null, "Q");
    }//GEN-LAST:event_btSimpanActionPerformed

    private void tabReqECTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabReqECTMousePressed
        if (tabReqECT.getSelectedIndex()==1){
            tampil_ect();
//            JOptionPane.showMessageDialog(null, "Q1");
        }
    }//GEN-LAST:event_tabReqECTMousePressed

    private void tbECTPlhMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbECTPlhMouseReleased
        if (evt.isPopupTrigger()){
            MnECT.show(this,evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_tbECTPlhMouseReleased

    private void MnValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnValidActionPerformed
        try {
        ps = koneksi.prepareStatement("update permintaan_ect set stts='Sudah' where no_rawat like ?");
        ps.setString(1, "%"+TNoRw.getText()+"%");
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Semua tindakan telah tervalid");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_MnValidActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        int sr = tbECTPlh.getSelectedRow();
        try {
        ps = koneksi.prepareStatement("delete from permintaan_ect where x1 like ?");
        ps.setString(1, "%"+(tbECTPlh.getValueAt(sr, 9)).toString()+"%");
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Tindakan telah dihapus");
        tampil_ect();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void TNoRwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNoRwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRwActionPerformed
   private void tampil_ect(){
       try {
        ps = koneksi.prepareStatement("SELECT permintaan_ect.no_rawat, permintaan_ect.no_rm, permintaan_ect.stts, permintaan_ect.tgl_periksa, permintaan_ect.jam, jns_perawatan_ect.nm_perawatan,\n" +
        "jns_perawatan_ect.total, dokter.nm_dokter, permintaan_ect.x1, permintaan_ect.kd_petugas FROM permintaan_ect\n" +
        "INNER JOIN jns_perawatan_ect ON jns_perawatan_ect.kd_jenis_prw = permintaan_ect.kd_tindakan\n" +
        "INNER JOIN dokter ON permintaan_ect.kd_dok = dokter.kd_dokter WHERE permintaan_ect.no_rawat like ? order by jns_perawatan_ect.nm_perawatan asc");
        ps.setString(1, "%"+TNoRw.getText()+"%");
        rs = ps.executeQuery();
        tabMode2.setRowCount(0);
        while (rs.next()){            
            tabMode2.addRow(new Object[]{
                rs.getString("no_rawat"),rs.getString("no_rm"),rs.getString("tgl_periksa"),rs.getString("jam"), 
                rs.getString("nm_perawatan"),rs.getString("total"), rs.getString("nm_dokter"), rs.getString("kd_petugas"), 
                rs.getString("stts"),rs.getString("x1")
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
            java.util.logging.Logger.getLogger(DlgECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgECT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgECT dialog = new DlgECT(new javax.swing.JFrame(), true);
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
    private javax.swing.JPopupMenu MnECT;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnValid;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Button btSimpan;
    private widget.Button button3;
    private widget.Button button4;
    private widget.Button button5;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private widget.Label label1;
    private widget.Label label2;
    private widget.Label label3;
    private widget.Label label4;
    private widget.panelisi2 panelisi21;
    private widget.panelisi2 panelisi22;
    private widget.TabPane tabReqECT;
    private widget.Table tbECT;
    private widget.Table tbECTPlh;
    private widget.Tanggal tgl;
    // End of variables declaration//GEN-END:variables

    
}
