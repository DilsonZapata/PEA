/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pea;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author dzapata
 */
public class conf extends javax.swing.JDialog {

    String AbsolutePath = "";
    static String print = "";

    /**
     * Creates new form conf
     */
    public conf(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AbsolutePath = new java.io.File("").getAbsolutePath().replace("\\", "\\\\");
        leerXML(AbsolutePath + "\\confDM.xml");
        this.setLocationRelativeTo(parent);
        ImageIcon imgicon = new ImageIcon(getClass().getResource("/icon/conf.png"));
        this.setIconImage(imgicon.getImage());

        //Preguntar si desea cerrar
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
    }

    private void handleClosing() {

        showOptionDialog sh = new showOptionDialog(this, true);

        sh.txtMessaje.setText("Salir de configuracion?");
        sh.setVisible(true);
        if (sh.isV()) {
            {
                this.dispose();
            }
        }
    }

    public String getPrint() {
        return print;
    }

    public static void setPrint(String print) {
        conf.print = print;
    }

    public void leerXML(String dir) {
        try {

            SAXBuilder sb = new SAXBuilder();
            Document doc = (Document) sb.build(new File(dir));
            Element nRoot = doc.getRootElement();

            List<Element> child = nRoot.getChildren();

            for (Element i : child) {

                List<Element> child2 = i.getChildren();
                for (Element o : child2) {

                    if (o.getName().equals("email")) {
                        txtEmail.setText(o.getValue());
                    } else if (o.getName().equals("pass")) {
                        txtPass.setText(o.getValue());
                    } else if (o.getName().equals("print")) {
                        txtImpresora.setText(o.getValue());
                    } else if (o.getName().equals("deleF")) {

                        ckDelete._setValueStr(o.getValue());
                    }

                }
            }

        } catch (IOException | JDOMException ex) {
            System.err.println("Error -->\n" + ex.getMessage());
        }
    }

    public boolean modificarXML(String dir) {
        try {
            SAXBuilder build = new SAXBuilder(); //Instanciamos la clase SAXBuilder para poder obtener la extructura del documento XML.
            Document doc = (Document) build.build(new File(dir)); //Leemos el XML para convertirlo en Document.
            Element root = doc.getRootElement(); //Obtenemos el elemento root del documento.
            List<Element> child = root.getChildren(); //Obtenemos a los hijos pertenecientes al root, el cual nos trae en forma de lista.
            /*
                Creamos un for tipo foreach el cual leeremos todos los Elements que tiene cada Element.
                De cada Element sacamos sus hijos y le mandamos el nuevo texto que tendra cada etiqueta
             */
            for (Element i : child) {
                List<Element> ch = i.getChildren();

                for (Element o : ch) {
                    if (o.getName().equals("email")) {
                        o.setText(txtEmail.getText());
                    } else if (o.getName().equals("pass")) {
                        o.setText(txtPass.getText());
                    } else if (o.getName().equals("print")) {
                        o.setText(txtImpresora.getText());
                    }
                    if (o.getName().equals("deleF")) {
                        o.setText(ckDelete._getValueStr());
                    }

                }

            }
            XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(new File(dir));
            xmlOut.output(doc, fw);
            fw.close();
            return true;
        } catch (IOException | JDOMException ex) {
            System.err.println("Error modificarXML\n" + ex.getMessage());
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sbLabel1 = new Controls.SbLabel();
        sbLabel2 = new Controls.SbLabel();
        sbLabel3 = new Controls.SbLabel();
        ckDelete = new Controls.SbCheckBox();
        sbButton1 = new Controls.SbButton();
        txtImpresora = new Controls.SbTextBox();
        txtEmail = new Controls.SbTextBox();
        txtPass = new Controls.SbTextBox();
        sbButton2 = new Controls.SbButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        sbLabel1.setText("Impresora");
        sbLabel1.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        sbLabel2.setText("Email");
        sbLabel2.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        sbLabel3.setText("Contraseña");
        sbLabel3.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        ckDelete.setText("Eliminar archivo despues de imprimir ?");
        ckDelete.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N

        sbButton1.setText("Guardar");
        sbButton1.setPreferredSize(new java.awt.Dimension(120, 225));
        sbButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton1ActionPerformed(evt);
            }
        });

        txtImpresora.setEditable(false);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        sbButton2.setText("Guardar");
        sbButton2.setMinimumSize(new java.awt.Dimension(120, 23));
        sbButton2.setPreferredSize(new java.awt.Dimension(120, 23));
        sbButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sbLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sbLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sbLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(sbButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sbButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(ckDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(sbButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void sbButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton1ActionPerformed
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        String email = txtEmail.getText();

        Matcher mather = pattern.matcher(email);

        //Si es un email valido
        if (mather.find() == true) {
          
            if(  modificarXML(AbsolutePath + "\\confDM.xml"))
            JOptionPane.showMessageDialog(null,"Configuracion actualizada");
            leerXML(AbsolutePath + "\\confDM.xml");
        }
    }//GEN-LAST:event_sbButton1ActionPerformed

    private void sbButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton2ActionPerformed
        Prints P = new Prints(this, true);
        P.setVisible(true);
        txtImpresora.setText(this.print);
    }//GEN-LAST:event_sbButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                conf dialog = new conf(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Controls.SbCheckBox ckDelete;
    private Controls.SbButton sbButton1;
    private Controls.SbButton sbButton2;
    private Controls.SbLabel sbLabel1;
    private Controls.SbLabel sbLabel2;
    private Controls.SbLabel sbLabel3;
    private Controls.SbTextBox txtEmail;
    private Controls.SbTextBox txtImpresora;
    private Controls.SbTextBox txtPass;
    // End of variables declaration//GEN-END:variables
}
