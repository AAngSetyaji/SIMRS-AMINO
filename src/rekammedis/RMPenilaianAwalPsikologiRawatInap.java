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
public final class RMPenilaianAwalPsikologiRawatInap extends javax.swing.JDialog {
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
    public RMPenilaianAwalPsikologiRawatInap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir","Tanggal Pemeriksaan",
            "Anak ke","Saudara","Tinggal Bersama","Pendidikan", 
            "Penampilan dan Tingkah Laku","Sikap Terhadap Psikolog dan Lingkungan",
            "Komunikasi Pasien","Isi Pikiran","Fungsi Kognitif","Fungsi Emosi","Observasi Psikologi",
            "Dinamika Psikologi","Diagnosis Psikologi","Rencana Intervensi",
            "Kd Dokter","Nama Dokter"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
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
                column.setPreferredWidth(90);
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
//                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
//                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
//                    NIP.requestFocus();
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
        WaktuSelesai = new widget.Tanggal();
        jLabel115 = new widget.Label();
        label12 = new widget.Label();
        WaktuMulai = new widget.Tanggal();
        label15 = new widget.Label();
        label16 = new widget.Label();
        jSeparator4 = new javax.swing.JSeparator();
        label13 = new widget.Label();
        AnakKe = new widget.TextBox();
        label27 = new widget.Label();
        label28 = new widget.Label();
        Dari = new widget.TextBox();
        label29 = new widget.Label();
        Pendidikan = new widget.TextBox();
        label30 = new widget.Label();
        TinggalBersama = new widget.TextBox();
        label31 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        label32 = new widget.Label();
        label33 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        ObservasiPsikolog = new widget.TextArea();
        jLabel116 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        Penampilan = new widget.TextArea();
        scrollPane3 = new widget.ScrollPane();
        SikapPasien = new widget.TextArea();
        jLabel117 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        KomunikasiPasien = new widget.TextArea();
        jLabel118 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        IsiPikiran = new widget.TextArea();
        jLabel119 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        FungsiKognitif = new widget.TextArea();
        jLabel120 = new widget.Label();
        label34 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        DinamikaPsikolog = new widget.TextArea();
        label35 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        KeluhanUtama7 = new widget.TextArea();
        scrollPane9 = new widget.ScrollPane();
        RencanaIntervensi = new widget.TextArea();
        label36 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        FungsiEmosi = new widget.TextArea();
        label37 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        DiagnosisPsikolog = new widget.TextArea();
        NmDokter = new widget.TextBox();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Asesmen Psikologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(750, 1500));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(80, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(120, 120, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(210, 10, 110, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(50, 150, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        TglLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TglLahirActionPerformed(evt);
            }
        });
        FormInput.add(TglLahir);
        TglLahir.setBounds(120, 150, 150, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(120, 180, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(80, 180, 30, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 230, 750, 1);

        label11.setText("Waktu Selesai :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(420, 40, 110, 23);

        WaktuSelesai.setForeground(new java.awt.Color(50, 70, 50));
        WaktuSelesai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023 15:19:04" }));
        WaktuSelesai.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        WaktuSelesai.setName("WaktuSelesai"); // NOI18N
        WaktuSelesai.setOpaque(false);
        WaktuSelesai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WaktuSelesaiKeyPressed(evt);
            }
        });
        FormInput.add(WaktuSelesai);
        WaktuSelesai.setBounds(540, 40, 135, 23);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("a. Penampilan dan Tingkah Laku Pasien");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(40, 290, 190, 23);

        label12.setText("dari");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(620, 120, 20, 23);

        WaktuMulai.setForeground(new java.awt.Color(50, 70, 50));
        WaktuMulai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023 15:19:05" }));
        WaktuMulai.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        WaktuMulai.setName("WaktuMulai"); // NOI18N
        WaktuMulai.setOpaque(false);
        WaktuMulai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WaktuMulaiKeyPressed(evt);
            }
        });
        FormInput.add(WaktuMulai);
        WaktuMulai.setBounds(540, 10, 135, 23);

        label15.setText("Dokter :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(20, 40, 50, 23);

        label16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label16.setText("2. Hasil Wawancara");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(20, 890, 130, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 1400, 750, 1);

        label13.setText("Waktu Pemeriksaan :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label13);
        label13.setBounds(420, 10, 110, 23);

        AnakKe.setHighlighter(null);
        AnakKe.setName("AnakKe"); // NOI18N
        AnakKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnakKeActionPerformed(evt);
            }
        });
        FormInput.add(AnakKe);
        AnakKe.setBounds(570, 120, 40, 23);

        label27.setText("Pendidikan :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label27);
        label27.setBounds(450, 180, 60, 23);

        label28.setText("Anak ke -");
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label28);
        label28.setBounds(510, 120, 50, 23);

        Dari.setHighlighter(null);
        Dari.setName("Dari"); // NOI18N
        Dari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DariActionPerformed(evt);
            }
        });
        FormInput.add(Dari);
        Dari.setBounds(650, 120, 40, 23);

        label29.setText("Urutan Kelahiran :");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label29);
        label29.setBounds(420, 120, 90, 23);

        Pendidikan.setHighlighter(null);
        Pendidikan.setName("Pendidikan"); // NOI18N
        FormInput.add(Pendidikan);
        Pendidikan.setBounds(520, 180, 170, 23);

        label30.setText("Nama :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label30);
        label30.setBounds(20, 120, 90, 23);

        TinggalBersama.setHighlighter(null);
        TinggalBersama.setName("TinggalBersama"); // NOI18N
        FormInput.add(TinggalBersama);
        TinggalBersama.setBounds(520, 150, 170, 23);

        label31.setText("Tinggal Bersama :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label31);
        label31.setBounds(420, 150, 90, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 80, 750, 3);

        label32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label32.setText("E. Rencana Intervensi");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label32);
        label32.setBounds(10, 1290, 120, 23);

        label33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label33.setText("A. Identitas Pasien");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label33);
        label33.setBounds(10, 90, 100, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        ObservasiPsikolog.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ObservasiPsikolog.setColumns(20);
        ObservasiPsikolog.setRows(5);
        ObservasiPsikolog.setName("ObservasiPsikolog"); // NOI18N
        ObservasiPsikolog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObservasiPsikologKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(ObservasiPsikolog);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(40, 910, 690, 100);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("f. Fungsi Emosi Pasien");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(40, 790, 280, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Penampilan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Penampilan.setColumns(20);
        Penampilan.setRows(5);
        Penampilan.setName("Penampilan"); // NOI18N
        Penampilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenampilanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Penampilan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(40, 310, 690, 70);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        SikapPasien.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SikapPasien.setColumns(20);
        SikapPasien.setRows(5);
        SikapPasien.setName("SikapPasien"); // NOI18N
        SikapPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SikapPasienKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(SikapPasien);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(40, 410, 690, 70);

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("b. Sikap Pasien Terhadap Psikolog Pemeriksa dan Lingkungan");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(40, 390, 300, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        KomunikasiPasien.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KomunikasiPasien.setColumns(20);
        KomunikasiPasien.setRows(5);
        KomunikasiPasien.setName("KomunikasiPasien"); // NOI18N
        KomunikasiPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomunikasiPasienKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(KomunikasiPasien);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(40, 510, 690, 70);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("c. Komunikasi Pasien");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(40, 490, 100, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        IsiPikiran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        IsiPikiran.setColumns(20);
        IsiPikiran.setRows(5);
        IsiPikiran.setName("IsiPikiran"); // NOI18N
        IsiPikiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IsiPikiranKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(IsiPikiran);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(40, 610, 690, 70);

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("d. Isi Pikiran (insight, penilaian, dan keyakinan) Pasien");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(40, 590, 280, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        FungsiKognitif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        FungsiKognitif.setColumns(20);
        FungsiKognitif.setRows(5);
        FungsiKognitif.setName("FungsiKognitif"); // NOI18N
        FungsiKognitif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsiKognitifKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(FungsiKognitif);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(40, 710, 690, 70);

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("e. Fungsi Kognitif Pasien");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(40, 690, 280, 23);

        label34.setText("1. Observasi Psikologi");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label34);
        label34.setBounds(0, 270, 130, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        DinamikaPsikolog.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DinamikaPsikolog.setColumns(20);
        DinamikaPsikolog.setRows(5);
        DinamikaPsikolog.setName("DinamikaPsikolog"); // NOI18N
        DinamikaPsikolog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DinamikaPsikologKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(DinamikaPsikolog);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(20, 1040, 710, 120);

        label35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label35.setText("B. Asesmen");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label35);
        label35.setBounds(10, 240, 70, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        KeluhanUtama7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama7.setColumns(20);
        KeluhanUtama7.setRows(5);
        KeluhanUtama7.setName("KeluhanUtama7"); // NOI18N
        KeluhanUtama7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtama7KeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(KeluhanUtama7);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(40, 910, 690, 100);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

        RencanaIntervensi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RencanaIntervensi.setColumns(20);
        RencanaIntervensi.setRows(5);
        RencanaIntervensi.setName("RencanaIntervensi"); // NOI18N
        RencanaIntervensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaIntervensiKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(RencanaIntervensi);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(20, 1310, 710, 70);

        label36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label36.setText("C. Dinamika Psikologi");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label36);
        label36.setBounds(10, 1020, 120, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        FungsiEmosi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        FungsiEmosi.setColumns(20);
        FungsiEmosi.setRows(5);
        FungsiEmosi.setName("FungsiEmosi"); // NOI18N
        FungsiEmosi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsiEmosiKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(FungsiEmosi);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(40, 810, 690, 70);

        label37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label37.setText("D. Diagnosa Psikologi");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label37);
        label37.setBounds(10, 1180, 120, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        DiagnosisPsikolog.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DiagnosisPsikolog.setColumns(20);
        DiagnosisPsikolog.setRows(5);
        DiagnosisPsikolog.setName("DiagnosisPsikolog"); // NOI18N
        DiagnosisPsikolog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosisPsikologKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(DiagnosisPsikolog);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(20, 1200, 710, 70);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(170, 40, 170, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(80, 40, 90, 23);

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
        BtnDokter.setBounds(340, 40, 28, 23);

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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Ahli Bedah");
//        }else if(NmPetugas.getText().trim().equals("")){
//            Valid.textKosong(BtnPetugas,"Petugas");
//        }else if(UraianTindakan.getText().trim().equals("")){
//            Valid.textKosong(UraianTindakan,"Uraian Tindakan");
//        }else if(Diagnosa.getText().trim().equals("")){
//            Valid.textKosong(Diagnosa,"Diagnosa");
//        }else if(Tindakan.getText().trim().equals("")){
//            Valid.textKosong(Tindakan,"Tindakan");
        }else{
            if(Sequel.menyimpantf("asesmen_psikologi_ranap","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat & Waktu Mulai",18,new String[]{
                    TNoRw.getText(),Valid.SetTgl(WaktuMulai.getSelectedItem()+"")+" "+WaktuMulai.getSelectedItem().toString().substring(11,19),
                    Valid.SetTgl(WaktuSelesai.getSelectedItem()+"")+" "+WaktuSelesai.getSelectedItem().toString().substring(11,19),
                    KdDokter.getText(),AnakKe.getText(),Dari.getText(),TinggalBersama.getText(),Pendidikan.getText(),
                    Penampilan.getText(),SikapPasien.getText(),KomunikasiPasien.getText(),IsiPikiran.getText(),FungsiKognitif.getText(),
                    FungsiEmosi.getText(),ObservasiPsikolog.getText(),DinamikaPsikolog.getText(),DiagnosisPsikolog.getText(),RencanaIntervensi.getText()
                })==true){
                tampil();
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
            Valid.textKosong(BtnDokter,"Psikolog");
//        }else if(NmPetugas.getText().trim().equals("")){
//            Valid.textKosong(BtnPetugas,"Petugas");
//        }else if(UraianTindakan.getText().trim().equals("")){
//            Valid.textKosong(UraianTindakan,"Uraian Tindakan");
//        }else if(Diagnosa.getText().trim().equals("")){
//            Valid.textKosong(Diagnosa,"Diagnosa");
//        }else if(Tindakan.getText().trim().equals("")){
//            Valid.textKosong(Tindakan,"Tindakan");
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

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void WaktuSelesaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WaktuSelesaiKeyPressed
        Valid.pindah2(evt,WaktuMulai,BtnDokter);
    }//GEN-LAST:event_WaktuSelesaiKeyPressed

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
"asesmen_psikologi_ranap.no_rawat," +
"  asesmen_psikologi_ranap.tanggal_mulai," +
"  asesmen_psikologi_ranap.tgl_selesai," +
"  asesmen_psikologi_ranap.kd_dokter," +
"  asesmen_psikologi_ranap.anak_ke," +
"  asesmen_psikologi_ranap.saudara," +
"  asesmen_psikologi_ranap.tinggal_bersama," +
"  asesmen_psikologi_ranap.pendidikan," +
"  asesmen_psikologi_ranap.penampilan_tingkah_laku," +
"  asesmen_psikologi_ranap.sikap_pasien," +
"  asesmen_psikologi_ranap.komunikasi_pasien," +
"  asesmen_psikologi_ranap.isi_pikiran," +
"  asesmen_psikologi_ranap.fungsi_kognitif," +
"  asesmen_psikologi_ranap.fungsi_emosi," +
"  asesmen_psikologi_ranap.hasil_wawancara," +
"  asesmen_psikologi_ranap.dinamika_psikolog," +
"  asesmen_psikologi_ranap.diagnosis_psikolog," +
"  asesmen_psikologi_ranap.rencana_intervensi,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join asesmen_psikologi_ranap on reg_periksa.no_rawat=asesmen_psikologi_ranap.no_rawat "+
                        "inner join dokter on asesmen_psikologi_ranap.kd_dokter=dokter.kd_dokter where hasil_tindakan_eswl.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
                "and hasil_tindakan_eswl.mulai='"+tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()+"'",param);
        }
    }//GEN-LAST:event_MnDokumenESWLActionPerformed

    private void WaktuMulaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WaktuMulaiKeyPressed
//       Valid.pindah2(evt,Anjungan,WaktuSelesai);
    }//GEN-LAST:event_WaktuMulaiKeyPressed

    private void AnakKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnakKeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnakKeActionPerformed

    private void DariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DariActionPerformed

    private void ObservasiPsikologKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObservasiPsikologKeyPressed
//        Valid.pindah2(evt,BMI,RPK);
    }//GEN-LAST:event_ObservasiPsikologKeyPressed

    private void PenampilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenampilanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenampilanKeyPressed

    private void SikapPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SikapPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SikapPasienKeyPressed

    private void KomunikasiPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomunikasiPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KomunikasiPasienKeyPressed

    private void IsiPikiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IsiPikiranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_IsiPikiranKeyPressed

    private void FungsiKognitifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FungsiKognitifKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FungsiKognitifKeyPressed

    private void DinamikaPsikologKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DinamikaPsikologKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DinamikaPsikologKeyPressed

    private void KeluhanUtama7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtama7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeluhanUtama7KeyPressed

    private void RencanaIntervensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaIntervensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RencanaIntervensiKeyPressed

    private void FungsiEmosiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FungsiEmosiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FungsiEmosiKeyPressed

    private void DiagnosisPsikologKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosisPsikologKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosisPsikologKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
//        Valid.pindah(evt,WaktuSelesai,BtnPetugas);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TglLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglLahirActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalPsikologiRawatInap dialog = new RMPenilaianAwalPsikologiRawatInap(new javax.swing.JFrame(), true);
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
    private widget.TextBox AnakKe;
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
    private widget.TextBox Dari;
    private widget.TextArea DiagnosisPsikolog;
    private widget.TextArea DinamikaPsikolog;
    private widget.PanelBiasa FormInput;
    private widget.TextArea FungsiEmosi;
    private widget.TextArea FungsiKognitif;
    private widget.TextArea IsiPikiran;
    private widget.TextBox Jk;
    private widget.TextBox KdDokter;
    private widget.TextArea KeluhanUtama7;
    private widget.TextArea KomunikasiPasien;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnDokumenESWL;
    private widget.TextBox NmDokter;
    private widget.TextArea ObservasiPsikolog;
    private widget.TextArea Penampilan;
    private widget.TextBox Pendidikan;
    private widget.TextArea RencanaIntervensi;
    private widget.ScrollPane Scroll;
    private widget.TextArea SikapPasien;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TglLahir;
    private widget.TextBox TinggalBersama;
    private widget.Tanggal WaktuMulai;
    private widget.Tanggal WaktuSelesai;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
//        System.out.println("Simpan");
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"
                                + "asesmen_psikologi_ranap.no_rawat," +
"  asesmen_psikologi_ranap.tanggal_mulai," +
//"  asesmen_psikologi_ranap.tgl_selesai," +
"  asesmen_psikologi_ranap.kd_dokter," +
"  asesmen_psikologi_ranap.anak_ke," +
"  asesmen_psikologi_ranap.saudara," +
"  asesmen_psikologi_ranap.tinggal_bersama," +
"  asesmen_psikologi_ranap.pendidikan," +
"  asesmen_psikologi_ranap.penampilan_tingkah_laku," +
"  asesmen_psikologi_ranap.sikap_pasien," +
"  asesmen_psikologi_ranap.komunikasi_pasien," +
"  asesmen_psikologi_ranap.isi_pikiran," +
"  asesmen_psikologi_ranap.fungsi_kognitif," +
"  asesmen_psikologi_ranap.fungsi_emosi," +
"  asesmen_psikologi_ranap.hasil_wawancara," +
"  asesmen_psikologi_ranap.dinamika_psikolog," +
"  asesmen_psikologi_ranap.diagnosis_psikolog," +
"  asesmen_psikologi_ranap.rencana_intervensi,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join asesmen_psikologi_ranap on reg_periksa.no_rawat=asesmen_psikologi_ranap.no_rawat "+
                        "inner join dokter on asesmen_psikologi_ranap.kd_dokter=dokter.kd_dokter "+
                        "where "+
                "asesmen_psikologi_ranap.tanggal_mulai between ? and ? order by asesmen_psikologi_ranap.tanggal_mulai");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"
                                + "asesmen_psikologi_ranap.no_rawat," +
