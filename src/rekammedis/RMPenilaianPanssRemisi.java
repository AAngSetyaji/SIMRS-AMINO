/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import widget.PanelJudul;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianPanssRemisi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private String finger="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianPanssRemisi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
//            Untuk Tabel Head My Note
            "No.Rawat","No.R.M.","Nama Pasien","Tgl.Lahir","JK","Tanggal","P1","Skor P1","P2","Skor P2",
            "P3","Skor P3","NI","Skor NI","N4","Skor N4","N6","Skor N6",
            "G5","Skor G5","G9","Skor G9","TOTAL SKOR","PERSEN PENINGKATAN SKOR","NIM",
            "Dokter",
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(80);
            }else if(i==7){
                column.setPreferredWidth(70);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setPreferredWidth(70);
            }else if(i==12){
                column.setPreferredWidth(80);
            }else if(i==13){
                column.setPreferredWidth(70);
            }else if(i==14){
                column.setPreferredWidth(80);
            }else if(i==15){
                column.setPreferredWidth(70);
            }else if(i==16){
                column.setPreferredWidth(80);
            }else if(i==17){
                column.setPreferredWidth(70);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(70);
            }else if(i==20){
                column.setPreferredWidth(80);
            }else if(i==21){
                column.setPreferredWidth(70);
            }else if(i==22){
                column.setPreferredWidth(80);
            }else if(i==23){
                column.setPreferredWidth(70);
            }else if(i==24){
                column.setPreferredWidth(80);
            }else if(i==25){
                column.setPreferredWidth(80);
            }
        }
//        Untuk Menampilkan di Tabel My Note
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));
        NmDokter.setDocument(new batasInput((byte)50).getKata(NmDokter));
        textBox1.setDocument(new batasInput((int)100).getKata(textBox1));
