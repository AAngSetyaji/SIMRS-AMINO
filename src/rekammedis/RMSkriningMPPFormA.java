/*
 * Kontribusi dari Haris Rochmatullah, RS Bhayangkara Nganjuk
 */


package rekammedis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.FileReader;
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
public final class RMSkriningMPPFormA extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeMasalah,tabModeDetailMasalah;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0,jml=0,index=0, pilihan=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private boolean[] pilih; 
    private String[] kode,masalah;
    private String masalahidentifikasi="",finger=""; 
    private StringBuilder htmlContent;
    private File file;
    private FileWriter fileWriter;
    private String iyem;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    
    /** Creates new form 
     * @param parent
     * @param modal */
    public RMSkriningMPPFormA(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir","Alamat","Tgl.Evaluasi","Ruang","Tgl.Masuk","Kode DPJP","DPJP","Kode Konsulan","Dokter Konsulan","Diagnosis","Kelompok",          
            "Assesmen ADL","Keterangan ADL",
            "Assesmen Riwayat Kesehatan","Keterangan Penyakit Kronis","Kebiasaan","Pola Kebiasaan Lain","Riwayat Kesehatan Lain",
            "Assesmen Spiritual","Nilai Keyakinan Agama Tertentu","Nilai Keyakinan Sosial Tertentu","Nilai Budaya Tertentu",
            "Assesmen Lingkungan","Assesmen Lingkungan Keterangan","Assesmen Dukungan Keluarga","Keterangan Dukungan Keluarga ","Assesmen Finansial","Ket. Finansial","Assesmen Kesehatan Mental","Ket. Kesehatan Mental",
            "Assesmen Pengobatan Alternatif","Keterangan Pengobatan Alternatif","Assesmen Pemahaman","Keterangan Pemahaman","Assesmen Harapan","Keterngan Harapan","Status Asuransi","Assesmen Trauma","Keterangan Trauma","Assesmen Legal","Keterangan Legal",
            "Identifikasi Masalah","Identifikasi Masalah Lain","Perencanaan MPP","Perencanaan MPP Lain","Keputusan Pasien","Keterangan Keputusan Pasien","Kesimpulan",
            "DP 1 a","DP 1 b","DP 1 c","DP 1 d","DP 2 a","DP 2 b","DP 2 c","DP 3 a","DP 3 b","DP 4 a","DP 4 b","DP 5 a","DP 5 b","DP 5 c","DP 6 a","DP 6 b",
            "NIP","Nama Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 70; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(180);
            }else if(i==6){
                column.setPreferredWidth(115);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(115);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(90);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(160);
            }else if(i==14){
                column.setPreferredWidth(160);
            }else if(i==15){
                column.setPreferredWidth(160);
            }else if(i==16){
                column.setPreferredWidth(160);
            }else if(i==17){
                column.setPreferredWidth(160);
            }else if(i==18){
                column.setPreferredWidth(80);
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
            }else if(i==37){
                column.setPreferredWidth(150);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(150);
            }else if(i==40){
                column.setPreferredWidth(150);
            }else if(i==41){
                column.setPreferredWidth(150);
            }else if(i==42){
                column.setPreferredWidth(150);
            }else if(i==43){
                column.setPreferredWidth(150);
            }else if(i==44){
                column.setPreferredWidth(150);
            }else if(i==45){
                column.setPreferredWidth(150);
            }else if(i==46){
                column.setPreferredWidth(150);
            }else if(i==47){
                column.setPreferredWidth(150);
            }else if(i==48){
                column.setPreferredWidth(150);
            }else if(i==49){
                column.setPreferredWidth(150);
            }else if(i==50){
                column.setPreferredWidth(150);
            }else if(i==51){
                column.setPreferredWidth(150);
            }else if(i==52){
                column.setPreferredWidth(150);
            }else if(i==53){
                column.setPreferredWidth(150);
            }else if(i==54){
                column.setPreferredWidth(150);
            }else if(i==55){
                column.setPreferredWidth(150);
            }else if(i==56){
                column.setPreferredWidth(150);
            }else if(i==57){
                column.setPreferredWidth(150);
            }else if(i==58){
                column.setPreferredWidth(150);
            }else if(i==59){
                column.setPreferredWidth(150);
            }else if(i==60){
                column.setPreferredWidth(150);
            }else if(i==61){
                column.setPreferredWidth(150);
            }else if(i==62){
                column.setPreferredWidth(150);
            }else if(i==63){
                column.setPreferredWidth(150);
            }else if(i==64){
                column.setPreferredWidth(150);
            }else if(i==65){
                column.setPreferredWidth(150);
            }else if(i==66){
                column.setPreferredWidth(150);
            }else if(i==67){
                column.setPreferredWidth(150);
            }else if(i==68){
                column.setPreferredWidth(150);
            }else if(i==69){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeMasalah=new DefaultTableModel(null,new Object[]{
                "P","Kode","Identifikasi Masalah"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbIdentifikasiMPP.setModel(tabModeMasalah);

        tbIdentifikasiMPP.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbIdentifikasiMPP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbIdentifikasiMPP.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(360);
            }
        }
        tbIdentifikasiMPP.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailMasalah=new DefaultTableModel(null,new Object[]{
                "Kode","Identifikasi Masalah"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbMasalahDetailMasalah.setModel(tabModeDetailMasalah);

        tbMasalahDetailMasalah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalahDetailMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbMasalahDetailMasalah.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(420);
            }
        }
        tbMasalahDetailMasalah.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TDiagnosis.setDocument(new batasInput((int)150).getKata(TDiagnosis));
        TKelompok.setDocument(new batasInput((int)150).getKata(TKelompok));
//        Assemen.setDocument(new batasInput((int)250).getKata(Assemen));
//        Identifikasi.setDocument(new batasInput((int)250).getKata(Identifikasi));
//        Perencanaan.setDocument(new batasInput((int)2000).getKata(Perencanaan));
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
            
            TCariMasalah.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCariMasalah.getText().length()>2){
                        tampilMasalah2();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCariMasalah.getText().length()>2){
                        tampilMasalah2();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCariMasalah.getText().length()>2){
                        tampilMasalah2();
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
                    KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());   
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
                    if(pilihan==1){
                        KdDok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter1.requestFocus();
                    }else if(pilihan==2){
                        KdDok2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter2.requestFocus();
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
        
        
        ChkAccor.setSelected(false);
        isMenu();
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
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        TglEvaluasi = new widget.Tanggal();
        jLabel94 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        Scroll6 = new widget.ScrollPane();
        tbIdentifikasiMPP = new widget.Table();
        label12 = new widget.Label();
        TCariMasalah = new widget.TextBox();
        BtnCariMasalah = new widget.Button();
        BtnTambahMasalah = new widget.Button();
        Alamat = new widget.TextBox();
        jLabel5 = new widget.Label();
        Kamar = new widget.TextBox();
        jLabel12 = new widget.Label();
        jLabel16 = new widget.Label();
        TDokter1 = new widget.TextBox();
        KdDok1 = new widget.TextBox();
        jLabel18 = new widget.Label();
        btnDokter1 = new widget.Button();
        jLabel20 = new widget.Label();
        TDokter2 = new widget.TextBox();
        KdDok2 = new widget.TextBox();
        btnDokter2 = new widget.Button();
        jLabel40 = new widget.Label();
        jLabel22 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        TDiagnosis = new widget.TextArea();
        scrollPane7 = new widget.ScrollPane();
        TKelompok = new widget.TextArea();
        BtnAllMasalah = new widget.Button();
        TglMasuk = new widget.TextBox();
        jLabel33 = new widget.Label();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        Jk1 = new widget.TextBox();
        jLabel35 = new widget.Label();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        Jk2 = new widget.TextBox();
        jRadioButton5 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        Jk3 = new widget.TextBox();
        jRadioButton6 = new javax.swing.JRadioButton();
        Jk4 = new widget.TextBox();
        jLabel36 = new widget.Label();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        Jk5 = new widget.TextBox();
        jRadioButton9 = new javax.swing.JRadioButton();
        Jk6 = new widget.TextBox();
        jRadioButton10 = new javax.swing.JRadioButton();
        Jk7 = new widget.TextBox();
        jLabel37 = new widget.Label();
        jComboBox1 = new javax.swing.JComboBox<>();
        Jk8 = new widget.TextBox();
        jLabel38 = new widget.Label();
        jComboBox2 = new javax.swing.JComboBox<>();
        Jk9 = new widget.TextBox();
        jLabel39 = new widget.Label();
        jComboBox3 = new javax.swing.JComboBox<>();
        Jk10 = new widget.TextBox();
        jRadioButton11 = new javax.swing.JRadioButton();
        Jk11 = new widget.TextBox();
        jRadioButton12 = new javax.swing.JRadioButton();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        Jk12 = new widget.TextBox();
        jLabel43 = new widget.Label();
        jRadioButton15 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        Jk13 = new widget.TextBox();
        jLabel44 = new widget.Label();
        jRadioButton17 = new javax.swing.JRadioButton();
        jRadioButton18 = new javax.swing.JRadioButton();
        jRadioButton19 = new javax.swing.JRadioButton();
        jRadioButton20 = new javax.swing.JRadioButton();
        Jk14 = new widget.TextBox();
        jLabel45 = new widget.Label();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel46 = new widget.Label();
        jRadioButton21 = new javax.swing.JRadioButton();
        Jk15 = new widget.TextBox();
        jRadioButton22 = new javax.swing.JRadioButton();
        jLabel47 = new widget.Label();
        jRadioButton23 = new javax.swing.JRadioButton();
        Jk16 = new widget.TextBox();
        jRadioButton24 = new javax.swing.JRadioButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jRadioButton25 = new javax.swing.JRadioButton();
        Jk17 = new widget.TextBox();
        jComboBox6 = new javax.swing.JComboBox<>();
        jRadioButton26 = new javax.swing.JRadioButton();
        Jk18 = new widget.TextBox();
        jLabel48 = new widget.Label();
        jRadioButton27 = new javax.swing.JRadioButton();
        jRadioButton28 = new javax.swing.JRadioButton();
        Jk19 = new widget.TextBox();
        jLabel95 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jRadioButton29 = new javax.swing.JRadioButton();
        jRadioButton30 = new javax.swing.JRadioButton();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        Jk20 = new widget.TextBox();
        Jk21 = new widget.TextBox();
        Jk43 = new widget.TextBox();
        Jk46 = new widget.TextBox();
        Jk47 = new widget.TextBox();
        Jk48 = new widget.TextBox();
        Jk49 = new widget.TextBox();
        Jk50 = new widget.TextBox();
        Jk51 = new widget.TextBox();
        Jk52 = new widget.TextBox();
        Jk53 = new widget.TextBox();
        Jk54 = new widget.TextBox();
        Jk55 = new widget.TextBox();
        Jk56 = new widget.TextBox();
        Jk57 = new widget.TextBox();
        Jk58 = new widget.TextBox();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
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
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        jLabel34 = new widget.Label();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        BtnPrintLap = new widget.Button();
        FormMasalahRencana = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbMasalahDetailMasalah = new widget.Table();
        scrollPane6 = new widget.ScrollPane();
        DetailRencana = new widget.TextArea();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Form A – Evaluasi Awal Manajer Pelayanan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 3000));
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

        label14.setText("Petugas :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(424, 70, 80, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(508, 70, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(610, 70, 213, 23);

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
        BtnPetugas.setBounds(826, 70, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("B. Identifikasi Masalah :");
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(10, 1490, 160, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(780, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tgl.Evaluasi :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(200, 70, 80, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("WAKTU PELAKSANAAN / DISKRIPSI");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(520, 1930, 220, 23);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("1. Penurunan kemampuan fungsional dan kemandirian pasien dalam pemenuhan ADL's");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(20, 240, 430, 23);

        TglEvaluasi.setForeground(new java.awt.Color(50, 70, 50));
        TglEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-10-2023 07:28:39" }));
        TglEvaluasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglEvaluasi.setName("TglEvaluasi"); // NOI18N
        TglEvaluasi.setOpaque(false);
        TglEvaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglEvaluasiKeyPressed(evt);
            }
        });
        FormInput.add(TglEvaluasi);
        TglEvaluasi.setBounds(290, 70, 130, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("Jelaskan :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(50, 1750, 60, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 210, 880, 1);

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbIdentifikasiMPP.setName("tbIdentifikasiMPP"); // NOI18N
        Scroll6.setViewportView(tbIdentifikasiMPP);

        FormInput.add(Scroll6);
        Scroll6.setBounds(10, 2640, 410, 303);

        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(0, 2950, 70, 23);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(70, 2950, 241, 23);

        BtnCariMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariMasalah.setMnemonic('1');
        BtnCariMasalah.setToolTipText("Alt+1");
        BtnCariMasalah.setName("BtnCariMasalah"); // NOI18N
        BtnCariMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariMasalahActionPerformed(evt);
            }
        });
        BtnCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariMasalah);
        BtnCariMasalah.setBounds(320, 2950, 28, 23);

        BtnTambahMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMasalah.setMnemonic('3');
        BtnTambahMasalah.setToolTipText("Alt+3");
        BtnTambahMasalah.setName("BtnTambahMasalah"); // NOI18N
        BtnTambahMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMasalahActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahMasalah);
        BtnTambahMasalah.setBounds(380, 2950, 28, 23);

        Alamat.setEditable(false);
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        FormInput.add(Alamat);
        Alamat.setBounds(74, 40, 495, 23);

        jLabel5.setText("Alamat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 70, 23);

        Kamar.setEditable(false);
        Kamar.setHighlighter(null);
        Kamar.setName("Kamar"); // NOI18N
        Kamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KamarKeyPressed(evt);
            }
        });
        FormInput.add(Kamar);
        Kamar.setBounds(644, 40, 210, 23);

        jLabel12.setText("Kamar :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(580, 40, 60, 23);

        jLabel16.setText("Tgl.Masuk :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 70, 70, 23);

        TDokter1.setEditable(false);
        TDokter1.setName("TDokter1"); // NOI18N
        FormInput.add(TDokter1);
        TDokter1.setBounds(176, 100, 190, 23);

        KdDok1.setEditable(false);
        KdDok1.setHighlighter(null);
        KdDok1.setName("KdDok1"); // NOI18N
        KdDok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok1KeyPressed(evt);
            }
        });
        FormInput.add(KdDok1);
        KdDok1.setBounds(74, 100, 100, 23);

        jLabel18.setText("Dokter P.J. :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 100, 70, 23);

        btnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter1.setMnemonic('2');
        btnDokter1.setToolTipText("ALt+2");
        btnDokter1.setName("btnDokter1"); // NOI18N
        btnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter1ActionPerformed(evt);
            }
        });
        btnDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter1KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter1);
        btnDokter1.setBounds(369, 100, 28, 23);

        jLabel20.setText("Dokter Konsulan :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(401, 100, 103, 23);

        TDokter2.setEditable(false);
        TDokter2.setName("TDokter2"); // NOI18N
        FormInput.add(TDokter2);
        TDokter2.setBounds(610, 100, 213, 23);

        KdDok2.setEditable(false);
        KdDok2.setHighlighter(null);
        KdDok2.setName("KdDok2"); // NOI18N
        KdDok2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok2KeyPressed(evt);
            }
        });
        FormInput.add(KdDok2);
        KdDok2.setBounds(508, 100, 100, 23);

        btnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter2.setMnemonic('2');
        btnDokter2.setToolTipText("ALt+2");
        btnDokter2.setName("btnDokter2"); // NOI18N
        btnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter2ActionPerformed(evt);
            }
        });
        btnDokter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter2KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter2);
        btnDokter2.setBounds(826, 100, 28, 23);

        jLabel40.setText("Diagnosis :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 130, 70, 23);

        jLabel22.setText("Kelompok Resiko :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(401, 130, 103, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        TDiagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDiagnosis.setColumns(20);
        TDiagnosis.setRows(5);
        TDiagnosis.setName("TDiagnosis"); // NOI18N
        TDiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagnosisKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TDiagnosis);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(74, 130, 323, 73);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        TKelompok.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKelompok.setColumns(20);
        TKelompok.setRows(5);
        TKelompok.setName("TKelompok"); // NOI18N
        TKelompok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKelompokKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(TKelompok);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(508, 130, 346, 73);

        BtnAllMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllMasalah.setMnemonic('2');
        BtnAllMasalah.setToolTipText("2Alt+2");
        BtnAllMasalah.setName("BtnAllMasalah"); // NOI18N
        BtnAllMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllMasalahActionPerformed(evt);
            }
        });
        BtnAllMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllMasalah);
        BtnAllMasalah.setBounds(350, 2950, 28, 23);

        TglMasuk.setEditable(false);
        TglMasuk.setHighlighter(null);
        TglMasuk.setName("TglMasuk"); // NOI18N
        FormInput.add(TglMasuk);
        TglMasuk.setBounds(74, 70, 130, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("A. Assesmen :");
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(20, 220, 120, 23);

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton1.setText("Ada :");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton1);
        jRadioButton1.setBounds(40, 290, 60, 19);

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(50, 50, 50));
        jRadioButton2.setText("Tidak Ada Hambatan");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });
        FormInput.add(jRadioButton2);
        jRadioButton2.setBounds(40, 259, 170, 20);

        Jk1.setEditable(false);
        Jk1.setHighlighter(null);
        Jk1.setName("Jk1"); // NOI18N
        Jk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jk1ActionPerformed(evt);
            }
        });
        FormInput.add(Jk1);
        Jk1.setBounds(110, 290, 320, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("(isi bila memilih lain-lain)");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(60, 840, 120, 23);

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton3.setText("Tidak Ada Hambatan");
        jRadioButton3.setName("jRadioButton3"); // NOI18N
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton3);
        jRadioButton3.setBounds(40, 470, 170, 30);

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton4.setText("Pola Kebiasaan :");
        jRadioButton4.setName("jRadioButton4"); // NOI18N
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton4);
        jRadioButton4.setBounds(40, 390, 110, 30);

        Jk2.setEditable(false);
        Jk2.setHighlighter(null);
        Jk2.setName("Jk2"); // NOI18N
        FormInput.add(Jk2);
        Jk2.setBounds(220, 530, 210, 24);

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton5.setText("Nilai keyakinan sosial tertentu :");
        jRadioButton5.setName("jRadioButton5"); // NOI18N
        jRadioButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton5ItemStateChanged(evt);
            }
        });
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton5);
        jRadioButton5.setBounds(40, 530, 180, 19);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox1.setText("Lain-lain :");
        jCheckBox1.setEnabled(false);
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        FormInput.add(jCheckBox1);
        jCheckBox1.setBounds(350, 400, 80, 20);

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox2.setText("Merokok");
        jCheckBox2.setEnabled(false);
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        FormInput.add(jCheckBox2);
        jCheckBox2.setBounds(150, 399, 80, 20);

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox3.setText("Konsumsi Alkohol");
        jCheckBox3.setEnabled(false);
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        FormInput.add(jCheckBox3);
        jCheckBox3.setBounds(230, 400, 120, 20);

        Jk3.setEditable(false);
        Jk3.setHighlighter(null);
        Jk3.setName("Jk3"); // NOI18N
        FormInput.add(Jk3);
        Jk3.setBounds(160, 366, 270, 24);

        jRadioButton6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton6.setText("Lain-lain :");
        jRadioButton6.setName("jRadioButton6"); // NOI18N
        jRadioButton6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton6ItemStateChanged(evt);
            }
        });
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton6);
        jRadioButton6.setBounds(40, 420, 80, 19);

        Jk4.setEditable(false);
        Jk4.setHighlighter(null);
        Jk4.setName("Jk4"); // NOI18N
        FormInput.add(Jk4);
        Jk4.setBounds(120, 426, 310, 24);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("2. Riwayat kesehatan dan/atau kebiasaan :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(20, 320, 430, 23);

        jRadioButton7.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton7.setText("Tidak Ada");
        jRadioButton7.setName("jRadioButton7"); // NOI18N
        jRadioButton7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton7ItemStateChanged(evt);
            }
        });
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton7);
        jRadioButton7.setBounds(40, 340, 120, 30);

        jRadioButton8.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton8.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton8.setText("Penyakit Kronis :");
        jRadioButton8.setName("jRadioButton8"); // NOI18N
        jRadioButton8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton8ItemStateChanged(evt);
            }
        });
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton8);
        jRadioButton8.setBounds(40, 370, 110, 19);

        Jk5.setEditable(false);
        Jk5.setEnabled(false);
        Jk5.setHighlighter(null);
        Jk5.setName("Jk5"); // NOI18N
        FormInput.add(Jk5);
        Jk5.setBounds(430, 394, 180, 24);

        jRadioButton9.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton9.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton9.setText("Nilai keyakinan agama tertentu :");
        jRadioButton9.setName("jRadioButton9"); // NOI18N
        jRadioButton9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton9ItemStateChanged(evt);
            }
        });
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton9);
        jRadioButton9.setBounds(40, 500, 180, 19);

        Jk6.setEditable(false);
        Jk6.setHighlighter(null);
        Jk6.setName("Jk6"); // NOI18N
        FormInput.add(Jk6);
        Jk6.setBounds(230, 500, 200, 24);

        jRadioButton10.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton10.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton10.setText("Nilai budaya tertentu :");
        jRadioButton10.setName("jRadioButton10"); // NOI18N
        jRadioButton10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton10ItemStateChanged(evt);
            }
        });
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton10);
        jRadioButton10.setBounds(40, 560, 140, 19);

        Jk7.setEditable(false);
        Jk7.setText("-");
        Jk7.setHighlighter(null);
        Jk7.setName("Jk7"); // NOI18N
        Jk7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jk7ActionPerformed(evt);
            }
        });
        FormInput.add(Jk7);
        Jk7.setBounds(190, 660, 180, 24);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("3. Perilaku spiritual, sosial, dan kultural :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(20, 450, 430, 23);

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak Ada Hambatan", "Pasien tinggal sendirian", "Pasien tinggal di panti", "Masyarakat tidak menerima pasien", "Rumah tinggal jauh dari fasilitas kesehatan", "Rumah tinggal dekat dengan fasilitas kesehatan", "Lain-lain" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        FormInput.add(jComboBox1);
        jComboBox1.setBounds(50, 630, 320, 20);

        Jk8.setEditable(false);
        Jk8.setHighlighter(null);
        Jk8.setName("Jk8"); // NOI18N
        FormInput.add(Jk8);
        Jk8.setBounds(190, 560, 240, 24);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("5. Tersedia dukungan keluarga dan kemampuan");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(20, 690, 430, 23);

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak Ada Hambatan", "Keluarga tidak pernah menunggui", "Keluarga tidak bisa dihubungi", "Keluarga tidak mau menerima kondisi pasien", "Keluarga tidak mampu merawat", "Keluarga tidak tahu perkembangan kondisi pasien", "Lain-lain" }));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        FormInput.add(jComboBox2);
        jComboBox2.setBounds(50, 720, 320, 20);

        Jk9.setEditable(false);
        Jk9.setText("-");
        Jk9.setHighlighter(null);
        Jk9.setName("Jk9"); // NOI18N
        FormInput.add(Jk9);
        Jk9.setBounds(190, 750, 180, 24);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("6. Kemampuan finansial :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(20, 780, 430, 23);

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tingkat Asuhan yang tidak sesuai dengan regulasi (PPK / clinical pathway)", "Over dan/atau under utilisasi layanan atas dasar regulasi yang berlaku", "Ketidakpatuhan pasien dalam proses asuhan", "Kurangnya dukungan keluarga", "Kurang pengetahuan keluarga terhadap penyakit, kondisi dan asuhan", "Masalah rujukan yang tertunda", "Penurunan determinasi pasien (adanya perburukan kondisi, peningkatan komplikasi, dan lain-lain)", "Kendala finansial (terutama saat adanya perburukan kondisi, peningkatan komplikasi, dan lain-lain)", "Rencana pemulangan yang belum memenuhi kriteria dan/atau penundaan pemulangan" }));
        jComboBox3.setName("jComboBox3"); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        FormInput.add(jComboBox3);
        jComboBox3.setBounds(30, 1520, 550, 20);

        Jk10.setEditable(false);
        Jk10.setText("-");
        Jk10.setHighlighter(null);
        Jk10.setName("Jk10"); // NOI18N
        FormInput.add(Jk10);
        Jk10.setBounds(190, 840, 180, 24);

        jRadioButton11.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton11.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton11.setText("Tidak baik, jelaskan :");
        jRadioButton11.setName("jRadioButton11"); // NOI18N
        jRadioButton11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton11ItemStateChanged(evt);
            }
        });
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton11);
        jRadioButton11.setBounds(40, 920, 130, 19);

        Jk11.setEditable(false);
        Jk11.setHighlighter(null);
        Jk11.setName("Jk11"); // NOI18N
        FormInput.add(Jk11);
        Jk11.setBounds(170, 920, 260, 23);

        jRadioButton12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup4.add(jRadioButton12);
        jRadioButton12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton12.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton12.setText("Baik");
        jRadioButton12.setName("jRadioButton12"); // NOI18N
        jRadioButton12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton12ItemStateChanged(evt);
            }
        });
        FormInput.add(jRadioButton12);
        jRadioButton12.setBounds(40, 890, 170, 20);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("7. Kesehatan mental dan kognitif :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(20, 870, 430, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("8. Riwayat pengobatan alternatif dan/atau Non Medis :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(20, 950, 430, 23);

        jRadioButton13.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(jRadioButton13);
        jRadioButton13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton13.setForeground(new java.awt.Color(50, 50, 50));
        jRadioButton13.setText("Tidak Ada");
        jRadioButton13.setName("jRadioButton13"); // NOI18N
        jRadioButton13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton13ItemStateChanged(evt);
            }
        });
        FormInput.add(jRadioButton13);
        jRadioButton13.setBounds(40, 970, 170, 20);

        jRadioButton14.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup5.add(jRadioButton14);
        jRadioButton14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton14.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton14.setText("Ada :");
        jRadioButton14.setName("jRadioButton14"); // NOI18N
        jRadioButton14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton14ItemStateChanged(evt);
            }
        });
        jRadioButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton14ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton14);
        jRadioButton14.setBounds(40, 1000, 60, 19);

        Jk12.setEditable(false);
        Jk12.setHighlighter(null);
        Jk12.setName("Jk12"); // NOI18N
        FormInput.add(Jk12);
        Jk12.setBounds(100, 1000, 330, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("9. Pemahaman terhadap kesehatan :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(20, 1030, 430, 23);

        jRadioButton15.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup6.add(jRadioButton15);
        jRadioButton15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton15.setForeground(new java.awt.Color(50, 50, 50));
        jRadioButton15.setText("Baik");
        jRadioButton15.setName("jRadioButton15"); // NOI18N
        jRadioButton15.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton15ItemStateChanged(evt);
            }
        });
        FormInput.add(jRadioButton15);
        jRadioButton15.setBounds(40, 1050, 170, 20);

        jRadioButton16.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup6.add(jRadioButton16);
        jRadioButton16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton16.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton16.setText("Kurang :");
        jRadioButton16.setName("jRadioButton16"); // NOI18N
        jRadioButton16.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton16ItemStateChanged(evt);
            }
        });
        jRadioButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton16ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton16);
        jRadioButton16.setBounds(40, 1080, 70, 19);

        Jk13.setEditable(false);
        Jk13.setHighlighter(null);
        Jk13.setName("Jk13"); // NOI18N
        FormInput.add(Jk13);
        Jk13.setBounds(120, 1213, 310, 24);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("10. Harapan terhadap asuhan :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(20, 1110, 430, 23);

        jRadioButton17.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(jRadioButton17);
        jRadioButton17.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton17.setForeground(new java.awt.Color(50, 50, 50));
        jRadioButton17.setText("Kondisi pasien membaik dan/atau sembuh");
        jRadioButton17.setName("jRadioButton17"); // NOI18N
        FormInput.add(jRadioButton17);
        jRadioButton17.setBounds(40, 1130, 250, 30);

        jRadioButton18.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(jRadioButton18);
        jRadioButton18.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton18.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton18.setText("Segera dilakukan tindakan");
        jRadioButton18.setName("jRadioButton18"); // NOI18N
        jRadioButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton18ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton18);
        jRadioButton18.setBounds(40, 1160, 210, 19);

        jRadioButton19.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(jRadioButton19);
        jRadioButton19.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton19.setForeground(new java.awt.Color(50, 50, 50));
        jRadioButton19.setText("Keluarga pasrah terhadap kondisi pasien");
        jRadioButton19.setName("jRadioButton19"); // NOI18N
        FormInput.add(jRadioButton19);
        jRadioButton19.setBounds(40, 1180, 250, 30);

        jRadioButton20.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup7.add(jRadioButton20);
        jRadioButton20.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton20.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton20.setText("Lain-lain :");
        jRadioButton20.setName("jRadioButton20"); // NOI18N
        jRadioButton20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton20ItemStateChanged(evt);
            }
        });
        jRadioButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton20ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton20);
        jRadioButton20.setBounds(40, 1210, 80, 19);

        Jk14.setEditable(false);
        Jk14.setHighlighter(null);
        Jk14.setName("Jk14"); // NOI18N
        FormInput.add(Jk14);
        Jk14.setBounds(120, 1080, 310, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("12. Riwayat trauma / kekerasan");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(20, 1300, 430, 23);

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak Ada Hambatan", "Pasien tidak mampu dan/atau miskin", "Pasien belum memiliki asuransi", "Ada masalah asuransi", "Tidak ada penanggung jawab pembiayaan", "Total biaya melebihi INA CBG's klaim asuransi", "Lain-lain" }));
        jComboBox4.setName("jComboBox4"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        FormInput.add(jComboBox4);
        jComboBox4.setBounds(50, 810, 320, 20);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("11. Status Asuransi :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(20, 1240, 430, 23);

        jRadioButton21.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup8.add(jRadioButton21);
        jRadioButton21.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton21.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton21.setText("Tidak Ada");
        jRadioButton21.setName("jRadioButton21"); // NOI18N
        jRadioButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton21ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton21);
        jRadioButton21.setBounds(40, 1360, 80, 19);

        Jk15.setEditable(false);
        Jk15.setHighlighter(null);
        Jk15.setName("Jk15"); // NOI18N
        FormInput.add(Jk15);
        Jk15.setBounds(120, 1330, 310, 23);

        jRadioButton22.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup8.add(jRadioButton22);
        jRadioButton22.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton22.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton22.setText("Ada :");
        jRadioButton22.setName("jRadioButton22"); // NOI18N
        jRadioButton22.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton22ItemStateChanged(evt);
            }
        });
        jRadioButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton22ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton22);
        jRadioButton22.setBounds(40, 1330, 70, 19);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("13. Aspek Legal / Advokasi :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(20, 1390, 430, 23);

        jRadioButton23.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup9.add(jRadioButton23);
        jRadioButton23.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton23.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton23.setText("Tidak Dibutuhkan");
        jRadioButton23.setName("jRadioButton23"); // NOI18N
        jRadioButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton23ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton23);
        jRadioButton23.setBounds(40, 1420, 120, 19);

        Jk16.setEditable(false);
        Jk16.setHighlighter(null);
        Jk16.setName("Jk16"); // NOI18N
        FormInput.add(Jk16);
        Jk16.setBounds(140, 1450, 290, 23);

        jRadioButton24.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup9.add(jRadioButton24);
        jRadioButton24.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton24.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton24.setText("Dibutuhkan :");
        jRadioButton24.setName("jRadioButton24"); // NOI18N
        jRadioButton24.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton24ItemStateChanged(evt);
            }
        });
        jRadioButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton24ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton24);
        jRadioButton24.setBounds(40, 1450, 90, 19);

        jComboBox5.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Tidak Aktif / tidak memiliki", "Dengan Layanan Umum / bayar mandiri" }));
        jComboBox5.setName("jComboBox5"); // NOI18N
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        FormInput.add(jComboBox5);
        jComboBox5.setBounds(40, 1270, 270, 20);

        jRadioButton25.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton25.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton25.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton25.setText("Masalah Lainnya : ");
        jRadioButton25.setName("jRadioButton25"); // NOI18N
        jRadioButton25.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton25ItemStateChanged(evt);
            }
        });
        jRadioButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton25ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton25);
        jRadioButton25.setBounds(30, 1550, 120, 19);

        Jk17.setEditable(false);
        Jk17.setHighlighter(null);
        Jk17.setName("Jk17"); // NOI18N
        FormInput.add(Jk17);
        Jk17.setBounds(150, 1550, 370, 23);

        jComboBox6.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Berkoordinasi dengan PPA", "Memfasilitasi koordinasi, komunikasi, dan kolaborasi", "Edukasi kepada pasien dan keluarga", "Maksimalkan dukungan keluarga", "Koordinasi dengan Pimpinan Rumah Sakit", "Terminasi berkoordinasi dengan PPA", "Advokasi", "Monitoring hasil pelayanan", "Membantu mengkoordinasi pemenuhan ADL's", "Memenuhi kebutuhan spiritual, social, dan budaya sesuai regulasi yang berlaku", "Manajemen sumber daya Rumah Sakit", "Berkoordinasi terkait kebutuhan rencana pemulangan dan kontinuitas pelayanan dengan pihak terkait" }));
        jComboBox6.setName("jComboBox6"); // NOI18N
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        FormInput.add(jComboBox6);
        jComboBox6.setBounds(30, 1610, 550, 20);

        jRadioButton26.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton26.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton26.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton26.setText("Lain-Lain :");
        jRadioButton26.setName("jRadioButton26"); // NOI18N
        jRadioButton26.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton26ItemStateChanged(evt);
            }
        });
        jRadioButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton26ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton26);
        jRadioButton26.setBounds(30, 1640, 90, 19);

        Jk18.setEditable(false);
        Jk18.setHighlighter(null);
        Jk18.setName("Jk18"); // NOI18N
        FormInput.add(Jk18);
        Jk18.setBounds(120, 1640, 400, 23);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("C. Perencanaan MPP :");
        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(10, 1580, 140, 23);

        jRadioButton27.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup10.add(jRadioButton27);
        jRadioButton27.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton27.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton27.setText("Melanjutkan asuhan medis dan asuhan Perawatan dan/atau Pengobatan");
        jRadioButton27.setName("jRadioButton27"); // NOI18N
        jRadioButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton27ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton27);
        jRadioButton27.setBounds(30, 1700, 400, 19);

        jRadioButton28.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup10.add(jRadioButton28);
        jRadioButton28.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton28.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton28.setText("Menghentikan asuhan medis dan asuhan perawatan dan/atau pengobatan");
        jRadioButton28.setName("jRadioButton28"); // NOI18N
        jRadioButton28.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton28ItemStateChanged(evt);
            }
        });
        jRadioButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton28ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton28);
        jRadioButton28.setBounds(30, 1730, 400, 19);

        Jk19.setEditable(false);
        Jk19.setHighlighter(null);
        Jk19.setName("Jk19"); // NOI18N
        FormInput.add(Jk19);
        Jk19.setBounds(110, 1750, 410, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("Catatan :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(10, 2620, 180, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("D. Keputusan Pasien dan atau Keluarga :");
        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(10, 1670, 230, 23);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("F. Discharge Planning :");
        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(10, 1900, 230, 23);

        jRadioButton29.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup11.add(jRadioButton29);
        jRadioButton29.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton29.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton29.setText("Ya, lanjut penatalaksanaan di FORM B");
        jRadioButton29.setName("jRadioButton29"); // NOI18N
        jRadioButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton29ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton29);
        jRadioButton29.setBounds(30, 1820, 400, 20);

        jRadioButton30.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup11.add(jRadioButton30);
        jRadioButton30.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jRadioButton30.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton30.setText("Tidak, dikembalikan kepada PPA");
        jRadioButton30.setName("jRadioButton30"); // NOI18N
        jRadioButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton30ActionPerformed(evt);
            }
        });
        FormInput.add(jRadioButton30);
        jRadioButton30.setBounds(30, 1850, 400, 19);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("E. Kesimpulan :");
        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(10, 1780, 230, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("c. Pengobatan yang sangat dibutuhkan");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(70, 2040, 230, 23);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("NO");
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(10, 1930, 30, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("HAL YANG DIPERSIAPKAN OLEH MPP");
        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(70, 1930, 220, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("PASIEN INI PERLU DIKELOLA MPP");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(10, 1800, 230, 23);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("1.");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(20, 1960, 20, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("a. Resep obat pulang");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(70, 1980, 230, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("d. Aturan minum obat dan kewaspadaan dari efek samping obat");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(70, 2070, 320, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("b. Pengobatan yang harus dihentikan");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(70, 2010, 230, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Pengobatan di rumah");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(70, 1960, 230, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("2.");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(20, 2120, 20, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Pemeriksaan Penunjang : (K/P untuk melakukan pemeriksaan rutin)");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(70, 2120, 340, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("a. Laboratorium");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(70, 2140, 230, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("c. Lainnya");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(70, 2200, 230, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("b. Radiologi");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(70, 2170, 230, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("3.");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(20, 2240, 20, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Gaya hidup terkait penyakit :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(70, 2240, 270, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("a. Diet makanan");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(70, 2260, 230, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("b. Perubahan aktivitas");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(70, 2290, 230, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("4.");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(20, 2330, 20, 23);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Perawatan diri secara khusus :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(70, 2330, 300, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("a. Diet makanan");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(70, 2350, 230, 23);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("b. Perawatan pemakaian alat medis tertentu");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(70, 2380, 230, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Kesinambungan pelayanan pasca rawat inap :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(70, 2420, 290, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("5.");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(20, 2420, 20, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("a. Nama fasilitas kesehatan");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(70, 2440, 230, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("b. Tanggal pengobatan");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(70, 2470, 230, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("c. Kontak person fasilitas kesehatan");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(70, 2500, 230, 23);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("b. Nama fasilitas Kesehatan / Kader Kesehatan / lainnya");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(70, 2590, 290, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("a. No Telp dan kontak person");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(70, 2560, 230, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("Pertolongan pada Kondisi darurat :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(70, 2540, 300, 23);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("6.");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(20, 2540, 20, 23);

        Jk20.setHighlighter(null);
        Jk20.setName("Jk20"); // NOI18N
        FormInput.add(Jk20);
        Jk20.setBounds(460, 2590, 310, 23);

        Jk21.setHighlighter(null);
        Jk21.setName("Jk21"); // NOI18N
        FormInput.add(Jk21);
        Jk21.setBounds(460, 1980, 310, 23);

        Jk43.setHighlighter(null);
        Jk43.setName("Jk43"); // NOI18N
        FormInput.add(Jk43);
        Jk43.setBounds(460, 2010, 310, 23);

        Jk46.setHighlighter(null);
        Jk46.setName("Jk46"); // NOI18N
        FormInput.add(Jk46);
        Jk46.setBounds(460, 2040, 310, 23);

        Jk47.setHighlighter(null);
        Jk47.setName("Jk47"); // NOI18N
        FormInput.add(Jk47);
        Jk47.setBounds(460, 2070, 310, 23);

        Jk48.setHighlighter(null);
        Jk48.setName("Jk48"); // NOI18N
        FormInput.add(Jk48);
        Jk48.setBounds(460, 2140, 310, 23);

        Jk49.setHighlighter(null);
        Jk49.setName("Jk49"); // NOI18N
        FormInput.add(Jk49);
        Jk49.setBounds(460, 2170, 310, 23);

        Jk50.setHighlighter(null);
        Jk50.setName("Jk50"); // NOI18N
        FormInput.add(Jk50);
        Jk50.setBounds(460, 2200, 310, 23);

        Jk51.setHighlighter(null);
        Jk51.setName("Jk51"); // NOI18N
        FormInput.add(Jk51);
        Jk51.setBounds(460, 2260, 310, 23);

        Jk52.setHighlighter(null);
        Jk52.setName("Jk52"); // NOI18N
        FormInput.add(Jk52);
        Jk52.setBounds(460, 2290, 310, 23);

        Jk53.setHighlighter(null);
        Jk53.setName("Jk53"); // NOI18N
        FormInput.add(Jk53);
        Jk53.setBounds(460, 2350, 310, 23);

        Jk54.setHighlighter(null);
        Jk54.setName("Jk54"); // NOI18N
        FormInput.add(Jk54);
        Jk54.setBounds(460, 2380, 310, 23);

        Jk55.setHighlighter(null);
        Jk55.setName("Jk55"); // NOI18N
        FormInput.add(Jk55);
        Jk55.setBounds(460, 2440, 310, 23);

        Jk56.setHighlighter(null);
        Jk56.setName("Jk56"); // NOI18N
        FormInput.add(Jk56);
        Jk56.setBounds(460, 2470, 310, 23);

        Jk57.setHighlighter(null);
        Jk57.setName("Jk57"); // NOI18N
        FormInput.add(Jk57);
        Jk57.setBounds(460, 2500, 310, 23);

        Jk58.setHighlighter(null);
        Jk58.setName("Jk58"); // NOI18N
        FormInput.add(Jk58);
        Jk58.setBounds(460, 2560, 310, 23);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("4. Lingkungan dan tempat tinggal :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(20, 600, 430, 23);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("(isi bila memilih lain-lain)");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(60, 660, 120, 23);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("(isi bila memilih lain-lain)");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(60, 750, 120, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Evaluasi", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
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

        jLabel19.setText("Tgl. Evaluasi : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-10-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-10-2023" }));
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

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(470, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel34.setText("Pasien :");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(55, 23));
        FormMenu.add(jLabel34);

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N
        TNoRM1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormMenu.add(TNoRM1);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(250, 23));
        FormMenu.add(TPasien1);

        BtnPrintLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrintLap.setMnemonic('T');
        BtnPrintLap.setToolTipText("Alt+T");
        BtnPrintLap.setName("BtnPrintLap"); // NOI18N
        BtnPrintLap.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrintLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintLapActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPrintLap);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.NORTH);

        FormMasalahRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormMasalahRencana.setName("FormMasalahRencana"); // NOI18N
        FormMasalahRencana.setLayout(new java.awt.GridLayout(2, 0, 1, 1));

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbMasalahDetailMasalah.setName("tbMasalahDetailMasalah"); // NOI18N
        Scroll7.setViewportView(tbMasalahDetailMasalah);

        FormMasalahRencana.add(Scroll7);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Perencanaan :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        DetailRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DetailRencana.setColumns(20);
        DetailRencana.setRows(5);
        DetailRencana.setName("DetailRencana"); // NOI18N
        DetailRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetailRencanaKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(DetailRencana);

        FormMasalahRencana.add(scrollPane6);

        PanelAccor.add(FormMasalahRencana, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Evaluasi", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String assesmenadl = "";
        String assesmenriwayat = "";
        String polakebiasaan = "";
        String assesmenspiritual = "";
        String assesmenmental = "";
        String assesmenalternatif = "";
        String assesmenpemahaman = "";
        String assesmenharapan = "";
        String assesmentrauma = "";
        String assesmenlegal = "";
        
        String keputusan = "";
        
        String kesimpulan = "";
        
        if (jRadioButton2.isSelected()){
            assesmenadl = "Tidak Ada Hambatan";
        }else if (jRadioButton1.isSelected()){
            assesmenadl = "Ada";
        }
        
        if (jRadioButton7.isSelected()){
            assesmenriwayat = "Tidak Ada";
        } else if (jRadioButton8.isSelected()){
            assesmenriwayat = "Penyakit Kronis";
        } else if (jRadioButton4.isSelected()){
            assesmenriwayat = "Pola Kebiasaan";
        } else if (jRadioButton6.isSelected()){
            assesmenriwayat = "Lain-lain";
        }
        
        
        if (jCheckBox2.isSelected()) {
            polakebiasaan += "Merokok, ";
        }
        if (jCheckBox3.isSelected()) {
            polakebiasaan += "Konsumsi Alkohol, ";
        }
        if (jCheckBox1.isSelected()) {
            polakebiasaan += "Lain-lain";
        }
        polakebiasaan = polakebiasaan.trim();
        if (polakebiasaan.endsWith(", ")) {
            polakebiasaan = polakebiasaan.substring(0, polakebiasaan.length() - 2);
        }
        
        if (jRadioButton3.isSelected()) {
            assesmenspiritual = "Tidak Ada Hambatan";
        }else if (jRadioButton9.isSelected()){
            assesmenspiritual = "Nilai keyakinan agama tertentu";
        }else if (jRadioButton5.isSelected()){
            assesmenspiritual = "Nilai keyakinan sosial tertentu";
        }else if (jRadioButton10.isSelected()){
            assesmenspiritual = "Nilai budaya tertentu";
        }
        
        if (jRadioButton12.isSelected()){
            assesmenmental = "Baik";
        }else if (jRadioButton11.isSelected()){
            assesmenmental = "Tidak baik";
        }
        
        if (jRadioButton13.isSelected()){
            assesmenalternatif = "Tidak Ada";
        }else if (jRadioButton14.isSelected()){
            assesmenalternatif = "Ada";
        }
        
        if (jRadioButton15.isSelected()){
            assesmenpemahaman = "Baik";
        }else if (jRadioButton16.isSelected()){
            assesmenpemahaman = "Kurang";
        }
        
        if (jRadioButton17.isSelected()){
            assesmenharapan = "Kondisi pasien membaik dan/atau sembuh";
        }else if (jRadioButton18.isSelected()){
            assesmenharapan = "Segera dilakukan tindakan";
        }else if (jRadioButton19.isSelected()){
            assesmenharapan = "Keluarga pasrah terhadap kondisi pasien";
        }else if (jRadioButton20.isSelected()){
            assesmenharapan = "Lain-lain";
        }
        
        if (jRadioButton22.isSelected()){
            assesmentrauma = "Ada";
        }else if (jRadioButton21.isSelected()){
            assesmentrauma = "Tidak Ada";
        }
        
        if (jRadioButton23.isSelected()){
            assesmenlegal = "Tidak Dibutuhkan";
        }else if (jRadioButton24.isSelected()){
            assesmenlegal = "Dibutuhkan";
        }
        
        if (jRadioButton27.isSelected()){
            keputusan = "Melanjutkan asuhan medis dan asuhan Perawatan dan/atau Pengobatan";
        }else if (jRadioButton28.isSelected()){
            keputusan = "Menghentikan asuhan medis dan asuhan perawatan dan/atau pengobatan";
        }
        
        if (jRadioButton29.isSelected()){
            kesimpulan = "Ya, lanjut penatalaksanaan di FORM B";
        }else if (jRadioButton30.isSelected()){
            kesimpulan = "Tidak, dikembalikan kepada PPA";
        }
        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(TDiagnosis.getText().trim().equals("")){
            Valid.textKosong(TDiagnosis,"Diagnosis");
        }else if(TKelompok.getText().trim().equals("")){
            Valid.textKosong(TKelompok,"Kelompok Resiko");
//        }else if(Perencanaan.getText().trim().equals("")){
//            Valid.textKosong(Perencanaan,"Riwayat Penyakit Dahulu");
//        }else if(Assemen.getText().trim().equals("")){
//            Valid.textKosong(Assemen,"Riwayat Penyakit Keluarga");
//        }else if(Identifikasi.getText().trim().equals("")){
//            Valid.textKosong(Identifikasi,"Riwayat Pengobatan");
//        }else if(Perencanaan.getText().trim().equals("")){
//            Valid.textKosong(Perencanaan,"Lokasi");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
        }else if(TDokter1.getText().trim().equals("")){
            Valid.textKosong(TDokter1,"Dokter DPJP");
        }else if(TDokter2.getText().trim().equals("")){
            Valid.textKosong(TDokter2,"Dokter Konsulan");
        }else{
            if(Sequel.menyimpantf("mpp_evaluasi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",60,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglEvaluasi.getSelectedItem()+"")+" "+TglEvaluasi.getSelectedItem().toString().substring(11,19),
                    KdDok1.getText(),KdDok2.getText(),TDiagnosis.getText(),TKelompok.getText(),
                    assesmenadl,Jk1.getText(),
                    assesmenriwayat,Jk3.getText(),polakebiasaan,Jk5.getText(),Jk4.getText(),
                    assesmenspiritual,Jk6.getText(),Jk2.getText(),Jk8.getText(),
                    jComboBox1.getSelectedItem().toString(),Jk7.getText(),
                    jComboBox2.getSelectedItem().toString(),Jk9.getText(),
                    jComboBox4.getSelectedItem().toString(),Jk10.getText(),
                    assesmenmental,Jk11.getText(),
                    assesmenalternatif,Jk12.getText(),
                    assesmenpemahaman,Jk14.getText(),
                    assesmenharapan,Jk13.getText(),
                    jComboBox5.getSelectedItem().toString(),
                    assesmentrauma,Jk15.getText(),
                    assesmenlegal,Jk16.getText(),
                    jComboBox3.getSelectedItem().toString(),Jk17.getText(),
                    jComboBox6.getSelectedItem().toString(),Jk18.getText(),
                    keputusan,Jk19.getText(),
                    kesimpulan,
                    Jk21.getText(),Jk43.getText(),Jk46.getText(),Jk47.getText(),
                    Jk48.getText(),Jk49.getText(),Jk50.getText(),
                    Jk51.getText(),Jk52.getText(),
                    Jk53.getText(),Jk54.getText(),
                    Jk55.getText(),Jk56.getText(),Jk57.getText(),
                    Jk58.getText(),Jk20.getText(),
                    KdPetugas.getText()
                })==true){
                    for (i = 0; i < tbIdentifikasiMPP.getRowCount(); i++) {
                        if(tbIdentifikasiMPP.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("mpp_evaluasi_masalah","?,?,?",3,new String[]{TNoRw.getText(),Valid.SetTgl(TglEvaluasi.getSelectedItem()+"")+" "+TglEvaluasi.getSelectedItem().toString().substring(11,19),tbIdentifikasiMPP.getValueAt(i,1).toString()});
                        }
                    }
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,Perencanaan,BtnBatal);
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
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString())){
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(TDiagnosis.getText().trim().equals("")){
            Valid.textKosong(TDiagnosis,"Diagnosis");
        }else if(TKelompok.getText().trim().equals("")){
            Valid.textKosong(TKelompok,"Kelompok Resiko");
//        }else if(Perencanaan.getText().trim().equals("")){
//            Valid.textKosong(Perencanaan,"Riwayat Penyakit Dahulu");
//        }else if(Assemen.getText().trim().equals("")){
//            Valid.textKosong(Assemen,"Riwayat Penyakit Keluarga");
//        }else if(Identifikasi.getText().trim().equals("")){
//            Valid.textKosong(Identifikasi,"Riwayat Pengobatan");
//        }else if(Perencanaan.getText().trim().equals("")){
//            Valid.textKosong(Perencanaan,"Lokasi");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
        }else if(TDokter1.getText().trim().equals("")){
            Valid.textKosong(TDokter1,"Dokter DPJP");
        }else if(TDokter2.getText().trim().equals("")){
            Valid.textKosong(TDokter2,"Dokter Konsulan");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
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
                if(TCari.getText().equals("")){
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir, " +
                        "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,mpp_evaluasi.tanggal, " +
                        "ifnull(bangsal.nm_bangsal,'Ranap Gabung') as ruang,ifnull(kamar_inap.kd_kamar,'RG') as kamar,kamar_inap.tgl_masuk,kamar_inap.jam_masuk,"+
                        "mpp_evaluasi.kd_dokter,dokterpj.nm_dokter as dpjp,mpp_evaluasi.kd_konsulan,dokterkonsulen.nm_dokter as konsulan, " +
                        "mpp_evaluasi.diagnosis,mpp_evaluasi.kelompok,mpp_evaluasi.assesmen,mpp_evaluasi.identifikasi,mpp_evaluasi.nip,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join mpp_evaluasi on mpp_evaluasi.no_rawat=reg_periksa.no_rawat " +
                        "left join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                        "left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                        "left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        "inner join dokter as dokterpj on mpp_evaluasi.kd_dokter=dokterpj.kd_dokter " +
                        "inner join dokter as dokterkonsulen on mpp_evaluasi.kd_konsulan=dokterkonsulen.kd_dokter " +
                        "inner join petugas on mpp_evaluasi.nip=petugas.nip " +
                        "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel " +
                        "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec " +
                        "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                        "inner join propinsi on pasien.kd_prop=propinsi.kd_prop where "+
                        "mpp_evaluasi.tanggal between ? and ? group by reg_periksa.no_rawat order by mpp_evaluasi.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir, " +
                        "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,mpp_evaluasi.tanggal, " +
                        "ifnull(bangsal.nm_bangsal,'Ranap Gabung') as ruang,ifnull(kamar_inap.kd_kamar,'RG') as kamar,kamar_inap.tgl_masuk,kamar_inap.jam_masuk,"+
                        "mpp_evaluasi.kd_dokter,dokterpj.nm_dokter as dpjp,mpp_evaluasi.kd_konsulan,dokterkonsulen.nm_dokter as konsulan, " +
                        "mpp_evaluasi.diagnosis,mpp_evaluasi.kelompok,mpp_evaluasi.assesmen,mpp_evaluasi.identifikasi,mpp_evaluasi.nip,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join mpp_evaluasi on mpp_evaluasi.no_rawat=reg_periksa.no_rawat " +
                        "left join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                        "left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                        "left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        "inner join dokter as dokterpj on mpp_evaluasi.kd_dokter=dokterpj.kd_dokter " +
                        "inner join dokter as dokterkonsulen on mpp_evaluasi.kd_konsulan=dokterkonsulen.kd_dokter " +
                        "inner join petugas on mpp_evaluasi.nip=petugas.nip " +
                        "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel " +
                        "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec " +
                        "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                        "inner join propinsi on pasien.kd_prop=propinsi.kd_prop where "+
                        "mpp_evaluasi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or "+
                        "pasien.nm_pasien like ? or mpp_evaluasi.nip like ? or petugas.nama like ?) "+
                        "group by reg_periksa.no_rawat order by mpp_evaluasi.tanggal");
                }

                try {
                    if(TCari.getText().equals("")){
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
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50%'><b>PASIEN & PETUGAS</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='30%'><b>IDENTIFIKASI</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='20%'><b>EVALUASI</b></td>"+
                        "</tr>"
                    );
                    while(rs.next()){
                        masalahidentifikasi="";
                        ps2=koneksi.prepareStatement(
                            "select master_masalah_mpp.kode_masalah,master_masalah_mpp.nama_masalah from master_masalah_mpp "+
                            "inner join mpp_evaluasi_masalah on mpp_evaluasi_masalah.kode_masalah=master_masalah_mpp.kode_masalah "+
                            "where mpp_evaluasi_masalah.no_rawat=? and mpp_evaluasi_masalah.tanggal=? order by kode_masalah");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            ps2.setString(2,rs.getString("tanggal"));
                            rs2=ps2.executeQuery();
                            while(rs2.next()){
                                masalahidentifikasi=rs2.getString("nama_masalah")+", "+masalahidentifikasi;
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        htmlContent.append(
                            "<tr class='isi'>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>No.Rawat</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("no_rawat")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>No.R.M.</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Nama Pasien</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>J.K.</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("jk")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Tgl.Lahir</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Alamat</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("alamat")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Kamar</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("kamar")+" "+rs.getString("ruang")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Tgl.Masuk</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("tgl_masuk")+" "+rs.getString("jam_masuk")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Dokter DPJP</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("dpjp")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Dokter Konsulan</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("konsulan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Petugas</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nip")+" "+rs.getString("nama")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Tanggal Evaluasi</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("tanggal")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Diagnosis</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("diagnosis")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Kelompok</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("kelompok")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Assesmen</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("assesmen")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "Masalah MPP : "+masalahidentifikasi+"<br><br>"+
                                    "Rencana MPP : "+rs.getString("rencana")+
                                "</td>"+
                            "</tr>"
                        );
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='100%' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                    File f = new File("DataEvaluasiMPP.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA EVALUASI MANAJER PELAYANAN PASIEN<br><br></font>"+        
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
                ChkAccor.setSelected(true);
                isMenu();
                getMasalah();
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
                    ChkAccor.setSelected(true);
                    isMenu();
                    getMasalah();
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if(Valid.daysOld("./cache/masalahmpp.iyem")<30){
                tampilMasalah2();
            }else{
                tampilMasalah();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            isMenu();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnPrintLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintLapActionPerformed
       if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();    
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select logo from setting"));  
            finger=Sequel.cariIsi("select sha1(sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),19).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),18).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()));  
           try {
                masalahidentifikasi="";
                ps2=koneksi.prepareStatement(
                    "select master_masalah_mpp.kode_masalah,master_masalah_mpp.nama_masalah from master_masalah_mpp "+
                    "inner join mpp_evaluasi_masalah on mpp_evaluasi_masalah.kode_masalah=master_masalah_mpp.kode_masalah "+
                    "where mpp_evaluasi_masalah.no_rawat=? and mpp_evaluasi_masalah.tanggal=? order by kode_masalah");
                try {
                    ps2.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    ps2.setString(2,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
                    rs2=ps2.executeQuery();
                    while(rs2.next()){
                        masalahidentifikasi=rs2.getString("nama_masalah")+", "+masalahidentifikasi;
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs2!=null){
                        rs2.close();
                    }
                    if(ps2!=null){
                        ps2.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
            param.put("masalah",masalahidentifikasi);  
            Valid.MyReportqry("rptCetakEvaluasiAwalMPP.jasper","report","::[ Laporan Evaluasi Awal Manajer Pelayanan Pasien ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir, " +
                        "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,mpp_evaluasi.tanggal, " +
                        "ifnull(bangsal.nm_bangsal,'Ranap Gabung') as ruang,ifnull(kamar_inap.kd_kamar,'RG') as kamar,date_format(kamar_inap.tgl_masuk,'%d-%m-%Y') as tgl_masuk,kamar_inap.jam_masuk,"+
                        "mpp_evaluasi.kd_dokter,dokterpj.nm_dokter as dpjp,mpp_evaluasi.kd_konsulan,dokterkonsulen.nm_dokter as konsulan, " +
                        "mpp_evaluasi.diagnosis,mpp_evaluasi.kelompok,mpp_evaluasi.assesmen,mpp_evaluasi.identifikasi,mpp_evaluasi.nip,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join mpp_evaluasi on mpp_evaluasi.no_rawat=reg_periksa.no_rawat " +
                        "left join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                        "left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                        "left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                        "inner join dokter as dokterpj on mpp_evaluasi.kd_dokter=dokterpj.kd_dokter " +
                        "inner join dokter as dokterkonsulen on mpp_evaluasi.kd_konsulan=dokterkonsulen.kd_dokter " +
                        "inner join petugas on mpp_evaluasi.nip=petugas.nip " +
                        "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel " +
                        "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec " +
                        "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                        "inner join propinsi on pasien.kd_prop=propinsi.kd_prop where "+
                        "mpp_evaluasi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' and "+
                        "mpp_evaluasi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"'",param);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }  
    }//GEN-LAST:event_BtnPrintLapActionPerformed

    private void DetailRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetailRencanaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DetailRencanaKeyPressed

    private void jRadioButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton30ActionPerformed

    private void jRadioButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton29ActionPerformed

    private void jRadioButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton28ActionPerformed

    private void jRadioButton28ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton28ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton28.isSelected()){
            Jk19.setEditable(true);
        } else {
            Jk19.setEditable(false);
            Jk19.setText("");
        }
    }//GEN-LAST:event_jRadioButton28ItemStateChanged

    private void jRadioButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton27ActionPerformed

    private void jRadioButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton26ActionPerformed

    private void jRadioButton26ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton26ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton26.isSelected()){
            Jk18.setEditable(true);
        } else {
            Jk18.setEditable(false);
            Jk18.setText("");
        }
    }//GEN-LAST:event_jRadioButton26ItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jRadioButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton25ActionPerformed

    private void jRadioButton25ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton25ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton25.isSelected()){
            Jk17.setEditable(true);
        } else {
            Jk17.setEditable(false);
            Jk17.setText("");
        }
    }//GEN-LAST:event_jRadioButton25ItemStateChanged

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jRadioButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton24ActionPerformed

    private void jRadioButton24ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton24ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton24.isSelected()){
            Jk16.setEditable(true);
        } else {
            Jk16.setEditable(false);
            Jk16.setText("");
        }
    }//GEN-LAST:event_jRadioButton24ItemStateChanged

    private void jRadioButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton23ActionPerformed

    private void jRadioButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton22ActionPerformed

    private void jRadioButton22ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton22ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton22.isSelected()){
            Jk15.setEditable(true);
        } else {
            Jk15.setEditable(false);
            Jk15.setText("");
        }
    }//GEN-LAST:event_jRadioButton22ItemStateChanged

    private void jRadioButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton21ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        if (jComboBox4.getSelectedIndex() == 6) {
            Jk10.setText("");
            Jk10.setEditable(true);
        } else {
            Jk10.setText("-");
            Jk10.setEditable(false);
        }
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jRadioButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton20ActionPerformed

    private void jRadioButton20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton20ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton20.isSelected()){
            Jk13.setEditable(true);
        } else {
            Jk13.setEditable(false);
            Jk13.setText("");
        }
    }//GEN-LAST:event_jRadioButton20ItemStateChanged

    private void jRadioButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton18ActionPerformed

    private void jRadioButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton16ActionPerformed

    private void jRadioButton16ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton16ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton16.isSelected()){
            Jk14.setEditable(true);
        } else {
            Jk14.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton16ItemStateChanged

    private void jRadioButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton14ActionPerformed

    private void jRadioButton14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton14ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton14.isSelected()){
            Jk12.setEditable(true);
        } else {
            Jk12.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton14ItemStateChanged

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void jRadioButton11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton11ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton11.isSelected()){
            Jk11.setEditable(true);
        } else {
            Jk11.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton11ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        if (jComboBox2.getSelectedIndex() == 6) {
            Jk9.setEditable(true);
            Jk9.setText("");
        } else {
            Jk9.setEditable(false);
            Jk9.setText("-");
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if (jComboBox1.getSelectedIndex() == 6) {
            Jk7.setEditable(true);
            Jk7.setText("");
        } else {
            Jk7.setEditable(false);
            Jk7.setText("-");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton10ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton10.isSelected()){
            Jk8.setEditable(true);
            Jk2.setText("");
            Jk6.setText("");
        } else {
            Jk8.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton10ItemStateChanged

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton9ItemStateChanged
        if (jRadioButton9.isSelected()){
            Jk6.setEditable(true);
            Jk2.setText("");
            Jk8.setText("");
        } else {
            Jk6.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton9ItemStateChanged

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton8ItemStateChanged
        Jk3.setEditable(true);
        Jk4.setText("");
        Jk5.setText("");
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox1.setSelected(false);
        
    }//GEN-LAST:event_jRadioButton8ItemStateChanged

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton6ItemStateChanged
        Jk3.setText("");
        Jk3.setEditable(false);
        Jk5.setText("");
        Jk5.setEditable(false);
        Jk4.setEnabled(true);
        Jk4.setEditable(true);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox1.setSelected(false);
    }//GEN-LAST:event_jRadioButton6ItemStateChanged

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton5ItemStateChanged
        
        if (jRadioButton5.isSelected()){
            Jk2.setEditable(true);
            Jk6.setText("");
            Jk8.setText("");
        } else {
            Jk2.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton5ItemStateChanged

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
            jCheckBox2.setEnabled(true);
            jCheckBox3.setEnabled(true);
            jCheckBox1.setEnabled(true);
            Jk3.setText("");
            Jk4.setText("");
      
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
        if (jRadioButton1.isSelected()){
            Jk1.setEditable(true);
        } else {
            Jk1.setEditable(false);
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void BtnAllMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllMasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAllMasalahKeyPressed

    private void BtnAllMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllMasalahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAllMasalahActionPerformed

    private void TKelompokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKelompokKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKelompokKeyPressed

    private void TDiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDiagnosisKeyPressed

    private void btnDokter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter2KeyPressed

    private void btnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter2ActionPerformed
        pilihan=2;
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter2ActionPerformed

    private void KdDok2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            TDokter2.setText(dokter.tampil3(KdDok2.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokter2ActionPerformed(null);
        }
    }//GEN-LAST:event_KdDok2KeyPressed

    private void btnDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter1KeyPressed

    private void btnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter1ActionPerformed
        pilihan=1;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter1ActionPerformed

    private void KdDok1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdDok1KeyPressed

    private void KamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KamarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KamarKeyPressed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void BtnCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariMasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariMasalahKeyPressed

    private void BtnCariMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariMasalahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariMasalahActionPerformed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariMasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariMasalahKeyPressed

    private void TglEvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglEvaluasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglEvaluasiKeyPressed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRwKeyPressed

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        Jk1.setText("");
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jRadioButton7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton7ItemStateChanged
        Jk3.setText("");
        Jk5.setText("");
        Jk4.setText("");
        Jk3.setEditable(false);
        Jk5.setEditable(false);
        Jk4.setEditable(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        
    }//GEN-LAST:event_jRadioButton7ItemStateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       if (jCheckBox1.isSelected()){
           Jk5.setEnabled(true);
           Jk5.setEditable(true);
       } else {
           Jk5.setEnabled(false);
           Jk5.setEditable(false);
           Jk5.setText("");
       }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        Jk6.setText("");
        Jk2.setText("");
        Jk8.setText("");
    }//GEN-LAST:event_jRadioButton3ItemStateChanged

    private void jRadioButton12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton12ItemStateChanged
        Jk11.setText("");
    }//GEN-LAST:event_jRadioButton12ItemStateChanged

    private void jRadioButton13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton13ItemStateChanged
        Jk12.setText("");
    }//GEN-LAST:event_jRadioButton13ItemStateChanged

    private void jRadioButton15ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton15ItemStateChanged
        Jk14.setText("");
    }//GEN-LAST:event_jRadioButton15ItemStateChanged

    private void Jk7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jk7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jk7ActionPerformed

    private void Jk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jk1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSkriningMPPFormA dialog = new RMSkriningMPPFormA(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.Button BtnAll;
    private widget.Button BtnAllMasalah;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariMasalah;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnPrintLap;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahMasalah;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DetailRencana;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox Jk;
    private widget.TextBox Jk1;
    private widget.TextBox Jk10;
    private widget.TextBox Jk11;
    private widget.TextBox Jk12;
    private widget.TextBox Jk13;
    private widget.TextBox Jk14;
    private widget.TextBox Jk15;
    private widget.TextBox Jk16;
    private widget.TextBox Jk17;
    private widget.TextBox Jk18;
    private widget.TextBox Jk19;
    private widget.TextBox Jk2;
    private widget.TextBox Jk20;
    private widget.TextBox Jk21;
    private widget.TextBox Jk3;
    private widget.TextBox Jk4;
    private widget.TextBox Jk43;
    private widget.TextBox Jk46;
    private widget.TextBox Jk47;
    private widget.TextBox Jk48;
    private widget.TextBox Jk49;
    private widget.TextBox Jk5;
    private widget.TextBox Jk50;
    private widget.TextBox Jk51;
    private widget.TextBox Jk52;
    private widget.TextBox Jk53;
    private widget.TextBox Jk54;
    private widget.TextBox Jk55;
    private widget.TextBox Jk56;
    private widget.TextBox Jk57;
    private widget.TextBox Jk58;
    private widget.TextBox Jk6;
    private widget.TextBox Jk7;
    private widget.TextBox Jk8;
    private widget.TextBox Jk9;
    private widget.TextBox Kamar;
    private widget.TextBox KdDok1;
    private widget.TextBox KdDok2;
    private widget.TextBox KdPetugas;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.TextBox NmPetugas;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TCari;
    private widget.TextBox TCariMasalah;
    private widget.TextArea TDiagnosis;
    private widget.TextBox TDokter1;
    private widget.TextBox TDokter2;
    private widget.TextArea TKelompok;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglEvaluasi;
    private widget.TextBox TglLahir;
    private widget.TextBox TglMasuk;
    private widget.Button btnDokter1;
    private widget.Button btnDokter2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
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
    private widget.Label jLabel5;
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
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JRadioButton jRadioButton19;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton20;
    private javax.swing.JRadioButton jRadioButton21;
    private javax.swing.JRadioButton jRadioButton22;
    private javax.swing.JRadioButton jRadioButton23;
    private javax.swing.JRadioButton jRadioButton24;
    private javax.swing.JRadioButton jRadioButton25;
    private javax.swing.JRadioButton jRadioButton26;
    private javax.swing.JRadioButton jRadioButton27;
    private javax.swing.JRadioButton jRadioButton28;
    private javax.swing.JRadioButton jRadioButton29;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton30;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JSeparator jSeparator3;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbIdentifikasiMPP;
    private widget.Table tbMasalahDetailMasalah;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir, " +
                    "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,mpp_evaluasi.tanggal, " +
                    "ifnull(bangsal.nm_bangsal,'Ranap Gabung') as ruang,ifnull(kamar_inap.kd_kamar,'RG') as kamar,kamar_inap.tgl_masuk,kamar_inap.jam_masuk,"+
                    "mpp_evaluasi.kd_dokter,dokterpj.nm_dokter as dpjp,mpp_evaluasi.kd_konsulan,dokterkonsulen.nm_dokter as konsulan, " +
                    "mpp_evaluasi.diagnosis,mpp_evaluasi.kelompok,mpp_evaluasi.assesmen_adl,mpp_evaluasi.assesmen_adl_ket,mpp_evaluasi.assesmen_riwayat,mpp_evaluasi.assesmen_kronis_ket,"+
                    "mpp_evaluasi.assesmen_polakebiasaan,mpp_evaluasi.assesmen_pola_ket,mpp_evaluasi.assesmen_riwayat_lain,mpp_evaluasi.assesmen_spiritual,mpp_evaluasi.assesmen_agama,mpp_evaluasi.assesmen_sosial,"+
                    "mpp_evaluasi.assesmen_budaya,mpp_evaluasi.assesmen_lingkungan,mpp_evaluasi.assesmen_lingkungan_ket,mpp_evaluasi.assesmen_dukungan,mpp_evaluasi.assesmen_dukungan_ket,mpp_evaluasi.assesmen_finansial,"+
                    "mpp_evaluasi.assesmen_finansial_ket,mpp_evaluasi.assesmen_mental,mpp_evaluasi.assesmen_mental_ket,mpp_evaluasi.assesmen_alternatif,mpp_evaluasi.assesmen_alternatif_ket,mpp_evaluasi.assesmen_pemahaman,"+
                    "mpp_evaluasi.assesmen_pemahaman_ket,mpp_evaluasi.assesmen_harapan,mpp_evaluasi.assesmen_harapan_ket,mpp_evaluasi.assesmen_asuransi,mpp_evaluasi.assesmen_trauma,mpp_evaluasi.assesmen_trauma_ket,mpp_evaluasi.assesmen_legal,"+
                    "mpp_evaluasi.assesmen_legal_ket,mpp_evaluasi.identifikasi_masalah,mpp_evaluasi.identifikasi_masalah_lain,mpp_evaluasi.perencanaan_mpp,mpp_evaluasi.perencanaan_mpp_lain,mpp_evaluasi.keputusan,mpp_evaluasi.keputusan_lain,mpp_evaluasi.kesimpulan,"+
                    "mpp_evaluasi.DP1a,mpp_evaluasi.DP1b,mpp_evaluasi.DP1c,mpp_evaluasi.DP1d,mpp_evaluasi.DP2a,mpp_evaluasi.DP2b,mpp_evaluasi.DP2c,mpp_evaluasi.DP3a,mpp_evaluasi.DP3b,mpp_evaluasi.DP4a,mpp_evaluasi.DP4b,"+
                    "mpp_evaluasi.DP5a,mpp_evaluasi.DP5b,mpp_evaluasi.DP5c,mpp_evaluasi.DP6a,mpp_evaluasi.DP6b,"+
                    "mpp_evaluasi.nip,"+
                    "petugas.nama "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join mpp_evaluasi on mpp_evaluasi.no_rawat=reg_periksa.no_rawat " +
                    "left join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                    "left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                    "left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                    "inner join dokter as dokterpj on mpp_evaluasi.kd_dokter=dokterpj.kd_dokter " +
                    "inner join dokter as dokterkonsulen on mpp_evaluasi.kd_konsulan=dokterkonsulen.kd_dokter " +
                    "inner join petugas on mpp_evaluasi.nip=petugas.nip " +
                    "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel " +
                    "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec " +
                    "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                    "inner join propinsi on pasien.kd_prop=propinsi.kd_prop where "+
                    "mpp_evaluasi.tanggal between ? and ? group by mpp_evaluasi.no_rawat,mpp_evaluasi.tanggal order by mpp_evaluasi.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir, " +
                    "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) as alamat,mpp_evaluasi.tanggal, " +
                    "ifnull(bangsal.nm_bangsal,'Ranap Gabung') as ruang,ifnull(kamar_inap.kd_kamar,'RG') as kamar,kamar_inap.tgl_masuk,kamar_inap.jam_masuk,"+
                    "mpp_evaluasi.kd_dokter,dokterpj.nm_dokter as dpjp,mpp_evaluasi.kd_konsulan,dokterkonsulen.nm_dokter as konsulan," +
                    "mpp_evaluasi.diagnosis,mpp_evaluasi.kelompok,mpp_evaluasi.assesmen_adl,mpp_evaluasi.assesmen_adl_ket,mpp_evaluasi.assesmen_riwayat,mpp_evaluasi.assesmen_kronis_ket,"+
                    "mpp_evaluasi.assesmen_polakebiasaan,mpp_evaluasi.assesmen_pola_ket,mpp_evaluasi.assesmen_riwayat_lain,mpp_evaluasi.assesmen_spiritual,mpp_evaluasi.assesmen_agama,mpp_evaluasi.assesmen_sosial,"+
                    "mpp_evaluasi.assesmen_budaya,mpp_evaluasi.assesmen_lingkungan,mpp_evaluasi.assesmen_lingkungan_ket,mpp_evaluasi.assesmen_dukungan,mpp_evaluasi.assesmen_dukungan_ket,mpp_evaluasi.assesmen_finansial,"+
                    "mpp_evaluasi.assesmen_finansial_ket,mpp_evaluasi.assesmen_mental,mpp_evaluasi.assesmen_mental_ket,mpp_evaluasi.assesmen_alternatif,mpp_evaluasi.assesmen_alternatif_ket,mpp_evaluasi.assesmen_pemahaman,"+
                    "mpp_evaluasi.assesmen_pemahaman_ket,mpp_evaluasi.assesmen_harapan,mpp_evaluasi.assesmen_harapan_ket,mpp_evaluasi.assesmen_asuransi,mpp_evaluasi.assesmen_trauma,mpp_evaluasi.assesmen_trauma_ket,mpp_evaluasi.assesmen_legal,"+
                    "mpp_evaluasi.assesmen_legal_ket,mpp_evaluasi.identifikasi_masalah,mpp_evaluasi.identifikasi_masalah_lain,mpp_evaluasi.perencanaan_mpp,mpp_evaluasi.perencanaan_mpp_lain,mpp_evaluasi.keputusan,mpp_evaluasi.keputusan_lain,mpp_evaluasi.kesimpulan,"+
                    "mpp_evaluasi.DP1a,mpp_evaluasi.DP1b,mpp_evaluasi.DP1c,mpp_evaluasi.DP1d,mpp_evaluasi.DP2a,mpp_evaluasi.DP2b,mpp_evaluasi.DP2c,mpp_evaluasi.DP3a,mpp_evaluasi.DP3b,mpp_evaluasi.DP4a,mpp_evaluasi.DP4b,"+
                    "mpp_evaluasi.DP5a,mpp_evaluasi.DP5b,mpp_evaluasi.DP5c,mpp_evaluasi.DP6a,mpp_evaluasi.DP6b," +
                    "mpp_evaluasi.nip,"+
                    "petugas.nama "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join mpp_evaluasi on mpp_evaluasi.no_rawat=reg_periksa.no_rawat " +
                    "left join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                    "left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                    "left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                    "inner join dokter as dokterpj on mpp_evaluasi.kd_dokter=dokterpj.kd_dokter " +
                    "inner join dokter as dokterkonsulen on mpp_evaluasi.kd_konsulan=dokterkonsulen.kd_dokter " +
                    "inner join petugas on mpp_evaluasi.nip=petugas.nip " +
                    "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel " +
                    "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec " +
                    "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                    "inner join propinsi on pasien.kd_prop=propinsi.kd_prop where "+
                    "mpp_evaluasi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or "+
                    "pasien.nm_pasien like ? or mpp_evaluasi.nip like ? or petugas.nama like ?) "+
                    "group by mpp_evaluasi.no_rawat,mpp_evaluasi.tanggal order by mpp_evaluasi.tanggal");
            }
                
            try {
                if(TCari.getText().equals("")){
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("jk"),rs.getString("tgl_lahir"),rs.getString("alamat"),rs.getString("tanggal"),
                        rs.getString("kamar")+" "+rs.getString("ruang"),rs.getString("tgl_masuk")+" "+rs.getString("jam_masuk"),rs.getString("kd_dokter"),rs.getString("dpjp"),rs.getString("kd_konsulan"),
                        rs.getString("konsulan"),rs.getString("diagnosis"),rs.getString("kelompok"),
                        
                        rs.getString("assesmen_adl"),rs.getString("assesmen_adl_ket"),
                        rs.getString("assesmen_riwayat"),rs.getString("assesmen_kronis_ket"),rs.getString("assesmen_polakebiasaan"),rs.getString("assesmen_pola_ket"),rs.getString("assesmen_riwayat_lain"),
                        
                        rs.getString("assesmen_spiritual"),rs.getString("assesmen_agama"),rs.getString("assesmen_sosial"),rs.getString("assesmen_budaya"),
                        
                        rs.getString("assesmen_lingkungan"),rs.getString("assesmen_lingkungan_ket"),rs.getString("assesmen_dukungan"),rs.getString("assesmen_dukungan_ket"),rs.getString("assesmen_finansial"),
                        rs.getString("assesmen_finansial_ket"),rs.getString("assesmen_mental"),rs.getString("assesmen_mental_ket"),rs.getString("assesmen_alternatif"),rs.getString("assesmen_alternatif_ket"),rs.getString("assesmen_pemahaman"),
                        rs.getString("assesmen_pemahaman_ket"),rs.getString("assesmen_harapan"),rs.getString("assesmen_harapan_ket"),rs.getString("assesmen_asuransi"),rs.getString("assesmen_trauma"),rs.getString("assesmen_trauma_ket"),rs.getString("assesmen_legal"),
                        rs.getString("assesmen_legal_ket"),rs.getString("identifikasi_masalah"),rs.getString("identifikasi_masalah_lain"),rs.getString("perencanaan_mpp"),rs.getString("perencanaan_mpp_lain"),rs.getString("keputusan"),rs.getString("keputusan_lain"),rs.getString("kesimpulan"),
                        
                        rs.getString("DP1a"),rs.getString("DP1b"),rs.getString("DP1c"),rs.getString("DP1d"),rs.getString("DP2a"),rs.getString("DP2b"),rs.getString("DP2c"),rs.getString("DP3a"),rs.getString("DP3b"),rs.getString("DP4a"),rs.getString("DP4b"),
                        rs.getString("DP5a"),rs.getString("DP5b"),rs.getString("DP5c"),rs.getString("DP6a"),rs.getString("DP6b"),
                        rs.getString("nip"),rs.getString("nama")
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
        TglEvaluasi.setDate(new Date());
        KdDok1.setText("");
        KdDok2.setText("");
        TDiagnosis.setText("");
        TKelompok.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        Jk1.setText("");
        jRadioButton4.setSelected(false);
        jRadioButton6.setSelected(false);
        jRadioButton7.setSelected(false);
        jRadioButton8.setSelected(false);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        Jk3.setText("");
        Jk4.setText("");
        Jk5.setText("");
        jRadioButton3.setSelected(false);
        jRadioButton5.setSelected(false);
        jRadioButton9.setSelected(false);
        jRadioButton10.setSelected(false);
        Jk2.setText("");
        Jk6.setText("");
        Jk8.setText("");
        jComboBox1.setSelectedIndex(0);
        Jk7.setText("");
        jComboBox2.setSelectedIndex(0);
        Jk9.setText("");
        jComboBox4.setSelectedIndex(0);
        Jk10.setText("");
        jRadioButton11.setSelected(false);
        jRadioButton12.setSelected(false);
        Jk11.setText("");
        jRadioButton13.setSelected(false);
        jRadioButton14.setSelected(false);
        Jk12.setText("");
        jRadioButton15.setSelected(false);
        jRadioButton16.setSelected(false);
        Jk14.setText("");
        jRadioButton17.setSelected(false);
        jRadioButton18.setSelected(false);
        jRadioButton19.setSelected(false);
        jRadioButton20.setSelected(false);
        Jk13.setText("");
        jComboBox5.setSelectedIndex(0);
        jRadioButton20.setSelected(false);
        jRadioButton21.setSelected(false);
        Jk15.setText("");
        jRadioButton23.setSelected(false);
        jRadioButton24.setSelected(false);
        Jk16.setText("");
        jComboBox3.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jRadioButton25.setSelected(false);
        jRadioButton26.setSelected(false);
        jRadioButton27.setSelected(false);
        jRadioButton28.setSelected(false);
        Jk17.setText("");
        Jk18.setText("");
        Jk19.setText("");
        jRadioButton29.setSelected(false);
        jRadioButton30.setSelected(false);
        Jk21.setText("");
        Jk43.setText("");
        Jk46.setText("");
        Jk47.setText("");
        Jk48.setText("");
        Jk49.setText("");
        Jk50.setText("");
        Jk51.setText("");
        Jk52.setText("");
        Jk53.setText("");
        Jk54.setText("");
        Jk55.setText("");
        Jk56.setText("");
        Jk57.setText("");
        Jk20.setText("");
        Jk58.setText("");
        
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false,i,0);
        }
        TabRawat.setSelectedIndex(0);
        TDiagnosis.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); 
            Alamat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); 
            Kamar.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); 
            TglMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            KdDok1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            TDokter1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            KdDok2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            TDokter2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            TDiagnosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            TKelompok.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            
            String assesmenadl = tbObat.getValueAt(tbObat.getSelectedRow(),15).toString();
                if (assesmenadl.contains("Tidak Ada Hambatan")){
                    jRadioButton2.setSelected(true);
                } else if (assesmenadl.contains("Ada")){
                    jRadioButton1.setSelected(true);
                }
            
            Jk1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            
            String assesmenriwayat = tbObat.getValueAt(tbObat.getSelectedRow(),17).toString();
                if (assesmenriwayat.contains("Tidak Ada")){
                    jRadioButton7.setSelected(true);
                } else if (assesmenriwayat.contains("Penyakit Kronis")){
                    jRadioButton8.setSelected(true);
                } else if (assesmenriwayat.contains("Pola Kebiasaan")){
                    jRadioButton4.setSelected(true);
                } else if (assesmenriwayat.contains("Lain-lain")){
                    jRadioButton6.setSelected(true);
                }
            
            Jk3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            
            String polakebiasaan = tbObat.getValueAt(tbObat.getSelectedRow(),19).toString();
                if (polakebiasaan.contains("Merokok")){
                    jCheckBox2.setSelected(true);
                } else if (polakebiasaan.contains("Konsumsi Alkohol")){
                    jCheckBox3.setSelected(true);
                } else {
                    jCheckBox1.setSelected(true);
                }
            
            Jk5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            Jk4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            
            String assesmenspiritual = tbObat.getValueAt(tbObat.getSelectedRow(),22).toString();
                if (assesmenspiritual.contains("Tidak Ada Hambatan")){
                    jRadioButton3.setSelected(true);
                }else if (assesmenspiritual.contains("Nilai keyakinan agama tertentu")){
                    jRadioButton9.setSelected(true);
                }else if ( assesmenspiritual.contains("Nilai keyakinan sosial tertentu")){
                   jRadioButton5.setSelected(true);
                }else if (assesmenspiritual.contains("Nilai budaya tertentu")){
                   jRadioButton10.setSelected(true);    
                }
            
            Jk6.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());    
            Jk2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());    
            Jk8.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());    
        
            jComboBox1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Jk7.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            jComboBox2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Jk9.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            jComboBox4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            Jk10.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            
            String assesmenmental = tbObat.getValueAt(tbObat.getSelectedRow(),32).toString();
                if (assesmenmental.contains("Baik")){
                    jRadioButton12.setSelected(true);
                }else if (assesmenmental.contains("Tidak baik")){
                    jRadioButton11.setSelected(true);
                }
            
            Jk11.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            
            String assesmenalternatif = tbObat.getValueAt(tbObat.getSelectedRow(),34).toString();
                if (assesmenalternatif.contains("Tidak Ada")){
                    jRadioButton13.setSelected(true);
                }else if (assesmenalternatif.contains("Ada")){
                    jRadioButton14.setSelected(true);
                }
                    
            Jk12.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            
            String assesmenpemahaman = tbObat.getValueAt(tbObat.getSelectedRow(),36).toString();
                if (assesmenpemahaman.contains("Baik")){
                    jRadioButton15.setSelected(true);
                }else if (assesmenpemahaman.contains("Kurang")){
                    jRadioButton16.setSelected(true);
                }
        
            Jk14.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            
            String assesmenharapan = tbObat.getValueAt(tbObat.getSelectedRow(),38).toString();
                if (assesmenharapan.contains("Kondisi pasien membaik dan/atau sembuh")){
                    jRadioButton17.setSelected(true);
                }else if (assesmenharapan.contains("Segera dilakukan tindakan")){
                    jRadioButton18.setSelected(true);
                }else if ( assesmenharapan.contains("Keluarga pasrah terhadap kondisi pasien")){
                   jRadioButton19.setSelected(true);
                }else if (assesmenharapan.contains("Lain-lain")){
                   jRadioButton20.setSelected(true);    
                }
                
            Jk13.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            
            jComboBox5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            
            String assesmentrauma = tbObat.getValueAt(tbObat.getSelectedRow(),41).toString();
                if (assesmentrauma.contains("Ada")){
                    jRadioButton15.setSelected(true);
                }else if (assesmentrauma.contains("Tidak Ada")){
                    jRadioButton16.setSelected(true);
                }    
            Jk15.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());  
            
            String assesmenlegal = tbObat.getValueAt(tbObat.getSelectedRow(),43).toString();
                if (assesmenlegal.contains("Tidak Dibutuhkan")){
                    jRadioButton23.setSelected(true);
                }else if (assesmenlegal.contains("Dibutuhkan")){
                    jRadioButton24.setSelected(true);
                }
            
            Jk16.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString()); 
            
            jComboBox3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            Jk17.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            jComboBox6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            Jk18.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
                    
            String keputusan = tbObat.getValueAt(tbObat.getSelectedRow(),49).toString();
                if (keputusan.contains("Melanjutkan asuhan medis dan asuhan Perawatan dan/atau Pengobatan")){
                    jRadioButton27.setSelected(true);
                }else if (keputusan.contains("Menghentikan asuhan medis dan asuhan perawatan dan/atau pengobatan")){
                    jRadioButton28.setSelected(true);   
                }
            Jk19.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());        
            
            String kesimpulan = tbObat.getValueAt(tbObat.getSelectedRow(),51).toString();
                if (kesimpulan.contains("Ya, lanjut penatalaksanaan di FORM B")){
                    jRadioButton29.setSelected(true);
                }else if (kesimpulan.contains("Tidak, dikembalikan kepada PPA")){
                    jRadioButton30.setSelected(true);
                }
            
            Jk21.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            Jk43.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            Jk46.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            Jk47.setText(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            Jk48.setText(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            Jk49.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            Jk50.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            Jk51.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            Jk52.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            Jk53.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            Jk54.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            Jk55.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            Jk56.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            Jk57.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            Jk20.setText(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            Jk58.setText(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());        
            
//            Assemen.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
//            Identifikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
//            Perencanaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            Valid.SetTgl2(TglEvaluasi,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            
            try {
                Valid.tabelKosong(tabModeMasalah);
                ps=koneksi.prepareStatement(
                        "select master_masalah_mpp.kode_masalah,master_masalah_mpp.nama_masalah from master_masalah_mpp "+
                        "inner join mpp_evaluasi_masalah on mpp_evaluasi_masalah.kode_masalah=master_masalah_mpp.kode_masalah "+
                        "where mpp_evaluasi_masalah.no_rawat=? and mpp_evaluasi_masalah.tanggal=? order by kode_masalah");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    ps.setString(2,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeMasalah.addRow(new Object[]{true,rs.getString(1),rs.getString(2)});
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
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,"+
                    "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop)as alamat,ifnull(bangsal.nm_bangsal,'Ranap Gabung') as nm_bangsal, "+
                    "ifnull(kamar_inap.kd_kamar,'RG') as kamar,kamar_inap.tgl_masuk,kamar_inap.jam_masuk "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "+
                    "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                    "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                    "inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                    "left join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat "+
                    "left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar "+
                    "left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "+
                    "where reg_periksa.no_rawat=? group by reg_periksa.no_rawat");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Alamat.setText(rs.getString("alamat"));
                    TglMasuk.setText(rs.getString("tgl_masuk")+" "+rs.getString("jam_masuk"));
                    Kamar.setText(rs.getString("kamar")+" "+rs.getString("nm_bangsal"));
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
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getmpp_skrining());
        BtnHapus.setEnabled(akses.getmpp_skrining());
        BtnEdit.setEnabled(akses.getmpp_skrining());
        BtnPrint.setEnabled(akses.getmpp_skrining());   
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
        }            
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }
    
    private void tampilMasalah() {
        try{
            Valid.tabelKosong(tabModeMasalah);
            file=new File("./cache/masalahmpp.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_masalah_mpp order by master_masalah_mpp.kode_masalah");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    tabModeMasalah.addRow(new Object[]{false,rs.getString(1),rs.getString(2)});
                    iyem=iyem+"{\"KodeMasalah\":\""+rs.getString(1)+"\",\"NamaMasalah\":\""+rs.getString(2)+"\"},";
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
            fileWriter.write("{\"masalahmpp\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilMasalah2() {
        try{
            jml=0;
            for(i=0;i<tbIdentifikasiMPP.getRowCount();i++){
                if(tbIdentifikasiMPP.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml]; 
            kode=null;
            kode=new String[jml];
            masalah=null;
            masalah=new String[jml];

            index=0;        
            for(i=0;i<tbIdentifikasiMPP.getRowCount();i++){
                if(tbIdentifikasiMPP.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbIdentifikasiMPP.getValueAt(i,1).toString();
                    masalah[index]=tbIdentifikasiMPP.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeMasalah);

            for(i=0;i<jml;i++){
                tabModeMasalah.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }
            
            myObj = new FileReader("./cache/masalahmpp.iyem");
            root = mapper.readTree(myObj);
            response = root.path("masalahmpp");
            if(response.isArray()){
                for(JsonNode list:response){
                    if(list.path("KodeMasalah").asText().toLowerCase().contains(TCariMasalah.getText().toLowerCase())||list.path("NamaMasalah").asText().toLowerCase().contains(TCariMasalah.getText().toLowerCase())){
                        tabModeMasalah.addRow(new Object[]{
                            false,list.path("KodeMasalah").asText(),list.path("NamaMasalah").asText()
                        });                    
                    }
                }
            }
            myObj.close();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470,HEIGHT));
            FormMenu.setVisible(true);  
            FormMasalahRencana.setVisible(true);  
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){   
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);  
            FormMasalahRencana.setVisible(false);   
            ChkAccor.setVisible(true);
        }
    }

    private void getMasalah() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            try {
                Valid.tabelKosong(tabModeDetailMasalah);
                ps=koneksi.prepareStatement(
                        "select master_masalah_mpp.kode_masalah,master_masalah_mpp.nama_masalah from master_masalah_mpp "+
                        "inner join mpp_evaluasi_masalah on mpp_evaluasi_masalah.kode_masalah=master_masalah_mpp.kode_masalah "+
                        "where mpp_evaluasi_masalah.no_rawat=? and mpp_evaluasi_masalah.tanggal=? order by master_masalah_mpp.kode_masalah");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    ps.setString(2,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeDetailMasalah.addRow(new Object[]{rs.getString(1),rs.getString(2)});
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
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from mpp_evaluasi where no_rawat=? and tanggal=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()
        })==true){
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("mpp_evaluasi_masalah","no_rawat","tanggal",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Valid.tabelKosong(tabModeDetailMasalah);
            ChkAccor.setSelected(false);
            isMenu();
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        String assesmenadl = "";
        String assesmenriwayat = "";
        String polakebiasaan = "";
        String assesmenspiritual = "";
        String assesmenmental = "";
        String assesmenalternatif = "";
        String assesmenpemahaman = "";
        String assesmenharapan = "";
        String assesmentrauma = "";
        String assesmenlegal = "";
        
        String keputusan = "";
        
        String kesimpulan = "";
        
        if (jRadioButton2.isSelected()){
            assesmenadl = "Tidak Ada Hambatan";
        }else if (jRadioButton1.isSelected()){
            assesmenadl = "Ada";
        }
        
        if (jRadioButton7.isSelected()){
            assesmenriwayat = "Tidak Ada";
        } else if (jRadioButton8.isSelected()){
            assesmenriwayat = "Penyakit Kronis";
        } else if (jRadioButton4.isSelected()){
            assesmenriwayat = "Pola Kebiasaan";
        } else if (jRadioButton6.isSelected()){
            assesmenriwayat = "Lain-lain";
        }
        
        
        if (jCheckBox2.isSelected()) {
            polakebiasaan += "Merokok, ";
        }
        if (jCheckBox3.isSelected()) {
            polakebiasaan += "Konsumsi Alkohol, ";
        }
        if (jCheckBox1.isSelected()) {
            polakebiasaan += "Lain-lain";
        }
        polakebiasaan = polakebiasaan.trim();
        if (polakebiasaan.endsWith(", ")) {
            polakebiasaan = polakebiasaan.substring(0, polakebiasaan.length() - 2);
        }
        
        if (jRadioButton3.isSelected()) {
            assesmenspiritual = "Tidak Ada Hambatan";
        }else if (jRadioButton9.isSelected()){
            assesmenspiritual = "Nilai keyakinan agama tertentu";
        }else if (jRadioButton5.isSelected()){
            assesmenspiritual = "Nilai keyakinan sosial tertentu";
        }else if (jRadioButton10.isSelected()){
            assesmenspiritual = "Nilai budaya tertentu";
        }
        
        if (jRadioButton12.isSelected()){
            assesmenmental = "Baik";
        }else if (jRadioButton11.isSelected()){
            assesmenmental = "Tidak baik";
        }
        
        if (jRadioButton13.isSelected()){
            assesmenalternatif = "Tidak Ada";
        }else if (jRadioButton14.isSelected()){
            assesmenalternatif = "Ada";
        }
        
        if (jRadioButton15.isSelected()){
            assesmenpemahaman = "Baik";
        }else if (jRadioButton16.isSelected()){
            assesmenpemahaman = "Kurang";
        }
        
        if (jRadioButton17.isSelected()){
            assesmenharapan = "Kondisi pasien membaik dan/atau sembuh";
        }else if (jRadioButton18.isSelected()){
            assesmenharapan = "Segera dilakukan tindakan";
        }else if (jRadioButton19.isSelected()){
            assesmenharapan = "Keluarga pasrah terhadap kondisi pasien";
        }else if (jRadioButton20.isSelected()){
            assesmenharapan = "Lain-lain";
        }
        
        if (jRadioButton22.isSelected()){
            assesmentrauma = "Ada";
        }else if (jRadioButton21.isSelected()){
            assesmentrauma = "Tidak Ada";
        }
        
        if (jRadioButton23.isSelected()){
            assesmenlegal = "Tidak Dibutuhkan";
        }else if (jRadioButton24.isSelected()){
            assesmenlegal = "Dibutuhkan";
        }
        
        if (jRadioButton27.isSelected()){
            keputusan = "Melanjutkan asuhan medis dan asuhan Perawatan dan/atau Pengobatan";
        }else if (jRadioButton28.isSelected()){
            keputusan = "Menghentikan asuhan medis dan asuhan perawatan dan/atau pengobatan";
        }
        
        if (jRadioButton29.isSelected()){
            kesimpulan = "Ya, lanjut penatalaksanaan di FORM B";
        }else if (jRadioButton30.isSelected()){
            kesimpulan = "Tidak, dikembalikan kepada PPA";
        }
        
        
        if(tbObat.getSelectedRow()>-1){
            if(Sequel.mengedittf("mpp_evaluasi","no_rawat=? and tanggal=?","no_rawat=?,tanggal=?,kd_dokter=?,kd_konsulan=?,diagnosis=?,kelompok=?,"+
                    "assesmen_adl=?,assesmen_adl_ket=?,assesmen_riwayat=?,assesmen_kronis_ket=?,"+
                    "assesmen_polakebiasaan=?,assesmen_pola_ket=?,assesmen_riwayat_lain=?,assesmen_spiritual=?,assesmen_agama=?,assesmen_sosial=?,"+
                    "assesmen_budaya=?,assesmen_lingkungan=?,assesmen_lingkungan_ket=?,assesmen_dukungan=?,assesmen_dukungan_ket=?,assesmen_finansial=?,"+
                    "assesmen_finansial_ket=?,assesmen_mental=?,assesmen_mental_ket=?,assesmen_alternatif=?,assesmen_alternatif_ket=?,assesmen_pemahaman=?,"+
                    "assesmen_pemahaman_ket=?,assesmen_harapan=?,assesmen_harapan_ket=?,assesmen_asuransi=?,assesmen_trauma=?,assesmen_trauma_ket=?,assesmen_legal=?,"+
                    "assesmen_legal_ket=?,identifikasi_masalah=?,identifikasi_masalah_lain=?,perencanaan_mpp=?,perencanaan_mpp_lain=?,keputusan=?,keputusan_lain=?,kesimpulan=?,"+
                    "DP1a=?,DP1b=?,DP1c=?,DP1d=?,DP2a=?,DP2b=?,DP2c=?,DP3a=?,DP3b=?,DP4a=?,DP4b=?,"+
                    "DP5a=?,DP5b=?,DP5c=?,DP6a=?,DP6b=?,"+
                    "nip=?",61,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglEvaluasi.getSelectedItem()+"")+" "+TglEvaluasi.getSelectedItem().toString().substring(11,19),KdDok1.getText(),KdDok2.getText(),TDiagnosis.getText(),TKelompok.getText(),
                    assesmenadl,Jk1.getText(),
                    assesmenriwayat,Jk3.getText(),polakebiasaan,Jk5.getText(),Jk4.getText(),
                    assesmenspiritual,Jk6.getText(),Jk2.getText(),Jk8.getText(),
                    jComboBox1.getSelectedItem().toString(),Jk7.getText(),
                    jComboBox2.getSelectedItem().toString(),Jk9.getText(),
                    jComboBox4.getSelectedItem().toString(),Jk10.getText(),
                    assesmenmental,Jk11.getText(),
                    assesmenalternatif,Jk12.getText(),
                    assesmenpemahaman,Jk14.getText(),
                    assesmenharapan,Jk13.getText(),
                    jComboBox5.getSelectedItem().toString(),
                    assesmentrauma,Jk15.getText(),
                    assesmenlegal,Jk16.getText(),
                    jComboBox3.getSelectedItem().toString(),Jk17.getText(),
                    jComboBox6.getSelectedItem().toString(),Jk18.getText(),
                    keputusan,Jk19.getText(),
                    kesimpulan,
                    Jk21.getText(),Jk43.getText(),Jk46.getText(),Jk47.getText(),
                    Jk48.getText(),Jk49.getText(),Jk50.getText(),
                    Jk51.getText(),Jk52.getText(),
                    Jk53.getText(),Jk54.getText(),
                    Jk55.getText(),Jk56.getText(),Jk57.getText(),
                    Jk58.getText(),Jk20.getText(),
                       
                    KdPetugas.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()
                 })==true){
                    Sequel.meghapus("mpp_evaluasi_masalah","no_rawat","tanggal",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
                    Valid.tabelKosong(tabModeDetailMasalah);
                    tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
                    tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
                    tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
                    tbObat.setValueAt(Jk.getText(),tbObat.getSelectedRow(),3);
                    tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),4);
                    tbObat.setValueAt(Alamat.getText(),tbObat.getSelectedRow(),5);
                    tbObat.setValueAt(Valid.SetTgl(TglEvaluasi.getSelectedItem()+"")+" "+TglEvaluasi.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),6);
                    tbObat.setValueAt(Kamar.getText(),tbObat.getSelectedRow(),7);
                    tbObat.setValueAt(TglMasuk.getText(),tbObat.getSelectedRow(),8);
                    tbObat.setValueAt(KdDok1.getText(),tbObat.getSelectedRow(),9);
                    tbObat.setValueAt(TDokter1.getText(),tbObat.getSelectedRow(),10);
                    tbObat.setValueAt(KdDok2.getText(),tbObat.getSelectedRow(),11);
                    tbObat.setValueAt(TDokter2.getText(),tbObat.getSelectedRow(),12);
                    tbObat.setValueAt(TDiagnosis.getText(),tbObat.getSelectedRow(),13);
                    tbObat.setValueAt(TKelompok.getText(),tbObat.getSelectedRow(),14);
                    
                    tbObat.setValueAt(KdPetugas.getText(),tbObat.getSelectedRow(),18);
                    tbObat.setValueAt(NmPetugas.getText(),tbObat.getSelectedRow(),19);
                    
                    
                    for (i = 0; i < tbIdentifikasiMPP.getRowCount(); i++) {
                        if(tbIdentifikasiMPP.getValueAt(i,0).toString().equals("true")){
                            if(Sequel.menyimpantf2("mpp_evaluasi_masalah","?,?,?",3,new String[]{TNoRw.getText(),Valid.SetTgl(TglEvaluasi.getSelectedItem()+"")+" "+TglEvaluasi.getSelectedItem().toString().substring(11,19),tbIdentifikasiMPP.getValueAt(i,1).toString()})==true){
                                tabModeDetailMasalah.addRow(new Object[]{tbIdentifikasiMPP.getValueAt(i,1).toString(),tbIdentifikasiMPP.getValueAt(i,2).toString()});
                            }
                        }
                    }
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        } 
    }
}
