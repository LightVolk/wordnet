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
    
   private static  Bag<String> _synsetStrings = new Bag<>(),HyperNymsStrings = new Bag<>();
   private static Bag<String> _Nouns=new Bag<String>();
   private static Bag<Integer> _indexesOfNouns=new Bag<Integer>();
   private static SAP _sap;
   private Digraph di;
   
   public static int V=10;
    public WordNet(String synsets,String hypernyms)
    {
        int count=0;
       
      In in_syn = new In(synsets);
      while(in_syn.hasNextLine())
          {
              _synsetStrings.add(in_syn.readLine());
          }
      In in_hyp=new In(hypernyms);
      while(in_hyp.hasNextLine())
      {
          HyperNymsStrings.add(in_hyp.readLine());
      }
      
      this._sap=new SAP((this.di));
      
    }
    public Iterable<String> nouns()
    {       
        Bag<String> NounsTmp=new Bag<>();
          
            for (String string:_synsetStrings) 
                {
                 int subtIndex=0;subtIndex=string.indexOf(",");
                 NounsTmp.add(string.substring(subtIndex+1));
                }
          
        return (Iterable<String>)NounsTmp;   
    }
    
    public boolean isNoun(String word)
    {
        for(String string:_Nouns)
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
    
     
    }
   
   
    
}
