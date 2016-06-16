/*
 * Copyright (C) 2016 CodeFireUA <edu@codefire.com.ua>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package javathreads;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public class MainFrame extends javax.swing.JFrame implements DownloadFileTask.DownloadFileTaskListener {

    private static final File STORAGE_FILE = new File("Downloads");

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jbRead = new javax.swing.JButton();
        jtfPlaylistAddress = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlDownloads = new javax.swing.JList<>();
        jpbDownloadProgress = new javax.swing.JProgressBar();
        jbDownload = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Playlist address:");

        jbRead.setText("Read");
        jbRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbReadActionPerformed(evt);
            }
        });

        jtfPlaylistAddress.setText("http://www.ex.ua/playlist/9426590.m3u");

        jScrollPane1.setViewportView(jlDownloads);

        jbDownload.setText("Download");
        jbDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDownloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpbDownloadProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPlaylistAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRead))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbDownload)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbRead)
                    .addComponent(jtfPlaylistAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpbDownloadProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbDownload)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbReadActionPerformed

        DefaultListModel<DownloadFileTask> defaultListModel = new DefaultListModel<>();

        int downloads = 0;

        try (Scanner scan = new Scanner(new URL(jtfPlaylistAddress.getText()).openStream())) {
            while (scan.hasNextLine()) {
                DownloadFileTask downloadFileTask = new DownloadFileTask(new URL(scan.nextLine()), STORAGE_FILE);
                defaultListModel.addElement(downloadFileTask);
                
                downloads++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        jlDownloads.setModel(defaultListModel);

        jpbDownloadProgress.setMaximum(downloads);

    }//GEN-LAST:event_jbReadActionPerformed

    private void jbDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDownloadActionPerformed

        ListModel<DownloadFileTask> model = jlDownloads.getModel();

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < model.getSize(); i++) {
            DownloadFileTask downloadFileTask = model.getElementAt(i);
            downloadFileTask.setListener(this);

            threadPool.execute(downloadFileTask);
        }

        threadPool.shutdown();

    }//GEN-LAST:event_jbDownloadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        if (!STORAGE_FILE.exists()) {
            STORAGE_FILE.mkdir();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbDownload;
    private javax.swing.JButton jbRead;
    private javax.swing.JList<DownloadFileTask> jlDownloads;
    private javax.swing.JProgressBar jpbDownloadProgress;
    private javax.swing.JTextField jtfPlaylistAddress;
    // End of variables declaration//GEN-END:variables

    @Override
    public void complete(DownloadFileTask downloadFileTask) {
//        System.out.println("Download complete: " + source + " into: " + target);

        jpbDownloadProgress.setValue(jpbDownloadProgress.getValue() + 1);
    }

    @Override
    public void progress(DownloadFileTask downloadFileTask) {
        jlDownloads.repaint();
    }
}
