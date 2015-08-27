
/**
 * This is the Console class that handles all the interaction with the GUI
 * including execution of commands sent to the console
 * 
 * @author Robert King 
 * @version Assignment#1
 */

import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.text.*;
import java.lang.Object.*;
import java.awt.*;

public class Console extends JPanel implements ActionListener
{
    //protected static variables for the User Login prompt
    //USER1 is also used for the console textarea
    protected final String USER1 = "Bobby";
    protected final String PWORD1 = "geneseo";
    
    //creating final string variables for console commands 
    private static final String LIST_COMMANDS = "cmds";
    private static final String DEFRAGMENT = "df";
    private static final String CREATE_DIR = "crd";
    private static final String CHANGE_DIR = "cd";
    private static final String REM_DIR = "rd";
    private static final String LIST_DIR = "ld";
    private static final String CREATE_F = "cf";
    private static final String REM_F= "rf";
    private static final String LIST_F = "lf";
    private static final String LIST_MEM = "lm";
    
    //creating static variable for JFrame
    private static JFrame frame;
    
    //static variables for the User Login prompt
    private static JTextField user;
    private static JPasswordField passwordField;
    private static JButton submit;
    private static JLabel label1;
    private static JLabel label2;
    private static String entered_uName;
    private static String entered_pWord;
    
    //Static variables for the Console Prompt
    private static JTextField prompt;
    private static JTextArea consoleField;
    private static JPanel c;
    private static JScrollPane scroll;
    
    //Initializing a new MainMemory object
    Memory main = new Memory();
    
    //initializing arrays to keep the the created directories and files
    private FoldersNFiles[] directories = new FoldersNFiles[20];
    private FoldersNFiles[] files = new FoldersNFiles[20];
    
    //boolean variable set to false, when true the GUI changes from
    //a user login window to a command prompt.
    private static boolean next = false;
    
    //name of the current stored in variable. Initilizing it at the Root
    private String currentDir = "Root";
    private int file;
    private int dir;
    
