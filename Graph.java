import java.io.*;
import java.util.Vector;

public class Graph {
    
    private Vector<Frat> _fratList;
    
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
                    
                    //or this code
                    //
                    //for (int i=0;i<_fratList.size();i++){//must find debitor
                    //  tmp = _fratList.get(i);
                    //  if (tmp.isFrat(splitLine[0])){//if found debitor
                    //      System.out.println("found Frat");
                    //      
                    //      for (int j = 0;j<_fratList.size();j++){//must find creditor
                    //          tmpCreditor=_fratList.get(j);
                    //          
                    //          if (tmpCreditor.isFrat(splitLine[1])){//if found creditor
                    //              System.out.println("Adding debt");
                    //              tmp.addDebt(tmpCreditor,Integer.decode(splitLine[2]));
                    //          }
                    //          
                    //      }
                    //      
                    //   }
                    //}
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
    
    public void test(){
        int len = getLength();
        for (int i = 0;i<len;i++){
            Frat tmp = _fratList.get(i);
            System.out.println(tmp.getName());
            if (tmp.getDebtList().size()>0){
                Vector<Debt> Debts = new Vector<Debt>(tmp.getDebtList());
                for (int j = 0;j<Debts.size();j++){
                    Debt tmpDebt = Debts.get(j);
                    System.out.println("Debt to "+tmpDebt.getCreditor().getName()+" of "+tmpDebt.getAmount()+" euros");
                }
            }
        }    
    }
    
    public static void main(String[] argv){
        Graph a = new Graph("test.txt");
        a.test();
    }
    
    
    
    public void detectCycle(){
        
    }
    
    public void graphToImage(){
        
    }
    
}