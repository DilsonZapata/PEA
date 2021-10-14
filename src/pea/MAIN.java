/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pea;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.print.PrintService;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
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
public class MAIN extends javax.swing.JFrame implements Runnable {

    int c = 0;
    String SQLConnString = "";
    String vPath = "";
    String dbServer = "";
    String dbUser = "";
    String dbPassword = "";
    String dbPort = "";
    String dbName = "";
    Thread h1;
    int timeExeC;
    Boolean vStop = false;
    boolean validad = false;
    static String AbsolutePath = "";
    static String FILE;
    static String email = "";
    static String pass = "";
    static String print = "";
    String IsDelete = "";

    /**
     * Creates new form MAIN
     */
    public MAIN() throws HeadlessException {
        initComponents();
        h1 = new Thread(this);
        this.setResizable(false);
        AbsolutePath = new java.io.File("").getAbsolutePath().replace("\\", "\\\\");
        cmdList.setBackground(Color.decode("#EEEEEE"));
        ImageIcon imgicon = new ImageIcon(getClass().getResource("/icon/ck.png"));
        this.setLocationRelativeTo(null);
        this.setIconImage(imgicon.getImage());
        leerXML(AbsolutePath + "\\confDM.xml");

        //Preguntar si desea cerrar
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });

        setSize(480, 320);
        setLocationRelativeTo(null);

    }
    //Preguntar si quiere cerrar el programa

    private void handleClosing() {

        showOptionDialog sh = new showOptionDialog(this, true);

        sh.txtMessaje.setText("Desea cerrar la aplicacion?");
        sh.setVisible(true);
        if (sh.isV()) {
            {
                this.dispose();
            }
        }
    }
    //crea un xml con la configuracion predeterminada

    public boolean crearXML() {
        try {
            addMessageList("Creando archivo de configuracion....");
            Document doc = new Document(); //Creamos el documento que guardara toda la información.
            Element root = new Element("configuracion"); //Elemento root al cual nombraremos personas
            doc.setRootElement(root); //Añadimos nuestro elemento root al documento

            Element persona = new Element("conf");

            persona.addContent(new Element("email").setText("default@gmail.com"));
            persona.addContent(new Element("pass").setText("default"));
            persona.addContent(new Element("print").setText("default"));
            persona.addContent(new Element("deleF").setText("N"));

            root.addContent(persona);

            XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(new File(AbsolutePath, "confDM.xml"));
            xmlOut.output(doc, fw);
            fw.close();
            addMessageList("Archivo creado exitosamente....");
            return true;
        } catch (IOException ex) {
            System.err.println("Error crearXML\n" + ex.getMessage());
            return false;
        }
    }

    //Leer el xml
    public void leerXML(String dir) {
        File archivo = new File(dir);
        try {

            if (archivo.exists()) {
                SAXBuilder sb = new SAXBuilder();
                Document doc = (Document) sb.build(archivo);
                Element nRoot = doc.getRootElement();

                List<Element> child = nRoot.getChildren();

                for (Element i : child) {

                    List<Element> child2 = i.getChildren();
                    for (Element o : child2) {

                        if (o.getName().equals("email")) {
                            email = o.getValue();
                        } else if (o.getName().equals("pass")) {
                            pass = o.getValue();
                        } else if (o.getName().equals("print")) {
                            print = o.getValue();
                        } else if (o.getName().equals("deleF")) {
                            IsDelete = o.getValue();
                        }

                    }
                }
            } else {
                addMessageList("El archivo de configuracion no existe se creara uno por default");
                crearXML();
            }

        } catch (IOException | JDOMException ex) {
            System.err.println("Error -->\n" + ex.getMessage());
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

        sbButton1 = new Controls.SbButton();
        sbButton2 = new Controls.SbButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cmdList = new Controls.SbListBox<>();
        sbButton3 = new Controls.SbButton();
        sbButton4 = new Controls.SbButton();
        labelReloj = new Controls.SbLabel();
        sbButton5 = new Controls.SbButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        sbButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/g.png"))); // NOI18N
        sbButton1.setText("INICIAR");
        sbButton1.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        sbButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton1ActionPerformed(evt);
            }
        });

        sbButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/s.png"))); // NOI18N
        sbButton2.setText("DETENER");
        sbButton2.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        sbButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton2ActionPerformed(evt);
            }
        });

        cmdList.setFocusable(false);
        cmdList.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(cmdList);

        sbButton3.setText("Salir");
        sbButton3.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        sbButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton3ActionPerformed(evt);
            }
        });

        sbButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cl.png"))); // NOI18N
        sbButton4.setText("Limpiar");
        sbButton4.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        sbButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton4ActionPerformed(evt);
            }
        });

        labelReloj.setText("Tiempo:: 1 SEG");
        labelReloj.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N

        sbButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/c.png"))); // NOI18N
        sbButton5.setText("Conf");
        sbButton5.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        sbButton5.setPreferredSize(new java.awt.Dimension(120, 23));
        sbButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbButton5ActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sbButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sbButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sbButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sbButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(245, 245, 245)
                        .addComponent(sbButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sbButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(sbButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sbButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sbButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton1ActionPerformed

        if (vStop == false) {
            vStop = true;
            h1.resume();
            if (c == 0) {

                h1.start();

                c = 2;
            }

        }

    }//GEN-LAST:event_sbButton1ActionPerformed

    private void sbButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton2ActionPerformed

        vStop = false;
        h1.suspend();

    }//GEN-LAST:event_sbButton2ActionPerformed

    private void sbButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton3ActionPerformed

        if (vStop) {
            vStop = false;
            h1.suspend();
        }
        this.dispose();

    }//GEN-LAST:event_sbButton3ActionPerformed

    private void sbButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton4ActionPerformed
        cmdList._clear();
    }//GEN-LAST:event_sbButton4ActionPerformed

    private void sbButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbButton5ActionPerformed
            conf f = new conf(this, true);
        f.setVisible(true);
    }//GEN-LAST:event_sbButton5ActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MAIN().setVisible(true);

            }
        });

    }

    @Override
    public void run() {
        int t = 0;
        addMessageList("Programa iniciado...");
        while (vStop) {
            try {

                t++;

                ReadEmail();

                labelReloj.setText("time >>> " + t + " SEG");

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                addMessageList("error en el hilo ");
            }
        }
    }

    public boolean addMessageList(String message) {

        int z = cmdList._getItemCount();
        if (!message.isEmpty()) {
            cmdList._addItem(z, message);

        } else {
            return false;
        }
        return true;
    }

    public boolean ReadEmail() {

        boolean valid = false;
        if (!email.isEmpty() && !pass.isEmpty()) {
            //Se obtiene la Session
            Properties prop = new Properties();
            prop.setProperty("mail.pop3.starttls.enable", "false");
            prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.pop3.socketFactory.fallback", "false");
            prop.setProperty("mail.pop3.port", "995");
            prop.setProperty("mail.pop3.socketFactory.port", "995");
            Session sesion = Session.getInstance(prop);
            // sesion.setDebug(true);

            try {
                // Se obtiene el Store y el Folder, para poder leer el
                // correo.
                Store store = sesion.getStore("pop3");
                store.connect("pop.gmail.com", email, pass);
                Folder folder = store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);

                // Se obtienen los mensajes.
                Message[] mensajes = folder.getMessages();

                // Se escribe from y subject de cada mensaje
                for (int i = 0; i < mensajes.length; i++) {

                    addMessageList("From:" + mensajes[i].getFrom()[0].toString());
                    addMessageList("Subject:" + mensajes[i].getSubject());
                    // Se visualiza, si se sabe como, el contenido de cada mensaje
                    addMessageList("____________________________________________________");
                    addMessageList("Obteniendo contenido del mensaje.....");
                    analizaParteDeMensaje(mensajes[i], mensajes[i].getSubject());
                }

                folder.close(false);
                valid = true;
                store.close();
            } catch (AuthenticationFailedException e) {

                addMessageList(e.getMessage());
            } catch (MessagingException e) {

                addMessageList(e.getMessage());
            }
        } else {
            addMessageList("Email o Pass incorrectos");
        }
        return valid;
    }

    /**
     * *
     * Imprime el archivo pasandole la ruta y el nombre de la impresora
     *
     * @param FILE ruta junto al rchivo al imprimir ej:f //d/test.txt
     * @param printerName nombre de la impresora donde voy a imprimir
     * @return
     */
    public static boolean Print(String FILE, String printerName) {

        try {

            PDDocument document = PDDocument.load(new File(FILE));

            PrintService myPrintService = findPrintService(printerName);

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.setPrintService(myPrintService);
            job.print();

        } catch (Exception ex) {
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

//Busco el impresora para imprimir 
    public static PrintService findPrintService(String printerName) {

        printerName = printerName.toLowerCase();
        PrintService service = null;

        if (printerName.length() != 0) {

            // Get array of all print services
            PrintService[] services = PrinterJob.lookupPrintServices();

            // Retrieve a print service from the array
            for (int index = 0; service == null && index < services.length; index++) {

                if (services[index].getName().toLowerCase().indexOf(printerName) >= 0) {
                    service = services[index];
                    System.out.println(services[index].getName().toLowerCase());
                }
            }
        }

        // Return the print service
        return service;
    }

    /**
     * *
     * En esta parte recorremos el mansaje del correo para sacar las partes que
     * lo componen como img,pdf etc..F
     *
     * @param unaParte parte a analizar del mensaje
     * @param title tiulo del mensaje
     */
    private void analizaParteDeMensaje(Part unaParte, String title) {
        try {
            // Si es multipart, se analiza cada una de sus partes recursivamente.
            if (unaParte.isMimeType("multipart/*")) {
                Multipart multi;
                multi = (Multipart) unaParte.getContent();

                //le resto -1 para que no me incluya el contenido en html
                for (int j = 0; j < multi.getCount(); j++) {
                    analizaParteDeMensaje(multi.getBodyPart(j), title);
                }
            } else {
                // Si es imagen, se guarda en fichero y se visualiza en JFrame
                if (unaParte.isMimeType("application/pdf")) {

                    addMessageList("File " + unaParte.getContentType());

                    addMessageList("Fichero=" + unaParte.getFileName());
                    addMessageList("____________________________________________________");

                    addMessageList("Guardando file.....");
                    boolean v = salvaFichero(unaParte);
                    // visualizaImagenEnJFrame(unaParte);

                    if (v) {
                        addMessageList("file guardado exitosamente.....");
                        addMessageList("____________________________________________________");

                        //boolean f = Print(FILE, "confi");
                        boolean f = Print(FILE, print);

                        if (f) {
                            addMessageList("imprimiendo archivo....");
                            addMessageList("____________________________________________________");
                            addMessageList("Su archivo se imprimio....");
                            addMessageList("____________________________________________________");
                            if (IsDelete.equals("Y")) {
                                File archivo1 = new File(FILE);

                                if (archivo1.delete()) {
                                    addMessageList("Archivo borrado.....");
                                }

                            }
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Supone que unaParte corresponde a una imagen de un fichero y que
     * getFileName() esta relleno. Salva la imagen en d:\getFileName().
     *
     * @param unaParte Parte de un correo correspondiente a una imagen.
     *
     * @throws FileNotFoundException
     * @throws MessagingException
     * @throws IOException
     */
    private static boolean salvaFichero(Part unaParte)
            throws FileNotFoundException, MessagingException, IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(AbsolutePath + "\\\\" + unaParte.getFileName());
        FILE = AbsolutePath + "\\\\" + unaParte.getFileName();

        InputStream File = unaParte.getInputStream();
        byte[] bytes = new byte[100000];
        int leidos = 0;

        while ((leidos = File.read(bytes)) > 0) {
            fileOutputStream.write(bytes, 0, leidos);
        }

        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Controls.SbListBox<String> cmdList;
    private javax.swing.JScrollPane jScrollPane1;
    private Controls.SbLabel labelReloj;
    private Controls.SbButton sbButton1;
    private Controls.SbButton sbButton2;
    private Controls.SbButton sbButton3;
    private Controls.SbButton sbButton4;
    private Controls.SbButton sbButton5;
    // End of variables declaration//GEN-END:variables
}
