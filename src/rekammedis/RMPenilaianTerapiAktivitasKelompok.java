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
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianTerapiAktivitasKelompok extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private String finger="";
    private StringBuilder htmlContent;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianTerapiAktivitasKelompok(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Tgl.Lahir","JK","Tanggal",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Kemampuan","TAK 1","TAK 2","TAK 3","TAK 4","TAK 5",
            "Total Skor","Kesimpulan","NIP","Petugas"
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
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(130);
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
                column.setPreferredWidth(130);
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
                column.setPreferredWidth(160);
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
                column.setPreferredWidth(130);
            }else if(i==25){
                column.setPreferredWidth(90);
            }else if(i==26){
                column.setPreferredWidth(80);
            }else if(i==27){
                column.setPreferredWidth(90);
            }else if(i==28){
                column.setPreferredWidth(80);
            }else if(i==29){
                column.setPreferredWidth(90);
            }else if(i==30){
                column.setPreferredWidth(130);
            }else if(i==31){
                column.setPreferredWidth(90);
            }else if(i==32){
                column.setPreferredWidth(80);
            }else if(i==33){
                column.setPreferredWidth(90);
            }else if(i==34){
                column.setPreferredWidth(80);
            }else if(i==35){
                column.setPreferredWidth(90);
            }else if(i==36){
                column.setPreferredWidth(130);
            }else if(i==37){
                column.setPreferredWidth(90);
            }else if(i==38){
                column.setPreferredWidth(80);
            }else if(i==39){
                column.setPreferredWidth(90);
            }else if(i==40){
                column.setPreferredWidth(80);
            }else if(i==41){
                column.setPreferredWidth(90);
            }
            
            else if(i==42){
                column.setPreferredWidth(130);
            }else if(i==43){
                column.setPreferredWidth(60);
            }else if(i==44){
                column.setPreferredWidth(75);
            }else if(i==45){
                column.setPreferredWidth(80);
            }
            else if(i==46){
                column.setPreferredWidth(150);
            }
            else if(i==47){
                column.setPreferredWidth(150);
            }
            else if(i==48){
                column.setPreferredWidth(150);
            }
            else if(i==49){
                column.setPreferredWidth(80);
            }
            else if(i==50){
                column.setPreferredWidth(80);
            }
            else if(i==51){
                column.setPreferredWidth(80);
            }
            else if(i==52){
                column.setPreferredWidth(80);
            }
            else if(i==53){
                column.setPreferredWidth(80);
            }
            else if(i==54){
                column.setPreferredWidth(150);
            }
            else if(i==55){
                column.setPreferredWidth(80);
            }
            else if(i==56){
                column.setPreferredWidth(80);
            }
            else if(i==57){
                column.setPreferredWidth(80);
            }
            else if(i==58){
                column.setPreferredWidth(80);
            }
            else if(i==59){
                column.setPreferredWidth(80);
            }
            else if(i==60){
                column.setPreferredWidth(380);
            }
            else if(i==61){
                column.setPreferredWidth(80);
            }else if(i==62){
                column.setPreferredWidth(80);
            }else if(i==63){
                column.setPreferredWidth(80);
            }else if(i==64){
                column.setPreferredWidth(80);
            }else if(i==65){
                column.setPreferredWidth(80);
            }else if(i==66){
                column.setPreferredWidth(80);
            }else if(i==67){
                column.setPreferredWidth(80);
            }else if(i==59){
                column.setPreferredWidth(150);
            }else if(i==68){
                column.setPreferredWidth(120);
            }else if(i==69){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
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
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel57 = new widget.Label();
        jLabel217 = new widget.Label();
        a1 = new widget.TextBox();
        jLabel220 = new widget.Label();
        b1 = new widget.TextBox();
        jLabel223 = new widget.Label();
        c1 = new widget.TextBox();
        jLabel226 = new widget.Label();
        d1 = new widget.TextBox();
        jLabel229 = new widget.Label();
        e1 = new widget.TextBox();
        jLabel235 = new widget.Label();
        Jumlah = new widget.TextBox();
        TingkatResiko1 = new widget.Label();
        jLabel230 = new widget.Label();
        jLabel232 = new widget.Label();
        jLabel233 = new widget.Label();
        jLabel234 = new widget.Label();
        jLabel236 = new widget.Label();
        jLabel58 = new widget.Label();
        f1 = new widget.TextBox();
        g1 = new widget.TextBox();
        h1 = new widget.TextBox();
        i1 = new widget.TextBox();
        j1 = new widget.TextBox();
        a2 = new widget.TextBox();
        b2 = new widget.TextBox();
        c2 = new widget.TextBox();
        d2 = new widget.TextBox();
        e2 = new widget.TextBox();
        jLabel59 = new widget.Label();
        f2 = new widget.TextBox();
        g2 = new widget.TextBox();
        h2 = new widget.TextBox();
        i2 = new widget.TextBox();
        j2 = new widget.TextBox();
        jLabel60 = new widget.Label();
        i3 = new widget.TextBox();
        f3 = new widget.TextBox();
        d3 = new widget.TextBox();
        c3 = new widget.TextBox();
        g3 = new widget.TextBox();
        b3 = new widget.TextBox();
        j3 = new widget.TextBox();
        a3 = new widget.TextBox();
        e3 = new widget.TextBox();
        h3 = new widget.TextBox();
        jLabel61 = new widget.Label();
        i4 = new widget.TextBox();
        f4 = new widget.TextBox();
        d4 = new widget.TextBox();
        c4 = new widget.TextBox();
        g4 = new widget.TextBox();
        b4 = new widget.TextBox();
        j4 = new widget.TextBox();
        a4 = new widget.TextBox();
        e4 = new widget.TextBox();
        h4 = new widget.TextBox();
        jLabel62 = new widget.Label();
        i5 = new widget.TextBox();
        f5 = new widget.TextBox();
        d5 = new widget.TextBox();
        c5 = new widget.TextBox();
        g5 = new widget.TextBox();
        b5 = new widget.TextBox();
        j5 = new widget.TextBox();
        a5 = new widget.TextBox();
        e5 = new widget.TextBox();
        h5 = new widget.TextBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
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
        scrollPane2 = new widget.ScrollPane();
        Kesimpulan = new widget.TextArea();
        TingkatResiko2 = new widget.Label();
        TingkatResiko3 = new widget.Label();
        TingkatResiko4 = new widget.Label();
        TingkatResiko5 = new widget.Label();
        TingkatResiko6 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianTambahanMelarikanDiri.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianTambahanMelarikanDiri.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianTambahanMelarikanDiri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianTambahanMelarikanDiri.setText("Formulir Penilaian Tambahan Berisiko Melarikan Diri");
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Terhadap Aktivitas Kelompok ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-11-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-11-2023" }));
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
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 760));
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
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-11-2023" }));
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

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(400, 40, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(474, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(570, 40, 187, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(761, 40, 28, 23);

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

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Kemampuan Yang Dinilai");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(20, 70, 170, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1. Kemampuan berkomunikasi dengan topic bahasan.");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(20, 100, 260, 20);

        a1.setFocusTraversalPolicyProvider(true);
        a1.setName("a1"); // NOI18N
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });
        FormInput.add(a1);
        a1.setBounds(420, 100, 40, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2. Kemampuan menyampaikan ide.");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(20, 140, 180, 23);

        b1.setFocusTraversalPolicyProvider(true);
        b1.setName("b1"); // NOI18N
        FormInput.add(b1);
        b1.setBounds(420, 140, 40, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3. Kemampuan mengendalikan komunikasi.");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(20, 180, 220, 23);

        c1.setFocusTraversalPolicyProvider(true);
        c1.setName("c1"); // NOI18N
        FormInput.add(c1);
        c1.setBounds(420, 180, 40, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("4. Kemampuan berorientasi realitas.");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(20, 220, 180, 23);

        d1.setFocusTraversalPolicyProvider(true);
        d1.setName("d1"); // NOI18N
        FormInput.add(d1);
        d1.setBounds(420, 220, 40, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("7. Kemampuan beraktivitas sesuai topic.");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(20, 340, 200, 23);

        e1.setFocusTraversalPolicyProvider(true);
        e1.setName("e1"); // NOI18N
        FormInput.add(e1);
        e1.setBounds(420, 260, 40, 23);

        jLabel235.setText("Jumlah :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(280, 500, 50, 23);

        Jumlah.setEditable(false);
        Jumlah.setFocusTraversalPolicyProvider(true);
        Jumlah.setName("Jumlah"); // NOI18N
        Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahActionPerformed(evt);
            }
        });
        FormInput.add(Jumlah);
        Jumlah.setBounds(340, 500, 70, 23);

        TingkatResiko1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko1.setText("Kseimpulan dan rencana tindak lanjut :");
        TingkatResiko1.setToolTipText("");
        TingkatResiko1.setName("TingkatResiko1"); // NOI18N
        FormInput.add(TingkatResiko1);
        TingkatResiko1.setBounds(20, 530, 190, 23);

        jLabel230.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel230.setText("5. Kemampuan memberikan instruksi.");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(20, 260, 180, 23);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("6. Kemampuan memberikan respon.");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(20, 300, 180, 23);

        jLabel233.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel233.setText("8. Kemampuan mengikuti aturan / instruksi.");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(20, 380, 210, 23);

        jLabel234.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel234.setText("9. Kemampuan bekerjasama secara team.");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(20, 420, 210, 23);

        jLabel236.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel236.setText("10. Mendemonstrasikan prilaku adaptif sesuai dengan masalah yang dihadapi. ");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(20, 460, 380, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("TAK - 1");
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(420, 70, 40, 23);

        f1.setFocusTraversalPolicyProvider(true);
        f1.setName("f1"); // NOI18N
        FormInput.add(f1);
        f1.setBounds(420, 300, 40, 23);

        g1.setFocusTraversalPolicyProvider(true);
        g1.setName("g1"); // NOI18N
        FormInput.add(g1);
        g1.setBounds(420, 340, 40, 23);

        h1.setFocusTraversalPolicyProvider(true);
        h1.setName("h1"); // NOI18N
        FormInput.add(h1);
        h1.setBounds(420, 380, 40, 23);

        i1.setFocusTraversalPolicyProvider(true);
        i1.setName("i1"); // NOI18N
        FormInput.add(i1);
        i1.setBounds(420, 420, 40, 23);

        j1.setFocusTraversalPolicyProvider(true);
        j1.setName("j1"); // NOI18N
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });
        FormInput.add(j1);
        j1.setBounds(420, 460, 40, 23);

        a2.setFocusTraversalPolicyProvider(true);
        a2.setName("a2"); // NOI18N
        FormInput.add(a2);
        a2.setBounds(490, 100, 40, 23);

        b2.setFocusTraversalPolicyProvider(true);
        b2.setName("b2"); // NOI18N
        FormInput.add(b2);
        b2.setBounds(490, 140, 40, 23);

        c2.setFocusTraversalPolicyProvider(true);
        c2.setName("c2"); // NOI18N
        FormInput.add(c2);
        c2.setBounds(490, 180, 40, 23);

        d2.setFocusTraversalPolicyProvider(true);
        d2.setName("d2"); // NOI18N
        FormInput.add(d2);
        d2.setBounds(490, 220, 40, 23);

        e2.setFocusTraversalPolicyProvider(true);
        e2.setName("e2"); // NOI18N
        FormInput.add(e2);
        e2.setBounds(490, 260, 40, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("TAK - 2");
        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(490, 70, 40, 23);

        f2.setFocusTraversalPolicyProvider(true);
        f2.setName("f2"); // NOI18N
        FormInput.add(f2);
        f2.setBounds(490, 300, 40, 23);

        g2.setFocusTraversalPolicyProvider(true);
        g2.setName("g2"); // NOI18N
        FormInput.add(g2);
        g2.setBounds(490, 340, 40, 23);

        h2.setFocusTraversalPolicyProvider(true);
        h2.setName("h2"); // NOI18N
        FormInput.add(h2);
        h2.setBounds(490, 380, 40, 23);

        i2.setFocusTraversalPolicyProvider(true);
        i2.setName("i2"); // NOI18N
        FormInput.add(i2);
        i2.setBounds(490, 420, 40, 23);

        j2.setFocusTraversalPolicyProvider(true);
        j2.setName("j2"); // NOI18N
        FormInput.add(j2);
        j2.setBounds(490, 460, 40, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("TAK - 3");
        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(560, 70, 40, 23);

        i3.setFocusTraversalPolicyProvider(true);
        i3.setName("i3"); // NOI18N
        FormInput.add(i3);
        i3.setBounds(560, 420, 40, 23);

        f3.setFocusTraversalPolicyProvider(true);
        f3.setName("f3"); // NOI18N
        FormInput.add(f3);
        f3.setBounds(560, 300, 40, 23);

        d3.setFocusTraversalPolicyProvider(true);
        d3.setName("d3"); // NOI18N
        FormInput.add(d3);
        d3.setBounds(560, 220, 40, 23);

        c3.setFocusTraversalPolicyProvider(true);
        c3.setName("c3"); // NOI18N
        FormInput.add(c3);
        c3.setBounds(560, 180, 40, 23);

        g3.setFocusTraversalPolicyProvider(true);
        g3.setName("g3"); // NOI18N
        FormInput.add(g3);
        g3.setBounds(560, 340, 40, 23);

        b3.setFocusTraversalPolicyProvider(true);
        b3.setName("b3"); // NOI18N
        FormInput.add(b3);
        b3.setBounds(560, 140, 40, 23);

        j3.setFocusTraversalPolicyProvider(true);
        j3.setName("j3"); // NOI18N
        FormInput.add(j3);
        j3.setBounds(560, 460, 40, 23);

        a3.setFocusTraversalPolicyProvider(true);
        a3.setName("a3"); // NOI18N
        FormInput.add(a3);
        a3.setBounds(560, 100, 40, 23);

        e3.setFocusTraversalPolicyProvider(true);
        e3.setName("e3"); // NOI18N
        FormInput.add(e3);
        e3.setBounds(560, 260, 40, 23);

        h3.setFocusTraversalPolicyProvider(true);
        h3.setName("h3"); // NOI18N
        FormInput.add(h3);
        h3.setBounds(560, 380, 40, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("TAK - 4");
        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(630, 70, 40, 23);

        i4.setFocusTraversalPolicyProvider(true);
        i4.setName("i4"); // NOI18N
        FormInput.add(i4);
        i4.setBounds(630, 420, 40, 23);

        f4.setFocusTraversalPolicyProvider(true);
        f4.setName("f4"); // NOI18N
        FormInput.add(f4);
        f4.setBounds(630, 300, 40, 23);

        d4.setFocusTraversalPolicyProvider(true);
        d4.setName("d4"); // NOI18N
        FormInput.add(d4);
        d4.setBounds(630, 220, 40, 23);

        c4.setFocusTraversalPolicyProvider(true);
        c4.setName("c4"); // NOI18N
        FormInput.add(c4);
        c4.setBounds(630, 180, 40, 23);

        g4.setFocusTraversalPolicyProvider(true);
        g4.setName("g4"); // NOI18N
        FormInput.add(g4);
        g4.setBounds(630, 340, 40, 23);

        b4.setFocusTraversalPolicyProvider(true);
        b4.setName("b4"); // NOI18N
        FormInput.add(b4);
        b4.setBounds(630, 140, 40, 23);

        j4.setFocusTraversalPolicyProvider(true);
        j4.setName("j4"); // NOI18N
        FormInput.add(j4);
        j4.setBounds(630, 460, 40, 23);

        a4.setFocusTraversalPolicyProvider(true);
        a4.setName("a4"); // NOI18N
        FormInput.add(a4);
        a4.setBounds(630, 100, 40, 23);

        e4.setFocusTraversalPolicyProvider(true);
        e4.setName("e4"); // NOI18N
        FormInput.add(e4);
        e4.setBounds(630, 260, 40, 23);

        h4.setFocusTraversalPolicyProvider(true);
        h4.setName("h4"); // NOI18N
        FormInput.add(h4);
        h4.setBounds(630, 380, 40, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("TAK - 5");
        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(700, 70, 40, 23);

        i5.setFocusTraversalPolicyProvider(true);
        i5.setName("i5"); // NOI18N
        FormInput.add(i5);
        i5.setBounds(700, 420, 40, 23);

        f5.setFocusTraversalPolicyProvider(true);
        f5.setName("f5"); // NOI18N
        FormInput.add(f5);
        f5.setBounds(700, 300, 40, 23);

        d5.setFocusTraversalPolicyProvider(true);
        d5.setName("d5"); // NOI18N
        FormInput.add(d5);
        d5.setBounds(700, 220, 40, 23);

        c5.setFocusTraversalPolicyProvider(true);
        c5.setName("c5"); // NOI18N
        FormInput.add(c5);
        c5.setBounds(700, 180, 40, 23);

        g5.setFocusTraversalPolicyProvider(true);
        g5.setName("g5"); // NOI18N
        FormInput.add(g5);
        g5.setBounds(700, 340, 40, 23);

        b5.setFocusTraversalPolicyProvider(true);
        b5.setName("b5"); // NOI18N
        FormInput.add(b5);
        b5.setBounds(700, 140, 40, 23);

        j5.setFocusTraversalPolicyProvider(true);
        j5.setName("j5"); // NOI18N
        j5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j5ActionPerformed(evt);
            }
        });
        j5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                j5PropertyChange(evt);
            }
        });
        FormInput.add(j5);
        j5.setBounds(700, 460, 40, 23);

        a5.setFocusTraversalPolicyProvider(true);
        a5.setName("a5"); // NOI18N
        FormInput.add(a5);
        a5.setBounds(700, 100, 40, 23);

        e5.setFocusTraversalPolicyProvider(true);
        e5.setName("e5"); // NOI18N
        FormInput.add(e5);
        e5.setBounds(700, 260, 40, 23);

        h5.setFocusTraversalPolicyProvider(true);
        h5.setName("h5"); // NOI18N
        FormInput.add(h5);
        h5.setBounds(700, 380, 40, 23);

        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(10, 490, 750, 30);

        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(10, 90, 750, 30);

        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(10, 130, 750, 30);

        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(10, 130, 750, 30);

        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(10, 170, 750, 30);

        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(10, 170, 750, 30);

        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(10, 210, 750, 30);

        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(10, 250, 750, 30);

        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(10, 290, 750, 30);

        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(10, 330, 750, 30);

        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(10, 370, 750, 30);

        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(10, 410, 750, 30);

        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(10, 450, 750, 30);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Kesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Kesimpulan.setColumns(20);
        Kesimpulan.setRows(5);
        Kesimpulan.setName("Kesimpulan"); // NOI18N
        Kesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesimpulanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Kesimpulan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(20, 550, 740, 90);

        TingkatResiko2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko2.setText("1 : Pasien menunjukan kemampuan minimal");
        TingkatResiko2.setToolTipText("");
        TingkatResiko2.setName("TingkatResiko2"); // NOI18N
        FormInput.add(TingkatResiko2);
        TingkatResiko2.setBounds(20, 690, 210, 23);

        TingkatResiko3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko3.setText("Petunjuk Penilaian :");
        TingkatResiko3.setToolTipText("");
        TingkatResiko3.setName("TingkatResiko3"); // NOI18N
        FormInput.add(TingkatResiko3);
        TingkatResiko3.setBounds(10, 650, 100, 23);

        TingkatResiko4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko4.setText("2 : Pasien menunjukan kemampuan sedang");
        TingkatResiko4.setToolTipText("");
        TingkatResiko4.setName("TingkatResiko4"); // NOI18N
        FormInput.add(TingkatResiko4);
        TingkatResiko4.setBounds(20, 710, 210, 23);

        TingkatResiko5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko5.setText("3 : Pasien menunjukan kemampuan maksimal");
        TingkatResiko5.setToolTipText("");
        TingkatResiko5.setName("TingkatResiko5"); // NOI18N
        FormInput.add(TingkatResiko5);
        TingkatResiko5.setBounds(20, 730, 220, 23);

        TingkatResiko6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko6.setText("0 : Pasien sama sekali tidak mampu menunjukan kemampuan");
        TingkatResiko6.setToolTipText("");
        TingkatResiko6.setName("TingkatResiko6"); // NOI18N
        FormInput.add(TingkatResiko6);
        TingkatResiko6.setBounds(20, 670, 300, 23);

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
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(Sequel.menyimpantf("penilaian_terapi_aktivitas_kelompok","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",55,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),NIP.getText(),
                a1.getText(),a2.getText(),a3.getText(),a4.getText(),a5.getText(),
                b1.getText(),b2.getText(),b3.getText(),b4.getText(),b5.getText(),
                c1.getText(),c2.getText(),c3.getText(),c4.getText(),c5.getText(),
                d1.getText(),d2.getText(),d3.getText(),d4.getText(),d5.getText(),
                e1.getText(),e2.getText(),e3.getText(),e4.getText(),e5.getText(),
                f1.getText(),f2.getText(),f3.getText(),f4.getText(),f5.getText(),
                g1.getText(),g2.getText(),g3.getText(),g4.getText(),g5.getText(),
                h1.getText(),h2.getText(),h3.getText(),h4.getText(),h5.getText(),
                i1.getText(),i2.getText(),i3.getText(),i4.getText(),i5.getText(),
                j1.getText(),j2.getText(),j3.getText(),j4.getText(),j5.getText(),Jumlah.getText(),Kesimpulan.getText()
            })==true){
               tampil();
                emptTeks();
                LCount.setText(""+tabMode.getRowCount());
            }  
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
//            Valid.pindah(evt,FaktorPencegahan,BtnBatal);
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
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
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
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString())){
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
        petugas.dispose();
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
//                            +style=\"color:#00FFE3;\"
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.R.M.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>JK</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle'  bgcolor='#FFFAF8' align='center'><b>Faktor Statik 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Statik 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Statik 9</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml Skor Statik</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 1</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 2</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 3</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 4</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 5</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 6</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 7</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor Dinamis 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Skor Dinamis 8</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Jml Skor Dinamis</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Faktor-faktor Pencegahan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Total Skor</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Level Skor</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NIP</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Petugas</b></td>"+
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
        Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
//        Valid.pindah(evt,Detik,FaktorStatik1);
    }//GEN-LAST:event_btnPetugasKeyPressed

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
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),46).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),45).toString():finger)+"\n"+Tanggal.getSelectedItem());
            Valid.MyReportqry("rptFormulirPenilaianTambahanMelarikanDiri.jasper","report","::[ Formulir Penilaian Tambahan Berisiko Melarikan Diri ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_tambahan_beresiko_melarikan_diri.tanggal,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y')as tgl_registrasi,reg_periksa.jam_reg,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_riwayat_melarikan_diri,penilaian_tambahan_beresiko_melarikan_diri.statik_skorriwayat_melarikan_diri,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_riwayat_penolakan_pengobatan,penilaian_tambahan_beresiko_melarikan_diri.statik_skorriwayat_penolakan_pengobatan,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_usia_dibawah_35,penilaian_tambahan_beresiko_melarikan_diri.statik_skorusia_dibawah_35,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_laki_laki,penilaian_tambahan_beresiko_melarikan_diri.statik_skorlaki_laki,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_diagnosis_skizofrenia,penilaian_tambahan_beresiko_melarikan_diri.statik_skordiagnosis_skizofrenia,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_belum_menikah,penilaian_tambahan_beresiko_melarikan_diri.statik_skorbelum_menikah,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_riwayat_penggunaan_napza,penilaian_tambahan_beresiko_melarikan_diri.statik_skoriwayat_penggunaan_napza,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_diagnosis_gangguan_kepribadian,penilaian_tambahan_beresiko_melarikan_diri.statik_skordiagnosis_gangguan_kepribadian,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_riwayat_kriminal,penilaian_tambahan_beresiko_melarikan_diri.statik_skorriwayat_kriminal,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.statik_skortotal,penilaian_tambahan_beresiko_melarikan_diri.dinamis_anti_treatment,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skoranti_treatment,penilaian_tambahan_beresiko_melarikan_diri.dinamis_penggunaan_napza,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorpenggunaan_napza,penilaian_tambahan_beresiko_melarikan_diri.dinamis_kebosanan,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorkebosanan,penilaian_tambahan_beresiko_melarikan_diri.dinamis_perintah_halusinasi,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorperintah_halusinasi,penilaian_tambahan_beresiko_melarikan_diri.dinamis_hilangnya_kontrol_diri,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorhilangnya_kontrol_diri,penilaian_tambahan_beresiko_melarikan_diri.dinamis_seksual_tidak_wajar,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorseksual_tidak_wajar,penilaian_tambahan_beresiko_melarikan_diri.dinamis_kemarahan_frustasi,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorkemarahan_frustasi,penilaian_tambahan_beresiko_melarikan_diri.dinamis_ketakutan_perawatan,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.dinamis_skorketakutan_perawatan,penilaian_tambahan_beresiko_melarikan_diri.dinamis_skortotal,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.faktor_faktor_pencegahan,penilaian_tambahan_beresiko_melarikan_diri.total_skor,"+
                    "penilaian_tambahan_beresiko_melarikan_diri.level_skor,penilaian_tambahan_beresiko_melarikan_diri.nip,petugas.nama,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.umurdaftar,reg_periksa.sttsumur "+
                    "from penilaian_tambahan_beresiko_melarikan_diri inner join reg_periksa on penilaian_tambahan_beresiko_melarikan_diri.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on penilaian_tambahan_beresiko_melarikan_diri.nip=petugas.nip inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianTambahanMelarikanDiriActionPerformed

    private void KesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesimpulanKeyPressed
        //        Valid.pindah2(evt,KeluhanUtama,RiwayatPenyakitDahulu);
    }//GEN-LAST:event_KesimpulanKeyPressed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a1ActionPerformed

    private void j1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j1ActionPerformed

    private void JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JumlahActionPerformed

    private void j5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j5ActionPerformed
        // TODO add your handling code here:
       total();
    }//GEN-LAST:event_j5ActionPerformed

    private void j5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_j5PropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_j5PropertyChange

    public void total(){
        
//        Jumlah.setText((Integer.parseInt(a1.getText())));
        
       int q=Integer.valueOf(a1.getText());
        int w=Integer.valueOf(a2.getText());
        int eq=Integer.valueOf(a3.getText());
        int r=Integer.valueOf(a4.getText());
        int t=Integer.valueOf(a5.getText());
        
        int y=Integer.valueOf(b1.getText());
        int u=Integer.valueOf(b2.getText());
        int i=Integer.valueOf(b3.getText());
        int o=Integer.valueOf(b4.getText());
        int p=Integer.valueOf(b5.getText());
        
        int a=Integer.valueOf(c1.getText());
        int s=Integer.valueOf(c2.getText());
        int d=Integer.valueOf(c3.getText());
        int f=Integer.valueOf(c4.getText());
        int g=Integer.valueOf(c5.getText());
        
        int h=Integer.valueOf(d1.getText());
        int j=Integer.valueOf(d2.getText());
        int k=Integer.valueOf(d3.getText());
        int l=Integer.valueOf(d4.getText());
        int z=Integer.valueOf(d5.getText());
        
        int x=Integer.valueOf(e1.getText());
        int c=Integer.valueOf(e2.getText());
        int v=Integer.valueOf(e3.getText());
        int b=Integer.valueOf(e4.getText());
        int n=Integer.valueOf(e5.getText());
     
        int m=Integer.valueOf(f1.getText());
        int qq=Integer.valueOf(f2.getText());
        int ww=Integer.valueOf(f3.getText());
        int ee=Integer.valueOf(f4.getText());
        int rr=Integer.valueOf(f5.getText());
        
        int tt=Integer.valueOf(g1.getText());
        int yy=Integer.valueOf(g2.getText());
        int uu=Integer.valueOf(g3.getText());
        int ii=Integer.valueOf(g4.getText());
        int oo=Integer.valueOf(g5.getText());
        
        int pp=Integer.valueOf(h1.getText());
        int aa=Integer.valueOf(h2.getText());
        int ss=Integer.valueOf(h3.getText());
        int dd=Integer.valueOf(h4.getText());
        int ff=Integer.valueOf(h5.getText());
        
        int gg=Integer.valueOf(i1.getText());
        int hh=Integer.valueOf(i2.getText());
        int jj=Integer.valueOf(i3.getText());
        int kk=Integer.valueOf(i4.getText());
        int ll=Integer.valueOf(i5.getText());
        
        int zz=Integer.valueOf(j1.getText());
        int xx=Integer.valueOf(j2.getText());
        int cc=Integer.valueOf(j3.getText());
        int vv=Integer.valueOf(j4.getText());
        int bb=Integer.valueOf(j5.getText());
      
       
        
        int total1 = q + w + eq + r + t;
        int total2 = y + u + i + o + p;
        int total3 = a + s + d + f + g;
        int total4 = h + j + k + l + z;
        int total5 = x + c + v + b + n;
        int total6 = m + qq + ww + ee + rr;
        int total7 = tt + yy + uu + ii + oo;
        int total8 = pp + aa + ss + dd + ff;
        int total9 = gg + hh + jj + kk + ll;
        int total10 = zz + xx + cc + vv + bb;
        
        int totals=total1 + total2+total3+total4+total5+total6+total7+total8+total9+total10;
        
//        String ddd=18;

    try {
        Jumlah.setText(Integer.toString(totals));
//            textBox9.setText((Integer.parseInt(textBox1.getText()))+(Integer.parseInt(textBox2.getText()))+(Integer.parseInt(textBox3.getText()))+(Integer.parseInt(textBox4.getText()))+(Integer.parseInt(textBox5.getText()))+(Integer.parseInt(textBox6.getText()))+(Integer.parseInt(textBox7.getText()))+(Integer.parseInt(textBox8.getText()))+"");
        } catch (Exception e) {
            Jumlah.setText("0");
        }
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianTerapiAktivitasKelompok dialog = new RMPenilaianTerapiAktivitasKelompok(new javax.swing.JFrame(), true);
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
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.TextBox Jumlah;
    private widget.TextArea Kesimpulan;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnPenilaianTambahanMelarikanDiri;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.Label TingkatResiko1;
    private widget.Label TingkatResiko2;
    private widget.Label TingkatResiko3;
    private widget.Label TingkatResiko4;
    private widget.Label TingkatResiko5;
    private widget.Label TingkatResiko6;
    private widget.TextBox a1;
    private widget.TextBox a2;
    private widget.TextBox a3;
    private widget.TextBox a4;
    private widget.TextBox a5;
    private widget.TextBox b1;
    private widget.TextBox b2;
    private widget.TextBox b3;
    private widget.TextBox b4;
    private widget.TextBox b5;
    private widget.Button btnPetugas;
    private widget.TextBox c1;
    private widget.TextBox c2;
    private widget.TextBox c3;
    private widget.TextBox c4;
    private widget.TextBox c5;
    private widget.TextBox d1;
    private widget.TextBox d2;
    private widget.TextBox d3;
    private widget.TextBox d4;
    private widget.TextBox d5;
    private widget.TextBox e1;
    private widget.TextBox e2;
    private widget.TextBox e3;
    private widget.TextBox e4;
    private widget.TextBox e5;
    private widget.TextBox f1;
    private widget.TextBox f2;
    private widget.TextBox f3;
    private widget.TextBox f4;
    private widget.TextBox f5;
    private widget.TextBox g1;
    private widget.TextBox g2;
    private widget.TextBox g3;
    private widget.TextBox g4;
    private widget.TextBox g5;
    private widget.TextBox h1;
    private widget.TextBox h2;
    private widget.TextBox h3;
    private widget.TextBox h4;
    private widget.TextBox h5;
    private widget.TextBox i1;
    private widget.TextBox i2;
    private widget.TextBox i3;
    private widget.TextBox i4;
    private widget.TextBox i5;
    private widget.InternalFrame internalFrame1;
    private widget.TextBox j1;
    private widget.TextBox j2;
    private widget.TextBox j3;
    private widget.TextBox j4;
    private widget.TextBox j5;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel217;
    private widget.Label jLabel220;
    private widget.Label jLabel223;
    private widget.Label jLabel226;
    private widget.Label jLabel229;
    private widget.Label jLabel230;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel236;
    private widget.Label jLabel4;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,"+
                    "penilaian_terapi_aktivitas_kelompok.no_rawat,\n" +
"  penilaian_terapi_aktivitas_kelompok.tanggal,\n" +
"  penilaian_terapi_aktivitas_kelompok.nip,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon5,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic1,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic2,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic3,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic4,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic5,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team5,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku1,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku2,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku3,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku4,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku5,\n" +
"  penilaian_terapi_aktivitas_kelompok.jumlah,\n" +
"  penilaian_terapi_aktivitas_kelompok.kesimpulan"
                            + ",petugas.nama "+
                    "from penilaian_terapi_aktivitas_kelompok inner join reg_periksa on penilaian_terapi_aktivitas_kelompok.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_terapi_aktivitas_kelompok.nip=petugas.nip where "+
                    "penilaian_terapi_aktivitas_kelompok.tanggal between ? and ? order by penilaian_terapi_aktivitas_kelompok.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,"+
                    "penilaian_terapi_aktivitas_kelompok.no_rawat,\n" +
"  penilaian_terapi_aktivitas_kelompok.tanggal,\n" +
"  penilaian_terapi_aktivitas_kelompok.nip,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berkomunikasi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_menyampaikan_ide5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_mengendalikan_komunikasi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_berorientasi_realita5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_instruksi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kemampuan_memberikan_respon5,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic1,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic2,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic3,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic4,\n" +
"  penilaian_terapi_aktivitas_kelompok.beraktivitas_sesuai_topic5,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi1,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi2,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi3,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi4,\n" +
"  penilaian_terapi_aktivitas_kelompok.mengikuti_instruksi5,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team1,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team2,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team3,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team4,\n" +
"  penilaian_terapi_aktivitas_kelompok.kerjasama_team5,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku1,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku2,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku3,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku4,\n" +
"  penilaian_terapi_aktivitas_kelompok.demonstrasi_prilaku5,\n" +
"  penilaian_terapi_aktivitas_kelompok.jumlah,\n" +
"  penilaian_terapi_aktivitas_kelompok.kesimpulan,"+
                    "petugas.nama "+
                    "from penilaian_terapi_aktivitas_kelompok inner join reg_periksa on penilaian_terapi_aktivitas_kelompok.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_terapi_aktivitas_kelompok.nip=petugas.nip where "+
                    "penilaian_terapi_aktivitas_kelompok.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_terapi_aktivitas_kelompok.nip like ? or petugas.nama like ?) "+
                    "order by penilaian_terapi_aktivitas_kelompok.tanggal ");
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
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("tanggal"),
                        
                        "Komunikasi sesuai Topik :",rs.getString("kemampuan_berkomunikasi1"),rs.getString("kemampuan_berkomunikasi2"),rs.getString("kemampuan_berkomunikasi3"),rs.getString("kemampuan_berkomunikasi4"),rs.getString("kemampuan_berkomunikasi5"),
                        "Menyampaikan ide :",rs.getString("kemampuan_menyampaikan_ide1"),rs.getString("kemampuan_menyampaikan_ide2"),rs.getString("kemampuan_menyampaikan_ide3"),rs.getString("kemampuan_menyampaikan_ide4"),rs.getString("kemampuan_menyampaikan_ide5"),
                        "Mengendalikan Komunikasi :",rs.getString("kemampuan_mengendalikan_komunikasi1"),rs.getString("kemampuan_mengendalikan_komunikasi2"),rs.getString("kemampuan_mengendalikan_komunikasi3"),rs.getString("kemampuan_mengendalikan_komunikasi4"),rs.getString("kemampuan_mengendalikan_komunikasi5"),
                        "Berorientasi realitas :",rs.getString("kemampuan_berorientasi_realita1"),rs.getString("kemampuan_berorientasi_realita2"),rs.getString("kemampuan_berorientasi_realita3"),rs.getString("kemampuan_berorientasi_realita4"),rs.getString("kemampuan_berorientasi_realita5"),
                        "Memberikan instruksi :",rs.getString("kemampuan_memberikan_instruksi1"),rs.getString("kemampuan_memberikan_instruksi2"),rs.getString("kemampuan_memberikan_instruksi3"),rs.getString("kemampuan_memberikan_instruksi4"),rs.getString("kemampuan_memberikan_instruksi5"),
                        "Memberikan respon :",rs.getString("kemampuan_memberikan_respon1"),rs.getString("kemampuan_memberikan_respon2"),rs.getString("kemampuan_memberikan_respon3"),rs.getString("kemampuan_memberikan_respon4"),rs.getString("kemampuan_memberikan_respon5"),
                        "Beraktivitas sesuai topic :",rs.getString("beraktivitas_sesuai_topic1"),rs.getString("beraktivitas_sesuai_topic2"),rs.getString("beraktivitas_sesuai_topic3"),rs.getString("beraktivitas_sesuai_topic4"),rs.getString("beraktivitas_sesuai_topic5"),
                        "Mengikuti aturan/instruksi :",rs.getString("mengikuti_instruksi1"),rs.getString("mengikuti_instruksi2"),rs.getString("mengikuti_instruksi3"),rs.getString("mengikuti_instruksi4"),rs.getString("mengikuti_instruksi5"),
                        "Bekerjasama secara team :",rs.getString("kerjasama_team1"),rs.getString("kerjasama_team2"),rs.getString("kerjasama_team3"),rs.getString("kerjasama_team4"),rs.getString("kerjasama_team5"),
                        " Mendemonstrasikan prilaku adaptif sesuai dengan masalah yang dihadapi :",rs.getString("demonstrasi_prilaku1"),rs.getString("demonstrasi_prilaku2"),rs.getString("demonstrasi_prilaku3"),rs.getString("demonstrasi_prilaku4"),rs.getString("demonstrasi_prilaku5"),
                        rs.getString("jumlah"),rs.getString("kesimpulan"),rs.getString("nip"),rs.getString("nama"),
                        
