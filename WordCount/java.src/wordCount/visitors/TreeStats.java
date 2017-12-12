package wordCount.visitors;

import java.util.ArrayList;
import java.util.List;

public class TreeStats {

	/**
	 * <p>
	 * This variable stores total word count.
	 * </p>
	 */
	private int totalWordCount;

	/**
	 * <p>
	 * This variable stores total number of characters.
	 * </p>
	 */
	private int totalNumberOfCharacters;

	/**
	 * <p>
	 * This variable stores the frequency of most frequent words.
	 * </p>
	 *
	 */
	private int frequencyMostFreqWords;

	/**
	 * <p>
	 * This variable stores a list of most frequent words.
	 * </p>
	 */
	private List<String> mostFreqWords;

	public TreeStats() {
		totalWordCount = 0;
		frequencyMostFreqWords = 0;
		totalNumberOfCharacters = 0;
		mostFreqWords = new ArrayList<String>();
	}

	public TreeStats(int totalWordCount, int frequencyMostFreqWord, int totalNumberOfCharacters) {
		super();
		this.totalWordCount = totalWordCount;
		this.frequencyMostFreqWords = frequencyMostFreqWord;
		this.totalNumberOfCharacters = totalNumberOfCharacters;
	}

	/**
	 * @return the totalWordCount
	 */
	public int getTotalWordCount() {
		return totalWordCount;
	}

	/**
	 * @param totalWordCount
	 *            the totalWordCount to set
	 */
	public void setTotalWordCount(int totalWordCount) {
		this.totalWordCount = totalWordCount;
	}

	/**
	 * @return the frequencyMostFreqWords
	 */
	public int getFrequencyMostFreqWord() {
		return frequencyMostFreqWords;
	}

	/**
	 * @param frequencyMostFreqWords
	 *            the frequencyMostFreqWords to set
	 */
	public void setFrequencyMostFreqWord(int frequencyMostFreqWord) {
		this.frequencyMostFreqWords = frequencyMostFreqWord;
	}

	/**
	 * @return the totalNumberOfCharacters
	 */
	public int getTotalNumberOfCharacters() {
		return totalNumberOfCharacters;
	}

	/**
	 * @param totalNumberOfCharacters
	 *            the totalNumberOfCharacters to set
	 */
	public void setTotalNumberOfCharacters(int totalNumberOfCharacters) {
		this.totalNumberOfCharacters = totalNumberOfCharacters;
	}

	/**
	 * @return the frequencyMostFreqWords
	 */
	public int getFrequencyMostFreqWords() {
		return frequencyMostFreqWords;
	}

	/**
	 * @param frequencyMostFreqWords
	 *            the frequencyMostFreqWords to set
	 */
	public void setFrequencyMostFreqWords(int frequencyMostFreqWords) {
		this.frequencyMostFreqWords = frequencyMostFreqWords;
	}

	/**
	 * @return the mostFreqWords
	 */
	public List<String> getMostFreqWords() {
		return mostFreqWords;
	}

	/**
	 * @param mostFreqWords
	 *            the mostFreqWords to set
	 */
	public void setMostFreqWords(List<String> mostFrequentWords) {
		this.mostFreqWords = mostFrequentWords;
	}

	/**
	 * <p>
	 * This method updates list of most frequent words and frequency of most
	 * frequent words based on given input.
	 * </p>
	 * 
	 * @param word
	 *            word which is to be processed
	 * @param noOccurences
	 *            number of times the given word has occurred in the input file.
	 */
	public void updateStats(String word, int noOccurences) {
		if (noOccurences == frequencyMostFreqWords) {
			mostFreqWords.add(word);
		}
		if (noOccurences > frequencyMostFreqWords) {
			mostFreqWords.clear();
			mostFreqWords.add(word);
			frequencyMostFreqWords = noOccurences;
		}
	}

	@Override
	public String toString() {
		return "TreeStats [totalWordCount=" + totalWordCount + ", totalNumberOfCharacters=" + totalNumberOfCharacters
				+ ", frequencyMostFreqWords=" + frequencyMostFreqWords + ", mostFreqWords=" + mostFreqWords + "]";
	}
}