"  asesmen_psikologi_ranap.tanggal_mulai," +
//"  asesmen_psikologi_ranap.tgl_selesai," +
"  asesmen_psikologi_ranap.kd_dokter," +
"  asesmen_psikologi_ranap.anak_ke," +
"  asesmen_psikologi_ranap.saudara," +
"  asesmen_psikologi_ranap.tinggal_bersama," +
"  asesmen_psikologi_ranap.pendidikan," +
"  asesmen_psikologi_ranap.penampilan_tingkah_laku," +
"  asesmen_psikologi_ranap.sikap_pasien," +
"  asesmen_psikologi_ranap.komunikasi_pasien," +
"  asesmen_psikologi_ranap.isi_pikiran," +
"  asesmen_psikologi_ranap.fungsi_kognitif," +
"  asesmen_psikologi_ranap.fungsi_emosi," +
"  asesmen_psikologi_ranap.hasil_wawancara," +
"  asesmen_psikologi_ranap.dinamika_psikolog," +
"  asesmen_psikologi_ranap.diagnosis_psikolog," +
"  asesmen_psikologi_ranap.rencana_intervensi,"+
                        "dokter.nm_dokter from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                        "inner join asesmen_psikologi_ranap on reg_periksa.no_rawat=asesmen_psikologi_ranap.no_rawat "+
                        "inner join dokter on asesmen_psikologi_ranap.kd_dokter=dokter.kd_dokter where "+
