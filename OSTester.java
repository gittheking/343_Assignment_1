
/**
 * this is the Tester class that implements and tests all the components of the OS Package.
 * 
 * @author Robert King
 * @version Assigment#1
 */
public class OSTester
{
    public static void main(String args[]){
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                new Console();
            }
            
        });
       
    }
}
