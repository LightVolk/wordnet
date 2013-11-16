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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class WordNet {
    
   private static  Bag<String> _synsetStrings = new Bag<>(),HyperNymsStrings = new Bag<>();
   private HashMap<String, Bag<Integer>> nouns;
   private HashMap<Integer, String> _ints;
  
   private static SAP _sap;
   private Digraph di;
   
  
    public WordNet(String synsets,String hypernyms)
    {
      
      
      this._sap=new SAP((this.di));
      
    }
    
    private void GetSynsets(String synsets)
        {
                In in = new In(synsets);
                LinkedList<Integer> ids = new LinkedList<Integer>();
                LinkedList<String> n = new LinkedList<String>();

                String[] line = null;
                while(!in.isEmpty())
                {
                        line = in.readLine().split(",");
                        ids.add(new Integer(line[0]));
                        n.add(line[1]);
                }

                this.di = new Digraph(ids.size());

                // initialize dictionary
                this.nouns = new HashMap<String, Bag<Integer>>();
                this._ints = new HashMap<Integer, String>();

                Iterator<Integer> itId = ids.iterator();
                Iterator<String> itNouns = n.iterator();

                int id = -1;
                String noun = null;
                String[] syns = null;
                while(itId.hasNext() && itNouns.hasNext())
                {
                        id = itId.next();
                        noun = itNouns.next();
                        syns = noun.split(" ");
                        for(String syn : syns)
                        {
                                if(!this.nouns.containsKey(syn))
                                {
                                        Bag<Integer> newBag = new Bag<Integer>();
                                        newBag.add(id);
                                        this.nouns.put(syn, newBag);
                                } else
                                {
                                        this.nouns.get(syn).add(id);
                                }
                        }

                        this._ints.put(id, noun);
                }
        }

        /**
         * 
         * Read the hypernym files
         */
        private void GetHypernyms(String hypernyms)
        {
                In in = new In(hypernyms);

                String[] line = null;
                Integer id;
                while(!in.isEmpty())
                {
                        line = in.readLine().split(",");
                        id = new Integer(line[0]);
                        for(int i = 1; i < line.length; i++)
                                di.addEdge(id, new Integer(line[i]));
                }
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
       return this.nouns.containsKey(word);
    }
    private Bag<Integer> GetNounIndex(String noun)
                        throws IllegalArgumentException
        {
                if(this.nouns.containsKey(noun))
                        return this.nouns.get(noun);
                else
                        throw new IllegalArgumentException("Noun not found: "
                                        + noun);
        }
    public int distance(String nounA,String nounB)
    {
         Bag<Integer> v = GetNounIndex(nounA);
         Bag<Integer> w = GetNounIndex(nounB);

                if(v != null && w != null)
                        return _sap.length(v, w);

                return -1;
    }
    public String sap(String nounA,String nounB)
    { 
                Bag<Integer> intA = GetNounIndex(nounA);
                Bag<Integer> intB = GetNounIndex(nounB);

                if(intA != null && intB != null)
                {
                        int ancestor = _sap.ancestor(intA, intB);
                        if(ancestor != -1)
                                return this._ints.get(ancestor);
                }

                return null;
    }
    public static void main(String[] args)
    {
    
     
    }
   
   
    
}