//                        "asesmen_psikologi_ranap.tanggal_mulai between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? ");
                
                "asesmen_psikologi_ranap.tanggal_mulai between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or asesmen_psikologi_ranap.kd_dokter like ? or dokter.nm_dokter like ?) "+
                    "order by asesmen_psikologi_ranap.tanggal_mulai ");
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("jk"),rs.getString("tgl_lahir"),
                        rs.getString("tanggal_mulai"),rs.getString("anak_ke"),rs.getString("saudara"),rs.getString("tinggal_bersama"),
                        rs.getString("pendidikan"),rs.getString("penampilan_tingkah_laku"),rs.getString("sikap_pasien"),rs.getString("komunikasi_pasien"),rs.getString("isi_pikiran"),
                        rs.getString("fungsi_kognitif"),rs.getString("fungsi_emosi"),rs.getString("hasil_wawancara"),rs.getString("dinamika_psikolog"),rs.getString("diagnosis_psikolog"),
                        rs.getString("rencana_intervensi"),rs.getString("kd_dokter"),rs.getString("nm_dokter")
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
        TNoRM.setText("");
        TNoRw.setText("");
        TPasien.setText("");
        TglLahir.setText("");
        Jk.setText("");
        AnakKe.setText("");
        Dari.setText("");
        TinggalBersama.setText("");
        Pendidikan.setText("");
        Penampilan.setText("");
        SikapPasien.setText("");
        KomunikasiPasien.setText("");
        IsiPikiran.setText("");
        FungsiKognitif.setText("");
        FungsiEmosi.setText("");
        ObservasiPsikolog.setText("");
        DinamikaPsikolog.setText("");
        DiagnosisPsikolog.setText("");
        RencanaIntervensi.setText("");
        WaktuMulai.setDate(new Date());
        WaktuSelesai.setDate(new Date());
       
        TabRawat.setSelectedIndex(0);
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl2(WaktuMulai,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());

            AnakKe.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); 
            Dari.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); 
            TinggalBersama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            Pendidikan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            Penampilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            SikapPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            KomunikasiPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            IsiPikiran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            FungsiKognitif.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            FungsiEmosi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            ObservasiPsikolog.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            DinamikaPsikolog.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            DiagnosisPsikolog.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            RencanaIntervensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
