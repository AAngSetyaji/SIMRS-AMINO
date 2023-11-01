/*
 * Kontribusi dari M. Syukur Rsjiwa Kendari
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


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianPsikologiDewasa extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="",pilihan="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianPsikologiDewasa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Diagnosa Dokter","Keluhan Fisik","Keluhan Psikologis","Penampilan Umum","Sikap Terhadap Pemeriksa",
            "Afek","Roman Muka","Proses Pikir","Gangguan Persepsi","Deskripsi Lainnya","Memori","Konsentrasi","Orientasi","Kemampuan Verbal","Emosi","Perilaku","Fungsi Lainnya","Psikotes 1","Psikotes 2","Psikotes 3","Psikotes 4","Psikotes 5","Hasil Psikotes","Simptom","Simptom Lainnya",
            "Dinamika Psikologi","Diagnosa Psikologi","Rencana Intervensi","Intervensi Psikologi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 37; i++) {
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
                column.setPreferredWidth(155);
            }else if(i==7){
                column.setPreferredWidth(117);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(300);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(150);
            }else if(i==16){
                column.setPreferredWidth(150);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(150);
            }else if(i==19){
                column.setPreferredWidth(150);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(150);
            }else if(i==22){
                column.setPreferredWidth(150);
            }else if(i==23){
                column.setPreferredWidth(150);
            }else if(i==24){
                column.setPreferredWidth(150);
            }else if(i==25){
                column.setPreferredWidth(150);
            }else if(i==26){
                column.setPreferredWidth(150);
            }else if(i==27){
                column.setPreferredWidth(150);
            }else if(i==28){
                column.setPreferredWidth(150);
            }else if(i==29){
                column.setPreferredWidth(150);
            }else if(i==30){
                column.setPreferredWidth(150);
            }else if(i==31){
                column.setPreferredWidth(150);
            }else if(i==32){
                column.setPreferredWidth(150);
            }else if(i==33){
                column.setPreferredWidth(150);
            }else if(i==34){
                column.setPreferredWidth(150);
            }else if(i==35){
                column.setPreferredWidth(150);
            }else if(i==36){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));

        DiagnosaDokter.setDocument(new batasInput((int)2000).getKata(DiagnosaDokter));
        KelPsiko.setDocument(new batasInput((int)2000).getKata(KelPsiko));
        KelFisik.setDocument(new batasInput((int)1000).getKata(KelFisik));
        IntervensiPsikologi.setDocument(new batasInput((int)1000).getKata(IntervensiPsikologi));
//        Diagnosis.setDocument(new batasInput((int)300).getKata(Diagnosis));
//        Konsul.setDocument(new batasInput((int)500).getKata(Konsul));
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
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
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
        jLabel9 = new widget.Label();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        DiagnosaDokter = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        KelFisik = new widget.TextArea();
        jLabel32 = new widget.Label();
        jLabel94 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel33 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        KelPsiko = new widget.TextArea();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel99 = new widget.Label();
        jLabel100 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        IntervensiPsikologi = new widget.TextArea();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel105 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel67 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        scrollPane8 = new widget.ScrollPane();
        DinamikaPsikologi = new widget.TextArea();
        scrollPane34 = new widget.ScrollPane();
        DeskripsiLainnya = new widget.TextArea();
        scrollPane39 = new widget.ScrollPane();
        FungsiLainnya = new widget.TextArea();
        Tes5 = new widget.TextBox();
        scrollPane41 = new widget.ScrollPane();
        HasilTes = new widget.TextArea();
        jLabel91 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        jLabel108 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        MemoriPlus = new widget.RadioButton();
        MemoriMinus = new widget.RadioButton();
        KonsentrasiPlus = new widget.RadioButton();
        KonsentrasiMinus = new widget.RadioButton();
        OrientasiPlus = new widget.RadioButton();
        VerbalPlus = new widget.RadioButton();
        OrientasiMinus = new widget.RadioButton();
        VerbalMinus = new widget.RadioButton();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        Tes1 = new widget.TextBox();
        Tes2 = new widget.TextBox();
        Tes3 = new widget.TextBox();
        Tes4 = new widget.TextBox();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        Cemas = new widget.CekBox();
        SakitKepala = new widget.CekBox();
        KurangNafsuMakan = new widget.CekBox();
        SulitTidur = new widget.CekBox();
        MudahTakut = new widget.CekBox();
        Tegang = new widget.CekBox();
        PutusAsa = new widget.CekBox();
        Gemetar = new widget.CekBox();
        GangguanPerut = new widget.CekBox();
        SulitMengambilKeputusan = new widget.CekBox();
        KehilanganMinat = new widget.CekBox();
        MudahLupa = new widget.CekBox();
        TidakPercayaDiri = new widget.CekBox();
        Sedih = new widget.CekBox();
        MudahMarah = new widget.CekBox();
        MudahTersinggung = new widget.CekBox();
        MimpiBuruk = new widget.CekBox();
        SulitKonsentrasi = new widget.CekBox();
        SimptomLainnya = new widget.CekBox();
        SimptomLainText = new widget.TextBox();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel123 = new widget.Label();
        jSeparator21 = new javax.swing.JSeparator();
        scrollPane10 = new widget.ScrollPane();
        DiagnosaPsikologi = new widget.TextArea();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel124 = new widget.Label();
        jSeparator23 = new javax.swing.JSeparator();
        scrollPane12 = new widget.ScrollPane();
        RencanaIntervensi = new widget.TextArea();
        PenampilanUmum = new widget.ComboBox();
        Persepsi = new widget.ComboBox();
        Sikap = new widget.ComboBox();
        Afek = new widget.ComboBox();
        RomanMuka = new widget.ComboBox();
        ProsesPikir = new widget.ComboBox();
        Perilaku = new widget.ComboBox();
        Emosi = new widget.ComboBox();
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
        MnPenilaianMedis.setText("Laporan Penilaian Medis");
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pemeriksaan Psikologi Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
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
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2000));
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
        label14.setBounds(0, 40, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        NmDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmDokterActionPerformed(evt);
            }
        });
        FormInput.add(NmDokter);
        NmDokter.setBounds(166, 40, 180, 23);

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
        BtnDokter.setBounds(348, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        jLabel9.setText("Penampilan Umum :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(40, 260, 140, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        DiagnosaDokter.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DiagnosaDokter.setColumns(20);
        DiagnosaDokter.setRows(5);
        DiagnosaDokter.setName("DiagnosaDokter"); // NOI18N
        DiagnosaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaDokterKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(DiagnosaDokter);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(129, 90, 310, 43);

        jLabel30.setText("Keluhan Psikologis :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(440, 90, 150, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        KelFisik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KelFisik.setColumns(20);
        KelFisik.setRows(5);
        KelFisik.setName("KelFisik"); // NOI18N
        KelFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelFisikKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(KelFisik);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(129, 140, 310, 43);

        jLabel32.setText("Keluhan Fisik :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 140, 120, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("VI. INTERVENSI PSIKOLOGI");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 1650, 180, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jLabel33.setText("Diagnosa Dokter :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 90, 125, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        KelPsiko.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KelPsiko.setColumns(20);
        KelPsiko.setRows(5);
        KelPsiko.setName("KelPsiko"); // NOI18N
        KelPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelPsikoKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(KelPsiko);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(594, 90, 260, 43);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 1250, 880, 1);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("II. ASSESMENT");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 200, 180, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("III. DINAMIKA PSIKOLOGI");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(10, 1250, 180, 23);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        IntervensiPsikologi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        IntervensiPsikologi.setColumns(20);
        IntervensiPsikologi.setRows(5);
        IntervensiPsikologi.setName("IntervensiPsikologi"); // NOI18N
        IntervensiPsikologi.setPreferredSize(new java.awt.Dimension(102, 52));
        IntervensiPsikologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IntervensiPsikologiKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(IntervensiPsikologi);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(30, 1680, 770, 90);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(380, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-10-2023 08:54:03" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(436, 40, 130, 23);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(0, 1650, 880, 1);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("I. RIWAYAT PENYAKIT PASIEN");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(10, 70, 180, 23);

        jLabel37.setText("Lainnya :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(20, 440, 160, 23);

        jLabel57.setText("Deskripsi Umum");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(20, 240, 120, 23);

        jLabel58.setText("Sikap Terhadap Pemeriksa :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(20, 290, 160, 23);

        jLabel59.setText("Gangguan Persepsi :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(20, 410, 160, 23);

        jLabel67.setText("5.");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 1000, 70, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 1250, 880, 1);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        FormInput.add(jSeparator19);
        jSeparator19.setBounds(0, 200, 880, 3);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        DinamikaPsikologi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DinamikaPsikologi.setColumns(20);
        DinamikaPsikologi.setRows(5);
        DinamikaPsikologi.setName("DinamikaPsikologi"); // NOI18N
        DinamikaPsikologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DinamikaPsikologiKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(DinamikaPsikologi);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(30, 1280, 770, 80);

        scrollPane34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane34.setName("scrollPane34"); // NOI18N

        DeskripsiLainnya.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DeskripsiLainnya.setColumns(20);
        DeskripsiLainnya.setRows(5);
        DeskripsiLainnya.setName("DeskripsiLainnya"); // NOI18N
        DeskripsiLainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DeskripsiLainnyaKeyPressed(evt);
            }
        });
        scrollPane34.setViewportView(DeskripsiLainnya);

        FormInput.add(scrollPane34);
        scrollPane34.setBounds(200, 450, 570, 60);

        scrollPane39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane39.setName("scrollPane39"); // NOI18N

        FungsiLainnya.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        FungsiLainnya.setColumns(20);
        FungsiLainnya.setRows(5);
        FungsiLainnya.setName("FungsiLainnya"); // NOI18N
        FungsiLainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsiLainnyaKeyPressed(evt);
            }
        });
        scrollPane39.setViewportView(FungsiLainnya);

        FormInput.add(scrollPane39);
        scrollPane39.setBounds(170, 770, 570, 60);

        Tes5.setFocusTraversalPolicyProvider(true);
        Tes5.setName("Tes5"); // NOI18N
        Tes5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tes5KeyPressed(evt);
            }
        });
        FormInput.add(Tes5);
        Tes5.setBounds(80, 1000, 260, 23);

        scrollPane41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane41.setName("scrollPane41"); // NOI18N

        HasilTes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HasilTes.setColumns(20);
        HasilTes.setRows(5);
        HasilTes.setName("HasilTes"); // NOI18N
        HasilTes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilTesKeyPressed(evt);
            }
        });
        scrollPane41.setViewportView(HasilTes);

        FormInput.add(scrollPane41);
        scrollPane41.setBounds(390, 900, 350, 120);

        jLabel91.setText("3. Simptom :");
        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 1050, 100, 23);

        jLabel106.setText("Afek :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(20, 320, 160, 23);

        jLabel107.setText("Roman Muka :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(20, 350, 160, 23);

        jLabel108.setText("Proses Pikir :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(20, 380, 160, 23);

        jLabel27.setText("Kognitif");
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(40, 550, 120, 23);

        jLabel79.setText("Fungsi Psikologi");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(20, 520, 120, 23);

        jLabel109.setText("Memori :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(20, 580, 140, 23);

        jLabel110.setText("Lainnya :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(10, 770, 150, 23);

        jLabel111.setText("Konsentrasi :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(20, 610, 140, 23);

        jLabel112.setText("Orientasi :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(20, 640, 140, 23);

        jLabel113.setText("Kemampuan Verbal :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(20, 670, 140, 23);

        buttonGroup7.add(MemoriPlus);
        MemoriPlus.setText("+");
        MemoriPlus.setName("MemoriPlus"); // NOI18N
        FormInput.add(MemoriPlus);
        MemoriPlus.setBounds(170, 580, 50, 17);

        buttonGroup7.add(MemoriMinus);
        MemoriMinus.setText("-");
        MemoriMinus.setName("MemoriMinus"); // NOI18N
        FormInput.add(MemoriMinus);
        MemoriMinus.setBounds(280, 580, 40, 17);

        buttonGroup8.add(KonsentrasiPlus);
        KonsentrasiPlus.setText("+");
        KonsentrasiPlus.setName("KonsentrasiPlus"); // NOI18N
        FormInput.add(KonsentrasiPlus);
        KonsentrasiPlus.setBounds(170, 610, 50, 17);

        buttonGroup8.add(KonsentrasiMinus);
        KonsentrasiMinus.setText("-");
        KonsentrasiMinus.setName("KonsentrasiMinus"); // NOI18N
        FormInput.add(KonsentrasiMinus);
        KonsentrasiMinus.setBounds(280, 610, 40, 17);

        buttonGroup9.add(OrientasiPlus);
        OrientasiPlus.setText("+");
        OrientasiPlus.setName("OrientasiPlus"); // NOI18N
        FormInput.add(OrientasiPlus);
        OrientasiPlus.setBounds(170, 640, 50, 17);

        buttonGroup10.add(VerbalPlus);
        VerbalPlus.setText("+");
        VerbalPlus.setName("VerbalPlus"); // NOI18N
        FormInput.add(VerbalPlus);
        VerbalPlus.setBounds(170, 670, 50, 17);

        buttonGroup9.add(OrientasiMinus);
        OrientasiMinus.setText("-");
        OrientasiMinus.setName("OrientasiMinus"); // NOI18N
        FormInput.add(OrientasiMinus);
        OrientasiMinus.setBounds(280, 640, 50, 17);

        buttonGroup10.add(VerbalMinus);
        VerbalMinus.setText("-");
        VerbalMinus.setName("VerbalMinus"); // NOI18N
        FormInput.add(VerbalMinus);
        VerbalMinus.setBounds(280, 670, 50, 17);

        jLabel114.setText("Emosi :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(20, 710, 140, 23);

        jLabel115.setText("Perilaku :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(20, 740, 140, 23);

        jLabel116.setText("1. Observasi :");
        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 220, 100, 23);

        jLabel117.setText("Hasil Tes :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(370, 880, 70, 23);

        jLabel118.setText("2.");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 910, 70, 23);

        jLabel119.setText("3.");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 940, 70, 23);

        jLabel120.setText("4.");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 970, 70, 23);

        Tes1.setFocusTraversalPolicyProvider(true);
        Tes1.setName("Tes1"); // NOI18N
        Tes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tes1KeyPressed(evt);
            }
        });
        FormInput.add(Tes1);
        Tes1.setBounds(80, 880, 260, 23);

        Tes2.setFocusTraversalPolicyProvider(true);
        Tes2.setName("Tes2"); // NOI18N
        Tes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tes2KeyPressed(evt);
            }
        });
        FormInput.add(Tes2);
        Tes2.setBounds(80, 910, 260, 23);

        Tes3.setFocusTraversalPolicyProvider(true);
        Tes3.setName("Tes3"); // NOI18N
        Tes3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tes3KeyPressed(evt);
            }
        });
        FormInput.add(Tes3);
        Tes3.setBounds(80, 940, 260, 23);

        Tes4.setFocusTraversalPolicyProvider(true);
        Tes4.setName("Tes4"); // NOI18N
        Tes4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tes4KeyPressed(evt);
            }
        });
        FormInput.add(Tes4);
        Tes4.setBounds(80, 970, 260, 23);

        jLabel121.setText("1.");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 880, 70, 23);

        jLabel122.setText("2. Psikotes Pendukung :");
        jLabel122.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 850, 160, 23);

        Cemas.setText("Cemas");
        Cemas.setName("Cemas"); // NOI18N
        FormInput.add(Cemas);
        Cemas.setBounds(210, 1080, 130, 17);

        SakitKepala.setText("Sakit Kepala");
        SakitKepala.setName("SakitKepala"); // NOI18N
        FormInput.add(SakitKepala);
        SakitKepala.setBounds(60, 1080, 140, 17);

        KurangNafsuMakan.setText("Kurang Nafsu Makan");
        KurangNafsuMakan.setName("KurangNafsuMakan"); // NOI18N
        FormInput.add(KurangNafsuMakan);
        KurangNafsuMakan.setBounds(60, 1110, 140, 17);

        SulitTidur.setText("Sulit Tidur");
        SulitTidur.setName("SulitTidur"); // NOI18N
        FormInput.add(SulitTidur);
        SulitTidur.setBounds(60, 1140, 130, 17);

        MudahTakut.setText("Mudah Takut");
        MudahTakut.setName("MudahTakut"); // NOI18N
        FormInput.add(MudahTakut);
        MudahTakut.setBounds(60, 1170, 130, 17);

        Tegang.setText("Tegang");
        Tegang.setName("Tegang"); // NOI18N
        FormInput.add(Tegang);
        Tegang.setBounds(60, 1200, 130, 17);

        PutusAsa.setText("Putus Asa");
        PutusAsa.setName("PutusAsa"); // NOI18N
        FormInput.add(PutusAsa);
        PutusAsa.setBounds(390, 1110, 130, 17);

        Gemetar.setText("Gemetar");
        Gemetar.setName("Gemetar"); // NOI18N
        FormInput.add(Gemetar);
        Gemetar.setBounds(210, 1110, 130, 17);

        GangguanPerut.setText("Gangguan Perut");
        GangguanPerut.setName("GangguanPerut"); // NOI18N
        FormInput.add(GangguanPerut);
        GangguanPerut.setBounds(210, 1140, 130, 17);

        SulitMengambilKeputusan.setText("Sulit mengambil kerputusan");
        SulitMengambilKeputusan.setName("SulitMengambilKeputusan"); // NOI18N
        FormInput.add(SulitMengambilKeputusan);
        SulitMengambilKeputusan.setBounds(210, 1170, 170, 17);

        KehilanganMinat.setText("Kehilangan minat");
        KehilanganMinat.setName("KehilanganMinat"); // NOI18N
        FormInput.add(KehilanganMinat);
        KehilanganMinat.setBounds(210, 1200, 130, 17);

        MudahLupa.setText("Mudah lupa");
        MudahLupa.setName("MudahLupa"); // NOI18N
        FormInput.add(MudahLupa);
        MudahLupa.setBounds(390, 1080, 130, 17);

        TidakPercayaDiri.setText("Tidak Percaya Diri");
        TidakPercayaDiri.setName("TidakPercayaDiri"); // NOI18N
        FormInput.add(TidakPercayaDiri);
        TidakPercayaDiri.setBounds(540, 1140, 130, 17);

        Sedih.setText("Sedih");
        Sedih.setName("Sedih"); // NOI18N
        FormInput.add(Sedih);
        Sedih.setBounds(390, 1170, 130, 17);

        MudahMarah.setText("Mudah marah");
        MudahMarah.setName("MudahMarah"); // NOI18N
        FormInput.add(MudahMarah);
        MudahMarah.setBounds(390, 1200, 130, 17);

        MudahTersinggung.setText("Mudah Tersinggung");
        MudahTersinggung.setName("MudahTersinggung"); // NOI18N
        FormInput.add(MudahTersinggung);
        MudahTersinggung.setBounds(540, 1080, 130, 17);

        MimpiBuruk.setText("Mimpi Buruk");
        MimpiBuruk.setName("MimpiBuruk"); // NOI18N
        FormInput.add(MimpiBuruk);
        MimpiBuruk.setBounds(540, 1110, 130, 17);

        SulitKonsentrasi.setText("Sulit Konsentrasi");
        SulitKonsentrasi.setName("SulitKonsentrasi"); // NOI18N
        FormInput.add(SulitKonsentrasi);
        SulitKonsentrasi.setBounds(390, 1140, 130, 17);

        SimptomLainnya.setText("Lainnya :");
        SimptomLainnya.setName("SimptomLainnya"); // NOI18N
        SimptomLainnya.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SimptomLainnyaItemStateChanged(evt);
            }
        });
        FormInput.add(SimptomLainnya);
        SimptomLainnya.setBounds(540, 1170, 80, 17);

        SimptomLainText.setEditable(false);
        SimptomLainText.setName("SimptomLainText"); // NOI18N
        FormInput.add(SimptomLainText);
        SimptomLainText.setBounds(560, 1190, 240, 24);

        jSeparator20.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator20.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator20.setName("jSeparator20"); // NOI18N
        FormInput.add(jSeparator20);
        jSeparator20.setBounds(0, 1380, 880, 1);

        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("IV. DIAGNOSA PSIKOLOGI");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(10, 1380, 180, 23);

        jSeparator21.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator21.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator21.setName("jSeparator21"); // NOI18N
        FormInput.add(jSeparator21);
        jSeparator21.setBounds(0, 1380, 880, 1);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        DiagnosaPsikologi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DiagnosaPsikologi.setColumns(20);
        DiagnosaPsikologi.setRows(5);
        DiagnosaPsikologi.setName("DiagnosaPsikologi"); // NOI18N
        DiagnosaPsikologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaPsikologiKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(DiagnosaPsikologi);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(30, 1410, 770, 80);

        jSeparator22.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator22.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator22.setName("jSeparator22"); // NOI18N
        FormInput.add(jSeparator22);
        jSeparator22.setBounds(0, 1520, 880, 1);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("V. RENCANA INTERVENSI / DIRUJUK KE-");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(10, 1520, 220, 23);

        jSeparator23.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator23.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator23.setName("jSeparator23"); // NOI18N
        FormInput.add(jSeparator23);
        jSeparator23.setBounds(0, 1520, 880, 1);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        RencanaIntervensi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RencanaIntervensi.setColumns(20);
        RencanaIntervensi.setRows(5);
        RencanaIntervensi.setName("RencanaIntervensi"); // NOI18N
        RencanaIntervensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaIntervensiKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(RencanaIntervensi);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(30, 1550, 770, 80);

        PenampilanUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terawat", "Kurang Terawat" }));
        PenampilanUmum.setName("PenampilanUmum"); // NOI18N
        FormInput.add(PenampilanUmum);
        PenampilanUmum.setBounds(200, 260, 109, 20);

        Persepsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Halusinasi", "Delusi", "Tidak Ada" }));
        Persepsi.setName("Persepsi"); // NOI18N
        FormInput.add(Persepsi);
        Persepsi.setBounds(200, 410, 109, 20);

        Sikap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kooperatif", "Kurang Kooperatif" }));
        Sikap.setName("Sikap"); // NOI18N
        FormInput.add(Sikap);
        Sikap.setBounds(200, 290, 119, 20);

        Afek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Datar", "Depresif" }));
        Afek.setName("Afek"); // NOI18N
        FormInput.add(Afek);
        Afek.setBounds(200, 320, 109, 20);

        RomanMuka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Murung", "Wajar", "Euphoria" }));
        RomanMuka.setName("RomanMuka"); // NOI18N
        FormInput.add(RomanMuka);
        RomanMuka.setBounds(200, 350, 109, 20);

        ProsesPikir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Realistik", "Tidak Realistik" }));
        ProsesPikir.setName("ProsesPikir"); // NOI18N
        FormInput.add(ProsesPikir);
        ProsesPikir.setBounds(200, 380, 109, 20);

        Perilaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada Hambatan", "Normal", "Agresif", "Menarik Diri" }));
        Perilaku.setName("Perilaku"); // NOI18N
        FormInput.add(Perilaku);
        Perilaku.setBounds(170, 740, 150, 20);

        Emosi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Stabil", "Tidak Stabil" }));
        Emosi.setName("Emosi"); // NOI18N
        FormInput.add(Emosi);
        Emosi.setBounds(170, 710, 72, 20);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-10-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-10-2023" }));
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
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Penilaian Awal Medis Rawat Jalan Umum ]::");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String memori = " ";
        String konsentrasi = " ";
        String orientasi = " ";
        String verbal = " ";
        String simptom = " ";
        
//        Memori
        if (MemoriPlus.isSelected()) {
            memori = "+";
        } else if (MemoriMinus.isSelected()) {
            memori = "-";
        }
        
//        Konsentrasi
        if (KonsentrasiPlus.isSelected()) {
            konsentrasi = "+";
        } else if (KonsentrasiMinus.isSelected()) {
            konsentrasi = "-";
        }
        
//        Orientasi
        if (OrientasiPlus.isSelected()) {
            orientasi = "+";
        } else if (OrientasiMinus.isSelected()) {
            orientasi = "-";
        }
        
//        Kemampuan Verbal
        if (VerbalPlus.isSelected()) {
            verbal = "+";
        } else if (VerbalMinus.isSelected()) {
            verbal = "-";
        }
        
//        Simptom
        if (SakitKepala.isSelected()) {
            simptom += "Sakit Kepala, ";
        }
        if (KurangNafsuMakan.isSelected()) {
            simptom += "Kurang Nafsu Makan, ";
        }
        if (SulitTidur.isSelected()) {
            simptom += "Sulit Tidur, ";
        }
        if (MudahTakut.isSelected()) {
            simptom += "Mudah Takut, ";
        }
        if (Tegang.isSelected()) {
            simptom += "Tegang, ";
        }
        if (Cemas.isSelected()) {
            simptom += "Cemas, ";
        }
        if (Gemetar.isSelected()) {
            simptom += "Gemetar, ";
        }
        if (GangguanPerut.isSelected()) {
            simptom += "Gangguan Perut, ";
        }
        if (SulitMengambilKeputusan.isSelected()) {
            simptom += "Sulit Mengambil Keputusan, ";
        }
        if (KehilanganMinat.isSelected()) {
            simptom += "Kehilangan Minat, ";
        }
        if (MudahLupa.isSelected()) {
            simptom += "Mudah Lupa, ";
        }
        if (PutusAsa.isSelected()) {
            simptom += "Putus Asa, ";
        }
        if (SulitKonsentrasi.isSelected()) {
            simptom += "Sulit Konsentrasi, ";
        }
        if (Sedih.isSelected()) {
            simptom += "Sedih, ";
        }
        if (MudahMarah.isSelected()) {
            simptom += "Mudah Marah, ";
        }
        if (MudahTersinggung.isSelected()) {
            simptom += "Mudah Tersinggung, ";
        }
        if (MimpiBuruk.isSelected()) {
            simptom += "Mimpi Buruk, ";
        }
        if (TidakPercayaDiri.isSelected()) {
            simptom += "Tidak Percaya Diri, ";
        }
        if (SimptomLainnya.isSelected()) {
            simptom += "Lainnya, ";
        }
        if (simptom.endsWith(", ")) {
            simptom = simptom.substring(0, simptom.length() - 2);
        }

        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(DiagnosaDokter.getText().trim().equals("")){
            Valid.textKosong(DiagnosaDokter,"Keluhan Utama");
        }else if(KelPsiko.getText().trim().equals("")){
            Valid.textKosong(KelPsiko,"Riwayat Penyakit Sekarang");
        }else{
            if(Sequel.menyimpantf("penilaian_psikologi_dewasa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",32,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),DiagnosaDokter.getText(),KelPsiko.getText(),KelFisik.getText(),
                    PenampilanUmum.getSelectedItem().toString(),Sikap.getSelectedItem().toString(),Afek.getSelectedItem().toString(),RomanMuka.getSelectedItem().toString(),ProsesPikir.getSelectedItem().toString(),Persepsi.getSelectedItem().toString(),DeskripsiLainnya.getText(),
                    memori,konsentrasi,orientasi,verbal,Emosi.getSelectedItem().toString(),Perilaku.getSelectedItem().toString(),FungsiLainnya.getText(),
                    Tes1.getText(),Tes2.getText(),Tes3.getText(),Tes4.getText(),Tes5.getText(),HasilTes.getText(),
                    simptom,SimptomLainText.getText(),
                    DinamikaPsikologi.getText(),
                    DiagnosaPsikologi.getText(),
                    RencanaIntervensi.getText(),
                    IntervensiPsikologi.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,KetFisik,BtnBatal);
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
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(DiagnosaDokter.getText().trim().equals("")){
            Valid.textKosong(DiagnosaDokter,"Keluhan Utama");
        }else if(KelPsiko.getText().trim().equals("")){
            Valid.textKosong(KelPsiko,"Riwayat Penyakit Sekarang");
        }else if(KelFisik.getText().trim().equals("")){
            Valid.textKosong(KelFisik,"Riwayat Penyakit Keluarga");
//        }else if(RPD.getText().trim().equals("")){
//            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
//        }else if(RPO.getText().trim().equals("")){
//            Valid.textKosong(RPO,"Riwayat Pengunaan obat");
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
                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+                    
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
                bg.close();

                File f;            
                BufferedWriter bw; 
                
                if(TCari.getText().trim().equals("")){
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_psikologi_dewasa.tanggal,"+
                            "penilaian_psikologi_dewasa.kd_dokter,dokter.nm_dokter,penilaian_psikologi_dewasa.diagnosa,penilaian_psikologi_dewasa.keluhan_fisik,penilaian_psikologi_dewasa.keluhan_psiko,penilaian_psikologi_dewasa.penampil_umum,penilaian_psikologi_dewasa.sikap_pasien,penilaian_psikologi_dewasa.afek,penilaian_psikologi_dewasa.roman_muka,penilaian_psikologi_dewasa.pikir,"+
                            "penilaian_psikologi_dewasa.persepsi,penilaian_psikologi_dewasa.deskripsi_lainnya,penilaian_psikologi_dewasa.memori,penilaian_psikologi_dewasa.konsentrasi,penilaian_psikologi_dewasa.orientasi,penilaian_psikologi_dewasa.verbal,penilaian_psikologi_dewasa.emosi,penilaian_psikologi_dewasa.perilaku,penilaian_psikologi_dewasa.fungsi_lainnya,penilaian_psikologi_dewasa.psikotes1,penilaian_psikologi_dewasa.psikotes2,penilaian_psikologi_dewasa.psikotes3,penilaian_psikologi_dewasa.psikotes4,penilaian_psikologi_dewasa.psikotes5,penilaian_psikologi_dewasa.hasil_psikotes,penilaian_psikologi_dewasa.simptom,penilaian_psikologi_dewasa.simptom_lainnya,"+
                            "penilaian_psikologi_dewasa.dinamika_psikologi,penilaian_psikologi_dewasa.diagnosa_psikologi,penilaian_psikologi_dewasa.rencana_intervensi,penilaian_psikologi_dewasa.intervensi_psikologi "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_medis_ralan_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ralan_psikiatrik_anak.no_rawat "+
                            "inner join dokter on penilaian_medis_ralan_psikiatrik_anak.kd_dokter=dokter.kd_dokter where "+
                            "penilaian_medis_ralan_psikiatrik_anak.tanggal between ? and ? order by penilaian_medis_ralan_psikiatrik_anak.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_psikologi_dewasa.tanggal,"+
                            "penilaian_psikologi_dewasa.kd_dokter,dokter.nm_dokter,penilaian_psikologi_dewasa.diagnosa,penilaian_psikologi_dewasa.keluhan_fisik,penilaian_psikologi_dewasa.keluhan_psiko,penilaian_psikologi_dewasa.penampil_umum,penilaian_psikologi_dewasa.sikap_pasien,penilaian_psikologi_dewasa.afek,penilaian_psikologi_dewasa.roman_muka,penilaian_psikologi_dewasa.pikir,"+
                            "penilaian_psikologi_dewasa.persepsi,penilaian_psikologi_dewasa.deskripsi_lainnya,penilaian_psikologi_dewasa.memori,penilaian_psikologi_dewasa.konsentrasi,penilaian_psikologi_dewasa.orientasi,penilaian_psikologi_dewasa.verbal,penilaian_psikologi_dewasa.emosi,penilaian_psikologi_dewasa.perilaku,penilaian_psikologi_dewasa.fungsi_lainnya,penilaian_psikologi_dewasa.psikotes1,penilaian_psikologi_dewasa.psikotes2,penilaian_psikologi_dewasa.psikotes3,penilaian_psikologi_dewasa.psikotes4,penilaian_psikologi_dewasa.psikotes5,penilaian_psikologi_dewasa.hasil_psikotes,penilaian_psikologi_dewasa.simptom,penilaian_psikologi_dewasa.simptom_lainnya,"+
                            "penilaian_psikologi_dewasa.dinamika_psikologi,penilaian_psikologi_dewasa.diagnosa_psikologi,penilaian_psikologi_dewasa.rencana_intervensi,penilaian_psikologi_dewasa.intervensi_psikologi "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_medis_ralan_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ralan_psikiatrik_anak.no_rawat "+
                            "inner join dokter on penilaian_medis_ralan_psikiatrik_anak.kd_dokter=dokter.kd_dokter where "+
                            "penilaian_medis_ralan_psikiatrik_anak.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                            "penilaian_medis_ralan_psikiatrik_anak.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ralan_psikiatrik_anak.tanggal");
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
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih laporan..!","Pilihan Cetak",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Laporan 1 (HTML)","Laporan 2 (WPS)","Laporan 3 (CSV)"},"Laporan 1 (HTML)");
                    switch (pilihan) {
                        case "Laporan 1 (HTML)":
                                htmlContent = new StringBuilder();
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>No.Rawat</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>No.RM</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pasien</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tgl.Lahir</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>J.K.</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kode Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Diagnosa Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keluhan Fisik</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keluhan Psikologis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penampilan Umum</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sikap Terhadap Pemeriksa</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Afek</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Roman Muka</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Proses Pikir</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Persepsi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Deskripsi Lainnya</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Memori</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Konsentrasi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Orientasi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kemampuan Verbal</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Emosi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perilaku</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fungsi Lainnya</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 1</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 2</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 3</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 4</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 5</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Hasil Psikotes</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Simptom</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Simptom Lainnya</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Dinamika Psikologi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Diagnosa Psikologi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Rencana Intervensi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Intervensi Psikologi</td>"+
                                        
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
                                           "<td valign='top'>"+rs.getString("diagnosa")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keluhan_fisik")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keluhan_psiko")+"</td>"+
                                           "<td valign='top'>"+rs.getString("penampil_umum")+"</td>"+
                                           "<td valign='top'>"+rs.getString("sikap_pasien")+"</td>"+
                                           "<td valign='top'>"+rs.getString("afek")+"</td>"+
                                           "<td valign='top'>"+rs.getString("roman_muka")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pikir")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("persepsi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("deskripsi_lainnya")+"</td>"+
                                           "<td valign='top'>"+rs.getString("memori")+"</td>"+
                                           "<td valign='top'>"+rs.getString("konsentrasi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("orientasi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("verbal")+"</td>"+
                                           "<td valign='top'>"+rs.getString("emosi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("perilaku")+"</td>"+
                                           "<td valign='top'>"+rs.getString("fungsi_lainnya")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes1")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes2")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes3")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes4")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes5")+"</td>"+
                                           "<td valign='top'>"+rs.getString("hasil_psikotes")+"</td>"+
                                           "<td valign='top'>"+rs.getString("simptom")+"</td>"+
                                           "<td valign='top'>"+rs.getString("simptom_lainnya")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("dinamika_psikologi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("diagnosa_psikologi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rencana_intervensi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("intervensi_psikologi")+"</td>"+
                                        "</tr>");
                                }
                                f = new File("PenilaianAwalMedisRalanPsikiatrik.html");            
                                bw = new BufferedWriter(new FileWriter(f));            
                                bw.write("<html>"+
                                            "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                            "<body>"+
                                                "<table width='4600px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                    htmlContent.toString()+
                                                "</table>"+
                                            "</body>"+                   
                                         "</html>"
                                );

                                bw.close();                         
                                Desktop.getDesktop().browse(f.toURI());
                            break;
                        case "Laporan 2 (WPS)":
                                htmlContent = new StringBuilder();
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>No.Rawat</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>No.RM</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Pasien</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tgl.Lahir</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>J.K.</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kode Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nama Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Diagnosa Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keluhan Fisik</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keluhan Psikologis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penampilan Umum</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sikap Terhadap Pemeriksa</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Afek</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Roman Muka</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Proses Pikir</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Persepsi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Deskripsi Lainnya</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Memori</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Konsentrasi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Orientasi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kemampuan Verbal</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Emosi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perilaku</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fungsi Lainnya</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 1</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 2</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 3</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 4</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikotes 5</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Hasil Psikotes</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Simptom</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Simptom Lainnya</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Dinamika Psikologi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Diagnosa Psikologi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Rencana Intervensi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Intervensi Psikologi</td>"+
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
                                           "<td valign='top'>"+rs.getString("diagnosa")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keluhan_fisik")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keluhan_psiko")+"</td>"+
                                           "<td valign='top'>"+rs.getString("penampil_umum")+"</td>"+
                                           "<td valign='top'>"+rs.getString("sikap_pasien")+"</td>"+
                                           "<td valign='top'>"+rs.getString("afek")+"</td>"+
                                           "<td valign='top'>"+rs.getString("roman_muka")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pikir")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("persepsi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("deskripsi_lainnya")+"</td>"+
                                           "<td valign='top'>"+rs.getString("memori")+"</td>"+
                                           "<td valign='top'>"+rs.getString("konsentrasi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("orientasi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("verbal")+"</td>"+
                                           "<td valign='top'>"+rs.getString("emosi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("perilaku")+"</td>"+
                                           "<td valign='top'>"+rs.getString("fungsi_lainnya")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes1")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes2")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes3")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes4")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikotes5")+"</td>"+
                                           "<td valign='top'>"+rs.getString("hasil_psikotes")+"</td>"+
                                           "<td valign='top'>"+rs.getString("simptom")+"</td>"+
                                           "<td valign='top'>"+rs.getString("simptom_lainnya")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("dinamika_psikologi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("diagnosa_psikologi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rencana_intervensi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("intervensi_psikologi")+"</td>"+
                                        "</tr>");
                                }
                                f = new File("PenilaianAwalMedisRalanPsikiatrik.wps");            
                                bw = new BufferedWriter(new FileWriter(f));            
                                bw.write("<html>"+
                                            "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                            "<body>"+
                                                "<table width='4600px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                    htmlContent.toString()+
                                                "</table>"+
                                            "</body>"+                   
                                         "</html>"
                                );

                                bw.close();                         
                                Desktop.getDesktop().browse(f.toURI());
                            break;
                        case "Laporan 3 (CSV)":
                                htmlContent = new StringBuilder();
                                htmlContent.append(                             
                                    "\"No.Rawat\";\"No.RM\";\"Nama Pasien\";\"Tgl.Lahir\";\"J.K.\";\"Kode Dokter\";\"Nama Dokter\";\"Tanggal\";\"Anamnesis\";\"Hubungan\";\"Keluhan Utama\";\"Riwayat Penyakit Sekarang\";\"Riwayat Penyakit Dahulu\";\"Riwayat Penyakit Keluarga\";\"Riwayat Napza\";\"Riwayat Alergi\";\"Penampilan\";\"Pembicaraan\";\"Psikomotor\";\"Sikap\";\"Mood\";\"Fungsi Kognitif\";\"Gangguan Persepsi\";\"Proses Pikir\";\"Pengendalian Impuls\";\"Tilikan\";\"Reality Testing Ability\";\"Keadaan Umum\";\"GCS\";\"Kesadaran\";\"TD(mmHg)\";\"Nadi(x/menit)\";\"RR(x/menit)\";\"Suhu\";\"SpO2\";\"BB(Kg)\";\"TB(cm)\";\"Kepala\";\"Gigi & Mulut\";\"THT\";\"Thoraks\";\"Abdomen\";\"Genital & Anus\";\"Ekstremitas\";\"Kulit\";\"Keterangan Pemeriksaan Fisik\";\"Pemeriksaan Penunjang\";\"Diagnosis/Asesmen\";\"Konsul/Rujuk\"\n"
                                ); 
                                while(rs.next()){
                                    htmlContent.append(
                                        "\""+rs.getString("no_rawat")+"\";\" "+rs.getString("no_rkm_medis")+"\";\""+rs.getString("nm_pasien")+"\";\""+rs.getString("tgl_lahir")+"\";\""+rs.getString("jk")+"\";\""+rs.getString("kd_dokter")+"\";\""+rs.getString("nm_dokter")+"\";\""+rs.getString("tanggal")+"\";\""+rs.getString("anamnesis")+"\";\""+rs.getString("hubungan")+"\";\""+rs.getString("keluhan_utama")+"\";\""+rs.getString("rps")+"\";\""+rs.getString("rpd")+"\";\""+rs.getString("rpk")+"\";\""+rs.getString("rpo")+"\";\""+rs.getString("alergi")+"\";\""+rs.getString("penampilan")+"\";\""+rs.getString("pembicaraan")+"\";\""+rs.getString("psikomotor")+"\";\""+rs.getString("sikap")+"\";\""+rs.getString("mood")+"\";\""+rs.getString("fungsi_kognitif")+"\";\""+rs.getString("gangguan_persepsi")+"\";\""+rs.getString("proses_pikir")+"\";\""+rs.getString("pengendalian_impuls")+"\";\""+rs.getString("tilikan")+"\";\""+rs.getString("rta")+"\";\""+rs.getString("keadaan")+"\";\""+rs.getString("gcs")+"\";\""+rs.getString("kesadaran")+"\";\""+rs.getString("td")+"\";\""+rs.getString("nadi")+"\";\""+rs.getString("rr")+"\";\""+rs.getString("suhu")+"\";\""+rs.getString("spo")+"\";\""+rs.getString("bb")+"\";\""+rs.getString("tb")+"\";\""+rs.getString("kepala")+"\";\""+rs.getString("gigi")+"\";\""+rs.getString("tht")+"\";\""+rs.getString("thoraks")+"\";\""+rs.getString("abdomen")+"\";\""+rs.getString("genital")+"\";\""+rs.getString("ekstremitas")+"\";\""+rs.getString("kulit")+"\";\""+rs.getString("ket_fisik")+"\";\""+rs.getString("penunjang")+"\";\""+rs.getString("diagnosis")+"\";\""+rs.getString("tata")+"\";\""+rs.getString("konsulrujuk")+"\"\n"
                                    );
                                }
                                f = new File("PenilaianAwalMedisRalanPsikiatrik.csv");            
                                bw = new BufferedWriter(new FileWriter(f));            
                                bw.write(htmlContent.toString());

                                bw.close();                         
                                Desktop.getDesktop().browse(f.toURI());
                            break; 
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

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

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
            
            Valid.MyReportqry("rptCetakPenilaianPsikologiDewasa2.jasper","report","::[ Laporan Penilaian Psikologi Dewasa Hal 2 ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_psikologi_dewasa.tanggal,"+
                        "penilaian_psikologi_dewasa.kd_dokter,dokter.nm_dokter,penilaian_psikologi_dewasa.diagnosa,penilaian_psikologi_dewasa.keluhan_fisik,penilaian_psikologi_dewasa.keluhan_psiko,penilaian_psikologi_dewasa.penampil_umum,penilaian_psikologi_dewasa.sikap_pasien,penilaian_psikologi_dewasa.afek,penilaian_psikologi_dewasa.roman_muka,penilaian_psikologi_dewasa.pikir,"+
                        "penilaian_psikologi_dewasa.persepsi,penilaian_psikologi_dewasa.deskripsi_lainnya,penilaian_psikologi_dewasa.memori,penilaian_psikologi_dewasa.konsentrasi,penilaian_psikologi_dewasa.orientasi,penilaian_psikologi_dewasa.verbal,penilaian_psikologi_dewasa.emosi,penilaian_psikologi_dewasa.perilaku,penilaian_psikologi_dewasa.fungsi_lainnya,penilaian_psikologi_dewasa.psikotes1,penilaian_psikologi_dewasa.psikotes2,penilaian_psikologi_dewasa.psikotes3,penilaian_psikologi_dewasa.psikotes4,penilaian_psikologi_dewasa.psikotes5,penilaian_psikologi_dewasa.hasil_psikotes,penilaian_psikologi_dewasa.simptom,penilaian_psikologi_dewasa.simptom_lainnya,"+
                        "penilaian_psikologi_dewasa.dinamika_psikologi,penilaian_psikologi_dewasa.diagnosa_psikologi,penilaian_psikologi_dewasa.rencana_intervensi,penilaian_psikologi_dewasa.intervensi_psikologi "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_psikologi_dewasa on reg_periksa.no_rawat=penilaian_psikologi_dewasa.no_rawat "+
                        "inner join dokter on penilaian_psikologi_dewasa.kd_dokter=dokter.kd_dokter "+
                        "where penilaian_psikologi_dewasa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
            
            Valid.MyReportqry("rptCetakPenilaianPsikologiDewasa.jasper","report","::[ Laporan Penilaian Psikologi Dewasa Hal 1 ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_psikologi_dewasa.tanggal,"+
                        "penilaian_psikologi_dewasa.kd_dokter,dokter.nm_dokter,penilaian_psikologi_dewasa.diagnosa,penilaian_psikologi_dewasa.keluhan_fisik,penilaian_psikologi_dewasa.keluhan_psiko,penilaian_psikologi_dewasa.penampil_umum,penilaian_psikologi_dewasa.sikap_pasien,penilaian_psikologi_dewasa.afek,penilaian_psikologi_dewasa.roman_muka,penilaian_psikologi_dewasa.pikir,"+
                        "penilaian_psikologi_dewasa.persepsi,penilaian_psikologi_dewasa.deskripsi_lainnya,penilaian_psikologi_dewasa.memori,penilaian_psikologi_dewasa.konsentrasi,penilaian_psikologi_dewasa.orientasi,penilaian_psikologi_dewasa.verbal,penilaian_psikologi_dewasa.emosi,penilaian_psikologi_dewasa.perilaku,penilaian_psikologi_dewasa.fungsi_lainnya,penilaian_psikologi_dewasa.psikotes1,penilaian_psikologi_dewasa.psikotes2,penilaian_psikologi_dewasa.psikotes3,penilaian_psikologi_dewasa.psikotes4,penilaian_psikologi_dewasa.psikotes5,penilaian_psikologi_dewasa.hasil_psikotes,penilaian_psikologi_dewasa.simptom,penilaian_psikologi_dewasa.simptom_lainnya,"+
                        "penilaian_psikologi_dewasa.dinamika_psikologi,penilaian_psikologi_dewasa.diagnosa_psikologi,penilaian_psikologi_dewasa.rencana_intervensi,penilaian_psikologi_dewasa.intervensi_psikologi "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_psikologi_dewasa on reg_periksa.no_rawat=penilaian_psikologi_dewasa.no_rawat "+
                        "inner join dokter on penilaian_psikologi_dewasa.kd_dokter=dokter.kd_dokter "+
                        "where penilaian_psikologi_dewasa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void HasilTesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilTesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HasilTesKeyPressed

    private void Tes5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tes5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tes5KeyPressed

    private void FungsiLainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FungsiLainnyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FungsiLainnyaKeyPressed

    private void DeskripsiLainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DeskripsiLainnyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeskripsiLainnyaKeyPressed

    private void DinamikaPsikologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DinamikaPsikologiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DinamikaPsikologiKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        //        Valid.pindah(evt,Konsul,Anamnesis);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void IntervensiPsikologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IntervensiPsikologiKeyPressed
        //        Valid.pindah2(evt,KetFisik,Diagnosis);
    }//GEN-LAST:event_IntervensiPsikologiKeyPressed

    private void KelPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelPsikoKeyPressed
        Valid.pindah2(evt,DiagnosaDokter,KelFisik);
    }//GEN-LAST:event_KelPsikoKeyPressed

    private void KelFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelFisikKeyPressed
//        Valid.pindah2(evt,KelPsiko,RPD);
    }//GEN-LAST:event_KelFisikKeyPressed

    private void DiagnosaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaDokterKeyPressed
//        Valid.pindah2(evt,Hubungan,KelPsiko);
    }//GEN-LAST:event_DiagnosaDokterKeyPressed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void NmDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmDokterActionPerformed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed

    }//GEN-LAST:event_KdDokterKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{
            Valid.pindah(evt,TCari,BtnDokter);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void Tes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tes1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tes1KeyPressed

    private void Tes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tes2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tes2KeyPressed

    private void Tes3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tes3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tes3KeyPressed

    private void Tes4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tes4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tes4KeyPressed

    private void DiagnosaPsikologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaPsikologiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaPsikologiKeyPressed

    private void RencanaIntervensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaIntervensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RencanaIntervensiKeyPressed

    private void SimptomLainnyaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SimptomLainnyaItemStateChanged
        // TODO add your handling code here:
        if (SimptomLainnya.isSelected()==true){
            SimptomLainText.setEditable(true);
        } else {
            SimptomLainText.setEditable(false);
            SimptomLainText.setText(" ");
        }
    }//GEN-LAST:event_SimptomLainnyaItemStateChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianPsikologiDewasa dialog = new RMPenilaianPsikologiDewasa(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Afek;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox Cemas;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DeskripsiLainnya;
    private widget.TextArea DiagnosaDokter;
    private widget.TextArea DiagnosaPsikologi;
    private widget.TextArea DinamikaPsikologi;
    private widget.ComboBox Emosi;
    private widget.PanelBiasa FormInput;
    private widget.TextArea FungsiLainnya;
    private widget.CekBox GangguanPerut;
    private widget.CekBox Gemetar;
    private widget.TextArea HasilTes;
    private widget.TextArea IntervensiPsikologi;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.CekBox KehilanganMinat;
    private widget.TextArea KelFisik;
    private widget.TextArea KelPsiko;
    private widget.RadioButton KonsentrasiMinus;
    private widget.RadioButton KonsentrasiPlus;
    private widget.CekBox KurangNafsuMakan;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.RadioButton MemoriMinus;
    private widget.RadioButton MemoriPlus;
    private widget.CekBox MimpiBuruk;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.CekBox MudahLupa;
    private widget.CekBox MudahMarah;
    private widget.CekBox MudahTakut;
    private widget.CekBox MudahTersinggung;
    private widget.TextBox NmDokter;
    private widget.RadioButton OrientasiMinus;
    private widget.RadioButton OrientasiPlus;
    private widget.ComboBox PenampilanUmum;
    private widget.ComboBox Perilaku;
    private widget.ComboBox Persepsi;
    private widget.ComboBox ProsesPikir;
    private widget.CekBox PutusAsa;
    private widget.TextArea RencanaIntervensi;
    private widget.ComboBox RomanMuka;
    private widget.CekBox SakitKepala;
    private widget.ScrollPane Scroll;
    private widget.CekBox Sedih;
    private widget.ComboBox Sikap;
    private widget.TextBox SimptomLainText;
    private widget.CekBox SimptomLainnya;
    private widget.CekBox SulitKonsentrasi;
    private widget.CekBox SulitMengambilKeputusan;
    private widget.CekBox SulitTidur;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.CekBox Tegang;
    private widget.TextBox Tes1;
    private widget.TextBox Tes2;
    private widget.TextBox Tes3;
    private widget.TextBox Tes4;
    private widget.TextBox Tes5;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.CekBox TidakPercayaDiri;
    private widget.RadioButton VerbalMinus;
    private widget.RadioButton VerbalPlus;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel27;
    private widget.Label jLabel30;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel37;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel67;
    private widget.Label jLabel7;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel91;
    private widget.Label jLabel94;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane34;
    private widget.ScrollPane scrollPane39;
    private widget.ScrollPane scrollPane41;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_psikologi_dewasa.tanggal,"+
                        "penilaian_psikologi_dewasa.kd_dokter,dokter.nm_dokter,penilaian_psikologi_dewasa.diagnosa,penilaian_psikologi_dewasa.keluhan_fisik,penilaian_psikologi_dewasa.keluhan_psiko,penilaian_psikologi_dewasa.penampil_umum,penilaian_psikologi_dewasa.sikap_pasien,penilaian_psikologi_dewasa.afek,penilaian_psikologi_dewasa.roman_muka,penilaian_psikologi_dewasa.pikir,"+
                        "penilaian_psikologi_dewasa.persepsi,penilaian_psikologi_dewasa.deskripsi_lainnya,penilaian_psikologi_dewasa.memori,penilaian_psikologi_dewasa.konsentrasi,penilaian_psikologi_dewasa.orientasi,penilaian_psikologi_dewasa.verbal,penilaian_psikologi_dewasa.emosi,penilaian_psikologi_dewasa.perilaku,penilaian_psikologi_dewasa.fungsi_lainnya,penilaian_psikologi_dewasa.psikotes1,penilaian_psikologi_dewasa.psikotes2,penilaian_psikologi_dewasa.psikotes3,penilaian_psikologi_dewasa.psikotes4,penilaian_psikologi_dewasa.psikotes5,penilaian_psikologi_dewasa.hasil_psikotes,penilaian_psikologi_dewasa.simptom,penilaian_psikologi_dewasa.simptom_lainnya,"+
                        "penilaian_psikologi_dewasa.dinamika_psikologi,penilaian_psikologi_dewasa.diagnosa_psikologi,penilaian_psikologi_dewasa.rencana_intervensi,penilaian_psikologi_dewasa.intervensi_psikologi "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_psikologi_dewasa on reg_periksa.no_rawat=penilaian_psikologi_dewasa.no_rawat "+
                        "inner join dokter on penilaian_psikologi_dewasa.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_psikologi_dewasa.tanggal between ? and ? order by penilaian_psikologi_dewasa.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_psikologi_dewasa.tanggal,"+
                        "penilaian_psikologi_dewasa.kd_dokter,dokter.nm_dokter,penilaian_psikologi_dewasa.diagnosa,penilaian_psikologi_dewasa.keluhan_fisik,penilaian_psikologi_dewasa.keluhan_psiko,penilaian_psikologi_dewasa.penampil_umum,penilaian_psikologi_dewasa.sikap_pasien,penilaian_psikologi_dewasa.afek,penilaian_psikologi_dewasa.roman_muka,penilaian_psikologi_dewasa.pikir,"+
                        "penilaian_psikologi_dewasa.persepsi,penilaian_psikologi_dewasa.deskripsi_lainnya,penilaian_psikologi_dewasa.memori,penilaian_psikologi_dewasa.konsentrasi,penilaian_psikologi_dewasa.orientasi,penilaian_psikologi_dewasa.verbal,penilaian_psikologi_dewasa.emosi,penilaian_psikologi_dewasa.perilaku,penilaian_psikologi_dewasa.fungsi_lainnya,penilaian_psikologi_dewasa.psikotes1,penilaian_psikologi_dewasa.psikotes2,penilaian_psikologi_dewasa.psikotes3,penilaian_psikologi_dewasa.psikotes4,penilaian_psikologi_dewasa.psikotes5,penilaian_psikologi_dewasa.hasil_psikotes,penilaian_psikologi_dewasa.simptom,penilaian_psikologi_dewasa.simptom_lainnya,"+
                        "penilaian_psikologi_dewasa.dinamika_psikologi,penilaian_psikologi_dewasa.diagnosa_psikologi,penilaian_psikologi_dewasa.rencana_intervensi,penilaian_psikologi_dewasa.intervensi_psikologi "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_psikologi_dewasa on reg_periksa.no_rawat=penilaian_psikologi_dewasa.no_rawat "+
                        "inner join dokter on penilaian_psikologi_dewasa.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_psikologi_dewasa.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_psikologi_dewasa.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_psikologi_dewasa.tanggal");
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
                        rs.getString("diagnosa"),rs.getString("keluhan_fisik"),rs.getString("keluhan_psiko"),rs.getString("penampil_umum"),rs.getString("sikap_pasien"),rs.getString("afek"),rs.getString("roman_muka"),rs.getString("pikir"),
                        rs.getString("persepsi"),rs.getString("deskripsi_lainnya"),rs.getString("memori"),rs.getString("konsentrasi"),rs.getString("orientasi"),rs.getString("verbal"),rs.getString("emosi"),rs.getString("perilaku"),rs.getString("fungsi_lainnya"),rs.getString("psikotes1"),rs.getString("psikotes2"),rs.getString("psikotes3"),rs.getString("psikotes4"),rs.getString("psikotes5"),rs.getString("hasil_psikotes"),rs.getString("simptom"),rs.getString("simptom_lainnya"),
                        rs.getString("dinamika_psikologi"),rs.getString("diagnosa_psikologi"),rs.getString("rencana_intervensi"),rs.getString("intervensi_psikologi")
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
        
        DiagnosaDokter.setText("");
        KelPsiko.setText("");
        KelFisik.setText("");
        
        PenampilanUmum.setSelectedIndex(0);
        Sikap.setSelectedIndex(0);
        Afek.setSelectedIndex(0);
        RomanMuka.setSelectedIndex(0);
        ProsesPikir.setSelectedIndex(0);
        Persepsi.setSelectedIndex(0);
        DeskripsiLainnya.setText("");
        
        MemoriPlus.setSelected(false);
        MemoriMinus.setSelected(false);
        KonsentrasiPlus.setSelected(false);
        KonsentrasiMinus.setSelected(false);
        OrientasiPlus.setSelected(false);
        OrientasiMinus.setSelected(false);
        VerbalPlus.setSelected(false);
        VerbalMinus.setSelected(false);
        Emosi.setSelectedIndex(0);
        Perilaku.setSelectedIndex(0);
        FungsiLainnya.setText("");
        
        Tes1.setText("");
        Tes2.setText("");
        Tes3.setText("");
        Tes4.setText("");
        Tes5.setText("");
        HasilTes.setText("");
        
        SakitKepala.setSelected(false);
        KurangNafsuMakan.setSelected(false);
        SulitTidur.setSelected(false);
        MudahTakut.setSelected(false);
        Tegang.setSelected(false);
        Cemas.setSelected(false);
        Gemetar.setSelected(false);
        GangguanPerut.setSelected(false);
        SulitMengambilKeputusan.setSelected(false);
        KehilanganMinat.setSelected(false);
        MudahLupa.setSelected(false);
        PutusAsa.setSelected(false);
        SulitKonsentrasi.setSelected(false);
        Sedih.setSelected(false);
        MudahMarah.setSelected(false);
        MudahTersinggung.setSelected(false);
        MimpiBuruk.setSelected(false);
        TidakPercayaDiri.setSelected(false);
        SimptomLainnya.setSelected(false);
        SimptomLainText.setText(" ");
        
        DinamikaPsikologi.setText("");
        DiagnosaPsikologi.setText("");
        RencanaIntervensi.setText("");
        IntervensiPsikologi.setText("");
        
        TglAsuhan.setDate(new Date()); 
        TabRawat.setSelectedIndex(0);
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
            
            DiagnosaDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            KelFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KelPsiko.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            
            PenampilanUmum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            Sikap.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Afek.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            RomanMuka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            ProsesPikir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Persepsi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            DeskripsiLainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            
            String memori = tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString();
            if ("+".equals(memori)) {
                MemoriPlus.setSelected(true);
            } else if ("-".equals(memori)) {
                MemoriMinus.setSelected(true);
            }
            
            String konsentrasi = tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString();
            if ("+".equals(konsentrasi)) {
                KonsentrasiPlus.setSelected(true);
            } else if ("-".equals(konsentrasi)) {
                KonsentrasiMinus.setSelected(true);
            }
            
            String orientasi = tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString();
            if ("+".equals(orientasi)) {
                OrientasiPlus.setSelected(true);
            } else if ("-".equals(orientasi)) {
                OrientasiMinus.setSelected(true);
            }
            
            String verbal = tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString();
            if ("+".equals(verbal)) {
                VerbalPlus.setSelected(true);
            } else if ("-".equals(verbal)) {
                VerbalMinus.setSelected(true);
            }
            
            Emosi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            Perilaku.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            FungsiLainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            
            Tes1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            Tes2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Tes3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            Tes4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Tes5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            HasilTes.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            
            String simptom = tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString();
            if (simptom.contains("Sakit Kepala")) {
                SakitKepala.setSelected(true);
            } if (simptom.contains("Kurang Nafsu Makan")) {
                KurangNafsuMakan.setSelected(true);
            } if (simptom.contains("Sulit Tidur")) {
                SulitTidur.setSelected(true);
            } if (simptom.contains("Mudah Takut")) {
                MudahTakut.setSelected(true);
            } if (simptom.contains("Tegang")) {
                Tegang.setSelected(true);
            } if (simptom.contains("Cemas")) {
                Cemas.setSelected(true);
            } if (simptom.contains("Gangguan Perut")) {
                GangguanPerut.setSelected(true);
            } if (simptom.contains("Sulit Mengambil Keputusan")) {
                SulitMengambilKeputusan.setSelected(true);
            } if (simptom.contains("Kehilangan Minat")) {
                KehilanganMinat.setSelected(true);
            } if (simptom.contains("Mudah Lupa")) {
                MudahLupa.setSelected(true);
            } if (simptom.contains("Putus Asa")) {
                PutusAsa.setSelected(true);
            } if (simptom.contains("Sulit Konsentrasi")) {
                SulitKonsentrasi.setSelected(true);
            } if (simptom.contains("Sedih")) {
                Sedih.setSelected(true);
            } if (simptom.contains("Mudah Marah")) {
                MudahMarah.setSelected(true);
            } if (simptom.contains("Mudah Tersinggung")) {
                MudahTersinggung.setSelected(true);
            } if (simptom.contains("Mimpi Buruk")) {
                MimpiBuruk.setSelected(true);
            } if (simptom.contains("Tidak Percaya Diri")) {
                TidakPercayaDiri.setSelected(true);
            } if (simptom.contains("Lainnya")) {
                SimptomLainnya.setSelected(true);
            }
            
            SimptomLainText.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            DinamikaPsikologi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            DiagnosaPsikologi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            RencanaIntervensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            IntervensiPsikologi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
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
        BtnSimpan.setEnabled(akses.getpsikolog_dewasa()); 
        BtnHapus.setEnabled(akses.getpsikolog_dewasa()); 
        BtnEdit.setEnabled(akses.getpsikolog_dewasa());  
        BtnEdit.setEnabled(akses.getpsikolog_dewasa()); 
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
        if(Sequel.queryu2tf("delete from penilaian_psikologi_dewasa where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount()); 
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        String memori = " ";
        String konsentrasi = " ";
        String orientasi = " ";
        String verbal = " ";
        String simptom = " ";
        
//        Memori
        if (MemoriPlus.isSelected()) {
            memori = "+";
        } else if (MemoriMinus.isSelected()) {
            memori = "-";
        }
        
//        Konsentrasi
        if (KonsentrasiPlus.isSelected()) {
            konsentrasi = "+";
        } else if (KonsentrasiMinus.isSelected()) {
            konsentrasi = "-";
        }
        
//        Orientasi
        if (OrientasiPlus.isSelected()) {
            orientasi = "+";
        } else if (OrientasiMinus.isSelected()) {
            orientasi = "-";
        }
        
//        Kemampuan Verbal
        if (VerbalPlus.isSelected()) {
            verbal = "+";
        } else if (VerbalMinus.isSelected()) {
            verbal = "-";
        }
        
//        Simptom
        if (SakitKepala.isSelected()) {
            simptom += "Sakit Kepala, ";
        }
        if (KurangNafsuMakan.isSelected()) {
            simptom += "Kurang Nafsu Makan, ";
        }
        if (SulitTidur.isSelected()) {
            simptom += "Sulit Tidur, ";
        }
        if (MudahTakut.isSelected()) {
            simptom += "Mudah Takut, ";
        }
        if (Tegang.isSelected()) {
            simptom += "Tegang, ";
        }
        if (Cemas.isSelected()) {
            simptom += "Cemas, ";
        }
        if (Gemetar.isSelected()) {
            simptom += "Gemetar, ";
        }
        if (GangguanPerut.isSelected()) {
            simptom += "Gangguan Perut, ";
        }
        if (SulitMengambilKeputusan.isSelected()) {
            simptom += "Sulit Mengambil Keputusan, ";
        }
        if (KehilanganMinat.isSelected()) {
            simptom += "Kehilangan Minat, ";
        }
        if (MudahLupa.isSelected()) {
            simptom += "Mudah Lupa, ";
        }
        if (PutusAsa.isSelected()) {
            simptom += "Putus Asa, ";
        }
        if (SulitKonsentrasi.isSelected()) {
            simptom += "Sulit Konsentrasi, ";
        }
        if (Sedih.isSelected()) {
            simptom += "Sedih, ";
        }
        if (MudahMarah.isSelected()) {
            simptom += "Mudah Marah, ";
        }
        if (MudahTersinggung.isSelected()) {
            simptom += "Mudah Tersinggung, ";
        }
        if (MimpiBuruk.isSelected()) {
            simptom += "Mimpi Buruk, ";
        }
        if (TidakPercayaDiri.isSelected()) {
            simptom += "Tidak Percaya Diri, ";
        }
        if (SimptomLainnya.isSelected()) {
            simptom += "Lainnya, ";
        }
        if (simptom.endsWith(", ")) {
            simptom = simptom.substring(0, simptom.length() - 2);
        }
        
        if(Sequel.mengedittf("penilaian_psikologi_dewasa","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,diagnosa=?,keluhan_fisik=?,keluhan_psiko=?,penampil_umum=?,sikap_pasien=?,afek=?,roman_muka=?,"
                + "pikir=?,persepsi=?,deskripsi_lainnya=?,memori=?,konsentrasi=?,orientasi=?,verbal=?,emosi=?,perilaku=?,fungsi_lainnya=?,psikotes1=?,psikotes2=?,psikotes3=?,psikotes4=?,psikotes5=?,hasil_psikotes=?,simptom=?,"
                + "simptom_lainnya=?,dinamika_psikologi=?,diagnosa_psikologi=?,rencana_intervensi=?,intervensi_psikologi=?",33,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),DiagnosaDokter.getText(),KelFisik.getText(),KelPsiko.getText(),
                    PenampilanUmum.getSelectedItem().toString(),Sikap.getSelectedItem().toString(),Afek.getSelectedItem().toString(),RomanMuka.getSelectedItem().toString(),ProsesPikir.getSelectedItem().toString(),Persepsi.getSelectedItem().toString(),DeskripsiLainnya.getText(),
                    memori,konsentrasi,orientasi,verbal,Emosi.getSelectedItem().toString(),Perilaku.getSelectedItem().toString(),FungsiLainnya.getText(),
                    Tes1.getText(),Tes2.getText(),Tes3.getText(),Tes4.getText(),Tes5.getText(),HasilTes.getText(),
                    simptom,SimptomLainText.getText(),
                    DinamikaPsikologi.getText(),
                    DiagnosaPsikologi.getText(),
                    RencanaIntervensi.getText(),
                    IntervensiPsikologi.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
