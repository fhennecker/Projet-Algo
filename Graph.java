import java.io.*;
import java.util.Vector;

public class Graph {
    
    private Vector<Frat> _fratList;
    
    public Graph(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            
            _fratList = new Vector<Frat>(Integer.decode(text));
            
            while ((text = reader.readLine()) != null) {
                String[] splitLine = text.split(" ");
                if (splitLine.length==2){
                    System.out.println("1 : "+text);
                    //add to vector
                } else if (splitLine.length==3){
                    //add debt
                    System.out.println("2 : "+text);
                }
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error opening file");
        }
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing file");
        }   
    }
    
    public static void main(String[] argv){
        Graph a = new Graph("test.txt");
    }
    
    ///getters setters///
    
    public void detectCycle(){
        
    }
    
    public void graphToImage(){
        
    }
    
}