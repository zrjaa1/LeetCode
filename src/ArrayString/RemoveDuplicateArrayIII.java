package ArrayString;

/*
Leetcode上没找到，重复留0个, [2,2,3,3,4,1,1,1] -> [4]
 */

/*
1. sol1: Hashmap 2 pass O(n)
2. sol2: Stack + flag, 重复设为 true，发现与栈顶不同的时候 check flag，如果 true，删去后再 push，并设 flag 为 false。注意 post-processing，还需检查一次 flag
3. sol3: slow fast，同样是设 flag，与第二个类似
 */
public class RemoveDuplicateArrayIII {
}
