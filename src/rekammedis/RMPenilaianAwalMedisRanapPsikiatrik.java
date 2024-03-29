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
public final class RMPenilaianAwalMedisRanapPsikiatrik extends javax.swing.JDialog {
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
    public RMPenilaianAwalMedisRanapPsikiatrik(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            //ini header dari tabel data tampil My Note
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Tanggal","Anamnesis",
            "Hubungan","Keluhan Utama",
"Riwayat Penyakit Sekarang","Riw Pen Dahulu",
"Riw Pen Fisik & Neurologi","Riwayat Napza","Riwayat Alergi",
"Penampilan","Pembicaraan","Psikomotor",
"Sikap","Mood / Afek",
"Fungsi Kognitif","Gangguan Persepsi","Proses Pikir",
"Pengendalian Impuls","Tilikan","Keadaan Umum",
"GCS","Kesadaran","TD(mmHg)","Nadi(x/menit)",
"RR(x/menit)","Suhu","SpO2","BB(Kg)",
"TB(cm)","Kepala","Gigi & Mulut","THT",
"Thoraks","Abdomen","Genital & Anus","Ekstremitas",
"Kulit","Keterangan Pemeriksaan Fisik","Pemeriksaan Penunjang",
//"Tatalaksana",
"Konsul/Rujuk","Kesadaran Jiwa","Tingkah Laku","PANSS EC","Assesmen Bundir",
"Memori","Daya Nilai","Res Melarikan Diri","Axis 1","Axis 2","Axis 3","Axis 4","Axis 5","Farmakologis",
"Lama Keperawatan","Target Terukur","Non Farmakologis","Prognosis","Permasalahan"
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
        //ini buat tabel tampil data My Note
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Hubungan.setDocument(new batasInput((int)30).getKata(Hubungan));
        KeluhanUtama.setDocument(new batasInput((int)2000).getKata(KeluhanUtama));
        
        RPS.setDocument(new batasInput((int)2000).getKata(RPS));
        RPK.setDocument(new batasInput((int)1000).getKata(RPK));
        RPD.setDocument(new batasInput((int)1000).getKata(RPD));
        RPO.setDocument(new batasInput((int)1000).getKata(RPO));
        Alergi.setDocument(new batasInput((int)50).getKata(Alergi));
            
        Penampilan.setDocument(new batasInput((int)200).getKata(Penampilan));
        KesadaranJiwa.setDocument(new batasInput((int)200).getKata(KesadaranJiwa));
        Sikap.setDocument(new batasInput((int)200).getKata(Sikap));
        TingkahLaku.setDocument(new batasInput((int)200).getKata(TingkahLaku));
        Pembicaraan.setDocument(new batasInput((int)200).getKata(Pembicaraan));
        Psikomotor.setDocument(new batasInput((int)200).getKata(Psikomotor));
        MoodAfek.setDocument(new batasInput((int)200).getKata(MoodAfek));
        Prosespikir.setDocument(new batasInput((int)200).getKata(Prosespikir));
        Pengendalianimpuls.setDocument(new batasInput((int)200).getKata(Pengendalianimpuls));
        Memori.setDocument(new batasInput((int)200).getKata(Memori));
        Dayanilai.setDocument(new batasInput((int)200).getKata(Dayanilai));
        Tilikan.setDocument(new batasInput((int)200).getKata(Tilikan));
        GangguanPersepsi.setDocument(new batasInput((int)200).getKata(GangguanPersepsi));
        PANSS.setDocument(new batasInput((int)200).getKata(PANSS));
        ResikoMelarikanDiri.setDocument(new batasInput((int)200).getKata(ResikoMelarikanDiri));
        
        TD.setDocument(new batasInput((byte)8).getKata(TD));
        Nadi.setDocument(new batasInput((byte)5).getKata(Nadi));
        RR.setDocument(new batasInput((byte)5).getKata(RR));
        Suhu.setDocument(new batasInput((byte)5).getKata(Suhu));
        SPO.setDocument(new batasInput((byte)5).getKata(SPO));
        BB.setDocument(new batasInput((byte)5).getKata(BB));
        TB.setDocument(new batasInput((byte)5).getKata(TB));
        KetFisik.setDocument(new batasInput((int)1000).getKata(KetFisik));
        
        Penunjang.setDocument(new batasInput((int)1000).getKata(Penunjang));
        
        Axis1.setDocument(new batasInput((int)300).getKata(Axis1));
        Axis2.setDocument(new batasInput((int)300).getKata(Axis2));
        Axis3.setDocument(new batasInput((int)300).getKata(Axis3));
        Axis4.setDocument(new batasInput((int)300).getKata(Axis4));
        Axis5.setDocument(new batasInput((int)300).getKata(Axis5));
        
//        Tatalaksana.setDocument(new batasInput((int)1000).getKata(Tatalaksana));
        Permasalahan.setDocument(new batasInput((int)1000).getKata(Permasalahan));
        Farmakologis.setDocument(new batasInput((int)1000).getKata(Farmakologis));
        Nonfarmakologis.setDocument(new batasInput((int)1000).getKata(Nonfarmakologis));
        Lamarawat.setDocument(new batasInput((int)1000).getKata(Lamarawat));
        Prognosis.setDocument(new batasInput((int)1000).getKata(Prognosis));
        Targetukur.setDocument(new batasInput((int)1000).getKata(Targetukur));
        Konsul.setDocument(new batasInput((int)500).getKata(Konsul));
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
        scrollPane12 = new widget.ScrollPane();
        Axis2 = new widget.TextArea();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel103 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Konsul = new widget.TextArea();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel104 = new widget.Label();
        jLabel14 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Penampilan = new widget.TextArea();
        jLabel34 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Pembicaraan = new widget.TextArea();
        jLabel36 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Psikomotor = new widget.TextArea();
        jLabel42 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        MoodAfek = new widget.TextArea();
        jLabel43 = new widget.Label();
        scrollPane17 = new widget.ScrollPane();
        Sikap = new widget.TextArea();
        jLabel53 = new widget.Label();
        scrollPane20 = new widget.ScrollPane();
        Prosespikir = new widget.TextArea();
        jLabel54 = new widget.Label();
        scrollPane21 = new widget.ScrollPane();
        Pengendalianimpuls = new widget.TextArea();
        jLabel55 = new widget.Label();
        scrollPane22 = new widget.ScrollPane();
        GangguanPersepsi = new widget.TextArea();
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
        Alergi = new widget.TextBox();
        scrollPane8 = new widget.ScrollPane();
        KesadaranJiwa = new widget.TextArea();
        jLabel27 = new widget.Label();
        jLabel57 = new widget.Label();
        scrollPane24 = new widget.ScrollPane();
        TingkahLaku = new widget.TextArea();
        jLabel58 = new widget.Label();
        scrollPane25 = new widget.ScrollPane();
        Memori = new widget.TextArea();
        jLabel59 = new widget.Label();
        scrollPane26 = new widget.ScrollPane();
        Dayanilai = new widget.TextArea();
        jLabel60 = new widget.Label();
        scrollPane27 = new widget.ScrollPane();
        PANSS = new widget.TextArea();
        scrollPane33 = new widget.ScrollPane();
        ResikoMelarikanDiri = new widget.TextArea();
        jLabel66 = new widget.Label();
        jLabel95 = new widget.Label();
        jLabel67 = new widget.Label();
        scrollPane34 = new widget.ScrollPane();
        Axis1 = new widget.TextArea();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        scrollPane35 = new widget.ScrollPane();
        Axis3 = new widget.TextArea();
        jLabel108 = new widget.Label();
        scrollPane36 = new widget.ScrollPane();
        Axis4 = new widget.TextArea();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        scrollPane37 = new widget.ScrollPane();
        Axis5 = new widget.TextArea();
        jLabel112 = new widget.Label();
        scrollPane39 = new widget.ScrollPane();
        Permasalahan = new widget.TextArea();
        jLabel113 = new widget.Label();
        scrollPane40 = new widget.ScrollPane();
        Farmakologis = new widget.TextArea();
        jLabel114 = new widget.Label();
        scrollPane41 = new widget.ScrollPane();
        Nonfarmakologis = new widget.TextArea();
        jLabel115 = new widget.Label();
        scrollPane42 = new widget.ScrollPane();
        Lamarawat = new widget.TextArea();
        jLabel116 = new widget.Label();
        scrollPane43 = new widget.ScrollPane();
        Targetukur = new widget.TextArea();
        scrollPane44 = new widget.ScrollPane();
        Prognosis = new widget.TextArea();
        scrollPane45 = new widget.ScrollPane();
        AssesmenBundir = new widget.TextArea();
        jLabel68 = new widget.Label();
        scrollPane46 = new widget.ScrollPane();
        FungsiKognitif = new widget.TextArea();
        jLabel69 = new widget.Label();
        jLabel118 = new widget.Label();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Medis Rawat Inap Psikiatri Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1700));
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

        jLabel9.setText("Riwayat NAPZA :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 190, 126, 23);

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
        jLabel12.setBounds(760, 650, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(800, 650, 45, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(850, 650, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(680, 650, 45, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(640, 650, 30, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" Cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(720, 650, 30, 23);

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
        jLabel28.setBounds(490, 650, 70, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(570, 650, 60, 23);

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
        jSeparator12.setBounds(0, 240, 880, 1);

        jLabel39.setText("Kesadaran :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(270, 650, 70, 23);

        Keadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sehat", "Sakit Ringan", "Sakit Sedang", "Sakit Berat" }));
        Keadaan.setName("Keadaan"); // NOI18N
        Keadaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanKeyPressed(evt);
            }
        });
        FormInput.add(Keadaan);
        Keadaan.setBounds(140, 650, 118, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Apatis", "Somnolen", "Sopor", "Koma" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KesadaranActionPerformed(evt);
            }
        });
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(340, 650, 130, 23);

        jLabel41.setText("Keadaan Umum :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 650, 127, 23);

        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("I. RIWAYAT KESEHATAN");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 70, 180, 23);

        jLabel79.setText("Riwayat Alergi :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(440, 190, 150, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 840, 880, 1);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("II. STATUS PSIKIATRIK");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(10, 240, 180, 23);

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
        scrollPane9.setBounds(50, 860, 810, 63);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(0, 940, 880, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("IV. PEMERIKSAAN PENUNJANG");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(10, 840, 190, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Axis2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Axis2.setColumns(20);
        Axis2.setRows(3);
        Axis2.setName("Axis2"); // NOI18N
        Axis2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis2KeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Axis2);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(50, 1040, 810, 43);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(0, 1280, 880, 1);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("VI. TATALAKSANA & PERMASALAHAN");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(10, 1280, 190, 23);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Konsul.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Konsul.setColumns(20);
        Konsul.setRows(5);
        Konsul.setName("Konsul"); // NOI18N
        Konsul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KonsulKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Konsul);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(50, 1570, 810, 63);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(380, 40, 52, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023 13:40:22" }));
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

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(0, 1550, 880, 1);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("VII. KONSUL/RUJUK");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(10, 1550, 190, 23);

        jLabel14.setText("Penampilan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 260, 124, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Penampilan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Penampilan.setColumns(20);
        Penampilan.setRows(5);
        Penampilan.setName("Penampilan"); // NOI18N
        Penampilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenampilanKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Penampilan);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(130, 260, 290, 33);

        jLabel34.setText("Pembicaraan :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 420, 124, 23);

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
        scrollPane11.setBounds(130, 420, 290, 33);

        jLabel36.setText("Psikomotor :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 460, 124, 23);

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
        scrollPane15.setBounds(130, 460, 290, 33);

        jLabel42.setText("Mood / Afek :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 500, 124, 23);

        scrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane16.setName("scrollPane16"); // NOI18N

        MoodAfek.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MoodAfek.setColumns(20);
        MoodAfek.setRows(5);
        MoodAfek.setName("MoodAfek"); // NOI18N
        MoodAfek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoodAfekKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(MoodAfek);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(130, 500, 290, 33);

        jLabel43.setText("Sikap :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 340, 124, 23);

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
        scrollPane17.setBounds(130, 340, 290, 33);

        jLabel53.setText("Proses Pikir & Isi Pikir :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 540, 120, 23);

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
        scrollPane20.setBounds(130, 540, 290, 33);

        jLabel54.setText("Pengendalian Impuls :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(450, 260, 120, 23);

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
        scrollPane21.setBounds(580, 260, 290, 33);

        jLabel55.setText("Gangguan Persepsi :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 580, 120, 23);

        scrollPane22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane22.setName("scrollPane22"); // NOI18N

        GangguanPersepsi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        GangguanPersepsi.setColumns(20);
        GangguanPersepsi.setRows(5);
        GangguanPersepsi.setName("GangguanPersepsi"); // NOI18N
        GangguanPersepsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanPersepsiKeyPressed(evt);
            }
        });
        scrollPane22.setViewportView(GangguanPersepsi);

        FormInput.add(scrollPane22);
        scrollPane22.setBounds(130, 580, 290, 33);

        jLabel56.setText("Insight (Tilikan):");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(450, 470, 120, 23);

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
        scrollPane23.setBounds(580, 460, 290, 33);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(0, 630, 880, 1);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(380, 680, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(330, 680, 45, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(280, 680, 40, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(610, 680, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(650, 680, 45, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 680, 127, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(140, 680, 76, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(700, 680, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(220, 680, 50, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(530, 680, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(480, 680, 45, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(440, 680, 40, 23);

        jLabel29.setText("SpO2 :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(750, 680, 40, 23);

        SPO.setFocusTraversalPolicyProvider(true);
        SPO.setName("SPO"); // NOI18N
        SPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SPOKeyPressed(evt);
            }
        });
        FormInput.add(SPO);
        SPO.setBounds(800, 680, 45, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("%");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(850, 680, 30, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        KetFisik.setBorder(javax.swing.BorderFactory.createTitledBorder("Keterangan Fisik"));
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
        scrollPane5.setBounds(520, 710, 340, 113);

        Abdomen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Abdomen.setName("Abdomen"); // NOI18N
        Abdomen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbdomenActionPerformed(evt);
            }
        });
        Abdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbdomenKeyPressed(evt);
            }
        });
        FormInput.add(Abdomen);
        Abdomen.setBounds(370, 710, 128, 23);

        jLabel49.setText("Abdomen :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(270, 710, 95, 23);

        Kepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Kepala.setName("Kepala"); // NOI18N
        Kepala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KepalaActionPerformed(evt);
            }
        });
        Kepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KepalaKeyPressed(evt);
            }
        });
        FormInput.add(Kepala);
        Kepala.setBounds(140, 710, 128, 23);

        jLabel40.setText("Kepala :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 710, 127, 23);

        jLabel44.setText("Gigi & Mulut :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 740, 127, 23);

        Gigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Gigi.setName("Gigi"); // NOI18N
        Gigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GigiKeyPressed(evt);
            }
        });
        FormInput.add(Gigi);
        Gigi.setBounds(140, 740, 128, 23);

        jLabel50.setText("Genital & Anus :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(270, 740, 95, 23);

        Genital.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Genital.setName("Genital"); // NOI18N
        Genital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GenitalKeyPressed(evt);
            }
        });
        FormInput.add(Genital);
        Genital.setBounds(370, 740, 128, 23);

        Ekstremitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Ekstremitas.setName("Ekstremitas"); // NOI18N
        Ekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstremitasKeyPressed(evt);
            }
        });
        FormInput.add(Ekstremitas);
        Ekstremitas.setBounds(370, 770, 128, 23);

        jLabel51.setText("Ekstremitas :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(270, 770, 95, 23);

        THT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        THT.setName("THT"); // NOI18N
        THT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                THTActionPerformed(evt);
            }
        });
        THT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THTKeyPressed(evt);
            }
        });
        FormInput.add(THT);
        THT.setBounds(140, 770, 128, 23);

        jLabel45.setText("THT :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 770, 127, 23);

        Kulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Kulit.setName("Kulit"); // NOI18N
        Kulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KulitKeyPressed(evt);
            }
        });
        FormInput.add(Kulit);
        Kulit.setBounds(370, 800, 128, 23);

        jLabel52.setText("Kulit :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(270, 800, 95, 23);

        Thoraks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal", "Tidak Diperiksa" }));
        Thoraks.setName("Thoraks"); // NOI18N
        Thoraks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThoraksKeyPressed(evt);
            }
        });
        FormInput.add(Thoraks);
        Thoraks.setBounds(140, 800, 128, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(594, 190, 260, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        KesadaranJiwa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KesadaranJiwa.setColumns(20);
        KesadaranJiwa.setRows(5);
        KesadaranJiwa.setName("KesadaranJiwa"); // NOI18N
        KesadaranJiwa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranJiwaKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(KesadaranJiwa);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(130, 300, 290, 33);

        jLabel27.setText("Kesadaran Jiwa :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 300, 124, 23);

        jLabel57.setText("Tingkah Laku :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 380, 124, 23);

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
        scrollPane24.setBounds(130, 380, 290, 33);

        jLabel58.setText("Memori :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(450, 300, 120, 23);

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
        scrollPane25.setBounds(580, 300, 290, 33);

        jLabel59.setText("Daya Nilai :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(450, 340, 120, 23);

        scrollPane26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane26.setName("scrollPane26"); // NOI18N

        Dayanilai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Dayanilai.setColumns(20);
        Dayanilai.setRows(5);
        Dayanilai.setName("Dayanilai"); // NOI18N
        Dayanilai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DayanilaiKeyPressed(evt);
            }
        });
        scrollPane26.setViewportView(Dayanilai);

        FormInput.add(scrollPane26);
        scrollPane26.setBounds(580, 340, 290, 33);

        jLabel60.setText("PANSS-EC:");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(450, 380, 120, 23);

        scrollPane27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane27.setName("scrollPane27"); // NOI18N

        PANSS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PANSS.setColumns(20);
        PANSS.setRows(5);
        PANSS.setName("PANSS"); // NOI18N
        PANSS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PANSSKeyPressed(evt);
            }
        });
        scrollPane27.setViewportView(PANSS);

        FormInput.add(scrollPane27);
        scrollPane27.setBounds(580, 380, 290, 33);

        scrollPane33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane33.setName("scrollPane33"); // NOI18N

        ResikoMelarikanDiri.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ResikoMelarikanDiri.setColumns(20);
        ResikoMelarikanDiri.setRows(5);
        ResikoMelarikanDiri.setName("ResikoMelarikanDiri"); // NOI18N
        ResikoMelarikanDiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ResikoMelarikanDiriKeyPressed(evt);
            }
        });
        scrollPane33.setViewportView(ResikoMelarikanDiri);

        FormInput.add(scrollPane33);
        scrollPane33.setBounds(580, 500, 290, 33);

        jLabel66.setText("Fungsi Kognitif :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(450, 540, 120, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("III. PEMERIKSAAN FISIK");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(10, 630, 180, 23);

        jLabel67.setText("Thoraks :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 800, 127, 23);

        scrollPane34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane34.setName("scrollPane34"); // NOI18N

        Axis1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Axis1.setColumns(20);
        Axis1.setRows(3);
        Axis1.setName("Axis1"); // NOI18N
        Axis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis1KeyPressed(evt);
            }
        });
        scrollPane34.setViewportView(Axis1);

        FormInput.add(scrollPane34);
        scrollPane34.setBounds(50, 980, 810, 43);

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("V. DIAGNOSIS/ASESMEN");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(10, 940, 190, 23);

        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("Axis I :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(50, 960, 190, 23);

        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("Axis II :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(50, 1020, 190, 23);

        scrollPane35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane35.setName("scrollPane35"); // NOI18N

        Axis3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Axis3.setColumns(20);
        Axis3.setRows(3);
        Axis3.setName("Axis3"); // NOI18N
        Axis3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis3KeyPressed(evt);
            }
        });
        scrollPane35.setViewportView(Axis3);

        FormInput.add(scrollPane35);
        scrollPane35.setBounds(50, 1100, 810, 43);

        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("Axis III :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(50, 1080, 190, 23);

        scrollPane36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane36.setName("scrollPane36"); // NOI18N

        Axis4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Axis4.setColumns(20);
        Axis4.setRows(3);
        Axis4.setName("Axis4"); // NOI18N
        Axis4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis4KeyPressed(evt);
            }
        });
        scrollPane36.setViewportView(Axis4);

        FormInput.add(scrollPane36);
        scrollPane36.setBounds(50, 1160, 810, 43);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Axis IV :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(50, 1140, 190, 23);

        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("Axis V :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(50, 1200, 190, 23);

        scrollPane37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane37.setName("scrollPane37"); // NOI18N

        Axis5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Axis5.setColumns(20);
        Axis5.setRows(3);
        Axis5.setName("Axis5"); // NOI18N
        Axis5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Axis5KeyPressed(evt);
            }
        });
        scrollPane37.setViewportView(Axis5);

        FormInput.add(scrollPane37);
        scrollPane37.setBounds(50, 1220, 810, 43);

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Permasalahan :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(490, 1300, 190, 23);

        scrollPane39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane39.setName("scrollPane39"); // NOI18N

        Permasalahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Permasalahan.setColumns(20);
        Permasalahan.setRows(3);
        Permasalahan.setName("Permasalahan"); // NOI18N
        Permasalahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PermasalahanKeyPressed(evt);
            }
        });
        scrollPane39.setViewportView(Permasalahan);

        FormInput.add(scrollPane39);
        scrollPane39.setBounds(490, 1320, 360, 43);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Farmakologis :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(40, 1300, 190, 23);

        scrollPane40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane40.setName("scrollPane40"); // NOI18N

        Farmakologis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Farmakologis.setColumns(20);
        Farmakologis.setRows(3);
        Farmakologis.setName("Farmakologis"); // NOI18N
        Farmakologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FarmakologisKeyPressed(evt);
            }
        });
        scrollPane40.setViewportView(Farmakologis);

        FormInput.add(scrollPane40);
        scrollPane40.setBounds(40, 1320, 360, 43);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("Non Farmakologis :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(490, 1360, 190, 23);

        scrollPane41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane41.setName("scrollPane41"); // NOI18N

        Nonfarmakologis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Nonfarmakologis.setColumns(20);
        Nonfarmakologis.setRows(3);
        Nonfarmakologis.setName("Nonfarmakologis"); // NOI18N
        Nonfarmakologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NonfarmakologisKeyPressed(evt);
            }
        });
        scrollPane41.setViewportView(Nonfarmakologis);

        FormInput.add(scrollPane41);
        scrollPane41.setBounds(490, 1380, 360, 43);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("Lama Perawatan :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(40, 1360, 190, 23);

        scrollPane42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane42.setName("scrollPane42"); // NOI18N

        Lamarawat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Lamarawat.setColumns(20);
        Lamarawat.setRows(3);
        Lamarawat.setName("Lamarawat"); // NOI18N
        Lamarawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamarawatKeyPressed(evt);
            }
        });
        scrollPane42.setViewportView(Lamarawat);

        FormInput.add(scrollPane42);
        scrollPane42.setBounds(40, 1380, 360, 43);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("Target Terukur :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(40, 1420, 190, 23);

        scrollPane43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane43.setName("scrollPane43"); // NOI18N

        Targetukur.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Targetukur.setColumns(20);
        Targetukur.setRows(3);
        Targetukur.setName("Targetukur"); // NOI18N
        Targetukur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TargetukurKeyPressed(evt);
            }
        });
        scrollPane43.setViewportView(Targetukur);

        FormInput.add(scrollPane43);
        scrollPane43.setBounds(40, 1440, 360, 43);

        scrollPane44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane44.setName("scrollPane44"); // NOI18N

        Prognosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Prognosis.setColumns(20);
        Prognosis.setRows(3);
        Prognosis.setName("Prognosis"); // NOI18N
        Prognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PrognosisKeyPressed(evt);
            }
        });
        scrollPane44.setViewportView(Prognosis);

        FormInput.add(scrollPane44);
        scrollPane44.setBounds(490, 1440, 360, 43);

        scrollPane45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane45.setName("scrollPane45"); // NOI18N

        AssesmenBundir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        AssesmenBundir.setColumns(20);
        AssesmenBundir.setRows(5);
        AssesmenBundir.setName("AssesmenBundir"); // NOI18N
        AssesmenBundir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AssesmenBundirKeyPressed(evt);
            }
        });
        scrollPane45.setViewportView(AssesmenBundir);

        FormInput.add(scrollPane45);
        scrollPane45.setBounds(580, 420, 290, 33);

        jLabel68.setText("Assesmen Bunuh Diri :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(450, 420, 120, 23);

        scrollPane46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane46.setName("scrollPane46"); // NOI18N

        FungsiKognitif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        FungsiKognitif.setColumns(20);
        FungsiKognitif.setRows(5);
        FungsiKognitif.setName("FungsiKognitif"); // NOI18N
        FungsiKognitif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsiKognitifKeyPressed(evt);
            }
        });
        scrollPane46.setViewportView(FungsiKognitif);

        FormInput.add(scrollPane46);
        scrollPane46.setBounds(580, 540, 290, 33);

        jLabel69.setText("Risiko Melarikan Diri :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(450, 510, 120, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("Prognosis :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(490, 1420, 190, 23);

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
        tbObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
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
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Penilaian Awal Medis Rawat Inap Psikiatri Dewasa]::");

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
            if(Sequel.menyimpantf("penilaian_medis_ranap_psikiatri_dewasa","?,?,?,?,?,?,?,?,?,?," +
"?,?,?,?,?,?,?,?,?,?," +
"?,?,?,?,?,?,?,?,?,?," +
"?,?,?,?,?,?,?,?,?,?," +
"?,?,?,?,?,?,?,?,?,?," +
"?,?,?,?,?,?,?,?,?,?","No.Rawat",60,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),Anamnesis.getSelectedItem().toString(),
                Hubungan.getText(),KeluhanUtama.getText(),RPS.getText(),RPD.getText(),RPK.getText(),RPO.getText(),
                Alergi.getText(),Penampilan.getText(),Pembicaraan.getText(),Psikomotor.getText(),Sikap.getText(),
                MoodAfek.getText(),FungsiKognitif.getText(),GangguanPersepsi.getText(),Prosespikir.getText(),
                Pengendalianimpuls.getText(),Tilikan.getText(),Keadaan.getSelectedItem().toString(),GCS.getText(),
                Kesadaran.getSelectedItem().toString(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),
                SPO.getText(),BB.getText(),TB.getText(),Kepala.getSelectedItem().toString(),Gigi.getSelectedItem().toString(),
                THT.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),
                Genital.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),Kulit.getSelectedItem().toString(),
                KetFisik.getText(),Penunjang.getText(),
