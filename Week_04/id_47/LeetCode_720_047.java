/**
 * 720. Longest Word in Dictionary
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built
 * one character at a time by other words in words. If there is more than one possible answer, return the longest
 * word with the smallest lexicographical order.
 *
 * If there is no answer, return the empty string.
 *
 * 解题思路: 先构建trie树，然后进行对比
 */
class Solution {
    private class TireNode {
        char c;
        private Map<Character, TireNode> next;
        boolean isWord;
        private String prefix;

        public TireNode(char c) {
            this.c = c;
        }
    }

    public String longestWord(String[] words) {
        TireNode root = buildTire(words);
        Queue<TireNode> queue = new LinkedList<>();
        queue.add(root);
        String res = "";
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                TireNode node = queue.poll();
                String tmp = node.prefix;
                if (tmp.compareTo(res) <= 0 || tmp.length() > res.length()) {
                    res = node.prefix;
                }
                if (node.next != null) {
                    for (TireNode child : node.next.values()) {
                        if (child.isWord) {
                            queue.add(child);
                        }
                    }
                }
            }

        }
        return res;
    }

    private TireNode buildTire(String[] words) {
        TireNode root = new TireNode('/');
        root.prefix = "";
        for (String w : words) {
            addWord(root, w);
        }
        return root;
    }

    private void addWord(TireNode node, String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        for (char c : word.toCharArray()) {
            if (node.next == null) {
                node.next = new HashMap<>();
            }
            if (!node.next.containsKey(c)) {
                TireNode n = new TireNode(c);
                n.prefix = node.prefix + c;
                node.next.put(c, n);
            }
            node = node.next.get(c);
        }
        node.isWord = true;

    }
}