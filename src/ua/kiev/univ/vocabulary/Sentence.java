package ua.kiev.univ.vocabulary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class represents a sentence which consists of a list of words and
 * punctuation marks (both implement SentenceObject). Provides methods to read
 * sentence, add elements to the sentence, iterator.
 * 
 * @version 1.0 26 Nov 2013
 * @author Andrei Loyevets
 */
public class Sentence implements Iterable<SentenceObject> {
	private List<SentenceObject> tokens; // words and punctuation marks
	
	public Sentence() {
		tokens = new ArrayList<SentenceObject>();
	}
	
	/**
	 * Adds new token to the sentence (to the list of tokens).
	 * @param token the element to be added to the sentence
	 */
	public void addToken(SentenceObject token) {
		tokens.add(token);
	}
	
	/**
	 * Reads the sentence tokens from Scanner.
	 * @param scanner the source of input data
	 */
	public void read(Scanner scanner) {
		String temp = null;
		int wordLength = 0;
		boolean endOfSentence = false;
		Stack<PunctuationMark> marks = new Stack<PunctuationMark>();
		
		do {
			
			/* Read the sentence string-by-string until we meet the punctuation
			 * mark which represents the end of a sentence or the last string
			 * was processed.
			*/
			if (scanner.hasNext()) {
				temp = scanner.next();
				
				// check the last symbol
				wordLength = temp.length();
				
				// try to create a punctuation mark
				marks.push(PunctuationMark.create((temp.charAt(wordLength - 1))));
				if (marks.peek() == null) {
					
					// the last symbol is not a punctuation mark
					marks.pop();
					
					// create new word from temp and add it to the list
					addToken(new Word(temp));
				} else {
					
					// mark - punctuation mark
					--wordLength;
					
					if (marks.peek().isEndOfSentence()) {
						endOfSentence = true;
					}
					
					// read all punctuation marks to get pure text
					for (int i = wordLength - 1; i >= 0; --i) {
							marks.push(PunctuationMark.create(temp.charAt(i)));
							if (marks.peek() == null) {
								
								// it is not a punctuation mark
								marks.pop();
								break;
							} else {
								
								// punctuation mark, cut the word
								--wordLength;
							}
					}
					
					// finally add the word to the list
					if (wordLength > 0) {
						addToken(new Word(temp.substring(0, wordLength)));
					}
					
					// add all punctuation marks to the list
					while (!marks.empty()) {
						addToken(marks.pop());
					}
				}
			} else {
				endOfSentence = true;
			}
		}
		while (!endOfSentence);
	}

	@Override
	public Iterator<SentenceObject> iterator() {
		return tokens.iterator();
	}
}