/*
 * By Mas Elkhanza
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
public final class RMFormulirAssesmenWajibLaporRehab extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMFormulirAssesmenWajibLaporRehab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Waktu Mulai","Kode Dokter","Nama Dokter","NIP","Nama Petugas",
            "Medis","Pekerjaan / Dukungan","Napza","Legal","Keluarga / Sosial","Psikiatris","Klien Memenuhi kriteria","Diagnosis Lainnya","Resume Masalah","Rencana Terapi"
            ,"Rencana Terapi Rumatan","Rencana Terapi Rehab Ranap","Rencana Terapi Konseling","Rencana Terapi Lain",
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 24; i++) {
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
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(115);
            }else if(i==10){
                column.setPreferredWidth(115);
            }else if(i==11){
                column.setPreferredWidth(200);
            }else if(i==12){
                column.setPreferredWidth(200);
            }else if(i==13){
                column.setPreferredWidth(200);
            }else if(i==14){
                column.setPreferredWidth(200);
            }else if(i==15){
                column.setPreferredWidth(300);
            }else if(i==16){
                column.setPreferredWidth(200);
            }else if(i==17){
                column.setPreferredWidth(200);
            }else if(i==18){
                column.setPreferredWidth(200);
            }else if(i==19){
                column.setPreferredWidth(200);
            }else if(i==20){
                column.setPreferredWidth(200);
            }else if(i==21){
                column.setPreferredWidth(200);
            }else if(i==22){
                column.setPreferredWidth(200);
            }else if(i==23){
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
//        UraianTindakan.setDocument(new batasInput((int)500).getKata(UraianTindakan));
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                    NIP.requestFocus();
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
        MnDokumenESWL = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
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
        jLabel11 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label12 = new widget.Label();
        WaktuMulai = new widget.Tanggal();
        label15 = new widget.Label();
        NIP = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        Diagnosa = new widget.TextBox();
        label16 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        label27 = new widget.Label();
        medis2 = new javax.swing.JRadioButton();
        medis0 = new javax.swing.JRadioButton();
        medis1 = new javax.swing.JRadioButton();
        label29 = new widget.Label();
        medis5 = new javax.swing.JRadioButton();
        medis3 = new javax.swing.JRadioButton();
        medis4 = new javax.swing.JRadioButton();
        medis9 = new javax.swing.JRadioButton();
        medis6 = new javax.swing.JRadioButton();
        medis7 = new javax.swing.JRadioButton();
        medis8 = new javax.swing.JRadioButton();
        pekerjaan2 = new javax.swing.JRadioButton();
        pekerjaan0 = new javax.swing.JRadioButton();
        pekerjaan1 = new javax.swing.JRadioButton();
        pekerjaan5 = new javax.swing.JRadioButton();
        pekerjaan3 = new javax.swing.JRadioButton();
        pekerjaan4 = new javax.swing.JRadioButton();
        pekerjaan9 = new javax.swing.JRadioButton();
        pekerjaan6 = new javax.swing.JRadioButton();
        pekerjaan7 = new javax.swing.JRadioButton();
        pekerjaan8 = new javax.swing.JRadioButton();
        napza2 = new javax.swing.JRadioButton();
        napza0 = new javax.swing.JRadioButton();
        napza1 = new javax.swing.JRadioButton();
        napza5 = new javax.swing.JRadioButton();
        napza3 = new javax.swing.JRadioButton();
        napza4 = new javax.swing.JRadioButton();
        napza9 = new javax.swing.JRadioButton();
        napza6 = new javax.swing.JRadioButton();
        napza7 = new javax.swing.JRadioButton();
        napza8 = new javax.swing.JRadioButton();
        legal2 = new javax.swing.JRadioButton();
        legal0 = new javax.swing.JRadioButton();
        legal1 = new javax.swing.JRadioButton();
        legal5 = new javax.swing.JRadioButton();
        legal3 = new javax.swing.JRadioButton();
        legal4 = new javax.swing.JRadioButton();
        legal9 = new javax.swing.JRadioButton();
        legal6 = new javax.swing.JRadioButton();
        legal7 = new javax.swing.JRadioButton();
        legal8 = new javax.swing.JRadioButton();
        kel2 = new javax.swing.JRadioButton();
        kel0 = new javax.swing.JRadioButton();
        kel1 = new javax.swing.JRadioButton();
        kel5 = new javax.swing.JRadioButton();
        kel3 = new javax.swing.JRadioButton();
        kel4 = new javax.swing.JRadioButton();
        kel9 = new javax.swing.JRadioButton();
        kel6 = new javax.swing.JRadioButton();
        kel7 = new javax.swing.JRadioButton();
        kel8 = new javax.swing.JRadioButton();
        psikiatris2 = new javax.swing.JRadioButton();
        psikiatris0 = new javax.swing.JRadioButton();
        psikiatris1 = new javax.swing.JRadioButton();
        psikiatris5 = new javax.swing.JRadioButton();
        psikiatris3 = new javax.swing.JRadioButton();
        psikiatris4 = new javax.swing.JRadioButton();
        psikiatris9 = new javax.swing.JRadioButton();
        psikiatris6 = new javax.swing.JRadioButton();
        psikiatris7 = new javax.swing.JRadioButton();
        psikiatris8 = new javax.swing.JRadioButton();
        label30 = new widget.Label();
        label31 = new widget.Label();
        label32 = new widget.Label();
        label33 = new widget.Label();
        label34 = new widget.Label();
        label35 = new widget.Label();
        label36 = new widget.Label();
        label37 = new widget.Label();
        klien = new widget.TextBox();
        label38 = new widget.Label();
        label39 = new widget.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        resume = new javax.swing.JTextArea();
        label41 = new widget.Label();
        detoksifikasi = new javax.swing.JCheckBox();
        asesmen = new javax.swing.JCheckBox();
        evaluasi = new javax.swing.JCheckBox();
        terapi = new javax.swing.JCheckBox();
        wawancara = new javax.swing.JCheckBox();
        intervensi = new javax.swing.JCheckBox();
        lain = new javax.swing.JCheckBox();
        rehabranap = new javax.swing.JCheckBox();
        konseling = new javax.swing.JCheckBox();
        lainlain = new widget.TextBox();
        terapirumatan = new widget.TextBox();
        rehabrawatinap = new widget.TextBox();
        konselinglain = new widget.TextBox();
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

        MnDokumenESWL.setBackground(new java.awt.Color(255, 255, 254));
        MnDokumenESWL.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenESWL.setForeground(new java.awt.Color(50, 50, 50));
        MnDokumenESWL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenESWL.setText("Laporan/Dokumentasi Tindakan ESWL");
        MnDokumenESWL.setName("MnDokumenESWL"); // NOI18N
        MnDokumenESWL.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDokumenESWL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenESWLActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenESWL);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ FORMULIR ASESMEN WAJIB LAPOR & REHABILITASI MEDIS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(750, 945));
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
        label14.setBounds(0, 70, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 70, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(166, 70, 170, 23);

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
        BtnDokter.setBounds(338, 70, 28, 23);

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
        Jk.setBounds(74, 40, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 40, 70, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 100, 750, 1);

        label12.setText("Tanggal Kedatangan :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(181, 40, 110, 23);

        WaktuMulai.setForeground(new java.awt.Color(50, 70, 50));
        WaktuMulai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023 08:00:04" }));
        WaktuMulai.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        WaktuMulai.setName("WaktuMulai"); // NOI18N
        WaktuMulai.setOpaque(false);
        WaktuMulai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WaktuMulaiKeyPressed(evt);
            }
        });
        FormInput.add(WaktuMulai);
        WaktuMulai.setBounds(295, 40, 135, 23);

        label15.setText("Petugas :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(380, 70, 50, 23);

        NIP.setEditable(false);
        NIP.setName("NIP"); // NOI18N
        NIP.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NIP);
        NIP.setBounds(432, 70, 90, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(524, 70, 170, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(696, 70, 28, 23);

        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.setPreferredSize(new java.awt.Dimension(80, 23));
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(140, 470, 600, 23);

        label16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label16.setText("MASALAH YANG DI HADAPI");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(260, 110, 280, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 390, 750, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 500, 750, 1);

        label27.setText("Psikiatris :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label27);
        label27.setBounds(30, 350, 100, 23);

        buttonGroup1.add(medis2);
        medis2.setText("2");
        medis2.setName("medis2"); // NOI18N
        FormInput.add(medis2);
        medis2.setBounds(220, 150, 40, 20);

        buttonGroup1.add(medis0);
        medis0.setText("0");
        medis0.setName("medis0"); // NOI18N
        FormInput.add(medis0);
        medis0.setBounds(140, 150, 40, 20);

        buttonGroup1.add(medis1);
        medis1.setText("1");
        medis1.setName("medis1"); // NOI18N
        FormInput.add(medis1);
        medis1.setBounds(180, 150, 40, 20);

        label29.setText("3. Rencana Terapi DAN REHABILITASI");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label29);
        label29.setBounds(0, 510, 200, 23);

        buttonGroup1.add(medis5);
        medis5.setText("5");
        medis5.setName("medis5"); // NOI18N
        FormInput.add(medis5);
        medis5.setBounds(340, 150, 40, 20);

        buttonGroup1.add(medis3);
        medis3.setText("3");
        medis3.setName("medis3"); // NOI18N
        FormInput.add(medis3);
        medis3.setBounds(260, 150, 40, 20);

        buttonGroup1.add(medis4);
        medis4.setText("4");
        medis4.setName("medis4"); // NOI18N
        FormInput.add(medis4);
        medis4.setBounds(300, 150, 40, 20);

        buttonGroup1.add(medis9);
        medis9.setText("9");
        medis9.setName("medis9"); // NOI18N
        FormInput.add(medis9);
        medis9.setBounds(500, 150, 40, 20);

        buttonGroup1.add(medis6);
        medis6.setText("6");
        medis6.setName("medis6"); // NOI18N
        medis6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medis6ActionPerformed(evt);
            }
        });
        FormInput.add(medis6);
        medis6.setBounds(380, 150, 40, 20);

        buttonGroup1.add(medis7);
        medis7.setText("7");
        medis7.setName("medis7"); // NOI18N
        FormInput.add(medis7);
        medis7.setBounds(420, 150, 40, 20);

        buttonGroup1.add(medis8);
        medis8.setText("8");
        medis8.setName("medis8"); // NOI18N
        FormInput.add(medis8);
        medis8.setBounds(460, 150, 40, 20);

        buttonGroup2.add(pekerjaan2);
        pekerjaan2.setText("2");
        pekerjaan2.setName("pekerjaan2"); // NOI18N
        FormInput.add(pekerjaan2);
        pekerjaan2.setBounds(220, 190, 40, 20);

        buttonGroup2.add(pekerjaan0);
        pekerjaan0.setText("0");
        pekerjaan0.setName("pekerjaan0"); // NOI18N
        FormInput.add(pekerjaan0);
        pekerjaan0.setBounds(140, 190, 40, 20);

        buttonGroup2.add(pekerjaan1);
        pekerjaan1.setText("1");
        pekerjaan1.setName("pekerjaan1"); // NOI18N
        FormInput.add(pekerjaan1);
        pekerjaan1.setBounds(180, 190, 40, 20);

        buttonGroup2.add(pekerjaan5);
        pekerjaan5.setText("5");
        pekerjaan5.setName("pekerjaan5"); // NOI18N
        FormInput.add(pekerjaan5);
        pekerjaan5.setBounds(340, 190, 40, 20);

        buttonGroup2.add(pekerjaan3);
        pekerjaan3.setText("3");
        pekerjaan3.setName("pekerjaan3"); // NOI18N
        FormInput.add(pekerjaan3);
        pekerjaan3.setBounds(260, 190, 40, 20);

        buttonGroup2.add(pekerjaan4);
        pekerjaan4.setText("4");
        pekerjaan4.setName("pekerjaan4"); // NOI18N
        FormInput.add(pekerjaan4);
        pekerjaan4.setBounds(300, 190, 40, 20);

        buttonGroup2.add(pekerjaan9);
        pekerjaan9.setText("9");
        pekerjaan9.setName("pekerjaan9"); // NOI18N
        FormInput.add(pekerjaan9);
        pekerjaan9.setBounds(500, 190, 40, 20);

        buttonGroup2.add(pekerjaan6);
        pekerjaan6.setText("6");
        pekerjaan6.setName("pekerjaan6"); // NOI18N
        pekerjaan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pekerjaan6ActionPerformed(evt);
            }
        });
        FormInput.add(pekerjaan6);
        pekerjaan6.setBounds(380, 190, 40, 20);

        buttonGroup2.add(pekerjaan7);
        pekerjaan7.setText("7");
        pekerjaan7.setName("pekerjaan7"); // NOI18N
        FormInput.add(pekerjaan7);
        pekerjaan7.setBounds(420, 190, 40, 20);

        buttonGroup2.add(pekerjaan8);
        pekerjaan8.setText("8");
        pekerjaan8.setName("pekerjaan8"); // NOI18N
        FormInput.add(pekerjaan8);
        pekerjaan8.setBounds(460, 190, 40, 20);

        buttonGroup3.add(napza2);
        napza2.setText("2");
        napza2.setName("napza2"); // NOI18N
        FormInput.add(napza2);
        napza2.setBounds(220, 230, 40, 20);

        buttonGroup3.add(napza0);
        napza0.setText("0");
        napza0.setName("napza0"); // NOI18N
        FormInput.add(napza0);
        napza0.setBounds(140, 230, 40, 20);

        buttonGroup3.add(napza1);
        napza1.setText("1");
        napza1.setName("napza1"); // NOI18N
        FormInput.add(napza1);
        napza1.setBounds(180, 230, 40, 20);

        buttonGroup3.add(napza5);
        napza5.setText("5");
        napza5.setName("napza5"); // NOI18N
        FormInput.add(napza5);
        napza5.setBounds(340, 230, 40, 20);

        buttonGroup3.add(napza3);
        napza3.setText("3");
        napza3.setName("napza3"); // NOI18N
        FormInput.add(napza3);
        napza3.setBounds(260, 230, 40, 20);

        buttonGroup3.add(napza4);
        napza4.setText("4");
        napza4.setName("napza4"); // NOI18N
        FormInput.add(napza4);
        napza4.setBounds(300, 230, 40, 20);

        buttonGroup3.add(napza9);
        napza9.setText("9");
        napza9.setName("napza9"); // NOI18N
        FormInput.add(napza9);
        napza9.setBounds(500, 230, 40, 20);

        buttonGroup3.add(napza6);
        napza6.setText("6");
        napza6.setName("napza6"); // NOI18N
        napza6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                napza6ActionPerformed(evt);
            }
        });
        FormInput.add(napza6);
        napza6.setBounds(380, 230, 40, 20);

        buttonGroup3.add(napza7);
        napza7.setText("7");
        napza7.setName("napza7"); // NOI18N
        FormInput.add(napza7);
        napza7.setBounds(420, 230, 40, 20);

        buttonGroup3.add(napza8);
        napza8.setText("8");
        napza8.setName("napza8"); // NOI18N
        FormInput.add(napza8);
        napza8.setBounds(460, 230, 40, 20);

        buttonGroup4.add(legal2);
        legal2.setText("2");
        legal2.setName("legal2"); // NOI18N
        FormInput.add(legal2);
        legal2.setBounds(220, 270, 40, 20);

        buttonGroup4.add(legal0);
        legal0.setText("0");
        legal0.setName("legal0"); // NOI18N
        FormInput.add(legal0);
        legal0.setBounds(140, 270, 40, 20);

        buttonGroup4.add(legal1);
        legal1.setText("1");
        legal1.setName("legal1"); // NOI18N
        FormInput.add(legal1);
        legal1.setBounds(180, 270, 40, 20);

        buttonGroup4.add(legal5);
        legal5.setText("5");
        legal5.setName("legal5"); // NOI18N
        FormInput.add(legal5);
        legal5.setBounds(340, 270, 40, 20);

        buttonGroup4.add(legal3);
        legal3.setText("3");
        legal3.setName("legal3"); // NOI18N
        FormInput.add(legal3);
        legal3.setBounds(260, 270, 40, 20);

        buttonGroup4.add(legal4);
        legal4.setText("4");
        legal4.setName("legal4"); // NOI18N
        FormInput.add(legal4);
        legal4.setBounds(300, 270, 40, 20);

        buttonGroup4.add(legal9);
        legal9.setText("9");
        legal9.setName("legal9"); // NOI18N
        FormInput.add(legal9);
        legal9.setBounds(500, 270, 40, 20);

        buttonGroup4.add(legal6);
        legal6.setText("6");
        legal6.setName("legal6"); // NOI18N
        legal6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legal6ActionPerformed(evt);
            }
        });
        FormInput.add(legal6);
        legal6.setBounds(380, 270, 40, 20);

        buttonGroup4.add(legal7);
        legal7.setText("7");
        legal7.setName("legal7"); // NOI18N
        FormInput.add(legal7);
        legal7.setBounds(420, 270, 40, 20);

        buttonGroup4.add(legal8);
        legal8.setText("8");
        legal8.setName("legal8"); // NOI18N
        FormInput.add(legal8);
        legal8.setBounds(460, 270, 40, 20);

        buttonGroup5.add(kel2);
        kel2.setText("2");
        kel2.setName("kel2"); // NOI18N
        FormInput.add(kel2);
        kel2.setBounds(220, 310, 40, 20);

        buttonGroup5.add(kel0);
        kel0.setText("0");
        kel0.setName("kel0"); // NOI18N
        FormInput.add(kel0);
        kel0.setBounds(140, 310, 40, 20);

        buttonGroup5.add(kel1);
        kel1.setText("1");
        kel1.setName("kel1"); // NOI18N
        FormInput.add(kel1);
        kel1.setBounds(180, 310, 40, 20);

        buttonGroup5.add(kel5);
        kel5.setText("5");
        kel5.setName("kel5"); // NOI18N
        FormInput.add(kel5);
        kel5.setBounds(340, 310, 40, 20);

        buttonGroup5.add(kel3);
        kel3.setText("3");
        kel3.setName("kel3"); // NOI18N
        FormInput.add(kel3);
        kel3.setBounds(260, 310, 40, 20);

        buttonGroup5.add(kel4);
        kel4.setText("4");
        kel4.setName("kel4"); // NOI18N
        FormInput.add(kel4);
        kel4.setBounds(300, 310, 40, 20);

        buttonGroup5.add(kel9);
        kel9.setText("9");
        kel9.setName("kel9"); // NOI18N
        FormInput.add(kel9);
        kel9.setBounds(500, 310, 40, 20);

        buttonGroup5.add(kel6);
        kel6.setText("6");
        kel6.setName("kel6"); // NOI18N
        kel6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kel6ActionPerformed(evt);
            }
        });
        FormInput.add(kel6);
        kel6.setBounds(380, 310, 40, 20);

        buttonGroup5.add(kel7);
        kel7.setText("7");
        kel7.setName("kel7"); // NOI18N
        FormInput.add(kel7);
        kel7.setBounds(420, 310, 40, 20);

        buttonGroup5.add(kel8);
        kel8.setText("8");
        kel8.setName("kel8"); // NOI18N
        FormInput.add(kel8);
        kel8.setBounds(460, 310, 40, 20);

        buttonGroup6.add(psikiatris2);
        psikiatris2.setText("2");
        psikiatris2.setName("psikiatris2"); // NOI18N
        FormInput.add(psikiatris2);
        psikiatris2.setBounds(220, 350, 40, 20);

        buttonGroup6.add(psikiatris0);
        psikiatris0.setText("0");
        psikiatris0.setName("psikiatris0"); // NOI18N
        FormInput.add(psikiatris0);
        psikiatris0.setBounds(140, 350, 40, 20);

        buttonGroup6.add(psikiatris1);
        psikiatris1.setText("1");
        psikiatris1.setName("psikiatris1"); // NOI18N
        FormInput.add(psikiatris1);
        psikiatris1.setBounds(180, 350, 40, 20);

        buttonGroup6.add(psikiatris5);
        psikiatris5.setText("5");
        psikiatris5.setName("psikiatris5"); // NOI18N
        FormInput.add(psikiatris5);
        psikiatris5.setBounds(340, 350, 40, 20);

        buttonGroup6.add(psikiatris3);
        psikiatris3.setText("3");
        psikiatris3.setName("psikiatris3"); // NOI18N
        FormInput.add(psikiatris3);
        psikiatris3.setBounds(260, 350, 40, 20);

        buttonGroup6.add(psikiatris4);
        psikiatris4.setText("4");
        psikiatris4.setName("psikiatris4"); // NOI18N
        FormInput.add(psikiatris4);
        psikiatris4.setBounds(300, 350, 40, 20);

        buttonGroup6.add(psikiatris9);
        psikiatris9.setText("9");
        psikiatris9.setName("psikiatris9"); // NOI18N
        FormInput.add(psikiatris9);
        psikiatris9.setBounds(500, 350, 40, 20);

        buttonGroup6.add(psikiatris6);
        psikiatris6.setText("6");
        psikiatris6.setName("psikiatris6"); // NOI18N
        psikiatris6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psikiatris6ActionPerformed(evt);
            }
        });
        FormInput.add(psikiatris6);
        psikiatris6.setBounds(380, 350, 40, 20);

        buttonGroup6.add(psikiatris7);
        psikiatris7.setText("7");
        psikiatris7.setName("psikiatris7"); // NOI18N
        FormInput.add(psikiatris7);
        psikiatris7.setBounds(420, 350, 40, 20);

        buttonGroup6.add(psikiatris8);
        psikiatris8.setText("8");
        psikiatris8.setName("psikiatris8"); // NOI18N
        FormInput.add(psikiatris8);
        psikiatris8.setBounds(460, 350, 40, 20);

        label30.setText("Medis :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label30);
        label30.setBounds(30, 150, 100, 23);

        label31.setText("Pekerjaan / Dukungan :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label31);
        label31.setBounds(10, 190, 120, 23);

        label32.setText("Napza :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label32);
        label32.setBounds(30, 230, 100, 23);

        label33.setText("Legal :");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label33);
        label33.setBounds(30, 270, 100, 23);

        label34.setText("Keluarga / Sosial :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label34);
        label34.setBounds(30, 310, 100, 23);

        label35.setText("Rencana Terapi :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label35);
        label35.setBounds(30, 630, 100, 23);

        label36.setText("Klien memenuhi kriteria diagnosis Napza  F");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label36);
        label36.setBounds(20, 430, 220, 23);

        label37.setText("1. Kesimpulan");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label37);
        label37.setBounds(0, 110, 100, 23);

        klien.setName("klien"); // NOI18N
        klien.setPreferredSize(new java.awt.Dimension(80, 23));
        klien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                klienKeyPressed(evt);
            }
        });
        FormInput.add(klien);
        klien.setBounds(250, 430, 490, 23);

        label38.setText("2. Diagnosa Kerja");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label38);
        label38.setBounds(0, 400, 100, 23);

        label39.setText("Diagnosis Lainnya :");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label39);
        label39.setBounds(30, 470, 100, 23);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        resume.setColumns(20);
        resume.setRows(5);
        resume.setName("resume"); // NOI18N
        jScrollPane1.setViewportView(resume);

        FormInput.add(jScrollPane1);
        jScrollPane1.setBounds(140, 530, 560, 86);

        label41.setText("Resume Masalah :");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label41);
        label41.setBounds(30, 540, 100, 23);

        detoksifikasi.setText("Program Detoksifikasi");
        detoksifikasi.setName("detoksifikasi"); // NOI18N
        FormInput.add(detoksifikasi);
        detoksifikasi.setBounds(100, 730, 220, 20);

        asesmen.setText("Asesmen Lanjutan / Mendalam");
        asesmen.setName("asesmen"); // NOI18N
        FormInput.add(asesmen);
        asesmen.setBounds(100, 670, 220, 20);

        evaluasi.setText("Evaluasi Psikologis");
        evaluasi.setName("evaluasi"); // NOI18N
        FormInput.add(evaluasi);
        evaluasi.setBounds(100, 700, 220, 20);

        terapi.setText("Terapi Rumatan");
        terapi.setName("terapi"); // NOI18N
        terapi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                terapiItemStateChanged(evt);
            }
        });
        FormInput.add(terapi);
        terapi.setBounds(100, 820, 120, 20);

        wawancara.setText("Wawancara Motivasional");
        wawancara.setName("wawancara"); // NOI18N
        FormInput.add(wawancara);
        wawancara.setBounds(100, 760, 220, 20);

        intervensi.setText("Intervensi Singkat");
        intervensi.setName("intervensi"); // NOI18N
        FormInput.add(intervensi);
        intervensi.setBounds(100, 790, 220, 20);

        lain.setText("Lain - Lain");
        lain.setName("lain"); // NOI18N
        lain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lainItemStateChanged(evt);
            }
        });
        FormInput.add(lain);
        lain.setBounds(100, 910, 110, 20);

        rehabranap.setText("Rehabilitasi Rawat Inap");
        rehabranap.setName("rehabranap"); // NOI18N
        rehabranap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rehabranapItemStateChanged(evt);
            }
        });
        FormInput.add(rehabranap);
        rehabranap.setBounds(100, 850, 150, 20);

        konseling.setText("Konseling");
        konseling.setName("konseling"); // NOI18N
        konseling.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                konselingItemStateChanged(evt);
            }
        });
        FormInput.add(konseling);
        konseling.setBounds(100, 880, 100, 20);

        lainlain.setEditable(false);
        lainlain.setName("lainlain"); // NOI18N
        lainlain.setPreferredSize(new java.awt.Dimension(80, 23));
        lainlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lainlainKeyPressed(evt);
            }
        });
        FormInput.add(lainlain);
        lainlain.setBounds(220, 910, 510, 23);

        terapirumatan.setEditable(false);
        terapirumatan.setName("terapirumatan"); // NOI18N
        terapirumatan.setPreferredSize(new java.awt.Dimension(80, 23));
        terapirumatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                terapirumatanKeyPressed(evt);
            }
        });
        FormInput.add(terapirumatan);
        terapirumatan.setBounds(230, 820, 500, 23);

        rehabrawatinap.setEditable(false);
        rehabrawatinap.setName("rehabrawatinap"); // NOI18N
        rehabrawatinap.setPreferredSize(new java.awt.Dimension(80, 23));
        rehabrawatinap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rehabrawatinapKeyPressed(evt);
            }
        });
        FormInput.add(rehabrawatinap);
        rehabrawatinap.setBounds(260, 850, 470, 23);

        konselinglain.setEditable(false);
        konselinglain.setName("konselinglain"); // NOI18N
        konselinglain.setPreferredSize(new java.awt.Dimension(80, 23));
        konselinglain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                konselinglainKeyPressed(evt);
            }
        });
        FormInput.add(konselinglain);
        konselinglain.setBounds(220, 880, 510, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Dokumentasi Tindakan", internalFrame2);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023" }));
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

        TabRawat.addTab("Data Dokumentasi Tindakan", internalFrame3);

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
       String medis = "";
       String pekerjaan = "";
       String napza = "";
       String legal = "";
       String kel = "";
       String psikiatris = "";
        String rencana = "";
        
        if(medis0.isSelected()){
            medis = "0";
        } else if (medis1.isSelected()){
            medis = "1";
        } else if (medis2.isSelected()){
            medis = "2";
        }  else if (medis3.isSelected()){
            medis = "3";
        }  else if (medis4.isSelected()){
            medis = "4";
        }  else if (medis5.isSelected()){
            medis = "5";
        }  else if (medis6.isSelected()){
            medis = "6";
        }  else if (medis7.isSelected()){
            medis = "7";
        }  else if (medis8.isSelected()){
            medis = "8";
        }  else if (medis9.isSelected()){
            medis = "9";
        } 
        
        if(pekerjaan0.isSelected()){
            pekerjaan = "0";
        } else if (pekerjaan1.isSelected()){
            pekerjaan = "1";
        } else if (pekerjaan2.isSelected()){
            pekerjaan = "2";
        }  else if (pekerjaan3.isSelected()){
            pekerjaan = "3";
        }  else if (pekerjaan4.isSelected()){
            pekerjaan = "4";
        }  else if (pekerjaan5.isSelected()){
            pekerjaan = "5";
        }  else if (pekerjaan6.isSelected()){
            pekerjaan = "6";
        }  else if (pekerjaan7.isSelected()){
            pekerjaan = "7";
        }  else if (pekerjaan8.isSelected()){
            pekerjaan = "8";
        }  else if (pekerjaan9.isSelected()){
            pekerjaan = "9";
        } 
        
        if(napza0.isSelected()){
            napza = "0";
        } else if (napza1.isSelected()){
            napza = "1";
        } else if (napza2.isSelected()){
            napza = "2";
        }  else if (napza3.isSelected()){
            napza = "3";
        }  else if (napza4.isSelected()){
            napza = "4";
        }  else if (napza5.isSelected()){
            napza = "5";
        }  else if (napza6.isSelected()){
            napza = "6";
        }  else if (napza7.isSelected()){
            napza = "7";
        }  else if (napza8.isSelected()){
            napza = "8";
        }  else if (napza9.isSelected()){
            napza = "9";
        } 
        
        if(legal0.isSelected()){
            legal = "0";
        } else if (legal1.isSelected()){
            legal = "1";
        } else if (legal2.isSelected()){
            legal = "2";
        }  else if (legal3.isSelected()){
            legal = "3";
        }  else if (legal4.isSelected()){
            legal = "4";
        }  else if (legal5.isSelected()){
            legal = "5";
        }  else if (legal6.isSelected()){
            legal = "6";
        }  else if (legal7.isSelected()){
            legal = "7";
        }  else if (legal8.isSelected()){
            legal = "8";
        }  else if (legal9.isSelected()){
            legal = "9";
        } 
        
        
       if(kel0.isSelected()){
            kel = "0";
        } else if (kel1.isSelected()){
            kel = "1";
        } else if (kel2.isSelected()){
            kel = "2";
        }  else if (kel3.isSelected()){
            kel = "3";
        }  else if (kel4.isSelected()){
            kel = "4";
        }  else if (kel5.isSelected()){
            kel = "5";
        }  else if (kel6.isSelected()){
            kel = "6";
        }  else if (kel7.isSelected()){
            kel = "7";
        }  else if (kel8.isSelected()){
            kel = "8";
        }  else if (kel9.isSelected()){
            kel = "9";
        } 
        
       if(psikiatris0.isSelected()){
            psikiatris = "0";
        } else if (psikiatris1.isSelected()){
            psikiatris = "1";
        } else if (psikiatris2.isSelected()){
            psikiatris = "2";
        }  else if (psikiatris3.isSelected()){
            psikiatris = "3";
        }  else if (psikiatris4.isSelected()){
            psikiatris = "4";
        }  else if (psikiatris5.isSelected()){
            psikiatris = "5";
        }  else if (psikiatris6.isSelected()){
            psikiatris = "6";
        }  else if (psikiatris7.isSelected()){
            psikiatris = "7";
        }  else if (psikiatris8.isSelected()){
            psikiatris = "8";
        }  else if (psikiatris9.isSelected()){
            psikiatris = "9";
        } 
        
       if (asesmen.isSelected()) {
            rencana += "Asesmen Lanjutan / mendalam, ";
        }
        if (evaluasi.isSelected()) {
            rencana += "Evaluasi Psikologis, ";
        }
        if (detoksifikasi.isSelected()) {
            rencana += "Program Detoksifikasi, ";
        }
        if (wawancara.isSelected()) {
            rencana += "Wawancara Motivasional, ";
        }
        if (intervensi.isSelected()) {
            rencana += "Intervensi Singkat, ";
        }
         if (terapi.isSelected()) {
            rencana += "Terapi Rumatan, ";
        }
          if (rehabranap.isSelected()) {
            rencana += "Rehabilitasi Rawat Inap, ";
        }
           if (konseling.isSelected()) {
            rencana += "Konseling, ";
        } if (lain.isSelected()) {
            rencana += "Lain - Lain, ";
        }
        rencana = rencana.trim();
        if (rencana.endsWith(", ")) {
            rencana = rencana.substring(0, rencana.length() - 2);
        }

        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Ahli Bedah");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
        }else{
            if(Sequel.menyimpantf("asesmenwajiblapor","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat & Waktu Mulai",18,new String[]{
                    TNoRw.getText(),Valid.SetTgl(WaktuMulai.getSelectedItem()+"")+" "+WaktuMulai.getSelectedItem().toString().substring(11,19),
                    KdDokter.getText(), 
                    NIP.getText(),medis,pekerjaan,napza,legal,kel,psikiatris,
                    klien.getText(),Diagnosa.getText(),resume.getText(),rencana,terapirumatan.getText(),rehabrawatinap.getText(),konselinglain.getText(),lainlain.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,Anjungan,BtnBatal);
        }
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
            Valid.textKosong(BtnDokter,"Ahli Bedah");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
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
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Ahli Bedah</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NIP</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Asisten/Perawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Waktu Mulai</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Waktu Selesai</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosa</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tindakan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Obat Analgesik/Anastesi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Obat Lain</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Uraian Tindakan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Focus</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rate</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Power</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Shock</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diintegrasi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kekurangan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anjungan</b></td>"+
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
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='2300px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                File f = new File("DataDokumentasiTindakanESWL.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='2300px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA DOKUMENTASI TINDAKAN ESWL<br><br></font>"+        
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
        Valid.pindah(evt,WaktuMulai,BtnPetugas);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnDokumenESWLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenESWLActionPerformed
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
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString())); 
            
            Valid.MyReportqry("rptCetakDokumentasiTindakanESWL.jasper","report","::[ Laporan Dokumentasi Tindakan ESWL ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,hasil_tindakan_eswl.mulai,"+
                "hasil_tindakan_eswl.selesai,hasil_tindakan_eswl.kd_dokter,hasil_tindakan_eswl.nip,hasil_tindakan_eswl.diagnosa,hasil_tindakan_eswl.tindakan,hasil_tindakan_eswl.obat_analgesik,"+
                "hasil_tindakan_eswl.obat_lain,hasil_tindakan_eswl.uraian_tindakan,hasil_tindakan_eswl.uraian_tindakan_focus,hasil_tindakan_eswl.uraian_tindakan_rate,"+
                "hasil_tindakan_eswl.uraian_tindakan_power,hasil_tindakan_eswl.uraian_tindakan_shock,hasil_tindakan_eswl.diintegrasi,hasil_tindakan_eswl.kekurangan,hasil_tindakan_eswl.anjungan,"+
                "dokter.nm_dokter,petugas.nama from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join hasil_tindakan_eswl on reg_periksa.no_rawat=hasil_tindakan_eswl.no_rawat "+
                "inner join dokter on hasil_tindakan_eswl.kd_dokter=dokter.kd_dokter "+
                "inner join petugas on hasil_tindakan_eswl.nip=petugas.nip where hasil_tindakan_eswl.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and hasil_tindakan_eswl.mulai='"+tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()+"'",param);
        }
    }//GEN-LAST:event_MnDokumenESWLActionPerformed

    private void WaktuMulaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WaktuMulaiKeyPressed
//       Valid.pindah2(evt,Anjungan,WaktuSelesai);
    }//GEN-LAST:event_WaktuMulaiKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        Valid.pindah(evt,BtnDokter,Diagnosa);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        Valid.pindah(evt,BtnPetugas,Tindakan);
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void ObatLainLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatLainLainKeyPressed
//        Valid.pindah(evt,ObatAnastesi,UraianTindakan);
    }//GEN-LAST:event_ObatLainLainKeyPressed

    private void ObatAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatAnastesiKeyPressed
        Valid.pindah(evt,Tindakan,ObatLainLain);
    }//GEN-LAST:event_ObatAnastesiKeyPressed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        Valid.pindah(evt,Diagnosa,ObatAnastesi);
    }//GEN-LAST:event_TindakanKeyPressed

    private void medis6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medis6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medis6ActionPerformed

    private void pekerjaan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pekerjaan6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pekerjaan6ActionPerformed

    private void napza6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_napza6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_napza6ActionPerformed

    private void legal6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legal6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_legal6ActionPerformed

    private void kel6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kel6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kel6ActionPerformed

    private void psikiatris6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psikiatris6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_psikiatris6ActionPerformed

    private void klienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_klienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_klienKeyPressed

    private void lainlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lainlainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lainlainKeyPressed

    private void terapirumatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_terapirumatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_terapirumatanKeyPressed

    private void rehabrawatinapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rehabrawatinapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rehabrawatinapKeyPressed

    private void konselinglainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_konselinglainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_konselinglainKeyPressed

    private void terapiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_terapiItemStateChanged
          if (terapi.isSelected())  {
            terapirumatan.setEditable(true);
         }
        else {
             terapirumatan.setEditable(false);        // TODO add your handling code here:
    }//GEN-LAST:event_terapiItemStateChanged
    }
    private void rehabranapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rehabranapItemStateChanged
         if (rehabranap.isSelected())  {
            rehabrawatinap.setEditable(true);
         }
        else {
             rehabrawatinap.setEditable(false);  // TODO add your handling code here:
    }//GEN-LAST:event_rehabranapItemStateChanged
    }
    private void konselingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_konselingItemStateChanged
            if (konseling.isSelected())  {
            konselinglain.setEditable(true);
         }
        else {
             konselinglain.setEditable(false);     // TODO add your handling code here:
    }//GEN-LAST:event_konselingItemStateChanged
    }        
    private void lainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lainItemStateChanged
         if (lain.isSelected())  {
            lainlain.setEditable(true);
         }
        else {
             lainlain.setEditable(false);
    }//GEN-LAST:event_lainItemStateChanged
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMFormulirAssesmenWajibLaporRehab dialog = new RMFormulirAssesmenWajibLaporRehab(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnDokumenESWL;
    private widget.TextBox NIP;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPetugas;
    private widget.TextBox ObatAnastesi;
    private widget.TextBox ObatLainLain;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TglLahir;
    private widget.TextBox Tindakan;
    private widget.Tanggal WaktuMulai;
    private javax.swing.JCheckBox asesmen;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JCheckBox detoksifikasi;
    private javax.swing.JCheckBox evaluasi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private javax.swing.JCheckBox intervensi;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JRadioButton kel0;
    private javax.swing.JRadioButton kel1;
    private javax.swing.JRadioButton kel2;
    private javax.swing.JRadioButton kel3;
    private javax.swing.JRadioButton kel4;
    private javax.swing.JRadioButton kel5;
    private javax.swing.JRadioButton kel6;
    private javax.swing.JRadioButton kel7;
    private javax.swing.JRadioButton kel8;
    private javax.swing.JRadioButton kel9;
    private widget.TextBox klien;
    private javax.swing.JCheckBox konseling;
    private widget.TextBox konselinglain;
    private widget.Label label12;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label27;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label41;
    private javax.swing.JCheckBox lain;
    private widget.TextBox lainlain;
    private javax.swing.JRadioButton legal0;
    private javax.swing.JRadioButton legal1;
    private javax.swing.JRadioButton legal2;
    private javax.swing.JRadioButton legal3;
    private javax.swing.JRadioButton legal4;
    private javax.swing.JRadioButton legal5;
    private javax.swing.JRadioButton legal6;
    private javax.swing.JRadioButton legal7;
    private javax.swing.JRadioButton legal8;
    private javax.swing.JRadioButton legal9;
    private javax.swing.JRadioButton medis0;
    private javax.swing.JRadioButton medis1;
    private javax.swing.JRadioButton medis2;
    private javax.swing.JRadioButton medis3;
    private javax.swing.JRadioButton medis4;
    private javax.swing.JRadioButton medis5;
    private javax.swing.JRadioButton medis6;
    private javax.swing.JRadioButton medis7;
    private javax.swing.JRadioButton medis8;
    private javax.swing.JRadioButton medis9;
    private javax.swing.JRadioButton napza0;
    private javax.swing.JRadioButton napza1;
    private javax.swing.JRadioButton napza2;
    private javax.swing.JRadioButton napza3;
    private javax.swing.JRadioButton napza4;
    private javax.swing.JRadioButton napza5;
    private javax.swing.JRadioButton napza6;
    private javax.swing.JRadioButton napza7;
    private javax.swing.JRadioButton napza8;
    private javax.swing.JRadioButton napza9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JRadioButton pekerjaan0;
    private javax.swing.JRadioButton pekerjaan1;
    private javax.swing.JRadioButton pekerjaan2;
    private javax.swing.JRadioButton pekerjaan3;
    private javax.swing.JRadioButton pekerjaan4;
    private javax.swing.JRadioButton pekerjaan5;
    private javax.swing.JRadioButton pekerjaan6;
    private javax.swing.JRadioButton pekerjaan7;
    private javax.swing.JRadioButton pekerjaan8;
    private javax.swing.JRadioButton pekerjaan9;
    private javax.swing.JRadioButton psikiatris0;
    private javax.swing.JRadioButton psikiatris1;
    private javax.swing.JRadioButton psikiatris2;
    private javax.swing.JRadioButton psikiatris3;
    private javax.swing.JRadioButton psikiatris4;
    private javax.swing.JRadioButton psikiatris5;
    private javax.swing.JRadioButton psikiatris6;
    private javax.swing.JRadioButton psikiatris7;
    private javax.swing.JRadioButton psikiatris8;
    private javax.swing.JRadioButton psikiatris9;
    private javax.swing.JCheckBox rehabranap;
    private widget.TextBox rehabrawatinap;
    private javax.swing.JTextArea resume;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    private javax.swing.JCheckBox terapi;
    private widget.TextBox terapirumatan;
    private javax.swing.JCheckBox wawancara;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,"+
								"pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,"+
								"pasien.tgl_lahir,"+
								"asesmenwajiblapor.mulai,"+
								"asesmenwajiblapor.kd_dokter,"+
								"dokter.nm_dokter,"+
								"asesmenwajiblapor.nip,"+
								"petugas.nama,"+
								"asesmenwajiblapor.medis,"+
								"asesmenwajiblapor.pekerjaan,"+
								"asesmenwajiblapor.napza,"+
                                                                "asesmenwajiblapor.legal,"+
								"asesmenwajiblapor.keluarga,"+
								"asesmenwajiblapor.psikiatris,"+
								"asesmenwajiblapor.klien,"+
                                                                "asesmenwajiblapor.diagnosa,"+
								"asesmenwajiblapor.resume,"+
								"asesmenwajiblapor.rencanaterapi,"+
								"asesmenwajiblapor.rehabranap,"+
								"asesmenwajiblapor.terapirumatan,"+
								"asesmenwajiblapor.konseling,"+
								"asesmenwajiblapor.lain "+
			"from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join asesmenwajiblapor on reg_periksa.no_rawat=asesmenwajiblapor.no_rawat "+
                        "inner join dokter on asesmenwajiblapor.kd_dokter=dokter.kd_dokter "+
                        "inner join petugas on asesmenwajiblapor.nip=petugas.nip "+
			"where asesmenwajiblapor.mulai BETWEEN ? AND ? order by asesmenwajiblapor.mulai");
            }else{
                ps=koneksi.prepareStatement(
                       "select reg_periksa.no_rawat,pasien.no_rkm_medis,"+
								"pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,"+
								"pasien.tgl_lahir,"+
								"asesmenwajiblapor.mulai,"+
								"asesmenwajiblapor.kd_dokter,"+
								"dokter.nm_dokter,"+
								"asesmenwajiblapor.nip,"+
								"petugas.nama,"+
								"asesmenwajiblapor.medis,"+
								"asesmenwajiblapor.pekerjaan,"+
								"asesmenwajiblapor.napza,"+
                                                                "asesmenwajiblapor.legal,"+
								"asesmenwajiblapor.keluarga,"+
								"asesmenwajiblapor.psikiatris,"+
								"asesmenwajiblapor.klien,"+
                                                                "asesmenwajiblapor.diagnosa,"+
								"asesmenwajiblapor.resume,"+
								"asesmenwajiblapor.rencanaterapi,"+
								"asesmenwajiblapor.rehabranap,"+
								"asesmenwajiblapor.terapirumatan,"+
								"asesmenwajiblapor.konseling,"+
								"asesmenwajiblapor.lain "+
                        "from reg_periksa "+
                        "inner join pasien on reg_periksa.no_rkm_medis = pasien.no_rkm_medis "+       
                        " inner join asesmenwajiblapor on reg_periksa.no_rawat = asesmenwajiblapor.no_rawat "+
                        " inner join dokter on asesmenwajiblapor.kd_dokter=dokter.kd_dokter "+
                        " inner join petugas on asesmenwajiblapor.nip=petugas.nip "+   
                        "where asesmenwajiblapor.mulai between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "asesmenwajiblapor.diagnosa like ? ) order by asesmenwajiblapor.mulai");
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
                    
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"), rs.getString("mulai"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("nip"),rs.getString("nama"),
                       rs.getString("medis"),rs.getString("pekerjaan"),rs.getString("napza"),rs.getString("legal"),rs.getString("keluarga"),rs.getString("psikiatris"),rs.getString("klien"),
                        rs.getString("diagnosa"),rs.getString("resume"),rs.getString("rencanaterapi"),rs.getString("terapirumatan"),rs.getString("rehabranap"),rs.getString("konseling"),rs.getString("lain")
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
        WaktuMulai.setDate(new Date());
        KdDokter.setText("");
        NmDokter.setText("");
        NIP.setText("");
        NmPetugas.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        buttonGroup5.clearSelection();
        buttonGroup6.clearSelection();
        klien.setText("");
        Diagnosa.setText("");
        resume.setText("");
        asesmen.setSelected(false);
        evaluasi.setSelected(false);
        detoksifikasi.setSelected(false);
        wawancara.setSelected(false);
        intervensi.setSelected(false);
        terapi.setSelected(false);
        rehabranap.setSelected(false);
        konseling.setSelected(false);
        lain.setSelected(false);
        terapirumatan.setText("");
        rehabrawatinap.setText("");
        konselinglain.setText("");
        lainlain.setText("");
        TabRawat.setSelectedIndex(0);
        Diagnosa.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            String medis = tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString();
            
            if(medis.contains("0")){
            medis0.setSelected(true);
        } else if (medis.contains("1")){
            medis1.setSelected(true);
        } else if (medis.contains("2")){
            medis2.setSelected(true);
        }  else if (medis.contains("3")){
            medis3.setSelected(true);
        }  else if (medis.contains("4")){
            medis4.setSelected(true);
        }  else if (medis.contains("5")){
            medis5.setSelected(true);
        }  else if (medis.contains("6")){
            medis6.setSelected(true);
        }  else if (medis.contains("7")){
            medis7.setSelected(true);
        }  else if (medis.contains("8")){
            medis8.setSelected(true);
        }  else if (medis.contains("9")){
            medis9.setSelected(true);
        } 
            
            String pekerjaan = tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString();
            
            if(pekerjaan.contains("0")){
            pekerjaan0.setSelected(true);
        } else if (pekerjaan.contains("1")){
            pekerjaan1.setSelected(true);
        } else if (pekerjaan.contains("2")){
            pekerjaan2.setSelected(true);
        }  else if (pekerjaan.contains("3")){
            pekerjaan3.setSelected(true);
        }  else if (pekerjaan.contains("4")){
            pekerjaan4.setSelected(true);
        }  else if (pekerjaan.contains("5")){
            pekerjaan5.setSelected(true);
        }  else if (pekerjaan.contains("6")){
            pekerjaan6.setSelected(true);
        }  else if (pekerjaan.contains("7")){
            pekerjaan7.setSelected(true);
        }  else if (pekerjaan.contains("8")){
            pekerjaan8.setSelected(true);
        }  else if (pekerjaan.contains("9")){
            pekerjaan9.setSelected(true);
        } 

            
            String napza = tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString();
            
            if(napza.contains("0")){
            napza0.setSelected(true);
        } else if (napza.contains("1")){
            napza1.setSelected(true);
        } else if (napza.contains("2")){
            napza2.setSelected(true);
        }  else if (napza.contains("3")){
            napza3.setSelected(true);
        }  else if (napza.contains("4")){
            napza4.setSelected(true);
        }  else if (napza.contains("5")){
            napza5.setSelected(true);
        }  else if (napza.contains("6")){
            napza6.setSelected(true);
        }  else if (napza.contains("7")){
            napza7.setSelected(true);
        }  else if (napza.contains("8")){
            napza8.setSelected(true);
        }  else if (napza.contains("9")){
            napza9.setSelected(true);
        } 

            
            String legal = tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString();
            
            if(legal.contains("0")){
            legal0.setSelected(true);
        } else if (legal.contains("1")){
            legal1.setSelected(true);
        } else if (legal.contains("2")){
            legal2.setSelected(true);
        }  else if (legal.contains("3")){
            legal3.setSelected(true);
        }  else if (legal.contains("4")){
            legal4.setSelected(true);
        }  else if (legal.contains("5")){
            legal5.setSelected(true);
        }  else if (legal.contains("6")){
            legal6.setSelected(true);
        }  else if (legal.contains("7")){
            legal7.setSelected(true);
        }  else if (legal.contains("8")){
            legal8.setSelected(true);
        }  else if (legal.contains("9")){
            legal9.setSelected(true);
        } 

            
            String kel = tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString();

            if(kel.contains("0")){
            kel0.setSelected(true);
        } else if (kel.contains("1")){
            kel1.setSelected(true);
        } else if (kel.contains("2")){
            kel2.setSelected(true);
        }  else if (kel.contains("3")){
            kel3.setSelected(true);
        }  else if (kel.contains("4")){
            kel4.setSelected(true);
        }  else if (kel.contains("5")){
            kel5.setSelected(true);
        }  else if (kel.contains("6")){
            kel6.setSelected(true);
        }  else if (kel.contains("7")){
            kel7.setSelected(true);
        }  else if (kel.contains("8")){
            kel8.setSelected(true);
        }  else if (kel.contains("9")){
            kel9.setSelected(true);
        } 
           
            
            String psikiatris = tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString();
            if(psikiatris.contains("0")){
            psikiatris0.setSelected(true);
        } else if (psikiatris.contains("1")){
            psikiatris1.setSelected(true);
        } else if (psikiatris.contains("2")){
            psikiatris2.setSelected(true);
        }  else if (psikiatris.contains("3")){
            psikiatris3.setSelected(true);
        }  else if (psikiatris.contains("4")){
            psikiatris4.setSelected(true);
        }  else if (psikiatris.contains("5")){
            psikiatris5.setSelected(true);
        }  else if (psikiatris.contains("6")){
            psikiatris6.setSelected(true);
        }  else if (psikiatris.contains("7")){
            psikiatris7.setSelected(true);
        }  else if (psikiatris.contains("8")){
            psikiatris8.setSelected(true);
        }  else if (psikiatris.contains("9")){
            psikiatris9.setSelected(true);
        } 

            
            klien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            resume.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            
           
             String rencana = tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString();
                    
              if (rencana.contains("Asesmen Lanjutan / mendalam, ")) {
                asesmen.setSelected(true);
            } if (rencana.contains("Evaluasi Psikologis, ")) {
                evaluasi.setSelected(true);
            } if (rencana.contains("Program Detoksifikasi, ")) {
                detoksifikasi.setSelected(true);
            } if (rencana.contains("Wawancara Motivasional, ")) {
                wawancara.setSelected(true);
            } if (rencana.contains("Intervensi Singkat, ")) {
                intervensi.setSelected(true);
            } if (rencana.contains("Terapi Rumatan, ")) {
                terapi.setSelected(true);
            } if (rencana.contains("Rehabilitasi Rawat Inap, ")) {
                rehabranap.setSelected(true);
            } if (rencana.contains("Konseling, ")) {
                konseling.setSelected(true);
            } if (rencana.contains("Lain - Lain, ")) {
                lain.setSelected(true);
            }
            
            terapirumatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            rehabrawatinap.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            konselinglain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            lainlain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            Valid.SetTgl2(WaktuMulai,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            
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
        BtnSimpan.setEnabled(akses.gethasil_tindakan_eswl());
        BtnHapus.setEnabled(akses.gethasil_tindakan_eswl());
        BtnEdit.setEnabled(akses.gethasil_tindakan_eswl());
        BtnEdit.setEnabled(akses.gethasil_tindakan_eswl());
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
        if(Sequel.queryu2tf("delete from asesmenwajiblapor where no_rawat=? and mulai=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        
        String medis = "";
       String pekerjaan = "";
       String napza = "";
       String legal = "";
       String kel = "";
       String psikiatris = "";
        String rencana = "";
        
        if(medis0.isSelected()){
            medis = "0";
        } else if (medis1.isSelected()){
            medis = "1";
        } else if (medis2.isSelected()){
            medis = "2";
        }  else if (medis3.isSelected()){
            medis = "3";
        }  else if (medis4.isSelected()){
            medis = "4";
        }  else if (medis5.isSelected()){
            medis = "5";
        }  else if (medis6.isSelected()){
            medis = "6";
        }  else if (medis7.isSelected()){
            medis = "7";
        }  else if (medis8.isSelected()){
            medis = "8";
        }  else if (medis9.isSelected()){
            medis = "9";
        } 
        
        if(pekerjaan0.isSelected()){
            pekerjaan = "0";
        } else if (pekerjaan1.isSelected()){
            pekerjaan = "1";
        } else if (pekerjaan2.isSelected()){
            pekerjaan = "2";
        }  else if (pekerjaan3.isSelected()){
            pekerjaan = "3";
        }  else if (pekerjaan4.isSelected()){
            pekerjaan = "4";
        }  else if (pekerjaan5.isSelected()){
            pekerjaan = "5";
        }  else if (pekerjaan6.isSelected()){
            pekerjaan = "6";
        }  else if (pekerjaan7.isSelected()){
            pekerjaan = "7";
        }  else if (pekerjaan8.isSelected()){
            pekerjaan = "8";
        }  else if (pekerjaan9.isSelected()){
            pekerjaan = "9";
        } 
        
        if(napza0.isSelected()){
            napza = "0";
        } else if (napza1.isSelected()){
            napza = "1";
        } else if (napza2.isSelected()){
            napza = "2";
        }  else if (napza3.isSelected()){
            napza = "3";
        }  else if (napza4.isSelected()){
            napza = "4";
        }  else if (napza5.isSelected()){
            napza = "5";
        }  else if (napza6.isSelected()){
            napza = "6";
        }  else if (napza7.isSelected()){
            napza = "7";
        }  else if (napza8.isSelected()){
            napza = "8";
        }  else if (napza9.isSelected()){
            napza = "9";
        } 
        
        if(legal0.isSelected()){
            legal = "0";
        } else if (legal1.isSelected()){
            legal = "1";
        } else if (legal2.isSelected()){
            legal = "2";
        }  else if (legal3.isSelected()){
            legal = "3";
        }  else if (legal4.isSelected()){
            legal = "4";
        }  else if (legal5.isSelected()){
            legal = "5";
        }  else if (legal6.isSelected()){
            legal = "6";
        }  else if (legal7.isSelected()){
            legal = "7";
        }  else if (legal8.isSelected()){
            legal = "8";
        }  else if (legal9.isSelected()){
            legal = "9";
        } 
        
        
       if(kel0.isSelected()){
            kel = "0";
        } else if (kel1.isSelected()){
            kel = "1";
        } else if (kel2.isSelected()){
            kel = "2";
        }  else if (kel3.isSelected()){
            kel = "3";
        }  else if (kel4.isSelected()){
            kel = "4";
        }  else if (kel5.isSelected()){
            kel = "5";
        }  else if (kel6.isSelected()){
            kel = "6";
        }  else if (kel7.isSelected()){
            kel = "7";
        }  else if (kel8.isSelected()){
            kel = "8";
        }  else if (kel9.isSelected()){
            kel = "9";
        } 
        
       if(psikiatris0.isSelected()){
            psikiatris = "0";
        } else if (psikiatris1.isSelected()){
            psikiatris = "1";
        } else if (psikiatris2.isSelected()){
            psikiatris = "2";
        }  else if (psikiatris3.isSelected()){
            psikiatris = "3";
        }  else if (psikiatris4.isSelected()){
            psikiatris = "4";
        }  else if (psikiatris5.isSelected()){
            psikiatris = "5";
        }  else if (psikiatris6.isSelected()){
            psikiatris = "6";
        }  else if (psikiatris7.isSelected()){
            psikiatris = "7";
        }  else if (psikiatris8.isSelected()){
            psikiatris = "8";
        }  else if (psikiatris9.isSelected()){
            psikiatris = "9";
        } 
        
       if (asesmen.isSelected()) {
            rencana += "Asesmen Lanjutan / mendalam, ";
        }
        if (evaluasi.isSelected()) {
            rencana += "Evaluasi Psikologis, ";
        }
        if (detoksifikasi.isSelected()) {
            rencana += "Program Detoksifikasi, ";
        }
        if (wawancara.isSelected()) {
            rencana += "Wawancara Motivasional, ";
        }
        if (intervensi.isSelected()) {
            rencana += "Intervensi Singkat, ";
        }
         if (terapi.isSelected()) {
            rencana += "Terapi Rumatan, ";
        }
          if (rehabranap.isSelected()) {
            rencana += "Rehabilitasi Rawat Inap, ";
        }
           if (konseling.isSelected()) {
            rencana += "Konseling, ";
        } if (lain.isSelected()) {
            rencana += "Lain - Lain, ";
        }
        rencana = rencana.trim();
        if (rencana.endsWith(", ")) {
            rencana = rencana.substring(0, rencana.length() - 2);
        }
        
        if(Sequel.mengedittf("asesmenwajiblapor","no_rawat=? and mulai=?","no_rawat=?,mulai=?,kd_dokter=?,nip=?,medis=?,pekerjaan=?,"+
                "napza=?,legal=?,keluarga=?,psikiatris=?,klien=?,diagnosa=?,resume=?,"+
                "rencanaterapi=?,terapirumatan=?,rehabranap=?,konseling=?,lain=?",20,new String[]{
                TNoRw.getText(),Valid.SetTgl(WaktuMulai.getSelectedItem()+"")+" "+WaktuMulai.getSelectedItem().toString().substring(11,19),
                KdDokter.getText(),NIP.getText(),medis,pekerjaan,napza,legal,kel,psikiatris,
                klien.getText(),Diagnosa.getText(),resume.getText(),rencana,terapirumatan.getText(), 
                rehabrawatinap.getText(),konselinglain.getText(),lain.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
            })==true){
//               tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
//               tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
//               tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
//               tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
//               tbObat.setValueAt(Jk.getText(),tbObat.getSelectedRow(),4);
//               tbObat.setValueAt(Valid.SetTgl(WaktuMulai.getSelectedItem()+"")+" "+WaktuMulai.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),5);
//               tbObat.setValueAt(KdDokter.getText(),tbObat.getSelectedRow(),5);
//               tbObat.setValueAt(NmDokter.getText(),tbObat.getSelectedRow(),6);
//               tbObat.setValueAt(NIP.getText(),tbObat.getSelectedRow(),7);
//               tbObat.setValueAt(NmPetugas.getText(),tbObat.getSelectedRow(),8);
//               tbObat.setValueAt(Diagnosa.getText(),tbObat.getSelectedRow(),11);
//               tbObat.setValueAt(Tindakan.getText(),tbObat.getSelectedRow(),12);
//               tbObat.setValueAt(ObatAnastesi.getText(),tbObat.getSelectedRow(),13);
//               tbObat.setValueAt(ObatLainLain.getText(),tbObat.getSelectedRow(),14);
//               tbObat.setValueAt(UraianTindakan.getText(),tbObat.getSelectedRow(),15);
//               tbObat.setValueAt(Focus.getText(),tbObat.getSelectedRow(),16);
//               tbObat.setValueAt(Rate.getText(),tbObat.getSelectedRow(),17);
//               tbObat.setValueAt(Power.getText(),tbObat.getSelectedRow(),18);
//               tbObat.setValueAt(Shock.getText(),tbObat.getSelectedRow(),19);
//               tbObat.setValueAt(Diintegrasi.getText(),tbObat.getSelectedRow(),20);
//               tbObat.setValueAt(Kekurangan.getText(),tbObat.getSelectedRow(),21);
//               tbObat.setValueAt(Anjungan.getText(),tbObat.getSelectedRow(),22);
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
