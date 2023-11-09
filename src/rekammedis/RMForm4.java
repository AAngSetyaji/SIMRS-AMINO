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
public final class RMForm4 extends javax.swing.JDialog {
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
    public RMForm4(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        BtnPrint.setVisible(false);
        
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
        jLabel14 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel24 = new widget.Label();
        td = new widget.TextBox();
        jLabel25 = new widget.Label();
        nadi = new widget.TextBox();
        jLabel26 = new widget.Label();
        suhu = new widget.TextBox();
        jLabel27 = new widget.Label();
        rr = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        alkoholTidak = new widget.RadioButton();
        benzoYa = new widget.RadioButton();
        benzoTidak = new widget.RadioButton();
        kanabisYa = new widget.RadioButton();
        kanabisTidak = new widget.RadioButton();
        opiatYa = new widget.RadioButton();
        opiatTidak = new widget.RadioButton();
        amfeYa = new widget.RadioButton();
        amfeTidak = new widget.RadioButton();
        kokainYa = new widget.RadioButton();
        kokainTidak = new widget.RadioButton();
        barbiYa = new widget.RadioButton();
        barbiTidak = new widget.RadioButton();
        alkoholYa = new widget.RadioButton();
        SkorBenzo = new widget.TextBox();
        SkorAlkohol = new widget.TextBox();
        SkorKanabis = new widget.TextBox();
        SkorOpiat = new widget.TextBox();
        SkorAmfetamin = new widget.TextBox();
        SkorKokain = new widget.TextBox();
        SkorBarbiturat = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        Tskor = new widget.TextBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Ket = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        sPencernaan = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        sJantung = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        sNafas = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        sSaraf = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        THT = new javax.swing.JTextArea();
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
        MnPenilaianMedis.setText("Laporan Form 4 Napza");
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Form 4 Napza ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-11-2023 13:12:23" }));
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

        jLabel13.setText("Pemeriksaan Fisik");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 80, 140, 23);

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
        jSeparator7.setBounds(0, 780, 750, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 780, 750, 1);

