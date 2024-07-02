package Utils;

public class Random {
    
    public int randomizeTableID(){
        java.util.Random random = new java.util.Random();
        return random.nextInt(2000);
    }
    
}
