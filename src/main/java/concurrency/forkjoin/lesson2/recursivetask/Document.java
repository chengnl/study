package concurrency.forkjoin.lesson2.recursivetask;

import java.util.Random;

/**
 * @author chengnl
 * @E-mail:chengnengliang@vrvmail.com.cn
 * @date 2014年12月17日 上午10:14:34
 * @version 1.0
 * @Description:模拟文档
 */
public class Document {
	private String words[] = { "the", "hello", "goodbye", "packt", "java",
			"thread", "pool", "random", "class", "main" };

	public String[][] generateDocument(int numLines, int numWords, String word) {
		int counter = 0;
		String document[][] = new String[numLines][numWords];
		Random random = new Random();
		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < numWords; j++) {
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if (document[i][j].equals(word)) {
					counter++;
				}
			}
		}
		System.out.println("DocumentMock: The word appears " + counter
				+ " times in the document");

		return document;
	}

}
