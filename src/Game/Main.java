package Game;

public class Main {

    public static void main(String[] args) {
        Opening opening = new Opening();
        wait(2300);
        StartScreen startScreen = new StartScreen();
    }
    
    public static void wait(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
