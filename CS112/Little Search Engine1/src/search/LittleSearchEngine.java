package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		// COMPLETE THIS METHODD
		if (docFile == null) {
            throw new FileNotFoundException();
        }
        HashMap<String, Occurrence> keys = new HashMap<String, Occurrence>();
        BufferedReader reader = new BufferedReader(new FileReader(docFile));
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    String phrase = getKeyWord(st.nextToken());
                    if (phrase != null) {
                        if (phrase.contains(phrase)) {
                            keys.put(phrase, new Occurrence(docFile, 1));
                        } else {
                            keys.get(phrase).frequency++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keys;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// COMPLETE THIS METHOD
		Set<String> key = kws.keySet();
        Iterator<String> itre = key.iterator();
        while (itre.hasNext())
        {
            String word = itre.next();
            Occurrence occ = kws.get(word);
            if (!keywordsIndex.containsKey(word)){ // keyword is not in keywordIndex
                ArrayList<Occurrence> occurrences = new ArrayList<Occurrence>();
                Occurrence xyz = new Occurrence(occ.document, occ.frequency);
                occurrences.add(xyz);
                keywordsIndex.put(word, occurrences);

            } else{ // keyword is a duplicate
                Occurrence xyz = new Occurrence(occ.document, occ.frequency);
                keywordsIndex.get(word).add(xyz);
                insertLastOccurrence(keywordsIndex.get(word));
            }
        }
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		// COMPLETE THIS METHOD
		word = word.toLowerCase();
        while (word.length() > 0 && !(Character.isDigit(word.charAt(word.length() - 1))) && !(Character.isLetter(word.charAt(word.length() - 1)))) {
            char ch = word.charAt(word.length()-1);
            if((isPunctuation(ch))){
                word = word.substring(0, word.length() - 1);
            } else {
                return null;
            }
        }

        for (int i = 0; i < word.length(); i++) {
            if (!(Character.isLetter(word.charAt(i)))) {
                return null;
            }
        }

        if (noiseWords.containsKey(word))
            return null;
        return word;
	}
	private boolean isPunctuation(char c) {
        if (c == '.' || c == ',' || c == '?' || c == ':' || c == ';' || c == '!')
            return true;
        return false;
    }
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// COMPLETE THIS METHOD
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int low = 0;
        int high = occs.size() - 1;
        int x = occs.size() - 1;
        int mid = 0;
        Occurrence occ = occs.remove(occs.size() - 1);
        while (low <= high) {
            mid = (low + high) / 2;
            arrayList.add(mid);
            if (occ.frequency < occs.get(mid).frequency) {
                low = mid + 1;
            }
            if (occ.frequency > occs.get(mid).frequency) {
                high = mid - 1;
            }
            if (occ.frequency == occs.get(mid).frequency) {
                break;
            }
        }
        if (low > high) {
            mid = low;
        }
        if (mid != occs.size()) {
            occs.add(mid, occ);
        } else if (mid == occs.size()) {
            occs.add(occ);
        }
        return arrayList;
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		// COMPLETE THIS METHOD
        ArrayList<Occurrence> Res1 = keywordsIndex.get(getKeyWord(kw1));
        ArrayList<Occurrence> res2 = keywordsIndex.get(getKeyWord(kw1));
        ArrayList<String> arrayList = new ArrayList<String>();

        while (arrayList.size() < 6) {
            Occurrence aa = null;
            Occurrence b = null;
            {
                if (Res1 != null) {
                    for (Occurrence occr : Res1) {
                        if (!arrayList.contains(occr.document)) {
                            aa = occr;
                        }
                    }
                }  if(Res1 == null){
                    aa = new Occurrence(null, 0);
                }
                else aa = new Occurrence(null,0);
            }
            {
                if(res2 != null){
                    for(Occurrence occr : res2){
                        if(!arrayList.contains(occr.document)){
                            b = occr;
                        }
                    }
                }
                 if(res2 == null){
                    b = new Occurrence(null,0);
                }
                else b = new Occurrence(null,0);
            }
            Occurrence[] a = {aa,b};
            Occurrence m = null;
            if (a[0].frequency < a[1].frequency) {
                m = a[1];
            } else m = a[0];
            if (m.document != null) {
                arrayList.add(m.document);
            } else break;
        }
        return arrayList;
	}
}