    //Constructor for Console Class
    //Initializes all the values involving the console's GUI
    //features
    public Console(){
        //Passing the root directory as the first directory stored
        //in the directories array
        
        for(int i = 0; i<directories.length;i++){
            directories[i] = new FoldersNFiles();
            files[i] = new FoldersNFiles();
        }
        
        directories[0].setName("Root");
        directories[0].setType(0);
        directories[0].setStart(0);
        directories[0].setEnd(999);
        
        frame = new JFrame("Console");
        
        //Initializing necessary values for the User Login
        user = new JTextField(20);
        passwordField = new JPasswordField(20);
        submit = new JButton("Submit");
        submit.addActionListener(this);
        label1 = new JLabel("User Name:");
        label2 = new JLabel("Password:");
        
        //Initializing the necessary values for the command prompt
        prompt = new JTextField(30);
        prompt.addActionListener(this);
        consoleField = new JTextArea(30,60);
        consoleField.setEditable(false);
        c = new JPanel(new BorderLayout());
        scroll = new JScrollPane(consoleField);
        
        //declaring default closing operation for the login screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initilizing the layout for the login prompt
        JPanel c1 = new JPanel(new BorderLayout());
        JPanel c2 = new JPanel(new BorderLayout());
        JPanel c3 = new JPanel(new BorderLayout());
        JPanel c4 = new JPanel(new BorderLayout());
        
        
        //setting up the login prompt layout
        c1.add(user, BorderLayout.NORTH);
        c1.add(passwordField,BorderLayout.SOUTH);
        
        c2.add(label1,BorderLayout.NORTH);
        c2.add(label2,BorderLayout.SOUTH);
        
        c3.add(c1,BorderLayout.EAST);
        c3.add(c2,BorderLayout.WEST);
        
        c4.add(c3, BorderLayout.NORTH);
        c4.add(submit,BorderLayout.SOUTH);
        
        frame.setContentPane(c4);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Method to add actions to the User Login GUI and command prompt GUI.
     * @param ActionEvent evt, actions for this class include pushing the submit
     *        button and the entering commands into the command prompts textfield
     * @return void
     */
    public void actionPerformed(ActionEvent evt) {
        //retrieving the values entered into the User name and password fields
        entered_uName = user.getText();
        entered_pWord = passwordField.getText();
        
        //if statment to determine if the correct user name password has been entered
        if(!next){
            if(entered_uName.equals(USER1) && entered_pWord.equals(PWORD1)){
            
                c.add(scroll,BorderLayout.NORTH);
                c.add(prompt,BorderLayout.SOUTH);
                
                frame.setContentPane(c);
                frame.validate();
                frame.revalidate();
                frame.repaint();
                frame.pack();
                consoleField.append("Welcome " + USER1 + "!\n");
                
                next = true;
            }
        }
        //once the correct name and password have been entered, The user has access 
        //to the command prompt
        else{
            //Code to read in the value of the textfield and then print
            //that same text, and the corresponding actions involved with 
            //it
            String text = prompt.getText();
            String text2 = classify(text);
            consoleField.append(USER1 + ": " + text + "\n" + text2 + "\n");
            consoleField.setCaretPosition(consoleField.getDocument().getLength());
            prompt.selectAll();
        }
    }
    
    /**
     * Method to determine and execute the appropriate method for the value entered
     * into the console
     * @param String s is the string entered into the command prompt
     * @return returns a string of the action that was executed
     */
    public String classify(String s){
        String two = "";
        String name2 = "";
        String three = "";
        String name3 = "";
        if(s.length() >=3 ){
            String temp = s;
            two = temp.substring(0,2);
            name2 = temp.substring(3,temp.length());
            if(s.length() >= 4){
                three = temp.substring(0,3);
                name3 = temp.substring(4,temp.length());
            }
        }
        
        //conditonals to deterrmine what action is to be performed determined by 
        //the string s
        if(s.equals(LIST_COMMANDS)){
            return "\t'crd'\t-\tcreate directory\n\t"
                + "'rd'\t-\tremove directory\n\t'ld'\t-\tlist directory\n\t"
                + "'cf'\t-\tcreate file\n\t'rf'\t-\tremove file\n\t"
                + "'lf'\t-\tlist file\n\t'cd'\t-\tchange directory";
        }
        else if(s.equals(LIST_DIR)){
            int x = findD(currentDir);
            return directories[x].listDir();
        }
        else if(two.equals(LIST_F)){
            int temp = findF(name2);
            if(temp > -1){
                return files[temp].listF();
            }
            else{
                return "\t-Sorry, file '"+name2+"' does not exist-";
            }
        }
        else if(s.equals(LIST_MEM)){
            return "\tMemory\n\tTaken:\t"+main.getTakenMem()+"\n\tFree:\t"
                + main.getFreeMem()+"\n\tTotal:\t"+main.getTotal()
                + "\n" + main.freeMemList();
        }
        else if(two.equals(CREATE_F)){
            if(s.length() >= 4){
                int temp = findF(name2);
                if(temp < 0){
                    storeF(name2);
                    int x = findD(currentDir);
                    directories[x].addF2D(name2);
                    return "\t-File '"+name2+"' created-";
                }
                else{
                    return "\t-Sorry, file '"+name2+"' already exists-";
                }
            }
            else{
                return "\t-Must add a name for the file-";
            }
        }
        else if(three.equals(CREATE_DIR)){
            if(s.length() >= 5){
                int temp = findD(name3);
                if(temp < 0){
                    storeDir(name3);
                    int x = findD(currentDir);
                    directories[x].addD2D(name3);
                    return "\t-Directory '"+name3+"' created-";
                }
                else{
                    return "\t-Sorry, directory'"+name3+"' already exists-";
                }
            }
            else{
                return "\t-Must add a name for the directory-";
            }
        }
        else if(two.equals(CHANGE_DIR)){
            if(s.length() >= 4){
                currentDir = name2;
                return "\t-Directory changed to'"+name2+"'-";
            }
            else{
                return "\t-Must add directory to be changed";
            }
        }
        else if(two.equals(REM_DIR)){
            if(s.length() >= 4){
                if(name2.equals("Root")){
                    return "\t-Cannot remove Root directory-";
                }
                else{
                    int temp = findD(name2);
                    if(temp > -1){
                        remDir(name2);
                        String cd = directories[temp].getDir();
                        directories[temp] = new FoldersNFiles();
                        temp = findD(cd);
                        directories[temp].delete(name2,0);
                        currentDir = "Root";
                        return "-'"+name2+"' Directory has been removed, and current "+
                                "directory has been changed to 'Root'";
                    }
                    else{
                        return "\t-Sorry, directory '"+name2+"' does not exist";   
                    }
                }
            }
            else{
                return "\t-Must add directory is to be removed";
            }
        }
        else if(two.equals(REM_F)){
            if(s.length() >= 4){
                int temp = findF(name2);
                if(temp > -1){
                    main.removeMem(files[temp].getStart(),files[temp].getSize());
                    String x = files[temp].getDir();
                    files[temp] = new FoldersNFiles();
                    temp = findD(x);
                    directories[temp].delete(name2,1);
                    return "\t-'"+name2+"' file has been removed-";
                }
                else{
                    return "\t-Sorry, file '"+name2+"' does not exist"; 
                }
            }
            else{
                return "\tMust add file that is to be removed-";
            }
        }
        else{
            return "\t-Sorry, no command '"+s+"' found";
        }
    }
    
    /**
     * Method stores newly created directory in the directories array
     * @param class object of FoldersNFiles class, directory object specifically.
     * @return boolean value of true or false is returned. true determines that
     *         the directory was able to be stored, false if not.
     */
    public boolean storeDir(String s){
        int loc = 1;
        boolean stored = false;
        if(main.getTakenMem() <= 960){
            while(!stored && loc <=19){
                String name = directories[loc].getName();
                if(name.equals("")){
                    int x = main.getLoc();
                    directories[loc] = new FoldersNFiles(s,0,x,currentDir);
                    main.storeMem(s,1);
                    stored = true;
                }
                loc++;
            }
        }
        return stored;
    }
    
    /**
     * Method stores newly created File in the files array
     * @param class object of FoldersNFiles class, file object specifically.
     * @return boolean value of true or false is returned. true determines that
     *         the fiel was able to be stored, false if not.
     */
    public boolean storeF(String s){
        int loc = 0;
        boolean stored = false;
        while(!stored && loc <=19){
            String name = files[loc].getName();
            if(name.equals("")){
                int x = main.getLoc();
                files[loc] = new FoldersNFiles(s,1,x,currentDir);
                x = files[loc].getSize();
                main.storeMem(s,x);
                stored = true;
            }
            loc++;
        }
        return stored;
    }
    
    /**
     * Method the remove a directoy from the memory
     * @param string s contains the name of the directory 
     * @return returns true or false determining if the directory chosen to delete 
     *         exists in memory
     */
    public void remDir(String s){
        int loc = findD(s);
        main.removeMem(directories[loc].getStart(),1);
        String[][] cont = directories[loc].getContents();
        for(int i = 0;i < 10;i++){
            if(cont[i][1].equals("")){
                ;
            }
            else{
                int x = findF(cont[i][1]);
                main.removeMem(files[x].getStart(),files[x].getSize());
                files[x] = new FoldersNFiles();
            }
        }
        for(int i = 0;i < 10;i++){
            if(cont[i][0].equals("")){
                ;
            }
            else{
                remDir(cont[i][0]);
                int x = findD(cont[i][0]);
                //main.removeMem(directories[x].getStart(),1);
                directories[x] = new FoldersNFiles();
            }
        }
    }
    
    /**
     * Method to find the directory that matched the String s
     * @param String s represents the name of the directory being located
     * @return returns the index value of directory being searched, if -1
     *         it means it is not found.
     */
    public int findD(String s){
        boolean found = false;
        int loc = 0;
        int x = -1;
        while(!found && loc <= 19){
            if(directories[loc].getName().equals(s)){
                found = true;
                x = loc;
            }
            loc++;
        }
        return x;
    }
    
    /**
     * Method to find the file that matched the String s
     * @param String s represents the name of the file being located
     * @return returns the index value of directory being searched, if -1
     *         it means it is not found.
     */
    public int findF(String s){
        boolean found = false;
        int loc = 0;
        int x = -1;
        while(!found && loc <= 19){
            if(files[loc].getName().equals(s)){
                found = true;
                x = loc;
            }
            loc++;
        }
        return x;
    }
}
