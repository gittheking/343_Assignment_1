
/**
 * Class to create Directories and Files and the appropriate methods for each
 * 
 * @author Robert King 
 * @version Assignment#1
 */

import java.util.*;

public class FoldersNFiles
{
    
    private String name;
    private int start;
    private int end;
    private int size;
    private String dir;
    private int type;
    private String[][] contents;

    private final int DIRECTORY = 0;
    private final int FILE = 1;
    
    /**
     *Constructor to create an empty FoldersNFiles object
     */
    public FoldersNFiles(){
        contents = new String[10][2];
        for(int i=0;i<10;i++){
            for(int j=0;j<2;j++){
                contents[i][j]="";
            }
        }
        name = "";
        start = 0;
        end = 0;
        size = 0;
        type = 0;
        dir = "";
    }
    
    
    /**
     * Constructor for objects of class FoldersNFiles
     */
    public FoldersNFiles(String myName, int myType, int myStart, String d)
    {
        name = myName;
        type = myType;
        start = myStart;
        dir = d;
        contents = new String[10][2];
        for(int i=0;i<10;i++){
            for(int j=0;j<2;j++){
                contents[i][j]="";
            }
        }
        
        if(type == 1){
            Random gen = new Random();
            int x = gen.nextInt(20) + 1;
            size = x;
            end = start + size - 1;
        }
        else{
            size = 1;
            end = start;
        }
        
    }
    
    /**
     * Mutator method to set the name of the object
     * @param String that will be the object's new name
     * @return void
     */
    public void setName(String s){
        this.name = s;
    }
    
    /**
     * Mutator method to set the type of the object
     * @param integer either 0 or 1 depending if its a directory or a file
     * @return void
     */
    public void setType(int n){
        this.type = n;
    }
    
    /**
     * Mutator method to set the start location in memory of the object
     * @param integer n domain [0 999]
     * @return void
     */
    public void setStart(int n){
        this.start = n;
    }
    
    /**
     * Mutator method to set the end location in memory of the object
     * @param integer n domain [0 999]
     * @return void
     */
    public void setEnd(int n){
        this.end = n;
    }
    
    /**
     * Mutator method to set the size of the object being stored in the memory
     * @param integer n representing the size 
     * @return void
     */
    public void setSize(int n){
        this.size = n;
    }
    
    /**
     * Accessor method to return the value of the object's name
     * @param void
     * @return String representing objects name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Accessor method to return the type of the object
     * @param void
     * @return an integer representing the objects type
     */
    public int getType(){
        return type;
    }
    
    /**
     * Accessor method to return the objects start location
     * @param void
     * @return an integer representing the objects start location
     */
    public int getStart(){
        return start;
    }
    
    /**
     * Accessor method to return the objects end location
     * @param void
     * @return an integer representing the objects end location
     */
    public int getEnd(){
        return end;
    }
    
    /**
     * Accessor method to return the size of the object
     * @param void
     * @return an integer representing the size of the object
     */
    public int getSize(){
        return size;
    }
    
    /**
     * Accessor method to return the value of contents
     * @param void
     * @return String array containing the values of contents[][]
     */
    public String[][] getContents(){
        return contents;
    }
    
    /**
     * Accessor method to return the value of dir
     * @param void
     * @return a string representing the value of dir
     */
    public String getDir(){
        return dir;
    }
    
    /**
     * Method to list the metadata of a file
     * @param void
     * @return formatted string representing a files metadata
     */
    public String listF(){
            return "\tName:\t"+name+"\n\tType:\tFile\n\t"
                +"Start:\t"+start+"\n\tEnd:\t"+end+"\n\tSize:\t"
                +size;
    }
    
    /**
     * Method to add the string representing the name of a directory into 
     * an array storing all directories inside the current directory
     * @param string that represents directory to be added
     * @return void
     */
    public void addD2D(String s){
        boolean go = false;
        int loc = 0;
        while(!go && loc <= 9){
            String temp = contents[loc][0];
            if(temp.equals("")){
                contents[loc][0] = s;
                go = true;
            }
            loc++;
        }
    }
    
    /**
     * Method to add the string representing the name of a file into 
     * an array storing all the files inside the current directory
     * @param string that represents file to be added
     * @return void
     */
    public void addF2D(String s){
        boolean go = false;
        int loc = 0;
        while(!go && loc <= 9){
            String temp = contents[loc][1];
            if(temp.equals("")){
                contents[loc][1] = s;
                go = true;
            }
            loc++;
        }
    }
    
    /**
     * Method to delete either a directory or file from the current directory
     * @param string representing item to be removed, and integer n representing
     *        the type of object to be deleted(0=directory,1=file)
     * @return void
     */
    public void delete(String s,int n){
        for(int i = 0; i < 10;i++){
            if(s.equals(contents[i][n])){
                contents[i][n] = "";
            }
        }
    }
    
    /**
     * Method to format all the contents of the current directory into a neat string
     * @param void
     * @return String representing formatted content listing
     */
    public String listDir(){
        String n = "\tDirectory Name:\n\t"+name
                    +"\n\tLocation:\n\t"+getStart();
        String f = "\n\tFiles:";
        String d = "\n\n\tDirectories:";
        for(int i = 0;i <= 9;i++){
            String temp = contents[i][0];
            if(temp.equals("")){
                ;
            }
            else{
                d += ("\n\t"+contents[i][0]);
            }
        }
        for(int i = 0;i <= 9;i++){
            String temp = contents[i][1];
            if(temp.equals("")){
                ;
            }
            else{
                f += ("\n\t"+contents[i][1]);
            }
        }
        return n+f+d;
    }
}
