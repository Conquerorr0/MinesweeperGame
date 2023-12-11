package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartScreen extends javax.swing.JFrame {

    private BufferedImage arkaPlanResmi;
    final String iconURL = "src\\images\\mineIco.png";
    final private String clickURL = "src\\sounds\\click.wav";
    final private String rbURL = "src\\sounds\\RadioButtonSound.wav";
    private String choosenDifficulty;

    public StartScreen() {
        try {
            arkaPlanResmi = ImageIO.read(new File("src\\images\\logoblur.png")); // Arka plan resmini yükleyin
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        ArkaplanPaneli arkaPlanPanel = new ArkaplanPaneli(arkaPlanResmi);
        add(arkaPlanPanel);
        arkaPlanPanel.setOpaque(false);
        setContentPane(arkaPlanPanel);
        validate(); // Bileşeni yeniden düzenler
        arkaPlanPanel.repaint(); // Bileşeni yeniden çizer
        
        initComponents();
        
        StartButton.setContentAreaFilled(false);
        StartButton.setBorderPainted(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        setLayout(null);
    }

    public void Sound(String path) {
        // Ses dosyasının yolunu burada ayarlayın
        String sesDosyasiYolu = path;

        try {
            File sesDosyasi = new File(sesDosyasiYolu);
            if (sesDosyasi.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sesDosyasi);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f);
                clip.start();
            } else {
                System.err.println("Ses dosyası bulunamadı: " + sesDosyasiYolu);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        StartButton = new javax.swing.JButton();
        lblEasy = new javax.swing.JLabel();
        rbEasy = new javax.swing.JRadioButton();
        lblEasy1 = new javax.swing.JLabel();
        rbMedium = new javax.swing.JRadioButton();
        lblEasy2 = new javax.swing.JLabel();
        rbHard = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mayın Tarlası");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(600, 600));

        StartButton.setForeground(new java.awt.Color(60, 63, 65));
        StartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/startbutton.png"))); // NOI18N
        StartButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/startbutton.png"))); // NOI18N
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        lblEasy.setForeground(new java.awt.Color(0, 0, 0));
        lblEasy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEasy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/easy.jpg"))); // NOI18N
        lblEasy.setOpaque(true);

        buttonGroup1.add(rbEasy);
        rbEasy.setText("jRadioButton2");
        rbEasy.setOpaque(false);
        rbEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEasyActionPerformed(evt);
            }
        });

        lblEasy1.setForeground(new java.awt.Color(0, 0, 0));
        lblEasy1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEasy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medium.png"))); // NOI18N
        lblEasy1.setOpaque(true);

        buttonGroup1.add(rbMedium);
        rbMedium.setText("jRadioButton2");
        rbMedium.setOpaque(false);
        rbMedium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMediumActionPerformed(evt);
            }
        });

        lblEasy2.setForeground(new java.awt.Color(0, 0, 0));
        lblEasy2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEasy2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hard.png"))); // NOI18N
        lblEasy2.setOpaque(true);

        buttonGroup1.add(rbHard);
        rbHard.setText("jRadioButton2");
        rbHard.setOpaque(false);
        rbHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(lblEasy1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(lblEasy2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(rbEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(rbMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(rbHard, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StartButton)
                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(223, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEasy1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEasy2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbEasy)
                    .addComponent(rbMedium)
                    .addComponent(rbHard))
                .addGap(32, 32, 32)
                .addComponent(StartButton)
                .addGap(166, 166, 166))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        Sound(clickURL);
        dispose();
        Minesweeper minesweeper = new Minesweeper(choosenDifficulty);
    }//GEN-LAST:event_StartButtonActionPerformed

    private void rbEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEasyActionPerformed
        Sound(rbURL);
        choosenDifficulty = "EASY";
    }//GEN-LAST:event_rbEasyActionPerformed

    private void rbMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMediumActionPerformed
        Sound(rbURL);
        choosenDifficulty = "MEDIUM";
    }//GEN-LAST:event_rbMediumActionPerformed

    private void rbHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbHardActionPerformed
        Sound(rbURL);
        choosenDifficulty = "HARD";
    }//GEN-LAST:event_rbHardActionPerformed

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
            java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StartButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblEasy;
    private javax.swing.JLabel lblEasy1;
    private javax.swing.JLabel lblEasy2;
    private javax.swing.JRadioButton rbEasy;
    private javax.swing.JRadioButton rbHard;
    private javax.swing.JRadioButton rbMedium;
    // End of variables declaration//GEN-END:variables
}

class ArkaplanPaneli extends JPanel {

    private BufferedImage arkaPlanResmi;

    public ArkaplanPaneli(BufferedImage arkaPlanResmi) {
        this.arkaPlanResmi = arkaPlanResmi;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arkaPlanResmi != null) {
            g.drawImage(arkaPlanResmi, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
