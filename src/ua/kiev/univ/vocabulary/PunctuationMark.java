package ua.kiev.univ.vocabulary;

/**
 * This class represents a punctuation mark, provides methods to check
 * whether the symbol is a punctuation mark and whether it marks the end of
 * sentence.
 * 
 * @version 1.0 26 Nov 2013
 * @author Andrei Loyevets
 */
public class PunctuationMark extends Symbol implements SentenceObject {
	private boolean endOfSentence; // true if the symbol means end of sentence
	
	/**
	 * Creates a PunctuationMark object by the given character symbol.
	 * @param symbol the character symbol which represents the punctuation mark
	 */
	private PunctuationMark(char symbol) {
		super(symbol);
		
		if (symbol == '.' || symbol == '?' || symbol == '!') {
			endOfSentence = true;
		}
	}
 	
 	/**
 	 * Checks whether the given symbol is a punctuation mark.
 	 * @param symbol the symbol to be checked
 	 * @return true if the symbol is a punctuation mark, false - otherwise
 	 */
 	public static boolean isPunctuation(char symbol) {
 		if (symbol >= 'a' && symbol <= 'z' || symbol >= 'A' && symbol <= 'Z' ||
 				symbol == '\'') {
 			return false;
 		} else {
 			return true;
 		}
 	}
 	
 	/**
 	 * Creates a PunctuationMark object in case the input symbol is really
 	 * a punctuation mark.
 	 * @param symbol the punctuation mark
 	 * @return the newly created object or null, if it was not created
 	 */
 	public static PunctuationMark create(char symbol) {
 		if (isPunctuation(symbol)) {
 			return new PunctuationMark(symbol);
 		} else {
 			return null;
 		}
 	}
 	
 	/**
 	 * Checks whether the punctuation mark represents the end of a sentence.
 	 * @return true if the punctuation mark means the end of a sentence,
 	 *         false - otherwise
 	 */
 	public boolean isEndOfSentence() {
 		return endOfSentence;
 	}
}