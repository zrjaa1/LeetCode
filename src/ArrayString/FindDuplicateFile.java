package ArrayString;
import java.util.*;

/*
609. Find Duplicate File in System
Medium

209

345

Favorite

Share
Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least two files that have exactly the same content.

A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

"directory_path/file_name.txt"

Example 1:

Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]


Note:

No order is required for the final output.
You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
The number of files given is in the range of [1,20000].
You may assume no files or directories share the same name in the same directory.
You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.
 */

/*
又是字符串处理。。。这个还算简单
 */

public class FindDuplicateFile {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        if (paths == null || paths.length == 0) return res;
        // key - content value - path
        Map<String, List<String>> map = new HashMap<>();
        for (String str : paths) {
            int cur = 0;
            StringBuilder sb = new StringBuilder();
            while (cur < str.length() && str.charAt(cur) != ' ') {
                sb.append(str.charAt(cur));
                cur++;
            }
            cur++;
            String dict = sb.toString();
            sb.setLength(0);
            sb.append('/');
            String file = "";
            while (cur < str.length()) {
                char c = str.charAt(cur);
                if (c == '(') {
                    file = sb.toString();
                    sb.setLength(0);
                } else if (c == ')') {
                    String content = sb.toString();
                    sb.setLength(0);
                    sb.append('/');
                    List<String> path;
                    if (!map.containsKey(content)) path = new ArrayList<>();
                    else path = map.get(content);
                    path.add(dict+file);
                    map.put(content, path);
                } else if (c != ' ') sb.append(c);
                cur++;
            }
        }
        for (String str : map.keySet()) {
            List<String> sol = map.get(str);
            if (sol.size() > 1) res.add(map.get(str));
        }
        return res;
    }
}
