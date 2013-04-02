
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
		for(String n1 : nouns)
		{
			for(String n2: nouns)
			{
				if(!n1.equals(n2))
				{
					tmpDist = this.wordnet.distance(n1, n2);
					if(tmpDist > maxDistance)
					{
						maxDistance = tmpDist;
						maxDistanceNoun = n2;
					}
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
		// TODO Auto-generated method stub

	}

}
