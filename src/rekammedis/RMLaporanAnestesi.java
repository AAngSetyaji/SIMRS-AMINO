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


/**
 *
 * @author perpustakaan
 */
public final class RMLaporanAnestesi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
     private int i=0,pilihan=0; 
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMLaporanAnestesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir","Tanggal","Tanggal Anestesi","Tanggal Operasi","Kode Dokter Operator","Nama Dokter Operator",
            "Kode Dokter Anestesi","Kode Dokter Anestesi","Diagnosa Awal","Diagnosa Setelah Tindakan","TD","HR","RR","SaO2","Suhu","BB",
            "ASA","Premedikasi","Dilakukan Di","Induksi 1", "Induksi 2","Induksi 3","Induksi 4","Induksi 5","Oksigenasi","Inhalasi",
            "Tiva","Umum Inhalasi","Umum Inhalasi 2", "Nomer","Regional","Regional Lainnya","Posisi Puncture","Level", "Masuk Obat","Volume",
            "Komplikasi Akut","Penanganan","Pengawasan","Tiap","Program Cairan","Program Analgetik","Catatan Lain"
         
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 47; i++) {
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
                column.setPreferredWidth(115);
            }else if(i==9){
                column.setPreferredWidth(170);
            }else if(i==10){
                column.setPreferredWidth(170);
            }else if(i==11){
                column.setPreferredWidth(35);
            }else if(i==12){
                column.setPreferredWidth(35);
            }else if(i==13){
                column.setPreferredWidth(50);
            }else if(i==14){
                column.setPreferredWidth(35);
            }else if(i==15){
                column.setPreferredWidth(35);
            }else if(i==16){
                column.setPreferredWidth(55);
            }else if(i==17){
                column.setPreferredWidth(35);
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
                column.setPreferredWidth(100);
            }else if(i==31){
                column.setPreferredWidth(55);
            }else if(i==32){
                column.setPreferredWidth(97);
            }else if(i==33){
                column.setPreferredWidth(49);
            }else if(i==34){
                column.setPreferredWidth(94);
            }else if(i==35){
                column.setPreferredWidth(130);
            }else if(i==36){
                column.setPreferredWidth(170);
            }else if(i==37){
                column.setPreferredWidth(170);
            }else if(i==38){
                column.setPreferredWidth(170);
            }else if(i==39){
                column.setPreferredWidth(170);
            }else if(i==40){
                column.setPreferredWidth(60);
            }else if(i==41){
                column.setPreferredWidth(115);
            }else if(i==42){
                column.setPreferredWidth(95);
            }else if(i==43){
                column.setPreferredWidth(140);
            }
            else if(i==44){
                column.setPreferredWidth(140);
            }
            else if(i==45){
                column.setPreferredWidth(140);
            }
            else if(i==46){
                column.setPreferredWidth(140);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
       
        NmDokterOperator.setDocument(new batasInput((byte)5).getKata(NmDokterOperator));
       
        NmDokterAnestesi.setDocument(new batasInput((byte)5).getKata(NmDokterAnestesi));
        
       
        diagnosa_awal.setDocument(new batasInput((byte)100).getKata(diagnosa_awal));
        diagnosa_akhir.setDocument(new batasInput((byte)100).getKata(diagnosa_akhir));
        
        td.setDocument(new batasInput((byte)100).getKata(td));
        hr.setDocument(new batasInput((byte)100).getKata(hr));
        rr.setDocument(new batasInput((byte)100).getKata(rr));
        
        suhu.setDocument(new batasInput((byte)100).getKata(suhu));
        sao2.setDocument(new batasInput((byte)100).getKata(sao2));
        dilakukan.setDocument(new batasInput((byte)50).getKata(dilakukan));
       
        induksi1.setDocument(new batasInput((byte)100).getKata(induksi1));
        induksi2.setDocument(new batasInput((byte)100).getKata(induksi2));
        induksi3.setDocument(new batasInput((byte)100).getKata(induksi3));
        induksi4.setDocument(new batasInput((byte)100).getKata(induksi4));
  
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
                    if(pilihan==1){
                        KodeDokterOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        NmDokterOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                       BtnDokterOperator.requestFocus();
                    }else if(pilihan==2){
                        KodeDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        NmDokterAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                       BtnDokterAnestesi.requestFocus();
                    }   
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
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        label12 = new widget.Label();
        TglOperasi = new widget.Tanggal();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel109 = new widget.Label();
        jLabel15 = new widget.Label();
        NmDokterOperator = new widget.TextBox();
        jLabel18 = new widget.Label();
        NmDokterAnestesi = new widget.TextBox();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel30 = new widget.Label();
        diagnosa_awal = new widget.TextBox();
        jLabel31 = new widget.Label();
        diagnosa_akhir = new widget.TextBox();
        jLabel33 = new widget.Label();
        td = new widget.TextBox();
        jLabel34 = new widget.Label();
        hr = new widget.TextBox();
        jLabel36 = new widget.Label();
        rr = new widget.TextBox();
        jLabel37 = new widget.Label();
        suhu = new widget.TextBox();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        sao2 = new widget.TextBox();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel110 = new widget.Label();
        jLabel40 = new widget.Label();
        dilakukan = new widget.TextBox();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel43 = new widget.Label();
        induksi1 = new widget.TextBox();
        jLabel44 = new widget.Label();
        induksi2 = new widget.TextBox();
        induksi3 = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        induksi4 = new widget.TextBox();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel128 = new widget.Label();
        jLabel47 = new widget.Label();
        label15 = new widget.Label();
        TglAnestesi = new widget.Tanggal();
        jLabel112 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        bb = new widget.TextBox();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        premidikasi = new javax.swing.JTextArea();
        jLabel49 = new widget.Label();
        induksi5 = new widget.TextBox();
        jLabel50 = new widget.Label();
        oksigen = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel113 = new widget.Label();
        pp = new widget.TextBox();
        jLabel54 = new widget.Label();
        nomer = new widget.TextBox();
        jLabel55 = new widget.Label();
        regional_lainnya = new widget.TextBox();
        jLabel56 = new widget.Label();
        level = new widget.TextBox();
        jLabel57 = new widget.Label();
        tiap = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        penanganan = new widget.TextBox();
        jLabel60 = new widget.Label();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel114 = new widget.Label();
        jLabel61 = new widget.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        komplikasiakut = new javax.swing.JTextArea();
        masukobat = new widget.TextBox();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel115 = new widget.Label();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel116 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        volume = new widget.TextBox();
        jLabel65 = new widget.Label();
        jScrollPane3 = new javax.swing.JScrollPane();
        programanalgetik = new javax.swing.JTextArea();
        jLabel66 = new widget.Label();
        jScrollPane4 = new javax.swing.JScrollPane();
        catatanlain = new javax.swing.JTextArea();
        jLabel67 = new widget.Label();
        jScrollPane5 = new javax.swing.JScrollPane();
        programcairan = new javax.swing.JTextArea();
        jLabel41 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        drip = new javax.swing.JRadioButton();
        inter = new javax.swing.JRadioButton();
        semiclosed = new javax.swing.JRadioButton();
        semiopen = new javax.swing.JRadioButton();
        closed = new javax.swing.JRadioButton();
        sr = new javax.swing.JRadioButton();
        ar = new javax.swing.JRadioButton();
        rket = new javax.swing.JRadioButton();
        nt = new javax.swing.JRadioButton();
        masker = new javax.swing.JRadioButton();
        tangan = new javax.swing.JRadioButton();
        spiral = new javax.swing.JRadioButton();
        epidural = new javax.swing.JRadioButton();
        blok = new javax.swing.JRadioButton();
        td1 = new javax.swing.JRadioButton();
        nadi = new javax.swing.JRadioButton();
        rr1 = new javax.swing.JRadioButton();
        suhu1 = new javax.swing.JRadioButton();
        sevo = new javax.swing.JRadioButton();
        iso = new javax.swing.JRadioButton();
        desflu = new javax.swing.JRadioButton();
        ASA1 = new javax.swing.JRadioButton();
        ASAE = new javax.swing.JRadioButton();
        ASA2 = new javax.swing.JRadioButton();
        ASA3 = new javax.swing.JRadioButton();
        ASA4 = new javax.swing.JRadioButton();
        ASA5 = new javax.swing.JRadioButton();
        BtnDokterAnestesi = new widget.Button();
        BtnDokterOperator = new widget.Button();
        KodeDokterOperator = new widget.TextBox();
        KodeDokterAnestesi = new widget.TextBox();
        nomor = new javax.swing.JRadioButton();
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
        MnPenilaianMedis.setText("Laporan Penilaian Pre Anestesi");
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1539));
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

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(400, 40, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(470, 40, 80, 23);

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
        jSeparator1.setBounds(0, 70, 750, 1);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(180, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-10-2023 09:57:10" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(240, 40, 130, 23);

        label12.setText("Tgl.Operasi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(330, 80, 70, 23);

        TglOperasi.setForeground(new java.awt.Color(50, 70, 50));
        TglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-10-2023 09:57:10" }));
        TglOperasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglOperasi.setName("TglOperasi"); // NOI18N
        TglOperasi.setOpaque(false);
        TglOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglOperasiKeyPressed(evt);
            }
        });
        FormInput.add(TglOperasi);
        TglOperasi.setBounds(410, 80, 130, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 750, 1);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("II. Tanda - Tanda Vital");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(10, 250, 130, 23);

        jLabel15.setText("Dokter Operator :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 130, 130, 23);

        NmDokterOperator.setFocusTraversalPolicyProvider(true);
        NmDokterOperator.setName("NmDokterOperator"); // NOI18N
        NmDokterOperator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmDokterOperatorKeyPressed(evt);
            }
        });
        FormInput.add(NmDokterOperator);
        NmDokterOperator.setBounds(240, 130, 220, 23);

        jLabel18.setText("Dokter Anestesi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 160, 130, 23);

        NmDokterAnestesi.setFocusTraversalPolicyProvider(true);
        NmDokterAnestesi.setName("NmDokterAnestesi"); // NOI18N
        NmDokterAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmDokterAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(NmDokterAnestesi);
        NmDokterAnestesi.setBounds(240, 160, 220, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 110, 750, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 110, 750, 1);

        jLabel30.setText("Diagnosa Awal :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 190, 150, 23);

        diagnosa_awal.setFocusTraversalPolicyProvider(true);
        diagnosa_awal.setName("diagnosa_awal"); // NOI18N
        diagnosa_awal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosa_awalKeyPressed(evt);
            }
        });
        FormInput.add(diagnosa_awal);
        diagnosa_awal.setBounds(164, 190, 560, 23);

        jLabel31.setText("kg");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(510, 430, 30, 23);

        diagnosa_akhir.setFocusTraversalPolicyProvider(true);
        diagnosa_akhir.setName("diagnosa_akhir"); // NOI18N
        diagnosa_akhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosa_akhirKeyPressed(evt);
            }
        });
        FormInput.add(diagnosa_akhir);
        diagnosa_akhir.setBounds(164, 220, 560, 23);

        jLabel33.setText("TD :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 270, 130, 23);

        td.setFocusTraversalPolicyProvider(true);
        td.setName("td"); // NOI18N
        td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tdKeyPressed(evt);
            }
        });
        FormInput.add(td);
        td.setBounds(130, 270, 380, 23);

        jLabel34.setText("HR :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 300, 130, 23);

        hr.setFocusTraversalPolicyProvider(true);
        hr.setName("hr"); // NOI18N
        hr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hrKeyPressed(evt);
            }
        });
        FormInput.add(hr);
        hr.setBounds(130, 300, 380, 23);

        jLabel36.setText("RR :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 330, 130, 23);

        rr.setFocusTraversalPolicyProvider(true);
        rr.setName("rr"); // NOI18N
        rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rrKeyPressed(evt);
            }
        });
        FormInput.add(rr);
        rr.setBounds(130, 330, 380, 23);

        jLabel37.setText("SaO2 :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 360, 130, 23);

        suhu.setFocusTraversalPolicyProvider(true);
        suhu.setName("suhu"); // NOI18N
        suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suhuKeyPressed(evt);
            }
        });
        FormInput.add(suhu);
        suhu.setBounds(140, 400, 380, 23);

        jLabel38.setText("Suhu :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 400, 130, 23);

        jLabel39.setText("BB :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 430, 130, 23);

        sao2.setFocusTraversalPolicyProvider(true);
        sao2.setName("sao2"); // NOI18N
        sao2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sao2KeyPressed(evt);
            }
        });
        FormInput.add(sao2);
        sao2.setBounds(130, 360, 380, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 390, 750, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 390, 750, 1);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("III. Premedikasi");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(10, 490, 130, 23);

        jLabel40.setText("Dilakukan di :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 580, 130, 23);

        dilakukan.setFocusTraversalPolicyProvider(true);
        dilakukan.setName("dilakukan"); // NOI18N
        dilakukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dilakukanKeyPressed(evt);
            }
        });
        FormInput.add(dilakukan);
        dilakukan.setBounds(130, 580, 590, 23);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 610, 750, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 610, 750, 1);

        jLabel43.setText("1.");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 630, 130, 23);

        induksi1.setFocusTraversalPolicyProvider(true);
        induksi1.setName("induksi1"); // NOI18N
        induksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                induksi1KeyPressed(evt);
            }
        });
        FormInput.add(induksi1);
        induksi1.setBounds(130, 630, 180, 23);

        jLabel44.setText("2.");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 660, 130, 23);

        induksi2.setFocusTraversalPolicyProvider(true);
        induksi2.setName("induksi2"); // NOI18N
        induksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                induksi2KeyPressed(evt);
            }
        });
        FormInput.add(induksi2);
        induksi2.setBounds(130, 660, 180, 23);

        induksi3.setFocusTraversalPolicyProvider(true);
        induksi3.setName("induksi3"); // NOI18N
        induksi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                induksi3KeyPressed(evt);
            }
        });
        FormInput.add(induksi3);
        induksi3.setBounds(130, 690, 180, 23);

        jLabel45.setText("3.");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 690, 130, 23);

        jLabel46.setText("4.");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 720, 130, 23);

        induksi4.setFocusTraversalPolicyProvider(true);
        induksi4.setName("induksi4"); // NOI18N
        induksi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                induksi4KeyPressed(evt);
            }
        });
        FormInput.add(induksi4);
        induksi4.setBounds(130, 720, 180, 23);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(0, 780, 750, 1);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 780, 750, 1);

        jLabel128.setText("Tiva :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(0, 820, 130, 23);

        jLabel47.setText("CC");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(640, 1000, 50, 23);

        label15.setText("Tgl.Anestesi :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(40, 80, 70, 23);

        TglAnestesi.setForeground(new java.awt.Color(50, 70, 50));
        TglAnestesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-10-2023 09:57:37" }));
        TglAnestesi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAnestesi.setName("TglAnestesi"); // NOI18N
        TglAnestesi.setOpaque(false);
        TglAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(TglAnestesi);
        TglAnestesi.setBounds(120, 80, 130, 23);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("I. Rencana");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(10, 110, 130, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 250, 750, 1);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 250, 750, 1);

        bb.setFocusTraversalPolicyProvider(true);
        bb.setName("bb"); // NOI18N
        bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbKeyPressed(evt);
            }
        });
        FormInput.add(bb);
        bb.setBounds(140, 430, 380, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 490, 750, 1);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 490, 750, 1);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        premidikasi.setColumns(20);
        premidikasi.setRows(5);
        premidikasi.setName("premidikasi"); // NOI18N
        jScrollPane1.setViewportView(premidikasi);

        FormInput.add(jScrollPane1);
        jScrollPane1.setBounds(130, 510, 470, 50);

        jLabel49.setText("5.");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(0, 750, 130, 23);

        induksi5.setFocusTraversalPolicyProvider(true);
        induksi5.setName("induksi5"); // NOI18N
        induksi5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                induksi5KeyPressed(evt);
            }
        });
        FormInput.add(induksi5);
        induksi5.setBounds(130, 750, 180, 23);

        jLabel50.setText("L/menit");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(620, 630, 40, 23);

        oksigen.setFocusTraversalPolicyProvider(true);
        oksigen.setName("oksigen"); // NOI18N
        oksigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                oksigenKeyPressed(evt);
            }
        });
        FormInput.add(oksigen);
        oksigen.setBounds(410, 630, 210, 23);

        jLabel51.setText("Inhalasi :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(280, 660, 130, 23);

        jLabel52.setText("Oksigenasi :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(280, 630, 130, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("IV. Induksi");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(10, 610, 130, 23);

        pp.setFocusTraversalPolicyProvider(true);
        pp.setName("pp"); // NOI18N
        pp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ppKeyPressed(evt);
            }
        });
        FormInput.add(pp);
        pp.setBounds(140, 940, 500, 23);

        jLabel54.setText("Umum Inhalasi :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 850, 130, 23);

        nomer.setEditable(false);
        nomer.setFocusTraversalPolicyProvider(true);
        nomer.setName("nomer"); // NOI18N
        nomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomerKeyPressed(evt);
            }
        });
        FormInput.add(nomer);
        nomer.setBounds(490, 880, 50, 23);

        jLabel55.setText("Regional :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 910, 130, 23);

        regional_lainnya.setFocusTraversalPolicyProvider(true);
        regional_lainnya.setName("regional_lainnya"); // NOI18N
        regional_lainnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regional_lainnyaActionPerformed(evt);
            }
        });
        regional_lainnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                regional_lainnyaKeyPressed(evt);
            }
        });
        FormInput.add(regional_lainnya);
        regional_lainnya.setBounds(500, 910, 130, 23);

        jLabel56.setText("Level :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 970, 130, 23);

        level.setFocusTraversalPolicyProvider(true);
        level.setName("level"); // NOI18N
        level.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                levelKeyPressed(evt);
            }
        });
        FormInput.add(level);
        level.setBounds(140, 970, 470, 23);

        jLabel57.setText("jam");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(450, 1210, 30, 23);

        tiap.setFocusTraversalPolicyProvider(true);
        tiap.setName("tiap"); // NOI18N
        tiap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tiapKeyPressed(evt);
            }
        });
        FormInput.add(tiap);
        tiap.setBounds(410, 1210, 50, 23);

        jLabel58.setText("Penanganan :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 1150, 130, 23);

        jLabel59.setText("Median / Paramedian");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(570, 970, 150, 23);

        penanganan.setFocusTraversalPolicyProvider(true);
        penanganan.setName("penanganan"); // NOI18N
        penanganan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                penangananKeyPressed(evt);
            }
        });
        FormInput.add(penanganan);
        penanganan.setBounds(140, 1150, 500, 23);

        jLabel60.setText("Volume");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(470, 1000, 50, 23);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(0, 1030, 750, 1);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(0, 1030, 750, 1);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("V. Teknik Anestesi");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(10, 790, 130, 23);

        jLabel61.setText("Posisi Puncture :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 940, 130, 23);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        komplikasiakut.setColumns(20);
        komplikasiakut.setRows(5);
        komplikasiakut.setName("komplikasiakut"); // NOI18N
        jScrollPane2.setViewportView(komplikasiakut);

        FormInput.add(jScrollPane2);
        jScrollPane2.setBounds(140, 1060, 510, 86);

        masukobat.setFocusTraversalPolicyProvider(true);
        masukobat.setName("masukobat"); // NOI18N
        masukobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                masukobatKeyPressed(evt);
            }
        });
        FormInput.add(masukobat);
        masukobat.setBounds(140, 1000, 340, 23);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(0, 1180, 750, 1);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(0, 1180, 750, 1);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("VI.Komplikasi Akut");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(10, 1030, 130, 23);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        FormInput.add(jSeparator19);
        jSeparator19.setBounds(0, 1200, 750, 1);

        jSeparator20.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator20.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator20.setName("jSeparator20"); // NOI18N
        FormInput.add(jSeparator20);
        jSeparator20.setBounds(0, 1200, 750, 1);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("CATATAN UNDUK DI RUANGAN");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(290, 1180, 200, 23);

        jLabel62.setText("Masuk Obat :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 1000, 130, 23);

        jLabel63.setText("Program Analgetik :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 1340, 130, 23);

        jLabel64.setText("tiap");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(390, 1210, 20, 23);

        volume.setFocusTraversalPolicyProvider(true);
        volume.setName("volume"); // NOI18N
        volume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                volumeKeyPressed(evt);
            }
        });
        FormInput.add(volume);
        volume.setBounds(530, 1000, 130, 23);

        jLabel65.setText("Pengawasan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 1210, 130, 23);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        programanalgetik.setColumns(20);
        programanalgetik.setRows(5);
        programanalgetik.setName("programanalgetik"); // NOI18N
        jScrollPane3.setViewportView(programanalgetik);

        FormInput.add(jScrollPane3);
        jScrollPane3.setBounds(140, 1350, 260, 86);

        jLabel66.setText("Catatan Lain :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(430, 1210, 130, 23);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        catatanlain.setColumns(20);
        catatanlain.setRows(5);
        catatanlain.setName("catatanlain"); // NOI18N
        jScrollPane4.setViewportView(catatanlain);

        FormInput.add(jScrollPane4);
        jScrollPane4.setBounds(480, 1240, 260, 200);

        jLabel67.setText("Program Cairan :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 1240, 130, 23);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        programcairan.setColumns(20);
        programcairan.setRows(5);
        programcairan.setName("programcairan"); // NOI18N
        jScrollPane5.setViewportView(programcairan);

        FormInput.add(jScrollPane5);
        jScrollPane5.setBounds(140, 1240, 260, 86);

        jLabel41.setText("ASA :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 460, 130, 23);

        jLabel32.setText("Diagnosa Setelah Tindakan :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(-10, 220, 160, 23);

        jLabel42.setText("mmHg");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(390, 270, 160, 23);

        jLabel48.setText("x/menit");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(390, 300, 160, 23);

        jLabel68.setText("x/menit");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(460, 330, 90, 23);

        jLabel69.setText("%");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(500, 360, 30, 23);

        jLabel70.setText("0c");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(510, 400, 30, 23);

        buttonGroup1.add(drip);
        drip.setText("Drip");
        drip.setName("drip"); // NOI18N
        FormInput.add(drip);
        drip.setBounds(150, 820, 98, 20);

        buttonGroup1.add(inter);
        inter.setText("Intermitten");
        inter.setName("inter"); // NOI18N
        FormInput.add(inter);
        inter.setBounds(260, 820, 98, 20);

        buttonGroup2.add(semiclosed);
        semiclosed.setText("Semi Closed");
        semiclosed.setName("semiclosed"); // NOI18N
        FormInput.add(semiclosed);
        semiclosed.setBounds(140, 850, 110, 20);

        buttonGroup2.add(semiopen);
        semiopen.setText("Semi Open");
        semiopen.setName("semiopen"); // NOI18N
        FormInput.add(semiopen);
        semiopen.setBounds(270, 850, 110, 20);

        buttonGroup2.add(closed);
        closed.setText("Closed");
        closed.setName("closed"); // NOI18N
        FormInput.add(closed);
        closed.setBounds(390, 850, 80, 20);

        buttonGroup3.add(sr);
        sr.setText("SR");
        sr.setName("sr"); // NOI18N
        FormInput.add(sr);
        sr.setBounds(140, 880, 50, 20);

        buttonGroup3.add(ar);
        ar.setText("AR");
        ar.setName("ar"); // NOI18N
        FormInput.add(ar);
        ar.setBounds(200, 880, 50, 20);

        buttonGroup3.add(rket);
        rket.setText("RK dengan ET");
        rket.setName("rket"); // NOI18N
        FormInput.add(rket);
        rket.setBounds(250, 880, 110, 20);

        buttonGroup3.add(nt);
        nt.setText("NT");
        nt.setName("nt"); // NOI18N
        FormInput.add(nt);
        nt.setBounds(370, 880, 50, 20);

        buttonGroup3.add(masker);
        masker.setText("Masker dengan mesin");
        masker.setName("masker"); // NOI18N
        FormInput.add(masker);
        masker.setBounds(550, 880, 160, 20);

        buttonGroup3.add(tangan);
        tangan.setText("Tangan");
        tangan.setName("tangan"); // NOI18N
        FormInput.add(tangan);
        tangan.setBounds(710, 880, 80, 20);

        buttonGroup4.add(spiral);
        spiral.setText("Spiral");
        spiral.setName("spiral"); // NOI18N
        FormInput.add(spiral);
        spiral.setBounds(140, 910, 90, 20);

        buttonGroup4.add(epidural);
        epidural.setText("Epidural");
        epidural.setName("epidural"); // NOI18N
        FormInput.add(epidural);
        epidural.setBounds(250, 910, 100, 20);

        buttonGroup4.add(blok);
        blok.setText("Blok Penifer");
        blok.setName("blok"); // NOI18N
        FormInput.add(blok);
        blok.setBounds(370, 910, 110, 20);

        buttonGroup5.add(td1);
        td1.setText("TD");
        td1.setName("td1"); // NOI18N
        FormInput.add(td1);
        td1.setBounds(130, 1210, 50, 20);

        buttonGroup5.add(nadi);
        nadi.setText("Nadi");
        nadi.setName("nadi"); // NOI18N
        FormInput.add(nadi);
        nadi.setBounds(190, 1210, 60, 20);

        buttonGroup5.add(rr1);
        rr1.setText("RR");
        rr1.setName("rr1"); // NOI18N
        FormInput.add(rr1);
        rr1.setBounds(260, 1210, 50, 20);

        buttonGroup5.add(suhu1);
        suhu1.setText("Suhu");
        suhu1.setName("suhu1"); // NOI18N
        FormInput.add(suhu1);
        suhu1.setBounds(320, 1210, 60, 20);

        buttonGroup6.add(sevo);
        sevo.setText("Sevoflurance");
        sevo.setName("sevo"); // NOI18N
        FormInput.add(sevo);
        sevo.setBounds(410, 660, 104, 20);

        buttonGroup6.add(iso);
        iso.setText("Isoflurance");
        iso.setName("iso"); // NOI18N
        FormInput.add(iso);
        iso.setBounds(520, 660, 104, 20);

        buttonGroup6.add(desflu);
        desflu.setText("Desflurance");
        desflu.setName("desflu"); // NOI18N
        FormInput.add(desflu);
        desflu.setBounds(630, 660, 104, 20);

        buttonGroup7.add(ASA1);
        ASA1.setText("I");
        ASA1.setName("ASA1"); // NOI18N
        FormInput.add(ASA1);
        ASA1.setBounds(140, 460, 40, 20);

        buttonGroup7.add(ASAE);
        ASAE.setText("E");
        ASAE.setName("ASAE"); // NOI18N
        FormInput.add(ASAE);
        ASAE.setBounds(400, 460, 50, 20);

        buttonGroup7.add(ASA2);
        ASA2.setText("II");
        ASA2.setName("ASA2"); // NOI18N
        FormInput.add(ASA2);
        ASA2.setBounds(180, 460, 50, 20);

        buttonGroup7.add(ASA3);
        ASA3.setText("III");
        ASA3.setName("ASA3"); // NOI18N
        FormInput.add(ASA3);
        ASA3.setBounds(230, 460, 50, 20);

        buttonGroup7.add(ASA4);
        ASA4.setText("IV");
        ASA4.setName("ASA4"); // NOI18N
        FormInput.add(ASA4);
        ASA4.setBounds(290, 460, 50, 20);

        buttonGroup7.add(ASA5);
        ASA5.setText("V");
        ASA5.setName("ASA5"); // NOI18N
        FormInput.add(ASA5);
        ASA5.setBounds(350, 460, 50, 20);

        BtnDokterAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterAnestesi.setMnemonic('2');
        BtnDokterAnestesi.setToolTipText("Alt+2");
        BtnDokterAnestesi.setName("BtnDokterAnestesi"); // NOI18N
        BtnDokterAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterAnestesiActionPerformed(evt);
            }
        });
        BtnDokterAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterAnestesiKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokterAnestesi);
        BtnDokterAnestesi.setBounds(460, 160, 28, 23);

        BtnDokterOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterOperator.setMnemonic('2');
        BtnDokterOperator.setToolTipText("Alt+2");
        BtnDokterOperator.setName("BtnDokterOperator"); // NOI18N
        BtnDokterOperator.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterOperatorActionPerformed(evt);
            }
        });
        BtnDokterOperator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterOperatorKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokterOperator);
        BtnDokterOperator.setBounds(460, 130, 28, 23);

        KodeDokterOperator.setEditable(false);
        KodeDokterOperator.setName("KodeDokterOperator"); // NOI18N
        KodeDokterOperator.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KodeDokterOperator);
        KodeDokterOperator.setBounds(140, 130, 90, 23);

        KodeDokterAnestesi.setEditable(false);
        KodeDokterAnestesi.setName("KodeDokterAnestesi"); // NOI18N
        KodeDokterAnestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KodeDokterAnestesi);
        KodeDokterAnestesi.setBounds(140, 160, 90, 23);

        buttonGroup3.add(nomor);
        nomor.setText("Nomer");
        nomor.setName("nomor"); // NOI18N
        nomor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nomorItemStateChanged(evt);
            }
        });
        nomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomorActionPerformed(evt);
            }
        });
        FormInput.add(nomor);
        nomor.setBounds(420, 880, 70, 20);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-10-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-10-2023" }));
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
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Laporan Anestesi ]::");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
           
        }else{            
         
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String asa ="";
        String inhalasi ="";
        String tiva ="";
        String umuminhalasi="";
        String umuminhalasi2="";
        String regional="";
        String pengawasan ="";
        
        
          if(ASA1.isSelected()){
            asa = "I";
        } else if (ASA2.isSelected()){
            asa = "II";
        } else if (ASA3.isSelected()){
            asa = "III";
        } else if (ASA4.isSelected()){
            asa = "IV";
        } else if (ASA5.isSelected()){
            asa = "V";
        } else if (ASAE.isSelected()){
            asa = "E";
        } 

          
            if(sevo.isSelected()){
            inhalasi = "Sevoflurance";
        } else if (iso.isSelected()){
            inhalasi = "Isoflurance";
        } else if (desflu.isSelected()){
            inhalasi = "Desflurance";
        }

            
            if(drip.isSelected()){
            tiva = "Drip";
        } else if (inter.isSelected()){
            tiva = "Intermitten";
        }

               
            if(semiclosed.isSelected()){
            umuminhalasi = "Semi Closed";
        } else if (semiopen.isSelected()){
            umuminhalasi = "Semi Open";
        } else if (closed.isSelected()){
            umuminhalasi = "Closed";
        }
                
            if(sr.isSelected()){
            umuminhalasi2 = "SR";
        } else if (ar.isSelected()){
            umuminhalasi2 = "AR";
        } else if (rket.isSelected()){
            umuminhalasi2 = "RK dengan ET";
        } else if (nt.isSelected()){
            umuminhalasi2 = "NT";
        }  else if (nomor.isSelected()){
            umuminhalasi2 = "Nomer";
        }  else if (masker.isSelected()){
            umuminhalasi2 = "Masker dengan Mesin";
        } else if (tangan.isSelected()){
            umuminhalasi2 = "Tangan";
        }
                 
            if(spiral.isSelected()){
            regional = "Spiral";
        } else if (epidural.isSelected()){
            regional = "Epidural";
        } else if (blok.isSelected()){
            regional = "Block Penifer";
        }
            
            if(td1.isSelected()){
            pengawasan = "TD";
        } else if (nadi.isSelected()){
            pengawasan = "Nadi";
        } else if (rr1.isSelected()){
            pengawasan = "RR";
        } else if (suhu1.isSelected()){
            pengawasan = "Suhu";
        }


        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokterAnestesi.getText().trim().equals("")){
            Valid.textKosong(BtnDokterAnestesi,"Dokter");
        }else if(diagnosa_awal.getText().trim().equals("")){
            Valid.textKosong(diagnosa_awal,"Diagnosa");
        }else if(diagnosa_akhir.getText().trim().equals("")){
            Valid.textKosong(diagnosa_akhir,"Rencana Tindakan");
        }
        else{
            if(Sequel.menyimpantf("laporan_anestesi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",41,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Valid.SetTgl(TglAnestesi.getSelectedItem()+"")+" "+TglAnestesi.getSelectedItem().toString().substring(11,19),
                    Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),KodeDokterOperator.getText(),KodeDokterAnestesi.getText(),diagnosa_awal.getText(),diagnosa_akhir.getText(), 
                    td.getText(),hr.getText(),rr.getText(),sao2.getText(),suhu.getText(),bb.getText(),asa,
                    premidikasi.getText(),dilakukan.getText(),induksi1.getText(),induksi2.getText(),induksi3.getText(),induksi4.getText(),induksi5.getText(), 
                    oksigen.getText(),inhalasi,tiva,umuminhalasi,umuminhalasi2,nomer.getText(), 
                    regional,regional_lainnya.getText(),pp.getText(),
                    level.getText(),masukobat.getText(),volume.getText(),komplikasiakut.getText(),penanganan.getText(),
                    pengawasan,tiap.getText(),programcairan.getText(), 
                    programanalgetik.getText(),catatanlain.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//           Valid.pindah(evt,CatatanKhusus,BtnBatal);
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
                if(KodeDokterAnestesi.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
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
        }else if(NmDokterAnestesi.getText().trim().equals("")){
            Valid.textKosong(BtnDokterAnestesi,"Dokter");
        }else if(diagnosa_awal.getText().trim().equals("")){
            Valid.textKosong(diagnosa_awal,"Diagnosa");
        }else if(diagnosa_akhir.getText().trim().equals("")){
            Valid.textKosong(diagnosa_akhir,"Diagnosa Akhir");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KodeDokterAnestesi.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
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
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_pre_anestesi.tanggal,"+
                        "penilaian_pre_anestesi.kd_dokter,penilaian_pre_anestesi.tanggal_operasi,penilaian_pre_anestesi.diagnosa,penilaian_pre_anestesi.rencana_tindakan,penilaian_pre_anestesi.tb,"+
                        "penilaian_pre_anestesi.bb,penilaian_pre_anestesi.td,penilaian_pre_anestesi.io2,penilaian_pre_anestesi.nadi,penilaian_pre_anestesi.pernapasan,penilaian_pre_anestesi.suhu,"+
                        "penilaian_pre_anestesi.fisik_cardiovasculer,penilaian_pre_anestesi.fisik_paru,penilaian_pre_anestesi.fisik_abdomen,penilaian_pre_anestesi.fisik_extrimitas,"+
                        "penilaian_pre_anestesi.fisik_endokrin,penilaian_pre_anestesi.fisik_ginjal,penilaian_pre_anestesi.fisik_obatobatan,penilaian_pre_anestesi.fisik_laborat,"+
                        "penilaian_pre_anestesi.fisik_penunjang,penilaian_pre_anestesi.riwayat_penyakit_alergiobat,penilaian_pre_anestesi.riwayat_penyakit_alergilainnya,"+
                        "penilaian_pre_anestesi.riwayat_penyakit_terapi,penilaian_pre_anestesi.riwayat_kebiasaan_merokok,penilaian_pre_anestesi.riwayat_kebiasaan_ket_merokok,"+
                        "penilaian_pre_anestesi.riwayat_kebiasaan_alkohol,penilaian_pre_anestesi.riwayat_kebiasaan_ket_alkohol,penilaian_pre_anestesi.riwayat_kebiasaan_obat,"+
                        "penilaian_pre_anestesi.riwayat_kebiasaan_ket_obat,penilaian_pre_anestesi.riwayat_medis_cardiovasculer,penilaian_pre_anestesi.riwayat_medis_respiratory,"+
                        "penilaian_pre_anestesi.riwayat_medis_endocrine,penilaian_pre_anestesi.riwayat_medis_lainnya,penilaian_pre_anestesi.asa,penilaian_pre_anestesi.puasa,"+
                        "penilaian_pre_anestesi.rencana_anestesi,penilaian_pre_anestesi.rencana_perawatan,penilaian_pre_anestesi.catatan_khusus,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_pre_anestesi on reg_periksa.no_rawat=penilaian_pre_anestesi.no_rawat "+
                        "inner join dokter on penilaian_pre_anestesi.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_pre_anestesi.tanggal between ? and ? order by penilaian_pre_anestesi.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_pre_anestesi.tanggal,"+
                        "penilaian_pre_anestesi.kd_dokter,penilaian_pre_anestesi.tanggal_operasi,penilaian_pre_anestesi.diagnosa,penilaian_pre_anestesi.rencana_tindakan,penilaian_pre_anestesi.tb,"+
                        "penilaian_pre_anestesi.bb,penilaian_pre_anestesi.td,penilaian_pre_anestesi.io2,penilaian_pre_anestesi.nadi,penilaian_pre_anestesi.pernapasan,penilaian_pre_anestesi.suhu,"+
                        "penilaian_pre_anestesi.fisik_cardiovasculer,penilaian_pre_anestesi.fisik_paru,penilaian_pre_anestesi.fisik_abdomen,penilaian_pre_anestesi.fisik_extrimitas,"+
                        "penilaian_pre_anestesi.fisik_endokrin,penilaian_pre_anestesi.fisik_ginjal,penilaian_pre_anestesi.fisik_obatobatan,penilaian_pre_anestesi.fisik_laborat,"+
                        "penilaian_pre_anestesi.fisik_penunjang,penilaian_pre_anestesi.riwayat_penyakit_alergiobat,penilaian_pre_anestesi.riwayat_penyakit_alergilainnya,"+
                        "penilaian_pre_anestesi.riwayat_penyakit_terapi,penilaian_pre_anestesi.riwayat_kebiasaan_merokok,penilaian_pre_anestesi.riwayat_kebiasaan_ket_merokok,"+
                        "penilaian_pre_anestesi.riwayat_kebiasaan_alkohol,penilaian_pre_anestesi.riwayat_kebiasaan_ket_alkohol,penilaian_pre_anestesi.riwayat_kebiasaan_obat,"+
                        "penilaian_pre_anestesi.riwayat_kebiasaan_ket_obat,penilaian_pre_anestesi.riwayat_medis_cardiovasculer,penilaian_pre_anestesi.riwayat_medis_respiratory,"+
                        "penilaian_pre_anestesi.riwayat_medis_endocrine,penilaian_pre_anestesi.riwayat_medis_lainnya,penilaian_pre_anestesi.asa,penilaian_pre_anestesi.puasa,"+
                        "penilaian_pre_anestesi.rencana_anestesi,penilaian_pre_anestesi.rencana_perawatan,penilaian_pre_anestesi.catatan_khusus,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_pre_anestesi on reg_periksa.no_rawat=penilaian_pre_anestesi.no_rawat "+
                        "inner join dokter on penilaian_pre_anestesi.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_pre_anestesi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_pre_anestesi.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_pre_anestesi.tanggal");
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
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Operasi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosa</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Tindakan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>BB</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>TD</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>IO2</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nadi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pernapasan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suhu</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Cardiovasculer</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Paru</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Abdomen</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Extrimitas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Endokrin</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Ginjal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Obat-obatan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Laborat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Asesmen Fisik Penunjang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Alergi Obat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Alergi Lainnya</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Penyakit Terapi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebiasaan Merokok</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml.Rokok</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kebiasaan Alkohol</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml.Alko</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Penggunaan Obat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Obat Dikonsumsi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Cardiovasculer</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Respiratory</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Endocrine</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Riwayat Medis Lainnya</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Angka ASA</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Mulai Puasa</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Anestesi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Rencana Perawatan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Catatan Khusus</b></td>"+
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
                               "<td valign='top'>"+rs.getString("tanggal_operasi")+"</td>"+
                               "<td valign='top'>"+rs.getString("diagnosa")+"</td>"+
                               "<td valign='top'>"+rs.getString("rencana_tindakan")+"</td>"+
                               "<td valign='top'>"+rs.getString("tb")+"</td>"+
                               "<td valign='top'>"+rs.getString("bb")+"</td>"+
                               "<td valign='top'>"+rs.getString("td")+"</td>"+
                               "<td valign='top'>"+rs.getString("io2")+"</td>"+
                               "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                               "<td valign='top'>"+rs.getString("pernapasan")+"</td>"+
                               "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_cardiovasculer")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_paru")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_abdomen")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_extrimitas")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_endokrin")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_ginjal")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_obatobatan")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_laborat")+"</td>"+
                               "<td valign='top'>"+rs.getString("fisik_penunjang")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_penyakit_alergiobat")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_penyakit_alergilainnya")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_penyakit_terapi")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_merokok")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_merokok")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_alkohol")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_alkohol")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_obat")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_obat")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_cardiovasculer")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_respiratory")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_endocrine")+"</td>"+
                               "<td valign='top'>"+rs.getString("riwayat_medis_lainnya")+"</td>"+
                               "<td valign='top'>"+rs.getString("asa")+"</td>"+
                               "<td valign='top'>"+rs.getString("puasa")+"</td>"+
                               "<td valign='top'>"+rs.getString("rencana_anestesi")+"</td>"+
                               "<td valign='top'>"+rs.getString("rencana_perawatan")+"</td>"+
                               "<td valign='top'>"+rs.getString("catatan_khusus")+"</td>"+
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
            
            Valid.MyReportqry("rptCetakPenilaianPreAnestesi.jasper","report","::[ Laporan Penilaian Pre Anestesi ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_pre_anestesi.tanggal,"+
                "penilaian_pre_anestesi.kd_dokter,DATE_FORMAT(penilaian_pre_anestesi.tanggal_operasi,'%d-%m-%Y %H:%m:%s') as tanggal_operasi,penilaian_pre_anestesi.diagnosa,"+
                "penilaian_pre_anestesi.rencana_tindakan,penilaian_pre_anestesi.tb,penilaian_pre_anestesi.bb,penilaian_pre_anestesi.td,penilaian_pre_anestesi.io2,"+
                "penilaian_pre_anestesi.nadi,penilaian_pre_anestesi.pernapasan,penilaian_pre_anestesi.suhu,penilaian_pre_anestesi.fisik_cardiovasculer,penilaian_pre_anestesi.fisik_paru,"+
                "penilaian_pre_anestesi.fisik_abdomen,penilaian_pre_anestesi.fisik_extrimitas,penilaian_pre_anestesi.fisik_endokrin,penilaian_pre_anestesi.fisik_ginjal,"+
                "penilaian_pre_anestesi.fisik_obatobatan,penilaian_pre_anestesi.fisik_laborat,penilaian_pre_anestesi.fisik_penunjang,penilaian_pre_anestesi.riwayat_penyakit_alergiobat,"+
                "penilaian_pre_anestesi.riwayat_penyakit_alergilainnya,penilaian_pre_anestesi.riwayat_penyakit_terapi,penilaian_pre_anestesi.riwayat_kebiasaan_merokok,"+
                "penilaian_pre_anestesi.riwayat_kebiasaan_ket_merokok,penilaian_pre_anestesi.riwayat_kebiasaan_alkohol,penilaian_pre_anestesi.riwayat_kebiasaan_ket_alkohol,"+
                "penilaian_pre_anestesi.riwayat_kebiasaan_obat,penilaian_pre_anestesi.riwayat_kebiasaan_ket_obat,penilaian_pre_anestesi.riwayat_medis_cardiovasculer,"+
                "penilaian_pre_anestesi.riwayat_medis_respiratory,penilaian_pre_anestesi.riwayat_medis_endocrine,penilaian_pre_anestesi.riwayat_medis_lainnya,"+
                "penilaian_pre_anestesi.asa,DATE_FORMAT(penilaian_pre_anestesi.puasa,'%d-%m-%Y %H:%m:%s') as puasa,penilaian_pre_anestesi.rencana_anestesi,penilaian_pre_anestesi.rencana_perawatan,"+
                "penilaian_pre_anestesi.catatan_khusus,dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_pre_anestesi on reg_periksa.no_rawat=penilaian_pre_anestesi.no_rawat "+
                "inner join dokter on penilaian_pre_anestesi.kd_dokter=dokter.kd_dokter where penilaian_pre_anestesi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and penilaian_pre_anestesi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void TglOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglOperasiKeyPressed
//        Valid.pindah(evt,RencanaTindakan,dokter_op);
    }//GEN-LAST:event_TglOperasiKeyPressed

    private void NmDokterOperatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmDokterOperatorKeyPressed
