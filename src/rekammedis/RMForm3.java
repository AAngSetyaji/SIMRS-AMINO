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
           "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Riwayat 1","Skor Riwayat 1","Riwayat 2","Skor Riwayat 2","Saudara Kandung / Tiri","Skor Saudara","Ayah / Ibu","Skor Ayah / Ibu", 
           "Pasangan","Skor Pasangan","Om / Tante","Skor Om / Tante","Teman","Skor Teman","Lainnya","Nilai Lainnya","Skor Lainnya","Ibu (30 hari)","Ibu (Sepanjang hidup)","Ayah (30 hari)","Ayah (Sepanjang hidup)","Adik / Kakak (30 hari)","Adik / Kakak (Sepanjang hidup)","Pasangan (30 hari)","Pasangan (Sepanjang hidup)","Anak (30 hari)","Anak (Sepanjang hidup)",
           "Keluarga (30 hari)","Keluarga (Sepanjang hari)","Teman Akrab (30 hari)","Teman Akrab (Sepanjang hidup)","Tetangga (30 hari)","Tetangga (Sepanjang hidup)","Teman Sekerja (30 hari)","Teman Sekerja (Sepanjang hidup)","Total Skor 30 hari terakhir","Total Skor Sepanjang hidup","Skala Penilaian Riwayat Pasien",
           "Status 1 (30 hari)","Status 1 (Sepanjang hidup)","Status 2 (30 hari)","Status 2 (Sepanjang hidup)","Status 3 (30 hari)","Status 3 (Sepanjang hidup)","Status 4 (30 hari)","Status 4 (Sepanjang hidup)","Status 5 (30 hari)","Status 5 (Sepanjang hidup)","Status 6 (30 hari)","Status 6 (Sepanjang hidup)","Status 7 (30 hari)","Status 7 (Sepanjang hidup)","Status 8 (30 hari)","Status 8 (Sepanjang hidup)",
           "Total Skor 30 hari terakhir","Total Skor Sepanjang hidup","Skala Penilaian Status Psikiatris Pasien",
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 64; i++) {
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
                column.setPreferredWidth(150);
            }else if(i==32){
                column.setPreferredWidth(150);
            }else if(i==33){
                column.setPreferredWidth(170);
            }else if(i==34){
                column.setPreferredWidth(150);
            }else if(i==35){
                column.setPreferredWidth(170);
            }else if(i==36){
                column.setPreferredWidth(170);
            }else if(i==37){
                column.setPreferredWidth(170);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(115);
            }else if(i==40){
                column.setPreferredWidth(170);
            }else if(i==41){
                column.setPreferredWidth(50);
            }else if(i==42){
                column.setPreferredWidth(170);
            }else if(i==43){
                column.setPreferredWidth(170);
            }else if(i==44){
                column.setPreferredWidth(170);
            }else if(i==45){
                column.setPreferredWidth(170);
            }else if(i==46){
                column.setPreferredWidth(170);
            }else if(i==47){
                column.setPreferredWidth(170);
            }else if(i==48){
                column.setPreferredWidth(170);
            }else if(i==49){
                column.setPreferredWidth(170);
            }else if(i==50){
                column.setPreferredWidth(170);
            }else if(i==51){
                column.setPreferredWidth(170);
            }else if(i==52){
                column.setPreferredWidth(170);
            }else if(i==53){
                column.setPreferredWidth(170);
            }else if(i==54){
                column.setPreferredWidth(170);
            }else if(i==55){
                column.setPreferredWidth(170);
            }else if(i==56){
                column.setPreferredWidth(170);
            }else if(i==57){
                column.setPreferredWidth(170);
            }else if(i==58){
                column.setPreferredWidth(170);
            }else if(i==59){
                column.setPreferredWidth(170);
            }else if(i==60){
                column.setPreferredWidth(170);
            }else if(i==61){
                column.setPreferredWidth(170);
            }else if(i==62){
                column.setPreferredWidth(150);
            }else if(i==63){
                column.setPreferredWidth(100);
            }else if(i==64){
                column.setPreferredWidth(100);
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
        jLabel30 = new widget.Label();
        riwayat3a = new widget.ComboBox();
        riwayat1 = new widget.ComboBox();
        jLabel27 = new widget.Label();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        riwayat2 = new widget.ComboBox();
        jLabel52 = new widget.Label();
        riwayat3b = new widget.ComboBox();
        jLabel53 = new widget.Label();
        riwayat3c = new widget.ComboBox();
        riwayat3d = new widget.ComboBox();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        riwayat3e = new widget.ComboBox();
        jLabel56 = new widget.Label();
        riwayat3f = new widget.ComboBox();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        skor9 = new widget.TextBox();
        jLabel60 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        cmbIbua = new widget.ComboBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        cmbIbub = new widget.ComboBox();
        jLabel65 = new widget.Label();
        cmbAyaha = new widget.ComboBox();
        cmbAyahb = new widget.ComboBox();
        jLabel66 = new widget.Label();
        cmbAdika = new widget.ComboBox();
        cmbAdikb = new widget.ComboBox();
        jLabel67 = new widget.Label();
        cmbPasangana = new widget.ComboBox();
        cmbPasanganb = new widget.ComboBox();
        jLabel68 = new widget.Label();
        cmbAnakb = new widget.ComboBox();
        cmbAnaka = new widget.ComboBox();
        cmbKeluargaa = new widget.ComboBox();
        jLabel69 = new widget.Label();
        cmbKeluargab = new widget.ComboBox();
        cmbTemana = new widget.ComboBox();
        jLabel70 = new widget.Label();
        cmbTemanb = new widget.ComboBox();
        jLabel71 = new widget.Label();
        cmbTetanggaa = new widget.ComboBox();
        cmbTetanggab = new widget.ComboBox();
        jLabel72 = new widget.Label();
        cmbKerjaa = new widget.ComboBox();
        cmbKerjab = new widget.ComboBox();
        jLabel73 = new widget.Label();
        skala1 = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel15 = new widget.Label();
        jLabel75 = new widget.Label();
        skor10 = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        stts1a = new widget.ComboBox();
        stts1b = new widget.ComboBox();
        stts2a = new widget.ComboBox();
        stts2b = new widget.ComboBox();
        jLabel80 = new widget.Label();
        jLabel79 = new widget.Label();
        stts3a = new widget.ComboBox();
        stts3b = new widget.ComboBox();
        jLabel81 = new widget.Label();
        stts4a = new widget.ComboBox();
        stts4b = new widget.ComboBox();
        jLabel82 = new widget.Label();
        stts5a = new widget.ComboBox();
        stts5b = new widget.ComboBox();
        jLabel83 = new widget.Label();
        stts6a = new widget.ComboBox();
        stts6b = new widget.ComboBox();
        jLabel84 = new widget.Label();
        stts7a = new widget.ComboBox();
        stts7b = new widget.ComboBox();
        jLabel85 = new widget.Label();
        stts8a = new widget.ComboBox();
        stts8b = new widget.ComboBox();
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
        lainnya = new widget.TextBox();
        skor5 = new widget.TextBox();
        skor6 = new widget.TextBox();
        skor7 = new widget.TextBox();
        skor8 = new widget.TextBox();
        skorIbua = new widget.TextBox();
        skorAyaha = new widget.TextBox();
        skorAdika = new widget.TextBox();
        skorPasangana = new widget.TextBox();
        skorAnaka = new widget.TextBox();
        skorKeluargaa = new widget.TextBox();
        skorKerjaa = new widget.TextBox();
        skorTetanggaa = new widget.TextBox();
        skorTemana = new widget.TextBox();
        skorIbub = new widget.TextBox();
        skorAyahb = new widget.TextBox();
        skorAdikb = new widget.TextBox();
        skorPasanganb = new widget.TextBox();
        skorAnakb = new widget.TextBox();
        skorKeluargab = new widget.TextBox();
        skorTemanb = new widget.TextBox();
        skorTetanggab = new widget.TextBox();
        skorKerjab = new widget.TextBox();
        skorStts1a = new widget.TextBox();
        skorStts2a = new widget.TextBox();
        skorStts3a = new widget.TextBox();
        skorStts4a = new widget.TextBox();
        skorStts5a = new widget.TextBox();
        skorStts6a = new widget.TextBox();
        skorStts7a = new widget.TextBox();
        skorStts8a = new widget.TextBox();
        skorStts1b = new widget.TextBox();
        skorStts2b = new widget.TextBox();
        skorStts3b = new widget.TextBox();
        skorStts4b = new widget.TextBox();
        skorStts5b = new widget.TextBox();
        skorStts6b = new widget.TextBox();
        skorStts7b = new widget.TextBox();
        skorStts8b = new widget.TextBox();
        skor1 = new widget.TextBox();
        skor2 = new widget.TextBox();
        skor3 = new widget.TextBox();
        skor4 = new widget.TextBox();
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
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1600));
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
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023 15:25:58" }));
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

        jLabel30.setText("2. Apakah anda hidup dengan seseorang yang mempunyai masalah penyalahgunaan zat sekarang ini?");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 190, 520, 23);

        riwayat3a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat3a.setName("riwayat3a"); // NOI18N
        riwayat3a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat3aItemStateChanged(evt);
            }
        });
        riwayat3a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat3aActionPerformed(evt);
            }
        });
        FormInput.add(riwayat3a);
        riwayat3a.setBounds(190, 300, 80, 20);

        riwayat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Dengan pasangan dan anak", "Dengan pasangan saja", "Dengan anak saja", "Dengan orang tua", "Dengan Keluarga", "Dengan teman", "Sendiri", "Lingkungan terkontrol", "Kondisi yang tidak stabil" }));
        riwayat1.setName("riwayat1"); // NOI18N
        riwayat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat1ItemStateChanged(evt);
            }
        });
        riwayat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat1ActionPerformed(evt);
            }
        });
        FormInput.add(riwayat1);
        riwayat1.setBounds(60, 140, 300, 20);

        jLabel27.setText("Skor :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(410, 220, 80, 23);

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

        riwayat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat2.setName("riwayat2"); // NOI18N
        riwayat2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat2ItemStateChanged(evt);
            }
        });
        riwayat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat2ActionPerformed(evt);
            }
        });
        FormInput.add(riwayat2);
        riwayat2.setBounds(60, 220, 80, 20);

        jLabel52.setText("Ayah / Ibu");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 330, 170, 23);

        riwayat3b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat3b.setName("riwayat3b"); // NOI18N
        riwayat3b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat3bItemStateChanged(evt);
            }
        });
        riwayat3b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat3bActionPerformed(evt);
            }
        });
        FormInput.add(riwayat3b);
        riwayat3b.setBounds(190, 330, 80, 20);

        jLabel53.setText("Pasangan");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 360, 170, 23);

        riwayat3c.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat3c.setName("riwayat3c"); // NOI18N
        riwayat3c.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat3cItemStateChanged(evt);
            }
        });
        riwayat3c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat3cActionPerformed(evt);
            }
        });
        FormInput.add(riwayat3c);
        riwayat3c.setBounds(190, 360, 80, 20);

        riwayat3d.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat3d.setName("riwayat3d"); // NOI18N
        riwayat3d.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat3dItemStateChanged(evt);
            }
        });
        riwayat3d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat3dActionPerformed(evt);
            }
        });
        FormInput.add(riwayat3d);
        riwayat3d.setBounds(190, 390, 80, 20);

        jLabel54.setText("Om / Tante");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 390, 170, 23);

        jLabel55.setText("Teman");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 420, 170, 23);

        riwayat3e.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat3e.setName("riwayat3e"); // NOI18N
        riwayat3e.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat3eItemStateChanged(evt);
            }
        });
        riwayat3e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat3eActionPerformed(evt);
            }
        });
        FormInput.add(riwayat3e);
        riwayat3e.setBounds(190, 420, 80, 20);

        jLabel56.setText("Lainnya :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 450, 170, 23);

        riwayat3f.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih --", "Tidak", "Ya" }));
        riwayat3f.setName("riwayat3f"); // NOI18N
        riwayat3f.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                riwayat3fItemStateChanged(evt);
            }
        });
        riwayat3f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayat3fActionPerformed(evt);
            }
        });
        FormInput.add(riwayat3f);
        riwayat3f.setBounds(320, 450, 80, 20);

        jLabel28.setText("Skor :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(410, 300, 80, 23);

        jLabel29.setText("Skor :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(410, 330, 80, 23);

        jLabel57.setText("Skor :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(410, 360, 80, 23);

        jLabel58.setText("Skor :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(410, 390, 80, 23);

        jLabel59.setText("Skor :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(410, 420, 80, 23);

        skor9.setEditable(false);
        skor9.setName("skor9"); // NOI18N
        skor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor9ActionPerformed(evt);
            }
        });
        FormInput.add(skor9);
        skor9.setBounds(490, 860, 80, 24);

        jLabel60.setText("Skor :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(410, 450, 80, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(10, 510, 750, 1);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(10, 510, 750, 1);

        jLabel61.setText("Apakah anda pernah mengalami hal - hal berikut ini ( yang bukan akhibat langsung dari penggunaan Napza )");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 990, 550, 23);

        jLabel62.setText("30 hari terakhir");
        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(240, 550, 130, 23);

        cmbIbua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbIbua.setName("cmbIbua"); // NOI18N
        cmbIbua.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIbuaItemStateChanged(evt);
            }
        });
        cmbIbua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIbuaActionPerformed(evt);
            }
        });
        FormInput.add(cmbIbua);
        cmbIbua.setBounds(290, 580, 80, 20);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("1. Ibu");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(90, 580, 150, 23);

        jLabel64.setText("Sepanjang hidup");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(440, 550, 130, 23);

        cmbIbub.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbIbub.setName("cmbIbub"); // NOI18N
        cmbIbub.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIbubItemStateChanged(evt);
            }
        });
        cmbIbub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIbubActionPerformed(evt);
            }
        });
        FormInput.add(cmbIbub);
        cmbIbub.setBounds(490, 580, 80, 20);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("2. Ayah");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(90, 610, 150, 23);

        cmbAyaha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAyaha.setName("cmbAyaha"); // NOI18N
        cmbAyaha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAyahaItemStateChanged(evt);
            }
        });
        cmbAyaha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAyahaActionPerformed(evt);
            }
        });
        FormInput.add(cmbAyaha);
        cmbAyaha.setBounds(290, 610, 80, 20);

        cmbAyahb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAyahb.setName("cmbAyahb"); // NOI18N
        cmbAyahb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAyahbItemStateChanged(evt);
            }
        });
        cmbAyahb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAyahbActionPerformed(evt);
            }
        });
        FormInput.add(cmbAyahb);
        cmbAyahb.setBounds(490, 610, 80, 20);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("3. Adik / Kakak");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(90, 640, 150, 23);

        cmbAdika.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAdika.setName("cmbAdika"); // NOI18N
        cmbAdika.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAdikaItemStateChanged(evt);
            }
        });
        cmbAdika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAdikaActionPerformed(evt);
            }
        });
        FormInput.add(cmbAdika);
        cmbAdika.setBounds(290, 640, 80, 20);

        cmbAdikb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAdikb.setName("cmbAdikb"); // NOI18N
        cmbAdikb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAdikbItemStateChanged(evt);
            }
        });
        cmbAdikb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAdikbActionPerformed(evt);
            }
        });
        FormInput.add(cmbAdikb);
        cmbAdikb.setBounds(490, 640, 80, 20);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("4. Pasangan");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(90, 670, 150, 23);

        cmbPasangana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPasangana.setName("cmbPasangana"); // NOI18N
        cmbPasangana.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPasanganaItemStateChanged(evt);
            }
        });
        cmbPasangana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPasanganaActionPerformed(evt);
            }
        });
        FormInput.add(cmbPasangana);
        cmbPasangana.setBounds(290, 670, 80, 20);

        cmbPasanganb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPasanganb.setName("cmbPasanganb"); // NOI18N
        cmbPasanganb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPasanganbItemStateChanged(evt);
            }
        });
        cmbPasanganb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPasanganbActionPerformed(evt);
            }
        });
        FormInput.add(cmbPasanganb);
        cmbPasanganb.setBounds(490, 670, 80, 20);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("5. Anak - anak");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(90, 700, 150, 23);

        cmbAnakb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAnakb.setName("cmbAnakb"); // NOI18N
        cmbAnakb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAnakbItemStateChanged(evt);
            }
        });
        cmbAnakb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAnakbActionPerformed(evt);
            }
        });
        FormInput.add(cmbAnakb);
        cmbAnakb.setBounds(490, 700, 80, 20);

        cmbAnaka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAnaka.setName("cmbAnaka"); // NOI18N
        cmbAnaka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAnakaItemStateChanged(evt);
            }
        });
        cmbAnaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAnakaActionPerformed(evt);
            }
        });
        FormInput.add(cmbAnaka);
        cmbAnaka.setBounds(290, 700, 80, 20);

        cmbKeluargaa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKeluargaa.setName("cmbKeluargaa"); // NOI18N
        cmbKeluargaa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKeluargaaItemStateChanged(evt);
            }
        });
        cmbKeluargaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluargaaActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluargaa);
        cmbKeluargaa.setBounds(290, 730, 80, 20);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("6. Keluarga lain yang berarti");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(90, 730, 150, 23);

        cmbKeluargab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKeluargab.setName("cmbKeluargab"); // NOI18N
        cmbKeluargab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKeluargabItemStateChanged(evt);
            }
        });
        cmbKeluargab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeluargabActionPerformed(evt);
            }
        });
        FormInput.add(cmbKeluargab);
        cmbKeluargab.setBounds(490, 730, 80, 20);

        cmbTemana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbTemana.setName("cmbTemana"); // NOI18N
        cmbTemana.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTemanaItemStateChanged(evt);
            }
        });
        cmbTemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTemanaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTemana);
        cmbTemana.setBounds(290, 760, 80, 20);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("7. Teman akrab");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(90, 760, 150, 23);

        cmbTemanb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbTemanb.setName("cmbTemanb"); // NOI18N
        cmbTemanb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTemanbItemStateChanged(evt);
            }
        });
        cmbTemanb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTemanbActionPerformed(evt);
            }
        });
        FormInput.add(cmbTemanb);
        cmbTemanb.setBounds(490, 760, 80, 20);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("8. Tetangga");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(90, 790, 150, 23);

        cmbTetanggaa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbTetanggaa.setName("cmbTetanggaa"); // NOI18N
        cmbTetanggaa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTetanggaaItemStateChanged(evt);
            }
        });
        cmbTetanggaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTetanggaaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTetanggaa);
        cmbTetanggaa.setBounds(290, 790, 80, 20);

        cmbTetanggab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbTetanggab.setName("cmbTetanggab"); // NOI18N
        cmbTetanggab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTetanggabItemStateChanged(evt);
            }
        });
        cmbTetanggab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTetanggabActionPerformed(evt);
            }
        });
        FormInput.add(cmbTetanggab);
        cmbTetanggab.setBounds(490, 790, 80, 20);

        jLabel72.setText("Skala Penilaian Pasien :");
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(100, 900, 180, 23);

        cmbKerjaa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKerjaa.setName("cmbKerjaa"); // NOI18N
        cmbKerjaa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKerjaaItemStateChanged(evt);
            }
        });
        cmbKerjaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKerjaaActionPerformed(evt);
            }
        });
        FormInput.add(cmbKerjaa);
        cmbKerjaa.setBounds(290, 820, 80, 20);

        cmbKerjab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKerjab.setName("cmbKerjab"); // NOI18N
        cmbKerjab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKerjabItemStateChanged(evt);
            }
        });
        cmbKerjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKerjabActionPerformed(evt);
            }
        });
        FormInput.add(cmbKerjab);
        cmbKerjab.setBounds(490, 820, 80, 20);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("9. Teman sekerja");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(90, 820, 150, 23);

        skala1.setEditable(false);
        skala1.setName("skala1"); // NOI18N
        skala1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skala1ActionPerformed(evt);
            }
        });
        FormInput.add(skala1);
        skala1.setBounds(290, 900, 80, 24);

        jLabel74.setText("4. Apakah anda memiliki konflik serius dalam berhubungan dengan :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 520, 350, 23);

        jLabel15.setText("RIWAYAT KELUARGA / SOSIAL");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 80, 190, 23);

        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(100, 860, 180, 23);

        skor10.setEditable(false);
        skor10.setName("skor10"); // NOI18N
        skor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor10ActionPerformed(evt);
            }
        });
        FormInput.add(skor10);
        skor10.setBounds(290, 860, 80, 24);

        jLabel76.setText("30 hari terakhir");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(340, 1020, 130, 23);

        jLabel77.setText("Sepanjang hidup");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(520, 1020, 130, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("<html>Mengalami rasa cemas serius / ketegangan, gelisah,<br> merasa khawatir berlebihan?</html>");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(80, 1090, 300, 23);

        stts1a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts1a.setName("stts1a"); // NOI18N
        stts1a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts1aItemStateChanged(evt);
            }
        });
        stts1a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts1aActionPerformed(evt);
            }
        });
        FormInput.add(stts1a);
        stts1a.setBounds(390, 1050, 80, 20);

        stts1b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts1b.setName("stts1b"); // NOI18N
        stts1b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts1bItemStateChanged(evt);
            }
        });
        stts1b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts1bActionPerformed(evt);
            }
        });
        FormInput.add(stts1b);
        stts1b.setBounds(570, 1050, 80, 20);

        stts2a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts2a.setName("stts2a"); // NOI18N
        stts2a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts2aItemStateChanged(evt);
            }
        });
        stts2a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts2aActionPerformed(evt);
            }
        });
        FormInput.add(stts2a);
        stts2a.setBounds(390, 1090, 80, 20);

        stts2b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts2b.setName("stts2b"); // NOI18N
        stts2b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts2bItemStateChanged(evt);
            }
        });
        stts2b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts2bActionPerformed(evt);
            }
        });
        FormInput.add(stts2b);
        stts2b.setBounds(570, 1090, 80, 20);

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

        stts3a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts3a.setName("stts3a"); // NOI18N
        stts3a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts3aItemStateChanged(evt);
            }
        });
        stts3a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts3aActionPerformed(evt);
            }
        });
        FormInput.add(stts3a);
        stts3a.setBounds(390, 1130, 80, 20);

        stts3b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts3b.setName("stts3b"); // NOI18N
        stts3b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts3bItemStateChanged(evt);
            }
        });
        stts3b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts3bActionPerformed(evt);
            }
        });
        FormInput.add(stts3b);
        stts3b.setBounds(570, 1130, 80, 20);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("Mengalami kesulitan mengingat atau fokus pada sesuatu");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(80, 1170, 300, 23);

        stts4a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts4a.setName("stts4a"); // NOI18N
        stts4a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts4aItemStateChanged(evt);
            }
        });
        stts4a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts4aActionPerformed(evt);
            }
        });
        FormInput.add(stts4a);
        stts4a.setBounds(390, 1170, 80, 20);

        stts4b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts4b.setName("stts4b"); // NOI18N
        stts4b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts4bItemStateChanged(evt);
            }
        });
        stts4b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts4bActionPerformed(evt);
            }
        });
        FormInput.add(stts4b);
        stts4b.setBounds(570, 1170, 80, 20);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("<html>Mengalami kesukaran mengontrol perilaku kasar,<br> termasuk kemarahan atau kekerasan</html>");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(80, 1210, 300, 23);

        stts5a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts5a.setName("stts5a"); // NOI18N
        stts5a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts5aItemStateChanged(evt);
            }
        });
        stts5a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts5aActionPerformed(evt);
            }
        });
        FormInput.add(stts5a);
        stts5a.setBounds(390, 1210, 80, 20);

        stts5b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts5b.setName("stts5b"); // NOI18N
        stts5b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts5bItemStateChanged(evt);
            }
        });
        stts5b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts5bActionPerformed(evt);
            }
        });
        FormInput.add(stts5b);
        stts5b.setBounds(570, 1210, 80, 20);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("Mengalami pikiran serius untuk bunuh diri ?");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(80, 1250, 300, 23);

        stts6a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts6a.setName("stts6a"); // NOI18N
        stts6a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts6aItemStateChanged(evt);
            }
        });
        stts6a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts6aActionPerformed(evt);
            }
        });
        FormInput.add(stts6a);
        stts6a.setBounds(390, 1250, 80, 20);

        stts6b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts6b.setName("stts6b"); // NOI18N
        stts6b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts6bItemStateChanged(evt);
            }
        });
        stts6b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts6bActionPerformed(evt);
            }
        });
        FormInput.add(stts6b);
        stts6b.setBounds(570, 1250, 80, 20);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("8.");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(50, 1330, 20, 23);

        stts7a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts7a.setName("stts7a"); // NOI18N
        stts7a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts7aItemStateChanged(evt);
            }
        });
        stts7a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts7aActionPerformed(evt);
            }
        });
        FormInput.add(stts7a);
        stts7a.setBounds(390, 1290, 80, 20);

        stts7b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts7b.setName("stts7b"); // NOI18N
        stts7b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts7bItemStateChanged(evt);
            }
        });
        stts7b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts7bActionPerformed(evt);
            }
        });
        FormInput.add(stts7b);
        stts7b.setBounds(570, 1290, 80, 20);

        jLabel85.setText("Skor :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(80, 1370, 290, 23);

        stts8a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts8a.setName("stts8a"); // NOI18N
        stts8a.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts8aItemStateChanged(evt);
            }
        });
        stts8a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts8aActionPerformed(evt);
            }
        });
        FormInput.add(stts8a);
        stts8a.setBounds(390, 1330, 80, 20);

        stts8b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        stts8b.setName("stts8b"); // NOI18N
        stts8b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stts8bItemStateChanged(evt);
            }
        });
        stts8b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stts8bActionPerformed(evt);
            }
        });
        FormInput.add(stts8b);
        stts8b.setBounds(570, 1330, 80, 20);

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

        skor12.setEditable(false);
        skor12.setName("skor12"); // NOI18N
        skor12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor12ActionPerformed(evt);
            }
        });
        FormInput.add(skor12);
        skor12.setBounds(390, 1370, 80, 24);

        skor13.setEditable(false);
        skor13.setName("skor13"); // NOI18N
        skor13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor13ActionPerformed(evt);
            }
        });
        FormInput.add(skor13);
        skor13.setBounds(570, 1370, 80, 24);

        skor14.setEditable(false);
        skor14.setName("skor14"); // NOI18N
        skor14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor14ActionPerformed(evt);
            }
        });
        FormInput.add(skor14);
        skor14.setBounds(390, 1410, 80, 24);

        jLabel95.setText("Skala Penilaian Pasien :");
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(190, 1410, 180, 23);

        lainnya.setName("lainnya"); // NOI18N
        FormInput.add(lainnya);
        lainnya.setBounds(190, 450, 110, 24);

        skor5.setEditable(false);
        skor5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor5.setText("0");
        skor5.setFocusTraversalPolicyProvider(true);
        skor5.setName("skor5"); // NOI18N
        skor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor5ActionPerformed(evt);
            }
        });
        FormInput.add(skor5);
        skor5.setBounds(500, 360, 35, 23);

        skor6.setEditable(false);
        skor6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor6.setText("0");
        skor6.setFocusTraversalPolicyProvider(true);
        skor6.setName("skor6"); // NOI18N
        skor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor6ActionPerformed(evt);
            }
        });
        FormInput.add(skor6);
        skor6.setBounds(500, 390, 35, 23);

        skor7.setEditable(false);
        skor7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor7.setText("0");
        skor7.setFocusTraversalPolicyProvider(true);
        skor7.setName("skor7"); // NOI18N
        skor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor7ActionPerformed(evt);
            }
        });
        FormInput.add(skor7);
        skor7.setBounds(500, 420, 35, 23);

        skor8.setEditable(false);
        skor8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor8.setText("0");
        skor8.setFocusTraversalPolicyProvider(true);
        skor8.setName("skor8"); // NOI18N
        skor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor8ActionPerformed(evt);
            }
        });
        FormInput.add(skor8);
        skor8.setBounds(500, 450, 35, 23);

        skorIbua.setEditable(false);
        skorIbua.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorIbua.setText("0");
        skorIbua.setFocusTraversalPolicyProvider(true);
        skorIbua.setName("skorIbua"); // NOI18N
        skorIbua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorIbuaActionPerformed(evt);
            }
        });
        FormInput.add(skorIbua);
        skorIbua.setBounds(390, 580, 35, 23);

        skorAyaha.setEditable(false);
        skorAyaha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorAyaha.setText("0");
        skorAyaha.setFocusTraversalPolicyProvider(true);
        skorAyaha.setName("skorAyaha"); // NOI18N
        skorAyaha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorAyahaActionPerformed(evt);
            }
        });
        FormInput.add(skorAyaha);
        skorAyaha.setBounds(390, 610, 35, 23);

        skorAdika.setEditable(false);
        skorAdika.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorAdika.setText("0");
        skorAdika.setFocusTraversalPolicyProvider(true);
        skorAdika.setName("skorAdika"); // NOI18N
        skorAdika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorAdikaActionPerformed(evt);
            }
        });
        FormInput.add(skorAdika);
        skorAdika.setBounds(390, 640, 35, 23);

        skorPasangana.setEditable(false);
        skorPasangana.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorPasangana.setText("0");
        skorPasangana.setFocusTraversalPolicyProvider(true);
        skorPasangana.setName("skorPasangana"); // NOI18N
        skorPasangana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorPasanganaActionPerformed(evt);
            }
        });
        FormInput.add(skorPasangana);
        skorPasangana.setBounds(390, 670, 35, 23);

        skorAnaka.setEditable(false);
        skorAnaka.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorAnaka.setText("0");
        skorAnaka.setFocusTraversalPolicyProvider(true);
        skorAnaka.setName("skorAnaka"); // NOI18N
        skorAnaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorAnakaActionPerformed(evt);
            }
        });
        FormInput.add(skorAnaka);
        skorAnaka.setBounds(390, 700, 35, 23);

        skorKeluargaa.setEditable(false);
        skorKeluargaa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorKeluargaa.setText("0");
        skorKeluargaa.setFocusTraversalPolicyProvider(true);
        skorKeluargaa.setName("skorKeluargaa"); // NOI18N
        skorKeluargaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorKeluargaaActionPerformed(evt);
            }
        });
        FormInput.add(skorKeluargaa);
        skorKeluargaa.setBounds(390, 730, 35, 23);

        skorKerjaa.setEditable(false);
        skorKerjaa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorKerjaa.setText("0");
        skorKerjaa.setFocusTraversalPolicyProvider(true);
        skorKerjaa.setName("skorKerjaa"); // NOI18N
        skorKerjaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorKerjaaActionPerformed(evt);
            }
        });
        FormInput.add(skorKerjaa);
        skorKerjaa.setBounds(390, 820, 35, 23);

        skorTetanggaa.setEditable(false);
        skorTetanggaa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorTetanggaa.setText("0");
        skorTetanggaa.setFocusTraversalPolicyProvider(true);
        skorTetanggaa.setName("skorTetanggaa"); // NOI18N
        skorTetanggaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorTetanggaaActionPerformed(evt);
            }
        });
        FormInput.add(skorTetanggaa);
        skorTetanggaa.setBounds(390, 790, 35, 23);

        skorTemana.setEditable(false);
        skorTemana.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorTemana.setText("0");
        skorTemana.setFocusTraversalPolicyProvider(true);
        skorTemana.setName("skorTemana"); // NOI18N
        skorTemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorTemanaActionPerformed(evt);
            }
        });
        FormInput.add(skorTemana);
        skorTemana.setBounds(390, 760, 35, 23);

        skorIbub.setEditable(false);
        skorIbub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorIbub.setText("0");
        skorIbub.setFocusTraversalPolicyProvider(true);
        skorIbub.setName("skorIbub"); // NOI18N
        skorIbub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorIbubActionPerformed(evt);
            }
        });
        FormInput.add(skorIbub);
        skorIbub.setBounds(590, 580, 35, 23);

        skorAyahb.setEditable(false);
        skorAyahb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorAyahb.setText("0");
        skorAyahb.setFocusTraversalPolicyProvider(true);
        skorAyahb.setName("skorAyahb"); // NOI18N
        skorAyahb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorAyahbActionPerformed(evt);
            }
        });
        FormInput.add(skorAyahb);
        skorAyahb.setBounds(590, 610, 35, 23);

        skorAdikb.setEditable(false);
        skorAdikb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorAdikb.setText("0");
        skorAdikb.setFocusTraversalPolicyProvider(true);
        skorAdikb.setName("skorAdikb"); // NOI18N
        skorAdikb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorAdikbActionPerformed(evt);
            }
        });
        FormInput.add(skorAdikb);
        skorAdikb.setBounds(590, 640, 35, 23);

        skorPasanganb.setEditable(false);
        skorPasanganb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorPasanganb.setText("0");
        skorPasanganb.setFocusTraversalPolicyProvider(true);
        skorPasanganb.setName("skorPasanganb"); // NOI18N
        skorPasanganb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorPasanganbActionPerformed(evt);
            }
        });
        FormInput.add(skorPasanganb);
        skorPasanganb.setBounds(590, 670, 35, 23);

        skorAnakb.setEditable(false);
        skorAnakb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorAnakb.setText("0");
        skorAnakb.setFocusTraversalPolicyProvider(true);
        skorAnakb.setName("skorAnakb"); // NOI18N
        skorAnakb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorAnakbActionPerformed(evt);
            }
        });
        FormInput.add(skorAnakb);
        skorAnakb.setBounds(590, 700, 35, 23);

        skorKeluargab.setEditable(false);
        skorKeluargab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorKeluargab.setText("0");
        skorKeluargab.setFocusTraversalPolicyProvider(true);
        skorKeluargab.setName("skorKeluargab"); // NOI18N
        skorKeluargab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorKeluargabActionPerformed(evt);
            }
        });
        FormInput.add(skorKeluargab);
        skorKeluargab.setBounds(590, 730, 35, 23);

        skorTemanb.setEditable(false);
        skorTemanb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorTemanb.setText("0");
        skorTemanb.setFocusTraversalPolicyProvider(true);
        skorTemanb.setName("skorTemanb"); // NOI18N
        skorTemanb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorTemanbActionPerformed(evt);
            }
        });
        FormInput.add(skorTemanb);
        skorTemanb.setBounds(590, 760, 35, 23);

        skorTetanggab.setEditable(false);
        skorTetanggab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorTetanggab.setText("0");
        skorTetanggab.setFocusTraversalPolicyProvider(true);
        skorTetanggab.setName("skorTetanggab"); // NOI18N
        skorTetanggab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorTetanggabActionPerformed(evt);
            }
        });
        FormInput.add(skorTetanggab);
        skorTetanggab.setBounds(590, 790, 35, 23);

        skorKerjab.setEditable(false);
        skorKerjab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorKerjab.setText("0");
        skorKerjab.setFocusTraversalPolicyProvider(true);
        skorKerjab.setName("skorKerjab"); // NOI18N
        skorKerjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorKerjabActionPerformed(evt);
            }
        });
        FormInput.add(skorKerjab);
        skorKerjab.setBounds(590, 820, 35, 23);

        skorStts1a.setEditable(false);
        skorStts1a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts1a.setText("0");
        skorStts1a.setFocusTraversalPolicyProvider(true);
        skorStts1a.setName("skorStts1a"); // NOI18N
        skorStts1a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts1aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts1a);
        skorStts1a.setBounds(480, 1050, 35, 23);

        skorStts2a.setEditable(false);
        skorStts2a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts2a.setText("0");
        skorStts2a.setFocusTraversalPolicyProvider(true);
        skorStts2a.setName("skorStts2a"); // NOI18N
        skorStts2a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts2aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts2a);
        skorStts2a.setBounds(480, 1090, 35, 23);

        skorStts3a.setEditable(false);
        skorStts3a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts3a.setText("0");
        skorStts3a.setFocusTraversalPolicyProvider(true);
        skorStts3a.setName("skorStts3a"); // NOI18N
        skorStts3a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts3aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts3a);
        skorStts3a.setBounds(480, 1130, 35, 23);

        skorStts4a.setEditable(false);
        skorStts4a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts4a.setText("0");
        skorStts4a.setFocusTraversalPolicyProvider(true);
        skorStts4a.setName("skorStts4a"); // NOI18N
        skorStts4a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts4aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts4a);
        skorStts4a.setBounds(480, 1170, 35, 23);

        skorStts5a.setEditable(false);
        skorStts5a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts5a.setText("0");
        skorStts5a.setFocusTraversalPolicyProvider(true);
        skorStts5a.setName("skorStts5a"); // NOI18N
        skorStts5a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts5aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts5a);
        skorStts5a.setBounds(480, 1210, 35, 23);

        skorStts6a.setEditable(false);
        skorStts6a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts6a.setText("0");
        skorStts6a.setFocusTraversalPolicyProvider(true);
        skorStts6a.setName("skorStts6a"); // NOI18N
        skorStts6a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts6aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts6a);
        skorStts6a.setBounds(480, 1250, 35, 23);

        skorStts7a.setEditable(false);
        skorStts7a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts7a.setText("0");
        skorStts7a.setFocusTraversalPolicyProvider(true);
        skorStts7a.setName("skorStts7a"); // NOI18N
        skorStts7a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts7aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts7a);
        skorStts7a.setBounds(480, 1290, 35, 23);

        skorStts8a.setEditable(false);
        skorStts8a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts8a.setText("0");
        skorStts8a.setFocusTraversalPolicyProvider(true);
        skorStts8a.setName("skorStts8a"); // NOI18N
        skorStts8a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts8aActionPerformed(evt);
            }
        });
        FormInput.add(skorStts8a);
        skorStts8a.setBounds(480, 1330, 35, 23);

        skorStts1b.setEditable(false);
        skorStts1b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts1b.setText("0");
        skorStts1b.setFocusTraversalPolicyProvider(true);
        skorStts1b.setName("skorStts1b"); // NOI18N
        skorStts1b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts1bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts1b);
        skorStts1b.setBounds(660, 1050, 35, 23);

        skorStts2b.setEditable(false);
        skorStts2b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts2b.setText("0");
        skorStts2b.setFocusTraversalPolicyProvider(true);
        skorStts2b.setName("skorStts2b"); // NOI18N
        skorStts2b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts2bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts2b);
        skorStts2b.setBounds(660, 1090, 35, 23);

        skorStts3b.setEditable(false);
        skorStts3b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts3b.setText("0");
        skorStts3b.setFocusTraversalPolicyProvider(true);
        skorStts3b.setName("skorStts3b"); // NOI18N
        skorStts3b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts3bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts3b);
        skorStts3b.setBounds(660, 1130, 35, 23);

        skorStts4b.setEditable(false);
        skorStts4b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts4b.setText("0");
        skorStts4b.setFocusTraversalPolicyProvider(true);
        skorStts4b.setName("skorStts4b"); // NOI18N
        skorStts4b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts4bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts4b);
        skorStts4b.setBounds(660, 1170, 35, 23);

        skorStts5b.setEditable(false);
        skorStts5b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts5b.setText("0");
        skorStts5b.setFocusTraversalPolicyProvider(true);
        skorStts5b.setName("skorStts5b"); // NOI18N
        skorStts5b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts5bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts5b);
        skorStts5b.setBounds(660, 1210, 35, 23);

        skorStts6b.setEditable(false);
        skorStts6b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts6b.setText("0");
        skorStts6b.setFocusTraversalPolicyProvider(true);
        skorStts6b.setName("skorStts6b"); // NOI18N
        skorStts6b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts6bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts6b);
        skorStts6b.setBounds(660, 1250, 35, 23);

        skorStts7b.setEditable(false);
        skorStts7b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts7b.setText("0");
        skorStts7b.setFocusTraversalPolicyProvider(true);
        skorStts7b.setName("skorStts7b"); // NOI18N
        skorStts7b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts7bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts7b);
        skorStts7b.setBounds(660, 1290, 35, 23);

        skorStts8b.setEditable(false);
        skorStts8b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorStts8b.setText("0");
        skorStts8b.setFocusTraversalPolicyProvider(true);
        skorStts8b.setName("skorStts8b"); // NOI18N
        skorStts8b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorStts8bActionPerformed(evt);
            }
        });
        FormInput.add(skorStts8b);
        skorStts8b.setBounds(660, 1330, 35, 23);

        skor1.setEditable(false);
        skor1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor1.setText("0");
        skor1.setFocusTraversalPolicyProvider(true);
        skor1.setName("skor1"); // NOI18N
        skor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor1ActionPerformed(evt);
            }
        });
        FormInput.add(skor1);
        skor1.setBounds(510, 140, 35, 23);

        skor2.setEditable(false);
        skor2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor2.setText("0");
        skor2.setFocusTraversalPolicyProvider(true);
        skor2.setName("skor2"); // NOI18N
        skor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor2ActionPerformed(evt);
            }
        });
        FormInput.add(skor2);
        skor2.setBounds(510, 220, 35, 23);

        skor3.setEditable(false);
        skor3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor3.setText("0");
        skor3.setFocusTraversalPolicyProvider(true);
        skor3.setName("skor3"); // NOI18N
        skor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor3ActionPerformed(evt);
            }
        });
        FormInput.add(skor3);
        skor3.setBounds(500, 300, 35, 23);

        skor4.setEditable(false);
        skor4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skor4.setText("0");
        skor4.setFocusTraversalPolicyProvider(true);
        skor4.setName("skor4"); // NOI18N
        skor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skor4ActionPerformed(evt);
            }
        });
        FormInput.add(skor4);
        skor4.setBounds(500, 330, 35, 23);

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
            if(Sequel.menyimpantf("form3","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat, Tanggal & Jam",60,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
                riwayat1.getSelectedItem().toString(),skor1.getText(),riwayat2.getSelectedItem().toString(),skor2.getText(),riwayat3a.getSelectedItem().toString(),skor3.getText(),
                riwayat3b.getSelectedItem().toString(),skor4.getText(),riwayat3c.getSelectedItem().toString(),skor5.getText(),riwayat3d.getSelectedItem().toString(),skor6.getText(),riwayat3e.getSelectedItem().toString(),skor7.getText(),lainnya.getText(),riwayat3f.getSelectedItem().toString(),skor8.getText(),
                cmbIbua.getSelectedItem().toString(),cmbIbub.getSelectedItem().toString(),cmbAyaha.getSelectedItem().toString(),cmbAyahb.getSelectedItem().toString(),cmbAdika.getSelectedItem().toString(),cmbAdikb.getSelectedItem().toString(),cmbPasangana.getSelectedItem().toString(),cmbPasanganb.getSelectedItem().toString(),cmbAnaka.getSelectedItem().toString(),cmbAnakb.getSelectedItem().toString(),
                cmbKeluargaa.getSelectedItem().toString(),cmbKeluargab.getSelectedItem().toString(),cmbTemana.getSelectedItem().toString(),cmbTemanb.getSelectedItem().toString(),cmbTetanggaa.getSelectedItem().toString(),cmbTetanggab.getSelectedItem().toString(),cmbKerjaa.getSelectedItem().toString(),cmbKerjab.getSelectedItem().toString(),skor10.getText(),skor9.getText(),skala1.getText(),
                stts1a.getSelectedItem().toString(),stts1b.getSelectedItem().toString(),stts2a.getSelectedItem().toString(),stts2b.getSelectedItem().toString(),stts3a.getSelectedItem().toString(),stts3b.getSelectedItem().toString(),stts4a.getSelectedItem().toString(),stts4b.getSelectedItem().toString(),stts5a.getSelectedItem().toString(),
                stts5b.getSelectedItem().toString(),stts6a.getSelectedItem().toString(),stts6b.getSelectedItem().toString(),stts7a.getSelectedItem().toString(),stts7b.getSelectedItem().toString(),stts8a.getSelectedItem().toString(),stts8b.getSelectedItem().toString(),
                skor12.getText(),skor13.getText(),skor14.getText()
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

    private void riwayat3aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat3aActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_riwayat3aActionPerformed

    private void riwayat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat1ActionPerformed

    private void riwayat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat2ActionPerformed

    private void riwayat3bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat3bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat3bActionPerformed

    private void riwayat3cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat3cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat3cActionPerformed

    private void riwayat3dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat3dActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat3dActionPerformed

    private void riwayat3eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat3eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat3eActionPerformed

    private void riwayat3fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayat3fActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayat3fActionPerformed

    private void cmbIbuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIbuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbIbuaActionPerformed

    private void cmbIbubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIbubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbIbubActionPerformed

    private void cmbAyahaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAyahaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAyahaActionPerformed

    private void cmbAyahbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAyahbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAyahbActionPerformed

    private void cmbAdikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAdikaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAdikaActionPerformed

    private void cmbAdikbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAdikbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAdikbActionPerformed

    private void cmbPasanganaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPasanganaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPasanganaActionPerformed

    private void cmbPasanganbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPasanganbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPasanganbActionPerformed

    private void cmbAnakbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAnakbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAnakbActionPerformed

    private void cmbAnakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAnakaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAnakaActionPerformed

    private void cmbKeluargaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluargaaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKeluargaaActionPerformed

    private void cmbKeluargabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeluargabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKeluargabActionPerformed

    private void cmbTemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTemanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTemanaActionPerformed

    private void cmbTemanbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTemanbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTemanbActionPerformed

    private void cmbTetanggaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTetanggaaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTetanggaaActionPerformed

    private void cmbTetanggabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTetanggabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTetanggabActionPerformed

    private void cmbKerjaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKerjaaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKerjaaActionPerformed

    private void cmbKerjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKerjabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKerjabActionPerformed

    private void skor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor9ActionPerformed

    private void skala1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skala1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skala1ActionPerformed

    private void skor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor10ActionPerformed

    private void stts1aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts1aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts1aActionPerformed

    private void stts1bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts1bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts1bActionPerformed

    private void stts2aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts2aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts2aActionPerformed

    private void stts2bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts2bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts2bActionPerformed

    private void stts3aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts3aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts3aActionPerformed

    private void stts3bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts3bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts3bActionPerformed

    private void stts4aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts4aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts4aActionPerformed

    private void stts4bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts4bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts4bActionPerformed

    private void stts5aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts5aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts5aActionPerformed

    private void stts5bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts5bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts5bActionPerformed

    private void stts6aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts6aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts6aActionPerformed

    private void stts6bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts6bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts6bActionPerformed

    private void stts7aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts7aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts7aActionPerformed

    private void stts7bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts7bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts7bActionPerformed

    private void stts8aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts8aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts8aActionPerformed

    private void stts8bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stts8bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stts8bActionPerformed

    private void skor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor12ActionPerformed

    private void skor13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor13ActionPerformed

    private void skor14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor14ActionPerformed

    private void riwayat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat1ItemStateChanged
        // TODO add your handling code here:
        if(riwayat1.getSelectedIndex()==1){
            skor1.setText("1");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==2){
            skor1.setText("2");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==3){
            skor1.setText("3");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==4){
            skor1.setText("4");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==5){
            skor1.setText("5");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==6){
            skor1.setText("6");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==7){
            skor1.setText("7");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==8){
            skor1.setText("8");
            skor2.setText("0");
        }else if(riwayat1.getSelectedIndex()==9){
            skor1.setText("9");
            skor2.setText("0");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat1ItemStateChanged

    private void riwayat2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat2ItemStateChanged
        // TODO add your handling code here:
        if(riwayat2.getSelectedIndex()==1){
            skor2.setText("0");
            skor3.setText("0");
            skor4.setText("0");
            skor5.setText("0");
            skor6.setText("0");
            skor7.setText("0");
            skor8.setText("0");
        }else if(riwayat2.getSelectedIndex()==2){
            skor2.setText("1");
            skor3.setText("0");
            skor4.setText("0");
            skor5.setText("0");
            skor6.setText("0");
            skor7.setText("0");
            skor8.setText("0");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat2ItemStateChanged

    private void riwayat3aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat3aItemStateChanged
        // TODO add your handling code here:
        if(riwayat3a.getSelectedIndex()==1){
            skor3.setText("0");
        }else if(riwayat3a.getSelectedIndex()==2){
            skor3.setText("1");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat3aItemStateChanged

    private void riwayat3bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat3bItemStateChanged
        // TODO add your handling code here:
        if(riwayat3b.getSelectedIndex()==1){
            skor4.setText("0");
        }else if(riwayat3b.getSelectedIndex()==2){
            skor4.setText("1");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat3bItemStateChanged

    private void riwayat3cItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat3cItemStateChanged
        // TODO add your handling code here:
        if(riwayat3c.getSelectedIndex()==1){
            skor5.setText("0");
        }else if(riwayat3c.getSelectedIndex()==2){
            skor5.setText("1");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat3cItemStateChanged

    private void riwayat3dItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat3dItemStateChanged
        // TODO add your handling code here:
        if(riwayat3d.getSelectedIndex()==1){
            skor6.setText("0");
        }else if(riwayat3d.getSelectedIndex()==2){
            skor6.setText("1");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat3dItemStateChanged

    private void riwayat3eItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat3eItemStateChanged
        // TODO add your handling code here:
        if(riwayat3e.getSelectedIndex()==1){
            skor7.setText("0");
        }else if(riwayat3e.getSelectedIndex()==2){
            skor7.setText("1");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat3eItemStateChanged

    private void riwayat3fItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_riwayat3fItemStateChanged
        // TODO add your handling code here:
        if(riwayat3f.getSelectedIndex()==1){
            skor8.setText("0");
            
            skorIbua.setText("0");
            skorIbub.setText("0");
            skorAyaha.setText("0");
            skorAyahb.setText("0");
            skorAdika.setText("0");
            skorAdikb.setText("0");
            skorPasangana.setText("0");
            skorPasanganb.setText("0");
            skorAnaka.setText("0");
            skorAnakb.setText("0");
            skorKeluargaa.setText("0");
            skorKeluargab.setText("0");
            skorTemana.setText("0");
            skorTemanb.setText("0");
            skorTetanggaa.setText("0");
            skorTetanggab.setText("0");
            skorKerjaa.setText("0");
            skorKerjab.setText("0");
            
            skorStts1a.setText("0");
            skorStts1b.setText("0");
            skorStts2a.setText("0");
            skorStts2b.setText("0");
            skorStts3a.setText("0");
            skorStts3b.setText("0");
            skorStts4a.setText("0");
            skorStts4b.setText("0");
            skorStts5a.setText("0");
            skorStts5b.setText("0");
            skorStts6a.setText("0");
            skorStts6b.setText("0");
            skorStts7a.setText("0");
            skorStts7b.setText("0");
            skorStts8a.setText("0");
            skorStts8b.setText("0");
            
        }else if(riwayat3f.getSelectedIndex()==2){
            skor8.setText("1");
            
            skorIbua.setText("0");
            skorIbub.setText("0");
            skorAyaha.setText("0");
            skorAyahb.setText("0");
            skorAdika.setText("0");
            skorAdikb.setText("0");
            skorPasangana.setText("0");
            skorPasanganb.setText("0");
            skorAnaka.setText("0");
            skorAnakb.setText("0");
            skorKeluargaa.setText("0");
            skorKeluargab.setText("0");
            skorTemana.setText("0");
            skorTemanb.setText("0");
            skorTetanggaa.setText("0");
            skorTetanggab.setText("0");
            skorKerjaa.setText("0");
            skorKerjab.setText("0");
            
            skorStts1a.setText("0");
            skorStts1b.setText("0");
            skorStts2a.setText("0");
            skorStts2b.setText("0");
            skorStts3a.setText("0");
            skorStts3b.setText("0");
            skorStts4a.setText("0");
            skorStts4b.setText("0");
            skorStts5a.setText("0");
            skorStts5b.setText("0");
            skorStts6a.setText("0");
            skorStts6b.setText("0");
            skorStts7a.setText("0");
            skorStts7b.setText("0");
            skorStts8a.setText("0");
            skorStts8b.setText("0");
        }
        isTotalSkor();
    }//GEN-LAST:event_riwayat3fItemStateChanged

    private void cmbIbuaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIbuaItemStateChanged
        // TODO add your handling code here:
        if(cmbIbua.getSelectedIndex()==0){
            skorIbua.setText("0");
        }else if(cmbIbua.getSelectedIndex()==1){
            skorIbua.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbIbuaItemStateChanged

    private void cmbIbubItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIbubItemStateChanged
        // TODO add your handling code here:
        if(cmbIbub.getSelectedIndex()==0){
            skorIbub.setText("0");
        }else if(cmbIbub.getSelectedIndex()==1){
            skorIbub.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbIbubItemStateChanged

    private void cmbAyahaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAyahaItemStateChanged
        // TODO add your handling code here:
        if(cmbAyaha.getSelectedIndex()==0){
            skorAyaha.setText("0");
        }else if(cmbAyaha.getSelectedIndex()==1){
            skorAyaha.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbAyahaItemStateChanged

    private void cmbAdikaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAdikaItemStateChanged
        // TODO add your handling code here:
        if(cmbAdika.getSelectedIndex()==0){
            skorAdika.setText("0");
        }else if(cmbAdika.getSelectedIndex()==1){
            skorAdika.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbAdikaItemStateChanged

    private void cmbAdikbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAdikbItemStateChanged
        // TODO add your handling code here:
        if(cmbAdikb.getSelectedIndex()==0){
            skorAdikb.setText("0");
        }else if(cmbAdikb.getSelectedIndex()==1){
            skorAdikb.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbAdikbItemStateChanged

    private void cmbPasanganaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPasanganaItemStateChanged
        // TODO add your handling code here:
        if(cmbPasangana.getSelectedIndex()==0){
            skorPasangana.setText("0");
        }else if(cmbPasangana.getSelectedIndex()==1){
            skorPasangana.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbPasanganaItemStateChanged

    private void cmbPasanganbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPasanganbItemStateChanged
        // TODO add your handling code here:
        if(cmbPasanganb.getSelectedIndex()==0){
            skorPasanganb.setText("0");
        }else if(cmbPasanganb.getSelectedIndex()==1){
            skorPasanganb.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbPasanganbItemStateChanged

    private void cmbAnakaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAnakaItemStateChanged
        // TODO add your handling code here:
        if(cmbAnaka.getSelectedIndex()==0){
            skorAnaka.setText("0");
        }else if(cmbAnaka.getSelectedIndex()==1){
            skorAnaka.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbAnakaItemStateChanged

    private void cmbAnakbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAnakbItemStateChanged
        // TODO add your handling code here:
        if(cmbAnakb.getSelectedIndex()==0){
            skorAnakb.setText("0");
        }else if(cmbAnakb.getSelectedIndex()==1){
            skorAnakb.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbAnakbItemStateChanged

    private void cmbKeluargaaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKeluargaaItemStateChanged
        // TODO add your handling code here:
        if(cmbKeluargaa.getSelectedIndex()==0){
            skorKeluargaa.setText("0");
        }else if(cmbKeluargaa.getSelectedIndex()==1){
            skorKeluargaa.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbKeluargaaItemStateChanged

    private void cmbKeluargabItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKeluargabItemStateChanged
        // TODO add your handling code here:
        if(cmbKeluargab.getSelectedIndex()==0){
            skorKeluargab.setText("0");
        }else if(cmbKeluargab.getSelectedIndex()==1){
            skorKeluargab.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbKeluargabItemStateChanged

    private void cmbTemanaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTemanaItemStateChanged
        // TODO add your handling code here:
        if(cmbTemana.getSelectedIndex()==0){
            skorTemana.setText("0");
        }else if(cmbTemana.getSelectedIndex()==1){
            skorTemana.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbTemanaItemStateChanged

    private void cmbTemanbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTemanbItemStateChanged
        // TODO add your handling code here:
        if(cmbTemanb.getSelectedIndex()==0){
            skorTemanb.setText("0");
        }else if(cmbTemanb.getSelectedIndex()==1){
            skorTemanb.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbTemanbItemStateChanged

    private void cmbTetanggaaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTetanggaaItemStateChanged
        // TODO add your handling code here:
        if(cmbTetanggaa.getSelectedIndex()==0){
            skorTetanggaa.setText("0");
        }else if(cmbTetanggaa.getSelectedIndex()==1){
            skorTetanggaa.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbTetanggaaItemStateChanged

    private void cmbTetanggabItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTetanggabItemStateChanged
        // TODO add your handling code here:
        if(cmbTetanggab.getSelectedIndex()==0){
            skorTetanggab.setText("0");
        }else if(cmbTetanggab.getSelectedIndex()==1){
            skorTetanggab.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbTetanggabItemStateChanged

    private void cmbKerjaaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKerjaaItemStateChanged
        // TODO add your handling code here:
        if(cmbKerjaa.getSelectedIndex()==0){
            skorKerjaa.setText("0");
        }else if(cmbKerjaa.getSelectedIndex()==1){
            skorKerjaa.setText("1");
        }
        isTotalSkorRiwayat4a();
        isTotalSkor();
    }//GEN-LAST:event_cmbKerjaaItemStateChanged

    private void cmbKerjabItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKerjabItemStateChanged
        // TODO add your handling code here:
        if(cmbKerjab.getSelectedIndex()==0){
            skorKerjab.setText("0");
        }else if(cmbKerjab.getSelectedIndex()==1){
            skorKerjab.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbKerjabItemStateChanged

    private void cmbAyahbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAyahbItemStateChanged
        // TODO add your handling code here:
        if(cmbAyahb.getSelectedIndex()==0){
            skorAyahb.setText("0");
        }else if(cmbAyahb.getSelectedIndex()==1){
            skorAyahb.setText("1");
        }
        isTotalSkorRiwayat4b();
        isTotalSkor();
    }//GEN-LAST:event_cmbAyahbItemStateChanged

    private void stts1aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts1aItemStateChanged
        // TODO add your handling code here:
        if(stts1a.getSelectedIndex()==0){
            skorStts1a.setText("0");
        }else if(stts1a.getSelectedIndex()==1){
            skorStts1a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts1aItemStateChanged

    private void stts1bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts1bItemStateChanged
        // TODO add your handling code here:
        if(stts1b.getSelectedIndex()==0){
            skorStts1b.setText("0");
        }else if(stts1b.getSelectedIndex()==1){
            skorStts1b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts1bItemStateChanged

    private void stts2aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts2aItemStateChanged
        // TODO add your handling code here:
        if(stts2a.getSelectedIndex()==0){
            skorStts2a.setText("0");
        }else if(stts2a.getSelectedIndex()==1){
            skorStts2a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts2aItemStateChanged

    private void stts2bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts2bItemStateChanged
        // TODO add your handling code here:
        if(stts2b.getSelectedIndex()==0){
            skorStts2b.setText("0");
        }else if(stts2b.getSelectedIndex()==1){
            skorStts2b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts2bItemStateChanged

    private void stts3aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts3aItemStateChanged
        // TODO add your handling code here:
        if(stts3a.getSelectedIndex()==0){
            skorStts3a.setText("0");
        }else if(stts3a.getSelectedIndex()==1){
            skorStts3a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts3aItemStateChanged

    private void stts3bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts3bItemStateChanged
        // TODO add your handling code here:
        if(stts3b.getSelectedIndex()==0){
            skorStts3b.setText("0");
        }else if(stts3b.getSelectedIndex()==1){
            skorStts3b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts3bItemStateChanged

    private void stts4aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts4aItemStateChanged
        // TODO add your handling code here:
        if(stts4a.getSelectedIndex()==0){
            skorStts4a.setText("0");
        }else if(stts4a.getSelectedIndex()==1){
            skorStts4a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts4aItemStateChanged

    private void stts4bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts4bItemStateChanged
        // TODO add your handling code here:
        if(stts4b.getSelectedIndex()==0){
            skorStts4b.setText("0");
        }else if(stts4b.getSelectedIndex()==1){
            skorStts4b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts4bItemStateChanged

    private void stts5aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts5aItemStateChanged
        // TODO add your handling code here:
        if(stts5a.getSelectedIndex()==0){
            skorStts5a.setText("0");
        }else if(stts5a.getSelectedIndex()==1){
            skorStts5a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts5aItemStateChanged

    private void stts5bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts5bItemStateChanged
        // TODO add your handling code here:
        if(stts5b.getSelectedIndex()==0){
            skorStts5b.setText("0");
        }else if(stts5b.getSelectedIndex()==1){
            skorStts5b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts5bItemStateChanged

    private void stts6aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts6aItemStateChanged
        // TODO add your handling code here:
        if(stts6a.getSelectedIndex()==0){
            skorStts6a.setText("0");
        }else if(stts6a.getSelectedIndex()==1){
            skorStts6a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts6aItemStateChanged

    private void stts6bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts6bItemStateChanged
        // TODO add your handling code here:
        if(stts6b.getSelectedIndex()==0){
            skorStts6b.setText("0");
        }else if(stts6b.getSelectedIndex()==1){
            skorStts6b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts6bItemStateChanged

    private void stts7aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts7aItemStateChanged
        // TODO add your handling code here:
        if(stts7a.getSelectedIndex()==0){
            skorStts7a.setText("0");
        }else if(stts7a.getSelectedIndex()==1){
            skorStts7a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts7aItemStateChanged

    private void stts7bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts7bItemStateChanged
        // TODO add your handling code here:
        if(stts7b.getSelectedIndex()==0){
            skorStts7b.setText("0");
        }else if(stts7b.getSelectedIndex()==1){
            skorStts7b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts7bItemStateChanged

    private void stts8aItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts8aItemStateChanged
        // TODO add your handling code here:
        if(stts8a.getSelectedIndex()==0){
            skorStts8a.setText("0");
        }else if(stts8a.getSelectedIndex()==1){
            skorStts8a.setText("1");
        }
        isTotalSkorStatusa();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts8aItemStateChanged

    private void stts8bItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stts8bItemStateChanged
        // TODO add your handling code here:
        if(stts8b.getSelectedIndex()==0){
            skorStts8b.setText("0");
        }else if(stts8b.getSelectedIndex()==1){
            skorStts8b.setText("1");
        }
        isTotalSkorStatusb();
        isTotalSkorStatus();
    }//GEN-LAST:event_stts8bItemStateChanged

    private void skor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor5ActionPerformed

    private void skor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor6ActionPerformed

    private void skor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor7ActionPerformed

    private void skor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor8ActionPerformed

    private void skorIbuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorIbuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorIbuaActionPerformed

    private void skorAyahaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorAyahaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorAyahaActionPerformed

    private void skorAdikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorAdikaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorAdikaActionPerformed

    private void skorPasanganaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorPasanganaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorPasanganaActionPerformed

    private void skorAnakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorAnakaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorAnakaActionPerformed

    private void skorKeluargaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorKeluargaaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorKeluargaaActionPerformed

    private void skorKerjaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorKerjaaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorKerjaaActionPerformed

    private void skorTetanggaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorTetanggaaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorTetanggaaActionPerformed

    private void skorTemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorTemanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorTemanaActionPerformed

    private void skorIbubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorIbubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorIbubActionPerformed

    private void skorAyahbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorAyahbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorAyahbActionPerformed

    private void skorAdikbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorAdikbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorAdikbActionPerformed

    private void skorPasanganbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorPasanganbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorPasanganbActionPerformed

    private void skorAnakbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorAnakbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorAnakbActionPerformed

    private void skorKeluargabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorKeluargabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorKeluargabActionPerformed

    private void skorTemanbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorTemanbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorTemanbActionPerformed

    private void skorTetanggabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorTetanggabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorTetanggabActionPerformed

    private void skorKerjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorKerjabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorKerjabActionPerformed

    private void skorStts1aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts1aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts1aActionPerformed

    private void skorStts2aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts2aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts2aActionPerformed

    private void skorStts3aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts3aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts3aActionPerformed

    private void skorStts4aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts4aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts4aActionPerformed

    private void skorStts5aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts5aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts5aActionPerformed

    private void skorStts6aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts6aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts6aActionPerformed

    private void skorStts7aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts7aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts7aActionPerformed

    private void skorStts8aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts8aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts8aActionPerformed

    private void skorStts1bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts1bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts1bActionPerformed

    private void skorStts2bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts2bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts2bActionPerformed

    private void skorStts3bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts3bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts3bActionPerformed

    private void skorStts4bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts4bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts4bActionPerformed

    private void skorStts5bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts5bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts5bActionPerformed

    private void skorStts6bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts6bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts6bActionPerformed

    private void skorStts7bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts7bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts7bActionPerformed

    private void skorStts8bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorStts8bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skorStts8bActionPerformed

    private void skor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor1ActionPerformed

    private void skor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor2ActionPerformed

    private void skor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor3ActionPerformed

    private void skor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skor4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skor4ActionPerformed

    private void isTotalSkorRiwayat4a(){
        try {
            skor10.setText((Integer.parseInt(skorIbua.getText())+Integer.parseInt(skorAyaha.getText())+Integer.parseInt(skorAdika.getText())+Integer.parseInt(skorPasangana.getText())+Integer.parseInt(skorAnaka.getText())+Integer.parseInt(skorKeluargaa.getText())+Integer.parseInt(skorTemana.getText())+Integer.parseInt(skorTetanggaa.getText())+Integer.parseInt(skorKerjaa.getText()))+"");
        } catch (Exception e) {
            skor10.setText("0");
        }
    }
    
    private void isTotalSkorRiwayat4b(){
        try {
            skor9.setText((Integer.parseInt(skorIbub.getText())+Integer.parseInt(skorAyahb.getText())+Integer.parseInt(skorAdikb.getText())+Integer.parseInt(skorPasanganb.getText())+Integer.parseInt(skorAnakb.getText())+Integer.parseInt(skorKeluargab.getText())+Integer.parseInt(skorTemanb.getText())+Integer.parseInt(skorTetanggab.getText())+Integer.parseInt(skorKerjab.getText()))+"");
        } catch (Exception e) {
            skor9.setText("0");
        }
    }
    
    private void isTotalSkor(){
        try {
            skala1.setText((Integer.parseInt(skor1.getText())+Integer.parseInt(skor2.getText())+Integer.parseInt(skor3.getText())+Integer.parseInt(skor4.getText())+Integer.parseInt(skor5.getText())+Integer.parseInt(skor6.getText())+Integer.parseInt(skor7.getText())+Integer.parseInt(skor8.getText())+Integer.parseInt(skorIbua.getText())+Integer.parseInt(skorAyaha.getText())+Integer.parseInt(skorAdika.getText())+Integer.parseInt(skorPasangana.getText())+Integer.parseInt(skorAnaka.getText())+Integer.parseInt(skorKeluargaa.getText())+Integer.parseInt(skorTemana.getText())+Integer.parseInt(skorTetanggaa.getText())+Integer.parseInt(skorKerjaa.getText())+Integer.parseInt(skorIbub.getText())+Integer.parseInt(skorAyahb.getText())+Integer.parseInt(skorAdikb.getText())+Integer.parseInt(skorPasanganb.getText())+Integer.parseInt(skorAnakb.getText())+Integer.parseInt(skorKeluargab.getText())+Integer.parseInt(skorTemanb.getText())+Integer.parseInt(skorTetanggab.getText())+Integer.parseInt(skorKerjab.getText()))+"");
        } catch (Exception e) {
            skala1.setText("0");
        }
    }
    
    private void isTotalSkorStatusa(){
        try {
            skor12.setText((Integer.parseInt(skorStts1a.getText())+Integer.parseInt(skorStts2a.getText())+Integer.parseInt(skorStts3a.getText())+Integer.parseInt(skorStts4a.getText())+Integer.parseInt(skorStts5a.getText())+Integer.parseInt(skorStts6a.getText())+Integer.parseInt(skorStts7a.getText())+Integer.parseInt(skorStts8a.getText()))+"");
        } catch (Exception e) {
            skor12.setText("0");
        }
    }
    
    private void isTotalSkorStatusb(){
        try {
            skor13.setText((Integer.parseInt(skorStts1b.getText())+Integer.parseInt(skorStts2b.getText())+Integer.parseInt(skorStts3b.getText())+Integer.parseInt(skorStts4b.getText())+Integer.parseInt(skorStts5b.getText())+Integer.parseInt(skorStts6b.getText())+Integer.parseInt(skorStts7b.getText())+Integer.parseInt(skorStts8b.getText()))+"");
        } catch (Exception e) {
            skor13.setText("0");
        }
    }
    
    private void isTotalSkorStatus(){
        try {
            skor14.setText((Integer.parseInt(skorStts1a.getText())+Integer.parseInt(skorStts2a.getText())+Integer.parseInt(skorStts3a.getText())+Integer.parseInt(skorStts4a.getText())+Integer.parseInt(skorStts5a.getText())+Integer.parseInt(skorStts6a.getText())+Integer.parseInt(skorStts7a.getText())+Integer.parseInt(skorStts8a.getText())+Integer.parseInt(skorStts1b.getText())+Integer.parseInt(skorStts2b.getText())+Integer.parseInt(skorStts3b.getText())+Integer.parseInt(skorStts4b.getText())+Integer.parseInt(skorStts5b.getText())+Integer.parseInt(skorStts6b.getText())+Integer.parseInt(skorStts7b.getText())+Integer.parseInt(skorStts8b.getText()))+"");
        } catch (Exception e) {
            skor14.setText("0");
        }
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
    private widget.ComboBox cmbAdika;
    private widget.ComboBox cmbAdikb;
    private widget.ComboBox cmbAnaka;
    private widget.ComboBox cmbAnakb;
    private widget.ComboBox cmbAyaha;
    private widget.ComboBox cmbAyahb;
    private widget.ComboBox cmbIbua;
    private widget.ComboBox cmbIbub;
    private widget.ComboBox cmbKeluargaa;
    private widget.ComboBox cmbKeluargab;
    private widget.ComboBox cmbKerjaa;
    private widget.ComboBox cmbKerjab;
    private widget.ComboBox cmbPasangana;
    private widget.ComboBox cmbPasanganb;
    private widget.ComboBox cmbTemana;
    private widget.ComboBox cmbTemanb;
    private widget.ComboBox cmbTetanggaa;
    private widget.ComboBox cmbTetanggab;
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
    private widget.TextBox lainnya;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ComboBox riwayat1;
    private widget.ComboBox riwayat2;
    private widget.ComboBox riwayat3a;
    private widget.ComboBox riwayat3b;
    private widget.ComboBox riwayat3c;
    private widget.ComboBox riwayat3d;
    private widget.ComboBox riwayat3e;
    private widget.ComboBox riwayat3f;
    private widget.ScrollPane scrollInput;
    private widget.TextBox skala1;
    private widget.TextBox skor1;
    private widget.TextBox skor10;
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
    private widget.TextBox skorAdika;
    private widget.TextBox skorAdikb;
    private widget.TextBox skorAnaka;
    private widget.TextBox skorAnakb;
    private widget.TextBox skorAyaha;
    private widget.TextBox skorAyahb;
    private widget.TextBox skorIbua;
    private widget.TextBox skorIbub;
    private widget.TextBox skorKeluargaa;
    private widget.TextBox skorKeluargab;
    private widget.TextBox skorKerjaa;
    private widget.TextBox skorKerjab;
    private widget.TextBox skorPasangana;
    private widget.TextBox skorPasanganb;
    private widget.TextBox skorStts1a;
    private widget.TextBox skorStts1b;
    private widget.TextBox skorStts2a;
    private widget.TextBox skorStts2b;
    private widget.TextBox skorStts3a;
    private widget.TextBox skorStts3b;
    private widget.TextBox skorStts4a;
    private widget.TextBox skorStts4b;
    private widget.TextBox skorStts5a;
    private widget.TextBox skorStts5b;
    private widget.TextBox skorStts6a;
    private widget.TextBox skorStts6b;
    private widget.TextBox skorStts7a;
    private widget.TextBox skorStts7b;
    private widget.TextBox skorStts8a;
    private widget.TextBox skorStts8b;
    private widget.TextBox skorTemana;
    private widget.TextBox skorTemanb;
    private widget.TextBox skorTetanggaa;
    private widget.TextBox skorTetanggab;
    private widget.ComboBox stts1a;
    private widget.ComboBox stts1b;
    private widget.ComboBox stts2a;
    private widget.ComboBox stts2b;
    private widget.ComboBox stts3a;
    private widget.ComboBox stts3b;
    private widget.ComboBox stts4a;
    private widget.ComboBox stts4b;
    private widget.ComboBox stts5a;
    private widget.ComboBox stts5b;
    private widget.ComboBox stts6a;
    private widget.ComboBox stts6b;
    private widget.ComboBox stts7a;
    private widget.ComboBox stts7b;
    private widget.ComboBox stts8a;
    private widget.ComboBox stts8b;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,form3.tanggal,"+
                        "form3.kd_dokter,form3.riwayat1,form3.skor_riwayat1,form3.riwayat2,form3.skor_riwayat2,form3.saudara,form3.skor_saudara,form3.ayah,form3.skor_ayah,"+
                        "form3.pasangan,form3.skor_pasangan,form3.om,form3.skor_om,form3.teman,form3.skor_teman,form3.lain,form3.lainnya,form3.skor_lainnya,"+
                        "form3.ibu_a,form3.ibu_b,form3.ayah_a,form3.ayah_b,form3.adik_a,form3.adik_b,form3.pasangan_a,form3.pasangan_b,form3.anak_a,form3.anak_b,"+
                        "form3.keluarga_a,form3.keluarga_b,form3.akrab_a,form3.akrab_b,form3.tetangga_a,form3.tetangga_b,form3.kerja_a,form3.kerja_b,form3.tskor_a,form3.tskor_b,form3.skala_riwayat,"+
                        "form3.status_1a,form3.status_1b,form3.status_2a,form3.status_2b,form3.status_3a,form3.status_3b,form3.status_4a,form3.status_4b,form3.status_5a,form3.status_5b,form3.status_6a,form3.status_6b,"+
                        "form3.status_7a,form3.status_7b,form3.status_8a,form3.status_8b,form3.tstatus_a,form3.tstatus_b,form3.skala_status, "+
                        "dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join form3 on reg_periksa.no_rawat=form3.no_rawat "+
                        "inner join dokter on form3.kd_dokter=dokter.kd_dokter where "+
                        "form3.tanggal between ? and ? order by form3.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,form3.tanggal,"+
                        "form3.kd_dokter,form3.riwayat1,form3.skor_riwayat1,form3.riwayat2,form3.skor_riwayat2,form3.saudara,form3.skor_saudara,form3.ayah,form3.skor_ayah,"+
                        "form3.pasangan,form3.skor_pasangan,form3.om,form3.skor_om,form3.teman,form3.skor_teman,form3.lain,form3.lainnya,form3.skor_lainnya,"+
                        "form3.ibu_a,form3.ibu_b,form3.ayah_a,form3.ayah_b,form3.adik_a,form3.adik_b,form3.pasangan_a,form3.pasangan_b,form3.anak_a,form3.anak_b,"+
                        "form3.keluarga_a,form3.keluarga_b,form3.akrab_a,form3.akrab_b,form3.tetangga_a,form3.tetangga_b,form3.kerja_a,form3.kerja_b,form3.tskor_a,form3.tskor_b,form3.skala_riwayat,"+
                        "form3.status_1a,form3.status_1b,form3.status_2a,form3.status_2b,form3.status_3a,form3.status_3b,form3.status_4a,form3.status_4b,form3.status_5a,form3.status_5b,form3.status_6a,form3.status_6b,"+
                        "form3.status_7a,form3.status_7b,form3.status_8a,form3.status_8b,form3.tstatus_a,form3.tstatus_b,form3.skala_status, "+
                        "dokter.nm_dokter "+        
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join form3 on reg_periksa.no_rawat=form3.no_rawat "+
                        "inner join dokter on form3.kd_dokter=dokter.kd_dokter where "+
                        "form3.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "form3.kd_dokter like ? or dokter.kd_dokter like ?) order by form3.tanggal");
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
                        rs.getString("riwayat1"),rs.getString("skor_riwayat1"),rs.getString("riwayat2"),rs.getString("skor_riwayat2"),rs.getString("saudara"),rs.getString("skor_saudara"),rs.getString("ayah"),rs.getString("skor_ayah"),rs.getString("pasangan"),rs.getString("skor_pasangan"),
                        rs.getString("om"),rs.getString("skor_om"),rs.getString("teman"),rs.getString("skor_teman"),rs.getString("lain"),rs.getString("lainnya"),rs.getString("skor_lainnya"),rs.getString("ibu_a"),rs.getString("ibu_b"),rs.getString("ayah_a"),rs.getString("ayah_b"),rs.getString("adik_a"),rs.getString("adik_b"),rs.getString("pasangan_a"),rs.getString("pasangan_b"),
                        rs.getString("anak_a"),rs.getString("anak_b"),rs.getString("keluarga_a"),rs.getString("keluarga_b"),rs.getString("akrab_a"),rs.getString("akrab_b"),rs.getString("tetangga_a"),rs.getString("tetangga_b"),rs.getString("kerja_a"),rs.getString("kerja_b"),rs.getString("tskor_a"),rs.getString("tskor_b"),rs.getString("skala_riwayat"),rs.getString("status_1a"),rs.getString("status_1b"),
                        rs.getString("status_2a"),rs.getString("status_2b"),rs.getString("status_3a"),rs.getString("status_3b"),rs.getString("status_4a"),rs.getString("status_4b"),rs.getString("status_5a"),rs.getString("status_5b"),rs.getString("status_6a"),rs.getString("status_6b"),rs.getString("status_7a"),rs.getString("status_7b"),rs.getString("status_8a"),rs.getString("status_8b"),rs.getString("tstatus_a"),rs.getString("tstatus_b"),
                        rs.getString("skala_status")
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
        TglAsuhan.setDate(new Date());

        TabRawat.setSelectedIndex(0);

        KdDokter.setText("");
        NmDokter.setText("");
        Jk.setText("");
        
        skor1.setText("");
        skor2.setText("");
        skor3.setText("");
        skor4.setText("");
        skor5.setText("");
        skor6.setText("");
        skor7.setText("");
        skor8.setText("");
        skor9.setText("");
        skor10.setText("");
        skor12.setText("");
        skor13.setText("");
        skor14.setText("");
        skala1.setText("");
        
        skorIbua.setText("");
        skorIbub.setText("");
        skorAyaha.setText("");
        skorAyahb.setText("");
        skorAdika.setText("");
        skorAdikb.setText("");
        skorPasangana.setText("");
        skorPasanganb.setText("");
        skorAnaka.setText("");
        skorAnakb.setText("");
        skorKeluargaa.setText("");
        skorKeluargab.setText("");
        skorTemana.setText("");
        skorTemanb.setText("");
        skorTetanggaa.setText("");
        skorTetanggab.setText("");
        skorKerjaa.setText("");
        skorKerjab.setText("");
        
        skorStts1a.setText("");
        skorStts1b.setText("");
        skorStts2a.setText("");
        skorStts2b.setText("");
        skorStts3a.setText("");
        skorStts3b.setText("");
        skorStts4a.setText("");
        skorStts4b.setText("");
        skorStts5a.setText("");
        skorStts5b.setText("");
        skorStts6a.setText("");
        skorStts6b.setText("");
        skorTemanb.setText("");
        skorStts7b.setText("");
        skorTetanggab.setText("");
        skorStts8b.setText("");
        
        riwayat1.setSelectedIndex(0);
        riwayat2.setSelectedIndex(0);
        riwayat3a.setSelectedIndex(0);
        riwayat3b.setSelectedIndex(0);
        riwayat3c.setSelectedIndex(0);
        riwayat3d.setSelectedIndex(0);
        riwayat3e.setSelectedIndex(0);
        riwayat3f.setSelectedIndex(0);
        
        cmbIbua.setSelectedIndex(0);
        cmbIbub.setSelectedIndex(0);
        cmbAyaha.setSelectedIndex(0);
        cmbAyahb.setSelectedIndex(0);
        cmbAdika.setSelectedIndex(0);
        cmbAdikb.setSelectedIndex(0);
        cmbPasangana.setSelectedIndex(0);
        cmbPasanganb.setSelectedIndex(0);
        cmbAnaka.setSelectedIndex(0);
        cmbAnakb.setSelectedIndex(0);
        cmbKeluargaa.setSelectedIndex(0);
        cmbKeluargab.setSelectedIndex(0);
        cmbTemana.setSelectedIndex(0);
        cmbTemanb.setSelectedIndex(0);
        cmbTetanggaa.setSelectedIndex(0);
        cmbTetanggaa.setSelectedIndex(0);
        cmbKerjaa.setSelectedIndex(0);
        cmbKerjab.setSelectedIndex(0);
        
        stts1a.setSelectedIndex(0);
        stts1b.setSelectedIndex(0);
        stts2a.setSelectedIndex(0);
        stts2b.setSelectedIndex(0);
        stts3a.setSelectedIndex(0);
        stts3b.setSelectedIndex(0);
        stts4a.setSelectedIndex(0);
        stts4b.setSelectedIndex(0);
        stts5a.setSelectedIndex(0);
        stts5b.setSelectedIndex(0);
        stts6a.setSelectedIndex(0);
        stts6b.setSelectedIndex(0);
        stts7a.setSelectedIndex(0);
        stts7b.setSelectedIndex(0);
        stts8a.setSelectedIndex(0);
        stts8b.setSelectedIndex(0);
        
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
            
            riwayat1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            skor1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            
            riwayat2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            skor2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            
            riwayat3a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            skor3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            riwayat3b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            skor4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            riwayat3c.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            skor5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            riwayat3d.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            skor6.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            riwayat3e.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            skor7.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            lainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            riwayat3f.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            skor8.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            
            cmbIbua.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            cmbIbub.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            cmbAyaha.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            cmbAyahb.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            cmbAdika.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            cmbAdikb.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            cmbPasangana.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            cmbPasanganb.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            cmbAnaka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            cmbAnakb.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            cmbKeluargaa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            cmbKeluargab.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            cmbTemana.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            cmbTemanb.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            cmbTetanggaa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            cmbTetanggab.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            cmbKerjaa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            cmbKerjab.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            skor10.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            skor9.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            skala1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            
            stts1a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            stts1b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            stts2a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            stts2b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            stts3a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            stts3b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            stts4a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            stts4b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            stts5a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            stts5b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            stts6a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            stts6b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            stts7a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            stts7b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            stts8a.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            stts8b.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            skor12.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            skor13.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            skor14.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
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
        if(Sequel.queryu2tf("delete from form3 where no_rawat=? and tanggal=?",2,new String[]{
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
        if(Sequel.mengedittf("form3","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,kd_dokter=?,riwayat1=?,skor_riwayat1=?,riwayat2=?,skor_riwayat2=?,saudara=?,skor_saudara=?,ayah=?,skor_ayah=?,pasangan=?,skor_pasangan=?,om=?,skor_om=?,teman=?,skor_teman=?,lain=?,lainnya=?,skor_lainnya=?,ibu_a=?,ibu_b=?,ayah_a=?,ayah_b=?,adik_a=?,adik_b=?,pasangan_a=?,pasangan_b=?,anak_a=?,anak_b=?,keluarga_a=?,keluarga_b=?,akrab_a=?,akrab_b=?,tetangga_a=?,tetangga_b=?,kerja_a=?,kerja_b=?,tskor_a=?,tskor_b=?,skala_riwayat=?,status_1a=?,status_1b=?,status_2a=?,status_2b=?,status_3a=?,status_3b=?,status_4a=?,status_4b=?,status_5a=?,status_5b=?,status_6a=?,status_6b=?,status_7a=?,status_7b=?,status_8a=?,status_8b=?,tstatus_a=?,tstatus_b=?,skala_status=?",62,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
                riwayat1.getSelectedItem().toString(),skor1.getText(),riwayat2.getSelectedItem().toString(),skor2.getText(),riwayat3a.getSelectedItem().toString(),skor3.getText(),
                riwayat3b.getSelectedItem().toString(),skor4.getText(),riwayat3c.getSelectedItem().toString(),skor5.getText(),riwayat3d.getSelectedItem().toString(),skor6.getText(),riwayat3e.getSelectedItem().toString(),skor7.getText(),lainnya.getText(),riwayat3f.getSelectedItem().toString(),skor8.getText(),
                cmbIbua.getSelectedItem().toString(),cmbIbub.getSelectedItem().toString(),cmbAyaha.getSelectedItem().toString(),cmbAyahb.getSelectedItem().toString(),cmbAdika.getSelectedItem().toString(),cmbAdikb.getSelectedItem().toString(),cmbPasangana.getSelectedItem().toString(),cmbPasanganb.getSelectedItem().toString(),cmbAnaka.getSelectedItem().toString(),cmbAnakb.getSelectedItem().toString(),
                cmbKeluargaa.getSelectedItem().toString(),cmbKeluargab.getSelectedItem().toString(),cmbTemana.getSelectedItem().toString(),cmbTemanb.getSelectedItem().toString(),cmbTetanggaa.getSelectedItem().toString(),cmbTetanggab.getSelectedItem().toString(),cmbKerjaa.getSelectedItem().toString(),cmbKerjab.getSelectedItem().toString(),skor10.getText(),skor9.getText(),skala1.getText(),
                stts1a.getSelectedItem().toString(),stts1b.getSelectedItem().toString(),stts2a.getSelectedItem().toString(),stts2b.getSelectedItem().toString(),stts3a.getSelectedItem().toString(),stts3b.getSelectedItem().toString(),stts4a.getSelectedItem().toString(),stts4b.getSelectedItem().toString(),stts5a.getSelectedItem().toString(),
                stts5b.getSelectedItem().toString(),stts6a.getSelectedItem().toString(),stts6b.getSelectedItem().toString(),stts7a.getSelectedItem().toString(),stts7b.getSelectedItem().toString(),stts8a.getSelectedItem().toString(),stts8b.getSelectedItem().toString(),
                skor12.getText(),skor13.getText(),skor14.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
