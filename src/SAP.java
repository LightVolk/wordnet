
import java.util.Collections;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simplevolk
 */

public class SAP {
  
   private static Digraph Dig;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
       Dig=G;
    
    }
    // length of shortest ancestral path between v and w; -1 if no such path
    public int lenght(int v,int w)
    {
       Integer ances=ancestor(v, w);
        
        if(ances!=-1)
        {
            BreadthFirstDirectedPaths bfpV=new BreadthFirstDirectedPaths(Dig, v);
            BreadthFirstDirectedPaths bfpW=new BreadthFirstDirectedPaths(Dig, w);
            
            return bfpV.distTo(ances)+bfpV.distTo(ances);
        }
    
     return -1;   
    }

    private LinkedBag<Integer> GetCommon(Iterable<Integer> pathVtoW,Iterable<Integer> pathWtoV,int v,int w)
    {
        LinkedBag<Integer> Common=new LinkedBag<>();
        if(pathVtoW!=null&&pathWtoV!=null)
        {
        for (Iterator it = pathVtoW.iterator(); it.hasNext();) 
        {
            Object object = it.next();
            for (Iterator it1 = pathWtoV.iterator(); it1.hasNext();)
            {
                Object object1 = it1.next();
                if((Iterator)object==(Iterator)object1)
                    Common.add((Integer)object); /// add Common elements.
            }
        }
        }
        else if(pathVtoW!=null&&pathWtoV==null)
        {
            for (Iterator it = pathVtoW.iterator(); it.hasNext();) {
                Object object = it.next();
                Common.add((Integer)object);
            }
        }
        else if(pathWtoV!=null&&pathVtoW==null)
        {
            for (Iterator it = pathWtoV.iterator(); it.hasNext();) {
                Object object = it.next();
                Common.add((Integer)object);
            }
        }
       return Common;
    }
    // a Common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v,int w)
    {
        
        LinkedBag<Integer> Common=new LinkedBag<>();
        
        BreadthFirstDirectedPaths bfpV=new BreadthFirstDirectedPaths(Dig, v);
        BreadthFirstDirectedPaths bfpW=new BreadthFirstDirectedPaths(Dig, w);
        
        Iterable<Integer> pathVtoW=bfpV.pathTo(w);
        Iterable<Integer> pathWtoV=bfpW.pathTo(v);
     
        
        
        Common=GetCommon(pathVtoW, pathWtoV, v, w);
        
        Integer minPathV=-1,minPathW=-1;
        Integer tmpV=0,tmpW=0;
        Integer commonElemV=0,commonElemW=0;
        boolean firstTime=false;
        for (Iterator it = Common.iterator(); it.hasNext();) {
            Object object = it.next();
            
            tmpV=bfpV.distTo((Integer)object);
            if(firstTime==false)
            {
            minPathV=tmpV;
            firstTime=true;
            }
            
            if(minPathV<tmpV)
            {
                minPathV=tmpV;
                commonElemV=(Integer)object;
            }
            
            if(minPathW<tmpW)
            {
                minPathW=tmpW;
                commonElemW=(Integer)object;
            }
        }
        
        if(minPathV<=minPathW)
            return commonElemV;
        else return commonElemW;
    }
    
  
    public static void main(String[] args)
    {
     In in = new In("txt/digraph1.txt");
     Digraph di = new Digraph(in);

     SAP sap = new SAP(di);
     
     System.out.println(sap.ancestor(12, 1));
    // System.out.println(sap.lenght(12, 7));
    }

  
}
