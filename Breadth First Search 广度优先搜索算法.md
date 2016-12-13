# Description
广度优先搜索算法(Breadth First Search)与树的层序遍历（level-order traversal）类似，基本思想是思想是：

1. 从图中某顶点v出发，访问v之后，并将其访问标志置为已被访问，即visited[i]=1； 
2. 依次访问v的各个未曾访问过的邻接点； 
3. 分别从这些邻接点出发依次访问它们的邻接点，并使得“先被访问的顶点的邻接点先于后被访问的顶点的邻接点被访问，直至图中所有已被访问的顶点的邻接点都被访问到； 
4. 如果此时图中尚有顶点未被访问，则需要另选一个未曾被访问过的顶点作为新的起始点，重复上述过程，直至图中所有顶点都被访问到为止。

# LeetCode

### [No.127 Word Ladder](https://leetcode.com/problems/word-ladder/)
