
/**
 * This is the Memory class which contains all methods simulating the storing,
 * deletion, and altering of the Operating System's memory
 * 
 * @author Robert King 
 * @version Assignment#1
 */
public class Memory
{
    //private variables for the Memory class
    private String[] mem;
    private final int TOTAL_MEM = 1000;
    private int freeMem;
    private int takenMem;
    private int loc;

    /**
     * Default Constructor for objects of class Memory
     */
    public Memory()
    {
        freeMem = TOTAL_MEM - 1;
        takenMem = 1;
        mem = new String[1000];
        mem[0] = "Root";
        loc = 1;
    }
    
    /**
     * Accessor method to return the value of free memory
     * @param void
     * @return integer value of free memory is returned
     */
    public int getFreeMem(){
        return freeMem;
    }
    
    /**
     * Accessor method to return the value of memory taken
     * @param void
     * @return integer value of taken memory is returned
     */
    public int getTakenMem(){
        return takenMem;
    }
    
    /**
     * Accessor method to return the total amount of memory;
     * @param void
     * @return integer value of total memory
     */
    public int getTotal(){
        return freeMem + takenMem;
    }
    
    /**
     * Accessor method to return the value of loc
     * @param void 
     * @return integer value of loc returned
     */
    public int getLoc(){
        return loc;
    }
    
    /**
     * Method to update the takenMem value and freeMem
     * @param integer value n that is being added to the memory
     * @return void
     */
    public void updateMem(int n){
        takenMem += n;
        freeMem -= n;
    }
    
    /**
     * Method that stores the name of file or directory in memory array
     * @param string value s represents the name being stored and integer
     *        n represents the size of the item being stored.
     * @return void
     */
    public void storeMem(String s, int n){
        for(int i = loc;i <= loc+n;i++){
            mem[loc] = s;
        }
        loc++;
        updateMem(n);
    }
    
    /**
     * Method to remove strings representing directories or files from the array mem
     * @param integer start represents the start location of the file or directory,
     *        size is an integer representing the size of the object being removed
     * @return void
     */
    public void removeMem(int start, int size){
        for(int i = start; i <= start+size; i++){
            mem[i] = "";
        }
        takenMem -= size;
        freeMem += size;
    }
    
    /**
     * Method to return a string consisting of the index values where there is free memory
     * @param void
     * @return String consisting of index values of mem that are empty
     */
    public String freeMemList(){
        String list = "\tFree Blocks of Memory:\n\t\t";
        for(int i = 0; i < mem.length; i++){
            if(mem[i]==null || mem[i].equals("")){
                list += i + "\n\t\t";
            }
        }
        return list;
    }
}
