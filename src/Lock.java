import javax.swing.*;

public class Lock {

    public static boolean Lock1() {
        String str = JOptionPane.showInputDialog("Enter security key :");
        if(str.equals("Hack404himesh")){
            return true;
        }
        return false;
    }

    public static void showme(String msg){
        JOptionPane.showMessageDialog(null, msg,"Message",JOptionPane.PLAIN_MESSAGE);
    }
}
