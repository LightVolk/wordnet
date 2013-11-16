/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simplevolk
 */
public class Outcast {
    private WordNet _wordnet;

    private Outcast(WordNet wordnet) {
          this._wordnet = wordnet;
    }
    
    public String outcast(String[] nouns)
        {
                int maxDistance = -1;
                int maxDistanceIndex = -1;
                // use two-dimensional array to calculate distances
                int[][] distances = new int[nouns.length][nouns.length];

                // calculating total relative distances
                // the total distance for a noun is the sum of the distances to each of
                // the other
                // nouns in the list
                for(int i = 0; i < nouns.length; i++)
                {
                        for(int j = 0; j < nouns.length; j++)
                                distances[i][j] = (i == j) ? 0 : this._wordnet.distance(
                                                nouns[i], nouns[j]);
                }

                // summing distances to obtain total distances
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
                return nouns[maxDistanceIndex];
        }

        /**
         * @param args
         */
        public static void main(String[] args)
        {
                 
                WordNet _wordnet = new WordNet("txt/synsets.txt", "txt/hypernyms.txt");
                Outcast outcast = new Outcast(_wordnet);
                In in = null;
                String[] outcastFiles =        { "txt/out1.txt", "txt/out2.txt", "txt/out3.txt" };
             
                for(int t = 0; t < outcastFiles.length; t++)
                {
                        in = new In(outcastFiles[t]);
                        String[] nouns = in.readAllStrings();
                        StdOut.println(outcastFiles[t] + ": " + outcast.outcast(nouns));
                }
                 
        }
}
