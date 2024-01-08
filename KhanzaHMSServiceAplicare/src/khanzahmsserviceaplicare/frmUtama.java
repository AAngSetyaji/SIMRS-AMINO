/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanzahmsserviceaplicare;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.BPJSApiAplicare;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.Timer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author windiartonugroho
 */
public class frmUtama extends javax.swing.JFrame {
    private  Connection koneksi=koneksiDB.condb();
    private  sekuel Sequel=new sekuel();
    private  String requestJson,URL="",kodeppk=Sequel.cariIsi("select setting.kode_ppk from setting");
    private  BPJSApiAplicare api=new BPJSApiAplicare();
    private  HttpHeaders headers;
    private  HttpEntity requestEntity;
    private  ObjectMapper mapper= new ObjectMapper();
    private  JsonNode root;
    private  JsonNode nameNode;
    private  PreparedStatement ps;
    private  ResultSet rs;

    /**
     * Creates new form frmUtama
     */
    public frmUtama() {
        initComponents();
        
        this.setSize(390,340);
        
        jam();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TeksArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIMKES Khanza Service Aplicare");

        TeksArea.setColumns(20);
        TeksArea.setRows(5);
        jScrollPane1.setViewportView(TeksArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TeksArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
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
                nilai_jam = now.getHours();
                nilai_menit = now.getMinutes();
                nilai_detik = now.getSeconds();
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
                if(menit.equals("01")&&detik.equals("01")){
                    if(jam.equals("01")&&menit.equals("01")&&detik.equals("01")){
                        TeksArea.setText("");
                    }
                        
                    try {
                        koneksi=koneksiDB.condb();
                        TeksArea.append("Memulai update aplicare\n");
                        ps=koneksi.prepareStatement(
                                "select aplicare_ketersediaan_kamar.kode_kelas_aplicare,aplicare_ketersediaan_kamar.kd_bangsal," +
                                "bangsal.nm_bangsal,aplicare_ketersediaan_kamar.kelas,aplicare_ketersediaan_kamar.kapasitas," +
                                "aplicare_ketersediaan_kamar.tersedia,aplicare_ketersediaan_kamar.tersediapria," +
                                "aplicare_ketersediaan_kamar.tersediawanita,aplicare_ketersediaan_kamar.tersediapriawanita " +
                                "from aplicare_ketersediaan_kamar inner join bangsal on aplicare_ketersediaan_kamar.kd_bangsal=bangsal.kd_bangsal");
                        try {
                            rs=ps.executeQuery();
                            while(rs.next()){
                                TeksArea.append("Mengirimkan kamar "+rs.getString("kode_kelas_aplicare")+" "+rs.getString("nm_bangsal")+"\n");
                                try {     
                                    headers = new HttpHeaders();
                                    headers.setContentType(MediaType.APPLICATION_JSON);
                                    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIAPLICARE());
                                    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
                                    headers.add("X-Signature",api.getHmac());
                                    requestJson ="{\"kodekelas\":\""+rs.getString("kode_kelas_aplicare")+"\", "+
                                                  "\"koderuang\":\""+rs.getString("kd_bangsal")+"\","+ 
                                                  "\"namaruang\":\""+rs.getString("nm_bangsal")+"\","+ 
                                                  "\"kapasitas\":\""+Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and kd_bangsal='"+rs.getString("kd_bangsal")+"'")+"\","+ 
                                                  "\"tersedia\":\""+Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and kd_bangsal='"+rs.getString("kd_bangsal")+"' and status='KOSONG'")+"\","+
                                                  "\"tersediapria\":\""+Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and kd_bangsal='"+rs.getString("kd_bangsal")+"' and status='KOSONG'")+"\","+ 
                                                  "\"tersediawanita\":\""+Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and kd_bangsal='"+rs.getString("kd_bangsal")+"' and status='KOSONG'")+"\","+ 
                                                  "\"tersediapriawanita\":\""+Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"+rs.getString("kelas")+"' and kd_bangsal='"+rs.getString("kd_bangsal")+"' and status='KOSONG'")+"\""+
                                                  "}";
                                    TeksArea.append("JSON dikirim : "+requestJson+"\n");
                                    requestEntity = new HttpEntity(requestJson,headers);
                                    //System.out.println(rest.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                    root = mapper.readTree(api.getRest().exchange(URL+"/rest/bed/update/"+kodeppk, HttpMethod.POST, requestEntity, String.class).getBody());
                                    nameNode = root.path("metadata");
                                    TeksArea.append("respon WS BPJS : "+nameNode.path("message").asText()+"\n");
                                }catch (Exception ex) {
                                    System.out.println("Notifikasi Bridging : "+ex);
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Notif Ketersediaan : "+ex);
                        } finally{
                            if(rs!=null){
                                rs.close();
                            }
                            if(ps!=null){
                                ps.close();
                            }
                        }
                        TeksArea.append("Proses update selesai\n");
                    } catch (Exception ez) {
                        System.out.println("Notif : "+ez);
                    }
                }
            }
        };
        // Timer
        new Timer(600, taskPerformer).start();
    }
}