//        Valid.pindah(evt,RencanaTindakan,BB);
    }//GEN-LAST:event_NmDokterOperatorKeyPressed

    private void NmDokterAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmDokterAnestesiKeyPressed
//        Valid.pindah(evt,IO2,Suhu);
    }//GEN-LAST:event_NmDokterAnestesiKeyPressed

    private void diagnosa_awalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosa_awalKeyPressed
//        Valid.pindah(evt,Pernapasan,diagnosa_akhir);
    }//GEN-LAST:event_diagnosa_awalKeyPressed

    private void tdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdKeyPressed
//        Valid.pindah(evt,FisikAbdomen,hr);
    }//GEN-LAST:event_tdKeyPressed

    private void hrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hrKeyPressed
        Valid.pindah(evt,td,rr);
    }//GEN-LAST:event_hrKeyPressed

    private void rrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rrKeyPressed
//        Valid.pindah(evt,hr,FisikObat);
    }//GEN-LAST:event_rrKeyPressed

    private void suhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suhuKeyPressed
//        Valid.pindah(evt,FisikObat,sao2);
    }//GEN-LAST:event_suhuKeyPressed

    private void sao2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sao2KeyPressed
        Valid.pindah(evt,suhu,dilakukan);
    }//GEN-LAST:event_sao2KeyPressed

    private void dilakukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dilakukanKeyPressed
