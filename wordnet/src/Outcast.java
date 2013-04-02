
public class Outcast
{
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet)
	{
		this.wordnet = wordnet;
	}
	
	public String outcast(String[] nouns)
	{
		int maxDistance, tmpDist;
		maxDistance = tmpDist = -1;
		
		String maxDistanceNoun = null;
		String n1 = nouns[0];
		for(String n2: nouns)
		{
			if(!n1.equals(n2))
			{
				tmpDist = this.wordnet.distance(n1, n2);
				System.out.println(n1 + " " + n2 + " : " + tmpDist);
				if(tmpDist > maxDistance)
				{
					maxDistance = tmpDist;
					maxDistanceNoun = n2;
				}
			}
		}
		
		return maxDistanceNoun;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	    WordNet wordnet = new WordNet("csv/synsets.txt", "csv/hypernyms.txt");
	    Outcast outcast = new Outcast(wordnet);
	    In in = null;
	    String[] outcastFiles = {"csv/outcast5.txt","csv/outcast8.txt","csv/outcast11.txt"};
	    //String[] outcastFiles = {"csv/outcast5.txt"};
	    for (int t = 0; t < outcastFiles.length; t++)
	    {
	    	in = new In(outcastFiles[t]);
	    	String[] nouns = in.readAllStrings();
	        StdOut.println(outcastFiles[t] + ": " + outcast.outcast(nouns));
	    }

	}

}