//                Tatalaksana.getText(),
                Konsul.getText(),KesadaranJiwa.getText(),
                TingkahLaku.getText(),PANSS.getText(),AssesmenBundir.getText(),Memori.getText(),Dayanilai.getText(),
                ResikoMelarikanDiri.getText(),Axis1.getText(),Axis2.getText(),Axis3.getText(),Axis4.getText(),Axis5.getText(),Farmakologis.getText(),
                Lamarawat.getText(),Targetukur.getText(),Nonfarmakologis.getText(),Prognosis.getText(),Permasalahan.getText()
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
            Valid.textKosong(RPK,"Riw Pen Fisik & Neurologi");
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
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatri_dewasa.tanggal,"+
                            "  penilaian_medis_ranap_psikiatri_dewasa.kd_dokter," +
"  penilaian_medis_ranap_psikiatri_dewasa.anamnesis," +
"  penilaian_medis_ranap_psikiatri_dewasa.hubungan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keluhan_utama," +
"  penilaian_medis_ranap_psikiatri_dewasa.rps," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpd," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpk," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpo," +
"  penilaian_medis_ranap_psikiatri_dewasa.alergi," +
"  penilaian_medis_ranap_psikiatri_dewasa.penampilan," +
"  penilaian_medis_ranap_psikiatri_dewasa.pembicaraan," +
"  penilaian_medis_ranap_psikiatri_dewasa.psikomotor," +
"  penilaian_medis_ranap_psikiatri_dewasa.sikap," +
"  penilaian_medis_ranap_psikiatri_dewasa.mood," +
"  penilaian_medis_ranap_psikiatri_dewasa.fungsi_kognitif," +
"  penilaian_medis_ranap_psikiatri_dewasa.gangguan_persepsi," +
"  penilaian_medis_ranap_psikiatri_dewasa.proses_pikir," +
"  penilaian_medis_ranap_psikiatri_dewasa.pengendalian_impuls," +
"  penilaian_medis_ranap_psikiatri_dewasa.tilikan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keadaan," +
"  penilaian_medis_ranap_psikiatri_dewasa.gcs," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran," +
"  penilaian_medis_ranap_psikiatri_dewasa.td," +
"  penilaian_medis_ranap_psikiatri_dewasa.nadi," +
"  penilaian_medis_ranap_psikiatri_dewasa.rr," +
"  penilaian_medis_ranap_psikiatri_dewasa.suhu," +
"  penilaian_medis_ranap_psikiatri_dewasa.spo," +
"  penilaian_medis_ranap_psikiatri_dewasa.bb," +
"  penilaian_medis_ranap_psikiatri_dewasa.tb," +
"  penilaian_medis_ranap_psikiatri_dewasa.gigi," +
"  penilaian_medis_ranap_psikiatri_dewasa.kepala," +
"  penilaian_medis_ranap_psikiatri_dewasa.tht," +
"  penilaian_medis_ranap_psikiatri_dewasa.thoraks," +
"  penilaian_medis_ranap_psikiatri_dewasa.abdomen," +
"  penilaian_medis_ranap_psikiatri_dewasa.genital," +
"  penilaian_medis_ranap_psikiatri_dewasa.ekstremitas," +
"  penilaian_medis_ranap_psikiatri_dewasa.kulit," +
"  penilaian_medis_ranap_psikiatri_dewasa.ket_fisik," +
                                
                                
"  penilaian_medis_ranap_psikiatri_dewasa.penunjang," +
//"  penilaian_medis_ranap_psikiatri_dewasa.tata," +
"  penilaian_medis_ranap_psikiatri_dewasa.konsulrujuk," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran_jiwa," +
"  penilaian_medis_ranap_psikiatri_dewasa.tingkah_laku," +
"  penilaian_medis_ranap_psikiatri_dewasa.panss_ec," +
"  penilaian_medis_ranap_psikiatri_dewasa.assesmen_bundir," +
//"  penilaian_medis_ranap_psikiatri_dewasa.mood_afek," +
"  penilaian_medis_ranap_psikiatri_dewasa.memori," +
"  penilaian_medis_ranap_psikiatri_dewasa.daya_nilai," +
"  penilaian_medis_ranap_psikiatri_dewasa.resiko_melarikan_diri," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis2," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis1," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis3," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis4," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis5,"+
"  penilaian_medis_ranap_psikiatri_dewasa.farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.lama_perawatan,"+
"  penilaian_medis_ranap_psikiatri_dewasa.target_terukur,"+
"  penilaian_medis_ranap_psikiatri_dewasa.non_farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.prognosis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.permasalahan, dokter.nm_dokter "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_medis_ranap_psikiatri_dewasa on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatri_dewasa.no_rawat "+
                            "inner join dokter on penilaian_medis_ranap_psikiatri_dewasa.kd_dokter=dokter.kd_dokter where "+
                            "penilaian_medis_ranap_psikiatri_dewasa.tanggal between ? and ? order by penilaian_medis_ranap_psikiatri_dewasa.tanggal");
                }else{
                    ps=koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatri_dewasa.tanggal,"+
                            "  penilaian_medis_ranap_psikiatri_dewasa.kd_dokter," +
"  penilaian_medis_ranap_psikiatri_dewasa.anamnesis," +
"  penilaian_medis_ranap_psikiatri_dewasa.hubungan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keluhan_utama," +
"  penilaian_medis_ranap_psikiatri_dewasa.rps," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpd," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpk," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpo," +
"  penilaian_medis_ranap_psikiatri_dewasa.alergi," +
"  penilaian_medis_ranap_psikiatri_dewasa.penampilan," +
"  penilaian_medis_ranap_psikiatri_dewasa.pembicaraan," +
"  penilaian_medis_ranap_psikiatri_dewasa.psikomotor," +
"  penilaian_medis_ranap_psikiatri_dewasa.sikap," +
"  penilaian_medis_ranap_psikiatri_dewasa.mood," +
"  penilaian_medis_ranap_psikiatri_dewasa.fungsi_kognitif," +
"  penilaian_medis_ranap_psikiatri_dewasa.gangguan_persepsi," +
"  penilaian_medis_ranap_psikiatri_dewasa.proses_pikir," +
"  penilaian_medis_ranap_psikiatri_dewasa.pengendalian_impuls," +
"  penilaian_medis_ranap_psikiatri_dewasa.tilikan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keadaan," +
"  penilaian_medis_ranap_psikiatri_dewasa.gcs," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran," +
"  penilaian_medis_ranap_psikiatri_dewasa.td," +
"  penilaian_medis_ranap_psikiatri_dewasa.nadi," +
"  penilaian_medis_ranap_psikiatri_dewasa.rr," +
"  penilaian_medis_ranap_psikiatri_dewasa.suhu," +
"  penilaian_medis_ranap_psikiatri_dewasa.spo," +
"  penilaian_medis_ranap_psikiatri_dewasa.bb," +
"  penilaian_medis_ranap_psikiatri_dewasa.tb," +
"  penilaian_medis_ranap_psikiatri_dewasa.gigi," +
"  penilaian_medis_ranap_psikiatri_dewasa.kepala," +
"  penilaian_medis_ranap_psikiatri_dewasa.tht," +
"  penilaian_medis_ranap_psikiatri_dewasa.thoraks," +
"  penilaian_medis_ranap_psikiatri_dewasa.abdomen," +
"  penilaian_medis_ranap_psikiatri_dewasa.genital," +
"  penilaian_medis_ranap_psikiatri_dewasa.ekstremitas," +
"  penilaian_medis_ranap_psikiatri_dewasa.kulit," +
"  penilaian_medis_ranap_psikiatri_dewasa.ket_fisik," +
                                
                                
"  penilaian_medis_ranap_psikiatri_dewasa.penunjang," +
//"  penilaian_medis_ranap_psikiatri_dewasa.tata," +
"  penilaian_medis_ranap_psikiatri_dewasa.konsulrujuk," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran_jiwa," +
"  penilaian_medis_ranap_psikiatri_dewasa.tingkah_laku," +
"  penilaian_medis_ranap_psikiatri_dewasa.panss_ec," +
"  penilaian_medis_ranap_psikiatri_dewasa.assesmen_bundir," +
//"  penilaian_medis_ranap_psikiatri_dewasa.mood_afek," +
"  penilaian_medis_ranap_psikiatri_dewasa.memori," +
"  penilaian_medis_ranap_psikiatri_dewasa.daya_nilai," +
"  penilaian_medis_ranap_psikiatri_dewasa.resiko_melarikan_diri," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis2," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis1," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis3," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis4," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis5,"+
"  penilaian_medis_ranap_psikiatri_dewasa.farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.lama_perawatan,"+
"  penilaian_medis_ranap_psikiatri_dewasa.target_terukur,"+
"  penilaian_medis_ranap_psikiatri_dewasa.non_farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.prognosis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.permasalahan, dokter.nm_dokter "+
                            "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                            "inner join penilaian_medis_ranap_psikiatri_dewasa on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatri_dewasa.no_rawat "+
                            "inner join dokter on penilaian_medis_ranap_psikiatri_dewasa.kd_dokter=dokter.kd_dokter where "+
                            "penilaian_medis_ranap_psikiatri_dewasa.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                            "penilaian_medis_ranap_psikiatri_dewasa.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ranap_psikiatri_dewasa.tanggal");
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
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riw Pen Fisik & Neurologi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Napza</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Riwayat Alergi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Penampilan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pembicaraan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Psikomotor</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Sikap</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Mood</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Fungsi Kognitif</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Gangguan Persepsi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Proses Pikir</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Pengendalian Impuls</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Tilikan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Reality Testing Ability</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Keadaan Umum</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>GCS</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Kesadaran</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>TD(mmHg)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>Nadi(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAFA' align='center'>RR(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Suhu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>SpO2</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>BB(Kg)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>TB(cm)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kepala</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Gigi & Mulut</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>THT</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Thoraks</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Abdomen</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Genital & Anus</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Ekstremitas</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kulit</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keterangan Pemeriksaan Fisik</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan Penunjang</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis1/Asesmen</td>"+
//                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tatalaksana</td>"+
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
                                           "<td valign='top'>"+rs.getString("penampilan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pembicaraan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikomotor")+"</td>"+
                                           "<td valign='top'>"+rs.getString("sikap")+"</td>"+
                                           "<td valign='top'>"+rs.getString("mood")+"</td>"+
                                           "<td valign='top'>"+rs.getString("fungsi_kognitif")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gangguan_persepsi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("proses_pikir")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pengendalian_impuls")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tilikan")+"</td>"+
//                                           "<td valign='top'>"+rs.getString("rta")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keadaan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gcs")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kesadaran")+"</td>"+
                                           "<td valign='top'>"+rs.getString("td")+"</td>"+
                                           "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rr")+"</td>"+
                                           "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                                           "<td valign='top'>"+rs.getString("spo")+"</td>"+
                                           "<td valign='top'>"+rs.getString("bb")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tb")+"</td>"+
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
                                           "<td valign='top'>"+rs.getString("Axis1")+"</td>"+
