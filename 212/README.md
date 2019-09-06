# [212. Word Search II](https://leetcode.com/problems/word-search-ii/description/)

> Given a 2D board and a list of words from the dictionary, find all words in the board.

> Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

> Example:
> 
```
Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
```


更好的方法是利用字典树Trie来做, 就是将要搜索的单词先添加到字典树中, 然后从地图board的每一个元素搜索, 如果往上下左右搜索的时候其元素可以在字典树中找到, 那么就继续搜索下去, 并且如果搜索到某个结点的时候发现到这个结点构成了一个单词, 那么就将单词添加到结果集合中. 如果在字典树中无法找到这个元素, 那么就结束当前分支的搜索.