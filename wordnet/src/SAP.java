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
		if(v == w)
			return 0;
		
		int result = length(G.adj(v), G.adj(w));
		
		if(result != -1)
		{
			return result;
		}
		else
		{
			Iterator<Integer> itV = G.adj(v).iterator();
			Iterator<Integer> itW = G.adj(w).iterator();
			
			if(v > w && itV.hasNext())
				return 1 + length(itV.next(), w);
			else if(v < w && itW.hasNext())
				return 1 + length(v, itW.next());
			else
				return -1;
		}
	}
	
	public int ancestor(int v, int w)
	{
		if(v == w)
			return v;
		
		int result = ancestor(G.adj(v), G.adj(w));
		if(result != -1)
		{
			return result;
		}
		else
		{
			Iterator<Integer> itV = G.adj(v).iterator();
			Iterator<Integer> itW = G.adj(w).iterator();
			
			if(v > w && itV.hasNext())
				return ancestor(itV.next(), w);
			else if(v < w && itW.hasNext())
				return ancestor(v, itW.next());
			else
				return -1;
		}
	}
	
	public int length(Iterable<Integer> v, Iterable<Integer> w)
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
				if(tmpInt == itW.next())
				{
					ancestorFound = true;
					result = 2;
					break;
				}
			}
		}	
		return result;
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
				if(tmpInt == itW.next())
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
    	//System.out.println(di);
    	SAP sap = new SAP(di);
    	//ancestor
    	System.out.println(sap.ancestor(12, 7));
    	//length
    	System.out.println(sap.length(12, 7));
	}
}