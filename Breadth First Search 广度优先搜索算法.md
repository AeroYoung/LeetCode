# Description
广度优先搜索算法(Breadth First Search)与树的层序遍历（level-order traversal）类似，基本思想是思想是：

1. 从图中某顶点v出发，访问v之后，并将其访问标志置为已被访问，即visited[i]=1； 
2. 依次访问v的各个未曾访问过的邻接点； 
3. 分别从这些邻接点出发依次访问它们的邻接点，并使得“先被访问的顶点的邻接点先于后被访问的顶点的邻接点被访问，直至图中所有已被访问的顶点的邻接点都被访问到； 
4. 如果此时图中尚有顶点未被访问，则需要另选一个未曾被访问过的顶点作为新的起始点，重复上述过程，直至图中所有顶点都被访问到为止。

# LeetCode

### [No.199 Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

这是一个典型的BFS问题，流程如下：

1. 将顶点元素加入toVisit集合中.
2. 判断toVisit是否为空，否则3，是则6.
3. 遍历toVisit当前元素并移除之。**若该元素是最后一个元素则加入结果集合(这个本题核心结果输出所特有)**
4. 将当前元素和邻接元素加入toVisit。**本题特有顺序**
5. 转入2.
6. 返回结果

```java

public List<Integer> rightSideView(TreeNode root) {
	List<Integer> result = new ArrayList<Integer>();
    if(root==null) return result;
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.add(root);
    while(q.size()>0){
        int size = q.size();
        for(int i=0;i<size;i++){
            TreeNode node= q.poll();
            if(i==size-1)
                result.add(node.val);
            if(node.left!=null) q.add(node.left);
            if(node.right!=null) q.add(node.right);
        }
    }
    return result;
}

```

### [No.127 Word Ladder](https://leetcode.com/problems/word-ladder/)
> Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the word list
>
>For example,
>
>Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
>
>As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
**Note:**
    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.

这是一个双头广度优先搜索

```java

public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
	Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
	HashSet<String> visited = new HashSet<String>();
	int len = 1;		
	
	beginSet.add(beginWord);
	endSet.add(endWord);
	while (!beginSet.isEmpty() && !endSet.isEmpty()) {
		if (beginSet.size() > endSet.size()) {
			Set<String> set = beginSet;
			beginSet = endSet;
			endSet = set;
		}

		Set<String> temp = new HashSet<String>();
		for (String word : beginSet) {
			char[] chs = word.toCharArray();

			for (int i = 0; i < chs.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char old = chs[i];
					chs[i] = c;
					String target = String.valueOf(chs);

					if (endSet.contains(target)) {
						return len + 1;
					}

					if (!visited.contains(target) && wordList.contains(target)) {
						temp.add(target);
						visited.add(target);
					}
					chs[i] = old;
				}
			}
		}

		beginSet = temp;
		len++;
	}
	
	return 0;
}

```


