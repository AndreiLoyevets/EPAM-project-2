package ua.kiev.univ.vocabulary;

/**
 * This class represents a word, provides method to compare 2 words by their
 * lexicographical order.
 * 
 * @version 1.0 26 Nov 2013
 * @author Andrei Loyevets
 */
public class Word implements Comparable<Word>, SentenceObject {
	private String word;
	
	public Word(String word) {
		this.word = word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}

	@Override
	public int compareTo(Word anotherWord) {
		return word.compareToIgnoreCase(anotherWord.getWord());
	}
}