/*
 * Kontribusi dari khaalf AMINO SMG
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
public final class RMPenilaianGejalaEkstrapiramidal extends javax.swing.JDialog {
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
    public RMPenilaianGejalaEkstrapiramidal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Anamnesis","Hubungan",
            
            "Perlambatan/kelambatan","Sulit berjalan dan menjaga keseimbangan","Sulit menelan dan bicara","Kekakuan","Kram pada anggota gerak","Gelisah/nervous",
            "Tremor","Krisis okulogirik","Banyak ludah","Diskinesia Anggota Gerak","Diskinesia Lidah","Pusing pada saat berdiri",
                    
                //II.
                "Muka Topeng","Skor Muka Topeng",
                "Bradikinesia","Skor Bradikinesia",

                "Rigiditas Ekstrem Kanan Atas","Skor Ekstrem Kanan Atas","Rigiditas Ekstrem Kiri Atas","Skor Ekstrem Kiri Atas","Rigiditas Ekstrem Kanan Bawah","Skor Ekstrem Kanan Bawah","Rigiditas Ekstrem Kiri Bawah","Skor Ekstrem Kanan Bawah","Skor Total Rigiditas",
                "Gaya berjalan","Skor Gaya Jalan",

                "Tremor Lengan Kanan","Rasio Tremor Lengan kanan","Skor Tremor Lengan Kanan","Tremor lengkan kiri","Rasio Tremor lengkan kiri","Skor Tremor lengkan kiri","Tremor Kaki kanan","Rasio Tremor Kaki Kanan","Skor Tremor Kaki Kanan","Tremor Kaki Kiri","Rasio Tremor Kaki kiri","Skor Tremor Kaki Kiri",
                "Tremor Kepala","Rasio Tremor Kepala","Skor Tremor Kepala","Tremor Rahang","Rasio Tremor Rahang","Skor Tremor Rahang","Tremor Lidah","Rasio Tremor Lidah","Skor Tremor Lidah","Tremor Bibir","Rasio Tremor Bibir","Skor Tremor Bibir","Skor Tremor Total",

                "Akatisia","Skor Akatisia",
                "Sialarhoe","Skor Sialarhoe",
                "Stabilitas Postur","Skor Stabilitas Postur",

                //III.
                "Distonia Torsi Akut Tangan Kanan","Skor Distonia Torsi Akut Tangan Kanan","Distonia Torsi Akut Tangan Kiri","Skor Distonia Torsi Akut Tangan Kiri","Distonia Torsi Akut Kaki Kanan","Skor Distonia Torsi Akut Kaki Kanan","Distonia Torsi Akut Kaki Kiri","Skor Distonia Torsi Akut Kaki Kiri","Distonia Torsi Akut Kepala","Skor Distonia Torsi Akut Kepala",
                "Distonia Torsi Akut Rahang","Skor Distonia Torsi Akut Rahang","Distonia Torsi Akut Lidah","Skor Distonia Torsi Akut Lidah","Distonia Torsi Akut Bibir","Skor Distonia Torsi Akut Bibir","Distonia Torsi Akut Trunk","Skor Distonia Torsi Akut Trunk","Total Skor Distonia Torsi Akut",
                
                "Distonia Tardif non-akut Tangan Kanan","Skor Distonia Tardif non-akut Tangan Kanan","Distonia Tardif non-akut Tangan Kiri","Skor Distonia Tardif non-akut Tangan Kiri","Distonia Tardif non-akut Kaki Kanan","Skor Distonia Tardif non-akut Kaki Kanan","Distonia Tardif non-akut Kaki Kiri","Skor Distonia Tardif non-akut Kaki Kiri","Distonia Tardif non-akut Kepala","Skor Distonia Tardif non-akut Kepala",
                "Distonia Tardif non-akut Rahang","Skor Distonia Tardif non-akut Rahang","Distonia Tardif non-akut Lidah","Skor Distonia Tardif non-akut Lidah","Distonia Tardif non-akut Bibir","Skor Distonia Tardif non-akut Bibir","Distonia Tardif non-akut Trunk","Skor Distonia Tardif non-akut Trunk","Total Skor Distonia Tardif Non Akut",
                
                //IV.
                "Diskinetik Lidah","Rasio Diskinetik Lidah","Skor Diskinetik Lidah",
                "Diskinetik Rahang","Rasio Diskinetik Rahang","Skor Diskinetik Rahang",
                "Diskinetik Pipi dan Bibir","Rasio Diskinetik Pipi","Skor Diskinetik Pipi",
                "Diskinetik Badan","Rasio Diskinetik Badan","Skor Diskinetik Badan",
                "Diskinetik Ekstremitas Atas","Rasio Diskinetik Ekstremitas Atas","Skor Diskinetik Ekstremitas Atas",
                "Diskinetik Ekstremitas Bawah","Rasio Diskinetik Ekstremitas Bawah","Skor Diskinetik Ekstremitas Bawah",
                "Diskinetik Gerakan Involunter","Rasio Diskinetik Gerakan Involunter","Skor Diskinetik Gerakan Involunter",
//            "Keluhan Utama","Riwayat Penyakit Sekarang","Riwayat Penyakit Dahulu",
//            "Riwayat Penggunakan Obat","Riwayat Alergi","Kondisi Umum","TD(mmHg)","Nadi(x/menit)","Suhu","RR(x/menit)","Tulang Belakang","Kepala","Keterangan Kepala","Thoraks","Keterangan Thoraks",
//            "Abdomen","Keterangan Abdomen","Ekstremitas","Keterangan Ekstremitas","In.Kebersihan","In.Warna","In.Kelembaban","In.Gangguan","Lainnya","Kondisi Sosial","Status Psikologis (GDS)",
//            "Status Kognitif (MMSE)","Status Nutrisi (MNA)","Skrinning Risiko Jatuh (OMS)","Status Fungsional (ADL: BARTHEL INDEX)","Laboratorium","Radiologi","Pemeriksaan","Diagnosis",
//            "Diagnosis Banding","Permasalahan","Terapi","Tindakan","Edukasi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 49; i++) {
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
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(170);
            }else if(i==16){
                column.setPreferredWidth(60);
            }else if(i==17){
                column.setPreferredWidth(76);
            }else if(i==18){
                column.setPreferredWidth(34);
            }else if(i==19){
                column.setPreferredWidth(68);
            }else if(i==20){
                column.setPreferredWidth(88);
            }else if(i==21){
                column.setPreferredWidth(83);
            }else if(i==22){
                column.setPreferredWidth(150);
            }else if(i==23){
                column.setPreferredWidth(83);
            }else if(i==24){
                column.setPreferredWidth(150);
            }else if(i==25){
                column.setPreferredWidth(83);
            }else if(i==26){
                column.setPreferredWidth(150);
            }else if(i==27){
                column.setPreferredWidth(83);
            }else if(i==28){
                column.setPreferredWidth(150);
            }else if(i==29){
                column.setPreferredWidth(75);
            }else if(i==30){
                column.setPreferredWidth(53);
            }else if(i==31){
                column.setPreferredWidth(80);
            }else if(i==32){
                column.setPreferredWidth(71);
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
                column.setPreferredWidth(200);
            }else if(i==41){
                column.setPreferredWidth(200);
            }else if(i==42){
                column.setPreferredWidth(200);
            }else if(i==43){
                column.setPreferredWidth(150);
            }else if(i==44){
                column.setPreferredWidth(150);
            }else if(i==45){
                column.setPreferredWidth(200);
            }else if(i==46){
                column.setPreferredWidth(200);
            }else if(i==47){
                column.setPreferredWidth(200);
            }else if(i==48){
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Hubungan.setDocument(new batasInput((int)30).getKata(Hubungan));
        
//        KeluhanUtama.setDocument(new batasInput((int)2000).getKata(KeluhanUtama));
//        RPS.setDocument(new batasInput((int)2000).getKata(RPS));
//        RPD.setDocument(new batasInput((int)1000).getKata(RPD));
//        RPO.setDocument(new batasInput((int)1000).getKata(RPO));
//        Alergi.setDocument(new batasInput((int)50).getKata(Alergi));
//        KondisiUmum.setDocument(new batasInput((int)1000).getKata(KondisiUmum));
//        KondisiSosial.setDocument(new batasInput((int)500).getKata(KondisiSosial));
//        TD.setDocument(new batasInput((byte)8).getKata(TD));
//        Nadi.setDocument(new batasInput((byte)5).getKata(Nadi));
//        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
//        RR.setDocument(new batasInput((byte)5).getKata(RR));
//        KeteranganKepala.setDocument(new batasInput((byte)100).getKata(KeteranganKepala));
//        KeteranganThoraks.setDocument(new batasInput((byte)100).getKata(KeteranganThoraks));
//        KeteranganAbdomen.setDocument(new batasInput((byte)100).getKata(KeteranganAbdomen));
//        KeteranganEkstremitas.setDocument(new batasInput((byte)100).getKata(KeteranganEkstremitas));
//        Lainnya.setDocument(new batasInput((int)1000).getKata(Lainnya));
//        Lab.setDocument(new batasInput((int)1000).getKata(Lab));
//        Rad.setDocument(new batasInput((int)1000).getKata(Rad));
//        PenunjangLain.setDocument(new batasInput((int)1000).getKata(PenunjangLain));
//        Diagnosis.setDocument(new batasInput((int)500).getKata(Diagnosis));
//        Diagnosis2.setDocument(new batasInput((int)500).getKata(Diagnosis2));
//        Permasalahan.setDocument(new batasInput((int)500).getKata(Permasalahan));
//        Tindakan.setDocument(new batasInput((int)500).getKata(Tindakan));
//        Terapi.setDocument(new batasInput((int)500).getKata(Terapi));
//        Edukasi.setDocument(new batasInput((int)500).getKata(Edukasi));
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
        grpParkinson1 = new javax.swing.ButtonGroup();
        grpParkinson2 = new javax.swing.ButtonGroup();
        grpParkinson4 = new javax.swing.ButtonGroup();
        grpTremor1 = new javax.swing.ButtonGroup();
        grpTremor2 = new javax.swing.ButtonGroup();
        grpTremor3 = new javax.swing.ButtonGroup();
        grpTremor4 = new javax.swing.ButtonGroup();
        grpTremor5 = new javax.swing.ButtonGroup();
        grpTremor6 = new javax.swing.ButtonGroup();
        grpTremor7 = new javax.swing.ButtonGroup();
        grpTremor8 = new javax.swing.ButtonGroup();
        grpParkinson6 = new javax.swing.ButtonGroup();
        grpParkinson7 = new javax.swing.ButtonGroup();
        grpParkinson8 = new javax.swing.ButtonGroup();
        grpDiskinetik1 = new javax.swing.ButtonGroup();
        grpDiskinetik2 = new javax.swing.ButtonGroup();
        grpDiskinetik3 = new javax.swing.ButtonGroup();
        grpDiskinetik4 = new javax.swing.ButtonGroup();
        grpDiskinetik5 = new javax.swing.ButtonGroup();
        grpDiskinetik6 = new javax.swing.ButtonGroup();
        grpDiskinetik7 = new javax.swing.ButtonGroup();
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
        Anamnesis = new widget.ComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel38 = new widget.Label();
        Hubungan = new widget.TextBox();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel99 = new widget.Label();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jLabel95 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel94 = new widget.Label();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel101 = new widget.Label();
        jLabel42 = new widget.Label();
        SulitTugasrutin = new widget.ComboBox();
        jLabel43 = new widget.Label();
        SulitBerjalan = new widget.ComboBox();
        jLabel44 = new widget.Label();
        SulitMenelan = new widget.ComboBox();
        jLabel48 = new widget.Label();
        Kaku = new widget.ComboBox();
        jLabel50 = new widget.Label();
        Kram = new widget.ComboBox();
        jLabel62 = new widget.Label();
        EKananAtas = new widget.ComboBox();
        jLabel63 = new widget.Label();
        Tremor = new widget.ComboBox();
        jLabel64 = new widget.Label();
        Ludah = new widget.ComboBox();
        jLabel65 = new widget.Label();
        PosturAbnormal = new widget.ComboBox();
        DiskinesiaGerak = new widget.ComboBox();
        jLabel67 = new widget.Label();
        Pusing = new widget.ComboBox();
        jLabel68 = new widget.Label();
        DiskinesiaLidah = new widget.ComboBox();
        jLabel69 = new widget.Label();
        jLabel66 = new widget.Label();
        GPenurunan1 = new widget.RadioButton();
        GNormal = new widget.RadioButton();
        GSenyum = new widget.RadioButton();
        GPenurunan2 = new widget.RadioButton();
        GMuka1 = new widget.RadioButton();
        GSenyum2 = new widget.RadioButton();
        GMuka2 = new widget.RadioButton();
        jLabel70 = new widget.Label();
        SkorGerakan = new widget.TextBox();
        jLabel71 = new widget.Label();
        BLambat1 = new widget.RadioButton();
        BNormal = new widget.RadioButton();
        BSulit1 = new widget.RadioButton();
        BLambat2 = new widget.RadioButton();
        BSulit3 = new widget.RadioButton();
        BSulit2 = new widget.RadioButton();
        BJarang = new widget.RadioButton();
        jLabel72 = new widget.Label();
        SkorBradi = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        Gelisah = new widget.ComboBox();
        EKiriAtas = new widget.ComboBox();
        jLabel75 = new widget.Label();
        EKananBawah = new widget.ComboBox();
        jLabel76 = new widget.Label();
        EKiriBawah = new widget.ComboBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        SkorKiriAtas = new widget.TextBox();
        SkorRigid = new widget.TextBox();
        SkorKananAtas = new widget.TextBox();
        SkorKiriBawah = new widget.TextBox();
        SkorKananBawah = new widget.TextBox();
        jLabel79 = new widget.Label();
        JTurun = new widget.RadioButton();
        JNormal = new widget.RadioButton();
        JAyunan = new widget.RadioButton();
        JTurun2 = new widget.RadioButton();
        JNyata = new widget.RadioButton();
        JKaku = new widget.RadioButton();
        JFlexi = new widget.RadioButton();
        SkorJalan = new widget.TextBox();
        jLabel83 = new widget.Label();
        TLengki = new widget.ComboBox();
        jLabel84 = new widget.Label();
        jLabel86 = new widget.Label();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        SkorLengki = new widget.TextBox();
        SkorTremor = new widget.TextBox();
        SkorLengka = new widget.TextBox();
        SkorKaki = new widget.TextBox();
        SkorKaka = new widget.TextBox();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel96 = new widget.Label();
        TLengka = new widget.ComboBox();
        TKaki = new widget.ComboBox();
        TKaka = new widget.ComboBox();
        TPipi = new widget.ComboBox();
        TKepala = new widget.ComboBox();
        TBibir = new widget.ComboBox();
        TLidah = new widget.ComboBox();
        SekaliLengka = new widget.RadioButton();
        SeringLengka = new widget.RadioButton();
        SelaluLengka = new widget.RadioButton();
        SekaliLengki = new widget.RadioButton();
        SeringLengki = new widget.RadioButton();
        SelaluLengki = new widget.RadioButton();
        SekaliKaka = new widget.RadioButton();
        SeringKaka = new widget.RadioButton();
        SelaluKaka = new widget.RadioButton();
        SekaliKaki = new widget.RadioButton();
        SeringKaki = new widget.RadioButton();
        SelaluKaki = new widget.RadioButton();
        SekaliKepala = new widget.RadioButton();
        SeringKepala = new widget.RadioButton();
        SelaluKepala = new widget.RadioButton();
        SekaliPipi = new widget.RadioButton();
        SeringPipi = new widget.RadioButton();
        SelaluPipi = new widget.RadioButton();
        SekaliLidah = new widget.RadioButton();
        SeringLidah = new widget.RadioButton();
        SelaluLidah = new widget.RadioButton();
        SekaliBibir = new widget.RadioButton();
        SeringBibir = new widget.RadioButton();
        SelaluBibir = new widget.RadioButton();
        SkorPipi = new widget.TextBox();
        SkorKepala = new widget.TextBox();
        SkorBibir = new widget.TextBox();
        SkorLidah = new widget.TextBox();
        jLabel97 = new widget.Label();
        AGelisah = new widget.RadioButton();
        ATidakada = new widget.RadioButton();
        AGerak = new widget.RadioButton();
        AIngin = new widget.RadioButton();
        ADuduk = new widget.RadioButton();
        AHentak = new widget.RadioButton();
        ATerus = new widget.RadioButton();
        SkorAkatisia = new widget.TextBox();
        jLabel98 = new widget.Label();
        jLabel100 = new widget.Label();
        SgtRingan = new widget.RadioButton();
        STidakada = new widget.RadioButton();
        Ssedang = new widget.RadioButton();
        SRingan = new widget.RadioButton();
        SBerat = new widget.RadioButton();
        SAgakberat = new widget.RadioButton();
        SSangatberat = new widget.RadioButton();
        SkorSialorhoe = new widget.TextBox();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        StabLambat = new widget.RadioButton();
        StabTidakada = new widget.RadioButton();
        StabRetro1 = new widget.RadioButton();
        StabRetro = new widget.RadioButton();
        StabStabil = new widget.RadioButton();
        StabRespon = new widget.RadioButton();
        StabMampu = new widget.RadioButton();
        SkorStabilitas = new widget.TextBox();
        jLabel104 = new widget.Label();
        DistoniaTangka = new widget.ComboBox();
        jLabel111 = new widget.Label();
        jLabel113 = new widget.Label();
        jLabel117 = new widget.Label();
        SkorDistonia = new widget.TextBox();
        SkorDTangka = new widget.TextBox();
        DistoniaTangki = new widget.ComboBox();
        jLabel114 = new widget.Label();
        SkorDTangki = new widget.TextBox();
        DistoniaKaka = new widget.ComboBox();
        jLabel115 = new widget.Label();
        SkorDKaka = new widget.TextBox();
        DistoniaKaki = new widget.ComboBox();
        jLabel116 = new widget.Label();
        SkorDKaki = new widget.TextBox();
        DistoniaRahang = new widget.ComboBox();
        jLabel118 = new widget.Label();
        SkorDRahang = new widget.TextBox();
        DistoniaLidah = new widget.ComboBox();
        jLabel119 = new widget.Label();
        SkorDLidah = new widget.TextBox();
        DistoniaBibir = new widget.ComboBox();
        jLabel120 = new widget.Label();
        SkorDBibir = new widget.TextBox();
        DistoniaTrunk = new widget.ComboBox();
        jLabel121 = new widget.Label();
        SkorDTrunk = new widget.TextBox();
        DistoniaKepala = new widget.ComboBox();
        jLabel122 = new widget.Label();
        SkorDKepala = new widget.TextBox();
        TardifTangka = new widget.ComboBox();
        jLabel123 = new widget.Label();
        jLabel124 = new widget.Label();
        jLabel125 = new widget.Label();
        SkorTardif = new widget.TextBox();
        SkorTTangka = new widget.TextBox();
        TardifTangki = new widget.ComboBox();
        jLabel126 = new widget.Label();
        SkorTTangki = new widget.TextBox();
        TardifKaka = new widget.ComboBox();
        jLabel127 = new widget.Label();
        SkorTKaka = new widget.TextBox();
        TardifKaki = new widget.ComboBox();
        jLabel128 = new widget.Label();
        SkorTKaki = new widget.TextBox();
        TardifRahang = new widget.ComboBox();
        jLabel129 = new widget.Label();
        SkorTRahang = new widget.TextBox();
        TardifLidah = new widget.ComboBox();
        jLabel130 = new widget.Label();
        SkorTLidah = new widget.TextBox();
        TardifBibir = new widget.ComboBox();
        jLabel131 = new widget.Label();
        SkorTBibir = new widget.TextBox();
        TardifTrunk = new widget.ComboBox();
        jLabel132 = new widget.Label();
        SkorTTrunk = new widget.TextBox();
        TardifKepala = new widget.ComboBox();
        jLabel133 = new widget.Label();
        Skordiskilid = new widget.TextBox();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        jLabel141 = new widget.Label();
        jLabel142 = new widget.Label();
        jLabel143 = new widget.Label();
        DiskiLidah = new widget.ComboBox();
        radJarang1 = new widget.RadioButton();
        radSering1 = new widget.RadioButton();
        radSelalu1 = new widget.RadioButton();
        SkorTKepala = new widget.TextBox();
        jLabel105 = new widget.Label();
        Skordiskirah = new widget.TextBox();
        DiskiRahang = new widget.ComboBox();
        radJarang2 = new widget.RadioButton();
        radSering2 = new widget.RadioButton();
        radSelalu2 = new widget.RadioButton();
        jLabel144 = new widget.Label();
        Skordiskipi = new widget.TextBox();
        DiskiBibir = new widget.ComboBox();
        radJarang3 = new widget.RadioButton();
        radSering3 = new widget.RadioButton();
        radSelalu3 = new widget.RadioButton();
        jLabel145 = new widget.Label();
        Skordiskidan = new widget.TextBox();
        DiskiBadan = new widget.ComboBox();
        radJarang4 = new widget.RadioButton();
        radSering4 = new widget.RadioButton();
        radSelalu4 = new widget.RadioButton();
        Skordiskitas = new widget.TextBox();
        DiskiEkstrematas = new widget.ComboBox();
        radSering5 = new widget.RadioButton();
        radSelalu5 = new widget.RadioButton();
        jLabel146 = new widget.Label();
        radJarang5 = new widget.RadioButton();
        DiskiEkstrembawah = new widget.ComboBox();
        radSering6 = new widget.RadioButton();
        Skordiskiwah = new widget.TextBox();
        jLabel147 = new widget.Label();
        radJarang6 = new widget.RadioButton();
        radSelalu6 = new widget.RadioButton();
        Skordiskivol = new widget.TextBox();
        jLabel148 = new widget.Label();
        DiskiInvolunter = new widget.ComboBox();
        radSering7 = new widget.RadioButton();
        radJarang7 = new widget.RadioButton();
        radSelalu7 = new widget.RadioButton();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Gejala Ekstrapiramidal ESRS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2600));
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
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
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

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(644, 40, 128, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jLabel38.setText("Anamnesis :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(570, 40, 70, 23);

        Hubungan.setName("Hubungan"); // NOI18N
        Hubungan.setPreferredSize(new java.awt.Dimension(207, 23));
        Hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeyPressed(evt);
            }
        });
        FormInput.add(Hubungan);
        Hubungan.setBounds(774, 40, 80, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 360, 880, 1);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("I. GEJALA PARKINSON, DISTONIA, DAN DISKINESIA : Kuesioner");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 70, 320, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(380, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-09-2023 12:37:31" }));
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

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("II. GEJALA PARKINSON: Pemeriksaan Dokter");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(10, 360, 220, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 1810, 880, 1);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("III. DISTONIA : Pemeriksaan Dokter");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 1810, 180, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 2200, 880, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("1. Gerakan Lidah (ke samping atau memutar)");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(60, 2230, 220, 20);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Skor :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(80, 500, 30, 20);

        SulitTugasrutin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        SulitTugasrutin.setName("SulitTugasrutin"); // NOI18N
        SulitTugasrutin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SulitTugasrutinActionPerformed(evt);
            }
        });
        SulitTugasrutin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SulitTugasrutinKeyPressed(evt);
            }
        });
        FormInput.add(SulitTugasrutin);
        SulitTugasrutin.setBounds(320, 110, 138, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("<html>Kesulitan dalam berjalan  <br>dan menjaga keseimbangan. </html>");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(50, 150, 250, 30);

        SulitBerjalan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        SulitBerjalan.setName("SulitBerjalan"); // NOI18N
        SulitBerjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SulitBerjalanKeyPressed(evt);
            }
        });
        FormInput.add(SulitBerjalan);
        SulitBerjalan.setBounds(320, 150, 138, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("Kesulitan dalam menelan atau berbicara");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(50, 190, 250, 20);

        SulitMenelan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        SulitMenelan.setName("SulitMenelan"); // NOI18N
        SulitMenelan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SulitMenelanKeyPressed(evt);
            }
        });
        FormInput.add(SulitMenelan);
        SulitMenelan.setBounds(320, 190, 138, 23);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Kekakuan, postur tubuh kaku.");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(50, 230, 250, 20);

        Kaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        Kaku.setName("Kaku"); // NOI18N
        Kaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KakuKeyPressed(evt);
            }
        });
        FormInput.add(Kaku);
        Kaku.setBounds(320, 230, 138, 23);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("<html> Kram atau nyeri pada anggota gerak,  <br>tulang belakang dan atau leher. </html>");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(50, 260, 250, 40);

        Kram.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        Kram.setName("Kram"); // NOI18N
        Kram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KramKeyPressed(evt);
            }
        });
        FormInput.add(Kram);
        Kram.setBounds(320, 270, 138, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Gelisah, nervous, tidak bisa diam.");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(50, 310, 250, 30);

        EKananAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tonus otot normal", "Sangat ringan", "Ringan", "Sedang", "Agak berat ", "Berat", "Sangat Berat" }));
        EKananAtas.setName("EKananAtas"); // NOI18N
        EKananAtas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EKananAtasItemStateChanged(evt);
            }
        });
        EKananAtas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EKananAtasActionPerformed(evt);
            }
        });
        EKananAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKananAtasKeyPressed(evt);
            }
        });
        FormInput.add(EKananAtas);
        EKananAtas.setBounds(260, 720, 138, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Tremor, gemetar");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(480, 110, 250, 20);

        Tremor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        Tremor.setName("Tremor"); // NOI18N
        Tremor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TremorKeyPressed(evt);
            }
        });
        FormInput.add(Tremor);
        Tremor.setBounds(750, 110, 138, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("Banyak ludah");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(480, 190, 250, 20);

        Ludah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        Ludah.setName("Ludah"); // NOI18N
        Ludah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LudahKeyPressed(evt);
            }
        });
        FormInput.add(Ludah);
        Ludah.setBounds(750, 190, 138, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("<html> Krisis okulogirik atau postur tubuh <br>yang abnormal yang dipertahankan. </html>");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(480, 140, 250, 40);

        PosturAbnormal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        PosturAbnormal.setName("PosturAbnormal"); // NOI18N
        PosturAbnormal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PosturAbnormalKeyPressed(evt);
            }
        });
        FormInput.add(PosturAbnormal);
        PosturAbnormal.setBounds(750, 150, 138, 23);

        DiskinesiaGerak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        DiskinesiaGerak.setName("DiskinesiaGerak"); // NOI18N
        DiskinesiaGerak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiskinesiaGerakKeyPressed(evt);
            }
        });
        FormInput.add(DiskinesiaGerak);
        DiskinesiaGerak.setBounds(750, 230, 138, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Pusing pada saat berdiri (khususnya pada pagi hari)");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(480, 310, 250, 20);

        Pusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        Pusing.setName("Pusing"); // NOI18N
        Pusing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PusingKeyPressed(evt);
            }
        });
        FormInput.add(Pusing);
        Pusing.setBounds(750, 310, 138, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("<html> Gerakan-gerakan involunter yang <br>abnormal (diskinesia) dari lidah, rahang, bibir, atau muka. </html>");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(480, 260, 250, 40);

        DiskinesiaLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        DiskinesiaLidah.setName("DiskinesiaLidah"); // NOI18N
        DiskinesiaLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiskinesiaLidahKeyPressed(evt);
            }
        });
        FormInput.add(DiskinesiaLidah);
        DiskinesiaLidah.setBounds(750, 270, 138, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("<html> Gerakan-gerakan involunter yang <br>abnormal (diskinesia) dari anggota gerak badan. </html>");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(480, 220, 250, 40);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("<html> Perlambatan atau kelamahan yang nyata,  <br>ada kesan kesulitan dalam menjalankan tugas rutin. </html>");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(50, 100, 250, 40);

        grpParkinson1.add(GPenurunan1);
        GPenurunan1.setText("Penurunan yang sangat ringan dari ekspresi muka");
        GPenurunan1.setName("GPenurunan1"); // NOI18N
        GPenurunan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GPenurunan1ActionPerformed(evt);
            }
        });
        FormInput.add(GPenurunan1);
        GPenurunan1.setBounds(70, 420, 280, 17);

        grpParkinson1.add(GNormal);
        GNormal.setText("Normal");
        GNormal.setName("GNormal"); // NOI18N
        GNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GNormalActionPerformed(evt);
            }
        });
        FormInput.add(GNormal);
        GNormal.setBounds(70, 400, 54, 17);

        grpParkinson1.add(GSenyum);
        GSenyum.setText("<html>Senyum spontan jarang, berkedip kurang, suara agak monoton</html>");
        GSenyum.setName("GSenyum"); // NOI18N
        GSenyum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GSenyumActionPerformed(evt);
            }
        });
        FormInput.add(GSenyum);
        GSenyum.setBounds(70, 460, 220, 30);

        grpParkinson1.add(GPenurunan2);
        GPenurunan2.setText("Penurunan ringan dari ekspresi muka");
        GPenurunan2.setName("GPenurunan2"); // NOI18N
        GPenurunan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GPenurunan2ActionPerformed(evt);
            }
        });
        FormInput.add(GPenurunan2);
        GPenurunan2.setBounds(70, 440, 197, 20);

        grpParkinson1.add(GMuka1);
        GMuka1.setText("Muka topeng yang nyata, tidak mempu mengerutkan dahi, bicara pelo");
        GMuka1.setName("GMuka1"); // NOI18N
        GMuka1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GMuka1ActionPerformed(evt);
            }
        });
        FormInput.add(GMuka1);
        GMuka1.setBounds(410, 440, 370, 17);

        grpParkinson1.add(GSenyum2);
        GSenyum2.setText("<html>Tidak ada senyum spontan, pandangan kosong, pembicaraan perlahan dan monoton, bergumam</html>");
        GSenyum2.setName("GSenyum2"); // NOI18N
        GSenyum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GSenyum2ActionPerformed(evt);
            }
        });
        FormInput.add(GSenyum2);
        GSenyum2.setBounds(410, 400, 270, 30);

        grpParkinson1.add(GMuka2);
        GMuka2.setText("<html>Muka topeng yang sangat nyata dengan pembicaran yang sulit dimengerti</html>");
        GMuka2.setName("GMuka2"); // NOI18N
        GMuka2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GMuka2ActionPerformed(evt);
            }
        });
        FormInput.add(GMuka2);
        GMuka2.setBounds(410, 460, 320, 30);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("1. Gerakan-gerakan ekspresif otomatis (muka topeng/bicara)");
        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(50, 380, 410, 20);

        SkorGerakan.setText("0");
        SkorGerakan.setName("SkorGerakan"); // NOI18N
        FormInput.add(SkorGerakan);
        SkorGerakan.setBounds(120, 500, 40, 24);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Skor :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(80, 660, 30, 20);

        grpParkinson2.add(BLambat1);
        BLambat1.setText("Kesan perlambatan yang menyeluruh dari gerakan-gerakan");
        BLambat1.setName("BLambat1"); // NOI18N
        BLambat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLambat1ActionPerformed(evt);
            }
        });
        FormInput.add(BLambat1);
        BLambat1.setBounds(70, 580, 310, 17);

        grpParkinson2.add(BNormal);
        BNormal.setText("Normal");
        BNormal.setName("BNormal"); // NOI18N
        BNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNormalActionPerformed(evt);
            }
        });
        FormInput.add(BNormal);
        BNormal.setBounds(70, 560, 54, 17);

        grpParkinson2.add(BSulit1);
        BSulit1.setText("<html>Kesulitan yang sangat ringan dalam memulai gerakan</html>");
        BSulit1.setName("BSulit1"); // NOI18N
        BSulit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSulit1ActionPerformed(evt);
            }
        });
        FormInput.add(BSulit1);
        BSulit1.setBounds(70, 620, 280, 30);

        grpParkinson2.add(BLambat2);
        BLambat2.setText("Kelambatan yang nyata dalam gerakan");
        BLambat2.setName("BLambat2"); // NOI18N
        BLambat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLambat2ActionPerformed(evt);
            }
        });
        FormInput.add(BLambat2);
        BLambat2.setBounds(70, 600, 220, 20);

        grpParkinson2.add(BSulit3);
        BSulit3.setText("<html>Kesulitan dalan memulai/menghentikan setiap gerakan atau kekakuan dalam memulai gerakan volunter</html>");
        BSulit3.setName("BSulit3"); // NOI18N
        BSulit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSulit3ActionPerformed(evt);
            }
        });
        FormInput.add(BSulit3);
        BSulit3.setBounds(410, 590, 360, 30);

        grpParkinson2.add(BSulit2);
        BSulit2.setText("<html>Kesulitan ringan sampai sedang dalam memulai gerakan</html>");
        BSulit2.setName("BSulit2"); // NOI18N
        BSulit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSulit2ActionPerformed(evt);
            }
        });
        FormInput.add(BSulit2);
        BSulit2.setBounds(410, 560, 290, 30);

        grpParkinson2.add(BJarang);
        BJarang.setText("<html>Jarang terdapat gerakan-gerakan terarah, hampir nampak tak bergerak</html>");
        BJarang.setName("BJarang"); // NOI18N
        BJarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BJarangActionPerformed(evt);
            }
        });
        FormInput.add(BJarang);
        BJarang.setBounds(410, 620, 330, 30);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Ekstrimitas kanan atas");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(90, 720, 120, 20);

        SkorBradi.setText("0");
        SkorBradi.setName("SkorBradi"); // NOI18N
        SkorBradi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorBradiActionPerformed(evt);
            }
        });
        FormInput.add(SkorBradi);
        SkorBradi.setBounds(120, 660, 40, 24);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("2. Bradikinesia");
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(50, 540, 410, 20);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("3. Rigiditas");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(50, 700, 410, 14);

        Gelisah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Ringan", "Sedang", "Berat" }));
        Gelisah.setName("Gelisah"); // NOI18N
        Gelisah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GelisahKeyPressed(evt);
            }
        });
        FormInput.add(Gelisah);
        Gelisah.setBounds(320, 310, 138, 23);

        EKiriAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tonus otot normal", "Sangat ringan", "Ringan", "Sedang", "Agak berat ", "Berat", "Sangat Berat" }));
        EKiriAtas.setName("EKiriAtas"); // NOI18N
        EKiriAtas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EKiriAtasItemStateChanged(evt);
            }
        });
        EKiriAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKiriAtasKeyPressed(evt);
            }
        });
        FormInput.add(EKiriAtas);
        EKiriAtas.setBounds(260, 750, 138, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("Ekstrimitas kiri atas");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(90, 750, 120, 20);

        EKananBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tonus otot normal", "Sangat ringan", "Ringan", "Sedang", "Agak berat ", "Berat", "Sangat Berat" }));
        EKananBawah.setName("EKananBawah"); // NOI18N
        EKananBawah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EKananBawahItemStateChanged(evt);
            }
        });
        EKananBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKananBawahKeyPressed(evt);
            }
        });
        FormInput.add(EKananBawah);
        EKananBawah.setBounds(260, 780, 138, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Ekstrimitas kanan bawah");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(90, 780, 120, 20);

        EKiriBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tonus otot normal", "Sangat ringan", "Ringan", "Sedang", "Agak berat ", "Berat", "Sangat Berat" }));
        EKiriBawah.setName("EKiriBawah"); // NOI18N
        EKiriBawah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EKiriBawahItemStateChanged(evt);
            }
        });
        EKiriBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EKiriBawahKeyPressed(evt);
            }
        });
        FormInput.add(EKiriBawah);
        EKiriBawah.setBounds(260, 810, 138, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("Ekstrimitas kiri bawah");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(90, 810, 120, 20);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Skor total :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(360, 850, 53, 20);

        SkorKiriAtas.setText("0");
        SkorKiriAtas.setName("SkorKiriAtas"); // NOI18N
        FormInput.add(SkorKiriAtas);
        SkorKiriAtas.setBounds(420, 750, 40, 24);

        SkorRigid.setText("0");
        SkorRigid.setName("SkorRigid"); // NOI18N
        SkorRigid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorRigidActionPerformed(evt);
            }
        });
        FormInput.add(SkorRigid);
        SkorRigid.setBounds(420, 850, 40, 24);

        SkorKananAtas.setText("0");
        SkorKananAtas.setName("SkorKananAtas"); // NOI18N
        FormInput.add(SkorKananAtas);
        SkorKananAtas.setBounds(420, 720, 40, 24);

        SkorKiriBawah.setText("0");
        SkorKiriBawah.setName("SkorKiriBawah"); // NOI18N
        FormInput.add(SkorKiriBawah);
        SkorKiriBawah.setBounds(420, 810, 40, 24);

        SkorKananBawah.setText("0");
        SkorKananBawah.setName("SkorKananBawah"); // NOI18N
        FormInput.add(SkorKananBawah);
        SkorKananBawah.setBounds(420, 780, 40, 24);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("Skor :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(90, 1010, 30, 20);

        grpParkinson4.add(JTurun);
        JTurun.setText("Penurunan ringan dari ayunan lengan");
        JTurun.setName("JTurun"); // NOI18N
        JTurun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTurunActionPerformed(evt);
            }
        });
        FormInput.add(JTurun);
        JTurun.setBounds(80, 930, 310, 17);

        grpParkinson4.add(JNormal);
        JNormal.setText("Normal");
        JNormal.setName("JNormal"); // NOI18N
        JNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JNormalActionPerformed(evt);
            }
        });
        FormInput.add(JNormal);
        JNormal.setBounds(80, 910, 54, 17);

        grpParkinson4.add(JAyunan);
        JAyunan.setText("<html>Tidak ada ayunan lengan, kepala fleksi, langkah kurang lebih normal</html>");
        JAyunan.setName("JAyunan"); // NOI18N
        JAyunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JAyunanActionPerformed(evt);
            }
        });
        FormInput.add(JAyunan);
        JAyunan.setBounds(80, 970, 280, 30);

        grpParkinson4.add(JTurun2);
        JTurun2.setText("Penurunan sedang dari ayunan lengan, langkah normal");
        JTurun2.setName("JTurun2"); // NOI18N
        JTurun2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTurun2ActionPerformed(evt);
            }
        });
        FormInput.add(JTurun2);
        JTurun2.setBounds(80, 950, 290, 20);

        grpParkinson4.add(JNyata);
        JNyata.setText("<html>Lebih nyata, kekakuan dalam berputar</html>");
        JNyata.setName("JNyata"); // NOI18N
        JNyata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JNyataActionPerformed(evt);
            }
        });
        FormInput.add(JNyata);
        JNyata.setBounds(420, 950, 360, 20);

        grpParkinson4.add(JKaku);
        JKaku.setText("<html>Postur tubuh kaku (leher dan punggung). melangkah dengan kaki diseret</html>");
        JKaku.setName("JKaku"); // NOI18N
        JKaku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JKakuActionPerformed(evt);
            }
        });
        FormInput.add(JKaku);
        JKaku.setBounds(420, 920, 330, 30);

        grpParkinson4.add(JFlexi);
        JFlexi.setText("<html>Triple flexi, hampir tidak mampu berjalan</html>");
        JFlexi.setName("JFlexi"); // NOI18N
        JFlexi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFlexiActionPerformed(evt);
            }
        });
        FormInput.add(JFlexi);
        JFlexi.setBounds(420, 970, 330, 20);

        SkorJalan.setText("0");
        SkorJalan.setName("SkorJalan"); // NOI18N
        SkorJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorJalanActionPerformed(evt);
            }
        });
        FormInput.add(SkorJalan);
        SkorJalan.setBounds(130, 1010, 40, 24);

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("4. Gaya berjalan dan postur tubuh");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(60, 890, 410, 20);

        TLengki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TLengki.setName("TLengki"); // NOI18N
        TLengki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TLengkiItemStateChanged(evt);
            }
        });
        TLengki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TLengkiActionPerformed(evt);
            }
        });
        TLengki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLengkiKeyPressed(evt);
            }
        });
        FormInput.add(TLengki);
        TLengki.setBounds(230, 1100, 138, 23);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Lengan kanan");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(110, 1070, 120, 20);

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("5. Tremor");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(70, 1050, 70, 14);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("Lengan kiri");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(110, 1100, 120, 20);

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("Kaki kanan");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(110, 1130, 120, 20);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("Kaki kiri");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(110, 1160, 120, 20);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("Skor total :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(610, 1320, 53, 20);

        SkorLengki.setText("0");
        SkorLengki.setName("SkorLengki"); // NOI18N
        FormInput.add(SkorLengki);
        SkorLengki.setBounds(670, 1100, 40, 24);

        SkorTremor.setText("0");
        SkorTremor.setName("SkorTremor"); // NOI18N
        SkorTremor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorTremorActionPerformed(evt);
            }
        });
        FormInput.add(SkorTremor);
        SkorTremor.setBounds(670, 1320, 40, 24);

        SkorLengka.setText("0");
        SkorLengka.setName("SkorLengka"); // NOI18N
        SkorLengka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorLengkaActionPerformed(evt);
            }
        });
        FormInput.add(SkorLengka);
        SkorLengka.setBounds(670, 1070, 40, 24);

        SkorKaki.setText("0");
        SkorKaki.setName("SkorKaki"); // NOI18N
        FormInput.add(SkorKaki);
        SkorKaki.setBounds(670, 1160, 40, 24);

        SkorKaka.setText("0");
        SkorKaka.setName("SkorKaka"); // NOI18N
        SkorKaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorKakaActionPerformed(evt);
            }
        });
        FormInput.add(SkorKaka);
        SkorKaka.setBounds(670, 1130, 40, 24);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("Kepala");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(110, 1190, 120, 20);

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("Rahang/pipi");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(110, 1220, 120, 20);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("Lidah");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(110, 1250, 120, 20);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("Bibir");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(110, 1280, 120, 20);

        TLengka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TLengka.setName("TLengka"); // NOI18N
        TLengka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TLengkaItemStateChanged(evt);
            }
        });
        TLengka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TLengkaActionPerformed(evt);
            }
        });
        TLengka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLengkaKeyPressed(evt);
            }
        });
        FormInput.add(TLengka);
        TLengka.setBounds(230, 1070, 138, 23);

        TKaki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TKaki.setName("TKaki"); // NOI18N
        TKaki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TKakiItemStateChanged(evt);
            }
        });
        TKaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKakiKeyPressed(evt);
            }
        });
        FormInput.add(TKaki);
        TKaki.setBounds(230, 1160, 138, 23);

        TKaka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TKaka.setName("TKaka"); // NOI18N
        TKaka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TKakaItemStateChanged(evt);
            }
        });
        TKaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKakaActionPerformed(evt);
            }
        });
        TKaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKakaKeyPressed(evt);
            }
        });
        FormInput.add(TKaka);
        TKaka.setBounds(230, 1130, 138, 23);

        TPipi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TPipi.setName("TPipi"); // NOI18N
        TPipi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TPipiItemStateChanged(evt);
            }
        });
        TPipi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPipiKeyPressed(evt);
            }
        });
        FormInput.add(TPipi);
        TPipi.setBounds(230, 1220, 138, 23);

        TKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TKepala.setName("TKepala"); // NOI18N
        TKepala.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TKepalaItemStateChanged(evt);
            }
        });
        TKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKepalaActionPerformed(evt);
            }
        });
        TKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKepalaKeyPressed(evt);
            }
        });
        FormInput.add(TKepala);
        TKepala.setBounds(230, 1190, 138, 23);

        TBibir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TBibir.setName("TBibir"); // NOI18N
        TBibir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TBibirItemStateChanged(evt);
            }
        });
        TBibir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBibirKeyPressed(evt);
            }
        });
        FormInput.add(TBibir);
        TBibir.setBounds(230, 1280, 138, 23);

        TLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Tidak jelas", "Amplitudo kecil", "Amplitudo sedang", "Amplitudo besar" }));
        TLidah.setName("TLidah"); // NOI18N
        TLidah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TLidahItemStateChanged(evt);
            }
        });
        TLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLidahKeyPressed(evt);
            }
        });
        FormInput.add(TLidah);
        TLidah.setBounds(230, 1250, 138, 23);

        grpTremor1.add(SekaliLengka);
        SekaliLengka.setText("Sekali-sekali");
        SekaliLengka.setEnabled(false);
        SekaliLengka.setName("SekaliLengka"); // NOI18N
        SekaliLengka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliLengkaActionPerformed(evt);
            }
        });
        FormInput.add(SekaliLengka);
        SekaliLengka.setBounds(390, 1070, 78, 17);

        grpTremor1.add(SeringLengka);
        SeringLengka.setText("sering");
        SeringLengka.setEnabled(false);
        SeringLengka.setName("SeringLengka"); // NOI18N
        SeringLengka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringLengkaActionPerformed(evt);
            }
        });
        FormInput.add(SeringLengka);
        SeringLengka.setBounds(480, 1070, 60, 17);

        grpTremor1.add(SelaluLengka);
        SelaluLengka.setText("selalu");
        SelaluLengka.setEnabled(false);
        SelaluLengka.setName("SelaluLengka"); // NOI18N
        SelaluLengka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluLengkaActionPerformed(evt);
            }
        });
        FormInput.add(SelaluLengka);
        SelaluLengka.setBounds(550, 1070, 48, 17);

        grpTremor2.add(SekaliLengki);
        SekaliLengki.setText("Sekali-sekali");
        SekaliLengki.setEnabled(false);
        SekaliLengki.setName("SekaliLengki"); // NOI18N
        SekaliLengki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliLengkiActionPerformed(evt);
            }
        });
        FormInput.add(SekaliLengki);
        SekaliLengki.setBounds(390, 1100, 78, 17);

        grpTremor2.add(SeringLengki);
        SeringLengki.setText("sering");
        SeringLengki.setEnabled(false);
        SeringLengki.setName("SeringLengki"); // NOI18N
        SeringLengki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringLengkiActionPerformed(evt);
            }
        });
        FormInput.add(SeringLengki);
        SeringLengki.setBounds(480, 1100, 60, 17);

        grpTremor2.add(SelaluLengki);
        SelaluLengki.setText("selalu");
        SelaluLengki.setEnabled(false);
        SelaluLengki.setName("SelaluLengki"); // NOI18N
        SelaluLengki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluLengkiActionPerformed(evt);
            }
        });
        FormInput.add(SelaluLengki);
        SelaluLengki.setBounds(550, 1100, 48, 17);

        grpTremor3.add(SekaliKaka);
        SekaliKaka.setText("Sekali-sekali");
        SekaliKaka.setEnabled(false);
        SekaliKaka.setName("SekaliKaka"); // NOI18N
        SekaliKaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliKakaActionPerformed(evt);
            }
        });
        FormInput.add(SekaliKaka);
        SekaliKaka.setBounds(390, 1130, 78, 17);

        grpTremor3.add(SeringKaka);
        SeringKaka.setText("sering");
        SeringKaka.setEnabled(false);
        SeringKaka.setName("SeringKaka"); // NOI18N
        SeringKaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringKakaActionPerformed(evt);
            }
        });
        FormInput.add(SeringKaka);
        SeringKaka.setBounds(480, 1130, 60, 17);

        grpTremor3.add(SelaluKaka);
        SelaluKaka.setText("selalu");
        SelaluKaka.setEnabled(false);
        SelaluKaka.setName("SelaluKaka"); // NOI18N
        SelaluKaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluKakaActionPerformed(evt);
            }
        });
        FormInput.add(SelaluKaka);
        SelaluKaka.setBounds(550, 1130, 48, 17);

        grpTremor4.add(SekaliKaki);
        SekaliKaki.setText("Sekali-sekali");
        SekaliKaki.setEnabled(false);
        SekaliKaki.setName("SekaliKaki"); // NOI18N
        SekaliKaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliKakiActionPerformed(evt);
            }
        });
        FormInput.add(SekaliKaki);
        SekaliKaki.setBounds(390, 1160, 78, 17);

        grpTremor4.add(SeringKaki);
        SeringKaki.setText("sering");
        SeringKaki.setEnabled(false);
        SeringKaki.setName("SeringKaki"); // NOI18N
        SeringKaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringKakiActionPerformed(evt);
            }
        });
        FormInput.add(SeringKaki);
        SeringKaki.setBounds(480, 1160, 60, 17);

        grpTremor4.add(SelaluKaki);
        SelaluKaki.setText("selalu");
        SelaluKaki.setEnabled(false);
        SelaluKaki.setName("SelaluKaki"); // NOI18N
        SelaluKaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluKakiActionPerformed(evt);
            }
        });
        FormInput.add(SelaluKaki);
        SelaluKaki.setBounds(550, 1160, 48, 17);

        grpTremor5.add(SekaliKepala);
        SekaliKepala.setText("Sekali-sekali");
        SekaliKepala.setEnabled(false);
        SekaliKepala.setName("SekaliKepala"); // NOI18N
        SekaliKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliKepalaActionPerformed(evt);
            }
        });
        FormInput.add(SekaliKepala);
        SekaliKepala.setBounds(390, 1190, 78, 17);

        grpTremor5.add(SeringKepala);
        SeringKepala.setText("sering");
        SeringKepala.setEnabled(false);
        SeringKepala.setName("SeringKepala"); // NOI18N
        SeringKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringKepalaActionPerformed(evt);
            }
        });
        FormInput.add(SeringKepala);
        SeringKepala.setBounds(480, 1190, 60, 17);

        grpTremor5.add(SelaluKepala);
        SelaluKepala.setText("selalu");
        SelaluKepala.setEnabled(false);
        SelaluKepala.setName("SelaluKepala"); // NOI18N
        SelaluKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluKepalaActionPerformed(evt);
            }
        });
        FormInput.add(SelaluKepala);
        SelaluKepala.setBounds(550, 1190, 48, 17);

        SekaliPipi.setBorder(null);
        grpTremor6.add(SekaliPipi);
        SekaliPipi.setText("Sekali-sekali");
        SekaliPipi.setEnabled(false);
        SekaliPipi.setName("SekaliPipi"); // NOI18N
        SekaliPipi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliPipiActionPerformed(evt);
            }
        });
        FormInput.add(SekaliPipi);
        SekaliPipi.setBounds(390, 1220, 76, 15);

        SeringPipi.setBorder(null);
        grpTremor6.add(SeringPipi);
        SeringPipi.setText("sering");
        SeringPipi.setEnabled(false);
        SeringPipi.setName("SeringPipi"); // NOI18N
        SeringPipi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringPipiActionPerformed(evt);
            }
        });
        FormInput.add(SeringPipi);
        SeringPipi.setBounds(480, 1220, 60, 15);

        SelaluPipi.setBorder(null);
        grpTremor6.add(SelaluPipi);
        SelaluPipi.setText("selalu");
        SelaluPipi.setEnabled(false);
        SelaluPipi.setName("SelaluPipi"); // NOI18N
        SelaluPipi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluPipiActionPerformed(evt);
            }
        });
        FormInput.add(SelaluPipi);
        SelaluPipi.setBounds(550, 1220, 46, 15);

        grpTremor7.add(SekaliLidah);
        SekaliLidah.setText("Sekali-sekali");
        SekaliLidah.setEnabled(false);
        SekaliLidah.setName("SekaliLidah"); // NOI18N
        SekaliLidah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliLidahActionPerformed(evt);
            }
        });
        FormInput.add(SekaliLidah);
        SekaliLidah.setBounds(390, 1250, 78, 17);

        grpTremor7.add(SeringLidah);
        SeringLidah.setText("sering");
        SeringLidah.setEnabled(false);
        SeringLidah.setName("SeringLidah"); // NOI18N
        SeringLidah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringLidahActionPerformed(evt);
            }
        });
        FormInput.add(SeringLidah);
        SeringLidah.setBounds(480, 1250, 60, 17);

        grpTremor7.add(SelaluLidah);
        SelaluLidah.setText("selalu");
        SelaluLidah.setEnabled(false);
        SelaluLidah.setName("SelaluLidah"); // NOI18N
        SelaluLidah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluLidahActionPerformed(evt);
            }
        });
        FormInput.add(SelaluLidah);
        SelaluLidah.setBounds(550, 1250, 48, 17);

        grpTremor8.add(SekaliBibir);
        SekaliBibir.setText("Sekali-sekali");
        SekaliBibir.setEnabled(false);
        SekaliBibir.setName("SekaliBibir"); // NOI18N
        SekaliBibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SekaliBibirActionPerformed(evt);
            }
        });
        FormInput.add(SekaliBibir);
        SekaliBibir.setBounds(390, 1280, 78, 17);

        grpTremor8.add(SeringBibir);
        SeringBibir.setText("sering");
        SeringBibir.setEnabled(false);
        SeringBibir.setName("SeringBibir"); // NOI18N
        SeringBibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeringBibirActionPerformed(evt);
            }
        });
        FormInput.add(SeringBibir);
        SeringBibir.setBounds(480, 1280, 60, 17);

        grpTremor8.add(SelaluBibir);
        SelaluBibir.setText("selalu");
        SelaluBibir.setEnabled(false);
        SelaluBibir.setName("SelaluBibir"); // NOI18N
        SelaluBibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelaluBibirActionPerformed(evt);
            }
        });
        FormInput.add(SelaluBibir);
        SelaluBibir.setBounds(550, 1280, 48, 17);

        SkorPipi.setText("0");
        SkorPipi.setName("SkorPipi"); // NOI18N
        SkorPipi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorPipiActionPerformed(evt);
            }
        });
        FormInput.add(SkorPipi);
        SkorPipi.setBounds(670, 1220, 40, 24);

        SkorKepala.setText("0");
        SkorKepala.setName("SkorKepala"); // NOI18N
        SkorKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorKepalaActionPerformed(evt);
            }
        });
        FormInput.add(SkorKepala);
        SkorKepala.setBounds(670, 1190, 40, 24);

        SkorBibir.setText("0");
        SkorBibir.setName("SkorBibir"); // NOI18N
        FormInput.add(SkorBibir);
        SkorBibir.setBounds(670, 1280, 40, 24);

        SkorLidah.setText("0");
        SkorLidah.setName("SkorLidah"); // NOI18N
        SkorLidah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorLidahActionPerformed(evt);
            }
        });
        FormInput.add(SkorLidah);
        SkorLidah.setBounds(670, 1250, 40, 24);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("Skor :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(100, 1480, 30, 20);

        grpParkinson6.add(AGelisah);
        AGelisah.setText("Tampak gelisah, nervous, paling tidak pada satu ektremitas");
        AGelisah.setName("AGelisah"); // NOI18N
        AGelisah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGelisahActionPerformed(evt);
            }
        });
        FormInput.add(AGelisah);
        AGelisah.setBounds(90, 1400, 310, 17);

        grpParkinson6.add(ATidakada);
        ATidakada.setText("Tidak ada");
        ATidakada.setName("ATidakada"); // NOI18N
        ATidakada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATidakadaActionPerformed(evt);
            }
        });
        FormInput.add(ATidakada);
        ATidakada.setBounds(90, 1370, 90, 17);

        grpParkinson6.add(AGerak);
        AGerak.setText("<html>Sering ingin menggerakkan satu ekstremitas atau mengubah posisi</html>");
        AGerak.setName("AGerak"); // NOI18N
        AGerak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGerakActionPerformed(evt);
            }
        });
        FormInput.add(AGerak);
        AGerak.setBounds(90, 1440, 280, 30);

        grpParkinson6.add(AIngin);
        AIngin.setText("<html>Ingin selalu bergerak, paling tidak pada satu ekstremitas<html>");
        AIngin.setName("AIngin"); // NOI18N
        AIngin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AInginActionPerformed(evt);
            }
        });
        FormInput.add(AIngin);
        AIngin.setBounds(90, 1420, 320, 20);

        grpParkinson6.add(ADuduk);
        ADuduk.setText("<html>Hanya mampu untuk tetap duduk dalam jangka waktu yang pendek</html>");
        ADuduk.setName("ADuduk"); // NOI18N
        ADuduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADudukActionPerformed(evt);
            }
        });
        FormInput.add(ADuduk);
        ADuduk.setBounds(430, 1420, 360, 30);

        grpParkinson6.add(AHentak);
        AHentak.setText("<html>Menggerakkan satu ekstremitas hampir terus menerus pada saat duduk atau menghentak-hentakkan kaki ketika berdiri</html>");
        AHentak.setName("AHentak"); // NOI18N
        AHentak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AHentakActionPerformed(evt);
            }
        });
        FormInput.add(AHentak);
        AHentak.setBounds(430, 1390, 330, 30);

        grpParkinson6.add(ATerus);
        ATerus.setText("<html>Bergerak atau berjalan terus-menerus</html>");
        ATerus.setName("ATerus"); // NOI18N
        ATerus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATerusActionPerformed(evt);
            }
        });
        FormInput.add(ATerus);
        ATerus.setBounds(430, 1450, 330, 20);

        SkorAkatisia.setText("0");
        SkorAkatisia.setName("SkorAkatisia"); // NOI18N
        SkorAkatisia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorAkatisiaActionPerformed(evt);
            }
        });
        FormInput.add(SkorAkatisia);
        SkorAkatisia.setBounds(140, 1480, 40, 24);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("6. Akatisia");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(70, 1350, 60, 20);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Skor :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(100, 1620, 30, 20);

        grpParkinson7.add(SgtRingan);
        SgtRingan.setText("Sangat ringan");
        SgtRingan.setName("SgtRingan"); // NOI18N
        SgtRingan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SgtRinganActionPerformed(evt);
            }
        });
        FormInput.add(SgtRingan);
        SgtRingan.setBounds(90, 1550, 310, 17);

        grpParkinson7.add(STidakada);
        STidakada.setText("Tidak ada");
        STidakada.setName("STidakada"); // NOI18N
        STidakada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                STidakadaActionPerformed(evt);
            }
        });
        FormInput.add(STidakada);
        STidakada.setBounds(90, 1530, 90, 17);

        grpParkinson7.add(Ssedang);
        Ssedang.setText("Sedang, mengganggu pembicaraan");
        Ssedang.setName("Ssedang"); // NOI18N
        Ssedang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SsedangActionPerformed(evt);
            }
        });
        FormInput.add(Ssedang);
        Ssedang.setBounds(90, 1590, 280, 20);

        grpParkinson7.add(SRingan);
        SRingan.setText("Ringan");
        SRingan.setName("SRingan"); // NOI18N
        SRingan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SRinganActionPerformed(evt);
            }
        });
        FormInput.add(SRingan);
        SRingan.setBounds(90, 1570, 320, 17);

        grpParkinson7.add(SBerat);
        SBerat.setText("Berat");
        SBerat.setName("SBerat"); // NOI18N
        SBerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SBeratActionPerformed(evt);
            }
        });
        FormInput.add(SBerat);
        SBerat.setBounds(430, 1570, 360, 20);

        grpParkinson7.add(SAgakberat);
        SAgakberat.setText("Agak berat");
        SAgakberat.setName("SAgakberat"); // NOI18N
        SAgakberat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAgakberatActionPerformed(evt);
            }
        });
        FormInput.add(SAgakberat);
        SAgakberat.setBounds(430, 1550, 330, 20);

        grpParkinson7.add(SSangatberat);
        SSangatberat.setText("Sangat berat, \"drooling\"");
        SSangatberat.setName("SSangatberat"); // NOI18N
        SSangatberat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SSangatberatActionPerformed(evt);
            }
        });
        FormInput.add(SSangatberat);
        SSangatberat.setBounds(430, 1590, 330, 20);

        SkorSialorhoe.setText("0");
        SkorSialorhoe.setName("SkorSialorhoe"); // NOI18N
        SkorSialorhoe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorSialorhoeActionPerformed(evt);
            }
        });
        FormInput.add(SkorSialorhoe);
        SkorSialorhoe.setBounds(140, 1620, 40, 24);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("7. Sialorhoe");
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(70, 1510, 210, 20);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("Skor :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(100, 1770, 30, 20);

        grpParkinson8.add(StabLambat);
        StabLambat.setText("Kelambatan ketika didorong, tetapi tanpa retropulsi");
        StabLambat.setName("StabLambat"); // NOI18N
        StabLambat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabLambatActionPerformed(evt);
            }
        });
        FormInput.add(StabLambat);
        StabLambat.setBounds(90, 1700, 310, 17);

        grpParkinson8.add(StabTidakada);
        StabTidakada.setText("Normal");
        StabTidakada.setName("StabTidakada"); // NOI18N
        StabTidakada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabTidakadaActionPerformed(evt);
            }
        });
        FormInput.add(StabTidakada);
        StabTidakada.setBounds(90, 1680, 90, 17);

        grpParkinson8.add(StabRetro1);
        StabRetro1.setText("Retropulsi yang lebih nyata, tetapi tidak terjatuh");
        StabRetro1.setName("StabRetro1"); // NOI18N
        StabRetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabRetro1ActionPerformed(evt);
            }
        });
        FormInput.add(StabRetro1);
        StabRetro1.setBounds(90, 1740, 280, 20);

        grpParkinson8.add(StabRetro);
        StabRetro.setText("Retropulsi, tetapi segera pulih tanpa bantuan");
        StabRetro.setName("StabRetro"); // NOI18N
        StabRetro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabRetroActionPerformed(evt);
            }
        });
        FormInput.add(StabRetro);
        StabRetro.setBounds(90, 1720, 320, 17);

        grpParkinson8.add(StabStabil);
        StabStabil.setText("Berdiri tidak stabil, tanpa didorong sekalipun");
        StabStabil.setName("StabStabil"); // NOI18N
        StabStabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabStabilActionPerformed(evt);
            }
        });
        FormInput.add(StabStabil);
        StabStabil.setBounds(430, 1720, 360, 20);

        grpParkinson8.add(StabRespon);
        StabRespon.setText("Tidak ada respon postural, akan terjatuh jika tidak ditahan oleh pemeriksa");
        StabRespon.setName("StabRespon"); // NOI18N
        StabRespon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabResponActionPerformed(evt);
            }
        });
        FormInput.add(StabRespon);
        StabRespon.setBounds(430, 1700, 380, 20);

        grpParkinson8.add(StabMampu);
        StabMampu.setText("Tidak mampu berdiri tanpa bantuan");
        StabMampu.setName("StabMampu"); // NOI18N
        StabMampu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StabMampuActionPerformed(evt);
            }
        });
        FormInput.add(StabMampu);
        StabMampu.setBounds(430, 1740, 330, 20);

        SkorStabilitas.setText("0");
        SkorStabilitas.setName("SkorStabilitas"); // NOI18N
        SkorStabilitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorStabilitasActionPerformed(evt);
            }
        });
        FormInput.add(SkorStabilitas);
        SkorStabilitas.setBounds(140, 1770, 40, 24);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("8. Stabilitas postur tubuh");
        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(70, 1660, 210, 20);

        DistoniaTangka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaTangka.setName("DistoniaTangka"); // NOI18N
        DistoniaTangka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaTangkaItemStateChanged(evt);
            }
        });
        DistoniaTangka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaTangkaKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaTangka);
        DistoniaTangka.setBounds(220, 1860, 138, 23);

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Tangan kanan");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(80, 1860, 120, 20);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("1. Distonia torsi Akut");
        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(60, 1840, 410, 14);

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("Skor total :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(710, 1980, 53, 20);

        SkorDistonia.setText("0");
        SkorDistonia.setName("SkorDistonia"); // NOI18N
        SkorDistonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorDistoniaActionPerformed(evt);
            }
        });
        FormInput.add(SkorDistonia);
        SkorDistonia.setBounds(770, 1980, 40, 24);

        SkorDTangka.setText("0");
        SkorDTangka.setName("SkorDTangka"); // NOI18N
        FormInput.add(SkorDTangka);
        SkorDTangka.setBounds(380, 1860, 40, 24);

        DistoniaTangki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaTangki.setName("DistoniaTangki"); // NOI18N
        DistoniaTangki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaTangkiItemStateChanged(evt);
            }
        });
        DistoniaTangki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaTangkiKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaTangki);
        DistoniaTangki.setBounds(220, 1890, 138, 23);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("Tangan kiri");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(80, 1890, 120, 20);

        SkorDTangki.setText("0");
        SkorDTangki.setName("SkorDTangki"); // NOI18N
        FormInput.add(SkorDTangki);
        SkorDTangki.setBounds(380, 1890, 40, 24);

        DistoniaKaka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaKaka.setName("DistoniaKaka"); // NOI18N
        DistoniaKaka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaKakaItemStateChanged(evt);
            }
        });
        DistoniaKaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaKakaKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaKaka);
        DistoniaKaka.setBounds(220, 1920, 138, 23);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("Kaki kanan");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(80, 1920, 120, 20);

        SkorDKaka.setText("0");
        SkorDKaka.setName("SkorDKaka"); // NOI18N
        FormInput.add(SkorDKaka);
        SkorDKaka.setBounds(380, 1920, 40, 24);

        DistoniaKaki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaKaki.setName("DistoniaKaki"); // NOI18N
        DistoniaKaki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaKakiItemStateChanged(evt);
            }
        });
        DistoniaKaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaKakiKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaKaki);
        DistoniaKaki.setBounds(220, 1950, 138, 23);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("Kaki kiri");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(80, 1950, 120, 20);

        SkorDKaki.setText("0");
        SkorDKaki.setName("SkorDKaki"); // NOI18N
        FormInput.add(SkorDKaki);
        SkorDKaki.setBounds(380, 1950, 40, 24);

        DistoniaRahang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaRahang.setName("DistoniaRahang"); // NOI18N
        DistoniaRahang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaRahangItemStateChanged(evt);
            }
        });
        DistoniaRahang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaRahangKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaRahang);
        DistoniaRahang.setBounds(610, 1860, 138, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("Rahang");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(470, 1860, 120, 20);

        SkorDRahang.setText("0");
        SkorDRahang.setName("SkorDRahang"); // NOI18N
        FormInput.add(SkorDRahang);
        SkorDRahang.setBounds(770, 1860, 40, 24);

        DistoniaLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaLidah.setName("DistoniaLidah"); // NOI18N
        DistoniaLidah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaLidahItemStateChanged(evt);
            }
        });
        DistoniaLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaLidahKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaLidah);
        DistoniaLidah.setBounds(610, 1890, 138, 23);

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("Lidah");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(470, 1890, 120, 20);

        SkorDLidah.setText("0");
        SkorDLidah.setName("SkorDLidah"); // NOI18N
        FormInput.add(SkorDLidah);
        SkorDLidah.setBounds(770, 1890, 40, 24);

        DistoniaBibir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaBibir.setName("DistoniaBibir"); // NOI18N
        DistoniaBibir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaBibirItemStateChanged(evt);
            }
        });
        DistoniaBibir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaBibirKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaBibir);
        DistoniaBibir.setBounds(610, 1920, 138, 23);

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("Bibir");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(470, 1920, 120, 20);

        SkorDBibir.setText("0");
        SkorDBibir.setName("SkorDBibir"); // NOI18N
        FormInput.add(SkorDBibir);
        SkorDBibir.setBounds(770, 1920, 40, 24);

        DistoniaTrunk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaTrunk.setName("DistoniaTrunk"); // NOI18N
        DistoniaTrunk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaTrunkItemStateChanged(evt);
            }
        });
        DistoniaTrunk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaTrunkKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaTrunk);
        DistoniaTrunk.setBounds(610, 1950, 138, 23);

        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("Trunk");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(470, 1950, 120, 20);

        SkorDTrunk.setText("0");
        SkorDTrunk.setName("SkorDTrunk"); // NOI18N
        FormInput.add(SkorDTrunk);
        SkorDTrunk.setBounds(770, 1950, 40, 24);

        DistoniaKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        DistoniaKepala.setName("DistoniaKepala"); // NOI18N
        DistoniaKepala.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DistoniaKepalaItemStateChanged(evt);
            }
        });
        DistoniaKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DistoniaKepalaKeyPressed(evt);
            }
        });
        FormInput.add(DistoniaKepala);
        DistoniaKepala.setBounds(220, 1980, 138, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("Kepala");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(80, 1980, 120, 20);

        SkorDKepala.setText("0");
        SkorDKepala.setName("SkorDKepala"); // NOI18N
        FormInput.add(SkorDKepala);
        SkorDKepala.setBounds(380, 1980, 40, 24);

        TardifTangka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifTangka.setName("TardifTangka"); // NOI18N
        TardifTangka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifTangkaItemStateChanged(evt);
            }
        });
        TardifTangka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifTangkaKeyPressed(evt);
            }
        });
        FormInput.add(TardifTangka);
        TardifTangka.setBounds(220, 2040, 138, 23);

        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel123.setText("Tangan kanan");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(80, 2040, 120, 20);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("2. Distonia tardif non-akut atau kronik");
        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(60, 2020, 410, 14);

        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("Skor total :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(710, 2160, 53, 20);

        SkorTardif.setText("0");
        SkorTardif.setName("SkorTardif"); // NOI18N
        SkorTardif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkorTardifActionPerformed(evt);
            }
        });
        FormInput.add(SkorTardif);
        SkorTardif.setBounds(770, 2160, 40, 24);

        SkorTTangka.setText("0");
        SkorTTangka.setName("SkorTTangka"); // NOI18N
        FormInput.add(SkorTTangka);
        SkorTTangka.setBounds(380, 2040, 40, 24);

        TardifTangki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifTangki.setName("TardifTangki"); // NOI18N
        TardifTangki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifTangkiItemStateChanged(evt);
            }
        });
        TardifTangki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifTangkiKeyPressed(evt);
            }
        });
        FormInput.add(TardifTangki);
        TardifTangki.setBounds(220, 2070, 138, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("Tangan kiri");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(80, 2070, 120, 20);

        SkorTTangki.setText("0");
        SkorTTangki.setName("SkorTTangki"); // NOI18N
        FormInput.add(SkorTTangki);
        SkorTTangki.setBounds(380, 2070, 40, 24);

        TardifKaka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifKaka.setName("TardifKaka"); // NOI18N
        TardifKaka.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifKakaItemStateChanged(evt);
            }
        });
        TardifKaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifKakaKeyPressed(evt);
            }
        });
        FormInput.add(TardifKaka);
        TardifKaka.setBounds(220, 2100, 138, 23);

        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel127.setText("Kaki kanan");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(80, 2100, 120, 20);

        SkorTKaka.setText("0");
        SkorTKaka.setName("SkorTKaka"); // NOI18N
        FormInput.add(SkorTKaka);
        SkorTKaka.setBounds(380, 2100, 40, 24);

        TardifKaki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifKaki.setName("TardifKaki"); // NOI18N
        TardifKaki.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifKakiItemStateChanged(evt);
            }
        });
        TardifKaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifKakiKeyPressed(evt);
            }
        });
        FormInput.add(TardifKaki);
        TardifKaki.setBounds(220, 2130, 138, 23);

        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("Kaki kiri");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(80, 2130, 120, 20);

        SkorTKaki.setText("0");
        SkorTKaki.setName("SkorTKaki"); // NOI18N
        FormInput.add(SkorTKaki);
        SkorTKaki.setBounds(380, 2130, 40, 24);

        TardifRahang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifRahang.setName("TardifRahang"); // NOI18N
        TardifRahang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifRahangItemStateChanged(evt);
            }
        });
        TardifRahang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifRahangKeyPressed(evt);
            }
        });
        FormInput.add(TardifRahang);
        TardifRahang.setBounds(610, 2040, 138, 23);

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("Rahang");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(470, 2040, 120, 20);

        SkorTRahang.setText("0");
        SkorTRahang.setName("SkorTRahang"); // NOI18N
        FormInput.add(SkorTRahang);
        SkorTRahang.setBounds(770, 2040, 40, 24);

        TardifLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifLidah.setName("TardifLidah"); // NOI18N
        TardifLidah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifLidahItemStateChanged(evt);
            }
        });
        TardifLidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifLidahKeyPressed(evt);
            }
        });
        FormInput.add(TardifLidah);
        TardifLidah.setBounds(610, 2070, 138, 23);

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("Lidah");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(470, 2070, 120, 20);

        SkorTLidah.setText("0");
        SkorTLidah.setName("SkorTLidah"); // NOI18N
        FormInput.add(SkorTLidah);
        SkorTLidah.setBounds(770, 2070, 40, 24);

        TardifBibir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifBibir.setName("TardifBibir"); // NOI18N
        TardifBibir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifBibirItemStateChanged(evt);
            }
        });
        TardifBibir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifBibirKeyPressed(evt);
            }
        });
        FormInput.add(TardifBibir);
        TardifBibir.setBounds(610, 2100, 138, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("Bibir");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(470, 2100, 120, 20);

        SkorTBibir.setText("0");
        SkorTBibir.setName("SkorTBibir"); // NOI18N
        FormInput.add(SkorTBibir);
        SkorTBibir.setBounds(770, 2100, 40, 24);

        TardifTrunk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifTrunk.setName("TardifTrunk"); // NOI18N
        TardifTrunk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifTrunkItemStateChanged(evt);
            }
        });
        TardifTrunk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifTrunkKeyPressed(evt);
            }
        });
        FormInput.add(TardifTrunk);
        TardifTrunk.setBounds(610, 2130, 138, 23);

        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("Trunk");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(470, 2130, 120, 20);

        SkorTTrunk.setText("0");
        SkorTTrunk.setName("SkorTTrunk"); // NOI18N
        FormInput.add(SkorTTrunk);
        SkorTTrunk.setBounds(770, 2130, 40, 24);

        TardifKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Sangat ringan", "Ringan", "Sedang", "Agak berat", "Berat", "Sangat berat" }));
        TardifKepala.setName("TardifKepala"); // NOI18N
        TardifKepala.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TardifKepalaItemStateChanged(evt);
            }
        });
        TardifKepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TardifKepalaActionPerformed(evt);
            }
        });
        TardifKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TardifKepalaKeyPressed(evt);
            }
        });
        FormInput.add(TardifKepala);
        TardifKepala.setBounds(220, 2160, 138, 23);

        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("Kepala");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(80, 2160, 120, 20);

        Skordiskilid.setText("0");
        Skordiskilid.setName("Skordiskilid"); // NOI18N
        Skordiskilid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskilidActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskilid);
        Skordiskilid.setBounds(670, 2230, 40, 24);

        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("Lengan kanan");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(110, 1070, 120, 20);

        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("Lengan kiri");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(110, 1100, 120, 20);

        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("Kaki kanan");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(110, 1130, 120, 20);

        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("Kaki kiri");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(110, 1160, 120, 20);

        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("Skor total :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(610, 1320, 53, 20);

        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel139.setText("Kepala");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(110, 1190, 120, 20);

        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("Rahang/pipi");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(110, 1220, 120, 20);

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel141.setText("Lidah");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(110, 1250, 120, 20);

        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel142.setText("Bibir");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(110, 1280, 120, 20);

        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel143.setText("IV. GERAKAN-GERAKAN DISKINETIK : Pemeriksaan Dokter");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(10, 2200, 350, 23);

        DiskiLidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Kadang", "Complete protrusion" }));
        DiskiLidah.setName("DiskiLidah"); // NOI18N
        DiskiLidah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiLidahItemStateChanged(evt);
            }
        });
        DiskiLidah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiLidahActionPerformed(evt);
            }
        });
        FormInput.add(DiskiLidah);
        DiskiLidah.setBounds(330, 2230, 120, 20);

        grpDiskinetik1.add(radJarang1);
        radJarang1.setText("Jarang");
        radJarang1.setEnabled(false);
        radJarang1.setName("radJarang1"); // NOI18N
        radJarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang1ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang1);
        radJarang1.setBounds(470, 2230, 54, 17);

        grpDiskinetik1.add(radSering1);
        radSering1.setText("Sering");
        radSering1.setEnabled(false);
        radSering1.setName("radSering1"); // NOI18N
        radSering1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering1ActionPerformed(evt);
            }
        });
        FormInput.add(radSering1);
        radSering1.setBounds(530, 2230, 51, 17);

        grpDiskinetik1.add(radSelalu1);
        radSelalu1.setText("Selalu");
        radSelalu1.setEnabled(false);
        radSelalu1.setName("radSelalu1"); // NOI18N
        radSelalu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu1ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu1);
        radSelalu1.setBounds(590, 2230, 83, 17);

        SkorTKepala.setText("0");
        SkorTKepala.setName("SkorTKepala"); // NOI18N
        FormInput.add(SkorTKepala);
        SkorTKepala.setBounds(380, 2160, 40, 24);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("<html>2. Gerakan rahang (mengunyah, menggigit)</html>");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(60, 2260, 240, 30);

        Skordiskirah.setText("0");
        Skordiskirah.setName("Skordiskirah"); // NOI18N
        Skordiskirah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskirahActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskirah);
        Skordiskirah.setBounds(670, 2260, 40, 24);

        DiskiRahang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Amplitudo sedang", "Amplitudo besar" }));
        DiskiRahang.setName("DiskiRahang"); // NOI18N
        DiskiRahang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiRahangItemStateChanged(evt);
            }
        });
        DiskiRahang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiRahangActionPerformed(evt);
            }
        });
        FormInput.add(DiskiRahang);
        DiskiRahang.setBounds(330, 2260, 120, 20);

        grpDiskinetik2.add(radJarang2);
        radJarang2.setText("Jarang");
        radJarang2.setEnabled(false);
        radJarang2.setName("radJarang2"); // NOI18N
        radJarang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang2ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang2);
        radJarang2.setBounds(470, 2260, 54, 17);

        grpDiskinetik2.add(radSering2);
        radSering2.setText("Sering");
        radSering2.setEnabled(false);
        radSering2.setName("radSering2"); // NOI18N
        radSering2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering2ActionPerformed(evt);
            }
        });
        FormInput.add(radSering2);
        radSering2.setBounds(530, 2260, 51, 17);

        grpDiskinetik2.add(radSelalu2);
        radSelalu2.setText("Selalu");
        radSelalu2.setEnabled(false);
        radSelalu2.setName("radSelalu2"); // NOI18N
        radSelalu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu2ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu2);
        radSelalu2.setBounds(590, 2260, 83, 17);

        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel144.setText("<html>3. Gerakan pipi dan bibir (mencucur, mencibir, mengecap)</html>");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(60, 2300, 240, 30);

        Skordiskipi.setText("0");
        Skordiskipi.setName("Skordiskipi"); // NOI18N
        Skordiskipi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskipiActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskipi);
        Skordiskipi.setBounds(670, 2300, 40, 24);

        DiskiBibir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Amplitudo sedang", "Amplitudo besar", " " }));
        DiskiBibir.setName("DiskiBibir"); // NOI18N
        DiskiBibir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiBibirItemStateChanged(evt);
            }
        });
        DiskiBibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiBibirActionPerformed(evt);
            }
        });
        FormInput.add(DiskiBibir);
        DiskiBibir.setBounds(330, 2300, 120, 20);

        grpDiskinetik3.add(radJarang3);
        radJarang3.setText("Jarang");
        radJarang3.setEnabled(false);
        radJarang3.setName("radJarang3"); // NOI18N
        radJarang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang3ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang3);
        radJarang3.setBounds(470, 2300, 54, 17);

        grpDiskinetik3.add(radSering3);
        radSering3.setText("Sering");
        radSering3.setEnabled(false);
        radSering3.setName("radSering3"); // NOI18N
        radSering3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering3ActionPerformed(evt);
            }
        });
        FormInput.add(radSering3);
        radSering3.setBounds(530, 2300, 51, 17);

        grpDiskinetik3.add(radSelalu3);
        radSelalu3.setText("Selalu");
        radSelalu3.setEnabled(false);
        radSelalu3.setName("radSelalu3"); // NOI18N
        radSelalu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu3ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu3);
        radSelalu3.setBounds(590, 2300, 83, 17);

        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel145.setText("<html>4. Gerakan badan (bergoyang,berputar,goyang pinggul)</html>");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(60, 2340, 240, 30);

        Skordiskidan.setText("0");
        Skordiskidan.setName("Skordiskidan"); // NOI18N
        Skordiskidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskidanActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskidan);
        Skordiskidan.setBounds(670, 2340, 40, 24);

        DiskiBadan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Amplitudo sedang", "Amplitudo besar" }));
        DiskiBadan.setName("DiskiBadan"); // NOI18N
        DiskiBadan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiBadanItemStateChanged(evt);
            }
        });
        DiskiBadan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiBadanActionPerformed(evt);
            }
        });
        FormInput.add(DiskiBadan);
        DiskiBadan.setBounds(330, 2340, 120, 20);

        grpDiskinetik4.add(radJarang4);
        radJarang4.setText("Jarang");
        radJarang4.setEnabled(false);
        radJarang4.setName("radJarang4"); // NOI18N
        radJarang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang4ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang4);
        radJarang4.setBounds(470, 2340, 54, 17);

        grpDiskinetik4.add(radSering4);
        radSering4.setText("Sering");
        radSering4.setEnabled(false);
        radSering4.setName("radSering4"); // NOI18N
        radSering4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering4ActionPerformed(evt);
            }
        });
        FormInput.add(radSering4);
        radSering4.setBounds(530, 2340, 51, 17);

        grpDiskinetik4.add(radSelalu4);
        radSelalu4.setText("Selalu");
        radSelalu4.setEnabled(false);
        radSelalu4.setName("radSelalu4"); // NOI18N
        radSelalu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu4ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu4);
        radSelalu4.setBounds(590, 2340, 83, 17);

        Skordiskitas.setText("0");
        Skordiskitas.setName("Skordiskitas"); // NOI18N
        Skordiskitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskitasActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskitas);
        Skordiskitas.setBounds(670, 2380, 40, 24);

        DiskiEkstrematas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Amplitudo sedang", "Amplitudo besar" }));
        DiskiEkstrematas.setName("DiskiEkstrematas"); // NOI18N
        DiskiEkstrematas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiEkstrematasItemStateChanged(evt);
            }
        });
        DiskiEkstrematas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiEkstrematasActionPerformed(evt);
            }
        });
        FormInput.add(DiskiEkstrematas);
        DiskiEkstrematas.setBounds(330, 2380, 120, 20);

        grpDiskinetik5.add(radSering5);
        radSering5.setText("Sering");
        radSering5.setEnabled(false);
        radSering5.setName("radSering5"); // NOI18N
        radSering5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering5ActionPerformed(evt);
            }
        });
        FormInput.add(radSering5);
        radSering5.setBounds(530, 2380, 51, 17);

        grpDiskinetik5.add(radSelalu5);
        radSelalu5.setText("Selalu");
        radSelalu5.setEnabled(false);
        radSelalu5.setName("radSelalu5"); // NOI18N
        radSelalu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu5ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu5);
        radSelalu5.setBounds(590, 2380, 83, 17);

        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel146.setText("<html>5. Ekstremitas atas (gerakan koreotetoid : lengan, pergelangan tangan, tangan, jari)</html>");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(60, 2380, 240, 30);

        grpDiskinetik5.add(radJarang5);
        radJarang5.setText("Jarang");
        radJarang5.setEnabled(false);
        radJarang5.setName("radJarang5"); // NOI18N
        radJarang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang5ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang5);
        radJarang5.setBounds(470, 2380, 54, 17);

        DiskiEkstrembawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Amplitudo sedang", "Amplitudo besar" }));
        DiskiEkstrembawah.setName("DiskiEkstrembawah"); // NOI18N
        DiskiEkstrembawah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiEkstrembawahItemStateChanged(evt);
            }
        });
        DiskiEkstrembawah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiEkstrembawahActionPerformed(evt);
            }
        });
        FormInput.add(DiskiEkstrembawah);
        DiskiEkstrembawah.setBounds(330, 2420, 120, 20);

        grpDiskinetik6.add(radSering6);
        radSering6.setText("Sering");
        radSering6.setEnabled(false);
        radSering6.setName("radSering6"); // NOI18N
        radSering6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering6ActionPerformed(evt);
            }
        });
        FormInput.add(radSering6);
        radSering6.setBounds(530, 2420, 51, 17);

        Skordiskiwah.setText("0");
        Skordiskiwah.setName("Skordiskiwah"); // NOI18N
        Skordiskiwah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskiwahActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskiwah);
        Skordiskiwah.setBounds(670, 2420, 40, 24);

        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel147.setText("<html>6. Ekstremitas bawah (gerakan koreotetoid :paha, lutut, pergelangan kaki, telapak kaki)</html>");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(60, 2420, 240, 30);

        grpDiskinetik6.add(radJarang6);
        radJarang6.setText("Jarang");
        radJarang6.setEnabled(false);
        radJarang6.setName("radJarang6"); // NOI18N
        radJarang6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang6ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang6);
        radJarang6.setBounds(470, 2420, 54, 17);

        grpDiskinetik6.add(radSelalu6);
        radSelalu6.setText("Selalu");
        radSelalu6.setEnabled(false);
        radSelalu6.setName("radSelalu6"); // NOI18N
        radSelalu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu6ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu6);
        radSelalu6.setBounds(590, 2420, 83, 17);

        Skordiskivol.setText("0");
        Skordiskivol.setName("Skordiskivol"); // NOI18N
        Skordiskivol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkordiskivolActionPerformed(evt);
            }
        });
        FormInput.add(Skordiskivol);
        Skordiskivol.setBounds(670, 2460, 40, 24);

        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel148.setText("<html>7. Gerakan involunter yang lain (menelan, napas tidak teratur, mengerutkan dahi, berkedip, menyeringai)</html>");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(60, 2460, 240, 40);

        DiskiInvolunter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada", "Borderline", "Jelas ada", "Amplitudo sedang", "Amplitudo besar" }));
        DiskiInvolunter.setName("DiskiInvolunter"); // NOI18N
        DiskiInvolunter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DiskiInvolunterItemStateChanged(evt);
            }
        });
        DiskiInvolunter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskiInvolunterActionPerformed(evt);
            }
        });
        FormInput.add(DiskiInvolunter);
        DiskiInvolunter.setBounds(330, 2460, 120, 20);

        grpDiskinetik7.add(radSering7);
        radSering7.setText("Sering");
        radSering7.setEnabled(false);
        radSering7.setName("radSering7"); // NOI18N
        radSering7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSering7ActionPerformed(evt);
            }
        });
        FormInput.add(radSering7);
        radSering7.setBounds(530, 2460, 51, 17);

        grpDiskinetik7.add(radJarang7);
        radJarang7.setText("Jarang");
        radJarang7.setEnabled(false);
        radJarang7.setName("radJarang7"); // NOI18N
        radJarang7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radJarang7ActionPerformed(evt);
            }
        });
        FormInput.add(radJarang7);
        radJarang7.setBounds(470, 2460, 54, 17);

        grpDiskinetik7.add(radSelalu7);
        radSelalu7.setText("Selalu");
        radSelalu7.setEnabled(false);
        radSelalu7.setName("radSelalu7"); // NOI18N
        radSelalu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radSelalu7ActionPerformed(evt);
            }
        });
        FormInput.add(radSelalu7);
        radSelalu7.setBounds(590, 2460, 83, 17);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-09-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-09-2023" }));
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
        
        String mukatopeng = "";
        String bradi = "";
        String postur = "";
        
        String tremorlengka = "";
        String tremorlengki = "";
        String tremorkaka = "";
        String tremorkaki = "";
        String tremorkepala = "";
        String tremorrahang = "";
        String tremorlidah = "";
        String tremorbibir = "";
        
        String akatisia = "";
        String sialarhoe = "";
        String stabilitas = "";
        
        
        String diskilidah = "";
        String diskirahang = "";
        String diskipipi = "";
        String diskibadan = "";
        String diskieksatas = "";
        String diskieksbawah = "";
        String diskiinvol = "";
        
        
        
        
        
        //mukatopeng
        if (GNormal.isSelected()){
            mukatopeng = "Normal";
        }else if (GPenurunan1.isSelected()){
            mukatopeng = "Penurunan yang sangat ringan dari ekspresi muka";
        }else if (GPenurunan2.isSelected()){
            mukatopeng = "Penurunan ringan dari ekspresi muka";
        }else if (GSenyum.isSelected()){
            mukatopeng = "Senyum spontan jarang, berkedip kurang, suara agak monoton";
        }else if (GSenyum2.isSelected()){
            mukatopeng = "Tidak ada senyum spontan, pandangan kosong, pembicaraan perlahan dan monoton, bergumam";
        }else if (GMuka1.isSelected()){
            mukatopeng = "Muka topeng yang nyata, tidak mempu mengerutkan dahi, bicara pelo";
        }else if (GMuka2.isSelected()){
            mukatopeng = "Muka topeng yang sangat nyata dengan pembicaran yang sulit dimengerti";
        }
        
        //bradi
        if (BNormal.isSelected()){
            bradi = "Normal";
        }else if (BLambat1.isSelected()){
            bradi = "Kesan perlambatan yang menyeluruh dari gerakan-gerakan";
        }else if (BLambat2.isSelected()){
            bradi = "Kelambatan yang nyata dalam gerakan";
        }else if (BSulit1.isSelected()){
            bradi = "Kesulitan yang sangat ringan dalam memulai gerakan";
        }else if (BSulit2.isSelected()){
            bradi = "Kesulitan ringan sampai sedang dalam memulai gerakan";
        }else if (BSulit3.isSelected()){
            bradi = "Kesulitan dalan memulai/menghentikan setiap gerakan atau kekakuan dalam memulai gerakan volunter";
        }else if (BJarang.isSelected()){
            bradi = "Jarang terdapat gerakan-gerakan terarah, hampir nampak tak bergerak";
        }
        
        
        //postur
        if (JNormal.isSelected()){
            postur = "Normal";
        } else if (JTurun.isSelected()){
            postur = "Penurunan ringan dari ayunan lengan";
        }else if (JTurun2.isSelected()){
            postur = "Penurunan sedang dari ayunan lengan, langkah normal";
        }else if (JAyunan.isSelected()){
            postur = "Tidak ada ayunan lengan, kepala fleksi, langkah kurang lebih normal";
        }else if (JKaku.isSelected()){
            postur = "Postur tubuh kaku (leher dan punggung). melangkah dengan kaki diseret";
        }else if (JNyata.isSelected()){
            postur = "Lebih nyata, kekakuan dalam berputar";
        }else if (JFlexi.isSelected()){
            postur = "Triple flexi, hampir tidak mampu berjalan";
        }
        
        
        //lengka
        if (SekaliLengka.isSelected()){
            tremorlengka = "Sekali-sekali";
        }else if(SeringLengka.isSelected()){
            tremorlengka = "Sering";
        }else if (SelaluLengka.isSelected()){
            tremorlengka = "Selalu";
        }
        
        
        //lengki
        if (SekaliLengki.isSelected()){
            tremorlengki = "Sekali-sekali";
        }else if(SeringLengki.isSelected()){
            tremorlengki = "Sering";
        }else if (SelaluLengki.isSelected()){
            tremorlengki = "Selalu";
        }
        
        
        //kaka
        if (SekaliKaka.isSelected()){
            tremorkaka = "Sekali-sekali";
        }else if(SeringKaka.isSelected()){
            tremorkaka = "Sering";
        }else if (SelaluKaka.isSelected()){
            tremorkaka = "Selalu";
        }
        
        
        //kaki
        if (SekaliKaki.isSelected()){
            tremorkaki = "Sekali-sekali";
        }else if(SeringKaki.isSelected()){
            tremorkaki = "Sering";
        }else if (SelaluKaki.isSelected()){
            tremorkaki = "Selalu";
        }
        
        
        //kepala
        if (SekaliKepala.isSelected()){
            tremorkepala = "Sekali-sekali";
        }else if(SeringKepala.isSelected()){
            tremorkepala = "Sering";
        }else if (SelaluKepala.isSelected()){
            tremorkepala = "Selalu";
        }
        
        
        //rahang
        if (SekaliPipi.isSelected()){
            tremorrahang = "Sekali-sekali";
        }else if(SeringPipi.isSelected()){
            tremorrahang = "Sering";
        }else if (SelaluPipi.isSelected()){
            tremorrahang = "Selalu";
        }
        
        
        //lidah
        if (SekaliLidah.isSelected()){
            tremorlidah = "Sekali-sekali";
        }else if(SeringLidah.isSelected()){
            tremorlidah = "Sering";
        }else if (SelaluLidah.isSelected()){
            tremorlidah = "Selalu";
        }
        
        
        //bibir
        if (SekaliBibir.isSelected()){
            tremorbibir = "Sekali-sekali";
        }else if(SeringBibir.isSelected()){
            tremorbibir = "Sering";
        }else if (SelaluBibir.isSelected()){
            tremorbibir = "Selalu";
        }
        
        
        if(ATidakada.isSelected()){
            akatisia = "Tidak ada";
        }else if (AGelisah.isSelected()){
            akatisia = "Tampak gelisah, nervous, paling tidak pada satu ektremitas";
        }else if (AIngin.isSelected()){
            akatisia = "Ingin selalu bergerak, paling tidak pada satu ekstremitas";
        }else if (AGerak.isSelected()){
            akatisia = "Sering ingin menggerakkan satu ekstremitas atau mengubah posisi";
        }else if (AHentak.isSelected()){
            akatisia = "Menggerakkan satu ekstremitas hampir terus menerus pada saat duduk atau menghentak-hentakkan kaki ketika berdiri";
        }else if (ADuduk.isSelected()){
            akatisia = "Hanya mampu untuk tetap duduk dalam jangka waktu yang pendek";
        }else if (ATerus.isSelected()){
            akatisia = "Bergerak atau berjalan terus-menerus";
        }
        
        
        if(STidakada.isSelected()){
            sialarhoe = "Tidak ada";
        }else if (SgtRingan.isSelected()){
            sialarhoe = "Sangat ringan";
        }else if (SRingan.isSelected()){
            sialarhoe = "Ringan";
        }else if (Ssedang.isSelected()){
            sialarhoe = "Sedang, mengganggu pembicaraan";
        }else if (SAgakberat.isSelected()){
            sialarhoe = "Agak berat";
        }else if (SBerat.isSelected()){
            sialarhoe = "Berat";
        }else if (SSangatberat.isSelected()){
            sialarhoe = "Sangat berat, drooling";
        }
        
        
        if(StabTidakada.isSelected()){
            stabilitas = "Normal";
        }else if (StabLambat.isSelected()){
            stabilitas = "Kelambatan ketika didorong, tetapi tanpa retropulsi";
        }else if (StabRetro.isSelected()){
            stabilitas = "Retropulsi, tetapi segera pulih tanpa bantuan";
        }else if (StabRetro1.isSelected()){
            stabilitas = "Retropulsi yang lebih nyata, tetapi tidak terjatuh";
        }else if (StabRespon.isSelected()){
            stabilitas = "Tidak ada respon postural, akan terjatuh jika tidak ditahan oleh pemeriksa";
        }else if (StabStabil.isSelected()){
            stabilitas = "Berdiri tidak stabil, tanpa didorong sekalipun";
        }else if (StabMampu.isSelected()){
            stabilitas = "Tidak mampu berdiri tanpa bantuan";
        }
        
        
        if (radJarang1.isSelected()){
            diskilidah = "Jarang";
        }else if(radSering1.isSelected()){
            diskilidah = "Sering";
        }else if (radSelalu1.isSelected()){
            diskilidah = "Selalu";
        }
        
        
        if (radJarang2.isSelected()){
            diskirahang = "Jarang";
        }else if(radSering2.isSelected()){
            diskirahang = "Sering";
        }else if (radSelalu2.isSelected()){
            diskirahang = "Selalu";
        }
        
        
        if (radJarang3.isSelected()){
            diskipipi = "Jarang";
        }else if(radSering3.isSelected()){
            diskipipi = "Sering";
        }else if (radSelalu3.isSelected()){
            diskipipi = "Selalu";
        }
        
        
        if (radJarang4.isSelected()){
            diskibadan = "Jarang";
        }else if(radSering4.isSelected()){
            diskibadan = "Sering";
        }else if (radSelalu4.isSelected()){
            diskibadan = "Selalu";
        }
        
        
        
        if (radJarang5.isSelected()){
            diskieksatas = "Jarang";
        }else if(radSering5.isSelected()){
            diskieksatas = "Sering";
        }else if (radSelalu5.isSelected()){
            diskieksatas = "Selalu";
        }
        
        
         if (radJarang6.isSelected()){
            diskieksbawah = "Jarang";
        }else if(radSering6.isSelected()){
            diskieksbawah = "Sering";
        }else if (radSelalu6.isSelected()){
            diskieksbawah = "Selalu";
        }
         
        
         if (radJarang7.isSelected()){
            diskiinvol = "Jarang";
        }else if(radSering7.isSelected()){
            diskiinvol = "Sering";
        }else if (radSelalu7.isSelected()){
            diskiinvol = "Selalu";
        }
         
         
         
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
//        }else if(KeluhanUtama.getText().trim().equals("")){
//            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
//        }else if(RPS.getText().trim().equals("")){
//            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
//        }else if(RPD.getText().trim().equals("")){
//            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
//        }else if(RPO.getText().trim().equals("")){
//            Valid.textKosong(RPO,"Riwayat Pengunaan obat");
        }else{
            if(Sequel.menyimpantf("penilaian_esrs","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",122,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),
                    
                    
                    SulitTugasrutin.getSelectedItem().toString(),SulitBerjalan.getSelectedItem().toString(),SulitMenelan.getSelectedItem().toString(),Kaku.getSelectedItem().toString(),Kram.getSelectedItem().toString(),Gelisah.getSelectedItem().toString(),
                    Tremor.getSelectedItem().toString(),PosturAbnormal.getSelectedItem().toString(),Ludah.getSelectedItem().toString(),DiskinesiaGerak.getSelectedItem().toString(),DiskinesiaLidah.getSelectedItem().toString(),Pusing.getSelectedItem().toString(),
                     
                    //II.
                    //1 mukatopeng
                    mukatopeng,SkorGerakan.getText(),
                    //2 bradikines
                    bradi,SkorBradi.getText(),
                    //3 rigid
                    EKananAtas.getSelectedItem().toString(),SkorKananAtas.getText(),EKiriAtas.getSelectedItem().toString(),SkorKiriAtas.getText(),EKananBawah.getSelectedItem().toString(),SkorKananBawah.getText(),EKiriBawah.getSelectedItem().toString(),SkorKiriBawah.getText(),SkorRigid.getText(),
                    //4 gaya berjalan
                    postur,SkorJalan.getText(),
                    //5 tremor
                    TLengka.getSelectedItem().toString(),tremorlengka,SkorLengka.getText(),TLengki.getSelectedItem().toString(),tremorlengki,SkorLengki.getText(),TKaka.getSelectedItem().toString(),tremorkaka,SkorKaka.getText(),TKaki.getSelectedItem().toString(),tremorkaki,SkorKaki.getText(),
                    TKepala.getSelectedItem().toString(),tremorkepala,SkorKepala.getText(),TPipi.getSelectedItem().toString(),tremorrahang,SkorPipi.getText(),TLidah.getSelectedItem().toString(),tremorlidah,SkorLidah.getText(),TBibir.getSelectedItem().toString(),tremorbibir,SkorBibir.getText(),SkorTremor.getText(),
                    //6 akatisia
                    akatisia,SkorAkatisia.getText(),
                    //7 sialarhoe
                    sialarhoe,SkorSialorhoe.getText(),
                    //8 stabilitas
                    stabilitas,SkorStabilitas.getText(),
                    
                    //III.
                    //1
                    DistoniaTangka.getSelectedItem().toString(),SkorDTangka.getText(),DistoniaTangki.getSelectedItem().toString(),SkorDTangki.getText(),DistoniaKaka.getSelectedItem().toString(),SkorDKaka.getText(),DistoniaKaki.getSelectedItem().toString(),SkorDKaki.getText(),DistoniaKepala.getSelectedItem().toString(),SkorDKepala.getText(),
                    DistoniaRahang.getSelectedItem().toString(),SkorDRahang.getText(),DistoniaLidah.getSelectedItem().toString(),SkorDLidah.getText(),DistoniaBibir.getSelectedItem().toString(),SkorDBibir.getText(),DistoniaTrunk.getSelectedItem().toString(),SkorDTrunk.getText(),SkorDistonia.getText(),
                    //2
                    TardifTangka.getSelectedItem().toString(),SkorTTangka.getText(),TardifTangki.getSelectedItem().toString(),SkorTTangki.getText(),TardifKaka.getSelectedItem().toString(),SkorTKaka.getText(),TardifKaki.getSelectedItem().toString(),SkorTKaki.getText(),TardifKepala.getSelectedItem().toString(),SkorTKepala.getText(),
                    TardifRahang.getSelectedItem().toString(),SkorTRahang.getText(),TardifLidah.getSelectedItem().toString(),SkorTLidah.getText(),TardifBibir.getSelectedItem().toString(),SkorTBibir.getText(),TardifTrunk.getSelectedItem().toString(),SkorTTrunk.getText(),SkorTardif.getText(),
                    
                    //IV.
                    DiskiLidah.getSelectedItem().toString(),diskilidah,Skordiskilid.getText(),
                    DiskiRahang.getSelectedItem().toString(),diskirahang,Skordiskirah.getText(),
                    DiskiBibir.getSelectedItem().toString(),diskipipi,Skordiskipi.getText(),
                    DiskiBadan.getSelectedItem().toString(),diskibadan,Skordiskidan.getText(),
                    DiskiEkstrematas.getSelectedItem().toString(),diskieksatas,Skordiskitas.getText(),
                    DiskiEkstrembawah.getSelectedItem().toString(),diskieksbawah,Skordiskiwah.getText(),
                    DiskiInvolunter.getSelectedItem().toString(),diskiinvol,Skordiskivol.getText()
                    
                    
                    })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,Edukasi,BtnBatal);
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
//        }else if(KeluhanUtama.getText().trim().equals("")){
//            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
//        }else if(RPS.getText().trim().equals("")){
//            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
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
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kode Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Dokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Anamnesis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Hubungan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keluhan Utama</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Riwayat Penyakit Sekarang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Riwayat Penyakit Dahulu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Riwayat Penggunakan Obat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Riwayat Alergi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kondisi Umum</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>TD(mmHg)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nadi(x/menit)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Suhu</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>RR(x/menit)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tulang Belakang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kepala</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Kepala</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Thoraks</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Thoraks</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Abdomen</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Abdomen</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Ekstremitas</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Keterangan Ekstremitas</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>In.Kebersihan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>In.Warna</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>In.Kelembaban</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>In.Gangguan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Lainnya</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Kondisi Sosial</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Status Psikologis (GDS)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Status Kognitif (MMSE)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Status Nutrisi (MNA)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Skrinning Risiko Jatuh (OMS)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Status Fungsional (ADL: BARTHEL INDEX)</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Laboratorium</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Radiologi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Pemeriksaan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Diagnosis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Diagnosis Banding</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Permasalahan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Terapi</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tindakan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Edukasi</b></td>"+
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
                            "<td valign='top'>"+tbObat.getValueAt(i,47).toString()+"</td>"+
                            "<td valign='top'>"+tbObat.getValueAt(i,48).toString()+"</td>"+
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='5400px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                File f = new File("DataPenilaianAwalMedisRalanGeriatri.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='5400px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL MEDIS RAWAT JALAN GERIATRI<br><br></font>"+        
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
        //Valid.pindah(evt,Edukasi,Hubungan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt,TglAsuhan,Hubungan);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        //Valid.pindah(evt,Edukasi,Anamnesis);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
//        Valid.pindah(evt,Anamnesis,KeluhanUtama);
    }//GEN-LAST:event_HubunganKeyPressed

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
            
            Valid.MyReportqry("rptCetakPenilianEktrapiramidal.jasper","report","::[ Laporan Penilaian Gejala Ekstrapiramidal Halaman 1 ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_esrs.tanggal,"+
                "penilaian_esrs.kd_dokter,dokter.nm_dokter,penilaian_esrs.anamnesis,penilaian_esrs.hubungan," + 
                        
                "penilaian_esrs.kues_lambat,penilaian_esrs.kues_berjalan,penilaian_esrs.kues_menelan,penilaian_esrs.kues_kaku,penilaian_esrs.kues_kram,penilaian_esrs.kues_gelisah,penilaian_esrs.kues_tremor,penilaian_esrs.kues_postur,penilaian_esrs.kues_ludah,penilaian_esrs.kues_diskinesiagerak,penilaian_esrs.kues_diskinesialidah,penilaian_esrs.kues_pusing,"+

                "penilaian_esrs.mukatopeng,penilaian_esrs.skormuka,penilaian_esrs.bradi,penilaian_esrs.skorbradi,"+

                "penilaian_esrs.rigi_ekanana,penilaian_esrs.skorekanana,penilaian_esrs.rigi_ekiria,penilaian_esrs.skorekiria,penilaian_esrs.rigi_ekananb,penilaian_esrs.skorekananb,penilaian_esrs.rigi_ekirib,penilaian_esrs.skorekirib,penilaian_esrs.skorrigid,"+
                "penilaian_esrs.postur,penilaian_esrs.skorjalan,penilaian_esrs.ketlengka,penilaian_esrs.rasiolengka,penilaian_esrs.skorlengka,penilaian_esrs.ketlengki,penilaian_esrs.rasiolengki,penilaian_esrs.skorlengki,penilaian_esrs.ketkaka,penilaian_esrs.rasiokaka,"+
                "penilaian_esrs.skorkaka,penilaian_esrs.ketkaki,penilaian_esrs.rasiokaki,penilaian_esrs.skorkaki,penilaian_esrs.ketkepala,penilaian_esrs.rasiokepala,penilaian_esrs.skorkepala,penilaian_esrs.ketrahang,penilaian_esrs.rasiorahang,penilaian_esrs.skorrahang,penilaian_esrs.ketlidah,"+
                "penilaian_esrs.rasiolidah,penilaian_esrs.skorlidah,penilaian_esrs.ketbibir,penilaian_esrs.rasiobibir,penilaian_esrs.skorbibir,penilaian_esrs.skortremor,penilaian_esrs.akatisia,penilaian_esrs.skorakatisia,penilaian_esrs.sialorhoe,penilaian_esrs.skorsialorhoe,penilaian_esrs.stabilitas,penilaian_esrs.skorstabilitas,"+
                "penilaian_esrs.torsitangka,penilaian_esrs.skortorsitangka,penilaian_esrs.torsitangki,penilaian_esrs.skortorsitangki,penilaian_esrs.torsikaka,penilaian_esrs.skortorsikaka,penilaian_esrs.torsikaki,penilaian_esrs.skortorsikaki,penilaian_esrs.torsikepala,penilaian_esrs.skortorsikepala,penilaian_esrs.torsirahang,"+
                "penilaian_esrs.skortorsirahang,penilaian_esrs.torsilidah,penilaian_esrs.skortorsilidah,penilaian_esrs.torsibibir,penilaian_esrs.skortorsibibir,penilaian_esrs.torsitrunk,penilaian_esrs.skortorsitrunk,penilaian_esrs.skortorsi,penilaian_esrs.tardiftangka,penilaian_esrs.skortardiftangka,penilaian_esrs.tardiftangki,"+
                "penilaian_esrs.skortardiftangki,penilaian_esrs.tardifkaka,penilaian_esrs.skortardifkaka,penilaian_esrs.tardifkaki,penilaian_esrs.skortardifkaki,penilaian_esrs.tardifkepala,penilaian_esrs.skortardifkepala,penilaian_esrs.tardifrahang,penilaian_esrs.skortardifrahang,penilaian_esrs.tardiflidah,penilaian_esrs.skortardiflidah,penilaian_esrs.tardifbibir,"+
                "penilaian_esrs.skortardifbibir,penilaian_esrs.tardiftrunk,penilaian_esrs.skortardiftrunk,penilaian_esrs.skortardif,penilaian_esrs.diskilidah,penilaian_esrs.rasiodiskilidah,penilaian_esrs.skordiskilidah,penilaian_esrs.diskirahang,penilaian_esrs.rasiodiskirahang,penilaian_esrs.skordiskirahang,penilaian_esrs.diskipipi,penilaian_esrs.rasiodiskipipi,penilaian_esrs.skordiskipipi,"+
                "penilaian_esrs.diskibadan,penilaian_esrs.rasiodiskibadan,penilaian_esrs.skordiskibadan,penilaian_esrs.diskieksatas,penilaian_esrs.rasiodiskieksatas,penilaian_esrs.skordiskieksatas,penilaian_esrs.diskieksbawah,penilaian_esrs.rasiodiskieksbawah,penilaian_esrs.skordiskieskbawah,penilaian_esrs.diskiinvol,penilaian_esrs.rasiodiskiinvol,penilaian_esrs.skordiskiinvol "+
                        
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_esrs on reg_periksa.no_rawat=penilaian_esrs.no_rawat "+
                "inner join dokter on penilaian_esrs.kd_dokter=dokter.kd_dokter where penilaian_esrs.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
            
//            Valid.MyReportqry("rptCetakPenilaianGejalaEkstrapiramidal.jasper","report","::[ Laporan Penilaian Gejala Ekstrapiramidal Halaman 1 ]::",
//                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_esrs.tanggal,"+
//                "penilaian_esrs.kd_dokter,dokter.nm_dokter,penilaian_esrs.anamnesis,penilaian_esrs.hubungan," + 
//                        
//                "penilaian_esrs.kues_lambat,penilaian_esrs.kues_berjalan,penilaian_esrs.kues_menelan,penilaian_esrs.kues_kaku,penilaian_esrs.kues_kram,penilaian_esrs.kues_gelisah,penilaian_esrs.kues_tremor,penilaian_esrs.kues_postur,penilaian_esrs.kues_ludah,penilaian_esrs.kues_diskinesiagerak,penilaian_esrs.kues_diskinesialidah,penilaian_esrs.kues_pusing,"+
//
//                "penilaian_esrs.mukatopeng,penilaian_esrs.skormuka,penilaian_esrs.bradi,penilaian_esrs.skorbradi,"+
//
//                "penilaian_esrs.rigi_ekanana,penilaian_esrs.skorekanana,penilaian_esrs.rigi_ekiria,penilaian_esrs.skorekiria,penilaian_esrs.rigi_ekananb,penilaian_esrs.skorekananb,penilaian_esrs.rigi_ekirib,penilaian_esrs.skorekirib,penilaian_esrs.skorrigid,"+
//                "penilaian_esrs.postur,penilaian_esrs.skorjalan,penilaian_esrs.ketlengka,penilaian_esrs.rasiolengka,penilaian_esrs.skorlengka,penilaian_esrs.ketlengki,penilaian_esrs.rasiolengki,penilaian_esrs.skorlengki,penilaian_esrs.ketkaka,penilaian_esrs.rasiokaka,"+
//                "penilaian_esrs.skorkaka,penilaian_esrs.ketkaki,penilaian_esrs.rasiokaki,penilaian_esrs.skorkaki,penilaian_esrs.ketkepala,penilaian_esrs.rasiokepala,penilaian_esrs.skorkepala,penilaian_esrs.ketrahang,penilaian_esrs.rasiorahang,penilaian_esrs.skorrahang,penilaian_esrs.ketlidah,"+
//                "penilaian_esrs.rasiolidah,penilaian_esrs.skorlidah,penilaian_esrs.ketbibir,penilaian_esrs.rasiobibir,penilaian_esrs.skorbibir,penilaian_esrs.skortremor,penilaian_esrs.akatisia,penilaian_esrs.skorakatisia,penilaian_esrs.sialorhoe,penilaian_esrs.skorsialorhoe,penilaian_esrs.stabilitas,penilaian_esrs.skorstabilitas,"+
//                "penilaian_esrs.torsitangka,penilaian_esrs.skortorsitangka,penilaian_esrs.torsitangki,penilaian_esrs.skortorsitangki,penilaian_esrs.torsikaka,penilaian_esrs.skortorsikaka,penilaian_esrs.torsikaki,penilaian_esrs.skortorsikaki,penilaian_esrs.torsikepala,penilaian_esrs.skortorsikepala,penilaian_esrs.torsirahang,"+
//                "penilaian_esrs.skortorsirahang,penilaian_esrs.torsilidah,penilaian_esrs.skortorsilidah,penilaian_esrs.torsibibir,penilaian_esrs.skortorsibibir,penilaian_esrs.torsitrunk,penilaian_esrs.skortorsitrunk,penilaian_esrs.skortorsi,penilaian_esrs.tardiftangka,penilaian_esrs.skortardiftangka,penilaian_esrs.tardiftangki,"+
//                "penilaian_esrs.skortardiftangki,penilaian_esrs.tardifkaka,penilaian_esrs.skortardifkaka,penilaian_esrs.tardifkaki,penilaian_esrs.skortardifkaki,penilaian_esrs.tardifkepala,penilaian_esrs.skortardifkepala,penilaian_esrs.tardifrahang,penilaian_esrs.skortardifrahang,penilaian_esrs.tardiflidah,penilaian_esrs.skortardiflidah,penilaian_esrs.tardifbibir,"+
//                "penilaian_esrs.skortardifbibir,penilaian_esrs.tardiftrunk,penilaian_esrs.skortardiftrunk,penilaian_esrs.skortardif,penilaian_esrs.diskilidah,penilaian_esrs.rasiodiskilidah,penilaian_esrs.skordiskilidah,penilaian_esrs.diskirahang,penilaian_esrs.rasiodiskirahang,penilaian_esrs.skordiskirahang,penilaian_esrs.diskipipi,penilaian_esrs.rasiodiskipipi,penilaian_esrs.skordiskipipi,"+
//                "penilaian_esrs.diskibadan,penilaian_esrs.rasiodiskibadan,penilaian_esrs.skordiskibadan,penilaian_esrs.diskieksatas,penilaian_esrs.rasiodiskieksatas,penilaian_esrs.skordiskieksatas,penilaian_esrs.diskieksbawah,penilaian_esrs.rasiodiskieksbawah,penilaian_esrs.skordiskieskbawah,penilaian_esrs.diskiinvol,penilaian_esrs.rasiodiskiinvol,penilaian_esrs.skordiskiinvol "+
//                        
//                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                "inner join penilaian_esrs on reg_periksa.no_rawat=penilaian_esrs.no_rawat "+
//                "inner join dokter on penilaian_esrs.kd_dokter=dokter.kd_dokter where penilaian_esrs.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
//            
//            
//            Valid.MyReportqry("rptCetakPenilaianGejalaEkstrapiramidal2.jasper","report","::[ Laporan Penilaian Gejala Ekstrapiramidal Halaman 2 ]::",
//                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_esrs.tanggal,"+
//                "penilaian_esrs.kd_dokter,dokter.nm_dokter,penilaian_esrs.anamnesis,penilaian_esrs.hubungan," + 
//                        
//                "penilaian_esrs.kues_lambat,penilaian_esrs.kues_berjalan,penilaian_esrs.kues_menelan,penilaian_esrs.kues_kaku,penilaian_esrs.kues_kram,penilaian_esrs.kues_gelisah,penilaian_esrs.kues_tremor,penilaian_esrs.kues_postur,penilaian_esrs.kues_ludah,penilaian_esrs.kues_diskinesiagerak,penilaian_esrs.kues_diskinesialidah,penilaian_esrs.kues_pusing,"+
//
//                "penilaian_esrs.mukatopeng,penilaian_esrs.skormuka,penilaian_esrs.bradi,penilaian_esrs.skorbradi,"+
//
//                "penilaian_esrs.rigi_ekanana,penilaian_esrs.skorekanana,penilaian_esrs.rigi_ekiria,penilaian_esrs.skorekiria,penilaian_esrs.rigi_ekananb,penilaian_esrs.skorekananb,penilaian_esrs.rigi_ekirib,penilaian_esrs.skorekirib,penilaian_esrs.skorrigid,"+
//                "penilaian_esrs.postur,penilaian_esrs.skorjalan,penilaian_esrs.ketlengka,penilaian_esrs.rasiolengka,penilaian_esrs.skorlengka,penilaian_esrs.ketlengki,penilaian_esrs.rasiolengki,penilaian_esrs.skorlengki,penilaian_esrs.ketkaka,penilaian_esrs.rasiokaka,"+
//                "penilaian_esrs.skorkaka,penilaian_esrs.ketkaki,penilaian_esrs.rasiokaki,penilaian_esrs.skorkaki,penilaian_esrs.ketkepala,penilaian_esrs.rasiokepala,penilaian_esrs.skorkepala,penilaian_esrs.ketrahang,penilaian_esrs.rasiorahang,penilaian_esrs.skorrahang,penilaian_esrs.ketlidah,"+
//                "penilaian_esrs.rasiolidah,penilaian_esrs.skorlidah,penilaian_esrs.ketbibir,penilaian_esrs.rasiobibir,penilaian_esrs.skorbibir,penilaian_esrs.skortremor,penilaian_esrs.akatisia,penilaian_esrs.skorakatisia,penilaian_esrs.sialorhoe,penilaian_esrs.skorsialorhoe,penilaian_esrs.stabilitas,penilaian_esrs.skorstabilitas,"+
//                "penilaian_esrs.torsitangka,penilaian_esrs.skortorsitangka,penilaian_esrs.torsitangki,penilaian_esrs.skortorsitangki,penilaian_esrs.torsikaka,penilaian_esrs.skortorsikaka,penilaian_esrs.torsikaki,penilaian_esrs.skortorsikaki,penilaian_esrs.torsikepala,penilaian_esrs.skortorsikepala,penilaian_esrs.torsirahang,"+
//                "penilaian_esrs.skortorsirahang,penilaian_esrs.torsilidah,penilaian_esrs.skortorsilidah,penilaian_esrs.torsibibir,penilaian_esrs.skortorsibibir,penilaian_esrs.torsitrunk,penilaian_esrs.skortorsitrunk,penilaian_esrs.skortorsi,penilaian_esrs.tardiftangka,penilaian_esrs.skortardiftangka,penilaian_esrs.tardiftangki,"+
//                "penilaian_esrs.skortardiftangki,penilaian_esrs.tardifkaka,penilaian_esrs.skortardifkaka,penilaian_esrs.tardifkaki,penilaian_esrs.skortardifkaki,penilaian_esrs.tardifkepala,penilaian_esrs.skortardifkepala,penilaian_esrs.tardifrahang,penilaian_esrs.skortardifrahang,penilaian_esrs.tardiflidah,penilaian_esrs.skortardiflidah,penilaian_esrs.tardifbibir,"+
//                "penilaian_esrs.skortardifbibir,penilaian_esrs.tardiftrunk,penilaian_esrs.skortardiftrunk,penilaian_esrs.skortardif,penilaian_esrs.diskilidah,penilaian_esrs.rasiodiskilidah,penilaian_esrs.skordiskilidah,penilaian_esrs.diskirahang,penilaian_esrs.rasiodiskirahang,penilaian_esrs.skordiskirahang,penilaian_esrs.diskipipi,penilaian_esrs.rasiodiskipipi,penilaian_esrs.skordiskipipi,"+
//                "penilaian_esrs.diskibadan,penilaian_esrs.rasiodiskibadan,penilaian_esrs.skordiskibadan,penilaian_esrs.diskieksatas,penilaian_esrs.rasiodiskieksatas,penilaian_esrs.skordiskieksatas,penilaian_esrs.diskieksbawah,penilaian_esrs.rasiodiskieksbawah,penilaian_esrs.skordiskieskbawah,penilaian_esrs.diskiinvol,penilaian_esrs.rasiodiskiinvol,penilaian_esrs.skordiskiinvol "+
//                        
//                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                "inner join penilaian_esrs on reg_periksa.no_rawat=penilaian_esrs.no_rawat "+
//                "inner join dokter on penilaian_esrs.kd_dokter=dokter.kd_dokter where penilaian_esrs.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
//        
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void SulitTugasrutinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SulitTugasrutinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SulitTugasrutinKeyPressed

    private void SulitBerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SulitBerjalanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SulitBerjalanKeyPressed

    private void SulitMenelanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SulitMenelanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SulitMenelanKeyPressed

    private void KakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KakuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KakuKeyPressed

    private void KramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KramKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KramKeyPressed

    private void EKananAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKananAtasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKananAtasKeyPressed

    private void TremorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TremorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TremorKeyPressed

    private void LudahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LudahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LudahKeyPressed

    private void PosturAbnormalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PosturAbnormalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PosturAbnormalKeyPressed

    private void DiskinesiaGerakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiskinesiaGerakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskinesiaGerakKeyPressed

    private void PusingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PusingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PusingKeyPressed

    private void DiskinesiaLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiskinesiaLidahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskinesiaLidahKeyPressed

    private void GPenurunan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GPenurunan1ActionPerformed
        if(GPenurunan1.isSelected()){
            SkorGerakan.setText("1");
        }
    }//GEN-LAST:event_GPenurunan1ActionPerformed

    private void GNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GNormalActionPerformed
        if(GNormal.isSelected()){
            SkorGerakan.setText("0");
        }
//        isTotalSkor();
    }//GEN-LAST:event_GNormalActionPerformed

    private void GSenyumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GSenyumActionPerformed
        if(GSenyum.isSelected()){
            SkorGerakan.setText("3");
        }
    }//GEN-LAST:event_GSenyumActionPerformed

    private void GPenurunan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GPenurunan2ActionPerformed
        if(GPenurunan2.isSelected()){
            SkorGerakan.setText("2");
        }
    }//GEN-LAST:event_GPenurunan2ActionPerformed

    private void GMuka1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GMuka1ActionPerformed
        if(GMuka1.isSelected()){
            SkorGerakan.setText("5");
        }
    }//GEN-LAST:event_GMuka1ActionPerformed

    private void GSenyum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GSenyum2ActionPerformed
        if(GSenyum2.isSelected()){
            SkorGerakan.setText("4");
        }
    }//GEN-LAST:event_GSenyum2ActionPerformed

    private void GMuka2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GMuka2ActionPerformed
        if(GMuka2.isSelected()){
            SkorGerakan.setText("6");
        }
    }//GEN-LAST:event_GMuka2ActionPerformed

    private void BLambat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLambat1ActionPerformed
        if(BLambat1.isSelected()){
            SkorBradi.setText("1");
        }
    }//GEN-LAST:event_BLambat1ActionPerformed

    private void BNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNormalActionPerformed
        if(BNormal.isSelected()){
            SkorBradi.setText("0");
        }
    }//GEN-LAST:event_BNormalActionPerformed

    private void BSulit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSulit1ActionPerformed
        if(BSulit1.isSelected()){
            SkorBradi.setText("3");
        }
    }//GEN-LAST:event_BSulit1ActionPerformed

    private void BLambat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLambat2ActionPerformed
        if(BLambat2.isSelected()){
            SkorBradi.setText("2");
        }
    }//GEN-LAST:event_BLambat2ActionPerformed

    private void BSulit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSulit3ActionPerformed
        if(BSulit3.isSelected()){
            SkorBradi.setText("5");
        }
    }//GEN-LAST:event_BSulit3ActionPerformed

    private void BSulit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSulit2ActionPerformed
        if(BSulit2.isSelected()){
            SkorBradi.setText("4");
        }
    }//GEN-LAST:event_BSulit2ActionPerformed

    private void BJarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BJarangActionPerformed
        if(BJarang.isSelected()){
            SkorBradi.setText("6");
        }
    }//GEN-LAST:event_BJarangActionPerformed

    private void GelisahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GelisahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GelisahKeyPressed

    private void EKiriAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKiriAtasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKiriAtasKeyPressed

    private void EKananBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKananBawahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKananBawahKeyPressed

    private void EKiriBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EKiriBawahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKiriBawahKeyPressed

    private void SkorRigidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorRigidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorRigidActionPerformed

    private void EKananAtasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EKananAtasItemStateChanged
        if(EKananAtas.getSelectedIndex()==0){
            SkorKananAtas.setText("0");
        }else if (EKananAtas.getSelectedIndex()==1){
            SkorKananAtas.setText("1");
        }else if (EKananAtas.getSelectedIndex()==2){
            SkorKananAtas.setText("2");
        }else if (EKananAtas.getSelectedIndex()==3){
            SkorKananAtas.setText("3");
        }else if (EKananAtas.getSelectedIndex()==4){
            SkorKananAtas.setText("4");
        }else if (EKananAtas.getSelectedIndex()==5){
            SkorKananAtas.setText("5");
        }else{
            SkorKananAtas.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_EKananAtasItemStateChanged

    private void EKiriAtasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EKiriAtasItemStateChanged
        if(EKiriAtas.getSelectedIndex()==0){
            SkorKiriAtas.setText("0");
        }else if (EKiriAtas.getSelectedIndex()==1){
            SkorKiriAtas.setText("1");
        }else if (EKiriAtas.getSelectedIndex()==2){
            SkorKiriAtas.setText("2");
        }else if (EKiriAtas.getSelectedIndex()==3){
            SkorKiriAtas.setText("3");
        }else if (EKiriAtas.getSelectedIndex()==4){
            SkorKiriAtas.setText("4");
        }else if (EKiriAtas.getSelectedIndex()==5){
            SkorKiriAtas.setText("5");
        }else{
            SkorKiriAtas.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_EKiriAtasItemStateChanged

    private void EKananBawahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EKananBawahItemStateChanged
        if(EKananBawah.getSelectedIndex()==0){
            SkorKananBawah.setText("0");
        }else if (EKananBawah.getSelectedIndex()==1){
            SkorKananBawah.setText("1");
        }else if (EKananBawah.getSelectedIndex()==2){
            SkorKananBawah.setText("2");
        }else if (EKananBawah.getSelectedIndex()==3){
            SkorKananBawah.setText("3");
        }else if (EKananBawah.getSelectedIndex()==4){
            SkorKananBawah.setText("4");
        }else if (EKananBawah.getSelectedIndex()==5){
            SkorKananBawah.setText("5");
        }else{
            SkorKananBawah.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_EKananBawahItemStateChanged

    private void EKiriBawahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EKiriBawahItemStateChanged
        if(EKiriBawah.getSelectedIndex()==0){
            SkorKiriBawah.setText("0");
        }else if (EKiriBawah.getSelectedIndex()==1){
            SkorKiriBawah.setText("1");
        }else if (EKiriBawah.getSelectedIndex()==2){
            SkorKiriBawah.setText("2");
        }else if (EKiriBawah.getSelectedIndex()==3){
            SkorKiriBawah.setText("3");
        }else if (EKiriBawah.getSelectedIndex()==4){
            SkorKiriBawah.setText("4");
        }else if (EKiriBawah.getSelectedIndex()==5){
            SkorKiriBawah.setText("5");
        }else{
            SkorKiriBawah.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_EKiriBawahItemStateChanged

    private void JTurunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTurunActionPerformed
        if(JTurun.isSelected()){
            SkorJalan.setText("1");
        }
    }//GEN-LAST:event_JTurunActionPerformed

    private void JNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JNormalActionPerformed
        if(JNormal.isSelected()){
            SkorJalan.setText("0");
        }
    }//GEN-LAST:event_JNormalActionPerformed

    private void JAyunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JAyunanActionPerformed
        if(JAyunan.isSelected()){
            SkorJalan.setText("3");
        }
    }//GEN-LAST:event_JAyunanActionPerformed

    private void JTurun2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTurun2ActionPerformed
        if(JTurun2.isSelected()){
            SkorJalan.setText("2");
        }
    }//GEN-LAST:event_JTurun2ActionPerformed

    private void JNyataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JNyataActionPerformed
       if(JNyata.isSelected()){
            SkorJalan.setText("5");
        }
    }//GEN-LAST:event_JNyataActionPerformed

    private void JKakuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JKakuActionPerformed
        if(JKaku.isSelected()){
            SkorJalan.setText("4");
        }
    }//GEN-LAST:event_JKakuActionPerformed

    private void JFlexiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JFlexiActionPerformed
        if(JFlexi.isSelected()){
            SkorJalan.setText("6");
        }
    }//GEN-LAST:event_JFlexiActionPerformed

    private void SkorBradiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorBradiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorBradiActionPerformed

    private void TLengkiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TLengkiItemStateChanged
        if(TLengki.getSelectedIndex()==0){
            SkorLengki.setText("0");
            SekaliLengki.setEnabled(false);
            SeringLengki.setEnabled(false);
            SelaluLengki.setEnabled(false);
            isTotalSkor();
        }else if (TLengki.getSelectedIndex()==1){
            SkorLengki.setText("1");
            SekaliLengki.setEnabled(false);
            SeringLengki.setEnabled(false);
            SelaluLengki.setEnabled(false);
            isTotalSkor();
        }else if (TLengki.getSelectedIndex()>=2){
            SekaliLengki.setEnabled(true);
            SeringLengki.setEnabled(true);
            SelaluLengki.setEnabled(true);
            SkorLengki.setText("0");
        }
    }//GEN-LAST:event_TLengkiItemStateChanged

    private void TLengkiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLengkiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLengkiKeyPressed

    private void SkorTremorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorTremorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorTremorActionPerformed

    private void TLengkaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TLengkaItemStateChanged
          
        if(TLengka.getSelectedIndex()==0){
            SkorLengka.setText("0");
            SekaliLengka.setEnabled(false);
            SeringLengka.setEnabled(false);
            SelaluLengka.setEnabled(false);
            isTotalSkor();
        }else if (TLengka.getSelectedIndex()==1){
            SkorLengka.setText("1");
            SekaliLengka.setEnabled(false);
            SeringLengka.setEnabled(false);
            SelaluLengka.setEnabled(false);
            isTotalSkor();
        }else if (TLengka.getSelectedIndex()>=2){
            SekaliLengka.setEnabled(true);
            SeringLengka.setEnabled(true);
            SelaluLengka.setEnabled(true);
            SkorLengka.setText("0");
        }
    }//GEN-LAST:event_TLengkaItemStateChanged

    private void TLengkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLengkaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLengkaKeyPressed

    private void TKakiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TKakiItemStateChanged
        if(TKaki.getSelectedIndex()==0){
            SkorKaki.setText("0");
            SekaliKaki.setEnabled(false);
            SeringKaki.setEnabled(false);
            SelaluKaki.setEnabled(false);
            isTotalSkor();
        }else if (TKaki.getSelectedIndex()==1){
            SkorKaki.setText("1");
            SekaliKaki.setEnabled(false);
            SeringKaki.setEnabled(false);
            SelaluKaki.setEnabled(false);
            isTotalSkor();
        }else if (TKaki.getSelectedIndex()>=2){
            SekaliKaki.setEnabled(true);
            SeringKaki.setEnabled(true);
            SelaluKaki.setEnabled(true);
            SkorKaki.setText("0");
        }
    }//GEN-LAST:event_TKakiItemStateChanged

    private void TKakiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKakiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKakiKeyPressed

    private void TKakaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TKakaItemStateChanged
        if(TKaka.getSelectedIndex()==0){
            SkorKaka.setText("0");
            SekaliKaka.setEnabled(false);
            SeringKaka.setEnabled(false);
            SelaluKaka.setEnabled(false);
            isTotalSkor();
        }else if (TKaka.getSelectedIndex()==1){
            SkorKaka.setText("1");
            SekaliKaka.setEnabled(false);
            SeringKaka.setEnabled(false);
            SelaluKaka.setEnabled(false);
            isTotalSkor();
        }else if (TKaka.getSelectedIndex()>=2){
            SekaliKaka.setEnabled(true);
            SeringKaka.setEnabled(true);
            SelaluKaka.setEnabled(true);
            SkorKaka.setText("0");
        }
    }//GEN-LAST:event_TKakaItemStateChanged

    private void TKakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKakaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKakaKeyPressed

    private void TPipiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TPipiItemStateChanged
        if(TPipi.getSelectedIndex()==0){
            SkorPipi.setText("0");
            SekaliPipi.setEnabled(false);
            SeringPipi.setEnabled(false);
            SelaluPipi.setEnabled(false);
            isTotalSkor();
        }else if (TPipi.getSelectedIndex()==1){
            SkorPipi.setText("1");
            SekaliPipi.setEnabled(false);
            SeringPipi.setEnabled(false);
            SelaluPipi.setEnabled(false);
            isTotalSkor();
        }else if (TPipi.getSelectedIndex()>=2){
            SekaliPipi.setEnabled(true);
            SeringPipi.setEnabled(true);
            SelaluPipi.setEnabled(true);
            SkorPipi.setText("0");
        }
    }//GEN-LAST:event_TPipiItemStateChanged

    private void TPipiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPipiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPipiKeyPressed

    private void TKepalaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TKepalaItemStateChanged
        if(TKepala.getSelectedIndex()==0){
            SkorKepala.setText("0");
            SekaliKepala.setEnabled(false);
            SeringKepala.setEnabled(false);
            SelaluKepala.setEnabled(false);
            isTotalSkor();
        }else if (TKepala.getSelectedIndex()==1){
            SkorKepala.setText("1");
            SekaliKepala.setEnabled(false);
            SeringKepala.setEnabled(false);
            SelaluKepala.setEnabled(false);
            isTotalSkor();
        }else if (TKepala.getSelectedIndex()>=2){
            SekaliKepala.setEnabled(true);
            SeringKepala.setEnabled(true);
            SelaluKepala.setEnabled(true);
            SkorKepala.setText("0");
        }
    }//GEN-LAST:event_TKepalaItemStateChanged

    private void TKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKepalaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKepalaKeyPressed

    private void TBibirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TBibirItemStateChanged
        if(TBibir.getSelectedIndex()==0){
            SkorBibir.setText("0");
            SekaliBibir.setEnabled(false);
            SeringBibir.setEnabled(false);
            SelaluBibir.setEnabled(false);
            isTotalSkor();
        }else if (TBibir.getSelectedIndex()==1){
            SkorBibir.setText("1");
            SekaliBibir.setEnabled(false);
            SeringBibir.setEnabled(false);
            SelaluBibir.setEnabled(false);
            isTotalSkor();
        }else if (TBibir.getSelectedIndex()>=2){
            SekaliBibir.setEnabled(true);
            SeringBibir.setEnabled(true);
            SelaluBibir.setEnabled(true);
            SkorBibir.setText("0");
        }
    }//GEN-LAST:event_TBibirItemStateChanged

    private void TBibirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBibirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBibirKeyPressed

    private void TLidahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TLidahItemStateChanged
        if(TLidah.getSelectedIndex()==0){
            SkorLidah.setText("0");
            SekaliLidah.setEnabled(false);
            SeringLidah.setEnabled(false);
            SelaluLidah.setEnabled(false);
            isTotalSkor();
        }else if (TLidah.getSelectedIndex()==1){
            SkorLidah.setText("1");
            SekaliLidah.setEnabled(false);
            SeringLidah.setEnabled(false);
            SelaluLidah.setEnabled(false);
            isTotalSkor();
        }else if (TLidah.getSelectedIndex()>=2){
            SekaliLidah.setEnabled(true);
            SeringLidah.setEnabled(true);
            SelaluLidah.setEnabled(true);
            SkorLidah.setText("0");
        }
    }//GEN-LAST:event_TLidahItemStateChanged

    private void TLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLidahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLidahKeyPressed

    private void SeringLengkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringLengkaActionPerformed
         if (SeringLengka.isSelected()){
            if (TLengka.getSelectedIndex()==2){
                SkorLengka.setText("3");
            }else if (TLengka.getSelectedIndex()==3){
                SkorLengka.setText("4");
            }else{
                SkorLengka.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringLengkaActionPerformed

    private void SelaluLengkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluLengkaActionPerformed
        if (SelaluLengka.isSelected()){
            if (TLengka.getSelectedIndex()==2){
                SkorLengka.setText("4");
            }else if (TLengka.getSelectedIndex()==3){
                SkorLengka.setText("5");
            }else{
                SkorLengka.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluLengkaActionPerformed

    private void SeringLengkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringLengkiActionPerformed
        if (SeringLengki.isSelected()){
            if (TLengki.getSelectedIndex()==2){
                SkorLengki.setText("3");
            }else if (TLengki.getSelectedIndex()==3){
                SkorLengki.setText("4");
            }else{
                SkorLengki.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringLengkiActionPerformed

    private void SelaluLengkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluLengkiActionPerformed
        if (SelaluLengki.isSelected()){
            if (TLengki.getSelectedIndex()==2){
                SkorLengki.setText("4");
            }else if (TLengki.getSelectedIndex()==3){
                SkorLengki.setText("5");
            }else{
                SkorLengki.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluLengkiActionPerformed

    private void SekaliLengkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliLengkaActionPerformed
        if (SekaliLengka.isSelected()){
            if (TLengka.getSelectedIndex()==2){
                SkorLengka.setText("2");
            }else if (TLengka.getSelectedIndex()==3){
                SkorLengka.setText("3");
            }else{
                SkorLengka.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliLengkaActionPerformed

    private void TLengkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TLengkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLengkaActionPerformed

    private void SkorJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorJalanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorJalanActionPerformed

    private void SkorLengkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorLengkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorLengkaActionPerformed

    private void TLengkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TLengkiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TLengkiActionPerformed

    private void SekaliLengkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliLengkiActionPerformed
        if (SekaliLengki.isSelected()){
            if (TLengki.getSelectedIndex()==2){
                SkorLengki.setText("2");
            }else if (TLengki.getSelectedIndex()==3){
                SkorLengki.setText("3");
            }else{
                SkorLengki.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliLengkiActionPerformed

    private void SekaliKakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliKakaActionPerformed
        if (SekaliKaka.isSelected()){
            if (TKaka.getSelectedIndex()==2){
                SkorKaka.setText("2");
            }else if (TKaka.getSelectedIndex()==3){
                SkorKaka.setText("3");
            }else{
                SkorKaka.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliKakaActionPerformed

    private void SeringKakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringKakaActionPerformed
        if (SeringKaka.isSelected()){
            if (TKaka.getSelectedIndex()==2){
                SkorKaka.setText("3");
            }else if (TKaka.getSelectedIndex()==3){
                SkorKaka.setText("4");
            }else{
                SkorKaka.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringKakaActionPerformed

    private void SelaluKakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluKakaActionPerformed
        if (SelaluKaka.isSelected()){
            if (TKaka.getSelectedIndex()==2){
                SkorKaka.setText("4");
            }else if (TKaka.getSelectedIndex()==3){
                SkorKaka.setText("5");
            }else{
                SkorKaka.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluKakaActionPerformed

    private void SekaliKakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliKakiActionPerformed
        if (SekaliKaki.isSelected()){
            if (TKaki.getSelectedIndex()==2){
                SkorKaki.setText("2");
            }else if (TKaki.getSelectedIndex()==3){
                SkorKaki.setText("3");
            }else{
                SkorKaki.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliKakiActionPerformed

    private void SeringKakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringKakiActionPerformed
        if (SeringKaki.isSelected()){
            if (TKaki.getSelectedIndex()==2){
                SkorKaki.setText("3");
            }else if (TKaki.getSelectedIndex()==3){
                SkorKaki.setText("4");
            }else{
                SkorKaki.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringKakiActionPerformed

    private void SelaluKakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluKakiActionPerformed
        if (SelaluKaki.isSelected()){
            if (TKaki.getSelectedIndex()==2){
                SkorKaki.setText("4");
            }else if (TKaki.getSelectedIndex()==3){
                SkorKaki.setText("5");
            }else{
                SkorKaki.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluKakiActionPerformed

    private void SekaliKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliKepalaActionPerformed
        if (SekaliKepala.isSelected()){
            if (TKepala.getSelectedIndex()==2){
                SkorKepala.setText("2");
            }else if (TKepala.getSelectedIndex()==3){
                SkorKepala.setText("3");
            }else{
                SkorKepala.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliKepalaActionPerformed

    private void SeringKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringKepalaActionPerformed
        if (SeringKepala.isSelected()){
            if (TKepala.getSelectedIndex()==2){
                SkorKepala.setText("3");
            }else if (TKepala.getSelectedIndex()==3){
                SkorKepala.setText("4");
            }else{
                SkorKepala.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringKepalaActionPerformed

    private void SelaluKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluKepalaActionPerformed
        if (SelaluKepala.isSelected()){
            if (TKepala.getSelectedIndex()==2){
                SkorKepala.setText("4");
            }else if (TKepala.getSelectedIndex()==3){
                SkorKepala.setText("5");
            }else{
                SkorKepala.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluKepalaActionPerformed

    private void SekaliPipiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliPipiActionPerformed
        if (SekaliPipi.isSelected()){
            if (TPipi.getSelectedIndex()==2){
                SkorPipi.setText("2");
            }else if (TPipi.getSelectedIndex()==3){
                SkorPipi.setText("3");
            }else{
                SkorPipi.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliPipiActionPerformed

    private void SeringPipiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringPipiActionPerformed
        if (SeringPipi.isSelected()){
            if (TPipi.getSelectedIndex()==2){
                SkorPipi.setText("3");
            }else if (TPipi.getSelectedIndex()==3){
                SkorPipi.setText("4");
            }else{
                SkorPipi.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringPipiActionPerformed

    private void SelaluPipiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluPipiActionPerformed
        if (SelaluPipi.isSelected()){
            if (TPipi.getSelectedIndex()==2){
                SkorPipi.setText("4");
            }else if (TPipi.getSelectedIndex()==3){
                SkorPipi.setText("5");
            }else{
                SkorPipi.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluPipiActionPerformed

    private void SekaliLidahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliLidahActionPerformed
        if (SekaliLidah.isSelected()){
            if (TLidah.getSelectedIndex()==2){
                SkorLidah.setText("2");
            }else if (TLidah.getSelectedIndex()==3){
                SkorLidah.setText("3");
            }else{
                SkorLidah.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliLidahActionPerformed

    private void SeringLidahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringLidahActionPerformed
        if (SeringLidah.isSelected()){
            if (TLidah.getSelectedIndex()==2){
                SkorLidah.setText("3");
            }else if (TLidah.getSelectedIndex()==3){
                SkorLidah.setText("4");
            }else{
                SkorLidah.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringLidahActionPerformed

    private void SelaluLidahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluLidahActionPerformed
        if (SelaluLidah.isSelected()){
            if (TLidah.getSelectedIndex()==2){
                SkorLidah.setText("4");
            }else if (TLidah.getSelectedIndex()==3){
                SkorLidah.setText("5");
            }else{
                SkorLidah.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluLidahActionPerformed

    private void SekaliBibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SekaliBibirActionPerformed
       if (SekaliBibir.isSelected()){
            if (TBibir.getSelectedIndex()==2){
                SkorBibir.setText("2");
            }else if (TBibir.getSelectedIndex()==3){
                SkorBibir.setText("3");
            }else{
                SkorBibir.setText("4");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SekaliBibirActionPerformed

    private void SeringBibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeringBibirActionPerformed
        if (SeringBibir.isSelected()){
            if (TBibir.getSelectedIndex()==2){
                SkorBibir.setText("3");
            }else if (TBibir.getSelectedIndex()==3){
                SkorBibir.setText("4");
            }else{
                SkorBibir.setText("5");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SeringBibirActionPerformed

    private void SelaluBibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelaluBibirActionPerformed
        if (SelaluBibir.isSelected()){
            if (TBibir.getSelectedIndex()==2){
                SkorBibir.setText("4");
            }else if (TBibir.getSelectedIndex()==3){
                SkorBibir.setText("5");
            }else{
                SkorBibir.setText("6");
            }   
        }isTotalSkor();
    }//GEN-LAST:event_SelaluBibirActionPerformed

    private void SkorKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorKepalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorKepalaActionPerformed

    private void SkorKakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorKakaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorKakaActionPerformed

    private void SkorPipiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorPipiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorPipiActionPerformed

    private void SkorLidahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorLidahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorLidahActionPerformed

    private void TKakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKakaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKakaActionPerformed

    private void TKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKepalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKepalaActionPerformed

    private void AGelisahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGelisahActionPerformed
        if(AGelisah.isSelected()){
            SkorAkatisia.setText("1");
        }
    }//GEN-LAST:event_AGelisahActionPerformed

    private void ATidakadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATidakadaActionPerformed
        if(ATidakada.isSelected()){
            SkorAkatisia.setText("0");
        }
    }//GEN-LAST:event_ATidakadaActionPerformed

    private void AGerakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGerakActionPerformed
         if(AGerak.isSelected()){
            SkorAkatisia.setText("3");
        }
    }//GEN-LAST:event_AGerakActionPerformed

    private void AInginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AInginActionPerformed
         if(AIngin.isSelected()){
            SkorAkatisia.setText("2");
        }
    }//GEN-LAST:event_AInginActionPerformed

    private void ADudukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADudukActionPerformed
        if(ADuduk.isSelected()){
            SkorAkatisia.setText("5");
        }
    }//GEN-LAST:event_ADudukActionPerformed

    private void AHentakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AHentakActionPerformed
        if(AHentak.isSelected()){
            SkorAkatisia.setText("4");
        }
    }//GEN-LAST:event_AHentakActionPerformed

    private void ATerusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATerusActionPerformed
        if(ATerus.isSelected()){
            SkorAkatisia.setText("6");
        }
    }//GEN-LAST:event_ATerusActionPerformed

    private void SkorAkatisiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorAkatisiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorAkatisiaActionPerformed

    private void SgtRinganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SgtRinganActionPerformed
        if(SgtRingan.isSelected()){
            SkorSialorhoe.setText("1");
        }
    }//GEN-LAST:event_SgtRinganActionPerformed

    private void STidakadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STidakadaActionPerformed
        if(STidakada.isSelected()){
            SkorSialorhoe.setText("0");
        }
    }//GEN-LAST:event_STidakadaActionPerformed

    private void SsedangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SsedangActionPerformed
        if(Ssedang.isSelected()){
            SkorSialorhoe.setText("3");
        }
    }//GEN-LAST:event_SsedangActionPerformed

    private void SRinganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SRinganActionPerformed
        if(SRingan.isSelected()){
            SkorSialorhoe.setText("2");
        }
    }//GEN-LAST:event_SRinganActionPerformed

    private void SBeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SBeratActionPerformed
        if(SBerat.isSelected()){
            SkorSialorhoe.setText("5");
        }
    }//GEN-LAST:event_SBeratActionPerformed

    private void SAgakberatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAgakberatActionPerformed
        if(SAgakberat.isSelected()){
            SkorSialorhoe.setText("4");
        }
    }//GEN-LAST:event_SAgakberatActionPerformed

    private void SSangatberatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SSangatberatActionPerformed
        if(SSangatberat.isSelected()){
            SkorSialorhoe.setText("6");
        }
    }//GEN-LAST:event_SSangatberatActionPerformed

    private void SkorSialorhoeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorSialorhoeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorSialorhoeActionPerformed

    private void StabLambatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabLambatActionPerformed
        if(StabLambat.isSelected()){
            SkorStabilitas.setText("1");
        }
    }//GEN-LAST:event_StabLambatActionPerformed

    private void StabTidakadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabTidakadaActionPerformed
        if(StabTidakada.isSelected()){
            SkorStabilitas.setText("0");
        }
    }//GEN-LAST:event_StabTidakadaActionPerformed

    private void StabRetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabRetro1ActionPerformed
        if(StabRetro1.isSelected()){
            SkorStabilitas.setText("3");
        }
    }//GEN-LAST:event_StabRetro1ActionPerformed

    private void StabRetroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabRetroActionPerformed
        if(StabRetro.isSelected()){
            SkorStabilitas.setText("2");
        }
    }//GEN-LAST:event_StabRetroActionPerformed

    private void StabStabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabStabilActionPerformed
        if(StabStabil.isSelected()){
            SkorStabilitas.setText("5");
        }
    }//GEN-LAST:event_StabStabilActionPerformed

    private void StabResponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabResponActionPerformed
        if(StabRespon.isSelected()){
            SkorStabilitas.setText("4");
        }
    }//GEN-LAST:event_StabResponActionPerformed

    private void StabMampuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StabMampuActionPerformed
        if(StabMampu.isSelected()){
            SkorStabilitas.setText("6");
        }
    }//GEN-LAST:event_StabMampuActionPerformed

    private void SkorStabilitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorStabilitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorStabilitasActionPerformed

    private void SkorDistoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorDistoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorDistoniaActionPerformed

    private void DistoniaTangkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaTangkaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaTangkaKeyPressed

    private void DistoniaTangkaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaTangkaItemStateChanged
        if(DistoniaTangka.getSelectedIndex()==0){
            SkorDTangka.setText("0");
        }else if (DistoniaTangka.getSelectedIndex()==1){
            SkorDTangka.setText("1");
        }else if (DistoniaTangka.getSelectedIndex()==2){
            SkorDTangka.setText("2");
        }else if (DistoniaTangka.getSelectedIndex()==3){
            SkorDTangka.setText("3");
        }else if (DistoniaTangka.getSelectedIndex()==4){
            SkorDTangka.setText("4");
        }else if (DistoniaTangka.getSelectedIndex()==5){
            SkorDTangka.setText("5");
        }else{
            SkorDTangka.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_DistoniaTangkaItemStateChanged

    private void DistoniaTangkiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaTangkiItemStateChanged
        if(DistoniaTangki.getSelectedIndex()==0){
            SkorDTangki.setText("0");
        }else if (DistoniaTangki.getSelectedIndex()==1){
            SkorDTangki.setText("1");
        }else if (DistoniaTangki.getSelectedIndex()==2){
            SkorDTangki.setText("2");
        }else if (DistoniaTangki.getSelectedIndex()==3){
            SkorDTangki.setText("3");
        }else if (DistoniaTangki.getSelectedIndex()==4){
            SkorDTangki.setText("4");
        }else if (DistoniaTangki.getSelectedIndex()==5){
            SkorDTangki.setText("5");
        }else{
            SkorDTangki.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_DistoniaTangkiItemStateChanged

    private void DistoniaTangkiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaTangkiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaTangkiKeyPressed

    private void DistoniaKakaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaKakaItemStateChanged
        if(DistoniaKaka.getSelectedIndex()==0){
            SkorDKaka.setText("0");
        }else if (DistoniaKaka.getSelectedIndex()==1){
            SkorDKaka.setText("1");
        }else if (DistoniaKaka.getSelectedIndex()==2){
            SkorDKaka.setText("2");
        }else if (DistoniaKaka.getSelectedIndex()==3){
            SkorDKaka.setText("3");
        }else if (DistoniaKaka.getSelectedIndex()==4){
            SkorDKaka.setText("4");
        }else if (DistoniaKaka.getSelectedIndex()==5){
            SkorDKaka.setText("5");
        }else{
            SkorDKaka.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_DistoniaKakaItemStateChanged

    private void DistoniaKakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaKakaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaKakaKeyPressed

    private void DistoniaKakiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaKakiItemStateChanged
        if(DistoniaKaki.getSelectedIndex()==0){
            SkorDKaki.setText("0");
        }else if (DistoniaKaki.getSelectedIndex()==1){
            SkorDKaki.setText("1");
        }else if (DistoniaKaki.getSelectedIndex()==2){
            SkorDKaki.setText("2");
        }else if (DistoniaKaki.getSelectedIndex()==3){
            SkorDKaki.setText("3");
        }else if (DistoniaKaki.getSelectedIndex()==4){
            SkorDKaki.setText("4");
        }else if (DistoniaKaki.getSelectedIndex()==5){
            SkorDKaki.setText("5");
        }else{
            SkorDKaki.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_DistoniaKakiItemStateChanged

    private void DistoniaKakiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaKakiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaKakiKeyPressed

    private void DistoniaRahangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaRahangItemStateChanged
         if(DistoniaRahang.getSelectedIndex()==0){
            SkorDRahang.setText("0");
        }else if (DistoniaRahang.getSelectedIndex()==1){
            SkorDRahang.setText("1");
        }else if (DistoniaRahang.getSelectedIndex()==2){
            SkorDRahang.setText("2");
        }else if (DistoniaRahang.getSelectedIndex()==3){
            SkorDRahang.setText("3");
        }else if (DistoniaRahang.getSelectedIndex()==4){
            SkorDRahang.setText("4");
        }else if (DistoniaRahang.getSelectedIndex()==5){
            SkorDRahang.setText("5");
        }else{
            SkorDRahang.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_DistoniaRahangItemStateChanged

    private void DistoniaRahangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaRahangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaRahangKeyPressed

    private void DistoniaLidahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaLidahItemStateChanged
         if(DistoniaLidah.getSelectedIndex()==0){
            SkorDLidah.setText("0");
        }else if (DistoniaLidah.getSelectedIndex()==1){
            SkorDLidah.setText("1");
        }else if (DistoniaLidah.getSelectedIndex()==2){
            SkorDLidah.setText("2");
        }else if (DistoniaLidah.getSelectedIndex()==3){
            SkorDLidah.setText("3");
        }else if (DistoniaLidah.getSelectedIndex()==4){
            SkorDLidah.setText("4");
        }else if (DistoniaLidah.getSelectedIndex()==5){
            SkorDLidah.setText("5");
        }else{
            SkorDLidah.setText("6");
        }
        isTotalSkor();
    }//GEN-LAST:event_DistoniaLidahItemStateChanged

    private void DistoniaLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaLidahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaLidahKeyPressed

    private void DistoniaBibirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaBibirItemStateChanged
         if(DistoniaBibir.getSelectedIndex()==0){
            SkorDBibir.setText("0");
        }else if (DistoniaBibir.getSelectedIndex()==1){
            SkorDBibir.setText("1");
        }else if (DistoniaBibir.getSelectedIndex()==2){
            SkorDBibir.setText("2");
        }else if (DistoniaBibir.getSelectedIndex()==3){
            SkorDBibir.setText("3");
        }else if (DistoniaBibir.getSelectedIndex()==4){
            SkorDBibir.setText("4");
        }else if (DistoniaBibir.getSelectedIndex()==5){
            SkorDBibir.setText("5");
        }else{
            SkorDBibir.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_DistoniaBibirItemStateChanged

    private void DistoniaBibirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaBibirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaBibirKeyPressed

    private void DistoniaTrunkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaTrunkItemStateChanged
         if(DistoniaTrunk.getSelectedIndex()==0){
            SkorDTrunk.setText("0");
        }else if (DistoniaTrunk.getSelectedIndex()==1){
            SkorDTrunk.setText("1");
        }else if (DistoniaTrunk.getSelectedIndex()==2){
            SkorDTrunk.setText("2");
        }else if (DistoniaTrunk.getSelectedIndex()==3){
            SkorDTrunk.setText("3");
        }else if (DistoniaTrunk.getSelectedIndex()==4){
            SkorDTrunk.setText("4");
        }else if (DistoniaTrunk.getSelectedIndex()==5){
            SkorDTrunk.setText("5");
        }else{
            SkorDTrunk.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_DistoniaTrunkItemStateChanged

    private void DistoniaTrunkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaTrunkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaTrunkKeyPressed

    private void DistoniaKepalaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DistoniaKepalaItemStateChanged
         if(DistoniaKepala.getSelectedIndex()==0){
            SkorDKepala.setText("0");
        }else if (DistoniaKepala.getSelectedIndex()==1){
            SkorDKepala.setText("1");
        }else if (DistoniaKepala.getSelectedIndex()==2){
            SkorDKepala.setText("2");
        }else if (DistoniaKepala.getSelectedIndex()==3){
            SkorDKepala.setText("3");
        }else if (DistoniaKepala.getSelectedIndex()==4){
            SkorDKepala.setText("4");
        }else if (DistoniaKepala.getSelectedIndex()==5){
            SkorDKepala.setText("5");
        }else{
            SkorDKepala.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_DistoniaKepalaItemStateChanged

    private void DistoniaKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistoniaKepalaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DistoniaKepalaKeyPressed

    private void EKananAtasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EKananAtasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EKananAtasActionPerformed

    private void TardifTangkaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifTangkaItemStateChanged
        if(TardifTangka.getSelectedIndex()==0){
            SkorTTangka.setText("0");
        }else if (TardifTangka.getSelectedIndex()==1){
            SkorTTangka.setText("1");
        }else if (TardifTangka.getSelectedIndex()==2){
            SkorTTangka.setText("2");
        }else if (TardifTangka.getSelectedIndex()==3){
            SkorTTangka.setText("3");
        }else if (TardifTangka.getSelectedIndex()==4){
            SkorTTangka.setText("4");
        }else if (TardifTangka.getSelectedIndex()==5){
            SkorTTangka.setText("5");
        }else{
            SkorTTangka.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifTangkaItemStateChanged

    private void TardifTangkaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifTangkaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifTangkaKeyPressed

    private void SkorTardifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkorTardifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkorTardifActionPerformed

    private void TardifTangkiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifTangkiItemStateChanged
        if(TardifTangki.getSelectedIndex()==0){
            SkorTTangki.setText("0");
        }else if (TardifTangki.getSelectedIndex()==1){
            SkorTTangki.setText("1");
        }else if (TardifTangki.getSelectedIndex()==2){
            SkorTTangki.setText("2");
        }else if (TardifTangki.getSelectedIndex()==3){
            SkorTTangki.setText("3");
        }else if (TardifTangki.getSelectedIndex()==4){
            SkorTTangki.setText("4");
        }else if (TardifTangki.getSelectedIndex()==5){
            SkorTTangki.setText("5");
        }else{
            SkorTTangki.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifTangkiItemStateChanged

    private void TardifTangkiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifTangkiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifTangkiKeyPressed

    private void TardifKakaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifKakaItemStateChanged
        if(TardifKaka.getSelectedIndex()==0){
            SkorTKaka.setText("0");
        }else if (TardifKaka.getSelectedIndex()==1){
            SkorTKaka.setText("1");
        }else if (TardifKaka.getSelectedIndex()==2){
            SkorTKaka.setText("2");
        }else if (TardifKaka.getSelectedIndex()==3){
            SkorTKaka.setText("3");
        }else if (TardifKaka.getSelectedIndex()==4){
            SkorTKaka.setText("4");
        }else if (TardifKaka.getSelectedIndex()==5){
            SkorTKaka.setText("5");
        }else{
            SkorTKaka.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifKakaItemStateChanged

    private void TardifKakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifKakaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifKakaKeyPressed

    private void TardifKakiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifKakiItemStateChanged
        if(TardifKaki.getSelectedIndex()==0){
            SkorTKaki.setText("0");
        }else if (TardifKaki.getSelectedIndex()==1){
            SkorTKaki.setText("1");
        }else if (TardifKaki.getSelectedIndex()==2){
            SkorTKaki.setText("2");
        }else if (TardifKaki.getSelectedIndex()==3){
            SkorTKaki.setText("3");
        }else if (TardifKaki.getSelectedIndex()==4){
            SkorTKaki.setText("4");
        }else if (TardifKaki.getSelectedIndex()==5){
            SkorTKaki.setText("5");
        }else{
            SkorTKaki.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifKakiItemStateChanged

    private void TardifKakiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifKakiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifKakiKeyPressed

    private void TardifRahangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifRahangItemStateChanged
        if(TardifRahang.getSelectedIndex()==0){
            SkorTRahang.setText("0");
        }else if (TardifRahang.getSelectedIndex()==1){
            SkorTRahang.setText("1");
        }else if (TardifRahang.getSelectedIndex()==2){
            SkorTRahang.setText("2");
        }else if (TardifRahang.getSelectedIndex()==3){
            SkorTRahang.setText("3");
        }else if (TardifRahang.getSelectedIndex()==4){
            SkorTRahang.setText("4");
        }else if (TardifRahang.getSelectedIndex()==5){
            SkorTRahang.setText("5");
        }else{
            SkorTRahang.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifRahangItemStateChanged

    private void TardifRahangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifRahangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifRahangKeyPressed

    private void TardifLidahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifLidahItemStateChanged
        if(TardifLidah.getSelectedIndex()==0){
            SkorTLidah.setText("0");
        }else if (TardifLidah.getSelectedIndex()==1){
            SkorTLidah.setText("1");
        }else if (TardifLidah.getSelectedIndex()==2){
            SkorTLidah.setText("2");
        }else if (TardifLidah.getSelectedIndex()==3){
            SkorTLidah.setText("3");
        }else if (TardifLidah.getSelectedIndex()==4){
            SkorTLidah.setText("4");
        }else if (TardifLidah.getSelectedIndex()==5){
            SkorTLidah.setText("5");
        }else{
            SkorTLidah.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifLidahItemStateChanged

    private void TardifLidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifLidahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifLidahKeyPressed

    private void TardifBibirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifBibirItemStateChanged
       if(TardifBibir.getSelectedIndex()==0){
            SkorTBibir.setText("0");
        }else if (TardifBibir.getSelectedIndex()==1){
            SkorTBibir.setText("1");
        }else if (TardifBibir.getSelectedIndex()==2){
            SkorTBibir.setText("2");
        }else if (TardifBibir.getSelectedIndex()==3){
            SkorTBibir.setText("3");
        }else if (TardifBibir.getSelectedIndex()==4){
            SkorTBibir.setText("4");
        }else if (TardifBibir.getSelectedIndex()==5){
            SkorTBibir.setText("5");
        }else{
            SkorTBibir.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifBibirItemStateChanged

    private void TardifBibirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifBibirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifBibirKeyPressed

    private void TardifTrunkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifTrunkItemStateChanged
        if(TardifTrunk.getSelectedIndex()==0){
            SkorTTrunk.setText("0");
        }else if (TardifTrunk.getSelectedIndex()==1){
            SkorTTrunk.setText("1");
        }else if (TardifTrunk.getSelectedIndex()==2){
            SkorTTrunk.setText("2");
        }else if (TardifTrunk.getSelectedIndex()==3){
            SkorTTrunk.setText("3");
        }else if (TardifTrunk.getSelectedIndex()==4){
            SkorTTrunk.setText("4");
        }else if (TardifTrunk.getSelectedIndex()==5){
            SkorTTrunk.setText("5");
        }else{
            SkorTTrunk.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifTrunkItemStateChanged

    private void TardifTrunkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifTrunkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifTrunkKeyPressed

    private void TardifKepalaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TardifKepalaItemStateChanged
       if(TardifKepala.getSelectedIndex()==0){
            SkorTKepala.setText("0");
        }else if (TardifKepala.getSelectedIndex()==1){
            SkorTKepala.setText("1");
        }else if (TardifKepala.getSelectedIndex()==2){
            SkorTKepala.setText("2");
        }else if (TardifKepala.getSelectedIndex()==3){
            SkorTKepala.setText("3");
        }else if (TardifKepala.getSelectedIndex()==4){
            SkorTKepala.setText("4");
        }else if (TardifKepala.getSelectedIndex()==5){
            SkorTKepala.setText("5");
        }else{
            SkorTKepala.setText("6");
        }
         isTotalSkor();
    }//GEN-LAST:event_TardifKepalaItemStateChanged

    private void TardifKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TardifKepalaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifKepalaKeyPressed

    private void radSering1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering1ActionPerformed
       if (radSering1.isSelected()){
            if (DiskiLidah.getSelectedIndex()==2){
                Skordiskilid.setText("3");
            }else if (DiskiLidah.getSelectedIndex()==3){
                Skordiskilid.setText("4");
            }else{
                Skordiskilid.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering1ActionPerformed

    private void radJarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang1ActionPerformed
       if (radJarang1.isSelected()){
            if (DiskiLidah.getSelectedIndex()==2){
                Skordiskilid.setText("2");
            }else if (DiskiLidah.getSelectedIndex()==3){
                Skordiskilid.setText("3");
            }else{
                Skordiskilid.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang1ActionPerformed

    private void SkordiskilidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskilidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskilidActionPerformed

    private void DiskiLidahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiLidahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiLidahActionPerformed

    private void DiskiLidahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiLidahItemStateChanged
        if(DiskiLidah.getSelectedIndex()==0){
            Skordiskilid.setText("0");
            radJarang1.setEnabled(false);
            radSering1.setEnabled(false);
            radSelalu1.setEnabled(false);
        }else if (DiskiLidah.getSelectedIndex()==1){
            Skordiskilid.setText("1");
            radJarang1.setEnabled(false);
            radSering1.setEnabled(false);
            radSelalu1.setEnabled(false);
        }else if (DiskiLidah.getSelectedIndex()>=2){
            radJarang1.setEnabled(true);
            radSering1.setEnabled(true);
            radSelalu1.setEnabled(true);
            Skordiskilid.setText("0");
        }
    }//GEN-LAST:event_DiskiLidahItemStateChanged

    private void radSelalu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu1ActionPerformed
        if (radSelalu1.isSelected()){
            if (DiskiLidah.getSelectedIndex()==2){
                Skordiskilid.setText("4");
            }else if (DiskiLidah.getSelectedIndex()==3){
                Skordiskilid.setText("5");
            }else{
                Skordiskilid.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu1ActionPerformed

    private void SkordiskirahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskirahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskirahActionPerformed

    private void DiskiRahangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiRahangItemStateChanged
        if(DiskiRahang.getSelectedIndex()==0){
            Skordiskirah.setText("0");
            radJarang2.setEnabled(false);
            radSering2.setEnabled(false);
            radSelalu2.setEnabled(false);
        }else if (DiskiRahang.getSelectedIndex()==1){
            Skordiskirah.setText("1");
            radJarang2.setEnabled(false);
            radSering2.setEnabled(false);
            radSelalu2.setEnabled(false);
        }else if (DiskiRahang.getSelectedIndex()>=2){
            radJarang2.setEnabled(true);
            radSering2.setEnabled(true);
            radSelalu2.setEnabled(true);
            Skordiskirah.setText("0");
        }
    }//GEN-LAST:event_DiskiRahangItemStateChanged

    private void DiskiRahangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiRahangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiRahangActionPerformed

    private void radJarang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang2ActionPerformed
        if (radJarang2.isSelected()){
            if (DiskiRahang.getSelectedIndex()==2){
                Skordiskirah.setText("2");
            }else if (DiskiRahang.getSelectedIndex()==3){
                Skordiskirah.setText("3");
            }else{
                Skordiskirah.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang2ActionPerformed

    private void radSering2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering2ActionPerformed
        if (radSering2.isSelected()){
            if (DiskiRahang.getSelectedIndex()==2){
                Skordiskirah.setText("3");
            }else if (DiskiRahang.getSelectedIndex()==3){
                Skordiskirah.setText("4");
            }else{
                Skordiskirah.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering2ActionPerformed

    private void radSelalu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu2ActionPerformed
        if (radSelalu2.isSelected()){
            if (DiskiRahang.getSelectedIndex()==2){
                Skordiskirah.setText("4");
            }else if (DiskiRahang.getSelectedIndex()==3){
                Skordiskirah.setText("5");
            }else{
                Skordiskirah.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu2ActionPerformed

    private void SkordiskipiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskipiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskipiActionPerformed

    private void DiskiBibirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiBibirItemStateChanged
        if(DiskiBibir.getSelectedIndex()==0){
            Skordiskipi.setText("0");
            radJarang3.setEnabled(false);
            radSering3.setEnabled(false);
            radSelalu3.setEnabled(false);
        }else if (DiskiBibir.getSelectedIndex()==1){
            Skordiskipi.setText("1");
            radJarang3.setEnabled(false);
            radSering3.setEnabled(false);
            radSelalu3.setEnabled(false);
        }else if (DiskiBibir.getSelectedIndex()>=2){
            radJarang3.setEnabled(true);
            radSering3.setEnabled(true);
            radSelalu3.setEnabled(true);
            Skordiskipi.setText("0");
        }
    }//GEN-LAST:event_DiskiBibirItemStateChanged

    private void DiskiBibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiBibirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiBibirActionPerformed

    private void radJarang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang3ActionPerformed
        if (radJarang3.isSelected()){
            if (DiskiBibir.getSelectedIndex()==2){
                Skordiskipi.setText("2");
            }else if (DiskiBibir.getSelectedIndex()==3){
                Skordiskipi.setText("3");
            }else{
                Skordiskipi.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang3ActionPerformed

    private void radSering3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering3ActionPerformed
        if (radSering3.isSelected()){
            if (DiskiBibir.getSelectedIndex()==2){
                Skordiskipi.setText("3");
            }else if (DiskiBibir.getSelectedIndex()==3){
                Skordiskipi.setText("4");
            }else{
                Skordiskipi.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering3ActionPerformed

    private void radSelalu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu3ActionPerformed
        if (radSelalu3.isSelected()){
            if (DiskiBibir.getSelectedIndex()==2){
                Skordiskipi.setText("4");
            }else if (DiskiBibir.getSelectedIndex()==3){
                Skordiskipi.setText("5");
            }else{
                Skordiskipi.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu3ActionPerformed

    private void SkordiskidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskidanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskidanActionPerformed

    private void DiskiBadanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiBadanItemStateChanged
        if(DiskiBadan.getSelectedIndex()==0){
            Skordiskidan.setText("0");
            radJarang4.setEnabled(false);
            radSering4.setEnabled(false);
            radSelalu4.setEnabled(false);
        }else if (DiskiBadan.getSelectedIndex()==1){
            Skordiskidan.setText("1");
            radJarang4.setEnabled(false);
            radSering4.setEnabled(false);
            radSelalu4.setEnabled(false);
        }else if (DiskiBadan.getSelectedIndex()>=2){
            radJarang4.setEnabled(true);
            radSering4.setEnabled(true);
            radSelalu4.setEnabled(true);
            Skordiskidan.setText("0");
        }
    }//GEN-LAST:event_DiskiBadanItemStateChanged

    private void DiskiBadanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiBadanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiBadanActionPerformed

    private void radJarang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang4ActionPerformed
        if (radJarang4.isSelected()){
            if (DiskiBadan.getSelectedIndex()==2){
                Skordiskidan.setText("2");
            }else if (DiskiBadan.getSelectedIndex()==3){
                Skordiskidan.setText("3");
            }else{
                Skordiskidan.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang4ActionPerformed

    private void radSering4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering4ActionPerformed
        if (radSering4.isSelected()){
            if (DiskiBadan.getSelectedIndex()==2){
                Skordiskidan.setText("3");
            }else if (DiskiBadan.getSelectedIndex()==3){
                Skordiskidan.setText("4");
            }else{
                Skordiskidan.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering4ActionPerformed

    private void radSelalu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu4ActionPerformed
        if (radSelalu4.isSelected()){
            if (DiskiBadan.getSelectedIndex()==2){
                Skordiskidan.setText("4");
            }else if (DiskiBadan.getSelectedIndex()==3){
                Skordiskidan.setText("5");
            }else{
                Skordiskidan.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu4ActionPerformed

    private void SkordiskitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskitasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskitasActionPerformed

    private void DiskiEkstrematasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiEkstrematasItemStateChanged
        if(DiskiEkstrematas.getSelectedIndex()==0){
            Skordiskitas.setText("0");
            radJarang5.setEnabled(false);
            radSering5.setEnabled(false);
            radSelalu5.setEnabled(false);
        }else if (DiskiEkstrematas.getSelectedIndex()==1){
            Skordiskitas.setText("1");
            radJarang5.setEnabled(false);
            radSering5.setEnabled(false);
            radSelalu5.setEnabled(false);
        }else if (DiskiEkstrematas.getSelectedIndex()>=2){
            radJarang5.setEnabled(true);
            radSering5.setEnabled(true);
            radSelalu5.setEnabled(true);
            Skordiskitas.setText("0");
        }
    }//GEN-LAST:event_DiskiEkstrematasItemStateChanged

    private void DiskiEkstrematasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiEkstrematasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiEkstrematasActionPerformed

    private void radSering5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering5ActionPerformed
        if (radSering5.isSelected()){
            if (DiskiEkstrematas.getSelectedIndex()==2){
                Skordiskitas.setText("3");
            }else if (DiskiEkstrematas.getSelectedIndex()==3){
                Skordiskitas.setText("4");
            }else{
                Skordiskitas.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering5ActionPerformed

    private void radSelalu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu5ActionPerformed
        if (radSelalu5.isSelected()){
            if (DiskiEkstrematas.getSelectedIndex()==2){
                Skordiskitas.setText("4");
            }else if (DiskiEkstrematas.getSelectedIndex()==3){
                Skordiskitas.setText("5");
            }else{
                Skordiskitas.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu5ActionPerformed

    private void radJarang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang5ActionPerformed
        if (radJarang5.isSelected()){
            if (DiskiEkstrematas.getSelectedIndex()==2){
                Skordiskitas.setText("2");
            }else if (DiskiEkstrematas.getSelectedIndex()==3){
                Skordiskitas.setText("3");
            }else{
                Skordiskitas.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang5ActionPerformed

    private void DiskiEkstrembawahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiEkstrembawahItemStateChanged
        if(DiskiEkstrembawah.getSelectedIndex()==0){
            Skordiskiwah.setText("0");
            radJarang6.setEnabled(false);
            radSering6.setEnabled(false);
            radSelalu6.setEnabled(false);
        }else if (DiskiEkstrembawah.getSelectedIndex()==1){
            Skordiskiwah.setText("1");
            radJarang6.setEnabled(false);
            radSering6.setEnabled(false);
            radSelalu6.setEnabled(false);
        }else if (DiskiEkstrembawah.getSelectedIndex()>=2){
            radJarang6.setEnabled(true);
            radSering6.setEnabled(true);
            radSelalu6.setEnabled(true);
            Skordiskiwah.setText("0");
        }
    }//GEN-LAST:event_DiskiEkstrembawahItemStateChanged

    private void DiskiEkstrembawahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiEkstrembawahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiEkstrembawahActionPerformed

    private void radSering6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering6ActionPerformed
        if (radSering6.isSelected()){
            if (DiskiEkstrembawah.getSelectedIndex()==2){
                Skordiskiwah.setText("3");
            }else if (DiskiEkstrembawah.getSelectedIndex()==3){
                Skordiskiwah.setText("4");
            }else{
                Skordiskiwah.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering6ActionPerformed

    private void SkordiskiwahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskiwahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskiwahActionPerformed

    private void radJarang6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang6ActionPerformed
        if (radJarang6.isSelected()){
            if (DiskiEkstrembawah.getSelectedIndex()==2){
                Skordiskiwah.setText("2");
            }else if (DiskiEkstrembawah.getSelectedIndex()==3){
                Skordiskiwah.setText("3");
            }else{
                Skordiskiwah.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang6ActionPerformed

    private void radSelalu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu6ActionPerformed
        if (radSelalu6.isSelected()){
            if (DiskiEkstrembawah.getSelectedIndex()==2){
                Skordiskiwah.setText("4");
            }else if (DiskiEkstrembawah.getSelectedIndex()==3){
                Skordiskiwah.setText("5");
            }else{
                Skordiskiwah.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu6ActionPerformed

    private void SkordiskivolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkordiskivolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkordiskivolActionPerformed

    private void DiskiInvolunterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DiskiInvolunterItemStateChanged
        if(DiskiInvolunter.getSelectedIndex()==0){
            Skordiskivol.setText("0");
            radJarang7.setEnabled(false);
            radSering7.setEnabled(false);
            radSelalu7.setEnabled(false);
        }else if (DiskiInvolunter.getSelectedIndex()==1){
            Skordiskivol.setText("1");
            radJarang7.setEnabled(false);
            radSering7.setEnabled(false);
            radSelalu7.setEnabled(false);
        }else if (DiskiInvolunter.getSelectedIndex()>=2){
            radJarang7.setEnabled(true);
            radSering7.setEnabled(true);
            radSelalu7.setEnabled(true);
            Skordiskivol.setText("0");
        }
    }//GEN-LAST:event_DiskiInvolunterItemStateChanged

    private void DiskiInvolunterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskiInvolunterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiskiInvolunterActionPerformed

    private void radSering7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSering7ActionPerformed
        if (radSering7.isSelected()){
            if (DiskiInvolunter.getSelectedIndex()==2){
                Skordiskivol.setText("3");
            }else if (DiskiInvolunter.getSelectedIndex()==3){
                Skordiskivol.setText("4");
            }else{
                Skordiskivol.setText("5");
            }   
        }
    }//GEN-LAST:event_radSering7ActionPerformed

    private void radJarang7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radJarang7ActionPerformed
        if (radJarang7.isSelected()){
            if (DiskiInvolunter.getSelectedIndex()==2){
                Skordiskivol.setText("2");
            }else if (DiskiInvolunter.getSelectedIndex()==3){
                Skordiskivol.setText("3");
            }else{
                Skordiskivol.setText("4");
            }   
        }
    }//GEN-LAST:event_radJarang7ActionPerformed

    private void radSelalu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radSelalu7ActionPerformed
        if (radSelalu7.isSelected()){
            if (DiskiInvolunter.getSelectedIndex()==2){
                Skordiskivol.setText("4");
            }else if (DiskiInvolunter.getSelectedIndex()==3){
                Skordiskivol.setText("5");
            }else{
                Skordiskivol.setText("6");
            }   
        }
    }//GEN-LAST:event_radSelalu7ActionPerformed

    private void TardifKepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TardifKepalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TardifKepalaActionPerformed

    private void SulitTugasrutinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SulitTugasrutinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SulitTugasrutinActionPerformed

    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianGejalaEkstrapiramidal dialog = new RMPenilaianGejalaEkstrapiramidal(new javax.swing.JFrame(), true);
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
    private widget.RadioButton ADuduk;
    private widget.RadioButton AGelisah;
    private widget.RadioButton AGerak;
    private widget.RadioButton AHentak;
    private widget.RadioButton AIngin;
    private widget.RadioButton ATerus;
    private widget.RadioButton ATidakada;
    private widget.ComboBox Anamnesis;
    private widget.RadioButton BJarang;
    private widget.RadioButton BLambat1;
    private widget.RadioButton BLambat2;
    private widget.RadioButton BNormal;
    private widget.RadioButton BSulit1;
    private widget.RadioButton BSulit2;
    private widget.RadioButton BSulit3;
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
    private widget.ComboBox DiskiBadan;
    private widget.ComboBox DiskiBibir;
    private widget.ComboBox DiskiEkstrematas;
    private widget.ComboBox DiskiEkstrembawah;
    private widget.ComboBox DiskiInvolunter;
    private widget.ComboBox DiskiLidah;
    private widget.ComboBox DiskiRahang;
    private widget.ComboBox DiskinesiaGerak;
    private widget.ComboBox DiskinesiaLidah;
    private widget.ComboBox DistoniaBibir;
    private widget.ComboBox DistoniaKaka;
    private widget.ComboBox DistoniaKaki;
    private widget.ComboBox DistoniaKepala;
    private widget.ComboBox DistoniaLidah;
    private widget.ComboBox DistoniaRahang;
    private widget.ComboBox DistoniaTangka;
    private widget.ComboBox DistoniaTangki;
    private widget.ComboBox DistoniaTrunk;
    private widget.ComboBox EKananAtas;
    private widget.ComboBox EKananBawah;
    private widget.ComboBox EKiriAtas;
    private widget.ComboBox EKiriBawah;
    private widget.PanelBiasa FormInput;
    private widget.RadioButton GMuka1;
    private widget.RadioButton GMuka2;
    private widget.RadioButton GNormal;
    private widget.RadioButton GPenurunan1;
    private widget.RadioButton GPenurunan2;
    private widget.RadioButton GSenyum;
    private widget.RadioButton GSenyum2;
    private widget.ComboBox Gelisah;
    private widget.TextBox Hubungan;
    private widget.RadioButton JAyunan;
    private widget.RadioButton JFlexi;
    private widget.RadioButton JKaku;
    private widget.RadioButton JNormal;
    private widget.RadioButton JNyata;
    private widget.RadioButton JTurun;
    private widget.RadioButton JTurun2;
    private widget.TextBox Jk;
    private widget.ComboBox Kaku;
    private widget.TextBox KdDokter;
    private widget.ComboBox Kram;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Ludah;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextBox NmDokter;
    private widget.ComboBox PosturAbnormal;
    private widget.ComboBox Pusing;
    private widget.RadioButton SAgakberat;
    private widget.RadioButton SBerat;
    private widget.RadioButton SRingan;
    private widget.RadioButton SSangatberat;
    private widget.RadioButton STidakada;
    private widget.ScrollPane Scroll;
    private widget.RadioButton SekaliBibir;
    private widget.RadioButton SekaliKaka;
    private widget.RadioButton SekaliKaki;
    private widget.RadioButton SekaliKepala;
    private widget.RadioButton SekaliLengka;
    private widget.RadioButton SekaliLengki;
    private widget.RadioButton SekaliLidah;
    private widget.RadioButton SekaliPipi;
    private widget.RadioButton SelaluBibir;
    private widget.RadioButton SelaluKaka;
    private widget.RadioButton SelaluKaki;
    private widget.RadioButton SelaluKepala;
    private widget.RadioButton SelaluLengka;
    private widget.RadioButton SelaluLengki;
    private widget.RadioButton SelaluLidah;
    private widget.RadioButton SelaluPipi;
    private widget.RadioButton SeringBibir;
    private widget.RadioButton SeringKaka;
    private widget.RadioButton SeringKaki;
    private widget.RadioButton SeringKepala;
    private widget.RadioButton SeringLengka;
    private widget.RadioButton SeringLengki;
    private widget.RadioButton SeringLidah;
    private widget.RadioButton SeringPipi;
    private widget.RadioButton SgtRingan;
    private widget.TextBox SkorAkatisia;
    private widget.TextBox SkorBibir;
    private widget.TextBox SkorBradi;
    private widget.TextBox SkorDBibir;
    private widget.TextBox SkorDKaka;
    private widget.TextBox SkorDKaki;
    private widget.TextBox SkorDKepala;
    private widget.TextBox SkorDLidah;
    private widget.TextBox SkorDRahang;
    private widget.TextBox SkorDTangka;
    private widget.TextBox SkorDTangki;
    private widget.TextBox SkorDTrunk;
    private widget.TextBox SkorDistonia;
    private widget.TextBox SkorGerakan;
    private widget.TextBox SkorJalan;
    private widget.TextBox SkorKaka;
    private widget.TextBox SkorKaki;
    private widget.TextBox SkorKananAtas;
    private widget.TextBox SkorKananBawah;
    private widget.TextBox SkorKepala;
    private widget.TextBox SkorKiriAtas;
    private widget.TextBox SkorKiriBawah;
    private widget.TextBox SkorLengka;
    private widget.TextBox SkorLengki;
    private widget.TextBox SkorLidah;
    private widget.TextBox SkorPipi;
    private widget.TextBox SkorRigid;
    private widget.TextBox SkorSialorhoe;
    private widget.TextBox SkorStabilitas;
    private widget.TextBox SkorTBibir;
    private widget.TextBox SkorTKaka;
    private widget.TextBox SkorTKaki;
    private widget.TextBox SkorTKepala;
    private widget.TextBox SkorTLidah;
    private widget.TextBox SkorTRahang;
    private widget.TextBox SkorTTangka;
    private widget.TextBox SkorTTangki;
    private widget.TextBox SkorTTrunk;
    private widget.TextBox SkorTardif;
    private widget.TextBox SkorTremor;
    private widget.TextBox Skordiskidan;
    private widget.TextBox Skordiskilid;
    private widget.TextBox Skordiskipi;
    private widget.TextBox Skordiskirah;
    private widget.TextBox Skordiskitas;
    private widget.TextBox Skordiskivol;
    private widget.TextBox Skordiskiwah;
    private widget.RadioButton Ssedang;
    private widget.RadioButton StabLambat;
    private widget.RadioButton StabMampu;
    private widget.RadioButton StabRespon;
    private widget.RadioButton StabRetro;
    private widget.RadioButton StabRetro1;
    private widget.RadioButton StabStabil;
    private widget.RadioButton StabTidakada;
    private widget.ComboBox SulitBerjalan;
    private widget.ComboBox SulitMenelan;
    private widget.ComboBox SulitTugasrutin;
    private widget.ComboBox TBibir;
    private widget.TextBox TCari;
    private widget.ComboBox TKaka;
    private widget.ComboBox TKaki;
    private widget.ComboBox TKepala;
    private widget.ComboBox TLengka;
    private widget.ComboBox TLengki;
    private widget.ComboBox TLidah;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.ComboBox TPipi;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ComboBox TardifBibir;
    private widget.ComboBox TardifKaka;
    private widget.ComboBox TardifKaki;
    private widget.ComboBox TardifKepala;
    private widget.ComboBox TardifLidah;
    private widget.ComboBox TardifRahang;
    private widget.ComboBox TardifTangka;
    private widget.ComboBox TardifTangki;
    private widget.ComboBox TardifTrunk;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox Tremor;
    private javax.swing.ButtonGroup grpDiskinetik1;
    private javax.swing.ButtonGroup grpDiskinetik2;
    private javax.swing.ButtonGroup grpDiskinetik3;
    private javax.swing.ButtonGroup grpDiskinetik4;
    private javax.swing.ButtonGroup grpDiskinetik5;
    private javax.swing.ButtonGroup grpDiskinetik6;
    private javax.swing.ButtonGroup grpDiskinetik7;
    private javax.swing.ButtonGroup grpParkinson1;
    private javax.swing.ButtonGroup grpParkinson2;
    private javax.swing.ButtonGroup grpParkinson4;
    private javax.swing.ButtonGroup grpParkinson6;
    private javax.swing.ButtonGroup grpParkinson7;
    private javax.swing.ButtonGroup grpParkinson8;
    private javax.swing.ButtonGroup grpTremor1;
    private javax.swing.ButtonGroup grpTremor2;
    private javax.swing.ButtonGroup grpTremor3;
    private javax.swing.ButtonGroup grpTremor4;
    private javax.swing.ButtonGroup grpTremor5;
    private javax.swing.ButtonGroup grpTremor6;
    private javax.swing.ButtonGroup grpTremor7;
    private javax.swing.ButtonGroup grpTremor8;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel11;
    private widget.Label jLabel111;
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
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel38;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel48;
    private widget.Label jLabel50;
    private widget.Label jLabel6;
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
    private widget.Label jLabel83;
    private widget.Label jLabel84;
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
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.RadioButton radJarang1;
    private widget.RadioButton radJarang2;
    private widget.RadioButton radJarang3;
    private widget.RadioButton radJarang4;
    private widget.RadioButton radJarang5;
    private widget.RadioButton radJarang6;
    private widget.RadioButton radJarang7;
    private widget.RadioButton radSelalu1;
    private widget.RadioButton radSelalu2;
    private widget.RadioButton radSelalu3;
    private widget.RadioButton radSelalu4;
    private widget.RadioButton radSelalu5;
    private widget.RadioButton radSelalu6;
    private widget.RadioButton radSelalu7;
    private widget.RadioButton radSering1;
    private widget.RadioButton radSering2;
    private widget.RadioButton radSering3;
    private widget.RadioButton radSering4;
    private widget.RadioButton radSering5;
    private widget.RadioButton radSering6;
    private widget.RadioButton radSering7;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_esrs.tanggal,"+
                        "penilaian_esrs.kd_dokter,dokter.nm_dokter,penilaian_esrs.anamnesis,penilaian_esrs.hubungan," + 
                        
                        "penilaian_esrs.kues_lambat,penilaian_esrs.kues_berjalan,penilaian_esrs.kues_menelan,penilaian_esrs.kues_kaku,penilaian_esrs.kues_kram,penilaian_esrs.kues_gelisah,penilaian_esrs.kues_tremor,penilaian_esrs.kues_postur,penilaian_esrs.kues_ludah,penilaian_esrs.kues_diskinesiagerak,penilaian_esrs.kues_diskinesialidah,penilaian_esrs.kues_pusing,"+
                                
                        "penilaian_esrs.mukatopeng,penilaian_esrs.skormuka,penilaian_esrs.bradi,penilaian_esrs.skorbradi,"+
                        
                        "penilaian_esrs.rigi_ekanana,penilaian_esrs.skorekanana,penilaian_esrs.rigi_ekiria,penilaian_esrs.skorekiria,penilaian_esrs.rigi_ekananb,penilaian_esrs.skorekananb,penilaian_esrs.rigi_ekirib,penilaian_esrs.skorekirib,penilaian_esrs.skorrigid,"+
                        "penilaian_esrs.postur,penilaian_esrs.skorjalan,penilaian_esrs.ketlengka,penilaian_esrs.rasiolengka,penilaian_esrs.skorlengka,penilaian_esrs.ketlengki,penilaian_esrs.rasiolengki,penilaian_esrs.skorlengki,penilaian_esrs.ketkaka,penilaian_esrs.rasiokaka,"+
                        "penilaian_esrs.skorkaka,penilaian_esrs.ketkaki,penilaian_esrs.rasiokaki,penilaian_esrs.skorkaki,penilaian_esrs.ketkepala,penilaian_esrs.rasiokepala,penilaian_esrs.skorkepala,penilaian_esrs.ketrahang,penilaian_esrs.rasiorahang,penilaian_esrs.skorrahang,penilaian_esrs.ketlidah,"+
                        "penilaian_esrs.rasiolidah,penilaian_esrs.skorlidah,penilaian_esrs.ketbibir,penilaian_esrs.rasiobibir,penilaian_esrs.skorbibir,penilaian_esrs.skortremor,penilaian_esrs.akatisia,penilaian_esrs.skorakatisia,penilaian_esrs.sialorhoe,penilaian_esrs.skorsialorhoe,penilaian_esrs.stabilitas,penilaian_esrs.skorstabilitas,"+
                        "penilaian_esrs.torsitangka,penilaian_esrs.skortorsitangka,penilaian_esrs.torsitangki,penilaian_esrs.skortorsitangki,penilaian_esrs.torsikaka,penilaian_esrs.skortorsikaka,penilaian_esrs.torsikaki,penilaian_esrs.skortorsikaki,penilaian_esrs.torsikepala,penilaian_esrs.skortorsikepala,penilaian_esrs.torsirahang,"+
                        "penilaian_esrs.skortorsirahang,penilaian_esrs.torsilidah,penilaian_esrs.skortorsilidah,penilaian_esrs.torsibibir,penilaian_esrs.skortorsibibir,penilaian_esrs.torsitrunk,penilaian_esrs.skortorsitrunk,penilaian_esrs.skortorsi,penilaian_esrs.tardiftangka,penilaian_esrs.skortardiftangka,penilaian_esrs.tardiftangki,"+
                        "penilaian_esrs.skortardiftangki,penilaian_esrs.tardifkaka,penilaian_esrs.skortardifkaka,penilaian_esrs.tardifkaki,penilaian_esrs.skortardifkaki,penilaian_esrs.tardifkepala,penilaian_esrs.skortardifkepala,penilaian_esrs.tardifrahang,penilaian_esrs.skortardifrahang,penilaian_esrs.tardiflidah,penilaian_esrs.skortardiflidah,penilaian_esrs.tardifbibir,"+
                        "penilaian_esrs.skortardifbibir,penilaian_esrs.tardiftrunk,penilaian_esrs.skortardiftrunk,penilaian_esrs.skortardif,penilaian_esrs.diskilidah,penilaian_esrs.rasiodiskilidah,penilaian_esrs.skordiskilidah,penilaian_esrs.diskirahang,penilaian_esrs.rasiodiskirahang,penilaian_esrs.skordiskirahang,penilaian_esrs.diskipipi,penilaian_esrs.rasiodiskipipi,penilaian_esrs.skordiskipipi,"+
                        "penilaian_esrs.diskibadan,penilaian_esrs.rasiodiskibadan,penilaian_esrs.skordiskibadan,penilaian_esrs.diskieksatas,penilaian_esrs.rasiodiskieksatas,penilaian_esrs.skordiskieksatas,penilaian_esrs.diskieksbawah,penilaian_esrs.rasiodiskieksbawah,penilaian_esrs.skordiskieskbawah,penilaian_esrs.diskiinvol,penilaian_esrs.rasiodiskiinvol,penilaian_esrs.skordiskiinvol "+
                        
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_esrs on reg_periksa.no_rawat=penilaian_esrs.no_rawat "+
                        "inner join dokter on penilaian_esrs.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_esrs.tanggal between ? and ? order by penilaian_esrs.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_esrs.tanggal,"+
                        "penilaian_esrs.kd_dokter,dokter.nm_dokter,penilaian_esrs.anamnesis,penilaian_esrs.hubungan," + 
                        
                        "penilaian_esrs.kues_lambat,penilaian_esrs.kues_berjalan,penilaian_esrs.kues_menelan,penilaian_esrs.kues_kaku,penilaian_esrs.kues_kram,penilaian_esrs.kues_gelisah,penilaian_esrs.kues_tremor,penilaian_esrs.kues_postur,penilaian_esrs.kues_ludah,penilaian_esrs.kues_diskinesiagerak,penilaian_esrs.kues_diskinesialidah,penilaian_esrs.kues_pusing,"+
                                
                        "penilaian_esrs.mukatopeng,penilaian_esrs.skormuka,penilaian_esrs.bradi,penilaian_esrs.skorbradi,"+
                        
                        "penilaian_esrs.rigi_ekanana,penilaian_esrs.skorekanana,penilaian_esrs.rigi_ekiria,penilaian_esrs.skorekiria,penilaian_esrs.rigi_ekananb,penilaian_esrs.skorekananb,penilaian_esrs.rigi_ekirib,penilaian_esrs.skorekirib,penilaian_esrs.skorrigid,"+
                        "penilaian_esrs.postur,penilaian_esrs.skorjalan,penilaian_esrs.ketlengka,penilaian_esrs.rasiolengka,penilaian_esrs.skorlengka,penilaian_esrs.ketlengki,penilaian_esrs.rasiolengki,penilaian_esrs.skorlengki,penilaian_esrs.ketkaka,penilaian_esrs.rasiokaka,"+
                        "penilaian_esrs.skorkaka,penilaian_esrs.ketkaki,penilaian_esrs.rasiokaki,penilaian_esrs.skorkaki,penilaian_esrs.ketkepala,penilaian_esrs.rasiokepala,penilaian_esrs.skorkepala,penilaian_esrs.ketrahang,penilaian_esrs.rasiorahang,penilaian_esrs.skorrahang,penilaian_esrs.ketlidah,"+
                        "penilaian_esrs.rasiolidah,penilaian_esrs.skorlidah,penilaian_esrs.ketbibir,penilaian_esrs.rasiobibir,penilaian_esrs.skorbibir,penilaian_esrs.skortremor,penilaian_esrs.akatisia,penilaian_esrs.skorakatisia,penilaian_esrs.sialorhoe,penilaian_esrs.skorsialorhoe,penilaian_esrs.stabilitas,penilaian_esrs.skorstabilitas,"+
                        "penilaian_esrs.torsitangka,penilaian_esrs.skortorsitangka,penilaian_esrs.torsitangki,penilaian_esrs.skortorsitangki,penilaian_esrs.torsikaka,penilaian_esrs.skortorsikaka,penilaian_esrs.torsikaki,penilaian_esrs.skortorsikaki,penilaian_esrs.torsikepala,penilaian_esrs.skortorsikepala,penilaian_esrs.torsirahang,"+
                        "penilaian_esrs.skortorsirahang,penilaian_esrs.torsilidah,penilaian_esrs.skortorsilidah,penilaian_esrs.torsibibir,penilaian_esrs.skortorsibibir,penilaian_esrs.torsitrunk,penilaian_esrs.skortorsitrunk,penilaian_esrs.skortorsi,penilaian_esrs.tardiftangka,penilaian_esrs.skortardiftangka,penilaian_esrs.tardiftangki,"+
                        "penilaian_esrs.skortardiftangki,penilaian_esrs.tardifkaka,penilaian_esrs.skortardifkaka,penilaian_esrs.tardifkaki,penilaian_esrs.skortardifkaki,penilaian_esrs.tardifkepala,penilaian_esrs.skortardifkepala,penilaian_esrs.tardifrahang,penilaian_esrs.skortardifrahang,penilaian_esrs.tardiflidah,penilaian_esrs.skortardiflidah,penilaian_esrs.tardifbibir,"+
                        "penilaian_esrs.skortardifbibir,penilaian_esrs.tardiftrunk,penilaian_esrs.skortardiftrunk,penilaian_esrs.skortardif,penilaian_esrs.diskilidah,penilaian_esrs.rasiodiskilidah,penilaian_esrs.skordiskilidah,penilaian_esrs.diskirahang,penilaian_esrs.rasiodiskirahang,penilaian_esrs.skordiskirahang,penilaian_esrs.diskipipi,penilaian_esrs.rasiodiskipipi,penilaian_esrs.skordiskipipi,"+
                        "penilaian_esrs.diskibadan,penilaian_esrs.rasiodiskibadan,penilaian_esrs.skordiskibadan,penilaian_esrs.diskieksatas,penilaian_esrs.rasiodiskieksatas,penilaian_esrs.skordiskieksatas,penilaian_esrs.diskieksbawah,penilaian_esrs.rasiodiskieksbawah,penilaian_esrs.skordiskieskbawah,penilaian_esrs.diskiinvol,penilaian_esrs.rasiodiskiinvol,penilaian_esrs.skordiskiinvol "+
                        
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_esrs on reg_periksa.no_rawat=penilaian_esrs.no_rawat "+
                        "inner join dokter on penilaian_esrs.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_esrs.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_esrs.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_esrs.tanggal");
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
                        rs.getString("anamnesis"),rs.getString("hubungan"),
                        
                        rs.getString("kues_lambat"),rs.getString("kues_berjalan"),rs.getString("kues_menelan"),rs.getString("kues_kaku"),rs.getString("kues_kram"),rs.getString("kues_gelisah"),rs.getString("kues_tremor"),rs.getString("kues_postur"),rs.getString("kues_ludah"),rs.getString("kues_diskinesiagerak"),rs.getString("kues_diskinesialidah"),rs.getString("kues_pusing"),
                                
                        rs.getString("mukatopeng"),rs.getString("skormuka"),rs.getString("bradi"),rs.getString("skorbradi"),
                        
                        rs.getString("rigi_ekanana"),rs.getString("skorekanana"),rs.getString("rigi_ekiria"),rs.getString("skorekiria"),rs.getString("rigi_ekananb"),rs.getString("skorekananb"),rs.getString("rigi_ekirib"),rs.getString("skorekirib"),rs.getString("skorrigid"),
                        rs.getString("postur"),rs.getString("skorjalan"),rs.getString("ketlengka"),rs.getString("rasiolengka"),rs.getString("skorlengka"),rs.getString("ketlengki"),rs.getString("rasiolengki"),rs.getString("skorlengki"),rs.getString("ketkaka"),rs.getString("rasiokaka"),
                        rs.getString("skorkaka"),rs.getString("ketkaki"),rs.getString("rasiokaki"),rs.getString("skorkaki"),rs.getString("ketkepala"),rs.getString("rasiokepala"),rs.getString("skorkepala"),rs.getString("ketrahang"),rs.getString("rasiorahang"),rs.getString("skorrahang"),rs.getString("ketlidah"),
                        rs.getString("rasiolidah"),rs.getString("skorlidah"),rs.getString("ketbibir"),rs.getString("rasiobibir"),rs.getString("skorbibir"),rs.getString("skortremor"),rs.getString("akatisia"),rs.getString("skorakatisia"),rs.getString("sialorhoe"),rs.getString("skorsialorhoe"),rs.getString("stabilitas"),rs.getString("skorstabilitas"),
                        rs.getString("torsitangka"),rs.getString("skortorsitangka"),rs.getString("torsitangki"),rs.getString("skortorsitangki"),rs.getString("torsikaka"),rs.getString("skortorsikaka"),rs.getString("torsikaki"),rs.getString("skortorsikaki"),rs.getString("torsikepala"),rs.getString("skortorsikepala"),rs.getString("torsirahang"),
                        rs.getString("skortorsirahang"),rs.getString("torsilidah"),rs.getString("skortorsilidah"),rs.getString("torsibibir"),rs.getString("skortorsibibir"),rs.getString("torsitrunk"),rs.getString("skortorsitrunk"),rs.getString("skortorsi"),rs.getString("tardiftangka"),rs.getString("skortardiftangka"),rs.getString("tardiftangki"),
                        rs.getString("skortardiftangki"),rs.getString("tardifkaka"),rs.getString("skortardifkaka"),rs.getString("tardifkaki"),rs.getString("skortardifkaki"),rs.getString("tardifkepala"),rs.getString("skortardifkepala"),rs.getString("tardifrahang"),rs.getString("skortardifrahang"),rs.getString("tardiflidah"),rs.getString("skortardiflidah"),rs.getString("tardifbibir"),
                        rs.getString("skortardifbibir"),rs.getString("tardiftrunk"),rs.getString("skortardiftrunk"),rs.getString("skortardif"),rs.getString("diskilidah"),rs.getString("rasiodiskilidah"),rs.getString("skordiskilidah"),rs.getString("diskirahang"),rs.getString("rasiodiskirahang"),rs.getString("skordiskirahang"),rs.getString("diskipipi"),rs.getString("rasiodiskipipi"),rs.getString("skordiskipipi"),
                        rs.getString("diskibadan"),rs.getString("rasiodiskibadan"),rs.getString("skordiskibadan"),rs.getString("diskieksatas"),rs.getString("rasiodiskieksatas"),rs.getString("skordiskieksatas"),rs.getString("diskieksbawah"),rs.getString("rasiodiskieksbawah"),rs.getString("skordiskieskbawah"),rs.getString("diskiinvol"),rs.getString("rasiodiskiinvol"),rs.getString("skordiskiinvol")  
                        
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
        Anamnesis.setSelectedIndex(0);
        Hubungan.setText("");
        
        SulitTugasrutin.setSelectedIndex(0);
        SulitBerjalan.setSelectedIndex(0);
        SulitMenelan.setSelectedIndex(0);
        Kaku.setSelectedIndex(0);
        Kram.setSelectedIndex(0);
        Gelisah.setSelectedIndex(0);
        Tremor.setSelectedIndex(0);
        PosturAbnormal.setSelectedIndex(0);
        Ludah.setSelectedIndex(0);
        DiskinesiaGerak.setSelectedIndex(0);
        DiskinesiaLidah.setSelectedIndex(0);
        Pusing.setSelectedIndex(0);
                    
        GNormal.setSelected(false);
        GPenurunan1.setSelected(false);
        GPenurunan2.setSelected(false);
        GSenyum.setSelected(false);
        GSenyum2.setSelected(false);
        GMuka1.setSelected(false);
        GMuka2.setSelected(false);
        SkorGerakan.setText("0");
        
        BNormal.setSelected(false);
        BLambat1.setSelected(false);
        BLambat2.setSelected(false);
        BSulit1.setSelected(false);
        BSulit2.setSelected(false);
        BSulit3.setSelected(false);
        BJarang.setSelected(false);
        SkorBradi.setText("0");
                    
        EKananAtas.setSelectedIndex(0);
        SkorKananAtas.setText("0");
        EKiriAtas.setSelectedIndex(0);
        SkorKiriAtas.setText("0");
        EKananBawah.setSelectedIndex(0);
        SkorKananBawah.setText("0");
        EKiriBawah.setSelectedIndex(0);
        SkorKiriBawah.setText("0");
        SkorRigid.setText("0");
        
        JNormal.setSelected(false);
        JTurun.setSelected(false);
        JTurun2.setSelected(false);
        JAyunan.setSelected(false);
        JKaku.setSelected(false);
        JNyata.setSelected(false);
        JFlexi.setSelected(false);
        SkorJalan.setText("0");

        TLengka.setSelectedIndex(0);
        SekaliLengka.setSelected(false);
        SeringLengka.setSelected(false);
        SelaluLengka.setSelected(false);
        SkorLengka.setText("0");
        TLengki.setSelectedIndex(0);
        SekaliLengki.setSelected(false);
        SeringLengki.setSelected(false);
        SelaluLengki.setSelected(false);
        SkorLengki.setText("0");
        TKaka.setSelectedIndex(0);
        SekaliKaka.setSelected(false);
        SeringKaka.setSelected(false);
        SelaluKaka.setSelected(false);
        SkorKaka.setText("0");
        TKaki.setSelectedIndex(0);
        SekaliKaki.setSelected(false);
        SeringKaki.setSelected(false);
        SelaluKaki.setSelected(false);
        SkorKaki.setText("0");
        TKepala.setSelectedIndex(0);
        SekaliKepala.setSelected(false);
        SeringKepala.setSelected(false);
        SelaluKepala.setSelected(false);
        SkorKepala.setText("0");
        TPipi.setSelectedIndex(0);
        SekaliPipi.setSelected(false);
        SeringPipi.setSelected(false);
        SelaluPipi.setSelected(false);
        SkorPipi.setText("0");
        TLidah.setSelectedIndex(0);
        SekaliLidah.setSelected(false);
        SeringLidah.setSelected(false);
        SelaluLidah.setSelected(false);
        SkorLidah.setText("0");
        TBibir.setSelectedIndex(0);
        SekaliBibir.setSelected(false);
        SeringBibir.setSelected(false);
        SelaluBibir.setSelected(false);
        SkorBibir.setText("0");
        SkorTremor.setText("0");

        ATidakada.setSelected(false);
        AGelisah.setSelected(false);
        AIngin.setSelected(false);
        AGerak.setSelected(false);
        AHentak.setSelected(false);
        ADuduk.setSelected(false);
        ATerus.setSelected(false);
        SkorAkatisia.setText("0");
            
        STidakada.setSelected(false);
        SgtRingan.setSelected(false);
        SRingan.setSelected(false);
        Ssedang.setSelected(false);
        SAgakberat.setSelected(false);
        SBerat.setSelected(false);
        SSangatberat.setSelected(false);    
        SkorSialorhoe.setText("0");
        
        StabTidakada.setSelected(false);
        StabLambat.setSelected(false);
        StabRetro.setSelected(false);
        StabRetro1.setSelected(false);
        StabRespon.setSelected(false);
        StabStabil.setSelected(false);
        StabMampu.setSelected(false); 
        SkorStabilitas.setText("0");
        
        DistoniaTangka.setSelectedIndex(0);
        SkorDTangka.setText("0");
        DistoniaTangki.setSelectedIndex(0);
        SkorDTangki.setText("0");
        DistoniaKaka.setSelectedIndex(0);
        SkorDKaka.setText("0");
        DistoniaKaki.setSelectedIndex(0);
        SkorDKaki.setText("0");
        DistoniaKepala.setSelectedIndex(0);
        SkorDKepala.setText("0");
        DistoniaRahang.setSelectedIndex(0);
        SkorDRahang.setText("0");
        DistoniaLidah.setSelectedIndex(0);
        SkorDLidah.setText("0");
        DistoniaBibir.setSelectedIndex(0);
        SkorDBibir.setText("0");
        DistoniaTrunk.setSelectedIndex(0);
        SkorDTrunk.setText("0");
        SkorDistonia.setText("0");

        TardifTangka.setSelectedIndex(0);
        SkorTTangka.setText("0");
        TardifTangki.setSelectedIndex(0);
        SkorTTangki.setText("0");
        TardifKaka.setSelectedIndex(0);
        SkorTKaka.setText("0");
        TardifKaki.setSelectedIndex(0);
        SkorTKaki.setText("0");
        TardifKepala.setSelectedIndex(0);
        SkorTKepala.setText("0");
        TardifRahang.setSelectedIndex(0);
        SkorTRahang.setText("0");
        TardifLidah.setSelectedIndex(0);
        SkorTLidah.setText("0");
        TardifBibir.setSelectedIndex(0);
        SkorTBibir.setText("0");
        TardifTrunk.setSelectedIndex(0);
        SkorTTrunk.setText("0");
        SkorTardif.setText("0");
        
        DiskiLidah.setSelectedIndex(0);
        radJarang1.setSelected(false);
        radSering1.setSelected(false);
        radSelalu1.setSelected(false);
        Skordiskilid.setText("0");
        DiskiRahang.setSelectedIndex(0);
        radJarang2.setSelected(false);
        radSering2.setSelected(false);
        radSelalu2.setSelected(false);
        Skordiskirah.setText("0");
        DiskiBibir.setSelectedIndex(0);
        radJarang3.setSelected(false);
        radSering3.setSelected(false);
        radSelalu3.setSelected(false);
        Skordiskipi.setText("0");
        DiskiBadan.setSelectedIndex(0);
        radJarang4.setSelected(false);
        radSering4.setSelected(false);
        radSelalu4.setSelected(false);
        Skordiskidan.setText("0");
        DiskiEkstrematas.setSelectedIndex(0);
        radJarang5.setSelected(false);
        radSering5.setSelected(false);
        radSelalu5.setSelected(false);
        Skordiskitas.setText("0");
        DiskiEkstrembawah.setSelectedIndex(0);
        radJarang6.setSelected(false);
        radSering6.setSelected(false);
        radSelalu6.setSelected(false);
        Skordiskiwah.setText("0");
        DiskiInvolunter.setSelectedIndex(0);
        radJarang7.setSelected(false);
        radSering7.setSelected(false);
        radSelalu7.setSelected(false);
        Skordiskivol.setText("0");
                    
//        KeluhanUtama.setText("");
//        RPS.setText("");
//        RPD.setText("");
//        RPO.setText("");
//        Alergi.setText("");
//        KondisiUmum.setText("");
//        TD.setText("");
//        Nadi.setText("");
//        Suhu.setText("");
//        RR.setText("");
//        PosturTulang.setSelectedIndex(0);
//        Kepala.setSelectedIndex(0);
//        Thoraks.setSelectedIndex(0);
//        Abdomen.setSelectedIndex(0);
//        Ekstremitas.setSelectedIndex(0);
//        KeteranganKepala.setText("");
//        KeteranganThoraks.setText("");
//        KeteranganAbdomen.setText("");
//        KeteranganEkstremitas.setText("");
//        KondisiSosial.setText("");
//        Psikologis.setSelectedIndex(0);
//        Kognitif.setSelectedIndex(0);
//        Nutrisi.setSelectedIndex(0);
//        RisikoJatuh.setSelectedIndex(0);
//        Fungsional.setSelectedIndex(0);
//        IntegumentKebersihan.setSelectedIndex(0);
//        IntegumentWarna.setSelectedIndex(0);
//        IntegumentKelembaban.setSelectedIndex(0);
//        IntegumentGangguan.setSelectedIndex(0);
//        Lainnya.setText("");
//        Lab.setText("");
//        Rad.setText("");
//        PenunjangLain.setText("");
//        Diagnosis.setText("");
//        Diagnosis2.setText("");
//        Permasalahan.setText("");
//        Terapi.setText("");
//        Tindakan.setText("");
//        Edukasi.setText("");
        TglAsuhan.setDate(new Date());
        TabRawat.setSelectedIndex(0);
        Anamnesis.requestFocus();
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
            
            Anamnesis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Hubungan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            
            SulitTugasrutin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            SulitBerjalan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            SulitMenelan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Kaku.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Kram.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Gelisah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Tremor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            PosturAbnormal.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Ludah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            DiskinesiaGerak.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            DiskinesiaLidah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            Pusing.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            
            String mukatopeng = tbObat.getValueAt(tbObat.getSelectedRow(),22).toString();
                if (mukatopeng.contains("Normal")){
                    GNormal.setSelected(true);  
                }else if (mukatopeng.contains("Penurunan yang sangat ringan dari ekspresi muka")){
                    GPenurunan1.setSelected(true);   
                }else if(mukatopeng.contains("Penurunan ringan dari ekspresi muka")){
                    GPenurunan2.setSelected(true); 
                }else if(mukatopeng.contains("Senyum spontan jarang, berkedip kurang, suara agak monoton")){
                    GSenyum.setSelected(true);
                }else if(mukatopeng.contains("Tidak ada senyum spontan, pandangan kosong, pembicaraan perlahan dan monoton, bergumam")){
                    GSenyum2.setSelected(true);
                }else if(mukatopeng.contains("Muka topeng yang nyata, tidak mempu mengerutkan dahi, bicara pelo")){
                    GMuka1.setSelected(true);
                }else if(mukatopeng.contains("Muka topeng yang sangat nyata dengan pembicaran yang sulit dimengerti")){
                    GMuka2.setSelected(true);
                }
            SkorGerakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            
            String bradikinesia = tbObat.getValueAt(tbObat.getSelectedRow(),24).toString();
                if (bradikinesia.contains("Normal")) {
                    BNormal.setSelected(true);
                }else if (bradikinesia.contains("Kesan perlambatan yang menyeluruh dari gerakan-gerakan")){
                    BLambat1.setSelected(true);
                }else if (bradikinesia.contains("Kelambatan yang nyata dalam gerakan")){
                    BLambat2.setSelected(true);
                }else if (bradikinesia.contains("Kesulitan yang sangat ringan dalam memulai gerakan")){
                    BSulit1.setSelected(true);
                }else if (bradikinesia.contains("Kesulitan ringan sampai sedang dalam memulai gerakan")){
                    BSulit2.setSelected(true);
                }else if (bradikinesia.contains("Kesulitan dalan memulai/menghentikan setiap gerakan atau kekakuan dalam memulai gerakan volunter")){
                    BSulit3.setSelected(true);
                }else if (bradikinesia.contains("Jarang terdapat gerakan-gerakan terarah, hampir nampak tak bergerak")){
                    BJarang.setSelected(true);
                }
            SkorBradi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            
            EKananAtas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            SkorKananAtas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            EKiriAtas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            SkorKiriAtas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            EKananBawah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            SkorKananBawah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            EKiriBawah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            SkorKiriBawah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            SkorRigid.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            
            String berjalan = tbObat.getValueAt(tbObat.getSelectedRow(),35).toString();
                if(berjalan.contains("Normal")){
                    JNormal.setSelected(true);
                }else if (berjalan.equals("Penurunan ringan dari ayunan lengan")){
                    JTurun.setSelected(true);
                }else if (berjalan.contains("Penurunan sedang dari ayunan lengan, langkah normal")){
                    JTurun2.setSelected(true);
                }else if (berjalan.contains("Tidak ada ayunan lengan, kepala fleksi, langkah kurang lebih normal")){
                    JAyunan.setSelected(true);
                }else if (berjalan.contains("Postur tubuh kaku (leher dan punggung). melangkah dengan kaki diseret")){
                    JKaku.setSelected(true);
                }else if (berjalan.contains("Lebih nyata, kekakuan dalam berputar")){
                    JNyata.setSelected(true);
                }else if (berjalan.contains("Triple flexi, hampir tidak mampu berjalan")){
                    JFlexi.setSelected(true);
                }
            SkorJalan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
                
            TLengka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            String tremorlengka = tbObat.getValueAt(tbObat.getSelectedRow(),38).toString();
                if (tremorlengka.contains("Sekali-sekali")){
                    SekaliLengka.setSelected(true);
                }else if(tremorlengka.contains("Sering")){
                    SeringLengka.setSelected(true);
                }else if (tremorlengka.contains("Selalu")){
                    SelaluLengka.setSelected(true);
                }
            SkorLengka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            
            TLengki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            String tremorlengki = tbObat.getValueAt(tbObat.getSelectedRow(),41).toString();
                if (tremorlengki.contains( "Sekali-sekali")){
                    SekaliLengki.setSelected(true);
                }else if(tremorlengki.contains("Sering")){
                    SeringLengki.setSelected(true);
                }else if (tremorlengki.contains("Selalu")){
                    SelaluLengki.setSelected(true);
                }
            SkorLengki.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            
            TKaka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            String tremorkaka = tbObat.getValueAt(tbObat.getSelectedRow(),44).toString();
                if (tremorkaka.contains("Sekali-sekali")){
                    SekaliKaka.setSelected(true);
                }else if(tremorkaka.contains("Sering")){
                    SeringKaka.setSelected(true);
                }else if (tremorkaka.contains("Selalu")){
                    SelaluKaka.setSelected(true);
                }
            SkorKaka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            
            TKaki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            String tremorkaki = tbObat.getValueAt(tbObat.getSelectedRow(),47).toString();
                if (tremorkaki.contains("Sekali-sekali")){
                    SekaliKaki.setSelected(true);
                }else if(tremorkaki.contains("Sering")){
                    SeringKaki.setSelected(true);
                }else if (tremorkaki.contains("Selalu")){
                    SelaluKaki.setSelected(true);
                }
            SkorKaki.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            
            TKepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            String tremorkepala = tbObat.getValueAt(tbObat.getSelectedRow(),50).toString();
                if (tremorkepala.contains("Sekali-sekali")){
                    SekaliKepala.setSelected(true);
                }else if(tremorkepala.contains("Sering")){
                    SeringKepala.setSelected(true);
                }else if (tremorkepala.contains("Selalu")){
                    SelaluKepala.setSelected(true);
                }
            SkorKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
        
            TPipi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            String tremorrahang = tbObat.getValueAt(tbObat.getSelectedRow(),53).toString();
                if (tremorrahang.contains("Sekali-sekali")){
                    SekaliPipi.setSelected(true);
                }else if(tremorrahang.contains("Sering")){
                    SeringPipi.setSelected(true);
                }else if (tremorrahang.contains("Selalu")){
                    SelaluPipi.setSelected(true);
                }
            SkorPipi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            
            TLidah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            String tremorlidah = tbObat.getValueAt(tbObat.getSelectedRow(),56).toString();
                if (tremorlidah.contains("Sekali-sekali")){
                    SekaliLidah.setSelected(true);
                }else if(tremorlidah.contains("Sering")){
                    SeringLidah.setSelected(true);
                }else if (tremorlidah.contains("Selalu")){
                    SelaluLidah.setSelected(true);
                }
            SkorLidah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            
            TBibir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            String tremorbibir = tbObat.getValueAt(tbObat.getSelectedRow(),59).toString();
                if (tremorbibir.contains("Sekali-sekali")){
                    SekaliBibir.setSelected(true);
                }else if(tremorbibir.contains("Sering")){
                    SeringBibir.setSelected(true);
                }else if (tremorbibir.contains("Selalu")){
                    SelaluBibir.setSelected(true);
                }
            SkorBibir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            SkorTremor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());

            String akatisia = tbObat.getValueAt(tbObat.getSelectedRow(),62).toString();
                if(akatisia.contains("Tidak ada")){
                    ATidakada.setSelected(true);
                }else if (akatisia.contains("Tampak gelisah, nervous, paling tidak pada satu ektremitas")){
                    AGelisah.setSelected(true);
                }else if (akatisia.contains("Ingin selalu bergerak, paling tidak pada satu ekstremitas")){
                    AIngin.setSelected(true);
                }else if (akatisia.contains("Sering ingin menggerakkan satu ekstremitas atau mengubah posisi")){
                    AGerak.setSelected(true);
                }else if (akatisia.contains("Menggerakkan satu ekstremitas hampir terus menerus pada saat duduk atau menghentak-hentakkan kaki ketika berdiri")){
                    AHentak.setSelected(true);
                }else if (akatisia.contains("Hanya mampu untuk tetap duduk dalam jangka waktu yang pendek")){
                    ADuduk.setSelected(true);
                }else if (akatisia.contains("Bergerak atau berjalan terus-menerus")){
                    ATerus.setSelected(true);
                }
            SkorAkatisia.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());

            String sialarhoe = tbObat.getValueAt(tbObat.getSelectedRow(),64).toString();
                    if(sialarhoe.contains("Tidak ada")){
                    STidakada.setSelected(true);
                }else if (sialarhoe.contains("Sangat ringan")){
                    SgtRingan.setSelected(true);
                }else if (sialarhoe.contains("Ringan")){
                    SRingan.setSelected(true);
                }else if (sialarhoe.contains("Sedang, mengganggu pembicaraan")){
                    Ssedang.setSelected(true);
                }else if (sialarhoe.contains("Agak berat")){
                    SAgakberat.setSelected(true);
                }else if (sialarhoe.contains("Berat")){
                    SBerat.setSelected(true);
                }else if (sialarhoe.contains("Sangat berat, drooling")){
                    SSangatberat.setSelected(true);    
                }
            SkorSialorhoe.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            
            String stabilitas = tbObat.getValueAt(tbObat.getSelectedRow(),66).toString();
                if(stabilitas.contains("Normal")){
                    StabTidakada.setSelected(true);
                }else if (stabilitas.contains("Kelambatan ketika didorong, tetapi tanpa retropulsi")){
                    StabLambat.setSelected(true);
                }else if (stabilitas.contains("Retropulsi, tetapi segera pulih tanpa bantuan")){
                    StabRetro.setSelected(true);
                }else if (stabilitas.contains("Retropulsi yang lebih nyata, tetapi tidak terjatuh")){
                    StabRetro1.setSelected(true);
                }else if (stabilitas.contains("Tidak ada respon postural, akan terjatuh jika tidak ditahan oleh pemeriksa")){
                    StabRespon.setSelected(true);
                }else if (stabilitas.contains("Berdiri tidak stabil, tanpa didorong sekalipun")){
                    StabStabil.setSelected(true);
                }else if (stabilitas.contains("Tidak mampu berdiri tanpa bantuan")){
                    StabMampu.setSelected(true); 
                }
            SkorStabilitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            
            DistoniaTangka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            SkorDTangka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            DistoniaTangki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
            SkorDTangki.setText(tbObat.getValueAt(tbObat.getSelectedRow(),71).toString());
            DistoniaKaka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
            SkorDKaka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
            DistoniaKaki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),74).toString());
            SkorDKaki.setText(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
            DistoniaKepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
            SkorDKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),77).toString());
            DistoniaRahang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            SkorDRahang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString());
            DistoniaLidah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),80).toString());
            SkorDLidah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),81).toString());
            DistoniaBibir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),82).toString());
            SkorDBibir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),83).toString());
            DistoniaTrunk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),84).toString());
            SkorDTrunk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),85).toString());
            SkorDistonia.setText(tbObat.getValueAt(tbObat.getSelectedRow(),86).toString());
            
            TardifTangka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),87).toString());
            SkorTTangka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),88).toString());
            TardifTangki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),89).toString());
            SkorTTangki.setText(tbObat.getValueAt(tbObat.getSelectedRow(),90).toString());
            TardifKaka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),91).toString());
            SkorTKaka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),92).toString());
            TardifKaki.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),93).toString());
            SkorTKaki.setText(tbObat.getValueAt(tbObat.getSelectedRow(),94).toString());
            TardifKepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),95).toString());
            SkorTKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),96).toString());
            TardifRahang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),97).toString());
            SkorTRahang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),98).toString());
            TardifLidah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),99).toString());
            SkorTLidah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),100).toString());
            TardifBibir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),101).toString());
            SkorTBibir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),102).toString());
            TardifTrunk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),103).toString());
            SkorTTrunk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),104).toString());
            SkorTardif.setText(tbObat.getValueAt(tbObat.getSelectedRow(),105).toString());
            
            DiskiLidah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),106).toString());
            String diskilidah = tbObat.getValueAt(tbObat.getSelectedRow(),107).toString();
                if (diskilidah.contains("Jarang")){
                    radJarang1.setSelected(true);
                }else if(diskilidah.contains("Sering")){
                    radSering1.setSelected(true);
                }else if (diskilidah.contains("Selalu")){
                    radSelalu1.setSelected(true);
                }
            Skordiskilid.setText(tbObat.getValueAt(tbObat.getSelectedRow(),108).toString());
                
            DiskiRahang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),109).toString());
            String diskirahang = tbObat.getValueAt(tbObat.getSelectedRow(),110).toString(); 
                if (diskirahang.contains("Jarang")){
                    radJarang2.setSelected(true);
                }else if(diskirahang.contains("Sering")){
                    radSering2.setSelected(true);
                }else if (diskirahang.contains("Selalu")){
                    radSelalu2.setSelected(true);
                }
            Skordiskirah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),111).toString());
            
            DiskiBibir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),112).toString());
            String diskipipi = tbObat.getValueAt(tbObat.getSelectedRow(),113).toString(); 
                if (diskipipi.contains("Jarang")){
                    radJarang3.setSelected(true);
                }else if(diskipipi.contains("Sering")){
                    radSering3.setSelected(true);
                }else if (diskipipi.contains("Selalu")){
                    radSelalu3.setSelected(true);
                }
            Skordiskipi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),114).toString());
            
            DiskiBadan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),115).toString());
            String diskibadan = tbObat.getValueAt(tbObat.getSelectedRow(),116).toString(); 
                if (diskibadan.contains("Jarang")){
                    radJarang4.setSelected(true);
                }else if(diskibadan.contains("Sering")){
                    radSering4.setSelected(true);
                }else if (diskibadan.contains("Selalu")){
                    radSelalu4.setSelected(true);
                }
            Skordiskidan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),117).toString());
            
            DiskiEkstrematas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),118).toString());
            String diskieksatas = tbObat.getValueAt(tbObat.getSelectedRow(),119).toString();
                if (diskieksatas.contains("Jarang")){
                    radJarang5.setSelected(true);
                }else if(diskieksatas.contains("Sering")){
                    radSering5.setSelected(true);
                }else if (diskieksatas.contains("Selalu")){
                    radSelalu5.setSelected(true);
                }
            Skordiskitas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),120).toString());
            
            DiskiEkstrembawah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),121).toString());
            String diskieksbawah = tbObat.getValueAt(tbObat.getSelectedRow(),122).toString();
                if (diskieksbawah.contains("Jarang")){
                    radJarang6.setSelected(true);
                }else if(diskieksbawah.contains("Sering")){
                    radSering6.setSelected(true);
                }else if (diskieksbawah.contains("Selalu")){
                    radSelalu6.setSelected(true);
                }
            Skordiskiwah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),123).toString());
            
            DiskiInvolunter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),124).toString());
            String diskiinvol = tbObat.getValueAt(tbObat.getSelectedRow(),125).toString();
                if (diskiinvol.contains("Jarang")){
                    radJarang7.setSelected(true);
                }else if(diskiinvol.contains("Sering")){
                    radSering7.setSelected(true);
                }else if (diskiinvol.contains("Selalu")){
                    radSelalu7.setSelected(true);
                }  
            Skordiskivol.setText(tbObat.getValueAt(tbObat.getSelectedRow(),126).toString());
            
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
        BtnSimpan.setEnabled(akses.getpenilaian_gejala_ekstrapiramidal());
        BtnHapus.setEnabled(akses.getpenilaian_gejala_ekstrapiramidal());
        BtnEdit.setEnabled(akses.getpenilaian_gejala_ekstrapiramidal());
        BtnEdit.setEnabled(akses.getpenilaian_gejala_ekstrapiramidal());
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
        if(Sequel.queryu2tf("delete from penilaian_esrs where no_rawat=?",1,new String[]{
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
        
        String mukatopeng = "";
        String bradi = "";
        String postur = "";
        
        String tremorlengka = "";
        String tremorlengki = "";
        String tremorkaka = "";
        String tremorkaki = "";
        String tremorkepala = "";
        String tremorrahang = "";
        String tremorlidah = "";
        String tremorbibir = "";
        
        String akatisia = "";
        String sialarhoe = "";
        String stabilitas = "";
        
        
        String diskilidah = "";
        String diskirahang = "";
        String diskipipi = "";
        String diskibadan = "";
        String diskieksatas = "";
        String diskieksbawah = "";
        String diskiinvol = "";
        
        
        //mukatopeng
        if (GNormal.isSelected()){
            mukatopeng = "Normal";
        }else if (GPenurunan1.isSelected()){
            mukatopeng = "Penurunan yang sangat ringan dari ekspresi muka";
        }else if (GPenurunan2.isSelected()){
            mukatopeng = "Penurunan ringan dari ekspresi muka";
        }else if (GSenyum.isSelected()){
            mukatopeng = "Senyum spontan jarang, berkedip kurang, suara agak monoton";
        }else if (GSenyum2.isSelected()){
            mukatopeng = "Tidak ada senyum spontan, pandangan kosong, pembicaraan perlahan dan monoton, bergumam";
        }else if (GMuka1.isSelected()){
            mukatopeng = "Muka topeng yang nyata, tidak mempu mengerutkan dahi, bicara pelo";
        }else if (GMuka2.isSelected()){
            mukatopeng = "Muka topeng yang sangat nyata dengan pembicaran yang sulit dimengerti";
        }
        
        //bradi
        if (BNormal.isSelected()){
            bradi = "Normal";
        }else if (BLambat1.isSelected()){
            bradi = "Kesan perlambatan yang menyeluruh dari gerakan-gerakan";
        }else if (BLambat2.isSelected()){
            bradi = "Kelambatan yang nyata dalam gerakan";
        }else if (BSulit1.isSelected()){
            bradi = "Kesulitan yang sangat ringan dalam memulai gerakan";
        }else if (BSulit2.isSelected()){
            bradi = "Kesulitan ringan sampai sedang dalam memulai gerakan";
        }else if (BSulit3.isSelected()){
            bradi = "Kesulitan dalan memulai/menghentikan setiap gerakan atau kekakuan dalam memulai gerakan volunter";
        }else if (BJarang.isSelected()){
            bradi = "Jarang terdapat gerakan-gerakan terarah, hampir nampak tak bergerak";
        }
        
        
        //postur
        if (JNormal.isSelected()){
            postur = "Normal";
        } else if (JTurun.isSelected()){
            postur = "Penurunan ringan dari ayunan lengan";
        }else if (JTurun2.isSelected()){
            postur = "Penurunan sedang dari ayunan lengan, langkah normal";
        }else if (JAyunan.isSelected()){
            postur = "Tidak ada ayunan lengan, kepala fleksi, langkah kurang lebih normal";
        }else if (JKaku.isSelected()){
            postur = "Postur tubuh kaku (leher dan punggung). melangkah dengan kaki diseret";
        }else if (JNyata.isSelected()){
            postur = "Lebih nyata, kekakuan dalam berputar";
        }else if (JFlexi.isSelected()){
            postur = "Triple flexi, hampir tidak mampu berjalan";
        }
        
        
        //lengka
        if (SekaliLengka.isSelected()){
            tremorlengka = "Sekali-sekali";
        }else if(SeringLengka.isSelected()){
            tremorlengka = "Sering";
        }else if (SelaluLengka.isSelected()){
            tremorlengka = "Selalu";
        }
        
        
        //lengki
        if (SekaliLengki.isSelected()){
            tremorlengki = "Sekali-sekali";
        }else if(SeringLengki.isSelected()){
            tremorlengki = "Sering";
        }else if (SelaluLengki.isSelected()){
            tremorlengki = "Selalu";
        }
        
        
        //kaka
        if (SekaliKaka.isSelected()){
            tremorkaka = "Sekali-sekali";
        }else if(SeringKaka.isSelected()){
            tremorkaka = "Sering";
        }else if (SelaluKaka.isSelected()){
            tremorkaka = "Selalu";
        }
        
        
        //kaki
        if (SekaliKaki.isSelected()){
            tremorkaki = "Sekali-sekali";
        }else if(SeringKaki.isSelected()){
            tremorkaki = "Sering";
        }else if (SelaluKaki.isSelected()){
            tremorkaki = "Selalu";
        }
        
        
        //kepala
        if (SekaliKepala.isSelected()){
            tremorkepala = "Sekali-sekali";
        }else if(SeringKepala.isSelected()){
            tremorkepala = "Sering";
        }else if (SelaluKepala.isSelected()){
            tremorkepala = "Selalu";
        }
        
        
        //rahang
        if (SekaliPipi.isSelected()){
            tremorrahang = "Sekali-sekali";
        }else if(SeringPipi.isSelected()){
            tremorrahang = "Sering";
        }else if (SelaluPipi.isSelected()){
            tremorrahang = "Selalu";
        }
        
        
        //lidah
        if (SekaliLidah.isSelected()){
            tremorlidah = "Sekali-sekali";
        }else if(SeringLidah.isSelected()){
            tremorlidah = "Sering";
        }else if (SelaluLidah.isSelected()){
            tremorlidah = "Selalu";
        }
        
        
        //bibir
        if (SekaliBibir.isSelected()){
            tremorbibir = "Sekali-sekali";
        }else if(SeringBibir.isSelected()){
            tremorbibir = "Sering";
        }else if (SelaluBibir.isSelected()){
            tremorbibir = "Selalu";
        }
        
        
        if(ATidakada.isSelected()){
            akatisia = "Tidak ada";
        }else if (AGelisah.isSelected()){
            akatisia = "Tampak gelisah, nervous, paling tidak pada satu ektremitas";
        }else if (AIngin.isSelected()){
            akatisia = "Ingin selalu bergerak, paling tidak pada satu ekstremitas";
        }else if (AGerak.isSelected()){
            akatisia = "Sering ingin menggerakkan satu ekstremitas atau mengubah posisi";
        }else if (AHentak.isSelected()){
            akatisia = "Menggerakkan satu ekstremitas hampir terus menerus pada saat duduk atau menghentak-hentakkan kaki ketika berdiri";
        }else if (ADuduk.isSelected()){
            akatisia = "Hanya mampu untuk tetap duduk dalam jangka waktu yang pendek";
        }else if (ATerus.isSelected()){
            akatisia = "Bergerak atau berjalan terus-menerus";
        }
        
        
        if(STidakada.isSelected()){
            sialarhoe = "Tidak ada";
        }else if (SgtRingan.isSelected()){
            sialarhoe = "Sangat ringan";
        }else if (SRingan.isSelected()){
            sialarhoe = "Ringan";
        }else if (Ssedang.isSelected()){
            sialarhoe = "Sedang, mengganggu pembicaraan";
        }else if (SAgakberat.isSelected()){
            sialarhoe = "Agak berat";
        }else if (SBerat.isSelected()){
            sialarhoe = "Berat";
        }else if (SSangatberat.isSelected()){
            sialarhoe = "Sangat berat, drooling";
        }
        
        
        if(StabTidakada.isSelected()){
            stabilitas = "Normal";
        }else if (StabLambat.isSelected()){
            stabilitas = "Kelambatan ketika didorong, tetapi tanpa retropulsi";
        }else if (StabRetro.isSelected()){
            stabilitas = "Retropulsi, tetapi segera pulih tanpa bantuan";
        }else if (StabRetro1.isSelected()){
            stabilitas = "Retropulsi yang lebih nyata, tetapi tidak terjatuh";
        }else if (StabRespon.isSelected()){
            stabilitas = "Tidak ada respon postural, akan terjatuh jika tidak ditahan oleh pemeriksa";
        }else if (StabStabil.isSelected()){
            stabilitas = "Berdiri tidak stabil, tanpa didorong sekalipun";
        }else if (StabMampu.isSelected()){
            stabilitas = "Tidak mampu berdiri tanpa bantuan";
        }
        
        
        if (radJarang1.isSelected()){
            diskilidah = "Jarang";
        }else if(radSering1.isSelected()){
            diskilidah = "Sering";
        }else if (radSelalu1.isSelected()){
            diskilidah = "Selalu";
        }
        
        
        if (radJarang2.isSelected()){
            diskirahang = "Jarang";
        }else if(radSering2.isSelected()){
            diskirahang = "Sering";
        }else if (radSelalu2.isSelected()){
            diskirahang = "Selalu";
        }
        
        
        if (radJarang3.isSelected()){
            diskipipi = "Jarang";
        }else if(radSering3.isSelected()){
            diskipipi = "Sering";
        }else if (radSelalu3.isSelected()){
            diskipipi = "Selalu";
        }
        
        
        if (radJarang4.isSelected()){
            diskibadan = "Jarang";
        }else if(radSering4.isSelected()){
            diskibadan = "Sering";
        }else if (radSelalu4.isSelected()){
            diskibadan = "Selalu";
        }
        
        
        if (radJarang5.isSelected()){
            diskieksatas = "Jarang";
        }else if(radSering5.isSelected()){
            diskieksatas = "Sering";
        }else if (radSelalu5.isSelected()){
            diskieksatas = "Selalu";
        }
        
        
         if (radJarang6.isSelected()){
            diskieksbawah = "Jarang";
        }else if(radSering6.isSelected()){
            diskieksbawah = "Sering";
        }else if (radSelalu6.isSelected()){
            diskieksbawah = "Selalu";
        }
         
        
        if (radJarang7.isSelected()){
            diskiinvol = "Jarang";
        }else if(radSering7.isSelected()){
            diskiinvol = "Sering";
        }else if (radSelalu7.isSelected()){
            diskiinvol = "Selalu";
        }
        
        if(Sequel.mengedittf("penilaian_esrs","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,anamnesis=?,hubungan=?,"+
                
                "kues_lambat=?,kues_berjalan=?,kues_menelan=?,kues_kaku=?,kues_kram=?,kues_gelisah=?,kues_tremor=?,kues_postur=?,kues_ludah=?,kues_diskinesiagerak=?,kues_diskinesialidah=?,kues_pusing=?,"+
                
                "mukatopeng=?,skormuka=?,"
                + "bradi=?,skorbradi=?,"
                + "rigi_ekanana=?,skorekanana=?,rigi_ekiria=?,skorekiria=?,rigi_ekananb=?,skorekananb=?,rigi_ekirib=?,skorekirib=?,skorrigid=?,"+
                "postur=?,skorjalan=?,ketlengka=?,rasiolengka=?,skorlengka=?,ketlengki=?,rasiolengki=?,skorlengki=?,ketkaka=?,rasiokaka=?,skorkaka=?,ketkaki=?,rasiokaki=?,skorkaka=?,ketkepala=?,rasiokepala=?,skorkepala=?,"+
                "ketrahang=?,rasiorahang=?,skorrahang=?,ketlidah=?,rasiolidah=?,skorlidah=?,ketbibir=?,rasiobibir=?,skorbibir=?,skortremor=?,"+
                "akatisia=?,skorakatisia=?,sialorhoe=?,skorsialorhoe=?,stabilitas=?,skorstabilitas=?,torsitangka=?,skortorsitangka=?,torsitangki=?,skortorsitangki=?,"+
                
                
                "torsikaka=?,skortorsikaka=?,torsikaki=?,skortorsikaki=?,torsikepala=?,skortorsikepala=?,torsirahang=?,skortorsirahang=?,torsilidah=?,skortorsilidah=?,torsibibir=?,skortorsibibir=?,torsitrunk=?,skortorsitrunk=?,skortorsi=?,"+
                "tardiftangka=?,skortardiftangka=?,tardiftangki=?,skortardiftangki=?,tardifkaka=?,skortardifkaka=?,tardifkaki=?,skortardifkaki=?,tardifkepala=?,skortardifkepala=?,tardifrahang=?,skortardifrahang=?,tardiflidah=?,skortardiflidah=?,tardifbibir=?,skortardifbibir=?,tardiftrunk=?,skortardiftrunk=?,skortardif=?,"+
                
                
                "diskilidah=?,rasiodiskilidah=?,skordiskilidah=?,diskirahang=?,rasiodiskirahang=?,skordiskirahang=?,diskipipi=?,rasiodiskipipi=?,skordiskipipi=?,diskibadan=?,rasiodiskibadan=?,skordiskibadan=?,"+
                "diskieksatas=?,rasiodiskieksatas=?,skordiskieksatas=?,diskieksbawah=?,rasiodiskieksbawah=?,skordiskieskbawah=?,diskiinvol=?,rasiodiskiinvol=?,skordiskiinvol=?"
                ,123,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),
                    
                SulitTugasrutin.getSelectedItem().toString(),SulitBerjalan.getSelectedItem().toString(),SulitMenelan.getSelectedItem().toString(),Kaku.getSelectedItem().toString(),Kram.getSelectedItem().toString(),Gelisah.getSelectedItem().toString(),
                Tremor.getSelectedItem().toString(),PosturAbnormal.getSelectedItem().toString(),Ludah.getSelectedItem().toString(),DiskinesiaGerak.getSelectedItem().toString(),DiskinesiaLidah.getSelectedItem().toString(),Pusing.getSelectedItem().toString(),
                    
                //II.
                mukatopeng,SkorGerakan.getText(),
                bradi,SkorBradi.getText(),

                EKananAtas.getSelectedItem().toString(),SkorKananAtas.getText(),EKiriAtas.getSelectedItem().toString(),SkorKiriAtas.getText(),EKananBawah.getSelectedItem().toString(),SkorKananBawah.getText(),EKiriBawah.getSelectedItem().toString(),SkorKiriBawah.getText(),SkorRigid.getText(),
                postur,SkorJalan.getText(),

                TLengka.getSelectedItem().toString(),tremorlengka,SkorLengka.getText(),TLengki.getSelectedItem().toString(),tremorlengki,SkorLengki.getText(),TKaka.getSelectedItem().toString(),tremorkaka,SkorKaka.getText(),TKaki.getSelectedItem().toString(),tremorkaki,SkorKaki.getText(),
                TKepala.getSelectedItem().toString(),tremorkepala,SkorKepala.getText(),TPipi.getSelectedItem().toString(),tremorrahang,SkorPipi.getText(),TLidah.getSelectedItem().toString(),tremorlidah,SkorLidah.getText(),TBibir.getSelectedItem().toString(),tremorbibir,SkorBibir.getText(),SkorTremor.getText(),

                akatisia,SkorAkatisia.getText(),
                sialarhoe,SkorSialorhoe.getText(),
                stabilitas,SkorStabilitas.getText(),

                //III.
                DistoniaTangka.getSelectedItem().toString(),SkorDTangka.getText(),DistoniaTangki.getSelectedItem().toString(),SkorDTangki.getText(),DistoniaKaka.getSelectedItem().toString(),SkorDKaka.getText(),DistoniaKaki.getSelectedItem().toString(),SkorDKaki.getText(),DistoniaKepala.getSelectedItem().toString(),SkorDKepala.getText(),
                DistoniaRahang.getSelectedItem().toString(),SkorDRahang.getText(),DistoniaLidah.getSelectedItem().toString(),SkorDLidah.getText(),DistoniaBibir.getSelectedItem().toString(),SkorDBibir.getText(),DistoniaTrunk.getSelectedItem().toString(),SkorDTrunk.getText(),

                TardifTangka.getSelectedItem().toString(),SkorTTangka.getText(),TardifTangki.getSelectedItem().toString(),SkorTTangki.getText(),TardifKaka.getSelectedItem().toString(),SkorTKaka.getText(),TardifKaki.getSelectedItem().toString(),SkorTKaki.getText(),TardifKepala.getSelectedItem().toString(),SkorTKepala.getText(),
                TardifRahang.getSelectedItem().toString(),SkorTRahang.getText(),TardifLidah.getSelectedItem().toString(),SkorTLidah.getText(),TardifBibir.getSelectedItem().toString(),SkorTBibir.getText(),TardifTrunk.getSelectedItem().toString(),SkorTTrunk.getText(),

                //IV.
                DiskiLidah.getSelectedItem().toString(),diskilidah,Skordiskilid.getText(),
                DiskiRahang.getSelectedItem().toString(),diskirahang,Skordiskirah.getText(),
                DiskiBibir.getSelectedItem().toString(),diskipipi,Skordiskipi.getText(),
                DiskiBadan.getSelectedItem().toString(),diskibadan,Skordiskidan.getText(),
                DiskiEkstrematas.getSelectedItem().toString(),diskieksatas,Skordiskitas.getText(),
                DiskiEkstrembawah.getSelectedItem().toString(),diskieksbawah,Skordiskiwah.getText(),
                DiskiInvolunter.getSelectedItem().toString(),diskiinvol,Skordiskivol.getText()
               })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
    
    
     private void isTotalSkor(){
        try {
            SkorRigid.setText((Integer.parseInt(SkorKananAtas.getText())+Integer.parseInt(SkorKiriAtas.getText())+Integer.parseInt(SkorKananBawah.getText())+Integer.parseInt(SkorKiriBawah.getText()))+"");
            SkorTremor.setText((Integer.parseInt(SkorLengka.getText())+Integer.parseInt(SkorLengki.getText()))+Integer.parseInt(SkorKaka.getText())+Integer.parseInt(SkorKaki.getText())+Integer.parseInt(SkorKepala.getText())+Integer.parseInt(SkorPipi.getText())+Integer.parseInt(SkorLidah.getText())+Integer.parseInt(SkorBibir.getText())+"");
            SkorDistonia.setText((Integer.parseInt(SkorDTangka.getText())+Integer.parseInt(SkorDTangki.getText()))+Integer.parseInt(SkorDKaka.getText())+Integer.parseInt(SkorDKaki.getText())+Integer.parseInt(SkorDKepala.getText())+Integer.parseInt(SkorDRahang.getText())+Integer.parseInt(SkorDLidah.getText())+Integer.parseInt(SkorDBibir.getText())+Integer.parseInt(SkorDTrunk.getText())+"");
            SkorTardif.setText((Integer.parseInt(SkorTTangka.getText())+Integer.parseInt(SkorTTangki.getText()))+Integer.parseInt(SkorTKaka.getText())+Integer.parseInt(SkorTKaki.getText())+Integer.parseInt(SkorTKepala.getText())+Integer.parseInt(SkorTRahang.getText())+Integer.parseInt(SkorTLidah.getText())+Integer.parseInt(SkorTBibir.getText())+Integer.parseInt(SkorTTrunk.getText())+"");
            
//            SkorTotal.setText((Integer.parseInt(TotalStatik.getText()))+"");
//            if(Integer.parseInt(SkorTotal.getText())<=4){
//                Level.setText("Ketergantungan Penuh");
//            }else if(Integer.parseInt(SkorTotal.getText())>4 && Integer.parseInt(SkorTotal.getText())<=8){
//                Level.setText("Ketergantungan berat");
//            }else if(Integer.parseInt(SkorTotal.getText())>8 && Integer.parseInt(SkorTotal.getText())<=12){
//                Level.setText("Ketergantungan sebagian");
//            }else if(Integer.parseInt(SkorTotal.getText())>12 && Integer.parseInt(SkorTotal.getText())<=16){
//                Level.setText("Ketergantungan ringan");
//            }else if(Integer.parseInt(SkorTotal.getText())>16 && Integer.parseInt(SkorTotal.getText())<=20){
//                Level.setText("Mandiri");
//            }
        } catch (Exception e) {
            SkorRigid.setText("0");
            SkorTremor.setText("0");
            SkorDistonia.setText("0");
//            Level.setText("");
        }
    }
}
