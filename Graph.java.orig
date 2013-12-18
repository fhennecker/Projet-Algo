import java.io.*;
import java.util.Vector;

public class Graph {
    
    private Vector<Frat> _fratList;
    private Vector<Vector<Frat>> _cycles;
    
    ////////// GETTERS + SETTERS //////////
    
    public Vector<Frat> getFratList(){ return _fratList; }
    public int getLength(){ return _fratList.size(); }
    public Frat getFrat(int index){ return _fratList.get(index); }
    
    public void setFratList(Vector<Frat> fratList){
        _fratList.clear();
        _fratList = new Vector<Frat>(fratList);
    }
    
    ////////// CONSTRUCTOR //////////
    
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
        _cycles = new Vector<Vector<Frat>>();
    }
    
    ////////// WORK METHODS //////////
    
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
<<<<<<< HEAD
        Graph a = new Graph("testenonce.txt");
=======
        Graph a = new Graph(argv[0]);
>>>>>>> 8adaf12117643988917a7776edbd8635b2f4a65f
        a.test();
        a.graphToImage("before");
        a.detectCycles();
        a.reduceCycles();
        a.graphToImage("after");
        System.out.println(a._cycles);
    }
    
    
    
    public void detectCycles(){
        Vector<Frat> visited = new Vector<Frat>();
        for (Frat frat:getFratList()){
            if (! visited.contains(frat)){
                Vector<Frat> path = new Vector<Frat>();
                findCycle(frat, path, visited);
            }   
        }
    }

    public void findCycle(Frat currentFrat, Vector<Frat> path, Vector<Frat> visited){
        for (Debt debt:currentFrat.getDebtList()){ // looking for all creditors
                if (path.contains(debt.getCreditor())){
                    // found cycle
                    path.add(currentFrat);
                    Vector<Frat> cycle = new Vector<Frat>();
                    int i = 0;
                    while (path.get(i) != debt.getCreditor()){
                        i++;    // the first frats in the path are not necessarily
                    }           // in the cycle. we're skipping them here.
                    do{
                        cycle.add(path.get(i));
                        i++;
                    } while (i<path.size());
                    _cycles.add(cycle);
                    path.remove(currentFrat);
                }
                else {
                    path.add(currentFrat);
                    findCycle(debt.getCreditor(), path, visited);
                    path.remove(currentFrat);
                }
        }
        visited.add(currentFrat);
    }

    public void reduceCycles(){
        for(Vector<Frat> cycle:_cycles){
            // searching for minimum debt
            int amountToReduce = cycle.get(0).getDebt(cycle.get(1));
            int newDebt = 0;
            for (int i=1; i<cycle.size(); ++i){
                newDebt = cycle.get(i).getDebt(cycle.get( (i+1)%cycle.size() ));
                System.out.println("red : " + newDebt +" "+amountToReduce+"\n");
                if (newDebt < amountToReduce){
                    amountToReduce = newDebt;
                }
            }

            // reducing all debts by amountToReduce
            for (int i=0; i<cycle.size(); ++i){
                Frat frat = cycle.get(i);
                Frat nextFrat = cycle.get( (i+1)%cycle.size() );
                frat.changeDebt(nextFrat, -amountToReduce);
            }
        }
    }
    
    public void payBack(){
        for (int i = 0;i<getLength();i++){//every frat pays back what it can
            _fratList.get(i).payBack();
        }
    }
    
    public void graphToImage(String filename){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename+".dot"), "utf-8"));
            writer.write("digraph G {\n");
            Vector<Debt> debtList;
            for (int i = 0;i<getLength();i++){//for every node in graph
                
                Frat tmp =  _fratList.get(i);
                String myName = ("\""+tmp.getName()+"\n"+tmp.getBudget()+"\"");
                //myName == string with name and budget of Frat
                writer.write(myName+" [style=filled, fillcolor = orange]"+"\n");
                //create node with orange color and myName as info
                debtList = new Vector<Debt>(_fratList.get(i).getDebtList());
                
                for (int j =0;j<debtList.size();j++){//for every debt a Frat has
                    
                    Frat tmpCreditor = debtList.get(j).getCreditor();
                    String debtName = ("\""+tmpCreditor.getName()+"\n"+tmpCreditor.getBudget()+"\"");
                    //same as myName but for creditor
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