//                                           "<td valign='top'>"+rs.getString("tata")+"</td>"+
                                           "<td valign='top'>"+rs.getString("konsulrujuk")+"</td>"+
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
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.Rawat</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>No.RM</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nama Pasien</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tgl.Lahir</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>J.K.</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kode Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nama Dokter</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tanggal</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Anamnesis</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Hubungan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keluhan Utama</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Riwayat Penyakit Sekarang</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Riwayat Penyakit Dahulu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Riw Pen Fisik & Neurologi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Riwayat Napza</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Riwayat Alergi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Penampilan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pembicaraan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Psikomotor</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Sikap</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Mood</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Fungsi Kognitif</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Gangguan Persepsi</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Proses Pikir</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pengendalian Impuls</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tilikan</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Reality Testing Ability</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keadaan Umum</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>GCS</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kesadaran</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>TD(mmHg)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Nadi(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>RR(x/menit)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Suhu</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>SpO2</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>BB(Kg)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>TB(cm)</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kepala</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Gigi & Mulut</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>THT</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Thoraks</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Abdomen</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Genital & Anus</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Ekstremitas</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Kulit</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Keterangan Pemeriksaan Fisik</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan Penunjang</td>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Axis1/Asesmen</td>"+
//                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Tatalaksana</td>"+
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
                                           "<td valign='top'>"+rs.getString("penampilan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pembicaraan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("psikomotor")+"</td>"+
                                           "<td valign='top'>"+rs.getString("sikap")+"</td>"+
                                           "<td valign='top'>"+rs.getString("mood")+"</td>"+
                                           "<td valign='top'>"+rs.getString("fungsi_kognitif")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gangguan_persepsi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("proses_pikir")+"</td>"+
                                           "<td valign='top'>"+rs.getString("pengendalian_impuls")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tilikan")+"</td>"+
//                                           "<td valign='top'>"+rs.getString("rta")+"</td>"+
                                           "<td valign='top'>"+rs.getString("keadaan")+"</td>"+
                                           "<td valign='top'>"+rs.getString("gcs")+"</td>"+
                                           "<td valign='top'>"+rs.getString("kesadaran")+"</td>"+
                                           "<td valign='top'>"+rs.getString("td")+"</td>"+
                                           "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                                           "<td valign='top'>"+rs.getString("rr")+"</td>"+
                                           "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                                           "<td valign='top'>"+rs.getString("spo")+"</td>"+
                                           "<td valign='top'>"+rs.getString("bb")+"</td>"+
                                           "<td valign='top'>"+rs.getString("tb")+"</td>"+
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
                                           "<td valign='top'>"+rs.getString("Axis1")+"</td>"+
//                                           "<td valign='top'>"+rs.getString("tata")+"</td>"+
                                           "<td valign='top'>"+rs.getString("konsulrujuk")+"</td>"+
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
                                    "\"No.Rawat\";\"No.RM\";\"Nama Pasien\";\"Tgl.Lahir\";\"J.K.\";\"Kode Dokter\";\"Nama Dokter\";\"Tanggal\";\"Anamnesis\";\"Hubungan\";\"Keluhan Utama\";\"Riwayat Penyakit Sekarang\";\"Riwayat Penyakit Dahulu\";\"Riw Pen Fisik & Neurologi\";\"Riwayat Napza\";\"Riwayat Alergi\";\"Penampilan\";\"Pembicaraan\";\"Psikomotor\";\"Sikap\";\"Mood\";\"Fungsi Kognitif\";\"Gangguan Persepsi\";\"Proses Pikir\";\"Pengendalian Impuls\";\"Tilikan\";\"Reality Testing Ability\";\"Keadaan Umum\";\"GCS\";\"Kesadaran\";\"TD(mmHg)\";\"Nadi(x/menit)\";\"RR(x/menit)\";\"Suhu\";\"SpO2\";\"BB(Kg)\";\"TB(cm)\";\"Kepala\";\"Gigi & Mulut\";\"THT\";\"Thoraks\";\"Abdomen\";\"Genital & Anus\";\"Ekstremitas\";\"Kulit\";\"Keterangan Pemeriksaan Fisik\";\"Pemeriksaan Penunjang\";\"Axis1/Asesmen\";\"Tatalaksana\";\"Konsul/Rujuk\"\n"
                                ); 
                                while(rs.next()){
                                    htmlContent.append(
                                        "\""+rs.getString("no_rawat")+"\";\" "+rs.getString("no_rkm_medis")+"\";\""+rs.getString("nm_pasien")+"\";\""+rs.getString("tgl_lahir")+"\";\""+rs.getString("jk")+"\";\""+rs.getString("kd_dokter")+"\";\""+rs.getString("nm_dokter")+"\";\""+rs.getString("tanggal")+"\";\""+rs.getString("anamnesis")+"\";\""+rs.getString("hubungan")+"\";\""+rs.getString("keluhan_utama")+"\";\""+rs.getString("rps")+"\";\""+rs.getString("rpd")+"\";\""+rs.getString("rpk")+"\";\""+rs.getString("rpo")+"\";\""+rs.getString("alergi")+"\";\""+rs.getString("penampilan")+"\";\""+rs.getString("pembicaraan")+"\";\""+rs.getString("psikomotor")+"\";\""+rs.getString("sikap")+"\";\""+rs.getString("mood")+"\";\""+rs.getString("fungsi_kognitif")+"\";\""+rs.getString("gangguan_persepsi")+"\";\""+rs.getString("proses_pikir")+"\";\""+rs.getString("pengendalian_impuls")+"\";\""+rs.getString("tilikan")+"\";\""+"\";\""+rs.getString("keadaan")+"\";\""+rs.getString("gcs")+"\";\""+rs.getString("kesadaran")+"\";\""+rs.getString("td")+"\";\""+rs.getString("nadi")+"\";\""+rs.getString("rr")+"\";\""+rs.getString("suhu")+"\";\""+rs.getString("spo")+"\";\""+rs.getString("bb")+"\";\""+rs.getString("tb")+"\";\""+rs.getString("kepala")+"\";\""+rs.getString("gigi")+"\";\""+rs.getString("tht")+"\";\""+rs.getString("thoraks")+"\";\""+rs.getString("abdomen")+"\";\""+rs.getString("genital")+"\";\""+rs.getString("ekstremitas")+"\";\""+rs.getString("kulit")+"\";\""+rs.getString("ket_fisik")+"\";\""+rs.getString("penunjang")+"\";\""+rs.getString("Axis1")+"\";\""+rs.getString("tata")+"\";\""+rs.getString("konsulrujuk")+"\"\n"
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

//            UNTUK TAMPILAN JASPER


Valid.MyReportqry("rptCetakPenilaianAwalMedisRanapPsikiatrikDewasaXX.jasper","report","::[ Laporan Penilaian Awal Medis Rawat Jalan Psikiatrik ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatri_dewasa.tanggal,"+
                "  penilaian_medis_ranap_psikiatri_dewasa.kd_dokter," +
"  penilaian_medis_ranap_psikiatri_dewasa.anamnesis," +
"  penilaian_medis_ranap_psikiatri_dewasa.hubungan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keluhan_utama," +
"  penilaian_medis_ranap_psikiatri_dewasa.rps," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpd," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpk," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpo," +
"  penilaian_medis_ranap_psikiatri_dewasa.alergi," +
"  penilaian_medis_ranap_psikiatri_dewasa.penampilan," +
"  penilaian_medis_ranap_psikiatri_dewasa.pembicaraan," +
"  penilaian_medis_ranap_psikiatri_dewasa.psikomotor," +
"  penilaian_medis_ranap_psikiatri_dewasa.sikap," +
"  penilaian_medis_ranap_psikiatri_dewasa.mood," +
"  penilaian_medis_ranap_psikiatri_dewasa.fungsi_kognitif," +
"  penilaian_medis_ranap_psikiatri_dewasa.gangguan_persepsi," +
"  penilaian_medis_ranap_psikiatri_dewasa.proses_pikir," +
"  penilaian_medis_ranap_psikiatri_dewasa.pengendalian_impuls," +
"  penilaian_medis_ranap_psikiatri_dewasa.tilikan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keadaan," +
"  penilaian_medis_ranap_psikiatri_dewasa.gcs," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran," +
"  penilaian_medis_ranap_psikiatri_dewasa.td," +
"  penilaian_medis_ranap_psikiatri_dewasa.nadi," +
"  penilaian_medis_ranap_psikiatri_dewasa.rr," +
"  penilaian_medis_ranap_psikiatri_dewasa.suhu," +
"  penilaian_medis_ranap_psikiatri_dewasa.spo," +
"  penilaian_medis_ranap_psikiatri_dewasa.bb," +
"  penilaian_medis_ranap_psikiatri_dewasa.tb," +
"  penilaian_medis_ranap_psikiatri_dewasa.gigi," +
"  penilaian_medis_ranap_psikiatri_dewasa.kepala," +
"  penilaian_medis_ranap_psikiatri_dewasa.tht," +
"  penilaian_medis_ranap_psikiatri_dewasa.thoraks," +
"  penilaian_medis_ranap_psikiatri_dewasa.abdomen," +
"  penilaian_medis_ranap_psikiatri_dewasa.genital," +
"  penilaian_medis_ranap_psikiatri_dewasa.ekstremitas," +
"  penilaian_medis_ranap_psikiatri_dewasa.kulit," +
"  penilaian_medis_ranap_psikiatri_dewasa.ket_fisik," +
"  penilaian_medis_ranap_psikiatri_dewasa.penunjang," +
"  penilaian_medis_ranap_psikiatri_dewasa.konsulrujuk," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran_jiwa," +
"  penilaian_medis_ranap_psikiatri_dewasa.tingkah_laku," +
"  penilaian_medis_ranap_psikiatri_dewasa.panss_ec," +
"  penilaian_medis_ranap_psikiatri_dewasa.assesmen_bundir," +
"  penilaian_medis_ranap_psikiatri_dewasa.memori," +
"  penilaian_medis_ranap_psikiatri_dewasa.daya_nilai," +
"  penilaian_medis_ranap_psikiatri_dewasa.resiko_melarikan_diri," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis2," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis1," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis3," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis4," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis5,"+
"  penilaian_medis_ranap_psikiatri_dewasa.farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.lama_perawatan,"+
"  penilaian_medis_ranap_psikiatri_dewasa.target_terukur,"+
"  penilaian_medis_ranap_psikiatri_dewasa.non_farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.prognosis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.permasalahan, dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ranap_psikiatri_dewasa on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatri_dewasa.no_rawat "+
                "inner join dokter on penilaian_medis_ranap_psikiatri_dewasa.kd_dokter=dokter.kd_dokter where penilaian_medis_ranap_psikiatri_dewasa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        
Valid.MyReportqry("rptCetakPenilaianAwalMedisRanapPsikiatrikDewasaX.jasper","report","::[ Laporan Penilaian Awal Medis Rawat Jalan Psikiatrik ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatri_dewasa.tanggal,"+
                "  penilaian_medis_ranap_psikiatri_dewasa.kd_dokter," +
"  penilaian_medis_ranap_psikiatri_dewasa.anamnesis," +
"  penilaian_medis_ranap_psikiatri_dewasa.hubungan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keluhan_utama," +
"  penilaian_medis_ranap_psikiatri_dewasa.rps," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpd," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpk," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpo," +
"  penilaian_medis_ranap_psikiatri_dewasa.alergi," +
"  penilaian_medis_ranap_psikiatri_dewasa.penampilan," +
"  penilaian_medis_ranap_psikiatri_dewasa.pembicaraan," +
"  penilaian_medis_ranap_psikiatri_dewasa.psikomotor," +
"  penilaian_medis_ranap_psikiatri_dewasa.sikap," +
"  penilaian_medis_ranap_psikiatri_dewasa.mood," +
"  penilaian_medis_ranap_psikiatri_dewasa.fungsi_kognitif," +
"  penilaian_medis_ranap_psikiatri_dewasa.gangguan_persepsi," +
"  penilaian_medis_ranap_psikiatri_dewasa.proses_pikir," +
"  penilaian_medis_ranap_psikiatri_dewasa.pengendalian_impuls," +
"  penilaian_medis_ranap_psikiatri_dewasa.tilikan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keadaan," +
"  penilaian_medis_ranap_psikiatri_dewasa.gcs," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran," +
"  penilaian_medis_ranap_psikiatri_dewasa.td," +
"  penilaian_medis_ranap_psikiatri_dewasa.nadi," +
"  penilaian_medis_ranap_psikiatri_dewasa.rr," +
"  penilaian_medis_ranap_psikiatri_dewasa.suhu," +
"  penilaian_medis_ranap_psikiatri_dewasa.spo," +
"  penilaian_medis_ranap_psikiatri_dewasa.bb," +
"  penilaian_medis_ranap_psikiatri_dewasa.tb," +
"  penilaian_medis_ranap_psikiatri_dewasa.gigi," +
"  penilaian_medis_ranap_psikiatri_dewasa.kepala," +
"  penilaian_medis_ranap_psikiatri_dewasa.tht," +
"  penilaian_medis_ranap_psikiatri_dewasa.thoraks," +
"  penilaian_medis_ranap_psikiatri_dewasa.abdomen," +
"  penilaian_medis_ranap_psikiatri_dewasa.genital," +
"  penilaian_medis_ranap_psikiatri_dewasa.ekstremitas," +
"  penilaian_medis_ranap_psikiatri_dewasa.kulit," +
"  penilaian_medis_ranap_psikiatri_dewasa.ket_fisik," +
"  penilaian_medis_ranap_psikiatri_dewasa.penunjang," +
"  penilaian_medis_ranap_psikiatri_dewasa.konsulrujuk," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran_jiwa," +
"  penilaian_medis_ranap_psikiatri_dewasa.tingkah_laku," +
"  penilaian_medis_ranap_psikiatri_dewasa.panss_ec," +
"  penilaian_medis_ranap_psikiatri_dewasa.assesmen_bundir," +
"  penilaian_medis_ranap_psikiatri_dewasa.memori," +
"  penilaian_medis_ranap_psikiatri_dewasa.daya_nilai," +
"  penilaian_medis_ranap_psikiatri_dewasa.resiko_melarikan_diri," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis2," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis1," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis3," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis4," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis5,"+
"  penilaian_medis_ranap_psikiatri_dewasa.farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.lama_perawatan,"+
"  penilaian_medis_ranap_psikiatri_dewasa.target_terukur,"+
"  penilaian_medis_ranap_psikiatri_dewasa.non_farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.prognosis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.permasalahan, dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ranap_psikiatri_dewasa on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatri_dewasa.no_rawat "+
                "inner join dokter on penilaian_medis_ranap_psikiatri_dewasa.kd_dokter=dokter.kd_dokter where penilaian_medis_ranap_psikiatri_dewasa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        
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
        Valid.pindah2(evt,Sikap,MoodAfek);
    }//GEN-LAST:event_TilikanKeyPressed

    private void GangguanPersepsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GangguanPersepsiKeyPressed
//        Valid.pindah2(evt,MoodAfek,Fungsikognitif);
    }//GEN-LAST:event_GangguanPersepsiKeyPressed

    private void PengendalianimpulsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PengendalianimpulsKeyPressed
        Valid.pindah2(evt,Psikomotor,Sikap);
    }//GEN-LAST:event_PengendalianimpulsKeyPressed

    private void ProsespikirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsespikirKeyPressed
        Valid.pindah2(evt,Pembicaraan,Psikomotor);
    }//GEN-LAST:event_ProsespikirKeyPressed

    private void SikapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SikapKeyPressed
        Valid.pindah2(evt,Pengendalianimpuls,Tilikan);
    }//GEN-LAST:event_SikapKeyPressed

    private void MoodAfekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoodAfekKeyPressed
        Valid.pindah2(evt,Tilikan,GangguanPersepsi);
    }//GEN-LAST:event_MoodAfekKeyPressed

    private void PsikomotorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PsikomotorKeyPressed
        Valid.pindah2(evt,Prosespikir,Pengendalianimpuls);
    }//GEN-LAST:event_PsikomotorKeyPressed

    private void PembicaraanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PembicaraanKeyPressed
