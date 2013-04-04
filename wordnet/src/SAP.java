
public class SAP 
{
	private Digraph G;
	
	public SAP(Digraph G)
	{
		this.G = G;
	}
	
	/**
	 * Same as length(Iterable<Integer> v, Iterable<Integer> w) but using two vertices.
	 * This method that is NOT used in Wordnet since we associate a noun with a Bag
	 * of vertices. A noun can be in multiple synsets.
	 */
	public int length(int v, int w)
	{
		int ancestor = ancestor(v, w);
		if(ancestor != -1)
		{
			DeluxeBFS bfsV = new DeluxeBFS(this.G, v);
			DeluxeBFS bfsW = new DeluxeBFS(this.G, w);
			
			return bfsV.distTo(ancestor) + bfsW.distTo(ancestor);
		}
		
		return -1;
	}
	
	/**
	 * Same as ancestor(Iterable<Integer> v, Iterable<Integer> w) but using two vertices.
	 * This method that is NOT used in Wordnet since we associate a noun with a Bag
	 * of vertices. A noun can be in multiple synsets.
	 */
	public int ancestor(int v, int w)
	{
		DeluxeBFS bfsV = new DeluxeBFS(this.G, v);
		Stack<Integer> qV = bfsV.getMarked();
		
		DeluxeBFS bfsW = new DeluxeBFS(this.G, w);
		Stack<Integer> qW = bfsW.getMarked();
		
		int minDistance = -1;
		int tmpDistance = -1;
		int result = -1;
		
		while(!qV.isEmpty() && !qW.isEmpty())
		{	
			if(qV.peek().equals(qW.peek()))
			{
				tmpDistance = bfsV.distTo(qV.peek().intValue()) + bfsW.distTo(qW.peek().intValue()); 
				if(tmpDistance < minDistance || minDistance == -1)
				{
					minDistance = tmpDistance;
					result = qV.peek();
				}
				qV.pop();
				qW.pop();
			}
			else if(qV.peek().intValue() > qW.peek().intValue())
				qV.pop();
			else
				qW.pop();
		}
		
		return result;
	}
	
	/**
	 * Find the lowest distance between two sets of vertices
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		int ancestor = ancestor(v,w);
		if(ancestor != -1)
		{
			DeluxeBFS bfsV = new DeluxeBFS(this.G, v);
			DeluxeBFS bfsW = new DeluxeBFS(this.G, w);
			
			return bfsV.distTo(ancestor) + bfsW.distTo(ancestor);
		}
		
		return -1;
	}
	
	/**
	 * Getting the closest ancestor for two sets of vertices.
	 * Get all visited vertices on a ordered stack.
	 * Find the common vertices between the ones for v and w.
	 * Find the pair of common vertices that has the lowest distance between them.
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		DeluxeBFS bfsV = new DeluxeBFS(this.G, v);
		DeluxeBFS bfsW = new DeluxeBFS(this.G, w);
		
		//the stack of visited vertices is ordered
		Stack<Integer> qV = bfsV.getMarked();
		Stack<Integer> qW = bfsW.getMarked();
		
		int minDistance = -1;
		int tmpDistance = -1;
		int result = -1;
		
		//traverse the two stacks simultaneaously
		while(!qV.isEmpty() && !qW.isEmpty())
		{	
			if(qV.peek().equals(qW.peek())) 
			{
				tmpDistance = bfsV.distTo(qV.peek().intValue()) + bfsW.distTo(qW.peek().intValue()); 
				if(tmpDistance < minDistance || minDistance == -1)
				{
					minDistance = tmpDistance;
					result = qV.peek();
				}
				qV.pop();
				qW.pop();
			}
			else if(qV.peek().intValue() > qW.peek().intValue())
				qV.pop();
			else
				qW.pop();
		}
		
		return result;
	}
	
	public static void main(String[] args) 
	{
		/*
    	In in = new In("csv/digraph1.txt");
    	Digraph di = new Digraph(in);
    	//System.out.println(di);
    	SAP sap = new SAP(di);
    	//ancestor
    	System.out.println(sap.ancestor(12, 7));
    	//length
    	System.out.println(sap.length(12, 7));
    	*/
	}
}