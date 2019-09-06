import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath","pea","eat","rain"};

        Solution solution = new Solution();
        List<String> ans = solution.findWords(board, words);
        System.out.println(ans);
    }
}

class Solution {
    boolean[][] visited;

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();

        int M = board.length;
        if (M == 0) return ans;
        int N = board[0].length;
        if (N == 0) return ans;

        visited = new boolean[M][N];

        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dfs(trie.root, i, j, board, M, N, ans);
            }
        }

        Collections.sort(ans);
        return ans;
    }

    private void dfs(TrieNode node, int i, int j, char[][] board, int M, int N, List<String> ans) {
        if (!node.word.equals("")) {
            ans.add(node.word);
            node.word = "";
        }

        if (i < 0 || i >= M || j < 0 || j >= N ||
                visited[i][j] == true ||
                node.children[board[i][j] - 'a'] == null) {
            return;
        }

        visited[i][j] = true;

        dfs(node.children[board[i][j] - 'a'], i - 1, j, board, M, N, ans);
        dfs(node.children[board[i][j] - 'a'], i + 1, j, board, M, N, ans);
        dfs(node.children[board[i][j] - 'a'], i, j - 1, board, M, N, ans);
        dfs(node.children[board[i][j] - 'a'], i, j + 1, board, M, N, ans);

        visited[i][j] = false;
    }

    class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
    }


    class TrieNode {
        String word;
        TrieNode[] children;

        TrieNode() {
            word = "";
            children = new TrieNode[26];
        }
    }

}