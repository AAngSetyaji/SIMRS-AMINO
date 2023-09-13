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
public final class RMPenilaianAwalMedisRanapPsikiatrikAnakRemajaJiwa extends javax.swing.JDialog {
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
    public RMPenilaianAwalMedisRanapPsikiatrikAnakRemajaJiwa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Anamnesis","Hubungan","Keluhan Utama","Riwayat Penyakit Sekarang","Riwayat Penyakit Dahulu",
            "Riwayat Penyakit Keluarga","Riwayat Napza","Riwayat Alergi","Anamnesis Dengan","Hubungan","Kehamilan","Persalinan","Pola Makan","Toilet","Pk Fisik","Pk Emosi","Pk Sosial","Pk Psiko","Riwayat Sekolah","Riwayat Ayah","Riwayat Ibu","Riwayat Perkawinan Ortu","Riwayat Saudara","Faktor Keturunan","Genogram",
            "Penampilan","Status Kesadaran","Sikap","Tingkah Laku","Pembicaraan","Psikomotor","Mood","Proses Pikir","Gangguan Persepsi","Pengendalian Impuls","Memori","Daya Nilai","Tilikan","Fantasi","Superego","Kesadaran Anak","Motorik Kasar","Motorik Halus","Ass Bundir","PANSS EC","Fungsi Kognitif",
            "Keadaan","Kesadaran","GCS","TB(cm)","BB(Kg)","TD(mmHg)","Nadi(x/menit)","RR(x/menit)","Suhu","SpO2","Kepala","Gigi & Mulut","THT","Thoraks","Abdomen","Genital & Anus","Ekstremitas","Kulit","Ket. Fisik",
            "Penunjang",
            "Axis 1","Axis 2","Axis 3","Axis 4","Axis 5",
            "Permasalahan","Farmakulon","Non Farmakulon","Lama Perawatan","Target","Program","Konsul/Rujuk"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 50; i++) {
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
                column.setPreferredWidth(90);
            }else if(i==28){
                column.setPreferredWidth(40);
            }else if(i==29){
                column.setPreferredWidth(80);
            }else if(i==30){
                column.setPreferredWidth(60);
            }else if(i==31){
                column.setPreferredWidth(75);
            }else if(i==32){
                column.setPreferredWidth(68);
            }else if(i==33){
                column.setPreferredWidth(40);
            }else if(i==34){
                column.setPreferredWidth(40);
            }else if(i==35){
                column.setPreferredWidth(40);
            }else if(i==36){
                column.setPreferredWidth(40);
            }else if(i==37){
                column.setPreferredWidth(82);
            }else if(i==38){
                column.setPreferredWidth(82);
            }else if(i==39){
                column.setPreferredWidth(82);
            }else if(i==40){
                column.setPreferredWidth(82);
            }else if(i==41){
                column.setPreferredWidth(82);
            }else if(i==42){
                column.setPreferredWidth(82);
            }else if(i==43){
                column.setPreferredWidth(82);
            }else if(i==44){
                column.setPreferredWidth(82);
            }else if(i==44){
                column.setPreferredWidth(160);
            }else if(i==45){
                column.setPreferredWidth(160);
            }else if(i==46){
                column.setPreferredWidth(160);
            }else if(i==47){
                column.setPreferredWidth(160);
            }else if(i==48){
                column.setPreferredWidth(160);
            }else if(i==49){
                column.setPreferredWidth(160);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Hubungan.setDocument(new batasInput((int)30).getKata(Hubungan));
        KeluhanUtama.setDocument(new batasInput((int)2000).getKata(KeluhanUtama));
        RPS.setDocument(new batasInput((int)2000).getKata(RPS));
        RPK.setDocument(new batasInput((int)1000).getKata(RPK));
        RPD.setDocument(new batasInput((int)1000).getKata(RPD));
        RPO.setDocument(new batasInput((int)1000).getKata(RPO));
        Alergi.setDocument(new batasInput((int)50).getKata(Alergi));
        Ksadaran.setDocument(new batasInput((int)200).getKata(Ksadaran));
        Pembicaraan.setDocument(new batasInput((int)200).getKata(Pembicaraan));
        Psikomotor.setDocument(new batasInput((int)200).getKata(Psikomotor));
        Sikap.setDocument(new batasInput((int)200).getKata(Sikap));
        Mood.setDocument(new batasInput((int)200).getKata(Mood));
        Fungsikognitif.setDocument(new batasInput((int)200).getKata(Fungsikognitif));
        Gangguanpersepsi.setDocument(new batasInput((int)200).getKata(Gangguanpersepsi));
        Prosespikir.setDocument(new batasInput((int)200).getKata(Prosespikir));
        Pengendalianimpuls.setDocument(new batasInput((int)200).getKata(Pengendalianimpuls));
        Tilikan.setDocument(new batasInput((int)200).getKata(Tilikan));
        GCS.setDocument(new batasInput((byte)10).getKata(GCS));
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        Nadi.setDocument(new batasInput((byte)5).getKata(Nadi));
        RR.setDocument(new batasInput((byte)5).getKata(RR));
        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        SPO.setDocument(new batasInput((byte)5).getKata(SPO));
        BB.setDocument(new batasInput((byte)5).getKata(BB));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        KetFisik.setDocument(new batasInput((int)1000).getKata(KetFisik));
        Penunjang.setDocument(new batasInput((int)1000).getKata(Penunjang));
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
        jLabel12 = new widget.Label();
        BB = new widget.TextBox();
        jLabel13 = new widget.Label();
        TB = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel24 = new widget.Label();
        Anamnesis = new widget.ComboBox();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        RPD = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        RPK = new widget.TextArea();
        jLabel32 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        RPO = new widget.TextArea();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel94 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel38 = new widget.Label();
        Hubungan = new widget.TextBox();
        jLabel33 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        RPS = new widget.TextArea();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel39 = new widget.Label();
        Keadaan = new widget.ComboBox();
        Kesadaran = new widget.ComboBox();
        jLabel41 = new widget.Label();
        jLabel99 = new widget.Label();
        jLabel79 = new widget.Label();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel100 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        Penunjang = new widget.TextArea();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel101 = new widget.Label();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jLabel14 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Ksadaran = new widget.TextArea();
        jLabel34 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Pembicaraan = new widget.TextArea();
        jLabel36 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Psikomotor = new widget.TextArea();
        jLabel42 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        Mood = new widget.TextArea();
        jLabel43 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Sikap = new widget.TextArea();
        jLabel47 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Fungsikognitif = new widget.TextArea();
        jLabel48 = new widget.Label();
        scrollPane19 = new widget.ScrollPane();
        Gangguanpersepsi = new widget.TextArea();
        jLabel53 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Prosespikir = new widget.TextArea();
        jLabel54 = new widget.Label();
        scrollPane21 = new widget.ScrollPane();
        Pengendalianimpuls = new widget.TextArea();
        jLabel56 = new widget.Label();
        scrollPane23 = new widget.ScrollPane();
        Tilikan = new widget.TextArea();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel16 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel25 = new widget.Label();
        RR = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel29 = new widget.Label();
        SPO = new widget.TextBox();
        jLabel35 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        KetFisik = new widget.TextArea();
        Abdomen = new widget.ComboBox();
        jLabel49 = new widget.Label();
        Kepala = new widget.ComboBox();
        jLabel40 = new widget.Label();
        jLabel44 = new widget.Label();
        Gigi = new widget.ComboBox();
        jLabel50 = new widget.Label();
        Genital = new widget.ComboBox();
        Ekstremitas = new widget.ComboBox();
        jLabel51 = new widget.Label();
        THT = new widget.ComboBox();
        jLabel45 = new widget.Label();
        Kulit = new widget.ComboBox();
        jLabel52 = new widget.Label();
        Thoraks = new widget.ComboBox();
        jLabel46 = new widget.Label();
        Alergi = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel37 = new widget.Label();
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
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel73 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        Penampilan = new widget.TextArea();
        jLabel74 = new widget.Label();
        scrollPane24 = new widget.ScrollPane();
        TingkahLaku = new widget.TextArea();
        jLabel75 = new widget.Label();
        scrollPane25 = new widget.ScrollPane();
        Memori = new widget.TextArea();
        jLabel76 = new widget.Label();
        scrollPane26 = new widget.ScrollPane();
        DayaNilai = new widget.TextArea();
        jLabel77 = new widget.Label();
        scrollPane27 = new widget.ScrollPane();
        PANSSCE = new widget.TextArea();
        jLabel78 = new widget.Label();
        scrollPane28 = new widget.ScrollPane();
        Fantasi = new widget.TextArea();
        jLabel80 = new widget.Label();
        scrollPane29 = new widget.ScrollPane();
        Superego = new widget.TextArea();
        jLabel81 = new widget.Label();
        scrollPane30 = new widget.ScrollPane();
        KesadaranAnak = new widget.TextArea();
        jLabel82 = new widget.Label();
        scrollPane31 = new widget.ScrollPane();
        MotorikKasar = new widget.TextArea();
        jLabel83 = new widget.Label();
        scrollPane32 = new widget.ScrollPane();
        MotorikHalus = new widget.TextArea();
        jLabel84 = new widget.Label();
        scrollPane33 = new widget.ScrollPane();
        Bundir = new widget.TextArea();
        Anamnesis1 = new widget.TextBox();
        Hubungan1 = new widget.TextBox();
        scrollPane10 = new widget.ScrollPane();
        Kehamilan = new widget.TextArea();
        scrollPane22 = new widget.ScrollPane();
        Persalinan = new widget.TextArea();
        scrollPane34 = new widget.ScrollPane();
        PolaMakan = new widget.TextArea();
        scrollPane35 = new widget.ScrollPane();
        Toilet = new widget.TextArea();
        scrollPane36 = new widget.ScrollPane();
        PerkembanganFisik = new widget.TextArea();
        scrollPane37 = new widget.ScrollPane();
        PerkembanganEmosi = new widget.TextArea();
        scrollPane38 = new widget.ScrollPane();
        PerkembanganSosial = new widget.TextArea();
        scrollPane39 = new widget.ScrollPane();
        PerkembanganPsiko = new widget.TextArea();
        scrollPane40 = new widget.ScrollPane();
        RiwayatSekolah = new widget.TextArea();
        Ayah = new widget.TextBox();
        Ibu = new widget.TextBox();
        Perkawinan = new widget.TextBox();
        Saudara = new widget.TextBox();
        FaktorKeturunan = new widget.TextBox();
        scrollPane41 = new widget.ScrollPane();
        Genogram = new widget.TextArea();
        jLabel85 = new widget.Label();
        jLabel55 = new widget.Label();
        jLabel86 = new widget.Label();
        Axis1 = new widget.TextBox();
        Axis2 = new widget.TextBox();
        jLabel87 = new widget.Label();
        Axis3 = new widget.TextBox();
        jLabel88 = new widget.Label();
        Axis4 = new widget.TextBox();
        Axis5 = new widget.TextBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        scrollPane42 = new widget.ScrollPane();
        Permasalahan = new widget.TextArea();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        scrollPane43 = new widget.ScrollPane();
        Farmakulon = new widget.TextArea();
        scrollPane44 = new widget.ScrollPane();
        NonFarmakulon = new widget.TextArea();
        scrollPane45 = new widget.ScrollPane();
        LamaPerawatan = new widget.TextArea();
        scrollPane46 = new widget.ScrollPane();
        targetTindakan = new widget.TextArea();
        scrollPane47 = new widget.ScrollPane();
        Program = new widget.TextArea();
        jLabel98 = new widget.Label();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel104 = new widget.Label();
        scrollPane49 = new widget.ScrollPane();
        KonsulRujuk = new widget.TextArea();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Medis Rawat Inap Psikiatri Anak dan Remaja ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2800));
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

        jLabel9.setText("Hubungan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(400, 270, 70, 23);

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

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(760, 1670, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(790, 1670, 45, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(840, 1670, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(670, 1670, 45, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(640, 1670, 30, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" Cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(720, 1670, 30, 23);

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(644, 40, 128, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        KeluhanUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama.setColumns(20);
        KeluhanUtama.setRows(5);
        KeluhanUtama.setName("KeluhanUtama"); // NOI18N
        KeluhanUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtamaKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(KeluhanUtama);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(129, 90, 310, 43);

        jLabel30.setText("Riwayat Penyakit Sekarang :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(440, 90, 150, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        RPD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPD.setColumns(20);
        RPD.setRows(5);
        RPD.setName("RPD"); // NOI18N
        RPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPDKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(RPD);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(594, 140, 260, 43);

        jLabel31.setText("Riwayat Penyakit Dahulu :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(440, 140, 150, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        RPK.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPK.setColumns(20);
        RPK.setRows(5);
        RPK.setName("RPK"); // NOI18N
        RPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPKKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(RPK);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(223, 140, 216, 43);

        jLabel32.setText("Riwayat Penyakit Fisik & Neurologi :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 140, 219, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        RPO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPO.setColumns(20);
        RPO.setRows(5);
        RPO.setName("RPO"); // NOI18N
        RPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPOKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(RPO);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(130, 190, 309, 43);

        jLabel28.setText("GCS (E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(490, 1670, 70, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(560, 1670, 60, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("IV. PEMERIKSAAN FISIK");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 1650, 180, 23);

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

        jLabel33.setText("Keluhan Utama :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 90, 125, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        RPS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPS.setColumns(20);
        RPS.setRows(5);
        RPS.setName("RPS"); // NOI18N
        RPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPSKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(RPS);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(594, 90, 260, 43);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 1170, 880, 1);

        jLabel39.setText("Kesadaran :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(270, 1670, 70, 23);

        Keadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sehat", "Sakit Ringan", "Sakit Sedang", "Sakit Berat" }));
        Keadaan.setName("Keadaan"); // NOI18N
        Keadaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanKeyPressed(evt);
            }
        });
        FormInput.add(Keadaan);
        Keadaan.setBounds(130, 1670, 118, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Apatis", "Somnolen", "Sopor", "Koma" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(340, 1670, 130, 23);

        jLabel41.setText("Keadaan Umum :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 1670, 127, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("II. RIWAYAT PERKEMBANGAN ANAK");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 250, 180, 23);

        jLabel79.setText("Riwayat Alergi :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(440, 190, 150, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 1870, 880, 1);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("III. STATUS PSIKIATRIK");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(10, 1170, 180, 23);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        Penunjang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Penunjang.setColumns(20);
        Penunjang.setRows(5);
        Penunjang.setName("Penunjang"); // NOI18N
        Penunjang.setPreferredSize(new java.awt.Dimension(102, 52));
        Penunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenunjangKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Penunjang);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(40, 1890, 810, 63);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(0, 1970, 880, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("V. PEMERIKSAAN PENUNJANG");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(10, 1870, 190, 23);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(0, 2070, 880, 1);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("VI. DIAGNOSIS/ASESMEN");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(10, 1970, 190, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("VII. PERMASALAHAN & TATALAKSANA");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(10, 2070, 190, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(380, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-08-2023 08:54:46" }));
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

        jLabel14.setText("Kesadaran :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 1230, 124, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Ksadaran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ksadaran.setColumns(20);
        Ksadaran.setRows(5);
        Ksadaran.setName("Ksadaran"); // NOI18N
        Ksadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KsadaranKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Ksadaran);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(130, 1230, 290, 33);

        jLabel34.setText("Pembicaraan :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 1350, 124, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        Pembicaraan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Pembicaraan.setColumns(20);
        Pembicaraan.setRows(5);
        Pembicaraan.setName("Pembicaraan"); // NOI18N
        Pembicaraan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PembicaraanKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Pembicaraan);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(130, 1350, 290, 33);

        jLabel36.setText("Psikomotor :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 1390, 124, 23);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Psikomotor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Psikomotor.setColumns(20);
        Psikomotor.setRows(5);
        Psikomotor.setName("Psikomotor"); // NOI18N
        Psikomotor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PsikomotorKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Psikomotor);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(130, 1390, 290, 33);

        jLabel42.setText("Mood / Afek :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 1430, 124, 23);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        Mood.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Mood.setColumns(20);
        Mood.setRows(5);
        Mood.setName("Mood"); // NOI18N
        Mood.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoodKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Mood);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(130, 1430, 290, 33);

        jLabel43.setText("Tingkah Laku :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 1310, 124, 23);

        scrollPane17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane17.setName("scrollPane17"); // NOI18N

        Sikap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Sikap.setColumns(20);
        Sikap.setRows(5);
        Sikap.setName("Sikap"); // NOI18N
        Sikap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SikapKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(Sikap);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(130, 1270, 290, 33);

        jLabel47.setText("Fungsi Kognitif :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(510, 1580, 124, 23);

        scrollPane18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane18.setName("scrollPane18"); // NOI18N

        Fungsikognitif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Fungsikognitif.setColumns(20);
        Fungsikognitif.setRows(5);
        Fungsikognitif.setName("Fungsikognitif"); // NOI18N
        Fungsikognitif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsikognitifKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(Fungsikognitif);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(640, 1580, 290, 33);

        jLabel48.setText("Gangguan Persepsi :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 1510, 120, 23);

        scrollPane19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane19.setName("scrollPane19"); // NOI18N

        Gangguanpersepsi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Gangguanpersepsi.setColumns(20);
        Gangguanpersepsi.setRows(5);
        Gangguanpersepsi.setName("Gangguanpersepsi"); // NOI18N
        Gangguanpersepsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanpersepsiKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(Gangguanpersepsi);

        FormInput.add(scrollPane19);
        scrollPane19.setBounds(130, 1510, 290, 33);

        jLabel53.setText("Proses Pikir & Isi Pikir :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 1470, 120, 23);

        scrollPane20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane20.setName("scrollPane20"); // NOI18N

        Prosespikir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Prosespikir.setColumns(20);
        Prosespikir.setRows(5);
        Prosespikir.setName("Prosespikir"); // NOI18N
        Prosespikir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsespikirKeyPressed(evt);
            }
        });
        scrollPane20.setViewportView(Prosespikir);

        FormInput.add(scrollPane20);
        scrollPane20.setBounds(130, 1470, 290, 33);

        jLabel54.setText("Memori :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 1590, 120, 23);

        scrollPane21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane21.setName("scrollPane21"); // NOI18N

        Pengendalianimpuls.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Pengendalianimpuls.setColumns(20);
        Pengendalianimpuls.setRows(5);
        Pengendalianimpuls.setName("Pengendalianimpuls"); // NOI18N
        Pengendalianimpuls.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PengendalianimpulsKeyPressed(evt);
            }
        });
        scrollPane21.setViewportView(Pengendalianimpuls);

        FormInput.add(scrollPane21);
        scrollPane21.setBounds(130, 1550, 290, 33);

        jLabel56.setText("Daya Nilai :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(510, 1190, 120, 23);

        scrollPane23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane23.setName("scrollPane23"); // NOI18N

        Tilikan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tilikan.setColumns(20);
        Tilikan.setRows(5);
        Tilikan.setName("Tilikan"); // NOI18N
        Tilikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TilikanKeyPressed(evt);
            }
        });
        scrollPane23.setViewportView(Tilikan);

        FormInput.add(scrollPane23);
        scrollPane23.setBounds(640, 1230, 290, 33);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(0, 1650, 880, 1);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(370, 1700, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(320, 1700, 45, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(280, 1700, 40, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(610, 1700, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(650, 1700, 45, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 1700, 127, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(130, 1700, 76, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(700, 1700, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(210, 1700, 50, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(530, 1700, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(480, 1700, 45, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(440, 1700, 40, 23);

        jLabel29.setText("SpO2 :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(750, 1700, 40, 23);

        SPO.setFocusTraversalPolicyProvider(true);
        SPO.setName("SPO"); // NOI18N
        SPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPOKeyPressed(evt);
            }
        });
        FormInput.add(SPO);
        SPO.setBounds(790, 1700, 45, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("%");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(840, 1700, 30, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        KetFisik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KetFisik.setColumns(20);
        KetFisik.setRows(5);
        KetFisik.setName("KetFisik"); // NOI18N
        KetFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetFisikKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(KetFisik);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(510, 1730, 340, 113);

        Abdomen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Abdomen.setName("Abdomen"); // NOI18N
        Abdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbdomenKeyPressed(evt);
            }
        });
        FormInput.add(Abdomen);
        Abdomen.setBounds(370, 1730, 128, 23);

        jLabel49.setText("Abdomen :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(270, 1730, 95, 23);

        Kepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Kepala.setName("Kepala"); // NOI18N
        Kepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KepalaKeyPressed(evt);
            }
        });
        FormInput.add(Kepala);
        Kepala.setBounds(130, 1730, 128, 23);

        jLabel40.setText("Kepala :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 1730, 127, 23);

        jLabel44.setText("Gigi & Mulut :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 1760, 127, 23);

        Gigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Gigi.setName("Gigi"); // NOI18N
        Gigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GigiKeyPressed(evt);
            }
        });
        FormInput.add(Gigi);
        Gigi.setBounds(130, 1760, 128, 23);

        jLabel50.setText("Genital & Anus :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(270, 1760, 95, 23);

        Genital.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Genital.setName("Genital"); // NOI18N
        Genital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GenitalKeyPressed(evt);
            }
        });
        FormInput.add(Genital);
        Genital.setBounds(370, 1760, 128, 23);

        Ekstremitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Ekstremitas.setName("Ekstremitas"); // NOI18N
        Ekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstremitasKeyPressed(evt);
            }
        });
        FormInput.add(Ekstremitas);
        Ekstremitas.setBounds(370, 1790, 128, 23);

        jLabel51.setText("Ekstremitas :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(270, 1790, 95, 23);

        THT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        THT.setName("THT"); // NOI18N
        THT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THTKeyPressed(evt);
            }
        });
        FormInput.add(THT);
        THT.setBounds(130, 1790, 128, 23);

        jLabel45.setText("THT :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 1790, 127, 23);

        Kulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Kulit.setName("Kulit"); // NOI18N
        Kulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KulitKeyPressed(evt);
            }
        });
        FormInput.add(Kulit);
        Kulit.setBounds(370, 1820, 128, 23);

        jLabel52.setText("Kulit :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(270, 1820, 95, 23);

        Thoraks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Thoraks.setName("Thoraks"); // NOI18N
        Thoraks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThoraksKeyPressed(evt);
            }
        });
        FormInput.add(Thoraks);
        Thoraks.setBounds(130, 1820, 128, 23);

        jLabel46.setText("AXIS III :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(250, 2000, 127, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(600, 190, 260, 23);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("I. RIWAYAT KESEHATAN PASIEN");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(10, 70, 180, 23);

        jLabel27.setText("Riwayat NAPZA :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 190, 126, 23);

        jLabel37.setText("3. Pola Makan :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(20, 450, 160, 23);

        jLabel57.setText("Anamnesis dengan :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 270, 126, 23);

        jLabel58.setText("1. Kehamilan Ibu :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(20, 310, 160, 23);

        jLabel59.setText("2. Persalinan :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(20, 380, 160, 23);

        jLabel60.setText("4. Toilet - Training :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(20, 520, 160, 23);

        jLabel61.setText("5. Perkembangan Fisik :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(20, 590, 160, 23);

        jLabel62.setText("6. Perkembangan Emosi :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(20, 660, 160, 23);

        jLabel63.setText("7. Perkembangan Sosial :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(20, 730, 160, 23);

        jLabel64.setText("8. Perkembangan Psiko-sosial :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(20, 800, 160, 23);

        jLabel65.setText("9. Riwayat Sekolah :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(20, 870, 160, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("Gambar Genogram :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(120, 1120, 100, 23);

        jLabel67.setText("10. Riwayat Keluarga");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(20, 940, 160, 23);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Ayah :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(120, 970, 120, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Ibu :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(120, 1000, 120, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Perkawinan Orang Tua :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(120, 1030, 120, 23);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Saudara - Saudara Penderita :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(120, 1060, 150, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Faktor Keturunan :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(120, 1090, 100, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 1170, 880, 1);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        FormInput.add(jSeparator19);
        jSeparator19.setBounds(0, 247, 880, 3);

        jLabel73.setText("Penampilan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 1190, 124, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        Penampilan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Penampilan.setColumns(20);
        Penampilan.setRows(5);
        Penampilan.setName("Penampilan"); // NOI18N
        Penampilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenampilanKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Penampilan);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(130, 1190, 290, 33);

        jLabel74.setText("Sikap :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 1270, 124, 23);

        scrollPane24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane24.setName("scrollPane24"); // NOI18N

        TingkahLaku.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TingkahLaku.setColumns(20);
        TingkahLaku.setRows(5);
        TingkahLaku.setName("TingkahLaku"); // NOI18N
        TingkahLaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TingkahLakuKeyPressed(evt);
            }
        });
        scrollPane24.setViewportView(TingkahLaku);

        FormInput.add(scrollPane24);
        scrollPane24.setBounds(130, 1310, 290, 33);

        jLabel75.setText("Pengendalian Impuls :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 1550, 120, 23);

        scrollPane25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane25.setName("scrollPane25"); // NOI18N

        Memori.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Memori.setColumns(20);
        Memori.setRows(5);
        Memori.setName("Memori"); // NOI18N
        Memori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MemoriKeyPressed(evt);
            }
        });
        scrollPane25.setViewportView(Memori);

        FormInput.add(scrollPane25);
        scrollPane25.setBounds(130, 1590, 290, 33);

        jLabel76.setText("Assesment Bundir :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(510, 1500, 120, 23);

        scrollPane26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane26.setName("scrollPane26"); // NOI18N

        DayaNilai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DayaNilai.setColumns(20);
        DayaNilai.setRows(5);
        DayaNilai.setName("DayaNilai"); // NOI18N
        DayaNilai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DayaNilaiKeyPressed(evt);
            }
        });
        scrollPane26.setViewportView(DayaNilai);

        FormInput.add(scrollPane26);
        scrollPane26.setBounds(640, 1190, 290, 33);

        jLabel77.setText("Tilikan :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(510, 1230, 120, 23);

        scrollPane27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane27.setName("scrollPane27"); // NOI18N

        PANSSCE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PANSSCE.setColumns(20);
        PANSSCE.setRows(5);
        PANSSCE.setName("PANSSCE"); // NOI18N
        PANSSCE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PANSSCEKeyPressed(evt);
            }
        });
        scrollPane27.setViewportView(PANSSCE);

        FormInput.add(scrollPane27);
        scrollPane27.setBounds(640, 1540, 290, 33);

        jLabel78.setText("PANSS EC :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(510, 1540, 120, 23);

        scrollPane28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane28.setName("scrollPane28"); // NOI18N

        Fantasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Fantasi.setColumns(20);
        Fantasi.setRows(5);
        Fantasi.setName("Fantasi"); // NOI18N
        Fantasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FantasiKeyPressed(evt);
            }
        });
        scrollPane28.setViewportView(Fantasi);

        FormInput.add(scrollPane28);
        scrollPane28.setBounds(640, 1270, 290, 33);

        jLabel80.setText("Fantasi (Umur < 9 tahun) :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(490, 1270, 140, 23);

        scrollPane29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane29.setName("scrollPane29"); // NOI18N

        Superego.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Superego.setColumns(20);
        Superego.setRows(5);
        Superego.setName("Superego"); // NOI18N
        Superego.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuperegoKeyPressed(evt);
            }
        });
        scrollPane29.setViewportView(Superego);

        FormInput.add(scrollPane29);
        scrollPane29.setBounds(640, 1310, 290, 33);

        jLabel81.setText("Superego :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(510, 1310, 120, 23);

        scrollPane30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane30.setName("scrollPane30"); // NOI18N

        KesadaranAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KesadaranAnak.setColumns(20);
        KesadaranAnak.setRows(5);
        KesadaranAnak.setName("KesadaranAnak"); // NOI18N
        KesadaranAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranAnakKeyPressed(evt);
            }
        });
        scrollPane30.setViewportView(KesadaranAnak);

        FormInput.add(scrollPane30);
        scrollPane30.setBounds(640, 1350, 290, 33);

        jLabel82.setText("Kesadaran anak pada persoalannya :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(440, 1350, 190, 23);

        scrollPane31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane31.setName("scrollPane31"); // NOI18N

        MotorikKasar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MotorikKasar.setColumns(20);
        MotorikKasar.setRows(5);
        MotorikKasar.setName("MotorikKasar"); // NOI18N
        MotorikKasar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MotorikKasarKeyPressed(evt);
            }
        });
        scrollPane31.setViewportView(MotorikKasar);

        FormInput.add(scrollPane31);
        scrollPane31.setBounds(640, 1420, 290, 33);

        jLabel83.setText("Koordinasi Motorik ");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(510, 1390, 120, 23);

        scrollPane32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane32.setName("scrollPane32"); // NOI18N

        MotorikHalus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MotorikHalus.setColumns(20);
        MotorikHalus.setRows(5);
        MotorikHalus.setName("MotorikHalus"); // NOI18N
        MotorikHalus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MotorikHalusKeyPressed(evt);
            }
        });
        scrollPane32.setViewportView(MotorikHalus);

        FormInput.add(scrollPane32);
        scrollPane32.setBounds(640, 1460, 290, 33);

        jLabel84.setText("b. Motorik Halus :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(510, 1460, 120, 23);

        scrollPane33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane33.setName("scrollPane33"); // NOI18N

        Bundir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Bundir.setColumns(20);
        Bundir.setRows(5);
        Bundir.setName("Bundir"); // NOI18N
        Bundir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BundirKeyPressed(evt);
            }
        });
        scrollPane33.setViewportView(Bundir);

        FormInput.add(scrollPane33);
        scrollPane33.setBounds(640, 1500, 290, 33);

        Anamnesis1.setFocusTraversalPolicyProvider(true);
        Anamnesis1.setName("Anamnesis1"); // NOI18N
        Anamnesis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Anamnesis1ActionPerformed(evt);
            }
        });
        Anamnesis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Anamnesis1KeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis1);
        Anamnesis1.setBounds(130, 270, 260, 23);

        Hubungan1.setFocusTraversalPolicyProvider(true);
        Hubungan1.setName("Hubungan1"); // NOI18N
        Hubungan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Hubungan1KeyPressed(evt);
            }
        });
        FormInput.add(Hubungan1);
        Hubungan1.setBounds(480, 270, 260, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        Kehamilan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Kehamilan.setColumns(20);
        Kehamilan.setRows(5);
        Kehamilan.setName("Kehamilan"); // NOI18N
        Kehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KehamilanKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(Kehamilan);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(200, 310, 570, 60);

        scrollPane22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane22.setName("scrollPane22"); // NOI18N

        Persalinan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Persalinan.setColumns(20);
        Persalinan.setRows(5);
        Persalinan.setName("Persalinan"); // NOI18N
        Persalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PersalinanKeyPressed(evt);
            }
        });
        scrollPane22.setViewportView(Persalinan);

        FormInput.add(scrollPane22);
        scrollPane22.setBounds(200, 380, 570, 60);

        scrollPane34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane34.setName("scrollPane34"); // NOI18N

        PolaMakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PolaMakan.setColumns(20);
        PolaMakan.setRows(5);
        PolaMakan.setName("PolaMakan"); // NOI18N
        PolaMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PolaMakanKeyPressed(evt);
            }
        });
        scrollPane34.setViewportView(PolaMakan);

        FormInput.add(scrollPane34);
        scrollPane34.setBounds(200, 450, 570, 60);

        scrollPane35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane35.setName("scrollPane35"); // NOI18N

        Toilet.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Toilet.setColumns(20);
        Toilet.setRows(5);
        Toilet.setName("Toilet"); // NOI18N
        Toilet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToiletKeyPressed(evt);
            }
        });
        scrollPane35.setViewportView(Toilet);

        FormInput.add(scrollPane35);
        scrollPane35.setBounds(200, 520, 570, 60);

        scrollPane36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane36.setName("scrollPane36"); // NOI18N

        PerkembanganFisik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PerkembanganFisik.setColumns(20);
        PerkembanganFisik.setRows(5);
        PerkembanganFisik.setName("PerkembanganFisik"); // NOI18N
        PerkembanganFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerkembanganFisikKeyPressed(evt);
            }
        });
        scrollPane36.setViewportView(PerkembanganFisik);

        FormInput.add(scrollPane36);
        scrollPane36.setBounds(200, 590, 570, 60);

        scrollPane37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane37.setName("scrollPane37"); // NOI18N

        PerkembanganEmosi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PerkembanganEmosi.setColumns(20);
        PerkembanganEmosi.setRows(5);
        PerkembanganEmosi.setName("PerkembanganEmosi"); // NOI18N
        PerkembanganEmosi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerkembanganEmosiKeyPressed(evt);
            }
        });
        scrollPane37.setViewportView(PerkembanganEmosi);

        FormInput.add(scrollPane37);
        scrollPane37.setBounds(200, 660, 570, 60);

        scrollPane38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane38.setName("scrollPane38"); // NOI18N

        PerkembanganSosial.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PerkembanganSosial.setColumns(20);
        PerkembanganSosial.setRows(5);
        PerkembanganSosial.setName("PerkembanganSosial"); // NOI18N
        PerkembanganSosial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerkembanganSosialKeyPressed(evt);
            }
        });
        scrollPane38.setViewportView(PerkembanganSosial);

        FormInput.add(scrollPane38);
        scrollPane38.setBounds(200, 730, 570, 60);

        scrollPane39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane39.setName("scrollPane39"); // NOI18N

        PerkembanganPsiko.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PerkembanganPsiko.setColumns(20);
        PerkembanganPsiko.setRows(5);
        PerkembanganPsiko.setName("PerkembanganPsiko"); // NOI18N
        PerkembanganPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerkembanganPsikoKeyPressed(evt);
            }
        });
        scrollPane39.setViewportView(PerkembanganPsiko);

        FormInput.add(scrollPane39);
        scrollPane39.setBounds(200, 800, 570, 60);

        scrollPane40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane40.setName("scrollPane40"); // NOI18N

        RiwayatSekolah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RiwayatSekolah.setColumns(20);
        RiwayatSekolah.setRows(5);
        RiwayatSekolah.setName("RiwayatSekolah"); // NOI18N
        RiwayatSekolah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatSekolahKeyPressed(evt);
            }
        });
        scrollPane40.setViewportView(RiwayatSekolah);

        FormInput.add(scrollPane40);
        scrollPane40.setBounds(200, 870, 570, 60);

        Ayah.setFocusTraversalPolicyProvider(true);
        Ayah.setName("Ayah"); // NOI18N
        Ayah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AyahKeyPressed(evt);
            }
        });
        FormInput.add(Ayah);
        Ayah.setBounds(160, 970, 260, 23);

        Ibu.setFocusTraversalPolicyProvider(true);
        Ibu.setName("Ibu"); // NOI18N
        Ibu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IbuKeyPressed(evt);
            }
        });
        FormInput.add(Ibu);
        Ibu.setBounds(150, 1000, 260, 23);

        Perkawinan.setFocusTraversalPolicyProvider(true);
        Perkawinan.setName("Perkawinan"); // NOI18N
        Perkawinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PerkawinanKeyPressed(evt);
            }
        });
        FormInput.add(Perkawinan);
        Perkawinan.setBounds(240, 1030, 260, 23);

        Saudara.setFocusTraversalPolicyProvider(true);
        Saudara.setName("Saudara"); // NOI18N
        Saudara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaudaraActionPerformed(evt);
            }
        });
        Saudara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaudaraKeyPressed(evt);
            }
        });
        FormInput.add(Saudara);
        Saudara.setBounds(280, 1060, 260, 23);

        FaktorKeturunan.setFocusTraversalPolicyProvider(true);
        FaktorKeturunan.setName("FaktorKeturunan"); // NOI18N
        FaktorKeturunan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FaktorKeturunanActionPerformed(evt);
            }
        });
        FaktorKeturunan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FaktorKeturunanKeyPressed(evt);
            }
        });
        FormInput.add(FaktorKeturunan);
        FaktorKeturunan.setBounds(220, 1090, 260, 23);

        scrollPane41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane41.setName("scrollPane41"); // NOI18N

        Genogram.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Genogram.setColumns(20);
        Genogram.setRows(5);
        Genogram.setName("Genogram"); // NOI18N
        Genogram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GenogramKeyPressed(evt);
            }
        });
        scrollPane41.setViewportView(Genogram);

        FormInput.add(scrollPane41);
        scrollPane41.setBounds(230, 1120, 550, 40);

        jLabel85.setText("a. Motorik Kasar :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(510, 1420, 120, 23);

        jLabel55.setText("Thoraks :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 1820, 127, 23);

        jLabel86.setText("AXIS I :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 2000, 127, 23);

        Axis1.setFocusTraversalPolicyProvider(true);
        Axis1.setName("Axis1"); // NOI18N
        Axis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis1KeyPressed(evt);
            }
        });
        FormInput.add(Axis1);
        Axis1.setBounds(140, 2000, 76, 23);

        Axis2.setFocusTraversalPolicyProvider(true);
        Axis2.setName("Axis2"); // NOI18N
        Axis2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Axis2ActionPerformed(evt);
            }
        });
        Axis2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis2KeyPressed(evt);
            }
        });
        FormInput.add(Axis2);
        Axis2.setBounds(140, 2030, 76, 23);

        jLabel87.setText("Prognosis :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 2490, 150, 23);

        Axis3.setFocusTraversalPolicyProvider(true);
        Axis3.setName("Axis3"); // NOI18N
        Axis3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis3KeyPressed(evt);
            }
        });
        FormInput.add(Axis3);
        Axis3.setBounds(390, 2000, 76, 23);

        jLabel88.setText("AXIS IV :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(250, 2030, 127, 23);

        Axis4.setFocusTraversalPolicyProvider(true);
        Axis4.setName("Axis4"); // NOI18N
        Axis4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis4KeyPressed(evt);
            }
        });
        FormInput.add(Axis4);
        Axis4.setBounds(390, 2030, 76, 23);

        Axis5.setFocusTraversalPolicyProvider(true);
        Axis5.setName("Axis5"); // NOI18N
        Axis5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Axis5ActionPerformed(evt);
            }
        });
        Axis5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis5KeyPressed(evt);
            }
        });
        FormInput.add(Axis5);
        Axis5.setBounds(660, 2000, 76, 23);

        jLabel89.setText("AXIS V :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(520, 2000, 127, 23);

        jLabel90.setText("AXIS II :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 2030, 127, 23);

        scrollPane42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane42.setName("scrollPane42"); // NOI18N

        Permasalahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Permasalahan.setColumns(20);
        Permasalahan.setRows(5);
        Permasalahan.setName("Permasalahan"); // NOI18N
        Permasalahan.setPreferredSize(new java.awt.Dimension(102, 52));
        Permasalahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PermasalahanKeyPressed(evt);
            }
        });
        scrollPane42.setViewportView(Permasalahan);

        FormInput.add(scrollPane42);
        scrollPane42.setBounds(140, 2100, 660, 63);

        jLabel92.setText("Rencana Tindakan ");
        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 2180, 160, 23);

        jLabel93.setText("Farmakologis :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 2210, 150, 23);

        jLabel95.setText("Non Farmakologis :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 2280, 150, 23);

        jLabel96.setText("Lama Perawatan :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 2350, 150, 23);

        jLabel97.setText("Target Terukur :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 2420, 150, 23);

        scrollPane43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane43.setName("scrollPane43"); // NOI18N

        Farmakulon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Farmakulon.setColumns(20);
        Farmakulon.setRows(5);
        Farmakulon.setName("Farmakulon"); // NOI18N
        Farmakulon.setPreferredSize(new java.awt.Dimension(102, 52));
        Farmakulon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FarmakulonKeyPressed(evt);
            }
        });
        scrollPane43.setViewportView(Farmakulon);

        FormInput.add(scrollPane43);
        scrollPane43.setBounds(160, 2210, 640, 63);

        scrollPane44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane44.setName("scrollPane44"); // NOI18N

        NonFarmakulon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        NonFarmakulon.setColumns(20);
        NonFarmakulon.setRows(5);
        NonFarmakulon.setName("NonFarmakulon"); // NOI18N
        NonFarmakulon.setPreferredSize(new java.awt.Dimension(102, 52));
        NonFarmakulon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NonFarmakulonKeyPressed(evt);
            }
        });
        scrollPane44.setViewportView(NonFarmakulon);

        FormInput.add(scrollPane44);
        scrollPane44.setBounds(160, 2280, 640, 63);

        scrollPane45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane45.setName("scrollPane45"); // NOI18N

        LamaPerawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LamaPerawatan.setColumns(20);
        LamaPerawatan.setRows(5);
        LamaPerawatan.setName("LamaPerawatan"); // NOI18N
        LamaPerawatan.setPreferredSize(new java.awt.Dimension(102, 52));
        LamaPerawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaPerawatanKeyPressed(evt);
            }
        });
        scrollPane45.setViewportView(LamaPerawatan);

        FormInput.add(scrollPane45);
        scrollPane45.setBounds(160, 2350, 640, 63);

        scrollPane46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane46.setName("scrollPane46"); // NOI18N

        targetTindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        targetTindakan.setColumns(20);
        targetTindakan.setRows(5);
        targetTindakan.setName("targetTindakan"); // NOI18N
        targetTindakan.setPreferredSize(new java.awt.Dimension(102, 52));
        targetTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                targetTindakanKeyPressed(evt);
            }
        });
        scrollPane46.setViewportView(targetTindakan);

        FormInput.add(scrollPane46);
        scrollPane46.setBounds(160, 2420, 640, 63);

        scrollPane47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane47.setName("scrollPane47"); // NOI18N

        Program.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Program.setColumns(20);
        Program.setRows(5);
        Program.setName("Program"); // NOI18N
        Program.setPreferredSize(new java.awt.Dimension(102, 52));
        Program.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProgramKeyPressed(evt);
            }
        });
        scrollPane47.setViewportView(Program);

        FormInput.add(scrollPane47);
        scrollPane47.setBounds(160, 2490, 640, 63);

        jLabel98.setText("a. Permasalahan :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 2100, 127, 23);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(0, 2590, 880, 1);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("VIII. KONSUL & RUJUK");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 2590, 190, 23);

        scrollPane49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane49.setName("scrollPane49"); // NOI18N

        KonsulRujuk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KonsulRujuk.setColumns(20);
        KonsulRujuk.setRows(5);
        KonsulRujuk.setName("KonsulRujuk"); // NOI18N
        KonsulRujuk.setPreferredSize(new java.awt.Dimension(102, 52));
        KonsulRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KonsulRujukKeyPressed(evt);
            }
        });
        scrollPane49.setViewportView(KonsulRujuk);

        FormInput.add(scrollPane49);
        scrollPane49.setBounds(40, 2620, 760, 63);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-08-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-08-2023" }));
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(RPS.getText().trim().equals("")){
            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
        }else{
            if(Sequel.menyimpantf("penilaian_medis_ranap_psikiatrik_anak","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",81,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),KeluhanUtama.getText(),RPS.getText(),RPD.getText(),RPK.getText(),RPO.getText(),Alergi.getText(),
                    Anamnesis1.getText(),Hubungan1.getText(),Kehamilan.getText(),Persalinan.getText(),PolaMakan.getText(),Toilet.getText(),PerkembanganFisik.getText(),PerkembanganEmosi.getText(),PerkembanganSosial.getText(),PerkembanganPsiko.getText(),RiwayatSekolah.getText(),Ayah.getText(), Ibu.getText(), Perkawinan.getText(),Saudara.getText(), FaktorKeturunan.getText(), Genogram.getText(),
                    Penampilan.getText(),Ksadaran.getText(),Sikap.getText(),TingkahLaku.getText(),Pembicaraan.getText(),Psikomotor.getText(),Mood.getText(),Prosespikir.getText(),Gangguanpersepsi.getText(),Pengendalianimpuls.getText(),Memori.getText(),DayaNilai.getText(),Tilikan.getText(),Fantasi.getText(),Superego.getText(),KesadaranAnak.getText(),MotorikKasar.getText(),MotorikHalus.getText(),Bundir.getText(),PANSSCE.getText(),Fungsikognitif.getText(),
                    Keadaan.getSelectedItem().toString(),Kesadaran.getSelectedItem().toString(),GCS.getText(),TB.getText(),BB.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),SPO.getText(),Kepala.getSelectedItem().toString(),Gigi.getSelectedItem().toString(),THT.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Genital.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),Kulit.getSelectedItem().toString(),KetFisik.getText(),
                    Penunjang.getText(),
                    Axis1.getText(),Axis2.getText(),Axis3.getText(),Axis4.getText(),Axis5.getText(),
                    Permasalahan.getText(),Farmakulon.getText(),NonFarmakulon.getText(),LamaPerawatan.getText(),targetTindakan.getText(),Program.getText(),KonsulRujuk.getText()
                })==true){
                    emptTeks();
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,KetFisik,BtnBatal);
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
        }else if(KeluhanUtama.getText().trim().equals("")){
            Valid.textKosong(KeluhanUtama,"Keluhan Utama");
        }else if(RPS.getText().trim().equals("")){
            Valid.textKosong(RPS,"Riwayat Penyakit Sekarang");
        }else if(RPK.getText().trim().equals("")){
            Valid.textKosong(RPK,"Riwayat Penyakit Keluarga");
        }else if(RPD.getText().trim().equals("")){
            Valid.textKosong(RPD,"Riwayat Penyakit Dahulu");
        }else if(RPO.getText().trim().equals("")){
            Valid.textKosong(RPO,"Riwayat Pengunaan obat");
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
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatrik_anak.tanggal,"+
                            "penilaian_medis_ranap_psikiatrik_anak.kd_dokter,penilaian_medis_ranap_psikiatrik_anak.anamnesis,penilaian_medis_ranap_psikiatrik_anak.hubungan,penilaian_medis_ranap_psikiatrik_anak.keluhan_utama,penilaian_medis_ranap_psikiatrik_anak.rps,penilaian_medis_ranap_psikiatrik_anak.rpd,penilaian_medis_ranap_psikiatrik_anak.rpk,penilaian_medis_ranap_psikiatrik_anak.rpo,penilaian_medis_ranap_psikiatrik_anak.alergi,"+
                            "penilaian_medis_ranap_psikiatrik_anak.anamnesis_dengan,penilaian_medis_ranap_psikiatrik_anak.hubungan_p_a,penilaian_medis_ranap_psikiatrik_anak.kehamilan,penilaian_medis_ranap_psikiatrik_anak.persalinan,penilaian_medis_ranap_psikiatrik_anak.pola_makan,penilaian_medis_ranap_psikiatrik_anak.toilet,penilaian_medis_ranap_psikiatrik_anak.pk_fisik,penilaian_medis_ranap_psikiatrik_anak.pk_emosi,penilaian_medis_ranap_psikiatrik_anak.pk_sosial,penilaian_medis_ranap_psikiatrik_anak.pk_psiko,penilaian_medis_ranap_psikiatrik_anak.r_sekolah,penilaian_medis_ranap_psikiatrik_anak.r_ayah,penilaian_medis_ranap_psikiatrik_anak.r_ibu,penilaian_medis_ranap_psikiatrik_anak.r_perkawinan,penilaian_medis_ranap_psikiatrik_anak.r_saudara,penilaian_medis_ranap_psikiatrik_anak.faktor_keturunan,penilaian_medis_ranap_psikiatrik_anak.genogram,"+
                            "penilaian_medis_ranap_psikiatrik_anak.penampilan,penilaian_medis_ranap_psikiatrik_anak.stts_kesadaran,penilaian_medis_ranap_psikiatrik_anak.sikap,penilaian_medis_ranap_psikiatrik_anak.tingkah_laku,penilaian_medis_ranap_psikiatrik_anak.pembicaraan,penilaian_medis_ranap_psikiatrik_anak.psikomotor,penilaian_medis_ranap_psikiatrik_anak.mood,penilaian_medis_ranap_psikiatrik_anak.proses_pikir,penilaian_medis_ranap_psikiatrik_anak.gangguan_persepsi,penilaian_medis_ranap_psikiatrik_anak.pengendalian_impuls,penilaian_medis_ranap_psikiatrik_anak.memori,penilaian_medis_ranap_psikiatrik_anak.daya_nilai,penilaian_medis_ranap_psikiatrik_anak.tilikan,penilaian_medis_ranap_psikiatrik_anak.fantasi,penilaian_medis_ranap_psikiatrik_anak.superego,penilaian_medis_ranap_psikiatrik_anak.motorik_kasar,penilaian_medis_ranap_psikiatrik_anak.motorik_halus,penilaian_medis_ranap_psikiatrik_anak.ass_bundir,penilaian_medis_ranap_psikiatrik_anak.panss_ec,penilaian_medis_ranap_psikiatrik_anak.fungsi_kognitif,"+
                            "penilaian_medis_ranap_psikiatrik_anak.keadaan,penilaian_medis_ranap_psikiatrik_anak.kesadaran_anak,penilaian_medis_ranap_psikiatrik_anak.gcs,penilaian_medis_ranap_psikiatrik_anak.tb,penilaian_medis_ranap_psikiatrik_anak.bb,penilaian_medis_ranap_psikiatrik_anak.td,penilaian_medis_ranap_psikiatrik_anak.nadi,penilaian_medis_ranap_psikiatrik_anak.rr,penilaian_medis_ranap_psikiatrik_anak.suhu,penilaian_medis_ranap_psikiatrik_anak.spo,penilaian_medis_ranap_psikiatrik_anak.kepala,penilaian_medis_ranap_psikiatrik_anak.gigi,penilaian_medis_ranap_psikiatrik_anak.tht,penilaian_medis_ranap_psikiatrik_anak.thoraks,penilaian_medis_ranap_psikiatrik_anak.abdomen,penilaian_medis_ranap_psikiatrik_anak.ekstremitas,penilaian_medis_ranap_psikiatrik_anak.genital,penilaian_medis_ranap_psikiatrik_anak.kulit,penilaian_medis_ranap_psikiatrik_anak.ket_fisik,"+
                            "penilaian_medis_ranap_psikiatrik_anak.penunjang,"+
                            "penilaian_medis_ranap_psikiatrik_anak.axis1,penilaian_medis_ranap_psikiatrik_anak.axis2,penilaian_medis_ranap_psikiatrik_anak.axis3,penilaian_medis_ranap_psikiatrik_anak.axis4,penilaian_medis_ranap_psikiatrik_anak.axis5,"+    
                            "penilaian_medis_ranap_psikiatrik_anak.permasalahan,penilaian_medis_ranap_psikiatrik_anak.farmakulon,penilaian_medis_ranap_psikiatrik_anak.non_farmakulon,penilaian_medis_ranap_psikiatrik_anak.lama_perawatan,penilaian_medis_ranap_psikiatrik_anak.target,penilaian_medis_ranap_psikiatrik_anak.program,penilaian_medis_ranap_psikiatrik_anak.konsul_rujuk,"+       
                            "dokter.nm_dokter "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_medis_ranap_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatrik_anak.no_rawat "+
                            "inner join dokter on penilaian_medis_ranap_psikiatrik_anak.kd_dokter=dokter.kd_dokter where "+
                            "penilaian_medis_ranap_psikiatrik_anak.tanggal between ? and ? order by penilaian_medis_ranap_psikiatrik_anak.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatrik_anak.tanggal,"+
                            "penilaian_medis_ranap_psikiatrik_anak.kd_dokter,penilaian_medis_ranap_psikiatrik_anak.anamnesis,penilaian_medis_ranap_psikiatrik_anak.hubungan,penilaian_medis_ranap_psikiatrik_anak.keluhan_utama,penilaian_medis_ranap_psikiatrik_anak.rps,penilaian_medis_ranap_psikiatrik_anak.rpd,penilaian_medis_ranap_psikiatrik_anak.rpk,penilaian_medis_ranap_psikiatrik_anak.rpo,penilaian_medis_ranap_psikiatrik_anak.alergi,"+
                            "penilaian_medis_ranap_psikiatrik_anak.anamnesis_dengan,penilaian_medis_ranap_psikiatrik_anak.hubungan_p_a,penilaian_medis_ranap_psikiatrik_anak.kehamilan,penilaian_medis_ranap_psikiatrik_anak.persalinan,penilaian_medis_ranap_psikiatrik_anak.pola_makan,penilaian_medis_ranap_psikiatrik_anak.toilet,penilaian_medis_ranap_psikiatrik_anak.pk_fisik,penilaian_medis_ranap_psikiatrik_anak.pk_emosi,penilaian_medis_ranap_psikiatrik_anak.pk_sosial,penilaian_medis_ranap_psikiatrik_anak.pk_psiko,penilaian_medis_ranap_psikiatrik_anak.r_sekolah,penilaian_medis_ranap_psikiatrik_anak.r_ayah,penilaian_medis_ranap_psikiatrik_anak.r_ibu,penilaian_medis_ranap_psikiatrik_anak.r_perkawinan,penilaian_medis_ranap_psikiatrik_anak.r_saudara,penilaian_medis_ranap_psikiatrik_anak.faktor_keturunan,penilaian_medis_ranap_psikiatrik_anak.genogram,"+
                            "penilaian_medis_ranap_psikiatrik_anak.penampilan,penilaian_medis_ranap_psikiatrik_anak.stts_kesadaran,penilaian_medis_ranap_psikiatrik_anak.sikap,penilaian_medis_ranap_psikiatrik_anak.tingkah_laku,penilaian_medis_ranap_psikiatrik_anak.pembicaraan,penilaian_medis_ranap_psikiatrik_anak.psikomotor,penilaian_medis_ranap_psikiatrik_anak.mood,penilaian_medis_ranap_psikiatrik_anak.proses_pikir,penilaian_medis_ranap_psikiatrik_anak.gangguan_persepsi,penilaian_medis_ranap_psikiatrik_anak.pengendalian_impuls,penilaian_medis_ranap_psikiatrik_anak.memori,penilaian_medis_ranap_psikiatrik_anak.daya_nilai,penilaian_medis_ranap_psikiatrik_anak.tilikan,penilaian_medis_ranap_psikiatrik_anak.fantasi,penilaian_medis_ranap_psikiatrik_anak.superego,penilaian_medis_ranap_psikiatrik_anak.motorik_kasar,penilaian_medis_ranap_psikiatrik_anak.motorik_halus,penilaian_medis_ranap_psikiatrik_anak.ass_bundir,penilaian_medis_ranap_psikiatrik_anak.panss_ec,penilaian_medis_ranap_psikiatrik_anak.fungsi_kognitif,"+
                            "penilaian_medis_ranap_psikiatrik_anak.keadaan,penilaian_medis_ranap_psikiatrik_anak.kesadaran_anak,penilaian_medis_ranap_psikiatrik_anak.gcs,penilaian_medis_ranap_psikiatrik_anak.tb,penilaian_medis_ranap_psikiatrik_anak.bb,penilaian_medis_ranap_psikiatrik_anak.td,penilaian_medis_ranap_psikiatrik_anak.nadi,penilaian_medis_ranap_psikiatrik_anak.rr,penilaian_medis_ranap_psikiatrik_anak.suhu,penilaian_medis_ranap_psikiatrik_anak.spo,penilaian_medis_ranap_psikiatrik_anak.kepala,penilaian_medis_ranap_psikiatrik_anak.gigi,penilaian_medis_ranap_psikiatrik_anak.tht,penilaian_medis_ranap_psikiatrik_anak.thoraks,penilaian_medis_ranap_psikiatrik_anak.abdomen,penilaian_medis_ranap_psikiatrik_anak.ekstremitas,penilaian_medis_ranap_psikiatrik_anak.genital,penilaian_medis_ranap_psikiatrik_anak.kulit,penilaian_medis_ranap_psikiatrik_anak.ket_fisik,"+
                            "penilaian_medis_ranap_psikiatrik_anak.penunjang,"+
                            "penilaian_medis_ranap_psikiatrik_anak.axis1,penilaian_medis_ranap_psikiatrik_anak.axis2,penilaian_medis_ranap_psikiatrik_anak.axis3,penilaian_medis_ranap_psikiatrik_anak.axis4,penilaian_medis_ranap_psikiatrik_anak.axis5,"+    
                            "penilaian_medis_ranap_psikiatrik_anak.permasalahan,penilaian_medis_ranap_psikiatrik_anak.farmakulon,penilaian_medis_ranap_psikiatrik_anak.non_farmakulon,penilaian_medis_ranap_psikiatrik_anak.lama_perawatan,penilaian_medis_ranap_psikiatrik_anak.target,penilaian_medis_ranap_psikiatrik_anak.program,penilaian_medis_ranap_psikiatrik_anak.konsul_rujuk,"+ 
                            "dokter.nm_dokter "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_medis_ranap_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatrik_anak.no_rawat "+
                            "inner join dokter on penilaian_medis_ranap_psikiatrik_anak.kd_dokter=dokter.kd_dokter where "+
                            "penilaian_medis_ranap_psikiatrik_anak.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                            "penilaian_medis_ranap_psikiatrik_anak.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ranap_psikiatrik_anak.tanggal");
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
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anamnesis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Hubungan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keluhan Utama</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Sekarang</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Dahulu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Keluarga</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Napza</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Alergi</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anemnesis dengan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Hubungan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kehamilan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Persalinan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pola Makan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Toilet</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Fisik</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Emosi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Sosial</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Psiko</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Ayah</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Ibu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Perkawinan Ortu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Saudara</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Faktor Keturunan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Genogram</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penampilan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Status Kesadaran</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sikap</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tingkah Laku</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pembucaraan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikomotor</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mood</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Proses Pikir</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Persepsi</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pengendalian Impuls</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Memori</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Daya Nilai</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tilikan</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fantasi</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Super Ego</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran Anak</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Motorik Kasar</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Motorik Halus</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Assesment Bundir</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>PANSS EC</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fungsi Kognitif</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keadaan Umum</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>GCS</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>TB(cm)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>BB(Kg)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>TD(mmHg)</td>"+
                                        
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nadi(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>RR(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Suhu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>SpO2</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kepala</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Gigi & Mulut</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>THT</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Thoraks</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Abdomen</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Genital & Anus</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Ekstremitas</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kulit</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keterangan Pemeriksaan Fisik</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Penunjang</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis I</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis II</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis III</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis IV</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis V</td>"+
                                        
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Permasalahan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Farmakologis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Non Farmakologis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Lama Perawatan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Target Terukur</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Prognosis</td>"+
                                            
                                            
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Konsul/Rujuk</td>"+
                                        
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
                                           "<td valign='top'>"+rs.getString("anamnesis")+"</td>"+
                                           "<td valign='top'>"+rs.getString("hubungan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rps")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rpd")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rpk")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rpo")+"</td>"+
                                           "<td valign='top'>"+rs.getString("alergi")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("anamnesis_dengan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("hubungan_p_a")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kehamilan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("persalinan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pola_makan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("toilet")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_fisik")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_emosi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_sosial")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_psiko")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_sekolah")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_ayah")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_ibu")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_perkawinan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_saudara")+"</td>"+
                                           "<td valign='top'>"+rs.getString("faktor_keturunan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("genogram")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("penampilan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("stts_kesadaran")+"</td>"+
                                           "<td valign='top'>"+rs.getString("sikap")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tingkah_laku")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pembicaraan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikomotor")+"</td>"+
                                           "<td valign='top'>"+rs.getString("mood")+"</td>"+
                                           "<td valign='top'>"+rs.getString("proses_pikir")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gangguan_persepsi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pengendalian_impuls")+"</td>"+
                                           "<td valign='top'>"+rs.getString("memori")+"</td>"+
                                           "<td valign='top'>"+rs.getString("daya_nilai")+"</td>"+        
                                           "<td valign='top'>"+rs.getString("fantasi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("superego")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kesadaran_anak")+"</td>"+
                                           "<td valign='top'>"+rs.getString("motorik_kasar")+"</td>"+
                                           "<td valign='top'>"+rs.getString("motorik_halus")+"</td>"+
                                           "<td valign='top'>"+rs.getString("ass_bundir")+"</td>"+
                                           "<td valign='top'>"+rs.getString("panss_ec")+"</td>"+
                                           "<td valign='top'>"+rs.getString("fungsi_kognitif")+"</td>"+
                                           
                                           "<td valign='top'>"+rs.getString("keadaan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kesadaran_anak")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gcs")+"</td>"+        
                                           "<td valign='top'>"+rs.getString("tb")+"</td>"+
                                           "<td valign='top'>"+rs.getString("bb")+"</td>"+
                                           "<td valign='top'>"+rs.getString("td")+"</td>"+
                                           "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rr")+"</td>"+
                                           "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                                           "<td valign='top'>"+rs.getString("spo")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kepala")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gigi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tht")+"</td>"+
                                           "<td valign='top'>"+rs.getString("thoraks")+"</td>"+
                                           "<td valign='top'>"+rs.getString("abdomen")+"</td>"+
                                           "<td valign='top'>"+rs.getString("genital")+"</td>"+
                                           "<td valign='top'>"+rs.getString("ekstremitas")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kulit")+"</td>"+
                                           "<td valign='top'>"+rs.getString("ket_fisik")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("penunjang")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("axis1")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis2")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis3")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis4")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis5")+"</td>"+ 
                                                   
                                           "<td valign='top'>"+rs.getString("permasalahan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("farmakulon")+"</td>"+
                                           "<td valign='top'>"+rs.getString("non_farmakulon")+"</td>"+
                                           "<td valign='top'>"+rs.getString("lama_perawatan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("target")+"</td>"+        
                                           "<td valign='top'>"+rs.getString("program")+"</td>"+
                                           
                                           "<td valign='top'>"+rs.getString("konsul_rujuk")+"</td>"+
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
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anamnesis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Hubungan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keluhan Utama</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Sekarang</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Dahulu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Penyakit Keluarga</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Napza</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Alergi</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Anemnesis dengan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Hubungan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kehamilan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Persalinan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pola Makan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Toilet</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Fisik</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Emosi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Sosial</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Perkembangan Psiko</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Ayah</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Ibu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Perkawinan Ortu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Saudara</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Faktor Keturunan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Genogram</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penampilan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Status Kesadaran</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sikap</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tingkah Laku</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pembucaraan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikomotor</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mood</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Proses Pikir</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Persepsi</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pengendalian Impuls</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Memori</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Daya Nilai</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tilikan</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fantasi</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Super Ego</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran Anak</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Motorik Kasar</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Motorik Halus</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Assesment Bundir</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>PANSS EC</td>"+
                                            "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fungsi Kognitif</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keadaan Umum</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>GCS</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>TB(cm)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>BB(Kg)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>TD(mmHg)</td>"+
                                        
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nadi(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>RR(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Suhu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>SpO2</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kepala</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Gigi & Mulut</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>THT</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Thoraks</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Abdomen</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Genital & Anus</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Ekstremitas</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kulit</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keterangan Pemeriksaan Fisik</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Penunjang</td>"+
                                            
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis I</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis II</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis III</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis IV</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis V</td>"+
                                        
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Permasalahan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Farmakologis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Non Farmakologis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Lama Perawatan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Target Terukur</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Prognosis</td>"+
                                            
                                            
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Konsul/Rujuk</td>"+
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
                                           "<td valign='top'>"+rs.getString("anamnesis")+"</td>"+
                                           "<td valign='top'>"+rs.getString("hubungan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rps")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rpd")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rpk")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rpo")+"</td>"+
                                           "<td valign='top'>"+rs.getString("alergi")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("anamnesis_dengan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("hubungan_p_a")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kehamilan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("persalinan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pola_makan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("toilet")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_fisik")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_emosi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_sosial")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pk_psiko")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_sekolah")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_ayah")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_ibu")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_perkawinan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("r_saudara")+"</td>"+
                                           "<td valign='top'>"+rs.getString("faktor_keturunan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("genogram")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("penampilan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("stts_kesadaran")+"</td>"+
                                           "<td valign='top'>"+rs.getString("sikap")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tingkah_laku")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pembicaraan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikomotor")+"</td>"+
                                           "<td valign='top'>"+rs.getString("mood")+"</td>"+
                                           "<td valign='top'>"+rs.getString("proses_pikir")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gangguan_persepsi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pengendalian_impuls")+"</td>"+
                                           "<td valign='top'>"+rs.getString("memori")+"</td>"+
                                           "<td valign='top'>"+rs.getString("daya_nilai")+"</td>"+        
                                           "<td valign='top'>"+rs.getString("fantasi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("superego")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kesadaran_anak")+"</td>"+
                                           "<td valign='top'>"+rs.getString("motorik_kasar")+"</td>"+
                                           "<td valign='top'>"+rs.getString("motorik_halus")+"</td>"+
                                           "<td valign='top'>"+rs.getString("ass_bundir")+"</td>"+
                                           "<td valign='top'>"+rs.getString("panss_ec")+"</td>"+
                                           "<td valign='top'>"+rs.getString("fungsi_kognitif")+"</td>"+
                                           
                                           "<td valign='top'>"+rs.getString("keadaan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kesadaran_anak")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gcs")+"</td>"+        
                                           "<td valign='top'>"+rs.getString("tb")+"</td>"+
                                           "<td valign='top'>"+rs.getString("bb")+"</td>"+
                                           "<td valign='top'>"+rs.getString("td")+"</td>"+
                                           "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rr")+"</td>"+
                                           "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                                           "<td valign='top'>"+rs.getString("spo")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kepala")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gigi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tht")+"</td>"+
                                           "<td valign='top'>"+rs.getString("thoraks")+"</td>"+
                                           "<td valign='top'>"+rs.getString("abdomen")+"</td>"+
                                           "<td valign='top'>"+rs.getString("genital")+"</td>"+
                                           "<td valign='top'>"+rs.getString("ekstremitas")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kulit")+"</td>"+
                                           "<td valign='top'>"+rs.getString("ket_fisik")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("penunjang")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("axis1")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis2")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis3")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis4")+"</td>"+
                                           "<td valign='top'>"+rs.getString("axis5")+"</td>"+ 
                                                   
                                           "<td valign='top'>"+rs.getString("permasalahan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("farmakulon")+"</td>"+
                                           "<td valign='top'>"+rs.getString("non_farmakulon")+"</td>"+
                                           "<td valign='top'>"+rs.getString("lama_perawatan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("target")+"</td>"+        
                                           "<td valign='top'>"+rs.getString("program")+"</td>"+
                                                   
                                           "<td valign='top'>"+rs.getString("konsul_rujuk")+"</td>"+
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
            finger=Sequel.cariIsi("select sha1(pegawai.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianAwalMedisRalanPsikiatrik.jasper","report","::[ Laporan Penilaian Awal Medis Rawat Jalan Psikiatrik ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatrik_anak.tanggal,"+
                "penilaian_medis_ranap_psikiatrik_anak.kd_dokter,penilaian_medis_ranap_psikiatrik_anak.anamnesis,penilaian_medis_ranap_psikiatrik_anak.hubungan,penilaian_medis_ranap_psikiatrik_anak.keluhan_utama,penilaian_medis_ranap_psikiatrik_anak.rps,penilaian_medis_ranap_psikiatrik_anak.rpk,penilaian_medis_ranap_psikiatrik_anak.rpd,penilaian_medis_ranap_psikiatrik_anak.rpo,penilaian_medis_ranap_psikiatrik_anak.penampilan,"+
                "penilaian_medis_ranap_psikiatrik_anak.pembicaraan,penilaian_medis_ranap_psikiatrik_anak.psikomotor,penilaian_medis_ranap_psikiatrik_anak.sikap,penilaian_medis_ranap_psikiatrik_anak.mood,"+
                "penilaian_medis_ranap_psikiatrik_anak.fungsi_kognitif,penilaian_medis_ranap_psikiatrik_anak.gangguan_persepsi,penilaian_medis_ranap_psikiatrik_anak.proses_pikir,penilaian_medis_ranap_psikiatrik_anak.pengendalian_impuls,penilaian_medis_ranap_psikiatrik_anak.tilikan,penilaian_medis_ranap_psikiatrik_anak.rta,"+
                "penilaian_medis_ranap_psikiatrik_anak.keadaan,penilaian_medis_ranap_psikiatrik_anak.gcs,penilaian_medis_ranap_psikiatrik_anak.kesadaran,penilaian_medis_ranap_psikiatrik_anak.td,penilaian_medis_ranap_psikiatrik_anak.nadi,penilaian_medis_ranap_psikiatrik_anak.rr,penilaian_medis_ranap_psikiatrik_anak.suhu,penilaian_medis_ranap_psikiatrik_anak.spo,penilaian_medis_ranap_psikiatrik_anak.bb,penilaian_medis_ranap_psikiatrik_anak.tb,"+
                "penilaian_medis_ranap_psikiatrik_anak.kepala,penilaian_medis_ranap_psikiatrik_anak.gigi,penilaian_medis_ranap_psikiatrik_anak.tht,penilaian_medis_ranap_psikiatrik_anak.thoraks,penilaian_medis_ranap_psikiatrik_anak.abdomen,penilaian_medis_ranap_psikiatrik_anak.ekstremitas,penilaian_medis_ranap_psikiatrik_anak.genital,penilaian_medis_ranap_psikiatrik_anak.kulit,"+
                "penilaian_medis_ranap_psikiatrik_anak.ket_fisik,penilaian_medis_ranap_psikiatrik_anak.alergi,penilaian_medis_ranap_psikiatrik_anak.penunjang,penilaian_medis_ranap_psikiatrik_anak.diagnosis,penilaian_medis_ranap_psikiatrik_anak.tata,penilaian_medis_ranap_psikiatrik_anak.konsulrujuk,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ranap_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatrik_anak.no_rawat "+
                "inner join dokter on penilaian_medis_ranap_psikiatrik_anak.kd_dokter=dokter.kd_dokter where penilaian_medis_ranap_psikiatrik_anak.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianMedisActionPerformed

    private void ThoraksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThoraksKeyPressed
        Valid.pindah(evt,THT,Abdomen);
    }//GEN-LAST:event_ThoraksKeyPressed

    private void KulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KulitKeyPressed
        Valid.pindah(evt,Ekstremitas,KetFisik);
    }//GEN-LAST:event_KulitKeyPressed

    private void THTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THTKeyPressed
        Valid.pindah(evt,Gigi,Thoraks);
    }//GEN-LAST:event_THTKeyPressed

    private void EkstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstremitasKeyPressed
        Valid.pindah(evt,Genital,Kulit);
    }//GEN-LAST:event_EkstremitasKeyPressed

    private void GenitalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GenitalKeyPressed
        Valid.pindah(evt,Abdomen,Ekstremitas);
    }//GEN-LAST:event_GenitalKeyPressed

    private void GigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GigiKeyPressed
        Valid.pindah(evt,Kepala,THT);
    }//GEN-LAST:event_GigiKeyPressed

    private void KepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KepalaKeyPressed
        Valid.pindah(evt,SPO,Gigi);
    }//GEN-LAST:event_KepalaKeyPressed

    private void AbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbdomenKeyPressed
        Valid.pindah(evt,Thoraks,Genital);
    }//GEN-LAST:event_AbdomenKeyPressed

    private void KetFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetFisikKeyPressed
        Valid.pindah2(evt,Kulit,Penunjang);
    }//GEN-LAST:event_KetFisikKeyPressed

    private void SPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SPOKeyPressed
        Valid.pindah(evt,Suhu,Kepala);
    }//GEN-LAST:event_SPOKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt,Nadi,Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,BB,Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt,RR,SPO);
    }//GEN-LAST:event_SuhuKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void TilikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TilikanKeyPressed
        Valid.pindah2(evt,Sikap,Mood);
    }//GEN-LAST:event_TilikanKeyPressed

    private void PengendalianimpulsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PengendalianimpulsKeyPressed
        Valid.pindah2(evt,Psikomotor,Sikap);
    }//GEN-LAST:event_PengendalianimpulsKeyPressed

    private void ProsespikirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsespikirKeyPressed
        Valid.pindah2(evt,Pembicaraan,Psikomotor);
    }//GEN-LAST:event_ProsespikirKeyPressed

    private void GangguanpersepsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GangguanpersepsiKeyPressed
        Valid.pindah2(evt,Ksadaran,Pembicaraan);
    }//GEN-LAST:event_GangguanpersepsiKeyPressed

    private void FungsikognitifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FungsikognitifKeyPressed
