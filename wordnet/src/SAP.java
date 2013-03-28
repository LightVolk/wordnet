import java.util.Iterator;


public class SAP 
{
	private Digraph G;
	public SAP(Digraph G)
	{
		this.G = G;
	}
	
	public int length(int v, int w)
	{
		return -1;
	}
	
	public int ancestor(int v, int w)
	{
		int result = -1;
		Iterator<Integer> itV = G.adj(v).iterator(); 
		Iterator<Integer> itW = G.adj(w).iterator();
		
				
		Integer currentV, currentW;
		while(itV.hasNext() || itW.hasNext())
		{
			//if(itV.hasNext())
		}
		return result;
	}
	
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		return -1;
	}
	
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		int result = -1;
		Iterator<Integer> itV = v.iterator();
		Integer tmpInt = -1;
		boolean ancestorFound = false;
		while(itV.hasNext() && !ancestorFound)
		{
			tmpInt = itV.next();
			Iterator<Integer> itW = w.iterator();
			while(itW.hasNext())
			{
				if(tmpInt != itW.next())
				{
					ancestorFound = true;
					result = tmpInt;
					break;
				}
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) 
	{
    	In in = new In("csv/digraph1.txt");
    	Digraph di = new Digraph(in);
    	System.out.println(di.toString());
    	
	}

}
