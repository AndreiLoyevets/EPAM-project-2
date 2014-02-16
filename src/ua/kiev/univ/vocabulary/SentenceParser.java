package ua.kiev.univ.vocabulary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class holds a text as a list of sentences, provides methods to read the
 * text and create a vocabulary of the words met in the text.
 * 
 * @version 1.0 26 Nov 2013
 * @author Andrei Loyevets
 */
public class SentenceParser {
	private Vocabulary vocabulary; // holds distinct words in lexicographical order
	private List<Sentence> text; // list of sentences
	
	public SentenceParser() {
		vocabulary = new Vocabulary();
		text = new ArrayList<Sentence>();
	}
	
	/**
	 * Reads the text sentence-by-sentence from scanner.
	 * @param scanner the source of input data
	 */
	public void parse(Scanner scanner) {
		Sentence temp = null;
		
		// read all sentences
		while (scanner.hasNext()) {
			temp = new Sentence();
			temp.read(scanner);
			text.add(temp);
		}
	}
	
	/**
	 * Generates vocabulary from the given list of sentences.
	 * @return the vocabulary created from the sentences
	 */
	public Vocabulary createVocabulary() {
		for (Sentence s : text) {
			
			// loop through all sentences
			for (SentenceObject token : s) {
				
				// loop through all tokens in the sentence
				if (token instanceof Word) {
					
					// add word to vocabulary
					vocabulary.addWord((Word) token);
				}
			}
		}
		
		return vocabulary;
	}
	
	public Vocabulary getVocabulary() {
		return vocabulary;
	}

	/**
	 * Creates parser which reads a text from the file to the list of sentences,
	 * creates vocabulary and prints its content to html file.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SentenceParser parser = new SentenceParser();
		
		try {
			parser.parse(new Scanner(new File("input.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open the file input.txt");
		}
		
		parser.createVocabulary().printToHTML("output.htm");
		System.out.println("Check the output in output.htm");
	}
}
