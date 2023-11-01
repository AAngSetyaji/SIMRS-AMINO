/*
 * Kontribusi dari tim IT RSUD Prembun
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
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMForm3 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMForm3(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Tekanan Darah","Nadi","Pernapasan (RR)","Suhu (Celcius)","Sistem Pencernaan","Sistem Jantung dan pembuluh darah", 
            "Sistem Pernapasan","Sistem Saraf Pusat","THT dan Kulit","Keterangan","Benzodiazepin","Skor Benzodiazepin","Kanabis","Skor Kanabis","Opiat","Skor Opiat","Amfetamin","Skor Amfetamin","Kokain","Skor Kokain","Barbiturat","Skor Barbiturat","Alkohol","Skor Alkohol","Total Skor"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 32; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(115);
            }else if(i==8){
                column.setPreferredWidth(170);
            }else if(i==9){
                column.setPreferredWidth(50);
            }else if(i==10){
                column.setPreferredWidth(170);
            }else if(i==11){
                column.setPreferredWidth(170);
            }else if(i==12){
                column.setPreferredWidth(170);
            }else if(i==13){
                column.setPreferredWidth(170);
            }else if(i==14){
                column.setPreferredWidth(170);
            }else if(i==15){
                column.setPreferredWidth(170);
            }else if(i==16){
                column.setPreferredWidth(170);
            }else if(i==17){
                column.setPreferredWidth(170);
            }else if(i==18){
                column.setPreferredWidth(170);
            }else if(i==19){
                column.setPreferredWidth(170);
            }else if(i==20){
                column.setPreferredWidth(170);
            }else if(i==21){
                column.setPreferredWidth(170);
            }else if(i==22){
                column.setPreferredWidth(170);
            }else if(i==23){
                column.setPreferredWidth(170);
            }else if(i==24){
                column.setPreferredWidth(170);
            }else if(i==25){
                column.setPreferredWidth(170);
            }else if(i==26){
                column.setPreferredWidth(170);
            }else if(i==27){
                column.setPreferredWidth(170);
            }else if(i==28){
                column.setPreferredWidth(170);
            }else if(i==29){
                column.setPreferredWidth(170);
            }else if(i==30){
                column.setPreferredWidth(150);
            }else if(i==31){
                column.setPreferredWidth(100);
            }else if(i==32){
                column.setPreferredWidth(50);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
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
        
         dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){ 
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());   
                }              
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
         
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KdDokter.requestFocus();
                }
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

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianMedis = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel22 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel26 = new widget.Label();
        skor1 = new widget.TextBox();
        jLabel30 = new widget.Label();
        comboBox1 = new widget.ComboBox();
        comboBox2 = new widget.ComboBox();
        jLabel27 = new widget.Label();
        skor2 = new widget.TextBox();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        comboBox3 = new widget.ComboBox();
        jLabel52 = new widget.Label();
        comboBox4 = new widget.ComboBox();
        jLabel53 = new widget.Label();
        comboBox5 = new widget.ComboBox();
        comboBox6 = new widget.ComboBox();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        comboBox7 = new widget.ComboBox();
        jLabel56 = new widget.Label();
        comboBox8 = new widget.ComboBox();
        jLabel28 = new widget.Label();
        skor3 = new widget.TextBox();
        skor4 = new widget.TextBox();
        jLabel29 = new widget.Label();
        skor5 = new widget.TextBox();
        jLabel57 = new widget.Label();
        skor6 = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        skor7 = new widget.TextBox();
        skor8 = new widget.TextBox();
        jLabel60 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        comboBox9 = new widget.ComboBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        comboBox10 = new widget.ComboBox();
        jLabel65 = new widget.Label();
        comboBox11 = new widget.ComboBox();
        comboBox12 = new widget.ComboBox();
        jLabel66 = new widget.Label();
        comboBox13 = new widget.ComboBox();
        comboBox14 = new widget.ComboBox();
        jLabel67 = new widget.Label();
        comboBox15 = new widget.ComboBox();
        comboBox16 = new widget.ComboBox();
        jLabel68 = new widget.Label();
        comboBox17 = new widget.ComboBox();
        comboBox18 = new widget.ComboBox();
        comboBox19 = new widget.ComboBox();
        jLabel69 = new widget.Label();
        comboBox20 = new widget.ComboBox();
        comboBox21 = new widget.ComboBox();
        jLabel70 = new widget.Label();
        comboBox22 = new widget.ComboBox();
        jLabel71 = new widget.Label();
        comboBox23 = new widget.ComboBox();
        comboBox24 = new widget.ComboBox();
        jLabel72 = new widget.Label();
        comboBox25 = new widget.ComboBox();
        comboBox26 = new widget.ComboBox();
        jLabel73 = new widget.Label();
        skor9 = new widget.TextBox();
        skor10 = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel15 = new widget.Label();
        jLabel75 = new widget.Label();
        skor11 = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        comboBox27 = new widget.ComboBox();
        comboBox28 = new widget.ComboBox();
        comboBox29 = new widget.ComboBox();
        comboBox30 = new widget.ComboBox();
        jLabel80 = new widget.Label();
        jLabel79 = new widget.Label();
        comboBox31 = new widget.ComboBox();
        comboBox32 = new widget.ComboBox();
        jLabel81 = new widget.Label();
        comboBox33 = new widget.ComboBox();
        comboBox34 = new widget.ComboBox();
        jLabel82 = new widget.Label();
        comboBox35 = new widget.ComboBox();
        comboBox36 = new widget.ComboBox();
        jLabel83 = new widget.Label();
        comboBox37 = new widget.ComboBox();
        comboBox38 = new widget.ComboBox();
        jLabel84 = new widget.Label();
        comboBox39 = new widget.ComboBox();
        comboBox40 = new widget.ComboBox();
        jLabel85 = new widget.Label();
        comboBox41 = new widget.ComboBox();
        comboBox42 = new widget.ComboBox();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        skor12 = new widget.TextBox();
        skor13 = new widget.TextBox();
        skor14 = new widget.TextBox();
        jLabel95 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianMedis.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianMedis.setText("Laporan Penilaian PANSS-EC");
        MnPenilaianMedis.setName("MnPenilaianMedis"); // NOI18N
        MnPenilaianMedis.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPenilaianMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianMedisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianMedis);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Form 3 ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(467, 500));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 480));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1100));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(166, 40, 50, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(220, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(312, 40, 180, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(494, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(80, 40, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 750, 1);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(538, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-10-2023 09:37:42" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(594, 40, 130, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 750, 1);

        jLabel13.setText("STATUS PSIKIATRIS");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 960, 140, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(10, 180, 750, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(10, 180, 750, 1);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(10, 940, 750, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(10, 940, 750, 1);

        jLabel22.setText("1. Dalam situasi seperti apakah anda tinggal 3 tahun belakangan ini?");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 110, 360, 23);

        jLabel24.setText("J.K. :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 40, 70, 23);

        jLabel26.setText("Skor :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(410, 140, 80, 23);

        skor1.setName("skor1"); // NOI18N
        FormInput.add(skor1);
        skor1.setBounds(510, 140, 110, 24);

        jLabel30.setText("2. Apakah anda hidup dengan seseorang yang mempunyai masalah penyalahgunaan zat sekarang ini?");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 190, 520, 23);

        comboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox1.setName("comboBox1"); // NOI18N
        comboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox1ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox1);
        comboBox1.setBounds(190, 300, 70, 20);

        comboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dengan pasangan dan anak", "Dengan pasangan saja", "Dengan anak saja", "Dengan orang tua", "Dengan Keluarga", "Dengan teman", "Sendiri", "Lingkungan terkontrol", "Kondisi yang tidak stabil" }));
        comboBox2.setName("comboBox2"); // NOI18N
        comboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox2ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox2);
        comboBox2.setBounds(60, 140, 300, 20);

        jLabel27.setText("Skor :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(410, 220, 80, 23);

        skor2.setName("skor2"); // NOI18N
        FormInput.add(skor2);
        skor2.setBounds(510, 220, 110, 24);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(10, 260, 750, 1);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(10, 260, 750, 1);

        jLabel50.setText("Saudara kandung / tiri");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 300, 170, 23);

        jLabel51.setText("3. Jika ya, siapakah ia/mereka?");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 270, 180, 23);

        comboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox3.setName("comboBox3"); // NOI18N
        comboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox3ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox3);
        comboBox3.setBounds(60, 220, 70, 20);

        jLabel52.setText("Ayah / Ibu");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 330, 170, 23);

        comboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox4.setName("comboBox4"); // NOI18N
        comboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox4ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox4);
        comboBox4.setBounds(190, 330, 70, 20);

        jLabel53.setText("Pasangan");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 360, 170, 23);

        comboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox5.setName("comboBox5"); // NOI18N
        comboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox5ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox5);
        comboBox5.setBounds(190, 360, 70, 20);

        comboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox6.setName("comboBox6"); // NOI18N
        comboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox6ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox6);
        comboBox6.setBounds(190, 390, 70, 20);

        jLabel54.setText("Om / Tante");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 390, 170, 23);

        jLabel55.setText("Teman");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 420, 170, 23);

        comboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox7.setName("comboBox7"); // NOI18N
        comboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox7ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox7);
        comboBox7.setBounds(190, 420, 70, 20);

        jLabel56.setText("Lainnya");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 450, 170, 23);

        comboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox8.setName("comboBox8"); // NOI18N
        comboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox8ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox8);
        comboBox8.setBounds(190, 450, 70, 20);

        jLabel28.setText("Skor :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(280, 300, 80, 23);

        skor3.setName("skor3"); // NOI18N
        FormInput.add(skor3);
        skor3.setBounds(380, 300, 110, 24);

        skor4.setName("skor4"); // NOI18N
        FormInput.add(skor4);
        skor4.setBounds(380, 330, 110, 24);

        jLabel29.setText("Skor :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(280, 330, 80, 23);

        skor5.setName("skor5"); // NOI18N
        FormInput.add(skor5);
        skor5.setBounds(380, 360, 110, 24);

        jLabel57.setText("Skor :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(280, 360, 80, 23);

        skor6.setName("skor6"); // NOI18N
        FormInput.add(skor6);
        skor6.setBounds(380, 390, 110, 24);

        jLabel58.setText("Skor :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(280, 390, 80, 23);

        jLabel59.setText("Skor :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(280, 420, 80, 23);

        skor7.setName("skor7"); // NOI18N
        FormInput.add(skor7);
        skor7.setBounds(380, 420, 110, 24);

        skor8.setName("skor8"); // NOI18N
        skor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor8ActionPerformed(evt);
            }
        });
        FormInput.add(skor8);
        skor8.setBounds(450, 850, 90, 24);

        jLabel60.setText("Skor :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(280, 450, 80, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(10, 500, 750, 1);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(10, 500, 750, 1);

        jLabel61.setText("Apakah anda pernah mengalami hal - hal berikut ini ( yang bukan akhibat langsung dari penggunaan Napza )");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 990, 550, 23);

        jLabel62.setText("30 hari terakhir");
        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(240, 540, 130, 23);

        comboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox9.setName("comboBox9"); // NOI18N
        comboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox9ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox9);
        comboBox9.setBounds(290, 570, 70, 20);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("1. Ibu");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(90, 570, 150, 23);

        jLabel64.setText("Sepanjang hidup");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(410, 540, 130, 23);

        comboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox10.setName("comboBox10"); // NOI18N
        comboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox10ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox10);
        comboBox10.setBounds(460, 570, 70, 20);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("2. Ayah");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(90, 600, 150, 23);

        comboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox11.setName("comboBox11"); // NOI18N
        comboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox11ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox11);
        comboBox11.setBounds(290, 600, 70, 20);

        comboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox12.setName("comboBox12"); // NOI18N
        comboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox12ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox12);
        comboBox12.setBounds(460, 600, 70, 20);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("3. Adik / Kakak");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(90, 630, 150, 23);

        comboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox13.setName("comboBox13"); // NOI18N
        comboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox13ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox13);
        comboBox13.setBounds(290, 630, 70, 20);

        comboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox14.setName("comboBox14"); // NOI18N
        comboBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox14ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox14);
        comboBox14.setBounds(460, 630, 70, 20);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("4. Pasangan");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(90, 660, 150, 23);

        comboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox15.setName("comboBox15"); // NOI18N
        comboBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox15ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox15);
        comboBox15.setBounds(290, 660, 70, 20);

        comboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox16.setName("comboBox16"); // NOI18N
        comboBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox16ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox16);
        comboBox16.setBounds(460, 660, 70, 20);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("5. Anak - anak");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(90, 690, 150, 23);

        comboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox17.setName("comboBox17"); // NOI18N
        comboBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox17ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox17);
        comboBox17.setBounds(460, 690, 70, 20);

        comboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox18.setName("comboBox18"); // NOI18N
        comboBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox18ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox18);
        comboBox18.setBounds(290, 690, 70, 20);

        comboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox19.setName("comboBox19"); // NOI18N
        comboBox19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox19ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox19);
        comboBox19.setBounds(290, 720, 70, 20);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("6. Keluarga lain yang berarti");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(90, 720, 150, 23);

        comboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox20.setName("comboBox20"); // NOI18N
        comboBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox20ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox20);
        comboBox20.setBounds(460, 720, 70, 20);

        comboBox21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox21.setName("comboBox21"); // NOI18N
        comboBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox21ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox21);
        comboBox21.setBounds(290, 750, 70, 20);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("7. Teman akrab");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(90, 750, 150, 23);

        comboBox22.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox22.setName("comboBox22"); // NOI18N
        comboBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox22ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox22);
        comboBox22.setBounds(460, 750, 70, 20);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("8. Tetangga");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(90, 780, 150, 23);

        comboBox23.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox23.setName("comboBox23"); // NOI18N
        comboBox23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox23ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox23);
        comboBox23.setBounds(290, 780, 70, 20);

        comboBox24.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox24.setName("comboBox24"); // NOI18N
        comboBox24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox24ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox24);
        comboBox24.setBounds(460, 780, 70, 20);

        jLabel72.setText("Skala Penilaian Pasien :");
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(90, 890, 180, 23);

        comboBox25.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox25.setName("comboBox25"); // NOI18N
        comboBox25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox25ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox25);
        comboBox25.setBounds(290, 810, 70, 20);

        comboBox26.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox26.setName("comboBox26"); // NOI18N
        comboBox26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox26ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox26);
        comboBox26.setBounds(460, 810, 70, 20);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("9. Teman sekerja");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(90, 810, 150, 23);

        skor9.setName("skor9"); // NOI18N
        FormInput.add(skor9);
        skor9.setBounds(380, 450, 110, 24);

        skor10.setName("skor10"); // NOI18N
        skor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor10ActionPerformed(evt);
            }
        });
        FormInput.add(skor10);
        skor10.setBounds(280, 890, 90, 24);

        jLabel74.setText("4. Apakah anda memiliki konflik serius dalam berhubungan dengan :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 510, 350, 23);

        jLabel15.setText("RIWAYAT KELUARGA / SOSIAL");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 80, 190, 23);

        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(90, 850, 180, 23);

        skor11.setName("skor11"); // NOI18N
        skor11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor11ActionPerformed(evt);
            }
        });
        FormInput.add(skor11);
        skor11.setBounds(280, 850, 90, 24);

        jLabel76.setText("30 hari terakhir");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(340, 1020, 130, 23);

        jLabel77.setText("Sepanjang hidup");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(480, 1020, 130, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("<html>Mengalami rasa cemas serius / ketegangan, gelisah,<br> merasa khawatir berlebihan?</html>");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(80, 1090, 300, 23);

        comboBox27.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox27.setName("comboBox27"); // NOI18N
        comboBox27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox27ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox27);
        comboBox27.setBounds(390, 1050, 70, 20);

        comboBox28.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox28.setName("comboBox28"); // NOI18N
        comboBox28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox28ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox28);
        comboBox28.setBounds(530, 1050, 70, 20);

        comboBox29.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox29.setName("comboBox29"); // NOI18N
        comboBox29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox29ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox29);
        comboBox29.setBounds(390, 1090, 70, 20);

        comboBox30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox30.setName("comboBox30"); // NOI18N
        comboBox30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox30ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox30);
        comboBox30.setBounds(530, 1090, 70, 20);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("<html>Mengalami depresi serius (kesedihan, putus asa,<br> kehilangan minat, susah konsentrasi)</html>");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(80, 1050, 300, 23);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("<html>Mengalami halusinasi ( melihat / mendengar sesuatu<br> yang tidak ada obyeknya )</html>");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(80, 1130, 300, 23);

        comboBox31.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox31.setName("comboBox31"); // NOI18N
        comboBox31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox31ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox31);
        comboBox31.setBounds(390, 1130, 70, 20);

        comboBox32.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox32.setName("comboBox32"); // NOI18N
        comboBox32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox32ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox32);
        comboBox32.setBounds(530, 1130, 70, 20);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("Mengalami kesulitan mengingat atau fokus pada sesuatu");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(80, 1170, 300, 23);

        comboBox33.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox33.setName("comboBox33"); // NOI18N
        comboBox33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox33ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox33);
        comboBox33.setBounds(390, 1170, 70, 20);

        comboBox34.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox34.setName("comboBox34"); // NOI18N
        comboBox34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox34ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox34);
        comboBox34.setBounds(530, 1170, 70, 20);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("<html>Mengalami kesukaran mengontrol perilaku kasar,<br> termasuk kemarahan atau kekerasan</html>");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(80, 1210, 300, 23);

        comboBox35.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox35.setName("comboBox35"); // NOI18N
        comboBox35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox35ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox35);
        comboBox35.setBounds(390, 1210, 70, 20);

        comboBox36.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox36.setName("comboBox36"); // NOI18N
        comboBox36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox36ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox36);
        comboBox36.setBounds(530, 1210, 70, 20);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Mengalami pikiran serius untuk bunuh diri ?");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(80, 1250, 300, 23);

        comboBox37.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox37.setName("comboBox37"); // NOI18N
        comboBox37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox37ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox37);
        comboBox37.setBounds(390, 1250, 70, 20);

        comboBox38.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox38.setName("comboBox38"); // NOI18N
        comboBox38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox38ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox38);
        comboBox38.setBounds(530, 1250, 70, 20);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("8.");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(50, 1330, 20, 23);

        comboBox39.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox39.setName("comboBox39"); // NOI18N
        comboBox39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox39ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox39);
        comboBox39.setBounds(390, 1290, 70, 20);

        comboBox40.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox40.setName("comboBox40"); // NOI18N
        comboBox40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox40ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox40);
        comboBox40.setBounds(530, 1290, 70, 20);

        jLabel85.setText("Skor :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(80, 1370, 290, 23);

        comboBox41.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox41.setName("comboBox41"); // NOI18N
        comboBox41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox41ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox41);
        comboBox41.setBounds(390, 1330, 70, 20);

        comboBox42.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        comboBox42.setName("comboBox42"); // NOI18N
        comboBox42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox42ActionPerformed(evt);
            }
        });
        FormInput.add(comboBox42);
        comboBox42.setBounds(530, 1330, 70, 20);

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("Berusaha untuk bunuh diri ?");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(80, 1290, 300, 23);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("1.");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(50, 1050, 20, 23);

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("2.");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(50, 1090, 20, 23);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("3.");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(50, 1130, 20, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("4.");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(50, 1170, 20, 23);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("5.");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(50, 1210, 20, 23);

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("6.");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(50, 1250, 20, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("7.");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(50, 1290, 20, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("Menerima pengobatan dari psikiater ?");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(80, 1330, 300, 23);

        skor12.setName("skor12"); // NOI18N
        skor12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor12ActionPerformed(evt);
            }
        });
        FormInput.add(skor12);
        skor12.setBounds(380, 1370, 90, 24);

        skor13.setName("skor13"); // NOI18N
        skor13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor13ActionPerformed(evt);
            }
        });
        FormInput.add(skor13);
        skor13.setBounds(520, 1370, 90, 24);

        skor14.setName("skor14"); // NOI18N
        skor14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor14ActionPerformed(evt);
            }
        });
        FormInput.add(skor14);
        skor14.setBounds(380, 1410, 90, 24);

        jLabel95.setText("Skala Penilaian Pasien :");
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(190, 1410, 180, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-10-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-10-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else{
            if(Sequel.menyimpantf("form4","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",28,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnSimpanActionPerformed(null);
//        }else{
//           Valid.pindah(evt,CatatanKhusus,BtnBatal);
//        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
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
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
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
                if(TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                        "penilaian_ranap_panss_ec.kd_dokter,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                        "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                        "inner join dokter on penilaian_ranap_panss_ec.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_ranap_panss_ec.tanggal between ? and ? order by penilaian_ranap_panss_ec.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                        "penilaian_ranap_panss_ec.kd_dokter,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                        "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                        "inner join dokter on penilaian_ranap_panss_ec.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_ranap_panss_ec.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_ranap_panss_ec.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_ranap_panss_ec.tanggal");
                }

                try {
                    if(TCari.getText().trim().equals("")){
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
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Gaduh Gelisah (P4)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Permusuhan (P7)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Ketegangan (G4)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tidak Kooperatif (G8)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pengendalian Impuls yang Buruk (G14)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Skor</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kesimpulan Masuk Ruangan</b></td>"+
                                
                        "</tr>"
                    );
                    while(rs.next()){
                        htmlContent.append(
                            "<tr class='isi'>"+
                               "<td valign='top'>"+rs.getString("no_rawat")+"</td>"+
                               "<td valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                               "<td valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                               "<td valign='top'>"+rs.getString("jk")+"</td>"+
                               "<td valign='top'>"+rs.getString("kd_dokter")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_dokter")+"</td>"+
                               "<td valign='top'>"+rs.getString("tanggal")+"</td>"+
                               "<td valign='top'>"+rs.getString("gaduh_gelisah")+"</td>"+
                               "<td valign='top'>"+rs.getString("permusuhan")+"</td>"+
                               "<td valign='top'>"+rs.getString("ketegangan")+"</td>"+
                               "<td valign='top'>"+rs.getString("tidak_kooperatif")+"</td>"+
                               "<td valign='top'>"+rs.getString("pengendalian_impuls_buruk")+"</td>"+
                               "<td valign='top'>"+rs.getString("total_skor")+"</td>"+
                               "<td valign='top'>"+rs.getString("kesimpulan")+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='4500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                    File f = new File("DataPenilaianAwalMedisRalan.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='4500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENILAIAN PRE ANESTESI<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
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

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
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
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Edukasi,Hubungan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
//        Valid.pindah(evt,BtnDokter,Diagnosa);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void MnPenilaianMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianMedisActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianPanssEc.jasper","report","::[ Laporan Penilaian PANSS-EC ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_ranap_panss_ec.tanggal,"+
                "penilaian_ranap_panss_ec.kd_dokter,penilaian_ranap_panss_ec.gaduh_gelisah,penilaian_ranap_panss_ec.skor_p4,penilaian_ranap_panss_ec.permusuhan,penilaian_ranap_panss_ec.skor_p7, penilaian_ranap_panss_ec.ketegangan,penilaian_ranap_panss_ec.skor_g4, penilaian_ranap_panss_ec.tidak_kooperatif,"+
                "penilaian_ranap_panss_ec.skor_g8,penilaian_ranap_panss_ec.pengendalian_impuls_buruk,penilaian_ranap_panss_ec.skor_g14,penilaian_ranap_panss_ec.total_skor,penilaian_ranap_panss_ec.kesimpulan,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+ 
                "inner join penilaian_ranap_panss_ec on reg_periksa.no_rawat=penilaian_ranap_panss_ec.no_rawat "+
                "inner join dokter on penilaian_ranap_panss_ec.kd_dokter=dokter.kd_dokter "+
                "where penilaian_ranap_panss_ec.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and penilaian_ranap_panss_ec.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void comboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboBox1ActionPerformed

    private void comboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox2ActionPerformed

    private void comboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox3ActionPerformed

    private void comboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox4ActionPerformed

    private void comboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox5ActionPerformed

    private void comboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox6ActionPerformed

    private void comboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox7ActionPerformed

    private void comboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox8ActionPerformed

    private void comboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox9ActionPerformed

    private void comboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox10ActionPerformed

    private void comboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox11ActionPerformed

    private void comboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox12ActionPerformed

    private void comboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox13ActionPerformed

    private void comboBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox14ActionPerformed

    private void comboBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox15ActionPerformed

    private void comboBox16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox16ActionPerformed

    private void comboBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox17ActionPerformed

    private void comboBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox18ActionPerformed

    private void comboBox19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox19ActionPerformed

    private void comboBox20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox20ActionPerformed

    private void comboBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox21ActionPerformed

    private void comboBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox22ActionPerformed

    private void comboBox23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox23ActionPerformed

    private void comboBox24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox24ActionPerformed

    private void comboBox25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox25ActionPerformed

    private void comboBox26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox26ActionPerformed

    private void skor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor8ActionPerformed

    private void skor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor10ActionPerformed

    private void skor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor11ActionPerformed

    private void comboBox27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox27ActionPerformed

    private void comboBox28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox28ActionPerformed

    private void comboBox29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox29ActionPerformed

    private void comboBox30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox30ActionPerformed

    private void comboBox31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox31ActionPerformed

    private void comboBox32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox32ActionPerformed

    private void comboBox33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox33ActionPerformed

    private void comboBox34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox34ActionPerformed

    private void comboBox35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox35ActionPerformed

    private void comboBox36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox36ActionPerformed

    private void comboBox37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox37ActionPerformed

    private void comboBox38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox38ActionPerformed

    private void comboBox39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox39ActionPerformed

    private void comboBox40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox40ActionPerformed

    private void comboBox41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox41ActionPerformed

    private void comboBox42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox42ActionPerformed

    private void skor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor12ActionPerformed

    private void skor13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor13ActionPerformed

    private void skor14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor14ActionPerformed

    private void TotalSkor() {
//        try {
//            Tskor.setText((Integer.parseInt(SkorBenzo.getText())+Integer.parseInt(SkorKanabis.getText())+Integer.parseInt(SkorOpiat.getText())+Integer.parseInt(SkorAmfetamin.getText())+Integer.parseInt(SkorKokain.getText())+Integer.parseInt(SkorBarbiturat.getText())+Integer.parseInt(SkorAlkohol.getText()))+"");
//        }catch (Exception e){
//            Tskor.setText(Integer.toString(0));
//        }
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMForm3 dialog = new RMForm3(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmDokter;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private widget.ComboBox comboBox1;
    private widget.ComboBox comboBox10;
    private widget.ComboBox comboBox11;
    private widget.ComboBox comboBox12;
    private widget.ComboBox comboBox13;
    private widget.ComboBox comboBox14;
    private widget.ComboBox comboBox15;
    private widget.ComboBox comboBox16;
    private widget.ComboBox comboBox17;
    private widget.ComboBox comboBox18;
    private widget.ComboBox comboBox19;
    private widget.ComboBox comboBox2;
    private widget.ComboBox comboBox20;
    private widget.ComboBox comboBox21;
    private widget.ComboBox comboBox22;
    private widget.ComboBox comboBox23;
    private widget.ComboBox comboBox24;
    private widget.ComboBox comboBox25;
    private widget.ComboBox comboBox26;
    private widget.ComboBox comboBox27;
    private widget.ComboBox comboBox28;
    private widget.ComboBox comboBox29;
    private widget.ComboBox comboBox3;
    private widget.ComboBox comboBox30;
    private widget.ComboBox comboBox31;
    private widget.ComboBox comboBox32;
    private widget.ComboBox comboBox33;
    private widget.ComboBox comboBox34;
    private widget.ComboBox comboBox35;
    private widget.ComboBox comboBox36;
    private widget.ComboBox comboBox37;
    private widget.ComboBox comboBox38;
    private widget.ComboBox comboBox39;
    private widget.ComboBox comboBox4;
    private widget.ComboBox comboBox40;
    private widget.ComboBox comboBox41;
    private widget.ComboBox comboBox42;
    private widget.ComboBox comboBox5;
    private widget.ComboBox comboBox6;
    private widget.ComboBox comboBox7;
    private widget.ComboBox comboBox8;
    private widget.ComboBox comboBox9;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel24;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.TextBox skor1;
    private widget.TextBox skor10;
    private widget.TextBox skor11;
    private widget.TextBox skor12;
    private widget.TextBox skor13;
    private widget.TextBox skor14;
    private widget.TextBox skor2;
    private widget.TextBox skor3;
    private widget.TextBox skor4;
    private widget.TextBox skor5;
    private widget.TextBox skor6;
    private widget.TextBox skor7;
    private widget.TextBox skor8;
    private widget.TextBox skor9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,form4.tanggal,"+
                        "form4.kd_dokter,form4.td,form4.nadi,form4.rr,form4.suhu,form4.s_cerna,form4.s_jantung,form4.s_napas,"+
                        "form4.s_saraf,form4.tht,form4.keterangan,form4.benzo,form4.skor_benzo,"+
                        "form4.kanabis,form4.skor_kanabis,form4.opiat,form4.skor_opiat,form4.amfetamin,form4.skor_amfe,form4.kokain,form4.skor_kokain,form4.barbiturat,form4.skor_barbi,form4.alkohol,form4.skor_alkohol,form4.tskor,"+        
                        "dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join form4 on reg_periksa.no_rawat=form4.no_rawat "+
                        "inner join dokter on form4.kd_dokter=dokter.kd_dokter where "+
                        "form4.tanggal between ? and ? order by form4.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,form4.tanggal,"+
                        "form4.kd_dokter,form4.td,form4.nadi,form4.rr,form4.suhu,form4.s_cerna,form4.s_jantung,form4.s_napas,"+
                        "form4.s_saraf,form4.tht,form4.keterangan,form4.benzo,form4.skor_benzo,"+
                        "form4.kanabis,form4.skor_kanabis,form4.opiat,form4.skor_opiat,form4.amfetamin,form4.skor_amfe,form4.kokain,form4.skor_kokain,form4.barbiturat,form4.skor_barbi,form4.alkohol,form4.skor_alkohol,form4.tskor,"+    
                        "dokter.nm_dokter "+        
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join form4 on reg_periksa.no_rawat=form4.no_rawat "+
                        "inner join dokter on form4.kd_dokter=dokter.kd_dokter where "+
                        "form4.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "form4.kd_dokter like ? or dokter.kd_dokter like ?) order by form4.tanggal");
            }
                
            try {
                if(TCari.getText().trim().equals("")){
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("tanggal"),
                        rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("s_cerna"),rs.getString("s_jantung"),rs.getString("s_napas"),rs.getString("s_saraf"),rs.getString("tht"),rs.getString("keterangan"),
                        rs.getString("benzo"),rs.getString("skor_benzo"),rs.getString("kanabis"),rs.getString("skor_kanabis"),rs.getString("opiat"),rs.getString("skor_opiat"),rs.getString("amfetamin"),rs.getString("skor_amfe"),rs.getString("kokain"),rs.getString("skor_kokain"),rs.getString("barbiturat"),rs.getString("skor_barbi"),rs.getString("alkohol"),rs.getString("skor_alkohol"),
                        rs.getString("tskor")
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
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        // Mengatur tanggal menjadi tanggal saat ini
        TglAsuhan.setDate(new Date());

        // Mengatur tab yang dipilih menjadi indeks 0
        TabRawat.setSelectedIndex(0);

        // Mengosongkan teks di Jk
        KdDokter.setText("");
        NmDokter.setText("");
        Jk.setText("");
        
//        td.setText("");
//        nadi.setText("");
//        rr.setText("");
        skor1.setText("");
        
//        sPencernaan.setText("");
//        sJantung.setText("");
//        sNafas.setText("");
//        sSaraf.setText("");
//        THT.setText("");
//        Ket.setText("");
//        
//        benzoYa.setSelected(false);
//        kanabisYa.setSelected(false);
//        opiatYa.setSelected(false);
//        amfeYa.setSelected(false);
//        kokainYa.setSelected(false);
//        barbiYa.setSelected(false);
//        alkoholYa.setSelected(false);
//        
//        benzoTidak.setSelected(false);
//        kanabisTidak.setSelected(false);
//        opiatTidak.setSelected(false);
//        amfeTidak.setSelected(false);
//        kokainTidak.setSelected(false);
//        barbiTidak.setSelected(false);
//        alkoholTidak.setSelected(false);
//
//        SkorBenzo.setText("");
//        SkorKanabis.setText("");
//        SkorOpiat.setText("");
//        SkorAmfetamin.setText("");
//        SkorKokain.setText("");
//        SkorBarbiturat.setText("");
//        SkorAlkohol.setText("");
//
//        Tskor.setText("");
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); 
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); 
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            
//            td.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
//            nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
//            rr.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()); 
            skor1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()); 
            
//            sPencernaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()); 
//            sJantung.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()); 
//            sNafas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()); 
//            sSaraf.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString()); 
//            THT.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString()); 
//            Ket.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString()); 
            
            String benzo = tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString();
//            if (benzo.contains("Ya")) {
//                benzoYa.setSelected(true);
//            } if (benzo.contains("Tidak")) {
//                benzoTidak.setSelected(true);
//            }
                
//            SkorBenzo.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            
//            String kanabis = tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString();
//            if (kanabis.contains("Ya")) {
//                kanabisYa.setSelected(true);
//            } if (kanabis.contains("Tidak")) {
//                kanabisTidak.setSelected(true);
//            }
//                
//            SkorKanabis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString()); 
            
            String opiat = tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString();
//            if (opiat.contains("Ya")) {
//                opiatYa.setSelected(true);
//            } if (opiat.contains("Tidak")) {
//                opiatTidak.setSelected(true);
//            }
//                
//            SkorOpiat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());  
            
            String amfe = tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString();
//            if (amfe.contains("Ya")) {
//                amfeYa.setSelected(true);
//            } if (amfe.contains("Tidak")) {
//                amfeTidak.setSelected(true);
//            }
//                
//            SkorAmfetamin.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString()); 
            
            String kokain = tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString();
//            if (kokain.contains("Ya")) {
//                kokainYa.setSelected(true);
//            } if (kokain.contains("Tidak")) {
//                kokainTidak.setSelected(true);
//            }
//                
//            SkorKokain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());  
            
            String barbi = tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString();
//            if (barbi.contains("Ya")) {
//                barbiYa.setSelected(true);
//            } if (barbi.contains("Tidak")) {
//                barbiTidak.setSelected(true);
//            }
//                
//            SkorBarbiturat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString()); 
            
            String alkohol = tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString();
//            if (alkohol.contains("Ya")) {
//                alkoholYa.setSelected(true);
//            } if (alkohol.contains("Tidak")) {
//                alkoholTidak.setSelected(true);
//            }    
//            SkorAlkohol.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());  
//                
//            Tskor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
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
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpanss_ec());
        BtnHapus.setEnabled(akses.getpanss_ec());
        BtnEdit.setEnabled(akses.getpanss_ec());
        BtnEdit.setEnabled(akses.getpanss_ec());
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan Dokter...!!");
            }
        }            
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from form4 where no_rawat=? and tanggal=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
//        String benzo = "";
//        String kanabis = "";
//        String opiat = "";
//        String amfetamin = "";
//        String kokain = "";
//        String barbiturat = "";
//        String alkohol = "";
//        
//        if (benzoYa.isSelected()){
//            benzo = "Ya";
//        } else if (benzoTidak.isSelected()){
//            benzo = "Tidak";
//        }
//        
//        if (kanabisYa.isSelected()){
//            kanabis = "Ya";
//        } else if (kanabisTidak.isSelected()){
//            kanabis = "Tidak";
//        }
//        
//        if (opiatYa.isSelected()){
//            opiat = "Ya";
//        } else if (opiatTidak.isSelected()){
//            opiat = "Tidak";
//        }
//        
//        if (amfeYa.isSelected()){
//            amfetamin = "Ya";
//        } else if (amfeTidak.isSelected()){
//            amfetamin = "Tidak";
//        }
//        
//        if (kokainYa.isSelected()){
//            kokain = "Ya";
//        } else if (kokainTidak.isSelected()){
//            kokain = "Tidak";
//        }
//        
//        if (barbiYa.isSelected()){
//            barbiturat = "Ya";
//        } else if (barbiTidak.isSelected()){
//            barbiturat = "Tidak";
//        }
//        
//        if (alkoholYa.isSelected()){
//            alkohol = "Ya";
//        } else if (alkoholTidak.isSelected()){
//            alkohol = "Tidak";
//        }
        
        if(Sequel.mengedittf("form4","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,kd_dokter=?,td=?,nadi=?,rr=?,suhu=?,s_cerna=?,s_jantung=?,s_napas=?,s_saraf=?,tht=?,keterangan=?,benzo=?,skor_benzo=?,kanabis=?,skor_kanabis=?,opiat=?,skor_opiat=?,amfetamin=?,skor_amfe=?,kokain=?,skor_kokain=?,barbiturat=?,skor_barbi=?,alkohol=?,skor_alkohol=?,tskor=?",30,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
//                td.getText(),nadi.getText(),rr.getText(),skor1.getText(),sPencernaan.getText(),sJantung.getText(),sNafas.getText(),sSaraf.getText(),THT.getText(),Ket.getText(),
//                benzo,SkorBenzo.getText(),kanabis,SkorKanabis.getText(),opiat,SkorOpiat.getText(),amfetamin,SkorAmfetamin.getText(),kokain,SkorKokain.getText(),barbiturat,SkorBarbiturat.getText(),alkohol,SkorAlkohol.getText(),
//                Tskor.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
