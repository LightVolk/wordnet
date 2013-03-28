import java.util.LinkedList;


public class WordNet
{
	/**
	 * @param args
	 */
	private Digraph di;
	private String[] nouns;
	
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
		this.nouns = n.toArray(new String[n.size()]);
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
				di.addEdge(new Integer(line[i]), id);
		}
	}
	
	public boolean isNoun(String word)
	{
		boolean result = false;
		
		for(int i = 0; i < this.nouns.length; i++)
		{
			if(this.nouns[i].equals(word))
			{
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public int getNumNouns()
	{
		return this.nouns.length;
	}
	
	
	public int distance(String nounA, String nounB)
	{
		return -1;
	}
	
	public String sap(String nounA, String nounB)
	{
		return null;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		WordNet wordnet = new WordNet("csv/synsets.txt", "csv/hypernyms.txt");
		System.out.println(wordnet.getNumNouns());
		System.out.println(wordnet.isNoun("hui"));
		
	}

}