//        Valid.pindah2(evt,RTA,Keadaan);
    }//GEN-LAST:event_FungsikognitifKeyPressed

    private void SikapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SikapKeyPressed
        Valid.pindah2(evt,Pengendalianimpuls,Tilikan);
    }//GEN-LAST:event_SikapKeyPressed

    private void MoodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoodKeyPressed
//        Valid.pindah2(evt,Tilikan,RTA);
    }//GEN-LAST:event_MoodKeyPressed

    private void PsikomotorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PsikomotorKeyPressed
        Valid.pindah2(evt,Prosespikir,Pengendalianimpuls);
    }//GEN-LAST:event_PsikomotorKeyPressed

    private void PembicaraanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PembicaraanKeyPressed
        Valid.pindah2(evt,Gangguanpersepsi,Prosespikir);
    }//GEN-LAST:event_PembicaraanKeyPressed

    private void KsadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KsadaranKeyPressed
        Valid.pindah2(evt,Alergi,Gangguanpersepsi);
    }//GEN-LAST:event_KsadaranKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
//        Valid.pindah(evt,Konsul,Anamnesis);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void PenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenunjangKeyPressed
//        Valid.pindah2(evt,KetFisik,Diagnosis);
    }//GEN-LAST:event_PenunjangKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt,Keadaan,GCS);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void KeadaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanKeyPressed
        Valid.pindah(evt,Fungsikognitif,Kesadaran);
    }//GEN-LAST:event_KeadaanKeyPressed

    private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPSKeyPressed
        Valid.pindah2(evt,KeluhanUtama,RPK);
    }//GEN-LAST:event_RPSKeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
        Valid.pindah(evt,Anamnesis,KeluhanUtama);
    }//GEN-LAST:event_HubunganKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt,Kesadaran,TB);
    }//GEN-LAST:event_GCSKeyPressed

    private void RPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPOKeyPressed
        Valid.pindah2(evt,RPD,Alergi);
    }//GEN-LAST:event_RPOKeyPressed

    private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPKKeyPressed
        Valid.pindah2(evt,RPS,RPD);
    }//GEN-LAST:event_RPKKeyPressed

    private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPDKeyPressed
        Valid.pindah2(evt,RPK,RPO);
    }//GEN-LAST:event_RPDKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt,Hubungan,RPS);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt,TglAsuhan,Hubungan);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,GCS,BB);
    }//GEN-LAST:event_TBKeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt,TB,TD);
    }//GEN-LAST:event_BBKeyPressed

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

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed

    }//GEN-LAST:event_KdDokterKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{
            Valid.pindah(evt,TCari,BtnDokter);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt,RPO,Ksadaran);
    }//GEN-LAST:event_AlergiKeyPressed

    private void PenampilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenampilanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenampilanKeyPressed

    private void TingkahLakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TingkahLakuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TingkahLakuKeyPressed

    private void MemoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MemoriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemoriKeyPressed

    private void DayaNilaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DayaNilaiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DayaNilaiKeyPressed

    private void PANSSCEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PANSSCEKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PANSSCEKeyPressed

    private void FantasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FantasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FantasiKeyPressed

    private void SuperegoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuperegoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuperegoKeyPressed

    private void KesadaranAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranAnakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesadaranAnakKeyPressed

    private void MotorikKasarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MotorikKasarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotorikKasarKeyPressed

    private void MotorikHalusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MotorikHalusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotorikHalusKeyPressed

    private void BundirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BundirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BundirKeyPressed

    private void Anamnesis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Anamnesis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Anamnesis1KeyPressed

    private void Anamnesis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Anamnesis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Anamnesis1ActionPerformed

    private void Hubungan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Hubungan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hubungan1KeyPressed

    private void KehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KehamilanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KehamilanKeyPressed

    private void PersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PersalinanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PersalinanKeyPressed

    private void PolaMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PolaMakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PolaMakanKeyPressed

    private void ToiletKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToiletKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ToiletKeyPressed

    private void PerkembanganFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerkembanganFisikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerkembanganFisikKeyPressed

    private void PerkembanganEmosiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerkembanganEmosiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerkembanganEmosiKeyPressed

    private void PerkembanganSosialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerkembanganSosialKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerkembanganSosialKeyPressed

    private void PerkembanganPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerkembanganPsikoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerkembanganPsikoKeyPressed

    private void RiwayatSekolahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatSekolahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RiwayatSekolahKeyPressed

    private void AyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AyahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AyahKeyPressed

    private void IbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IbuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IbuKeyPressed

    private void PerkawinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PerkawinanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerkawinanKeyPressed

    private void SaudaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaudaraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaudaraKeyPressed

    private void SaudaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaudaraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaudaraActionPerformed

    private void FaktorKeturunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FaktorKeturunanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FaktorKeturunanActionPerformed

    private void FaktorKeturunanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FaktorKeturunanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FaktorKeturunanKeyPressed

    private void GenogramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GenogramKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenogramKeyPressed

    private void Axis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis1KeyPressed

    private void Axis2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis2KeyPressed

    private void Axis3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis3KeyPressed

    private void Axis2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Axis2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis2ActionPerformed

    private void Axis4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis4KeyPressed

    private void Axis5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Axis5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis5ActionPerformed

    private void Axis5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis5KeyPressed

    private void PermasalahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PermasalahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PermasalahanKeyPressed

    private void FarmakulonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FarmakulonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FarmakulonKeyPressed

    private void NonFarmakulonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NonFarmakulonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NonFarmakulonKeyPressed

    private void LamaPerawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaPerawatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamaPerawatanKeyPressed

    private void targetTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_targetTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_targetTindakanKeyPressed

    private void ProgramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProgramKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProgramKeyPressed

    private void KonsulRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KonsulRujukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KonsulRujukKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedisRanapPsikiatrikAnakRemajaJiwa dialog = new RMPenilaianAwalMedisRanapPsikiatrikAnakRemajaJiwa(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Abdomen;
    private widget.TextBox Alergi;
    private widget.ComboBox Anamnesis;
    private widget.TextBox Anamnesis1;
    private widget.TextBox Axis1;
    private widget.TextBox Axis2;
    private widget.TextBox Axis3;
    private widget.TextBox Axis4;
    private widget.TextBox Axis5;
    private widget.TextBox Ayah;
    private widget.TextBox BB;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextArea Bundir;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DayaNilai;
    private widget.ComboBox Ekstremitas;
    private widget.TextBox FaktorKeturunan;
    private widget.TextArea Fantasi;
    private widget.TextArea Farmakulon;
    private widget.PanelBiasa FormInput;
    private widget.TextArea Fungsikognitif;
    private widget.TextBox GCS;
    private widget.TextArea Gangguanpersepsi;
    private widget.ComboBox Genital;
    private widget.TextArea Genogram;
    private widget.ComboBox Gigi;
    private widget.TextBox Hubungan;
    private widget.TextBox Hubungan1;
    private widget.TextBox Ibu;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.ComboBox Keadaan;
    private widget.TextArea Kehamilan;
    private widget.TextArea KeluhanUtama;
    private widget.ComboBox Kepala;
    private widget.ComboBox Kesadaran;
    private widget.TextArea KesadaranAnak;
    private widget.TextArea KetFisik;
    private widget.TextArea KonsulRujuk;
    private widget.TextArea Ksadaran;
    private widget.ComboBox Kulit;
    private widget.Label LCount;
    private widget.TextArea LamaPerawatan;
    private widget.editorpane LoadHTML;
    private widget.TextArea Memori;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextArea Mood;
    private widget.TextArea MotorikHalus;
    private widget.TextArea MotorikKasar;
    private widget.TextBox Nadi;
    private widget.TextBox NmDokter;
    private widget.TextArea NonFarmakulon;
    private widget.TextArea PANSSCE;
    private widget.TextArea Pembicaraan;
    private widget.TextArea Penampilan;
    private widget.TextArea Pengendalianimpuls;
    private widget.TextArea Penunjang;
    private widget.TextBox Perkawinan;
    private widget.TextArea PerkembanganEmosi;
    private widget.TextArea PerkembanganFisik;
    private widget.TextArea PerkembanganPsiko;
    private widget.TextArea PerkembanganSosial;
    private widget.TextArea Permasalahan;
    private widget.TextArea Persalinan;
    private widget.TextArea PolaMakan;
    private widget.TextArea Program;
    private widget.TextArea Prosespikir;
    private widget.TextArea Psikomotor;
    private widget.TextArea RPD;
    private widget.TextArea RPK;
    private widget.TextArea RPO;
    private widget.TextArea RPS;
    private widget.TextBox RR;
    private widget.TextArea RiwayatSekolah;
    private widget.TextBox SPO;
    private widget.TextBox Saudara;
    private widget.ScrollPane Scroll;
    private widget.TextArea Sikap;
    private widget.TextBox Suhu;
    private widget.TextArea Superego;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.ComboBox THT;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox Thoraks;
    private widget.TextArea Tilikan;
    private widget.TextArea TingkahLaku;
    private widget.TextArea Toilet;
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
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
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
    private widget.Label jLabel9;
    private widget.Label jLabel90;
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
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.ScrollPane scrollPane22;
    private widget.ScrollPane scrollPane23;
    private widget.ScrollPane scrollPane24;
    private widget.ScrollPane scrollPane25;
    private widget.ScrollPane scrollPane26;
    private widget.ScrollPane scrollPane27;
    private widget.ScrollPane scrollPane28;
    private widget.ScrollPane scrollPane29;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane30;
    private widget.ScrollPane scrollPane31;
    private widget.ScrollPane scrollPane32;
    private widget.ScrollPane scrollPane33;
    private widget.ScrollPane scrollPane34;
    private widget.ScrollPane scrollPane35;
    private widget.ScrollPane scrollPane36;
    private widget.ScrollPane scrollPane37;
    private widget.ScrollPane scrollPane38;
    private widget.ScrollPane scrollPane39;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane40;
    private widget.ScrollPane scrollPane41;
    private widget.ScrollPane scrollPane42;
    private widget.ScrollPane scrollPane43;
    private widget.ScrollPane scrollPane44;
    private widget.ScrollPane scrollPane45;
    private widget.ScrollPane scrollPane46;
    private widget.ScrollPane scrollPane47;
    private widget.ScrollPane scrollPane49;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.TextArea targetTindakan;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatrik_anak.tanggal,"+
                        "penilaian_medis_ranap_psikiatrik_anak.kd_dokter,penilaian_medis_ranap_psikiatrik_anak.anamnesis,penilaian_medis_ranap_psikiatrik_anak.hubungan,penilaian_medis_ranap_psikiatrik_anak.keluhan_utama,penilaian_medis_ranap_psikiatrik_anak.rps,penilaian_medis_ranap_psikiatrik_anak.rpk,penilaian_medis_ranap_psikiatrik_anak.rpd,penilaian_medis_ranap_psikiatrik_anak.rpo,penilaian_medis_ranap_psikiatrik_anak.alergi,"+
                        "penilaian_medis_ranap_psikiatrik_anak.anamnesis_dengan,penilaian_medis_ranap_psikiatrik_anak.hubungan_p_a,penilaian_medis_ranap_psikiatrik_anak.kehamilan,penilaian_medis_ranap_psikiatrik_anak.persalinan,penilaian_medis_ranap_psikiatrik_anak.pola_makan,penilaian_medis_ranap_psikiatrik_anak.toilet,penilaian_medis_ranap_psikiatrik_anak.pk_fisik,penilaian_medis_ranap_psikiatrik_anak.pk_emosi,penilaian_medis_ranap_psikiatrik_anak.pk_sosial,penilaian_medis_ranap_psikiatrik_anak.pk_psiko,penilaian_medis_ranap_psikiatrik_anak.r_sekolah,penilaian_medis_ranap_psikiatrik_anak.r_ayah,penilaian_medis_ranap_psikiatrik_anak.r_ibu,penilaian_medis_ranap_psikiatrik_anak.r_perkawinan,penilaian_medis_ranap_psikiatrik_anak.r_saudara,penilaian_medis_ranap_psikiatrik_anak.faktor_keturunan,penilaian_medis_ranap_psikiatrik_anak.genogram,"+
                        "penilaian_medis_ranap_psikiatrik_anak.penampilan,penilaian_medis_ranap_psikiatrik_anak.stts_kesadaran,penilaian_medis_ranap_psikiatrik_anak.sikap,penilaian_medis_ranap_psikiatrik_anak.tingkah_laku,penilaian_medis_ranap_psikiatrik_anak.pembicaraan,penilaian_medis_ranap_psikiatrik_anak.psikomotor,penilaian_medis_ranap_psikiatrik_anak.mood,penilaian_medis_ranap_psikiatrik_anak.proses_pikir,penilaian_medis_ranap_psikiatrik_anak.gangguan_persepsi,penilaian_medis_ranap_psikiatrik_anak.pengendalian_impuls,penilaian_medis_ranap_psikiatrik_anak.memori,penilaian_medis_ranap_psikiatrik_anak.daya_nilai,penilaian_medis_ranap_psikiatrik_anak.tilikan,penilaian_medis_ranap_psikiatrik_anak.fantasi,penilaian_medis_ranap_psikiatrik_anak.superego,penilaian_medis_ranap_psikiatrik_anak.kesadaran_anak,penilaian_medis_ranap_psikiatrik_anak.motorik_kasar,penilaian_medis_ranap_psikiatrik_anak.motorik_halus,penilaian_medis_ranap_psikiatrik_anak.ass_bundir,penilaian_medis_ranap_psikiatrik_anak.panss_ec,penilaian_medis_ranap_psikiatrik_anak.fungsi_kognitif,"+
                        
                        "penilaian_medis_ranap_psikiatrik_anak.keadaan,penilaian_medis_ranap_psikiatrik_anak.gcs,penilaian_medis_ranap_psikiatrik_anak.kesadaran,penilaian_medis_ranap_psikiatrik_anak.td,penilaian_medis_ranap_psikiatrik_anak.nadi,penilaian_medis_ranap_psikiatrik_anak.rr,penilaian_medis_ranap_psikiatrik_anak.suhu,penilaian_medis_ranap_psikiatrik_anak.spo,penilaian_medis_ranap_psikiatrik_anak.bb,penilaian_medis_ranap_psikiatrik_anak.tb,"+
                        "penilaian_medis_ranap_psikiatrik_anak.kepala,penilaian_medis_ranap_psikiatrik_anak.gigi,penilaian_medis_ranap_psikiatrik_anak.tht,penilaian_medis_ranap_psikiatrik_anak.thoraks,penilaian_medis_ranap_psikiatrik_anak.abdomen,penilaian_medis_ranap_psikiatrik_anak.ekstremitas,penilaian_medis_ranap_psikiatrik_anak.genital,penilaian_medis_ranap_psikiatrik_anak.kulit,penilaian_medis_ranap_psikiatrik_anak.ket_fisik,"+
                        "penilaian_medis_ranap_psikiatrik_anak.axis1,penilaian_medis_ranap_psikiatrik_anak.axis2,penilaian_medis_ranap_psikiatrik_anak.axis3,penilaian_medis_ranap_psikiatrik_anak.axis4,penilaian_medis_ranap_psikiatrik_anak.axis5,"+
                        "penilaian_medis_ranap_psikiatrik_anak.permasalahan,penilaian_medis_ranap_psikiatrik_anak.farmakulon,penilaian_medis_ranap_psikiatrik_anak.non_farmakulon,penilaian_medis_ranap_psikiatrik_anak.lama_perawatan,penilaian_medis_ranap_psikiatrik_anak.target,penilaian_medis_ranap_psikiatrik_anak.program,penilaian_medis_ranap_psikiatrik_anak.penunjang,penilaian_medis_ranap_psikiatrik_anak.konsul_rujuk,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatrik_anak.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_psikiatrik_anak.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_psikiatrik_anak.tanggal between ? and ? order by penilaian_medis_ranap_psikiatrik_anak.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatrik_anak.tanggal,"+
                        "penilaian_medis_ranap_psikiatrik_anak.kd_dokter,penilaian_medis_ranap_psikiatrik_anak.anamnesis,penilaian_medis_ranap_psikiatrik_anak.hubungan,penilaian_medis_ranap_psikiatrik_anak.keluhan_utama,penilaian_medis_ranap_psikiatrik_anak.rps,penilaian_medis_ranap_psikiatrik_anak.rpk,penilaian_medis_ranap_psikiatrik_anak.rpd,penilaian_medis_ranap_psikiatrik_anak.rpo,penilaian_medis_ranap_psikiatrik_anak.alergi,"+
                        "penilaian_medis_ranap_psikiatrik_anak.anamnesis_dengan,penilaian_medis_ranap_psikiatrik_anak.hubungan_p_a,penilaian_medis_ranap_psikiatrik_anak.kehamilan,penilaian_medis_ranap_psikiatrik_anak.persalinan,penilaian_medis_ranap_psikiatrik_anak.pola_makan,penilaian_medis_ranap_psikiatrik_anak.toilet,penilaian_medis_ranap_psikiatrik_anak.pk_fisik,penilaian_medis_ranap_psikiatrik_anak.pk_emosi,penilaian_medis_ranap_psikiatrik_anak.pk_sosial,penilaian_medis_ranap_psikiatrik_anak.pk_psiko,penilaian_medis_ranap_psikiatrik_anak.r_sekolah,penilaian_medis_ranap_psikiatrik_anak.r_ayah,penilaian_medis_ranap_psikiatrik_anak.r_ibu,penilaian_medis_ranap_psikiatrik_anak.r_perkawinan,penilaian_medis_ranap_psikiatrik_anak.r_saudara,penilaian_medis_ranap_psikiatrik_anak.faktor_keturunan,penilaian_medis_ranap_psikiatrik_anak.genogram,"+
                        "penilaian_medis_ranap_psikiatrik_anak.penampilan,penilaian_medis_ranap_psikiatrik_anak.stts_kesadaran,penilaian_medis_ranap_psikiatrik_anak.sikap,penilaian_medis_ranap_psikiatrik_anak.tingkah_laku,penilaian_medis_ranap_psikiatrik_anak.pembicaraan,penilaian_medis_ranap_psikiatrik_anak.psikomotor,penilaian_medis_ranap_psikiatrik_anak.mood,penilaian_medis_ranap_psikiatrik_anak.proses_pikir,penilaian_medis_ranap_psikiatrik_anak.gangguan_persepsi,penilaian_medis_ranap_psikiatrik_anak.pengendalian_impuls,penilaian_medis_ranap_psikiatrik_anak.memori,penilaian_medis_ranap_psikiatrik_anak.daya_nilai,penilaian_medis_ranap_psikiatrik_anak.tilikan,penilaian_medis_ranap_psikiatrik_anak.fantasi,penilaian_medis_ranap_psikiatrik_anak.superego,penilaian_medis_ranap_psikiatrik_anak.kesadaran_anak,penilaian_medis_ranap_psikiatrik_anak.motorik_kasar,penilaian_medis_ranap_psikiatrik_anak.motorik_halus,penilaian_medis_ranap_psikiatrik_anak.ass_bundir,penilaian_medis_ranap_psikiatrik_anak.panss_ec,penilaian_medis_ranap_psikiatrik_anak.fungsi_kognitif,"+
                        
                        "penilaian_medis_ranap_psikiatrik_anak.keadaan,penilaian_medis_ranap_psikiatrik_anak.gcs,penilaian_medis_ranap_psikiatrik_anak.kesadaran,penilaian_medis_ranap_psikiatrik_anak.td,penilaian_medis_ranap_psikiatrik_anak.nadi,penilaian_medis_ranap_psikiatrik_anak.rr,penilaian_medis_ranap_psikiatrik_anak.suhu,penilaian_medis_ranap_psikiatrik_anak.spo,penilaian_medis_ranap_psikiatrik_anak.bb,penilaian_medis_ranap_psikiatrik_anak.tb,"+
                        "penilaian_medis_ranap_psikiatrik_anak.kepala,penilaian_medis_ranap_psikiatrik_anak.gigi,penilaian_medis_ranap_psikiatrik_anak.tht,penilaian_medis_ranap_psikiatrik_anak.thoraks,penilaian_medis_ranap_psikiatrik_anak.abdomen,penilaian_medis_ranap_psikiatrik_anak.ekstremitas,penilaian_medis_ranap_psikiatrik_anak.genital,penilaian_medis_ranap_psikiatrik_anak.kulit,penilaian_medis_ranap_psikiatrik_anak.ket_fisik,"+
                        "penilaian_medis_ranap_psikiatrik_anak.axis1,penilaian_medis_ranap_psikiatrik_anak.axis2,penilaian_medis_ranap_psikiatrik_anak.axis3,penilaian_medis_ranap_psikiatrik_anak.axis4,penilaian_medis_ranap_psikiatrik_anak.axis5,"+
                        "penilaian_medis_ranap_psikiatrik_anak.permasalahan,penilaian_medis_ranap_psikiatrik_anak.farmakulon,penilaian_medis_ranap_psikiatrik_anak.non_farmakulon,penilaian_medis_ranap_psikiatrik_anak.lama_perawatan,penilaian_medis_ranap_psikiatrik_anak.target,penilaian_medis_ranap_psikiatrik_anak.program,penilaian_medis_ranap_psikiatrik_anak.penunjang,penilaian_medis_ranap_psikiatrik_anak.konsul_rujuk,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_psikiatrik_anak on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatrik_anak.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_psikiatrik_anak.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_psikiatrik_anak.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_medis_ranap_psikiatrik_anak.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ranap_psikiatrik_anak.tanggal");
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
                        rs.getString("anamnesis"),rs.getString("hubungan"),rs.getString("keluhan_utama"),rs.getString("rps"),rs.getString("rpd"),rs.getString("rpk"),rs.getString("rpo"),rs.getString("alergi"),
                        rs.getString("anamnesis_dengan"),rs.getString("hubungan_p_a"),rs.getString("kehamilan"),rs.getString("persalinan"),rs.getString("pola_makan"),rs.getString("toilet"),rs.getString("pk_fisik"),rs.getString("pk_emosi"),rs.getString("pk_sosial"),rs.getString("pk_psiko"),rs.getString("r_sekolah"),rs.getString("r_ayah"),rs.getString("r_ibu"),rs.getString("r_perkawinan"),rs.getString("r_saudara"),rs.getString("faktor_keturunan"),rs.getString("genogram"),
                        rs.getString("penampilan"),rs.getString("stts_kesadaran"),rs.getString("sikap"),rs.getString("tingkah_laku"),rs.getString("pembicaraan"),rs.getString("psikomotor"),rs.getString("mood"),rs.getString("proses_pikir"),rs.getString("gangguan_persepsi"),rs.getString("pengendalian_impuls"),rs.getString("memori"),rs.getString("daya_nilai"),rs.getString("tilikan"),rs.getString("fantasi"),rs.getString("superego"),rs.getString("kesadaran_anak"),rs.getString("motorik_kasar"),rs.getString("motorik_halus"),rs.getString("ass_bundir"),rs.getString("panss_ec"),rs.getString("fungsi_kognitif"),
                        
                        rs.getString("keadaan"),rs.getString("kesadaran"),rs.getString("gcs"),rs.getString("tb"),rs.getString("bb"),rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("spo"),rs.getString("kepala"),rs.getString("gigi"),rs.getString("tht"),rs.getString("thoraks"),rs.getString("abdomen"),rs.getString("genital"),rs.getString("ekstremitas"),rs.getString("kulit"),rs.getString("ket_fisik"),
                        rs.getString("penunjang"),
                        rs.getString("axis1"),rs.getString("axis2"),rs.getString("axis3"),rs.getString("axis4"),rs.getString("axis5"),
                        
                        rs.getString("permasalahan"),rs.getString("farmakulon"),rs.getString("non_farmakulon"),rs.getString("lama_perawatan"),rs.getString("target"),rs.getString("program"),
                        
                        rs.getString("konsul_rujuk")
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
        KeluhanUtama.setText("");
        RPS.setText("");
        RPK.setText("");
        RPD.setText("");
        RPO.setText("");
        Alergi.setText("");
        
        Anamnesis1.setText("");
        Hubungan1.setText("");
        Kehamilan.setText("");
        Persalinan.setText("");
        PolaMakan.setText("");
        Toilet.setText("");
        PerkembanganFisik.setText("");
        PerkembanganEmosi.setText("");
        PerkembanganSosial.setText("");
        PerkembanganPsiko.setText("");
        RiwayatSekolah.setText("");
        Ayah.setText("");
        Ibu.setText("");
        Perkawinan.setText("");
        Saudara.setText("");
        FaktorKeturunan.setText("");
        Genogram.setText("");
        
        Penampilan.setText("");
        Ksadaran.setText("");
        Sikap.setText("");
        TingkahLaku.setText("");
        Pembicaraan.setText("");
        Psikomotor.setText("");
        Mood.setText("");
        Prosespikir.setText("");
        Gangguanpersepsi.setText("");
        Pengendalianimpuls.setText("");
        Memori.setText("");
        DayaNilai.setText("");
        Tilikan.setText("");
        Fantasi.setText("");
        Superego.setText("");
        KesadaranAnak.setText("");
        MotorikKasar.setText("");
        MotorikHalus.setText("");
        Bundir.setText("");
        PANSSCE.setText("");
        Fungsikognitif.setText("");
        
        Keadaan.setSelectedIndex(0);
        GCS.setText("");
        Kesadaran.setSelectedIndex(0);
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        SPO.setText("");
        BB.setText("");
        TB.setText("");
        Kepala.setSelectedIndex(0);
        Gigi.setSelectedIndex(0);
        THT.setSelectedIndex(0);
        Thoraks.setSelectedIndex(0);
        Abdomen.setSelectedIndex(0);
        Genital.setSelectedIndex(0);
        Ekstremitas.setSelectedIndex(0);
        Kulit.setSelectedIndex(0);
        KetFisik.setText("");
        
        Penunjang.setText("");
        
        Axis1.setText("");
        Axis2.setText("");
        Axis3.setText("");
        Axis4.setText("");
        Axis5.setText("");
        
        Permasalahan.setText("");
        Farmakulon.setText("");
        NonFarmakulon.setText("");
        LamaPerawatan.setText("");
        targetTindakan.setText("");
        Program.setText("");
        
        KonsulRujuk.setText("");
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
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            
            Anamnesis1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Hubungan1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Kehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            Persalinan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            PolaMakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            Toilet.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            PerkembanganFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            PerkembanganEmosi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            PerkembanganSosial.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            PerkembanganPsiko.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            RiwayatSekolah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Ayah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            Ibu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Perkawinan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            Saudara.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            FaktorKeturunan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            Genogram.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            
            Penampilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());     
            Ksadaran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            Sikap.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            TingkahLaku.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            Pembicaraan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            Psikomotor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            Mood.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            Prosespikir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            Gangguanpersepsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            Pengendalianimpuls.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            Memori.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            DayaNilai.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            Tilikan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            Fantasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            Superego.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            KesadaranAnak.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            MotorikKasar.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            MotorikHalus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            Bundir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            PANSSCE.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            Fungsikognitif.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            
            Keadaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            SPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            Kepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            Gigi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            THT.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            Thoraks.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            Abdomen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            Genital.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            Ekstremitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),70).toString());
            Kulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),71).toString());
            KetFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
            
            Penunjang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
            
            Axis1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),74).toString());
            Axis2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
            Axis3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
            Axis4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),77).toString());
            Axis5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            
            Permasalahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),79).toString());
            Farmakulon.setText(tbObat.getValueAt(tbObat.getSelectedRow(),80).toString());
            NonFarmakulon.setText(tbObat.getValueAt(tbObat.getSelectedRow(),81).toString());
            LamaPerawatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),82).toString());
            targetTindakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),83).toString());
            Program.setText(tbObat.getValueAt(tbObat.getSelectedRow(),84).toString());
            
            KonsulRujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),85).toString());
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
        BtnSimpan.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_anak());
        BtnHapus.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_anak());
        BtnEdit.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_anak());
        BtnEdit.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_anak());
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
        if(Sequel.queryu2tf("delete from penilaian_medis_ranap_psikiatrik_anak where no_rawat=?",1,new String[]{
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
        if(Sequel.mengedittf("penilaian_medis_ranap_psikiatrik_anak","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,anamnesis=?,hubungan=?,keluhan_utama=?,rps=?,rpd=?,rpk=?,rpo=?,alergi=?,"
                + "anamnesis_dengan=?,hubungan_p_a=?,kehamilan=?,persalinan=?,pola_makan=?,toilet=?,pk_fisik=?,pk_emosi=?,pk_sosial=?,pk_psiko=?,r_sekolah=?,r_ayah=?,r_ibu=?,r_perkawinan=?,r_saudara=?,faktor_keturunan=?,genogram=?,"
                + "penampilan=?,stts_kesadaran=?,sikap=?,tingkah_laku=?,pembicaraan=?,psikomotor=?,mood=?,proses_pikir=?,gangguan_persepsi=?,pengendalian_impuls=?,memori=?,daya_nilai=?,tilikan=?,fantasi=?,superego=?,kesadaran_anak=?,motorik_kasar=?,motorik_halus=?,ass_bundir=?,panss_ec=?,fungsi_kognitif=?,"
                + "keadaan=?,kesadaran=?,gcs=?,tb=?,bb=?,td=?,nadi=?,rr=?,suhu=?,spo=?,kepala=?,gigi=?,tht=?,thoraks=?,abdomen=?,genital=?,ekstremitas=?,kulit=?,ket_fisik=?,"
                + "penunjang=?,"
                + "axis1=?,axis2=?,axis3=?,axis4=?,axis5=?,"
                + "permasalahan=?,farmakulon=?,non_farmakulon=?,lama_perawatan=?,target=?,program=?,konsul_rujuk=?",82,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),Hubungan.getText(),KeluhanUtama.getText(),RPS.getText(),RPD.getText(),RPK.getText(),RPO.getText(),Alergi.getText(),
                Anamnesis1.getText(),Hubungan1.getText(),Kehamilan.getText(),Persalinan.getText(),PolaMakan.getText(),Toilet.getText(),PerkembanganFisik.getText(),PerkembanganEmosi.getText(),PerkembanganSosial.getText(),PerkembanganPsiko.getText(),RiwayatSekolah.getText(),Ayah.getText(), Ibu.getText(), Perkawinan.getText(),Saudara.getText(), FaktorKeturunan.getText(), Genogram.getText(),
                Penampilan.getText(),Ksadaran.getText(),Sikap.getText(),TingkahLaku.getText(),Pembicaraan.getText(),Psikomotor.getText(),Mood.getText(),Prosespikir.getText(),Gangguanpersepsi.getText(),Pengendalianimpuls.getText(),Memori.getText(),DayaNilai.getText(),Tilikan.getText(),Fantasi.getText(),Superego.getText(),KesadaranAnak.getText(),MotorikKasar.getText(),MotorikHalus.getText(),Bundir.getText(),PANSSCE.getText(),Fungsikognitif.getText(),
                Keadaan.getSelectedItem().toString(),Kesadaran.getSelectedItem().toString(),GCS.getText(),TB.getText(),BB.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),SPO.getText(),Kepala.getSelectedItem().toString(),Gigi.getSelectedItem().toString(),THT.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Genital.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),Kulit.getSelectedItem().toString(),KetFisik.getText(),
                Penunjang.getText(),
                Axis1.getText(),Axis2.getText(),Axis3.getText(),Axis4.getText(),Axis5.getText(),
                Permasalahan.getText(),Farmakulon.getText(),NonFarmakulon.getText(),LamaPerawatan.getText(),targetTindakan.getText(),Program.getText(),KonsulRujuk.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
