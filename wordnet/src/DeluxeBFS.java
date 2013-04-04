import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class DeluxeBFS
{
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // marked[v] = is there an s->v path?
	private LinkedList<Integer> markedVertex;

	private int[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
	private int[] distTo; // distTo[v] = length of shortest s->v path

	// single source
	public DeluxeBFS(Digraph G, int s)
	{
		marked = new boolean[G.V()];
		markedVertex = new LinkedList<Integer>();
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];

		for(int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		bfs(G, s);
	}

	// multiple sources
	public DeluxeBFS(Digraph G, Iterable<Integer> sources)
	{
		marked = new boolean[G.V()];
		markedVertex = new LinkedList<Integer>();
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		bfs(G, sources);
	}

	// BFS from single source
	private void bfs(Digraph G, int s)
	{
		Queue<Integer> q = new Queue<Integer>();
		marked[s] = true;
		markedVertex.add(s);
		distTo[s] = 0;
		q.enqueue(s);
		while(!q.isEmpty())
		{
			int v = q.dequeue();
			for(int w : G.adj(v))
			{
				if(!marked[w])
				{
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					markedVertex.add(w);
					q.enqueue(w);
				}
			}
		}
	}

	// BFS from multiple sources
	private void bfs(Digraph G, Iterable<Integer> sources)
	{
		Queue<Integer> q = new Queue<Integer>();
		for(int s : sources)
		{
			marked[s] = true;
			markedVertex.add(s);
			distTo[s] = 0;
			q.enqueue(s);
		}
		while(!q.isEmpty())
		{
			int v = q.dequeue();
			for(int w : G.adj(v))
			{
				if(!marked[w])
				{
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					markedVertex.add(w);
					q.enqueue(w);
				}
			}
		}
	}

	// length of shortest path from s (or sources) to v
	public int distTo(int v)
	{
		return distTo[v];
	}

	// is there a directed path from s (or sources) to v?
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}

	// shortest path from s (or sources) to v; null if no such path
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for(x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	/**
	 * Gives the list of visited vertices in the form of a stack
	 */
	public Stack<Integer> getMarked()
	{
		Stack<Integer> result = new Stack<Integer>();
		Collections.sort(markedVertex);
		Iterator<Integer> it = markedVertex.iterator();

		while(it.hasNext())
			result.push(it.next());

		return result;
	}

	public static void main(String[] args)
	{
		In in = new In("csv/digraph1.txt");
		Digraph G = new Digraph(in);
		// StdOut.println(G);

		int s = 7;
		DeluxeBFS bfs = new DeluxeBFS(G, s);

		for(int v = 0; v < G.V(); v++)
		{
			if(bfs.hasPathTo(v))
			{
				StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
				for(int x : bfs.pathTo(v))
				{
					if(x == s)
						StdOut.print(x);
					else
						StdOut.print("->" + x);
				}
				StdOut.println();
			} else
			{
				StdOut.printf("%d to %d (-):  not connected\n", s, v);
			}
		}
	}
}