//        Valid.pindah2(evt,Gangguanpersepsi,Prosespikir);
    }//GEN-LAST:event_PembicaraanKeyPressed

    private void PenampilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenampilanKeyPressed
//        Valid.pindah2(evt,Alergi,Gangguanpersepsi);
    }//GEN-LAST:event_PenampilanKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        Valid.pindah(evt,Konsul,Anamnesis);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void KonsulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KonsulKeyPressed
//        Valid.pindah2(evt,Tatalaksana,BtnSimpan);
    }//GEN-LAST:event_KonsulKeyPressed

    private void Axis2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis2KeyPressed
//        Valid.pindah2(evt,Penunjang,Tatalaksana);
    }//GEN-LAST:event_Axis2KeyPressed

    private void PenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenunjangKeyPressed
        Valid.pindah2(evt,KetFisik,Axis2);
    }//GEN-LAST:event_PenunjangKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt,Keadaan,GCS);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void KeadaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanKeyPressed
//        Valid.pindah(evt,Fungsikognitif,Kesadaran);
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
        Valid.pindah(evt,RPO,Penampilan);
    }//GEN-LAST:event_AlergiKeyPressed

    private void KesadaranJiwaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranJiwaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesadaranJiwaKeyPressed

    private void TingkahLakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TingkahLakuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TingkahLakuKeyPressed

    private void MemoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MemoriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemoriKeyPressed

    private void DayanilaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DayanilaiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DayanilaiKeyPressed

    private void PANSSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PANSSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PANSSKeyPressed

    private void ResikoMelarikanDiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResikoMelarikanDiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResikoMelarikanDiriKeyPressed

    private void KepalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KepalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KepalaActionPerformed

    private void THTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_THTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_THTActionPerformed

    private void KesadaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KesadaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KesadaranActionPerformed

    private void AbdomenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbdomenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AbdomenActionPerformed

    private void Axis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis1KeyPressed

    private void Axis3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis3KeyPressed

    private void Axis4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis4KeyPressed

    private void Axis5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Axis5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Axis5KeyPressed

    private void PermasalahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PermasalahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PermasalahanKeyPressed

    private void FarmakologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FarmakologisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FarmakologisKeyPressed

    private void NonfarmakologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NonfarmakologisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NonfarmakologisKeyPressed

    private void LamarawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamarawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamarawatKeyPressed

    private void TargetukurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TargetukurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TargetukurKeyPressed

    private void PrognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrognosisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrognosisKeyPressed

    private void AssesmenBundirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AssesmenBundirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AssesmenBundirKeyPressed

    private void FungsiKognitifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FungsiKognitifKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FungsiKognitifKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedisRanapPsikiatrik dialog = new RMPenilaianAwalMedisRanapPsikiatrik(new javax.swing.JFrame(), true);
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
    private widget.TextArea AssesmenBundir;
    private widget.TextArea Axis1;
    private widget.TextArea Axis2;
    private widget.TextArea Axis3;
    private widget.TextArea Axis4;
    private widget.TextArea Axis5;
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
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea Dayanilai;
    private widget.ComboBox Ekstremitas;
    private widget.TextArea Farmakologis;
    private widget.PanelBiasa FormInput;
    private widget.TextArea FungsiKognitif;
    private widget.TextBox GCS;
    private widget.TextArea GangguanPersepsi;
    private widget.ComboBox Genital;
    private widget.ComboBox Gigi;
    private widget.TextBox Hubungan;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.ComboBox Keadaan;
    private widget.TextArea KeluhanUtama;
    private widget.ComboBox Kepala;
    private widget.ComboBox Kesadaran;
    private widget.TextArea KesadaranJiwa;
    private widget.TextArea KetFisik;
    private widget.TextArea Konsul;
    private widget.ComboBox Kulit;
    private widget.Label LCount;
    private widget.TextArea Lamarawat;
    private widget.editorpane LoadHTML;
    private widget.TextArea Memori;
    private javax.swing.JMenuItem MnPenilaianMedis;
    private widget.TextArea MoodAfek;
    private widget.TextBox Nadi;
    private widget.TextBox NmDokter;
    private widget.TextArea Nonfarmakologis;
    private widget.TextArea PANSS;
    private widget.TextArea Pembicaraan;
    private widget.TextArea Penampilan;
    private widget.TextArea Pengendalianimpuls;
    private widget.TextArea Penunjang;
    private widget.TextArea Permasalahan;
    private widget.TextArea Prognosis;
    private widget.TextArea Prosespikir;
    private widget.TextArea Psikomotor;
    private widget.TextArea RPD;
    private widget.TextArea RPK;
    private widget.TextArea RPO;
    private widget.TextArea RPS;
    private widget.TextBox RR;
    private widget.TextArea ResikoMelarikanDiri;
    private widget.TextBox SPO;
    private widget.ScrollPane Scroll;
    private widget.TextArea Sikap;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.ComboBox THT;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Targetukur;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox Thoraks;
    private widget.TextArea Tilikan;
    private widget.TextArea TingkahLaku;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel118;
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
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
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
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel95;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane20;
    private widget.ScrollPane scrollPane21;
    private widget.ScrollPane scrollPane22;
    private widget.ScrollPane scrollPane23;
    private widget.ScrollPane scrollPane24;
    private widget.ScrollPane scrollPane25;
    private widget.ScrollPane scrollPane26;
    private widget.ScrollPane scrollPane27;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane33;
    private widget.ScrollPane scrollPane34;
    private widget.ScrollPane scrollPane35;
    private widget.ScrollPane scrollPane36;
    private widget.ScrollPane scrollPane37;
    private widget.ScrollPane scrollPane39;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane40;
    private widget.ScrollPane scrollPane41;
    private widget.ScrollPane scrollPane42;
    private widget.ScrollPane scrollPane43;
    private widget.ScrollPane scrollPane44;
    private widget.ScrollPane scrollPane45;
    private widget.ScrollPane scrollPane46;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
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
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"
                                + "penilaian_medis_ranap_psikiatri_dewasa.tanggal," +
