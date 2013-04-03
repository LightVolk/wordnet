
public class SAP 
{
	private Digraph G;
	public SAP(Digraph G)
	{
		this.G = G;
	}
	
	public int length(int v, int w)
	{
		int ancestor = ancestor(v, w);
		if(ancestor != -1)
		{
			BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
			BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
			
			return bfsV.distTo(ancestor) + bfsW.distTo(ancestor);
		}
		
		return -1;
	}
	
	public int ancestor(int v, int w)
	{

		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
		Stack<Integer> qV = bfsV.getMarked();
		
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
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

	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		int ancestor = ancestor(v,w);
		if(ancestor != -1)
		{
			BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
			BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
			
			return bfsV.distTo(ancestor) + bfsW.distTo(ancestor);
		}
		
		return -1;
	}
	
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
		
		Stack<Integer> qV = bfsV.getMarked();
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
	
	public static void main(String[] args) 
	{
    	In in = new In("csv/digraph1.txt");
    	Digraph di = new Digraph(in);
    	//System.out.println(di);
    	SAP sap = new SAP(di);
    	//ancestor
    	System.out.println(sap.ancestor(12, 7));
    	//length
    	System.out.println(sap.length(12, 7));
	}
}