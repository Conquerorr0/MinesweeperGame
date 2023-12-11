package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;


public class Minesweeper implements MouseListener{
    JFrame frame;

    final private int buton_height = 60;
    final private int height = 900, width = 1200;
    private int mine_count;
    private int[][] mines_coordinates;
    private int score = 0;

    Buttons[][] game_board = new Buttons[height / 60][width / 60];

    final private String mineURL = "src\\images\\mine.png";
    final private String flagURL = "src\\images\\flag.png";
    final private String blowURL = "src\\sounds\\blow.wav";
    final private String clickURL = "src\\sounds\\click.wav";
    final private String winURL = "src\\sounds\\win.wav";
    final private String gameOverURL = "src\\sounds\\gameover1.wav";
    final private String iconURL = "src\\images\\mineIco.png";
    private String choosen;

    public Minesweeper(String choosen) {
        this.choosen = choosen;
        frame = new JFrame("Mayın Tarlası");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());
        frame.setSize(width, height);
        frame.setLayout(new GridLayout(height / buton_height, width / buton_height));
        createBoard();
        createMines();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void createBoard() {
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                Buttons b = new Buttons(i, j);
                b.setBackground(Color.gray);
                frame.add(b);
                b.addMouseListener(this);
                b.setFont(new Font("SansSerif", Font.BOLD, 30));
                b.setBorder(new BevelBorder(BevelBorder.RAISED, Color.lightGray, Color.darkGray));
                game_board[i][j] = b;
            }
        }
    }

    public void createMines() {
        int i = 0;

        switch (choosen) {
            case "EASY":
                mine_count = height / 90 + width / 120;
                break;
            case "MEDIUM":
                mine_count = height / 60 + width / 75;
                break;
            case "HARD":
                mine_count = height / 60 + width / 45;
                break;
            default:
                throw new AssertionError();
        }

        mines_coordinates = new int[mine_count][2];

        while (i < mine_count) {
            int randomCol = (int) (Math.random() * game_board.length);
            int randomRow = (int) (Math.random() * game_board[0].length);

            if (!game_board[randomCol][randomRow].isMine()) {
                game_board[randomCol][randomRow].setMine(true);
                mines_coordinates[i][0] = randomCol;
                mines_coordinates[i][1] = randomRow;
                i++;
            }
        }

        updateCounts();
    }

    public void printMines() {
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                if (game_board[i][j].isMine()) {
                    game_board[i][j].setIcon(new ImageIcon(mineURL));
                }
            }
        }
    }

    public void updateCounts() {
        int x, y;
        for (int i = 0; i < mine_count; i++) {
            x = mines_coordinates[i][0];
            y = mines_coordinates[i][1];
            countIncrease(x, y);
        }
    }

    public void countIncrease(int x, int y) {
        //Sol yukarı çapraz -> x-1, y-1
        //      Üst         -> x  , y-1
        //Sağ yukarı çapraz -> x+1, y-1
        //      Sol         -> x-1, y
        //      Sağ         -> x+1, y
        //Sol aşağı çapraz  -> x-1, y+1
        //      alt         -> x  , y+1
        //Sağ aşağı çapraz  -> x+1, y+1

        if ((x - 1 >= 0 && x - 1 < game_board.length) && (y - 1 >= 0 && y - 1 < game_board[0].length)) {
            game_board[x - 1][y - 1].setCount();
        }

        if ((x >= 0 && x < game_board.length) && (y - 1 >= 0 && y - 1 < game_board[0].length)) {
            game_board[x][y - 1].setCount();
        }

        if ((x + 1 >= 0 && x + 1 < game_board.length) && (y - 1 >= 0 && y - 1 < game_board[0].length)) {
            game_board[x + 1][y - 1].setCount();
        }

        if ((x - 1 >= 0 && x - 1 < game_board.length) && (y >= 0 && y < game_board[0].length)) {
            game_board[x - 1][y].setCount();
        }

        if ((x + 1 >= 0 && x + 1 < game_board.length) && (y >= 0 && y < game_board[0].length)) {
            game_board[x + 1][y].setCount();
        }

        if ((x - 1 >= 0 && x - 1 < game_board.length) && (y + 1 >= 0 && y + 1 < game_board[0].length)) {
            game_board[x - 1][y + 1].setCount();
        }

        if ((x >= 0 && x < game_board.length) && (y + 1 >= 0 && y + 1 < game_board[0].length)) {
            game_board[x][y + 1].setCount();
        }

        if ((x + 1 >= 0 && x + 1 < game_board.length) && (y + 1 >= 0 && y + 1 < game_board[0].length)) {
            game_board[x + 1][y + 1].setCount();
        }
    }

    public void open(int col, int row) {
        if ((col < 0 || col >= game_board.length) || (row < 0 || row >= game_board[0].length) || (game_board[col][row].getText().length() > 0) || game_board[col][row].isBlowup() == true || game_board[col][row].isMine() == true) {
            return;
        } else if (game_board[col][row].getCount() != 0) {
            game_board[col][row].setFont(new Font("Arial", Font.BOLD, 30));
            textColors(col, row);
            game_board[col][row].setBackground(Color.lightGray);
            game_board[col][row].setText(game_board[col][row].getCount() + "");
            game_board[col][row].setBlowup(true);
        } else {
            textColors(col, row);
            game_board[col][row].setBackground(Color.lightGray);
            game_board[col][row].setBlowup(true);
            open(col - 1, row);
            open(col + 1, row);
            open(col, row - 1);
            open(col, row + 1);
        }
    }

    public void endgame() {
        int dialogButton = JOptionPane.showConfirmDialog(frame, "Tekrar Oynamak İster misiniz?", "", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
            frame.dispose();
            StartScreen startScreen = new StartScreen();
        } else if (dialogButton == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
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

    public int remainingButtons() {
        int count = 0;
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                if (game_board[i][j].isEnabled()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public void textColors(int i, int j){
        switch (game_board[i][j].getCount()) {
                    case 1:
                        game_board[i][j].setForeground(Color.blue); 
                        break;
                    case 2:
                        game_board[i][j].setForeground(Color.green); 
                        break;
                    case 3:
                        game_board[i][j].setForeground(Color.red); 
                        break;
                    case 4:
                        game_board[i][j].setForeground(Color.magenta); 
                        break;
                }
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        Buttons b = (Buttons) e.getComponent();
        if(!b.isBlowup())  Sound(clickURL);
        if (e.getButton() == 3 && !b.isBlowup()) {
            if (!b.isFlag() && b.getIcon() == null) {
                b.setIcon(new ImageIcon(flagURL));
                b.setFlag(true);
            } else {
                b.setIcon(null);
                b.setFlag(false);
            }
        } else if (e.getButton() == 1) {
            if (b.isMine()) {
                for (int i = 0; i < mines_coordinates.length; i++) {
                    game_board[mines_coordinates[i][0]][mines_coordinates[i][1]].setIcon(new ImageIcon(mineURL));
                }
                Sound(blowURL);
                wait(700);
            } else {
                score++;
                open(b.getCol(), b.getRow());
                b.setIcon(null);
                b.setBlowup(true);
            }
        }

        if (mine_count == remainingButtons()) {
            Sound(winURL);
            JOptionPane.showMessageDialog(frame, "Tebrikler KAZANDINIZ!\nSkor: " + score);
            endgame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void wait(int millis) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Sound(gameOverURL);
                    JOptionPane.showMessageDialog(frame, "MAYINA BASTIN!\n***OYUN BİTTİ***\nSkor: " + score);
                    Thread.sleep(millis);
                    endgame();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        thread.start();
    }
}
