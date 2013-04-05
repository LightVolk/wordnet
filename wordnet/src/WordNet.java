import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class WordNet
{
	/**
	 * @param args
	 */
	private Digraph di;
	// private String[] nouns;
	private HashMap<String, Bag<Integer>> nouns;
	private HashMap<Integer, String> ints;
	private SAP sap;
	
	public WordNet(String synsets, String hypernyms)
	{
		readSynsets(synsets);
		readHypernyms(hypernyms);
		this.sap = new SAP(this.di);
	}

	/**
	 * 
	 * Read the synset files and construct reference data structures for easy
	 * lookup.
	 * 
	 */
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

		// initialize dictionary
		this.nouns = new HashMap<String, Bag<Integer>>();
		this.ints = new HashMap<Integer, String>();

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

			this.ints.put(id, noun);
		}
	}

	/**
	 * 
	 * Read the hypernym files
	 */
	private void readHypernyms(String hypernyms)
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

	public boolean isNoun(String noun)
	{
		return this.nouns.containsKey(noun);
	}

	public Iterable<String> nouns()
	{
		return this.nouns.keySet();
	}

	private Bag<Integer> getNounIndex(String noun)
			throws IllegalArgumentException
	{
		if(this.nouns.containsKey(noun))
			return this.nouns.get(noun);
		else
			throw new IllegalArgumentException("Noun is not in WordNet: "
					+ noun);
	}

	/**
	 * 
	 * Calculates the distances between two nouns
	 */
	public int distance(String nounA, String nounB)
			throws IllegalArgumentException
	{
		Bag<Integer> v = getNounIndex(nounA);
		Bag<Integer> w = getNounIndex(nounB);

		if(v != null && w != null)
			return sap.length(v, w);

		return -1;
	}

	/**
	 * Find the SAP between nounA and nounB.
	 */
	public String sap(String nounA, String nounB)
			throws IllegalArgumentException
	{
		Bag<Integer> intA = getNounIndex(nounA);
		Bag<Integer> intB = getNounIndex(nounB);

		if(intA != null && intB != null)
		{
			int ancestor = sap.ancestor(intA, intB);
			if(ancestor != -1)
				return this.ints.get(ancestor);
		}

		return null;
	}

	public static void main(String[] args)
	{
	}

}