//                        
//                        rs.getString("statik_skorriwayat_melarikan_diri"),rs.getString("statik_riwayat_penolakan_pengobatan"),
//                        rs.getString("statik_skorriwayat_penolakan_pengobatan"),rs.getString("statik_usia_dibawah_35"),rs.getString("statik_skorusia_dibawah_35"),rs.getString("statik_laki_laki"),
//                        rs.getString("statik_skorlaki_laki"),rs.getString("statik_diagnosis_skizofrenia"),rs.getString("statik_skordiagnosis_skizofrenia"),rs.getString("statik_belum_menikah"),
//                        rs.getString("statik_skorbelum_menikah"),rs.getString("statik_riwayat_penggunaan_napza"),rs.getString("statik_skoriwayat_penggunaan_napza"),
//                        rs.getString("statik_diagnosis_gangguan_kepribadian"),rs.getString("statik_skordiagnosis_gangguan_kepribadian"),rs.getString("statik_riwayat_kriminal"),
//                        rs.getString("statik_skorriwayat_kriminal"),rs.getString("statik_skortotal"),rs.getString("dinamis_anti_treatment"),rs.getString("dinamis_skoranti_treatment"),
//                        rs.getString("dinamis_penggunaan_napza"),rs.getString("dinamis_skorpenggunaan_napza"),rs.getString("dinamis_kebosanan"),rs.getString("dinamis_skorkebosanan"),
//                        rs.getString("dinamis_perintah_halusinasi"),rs.getString("dinamis_skorperintah_halusinasi"),rs.getString("dinamis_hilangnya_kontrol_diri"),
//                        rs.getString("dinamis_skorhilangnya_kontrol_diri"),rs.getString("dinamis_seksual_tidak_wajar"),rs.getString("dinamis_skorseksual_tidak_wajar"),
//                        rs.getString("dinamis_kemarahan_frustasi"),rs.getString("dinamis_skorkemarahan_frustasi"),rs.getString("dinamis_ketakutan_perawatan"),
//                        rs.getString("dinamis_skorketakutan_perawatan"),rs.getString("dinamis_skortotal"),rs.getString("faktor_faktor_pencegahan"),rs.getString("total_skor"),
//                        rs.getString("level_skor"),rs.getString("nip"),rs.getString("nama")
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
        TNoRw.setText(""); 
            TNoRM.setText("");
            TPasien.setText("");
            TglLahir.setText("");
            JK.setText("");
            
            a1.setText("");
            a2.setText("");
            a3.setText("");
            a4.setText("");
            a5.setText("");
            
            b1.setText("");
            b2.setText("");
            b3.setText("");
            b4.setText("");
            b5.setText("");
            
            c1.setText("");
            c2.setText("");
            c3.setText("");
            c4.setText("");
            c5.setText("");
            
            d1.setText("");
            d2.setText("");
            d3.setText("");
            d4.setText("");
            d5.setText("");
            
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
           
            f1.setText("");
            f2.setText("");
            f3.setText("");
            f4.setText("");
            f5.setText("");
            
            g1.setText("");
            g2.setText("");
            g3.setText("");
            g4.setText("");
            g5.setText("");
            
            h1.setText("");
            h2.setText("");
            h3.setText("");
            h4.setText("");
            h5.setText("");
            
            i1.setText("");
            i2.setText("");
            i3.setText("");
            i4.setText("");
            i5.setText("");
            
            j1.setText("");
            j2.setText("");
            j3.setText("");
            j4.setText("");
            j5.setText("");
            Jumlah.setText("");
            
            Kesimpulan.setText("");
            NIP.setText("");
            NamaPetugas.setText("");