"  penilaian_medis_ranap_psikiatri_dewasa.kd_dokter," +
"  penilaian_medis_ranap_psikiatri_dewasa.anamnesis," +
"  penilaian_medis_ranap_psikiatri_dewasa.hubungan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keluhan_utama," +
"  penilaian_medis_ranap_psikiatri_dewasa.rps," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpd," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpk," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpo," +
"  penilaian_medis_ranap_psikiatri_dewasa.alergi," +
"  penilaian_medis_ranap_psikiatri_dewasa.penampilan," +
"  penilaian_medis_ranap_psikiatri_dewasa.pembicaraan," +
"  penilaian_medis_ranap_psikiatri_dewasa.psikomotor," +
"  penilaian_medis_ranap_psikiatri_dewasa.sikap," +
"  penilaian_medis_ranap_psikiatri_dewasa.mood," +
"  penilaian_medis_ranap_psikiatri_dewasa.fungsi_kognitif," +
"  penilaian_medis_ranap_psikiatri_dewasa.gangguan_persepsi," +
"  penilaian_medis_ranap_psikiatri_dewasa.proses_pikir," +
"  penilaian_medis_ranap_psikiatri_dewasa.pengendalian_impuls," +
"  penilaian_medis_ranap_psikiatri_dewasa.tilikan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keadaan," +
"  penilaian_medis_ranap_psikiatri_dewasa.gcs," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran," +
"  penilaian_medis_ranap_psikiatri_dewasa.td," +
"  penilaian_medis_ranap_psikiatri_dewasa.nadi," +
"  penilaian_medis_ranap_psikiatri_dewasa.rr," +
"  penilaian_medis_ranap_psikiatri_dewasa.suhu," +
"  penilaian_medis_ranap_psikiatri_dewasa.spo," +
"  penilaian_medis_ranap_psikiatri_dewasa.bb," +
"  penilaian_medis_ranap_psikiatri_dewasa.tb," +
"  penilaian_medis_ranap_psikiatri_dewasa.gigi," +
"  penilaian_medis_ranap_psikiatri_dewasa.kepala," +
"  penilaian_medis_ranap_psikiatri_dewasa.tht," +
"  penilaian_medis_ranap_psikiatri_dewasa.thoraks," +
"  penilaian_medis_ranap_psikiatri_dewasa.abdomen," +
"  penilaian_medis_ranap_psikiatri_dewasa.genital," +
"  penilaian_medis_ranap_psikiatri_dewasa.ekstremitas," +
"  penilaian_medis_ranap_psikiatri_dewasa.kulit," +
"  penilaian_medis_ranap_psikiatri_dewasa.ket_fisik," +
                                
                                
"  penilaian_medis_ranap_psikiatri_dewasa.penunjang," +
//"  penilaian_medis_ranap_psikiatri_dewasa.tata," +
"  penilaian_medis_ranap_psikiatri_dewasa.konsulrujuk," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran_jiwa," +
"  penilaian_medis_ranap_psikiatri_dewasa.tingkah_laku," +
"  penilaian_medis_ranap_psikiatri_dewasa.panss_ec," +
"  penilaian_medis_ranap_psikiatri_dewasa.assesmen_bundir," +
//"  penilaian_medis_ranap_psikiatri_dewasa.mood_afek," +
"  penilaian_medis_ranap_psikiatri_dewasa.memori," +
"  penilaian_medis_ranap_psikiatri_dewasa.daya_nilai," +
"  penilaian_medis_ranap_psikiatri_dewasa.resiko_melarikan_diri," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis2," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis1," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis3," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis4," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis5,"+
"  penilaian_medis_ranap_psikiatri_dewasa.farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.lama_perawatan,"+
"  penilaian_medis_ranap_psikiatri_dewasa.target_terukur,"+
"  penilaian_medis_ranap_psikiatri_dewasa.non_farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.prognosis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.permasalahan, dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_psikiatri_dewasa on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatri_dewasa.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_psikiatri_dewasa.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_psikiatri_dewasa.tanggal between ? and ? order by penilaian_medis_ranap_psikiatri_dewasa.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap_psikiatri_dewasa.tanggal," +
"  penilaian_medis_ranap_psikiatri_dewasa.kd_dokter," +
"  penilaian_medis_ranap_psikiatri_dewasa.anamnesis," +
"  penilaian_medis_ranap_psikiatri_dewasa.hubungan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keluhan_utama," +
"  penilaian_medis_ranap_psikiatri_dewasa.rps," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpd," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpk," +
"  penilaian_medis_ranap_psikiatri_dewasa.rpo," +
"  penilaian_medis_ranap_psikiatri_dewasa.alergi," +
"  penilaian_medis_ranap_psikiatri_dewasa.penampilan," +
"  penilaian_medis_ranap_psikiatri_dewasa.pembicaraan," +
"  penilaian_medis_ranap_psikiatri_dewasa.psikomotor," +
"  penilaian_medis_ranap_psikiatri_dewasa.sikap," +
"  penilaian_medis_ranap_psikiatri_dewasa.mood," +
"  penilaian_medis_ranap_psikiatri_dewasa.fungsi_kognitif," +
"  penilaian_medis_ranap_psikiatri_dewasa.gangguan_persepsi," +
"  penilaian_medis_ranap_psikiatri_dewasa.proses_pikir," +
"  penilaian_medis_ranap_psikiatri_dewasa.pengendalian_impuls," +
"  penilaian_medis_ranap_psikiatri_dewasa.tilikan," +
"  penilaian_medis_ranap_psikiatri_dewasa.keadaan," +
"  penilaian_medis_ranap_psikiatri_dewasa.gcs," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran," +
"  penilaian_medis_ranap_psikiatri_dewasa.td," +
"  penilaian_medis_ranap_psikiatri_dewasa.nadi," +
"  penilaian_medis_ranap_psikiatri_dewasa.rr," +
"  penilaian_medis_ranap_psikiatri_dewasa.suhu," +
"  penilaian_medis_ranap_psikiatri_dewasa.spo," +
"  penilaian_medis_ranap_psikiatri_dewasa.bb," +
"  penilaian_medis_ranap_psikiatri_dewasa.tb," +
"  penilaian_medis_ranap_psikiatri_dewasa.gigi," +
"  penilaian_medis_ranap_psikiatri_dewasa.kepala," +
"  penilaian_medis_ranap_psikiatri_dewasa.tht," +
"  penilaian_medis_ranap_psikiatri_dewasa.thoraks," +
"  penilaian_medis_ranap_psikiatri_dewasa.abdomen," +
"  penilaian_medis_ranap_psikiatri_dewasa.genital," +
"  penilaian_medis_ranap_psikiatri_dewasa.ekstremitas," +
"  penilaian_medis_ranap_psikiatri_dewasa.kulit," +
"  penilaian_medis_ranap_psikiatri_dewasa.ket_fisik," +
                                
                                
"  penilaian_medis_ranap_psikiatri_dewasa.penunjang," +
//"  penilaian_medis_ranap_psikiatri_dewasa.tata," +
"  penilaian_medis_ranap_psikiatri_dewasa.konsulrujuk," +
"  penilaian_medis_ranap_psikiatri_dewasa.kesadaran_jiwa," +
"  penilaian_medis_ranap_psikiatri_dewasa.tingkah_laku," +
"  penilaian_medis_ranap_psikiatri_dewasa.panss_ec," +
"  penilaian_medis_ranap_psikiatri_dewasa.assesmen_bundir," +
//"  penilaian_medis_ranap_psikiatri_dewasa.mood_afek," +
"  penilaian_medis_ranap_psikiatri_dewasa.memori," +
"  penilaian_medis_ranap_psikiatri_dewasa.daya_nilai," +
"  penilaian_medis_ranap_psikiatri_dewasa.resiko_melarikan_diri," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis2," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis1," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis3," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis4," +
"  penilaian_medis_ranap_psikiatri_dewasa.axis5,"+
"  penilaian_medis_ranap_psikiatri_dewasa.farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.lama_perawatan,"+
"  penilaian_medis_ranap_psikiatri_dewasa.target_terukur,"+
"  penilaian_medis_ranap_psikiatri_dewasa.non_farmakologis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.prognosis,"+
"  penilaian_medis_ranap_psikiatri_dewasa.permasalahan,dokter.nm_dokter "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join penilaian_medis_ranap_psikiatri_dewasa on reg_periksa.no_rawat=penilaian_medis_ranap_psikiatri_dewasa.no_rawat "+
                        "inner join dokter on penilaian_medis_ranap_psikiatri_dewasa.kd_dokter=dokter.kd_dokter where "+
                        "penilaian_medis_ranap_psikiatri_dewasa.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or "+
                        "penilaian_medis_ranap_psikiatri_dewasa.kd_dokter like ? or dokter.nm_dokter like ?) order by penilaian_medis_ranap_psikiatri_dewasa.tanggal");
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
//                        Urutan Tampil Data
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("tanggal"),
                        rs.getString("anamnesis"),rs.getString("hubungan"),rs.getString("keluhan_utama"),rs.getString("rps"),rs.getString("rpd"),rs.getString("rpk"),rs.getString("rpo"),rs.getString("alergi"),rs.getString("penampilan"),rs.getString("pembicaraan"),rs.getString("psikomotor"),
                        rs.getString("sikap"),rs.getString("mood"),rs.getString("fungsi_kognitif"),rs.getString("gangguan_persepsi"),rs.getString("proses_pikir"),rs.getString("pengendalian_impuls"),rs.getString("tilikan"),
                        rs.getString("keadaan"),rs.getString("gcs"),rs.getString("kesadaran"),rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("spo"),rs.getString("bb"),
                        rs.getString("tb"),rs.getString("kepala"),rs.getString("gigi"),rs.getString("tht"),rs.getString("thoraks"),rs.getString("abdomen"),rs.getString("genital"),rs.getString("ekstremitas"),
                        rs.getString("kulit"),rs.getString("ket_fisik"),rs.getString("penunjang"),
