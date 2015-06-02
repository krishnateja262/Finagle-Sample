package com.voodoo.server.algos;

/**
 * Created by krishna on 6/3/15.
 */
public class TitleSimilarity {

    public static double similarityIndex(String a, String b){
        int wordDistance = Levenshtein.wordComparision(a,b);
        return 10*CosineSimilarity.wordSimilarity(a,b) - Math.sqrt(wordDistance*wordDistance+Levenshtein.charComparision(a,b));
    }
}