//        Valid.pindah(evt,sao2,PenyakitAlergiLainnya);
    }//GEN-LAST:event_dilakukanKeyPressed

    private void induksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_induksi1KeyPressed
//        Valid.pindah(evt,PenyakitKebiasaanObatDiminum,induksi2);
    }//GEN-LAST:event_induksi1KeyPressed

    private void induksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_induksi2KeyPressed
        Valid.pindah(evt,induksi1,induksi3);
    }//GEN-LAST:event_induksi2KeyPressed

    private void induksi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_induksi3KeyPressed
        Valid.pindah(evt,induksi2,induksi4);
    }//GEN-LAST:event_induksi3KeyPressed

    private void induksi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_induksi4KeyPressed
//        Valid.pindah(evt,induksi3,RencanaAnestesi);
    }//GEN-LAST:event_induksi4KeyPressed

    private void TglAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAnestesiKeyPressed

    private void diagnosa_akhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosa_akhirKeyPressed
//        Valid.pindah(evt,diagnosa_awal,FisikAbdomen);
    }//GEN-LAST:event_diagnosa_akhirKeyPressed

    private void bbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bbKeyPressed

    private void induksi5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_induksi5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_induksi5KeyPressed

    private void oksigenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_oksigenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_oksigenKeyPressed

    private void ppKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ppKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ppKeyPressed

    private void nomerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomerKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomerKeyPressed

    private void regional_lainnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regional_lainnyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_regional_lainnyaKeyPressed

    private void levelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_levelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_levelKeyPressed

    private void tiapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tiapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiapKeyPressed

    private void penangananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_penangananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_penangananKeyPressed

    private void masukobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_masukobatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_masukobatKeyPressed

    private void volumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volumeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_volumeKeyPressed

    private void regional_lainnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regional_lainnyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regional_lainnyaActionPerformed

    private void BtnDokterAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterAnestesiActionPerformed
        pilihan=2;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterAnestesiActionPerformed

    private void BtnDokterAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterAnestesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterAnestesiKeyPressed

    private void BtnDokterOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterOperatorActionPerformed
          pilihan=1;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterOperatorActionPerformed

    private void BtnDokterOperatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterOperatorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokterOperatorKeyPressed

    private void nomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomorActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_nomorActionPerformed

    private void nomorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nomorItemStateChanged
         if (nomor.isSelected())  {
            nomer.setEditable(true);
         }
        else {
             nomer.setEditable(false);
         }// TODO add your handling code here:
    }//GEN-LAST:event_nomorItemStateChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLaporanAnestesi dialog = new RMLaporanAnestesi(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton ASA1;
    private javax.swing.JRadioButton ASA2;
    private javax.swing.JRadioButton ASA3;
    private javax.swing.JRadioButton ASA4;
    private javax.swing.JRadioButton ASA5;
    private javax.swing.JRadioButton ASAE;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokterAnestesi;
    private widget.Button BtnDokterOperator;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KodeDokterAnestesi;
    private widget.TextBox KodeDokterOperator;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmDokterAnestesi;
    private widget.TextBox NmDokterOperator;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAnestesi;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglOperasi;
    private javax.swing.JRadioButton ar;
    private widget.TextBox bb;
    private javax.swing.JRadioButton blok;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JTextArea catatanlain;
    private javax.swing.JRadioButton closed;
    private javax.swing.JRadioButton desflu;
    private widget.TextBox diagnosa_akhir;
    private widget.TextBox diagnosa_awal;
    private widget.TextBox dilakukan;
    private javax.swing.JRadioButton drip;
    private javax.swing.JRadioButton epidural;
    private widget.TextBox hr;
    private widget.TextBox induksi1;
    private widget.TextBox induksi2;
    private widget.TextBox induksi3;
    private widget.TextBox induksi4;
    private widget.TextBox induksi5;
    private javax.swing.JRadioButton inter;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private javax.swing.JRadioButton iso;
    private widget.Label jLabel10;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel128;
    private widget.Label jLabel15;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
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
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
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
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea komplikasiakut;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label15;
    private widget.TextBox level;
    private javax.swing.JRadioButton masker;
    private widget.TextBox masukobat;
    private javax.swing.JRadioButton nadi;
    private widget.TextBox nomer;
    private javax.swing.JRadioButton nomor;
    private javax.swing.JRadioButton nt;
    private widget.TextBox oksigen;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox penanganan;
    private widget.TextBox pp;
    private javax.swing.JTextArea premidikasi;
    private javax.swing.JTextArea programanalgetik;
    private javax.swing.JTextArea programcairan;
    private widget.TextBox regional_lainnya;
    private javax.swing.JRadioButton rket;
    private widget.TextBox rr;
    private javax.swing.JRadioButton rr1;
    private widget.TextBox sao2;
    private widget.ScrollPane scrollInput;
    private javax.swing.JRadioButton semiclosed;
    private javax.swing.JRadioButton semiopen;
    private javax.swing.JRadioButton sevo;
    private javax.swing.JRadioButton spiral;
    private javax.swing.JRadioButton sr;
    private widget.TextBox suhu;
    private javax.swing.JRadioButton suhu1;
    private javax.swing.JRadioButton tangan;
    private widget.Table tbObat;
    private widget.TextBox td;
    private javax.swing.JRadioButton td1;
    private widget.TextBox tiap;
    private widget.TextBox volume;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                          "SELECT "+
        "reg_periksa.no_rawat,"+
        "pasien.no_rkm_medis,"+
        "pasien.nm_pasien,"+
        "IF(pasien.jk = 'L', 'Laki-Laki', 'Perempuan') AS jk,"+
        "pasien.tgl_lahir,"+
    "laporan_anestesi.tanggal,"+
    "laporan_anestesi.tanggal_anestesi,"+
    "laporan_anestesi.tanggal_operasi,"+
    "laporan_anestesi.kd_dokter_operator,"+
    "dokteroperator.nm_dokter AS dokteroperator,"+
    "laporan_anestesi.kd_dokter_anestesi,"+
    "dokteranestesi.nm_dokter AS dokteranestesi,"+ 
    "laporan_anestesi.diagnosa_awal,"+
    "laporan_anestesi.diagnosa_akhir,"+
    "laporan_anestesi.td,"+
    "laporan_anestesi.hr,"+
    "laporan_anestesi.rr,"+
    "laporan_anestesi.sao2,"+
    "laporan_anestesi.suhu,"+
    "laporan_anestesi.bb,"+
    "laporan_anestesi.asa,"+
    "laporan_anestesi.premedikasi,"+
    "laporan_anestesi.dilakukan,"+
    "laporan_anestesi.induksi1,"+
    "laporan_anestesi.induksi2,"+
    "laporan_anestesi.induksi3,"+
    "laporan_anestesi.induksi4,"+
    "laporan_anestesi.induksi5,"+
    "laporan_anestesi.oksigenasi,"+
    "laporan_anestesi.inhalasi,"+
    "laporan_anestesi.tiva,"+
    "laporan_anestesi.umum_inhalasi,"+
    "laporan_anestesi.umum_inhalasi2,"+
    "laporan_anestesi.nomer,"+
    "laporan_anestesi.regional,"+
    "laporan_anestesi.regional_lainnya,"+
    "laporan_anestesi.posisipuncture,"+
    "laporan_anestesi.level,"+
    "laporan_anestesi.masuk_obat,"+
    "laporan_anestesi.volume,"+
    "laporan_anestesi.komplikasi_akut,"+
    "laporan_anestesi.penanganan,"+
    "laporan_anestesi.pengawasan,"+
    "laporan_anestesi.tiap,"+
    "laporan_anestesi.program_cair,"+
    "laporan_anestesi.program_analgetik,"+
    "laporan_anestesi.catatan_lain "+
    "from reg_periksa "+
"INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "+
"INNER JOIN laporan_anestesi ON reg_periksa.no_rawat = laporan_anestesi.no_rawat "+
"INNER JOIN dokter AS dokteroperator ON dokteroperator.kd_dokter = laporan_anestesi.kd_dokter_operator "+                                  
"INNER JOIN dokter AS dokteranestesi on dokteranestesi.kd_dokter = laporan_anestesi.kd_dokter_anestesi "+

"WHERE  laporan_anestesi.tanggal BETWEEN ? AND ? ORDER BY laporan_anestesi.tanggal ");

            }else{
                ps=koneksi.prepareStatement(
                          "SELECT "+
        "reg_periksa.no_rawat,"+
        "pasien.no_rkm_medis,"+
        "pasien.nm_pasien,"+
        "IF(pasien.jk = 'L', 'Laki-Laki', 'Perempuan') AS jk,"+
        "pasien.tgl_lahir,"+
    "laporan_anestesi.tanggal,"+
    "laporan_anestesi.tanggal_anestesi,"+
    "laporan_anestesi.tanggal_operasi,"+
    "laporan_anestesi.kd_dokter_operator,"+
    "dokteroperator.nm_dokter as dokteroperator,"+
    "laporan_anestesi.kd_dokter_anestesi,"+
    "dokteranestesi.nm_dokter as dokteranestesi,"+ 
    "laporan_anestesi.diagnosa_awal,"+
    "laporan_anestesi.diagnosa_akhir,"+
    "laporan_anestesi.td,"+
    "laporan_anestesi.hr,"+
    "laporan_anestesi.rr,"+
    "laporan_anestesi.sao2,"+
    "laporan_anestesi.suhu,"+
    "laporan_anestesi.bb,"+
    "laporan_anestesi.asa,"+
    "laporan_anestesi.premedikasi,"+
    "laporan_anestesi.dilakukan,"+
    "laporan_anestesi.induksi1,"+
    "laporan_anestesi.induksi2,"+
    "laporan_anestesi.induksi3,"+
    "laporan_anestesi.induksi4,"+
    "laporan_anestesi.induksi5,"+
    "laporan_anestesi.oksigenasi,"+
    "laporan_anestesi.inhalasi,"+
    "laporan_anestesi.tiva,"+
    "laporan_anestesi.umum_inhalasi,"+
    "laporan_anestesi.umum_inhalasi2,"+
    "laporan_anestesi.nomer,"+
    "laporan_anestesi.regional,"+
    "laporan_anestesi.regional_lainnya,"+
    "laporan_anestesi.posisipuncture,"+
    "laporan_anestesi.level,"+
    "laporan_anestesi.masuk_obat,"+
    "laporan_anestesi.volume,"+
    "laporan_anestesi.komplikasi_akut,"+
    "laporan_anestesi.penanganan,"+
    "laporan_anestesi.pengawasan,"+
    "laporan_anestesi.tiap,"+
    "laporan_anestesi.program_cair,"+
    "laporan_anestesi.program_analgetik,"+
    "laporan_anestesi.catatan_lain "+
     "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join laporan_anestesi on reg_periksa.no_rawat=laporan_anestesi.no_rawat "+
                        "INNER JOIN dokter AS dokteroperator ON dokteroperator.kd_dokter = laporan_anestesi.kd_dokter_operator "+                                  
                        "INNER JOIN dokter as dokteranestesi on dokteranestesi.kd_dokter = laporan_anestesi.kd_dokter_anestesi "+
                        "where laporan_anestesi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "dokteroperator.nm_dokter like ? or dokteranestesi.nm_dokter like ?) order by laporan_anestesi.tanggal ");
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("jk"),rs.getString("tgl_lahir"),rs.getString("tanggal"),rs.getString("tanggal_anestesi"),rs.getString("tanggal_operasi"),
                        rs.getString("kd_dokter_operator"),rs.getString("dokteroperator"),rs.getString("kd_dokter_anestesi"),rs.getString("dokteranestesi"),rs.getString("diagnosa_awal"),rs.getString("diagnosa_akhir"),rs.getString("td"),rs.getString("hr"),rs.getString("rr"),
                        rs.getString("sao2"),rs.getString("suhu"),rs.getString("bb"),rs.getString("asa"),rs.getString("premedikasi"),rs.getString("dilakukan"),rs.getString("induksi1"),rs.getString("induksi2"),rs.getString("induksi3"),rs.getString("induksi4"),rs.getString("induksi5"),
                        rs.getString("oksigenasi"),rs.getString("inhalasi"),rs.getString("tiva"),rs.getString("umum_inhalasi"),rs.getString("umum_inhalasi2"),
                        rs.getString("nomer"),rs.getString("regional"),rs.getString("regional_lainnya"),rs.getString("posisipuncture"),rs.getString("level"),
                        rs.getString("masuk_obat"),rs.getString("volume"),rs.getString("komplikasi_akut"),rs.getString("penanganan"),rs.getString("pengawasan"),
                        rs.getString("tiap"),rs.getString("program_cair"),rs.getString("program_analgetik"),rs.getString("catatan_lain")
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
        KodeDokterOperator.setText("");
        NmDokterOperator.setText("");
        KodeDokterAnestesi.setText("");
        NmDokterAnestesi.setText("");
        diagnosa_awal.setText("");
        diagnosa_akhir.setText("");
        td.setText("");
        hr.setText("");
        rr.setText("");
        sao2.setText("");
        suhu.setText("");
        bb.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        buttonGroup5.clearSelection();
        buttonGroup6.clearSelection();
        buttonGroup7.clearSelection();
        premidikasi.setText("");
        dilakukan.setText("");
        induksi1.setText("");
        induksi2.setText("");
        induksi3.setText("");
        induksi4.setText("");
        induksi5.setText("");
        oksigen.setText("");
        nomer.setText("");
        regional_lainnya.setText("");
        pp.setText("");
        level.setText("");
        masukobat.setText("");
        volume.setText("");
        komplikasiakut.setText("");
        penanganan.setText("");
        tiap.setText("");
        programcairan.setText("");
        programanalgetik.setText("");
        TglAsuhan.setDate(new Date());
        TglOperasi.setDate(new Date());
       catatanlain.setText("");
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
             TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            KodeDokterOperator.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());       
            NmDokterOperator.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KodeDokterAnestesi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());       
            NmDokterAnestesi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            diagnosa_awal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            diagnosa_akhir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
             td.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
           hr.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            rr.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            sao2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            bb.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            
               String asa = tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString();
           if(asa.contains("I")){
           ASA1.setSelected(true);
        } else if (asa.contains("II")){
            ASA2.setSelected(true);
        } else if (asa.contains("III")){
            ASA3.setSelected(true);
        } else if (asa.contains("IV")){
           ASA4.setSelected(true);
        } else if (asa.contains("V")){
            ASA5.setSelected(true);
        } else if (asa.contains("E")){
          ASAE.setSelected(true);
        } 
          
           premidikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            dilakukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            induksi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            induksi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            induksi3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            induksi4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            induksi5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            oksigen.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
         
              String inhalasi = tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString();
          if(inhalasi.contains("Sevoflurance")){
            sevo.setSelected(true);
        } else if (inhalasi.contains("Isoflurance")){
            iso.setSelected(true);
        } else if (inhalasi.contains("Desflurance")){
            desflu.setSelected(true);
        }          
           
          String tiva= tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString();
            if(tiva.contains("Drip")){
           drip.setSelected(true);
        } else if (tiva.contains("Intermitten")){
            inter.setSelected(true);
        }

          String umuminhalasi = tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString();
               
            if(umuminhalasi.contains ("Semi Closed")){
           semiclosed.setSelected(true);
        } else if (umuminhalasi.contains ("Semi Open")){
           semiopen.setSelected(true);
        } else if (umuminhalasi.contains ("Closed")){
            closed.setSelected(true);
        }
               String umuminhalasi2 = tbObat.getValueAt(tbObat.getSelectedRow(), 32).toString(); 
            if(umuminhalasi2.contains ("SR")){
             sr.setSelected(true);
        } else if (umuminhalasi2.contains ("AR")){
             ar.setSelected(true);
        } else if (umuminhalasi2.contains ("RK dengan ET")){
             rket.setSelected(true);
        } else if (umuminhalasi2.contains ("NT")){
             nt.setSelected(true);
        }  else if (umuminhalasi2.contains ("Nomer")){
            nomor.setSelected(true);
        }  else if (umuminhalasi2.contains ("Masker dengan Mesin")){
            masker.setSelected(true);
        } else if (umuminhalasi2.contains ("Tangan")){
            tangan.setSelected(true);
        }
           
            nomer.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());     
           
             String regional= tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString();
        if(regional.contains ("Spiral")){
            spiral.setSelected(true);
        } else if (regional.contains ("Epidural")){
            epidural.setSelected(true);
        } else if (regional.contains ("Block Penifer")){
            blok.setSelected(true);
        }
            regional_lainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString()); 
            pp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString()); 
            level.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString()); 
            masukobat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString()); 
            volume.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString()); 
           komplikasiakut.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString()); 
            penanganan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString()); 
            
              String pengawasan= tbObat.getValueAt(tbObat.getSelectedRow(), 42).toString();
            if(regional.contains ("TD")){
            td1.setSelected(true);
        } else if (regional.contains ("Nadi")){
           nadi.setSelected(true);
        } else if (regional.contains ("RR")){
           rr1.setSelected(true);
        } else if (regional.contains ("Suhu")){
           suhu1.setSelected(true);
        }
            tiap.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString()); 
            
            programcairan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString()); 
            programanalgetik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString()); 
            catatanlain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString()); 
            
            
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            Valid.SetTgl2(TglAnestesi,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Valid.SetTgl2(TglOperasi,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            
//            Valid.SetTgl2(TglPuasa,tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
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
        BtnSimpan.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnHapus.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_anestesi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_anestesi());
       
    }
      
    
    
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from laporan_anestesi where no_rawat=? and tanggal=?",2,new String[]{
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
               String asa ="";
        String inhalasi ="";
        String tiva ="";
        String umuminhalasi="";
        String umuminhalasi2="";
        String regional="";
        String pengawasan ="";
        
        
          if(ASA1.isSelected()){
            asa = "I";
        } else if (ASA2.isSelected()){
            asa = "II";
        } else if (ASA3.isSelected()){
            asa = "III";
        } else if (ASA4.isSelected()){
            asa = "IV";
        } else if (ASA5.isSelected()){
            asa = "V";
        } else if (ASAE.isSelected()){
            asa = "E";
        } 

          
            if(sevo.isSelected()){
            inhalasi = "Sevoflurance";
        } else if (iso.isSelected()){
            inhalasi = "Isoflurance";
        } else if (desflu.isSelected()){
            inhalasi = "Desflurance";
        }

            
            if(drip.isSelected()){
            tiva = "Drip";
        } else if (inter.isSelected()){
            tiva = "Intermitten";
        }

               
            if(semiclosed.isSelected()){
            umuminhalasi = "Semi Closed";
        } else if (semiopen.isSelected()){
            umuminhalasi = "Semi Open";
        } else if (closed.isSelected()){
            umuminhalasi = "Closed";
        }
                
            if(sr.isSelected()){
            umuminhalasi2 = "SR";
        } else if (ar.isSelected()){
            umuminhalasi2 = "AR";
        } else if (rket.isSelected()){
            umuminhalasi2 = "RK dengan ET";
        } else if (nt.isSelected()){
            umuminhalasi2 = "NT";
        }  else if (nomor.isSelected()){
            umuminhalasi2 = "Nomer";
        }  else if (masker.isSelected()){
            umuminhalasi2 = "Masker dengan Mesin";
        } else if (tangan.isSelected()){
            umuminhalasi2 = "Tangan";
        }
                 
            if(spiral.isSelected()){
            regional = "Spiral";
        } else if (epidural.isSelected()){
            regional = "Epidural";
        } else if (blok.isSelected()){
            regional = "Block Penifer";
        }
            
            if(td1.isSelected()){
            pengawasan = "TD";
        } else if (nadi.isSelected()){
            pengawasan = "Nadi";
        } else if (rr1.isSelected()){
            pengawasan = "RR";
        } else if (suhu1.isSelected()){
            pengawasan = "Suhu";
        }
        
        if(Sequel.mengedittf("laporan_anestesi","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,tanggal_anestesi=?,tanggal_operasi=?,kd_dokter_operator=?,kd_dokter_anestesi=?,diagnosa_awal=?,diagnosa_akhir=?,td=?,hr=?,rr=?,sao2=?,suhu=?,bb=?,"+
                "asa=?,premedikasi=?,dilakukan=?,induksi1=?,induksi2=?,induksi3=?,induksi4=?,induksi5=?,oksigenasi=?,inhalasi=?,tiva=?,"+
                "umum_inhalasi=?,umum_inhalasi2=?,nomer=?,regional=?,regional_lainnya=?,posisipuncture=?,"+
                "level=?,masuk_obat=?,volume=?,komplikasi_akut=?,penanganan=?,pengawasan=?,"+
                "tiap=?,program_cair=?,program_analgetik=?,catatan_lain=?",43,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Valid.SetTgl(TglAnestesi.getSelectedItem()+"")+" "+TglAnestesi.getSelectedItem().toString().substring(11,19),
                    Valid.SetTgl(TglOperasi.getSelectedItem()+"")+" "+TglOperasi.getSelectedItem().toString().substring(11,19),KodeDokterOperator.getText(),KodeDokterAnestesi.getText(),diagnosa_awal.getText(),diagnosa_akhir.getText(), 
                    td.getText(),hr.getText(),rr.getText(),sao2.getText(),suhu.getText(),bb.getText(),asa,
                    premidikasi.getText(),dilakukan.getText(),induksi1.getText(),induksi2.getText(),induksi3.getText(),induksi4.getText(),induksi5.getText(), 
                    oksigen.getText(),inhalasi,tiva,umuminhalasi,umuminhalasi2,nomer.getText(), 
                    regional,regional_lainnya.getText(),pp.getText(),
                    level.getText(),masukobat.getText(),volume.getText(),komplikasiakut.getText(),penanganan.getText(),
                    pengawasan,tiap.getText(),programcairan.getText(), 
                    programanalgetik.getText(),catatanlain.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(), tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
