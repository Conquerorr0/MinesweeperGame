package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Opening {

    private JFrame open;
    private BufferedImage arkaPlanResmi;
    final private String iconURL = "src\\images\\mineIco.png";
    
    public Opening() {
        open = new JFrame();
        open.setSize(600, 600);
        open.setUndecorated(true);
        open.setVisible(true);
        open.setResizable(false);
        open.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(iconURL);
        open.setIconImage(icon.getImage());
        
        try {
            arkaPlanResmi = ImageIO.read(new File("src\\images\\logo.jpg")); // Arka plan resmini yükleyin
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArkaplanPanel arkaPlanPanel = new ArkaplanPanel(arkaPlanResmi);
        open.setContentPane(arkaPlanPanel);
        open.validate(); // Bileşeni yeniden düzenler
        arkaPlanPanel.repaint(); // Bileşeni yeniden çizer

        Sound("src\\sounds\\start.wav");
        // Pencerenin kapatılmasını sağlayan bir Timer oluştur
        Timer timer = new Timer(2300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Timer tetiklendiğinde pencereyi kapat
                open.dispose();
                // MineField sınıfını başlat
            }
        });
        // Timer'ı başlat
        timer.start();
        
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
}

class ArkaplanPanel extends JPanel {

    private BufferedImage arkaPlanResmi;

    public ArkaplanPanel(BufferedImage arkaPlanResmi) {
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
