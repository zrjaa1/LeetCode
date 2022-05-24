package String;

import java.util.ArrayList;
import java.util.List;

/**
 * 71. Simplify Path: https://leetcode.com/problems/simplify-path/
 * Medium
 *
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */

/**
 * The key to solve this problem is to use String.split(') to help us differenciate: "./" "//", "../" and other folder/file name
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }

        if (path.charAt(0) != '/') {
            throw new IllegalArgumentException("Not a valid absolute path");
        }

        String[] components = path.split("/");
        StringBuilder sb = new StringBuilder("/");
        List<Integer> slashIdx = new ArrayList<>(); // to store index of '/' in StringBuilder, used for case "../"
        slashIdx.add(0);
        for (String component : components) {
            if (component.isEmpty() || component.equals(".")) { // case "./" or "//"
                continue;
            } else if (component.equals("..")) { // case "../"
                int lastIdx = 0;
                if (slashIdx.size() >= 2) {
                    lastIdx = slashIdx.get(slashIdx.size() - 2);
                    slashIdx.remove(slashIdx.size() - 1);
                }
                sb.setLength(lastIdx + 1);
            } else {
                sb.append(component);
                sb.append("/");
                slashIdx.add(sb.length() - 1);
            }
        }

        while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '/') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath tester = new SimplifyPath();
        String path = "/home//jay/../";
        String path2 = "/../";
        String path3 = "/a/./b/../../c/";
        String path4 = "/a//b////c/d//././/..";
        String path5 = "/...";
        String path6 = "/..hidden"; // note, all these inputs are valid absolute path
        String path7 = "/.hidden";
        String path8 = "/home/.";
        String path9 = "/a/../../b/../c//.//";
        String res = tester.simplifyPath(path9);
        System.out.println(res);
    }
}
