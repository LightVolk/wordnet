/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simplevolk
 */
import java.lang.*;
import java.util.Iterator;



public class WordNet {
    
   private static  Bag<String> SynsetStrings = new Bag<>(),HyperNymsStrings = new Bag<>();
   private static Bag<String> Nouns=new Bag<String>();
   private static Bag<Integer> IndexesOfNouns=new Bag<Integer>();
   public static int V=10;
    public WordNet(String synsets,String hypernyms)
    {
        int count=0;
       
      In in_syn = new In(synsets);
      while(in_syn.hasNextLine())
          {
              SynsetStrings.add(in_syn.readLine());
          }
      In in_hyp=new In(hypernyms);
      while(in_hyp.hasNextLine())
      {
          HyperNymsStrings.add(in_hyp.readLine());
      }
    }
    public Iterable<String> nouns()
    {       
        Bag<String> NounsTmp=new Bag<>();
          
            for (String string:SynsetStrings) 
                {
                 int subtIndex=0;subtIndex=string.indexOf(",");
                 NounsTmp.add(string.substring(subtIndex+1));
               //  tmp=indexOfNouns.parseInt(string.substring(0, subtIndex).toString());
               //  IndexesOfNouns.add(tmp);
                }                          
        return (Iterable<String>)NounsTmp;   
    }
    
    public boolean isNoun(String word)
    {
        for(String string:Nouns)
        {
            if(string.equals(word))
                return true;
        }
        return false;
    }
    public int distance(String nounA,String nounB)
    {
        
        return 0;
    }
    public String sap(String nounA,String nounB)
    {
        
        return "";
    }
    public static void main(String[] args)
    {
       
      
      WordNet wordNet=new WordNet(args[0], args[1]);
      wordNet.nouns();
      wordNet.GetGraph();
    }
   
    
    private Bag<Bag<Integer>> GetGraph()
    {
        
        
      
        
        Bag<Bag<Integer>> WordNetIndexGraph;
        WordNetIndexGraph = new Bag<>();
        
        Iterator<String> iterator=HyperNymsStrings.iterator();
       /*
        for(int i=0;i<HyperNymsStrings.size();i++)
        {
            Vector<Integer> Vertexes=new Vector<>();// indexes of ","
            String hyperStr=iterator.next();
            Integer firstIndex=0;
            String subStr=hyperStr.substring(firstIndex,hyperStr.indexOf(","));
            subStr.getBytes();
            Vertexes.add(Integer.parseInt(subStr));
            int subStrIndexNext=hyperStr.indexOf(",");//index at first time
            while(subStrIndexNext!=-1)
            {
               Integer tmpIndex=hyperStr.indexOf(",",subStrIndexNext);
               
               subStr=hyperStr.substring(tmpIndex+1);
               Vertexes.add(Integer.parseInt(subStr));
               
               subStrIndexNext=subStr.indexOf(",");
               WordNetIndexGraph.add(Vertexes);
            }
            WordNetIndexGraph.getClass();
            System.out.println("\n");
            for(Integer vert:Vertexes)
            {
            System.out.println("vertx: "+vert+" Vertex.size() "+Vertexes.size());
            }
            System.out.println("\n");
            
        }
               */
       return new Bag<Bag<Integer>>();     
    }
 
    
}
