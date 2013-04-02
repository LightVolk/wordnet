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
	private HashMap<String, Integer> nouns;
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
		this.nouns = new HashMap<String, Integer>();
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
				this.nouns.put(syn, id);
			
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
	
	public int getNounIndex(String noun)
	{
		if(this.nouns.containsKey(noun))
			return this.nouns.get(noun);
		else
			return -1;
	}
	
	public int getNumNouns()
	{
		return this.nouns.size();
	}
	
	public int distance(String nounA, String nounB)
	{
		int v = getNounIndex(nounA);
		int w = getNounIndex(nounB);
		
		if(v != -1 && w != -1)
		{
			SAP sap = new SAP(this.di);
			return sap.length(v, w);
		}
		
		return -1;
	}
	
	public String sap(String nounA, String nounB)
	{
		int intA = getNounIndex(nounA);
		int intB = getNounIndex(nounB);
		
		if(intA != -1 && intB != -1)
		{
			SAP sap = new SAP(this.di);
			int ancestor = sap.ancestor(intA, intB);
			
			BreadthFirstDirectedPaths bfsA = new BreadthFirstDirectedPaths(this.di, intA);
			BreadthFirstDirectedPaths bfsB = new BreadthFirstDirectedPaths(this.di, intB);
			
			Iterable<Integer> bfsAPath = bfsA.pathTo(ancestor);
			Iterable<Integer> bfsBPath = bfsB.pathTo(ancestor);
			StringBuilder sb = new StringBuilder();
			
			for(int v : bfsAPath)
				sb.append((v == intA) ? this.ints.get(v): "->" + this.ints.get(v));
			
			sb.append(" ");
			
			for(int v : bfsBPath)
				sb.append((v == intB) ? this.ints.get(v): "->" + this.ints.get(v));

			return sb.toString();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		WordNet wordnet = new WordNet("csv/synsets.txt", "csv/hypernyms.txt");
		System.out.println(wordnet.distance("American_water_spaniel", "histology"));
	}

}