//            Anjungan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            Valid.SetTgl2(WaktuSelesai,tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
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
        if(Sequel.queryu2tf("delete from asesmen_psikologi_ranap where no_rawat=? and tanggal_mulai=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if(Sequel.mengedittf("asesmen_psikologi_ranap","no_rawat=? and tanggal_mulai=?","no_rawat=?,tanggal_mulai=?,tgl_selesai=?,kd_dokter=?,anak_ke=?,saudara=?,"+
                "tinggal_bersama=?,pendidikan=?,penampilan_tingkah_laku=?,sikap_pasien=?,komunikasi_pasien=?,isi_pikiran=?,fungsi_kognitif=?,"+
                "fungsi_emosi=?,hasil_wawancara=?,dinamika_psikolog=?,diagnosis_psikolog=?,rencana_intervensi=?",20,new String[]{
                    
//                    20
                TNoRw.getText(),
                Valid.SetTgl(WaktuMulai.getSelectedItem()+"")+" "+WaktuMulai.getSelectedItem().toString().substring(11,19),
                Valid.SetTgl(WaktuSelesai.getSelectedItem()+"")+" "+WaktuSelesai.getSelectedItem().toString().substring(11,19),
                KdDokter.getText(), 
                AnakKe.getText(),Dari.getText(),TinggalBersama.getText(),Pendidikan.getText(),Penampilan.getText(),
                
                SikapPasien.getText(), 
                KomunikasiPasien.getText(),IsiPikiran.getText(),FungsiKognitif.getText(),FungsiEmosi.getText(),
                ObservasiPsikolog.getText(),DinamikaPsikolog.getText(),
                DiagnosisPsikolog.getText(),RencanaIntervensi.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()
                     
//                TNoRw.getText(),
//                    Valid.SetTgl(WaktuMulai.getSelectedItem()+"")+" "+WaktuMulai.getSelectedItem().toString().substring(11,19),
//                    Valid.SetTgl(WaktuSelesai.getSelectedItem()+"")+" "+WaktuSelesai.getSelectedItem().toString().substring(11,19),
//                    KdDokter.getText(),AnakKe.getText(),Dari.getText(),TinggalBersama.getText(),Pendidikan.getText(),
//                    Penampilan.getText(),SikapPasien.getText(),KomunikasiPasien.getText(),IsiPikiran.getText(),FungsiKognitif.getText(),
//                    FungsiEmosi.getText(),ObservasiPsikolog.getText(),DinamikaPsikolog.getText(),DiagnosisPsikolog.getText(),RencanaIntervensi.getText()
                        
                        
            })==true){
               emptTeks();
               tampil();
//               TabRawat.setSelectedIndex(1);
        }
    }
}
