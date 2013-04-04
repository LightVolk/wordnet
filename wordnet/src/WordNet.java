import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class WordNet
{
	/**
	 * @param args
	 */
	private Digraph di;
	//private String[] nouns;
	private HashMap<String, Bag<Integer>> nouns;
	private HashMap<Integer, String> ints;
	
	public WordNet(String synsets, String hypernums)
	{
		readSynsets(synsets);
		readHypernums(hypernums);
	}
	
	private void readSynsets(String synsets)
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
		
		//initialize dictionary
		this.nouns = new HashMap<String, Bag<Integer>>();
		this.ints = new HashMap<Integer, String>();
		
		Iterator<Integer> itId = ids.iterator();
		Iterator<String> itNouns = n.iterator();
		
		int id = -1;
		String noun = null;
		String [] syns = null;
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
				}
				else
				{
					this.nouns.get(syn).add(id);
				}
			}
			
			this.ints.put(id,noun);
		}
	}
	
	private void readHypernums(String hypernums)
	{
		In in = new In(hypernums);
		
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
	
	public boolean isNoun(String noun)
	{
		return this.nouns.containsKey(noun);
	}
	
	public Bag<Integer> getNounIndex(String noun)
	{
		if(this.nouns.containsKey(noun))
			return this.nouns.get(noun);
		else
			return null;
	}
	
	public int getNumNouns()
	{
		return this.nouns.size();
	}
	
	public int distance(String nounA, String nounB)
	{
		Bag<Integer> v = getNounIndex(nounA);
		Bag<Integer> w = getNounIndex(nounB);
		
		if(v != null && w != null)
		{
			SAP sap = new SAP(this.di);
			return sap.length(v, w);
		}
		
		return -1;
	}
	
	public String sap(String nounA, String nounB)
	{
		Bag<Integer> intA = getNounIndex(nounA);
		Bag<Integer> intB = getNounIndex(nounB);
		
		if(intA != null && intB != null)
		{
			SAP sap = new SAP(this.di);
			int ancestor = sap.ancestor(intA, intB);
			
			DeluxeBFS bfsA = new DeluxeBFS(this.di, intA);
			DeluxeBFS bfsB = new DeluxeBFS(this.di, intB);
			
			StringBuilder sb = new StringBuilder();
			
			for(int v : bfsA.pathTo(ancestor))
				sb.append("->" + this.ints.get(v));
			
			sb.append(" | ");
			
			for(int v : bfsB.pathTo(ancestor))
				sb.append("->" + this.ints.get(v));

			return sb.toString();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		WordNet wordnet = new WordNet("csv/synsets.txt", "csv/hypernyms.txt");
		//System.out.println(wordnet.distance("histology","American_water_spaniel"));
		//System.out.println(wordnet.sap("histology","American_water_spaniel"));
		
		
		System.out.println(wordnet.distance("horse","zebra"));
		System.out.println(wordnet.distance("horse","cat"));
		System.out.println(wordnet.distance("horse","bear"));
		System.out.println(wordnet.distance("horse","table"));
		
		//System.out.println(wordnet.sap("horse", "zebra"));
		//System.out.println(wordnet.distance("horse", "zebra"));
		
		//System.out.println(wordnet.sap("horse", "table"));
		//System.out.println(wordnet.distance("horse", "table"));
	}

}
