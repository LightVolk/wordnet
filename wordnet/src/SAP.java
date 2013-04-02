
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
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
		
		Stack<Integer> qV = bfsV.marked();
		Stack<Integer> qW = bfsW.marked();
		
		int result = -1;
		
		while(!qV.isEmpty() && !qW.isEmpty())
		{	
			if(qV.peek() == qW.peek())
			{
				result = qV.peek();
				break;
			}
			else if(qV.peek() > qW.peek())
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
		
		Stack<Integer> qV = bfsV.marked();
		Stack<Integer> qW = bfsW.marked();
		
		int result = -1;
		
		while(!qV.isEmpty() && !qW.isEmpty())
		{
			if(qV.peek() == qW.peek())
			{
				result = qV.peek();
				break;
			}
			else if(qV.peek() > qW.peek())
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