        jLabel14.setText("Hasil Urinalisis :");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 790, 160, 23);

        jLabel22.setText("Tekanan Darah :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 110, 160, 23);

        jLabel24.setText("J.K. :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 40, 70, 23);

        td.setName("td"); // NOI18N
        FormInput.add(td);
        td.setBounds(180, 110, 110, 24);

        jLabel25.setText("Alkohol");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 1000, 160, 23);

        nadi.setName("nadi"); // NOI18N
        FormInput.add(nadi);
        nadi.setBounds(180, 140, 110, 24);

        jLabel26.setText("Suhu (celcius) :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(350, 140, 140, 23);

        suhu.setName("suhu"); // NOI18N
        FormInput.add(suhu);
        suhu.setBounds(510, 140, 110, 24);

        jLabel27.setText("Pernapasan (RR) :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(350, 110, 140, 23);

        rr.setName("rr"); // NOI18N
        FormInput.add(rr);
        rr.setBounds(510, 110, 110, 24);

        jLabel28.setText("Nadi :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 140, 160, 23);

        jLabel29.setText("Pemeriksaan Sistematik :");
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 190, 160, 23);

        jLabel30.setText("Sistem Pencernaan");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 220, 200, 23);

        jLabel31.setText("Sistem Jantung dan pembuluh darah");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 310, 200, 23);

        jLabel32.setText("Sistem Pernapasan");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 400, 200, 23);

        jLabel33.setText("Sistem Saraf Pusat");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 490, 200, 23);

        jLabel34.setText("THT dan Kulit");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 580, 200, 23);

        jLabel35.setText("Keterangan");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 670, 200, 23);

        jLabel36.setText("Benzodiazepin");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 820, 160, 23);

        jLabel37.setText("Kanabis");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 850, 160, 23);

        jLabel38.setText("Opiat");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 880, 160, 23);

        jLabel39.setText("Amfetamin");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 910, 160, 23);

        jLabel40.setText("Kokain");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 940, 160, 23);

        jLabel41.setText("Barbiturat");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 970, 160, 23);

        buttonGroup7.add(alkoholTidak);
        alkoholTidak.setText("Tidak");
        alkoholTidak.setName("alkoholTidak"); // NOI18N
        alkoholTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alkoholTidakActionPerformed(evt);
            }
        });
        FormInput.add(alkoholTidak);
        alkoholTidak.setBounds(240, 1000, 60, 17);

        buttonGroup1.add(benzoYa);
        benzoYa.setText("Ya");
        benzoYa.setName("benzoYa"); // NOI18N
        benzoYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                benzoYaActionPerformed(evt);
            }
        });
        FormInput.add(benzoYa);
        benzoYa.setBounds(170, 820, 33, 17);

        buttonGroup1.add(benzoTidak);
        benzoTidak.setText("Tidak");
        benzoTidak.setName("benzoTidak"); // NOI18N
        benzoTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                benzoTidakActionPerformed(evt);
            }
        });
        FormInput.add(benzoTidak);
        benzoTidak.setBounds(240, 820, 60, 17);

        buttonGroup2.add(kanabisYa);
        kanabisYa.setText("Ya");
        kanabisYa.setName("kanabisYa"); // NOI18N
        kanabisYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kanabisYaActionPerformed(evt);
            }
        });
        FormInput.add(kanabisYa);
        kanabisYa.setBounds(170, 850, 33, 17);

        buttonGroup2.add(kanabisTidak);
        kanabisTidak.setText("Tidak");
        kanabisTidak.setName("kanabisTidak"); // NOI18N
        kanabisTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kanabisTidakActionPerformed(evt);
            }
        });
        FormInput.add(kanabisTidak);
        kanabisTidak.setBounds(240, 850, 60, 17);

        buttonGroup3.add(opiatYa);
        opiatYa.setText("Ya");
        opiatYa.setName("opiatYa"); // NOI18N
        opiatYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opiatYaActionPerformed(evt);
            }
        });
        FormInput.add(opiatYa);
        opiatYa.setBounds(170, 880, 33, 17);

        buttonGroup3.add(opiatTidak);
        opiatTidak.setText("Tidak");
        opiatTidak.setName("opiatTidak"); // NOI18N
        opiatTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opiatTidakActionPerformed(evt);
            }
        });
        FormInput.add(opiatTidak);
        opiatTidak.setBounds(240, 880, 60, 17);

        buttonGroup4.add(amfeYa);
        amfeYa.setText("Ya");
        amfeYa.setName("amfeYa"); // NOI18N
        amfeYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amfeYaActionPerformed(evt);
            }
        });
        FormInput.add(amfeYa);
        amfeYa.setBounds(170, 910, 33, 17);

        buttonGroup4.add(amfeTidak);
        amfeTidak.setText("Tidak");
        amfeTidak.setName("amfeTidak"); // NOI18N
        amfeTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amfeTidakActionPerformed(evt);
            }
        });
        FormInput.add(amfeTidak);
        amfeTidak.setBounds(240, 910, 60, 17);

        buttonGroup5.add(kokainYa);
        kokainYa.setText("Ya");
        kokainYa.setName("kokainYa"); // NOI18N
        kokainYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kokainYaActionPerformed(evt);
            }
        });
        FormInput.add(kokainYa);
        kokainYa.setBounds(170, 940, 33, 17);

        buttonGroup5.add(kokainTidak);
        kokainTidak.setText("Tidak");
        kokainTidak.setName("kokainTidak"); // NOI18N
        kokainTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kokainTidakActionPerformed(evt);
            }
        });
        FormInput.add(kokainTidak);
        kokainTidak.setBounds(240, 940, 60, 17);

        buttonGroup6.add(barbiYa);
        barbiYa.setText("Ya");
        barbiYa.setName("barbiYa"); // NOI18N
        barbiYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barbiYaActionPerformed(evt);
            }
        });
        FormInput.add(barbiYa);
        barbiYa.setBounds(170, 970, 33, 17);

        buttonGroup6.add(barbiTidak);
        barbiTidak.setText("Tidak");
        barbiTidak.setName("barbiTidak"); // NOI18N
        barbiTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barbiTidakActionPerformed(evt);
            }
        });
        FormInput.add(barbiTidak);
        barbiTidak.setBounds(240, 970, 60, 17);

        buttonGroup7.add(alkoholYa);
        alkoholYa.setText("Ya");
        alkoholYa.setName("alkoholYa"); // NOI18N
        alkoholYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alkoholYaActionPerformed(evt);
            }
        });
        FormInput.add(alkoholYa);
        alkoholYa.setBounds(170, 1000, 33, 17);

        SkorBenzo.setName("SkorBenzo"); // NOI18N
        SkorBenzo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorBenzoActionPerformed(evt);
            }
        });
        FormInput.add(SkorBenzo);
        SkorBenzo.setBounds(460, 820, 65, 24);

        SkorAlkohol.setName("SkorAlkohol"); // NOI18N
        FormInput.add(SkorAlkohol);
        SkorAlkohol.setBounds(460, 1000, 64, 24);

        SkorKanabis.setName("SkorKanabis"); // NOI18N
        FormInput.add(SkorKanabis);
        SkorKanabis.setBounds(460, 850, 64, 24);

        SkorOpiat.setName("SkorOpiat"); // NOI18N
        FormInput.add(SkorOpiat);
        SkorOpiat.setBounds(460, 880, 64, 24);

        SkorAmfetamin.setName("SkorAmfetamin"); // NOI18N
        FormInput.add(SkorAmfetamin);
        SkorAmfetamin.setBounds(460, 910, 64, 24);

        SkorKokain.setName("SkorKokain"); // NOI18N
        FormInput.add(SkorKokain);
        SkorKokain.setBounds(460, 940, 64, 24);

        SkorBarbiturat.setName("SkorBarbiturat"); // NOI18N
        FormInput.add(SkorBarbiturat);
        SkorBarbiturat.setBounds(460, 970, 64, 24);

        jLabel42.setText("Skor :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(340, 820, 100, 23);

        jLabel43.setText("Skor :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(340, 850, 100, 23);

        jLabel44.setText("Skor :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(340, 880, 100, 23);

        jLabel45.setText("Skor :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(340, 910, 100, 23);

        jLabel46.setText("Skor :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(340, 940, 100, 23);

        jLabel47.setText("Skor :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(340, 970, 100, 23);

        jLabel48.setText("Skor :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(340, 1000, 100, 23);

        jLabel49.setText("Total Skor :");
        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(340, 1040, 100, 23);

        Tskor.setName("Tskor"); // NOI18N
        FormInput.add(Tskor);
        Tskor.setBounds(460, 1040, 64, 24);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Ket.setColumns(20);
        Ket.setRows(5);
        Ket.setName("Ket"); // NOI18N
        jScrollPane1.setViewportView(Ket);

        FormInput.add(jScrollPane1);
        jScrollPane1.setBounds(220, 670, 480, 80);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        sPencernaan.setColumns(20);
        sPencernaan.setRows(5);
        sPencernaan.setName("sPencernaan"); // NOI18N
        jScrollPane2.setViewportView(sPencernaan);

        FormInput.add(jScrollPane2);
        jScrollPane2.setBounds(220, 220, 480, 80);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        sJantung.setColumns(20);
        sJantung.setRows(5);
        sJantung.setName("sJantung"); // NOI18N
        jScrollPane3.setViewportView(sJantung);

        FormInput.add(jScrollPane3);
        jScrollPane3.setBounds(220, 310, 480, 80);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        sNafas.setColumns(20);
        sNafas.setRows(5);
        sNafas.setName("sNafas"); // NOI18N
        jScrollPane4.setViewportView(sNafas);

        FormInput.add(jScrollPane4);
        jScrollPane4.setBounds(220, 400, 480, 80);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        sSaraf.setColumns(20);
        sSaraf.setRows(5);
        sSaraf.setName("sSaraf"); // NOI18N
        jScrollPane5.setViewportView(sSaraf);

        FormInput.add(jScrollPane5);
        jScrollPane5.setBounds(220, 490, 480, 80);

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        THT.setColumns(20);
        THT.setRows(5);
        THT.setName("THT"); // NOI18N
        jScrollPane6.setViewportView(THT);

        FormInput.add(jScrollPane6);
        jScrollPane6.setBounds(220, 580, 480, 80);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-11-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-11-2023" }));
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
        String benzo = "";
        String kanabis = "";
        String opiat = "";
        String amfetamin = "";
        String kokain = "";
        String barbiturat = "";
        String alkohol = "";
        
        if (benzoYa.isSelected()){
            benzo = "Ya";
        } else if (benzoTidak.isSelected()){
            benzo = "Tidak";
        }
        
        if (kanabisYa.isSelected()){
            kanabis = "Ya";
        } else if (kanabisTidak.isSelected()){
            kanabis = "Tidak";
        }
        
        if (opiatYa.isSelected()){
            opiat = "Ya";
        } else if (opiatTidak.isSelected()){
            opiat = "Tidak";
        }
        
        if (amfeYa.isSelected()){
            amfetamin = "Ya";
        } else if (amfeTidak.isSelected()){
            amfetamin = "Tidak";
        }
        
        if (kokainYa.isSelected()){
            kokain = "Ya";
        } else if (kokainTidak.isSelected()){
            kokain = "Tidak";
        }
        
        if (barbiYa.isSelected()){
            barbiturat = "Ya";
        } else if (barbiTidak.isSelected()){
            barbiturat = "Tidak";
        }
        
        if (alkoholYa.isSelected()){
            alkohol = "Ya";
        } else if (alkoholTidak.isSelected()){
            alkohol = "Tidak";
        }
        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else{
            if(Sequel.menyimpantf("form4","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",28,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
                td.getText(),nadi.getText(),rr.getText(),suhu.getText(),
                sPencernaan.getText(),sJantung.getText(),sNafas.getText(),sSaraf.getText(),THT.getText(),Ket.getText(),
                benzo,SkorBenzo.getText(),kanabis,SkorKanabis.getText(),opiat,SkorOpiat.getText(),amfetamin,SkorAmfetamin.getText(),kokain,SkorKokain.getText(),barbiturat,SkorBarbiturat.getText(),alkohol,SkorAlkohol.getText(),
                Tskor.getText()
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
            
            Valid.MyReportqry("rptCetakForm4Napza.jasper","report","::[ Laporan Penilaian Form 4 Napza ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,form4.tanggal,"+
                        "form4.kd_dokter,form4.td,form4.nadi,form4.rr,form4.suhu,form4.s_cerna,form4.s_jantung,form4.s_napas,"+
                        "form4.s_saraf,form4.tht,form4.keterangan,form4.benzo,form4.skor_benzo,"+
                        "form4.kanabis,form4.skor_kanabis,form4.opiat,form4.skor_opiat,form4.amfetamin,form4.skor_amfe,form4.kokain,form4.skor_kokain,form4.barbiturat,form4.skor_barbi,form4.alkohol,form4.skor_alkohol,form4.tskor,"+        
                        "dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join form4 on reg_periksa.no_rawat=form4.no_rawat "+
                        "inner join dokter on form4.kd_dokter=dokter.kd_dokter "+
                "where form4.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and form4.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void SkorBenzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorBenzoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorBenzoActionPerformed

    private void benzoYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_benzoYaActionPerformed
        // TODO add your handling code here:
        if (benzoYa.isSelected()){
            int benzo = 1;
            SkorBenzo.setText(Integer.toString(benzo));
        }
        TotalSkor();
    }//GEN-LAST:event_benzoYaActionPerformed

    private void benzoTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_benzoTidakActionPerformed
        // TODO add your handling code here:
        if (benzoTidak.isSelected()){
            int benzo = 0;
            SkorBenzo.setText(Integer.toString(benzo));
        }
        TotalSkor();
    }//GEN-LAST:event_benzoTidakActionPerformed

    private void kanabisYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kanabisYaActionPerformed
        // TODO add your handling code here:
        if (kanabisYa.isSelected()){
            int kanabis = 1;
            SkorKanabis.setText(Integer.toString(kanabis));
        }
        TotalSkor();
    }//GEN-LAST:event_kanabisYaActionPerformed

    private void kanabisTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kanabisTidakActionPerformed
        // TODO add your handling code here:
        if (kanabisTidak.isSelected()){
            int kanabis = 0;
            SkorKanabis.setText(Integer.toString(kanabis));
        }
        TotalSkor();
    }//GEN-LAST:event_kanabisTidakActionPerformed

    private void opiatYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opiatYaActionPerformed
        // TODO add your handling code here:
        if (opiatYa.isSelected()){
            int opiat = 1;
            SkorOpiat.setText(Integer.toString(opiat));
        }
        TotalSkor();
    }//GEN-LAST:event_opiatYaActionPerformed

    private void opiatTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opiatTidakActionPerformed
        // TODO add your handling code here:
        if (opiatTidak.isSelected()){
            int opiat = 0;
            SkorOpiat.setText(Integer.toString(opiat));
        }
        TotalSkor();
    }//GEN-LAST:event_opiatTidakActionPerformed

    private void amfeYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amfeYaActionPerformed
        // TODO add your handling code here:\
        if (amfeYa.isSelected()){
            int amfe = 1;
            SkorAmfetamin.setText(Integer.toString(amfe));
        }
        TotalSkor();
    }//GEN-LAST:event_amfeYaActionPerformed

    private void amfeTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amfeTidakActionPerformed
        // TODO add your handling code here:
        if (amfeTidak.isSelected()){
            int amfe = 0;
            SkorAmfetamin.setText(Integer.toString(amfe));
        }
        TotalSkor();
    }//GEN-LAST:event_amfeTidakActionPerformed

    private void kokainYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kokainYaActionPerformed
        // TODO add your handling code here:
        if (kokainYa.isSelected()){
            int kokain = 1;
            SkorKokain.setText(Integer.toString(kokain));
        }
        TotalSkor();
    }//GEN-LAST:event_kokainYaActionPerformed

    private void kokainTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kokainTidakActionPerformed
        // TODO add your handling code here:
         if (kokainTidak.isSelected()){
            int kokain = 0;
            SkorKokain.setText(Integer.toString(kokain));
        }
        TotalSkor();
    }//GEN-LAST:event_kokainTidakActionPerformed

    private void barbiYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barbiYaActionPerformed
        // TODO add your handling code here:
         if (barbiYa.isSelected()){
            int barbi = 1;
            SkorBarbiturat.setText(Integer.toString(barbi));
        }
        TotalSkor();
    }//GEN-LAST:event_barbiYaActionPerformed

    private void barbiTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barbiTidakActionPerformed
        // TODO add your handling code here:
        if (barbiTidak.isSelected()){
            int barbi = 0;
            SkorBarbiturat.setText(Integer.toString(barbi));
        }
        TotalSkor();
    }//GEN-LAST:event_barbiTidakActionPerformed

    private void alkoholYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alkoholYaActionPerformed
        // TODO add your handling code here:
        if (alkoholYa.isSelected()){
            int alkohol = 1;
            SkorAlkohol.setText(Integer.toString(alkohol));
        }
        TotalSkor();
    }//GEN-LAST:event_alkoholYaActionPerformed

    private void alkoholTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alkoholTidakActionPerformed
        // TODO add your handling code here:
        if (alkoholTidak.isSelected()){
            int alkohol = 0;
            SkorAlkohol.setText(Integer.toString(alkohol));
        }
        TotalSkor();
    }//GEN-LAST:event_alkoholTidakActionPerformed

    private void TotalSkor() {
        try {
            Tskor.setText((Integer.parseInt(SkorBenzo.getText())+Integer.parseInt(SkorKanabis.getText())+Integer.parseInt(SkorOpiat.getText())+Integer.parseInt(SkorAmfetamin.getText())+Integer.parseInt(SkorKokain.getText())+Integer.parseInt(SkorBarbiturat.getText())+Integer.parseInt(SkorAlkohol.getText()))+"");
        }catch (Exception e){
            Tskor.setText(Integer.toString(0));
        }
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMForm4 dialog = new RMForm4(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea Ket;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmDokter;
    private widget.ScrollPane Scroll;
    private widget.TextBox SkorAlkohol;
    private widget.TextBox SkorAmfetamin;
    private widget.TextBox SkorBarbiturat;
    private widget.TextBox SkorBenzo;
    private widget.TextBox SkorKanabis;
    private widget.TextBox SkorKokain;
    private widget.TextBox SkorOpiat;
    private widget.TextBox TCari;
    private javax.swing.JTextArea THT;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.TextBox Tskor;
    private widget.RadioButton alkoholTidak;
    private widget.RadioButton alkoholYa;
    private widget.RadioButton amfeTidak;
    private widget.RadioButton amfeYa;
    private widget.RadioButton barbiTidak;
    private widget.RadioButton barbiYa;
    private widget.RadioButton benzoTidak;
    private widget.RadioButton benzoYa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private widget.RadioButton kanabisTidak;
    private widget.RadioButton kanabisYa;
    private widget.RadioButton kokainTidak;
    private widget.RadioButton kokainYa;
    private widget.Label label11;
    private widget.Label label14;
    private widget.TextBox nadi;
    private widget.RadioButton opiatTidak;
    private widget.RadioButton opiatYa;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox rr;
    private javax.swing.JTextArea sJantung;
    private javax.swing.JTextArea sNafas;
    private javax.swing.JTextArea sPencernaan;
    private javax.swing.JTextArea sSaraf;
    private widget.ScrollPane scrollInput;
    private widget.TextBox suhu;
    private widget.Table tbObat;
    private widget.TextBox td;
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
        
        td.setText("");
        nadi.setText("");
        rr.setText("");
        suhu.setText("");
        
        sPencernaan.setText("");
        sJantung.setText("");
        sNafas.setText("");
        sSaraf.setText("");
        THT.setText("");
        Ket.setText("");
        
        benzoYa.setSelected(false);
        kanabisYa.setSelected(false);
        opiatYa.setSelected(false);
        amfeYa.setSelected(false);
        kokainYa.setSelected(false);
        barbiYa.setSelected(false);
        alkoholYa.setSelected(false);
        
        benzoTidak.setSelected(false);
        kanabisTidak.setSelected(false);
        opiatTidak.setSelected(false);
        amfeTidak.setSelected(false);
        kokainTidak.setSelected(false);
        barbiTidak.setSelected(false);
        alkoholTidak.setSelected(false);

        SkorBenzo.setText("");
        SkorKanabis.setText("");
        SkorOpiat.setText("");
        SkorAmfetamin.setText("");
        SkorKokain.setText("");
        SkorBarbiturat.setText("");
        SkorAlkohol.setText("");

        Tskor.setText("");
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
            
            td.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            rr.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()); 
            suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()); 
            
            sPencernaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()); 
            sJantung.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()); 
            sNafas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()); 
            sSaraf.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString()); 
            THT.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString()); 
            Ket.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString()); 
            
            String benzo = tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString();
            if (benzo.contains("Ya")) {
                benzoYa.setSelected(true);
            } if (benzo.contains("Tidak")) {
                benzoTidak.setSelected(true);
            }
                
            SkorBenzo.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            
            String kanabis = tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString();
            if (kanabis.contains("Ya")) {
                kanabisYa.setSelected(true);
            } if (kanabis.contains("Tidak")) {
                kanabisTidak.setSelected(true);
            }
                
            SkorKanabis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString()); 
            
            String opiat = tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString();
            if (opiat.contains("Ya")) {
                opiatYa.setSelected(true);
            } if (opiat.contains("Tidak")) {
                opiatTidak.setSelected(true);
            }
                
            SkorOpiat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());  
            
            String amfe = tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString();
            if (amfe.contains("Ya")) {
                amfeYa.setSelected(true);
            } if (amfe.contains("Tidak")) {
                amfeTidak.setSelected(true);
            }
                
            SkorAmfetamin.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString()); 
            
            String kokain = tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString();
            if (kokain.contains("Ya")) {
                kokainYa.setSelected(true);
            } if (kokain.contains("Tidak")) {
                kokainTidak.setSelected(true);
            }
                
            SkorKokain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());  
            
            String barbi = tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString();
            if (barbi.contains("Ya")) {
                barbiYa.setSelected(true);
            } if (barbi.contains("Tidak")) {
                barbiTidak.setSelected(true);
            }
                
            SkorBarbiturat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString()); 
            
            String alkohol = tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString();
            if (alkohol.contains("Ya")) {
                alkoholYa.setSelected(true);
            } if (alkohol.contains("Tidak")) {
                alkoholTidak.setSelected(true);
            }    
            SkorAlkohol.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());  
                
            Tskor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            
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
        String benzo = "";
        String kanabis = "";
        String opiat = "";
        String amfetamin = "";
        String kokain = "";
        String barbiturat = "";
        String alkohol = "";
        
        if (benzoYa.isSelected()){
            benzo = "Ya";
        } else if (benzoTidak.isSelected()){
            benzo = "Tidak";
        }
        
        if (kanabisYa.isSelected()){
            kanabis = "Ya";
        } else if (kanabisTidak.isSelected()){
            kanabis = "Tidak";
        }
        
        if (opiatYa.isSelected()){
            opiat = "Ya";
        } else if (opiatTidak.isSelected()){
            opiat = "Tidak";
        }
        
        if (amfeYa.isSelected()){
            amfetamin = "Ya";
        } else if (amfeTidak.isSelected()){
            amfetamin = "Tidak";
        }
        
        if (kokainYa.isSelected()){
            kokain = "Ya";
        } else if (kokainTidak.isSelected()){
            kokain = "Tidak";
        }
        
        if (barbiYa.isSelected()){
            barbiturat = "Ya";
        } else if (barbiTidak.isSelected()){
            barbiturat = "Tidak";
        }
        
        if (alkoholYa.isSelected()){
            alkohol = "Ya";
        } else if (alkoholTidak.isSelected()){
            alkohol = "Tidak";
        }
        
        if(Sequel.mengedittf("form4","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,kd_dokter=?,td=?,nadi=?,rr=?,suhu=?,s_cerna=?,s_jantung=?,s_napas=?,s_saraf=?,tht=?,keterangan=?,benzo=?,skor_benzo=?,kanabis=?,skor_kanabis=?,opiat=?,skor_opiat=?,amfetamin=?,skor_amfe=?,kokain=?,skor_kokain=?,barbiturat=?,skor_barbi=?,alkohol=?,skor_alkohol=?,tskor=?",30,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
                td.getText(),nadi.getText(),rr.getText(),suhu.getText(),sPencernaan.getText(),sJantung.getText(),sNafas.getText(),sSaraf.getText(),THT.getText(),Ket.getText(),
                benzo,SkorBenzo.getText(),kanabis,SkorKanabis.getText(),opiat,SkorOpiat.getText(),amfetamin,SkorAmfetamin.getText(),kokain,SkorKokain.getText(),barbiturat,SkorBarbiturat.getText(),alkohol,SkorAlkohol.getText(),
                Tskor.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
