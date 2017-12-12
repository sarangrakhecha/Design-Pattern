package wordCount.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wordCount.util.FileMode;

/**
 * <p>
 * This class contains logic for file read and write operations using NIO
 * library. It uses MappedByteBuffer to read and write chunks of data at a time.
 * </p>
 * 
 * @author Anand kulkarni
 *
 */
public class FileProcessorNIO implements InputReaderI, OutputWriterI {

	/**
	 * <p>
	 * This variable stores the RandomAccessFile instance.
	 * </p>
	 */
	private RandomAccessFile randomAccessFile;

	/**
	 * <p>
	 * This variable stores the file channel instance.
	 * </p>
	 */
	private FileChannel fChannel;

	/**
	 * <p>
	 * This variable acts as a buffer.
	 * </p>
	 */
	private ByteBuffer buffer;

	/**
	 * <p>
	 * This variable stores the size of data to be fetched in one IO operation.
	 * </p>
	 */
	private int BUFFER_SIZE = 10 * 1024;

	/**
	 * <p>
	 * This variable stores the current position of file pointer.
	 * </p>
	 */
	private int position;

	/**
	 * <p>
	 * This variable is used to store the continuation word between two adjacent
	 * buffers.
	 * </p>
	 */
	private StringBuilder continuationWord;

	/**
	 * <p>
	 * This variables stores the characters used to represent the line
	 * separator.
	 * </p>
	 */
	private static List<Character> LINE_SEPARATOR = Arrays.asList('\n', '\r');

	public FileProcessorNIO(String fileNameIn, FileMode fileMode) {
		try {
			randomAccessFile = new RandomAccessFile(fileNameIn, fileMode.getMode());
			fChannel = randomAccessFile.getChannel();
			buffer = MappedByteBuffer.allocate(BUFFER_SIZE);
			continuationWord = new StringBuilder();
			position = 0;
		} catch (IOException exception) {
			System.err.println("Error occured while creating a file object.");
			exception.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * <p>
	 * This method checks if the input character is a part of character sequence
	 * used to represent the line separator by the underlying operating system.
	 * </p>
	 * 
	 * @param ch
	 *            Character to verify.
	 * @return true if a character is a part of character sequence used as a
	 *         line separator, false otherwise.
	 */
	private boolean isLineSeparator(char ch) {
		return LINE_SEPARATOR.contains(ch);
	}

	/**
	 * <p>
	 * This method contains the logic to read contents of the file. This methods
	 * reads the file in chunks using MappedByteBuffer, process the contents to
	 * to return a list of words. It returns null if EOF is reached.
	 * </p>
	 */
	@Override
	public List<String> read() {
		try {
			if (position >= fChannel.size()) {
				return null;
			}
			char ch = 0;
			boolean isWordInProgress = false;
			List<String> words = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			if (position + BUFFER_SIZE < fChannel.size()) {
				fChannel.read(buffer);
				position += BUFFER_SIZE;
			} else {
				Long lastBlockSize = fChannel.size() - position;
				buffer = MappedByteBuffer.allocate(lastBlockSize.intValue());
				fChannel.read(buffer);
				position += lastBlockSize;
			}
			for (int i = 0; i < buffer.capacity(); i++) {
				ch = (char) buffer.get(i);
				if ((null != sb && sb.equals("uses"))
						|| (null != continuationWord && continuationWord.equals("uses"))) {
					System.out.println(true);
				}
				if (ch == ' ' || isLineSeparator(ch) || ch == '\t') {
					if (null != continuationWord) {
						words.add(continuationWord.toString());
						continuationWord = null;
						isWordInProgress = false;
					}
					if (isWordInProgress) {
						words.add(sb.toString());
						sb = new StringBuilder();
						isWordInProgress = false;
					}
				} else {
					if (!isWordInProgress) {
						isWordInProgress = true;
					}
					if (null == continuationWord) {
						sb.append(ch);
					} else {
						sb = continuationWord;
						sb.append(ch);
						continuationWord = null;
					}
				}
			}
			if (isWordInProgress) {
				continuationWord = new StringBuilder();
				continuationWord = sb;
			}
			if (position == fChannel.size() && (null != continuationWord)) {
				words.add(continuationWord.toString());
			}
			buffer.clear();
			return words;
		} catch (IOException exception) {
			System.err.println("Error occured while reading a file.");
			exception.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	/**
	 * <p>
	 * This method contains logic to write the content to the file.
	 * </p>
	 */
	@Override
	public void write(String content) {
		try {
			int size = content.getBytes().length;
			randomAccessFile.seek(randomAccessFile.length());
			buffer = fChannel.map(FileChannel.MapMode.READ_WRITE, fChannel.position(), size);
			buffer.put(content.getBytes());
		} catch (IOException exception) {
			System.err.println("Error occured while writing to a file.");
			exception.printStackTrace();
			System.exit(1);

		}
	}

	/**
	 * <p>
	 * This method contains logic to close FileChannel and RandomAccessFile
	 * resource.
	 * </p>
	 */
	@Override
	public void close() {
		try {
			randomAccessFile.close();
		} catch (IOException exception) {
			System.err.println("Error occured while closing a file.");
			exception.printStackTrace();
			System.exit(1);
		}
	}
}