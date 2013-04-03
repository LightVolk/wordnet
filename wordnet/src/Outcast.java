
public class Outcast
{
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet)
	{
		this.wordnet = wordnet;
	}
	
	public String outcast(String[] nouns)
	{
		int maxDistance = -1;
		int maxDistanceIndex = -1;

		int [][] distances = new int[nouns.length][nouns.length];
		
		for(int i = 0; i < nouns.length; i++)
		{
			for(int j = 0; j < nouns.length; j++)
				distances [i][j] = (i == j) ? 0 : this.wordnet.distance(nouns[i],nouns[j]); 
		}
		
		for(int i = 0; i < distances[0].length; i++)
		{
			int sumDistance = 0;
			
			for(int j = 0; j < distances[i].length; j++)
				sumDistance += distances[i][j];
			
			if(sumDistance > maxDistance)
			{
				maxDistance = sumDistance;
				maxDistanceIndex = i;
			}
		}
		System.out.println(maxDistance);
		return nouns[maxDistanceIndex];
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
