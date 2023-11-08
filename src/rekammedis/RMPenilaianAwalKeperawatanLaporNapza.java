/*
 * Kontribusi dari Abdul Wahid, RSUD Cipayung Jakarta Timur
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
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianAwalKeperawatanLaporNapza extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeMasalah,tabModeDetailMasalah,tabModeRencana,tabModeDetailRencana,tabModeEtiologi,tabModeDetailEtiologi,tabModeLuaran,tabModeDetailLuaran;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0,jml=0,index=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private boolean[] pilih; 
    private String[] kode,masalah;
    private String masalahkeperawatanigd="",finger=""; 
    private StringBuilder htmlContent;
    private File file;
    private FileWriter fileWriter;
    private String iyem;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalKeperawatanLaporNapza(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir","Tgl.Asuhan","Informasi","Status Perkawinan","Riwayat Pendidikan","Jenis Penyakit Non NAPZA",
            "Dirawat tahun","Lama Rawat","Riwayat Sakit Kronis","Nama Penyakit Kronis","Riwayat Terapi Medis","Nama Terapi Medis","Pernah Periksa HIV","Pernah Periksa Hepatitis B","Pernah Periksa Hepatitis C",
            "Status Pekerjaan","Pola Kerja","Keterampilan Teknis","Dukungan Hidup","Pemberi Dukungan","Dukungan Finansial","Dukungan Tempat Tinggal","Dukungan Makanan","Dukungan Perawatan",
            "Jenis NAPZA","30 Hari terakhir","Sepanjang Hidup","Cara Penggunaan","Jenis zat utama","Terapi Rehab","Nama Terapi Rehab","Pernah Mengalami OD","Waktu OD","Cara Penanggulanangan",
            "Rencana","NIP","Nama Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 81; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(50);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(90);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setPreferredWidth(120);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(200);
            }else if(i==11){
                column.setPreferredWidth(200);
            }else if(i==12){
                column.setPreferredWidth(200);
            }else if(i==13){
                column.setPreferredWidth(70);
            }else if(i==14){
                column.setPreferredWidth(50);
            }else if(i==15){
                column.setPreferredWidth(50);
            }else if(i==16){
                column.setPreferredWidth(50);
            }else if(i==17){
                column.setPreferredWidth(70);
            }else if(i==18){
                column.setPreferredWidth(110);
            }else if(i==19){
                column.setPreferredWidth(50);
            }else if(i==20){
                column.setPreferredWidth(160);
            }else if(i==21){
                column.setPreferredWidth(80);
            }else if(i==22){
                column.setPreferredWidth(67);
            }else if(i==23){
                column.setPreferredWidth(77);
            }else if(i==24){
                column.setPreferredWidth(77);
            }else if(i==25){
                column.setPreferredWidth(66);
            }else if(i==26){
                column.setPreferredWidth(107);
            }else if(i==27){
                column.setPreferredWidth(105);
            }else if(i==28){
                column.setPreferredWidth(90);
            }else if(i==29){
                column.setPreferredWidth(80);
            }else if(i==30){
                column.setPreferredWidth(65);
            }else if(i==31){
                column.setPreferredWidth(85);
            }else if(i==32){
                column.setPreferredWidth(80);
            }else if(i==33){
                column.setPreferredWidth(77);
            }else if(i==34){
                column.setPreferredWidth(65);
            }else if(i==35){
                column.setPreferredWidth(65);
            }else if(i==36){
                column.setPreferredWidth(80);
            }else if(i==37){
                column.setPreferredWidth(100);
            }else if(i==38){
                column.setPreferredWidth(147);
            }else if(i==39){
                column.setPreferredWidth(190);
            }else if(i==40){
                column.setPreferredWidth(90);
            }else if(i==41){
                column.setPreferredWidth(100);
            }else if(i==42){
                column.setPreferredWidth(220);
            }else if(i==43){
                column.setPreferredWidth(95);
            }else if(i==44){
                column.setPreferredWidth(85);
            }else if(i==45){
                column.setPreferredWidth(105);
            }else if(i==46){
                column.setPreferredWidth(100);
            }else if(i==47){
                column.setPreferredWidth(100);
            }else if(i==48){
                column.setPreferredWidth(120);
            }else if(i==49){
                column.setPreferredWidth(150);
            }else if(i==50){
                column.setPreferredWidth(97);
            }else if(i==51){
                column.setPreferredWidth(97);
            }else if(i==52){
                column.setPreferredWidth(110);
            }else if(i==53){
                column.setPreferredWidth(135);
            }else if(i==54){
                column.setPreferredWidth(155);
            }else if(i==55){
                column.setPreferredWidth(175);
            }else if(i==56){
                column.setPreferredWidth(65);
            }else if(i==57){
                column.setPreferredWidth(63);
            }else if(i==58){
                column.setPreferredWidth(97);
            }else if(i==59){
                column.setPreferredWidth(87);
            }else if(i==60){
                column.setPreferredWidth(85);
            }else if(i==61){
                column.setPreferredWidth(100);
            }else if(i==62){
                column.setPreferredWidth(90);
            }else if(i==63){
                column.setPreferredWidth(150);
            }else if(i==64){
                column.setPreferredWidth(80);
            }else if(i==65){
                column.setPreferredWidth(58);
            }else if(i==66){
                column.setPreferredWidth(65);
            }else if(i==67){
                column.setPreferredWidth(45);
            }else if(i==68){
                column.setPreferredWidth(85);
            }else if(i==69){
                column.setPreferredWidth(100);
            }else if(i==70){
                column.setPreferredWidth(85);
            }else if(i==71){
                column.setPreferredWidth(60);
            }else if(i==72){
                column.setPreferredWidth(85);
            }else if(i==73){
                column.setPreferredWidth(85);
            }else if(i==74){
                column.setPreferredWidth(85);
            }else if(i==75){
                column.setPreferredWidth(203);
            }else if(i==76){
                column.setPreferredWidth(70);
            }else if(i==77){
                column.setPreferredWidth(90);
            }else if(i==78){
                column.setPreferredWidth(210);
            }else if(i==79){
                column.setPreferredWidth(75);
            }else if(i==80){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeMasalah=new DefaultTableModel(null,new Object[]{
                "P","KODE","MASALAH KEPERAWATAN"
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
        tbMasalahKeperawatan.setModel(tabModeMasalah);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahKeperawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasalahKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbMasalahKeperawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(350);
            }
        }
        tbMasalahKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailMasalah=new DefaultTableModel(null,new Object[]{
                "Kode","Masalah Keperawatan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbMasalahDetailMasalah.setModel(tabModeDetailMasalah);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
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
        
        tabModeRencana=new DefaultTableModel(null,new Object[]{
                "P","KODE","RENCANA KEPERAWATAN"
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
        tbRencanaKeperawatan.setModel(tabModeRencana);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaKeperawatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRencanaKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaKeperawatan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(350);
            }
        }
        tbRencanaKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailRencana=new DefaultTableModel(null,new Object[]{
                "Kode","Rencana Keperawatan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRencanaDetail.setModel(tabModeDetailRencana);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaDetail.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRencanaDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbRencanaDetail.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(420);
            }
        }
        tbRencanaDetail.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeEtiologi=new DefaultTableModel(null,new Object[]{
                "P","KODE","ETIOLOGI"
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
        
        tbRencanaEtiologi.setModel(tabModeEtiologi);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaEtiologi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRencanaEtiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaEtiologi.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(350);
            }
        }
        tbRencanaEtiologi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailEtiologi=new DefaultTableModel(null,new Object[]{
                "Kode","Etiologi"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbEtiologiDetail.setModel(tabModeDetailEtiologi);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbEtiologiDetail.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbEtiologiDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbEtiologiDetail.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(420);
            }
        }
        tbEtiologiDetail.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeLuaran=new DefaultTableModel(null,new Object[]{
                "P","KODE","LUARAN"
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
        
        tbRencanaLuaran.setModel(tabModeLuaran);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaLuaran.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRencanaLuaran.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaLuaran.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(350);
            }
        }
        tbRencanaLuaran.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailLuaran=new DefaultTableModel(null,new Object[]{
                "Kode","Luaran"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbLuaranDetail.setModel(tabModeDetailLuaran);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbLuaranDetail.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLuaranDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbLuaranDetail.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(420);
            }
        }
        tbLuaranDetail.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        JenisPenyakit.setDocument(new batasInput((int)150).getKata(JenisPenyakit));
//        SkorSakitKronis.setDocument(new batasInput((byte)5).getKata(SkorSakitKronis));
        TahunRawat.setDocument(new batasInput((byte)2).getKata(TahunRawat));
//        TerampilTeknis.setDocument(new batasInput((int)50).getKata(TerampilTeknis));
//        Sebutkan.setDocument(new batasInput((int)50).getKata(Sebutkan));
//        KetTinggal.setDocument(new batasInput((int)50).getKata(KetTinggal));
//        KetBudaya.setDocument(new batasInput((int)50).getKata(KetBudaya));
//        KetPendidikanPJ.setDocument(new batasInput((int)50).getKata(KetPendidikanPJ));
//        KetEdukasi.setDocument(new batasInput((int)50).getKata(KetEdukasi));
//        KetAlatBantu.setDocument(new batasInput((int)50).getKata(KetAlatBantu));
//        KetProvokes.setDocument(new batasInput((int)40).getKata(KetProvokes));
//        KetQuality.setDocument(new batasInput((int)50).getKata(KetQuality));
//        Lokasi.setDocument(new batasInput((int)50).getKata(Lokasi));
//        Durasi.setDocument(new batasInput((int)25).getKata(Durasi));
//        KetNyeri.setDocument(new batasInput((int)40).getKata(KetNyeri));
//        KetDokter.setDocument(new batasInput((byte)15).getKata(KetDokter));
//        KetLapor.setDocument(new batasInput((int)15).getKata(KetLapor));
        Rencana.setDocument(new batasInput((int)200).getKata(Rencana));
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
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel57 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jLabel94 = new widget.Label();
        jLabel56 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel78 = new widget.Label();
        StatKawin = new widget.ComboBox();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel98 = new widget.Label();
        jLabel108 = new widget.Label();
        TahunRawat = new widget.TextBox();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        StatKerja = new widget.ComboBox();
        jLabel119 = new widget.Label();
        PolaKerja = new widget.ComboBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        jLabel124 = new widget.Label();
        ZatUtama = new widget.TextBox();
        jLabel39 = new widget.Label();
        Informasi = new widget.ComboBox();
        Scroll8 = new widget.ScrollPane();
        tbMasalahKeperawatan = new widget.Table();
        BtnTambahMasalah = new widget.Button();
        BtnAllMasalah = new widget.Button();
        BtnCariMasalah = new widget.Button();
        TCariMasalah = new widget.TextBox();
        label12 = new widget.Label();
        TabRencanaKeperawatan = new javax.swing.JTabbedPane();
        panelBiasa3 = new widget.PanelBiasa();
        Scroll11 = new widget.ScrollPane();
        tbRencanaEtiologi = new widget.Table();
        panelBiasa4 = new widget.PanelBiasa();
        Scroll13 = new widget.ScrollPane();
        tbRencanaLuaran = new widget.Table();
        panelBiasa1 = new widget.PanelBiasa();
        Scroll9 = new widget.ScrollPane();
        tbRencanaKeperawatan = new widget.Table();
        scrollPane5 = new widget.ScrollPane();
        Rencana = new widget.TextArea();
        label13 = new widget.Label();
        TCariRencana = new widget.TextBox();
        BtnCariRencana = new widget.Button();
        BtnAllRencana = new widget.Button();
        BtnTambahRencana = new widget.Button();
        jLabel53 = new widget.Label();
        jLabel62 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel79 = new widget.Label();
        PendidikanTerakhir = new widget.ComboBox();
        KetSakitKronis = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        JenisPenyakit = new widget.TextArea();
        jLabel30 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        LamaRawat = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel90 = new widget.Label();
        SakitKronis = new widget.ComboBox();
        jLabel36 = new widget.Label();
        jLabel91 = new widget.Label();
        TerapiMedis = new widget.ComboBox();
        jLabel37 = new widget.Label();
        KetTerapiMedis = new widget.TextBox();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        TerapiMedis1 = new widget.ComboBox();
        StatSehatHIV = new widget.ComboBox();
        StatSehatHepB = new widget.ComboBox();
        jLabel111 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        JenisPenyakit1 = new widget.TextArea();
        jLabel130 = new widget.Label();
        Finansial = new widget.ComboBox();
        jLabel131 = new widget.Label();
        PernahRehab = new widget.ComboBox();
        jLabel132 = new widget.Label();
        Makan = new widget.ComboBox();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        Pengobotan = new widget.ComboBox();
        jLabel61 = new widget.Label();
        jLabel69 = new widget.Label();
        DukunganHidup1 = new widget.ComboBox();
        jLabel73 = new widget.Label();
        JenisNarko = new widget.ComboBox();
        SebulanNarko = new widget.ComboBox();
        jLabel92 = new widget.Label();
        SepanjangNarko = new widget.ComboBox();
        CaraNarko = new widget.ComboBox();
        KetDukunganHidup = new widget.TextBox();
        TempatTinggal = new widget.ComboBox();
        jLabel74 = new widget.Label();
        KetRehab = new widget.TextBox();
        jLabel75 = new widget.Label();
        OD = new widget.ComboBox();
        jLabel76 = new widget.Label();
        WaktuOD = new widget.TextBox();
        jLabel77 = new widget.Label();
        OD1 = new widget.ComboBox();
        jLabel109 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel96 = new widget.Label();
        jLabel99 = new widget.Label();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        JmlBebas = new widget.TextBox();
        JmlVandal = new widget.TextBox();
        JmlNarkoba = new widget.TextBox();
        JmlPalsu = new widget.TextBox();
        JmlSenjata = new widget.TextBox();
        JmlPencurian = new widget.TextBox();
        JmlRampok = new widget.TextBox();
        JmlPenyerangan = new widget.TextBox();
        JmlBakar = new widget.TextBox();
        JmlPerkosa = new widget.TextBox();
        JmlPembunuhan = new widget.TextBox();
        JmlPelacuran = new widget.TextBox();
        JmlLain = new widget.TextBox();
        textBox14 = new widget.TextBox();
        JmlMelecehkan = new widget.TextBox();
        jLabel97 = new widget.Label();
        jLabel103 = new widget.Label();
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
        BtnPrint1 = new widget.Button();
        FormMasalahRencana = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbMasalahDetailMasalah = new widget.Table();
        Scroll12 = new widget.ScrollPane();
        tbEtiologiDetail = new widget.Table();
        Scroll14 = new widget.ScrollPane();
        tbLuaranDetail = new widget.Table();
        Scroll10 = new widget.ScrollPane();
        tbRencanaDetail = new widget.Table();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Wajib Lapor & Rehab Medis NAPZA ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1500));
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
        TPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPasienActionPerformed(evt);
            }
        });
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
        label14.setBounds(0, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(74, 40, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(176, 40, 180, 23);

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
        BtnDokter.setBounds(358, 40, 28, 23);

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
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(395, 40, 57, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel57.setText("Cara Pakai");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(670, 680, 70, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2023 10:00:55" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(456, 40, 130, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("III. STATUS PEKERJAAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 450, 360, 23);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("IV. STATUS PENGGUNAAN NARKOTIKA");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(10, 650, 230, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 650, 880, 1);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 1110, 880, 1);

        jLabel78.setText("Status Perkawinan :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(30, 100, 106, 23);

        StatKawin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum menikah", "Menikah", "Duda / Janda" }));
        StatKawin.setName("StatKawin"); // NOI18N
        StatKawin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatKawinKeyPressed(evt);
            }
        });
        FormInput.add(StatKawin);
        StatKawin.setBounds(140, 100, 180, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 150, 880, 0);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("I. INFORMASI DEMOGRAFIS");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(10, 70, 180, 23);

        jLabel108.setText("Pernah diperiksakan");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(160, 320, 100, 23);

        TahunRawat.setHighlighter(null);
        TahunRawat.setName("TahunRawat"); // NOI18N
        TahunRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TahunRawatKeyPressed(evt);
            }
        });
        FormInput.add(TahunRawat);
        TahunRawat.setBounds(510, 180, 50, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 450, 880, 1);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("II. STATUS MEDIS");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(10, 140, 180, 23);

        jLabel117.setText("Status Pekerjaan :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 470, 148, 23);

        StatKerja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak bekerja", "Bekerja", "Mahasiswa/Pelajar", "Ibu Rumah Tangga" }));
        StatKerja.setName("StatKerja"); // NOI18N
        StatKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatKerjaKeyPressed(evt);
            }
        });
        FormInput.add(StatKerja);
        StatKerja.setBounds(160, 470, 160, 23);

        jLabel119.setText("Bila bekerja, pola pekerjaan :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(340, 470, 140, 23);

        PolaKerja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Purna waktu", "Paruh waktu", "Tidak tentu" }));
        PolaKerja.setName("PolaKerja"); // NOI18N
        PolaKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaKerjaKeyPressed(evt);
            }
        });
        FormInput.add(PolaKerja);
        PolaKerja.setBounds(490, 470, 100, 23);

        jLabel122.setText("Dalam bentuk apa?");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(130, 590, 130, 23);

        jLabel123.setText("Keterampilan teknis yang dimiliki");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(40, 500, 170, 23);

        jLabel124.setText("Bila Ya, siapa?");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(380, 560, 70, 23);

        ZatUtama.setHighlighter(null);
        ZatUtama.setName("ZatUtama"); // NOI18N
        FormInput.add(ZatUtama);
        ZatUtama.setBounds(240, 710, 240, 23);

        jLabel39.setText("Informasi didapat dari :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(592, 40, 130, 23);

        Informasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Informasi.setName("Informasi"); // NOI18N
        Informasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InformasiKeyPressed(evt);
            }
        });
        FormInput.add(Informasi);
        Informasi.setBounds(726, 40, 128, 23);

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbMasalahKeperawatan.setName("tbMasalahKeperawatan"); // NOI18N
        tbMasalahKeperawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahKeperawatanMouseClicked(evt);
            }
        });
        tbMasalahKeperawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbMasalahKeperawatan);

        FormInput.add(Scroll8);
        Scroll8.setBounds(10, 1120, 400, 143);

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
        BtnTambahMasalah.setBounds(360, 1270, 28, 23);

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
        BtnAllMasalah.setBounds(330, 1270, 28, 23);

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
        BtnCariMasalah.setBounds(300, 1270, 28, 23);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(80, 1270, 215, 23);

        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(10, 1270, 60, 23);

        TabRencanaKeperawatan.setBackground(new java.awt.Color(255, 255, 254));
        TabRencanaKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabRencanaKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        TabRencanaKeperawatan.setName("TabRencanaKeperawatan"); // NOI18N

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(new java.awt.BorderLayout());

        Scroll11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbRencanaEtiologi.setName("tbRencanaEtiologi"); // NOI18N
        Scroll11.setViewportView(tbRencanaEtiologi);

        panelBiasa3.add(Scroll11, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Etiologi", panelBiasa3);

        panelBiasa4.setName("panelBiasa4"); // NOI18N
        panelBiasa4.setLayout(new java.awt.BorderLayout());

        Scroll13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        tbRencanaLuaran.setName("tbRencanaLuaran"); // NOI18N
        Scroll13.setViewportView(tbRencanaLuaran);

        panelBiasa4.add(Scroll13, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Luaran", panelBiasa4);

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setLayout(new java.awt.BorderLayout());

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbRencanaKeperawatan.setName("tbRencanaKeperawatan"); // NOI18N
        Scroll9.setViewportView(tbRencanaKeperawatan);

        panelBiasa1.add(Scroll9, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan", panelBiasa1);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Rencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Rencana.setColumns(20);
        Rencana.setRows(5);
        Rencana.setName("Rencana"); // NOI18N
        Rencana.setOpaque(true);
        Rencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Rencana);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan Lainnya", scrollPane5);

        FormInput.add(TabRencanaKeperawatan);
        TabRencanaKeperawatan.setBounds(430, 1120, 420, 143);

        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(440, 1270, 60, 23);

        TCariRencana.setToolTipText("Alt+C");
        TCariRencana.setName("TCariRencana"); // NOI18N
        TCariRencana.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(TCariRencana);
        TCariRencana.setBounds(500, 1270, 235, 23);

        BtnCariRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariRencana.setMnemonic('1');
        BtnCariRencana.setToolTipText("Alt+1");
        BtnCariRencana.setName("BtnCariRencana"); // NOI18N
        BtnCariRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariRencanaActionPerformed(evt);
            }
        });
        BtnCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariRencana);
        BtnCariRencana.setBounds(740, 1270, 28, 23);

        BtnAllRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllRencana.setMnemonic('2');
        BtnAllRencana.setToolTipText("2Alt+2");
        BtnAllRencana.setName("BtnAllRencana"); // NOI18N
        BtnAllRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllRencanaActionPerformed(evt);
            }
        });
        BtnAllRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllRencana);
        BtnAllRencana.setBounds(770, 1270, 28, 23);

        BtnTambahRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahRencana.setMnemonic('3');
        BtnTambahRencana.setToolTipText("Alt+3");
        BtnTambahRencana.setName("BtnTambahRencana"); // NOI18N
        BtnTambahRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahRencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahRencana);
        BtnTambahRencana.setBounds(800, 1270, 28, 23);

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("V. STATUS LEGAL");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(10, 830, 380, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Pembakaran rumah");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(490, 910, 180, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 817, 880, 3);

        jLabel79.setText("Pendidikan terakhir :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(440, 100, 106, 23);

        PendidikanTerakhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak sekolah / Tidak tamat SD", "Tamat SD", "Tamat SMP", "Tamat SMA", "Tamat Akademi", "Tamat PT" }));
        PendidikanTerakhir.setName("PendidikanTerakhir"); // NOI18N
        PendidikanTerakhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PendidikanTerakhirKeyPressed(evt);
            }
        });
        FormInput.add(PendidikanTerakhir);
        PendidikanTerakhir.setBounds(550, 100, 180, 23);

        KetSakitKronis.setHighlighter(null);
        KetSakitKronis.setName("KetSakitKronis"); // NOI18N
        KetSakitKronis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSakitKronisKeyPressed(evt);
            }
        });
        FormInput.add(KetSakitKronis);
        KetSakitKronis.setBounds(370, 250, 240, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        JenisPenyakit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JenisPenyakit.setColumns(20);
        JenisPenyakit.setRows(5);
        JenisPenyakit.setName("JenisPenyakit"); // NOI18N
        JenisPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPenyakitKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(JenisPenyakit);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(220, 500, 260, 53);

        jLabel30.setText("Riwayat rawat inap yang tidak terkait masalah narkotika");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 160, 310, 20);

        jLabel32.setText("Jika Ya, Nama Penyakit :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(240, 250, 120, 20);

        jLabel33.setText("Lama Perawatan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(410, 210, 90, 20);

        LamaRawat.setHighlighter(null);
        LamaRawat.setName("LamaRawat"); // NOI18N
        LamaRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaRawatKeyPressed(evt);
            }
        });
        FormInput.add(LamaRawat);
        LamaRawat.setBounds(510, 210, 120, 23);

        jLabel35.setText("Dirawat Tahun :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(410, 180, 90, 20);

        jLabel90.setText("Riwayat Penyakit Kronis :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(40, 250, 130, 23);

        SakitKronis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        SakitKronis.setName("SakitKronis"); // NOI18N
        SakitKronis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SakitKronisActionPerformed(evt);
            }
        });
        SakitKronis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SakitKronisKeyPressed(evt);
            }
        });
        FormInput.add(SakitKronis);
        SakitKronis.setBounds(170, 250, 60, 23);

        jLabel36.setText("Jenis Penyakit :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 180, 120, 20);

        jLabel91.setText("Sedang dalam terapi medis :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(30, 280, 140, 23);

        TerapiMedis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pernah", "Tidak Pernah" }));
        TerapiMedis.setName("TerapiMedis"); // NOI18N
        TerapiMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerapiMedisActionPerformed(evt);
            }
        });
        TerapiMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerapiMedisKeyPressed(evt);
            }
        });
        FormInput.add(TerapiMedis);
        TerapiMedis.setBounds(170, 410, 80, 23);

        jLabel37.setText("Jika Ya, Nama terapi :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(240, 280, 120, 20);

        KetTerapiMedis.setHighlighter(null);
        KetTerapiMedis.setName("KetTerapiMedis"); // NOI18N
        KetTerapiMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetTerapiMedisKeyPressed(evt);
            }
        });
        FormInput.add(KetTerapiMedis);
        KetTerapiMedis.setBounds(370, 280, 240, 23);

        jLabel128.setText("HIV");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(60, 350, 90, 23);

        jLabel129.setText("Hepatitis B");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(60, 380, 90, 23);

        TerapiMedis1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TerapiMedis1.setName("TerapiMedis1"); // NOI18N
        TerapiMedis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerapiMedis1ActionPerformed(evt);
            }
        });
        TerapiMedis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerapiMedis1KeyPressed(evt);
            }
        });
        FormInput.add(TerapiMedis1);
        TerapiMedis1.setBounds(170, 280, 60, 23);

        StatSehatHIV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pernah", "Tidak Pernah" }));
        StatSehatHIV.setName("StatSehatHIV"); // NOI18N
        StatSehatHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatSehatHIVActionPerformed(evt);
            }
        });
        StatSehatHIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatSehatHIVKeyPressed(evt);
            }
        });
        FormInput.add(StatSehatHIV);
        StatSehatHIV.setBounds(170, 350, 80, 23);

        StatSehatHepB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pernah", "Tidak Pernah" }));
        StatSehatHepB.setName("StatSehatHepB"); // NOI18N
        StatSehatHepB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatSehatHepBActionPerformed(evt);
            }
        });
        StatSehatHepB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatSehatHepBKeyPressed(evt);
            }
        });
        FormInput.add(StatSehatHepB);
        StatSehatHepB.setBounds(170, 380, 80, 23);

        jLabel111.setText("Hepatitis C");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(60, 410, 90, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        JenisPenyakit1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JenisPenyakit1.setColumns(20);
        JenisPenyakit1.setRows(5);
        JenisPenyakit1.setName("JenisPenyakit1"); // NOI18N
        JenisPenyakit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPenyakit1KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(JenisPenyakit1);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(130, 180, 260, 53);

        jLabel130.setText("Finansial : ");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(300, 590, 60, 23);

        Finansial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Finansial.setName("Finansial"); // NOI18N
        Finansial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FinansialKeyPressed(evt);
            }
        });
        FormInput.add(Finansial);
        Finansial.setBounds(360, 590, 60, 23);

        jLabel131.setText("Tempat tinggal : ");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(270, 620, 90, 23);

        PernahRehab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        PernahRehab.setName("PernahRehab"); // NOI18N
        PernahRehab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PernahRehabActionPerformed(evt);
            }
        });
        PernahRehab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernahRehabKeyPressed(evt);
            }
        });
        FormInput.add(PernahRehab);
        PernahRehab.setBounds(240, 740, 60, 23);

        jLabel132.setText("Adakah yang memberi dukungan hidup bagi anda?");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(20, 560, 276, 23);

        Makan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Makan.setName("Makan"); // NOI18N
        Makan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MakanKeyPressed(evt);
            }
        });
        FormInput.add(Makan);
        Makan.setBounds(570, 590, 60, 23);

        jLabel133.setText("Makan : ");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(510, 590, 60, 23);

        jLabel134.setText("Pengobatan/perawatan : ");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(430, 620, 140, 23);

        Pengobotan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        Pengobotan.setName("Pengobotan"); // NOI18N
        Pengobotan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PengobotanKeyPressed(evt);
            }
        });
        FormInput.add(Pengobotan);
        Pengobotan.setBounds(570, 620, 60, 23);

        jLabel61.setText("30 Hari Terakhir");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(320, 680, 80, 23);

        jLabel69.setText("Jenis NAPZA");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 680, 130, 23);

        DukunganHidup1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        DukunganHidup1.setName("DukunganHidup1"); // NOI18N
        DukunganHidup1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DukunganHidup1ItemStateChanged(evt);
            }
        });
        DukunganHidup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DukunganHidup1ActionPerformed(evt);
            }
        });
        DukunganHidup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DukunganHidup1KeyPressed(evt);
            }
        });
        FormInput.add(DukunganHidup1);
        DukunganHidup1.setBounds(300, 560, 60, 23);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("Jenis zat utama yang disalahgunakan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(50, 710, 186, 23);

        JenisNarko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alkohol", "Heroin", "Meladon/Buprenorfin", "Oplat lain/Analgesik", "Barbiturat", "Sedatif/Hipnotik", "Kokain", "Amfetamin", "Kanabis", "Halusinogen", "Inhalen", "Lebih dari 1 zat /hari (termasuk alkohol)" }));
        JenisNarko.setName("JenisNarko"); // NOI18N
        JenisNarko.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisNarkoItemStateChanged(evt);
            }
        });
        JenisNarko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JenisNarkoActionPerformed(evt);
            }
        });
        JenisNarko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisNarkoKeyPressed(evt);
            }
        });
        FormInput.add(JenisNarko);
        JenisNarko.setBounds(140, 680, 180, 23);

        SebulanNarko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        SebulanNarko.setName("SebulanNarko"); // NOI18N
        SebulanNarko.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SebulanNarkoItemStateChanged(evt);
            }
        });
        SebulanNarko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SebulanNarkoActionPerformed(evt);
            }
        });
        SebulanNarko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SebulanNarkoKeyPressed(evt);
            }
        });
        FormInput.add(SebulanNarko);
        SebulanNarko.setBounds(410, 680, 72, 23);

        jLabel92.setText("Sepanjang Hidup");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(490, 680, 90, 23);

        SepanjangNarko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        SepanjangNarko.setName("SepanjangNarko"); // NOI18N
        SepanjangNarko.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SepanjangNarkoItemStateChanged(evt);
            }
        });
        SepanjangNarko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SepanjangNarkoActionPerformed(evt);
            }
        });
        SepanjangNarko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SepanjangNarkoKeyPressed(evt);
            }
        });
        FormInput.add(SepanjangNarko);
        SepanjangNarko.setBounds(590, 680, 72, 23);

        CaraNarko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Oral", "Nasal/siblingual/suppositoria", "Merokok", "Injeksi non-IV", "IV" }));
        CaraNarko.setName("CaraNarko"); // NOI18N
        CaraNarko.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CaraNarkoItemStateChanged(evt);
            }
        });
        CaraNarko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaraNarkoActionPerformed(evt);
            }
        });
        CaraNarko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraNarkoKeyPressed(evt);
            }
        });
        FormInput.add(CaraNarko);
        CaraNarko.setBounds(750, 680, 70, 23);

        KetDukunganHidup.setHighlighter(null);
        KetDukunganHidup.setName("KetDukunganHidup"); // NOI18N
        FormInput.add(KetDukunganHidup);
        KetDukunganHidup.setBounds(460, 560, 160, 23);

        TempatTinggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TempatTinggal.setName("TempatTinggal"); // NOI18N
        TempatTinggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TempatTinggalKeyPressed(evt);
            }
        });
        FormInput.add(TempatTinggal);
        TempatTinggal.setBounds(360, 620, 60, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Terapi rehab yang dijalani :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(310, 740, 140, 23);

        KetRehab.setHighlighter(null);
        KetRehab.setName("KetRehab"); // NOI18N
        FormInput.add(KetRehab);
        KetRehab.setBounds(450, 740, 180, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("Pernah mengalami overdosis :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(50, 770, 180, 23);

        OD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        OD.setName("OD"); // NOI18N
        OD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ODActionPerformed(evt);
            }
        });
        OD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ODKeyPressed(evt);
            }
        });
        FormInput.add(OD);
        OD.setBounds(240, 770, 60, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Bila ya, kapan waktu OD?");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(310, 770, 140, 23);

        WaktuOD.setHighlighter(null);
        WaktuOD.setName("WaktuOD"); // NOI18N
        FormInput.add(WaktuOD);
        WaktuOD.setBounds(450, 770, 180, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("Cara penanggulangan OD");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(640, 770, 130, 23);

        OD1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Perawatan di RS", "Perawatan di Puskesmas", "Sendiri" }));
        OD1.setName("OD1"); // NOI18N
        OD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OD1ActionPerformed(evt);
            }
        });
        OD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OD1KeyPressed(evt);
            }
        });
        FormInput.add(OD1);
        OD1.setBounds(770, 770, 120, 23);

        jLabel109.setText("Status Kesehatan :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 310, 150, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("Pernah menjalani terapi rehablilitasi :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(50, 740, 180, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("Penyerangan");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(490, 880, 170, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("Pembunuhan");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(490, 970, 180, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Perkosaan");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(490, 940, 170, 23);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("Pelacuran");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(490, 1000, 170, 23);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Lain-lain");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(490, 1060, 130, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("Mencuri di toko / vandalisme");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(40, 880, 170, 23);

        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("Bebas bersyarat / masa percobaan");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(40, 910, 180, 23);

        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("Masalah narkoba");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(40, 940, 170, 23);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("Pemalsuan");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(40, 970, 180, 23);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Penyerangan bersenjata");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(40, 1000, 170, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Pembobolan dan pencurian");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(40, 1030, 180, 23);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("Perampokan");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(40, 1060, 170, 23);

        JmlBebas.setName("JmlBebas"); // NOI18N
        FormInput.add(JmlBebas);
        JmlBebas.setBounds(240, 910, 65, 24);

        JmlVandal.setName("JmlVandal"); // NOI18N
        JmlVandal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlVandalActionPerformed(evt);
            }
        });
        FormInput.add(JmlVandal);
        JmlVandal.setBounds(240, 880, 64, 24);

        JmlNarkoba.setName("JmlNarkoba"); // NOI18N
        JmlNarkoba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlNarkobaActionPerformed(evt);
            }
        });
        FormInput.add(JmlNarkoba);
        JmlNarkoba.setBounds(240, 940, 64, 24);

        JmlPalsu.setName("JmlPalsu"); // NOI18N
        FormInput.add(JmlPalsu);
        JmlPalsu.setBounds(240, 970, 65, 24);

        JmlSenjata.setName("JmlSenjata"); // NOI18N
        JmlSenjata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlSenjataActionPerformed(evt);
            }
        });
        FormInput.add(JmlSenjata);
        JmlSenjata.setBounds(240, 1000, 64, 24);

        JmlPencurian.setName("JmlPencurian"); // NOI18N
        FormInput.add(JmlPencurian);
        JmlPencurian.setBounds(240, 1030, 65, 24);

        JmlRampok.setName("JmlRampok"); // NOI18N
        JmlRampok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlRampokActionPerformed(evt);
            }
        });
        FormInput.add(JmlRampok);
        JmlRampok.setBounds(240, 1060, 64, 24);

        JmlPenyerangan.setName("JmlPenyerangan"); // NOI18N
        JmlPenyerangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlPenyeranganActionPerformed(evt);
            }
        });
        FormInput.add(JmlPenyerangan);
        JmlPenyerangan.setBounds(630, 880, 64, 24);

        JmlBakar.setName("JmlBakar"); // NOI18N
        FormInput.add(JmlBakar);
        JmlBakar.setBounds(630, 910, 65, 24);

        JmlPerkosa.setName("JmlPerkosa"); // NOI18N
        JmlPerkosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlPerkosaActionPerformed(evt);
            }
        });
        FormInput.add(JmlPerkosa);
        JmlPerkosa.setBounds(630, 940, 64, 24);

        JmlPembunuhan.setName("JmlPembunuhan"); // NOI18N
        FormInput.add(JmlPembunuhan);
        JmlPembunuhan.setBounds(630, 970, 65, 24);

        JmlPelacuran.setName("JmlPelacuran"); // NOI18N
        JmlPelacuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlPelacuranActionPerformed(evt);
            }
        });
        FormInput.add(JmlPelacuran);
        JmlPelacuran.setBounds(630, 1000, 64, 24);

        JmlLain.setName("JmlLain"); // NOI18N
        JmlLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlLainActionPerformed(evt);
            }
        });
        FormInput.add(JmlLain);
        JmlLain.setBounds(770, 1060, 65, 24);

        textBox14.setName("textBox14"); // NOI18N
        textBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox14ActionPerformed(evt);
            }
        });
        FormInput.add(textBox14);
        textBox14.setBounds(630, 1060, 130, 24);

        JmlMelecehkan.setName("JmlMelecehkan"); // NOI18N
        FormInput.add(JmlMelecehkan);
        JmlMelecehkan.setBounds(630, 1030, 65, 24);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("Berapa kali dalam hidup ditangkap dan dituntut dengan hal berikut :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(40, 850, 350, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("Melecehkan pengadilan");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(490, 1030, 180, 23);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2023" }));
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

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPrint1);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.NORTH);

        FormMasalahRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormMasalahRencana.setName("FormMasalahRencana"); // NOI18N
        FormMasalahRencana.setLayout(new java.awt.GridLayout(3, 0, 1, 1));

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbMasalahDetailMasalah.setName("tbMasalahDetailMasalah"); // NOI18N
        Scroll7.setViewportView(tbMasalahDetailMasalah);

        FormMasalahRencana.add(Scroll7);

        Scroll12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbEtiologiDetail.setName("tbEtiologiDetail"); // NOI18N
        Scroll12.setViewportView(tbEtiologiDetail);

        FormMasalahRencana.add(Scroll12);

        Scroll14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        tbLuaranDetail.setName("tbLuaranDetail"); // NOI18N
        Scroll14.setViewportView(tbLuaranDetail);

        FormMasalahRencana.add(Scroll14);

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbRencanaDetail.setName("tbRencanaDetail"); // NOI18N
        Scroll10.setViewportView(tbRencanaDetail);

        FormMasalahRencana.add(Scroll10);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Rencana Keperawatan Lainnya :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        DetailRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DetailRencana.setColumns(20);
        DetailRencana.setRows(5);
        DetailRencana.setName("DetailRencana"); // NOI18N
        scrollPane6.setViewportView(DetailRencana);

        FormMasalahRencana.add(scrollPane6);

        PanelAccor.add(FormMasalahRencana, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String riwayatk = "";
        String riwayati ="";
        
        
        
        
//        //        Nilai Riwayat Kelahiran
//        if (spontan.isSelected()) {
//            riwayatk = "Spontan";
//        } else if (tindakan.isSelected()) {
//            riwayatk = "Tindakan,Sebutkan";
//        }
//        
//          //        Nilai Riwayat Imunisasi
//        if (lengkap.isSelected()) {
//            riwayati = "Lengkap";
//        } else if (tdklengkap.isSelected()) {
//            riwayati = "Tidak Lengkap";
//        }
        
        
        
        
        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(JenisPenyakit.getText().trim().equals("")){
            Valid.textKosong(JenisPenyakit,"Riwayat Penyakit Sekarang");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Petugas");
        }else{
            if(Sequel.menyimpantf("penilaian_awal_keperawatan_igd","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",76,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Informasi.getSelectedItem().toString(),JenisPenyakit.getText(),
//                    Gravida.getText(),Para.getText(),Abortus.getText(),HPHT.getText(),Tekanan.getSelectedItem().toString(),Pupil.getSelectedItem().toString(),Neurosensorik.getSelectedItem().toString(),Integumen.getSelectedItem().toString(),
//                    Turgor.getSelectedItem().toString(),Edema.getSelectedItem().toString(),Mukosa.getSelectedItem().toString(),Perdarahan.getSelectedItem().toString(),SkorPendidikan.getText(),
//                    WarnaPerdarahan.getText(),Intoksikasi.getSelectedItem().toString(),TahunRawat.getText(),XBAB.getText(),KBAB.getText(),WBAB.getText(),BAK.getText(),XBAK.getText(),WBAK.getText(),LBAK.getText(),Psikologis.getSelectedItem().toString(),
                    PolaKerja.getSelectedItem().toString(),
//                    DukunganHidup.getSelectedItem().toString(),
//                    Aktifitas.getSelectedItem().toString(),AlatBantu.getSelectedItem().toString(),KetAlatBantu.getText(),bb.getText(),tb.getText(),
                    
//                    riwayatk,ket_riwayatk.getText(),riwayati,ket_riwayati.getText(),ket_riwayati1.getText(),Nyeri.getSelectedItem().toString(),Provokes.getSelectedItem().toString(),KetProvokes.getText(),Quality.getSelectedItem().toString(),
                    
//                    KetQuality.getText(),Lokasi.getText(),Menyebar.getSelectedItem().toString(),SkalaNyeri.getSelectedItem().toString(),Durasi.getText(),NyeriHilang.getSelectedItem().toString(),KetNyeri.getText(),PadaDokter.getSelectedItem().toString(),
//                    KetDokter.getText(),ATS.getSelectedItem().toString(),BJM.getSelectedItem().toString(),MSA.getSelectedItem().toString(),Hasil.getSelectedItem().toString(),Lapor.getSelectedItem().toString(),KetLapor.getText(),Rencana.getText(),KdPetugas.getText()
                })==true){
                    for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                        if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("penilaian_awal_keperawatan_igd_masalah","?,?",2,new String[]{TNoRw.getText(),tbMasalahKeperawatan.getValueAt(i,1).toString()});
                        }
                    }
                    
                    for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                        if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_rencana_igd","?,?",2,new String[]{TNoRw.getText(),tbRencanaKeperawatan.getValueAt(i,1).toString()});
                        }
                    }
                    for (i = 0; i < tbRencanaEtiologi.getRowCount(); i++) {
                        if(tbRencanaEtiologi.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_etiologi_igd","?,?",2,new String[]{TNoRw.getText(),tbRencanaEtiologi.getValueAt(i,1).toString()});
                        }
                    }
                    for (i = 0; i < tbRencanaLuaran.getRowCount(); i++) {
                        if(tbRencanaLuaran.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_luaran_igd","?,?",2,new String[]{TNoRw.getText(),tbRencanaLuaran.getValueAt(i,1).toString()});
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
            Valid.pindah(evt,Rencana,BtnBatal);
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
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString())){
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
        }else if(JenisPenyakit.getText().trim().equals("")){
            Valid.textKosong(JenisPenyakit,"Riwayat Penyakit Sekarang");
//        }else if(RPD.getText().trim().equals("")){
//            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
//        }else if(RPO.getText().trim().equals("")){
//            Valid.textKosong(RPO,"Riwayat Penggunaan Obat");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString())){
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
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_igd.tanggal,penilaian_awal_keperawatan_igd.informasi,"+
                            "penilaian_awal_keperawatan_igd.keluhan_utama,penilaian_awal_keperawatan_igd.rpd,penilaian_awal_keperawatan_igd.rpo,penilaian_awal_keperawatan_igd.status_kehamilan,penilaian_awal_keperawatan_igd.gravida,penilaian_awal_keperawatan_igd.para,"+
                            "penilaian_awal_keperawatan_igd.abortus,penilaian_awal_keperawatan_igd.hpht,penilaian_awal_keperawatan_igd.tekanan,penilaian_awal_keperawatan_igd.pupil,penilaian_awal_keperawatan_igd.neurosensorik,penilaian_awal_keperawatan_igd.integumen,penilaian_awal_keperawatan_igd.turgor,"+ 
                            "penilaian_awal_keperawatan_igd.edema,penilaian_awal_keperawatan_igd.mukosa,penilaian_awal_keperawatan_igd.perdarahan,penilaian_awal_keperawatan_igd.jumlah_perdarahan,penilaian_awal_keperawatan_igd.warna_perdarahan,penilaian_awal_keperawatan_igd.intoksikasi,"+
                            "penilaian_awal_keperawatan_igd.bab,penilaian_awal_keperawatan_igd.xbab,penilaian_awal_keperawatan_igd.kbab,penilaian_awal_keperawatan_igd.wbab,penilaian_awal_keperawatan_igd.bak,penilaian_awal_keperawatan_igd.xbak,penilaian_awal_keperawatan_igd.wbak,"+
                            "penilaian_awal_keperawatan_igd.lbak,penilaian_awal_keperawatan_igd.psikologis,penilaian_awal_keperawatan_igd.jiwa,penilaian_awal_keperawatan_igd.perilaku,penilaian_awal_keperawatan_igd.dilaporkan,penilaian_awal_keperawatan_igd.sebutkan,penilaian_awal_keperawatan_igd.hubungan,pasien.stts_nikah,"+ 
                            "penilaian_awal_keperawatan_igd.tinggal_dengan,penilaian_awal_keperawatan_igd.ket_tinggal,pasien.pekerjaan,penjab.png_jawab,penilaian_awal_keperawatan_igd.budaya,penilaian_awal_keperawatan_igd.ket_budaya,pasien.pnd,penilaian_awal_keperawatan_igd.pendidikan_pj,penilaian_awal_keperawatan_igd.ket_pendidikan_pj,"+  
                            "penilaian_awal_keperawatan_igd.edukasi,penilaian_awal_keperawatan_igd.ket_edukasi,penilaian_awal_keperawatan_igd.kemampuan,penilaian_awal_keperawatan_igd.aktifitas,penilaian_awal_keperawatan_igd.alat_bantu,penilaian_awal_keperawatan_igd.ket_bantu,"+
                            "penilaian_awal_keperawatan_igd.nyeri,penilaian_awal_keperawatan_igd.provokes,penilaian_awal_keperawatan_igd.ket_provokes,penilaian_awal_keperawatan_igd.quality,penilaian_awal_keperawatan_igd.ket_quality,penilaian_awal_keperawatan_igd.lokasi,penilaian_awal_keperawatan_igd.menyebar,"+
                            "penilaian_awal_keperawatan_igd.skala_nyeri,penilaian_awal_keperawatan_igd.durasi,penilaian_awal_keperawatan_igd.nyeri_hilang,penilaian_awal_keperawatan_igd.ket_nyeri,penilaian_awal_keperawatan_igd.pada_dokter,penilaian_awal_keperawatan_igd.ket_dokter,"+
                            "penilaian_awal_keperawatan_igd.berjalan_a,penilaian_awal_keperawatan_igd.berjalan_b,penilaian_awal_keperawatan_igd.berjalan_c,penilaian_awal_keperawatan_igd.hasil,penilaian_awal_keperawatan_igd.lapor,penilaian_awal_keperawatan_igd.ket_lapor,"+
                            "penilaian_awal_keperawatan_igd.rencana,penilaian_awal_keperawatan_igd.nip,petugas.nama "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_awal_keperawatan_igd on reg_periksa.no_rawat=penilaian_awal_keperawatan_igd.no_rawat "+
                            "inner join petugas on penilaian_awal_keperawatan_igd.nip=petugas.nip "+
                            "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                            "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "+
                            "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                            "penilaian_awal_keperawatan_igd.tanggal between ? and ? order by penilaian_awal_keperawatan_igd.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_igd.tanggal,penilaian_awal_keperawatan_igd.informasi,"+
                            "penilaian_awal_keperawatan_igd.keluhan_utama,penilaian_awal_keperawatan_igd.rpd,penilaian_awal_keperawatan_igd.rpo,penilaian_awal_keperawatan_igd.status_kehamilan,penilaian_awal_keperawatan_igd.gravida,penilaian_awal_keperawatan_igd.para,"+
                            "penilaian_awal_keperawatan_igd.abortus,penilaian_awal_keperawatan_igd.hpht,penilaian_awal_keperawatan_igd.tekanan,penilaian_awal_keperawatan_igd.pupil,penilaian_awal_keperawatan_igd.neurosensorik,penilaian_awal_keperawatan_igd.integumen,penilaian_awal_keperawatan_igd.turgor,"+ 
                            "penilaian_awal_keperawatan_igd.edema,penilaian_awal_keperawatan_igd.mukosa,penilaian_awal_keperawatan_igd.perdarahan,penilaian_awal_keperawatan_igd.jumlah_perdarahan,penilaian_awal_keperawatan_igd.warna_perdarahan,penilaian_awal_keperawatan_igd.intoksikasi,"+
                            "penilaian_awal_keperawatan_igd.bab,penilaian_awal_keperawatan_igd.xbab,penilaian_awal_keperawatan_igd.kbab,penilaian_awal_keperawatan_igd.wbab,penilaian_awal_keperawatan_igd.bak,penilaian_awal_keperawatan_igd.xbak,penilaian_awal_keperawatan_igd.wbak,"+
                            "penilaian_awal_keperawatan_igd.lbak,penilaian_awal_keperawatan_igd.psikologis,penilaian_awal_keperawatan_igd.jiwa,penilaian_awal_keperawatan_igd.perilaku,penilaian_awal_keperawatan_igd.dilaporkan,penilaian_awal_keperawatan_igd.sebutkan,penilaian_awal_keperawatan_igd.hubungan,pasien.stts_nikah,"+ 
                            "penilaian_awal_keperawatan_igd.tinggal_dengan,penilaian_awal_keperawatan_igd.ket_tinggal,pasien.pekerjaan,penjab.png_jawab,penilaian_awal_keperawatan_igd.budaya,penilaian_awal_keperawatan_igd.ket_budaya,pasien.pnd,penilaian_awal_keperawatan_igd.pendidikan_pj,penilaian_awal_keperawatan_igd.ket_pendidikan_pj,"+  
                            "penilaian_awal_keperawatan_igd.edukasi,penilaian_awal_keperawatan_igd.ket_edukasi,penilaian_awal_keperawatan_igd.kemampuan,penilaian_awal_keperawatan_igd.aktifitas,penilaian_awal_keperawatan_igd.alat_bantu,penilaian_awal_keperawatan_igd.ket_bantu,"+
                            "penilaian_awal_keperawatan_igd.nyeri,penilaian_awal_keperawatan_igd.provokes,penilaian_awal_keperawatan_igd.ket_provokes,penilaian_awal_keperawatan_igd.quality,penilaian_awal_keperawatan_igd.ket_quality,penilaian_awal_keperawatan_igd.lokasi,penilaian_awal_keperawatan_igd.menyebar,"+
                            "penilaian_awal_keperawatan_igd.skala_nyeri,penilaian_awal_keperawatan_igd.durasi,penilaian_awal_keperawatan_igd.nyeri_hilang,penilaian_awal_keperawatan_igd.ket_nyeri,penilaian_awal_keperawatan_igd.pada_dokter,penilaian_awal_keperawatan_igd.ket_dokter,"+
                            "penilaian_awal_keperawatan_igd.berjalan_a,penilaian_awal_keperawatan_igd.berjalan_b,penilaian_awal_keperawatan_igd.berjalan_c,penilaian_awal_keperawatan_igd.hasil,penilaian_awal_keperawatan_igd.lapor,penilaian_awal_keperawatan_igd.ket_lapor,"+
                            "penilaian_awal_keperawatan_igd.rencana,penilaian_awal_keperawatan_igd.nip,petugas.nama "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_awal_keperawatan_igd on reg_periksa.no_rawat=penilaian_awal_keperawatan_igd.no_rawat "+
                            "inner join petugas on penilaian_awal_keperawatan_igd.nip=petugas.nip "+
                            "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                            "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "+
                            "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                            "penilaian_awal_keperawatan_igd.tanggal between ? and ? and "+
                            "(reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                            "penilaian_awal_keperawatan_igd.nip like ? or petugas.nama like ?) "+
                            "order by penilaian_awal_keperawatan_igd.tanggal");
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
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='9%'><b>PASIEN & PETUGAS</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='10%'><b>I. RIWAYAT KESEHATAN PASIEN</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='15%'><b>II. PEMERIKSAAN FISIK</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='14%'><b>III. RIWAYAT PSIKOLOGIS - SOSIAL - EKONOMI - BUDAYA - SPIRITUAL</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='8%'><b>IV. PENGKAJIAN FUNGSI</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='12%'><b>V. SKALA NYERI</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='16%'><b>VI. PENILAIAN RESIKO JATUH (GET UP AND GO)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAFA' align='center' width='11%'><b>MASALAH & RENCANA KEPERAWATAN</b></td>"+
                        "</tr>"
                    );
                    while(rs.next()){
                        masalahkeperawatanigd="";
                        ps2=koneksi.prepareStatement(
                            "select master_masalah_keperawatan_igd.kode_masalah,master_masalah_keperawatan_igd.nama_masalah from master_masalah_keperawatan_igd "+
                            "inner join penilaian_awal_keperawatan_igd_masalah on penilaian_awal_keperawatan_igd_masalah.kode_masalah=master_masalah_keperawatan_igd.kode_masalah "+
                            "where penilaian_awal_keperawatan_igd_masalah.no_rawat=? order by penilaian_awal_keperawatan_igd_masalah.kode_masalah");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            while(rs2.next()){
                                masalahkeperawatanigd=rs2.getString("nama_masalah")+", "+masalahkeperawatanigd;
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
                                            "<td width='32%' valign='top'>Agama</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("agama")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Bahasa</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nama_bahasa")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Pekerjaan</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("pekerjaan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Pembayaran</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("png_jawab")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Pendidikan</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("pnd")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Stts.Nikah</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("stts_nikah")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Cacat Fisik</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nama_cacat")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Petugas</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("nip")+" "+rs.getString("nama")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Tgl.Asuhan</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("tanggal")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Informasi</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("informasi")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>RPS</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>RPD</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("rpd")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>RPO</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("rpo")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Stts.Hami</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("status_kehamilan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>HPHT</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("hpht")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Para</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("para")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Abortus</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("abortus")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='32%' valign='top'>Gravida</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>"+rs.getString("gravida")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Tekanan Intrakranial</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("tekanan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Pupil</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("pupil")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Neurosensorik / Muskuloskeletal</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("neurosensorik")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Integumen</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("integumen")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Turgor kulit</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("turgor")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Edema</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("edema")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Mukosa Mulut</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("mukosa")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Perdarahan</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("perdarahan")+", Jumlah : "+rs.getString("jumlah_perdarahan")+", Warna : "+rs.getString("warna_perdarahan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Intoksikasi</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("intoksikasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Eliminasi</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+
                                                "BAB : -Frekuensi : "+rs.getString("bab")+" x / "+rs.getString("xbab")+" -Konsistensi : "+rs.getString("kbab")+" -Warna : "+rs.getString("wbab")+"<br>"+
                                                "BAK : -Frekuensi : "+rs.getString("bak")+" x / "+rs.getString("xbak")+" -Warna : "+rs.getString("wbak")+" -Lain-lain : "+rs.getString("lbak")+"<br>"+
                                            "</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kondisi Psikologis</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("psikologis")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Gangguan Jiwa Di Masa Lalu</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("jiwa")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Adakah perilaku</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("perilaku")+", Dilaporkan Ke : "+rs.getString("dilaporkan")+", Sebutkan : "+rs.getString("sebutkan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Hubungan Pasien Dengan Anggota Keluarga</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("hubungan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Tinggal Dengan</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("tinggal_dengan")+(rs.getString("ket_tinggal").equals("")?"":", "+rs.getString("ket_tinggal"))+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kepercayaan / Budaya / Nilai-nilai Khusus</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("budaya")+(rs.getString("ket_budaya").equals("")?"":", "+rs.getString("ket_budaya"))+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Pendidikan P.J.</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("pendidikan_pj")+(rs.getString("ket_pendidikan_pj").equals("")?"":", "+rs.getString("ket_pendidikan_pj"))+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Edukasi Diberikan Kepada</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("edukasi")+(rs.getString("ket_edukasi").equals("")?"":", "+rs.getString("ket_edukasi"))+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Kemampuan Aktifitas Sehari-hari</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("kemampuan")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Aktifitas</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("aktifitas")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='34%' valign='top'>Alat Bantu</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("alat_bantu")+(rs.getString("ket_bantu").equals("")?"":", "+rs.getString("ket_bantu"))+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Tingkat Nyeri</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("nyeri")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Provokes</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("provokes")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Provokes</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_provokes")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Kualitas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("quality")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Kualitas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_quality")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Lokas</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("lokasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Menyebar</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("menyebar")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Skala Nyeri</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("skala_nyeri")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Durasi</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("durasi")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Nyeri Hilang</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("nyeri_hilang")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Ket. Hilang Nyeri</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_nyeri")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Lapor Ke Dokter</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("pada_dokter")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='44%' valign='top'>Jam Lapor</td><td valign='top'>:&nbsp;</td><td width='55%' valign='top'>"+rs.getString("ket_dokter")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Tidak seimbang/sempoyongan/limbung</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("berjalan_a")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain)</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("berjalan_b")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Menopang saat akan duduk, tampak memegang pinggiran kursi atau meja/benda lain sebagai penopang</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("berjalan_c")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Hasil</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("hasil")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Dilaporan ke dokter?</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("lapor")+"</td>"+
                                        "</tr>"+
                                        "<tr class='isi2'>"+
                                            "<td width='64%' valign='top'>Jam Lapor</td><td valign='top'>:&nbsp;</td><td width='35%' valign='top'>"+rs.getString("ket_lapor")+"</td>"+
                                        "</tr>"+
                                    "</table>"+
                                "</td>"+
                                "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                    "Masalah Keperawatan : "+masalahkeperawatanigd+"<br><br>"+
                                    "Rencana Keperawatan : "+rs.getString("rencana")+
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

                    File f = new File("DataPenilaianAwalKeperawatanIGD.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL KEPERAWATAN IGD<br><br></font>"+        
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
            if(Valid.daysOld("./cache/masalahkeperawatanigd.iyem")<30){
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

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
       if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();    
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            param.put("nyeri",Sequel.cariGambar("select gambar.nyeri from gambar")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),79).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),80).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),79).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString())); 
            try {
                masalahkeperawatanigd="";
                ps2=koneksi.prepareStatement(
                    "select master_masalah_keperawatan_igd.kode_masalah,master_masalah_keperawatan_igd.nama_masalah from master_masalah_keperawatan_igd "+
                    "inner join penilaian_awal_keperawatan_igd_masalah on penilaian_awal_keperawatan_igd_masalah.kode_masalah=master_masalah_keperawatan_igd.kode_masalah "+
                    "where penilaian_awal_keperawatan_igd_masalah.no_rawat=? order by penilaian_awal_keperawatan_igd_masalah.kode_masalah");
                try {
                    ps2.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs2=ps2.executeQuery();
                    while(rs2.next()){
                        masalahkeperawatanigd=rs2.getString("nama_masalah")+", "+masalahkeperawatanigd;
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
            param.put("masalah",masalahkeperawatanigd);  
            try {
                masalahkeperawatanigd="";
                ps2=koneksi.prepareStatement(
                    "select master_rencana_keperawatan_igd.kode_rencana,master_rencana_keperawatan_igd.rencana_keperawatan from master_rencana_keperawatan_igd "+
                    "inner join penilaian_awal_keperawatan_ralan_rencana_igd on penilaian_awal_keperawatan_ralan_rencana_igd.kode_rencana=master_rencana_keperawatan_igd.kode_rencana "+
                    "where penilaian_awal_keperawatan_ralan_rencana_igd.no_rawat=? order by penilaian_awal_keperawatan_ralan_rencana_igd.kode_rencana");
                try {
                    ps2.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs2=ps2.executeQuery();
                    while(rs2.next()){
                        masalahkeperawatanigd=rs2.getString("rencana_keperawatan")+", "+masalahkeperawatanigd;
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
            param.put("rencana",masalahkeperawatanigd); 
            Valid.MyReportqry("rptCetakPenilaianAwalKeperawatanIGD.jasper","report","::[ Laporan Penilaian Awal Keperawatan IGD ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_igd.tanggal,penilaian_awal_keperawatan_igd.informasi,"+
                        "penilaian_awal_keperawatan_igd.keluhan_utama,penilaian_awal_keperawatan_igd.rpd,penilaian_awal_keperawatan_igd.rpo,penilaian_awal_keperawatan_igd.status_kehamilan,penilaian_awal_keperawatan_igd.gravida,penilaian_awal_keperawatan_igd.para,"+
                        "penilaian_awal_keperawatan_igd.abortus,penilaian_awal_keperawatan_igd.hpht,penilaian_awal_keperawatan_igd.tekanan,penilaian_awal_keperawatan_igd.pupil,penilaian_awal_keperawatan_igd.neurosensorik,penilaian_awal_keperawatan_igd.integumen,penilaian_awal_keperawatan_igd.turgor,"+ 
                        "penilaian_awal_keperawatan_igd.edema,penilaian_awal_keperawatan_igd.mukosa,penilaian_awal_keperawatan_igd.perdarahan,penilaian_awal_keperawatan_igd.jumlah_perdarahan,penilaian_awal_keperawatan_igd.warna_perdarahan,penilaian_awal_keperawatan_igd.intoksikasi,"+
                        "penilaian_awal_keperawatan_igd.bab,penilaian_awal_keperawatan_igd.xbab,penilaian_awal_keperawatan_igd.kbab,penilaian_awal_keperawatan_igd.wbab,penilaian_awal_keperawatan_igd.bak,penilaian_awal_keperawatan_igd.xbak,penilaian_awal_keperawatan_igd.wbak,"+
                        "penilaian_awal_keperawatan_igd.lbak,penilaian_awal_keperawatan_igd.psikologis,penilaian_awal_keperawatan_igd.jiwa,penilaian_awal_keperawatan_igd.perilaku,penilaian_awal_keperawatan_igd.dilaporkan,penilaian_awal_keperawatan_igd.sebutkan,penilaian_awal_keperawatan_igd.hubungan,pasien.stts_nikah,"+ 
                        "penilaian_awal_keperawatan_igd.tinggal_dengan,penilaian_awal_keperawatan_igd.ket_tinggal,pasien.pekerjaan,penjab.png_jawab,penilaian_awal_keperawatan_igd.budaya,penilaian_awal_keperawatan_igd.ket_budaya,pasien.pnd,penilaian_awal_keperawatan_igd.pendidikan_pj,penilaian_awal_keperawatan_igd.ket_pendidikan_pj,"+  
                        "penilaian_awal_keperawatan_igd.edukasi,penilaian_awal_keperawatan_igd.ket_edukasi,penilaian_awal_keperawatan_igd.kemampuan,penilaian_awal_keperawatan_igd.aktifitas,penilaian_awal_keperawatan_igd.alat_bantu,penilaian_awal_keperawatan_igd.ket_bantu,"+
                        "penilaian_awal_keperawatan_igd.nyeri,penilaian_awal_keperawatan_igd.provokes,penilaian_awal_keperawatan_igd.ket_provokes,penilaian_awal_keperawatan_igd.quality,penilaian_awal_keperawatan_igd.ket_quality,penilaian_awal_keperawatan_igd.lokasi,penilaian_awal_keperawatan_igd.menyebar,"+
                        "penilaian_awal_keperawatan_igd.skala_nyeri,penilaian_awal_keperawatan_igd.durasi,penilaian_awal_keperawatan_igd.nyeri_hilang,penilaian_awal_keperawatan_igd.ket_nyeri,penilaian_awal_keperawatan_igd.pada_dokter,penilaian_awal_keperawatan_igd.ket_dokter,"+
                        "penilaian_awal_keperawatan_igd.berjalan_a,penilaian_awal_keperawatan_igd.berjalan_b,penilaian_awal_keperawatan_igd.berjalan_c,penilaian_awal_keperawatan_igd.hasil,penilaian_awal_keperawatan_igd.lapor,penilaian_awal_keperawatan_igd.ket_lapor,"+
                        "penilaian_awal_keperawatan_igd.rencana,penilaian_awal_keperawatan_igd.nip,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_awal_keperawatan_igd on reg_periksa.no_rawat=penilaian_awal_keperawatan_igd.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_igd.nip=petugas.nip "+
                        "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                        "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "+
                        "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }  
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void textBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox14ActionPerformed

    private void JmlPelacuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlPelacuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlPelacuranActionPerformed

    private void JmlPerkosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlPerkosaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlPerkosaActionPerformed

    private void JmlPenyeranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlPenyeranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlPenyeranganActionPerformed

    private void JmlRampokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlRampokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlRampokActionPerformed

    private void JmlSenjataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlSenjataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlSenjataActionPerformed

    private void JmlNarkobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlNarkobaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlNarkobaActionPerformed

    private void JmlVandalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlVandalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlVandalActionPerformed

    private void OD1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OD1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OD1KeyPressed

    private void OD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OD1ActionPerformed

    private void ODKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ODKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ODKeyPressed

    private void ODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ODActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ODActionPerformed

    private void TempatTinggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TempatTinggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TempatTinggalKeyPressed

    private void CaraNarkoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraNarkoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaraNarkoKeyPressed

    private void CaraNarkoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaraNarkoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaraNarkoActionPerformed

    private void CaraNarkoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CaraNarkoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CaraNarkoItemStateChanged

    private void SepanjangNarkoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SepanjangNarkoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SepanjangNarkoKeyPressed

    private void SepanjangNarkoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SepanjangNarkoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SepanjangNarkoActionPerformed

    private void SepanjangNarkoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SepanjangNarkoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SepanjangNarkoItemStateChanged

    private void SebulanNarkoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SebulanNarkoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SebulanNarkoKeyPressed

    private void SebulanNarkoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SebulanNarkoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SebulanNarkoActionPerformed

    private void SebulanNarkoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SebulanNarkoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SebulanNarkoItemStateChanged

    private void JenisNarkoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisNarkoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisNarkoKeyPressed

    private void JenisNarkoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JenisNarkoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisNarkoActionPerformed

    private void JenisNarkoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisNarkoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisNarkoItemStateChanged

    private void DukunganHidup1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DukunganHidup1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DukunganHidup1KeyPressed

    private void DukunganHidup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DukunganHidup1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DukunganHidup1ActionPerformed

    private void DukunganHidup1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DukunganHidup1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DukunganHidup1ItemStateChanged

    private void PengobotanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PengobotanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PengobotanKeyPressed

    private void MakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MakanKeyPressed

    private void PernahRehabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernahRehabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernahRehabKeyPressed

    private void PernahRehabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PernahRehabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernahRehabActionPerformed

    private void FinansialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FinansialKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinansialKeyPressed

    private void JenisPenyakit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPenyakit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPenyakit1KeyPressed

    private void StatSehatHepBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatSehatHepBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatSehatHepBKeyPressed

    private void StatSehatHepBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatSehatHepBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatSehatHepBActionPerformed

    private void StatSehatHIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatSehatHIVKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatSehatHIVKeyPressed

    private void StatSehatHIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatSehatHIVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatSehatHIVActionPerformed

    private void TerapiMedis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerapiMedis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TerapiMedis1KeyPressed

    private void TerapiMedis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerapiMedis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TerapiMedis1ActionPerformed

    private void KetTerapiMedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetTerapiMedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetTerapiMedisKeyPressed

    private void TerapiMedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerapiMedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TerapiMedisKeyPressed

    private void TerapiMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerapiMedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TerapiMedisActionPerformed

    private void SakitKronisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SakitKronisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitKronisKeyPressed

    private void SakitKronisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SakitKronisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitKronisActionPerformed

    private void LamaRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamaRawatKeyPressed

    private void JenisPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPenyakitKeyPressed
        //        Valid.pindah2(evt,Informasi,RPD);
    }//GEN-LAST:event_JenisPenyakitKeyPressed

    private void KetSakitKronisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSakitKronisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetSakitKronisKeyPressed

    private void PendidikanTerakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PendidikanTerakhirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PendidikanTerakhirKeyPressed

    private void BtnTambahRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahRencanaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterRencanaKeperawatanIGD form=new MasterRencanaKeperawatanIGD(null,false);
        form.isCek();
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahRencanaActionPerformed

    private void BtnAllRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllRencanaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllRencanaActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCariRencana, TCariRencana);
        }
    }//GEN-LAST:event_BtnAllRencanaKeyPressed

    private void BtnAllRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllRencanaActionPerformed
        TCariRencana.setText("");
        tampilRencana();
        tampilRencana2();
        tampilEtiologi();
        tampilEtiologi2();
        tampilLuaran();
        tampilLuaran2();
    }//GEN-LAST:event_BtnAllRencanaActionPerformed

    private void BtnCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariRencanaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilRencana2();
            tampilEtiologi2();
            tampilLuaran2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            BtnSimpan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TCariRencana.requestFocus();
        }
    }//GEN-LAST:event_BtnCariRencanaKeyPressed

    private void BtnCariRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariRencanaActionPerformed
        tampilRencana2();
        tampilEtiologi2();
        tampilLuaran2();
    }//GEN-LAST:event_BtnCariRencanaActionPerformed

    private void TCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariRencanaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilRencana2();
            tampilEtiologi2();
            tampilLuaran2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            BtnCariRencana.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TCariMasalah.requestFocus();
        }
    }//GEN-LAST:event_TCariRencanaKeyPressed

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaKeyPressed
        Valid.pindah2(evt,TCariMasalah,BtnSimpan);
    }//GEN-LAST:event_RencanaKeyPressed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariMasalahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilMasalah2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            Rencana.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//            KetDokter.requestFocus();
        }
    }//GEN-LAST:event_TCariMasalahKeyPressed

    private void BtnCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariMasalahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampilMasalah2();
        }else if((evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN)||(evt.getKeyCode()==KeyEvent.VK_TAB)){
            Rencana.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//            KetDokter.requestFocus();
        }
    }//GEN-LAST:event_BtnCariMasalahKeyPressed

    private void BtnCariMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariMasalahActionPerformed
        tampilMasalah2();
    }//GEN-LAST:event_BtnCariMasalahActionPerformed

    private void BtnAllMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllMasalahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllMasalahActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCariMasalah, TCariMasalah);
        }
    }//GEN-LAST:event_BtnAllMasalahKeyPressed

    private void BtnAllMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllMasalahActionPerformed
        TCari.setText("");
        tampilMasalah();
    }//GEN-LAST:event_BtnAllMasalahActionPerformed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterMasalahKeperawatanIGD form=new MasterMasalahKeperawatanIGD(null,false);
        form.isCek();
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void tbMasalahKeperawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyReleased
        if(tabModeMasalah.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    tampilRencana2();
                    tampilEtiologi2();
                    tampilLuaran2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyReleased

    private void tbMasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyPressed
        if(tabModeMasalah.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariMasalah.setText("");
                TCariMasalah.requestFocus();
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyPressed

    private void tbMasalahKeperawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanMouseClicked
        if(tabModeMasalah.getRowCount()!=0){
            try {
                tampilRencana2();
                tampilEtiologi2();
                tampilLuaran2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanMouseClicked

    private void InformasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InformasiKeyPressed
        Valid.pindah(evt,TglAsuhan,JenisPenyakit);
    }//GEN-LAST:event_InformasiKeyPressed

    private void PolaKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaKerjaKeyPressed
        //        Valid.pindah(evt,StatKerja,Perilaku);
    }//GEN-LAST:event_PolaKerjaKeyPressed

    private void StatKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatKerjaKeyPressed
        // Valid.pindah(evt,LBAK,PolaKerja);
    }//GEN-LAST:event_StatKerjaKeyPressed

    private void TahunRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TahunRawatKeyPressed
        //        Valid.pindah(evt,Intoksikasi,XBAB);
    }//GEN-LAST:event_TahunRawatKeyPressed

    private void StatKawinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatKawinKeyPressed
        //        Valid.pindah(evt,RPO,HPHT);
    }//GEN-LAST:event_StatKawinKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        //        Valid.pindah2(evt,Rencana,RPD);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed

    }//GEN-LAST:event_KdPetugasKeyPressed

    private void TPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPasienActionPerformed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{
            Valid.pindah(evt,TCari,BtnDokter);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void JmlLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlLainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlLainActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalKeperawatanLaporNapza dialog = new RMPenilaianAwalKeperawatanLaporNapza(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllMasalah;
    private widget.Button BtnAllRencana;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariMasalah;
    private widget.Button BtnCariRencana;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahMasalah;
    private widget.Button BtnTambahRencana;
    private widget.ComboBox CaraNarko;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DetailRencana;
    private widget.ComboBox DukunganHidup1;
    private widget.ComboBox Finansial;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.ComboBox Informasi;
    private widget.ComboBox JenisNarko;
    private widget.TextArea JenisPenyakit;
    private widget.TextArea JenisPenyakit1;
    private widget.TextBox Jk;
    private widget.TextBox JmlBakar;
    private widget.TextBox JmlBebas;
    private widget.TextBox JmlLain;
    private widget.TextBox JmlMelecehkan;
    private widget.TextBox JmlNarkoba;
    private widget.TextBox JmlPalsu;
    private widget.TextBox JmlPelacuran;
    private widget.TextBox JmlPembunuhan;
    private widget.TextBox JmlPencurian;
    private widget.TextBox JmlPenyerangan;
    private widget.TextBox JmlPerkosa;
    private widget.TextBox JmlRampok;
    private widget.TextBox JmlSenjata;
    private widget.TextBox JmlVandal;
    private widget.TextBox KdPetugas;
    private widget.TextBox KetDukunganHidup;
    private widget.TextBox KetRehab;
    private widget.TextBox KetSakitKronis;
    private widget.TextBox KetTerapiMedis;
    private widget.Label LCount;
    private widget.TextBox LamaRawat;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Makan;
    private widget.TextBox NmPetugas;
    private widget.ComboBox OD;
    private widget.ComboBox OD1;
    private widget.PanelBiasa PanelAccor;
    private widget.ComboBox PendidikanTerakhir;
    private widget.ComboBox Pengobotan;
    private widget.ComboBox PernahRehab;
    private widget.ComboBox PolaKerja;
    private widget.TextArea Rencana;
    private widget.ComboBox SakitKronis;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox SebulanNarko;
    private widget.ComboBox SepanjangNarko;
    private widget.ComboBox StatKawin;
    private widget.ComboBox StatKerja;
    private widget.ComboBox StatSehatHIV;
    private widget.ComboBox StatSehatHepB;
    private widget.TextBox TCari;
    private widget.TextBox TCariMasalah;
    private widget.TextBox TCariRencana;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabRencanaKeperawatan;
    private widget.TextBox TahunRawat;
    private widget.ComboBox TempatTinggal;
    private widget.ComboBox TerapiMedis;
    private widget.ComboBox TerapiMedis1;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.TextBox WaktuOD;
    private widget.TextBox ZatUtama;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
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
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel119;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel30;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel39;
    private widget.Label jLabel53;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel6;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator4;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.PanelBiasa panelBiasa1;
    private widget.PanelBiasa panelBiasa3;
    private widget.PanelBiasa panelBiasa4;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbEtiologiDetail;
    private widget.Table tbLuaranDetail;
    private widget.Table tbMasalahDetailMasalah;
    private widget.Table tbMasalahKeperawatan;
    private widget.Table tbObat;
    private widget.Table tbRencanaDetail;
    private widget.Table tbRencanaEtiologi;
    private widget.Table tbRencanaKeperawatan;
    private widget.Table tbRencanaLuaran;
    private widget.TextBox textBox14;
    // End of variables declaration//GEN-END:variables

     
    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_awal_keperawatan_igd.tanggal,penilaian_awal_keperawatan_igd.informasi,"+
                        "penilaian_awal_keperawatan_igd.keluhan_utama,penilaian_awal_keperawatan_igd.rpd,penilaian_awal_keperawatan_igd.rpo,penilaian_awal_keperawatan_igd.status_kehamilan,penilaian_awal_keperawatan_igd.gravida,penilaian_awal_keperawatan_igd.para,"+
                        "penilaian_awal_keperawatan_igd.abortus,penilaian_awal_keperawatan_igd.hpht,penilaian_awal_keperawatan_igd.tekanan,penilaian_awal_keperawatan_igd.pupil,penilaian_awal_keperawatan_igd.neurosensorik,penilaian_awal_keperawatan_igd.integumen,penilaian_awal_keperawatan_igd.turgor,"+ 
                        "penilaian_awal_keperawatan_igd.edema,penilaian_awal_keperawatan_igd.mukosa,penilaian_awal_keperawatan_igd.perdarahan,penilaian_awal_keperawatan_igd.jumlah_perdarahan,penilaian_awal_keperawatan_igd.warna_perdarahan,penilaian_awal_keperawatan_igd.intoksikasi,"+
                        "penilaian_awal_keperawatan_igd.bab,penilaian_awal_keperawatan_igd.xbab,penilaian_awal_keperawatan_igd.kbab,penilaian_awal_keperawatan_igd.wbab,penilaian_awal_keperawatan_igd.bak,penilaian_awal_keperawatan_igd.xbak,penilaian_awal_keperawatan_igd.wbak,"+
                        "penilaian_awal_keperawatan_igd.lbak,penilaian_awal_keperawatan_igd.psikologis,penilaian_awal_keperawatan_igd.jiwa,penilaian_awal_keperawatan_igd.perilaku,penilaian_awal_keperawatan_igd.dilaporkan,penilaian_awal_keperawatan_igd.sebutkan,penilaian_awal_keperawatan_igd.hubungan,pasien.stts_nikah,"+ 
                        "penilaian_awal_keperawatan_igd.tinggal_dengan,penilaian_awal_keperawatan_igd.ket_tinggal,pasien.pekerjaan,penjab.png_jawab,penilaian_awal_keperawatan_igd.budaya,penilaian_awal_keperawatan_igd.ket_budaya,pasien.pnd,penilaian_awal_keperawatan_igd.pendidikan_pj,penilaian_awal_keperawatan_igd.ket_pendidikan_pj,"+  
                        "penilaian_awal_keperawatan_igd.edukasi,penilaian_awal_keperawatan_igd.ket_edukasi,penilaian_awal_keperawatan_igd.kemampuan,penilaian_awal_keperawatan_igd.aktifitas,penilaian_awal_keperawatan_igd.alat_bantu,penilaian_awal_keperawatan_igd.ket_bantu,"+
                        "penilaian_awal_keperawatan_igd.beratbadan,penilaian_awal_keperawatan_igd.tinggibadan,penilaian_awal_keperawatan_igd.riwayatk,penilaian_awal_keperawatan_igd.ket_riwayatk,penilaian_awal_keperawatan_igd.riwayati,penilaian_awal_keperawatan_igd.ket_riwayati,penilaian_awal_keperawatan_igd.ket_riwayati1,"+
                        "penilaian_awal_keperawatan_igd.nyeri,penilaian_awal_keperawatan_igd.provokes,penilaian_awal_keperawatan_igd.ket_provokes,penilaian_awal_keperawatan_igd.quality,penilaian_awal_keperawatan_igd.ket_quality,penilaian_awal_keperawatan_igd.lokasi,penilaian_awal_keperawatan_igd.menyebar,"+
                        "penilaian_awal_keperawatan_igd.beratbadan,penilaian_awal_keperawatan_igd.tinggibadan,penilaian_awal_keperawatan_igd.riwayatk,penilaian_awal_keperawatan_igd.ket_riwayatk,penilaian_awal_keperawatan_igd.riwayati,penilaian_awal_keperawatan_igd.ket_riwayati,penilaian_awal_keperawatan_igd.ket_riwayati1,"+
                        "penilaian_awal_keperawatan_igd.skala_nyeri,penilaian_awal_keperawatan_igd.durasi,penilaian_awal_keperawatan_igd.nyeri_hilang,penilaian_awal_keperawatan_igd.ket_nyeri,penilaian_awal_keperawatan_igd.pada_dokter,penilaian_awal_keperawatan_igd.ket_dokter,"+
                        "penilaian_awal_keperawatan_igd.berjalan_a,penilaian_awal_keperawatan_igd.berjalan_b,penilaian_awal_keperawatan_igd.berjalan_c,penilaian_awal_keperawatan_igd.hasil,penilaian_awal_keperawatan_igd.lapor,penilaian_awal_keperawatan_igd.ket_lapor,"+
                        "penilaian_awal_keperawatan_igd.rencana,penilaian_awal_keperawatan_igd.nip,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_awal_keperawatan_igd on reg_periksa.no_rawat=penilaian_awal_keperawatan_igd.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_igd.nip=petugas.nip "+
                        "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                        "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "+
                        "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                        "penilaian_awal_keperawatan_igd.tanggal between ? and ? order by penilaian_awal_keperawatan_igd.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_awal_keperawatan_igd.tanggal,penilaian_awal_keperawatan_igd.informasi,"+
                        "penilaian_awal_keperawatan_igd.keluhan_utama,penilaian_awal_keperawatan_igd.rpd,penilaian_awal_keperawatan_igd.rpo,penilaian_awal_keperawatan_igd.status_kehamilan,penilaian_awal_keperawatan_igd.gravida,penilaian_awal_keperawatan_igd.para,"+
                        "penilaian_awal_keperawatan_igd.abortus,penilaian_awal_keperawatan_igd.hpht,penilaian_awal_keperawatan_igd.tekanan,penilaian_awal_keperawatan_igd.pupil,penilaian_awal_keperawatan_igd.neurosensorik,penilaian_awal_keperawatan_igd.integumen,penilaian_awal_keperawatan_igd.turgor,"+ 
                        "penilaian_awal_keperawatan_igd.edema,penilaian_awal_keperawatan_igd.mukosa,penilaian_awal_keperawatan_igd.perdarahan,penilaian_awal_keperawatan_igd.jumlah_perdarahan,penilaian_awal_keperawatan_igd.warna_perdarahan,penilaian_awal_keperawatan_igd.intoksikasi,"+
                        "penilaian_awal_keperawatan_igd.bab,penilaian_awal_keperawatan_igd.xbab,penilaian_awal_keperawatan_igd.kbab,penilaian_awal_keperawatan_igd.wbab,penilaian_awal_keperawatan_igd.bak,penilaian_awal_keperawatan_igd.xbak,penilaian_awal_keperawatan_igd.wbak,"+
                        "penilaian_awal_keperawatan_igd.lbak,penilaian_awal_keperawatan_igd.psikologis,penilaian_awal_keperawatan_igd.jiwa,penilaian_awal_keperawatan_igd.perilaku,penilaian_awal_keperawatan_igd.dilaporkan,penilaian_awal_keperawatan_igd.sebutkan,penilaian_awal_keperawatan_igd.hubungan,pasien.stts_nikah,"+ 
                        "penilaian_awal_keperawatan_igd.tinggal_dengan,penilaian_awal_keperawatan_igd.ket_tinggal,pasien.pekerjaan,penjab.png_jawab,penilaian_awal_keperawatan_igd.budaya,penilaian_awal_keperawatan_igd.ket_budaya,pasien.pnd,penilaian_awal_keperawatan_igd.pendidikan_pj,penilaian_awal_keperawatan_igd.ket_pendidikan_pj,"+  
                        "penilaian_awal_keperawatan_igd.edukasi,penilaian_awal_keperawatan_igd.ket_edukasi,penilaian_awal_keperawatan_igd.kemampuan,penilaian_awal_keperawatan_igd.aktifitas,penilaian_awal_keperawatan_igd.alat_bantu,penilaian_awal_keperawatan_igd.ket_bantu,"+
                        "penilaian_awal_keperawatan_igd.beratbadan,penilaian_awal_keperawatan_igd.tinggibadan,penilaian_awal_keperawatan_igd.riwayatk,penilaian_awal_keperawatan_igd.ket_riwayatk,penilaian_awal_keperawatan_igd.riwayati,penilaian_awal_keperawatan_igd.ket_riwayati,penilaian_awal_keperawatan_igd.ket_riwayati1,"+
                        "penilaian_awal_keperawatan_igd.nyeri,penilaian_awal_keperawatan_igd.provokes,penilaian_awal_keperawatan_igd.ket_provokes,penilaian_awal_keperawatan_igd.quality,penilaian_awal_keperawatan_igd.ket_quality,penilaian_awal_keperawatan_igd.lokasi,penilaian_awal_keperawatan_igd.menyebar,"+
                        "penilaian_awal_keperawatan_igd.skala_nyeri,penilaian_awal_keperawatan_igd.durasi,penilaian_awal_keperawatan_igd.nyeri_hilang,penilaian_awal_keperawatan_igd.ket_nyeri,penilaian_awal_keperawatan_igd.pada_dokter,penilaian_awal_keperawatan_igd.ket_dokter,"+
                        "penilaian_awal_keperawatan_igd.berjalan_a,penilaian_awal_keperawatan_igd.berjalan_b,penilaian_awal_keperawatan_igd.berjalan_c,penilaian_awal_keperawatan_igd.hasil,penilaian_awal_keperawatan_igd.lapor,penilaian_awal_keperawatan_igd.ket_lapor,"+
                        "penilaian_awal_keperawatan_igd.rencana,penilaian_awal_keperawatan_igd.nip,petugas.nama "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_awal_keperawatan_igd on reg_periksa.no_rawat=penilaian_awal_keperawatan_igd.no_rawat "+
                        "inner join petugas on penilaian_awal_keperawatan_igd.nip=petugas.nip "+
                        "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                        "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "+
                        "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "+
                        "penilaian_awal_keperawatan_igd.tanggal between ? and ? and "+
                        "(reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_awal_keperawatan_igd.nip like ? or petugas.nama like ?) "+
                        "order by penilaian_awal_keperawatan_igd.tanggal");
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("jk"),
                        rs.getString("tgl_lahir"),rs.getString("tanggal"),rs.getString("informasi"),rs.getString("keluhan_utama"),rs.getString("rpd"),rs.getString("rpo"),rs.getString("status_kehamilan"),rs.getString("gravida"),
                        rs.getString("para"),rs.getString("abortus"),rs.getString("hpht"),rs.getString("tekanan"),rs.getString("pupil"),rs.getString("neurosensorik"),rs.getString("integumen"),rs.getString("turgor"),
                        rs.getString("edema"),rs.getString("mukosa"),rs.getString("perdarahan"),rs.getString("jumlah_perdarahan"),rs.getString("warna_perdarahan"),rs.getString("intoksikasi"),rs.getString("bab"),rs.getString("xbab"),rs.getString("kbab"),
                        rs.getString("wbab"),rs.getString("bak"),rs.getString("xbak"),rs.getString("wbak"),rs.getString("lbak"),rs.getString("psikologis"),rs.getString("jiwa"),rs.getString("perilaku"),rs.getString("dilaporkan"),
                        rs.getString("sebutkan"),rs.getString("hubungan"),rs.getString("stts_nikah"),rs.getString("tinggal_dengan"),rs.getString("ket_tinggal"),rs.getString("pekerjaan"),rs.getString("png_jawab"),rs.getString("budaya"),rs.getString("ket_budaya"),rs.getString("pnd"),rs.getString("pendidikan_pj"),rs.getString("ket_pendidikan_pj"),rs.getString("edukasi"),
                        rs.getString("ket_edukasi"),rs.getString("kemampuan"),rs.getString("aktifitas"),rs.getString("alat_bantu"),rs.getString("ket_bantu"),rs.getString("beratbadan"),
                        rs.getString("tinggibadan"),rs.getString("riwayatk"),rs.getString("ket_riwayatk"),rs.getString("riwayati"),rs.getString("ket_riwayati"),rs.getString("ket_riwayati1"),rs.getString("nyeri"),rs.getString("provokes"),rs.getString("ket_provokes"),rs.getString("quality"),rs.getString("ket_quality"),
                        rs.getString("lokasi"),rs.getString("menyebar"),rs.getString("skala_nyeri"),rs.getString("durasi"),rs.getString("nyeri_hilang"),rs.getString("ket_nyeri"),rs.getString("pada_dokter"),rs.getString("ket_dokter"),
                        rs.getString("berjalan_a"),rs.getString("berjalan_b"),rs.getString("berjalan_c"),rs.getString("hasil"),rs.getString("lapor"),rs.getString("ket_lapor"),rs.getString("rencana"),rs.getString("nip"),rs.getString("nama")
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
        Informasi.setSelectedIndex(0);
        JenisPenyakit.setText("");
//        RPD.setText("");
//        RPO.setText("");
        StatKawin.setSelectedIndex(0);
//        Gravida.setText("");
//        Para.setText("");
//        Abortus.setText("");
//        HPHT.setText("");
//        Tekanan.setSelectedIndex(0);
//        Pupil.setSelectedIndex(0);
//        Neurosensorik.setSelectedIndex(0);
//        Integumen.setSelectedIndex(0);
//        Turgor.setSelectedIndex(0);
//        Edema.setSelectedIndex(0);
//        Mukosa.setSelectedIndex(0);
//        Perdarahan.setSelectedIndex(0);
//        SkorSakitKronis.setText("");
//        WarnaPerdarahan.setText("");
//        Intoksikasi.setSelectedIndex(0);
        TahunRawat.setText("");
//        XBAB.setText("");
//        KBAB.setText("");
//        WBAB.setText("");
//        BAK.setText("");
//        XBAK.setText("");
//        WBAK.setText("");
//        LBAK.setText("");
        StatKerja.setSelectedIndex(0);
        PolaKerja.setSelectedIndex(0);
//        Perilaku.setSelectedIndex(0);
//        TerampilTeknis.setText("");
//        Sebutkan.setText("");
//        DukunganHidup.setSelectedIndex(0);
//        TinggalDengan.setSelectedIndex(0);
//        KetTinggal.setText("");
//        StatusBudaya.setSelectedIndex(0);
//        KetBudaya.setText("");
//        PendidikanPJ.setSelectedIndex(0);
//        KetPendidikanPJ.setText("");
//        Edukasi.setSelectedIndex(0);
//        KetEdukasi.setText("");
//        ADL.setSelectedIndex(0);
//        Aktifitas.setSelectedIndex(0);
//        AlatBantu.setSelectedIndex(0);
//        KetAlatBantu.setText("");
//        bb.setText("");
//        tb.setText("");
       
//        Nyeri.setSelectedIndex(0);
//        Provokes.setSelectedIndex(0);
//        KetProvokes.setText("");
//        Quality.setSelectedIndex(0);
//        KetQuality.setText("");
//        Lokasi.setText("");
//        Menyebar.setSelectedIndex(0);
//        SkalaNyeri.setSelectedIndex(0);
//        Durasi.setText("");
//        NyeriHilang.setSelectedIndex(0);
//        KetNyeri.setText("");
//        PadaDokter.setSelectedIndex(0);
//        KetDokter.setText("");
//        ATS.setSelectedIndex(0);
//        BJM.setSelectedIndex(0);
//        MSA.setSelectedIndex(0);
//        Hasil.setSelectedIndex(0);
//        Lapor.setSelectedIndex(0);
//        KetLapor.setText("");
        Rencana.setText("");
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false,i,0);
        }
        Valid.tabelKosong(tabModeRencana);
        Valid.tabelKosong(tabModeEtiologi);
        Valid.tabelKosong(tabModeLuaran);
        TabRawat.setSelectedIndex(0);
        Informasi.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
//            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
//            Bahasa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
//            CacatFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); 
            Informasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            JenisPenyakit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
//            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
//            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            StatKawin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
//            Gravida.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
//            Para.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
//            Abortus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
//            HPHT.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
//            Tekanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
//            Pupil.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
//            Neurosensorik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
//            Integumen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
//            Turgor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
//            Edema.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
//            Mukosa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
//            Perdarahan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
//            SkorSakitKronis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
//            WarnaPerdarahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
//            Intoksikasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            TahunRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
//            XBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
//            KBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
//            WBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
//            BAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
//            XBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
//            WBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
//            LBAK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            StatKerja.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            PolaKerja.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
//            Perilaku.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
//            TerampilTeknis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
//            Sebutkan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
//            DukunganHidup.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
//            KodeKerja.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
//            TinggalDengan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
//            KetTinggal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            ZatUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
//            Pembayaran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
//            StatusBudaya.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
//            KetBudaya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
//            PendidikanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
//            PendidikanPJ.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
//            KetPendidikanPJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
//            Edukasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
//            KetEdukasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
//            ADL.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
//            Aktifitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
//            AlatBantu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
//            KetAlatBantu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
//            bb.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
//            tb.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            
            String rk = tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString();
//            if ("Spontan".equals(rk)) {
//                spontan.setSelected(true);
//            } else if ("Tindakan,Sebutkan".equals(rk)) {
//               tindakan.setSelected(true);
//            }
//            
//            ket_riwayatk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            
            String ri = tbObat.getValueAt(tbObat.getSelectedRow(), 63).toString();
//            if ("Lengkap".equals(ri)) {
//               lengkap.setSelected(true);
//            } else if ("Tidak Lengkap".equals(ri)) {
//               tdklengkap.setSelected(true);
//            }
//            ket_riwayati.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
//            ket_riwayati1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
//            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
//            Provokes.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
//            KetProvokes.setText(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
//            Quality.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
//            KetQuality.setText(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
//            Lokasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),71).toString());
//            Menyebar.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
//            SkalaNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
//            Durasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),74).toString());
//            NyeriHilang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
//            KetNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
//            PadaDokter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),77).toString());
//            KetDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
//            ATS.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString());
//            BJM.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),80).toString());
//            MSA.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),81).toString());
//            Hasil.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),82).toString());
//            Lapor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),83).toString());
//            KetLapor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),84).toString());
            Rencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),85).toString());
//            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            
            try {
                Valid.tabelKosong(tabModeMasalah);
                
                ps=koneksi.prepareStatement(
                        "select master_masalah_keperawatan_igd.kode_masalah,master_masalah_keperawatan_igd.nama_masalah from master_masalah_keperawatan_igd "+
                        "inner join penilaian_awal_keperawatan_igd_masalah on penilaian_awal_keperawatan_igd_masalah.kode_masalah=master_masalah_keperawatan_igd.kode_masalah "+
                        "where penilaian_awal_keperawatan_igd_masalah.no_rawat=? order by penilaian_awal_keperawatan_igd_masalah.kode_masalah");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
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
            
            try {
                Valid.tabelKosong(tabModeRencana);
                ps=koneksi.prepareStatement(
                        "select master_rencana_keperawatan_igd.kode_rencana,master_rencana_keperawatan_igd.rencana_keperawatan from master_rencana_keperawatan_igd "+
                        "inner join penilaian_awal_keperawatan_ralan_rencana_igd on penilaian_awal_keperawatan_ralan_rencana_igd.kode_rencana=master_rencana_keperawatan_igd.kode_rencana "+
                        "where penilaian_awal_keperawatan_ralan_rencana_igd.no_rawat=? order by penilaian_awal_keperawatan_ralan_rencana_igd.kode_rencana");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeRencana.addRow(new Object[]{true,rs.getString(1),rs.getString(2)});
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
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,"+
                    "pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,reg_periksa.tgl_registrasi, "+
                    "pasien.stts_nikah,pasien.pekerjaan,pasien.pnd,penjab.png_jawab "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                    "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik "+
                    "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
//                    Agama.setText(rs.getString("agama"));
//                    Bahasa.setText(rs.getString("nama_bahasa"));
//                    CacatFisik.setText(rs.getString("nama_cacat"));
//                    KodeKerja.setText(rs.getString("stts_nikah"));
                    ZatUtama.setText(rs.getString("pekerjaan"));
//                    PendidikanPasien.setText(rs.getString("pnd"));
//                    Pembayaran.setText(rs.getString("png_jawab"));
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
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_igd());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_igd());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_igd());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_igd());
        BtnTambahMasalah.setEnabled(akses.getmaster_masalah_keperawatan_igd());  
        BtnTambahRencana.setEnabled(akses.getmaster_rencana_keperawatan_igd()); 
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            BtnDokter.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if(NmPetugas.getText().equals("")){
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }             
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }
    
    private void tampilMasalah() {
        try{
            Valid.tabelKosong(tabModeMasalah);
            file=new File("./cache/masalahkeperawatanigd.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_masalah_keperawatan_igd order by master_masalah_keperawatan_igd.kode_masalah");
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
            fileWriter.write("{\"masalahkeperawatanigd\":["+iyem.substring(0,iyem.length()-1)+"]}");
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
            for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
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
            for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbMasalahKeperawatan.getValueAt(i,1).toString();
                    masalah[index]=tbMasalahKeperawatan.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeMasalah);

            for(i=0;i<jml;i++){
                tabModeMasalah.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }
            
            myObj = new FileReader("./cache/masalahkeperawatanigd.iyem");
            root = mapper.readTree(myObj);
            response = root.path("masalahkeperawatanigd");
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
    
    private void tampilRencana() {
        try{
            file=new File("./cache/rencanakeperawatanigd.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_rencana_keperawatan_igd order by master_rencana_keperawatan_igd.kode_rencana");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    iyem=iyem+"{\"KodeMasalah\":\""+rs.getString(1)+"\",\"KodeRencana\":\""+rs.getString(2)+"\",\"NamaRencana\":\""+rs.getString(3)+"\"},";
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
            fileWriter.write("{\"rencanakeperawatanigd\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilRencana2() {
        try{
            jml=0;
            for(i=0;i<tbRencanaKeperawatan.getRowCount();i++){
                if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
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
            for(i=0;i<tbRencanaKeperawatan.getRowCount();i++){
                if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbRencanaKeperawatan.getValueAt(i,1).toString();
                    masalah[index]=tbRencanaKeperawatan.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeRencana);

            for(i=0;i<jml;i++){
                tabModeRencana.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }

            myObj = new FileReader("./cache/rencanakeperawatanigd.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanakeperawatanigd");
            if(response.isArray()){
                for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                    if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                        for(JsonNode list:response){
                            if(list.path("KodeMasalah").asText().toLowerCase().equals(tbMasalahKeperawatan.getValueAt(i,1).toString())&&
                                    list.path("NamaRencana").asText().toLowerCase().contains(TCariRencana.getText().toLowerCase())){
                                tabModeRencana.addRow(new Object[]{
                                    false,list.path("KodeRencana").asText(),list.path("NamaRencana").asText()
                                });                    
                            }
                        }
                    }
                }
            }
            myObj.close();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilEtiologi() {
        try{
            file=new File("./cache/rencanaetiologiigd.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_etiologi_keperawatan order by master_etiologi_keperawatan.kode_etiologi");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    iyem=iyem+"{\"KodeMasalah\":\""+rs.getString(1)+"\",\"KodeEtiologi\":\""+rs.getString(2)+"\",\"Etiologi\":\""+rs.getString(3)+"\"},";
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
            fileWriter.write("{\"rencanaetiologiigd\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilEtiologi2() {
        try{
            jml=0;
            for(i=0;i<tbRencanaEtiologi.getRowCount();i++){
                if(tbRencanaEtiologi.getValueAt(i,0).toString().equals("true")){
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
            for(i=0;i<tbRencanaEtiologi.getRowCount();i++){
                if(tbRencanaEtiologi.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbRencanaEtiologi.getValueAt(i,1).toString();
                    masalah[index]=tbRencanaEtiologi.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeEtiologi);

            for(i=0;i<jml;i++){
                tabModeEtiologi.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }

            myObj = new FileReader("./cache/rencanaetiologiigd.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanaetiologiigd");
            if(response.isArray()){
                for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                    if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                        for(JsonNode list:response){
                            if(list.path("KodeMasalah").asText().toLowerCase().equals(tbMasalahKeperawatan.getValueAt(i,1).toString())&&
                                    list.path("Etiologi").asText().toLowerCase().contains(TCariRencana.getText().toLowerCase())){
                                tabModeEtiologi.addRow(new Object[]{
                                    false,list.path("KodeEtiologi").asText(),list.path("Etiologi").asText()
                                });                    
                            }
                        }
                    }
                }
            }
            myObj.close();
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
     private void tampilLuaran() {
        try{
            file=new File("./cache/rencanaluaranigd.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select * from master_luaran_keperawatan order by master_luaran_keperawatan.kode_luaran");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    iyem=iyem+"{\"KodeMasalah\":\""+rs.getString(1)+"\",\"KodeLuaran\":\""+rs.getString(2)+"\",\"Luaran\":\""+rs.getString(3)+"\"},";
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
            fileWriter.write("{\"rencanaluaranigd\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    private void tampilLuaran2() {
        try{
            jml=0;
            for(i=0;i<tbRencanaLuaran.getRowCount();i++){
                if(tbRencanaLuaran.getValueAt(i,0).toString().equals("true")){
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
            for(i=0;i<tbRencanaLuaran.getRowCount();i++){
                if(tbRencanaLuaran.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbRencanaLuaran.getValueAt(i,1).toString();
                    masalah[index]=tbRencanaLuaran.getValueAt(i,2).toString();
                    index++;
                }
            } 

            Valid.tabelKosong(tabModeLuaran);

            for(i=0;i<jml;i++){
                tabModeLuaran.addRow(new Object[] {
                    pilih[i],kode[i],masalah[i]
                });
            }

            myObj = new FileReader("./cache/rencanaluaranigd.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanaluaranigd");
            if(response.isArray()){
                for(i=0;i<tbMasalahKeperawatan.getRowCount();i++){
                    if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                        for(JsonNode list:response){
                            if(list.path("KodeMasalah").asText().toLowerCase().equals(tbMasalahKeperawatan.getValueAt(i,1).toString())&&
                                    list.path("Luaran").asText().toLowerCase().contains(TCariRencana.getText().toLowerCase())){
                                tabModeLuaran.addRow(new Object[]{
                                    false,list.path("KodeLuaran").asText(),list.path("Luaran").asText()
                                });                    
                            }
                        }
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
            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            try {
                Valid.tabelKosong(tabModeDetailMasalah);
                ps=koneksi.prepareStatement(
                        "select master_masalah_keperawatan_igd.kode_masalah,master_masalah_keperawatan_igd.nama_masalah from master_masalah_keperawatan_igd "+
                        "inner join penilaian_awal_keperawatan_igd_masalah on penilaian_awal_keperawatan_igd_masalah.kode_masalah=master_masalah_keperawatan_igd.kode_masalah "+
                        "where penilaian_awal_keperawatan_igd_masalah.no_rawat=? order by penilaian_awal_keperawatan_igd_masalah.kode_masalah");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
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
            
            try {
                Valid.tabelKosong(tabModeDetailRencana);
                ps=koneksi.prepareStatement(
                        "select master_rencana_keperawatan_igd.kode_rencana,master_rencana_keperawatan_igd.rencana_keperawatan from master_rencana_keperawatan_igd "+
                        "inner join penilaian_awal_keperawatan_ralan_rencana_igd on penilaian_awal_keperawatan_ralan_rencana_igd.kode_rencana=master_rencana_keperawatan_igd.kode_rencana "+
                        "where penilaian_awal_keperawatan_ralan_rencana_igd.no_rawat=? order by penilaian_awal_keperawatan_ralan_rencana_igd.kode_rencana");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeDetailRencana.addRow(new Object[]{rs.getString(1),rs.getString(2)});
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
            
            try {
                Valid.tabelKosong(tabModeDetailEtiologi);
                ps=koneksi.prepareStatement(
                        "select master_etiologi_keperawatan.kode_etiologi,master_etiologi_keperawatan.etiologi from master_etiologi_keperawatan "+
                        "inner join penilaian_awal_keperawatan_ralan_etiologi_igd on penilaian_awal_keperawatan_ralan_etiologi_igd.kode_etiologi=master_etiologi_keperawatan.kode_etiologi "+
                        "where penilaian_awal_keperawatan_ralan_etiologi_igd.no_rawat=? order by penilaian_awal_keperawatan_ralan_etiologi_igd.kode_etiologi");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeDetailEtiologi.addRow(new Object[]{rs.getString(1),rs.getString(2)});
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
            
            try {
                Valid.tabelKosong(tabModeDetailLuaran);
                ps=koneksi.prepareStatement(
                        "select master_luaran_keperawatan.kode_luaran,master_luaran_keperawatan.luaran from master_luaran_keperawatan "+
                        "inner join penilaian_awal_keperawatan_ralan_luaran_igd on penilaian_awal_keperawatan_ralan_luaran_igd.kode_luaran=master_luaran_keperawatan.kode_luaran "+
                        "where penilaian_awal_keperawatan_ralan_luaran_igd.no_rawat=? order by penilaian_awal_keperawatan_ralan_luaran_igd.kode_luaran");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabModeDetailLuaran.addRow(new Object[]{rs.getString(1),rs.getString(2)});
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

    private void ganti() {
        String riwayatk = "";
        String riwayati ="";
        
        
        
        
        //        Nilai Riwayat Kelahiran
//        if (spontan.isSelected()) {
//            riwayatk = "Spontan";
//        } else if (tindakan.isSelected()) {
//            riwayatk = "Tindakan,Sebutkan";
//        }
        
          //        Nilai Riwayat Imunisasi
//        if (lengkap.isSelected()) {
//            riwayati = "Lengkap";
//        } else if (tdklengkap.isSelected()) {
//            riwayati = "Tidak Lengkap";
//        }
        
        
        
        
        
        
        
        
        
        if(Sequel.mengedittf("penilaian_awal_keperawatan_igd","no_rawat=?","no_rawat=?,tanggal=?,keluhan_utama=?,rpd=?,rpo=?,status_kehamilan=?,gravida=?,"+
            "para=?,abortus=?,hpht=?,tekanan=?,pupil=?,neurosensorik=?,integumen=?,turgor=?,edema=?,mukosa=?,perdarahan=?,jumlah_perdarahan=?,warna_perdarahan=?,"+
            "intoksikasi=?,bab=?,xbab=?,kbab=?,wbab=?,bak=?,xbak=?,wbak=?,lbak=?,psikologis=?,jiwa=?,perilaku=?,dilaporkan=?,sebutkan=?,hubungan=?,tinggal_dengan=?,"+
            "ket_tinggal=?,budaya=?,ket_budaya=?,pendidikan_pj=?,ket_pendidikan_pj=?,edukasi=?,ket_edukasi=?,kemampuan=?,aktifitas=?,alat_bantu=?,ket_bantu=?,"
                + ""
                + "beratbadan=?,tinggibadan=?,riwayatk=?,ket_riwayatk=?,riwayati=?,ket_riwayati=?,ket_riwayati1=?,nyeri=?,"+
            "provokes=?,ket_provokes=?,quality=?,ket_quality=?,lokasi=?,menyebar=?,skala_nyeri=?,durasi=?,nyeri_hilang=?,ket_nyeri=?,pada_dokter=?,ket_dokter=?,"+
            "berjalan_a=?,berjalan_b=?,berjalan_c=?,hasil=?,lapor=?,ket_lapor=?,rencana=?,nip=?,informasi=?",77,new String[]{
            TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),JenisPenyakit.getText(),
//            Gravida.getText(),Para.getText(),Abortus.getText(),HPHT.getText(),Tekanan.getSelectedItem().toString(),Pupil.getSelectedItem().toString(),Neurosensorik.getSelectedItem().toString(),Integumen.getSelectedItem().toString(),
//            Turgor.getSelectedItem().toString(),Edema.getSelectedItem().toString(),Mukosa.getSelectedItem().toString(),Perdarahan.getSelectedItem().toString(),SkorPendidikan.getText(),
//            WarnaPerdarahan.getText(),Intoksikasi.getSelectedItem().toString(),TahunRawat.getText(),XBAB.getText(),KBAB.getText(),WBAB.getText(),BAK.getText(),XBAK.getText(),WBAK.getText(),LBAK.getText(),Psikologis.getSelectedItem().toString(),
            PolaKerja.getSelectedItem().toString(),
//            DukunganHidup.getSelectedItem().toString(),
//            ADL.getSelectedItem().toString(),
//            Aktifitas.getSelectedItem().toString(),AlatBantu.getSelectedItem().toString(),KetAlatBantu.getText(),bb.getText(),tb.getText(),
            
            riwayatk,riwayati, 
//            ket_riwayati.getText(),ket_riwayati1.getText(),
            
//            Nyeri.getSelectedItem().toString(),Provokes.getSelectedItem().toString(),KetProvokes.getText(),Quality.getSelectedItem().toString(),
//            
//            KetQuality.getText(),Lokasi.getText(),Menyebar.getSelectedItem().toString(),SkalaNyeri.getSelectedItem().toString(),Durasi.getText(),NyeriHilang.getSelectedItem().toString(),KetNyeri.getText(),PadaDokter.getSelectedItem().toString(),
//            KetDokter.getText(),ATS.getSelectedItem().toString(),BJM.getSelectedItem().toString(),MSA.getSelectedItem().toString(),Hasil.getSelectedItem().toString(),Lapor.getSelectedItem().toString(),KetLapor.getText(),Rencana.getText(),
            KdPetugas.getText(),Informasi.getSelectedItem().toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
             })==true){
                Sequel.meghapus("penilaian_awal_keperawatan_igd_masalah","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                    if(tbMasalahKeperawatan.getValueAt(i,0).toString().equals("true")){
                        Sequel.menyimpan2("penilaian_awal_keperawatan_igd_masalah","?,?",2,new String[]{TNoRw.getText(),tbMasalahKeperawatan.getValueAt(i,1).toString()});
                    }
                }
                Sequel.meghapus("penilaian_awal_keperawatan_ralan_rencana_igd","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                    if(tbRencanaKeperawatan.getValueAt(i,0).toString().equals("true")){
                        Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_rencana_igd","?,?",2,new String[]{TNoRw.getText(),tbRencanaKeperawatan.getValueAt(i,1).toString()});
                    }
                }
                Sequel.meghapus("penilaian_awal_keperawatan_ralan_etiologi_igd","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                for (i = 0; i < tbRencanaEtiologi.getRowCount(); i++) {
                    if(tbRencanaEtiologi.getValueAt(i,0).toString().equals("true")){
                        Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_etiologi_igd","?,?",2,new String[]{TNoRw.getText(),tbRencanaEtiologi.getValueAt(i,1).toString()});
                    }
                }
                Sequel.meghapus("penilaian_awal_keperawatan_ralan_luaran_igd","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                for (i = 0; i < tbRencanaLuaran.getRowCount(); i++) {
                    if(tbRencanaLuaran.getValueAt(i,0).toString().equals("true")){
                        Sequel.menyimpan2("penilaian_awal_keperawatan_ralan_luaran_igd","?,?",2,new String[]{TNoRw.getText(),tbRencanaLuaran.getValueAt(i,1).toString()});
                    }
                }
                
                getMasalah();
                tampil();
                DetailRencana.setText(Rencana.getText());
                emptTeks();
                TabRawat.setSelectedIndex(1);
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_awal_keperawatan_igd where no_rawat=?",1,new String[]{
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("penilaian_awal_keperawatan_igd_masalah","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            Sequel.meghapus("penilaian_awal_keperawatan_ralan_rencana_igd","no_rawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            Valid.tabelKosong(tabModeDetailMasalah);
            Valid.tabelKosong(tabModeDetailRencana);
            Valid.tabelKosong(tabModeDetailEtiologi);
            Valid.tabelKosong(tabModeDetailLuaran);
            ChkAccor.setSelected(false);
            isMenu();
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
}