//        FaktorStatik1.setSelectedIndex(0);
//        SkorStatik1.setText("");
//        FaktorStatik2.setSelectedIndex(0);
//        SkorStatik2.setText("0");
//        FaktorStatik3.setSelectedIndex(0);
//        SkorStatik3.setText("0");
//        FaktorStatik4.setSelectedIndex(0);
//        SkorStatik4.setText("0");
//        FaktorStatik5.setSelectedIndex(0);
//        SkorStatik5.setText("0");
//        FaktorStatik6.setSelectedIndex(0);
//        SkorStatik6.setText("0");
//        FaktorStatik7.setSelectedIndex(0);
//        SkorStatik7.setText("0");
//        FaktorStatik8.setSelectedIndex(0);
//        SkorStatik8.setText("0");
//        FaktorStatik9.setSelectedIndex(0);
//        SkorStatik9.setText("0");
//        TotalStatik.setText("0");
//        FaktorDinamis1.setSelectedIndex(0);
//        SkorDinamis1.setText("0");
//        FaktorDinamis2.setSelectedIndex(0);
//        SkorDinamis2.setText("0");
//        FaktorDinamis3.setSelectedIndex(0);
//        SkorDinamis3.setText("0");
//        FaktorDinamis4.setSelectedIndex(0);
//        SkorDinamis4.setText("0");
//        FaktorDinamis5.setSelectedIndex(0);
//        SkorDinamis5.setText("0");
//        FaktorDinamis6.setSelectedIndex(0);
//        SkorDinamis6.setText("0");
//        FaktorDinamis7.setSelectedIndex(0);
//        SkorDinamis7.setText("0");
//        FaktorDinamis8.setSelectedIndex(0);
//        SkorDinamis8.setText("0");
//        TotalDinamis.setText("0");
//        SkorTotal.setText("0");
//        Level.setText("Rendah(<7)");
//        FaktorPencegahan.setText("");
//        FaktorStatik1.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            
            
            a1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            a2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            a3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            a4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            a5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            
            b1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            b2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            b3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            b4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            b5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            
            c1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            c2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            c3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            c4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            c5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            
            d1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            d2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            d3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            d4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            d5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            
            e1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            e2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            e3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            e4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            e5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
           
            f1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            f2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            f3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            f4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            f5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            
            g1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            g2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            g3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            g4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            g5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            
            h1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            h2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            h3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            h4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),52).toString());
            h5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
            
            i1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),55).toString());
            i2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),56).toString());
            i3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),57).toString());
            i4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),58).toString());
            i5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),59).toString());
            
            j1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),61).toString());
            j2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),62).toString());
            j3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),63).toString());
            j4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),64).toString());
            j5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),65).toString());
            Jumlah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),66).toString());
            
            Kesimpulan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),67).toString());
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),68).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),69).toString());
            
            
            
            Jam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(11,13));
            Menit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(14,16));
            Detik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString().substring(17,19));
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
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
        BtnSimpan.setEnabled(akses.get_penilaian_aktivitas_kelompok());
        BtnHapus.setEnabled(akses.get_penilaian_aktivitas_kelompok());
        BtnEdit.setEnabled(akses.get_penilaian_aktivitas_kelompok());
        BtnPrint.setEnabled(akses.get_penilaian_aktivitas_kelompok()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
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
        if(Sequel.mengedittf("penilaian_terapi_aktivitas_kelompok","no_rawat=?",
                "no_rawat=?,tanggal=?,nip=?,kemampuan_berkomunikasi1=?,kemampuan_berkomunikasi2=?,"
                + "kemampuan_berkomunikasi3=?,kemampuan_berkomunikasi4=?,kemampuan_berkomunikasi5=?,"
                + "kemampuan_menyampaikan_ide1=?,kemampuan_menyampaikan_ide2=?,kemampuan_menyampaikan_ide3=?,"
                + "kemampuan_menyampaikan_ide4=?,kemampuan_menyampaikan_ide5=?,kemampuan_mengendalikan_komunikasi1=?,"
                + "kemampuan_mengendalikan_komunikasi2=?,kemampuan_mengendalikan_komunikasi3=?,kemampuan_mengendalikan_komunikasi4=?,"
                + "kemampuan_mengendalikan_komunikasi5=?,kemampuan_berorientasi_realita1=?,kemampuan_berorientasi_realita2=?,kemampuan_berorientasi_realita3=?,"
                + "kemampuan_berorientasi_realita4=?,kemampuan_berorientasi_realita5=?,kemampuan_memberikan_instruksi1=?,kemampuan_memberikan_instruksi2=?,"
                + "kemampuan_memberikan_instruksi3=?,kemampuan_memberikan_instruksi4=?,kemampuan_memberikan_instruksi5=?,kemampuan_memberikan_respon1=?,"
                + "kemampuan_memberikan_respon2=?,kemampuan_memberikan_respon3=?,kemampuan_memberikan_respon4=?,kemampuan_memberikan_respon5=?,"
                + "beraktivitas_sesuai_topic1=?,beraktivitas_sesuai_topic2=?,beraktivitas_sesuai_topic3=?,beraktivitas_sesuai_topic4=?,"
                + "beraktivitas_sesuai_topic5=?,mengikuti_instruksi1=?,mengikuti_instruksi2=?,mengikuti_instruksi3=?,mengikuti_instruksi4=?,"
                + "mengikuti_instruksi5=?,kerjasama_team1=?,kerjasama_team2=?,kerjasama_team3=?,kerjasama_team4=?,kerjasama_team5=?,"
                + "demonstrasi_prilaku1=?,demonstrasi_prilaku2=?,demonstrasi_prilaku3=?,demonstrasi_prilaku4=?,demonstrasi_prilaku5=?,jumlah=?,"
                + "kesimpulan=?"
                ,56,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),NIP.getText(),
                a1.getText(),a2.getText(),a3.getText(),a4.getText(),a5.getText(),
                b1.getText(),b2.getText(),b3.getText(),b4.getText(),b5.getText(),
                c1.getText(),c2.getText(),c3.getText(),c4.getText(),c5.getText(),
                d1.getText(),d2.getText(),d3.getText(),d4.getText(),d5.getText(),
                e1.getText(),e2.getText(),e3.getText(),e4.getText(),e5.getText(),
                f1.getText(),f2.getText(),f3.getText(),f4.getText(),f5.getText(),
                g1.getText(),g2.getText(),g3.getText(),g4.getText(),g5.getText(),
                h1.getText(),h2.getText(),h3.getText(),h4.getText(),h5.getText(),
                i1.getText(),i2.getText(),i3.getText(),i4.getText(),i5.getText(),
                j1.getText(),j2.getText(),j3.getText(),j4.getText(),j5.getText(),Jumlah.getText(),Kesimpulan.getText(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
            })==true){
            tampil();
            emptTeks();
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_terapi_aktivitas_kelompok where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
//    private void isTotalSkor(){
//        try {
//            TotalStatik.setText((Integer.parseInt(SkorStatik1.getText())+Integer.parseInt(SkorStatik2.getText())+Integer.parseInt(SkorStatik3.getText())+Integer.parseInt(SkorStatik4.getText())+Integer.parseInt(SkorStatik5.getText())+Integer.parseInt(SkorStatik6.getText())+Integer.parseInt(SkorStatik7.getText())+Integer.parseInt(SkorStatik8.getText())+Integer.parseInt(SkorStatik9.getText()))+"");
//            TotalDinamis.setText((Integer.parseInt(SkorDinamis1.getText())+Integer.parseInt(SkorDinamis2.getText())+Integer.parseInt(SkorDinamis3.getText())+Integer.parseInt(SkorDinamis4.getText())+Integer.parseInt(SkorDinamis5.getText())+Integer.parseInt(SkorDinamis6.getText())+Integer.parseInt(SkorDinamis7.getText())+Integer.parseInt(SkorDinamis8.getText()))+"");
//            SkorTotal.setText((Integer.parseInt(TotalStatik.getText())+Integer.parseInt(TotalDinamis.getText()))+"");
//            if(Integer.parseInt(SkorTotal.getText())<7){
//                Level.setText("Rendah(<7)");
//            }else if(Integer.parseInt(SkorTotal.getText())<=14){
//                Level.setText("Sedang(7-14)");
//            }else if(Integer.parseInt(SkorTotal.getText())>14){
//                Level.setText("Tinggi(>14)");
//            }
//        } catch (Exception e) {
//            SkorTotal.setText("0");
//            Level.setText("Rendah(<7)");
//        }
//    }
}
