package search;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class WordLadder_127 {

    /**
     * 思路：
     *  由 wordList 中的单词（包含 beginWord）组成一个图，每个单词都是一个节点
     *  如果两个单词之间只有一个字符不一样，那么两个单词之间有一条边
     *  该题目的目的就是寻找从 beginWord 节点到 endWord 节点的最短路径
     *  可以使用 BFS 进行求解
     * @param args
     */
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        WordLadder_127 solution = new WordLadder_127();
        int ladderLength = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println("[Solution] ladder length: " + ladderLength);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 特殊情况，endWord 不再 wordList 中，直接返回 0
        boolean isEndWordExist = false;
        for (String word : wordList) {
            if (endWord.equals(word)) {
                isEndWordExist = true;
            }
        }
        if (!isEndWordExist) {
            return 0;
        }

        // BFS 寻找单词转换的最短路径
        wordList.add(beginWord);
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        int shortestPathLen = 0;

        while (!queue.isEmpty()) {
            shortestPathLen ++;
            int size = queue.size();
            for (int i=0; i<size; i++) {
                String word = queue.poll();
                if (endWord.equals(word)) {
                    return shortestPathLen;
                }
                if (visited.contains(word)) {
                    continue;
                }
                visited.add(word);
                List<String> nextWordList = getNextWordList(wordList, word);
                for (String nextWord : nextWordList) {
                    queue.add(nextWord);
                }
            }
        }
        return 0;
    }

    /**
     * 获取某个单词所连接的单词：如果两个单词之间只有一个字符不一样，那么两个单词是连接的
     * @param wordList
     * @param word
     * @return
     */
    private List<String> getNextWordList(List<String> wordList, String word) {
        List<String> nextWordList = new ArrayList<>();
        char[] wordCharArr = word.toCharArray();
        for (String pword : wordList) {
            int diffLen = 0;
            char[] pwordCharArr = pword.toCharArray();
            for (int i=0; i<pwordCharArr.length; i++) {
                if (pwordCharArr[i] != wordCharArr[i]) {
                    diffLen++;
                }
            }
            if (diffLen == 1) {
                nextWordList.add(pword);
            }
        }
        return nextWordList;
    }
}