//                        rs.getString("tata"),
                        rs.getString("konsulrujuk"),rs.getString("kesadaran_jiwa"),rs.getString("tingkah_laku"),
                        rs.getString("panss_ec"),rs.getString("assesmen_bundir"),rs.getString("memori"),rs.getString("daya_nilai"),rs.getString("resiko_melarikan_diri"),rs.getString("axis1"),
                        rs.getString("axis2"),rs.getString("axis3"),rs.getString("axis4"),rs.getString("axis5"),rs.getString("farmakologis"),rs.getString("lama_perawatan"),rs.getString("target_terukur"),
                        rs.getString("non_farmakologis"),rs.getString("prognosis"),rs.getString("permasalahan")
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
        Penampilan.setText("");
        KesadaranJiwa.setText("");
        Sikap.setText("");
        TingkahLaku.setText("");
        Pembicaraan.setText("");
        Psikomotor.setText("");
        Sikap.setText("");
        MoodAfek.setText("");
        Prosespikir.setText("");
        Pengendalianimpuls.setText("");
        Memori.setText("");
        Dayanilai.setText("");
        Tilikan.setText("");
        GangguanPersepsi.setText("");
        PANSS.setText("");
        ResikoMelarikanDiri.setText("");
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
//        Tatalaksana.setText("");
        Permasalahan.setText("");
        Farmakologis.setText("");
        Nonfarmakologis.setText("");
        Lamarawat.setText("");
        Prognosis.setText("");
        Targetukur.setText("");
        Konsul.setText("");
        Permasalahan.setText("");
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
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
//            Undetec
//            Penampilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
//          endundetec
            
            Anamnesis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Hubungan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()); //Neurologi
            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());//Napsa
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            
            Penampilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            Pembicaraan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
                    
            
            
            Psikomotor.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            Sikap.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
