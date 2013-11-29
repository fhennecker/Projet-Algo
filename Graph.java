import java.io.*;
import java.util.Vector;

public class Graph {
    
    private Vector<Frat> _fratList;
    private Vector<Vector<Frat>> _cycles;
    
    //////////GETTERS + SETTERS//////////
    
    public Vector<Frat> getFratList(){ return _fratList; }
    public int getLength(){ return _fratList.size(); }
    public Frat getFrat(int index){ return _fratList.get(index); }
    
    public void setFratList(Vector<Frat> fratList){
        _fratList.clear();
        _fratList = new Vector<Frat>(fratList);
    }
    
    //////////CONSTRUCTOR//////////
    
    public Graph(String fileName){
        
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            text = reader.readLine();
            _fratList = new Vector<Frat>(Integer.decode(text));
            Frat tmp;
            Frat tmpCreditor;
            
            while ((text = reader.readLine()) != null) {
                String[] splitLine = text.split("\\s+");
                
                if (splitLine.length==2){
                    //System.out.println("1 : *"+splitLine[0]+"*"+splitLine[1]);
                    tmp = new Frat(splitLine[0],Integer.decode(splitLine[1]));
                    _fratList.add(tmp);
                    
                } else if (splitLine.length==3){
                    tmp=null;
                    tmpCreditor = null;
                    
                    for (int i=0;i<_fratList.size();i++){
                        if (_fratList.get(i).isFrat(splitLine[0])){
                            tmp = _fratList.get(i);//get Debitor
                        } else if (_fratList.get(i).isFrat(splitLine[1])){
                            tmpCreditor = _fratList.get(i);//get Creditor
                        }
                    }
                    if (tmp!=null && tmpCreditor!=null){//if both were found, add Debt
                        //System.out.println("adding debt");
                        tmp.addDebt(tmpCreditor,Integer.decode(splitLine[2]));
                    } else{System.out.println("One of the Fraternitys was not found in a debt");}
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error opening file");
        }try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing file");
        }
    }
    
    public static void main(String[] argv){
        Graph a = new Graph("test.txt");
        a.graphToImage();
    }
    
    public void detectCycle(){
        
    }                                 
    
    public void graphToImage(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("graph.dot"), "utf-8"));
            writer.write("digraph G {\n");
            Vector<Debt> debtList;
            for (int i = 0;i<getLength();i++){
                String myName = _fratList.get(i).getName();
                writer.write(myName+" [style=filled, fillcolor = orange]\n");
                debtList = new Vector<Debt>(_fratList.get(i).getDebtList());
                for (int j =0;j<debtList.size();j++){
                    String debtName = debtList.get(j).getCreditor().getName();
                    String line = "    "+myName+" -> "+debtName+"[label=\" "+debtList.get(j).getAmount()+"\"];\n";
                    writer.write(line);
                }                  
            }
        } catch (IOException ex) {
          System.out.println("Could not create .dot file");
        } finally {
            try {
                writer.write("}");
                writer.close();
            } catch (Exception ex) {System.out.println("Could not close .dot file");}
        }
    }       
}