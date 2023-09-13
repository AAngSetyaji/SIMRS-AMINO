/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianADLBerthelIndex extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private String finger="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianADLBerthelIndex(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Tgl.Lahir","JK","Tanggal","Mengontrol BAB","Skor BAB","Mengontrol BAK","Skor BAK",
            "Membersihkan Diri","Skor Membersihkan Diri","Penggunaan Toilet","Skor Penggunaan Toilet","Makan","Skor Makan","Pindah Tempat","Skor Pindah Tempat",
            "Mobilisasi","Skor Mobilisasi","Berpakaian","Skor Berpakaian","Naik Turun Tangga","Skor Naik Turun Tangga","Mandi","Skor Mandi","Jml Skor",
            "Total Skor","Level Skor","NIP","Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 29; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(80);
            }else if(i==7){
                column.setPreferredWidth(70);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setPreferredWidth(70);
            }else if(i==12){
                column.setPreferredWidth(80);
            }else if(i==13){
                column.setPreferredWidth(70);
            }else if(i==14){
                column.setPreferredWidth(80);
            }else if(i==15){
                column.setPreferredWidth(70);
            }else if(i==16){
                column.setPreferredWidth(80);
            }else if(i==17){
                column.setPreferredWidth(70);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(70);
            }else if(i==20){
                column.setPreferredWidth(80);
            }else if(i==21){
                column.setPreferredWidth(70);
            }else if(i==22){
                column.setPreferredWidth(80);
            }else if(i==23){
                column.setPreferredWidth(70);
            }else if(i==24){
                column.setPreferredWidth(80);
            }else if(i==25){
                column.setPreferredWidth(90);
            }else if(i==26){
                column.setPreferredWidth(80);
            }else if(i==27){
                column.setPreferredWidth(90);
            }else if(i==28){
                column.setPreferredWidth(80);
            }else if(i==29){
                column.setPreferredWidth(90);
//            }else if(i==30){
//                column.setPreferredWidth(80);
//            }else if(i==31){
//                column.setPreferredWidth(90);
//            }else if(i==32){
//                column.setPreferredWidth(80);
//            }else if(i==33){
//                column.setPreferredWidth(90);
//            }else if(i==34){
//                column.setPreferredWidth(80);
//            }else if(i==35){
//                column.setPreferredWidth(90);
//            }else if(i==36){
//                column.setPreferredWidth(80);
//            }else if(i==37){
//                column.setPreferredWidth(90);
//            }else if(i==38){
//                column.setPreferredWidth(80);
//            }else if(i==39){
//                column.setPreferredWidth(90);
//            }else if(i==40){
//                column.setPreferredWidth(80);
//            }else if(i==41){
//                column.setPreferredWidth(90);
//            }else if(i==42){
//                column.setPreferredWidth(200);
//            }else if(i==43){
//                column.setPreferredWidth(60);
//            }else if(i==44){
//                column.setPreferredWidth(75);
//            }else if(i==45){
//                column.setPreferredWidth(80);
//            }else if(i==46){
//                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        }); 
        
        ChkInput.setSelected(false);
        isForm();
        jam();
        
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianTambahanMelarikanDiri = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        LoadHTML = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel217 = new widget.Label();
        BAB = new widget.ComboBox();
        jLabel218 = new widget.Label();
        SkorBAB = new widget.TextBox();
        jLabel220 = new widget.Label();
        BAK = new widget.ComboBox();
        jLabel222 = new widget.Label();
        SkorBAK = new widget.TextBox();
        jLabel223 = new widget.Label();
        BersihDiri = new widget.ComboBox();
        jLabel225 = new widget.Label();
        SkorBersihDiri = new widget.TextBox();
        jLabel226 = new widget.Label();
        GunaToilet = new widget.ComboBox();
        jLabel228 = new widget.Label();
        SkorToilet = new widget.TextBox();
        jLabel229 = new widget.Label();
        Makan = new widget.ComboBox();
        jLabel231 = new widget.Label();
        SkorMakan = new widget.TextBox();
        jLabel232 = new widget.Label();
        PindahTempat = new widget.ComboBox();
        jLabel234 = new widget.Label();
        SkorPindah = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel236 = new widget.Label();
        Mobilisasi = new widget.ComboBox();
        jLabel238 = new widget.Label();
        SkorBerjalan = new widget.TextBox();
        TotalStatik = new widget.TextBox();
        jLabel246 = new widget.Label();
        jSeparator4 = new javax.swing.JSeparator();
        SkorTotal = new widget.Label();
        Level = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel247 = new widget.Label();
        Berpakaian = new widget.ComboBox();
        jLabel248 = new widget.Label();
        SkorBerpakaian = new widget.TextBox();
        jLabel251 = new widget.Label();
        Tangga = new widget.ComboBox();
        jLabel252 = new widget.Label();
        SkorTangga = new widget.TextBox();
        jLabel253 = new widget.Label();
        Mandi = new widget.ComboBox();
        jLabel254 = new widget.Label();
        SkorMandi = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianTambahanMelarikanDiri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianTambahanMelarikanDiri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianTambahanMelarikanDiri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setText("Formulir Penilaian Tambahan Berisiko Melarikan Diri");
        MnPenilaianTambahanMelarikanDiri.setName("MnPenilaianTambahanMelarikanDiri"); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setPreferredSize(new java.awt.Dimension(305, 26));
        MnPenilaianTambahanMelarikanDiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianTambahanMelarikanDiriActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianTambahanMelarikanDiri);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Assesmen Fungsional ADL Barthel Index ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-08-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-08-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 456));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 425));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-08-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(84, 40, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 80, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(178, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(243, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(308, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(373, 40, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(474, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(570, 40, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(761, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jLabel217.setText("<html>Mengendalikan rangsang <br>defekasi (mengontrol BAB)</html>");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(20, 80, 170, 30);

        BAB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inkontinen", "Kadang tak terkendali", "Mandiri" }));
        BAB.setName("BAB"); // NOI18N
        BAB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BABItemStateChanged(evt);
            }
        });
        BAB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BABActionPerformed(evt);
            }
        });
        BAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BABKeyPressed(evt);
            }
        });
        FormInput.add(BAB);
        BAB.setBounds(200, 80, 105, 23);

        jLabel218.setText("Skor :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(310, 80, 40, 23);

        SkorBAB.setEditable(false);
        SkorBAB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorBAB.setText("0");
        SkorBAB.setFocusTraversalPolicyProvider(true);
        SkorBAB.setName("SkorBAB"); // NOI18N
        SkorBAB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorBABActionPerformed(evt);
            }
        });
        FormInput.add(SkorBAB);
        SkorBAB.setBounds(350, 80, 35, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("<html>Mengendalikan rangsang <br>berkemih (mengontrol BAK)</html>");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(20, 120, 190, 23);

        BAK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inkontinen", "Kadang tak terkendali", "Mandiri" }));
        BAK.setName("BAK"); // NOI18N
        BAK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BAKItemStateChanged(evt);
            }
        });
        BAK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BAKKeyPressed(evt);
            }
        });
        FormInput.add(BAK);
        BAK.setBounds(200, 120, 105, 23);

        jLabel222.setText("Skor :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(310, 120, 40, 23);

        SkorBAK.setEditable(false);
        SkorBAK.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorBAK.setText("0");
        SkorBAK.setFocusTraversalPolicyProvider(true);
        SkorBAK.setName("SkorBAK"); // NOI18N
        SkorBAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorBAKActionPerformed(evt);
            }
        });
        FormInput.add(SkorBAK);
        SkorBAK.setBounds(350, 120, 35, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("<html>Membersihkan diri <br>(cuci muka, sisir rambut, menggosok gigi)</html>");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(20, 150, 160, 50);

        BersihDiri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Butuh pertolongan orang lain", "Mandiri" }));
        BersihDiri.setName("BersihDiri"); // NOI18N
        BersihDiri.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BersihDiriItemStateChanged(evt);
            }
        });
        BersihDiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BersihDiriActionPerformed(evt);
            }
        });
        BersihDiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BersihDiriKeyPressed(evt);
            }
        });
        FormInput.add(BersihDiri);
        BersihDiri.setBounds(200, 170, 105, 23);

        jLabel225.setText("Skor :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(310, 170, 40, 23);

        SkorBersihDiri.setEditable(false);
        SkorBersihDiri.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorBersihDiri.setText("0");
        SkorBersihDiri.setFocusTraversalPolicyProvider(true);
        SkorBersihDiri.setName("SkorBersihDiri"); // NOI18N
        FormInput.add(SkorBersihDiri);
        SkorBersihDiri.setBounds(350, 170, 35, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("<html>Penggunaan toilet masuk/keluar <br> (lepas, pakai celana, menyeka, menyiram)</html>");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(20, 210, 190, 40);

        GunaToilet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tergantung pertolongan orang lain", "Perlu pertolongan pada beberapa aktivitas", "Mandiri" }));
        GunaToilet.setName("GunaToilet"); // NOI18N
        GunaToilet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GunaToiletItemStateChanged(evt);
            }
        });
        GunaToilet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GunaToiletKeyPressed(evt);
            }
        });
        FormInput.add(GunaToilet);
        GunaToilet.setBounds(200, 220, 105, 23);

        jLabel228.setText("Skor :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(310, 220, 40, 23);

        SkorToilet.setEditable(false);
        SkorToilet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorToilet.setText("0");
        SkorToilet.setFocusTraversalPolicyProvider(true);
        SkorToilet.setName("SkorToilet"); // NOI18N
        FormInput.add(SkorToilet);
        SkorToilet.setBounds(350, 220, 35, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("Makan");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(20, 260, 190, 23);

        Makan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak mampu", "Perlu bantuan", "Mandiri" }));
        Makan.setName("Makan"); // NOI18N
        Makan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MakanItemStateChanged(evt);
            }
        });
        Makan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MakanKeyPressed(evt);
            }
        });
        FormInput.add(Makan);
        Makan.setBounds(200, 260, 105, 23);

        jLabel231.setText("Skor :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(310, 260, 40, 23);

        SkorMakan.setEditable(false);
        SkorMakan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorMakan.setText("0");
        SkorMakan.setFocusTraversalPolicyProvider(true);
        SkorMakan.setName("SkorMakan"); // NOI18N
        FormInput.add(SkorMakan);
        SkorMakan.setBounds(350, 260, 35, 23);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("<html>Pindah tempat dan <br> dari berbaring ke duduk</html>");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(410, 80, 130, 30);

        PindahTempat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak mampu", "Perlu banyak bantuan >2 orang", "Bantuan 1 orang", "Mandiri" }));
        PindahTempat.setName("PindahTempat"); // NOI18N
        PindahTempat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PindahTempatItemStateChanged(evt);
            }
        });
        PindahTempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PindahTempatKeyPressed(evt);
            }
        });
        FormInput.add(PindahTempat);
        PindahTempat.setBounds(590, 80, 105, 23);

        jLabel234.setText("Skor :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(700, 80, 40, 23);

        SkorPindah.setEditable(false);
        SkorPindah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorPindah.setText("0");
        SkorPindah.setFocusTraversalPolicyProvider(true);
        SkorPindah.setName("SkorPindah"); // NOI18N
        FormInput.add(SkorPindah);
        SkorPindah.setBounds(740, 80, 35, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 810, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(10, 300, 810, 1);

        jLabel236.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel236.setText("Mobilisasi / berjalan");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(410, 120, 190, 23);

        Mobilisasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak mampu", "Mobilitas dengan kursi roda", "Berjalan dengan walker", "Mandiri" }));
        Mobilisasi.setName("Mobilisasi"); // NOI18N
        Mobilisasi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MobilisasiItemStateChanged(evt);
            }
        });
        Mobilisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobilisasiKeyPressed(evt);
            }
        });
        FormInput.add(Mobilisasi);
        Mobilisasi.setBounds(590, 120, 105, 23);

        jLabel238.setText("Skor :");
        jLabel238.setName("jLabel238"); // NOI18N
        FormInput.add(jLabel238);
        jLabel238.setBounds(700, 120, 40, 23);

        SkorBerjalan.setEditable(false);
        SkorBerjalan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorBerjalan.setText("0");
        SkorBerjalan.setFocusTraversalPolicyProvider(true);
        SkorBerjalan.setName("SkorBerjalan"); // NOI18N
        FormInput.add(SkorBerjalan);
        SkorBerjalan.setBounds(740, 120, 35, 23);

        TotalStatik.setEditable(false);
        TotalStatik.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalStatik.setText("0");
        TotalStatik.setFocusTraversalPolicyProvider(true);
        TotalStatik.setName("TotalStatik"); // NOI18N
        TotalStatik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalStatikActionPerformed(evt);
            }
        });
        FormInput.add(TotalStatik);
        TotalStatik.setBounds(750, 310, 40, 23);

        jLabel246.setText("Jumlah Skor :");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(670, 310, 70, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(10, 340, 810, 1);

        SkorTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SkorTotal.setText("0");
        SkorTotal.setName("SkorTotal"); // NOI18N
        FormInput.add(SkorTotal);
        SkorTotal.setBounds(190, 340, 110, 23);

        Level.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Level.setText("Level");
        Level.setName("Level"); // NOI18N
        FormInput.add(Level);
        Level.setBounds(360, 340, 160, 23);

        jLabel34.setText("Level :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(290, 340, 60, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Total Skor Barthel Index");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(20, 340, 160, 23);

        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel247.setText("Berpakaian ");
        jLabel247.setName("jLabel247"); // NOI18N
        FormInput.add(jLabel247);
        jLabel247.setBounds(410, 170, 190, 23);

        Berpakaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tergantung orang lain", "Sebagian dibantu", "Mandiri" }));
        Berpakaian.setName("Berpakaian"); // NOI18N
        Berpakaian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BerpakaianItemStateChanged(evt);
            }
        });
        Berpakaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerpakaianKeyPressed(evt);
            }
        });
        FormInput.add(Berpakaian);
        Berpakaian.setBounds(590, 170, 105, 23);

        jLabel248.setText("Skor :");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(700, 170, 40, 23);

        SkorBerpakaian.setEditable(false);
        SkorBerpakaian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorBerpakaian.setText("0");
        SkorBerpakaian.setFocusTraversalPolicyProvider(true);
        SkorBerpakaian.setName("SkorBerpakaian"); // NOI18N
        FormInput.add(SkorBerpakaian);
        SkorBerpakaian.setBounds(740, 170, 35, 23);

        jLabel251.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel251.setText("Naik turun tangga");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(410, 220, 190, 23);

        Tangga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak mampu", "Butuh pertolongan", "Mandiri" }));
        Tangga.setName("Tangga"); // NOI18N
        Tangga.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggaItemStateChanged(evt);
            }
        });
        Tangga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggaKeyPressed(evt);
            }
        });
        FormInput.add(Tangga);
        Tangga.setBounds(590, 220, 105, 23);

        jLabel252.setText("Skor :");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(700, 220, 40, 23);

        SkorTangga.setEditable(false);
        SkorTangga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorTangga.setText("0");
        SkorTangga.setFocusTraversalPolicyProvider(true);
        SkorTangga.setName("SkorTangga"); // NOI18N
        FormInput.add(SkorTangga);
        SkorTangga.setBounds(740, 220, 35, 23);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("Mandi");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(410, 260, 190, 23);

        Mandi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tergantung orang lain", "Mandiri" }));
        Mandi.setName("Mandi"); // NOI18N
        Mandi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MandiItemStateChanged(evt);
            }
        });
        Mandi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MandiActionPerformed(evt);
            }
        });
        Mandi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MandiKeyPressed(evt);
            }
        });
        FormInput.add(Mandi);
        Mandi.setBounds(590, 260, 105, 23);

        jLabel254.setText("Skor :");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(700, 260, 40, 23);

        SkorMandi.setEditable(false);
        SkorMandi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkorMandi.setText("0");
        SkorMandi.setFocusTraversalPolicyProvider(true);
        SkorMandi.setName("SkorMandi"); // NOI18N
        FormInput.add(SkorMandi);
        SkorMandi.setBounds(740, 260, 35, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(Sequel.menyimpantf("penilaian_barthel_index","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",26,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),NIP.getText(),
                BAB.getSelectedItem().toString(),SkorBAB.getText(),BAK.getSelectedItem().toString(),SkorBAK.getText(),BersihDiri.getSelectedItem().toString(),SkorBersihDiri.getText(),
                GunaToilet.getSelectedItem().toString(),SkorToilet.getText(),Makan.getSelectedItem().toString(),SkorMakan.getText(),PindahTempat.getSelectedItem().toString(),SkorPindah.getText(), 
                Mobilisasi.getSelectedItem().toString(),SkorBerjalan.getText(),Berpakaian.getSelectedItem().toString(),SkorBerpakaian.getText(),Tangga.getSelectedItem().toString(),SkorTangga.getText(),Mandi.getSelectedItem().toString(),SkorMandi.getText(),
                TotalStatik.getText(),SkorTotal.getText(),Level.getText()
            })==true){
                tabMode.addRow(new String[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JK.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
                    BAB.getSelectedItem().toString(),SkorBAB.getText(),BAK.getSelectedItem().toString(),SkorBAK.getText(),BersihDiri.getSelectedItem().toString(),SkorBersihDiri.getText(),
                    GunaToilet.getSelectedItem().toString(),SkorToilet.getText(),Makan.getSelectedItem().toString(),SkorMakan.getText(),PindahTempat.getSelectedItem().toString(),SkorPindah.getText(), 
                    Mobilisasi.getSelectedItem().toString(),SkorBerjalan.getText(),Berpakaian.getSelectedItem().toString(),SkorBerpakaian.getText(),Tangga.getSelectedItem().toString(),SkorTangga.getText(),Mandi.getSelectedItem().toString(),SkorMandi.getText(),
                    TotalStatik.getText(),SkorTotal.getText(),Level.getText(),NIP.getText(),NamaPetugas.getText()
                });
                emptTeks();
                LCount.setText(""+tabMode.getRowCount());
            }  
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,PindahTempat,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.R.M.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>JK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml Skor Statik</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml Skor Dinamis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor-faktor Pencegahan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Skor</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Level Skor</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NIP</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Petugas</b></td>"+
                    "</tr>"
                );
                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
                            "<td valign='top'>"+tbObat.getValueAt(i,21).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,22).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,23).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,24).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,25).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,26).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,27).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,28).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,29).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,30).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,31).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,32).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,33).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,34).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,35).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,36).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,37).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,38).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,39).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,40).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,41).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,42).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,43).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,44).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,45).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,46).toString()+"</td>"+
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='4400px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>"
                );

                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                    ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                    ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                    ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                    ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                );
                bg.close();

                File f = new File("DataPenilaianTambahanBerisikoMelarikanDiri.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='4400px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN TAMBAHAN BERISIKO MELARIKAN DIRI<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
                );
                bw.close();                         
                Desktop.getDesktop().browse(f.toURI());
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,Detik,BAB);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnPenilaianTambahanMelarikanDiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianTambahanMelarikanDiriActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),30).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),29).toString():finger)+"\n"+Tanggal.getSelectedItem());
            Valid.MyReportqry("rptPenilaianBarthelIndex.jasper","report","::[ Assesment Fungsional ADL Barthel Index ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_barthel_index.tanggal,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y')as tgl_registrasi,reg_periksa.jam_reg,"+
                    "penilaian_barthel_index.bab,penilaian_barthel_index.skorbab,penilaian_barthel_index.bak,penilaian_barthel_index.skorbak,penilaian_barthel_index.bersihdiri,penilaian_barthel_index.skorbersihdiri," +
                    "penilaian_barthel_index.gunatoliet,penilaian_barthel_index.skorgunatoilet,penilaian_barthel_index.makan,penilaian_barthel_index.skormakan,penilaian_barthel_index.pindah,penilaian_barthel_index.skorpindah," +
                    "penilaian_barthel_index.mobilisasi,penilaian_barthel_index.skormobilisasi,penilaian_barthel_index.berpakaian,penilaian_barthel_index.skorberpakaian,penilaian_barthel_index.tangga," +
                    "penilaian_barthel_index.skortangga,penilaian_barthel_index.mandi,penilaian_barthel_index.skormandi,penilaian_barthel_index.statik_skortotal," +
                    "penilaian_barthel_index.total_skor,penilaian_barthel_index.level_skor,penilaian_barthel_index.nip,"+
                    "petugas.nama,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.umurdaftar,reg_periksa.sttsumur "+
                    "from penilaian_barthel_index inner join reg_periksa on penilaian_barthel_index.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on penilaian_barthel_index.nip=petugas.nip inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianTambahanMelarikanDiriActionPerformed

    private void BABItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BABItemStateChanged
        if(BAB.getSelectedIndex()==0){
            SkorBAB.setText("0");
        }else if(BAB.getSelectedIndex()==1){
            SkorBAB.setText("1");
        }else{
            SkorBAB.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_BABItemStateChanged

    private void BABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BABKeyPressed
        Valid.pindah(evt,btnPetugas,BAK);
    }//GEN-LAST:event_BABKeyPressed

    private void BAKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BAKItemStateChanged
        if(BAK.getSelectedIndex()==0){
            SkorBAK.setText("0");
        }else if(BAK.getSelectedIndex()==1){
            SkorBAK.setText("1");
        }else{
            SkorBAK.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_BAKItemStateChanged

    private void BAKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BAKKeyPressed
        Valid.pindah(evt,BAB,BersihDiri);
    }//GEN-LAST:event_BAKKeyPressed

    private void BersihDiriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BersihDiriItemStateChanged
        if(BersihDiri.getSelectedIndex()==0){
            SkorBersihDiri.setText("0");
        }else{
            SkorBersihDiri.setText("1");
        }
        isTotalSkor();
    }//GEN-LAST:event_BersihDiriItemStateChanged

    private void BersihDiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BersihDiriKeyPressed
        Valid.pindah(evt,BAK,GunaToilet);
    }//GEN-LAST:event_BersihDiriKeyPressed

    private void GunaToiletItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GunaToiletItemStateChanged
        if(GunaToilet.getSelectedIndex()==0){
            SkorToilet.setText("0");
        }else if(GunaToilet.getSelectedIndex()==1){
            SkorToilet.setText("1");
        }else{
            SkorToilet.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_GunaToiletItemStateChanged

    private void GunaToiletKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GunaToiletKeyPressed
        Valid.pindah(evt,BersihDiri,Makan);
    }//GEN-LAST:event_GunaToiletKeyPressed

    private void MakanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MakanItemStateChanged
        if(Makan.getSelectedIndex()==0){
            SkorMakan.setText("0");
        }else if(Makan.getSelectedIndex()==1){
            SkorMakan.setText("1");
        }else {
            SkorMakan.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_MakanItemStateChanged

    private void MakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MakanKeyPressed
        Valid.pindah(evt,GunaToilet,PindahTempat);
    }//GEN-LAST:event_MakanKeyPressed

    private void PindahTempatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PindahTempatItemStateChanged
        if(PindahTempat.getSelectedIndex()==0){
            SkorPindah.setText("0");
        }else if(PindahTempat.getSelectedIndex()==1){
            SkorPindah.setText("1");
        }else if(PindahTempat.getSelectedIndex()==2){
            SkorPindah.setText("2");
        }else{
            SkorPindah.setText("3");
        }
        isTotalSkor();
    }//GEN-LAST:event_PindahTempatItemStateChanged

    private void PindahTempatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PindahTempatKeyPressed
        Valid.pindah(evt,Makan,Mobilisasi);
    }//GEN-LAST:event_PindahTempatKeyPressed

    private void MobilisasiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MobilisasiItemStateChanged
        if(Mobilisasi.getSelectedIndex()==0){
            SkorBerjalan.setText("0");
        }else if(Mobilisasi.getSelectedIndex()==1){
            SkorBerjalan.setText("1");
        }else if(Mobilisasi.getSelectedIndex()==2){
            SkorBerjalan.setText("2");
        }else{
            SkorBerjalan.setText("3");
        }
        isTotalSkor();
    }//GEN-LAST:event_MobilisasiItemStateChanged

    private void MobilisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobilisasiKeyPressed
        Valid.pindah(evt,PindahTempat,Berpakaian);
    }//GEN-LAST:event_MobilisasiKeyPressed

    private void BerpakaianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BerpakaianItemStateChanged
        if(Berpakaian.getSelectedIndex()==0){
            SkorBerpakaian.setText("0");
        }else if(Berpakaian.getSelectedIndex()==1){
            SkorBerpakaian.setText("1");
        }else {
            SkorBerpakaian.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_BerpakaianItemStateChanged

    private void BerpakaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BerpakaianKeyPressed
        Valid.pindah(evt,Mobilisasi,Tangga);
    }//GEN-LAST:event_BerpakaianKeyPressed

    private void TanggaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggaItemStateChanged
        if(Tangga.getSelectedIndex()==0){
            SkorTangga.setText("0");
        }else if(Tangga.getSelectedIndex()==1){
            SkorTangga.setText("1");
        }else {
            SkorTangga.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_TanggaItemStateChanged

    private void TanggaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggaKeyPressed
        Valid.pindah(evt,Berpakaian,PindahTempat);
    }//GEN-LAST:event_TanggaKeyPressed

    private void SkorBABActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorBABActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorBABActionPerformed

    private void MandiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MandiItemStateChanged
        if(Mandi.getSelectedIndex()==0){
            SkorMandi.setText("0");
        }else if(Mandi.getSelectedIndex()==1){
            SkorMandi.setText("1");
        }else {
            SkorMandi.setText("2");
        }
        isTotalSkor();
    }//GEN-LAST:event_MandiItemStateChanged

    private void MandiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MandiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MandiKeyPressed

    private void MandiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MandiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MandiActionPerformed

    private void BersihDiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BersihDiriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BersihDiriActionPerformed

    private void TotalStatikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalStatikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalStatikActionPerformed

    private void BABActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BABActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BABActionPerformed

    private void SkorBAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorBAKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorBAKActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianADLBerthelIndex dialog = new RMPenilaianADLBerthelIndex(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.ComboBox BAB;
    private widget.ComboBox BAK;
    private widget.ComboBox Berpakaian;
    private widget.ComboBox BersihDiri;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox GunaToilet;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.Label LCount;
    private widget.Label Level;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Makan;
    private widget.ComboBox Mandi;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnPenilaianTambahanMelarikanDiri;
    private widget.ComboBox Mobilisasi;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.ComboBox PindahTempat;
    private widget.ScrollPane Scroll;
    private widget.TextBox SkorBAB;
    private widget.TextBox SkorBAK;
    private widget.TextBox SkorBerjalan;
    private widget.TextBox SkorBerpakaian;
    private widget.TextBox SkorBersihDiri;
    private widget.TextBox SkorMakan;
    private widget.TextBox SkorMandi;
    private widget.TextBox SkorPindah;
    private widget.TextBox SkorTangga;
    private widget.TextBox SkorToilet;
    private widget.Label SkorTotal;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.ComboBox Tangga;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.TextBox TotalStatik;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel217;
    private widget.Label jLabel218;
    private widget.Label jLabel220;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel234;
    private widget.Label jLabel236;
    private widget.Label jLabel238;
    private widget.Label jLabel246;
    private widget.Label jLabel247;
    private widget.Label jLabel248;
    private widget.Label jLabel251;
    private widget.Label jLabel252;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
     public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_barthel_index.tanggal,"+
                    "penilaian_barthel_index.bab,penilaian_barthel_index.skorbab,penilaian_barthel_index.bak,penilaian_barthel_index.skorbak,penilaian_barthel_index.bersihdiri,penilaian_barthel_index.skorbersihdiri," +
                    "penilaian_barthel_index.gunatoliet,penilaian_barthel_index.skorgunatoilet,penilaian_barthel_index.makan,penilaian_barthel_index.skormakan,penilaian_barthel_index.pindah,penilaian_barthel_index.skorpindah," +
                    "penilaian_barthel_index.mobilisasi,penilaian_barthel_index.skormobilisasi,penilaian_barthel_index.berpakaian,penilaian_barthel_index.skorberpakaian,penilaian_barthel_index.tangga," +
                    "penilaian_barthel_index.skortangga,penilaian_barthel_index.mandi,penilaian_barthel_index.skormandi,penilaian_barthel_index.statik_skortotal," +
                    "penilaian_barthel_index.total_skor,penilaian_barthel_index.level_skor,penilaian_barthel_index.nip,"+
                    "petugas.nama "+
                    "from penilaian_barthel_index inner join reg_periksa on penilaian_barthel_index.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_barthel_index.nip=petugas.nip where "+
                    "penilaian_barthel_index.tanggal between ? and ? order by penilaian_barthel_index.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_barthel_index.tanggal,"+
                    "penilaian_barthel_index.bab,penilaian_barthel_index.skorbab,penilaian_barthel_index.bak,penilaian_barthel_index.skorbak,penilaian_barthel_index.bersihdiri,penilaian_barthel_index.skorbersihdiri," +
                    "penilaian_barthel_index.gunatoliet,penilaian_barthel_index.skorgunatoilet,penilaian_barthel_index.makan,penilaian_barthel_index.skormakan,penilaian_barthel_index.pindah,penilaian_barthel_index.skorpindah," +
                    "penilaian_barthel_index.mobilisasi,penilaian_barthel_index.skormobilisasi,penilaian_barthel_index.berpakaian,penilaian_barthel_index.skorberpakaian,penilaian_barthel_index.tangga," +
                    "penilaian_barthel_index.skortangga,penilaian_barthel_index.mandi,penilaian_barthel_index.skormandi,penilaian_barthel_index.statik_skortotal," +
                    "penilaian_barthel_index.total_skor,penilaian_barthel_index.level_skor,penilaian_barthel_index.nip,"+
                    "petugas.nama "+
                    "from penilaian_barthel_index inner join reg_periksa on penilaian_barthel_index.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_barthel_index.nip=petugas.nip where "+
                    "penilaian_barthel_index.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_barthel_index.nip like ? or petugas.nama like ?) "+
                    "order by penilaian_barthel_index.tanggal ");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("tanggal"),
                        rs.getString("bab"),rs.getString("skorbab"),rs.getString("bak"),
                        rs.getString("skorbak"),rs.getString("bersihdiri"),rs.getString("skorbersihdiri"),rs.getString("gunatoliet"),
                        rs.getString("skorgunatoilet"),rs.getString("makan"),rs.getString("skormakan"),rs.getString("pindah"),
                        rs.getString("skorpindah"),rs.getString("mobilisasi"),rs.getString("skormobilisasi"),
                        rs.getString("berpakaian"),rs.getString("skorberpakaian"),rs.getString("tangga"),
                        rs.getString("skortangga"),rs.getString("mandi"),rs.getString("skormandi"),rs.getString("statik_skortotal"),rs.getString("total_skor"),
                        rs.getString("level_skor"),rs.getString("nip"),rs.getString("nama")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void emptTeks() {
        Tanggal.setDate(new Date());
        BAB.setSelectedIndex(0);
        SkorBAB.setText("0");
        BAK.setSelectedIndex(0);
        SkorBAK.setText("0");
        BersihDiri.setSelectedIndex(0);
        SkorBersihDiri.setText("0");
        GunaToilet.setSelectedIndex(0);
        SkorToilet.setText("0");
        Makan.setSelectedIndex(0);
        SkorMakan.setText("0");
        PindahTempat.setSelectedIndex(0);
        SkorPindah.setText("0");
        Mobilisasi.setSelectedIndex(0);
        SkorBerjalan.setText("0");
        Berpakaian.setSelectedIndex(0);
        SkorBerpakaian.setText("0");
        Tangga.setSelectedIndex(0);
        SkorTangga.setText("0");
        Mandi.setSelectedIndex(0);
        SkorMandi.setText("0");
        TotalStatik.setText("0");
        SkorTotal.setText("0");
        Level.setText("");
        BAB.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            
            BAB.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            SkorBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            BAK.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            SkorBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            BersihDiri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            SkorBersihDiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            GunaToilet.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            SkorToilet.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Makan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            SkorMakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            PindahTempat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            SkorPindah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Mobilisasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            SkorBerjalan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            Berpakaian.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            SkorBerpakaian.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Tangga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            SkorTangga.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            Mandi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            SkorMandi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            TotalStatik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Level.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(11,13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(14,16));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(17,19));
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
        }
    }
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        try {
            ps=koneksi.prepareStatement("select pasien.nm_pasien,pasien.jk,pasien.tgl_lahir as lahir from pasien where pasien.no_rkm_medis=?");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }finally {
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>628){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,456));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-172));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_barthel_index());
        BtnHapus.setEnabled(akses.getpenilaian_barthel_index());
        BtnEdit.setEnabled(akses.getpenilaian_barthel_index());
        BtnPrint.setEnabled(akses.getpenilaian_barthel_index()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if(Sequel.mengedittf("penilaian_barthel_index","no_rawat=?","no_rawat=?,tanggal=?,nip=?,"+
                "bab=?,skorbab=?,bak=?,skorbak=?,bersihdiri=?,skorbersihdiri=?,gunatoliet=?,skorgunatoilet=?,makan=?,skormakan=?," +
                "pindah=?,skorpindah=?,mobilisasi=?,skormobilisasi=?,berpakaian=?,skorberpakaian=?,tangga=?,skortangga=?,mandi=?,skormandi=?," +
                "statik_skortotal=?,total_skor=?,level_skor=?",27,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),NIP.getText(),
                BAB.getSelectedItem().toString(),SkorBAB.getText(),BAK.getSelectedItem().toString(),SkorBAK.getText(),BersihDiri.getSelectedItem().toString(),SkorBersihDiri.getText(),
                GunaToilet.getSelectedItem().toString(),SkorToilet.getText(),Makan.getSelectedItem().toString(),SkorMakan.getText(),PindahTempat.getSelectedItem().toString(),SkorPindah.getText(), 
                Mobilisasi.getSelectedItem().toString(),SkorBerjalan.getText(),Berpakaian.getSelectedItem().toString(),SkorBerpakaian.getText(),Tangga.getSelectedItem().toString(),SkorTangga.getText(),Mandi.getSelectedItem().toString(),SkorMandi.getText(),
                TotalStatik.getText(),SkorTotal.getText(),Level.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
            tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
            tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
            tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
            tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
            tbObat.setValueAt(JK.getText(),tbObat.getSelectedRow(),4);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),tbObat.getSelectedRow(),5);
            tbObat.setValueAt(BAB.getSelectedItem().toString(),tbObat.getSelectedRow(),6);
            tbObat.setValueAt(SkorBAB.getText(),tbObat.getSelectedRow(),7);
            tbObat.setValueAt(BAK.getSelectedItem().toString(),tbObat.getSelectedRow(),8);
            tbObat.setValueAt(SkorBAK.getText(),tbObat.getSelectedRow(),9);
            tbObat.setValueAt(BersihDiri.getSelectedItem().toString(),tbObat.getSelectedRow(),10);
            tbObat.setValueAt(SkorBersihDiri.getText(),tbObat.getSelectedRow(),11);
            tbObat.setValueAt(GunaToilet.getSelectedItem().toString(),tbObat.getSelectedRow(),12);
            tbObat.setValueAt(SkorToilet.getText(),tbObat.getSelectedRow(),13);
            tbObat.setValueAt(Makan.getSelectedItem().toString(),tbObat.getSelectedRow(),14);
            tbObat.setValueAt(SkorMakan.getText(),tbObat.getSelectedRow(),15);
            tbObat.setValueAt(PindahTempat.getSelectedItem().toString(),tbObat.getSelectedRow(),16);
            tbObat.setValueAt(SkorPindah.getText(),tbObat.getSelectedRow(),17);
            tbObat.setValueAt(Mobilisasi.getSelectedItem().toString(),tbObat.getSelectedRow(),18);
            tbObat.setValueAt(SkorBerjalan.getText(),tbObat.getSelectedRow(),19);
            tbObat.setValueAt(Berpakaian.getSelectedItem().toString(),tbObat.getSelectedRow(),20);
            tbObat.setValueAt(SkorBerpakaian.getText(),tbObat.getSelectedRow(),21);
            tbObat.setValueAt(Tangga.getSelectedItem().toString(),tbObat.getSelectedRow(),22);
            tbObat.setValueAt(SkorTangga.getText(),tbObat.getSelectedRow(),23);
            tbObat.setValueAt(Mandi.getSelectedItem().toString(),tbObat.getSelectedRow(),24);
            tbObat.setValueAt(SkorMandi.getText(),tbObat.getSelectedRow(),25);
            tbObat.setValueAt(TotalStatik.getText(),tbObat.getSelectedRow(),26);
            tbObat.setValueAt(SkorTotal.getText(),tbObat.getSelectedRow(),27);
            tbObat.setValueAt(Level.getText(),tbObat.getSelectedRow(),28);
            tbObat.setValueAt(NIP.getText(),tbObat.getSelectedRow(),29);
            tbObat.setValueAt(NamaPetugas.getText(),tbObat.getSelectedRow(),30);
            emptTeks();
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_barthel_index where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    private void isTotalSkor(){
        try {
            TotalStatik.setText((Integer.parseInt(SkorBAB.getText())+Integer.parseInt(SkorBAK.getText())+Integer.parseInt(SkorBersihDiri.getText())+Integer.parseInt(SkorToilet.getText())+Integer.parseInt(SkorMakan.getText())+Integer.parseInt(SkorPindah.getText())+Integer.parseInt(SkorBerjalan.getText())+Integer.parseInt(SkorBerpakaian.getText())+Integer.parseInt(SkorTangga.getText())+Integer.parseInt(SkorMandi.getText()))+"");
            SkorTotal.setText((Integer.parseInt(TotalStatik.getText()))+"");
            if(Integer.parseInt(SkorTotal.getText())<=4){
                Level.setText("Ketergantungan Penuh");
            }else if(Integer.parseInt(SkorTotal.getText())>4 && Integer.parseInt(SkorTotal.getText())<=8){
                Level.setText("Ketergantungan berat");
            }else if(Integer.parseInt(SkorTotal.getText())>8 && Integer.parseInt(SkorTotal.getText())<=12){
                Level.setText("Ketergantungan sebagian");
            }else if(Integer.parseInt(SkorTotal.getText())>12 && Integer.parseInt(SkorTotal.getText())<=16){
                Level.setText("Ketergantungan ringan");
            }else if(Integer.parseInt(SkorTotal.getText())>16 && Integer.parseInt(SkorTotal.getText())<=20){
                Level.setText("Mandiri");
            }
        } catch (Exception e) {
            SkorTotal.setText("0");
            Level.setText("");
        }
    }
}