//        haruse Mood    MoodAfek.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            MoodAfek.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            FungsiKognitif.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());

            GangguanPersepsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            Prosespikir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            
            Pengendalianimpuls.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            Tilikan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            Keadaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            SPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            Kepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            Gigi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            THT.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            Thoraks.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            
            Abdomen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            Genital.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            Ekstremitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            Kulit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            KetFisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            Penunjang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
//            Tatalaksana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            Konsul.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            KesadaranJiwa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());

            TingkahLaku.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            PANSS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            
            AssesmenBundir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            Memori.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            Dayanilai.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            ResikoMelarikanDiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            
            Axis1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),54).toString());
            Axis2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            Axis3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            Axis4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            Axis5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            
            Farmakologis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            Lamarawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),60).toString());
            Targetukur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            Nonfarmakologis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            Prognosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            Permasalahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
// 64            
            
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
        BtnSimpan.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_dewasa());
        BtnHapus.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_dewasa());
        BtnEdit.setEnabled(akses.getpenilaian_medis_ranap_psikiatri_dewasa());
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
        if(Sequel.queryu2tf("delete from penilaian_medis_ranap_psikiatri_dewasa where no_rawat=?",1,new String[]{
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
        if(Sequel.mengedittf("penilaian_medis_ranap_psikiatri_dewasa","no_rawat=?",
                "no_rawat=?,tanggal=?,kd_dokter=?,anamnesis=?,hubungan=?,keluhan_utama=?,rps=?,rpd=?,rpk=?,rpo=?,"
              + "alergi=?,penampilan=?,pembicaraan=?,psikomotor=?,sikap=?,mood=?,fungsi_kognitif=?,gangguan_persepsi=?,proses_pikir=?,pengendalian_impuls=?,"
              + "tilikan=?,keadaan=?,gcs=?,kesadaran=?,td=?,"
                        + "nadi=?,rr=?,suhu=?,spo=?,bb=?,"
                        + "tb=?,kepala=?,gigi=?,tht=?,thoraks=?,"
                        + "abdomen=?,genital=?,ekstremitas=?,kulit=?,ket_fisik=?,"
                        + "penunjang=?,"
//                        + "tata=?,"
                        + "konsulrujuk=?,kesadaran_jiwa=?,tingkah_laku=?,"
                        + "panss_ec=?,assesmen_bundir=?,memori=?,daya_nilai=?,resiko_melarikan_diri=?,"
                        + "axis1=?,axis2=?,axis3=?,axis4=?,axis5=?,"
                        + "farmakologis=?,lama_perawatan=?,target_terukur=?,non_farmakologis=?,prognosis=?,"
                        + "permasalahan=?",61,new String[]{
               TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),
               KdDokter.getText(),Anamnesis.getSelectedItem().toString(),
               Hubungan.getText(),KeluhanUtama.getText(),RPS.getText(),
               RPD.getText(),RPK.getText(),RPO.getText(),
               Alergi.getText(),Penampilan.getText(),Pembicaraan.getText(),
               Psikomotor.getText(),Sikap.getText(),
               MoodAfek.getText(),FungsiKognitif.getText(),
               GangguanPersepsi.getText(),Prosespikir.getText(),
               Pengendalianimpuls.getText(),Tilikan.getText(),
               Keadaan.getSelectedItem().toString(),GCS.getText(),
               Kesadaran.getSelectedItem().toString(),TD.getText(),Nadi.getText(),
               RR.getText(),Suhu.getText(),
               SPO.getText(),BB.getText(),TB.getText(),
               Kepala.getSelectedItem().toString(),Gigi.getSelectedItem().toString(),
               THT.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),
               Abdomen.getSelectedItem().toString(),
               Genital.getSelectedItem().toString(),
               Ekstremitas.getSelectedItem().toString(),
               Kulit.getSelectedItem().toString(),
               KetFisik.getText(),Penunjang.getText(),
//               Tatalaksana.getText(),
               Konsul.getText(),KesadaranJiwa.getText(),
               TingkahLaku.getText(),PANSS.getText(),AssesmenBundir.getText(),
               Memori.getText(),Dayanilai.getText(),
               ResikoMelarikanDiri.getText(),Axis1.getText(),
               Axis2.getText(),Axis3.getText(),Axis4.getText(),Axis5.getText(),
               Farmakologis.getText(),
               Lamarawat.getText(),Targetukur.getText(),
               Nonfarmakologis.getText(),Prognosis.getText(),
               Permasalahan.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
               tampil();
               emptTeks();
               TabRawat.setSelectedIndex(1);
        }
    }
}