//        FaktorPencegahan.setDocument(new batasInput((int)500).getKata(FaktorPencegahan));
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
                KdDokter.requestFocus();
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
        
        ChkInput.setSelected(false);
        isForm();
        jam();
        
        
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianTambahanMelarikanDiri = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        LoadHTML = new widget.editorpane();
        P1 = new javax.swing.ButtonGroup();
        P2 = new javax.swing.ButtonGroup();
        P3 = new javax.swing.ButtonGroup();
        NI = new javax.swing.ButtonGroup();
        N4 = new javax.swing.ButtonGroup();
        N6 = new javax.swing.ButtonGroup();
        G5 = new javax.swing.ButtonGroup();
        G9 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel31 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel57 = new widget.Label();
        jLabel220 = new widget.Label();
        radioButton1 = new widget.RadioButton();
        radioButton2 = new widget.RadioButton();
        radioButton3 = new widget.RadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        radioButton4 = new widget.RadioButton();
        jLabel253 = new widget.Label();
        radioButton5 = new widget.RadioButton();
        radioButton6 = new widget.RadioButton();
        radioButton7 = new widget.RadioButton();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel254 = new widget.Label();
        jLabel255 = new widget.Label();
        jLabel256 = new widget.Label();
        radioButton8 = new widget.RadioButton();
        radioButton9 = new widget.RadioButton();
        radioButton10 = new widget.RadioButton();
        radioButton11 = new widget.RadioButton();
        radioButton12 = new widget.RadioButton();
        radioButton13 = new widget.RadioButton();
        radioButton14 = new widget.RadioButton();
        jLabel257 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel258 = new widget.Label();
        jLabel259 = new widget.Label();
        radioButton15 = new widget.RadioButton();
        radioButton16 = new widget.RadioButton();
        radioButton17 = new widget.RadioButton();
        radioButton18 = new widget.RadioButton();
        radioButton19 = new widget.RadioButton();
        radioButton20 = new widget.RadioButton();
        radioButton21 = new widget.RadioButton();
        jLabel260 = new widget.Label();
        textBox1 = new widget.TextBox();
        jLabel1 = new javax.swing.JLabel();
        textBox2 = new widget.TextBox();
        textBox3 = new widget.TextBox();
        jLabel62 = new widget.Label();
        jLabel262 = new widget.Label();
        jLabel263 = new widget.Label();
        radioButton22 = new widget.RadioButton();
        radioButton23 = new widget.RadioButton();
        radioButton24 = new widget.RadioButton();
        radioButton25 = new widget.RadioButton();
        radioButton26 = new widget.RadioButton();
        radioButton27 = new widget.RadioButton();
        radioButton28 = new widget.RadioButton();
        jLabel264 = new widget.Label();
        textBox4 = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel265 = new widget.Label();
        jLabel266 = new widget.Label();
        radioButton29 = new widget.RadioButton();
        radioButton30 = new widget.RadioButton();
        radioButton31 = new widget.RadioButton();
        radioButton32 = new widget.RadioButton();
        radioButton33 = new widget.RadioButton();
        radioButton34 = new widget.RadioButton();
        radioButton35 = new widget.RadioButton();
        textBox5 = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel268 = new widget.Label();
        jLabel269 = new widget.Label();
        radioButton36 = new widget.RadioButton();
        radioButton37 = new widget.RadioButton();
        radioButton38 = new widget.RadioButton();
        radioButton39 = new widget.RadioButton();
        radioButton40 = new widget.RadioButton();
        radioButton41 = new widget.RadioButton();
        radioButton42 = new widget.RadioButton();
        textBox6 = new widget.TextBox();
        jLabel65 = new widget.Label();
        jLabel270 = new widget.Label();
        jLabel271 = new widget.Label();
        radioButton43 = new widget.RadioButton();
        radioButton44 = new widget.RadioButton();
        radioButton45 = new widget.RadioButton();
        radioButton46 = new widget.RadioButton();
        radioButton47 = new widget.RadioButton();
        radioButton48 = new widget.RadioButton();
        radioButton49 = new widget.RadioButton();
        textBox7 = new widget.TextBox();
        jLabel272 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel273 = new widget.Label();
        jLabel274 = new widget.Label();
        radioButton50 = new widget.RadioButton();
        radioButton51 = new widget.RadioButton();
        radioButton52 = new widget.RadioButton();
        radioButton53 = new widget.RadioButton();
        radioButton54 = new widget.RadioButton();
        radioButton55 = new widget.RadioButton();
        radioButton56 = new widget.RadioButton();
        textBox8 = new widget.TextBox();
        jLabel275 = new widget.Label();
        textBox9 = new widget.TextBox();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jSeparator27 = new javax.swing.JSeparator();
        jSeparator28 = new javax.swing.JSeparator();
        jSeparator29 = new javax.swing.JSeparator();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianTambahanMelarikanDiri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianTambahanMelarikanDiri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianTambahanMelarikanDiri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setText("Formulir Penilaian PANSS REMISI");
        MnPenilaianTambahanMelarikanDiri.setName("MnPenilaianTambahanMelarikanDiri"); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setPreferredSize(new java.awt.Dimension(305, 26));
        MnPenilaianTambahanMelarikanDiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianTambahanMelarikanDiriActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianTambahanMelarikanDiri);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian PANSS REMISI ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 402));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.setPreferredScrollableViewportSize(new java.awt.Dimension(450, 500));
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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-10-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-10-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 456));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 500));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 1000));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-10-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(84, 40, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 80, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(178, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(243, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(308, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(373, 40, 23, 23);

        jLabel18.setText("Dokter");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(474, 40, 94, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmDokterActionPerformed(evt);
            }
        });
        FormInput.add(NmDokter);
        NmDokter.setBounds(570, 40, 187, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("ALt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
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
        BtnDokter.setBounds(761, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 810, 1);

        jLabel31.setText("Total Skor :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(630, 880, 60, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 70, 810, 1);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("POSITIVE AND NEGATIVE SYNDROMES SCALE QUICKSCORE FORM");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 100, 810, 50);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("Dasar penilaian : Isi pikiran yang dideskripsikan dalam wawancara dan pengaruhnya terhadap relasi sosial dan prilaku");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(40, 210, 580, 23);

        P1.add(radioButton1);
        radioButton1.setText("Tidak Ada");
        radioButton1.setToolTipText("Definisi tidak dipenuhi");
        radioButton1.setName("radioButton1"); // NOI18N
        radioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton1ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton1);
        radioButton1.setBounds(40, 230, 81, 16);

        P1.add(radioButton2);
        radioButton2.setText("Minimal");
        radioButton2.setToolTipText("Patologis diragukan mungkin suatu ujung ekstrim dari batasan normal");
        radioButton2.setName("radioButton2"); // NOI18N
        radioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton2ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton2);
        radioButton2.setBounds(130, 230, 53, 16);

        P1.add(radioButton3);
        radioButton3.setText("Ringan");
        radioButton3.setToolTipText("Ada satu atau dua waham yang samar-samar, tidak terkristalisasi, dan tidak. bertahan. Waham tidak mempengaruhi proses pikir, relasi sosial atau perilaku.");
        radioButton3.setName("radioButton3"); // NOI18N
        radioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton3ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton3);
        radioButton3.setBounds(210, 230, 52, 16);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 870, 810, 40);

        P1.add(radioButton4);
        radioButton4.setText("Sedang");
        radioButton4.setToolTipText("Adanya rangkaian waham yang bentuknya kurang jelas dan tidak stabil atau beberapa waham yang berbentuk jelas, yang kadang-kadang mempengaruhi proses pikir, relasi sosial atau perilaku.");
        radioButton4.setName("radioButton4"); // NOI18N
        radioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton4ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton4);
        radioButton4.setBounds(290, 230, 55, 16);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("Dasar penilaian : Proses pikir kognitif verbal yang diamati  selama wawancara.");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(40, 300, 380, 23);

        P1.add(radioButton5);
        radioButton5.setText("Agak Berat");
        radioButton5.setToolTipText("Adanya beberapa waham yang berbentuk jelas, yang berbentuk jelas yang dipertahankan dan kadang - kadang mempengaruhi proses pikir, relasi sosial dan prilaku.");
        radioButton5.setName("radioButton5"); // NOI18N
        radioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton5ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton5);
        radioButton5.setBounds(360, 230, 72, 16);

        P1.add(radioButton6);
        radioButton6.setText("Berat");
        radioButton6.setToolTipText("Adanya suatu susunan waham yang stabil, yang. terkristalisasi, mungkin sistematik, dipertahankan, dan jelas mempengaruhi proses pikir. relasi sosial dan perilaku.");
        radioButton6.setName("radioButton6"); // NOI18N
        radioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton6ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton6);
        radioButton6.setBounds(450, 230, 45, 16);

        P1.add(radioButton7);
        radioButton7.setText("Sangat Berat");
        radioButton7.setToolTipText("Adanya suatu susunan waham yang stabil, sangat sistemik, atau sangat banyak, dan yang mendominasi bidang (facei) utama kehidupan pasien. Sering kali mengakibatkan tindakan yang tidak serasi dan tidak bertanggung jawab, yang bahkan membahayakan keamanan pasien atau orang lain");
        radioButton7.setName("radioButton7"); // NOI18N
        radioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton7ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton7);
        radioButton7.setBounds(510, 230, 90, 16);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("P2. KEKACAUAN PROSES PIKIR (CONCEPTUALDISORGANIZATION)");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(20, 250, 330, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("P1. WAHAM");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(20, 170, 80, 23);

        jLabel254.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel254.setText("Keyakinan yang tidak mempunyai dasar, tidak realistik dan aneh (idiosinkratik).");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(40, 190, 380, 23);

        jLabel255.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel255.setText("Proses pikir ditandai oleh putusnya tahapan penyampaian maksud, misalnya sirkumstansial, tangensial, asosiasi longgar");
        jLabel255.setName("jLabel255"); // NOI18N
        FormInput.add(jLabel255);
        jLabel255.setBounds(40, 270, 580, 14);

        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("tidak berurutan, ketidaklogisan yang parah, atau putusnyaarus pikir.");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(40, 280, 340, 23);

        P2.add(radioButton8);
        radioButton8.setText("Tidak Ada");
        radioButton8.setToolTipText("Definisi tidak dipenuhi");
        radioButton8.setName("radioButton8"); // NOI18N
        radioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton8ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton8);
        radioButton8.setBounds(40, 320, 66, 16);

        P2.add(radioButton9);
        radioButton9.setText("Minimal");
        radioButton9.setToolTipText("Patologis diragukan, mungkin suatu ujung ekstrim dari batasan normal.");
        radioButton9.setName("radioButton9"); // NOI18N
        radioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton9ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton9);
        radioButton9.setBounds(130, 320, 53, 16);

        P2.add(radioButton10);
        radioButton10.setText("Ringan");
        radioButton10.setToolTipText("Proses pikir sirkumstansial, tangensial atau paralogikal. Adanya kesulitan dalam mengarahkan pikiran ke tujuan dan kadang-kadang asosiasi longgar dapat dijumpai di bawah tekanan");
        radioButton10.setName("radioButton10"); // NOI18N
        radioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton10ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton10);
        radioButton10.setBounds(210, 320, 81, 16);

        P2.add(radioButton11);
        radioButton11.setText("Sedang");
        radioButton11.setToolTipText("mampu memusatkan pikiran bila komunikasi singkat dan terstruktur, tetapi menjadi longgar atau tidak relevan. bila menghadapi komunikasi yang lebih kompleks atau bila di bawah tekanan minimal.");
        radioButton11.setName("radioButton11"); // NOI18N
        radioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton11ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton11);
        radioButton11.setBounds(290, 320, 55, 16);

        P2.add(radioButton12);
        radioButton12.setText("Agak Berat");
        radioButton12.setToolTipText("Secara umum mengalami kesulitan dalam menata pikiran yang terbukti dalam bentuk sering tidak relevan, tidak ada hubungan, atau asosiasi longgar bahkan walaupun tanpa tekanan.");
        radioButton12.setName("radioButton12"); // NOI18N
        radioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton12ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton12);
        radioButton12.setBounds(360, 320, 72, 16);

        P2.add(radioButton13);
        radioButton13.setText("Berat");
        radioButton13.setToolTipText("proses pikir (thinking) sangat menyimpang dan pada dasamya (interally) tidak konsisten, mengakibatkan tidak relevan yang parah dan kekacauan proses pikir, yang hampir terjadi terus menerus.");
        radioButton13.setName("radioButton13"); // NOI18N
        radioButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton13ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton13);
        radioButton13.setBounds(450, 320, 45, 16);

        P2.add(radioButton14);
        radioButton14.setText("Sangat Berat");
        radioButton14.setToolTipText("Pikiran (thought) sangat kacau sehingga menjadi inkoheren Asosiasi longgar yang sangat jelas, yang mengakibakan kegagalan total.dalam komunikasi, misalnya \"word-salad atau mutisme.");
        radioButton14.setName("radioButton14"); // NOI18N
        radioButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton14ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton14);
        radioButton14.setBounds(510, 320, 82, 16);

        jLabel257.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel257.setText("atau keluarga.");
        jLabel257.setName("jLabel257"); // NOI18N
        FormInput.add(jLabel257);
        jLabel257.setBounds(40, 400, 580, 20);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("P3. PRILAKU HALUSINASI");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(20, 340, 330, 23);

        jLabel258.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel258.setText("Laporan secara verbal atau prilaku yang menunjukan persepsi yang tidak dirangsang oleh stimulasi luar. Dapat terjadi");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(40, 360, 580, 14);

        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel259.setText("halusinasi pendengaran, penglihatan, penciuman atau somatik.");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(40, 370, 340, 23);

        P3.add(radioButton15);
        radioButton15.setText("Tidak Ada");
        radioButton15.setToolTipText("Definisi tidak dipenuhi");
        radioButton15.setName("radioButton15"); // NOI18N
        radioButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton15ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton15);
        radioButton15.setBounds(40, 420, 66, 16);

        P3.add(radioButton16);
        radioButton16.setText("Minimal");
        radioButton16.setToolTipText("Patologis diragukan, mungkin suatu ujung ekstrim dari batasan normal");
        radioButton16.setName("radioButton16"); // NOI18N
        radioButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton16ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton16);
        radioButton16.setBounds(130, 420, 53, 16);

        P3.add(radioButton17);
        radioButton17.setText("Ringan");
        radioButton17.setToolTipText("Satu atau dua halusinasi yang jelas tetapi jarang timbul, atau beberapa abnormalitas persepsi yang samar-samar lainnya yang tidak mengakibatkan penyimpangan (distorsi) proses pikir atau perilaku.");
        radioButton17.setName("radioButton17"); // NOI18N
        radioButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton17ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton17);
        radioButton17.setBounds(210, 420, 81, 16);

        P3.add(radioButton18);
        radioButton18.setText("Sedang");
        radioButton18.setToolTipText("Sering ada halusinasi tetapi tidak terus menerus, dan proses. Pikir secara perilaku pasien hanya sedikit terpengaruh.");
        radioButton18.setName("radioButton18"); // NOI18N
        radioButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton18ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton18);
        radioButton18.setBounds(290, 420, 55, 16);

        P3.add(radioButton19);
        radioButton19.setText("Agak Berat");
        radioButton19.setToolTipText("Halusinasi sering, dapat meliputi lebih dari satu organ sensoris dan cenderung menyimpangkan. proses pikir dan/atau mengacaukan perilaku. Pasien dapat memiliki interpretasi bersifat waham atas pengalamannya ini dan bereaksi terhadapnya secara emosional, serta kadang-kadang juga secara verbal.");
        radioButton19.setName("radioButton19"); // NOI18N
        radioButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton19ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton19);
        radioButton19.setBounds(360, 420, 72, 16);

        P3.add(radioButton20);
        radioButton20.setText("Berat");
        radioButton20.setToolTipText("Halusinasi hampir terus menerus ada, mengakibatkan kakacauan berat pada proses pikir dan perilaku. Pasien menganggapnya sebagai persepsi nyata dan fungsinya terganggu oleh seringnya bereaksi secara emosional dan verbal terhadapnya.");
        radioButton20.setName("radioButton20"); // NOI18N
        radioButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton20ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton20);
        radioButton20.setBounds(450, 420, 45, 16);

        P3.add(radioButton21);
        radioButton21.setText("Sangat Berat");
        radioButton21.setToolTipText("Pasien hampir secara total mengalami preokupasi dengan halusinasi, yang jelas mendominasi proses pikir dan perilaku. Halusinasi diikuti interpretasi bersifat waham yang kaku dan memacu timbulnya respons verbal dan perilaku, termasuk kepatuhan lerhadap halusinasi perintah.");
        radioButton21.setName("radioButton21"); // NOI18N
        radioButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton21ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton21);
        radioButton21.setBounds(510, 420, 82, 16);

        jLabel260.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel260.setText("Dasar penilaian : Laporan verbal dan manifestasi fisik selama wawancara, dan juga prilaku yang dilaporkan oleh perawat");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(40, 390, 580, 20);

        textBox1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox1.setText("Skor");
        textBox1.setName("textBox1"); // NOI18N
        textBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox1ActionPerformed(evt);
            }
        });
        FormInput.add(textBox1);
        textBox1.setBounds(730, 200, 50, 24);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Skor");
        jLabel1.setName("jLabel1"); // NOI18N
        FormInput.add(jLabel1);
        jLabel1.setBounds(730, 150, 50, 20);

        textBox2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox2.setText("Skor");
        textBox2.setName("textBox2"); // NOI18N
        textBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox2ActionPerformed(evt);
            }
        });
        FormInput.add(textBox2);
        textBox2.setBounds(730, 290, 50, 24);

        textBox3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox3.setText("Skor");
        textBox3.setName("textBox3"); // NOI18N
        textBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox3ActionPerformed(evt);
            }
        });
        FormInput.add(textBox3);
        textBox3.setBounds(730, 380, 50, 24);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("NI. AFEK TUMPUL");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(20, 443, 330, 20);

        jLabel262.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel262.setText("Berkurangnya respons emosional yang ditandai oleh berkurangnya ekspresi wajah, gelobang (modulation) perasaan dan ");
        jLabel262.setName("jLabel262"); // NOI18N
        FormInput.add(jLabel262);
        jLabel262.setBounds(40, 460, 580, 14);

        jLabel263.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel263.setText("gerak gerik komunikatif.");
        jLabel263.setName("jLabel263"); // NOI18N
        FormInput.add(jLabel263);
        jLabel263.setBounds(40, 470, 340, 23);

        NI.add(radioButton22);
        radioButton22.setText("Tidak Ada");
        radioButton22.setToolTipText("Definisi tidak dipenuhi");
        radioButton22.setName("radioButton22"); // NOI18N
        radioButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton22ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton22);
        radioButton22.setBounds(40, 510, 66, 16);

        NI.add(radioButton23);
        radioButton23.setText("Minimal");
        radioButton23.setToolTipText("Patologis diragukan: mungkin suatu ujung ekstrim dari batasan normal.");
        radioButton23.setName("radioButton23"); // NOI18N
        radioButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton23ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton23);
        radioButton23.setBounds(130, 510, 53, 16);

        NI.add(radioButton24);
        radioButton24.setText("Ringan");
        radioButton24.setToolTipText("Perubahan ekspresi wajah dan gerak gerik komunikatif tampak kaku, dipaksakan, dibuat-buat atau kurangnya gelombang.");
        radioButton24.setName("radioButton24"); // NOI18N
        radioButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton24ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton24);
        radioButton24.setBounds(210, 510, 81, 16);

        NI.add(radioButton25);
        radioButton25.setText("Sedang");
        radioButton25.setToolTipText("Berkurangnya corak ekspresi wajah dan sedikitnya gerak gerik ekspresif yang tampak dalam penampilan yang tumpul (dull).");
        radioButton25.setName("radioButton25"); // NOI18N
        radioButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton25ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton25);
        radioButton25.setBounds(290, 510, 55, 16);

        NI.add(radioButton26);
        radioButton26.setText("Agak Berat");
        radioButton26.setToolTipText("Afek umumnya datar dengan hanya sekali-sekali tampak perubahan ekspresi wajah dan gerak gerik komunikatif sedikit.");
        radioButton26.setName("radioButton26"); // NOI18N
        radioButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton26ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton26);
        radioButton26.setBounds(360, 510, 72, 16);

        NI.add(radioButton27);
        radioButton27.setText("Berat");
        radioButton27.setToolTipText("Pendataran dan defisiensi emosi yang mencolok yang tampak hampir sepanjang waktu. \nKemungkinan terdapat pelepasan afek ekstrim yang tidak bergelombang seperti \"excitement\", kemarahan atau tertawa yang tidak terkendali yang tidak serasi");
        radioButton27.setName("radioButton27"); // NOI18N
        radioButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton27ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton27);
        radioButton27.setBounds(450, 510, 45, 16);

        NI.add(radioButton28);
        radioButton28.setText("Sangat Berat");
        radioButton28.setToolTipText("Jelas tidak tampak perubahan ekspresi wajah dan adanya gerak gerik komunikatif. Pasien terus menerus menampakkan ekspresi yang \"Tidak Hidup\" atau berwajah \"Kayu\".");
        radioButton28.setName("radioButton28"); // NOI18N
        radioButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton28ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton28);
        radioButton28.setBounds(510, 510, 82, 16);

        jLabel264.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel264.setText("Dasar penilaian : Observasi manifestasi fisik suasana afek dan respons emosional selama wawancara.");
        jLabel264.setName("jLabel264"); // NOI18N
        FormInput.add(jLabel264);
        jLabel264.setBounds(40, 490, 580, 23);

        textBox4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox4.setText("Skor");
        textBox4.setName("textBox4"); // NOI18N
        textBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox4ActionPerformed(evt);
            }
        });
        FormInput.add(textBox4);
        textBox4.setBounds(730, 480, 50, 24);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("N4. PENARIKAN DIRI DARI HUBUNGAN SOSIAL SECARA PASIF/APATIF");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(20, 530, 380, 23);

        jLabel265.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel265.setText("Berkurangnya minat dan inisiatif dalam interaksi sosial, yang disebabkan oleh pasivitas, apatis, energi atau tidak ada dorongan");
        jLabel265.setName("jLabel265"); // NOI18N
        FormInput.add(jLabel265);
        jLabel265.setBounds(40, 550, 610, 14);

        jLabel266.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel266.setText("kehendak. Hal ini mengarah pada berkurangnya keterlibatan interpersonal dan mengabaikan aktivitas kehidupan sehari-hari.");
        jLabel266.setName("jLabel266"); // NOI18N
        FormInput.add(jLabel266);
        jLabel266.setBounds(40, 560, 600, 23);

        N4.add(radioButton29);
        radioButton29.setText("Tidak Ada");
        radioButton29.setToolTipText("Definisi tidak terpenuhi.");
        radioButton29.setName("radioButton29"); // NOI18N
        radioButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton29ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton29);
        radioButton29.setBounds(40, 590, 66, 16);

        N4.add(radioButton30);
        radioButton30.setText("Minimal");
        radioButton30.setToolTipText("Patologis diragukan : mungkin suatu ujung ekstim dari batasan normal");
        radioButton30.setName("radioButton30"); // NOI18N
        radioButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton30ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton30);
        radioButton30.setBounds(130, 590, 53, 16);

        N4.add(radioButton31);
        radioButton31.setText("Ringan");
        radioButton31.setToolTipText("Sesekali menunjukan minat dalam aktivitas sosial, tetapi inisiatif sangat kurang. Biasanya keterlibatan dengan orang lain hanya bila \"didekati\" oleh orang lain tersebut.");
        radioButton31.setName("radioButton31"); // NOI18N
        radioButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton31ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton31);
        radioButton31.setBounds(210, 590, 81, 16);

        N4.add(radioButton32);
        radioButton32.setText("Sedang");
        radioButton32.setToolTipText("Secara pasif ikut dalam sebagian besar aktivitas sosial tetapi dengan cara \"oga-ogahan\" (disintersted) atausecara mekanis. Cenderung untuk ada dibaris belakang");
        radioButton32.setName("radioButton32"); // NOI18N
        radioButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton32ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton32);
        radioButton32.setBounds(290, 590, 55, 16);

        N4.add(radioButton33);
        radioButton33.setText("Agak Berat");
        radioButton33.setToolTipText("Secara pasif berpartisipasi dalam hanya sedikit aktivitas sosial dan menunjukan jelas tidak ada minat atau inisiatif. Umumnya menyendiri");
        radioButton33.setName("radioButton33"); // NOI18N
        radioButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton33ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton33);
        radioButton33.setBounds(360, 590, 72, 16);

        N4.add(radioButton34);
        radioButton34.setText("Berat");
        radioButton34.setToolTipText("Cenderung menjadi apatis dan terisolasi, sangat jarang berpartisipasi dalam aktivitas sosial dan sekali-kali mengabaikan kebutuhan pribadi, kontak sosial yang spontan sangat sedikit");
        radioButton34.setName("radioButton34"); // NOI18N
        radioButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton34ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton34);
        radioButton34.setBounds(450, 590, 45, 16);

        N4.add(radioButton35);
        radioButton35.setText("Sangat Berat");
        radioButton35.setToolTipText("Sangat apatis, terisolasi secara sosial dan sangat mengabaikan perawatan diri ");
        radioButton35.setName("radioButton35"); // NOI18N
        radioButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton35ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton35);
        radioButton35.setBounds(510, 590, 82, 16);

        textBox5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox5.setText("Skor");
        textBox5.setName("textBox5"); // NOI18N
        textBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox5ActionPerformed(evt);
            }
        });
        FormInput.add(textBox5);
        textBox5.setBounds(730, 560, 50, 24);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("N6. KURANGNYA SPONTANITAS DAN ARUS PERCAKAPAN");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(20, 613, 330, 20);

        jLabel268.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel268.setText("Kurangnya arus normal percakapan yang disertai dengan apatis, avolis (tidak ada dorongan kehendak), defensif atau defisit,");
        jLabel268.setName("jLabel268"); // NOI18N
        FormInput.add(jLabel268);
        jLabel268.setBounds(40, 630, 610, 23);

        jLabel269.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel269.setText("kognitif");
        jLabel269.setName("jLabel269"); // NOI18N
        FormInput.add(jLabel269);
        jLabel269.setBounds(40, 640, 600, 23);

        N6.add(radioButton36);
        radioButton36.setText("Tidak Ada");
        radioButton36.setToolTipText("Deifinisi tidak dipenuhi");
        radioButton36.setName("radioButton36"); // NOI18N
        radioButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton36ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton36);
        radioButton36.setBounds(40, 670, 66, 16);

        N6.add(radioButton37);
        radioButton37.setText("Minimal");
        radioButton37.setToolTipText("Patologis diragukan : mungkin suatu ujung ekstrim dari batasan normal");
        radioButton37.setName("radioButton37"); // NOI18N
        radioButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton37ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton37);
        radioButton37.setBounds(130, 670, 53, 16);

        N6.add(radioButton38);
        radioButton38.setText("Ringan");
        radioButton38.setToolTipText("Menunjukan sedikit inisiatif dalam percakapan, jawaban pasien cenderung singkat dan tanpa hambatan, membutuhkan pertanyaan langsung dan pengarahan dari pewawancara");
        radioButton38.setName("radioButton38"); // NOI18N
        radioButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton38ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton38);
        radioButton38.setBounds(210, 670, 81, 16);

        N6.add(radioButton39);
        radioButton39.setText("Sedang");
        radioButton39.setToolTipText("Arus percakapan kurang bebas dan tidak lancar atau henti-henti. Pertanyaan terarah sering dibutuhkan untuk mendapatkan respons yang adekuat dan untuk melanjutkan percakapan");
        radioButton39.setName("radioButton39"); // NOI18N
        radioButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton39ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton39);
        radioButton39.setBounds(290, 670, 55, 16);

        N6.add(radioButton40);
        radioButton40.setText("Agak Berat");
        radioButton40.setToolTipText("Pasien menunjukan berkurangnya spontanitas dan keterbukaan yang mencolok, menjawab pertanyaan pewawancara dengan hanya 1 dan 2 kalimat singkat");
        radioButton40.setName("radioButton40"); // NOI18N
        radioButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton40ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton40);
        radioButton40.setBounds(360, 670, 72, 16);

        N6.add(radioButton41);
        radioButton41.setText("Berat");
        radioButton41.setToolTipText("Respon pasien hanya terbatas terutama pada beberapa kata atau kalimat pendek untuk menghindari atau mempersingkat komunikasi (misalnya \"saya tidak tahu\". \"saya sedang tidak bebas berbicara\") akibatnya terdapat dendaya berat dalam percakapan, dan wawancara sangat tidak produktif");
        radioButton41.setName("radioButton41"); // NOI18N
        radioButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton41ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton41);
        radioButton41.setBounds(450, 670, 45, 16);

        N6.add(radioButton42);
        radioButton42.setText("Sangat Berat");
        radioButton42.setToolTipText("Kata-kata yang diucapkan sangat terbatas, paling bentar sekali-kali ada ungkapan sehingga percakapan tidak mungkin terjadi");
        radioButton42.setName("radioButton42"); // NOI18N
        radioButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton42ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton42);
        radioButton42.setBounds(510, 670, 82, 16);

        textBox6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox6.setText("Skor");
        textBox6.setName("textBox6"); // NOI18N
        textBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox6ActionPerformed(evt);
            }
        });
        FormInput.add(textBox6);
        textBox6.setBounds(730, 640, 50, 24);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("G5. MANNERISEDAN SIKAP TUBUH");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(20, 693, 330, 20);

        jLabel270.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel270.setText("Gerakan atau sikap tubuh yang tidak wajar seperti yang ditandai oleh kejanggalan, kaku, disorganisasi atau penampilan yang");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(40, 710, 610, 20);

        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel271.setText("Dasar penilaian : Observasi tentang maifestasi fisik selama wawancara dan laporan dari perawat atau keluarga.");
        jLabel271.setName("jLabel271"); // NOI18N
        FormInput.add(jLabel271);
        jLabel271.setBounds(40, 740, 600, 23);

        G5.add(radioButton43);
        radioButton43.setText("Tidak Ada");
        radioButton43.setToolTipText("Definisi tidak terpenuhi");
        radioButton43.setName("radioButton43"); // NOI18N
        radioButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton43ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton43);
        radioButton43.setBounds(40, 760, 66, 16);

        G5.add(radioButton44);
        radioButton44.setText("Minimal");
        radioButton44.setToolTipText("Patologis diragukan : mungkin suatu ujung ekstrim dari batas-batas normal");
        radioButton44.setName("radioButton44"); // NOI18N
        radioButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton44ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton44);
        radioButton44.setBounds(130, 760, 53, 16);

        G5.add(radioButton45);
        radioButton45.setText("Ringan");
        radioButton45.setToolTipText("Kejanggalan ringan dalam pergerakan atau kekakuan sikap tubuh yang ringan");
        radioButton45.setName("radioButton45"); // NOI18N
        radioButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton45ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton45);
        radioButton45.setBounds(210, 760, 81, 16);

        G5.add(radioButton46);
        radioButton46.setText("Sedang");
        radioButton46.setToolTipText("Gerakan janggal yang khusus atau terputus-putus, atau sikap tubuh tidak wajar yang dipertahankan untuk periode yang singkat");
        radioButton46.setName("radioButton46"); // NOI18N
        radioButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton46ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton46);
        radioButton46.setBounds(290, 760, 55, 16);

        G5.add(radioButton47);
        radioButton47.setText("Agak Berat");
        radioButton47.setToolTipText("Sesekali tampak ritual bizar, atau sikap tubuh yang berubah-ubah (contorized), atau suatu posisi abnormal yagn dipertahankan terus menerus untuk waktu yang agak lama");
        radioButton47.setName("radioButton47"); // NOI18N
        radioButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton47ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton47);
        radioButton47.setBounds(360, 760, 72, 16);

        G5.add(radioButton48);
        radioButton48.setText("Berat");
        radioButton48.setToolTipText("Sering tampak pengulangan ritual bizar, mannerisme, atau gerak-gerakan stereotipik, atau sikap tubuh yang berubah-ubah yang dipertahankan terus menerus untuk waktu yang lama");
        radioButton48.setName("radioButton48"); // NOI18N
        radioButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton48ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton48);
        radioButton48.setBounds(450, 760, 45, 16);

        G5.add(radioButton49);
        radioButton49.setText("Sangat Berat");
        radioButton49.setToolTipText("Fungsi terganggu berat oleh keterlibatan terus menerus yang jelas dalam ritual, mannerisme atau gerakan-gerakan streotipik atau suatu sikap tubuh tidak wajar tertentu yang dipertahankan terus menerus dalam sebagian besar waktu");
        radioButton49.setName("radioButton49"); // NOI18N
        radioButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton49ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton49);
        radioButton49.setBounds(510, 760, 82, 16);

        textBox7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox7.setText("Skor");
        textBox7.setName("textBox7"); // NOI18N
        textBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox7ActionPerformed(evt);
            }
        });
        FormInput.add(textBox7);
        textBox7.setBounds(730, 730, 50, 24);

        jLabel272.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel272.setText("bizar.");
        jLabel272.setName("jLabel272"); // NOI18N
        FormInput.add(jLabel272);
        jLabel272.setBounds(40, 720, 600, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("G9. ISI PIKIRAN YANG TIDAK BIASA");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(20, 783, 330, 20);

        jLabel273.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel273.setText("Poses pikir ditandai oleh ide-ide yang asing,fantastik, atau bazar, berkisar dari yang ringan atau atipikal sampai distorsi, tidal kogis");
        jLabel273.setName("jLabel273"); // NOI18N
        FormInput.add(jLabel273);
        jLabel273.setBounds(40, 800, 630, 23);

        jLabel274.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel274.setText("Dasar penilaian : Isi pikir yang diekspresikan selama wawancara.");
        jLabel274.setName("jLabel274"); // NOI18N
        FormInput.add(jLabel274);
        jLabel274.setBounds(40, 830, 600, 23);

        G9.add(radioButton50);
        radioButton50.setText("Tidak Ada");
        radioButton50.setToolTipText("Definisi tidak terpenuhi");
        radioButton50.setName("radioButton50"); // NOI18N
        radioButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton50ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton50);
        radioButton50.setBounds(40, 850, 66, 16);

        G9.add(radioButton51);
        radioButton51.setText("Minimal");
        radioButton51.setToolTipText("Patologis diragukan : mungkin suatu ujung ekstrim dari batasan normal");
        radioButton51.setName("radioButton51"); // NOI18N
        radioButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton51ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton51);
        radioButton51.setBounds(130, 850, 53, 16);

        G9.add(radioButton52);
        radioButton52.setText("Ringan");
        radioButton52.setToolTipText("isi pikir ganjil atau idionsinkratik, atau ide yang lazim dalam konteks yang aneh");
        radioButton52.setName("radioButton52"); // NOI18N
        radioButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton52ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton52);
        radioButton52.setBounds(210, 850, 81, 16);

        G9.add(radioButton53);
        radioButton53.setText("Sedang");
        radioButton53.setToolTipText("ide-ide seringkali mengalami distrosi dan sekali-kali cukup bizar");
        radioButton53.setName("radioButton53"); // NOI18N
        radioButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton53ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton53);
        radioButton53.setBounds(290, 850, 55, 16);

        G9.add(radioButton54);
        radioButton54.setText("Agak Berat");
        radioButton54.setToolTipText("Pasien mengekspresikan banyak pikiran-pikiran asing dan fantastik (misalnya menjadi anak angkat raja, orang yang berhasil lolos dalam kematian) atau beberapa pikiran yang sangat tidak masuk akal (misalnya mempunyai ratusan anak, menerima pesan radio dari angkasa luar melalui sebuah tambalan gigi)");
        radioButton54.setName("radioButton54"); // NOI18N
        radioButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton54ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton54);
        radioButton54.setBounds(360, 850, 72, 16);

        G9.add(radioButton55);
        radioButton55.setText("Berat");
        radioButton55.setToolTipText("Pasien mengekspresikan banyak ide yang tidak logis atau tidak masuk akal atau beberapa ide yang jelas berkualitas bizar (misalnya mempunyai 3 kepala, menjadi seorang pengunjung dari planet lain)");
        radioButton55.setName("radioButton55"); // NOI18N
        radioButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton55ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton55);
        radioButton55.setBounds(450, 850, 45, 16);

        G9.add(radioButton56);
        radioButton56.setText("Sangat Berat");
        radioButton56.setToolTipText("Proses pikir dipenuhi dengan ide-ide yang tidak masuk akal(absurd), bizar, dan aneh sekali (gortesque)");
        radioButton56.setName("radioButton56"); // NOI18N
        radioButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton56ActionPerformed(evt);
            }
        });
        FormInput.add(radioButton56);
        radioButton56.setBounds(510, 850, 82, 16);

        textBox8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBox8.setText("Skor");
        textBox8.setName("textBox8"); // NOI18N
        textBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox8ActionPerformed(evt);
            }
        });
        FormInput.add(textBox8);
        textBox8.setBounds(730, 820, 50, 24);

        jLabel275.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel275.setText("dan sanga tidak masuk akal.");
        jLabel275.setName("jLabel275"); // NOI18N
        FormInput.add(jLabel275);
        jLabel275.setBounds(40, 810, 600, 23);

        textBox9.setText("Total");
        textBox9.setName("textBox9"); // NOI18N
        textBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBox9ActionPerformed(evt);
            }
        });
        FormInput.add(textBox9);
        textBox9.setBounds(730, 880, 50, 24);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 70, 810, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 70, 810, 1);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 110, 810, 0);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 110, 810, 0);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(0, 780, 810, 90);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 170, 810, 80);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 250, 810, 90);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 340, 810, 100);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 440, 810, 90);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 530, 810, 80);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(0, 610, 810, 80);

        jSeparator17.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator17.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator17.setName("jSeparator17"); // NOI18N
        FormInput.add(jSeparator17);
        jSeparator17.setBounds(0, 170, 710, 80);

        jSeparator18.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator18.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator18.setName("jSeparator18"); // NOI18N
        FormInput.add(jSeparator18);
        jSeparator18.setBounds(0, 250, 710, 90);

        jSeparator19.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator19.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator19.setName("jSeparator19"); // NOI18N
        FormInput.add(jSeparator19);
        jSeparator19.setBounds(0, 340, 710, 100);

        jSeparator20.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator20.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator20.setName("jSeparator20"); // NOI18N
        FormInput.add(jSeparator20);
        jSeparator20.setBounds(0, 440, 710, 90);

        jSeparator21.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator21.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator21.setName("jSeparator21"); // NOI18N
        FormInput.add(jSeparator21);
        jSeparator21.setBounds(0, 530, 710, 80);

        jSeparator22.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator22.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator22.setName("jSeparator22"); // NOI18N
        FormInput.add(jSeparator22);
        jSeparator22.setBounds(0, 610, 710, 80);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(0, 690, 810, 90);

        jSeparator23.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator23.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator23.setName("jSeparator23"); // NOI18N
        FormInput.add(jSeparator23);
        jSeparator23.setBounds(0, 690, 710, 90);

        jSeparator24.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator24.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator24.setName("jSeparator24"); // NOI18N
        FormInput.add(jSeparator24);
        jSeparator24.setBounds(0, 780, 810, 90);

        jSeparator25.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator25.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator25.setName("jSeparator25"); // NOI18N
        FormInput.add(jSeparator25);
        jSeparator25.setBounds(0, 780, 810, 90);

        jSeparator26.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator26.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator26.setName("jSeparator26"); // NOI18N
        FormInput.add(jSeparator26);
        jSeparator26.setBounds(0, 690, 810, 90);

        jSeparator27.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator27.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator27.setName("jSeparator27"); // NOI18N
        FormInput.add(jSeparator27);
        jSeparator27.setBounds(0, 780, 710, 90);

        jSeparator28.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator28.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator28.setName("jSeparator28"); // NOI18N
        FormInput.add(jSeparator28);
        jSeparator28.setBounds(0, 870, 710, 40);

        jSeparator29.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator29.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator29.setName("jSeparator29"); // NOI18N
        FormInput.add(jSeparator29);
        jSeparator29.setBounds(0, 140, 810, 110);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String dP1="P1";
        String sP1="0";
        String dP2="P2";
        String sP2="0";
        String dP3="P3";
        String sP3="0";
        String dNi="NI";
        String sNi="0";
        String dN4="N4";
        String sN4="0";
        String dN6="N6";
        String sN6="0";
        String dG5="G5";
        String sG5="0";
        String dG9="G9";
        String sG9="0";
        String tScore="0";
        
        if(radioButton1.isSelected()){
            dP1 = "Tidak Ada : Definisi tidak dipenuhi";
        } 
        else if (radioButton2.isSelected()){
            dP1 = "Minimal : Patologis diragukan mungkin suatu ujung ekstrim dari batasan normal";
        } 
        else if (radioButton3.isSelected()){
            dP1 = "Ringan : Ada satu atau dua waham yang samar-samar, tidak terkristalisasi, dan tidak. bertahan. Waham tidak mempengaruhi proses pikir, relasi sosial atau perilaku.";
        } 
        else if (radioButton4.isSelected()){
            dP1 = "Sedang : Adanya rangkaian waham yang bentuknya kurang jelas dan tidak stabil atau beberapa waham yang berbentuk jelas, yang kadang-kadang mempengaruhi proses pikir, relasi sosial atau perilaku.";
        } 
        else if (radioButton5.isSelected()){
            dP1 = "Agak Berat : Adanya beberapa waham yang berbentuk jelas, yang berbentuk jelas yang dipertahankan dan kadang - kadang mempengaruhi proses pikir, relasi sosial dan prilaku.";
        } 
        else if (radioButton6.isSelected()){
            dP1 = "Berat : Adanya suatu susunan waham yang stabil, yang. terkristalisasi, mungkin sistematik, dipertahankan, dan jelas mempengaruhi proses pikir. relasi sosial dan perilaku.";
        } 
        else if (radioButton7.isSelected()){
            dP1 = "Sangat Berat : Adanya suatu susunan waham yang stabil, sangat sistemik, atau sangat banyak, dan yang mendominasi bidang (facei) utama kehidupan pasien. Sering kali mengakibatkan tindakan yang tidak serasi dan tidak bertanggung jawab, yang bahkan membahayakan keamanan pasien atau orang lain.";
        } 
        
        
        if(radioButton8.isSelected()){
            dP2 = "Tidak Ada : Definisi tidak dipenuhi.";
        } 
        else if (radioButton9.isSelected()){
            dP2 = "Minimal : Patologis diragukan, mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton10.isSelected()){
            dP2 = "Ringan : Proses pikir sirkumstansial, tangensial atau paralogikal. Adanya kesulitan dalam mengarahkan pikiran ke tujuan dan kadang-kadang asosiasi longgar dapat dijumpai di bawah tekanan.";
        } 
        else if (radioButton11.isSelected()){
            dP2 = "Sedang : Mampu memusatkan pikiran bila komunikasi singkat dan terstruktur, tetapi menjadi longgar atau tidak relevan. bila menghadapi komunikasi yang lebih kompleks atau bila di bawah tekanan minimal.";
        } 
        else if (radioButton12.isSelected()){
            dP2 = "Agak Berat : Secara umum mengalami kesulitan dalam menata pikiran yang terbukti dalam bentuk sering tidak relevan, tidak ada hubungan, atau asosiasi longgar bahkan walaupun tanpa tekanan.";
        } 
        else if (radioButton13.isSelected()){
            dP2 = "Berat : proses pikir (thinking) sangat menyimpang dan pada dasamya (interally) tidak konsisten, mengakibatkan tidak relevan yang parah dan kekacauan proses pikir, yang hampir terjadi terus menerus.";
        } 
        else if (radioButton14.isSelected()){
            dP2 = "Sangat Berat : Pikiran (thought) sangat kacau sehingga menjadi inkoheren Asosiasi longgar yang sangat jelas, yang mengakibakan kegagalan total.dalam komunikasi, misalnya \"word-salad atau mutisme.";
        } 
        
        
        if(radioButton15.isSelected()){
            dP3 = "Tidak Ada : Definisi tidak dipenuhi.";
        } 
        else if (radioButton16.isSelected()){
            dP3 = "Minimal : Patologis diragukan, mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton17.isSelected()){
            dP3 = "Ringan : Satu atau dua halusinasi yang jelas tetapi jarang timbul, atau beberapa abnormalitas persepsi yang samar-samar lainnya yang tidak mengakibatkan penyimpangan (distorsi) proses pikir atau perilaku.";
        } 
        else if (radioButton18.isSelected()){
            dP3 = "Sedang : Sering ada halusinasi tetapi tidak terus menerus, dan proses. Pikir secara perilaku pasien hanya sedikit terpengaruh.";
        } 
        else if (radioButton19.isSelected()){
            dP3 = "Agak Berat : Halusinasi sering, dapat meliputi lebih dari satu organ sensoris dan cenderung menyimpangkan. proses pikir dan/atau mengacaukan perilaku. Pasien dapat memiliki interpretasi bersifat waham atas pengalamannya ini dan bereaksi terhadapnya secara emosional, serta kadang-kadang juga secara verbal.";
        } 
        else if (radioButton20.isSelected()){
            dP3 = "Berat : Halusinasi hampir terus menerus ada, mengakibatkan kakacauan berat pada proses pikir dan perilaku. Pasien menganggapnya sebagai persepsi nyata dan fungsinya terganggu oleh seringnya bereaksi secara emosional dan verbal terhadapnya.";
        } 
        else if (radioButton21.isSelected()){
            dP3 = "Sangat Berat : Pasien hampir secara total mengalami preokupasi dengan halusinasi, yang jelas mendominasi proses pikir dan perilaku. Halusinasi diikuti interpretasi bersifat waham yang kaku dan memacu timbulnya respons verbal dan perilaku, termasuk kepatuhan lerhadap halusinasi perintah.";
        } 
        
        if(radioButton22.isSelected()){
            dNi = "Tidak Ada : Definisi tidak dipenuhi.";
        } 
        else if (radioButton23.isSelected()){
            dNi = "Minimal : Patologis diragukan: mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton24.isSelected()){
            dNi = "Ringan : Perubahan ekspresi wajah dan gerak gerik komunikatif tampak kaku, dipaksakan, dibuat-buat atau kurangnya gelombang.";
        } 
        else if (radioButton25.isSelected()){
            dNi = "Sedang : Berkurangnya corak ekspresi wajah dan sedikitnya gerak gerik ekspresif yang tampak dalam penampilan yang tumpul (dull).";
        } 
        else if (radioButton26.isSelected()){
            dNi = "Agak Berat : Afek umumnya datar dengan hanya sekali-sekali tampak perubahan ekspresi wajah dan gerak gerik komunikatif sedikit.";
        } 
        else if (radioButton27.isSelected()){
            dNi = "Berat : Pendataran dan defisiensi emosi yang mencolok yang tampak hampir sepanjang waktu.  Kemungkinan terdapat pelepasan afek ekstrim yang tidak bergelombang seperti \"excitement\", kemarahan atau tertawa yang tidak terkendali yang tidak serasi.";
        } 
        else if (radioButton28.isSelected()){
            dNi = "Sangat Berat : Jelas tidak tampak perubahan ekspresi wajah dan adanya gerak gerik komunikatif. Pasien terus menerus menampakkan ekspresi yang \"Tidak Hidup\" atau berwajah \"Kayu\".";
        } 
        
        
        if(radioButton29.isSelected()){
            dN4 = "Tidak Ada : Definisi tidak terpenuhi.";
        } 
        else if (radioButton30.isSelected()){
            dN4 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstim dari batasan normal.";
        } 
        else if (radioButton31.isSelected()){
            dN4 = "Ringan : Sesekali menunjukan minat dalam aktivitas sosial, tetapi inisiatif sangat kurang. Biasanya keterlibatan dengan orang lain hanya bila \"didekati\" oleh orang lain tersebut.";
        } 
        else if (radioButton32.isSelected()){
            dN4 = "Sedang : Secara pasif ikut dalam sebagian besar aktivitas sosial tetapi dengan cara \"oga-ogahan\" (disintersted) atausecara mekanis. Cenderung untuk ada dibaris belakang.";
        } 
        else if (radioButton33.isSelected()){
            dN4 = "Agak Berat : Secara pasif berpartisipasi dalam hanya sedikit aktivitas sosial dan menunjukan jelas tidak ada minat atau inisiatif. Umumnya menyendiri.";
        } 
        else if (radioButton34.isSelected()){
            dN4 = "Berat : Cenderung menjadi apatis dan terisolasi, sangat jarang berpartisipasi dalam aktivitas sosial dan sekali-kali mengabaikan kebutuhan pribadi, kontak sosial yang spontan sangat sedikit.";
        } 
        else if (radioButton35.isSelected()){
            dN4 = "Sangat Berat : Sangat apatis, terisolasi secara sosial dan sangat mengabaikan perawatan diri.";
        } 
        
        
        
        if(radioButton36.isSelected()){
            dN6 = "Tidak Ada : Deifinisi tidak dipenuhi.";
        } 
        else if (radioButton37.isSelected()){
            dN6 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton38.isSelected()){
            dN6 = "Ringan : Menunjukan sedikit inisiatif dalam percakapan, jawaban pasien cenderung singkat dan tanpa hambatan, membutuhkan pertanyaan langsung dan pengarahan dari pewawancara.";
        } 
        else if (radioButton39.isSelected()){
            dN6 = "Sedang : Arus percakapan kurang bebas dan tidak lancar atau henti-henti. Pertanyaan terarah sering dibutuhkan untuk mendapatkan respons yang adekuat dan untuk melanjutkan percakapan.";
        } 
        else if (radioButton40.isSelected()){
            dN6 = "Agak Berat : Pasien menunjukan berkurangnya spontanitas dan keterbukaan yang mencolok, menjawab pertanyaan pewawancara dengan hanya 1 dan 2 kalimat singkat.";
        } 
        else if (radioButton41.isSelected()){
            dN6 = "Berat : Respon pasien hanya terbatas terutama pada beberapa kata atau kalimat pendek untuk menghindari atau mempersingkat komunikasi (misalnya \"saya tidak tahu\". \"saya sedang tidak bebas berbicara\") akibatnya terdapat dendaya berat dalam percakapan, dan wawancara sangat tidak produktif.";
        } 
        else if (radioButton42.isSelected()){
            dN6 = "Sangat Berat : Kata-kata yang diucapkan sangat terbatas, paling bentar sekali-kali ada ungkapan sehingga percakapan tidak mungkin terjadi.";
        } 
        
        
        if(radioButton43.isSelected()){
            dG5 = "Tidak Ada : Definisi tidak terpenuhi.";
        } 
        else if (radioButton44.isSelected()){
            dG5 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstrim dari batas-batas normal.";
        } 
        else if (radioButton45.isSelected()){
            dG5 = "Ringan : Kejanggalan ringan dalam pergerakan atau kekakuan sikap tubuh yang ringan.";
        } 
        else if (radioButton46.isSelected()){
            dG5 = "Sedang : Gerakan janggal yang khusus atau terputus-putus, atau sikap tubuh tidak wajar yang dipertahankan untuk periode yang singkat.";
        } 
        else if (radioButton47.isSelected()){
            dG5 = "Agak Berat : Sesekali tampak ritual bizar, atau sikap tubuh yang berubah-ubah (contorized), atau suatu posisi abnormal yagn dipertahankan terus menerus untuk waktu yang agak lama.";
        } 
        else if (radioButton48.isSelected()){
            dG5 = "Berat : Sering tampak pengulangan ritual bizar, mannerisme, atau gerak-gerakan stereotipik, atau sikap tubuh yang berubah-ubah yang dipertahankan terus menerus untuk waktu yang lama.";
        } 
        else if (radioButton49.isSelected()){
            dG5 = "Sangat Berat : Fungsi terganggu berat oleh keterlibatan terus menerus yang jelas dalam ritual, mannerisme atau gerakan-gerakan streotipik atau suatu sikap tubuh tidak wajar tertentu yang dipertahankan terus menerus dalam sebagian besar waktu.";
        } 
        
        
        if(radioButton50.isSelected()){
            dG9 = "Tidak Ada : Definisi tidak terpenuhi.";
        } 
        else if (radioButton51.isSelected()){
            dG9 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton52.isSelected()){
            dG9 = "Ringan : Isi pikir ganjil atau idionsinkratik, atau ide yang lazim dalam konteks yang aneh.";
        } 
        else if (radioButton53.isSelected()){
            dG9 = "Sedang : Ide-ide seringkali mengalami distrosi dan sekali-kali cukup bizar.";
        } 
        else if (radioButton54.isSelected()){
            dG9 = "Agak Berat : Pasien mengekspresikan banyak pikiran-pikiran asing dan fantastik (misalnya menjadi anak angkat raja, orang yang berhasil lolos dalam kematian) atau beberapa pikiran yang sangat tidak masuk akal (misalnya mempunyai ratusan anak, menerima pesan radio dari angkasa luar melalui sebuah tambalan gigi)";
        } 
        else if (radioButton55.isSelected()){
            dG9 = "Berat : Pasien mengekspresikan banyak ide yang tidak logis atau tidak masuk akal atau beberapa ide yang jelas berkualitas bizar (misalnya mempunyai 3 kepala, menjadi seorang pengunjung dari planet lain)";
        } 
        else if (radioButton56.isSelected()){
            dG9 = "Sangat Berat : Proses pikir dipenuhi dengan ide-ide yang tidak masuk akal(absurd), bizar, dan aneh sekali (gortesque)";
        } 
        
        
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KdDokter.getText().trim().equals("")||NmDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else{
            if(Sequel.menyimpantf("penilaian_ranap_panss_remisi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
                     "?,?,?,?,?","Data",20,new String[]{
                TNoRw.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
//                Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem().toString(),
                KdDokter.getText(),
                dP1,
                textBox1.getText(),
                dP2,
                textBox2.getText(),
                dP3,
                textBox3.getText(),
                dNi, 
                textBox4.getText(),
                dN4,
                textBox5.getText(),
                dN6, 
                textBox6.getText(),
                dG5, 
                textBox7.getText(),
                dG9,
                textBox8.getText(),
                textBox9.getText(),
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
            Valid.pindah(evt,textBox1,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
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
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString())){
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
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(KdDokter.getText().trim().equals("")||NmDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Pokter");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString())){
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
        dokter.dispose();
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
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.R.M.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>JK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>P1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>P1 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>P2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>P2 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>P3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>P3 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NI</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NI Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N4 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>N6 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>G5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>G5 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>G9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>G9 Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Score</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>KdDokter</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dokter</b></td>"+
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
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='4400px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
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

                File f = new File("DataPenilaianTambahanBerisikoMelarikanDiri.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='4400px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PENILAIAN TAMBAHAN BERISIKO MELARIKAN DIRI<br><br></font>"+        
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

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
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
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,BtnDokter);
    }//GEN-LAST:event_DetikKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
//        Valid.pindah(evt,Detik,FaktorStatik1);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void MnPenilaianTambahanMelarikanDiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianTambahanMelarikanDiriActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),25).toString()+"\nID "+
                    (finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),24).toString():finger)+"\n"+Tanggal.getSelectedItem());
            Valid.MyReportqry("rptCetakPenilaianPanssRemisi.jasper","report","::[ Formulir Penilaian PANSS REMISI ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+
                    "penilaian_ranap_panss_remisi.no_rawat,"+
                    "penilaian_ranap_panss_remisi.tanggal,"+
                    "penilaian_ranap_panss_remisi.kd_dokter,"+
                    "penilaian_ranap_panss_remisi.p1,"+
                    "penilaian_ranap_panss_remisi.p1_score,"+
                    "penilaian_ranap_panss_remisi.p2,"+
                    "penilaian_ranap_panss_remisi.p2_score,"+
                    "penilaian_ranap_panss_remisi.p3,"+
                    "penilaian_ranap_panss_remisi.p3_score,"+
                    "penilaian_ranap_panss_remisi.ni,"+
                    "penilaian_ranap_panss_remisi.ni_score,"+
                    "penilaian_ranap_panss_remisi.n4,"+
                    "penilaian_ranap_panss_remisi.n4_score,"+
                    "penilaian_ranap_panss_remisi.n6,"+
                    "penilaian_ranap_panss_remisi.n6_score,"+
                    "penilaian_ranap_panss_remisi.g5,"+
                    "penilaian_ranap_panss_remisi.g5_score,"+
                    "penilaian_ranap_panss_remisi.g9,"+
                    "penilaian_ranap_panss_remisi.g9_score,"+
                    "penilaian_ranap_panss_remisi.total_score,"+
                    "penilaian_ranap_panss_remisi.kd_dokter,dokter.nm_dokter "+
                            
                    "from penilaian_ranap_panss_remisi inner join reg_periksa on penilaian_ranap_panss_remisi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on penilaian_ranap_panss_remisi.kd_dokter=dokter.kd_dokter "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
//            "where penilaian_ranap_panss_remisi.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"' "+
//                "and penilaian_ranap_panss_remisi.tanggal='"+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"'",param);
            
        }
    }//GEN-LAST:event_MnPenilaianTambahanMelarikanDiriActionPerformed

    private void radioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton1ActionPerformed
        // if radio button kepilih
        int in=1;
        if(radioButton1.isSelected()){
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton1ActionPerformed

    private void radioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton2ActionPerformed
        // TODO add your handling code here:
        int in=2;
        if(radioButton2.isSelected()){
            
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton2ActionPerformed

    private void radioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton3ActionPerformed
        // TODO add your handling code here:
        int in=3;
        if(radioButton3.isSelected()){
           
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton3ActionPerformed

    private void radioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton4ActionPerformed
        // TODO add your handling code here:
        int in=4;
        if(radioButton4.isSelected()){
            //             String prP1 = "";
            //      desk skor
            //            int skrP1=4;
            //        Tampilke Skor
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton4ActionPerformed

    private void radioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton5ActionPerformed
        // TODO add your handling code here:
        int in=5;
        if(radioButton5.isSelected()){
            //             String prP1 = "";
            //      desk skor
            //            int skrP1=5;
            //        Tampilke Skor
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton5ActionPerformed

    private void radioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton6ActionPerformed
        // TODO add your handling code here:
        int in=6;
        if(radioButton6.isSelected()){
            //             String prP1 = "";
            //      desk skor
            //            int skrP1=6;
            //        Tampilke Skor
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton6ActionPerformed

    private void radioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton7ActionPerformed
        // TODO add your handling code here:
        int in=7;
        if(radioButton7.isSelected()){
            //             String prP1 = "";
            //      desk skor
            //            int skrP1=7;
            //        Tampilke Skor
            textBox1.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton7ActionPerformed

    private void radioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton8ActionPerformed
        // TODO add your handling code here:
        int in=1;
        if(radioButton8.isSelected()){
            //             String prP1 = "";
            //      desk skor
            //            int skrP1=7;
            //        Tampilke Skor
            textBox2.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton8ActionPerformed

    private void radioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton9ActionPerformed
        // TODO add your handling code here:
        int in=2;
        if(radioButton9.isSelected()){
            textBox2.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton9ActionPerformed

    private void radioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton10ActionPerformed
        // TODO add your handling code here:
        int in=3;
        if(radioButton10.isSelected()){
            textBox2.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton10ActionPerformed

    private void radioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton11ActionPerformed
        int in=4;
        if(radioButton11.isSelected()){
            textBox2.setText(Integer.toString(in));
        };
        total();
     
    }//GEN-LAST:event_radioButton11ActionPerformed

    private void radioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton12ActionPerformed
        // TODO add your handling code here:
        int in=5;
        if(radioButton12.isSelected()){
            textBox2.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton12ActionPerformed

    private void radioButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton13ActionPerformed
        // TODO add your handling code here:
        int in=6;
        if(radioButton13.isSelected()){
            textBox2.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton13ActionPerformed

    private void radioButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton14ActionPerformed
        // TODO add your handling code here:
        int in=7;
        if(radioButton14.isSelected()){
            textBox2.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton14ActionPerformed

    private void radioButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton15ActionPerformed
        // TODO add your handling code here:
        int in=1;
        if(radioButton15.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton15ActionPerformed

    private void radioButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton16ActionPerformed
        // TODO add your handling code here:
        int in=2;
        if(radioButton16.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton16ActionPerformed

    private void radioButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton17ActionPerformed
        // TODO add your handling code here:
        int in=3;
        if(radioButton17.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton17ActionPerformed

    private void radioButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton18ActionPerformed
        // TODO add your handling code here:
        int in=4;
        if(radioButton18.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton18ActionPerformed

    private void radioButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton19ActionPerformed
        // TODO add your handling code here:
        int in=5;
        if(radioButton19.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton19ActionPerformed

    private void radioButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton20ActionPerformed
        // TODO add your handling code here:
        int in=6;
        if(radioButton20.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton20ActionPerformed

    private void radioButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton21ActionPerformed
        // TODO add your handling code here:
        int in=7;
        if(radioButton21.isSelected()){
            textBox3.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton21ActionPerformed

    private void textBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox1ActionPerformed

    private void textBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox2ActionPerformed

    private void textBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox3ActionPerformed

    private void radioButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton22ActionPerformed
        // TODO add your handling code here:
        int in=1;
        if(radioButton22.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton22ActionPerformed

    private void radioButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton23ActionPerformed
        // TODO add your handling code here:
        int in=2;
        if(radioButton23.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton23ActionPerformed

    private void radioButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton24ActionPerformed
        // TODO add your handling code here:
        int in=3;
        if(radioButton24.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton24ActionPerformed

    private void radioButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton25ActionPerformed
        // TODO add your handling code here:
        int in=4;
        if(radioButton25.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton25ActionPerformed

    private void radioButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton26ActionPerformed
        // TODO add your handling code here:
        int in=5;
        if(radioButton26.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton26ActionPerformed

    private void radioButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton27ActionPerformed
        // TODO add your handling code here:
        int in=6;
        if(radioButton27.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton27ActionPerformed

    private void radioButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton28ActionPerformed
        // TODO add your handling code here:
        int in=7;
        if(radioButton28.isSelected()){
            textBox4.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton28ActionPerformed

    private void textBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox4ActionPerformed

    private void radioButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton29ActionPerformed
        // TODO add your handling code here:
        int in=1;
        if(radioButton29.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton29ActionPerformed

    private void radioButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton30ActionPerformed
        int in=2;
        if(radioButton30.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton30ActionPerformed

    private void radioButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton31ActionPerformed
        // TODO add your handling code here:
        int in=3;
        if(radioButton31.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton31ActionPerformed

    private void radioButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton32ActionPerformed
        // TODO add your handling code here:
        int in=4;
        if(radioButton32.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton32ActionPerformed

    private void radioButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton33ActionPerformed
        // TODO add your handling code here:
        int in=5;
        if(radioButton33.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton33ActionPerformed

    private void radioButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton34ActionPerformed
        // TODO add your handling code here:
        int in=6;
        if(radioButton34.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton34ActionPerformed

    private void radioButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton35ActionPerformed
        // TODO add your handling code here:
        int in=7;
        if(radioButton35.isSelected()){
            textBox5.setText(Integer.toString(in));
        };
        total();
    }//GEN-LAST:event_radioButton35ActionPerformed

    private void textBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox5ActionPerformed

    private void radioButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton36ActionPerformed
        // TODO add your handling code here:
        if(radioButton36.isSelected()){
            textBox6.setText(Integer.toString(1));
        };
        total();
    }//GEN-LAST:event_radioButton36ActionPerformed

    private void radioButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton37ActionPerformed
        // TODO add your handling code here:
        if(radioButton37.isSelected()){
            textBox6.setText(Integer.toString(2));
        };
        total();
    }//GEN-LAST:event_radioButton37ActionPerformed

    private void radioButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton38ActionPerformed
        // TODO add your handling code here:
        if(radioButton38.isSelected()){
            textBox6.setText(Integer.toString(3));
        };
        total();
    }//GEN-LAST:event_radioButton38ActionPerformed

    private void radioButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton39ActionPerformed
        if(radioButton39.isSelected()){
            textBox6.setText(Integer.toString(4));
        };
        total();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButton39ActionPerformed

    private void radioButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton40ActionPerformed
        if(radioButton40.isSelected()){
            textBox6.setText(Integer.toString(5));
        };
        total();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButton40ActionPerformed

    private void radioButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton41ActionPerformed
        if(radioButton41.isSelected()){
            textBox6.setText(Integer.toString(6));
        };
        total();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButton41ActionPerformed

    private void radioButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton42ActionPerformed
        if(radioButton42.isSelected()){
            textBox6.setText(Integer.toString(7));
        };
        total();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButton42ActionPerformed

    private void textBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox6ActionPerformed

    private void radioButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton43ActionPerformed
        // TODO add your handling code here:
        if(radioButton43.isSelected()){
            textBox7.setText(Integer.toString(1));
        };
        total();
    }//GEN-LAST:event_radioButton43ActionPerformed

    private void radioButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton44ActionPerformed
        // TODO add your handling code here:
        if(radioButton44.isSelected()){
            textBox7.setText(Integer.toString(2));
        };
        total();
    }//GEN-LAST:event_radioButton44ActionPerformed

    private void radioButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton45ActionPerformed
        // TODO add your handling code here:
        if(radioButton45.isSelected()){
            textBox7.setText(Integer.toString(3));
        };
        total();
    }//GEN-LAST:event_radioButton45ActionPerformed

    private void radioButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton46ActionPerformed
        // TODO add your handling code here:
        if(radioButton46.isSelected()){
            textBox7.setText(Integer.toString(4));
        };
        total();
    }//GEN-LAST:event_radioButton46ActionPerformed

    private void radioButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton47ActionPerformed
        // TODO add your handling code here:
        if(radioButton47.isSelected()){
            textBox7.setText(Integer.toString(5));
        };
        total();
    }//GEN-LAST:event_radioButton47ActionPerformed

    private void radioButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton48ActionPerformed
        // TODO add your handling code here:
        if(radioButton48.isSelected()){
            textBox7.setText(Integer.toString(6));
        };
        total();
    }//GEN-LAST:event_radioButton48ActionPerformed

    private void total() {
        try {
            textBox9.setText((Integer.parseInt(textBox1.getText()))+(Integer.parseInt(textBox2.getText()))+(Integer.parseInt(textBox3.getText()))+(Integer.parseInt(textBox4.getText()))+(Integer.parseInt(textBox5.getText()))+(Integer.parseInt(textBox6.getText()))+(Integer.parseInt(textBox7.getText()))+(Integer.parseInt(textBox8.getText()))+"");
        } catch (Exception e) {
            textBox9.setText("0");
        }
//    int s1=Integer.parseInt(textBox1.getText());
//        int s2=Integer.parseInt(textBox2.getText());
//        int s3=Integer.parseInt(textBox3.getText());
//        int s4=Integer.parseInt(textBox4.getText());
//        int s5=Integer.parseInt(textBox5.getText());
//        int s6=Integer.parseInt(textBox6.getText());
//        int s7=Integer.parseInt(textBox7.getText());
//        int s8=Integer.parseInt(textBox8.getText());
//        
//        int totals = s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;
//        
//        int ddd=18;
        
}
    private void radioButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton49ActionPerformed
        // TODO add your handling code here:
        if(radioButton49.isSelected()){
            textBox7.setText(Integer.toString(7));
        };
        total();
    }//GEN-LAST:event_radioButton49ActionPerformed

    private void textBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox7ActionPerformed

    private void radioButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton50ActionPerformed
        // TODO add your handling code here:
        if(radioButton50.isSelected()){
            textBox8.setText(Integer.toString(1));
        };
        total();
    }//GEN-LAST:event_radioButton50ActionPerformed

    private void radioButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton51ActionPerformed
        // TODO add your handling code here:
        if(radioButton51.isSelected()){
            textBox8.setText(Integer.toString(2));
        };
        total();
    }//GEN-LAST:event_radioButton51ActionPerformed

    private void radioButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton52ActionPerformed
        // TODO add your handling code here:
        if(radioButton52.isSelected()){
            textBox8.setText(Integer.toString(3));
        };
        total();
    }//GEN-LAST:event_radioButton52ActionPerformed

    private void radioButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton53ActionPerformed
        // TODO add your handling code here:
        if(radioButton53.isSelected()){
            textBox8.setText(Integer.toString(4));
        };
        total();
    }//GEN-LAST:event_radioButton53ActionPerformed

    private void radioButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton54ActionPerformed
        // TODO add your handling code here:
        if(radioButton54.isSelected()){
            textBox8.setText(Integer.toString(5));
        };
        total();
    }//GEN-LAST:event_radioButton54ActionPerformed

    private void radioButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton55ActionPerformed
        // TODO add your handling code here:
        if(radioButton55.isSelected()){
            textBox8.setText(Integer.toString(6));
        };
        total();
    }//GEN-LAST:event_radioButton55ActionPerformed

    private void radioButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton56ActionPerformed
        // TODO add your handling code here:
        if(radioButton56.isSelected()){
            textBox8.setText(Integer.toString(7));
        };
        total();
    }//GEN-LAST:event_radioButton56ActionPerformed

    private void textBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBox8ActionPerformed

    private void textBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBox9ActionPerformed
        // TODO add your handling code here:
//        textBox9.setText((Integer.parseInt(textBox1.getText())+Integer.parseInt(textBox2.getText())+
//              Integer.parseInt(textBox3.getText())+Integer.parseInt(textBox4.getText())+Integer.parseInt(textBox5.getText())+
//              Integer.parseInt(textBox6.getText())+Integer.parseInt(textBox7.getText())+Integer.parseInt(textBox8.getText()))+"");


        int s1=Integer.parseInt(textBox1.getText());
        int s2=Integer.parseInt(textBox2.getText());
        int s3=Integer.parseInt(textBox3.getText());
        int s4=Integer.parseInt(textBox4.getText());
        int s5=Integer.parseInt(textBox5.getText());
        int s6=Integer.parseInt(textBox6.getText());
        int s7=Integer.parseInt(textBox7.getText());
        int s8=Integer.parseInt(textBox8.getText());
        
        int totals = s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;
        
        int ddd=18;

//isTotalSkor();
//        int textBox9=total;
        
        textBox9.setText(Integer.toString(totals));
    }//GEN-LAST:event_textBox9ActionPerformed

    private void NmDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmDokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmDokterActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianPanssRemisi dialog = new RMPenilaianPanssRemisi(new javax.swing.JFrame(), true);
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
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.PanelBiasa FormInput;
    private javax.swing.ButtonGroup G5;
    private javax.swing.ButtonGroup G9;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnPenilaianTambahanMelarikanDiri;
    private javax.swing.ButtonGroup N4;
    private javax.swing.ButtonGroup N6;
    private javax.swing.ButtonGroup NI;
    private widget.TextBox NmDokter;
    private javax.swing.ButtonGroup P1;
    private javax.swing.ButtonGroup P2;
    private javax.swing.ButtonGroup P3;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel220;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel255;
    private widget.Label jLabel256;
    private widget.Label jLabel257;
    private widget.Label jLabel258;
    private widget.Label jLabel259;
    private widget.Label jLabel260;
    private widget.Label jLabel262;
    private widget.Label jLabel263;
    private widget.Label jLabel264;
    private widget.Label jLabel265;
    private widget.Label jLabel266;
    private widget.Label jLabel268;
    private widget.Label jLabel269;
    private widget.Label jLabel270;
    private widget.Label jLabel271;
    private widget.Label jLabel272;
    private widget.Label jLabel273;
    private widget.Label jLabel274;
    private widget.Label jLabel275;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel57;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
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
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.RadioButton radioButton1;
    private widget.RadioButton radioButton10;
    private widget.RadioButton radioButton11;
    private widget.RadioButton radioButton12;
    private widget.RadioButton radioButton13;
    private widget.RadioButton radioButton14;
    private widget.RadioButton radioButton15;
    private widget.RadioButton radioButton16;
    private widget.RadioButton radioButton17;
    private widget.RadioButton radioButton18;
    private widget.RadioButton radioButton19;
    private widget.RadioButton radioButton2;
    private widget.RadioButton radioButton20;
    private widget.RadioButton radioButton21;
    private widget.RadioButton radioButton22;
    private widget.RadioButton radioButton23;
    private widget.RadioButton radioButton24;
    private widget.RadioButton radioButton25;
    private widget.RadioButton radioButton26;
    private widget.RadioButton radioButton27;
    private widget.RadioButton radioButton28;
    private widget.RadioButton radioButton29;
    private widget.RadioButton radioButton3;
    private widget.RadioButton radioButton30;
    private widget.RadioButton radioButton31;
    private widget.RadioButton radioButton32;
    private widget.RadioButton radioButton33;
    private widget.RadioButton radioButton34;
    private widget.RadioButton radioButton35;
    private widget.RadioButton radioButton36;
    private widget.RadioButton radioButton37;
    private widget.RadioButton radioButton38;
    private widget.RadioButton radioButton39;
    private widget.RadioButton radioButton4;
    private widget.RadioButton radioButton40;
    private widget.RadioButton radioButton41;
    private widget.RadioButton radioButton42;
    private widget.RadioButton radioButton43;
    private widget.RadioButton radioButton44;
    private widget.RadioButton radioButton45;
    private widget.RadioButton radioButton46;
    private widget.RadioButton radioButton47;
    private widget.RadioButton radioButton48;
    private widget.RadioButton radioButton49;
    private widget.RadioButton radioButton5;
    private widget.RadioButton radioButton50;
    private widget.RadioButton radioButton51;
    private widget.RadioButton radioButton52;
    private widget.RadioButton radioButton53;
    private widget.RadioButton radioButton54;
    private widget.RadioButton radioButton55;
    private widget.RadioButton radioButton56;
    private widget.RadioButton radioButton6;
    private widget.RadioButton radioButton7;
    private widget.RadioButton radioButton8;
    private widget.RadioButton radioButton9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    private widget.TextBox textBox1;
    private widget.TextBox textBox2;
    private widget.TextBox textBox3;
    private widget.TextBox textBox4;
    private widget.TextBox textBox5;
    private widget.TextBox textBox6;
    private widget.TextBox textBox7;
    private widget.TextBox textBox8;
    private widget.TextBox textBox9;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,"+
                    "penilaian_ranap_panss_remisi.no_rawat,"+
                    "penilaian_ranap_panss_remisi.tanggal,"+
                    "penilaian_ranap_panss_remisi.kd_dokter,"+
                    "penilaian_ranap_panss_remisi.p1,"+
                    "penilaian_ranap_panss_remisi.p1_score,"+
                    "penilaian_ranap_panss_remisi.p2,"+
                    "penilaian_ranap_panss_remisi.p2_score,"+
                    "penilaian_ranap_panss_remisi.p3,"+
                    "penilaian_ranap_panss_remisi.p3_score,"+
                    "penilaian_ranap_panss_remisi.ni,"+
                    "penilaian_ranap_panss_remisi.ni_score,"+
                    "penilaian_ranap_panss_remisi.n4,"+
                    "penilaian_ranap_panss_remisi.n4_score,"+
                    "penilaian_ranap_panss_remisi.n6,"+
                    "penilaian_ranap_panss_remisi.n6_score,"+
                    "penilaian_ranap_panss_remisi.g5,"+
                    "penilaian_ranap_panss_remisi.g5_score,"+
                    "penilaian_ranap_panss_remisi.g9,"+
                    "penilaian_ranap_panss_remisi.g9_score,"+
                    "penilaian_ranap_panss_remisi.total_score,"+
                    "penilaian_ranap_panss_remisi.kd_dokter,dokter.nm_dokter "+
                            
                    "from penilaian_ranap_panss_remisi inner join reg_periksa on penilaian_ranap_panss_remisi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on penilaian_ranap_panss_remisi.kd_dokter=dokter.kd_dokter where "+
                    "penilaian_ranap_panss_remisi.tanggal between ? and ? order by penilaian_ranap_panss_remisi.tanggal");
                }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,"+
                    "penilaian_ranap_panss_remisi.no_rawat,"+
                    "penilaian_ranap_panss_remisi.tanggal,"+
  "penilaian_ranap_panss_remisi.kd_dokter,"+
  "penilaian_ranap_panss_remisi.p1,"+
  "penilaian_ranap_panss_remisi.p1_score,"+
  "penilaian_ranap_panss_remisi.p2,"+
  "penilaian_ranap_panss_remisi.p2_score,"+
  "penilaian_ranap_panss_remisi.p3,"+
  "penilaian_ranap_panss_remisi.p3_score,"+
  "penilaian_ranap_panss_remisi.ni,"+
  "penilaian_ranap_panss_remisi.ni_score,"+
  "penilaian_ranap_panss_remisi.n4,"+
  "penilaian_ranap_panss_remisi.n4_score,"+
  "penilaian_ranap_panss_remisi.n6,"+
  "penilaian_ranap_panss_remisi.n6_score,"+
  "penilaian_ranap_panss_remisi.g5,"+
  "penilaian_ranap_panss_remisi.g5_score,"+
  "penilaian_ranap_panss_remisi.g9,"+
  "penilaian_ranap_panss_remisi.g9_score,"+
  "penilaian_ranap_panss_remisi.total_score,"+
                    "penilaian_ranap_panss_remisi.kd_dokter,dokter.nm_dokter "+
                    
                    "from penilaian_ranap_panss_remisi inner join reg_periksa on penilaian_ranap_panss_remisi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on penilaian_ranap_panss_remisi.kd_dokter=dokter.kd_dokter where "+
                    "penilaian_ranap_panss_remisi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_ranap_panss_remisi.kd_dokter like ? or dokter.nm_dokter like ?) "+
                    "order by penilaian_ranap_panss_remisi.tanggal ");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("tanggal"),
                        
                        rs.getString("p1"),rs.getString("p1_score"),rs.getString("p2"),
                        rs.getString("p2_score"),rs.getString("p3"),rs.getString("p3_score"),
                        rs.getString("ni"),rs.getString("ni_score"),rs.getString("n4"),
                        rs.getString("n4_score"),rs.getString("n6"),rs.getString("n6_score"),
                        rs.getString("g5"),rs.getString("g5_score"),rs.getString("g9"),
                        rs.getString("g9_score"),rs.getString("total_score"),rs.getString("total_score"),
                        rs.getString("kd_dokter"),rs.getString("nm_dokter")
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
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void emptTeks() {
        Tanggal.setDate(new Date());
//        Jk.setText("");
        TNoRw.getText();
//                Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem();
//                Valid.Tanggal.setSelected(false);
                KdDokter.getText();
                textBox1.getText(); 
                textBox2.getText();
                textBox3.getText();
                textBox4.getText();
                textBox5.getText();
                textBox6.getText();
                textBox7.getText();
                textBox8.getText();
                textBox9.getText();
                radioButton1.setSelected(false);
                radioButton2.setSelected(false);
                radioButton3.setSelected(false);
                radioButton4.setSelected(false);
                radioButton5.setSelected(false);
                radioButton6.setSelected(false);
                radioButton7.setSelected(false);
                radioButton8.setSelected(false);
                radioButton9.setSelected(false);
                radioButton10.setSelected(false);
                radioButton11.setSelected(false);
                radioButton12.setSelected(false);
                radioButton13.setSelected(false);
                radioButton14.setSelected(false);
                radioButton15.setSelected(false);
                radioButton16.setSelected(false);
                radioButton17.setSelected(false);
                radioButton18.setSelected(false);
                radioButton19.setSelected(false);
                radioButton20.setSelected(false);
                radioButton21.setSelected(false);
                radioButton22.setSelected(false);
                radioButton23.setSelected(false);
                radioButton24.setSelected(false);
                radioButton25.setSelected(false);
                radioButton26.setSelected(false);
                radioButton27.setSelected(false);
                radioButton28.setSelected(false);
                radioButton29.setSelected(false);
                radioButton30.setSelected(false);
                radioButton31.setSelected(false);
                radioButton32.setSelected(false);
                radioButton33.setSelected(false);
                radioButton34.setSelected(false);
                radioButton35.setSelected(false);
                radioButton36.setSelected(false);
                radioButton37.setSelected(false);
                radioButton38.setSelected(false);
                radioButton39.setSelected(false);
                radioButton40.setSelected(false);
                radioButton41.setSelected(false);
                radioButton42.setSelected(false);
                radioButton43.setSelected(false);
                radioButton44.setSelected(false);
                radioButton45.setSelected(false);
                radioButton46.setSelected(false);
                radioButton47.setSelected(false);
                radioButton48.setSelected(false);
                radioButton49.setSelected(false);
                radioButton50.setSelected(false);
                radioButton51.setSelected(false);
                radioButton52.setSelected(false);
                radioButton53.setSelected(false);
                radioButton54.setSelected(false);
                radioButton55.setSelected(false);
                radioButton56.setSelected(false);
        textBox9.setText("0");
//        Level.setText("Rendah(<7)");
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(11,13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(14,16));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(17,19));
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            
            
            textBox1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            textBox2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            textBox3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            textBox4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            textBox5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            textBox6.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            textBox7.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            textBox8.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            textBox9.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Tidak Ada")){
                radioButton1.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Minimal")){
                radioButton2.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Ringan")){
                radioButton3.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Sedang")){
                radioButton4.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Agak Berat")){
                radioButton5.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Berat")){
                radioButton6.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().contains("Sangat Berat")){
                radioButton7.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Tidak Ada")){
                radioButton8.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Minimal")){
                radioButton9.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Ringan")){
                radioButton10.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Sedang")){
                radioButton11.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Agak Berat")){
                radioButton12.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Berat")){
                radioButton13.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().contains("Sangat Berat")){
                radioButton14.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Tidak Ada")){
                radioButton15.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Minimal")){
                radioButton16.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Ringan")){
                radioButton17.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Sedang")){
                radioButton18.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Agak Berat")){
                radioButton19.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Berat")){
                radioButton20.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),10).toString().contains("Sangat Berat")){
                radioButton21.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Tidak Ada")){
                radioButton22.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Minimal")){
                radioButton23.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Ringan")){
                radioButton24.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Sedang")){
                radioButton25.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Agak Berat")){
                radioButton26.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Berat")){
                radioButton27.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),12).toString().contains("Sangat Berat")){
                radioButton28.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Tidak Ada")){
                radioButton29.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Minimal")){
                radioButton30.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Ringan")){
                radioButton31.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Sedang")){
                radioButton32.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Agak Berat")){
                radioButton33.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Berat")){
                radioButton34.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().contains("Sangat Berat")){
                radioButton35.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Tidak Ada")){
                radioButton36.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Minimal")){
                radioButton37.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Ringan")){
                radioButton38.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Sedang")){
                radioButton39.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Agak Berat")){
                radioButton40.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Berat")){
                radioButton41.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),16).toString().contains("Sangat Berat")){
                radioButton42.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Tidak Ada")){
                radioButton43.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Minimal")){
                radioButton44.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Ringan")){
                radioButton45.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Sedang")){
                radioButton46.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Agak Berat")){
                radioButton47.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Berat")){
                radioButton48.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().contains("Sangat Berat")){
                radioButton49.setSelected(true);
            }
            
            
            if(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Tidak Ada")){
                radioButton50.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Minimal")){
                radioButton51.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Ringan")){
                radioButton52.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Sedang")){
                radioButton53.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Agak Berat")){
                radioButton54.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Berat")){
                radioButton55.setSelected(true);
            }
            else if (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString().contains("Sangat Berat")){
                radioButton56.setSelected(true);
            }
        }
    }
    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
        try {
            ps=koneksi.prepareStatement("select pasien.nm_pasien,pasien.jk,pasien.tgl_lahir as lahir from pasien where pasien.no_rkm_medis=?");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }finally {
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
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>628){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,456));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-172));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_ranap_panss_remisi());
        BtnHapus.setEnabled(akses.getpenilaian_ranap_panss_remisi());
        BtnEdit.setEnabled(akses.getpenilaian_ranap_panss_remisi());
        BtnPrint.setEnabled(akses.getpenilaian_ranap_panss_remisi()); 
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan dokter...!!");
            }
        }            
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        String dP1="P1";
        String sP1="0";
        String dP2="P2";
        String sP2="0";
        String dP3="P3";
        String sP3="0";
        String dNi="NI";
        String sNi="0";
        String dN4="N4";
        String sN4="0";
        String dN6="N6";
        String sN6="0";
        String dG5="G5";
        String sG5="0";
        String dG9="G9";
        String sG9="0";
        String tScore="0";
        
        if(radioButton1.isSelected()){
            dP1 = "Tidak Ada : Definisi tidak dipenuhi";
        } 
        else if (radioButton2.isSelected()){
            dP1 = "Minimal : Patologis diragukan mungkin suatu ujung ekstrim dari batasan normal";
        } 
        else if (radioButton3.isSelected()){
            dP1 = "Ringan : Ada satu atau dua waham yang samar-samar, tidak terkristalisasi, dan tidak. bertahan. Waham tidak mempengaruhi proses pikir, relasi sosial atau perilaku.";
        } 
        else if (radioButton4.isSelected()){
            dP1 = "Sedang : Adanya rangkaian waham yang bentuknya kurang jelas dan tidak stabil atau beberapa waham yang berbentuk jelas, yang kadang-kadang mempengaruhi proses pikir, relasi sosial atau perilaku.";
        } 
        else if (radioButton5.isSelected()){
            dP1 = "Agak Berat : Adanya beberapa waham yang berbentuk jelas, yang berbentuk jelas yang dipertahankan dan kadang - kadang mempengaruhi proses pikir, relasi sosial dan prilaku.";
        } 
        else if (radioButton6.isSelected()){
            dP1 = "Berat : Adanya suatu susunan waham yang stabil, yang. terkristalisasi, mungkin sistematik, dipertahankan, dan jelas mempengaruhi proses pikir. relasi sosial dan perilaku.";
        } 
        else if (radioButton7.isSelected()){
            dP1 = "Sangat Berat : Adanya suatu susunan waham yang stabil, sangat sistemik, atau sangat banyak, dan yang mendominasi bidang (facei) utama kehidupan pasien. Sering kali mengakibatkan tindakan yang tidak serasi dan tidak bertanggung jawab, yang bahkan membahayakan keamanan pasien atau orang lain.";
        } 
        
        
        if(radioButton8.isSelected()){
            dP2 = "Tidak Ada : Definisi tidak dipenuhi.";
        } 
        else if (radioButton9.isSelected()){
            dP2 = "Minimal : Patologis diragukan, mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton10.isSelected()){
            dP2 = "Ringan : Proses pikir sirkumstansial, tangensial atau paralogikal. Adanya kesulitan dalam mengarahkan pikiran ke tujuan dan kadang-kadang asosiasi longgar dapat dijumpai di bawah tekanan.";
        } 
        else if (radioButton11.isSelected()){
            dP2 = "Sedang : Mampu memusatkan pikiran bila komunikasi singkat dan terstruktur, tetapi menjadi longgar atau tidak relevan. bila menghadapi komunikasi yang lebih kompleks atau bila di bawah tekanan minimal.";
        } 
        else if (radioButton12.isSelected()){
            dP2 = "Agak Berat : Secara umum mengalami kesulitan dalam menata pikiran yang terbukti dalam bentuk sering tidak relevan, tidak ada hubungan, atau asosiasi longgar bahkan walaupun tanpa tekanan.";
        } 
        else if (radioButton13.isSelected()){
            dP2 = "Berat : proses pikir (thinking) sangat menyimpang dan pada dasamya (interally) tidak konsisten, mengakibatkan tidak relevan yang parah dan kekacauan proses pikir, yang hampir terjadi terus menerus.";
        } 
        else if (radioButton14.isSelected()){
            dP2 = "Sangat Berat : Pikiran (thought) sangat kacau sehingga menjadi inkoheren Asosiasi longgar yang sangat jelas, yang mengakibakan kegagalan total.dalam komunikasi, misalnya \"word-salad atau mutisme.";
        } 
        
        
        if(radioButton15.isSelected()){
            dP3 = "Tidak Ada : Definisi tidak dipenuhi.";
        } 
        else if (radioButton16.isSelected()){
            dP3 = "Minimal : Patologis diragukan, mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton17.isSelected()){
            dP3 = "Ringan : Satu atau dua halusinasi yang jelas tetapi jarang timbul, atau beberapa abnormalitas persepsi yang samar-samar lainnya yang tidak mengakibatkan penyimpangan (distorsi) proses pikir atau perilaku.";
        } 
        else if (radioButton18.isSelected()){
            dP3 = "Sedang : Sering ada halusinasi tetapi tidak terus menerus, dan proses. Pikir secara perilaku pasien hanya sedikit terpengaruh.";
        } 
        else if (radioButton19.isSelected()){
            dP3 = "Agak Berat : Halusinasi sering, dapat meliputi lebih dari satu organ sensoris dan cenderung menyimpangkan. proses pikir dan/atau mengacaukan perilaku. Pasien dapat memiliki interpretasi bersifat waham atas pengalamannya ini dan bereaksi terhadapnya secara emosional, serta kadang-kadang juga secara verbal.";
        } 
        else if (radioButton20.isSelected()){
            dP3 = "Berat : Halusinasi hampir terus menerus ada, mengakibatkan kakacauan berat pada proses pikir dan perilaku. Pasien menganggapnya sebagai persepsi nyata dan fungsinya terganggu oleh seringnya bereaksi secara emosional dan verbal terhadapnya.";
        } 
        else if (radioButton21.isSelected()){
            dP3 = "Sangat Berat : Pasien hampir secara total mengalami preokupasi dengan halusinasi, yang jelas mendominasi proses pikir dan perilaku. Halusinasi diikuti interpretasi bersifat waham yang kaku dan memacu timbulnya respons verbal dan perilaku, termasuk kepatuhan lerhadap halusinasi perintah.";
        } 
        
        if(radioButton22.isSelected()){
            dNi = "Tidak Ada : Definisi tidak dipenuhi.";
        } 
        else if (radioButton23.isSelected()){
            dNi = "Minimal : Patologis diragukan: mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton24.isSelected()){
            dNi = "Ringan : Perubahan ekspresi wajah dan gerak gerik komunikatif tampak kaku, dipaksakan, dibuat-buat atau kurangnya gelombang.";
        } 
        else if (radioButton25.isSelected()){
            dNi = "Sedang : Berkurangnya corak ekspresi wajah dan sedikitnya gerak gerik ekspresif yang tampak dalam penampilan yang tumpul (dull).";
        } 
        else if (radioButton26.isSelected()){
            dNi = "Agak Berat : Afek umumnya datar dengan hanya sekali-sekali tampak perubahan ekspresi wajah dan gerak gerik komunikatif sedikit.";
        } 
        else if (radioButton27.isSelected()){
            dNi = "Berat : Pendataran dan defisiensi emosi yang mencolok yang tampak hampir sepanjang waktu.  Kemungkinan terdapat pelepasan afek ekstrim yang tidak bergelombang seperti \"excitement\", kemarahan atau tertawa yang tidak terkendali yang tidak serasi.";
        } 
        else if (radioButton28.isSelected()){
            dNi = "Sangat Berat : Jelas tidak tampak perubahan ekspresi wajah dan adanya gerak gerik komunikatif. Pasien terus menerus menampakkan ekspresi yang \"Tidak Hidup\" atau berwajah \"Kayu\".";
        } 
        
        
        if(radioButton29.isSelected()){
            dN4 = "Tidak Ada : Definisi tidak terpenuhi.";
        } 
        else if (radioButton30.isSelected()){
            dN4 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstim dari batasan normal.";
        } 
        else if (radioButton31.isSelected()){
            dN4 = "Ringan : Sesekali menunjukan minat dalam aktivitas sosial, tetapi inisiatif sangat kurang. Biasanya keterlibatan dengan orang lain hanya bila \"didekati\" oleh orang lain tersebut.";
        } 
        else if (radioButton32.isSelected()){
            dN4 = "Sedang : Secara pasif ikut dalam sebagian besar aktivitas sosial tetapi dengan cara \"oga-ogahan\" (disintersted) atausecara mekanis. Cenderung untuk ada dibaris belakang.";
        } 
        else if (radioButton33.isSelected()){
            dN4 = "Agak Berat : Secara pasif berpartisipasi dalam hanya sedikit aktivitas sosial dan menunjukan jelas tidak ada minat atau inisiatif. Umumnya menyendiri.";
        } 
        else if (radioButton34.isSelected()){
            dN4 = "Berat : Cenderung menjadi apatis dan terisolasi, sangat jarang berpartisipasi dalam aktivitas sosial dan sekali-kali mengabaikan kebutuhan pribadi, kontak sosial yang spontan sangat sedikit.";
        } 
        else if (radioButton35.isSelected()){
            dN4 = "Sangat Berat : Sangat apatis, terisolasi secara sosial dan sangat mengabaikan perawatan diri.";
        } 
        
        
        
        if(radioButton36.isSelected()){
            dN6 = "Tidak Ada : Deifinisi tidak dipenuhi.";
        } 
        else if (radioButton37.isSelected()){
            dN6 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton38.isSelected()){
            dN6 = "Ringan : Menunjukan sedikit inisiatif dalam percakapan, jawaban pasien cenderung singkat dan tanpa hambatan, membutuhkan pertanyaan langsung dan pengarahan dari pewawancara.";
        } 
        else if (radioButton39.isSelected()){
            dN6 = "Sedang : Arus percakapan kurang bebas dan tidak lancar atau henti-henti. Pertanyaan terarah sering dibutuhkan untuk mendapatkan respons yang adekuat dan untuk melanjutkan percakapan.";
        } 
        else if (radioButton40.isSelected()){
            dN6 = "Agak Berat : Pasien menunjukan berkurangnya spontanitas dan keterbukaan yang mencolok, menjawab pertanyaan pewawancara dengan hanya 1 dan 2 kalimat singkat.";
        } 
        else if (radioButton41.isSelected()){
            dN6 = "Berat : Respon pasien hanya terbatas terutama pada beberapa kata atau kalimat pendek untuk menghindari atau mempersingkat komunikasi (misalnya \"saya tidak tahu\". \"saya sedang tidak bebas berbicara\") akibatnya terdapat dendaya berat dalam percakapan, dan wawancara sangat tidak produktif.";
        } 
        else if (radioButton42.isSelected()){
            dN6 = "Sangat Berat : Kata-kata yang diucapkan sangat terbatas, paling bentar sekali-kali ada ungkapan sehingga percakapan tidak mungkin terjadi.";
        } 
        
        
        if(radioButton43.isSelected()){
            dG5 = "Tidak Ada : Definisi tidak terpenuhi.";
        } 
        else if (radioButton44.isSelected()){
            dG5 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstrim dari batas-batas normal.";
        } 
        else if (radioButton45.isSelected()){
            dG5 = "Ringan : Kejanggalan ringan dalam pergerakan atau kekakuan sikap tubuh yang ringan.";
        } 
        else if (radioButton46.isSelected()){
            dG5 = "Sedang : Gerakan janggal yang khusus atau terputus-putus, atau sikap tubuh tidak wajar yang dipertahankan untuk periode yang singkat.";
        } 
        else if (radioButton47.isSelected()){
            dG5 = "Agak Berat : Sesekali tampak ritual bizar, atau sikap tubuh yang berubah-ubah (contorized), atau suatu posisi abnormal yagn dipertahankan terus menerus untuk waktu yang agak lama.";
        } 
        else if (radioButton48.isSelected()){
            dG5 = "Berat : Sering tampak pengulangan ritual bizar, mannerisme, atau gerak-gerakan stereotipik, atau sikap tubuh yang berubah-ubah yang dipertahankan terus menerus untuk waktu yang lama.";
        } 
        else if (radioButton49.isSelected()){
            dG5 = "Sangat Berat : Fungsi terganggu berat oleh keterlibatan terus menerus yang jelas dalam ritual, mannerisme atau gerakan-gerakan streotipik atau suatu sikap tubuh tidak wajar tertentu yang dipertahankan terus menerus dalam sebagian besar waktu.";
        } 
        
        
        if(radioButton50.isSelected()){
            dG9 = "Tidak Ada : Definisi tidak terpenuhi.";
        } 
        else if (radioButton51.isSelected()){
            dG9 = "Minimal : Patologis diragukan : mungkin suatu ujung ekstrim dari batasan normal.";
        } 
        else if (radioButton52.isSelected()){
            dG9 = "Ringan : Isi pikir ganjil atau idionsinkratik, atau ide yang lazim dalam konteks yang aneh.";
        } 
        else if (radioButton53.isSelected()){
            dG9 = "Sedang : Ide-ide seringkali mengalami distrosi dan sekali-kali cukup bizar.";
        } 
        else if (radioButton54.isSelected()){
            dG9 = "Agak Berat : Pasien mengekspresikan banyak pikiran-pikiran asing dan fantastik (misalnya menjadi anak angkat raja, orang yang berhasil lolos dalam kematian) atau beberapa pikiran yang sangat tidak masuk akal (misalnya mempunyai ratusan anak, menerima pesan radio dari angkasa luar melalui sebuah tambalan gigi)";
        } 
        else if (radioButton55.isSelected()){
            dG9 = "Berat : Pasien mengekspresikan banyak ide yang tidak logis atau tidak masuk akal atau beberapa ide yang jelas berkualitas bizar (misalnya mempunyai 3 kepala, menjadi seorang pengunjung dari planet lain)";
        } 
        else if (radioButton56.isSelected()){
            dG9 = "Sangat Berat : Proses pikir dipenuhi dengan ide-ide yang tidak masuk akal(absurd), bizar, dan aneh sekali (gortesque)";
        } 
        
        if(Sequel.mengedittf("penilaian_ranap_panss_remisi",
                "no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,p1=?,p1_score=?,p2=?,"+
                "p2_score=?,p3=?,p3_score=?,ni=?,ni_score=?,n4=?,n4_score=?,"+
                "n6=?,n6_score=?,g5=?,g5_score=?,g9=?,g9_score=?,total_score=?",21,new String[]{
                    
                TNoRw.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),

                KdDokter.getText(),
                dP1,
                textBox1.getText(),
                dP2,
                textBox2.getText(),
                dP3,
                textBox3.getText(),
                dNi, 
                textBox4.getText(),
                dN4,
                textBox5.getText(),
                dN6, 
                textBox6.getText(),
                dG5, 
                textBox7.getText(),
                dG9,
                textBox8.getText(),
                textBox9.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                
            })==true){
            tampil();
            emptTeks();
//            TabRawat.setSelectedIndex(1);
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_ranap_panss_remisi where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    private void isTotalSkor(){
        try {
            textBox9.setText((Integer.parseInt(textBox1.getText())+Integer.parseInt(textBox2.getText())+
              Integer.parseInt(textBox3.getText())+Integer.parseInt(textBox4.getText())+Integer.parseInt(textBox5.getText())+
              Integer.parseInt(textBox6.getText())+Integer.parseInt(textBox7.getText())+Integer.parseInt(textBox8.getText()))+"");
            if(Integer.parseInt(textBox9.getText())<7){
//                Level.setText("Rendah(<7)");
            }else if(Integer.parseInt(textBox9.getText())<=14){
//                Level.setText("Sedang(7-14)");
            }else if(Integer.parseInt(textBox9.getText())>14){
//                Level.setText("Tinggi(>14)");
            }
        } catch (Exception e) {
            textBox9.setText("0");
//            Level.setText("Rendah(<7)");
        }
    }
}
