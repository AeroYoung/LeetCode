# [LeetCode](https://leetcode.com/problemset/algorithms/?cong=true "LeetCode")
## Java语言
* Main.java         : 主函数
* Solution.java     : 解决方法

## 目录

* [双指针算法](https://github.com/AeroYoung/LeetCode/blob/master/%E5%8F%8C%E6%8C%87%E9%92%88.md)——No15,No16,No18.
* [No.11Container With Most Water](https://github.com/AeroYoung/LeetCode/issues/1)
* [No.14 Longest Common Prefix](https://github.com/AeroYoung/LeetCode/issues/2)

## 未归类的问题

### [No65. Valid Number](https://leetcode.com/problems/valid-number/)
>Validate if a given string is numeric.
>
>Some examples:
>
>"0" => true
>
>" 0.1 " => true
>
>"abc" => false
>
>"1 a" => false
>
>"2e10" => true
>
>**Note: **It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. 

先看看[java正则表达式](http://www.runoob.com/java/java-regular-expressions.html)

We start with trimming.

If we see [0-9] we reset the number flags.

We can only see . if we didn't see e or ..

We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.

We can only see + and - in the beginning and after an e.

any other character break the validation.

At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

So basically the number should match this regular expression:

[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?