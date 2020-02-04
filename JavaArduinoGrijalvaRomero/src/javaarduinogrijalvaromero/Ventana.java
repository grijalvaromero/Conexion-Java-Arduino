/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinogrijalvaromero;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaarduinogrijalvaromero.Ventana.statusHilo;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author grijalvaromero
 */
public class Ventana extends javax.swing.JFrame {
    private static ConexionSerial conexion = new ConexionSerial();
    static JFreeChart chart;
    static ChartPanel panel;
    int cont  = 0;    
    Thread hilo;
    public static boolean statusHilo=false;
    /**
     * Creates new form Ventana
     */
      
    public Ventana() {
        initComponents();
        String[] puertos = conexion.obtenerLista().split(",");
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        for(int x=0;x<puertos.length;x++){
            modelo.addElement(puertos[x]);
        }
        comboPuertos.setModel(modelo);
       
        
    }
    private void reiniciarHilo(){
        statusHilo=true;
         hilo=new Thread(){
          
            @Override
            public void run() {
               while(statusHilo){
               
                   try {
                       consola.setText(consola.getText()+"\n"+conexion.getTodo());
                       crearGrafica();
                       Thread.sleep(2000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(ConexionSerial.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
            
        };
    }
    XYSeries dataset = new XYSeries("Temperaturas 1");
    private void crearGrafica() {
        //https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm
       String[] lecturas= consola.getText().split("\n");
       if(lecturas.length > 0){
           if(!lecturas[lecturas.length-1].trim().equals("")){
               if(Double.parseDouble(lecturas[lecturas.length-1])<50 && Double.parseDouble(lecturas[lecturas.length-1])> -10){
                dataset.add(cont,Double.parseDouble(lecturas[lecturas.length-1]));
                cont++;
               }
           }
           
       }
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(dataset);
        
        
        chart = ChartFactory.createXYLineChart(
                            "Temperatura", // chart title
                            "Hora", // X axis label
                            "Temperatura", // Y axis label
                            collection, // data
                            PlotOrientation.VERTICAL, 
                            true, // include legend
                            true, // tooltips
                            false // urls
        );
      
        panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        XYPlot plot = chart.getXYPlot( );
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer ); 
        jPanel1.setLayout(null);
        panel.setBounds(0,0,jPanel1.getWidth(),jPanel1.getHeight());
        jPanel1.removeAll();
        jPanel1.add(panel);
        jPanel1.repaint();
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboPuertos = new javax.swing.JComboBox();
        btnConectar = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        comboPuertos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        lblEstado.setText("Desconectado");

        consola.setColumns(20);
        consola.setRows(5);
        jScrollPane1.setViewportView(consola);

        jButton1.setText("Prender LED");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Apagar LED");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(comboPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConectar)
                        .addGap(41, 41, 41)
                        .addComponent(lblEstado)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 223, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConectar)
                    .addComponent(lblEstado)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        // TODO add your handling code here:
        if(btnConectar.getText().equals("Conectar")){
            conexion.conectar(comboPuertos.getSelectedItem().toString());
            conexion.iniciarIO();
            conexion.initListener();
            if(conexion.getConectado()){
                lblEstado.setText("Conectado");
                btnConectar.setText("Desconectar");
                reiniciarHilo();
                hilo.start();
            }else{
                lblEstado.setText("DESCONECTADO");
                hilo.stop();
                statusHilo=false;
            }
        }else{
            btnConectar.setText("Conectar");
            conexion.desconectar();
            hilo.stop();
            statusHilo=false;
        }
        
    }//GEN-LAST:event_btnConectarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        conexion.desconectar();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        conexion.escribir(1);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          conexion.escribir(2);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JComboBox comboPuertos;
    private static javax.swing.JTextArea consola;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables

    
}
