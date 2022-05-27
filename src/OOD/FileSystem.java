package OOD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 588. Design In-Memory File System: https://leetcode.com/problems/design-in-memory-file-system/
 * Hard
 *
 * Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *
 * FileSystem() Initializes the object of the system.
 * List<String> ls(String path)
 * If path is a file path, returns a list that only contains this file's name.
 * If path is a directory path, returns the list of file and directory names in this directory.
 * The answer should in lexicographic order.
 * void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
 * void addContentToFile(String filePath, String content)
 * If filePath does not exist, creates that file containing given content.
 * If filePath already exists, appends the given content to original content.
 * String readContentFromFile(String filePath) Returns the content in the file at filePath.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 * Output
 * [null, [], null, null, ["a"], "hello"]
 *
 * Explanation
 * FileSystem fileSystem = new FileSystem();
 * fileSystem.ls("/");                         // return []
 * fileSystem.mkdir("/a/b/c");
 * fileSystem.addContentToFile("/a/b/c/d", "hello");
 * fileSystem.ls("/");                         // return ["a"]
 * fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 *
 *
 * Constraints:
 *
 * 1 <= path.length, filePath.length <= 100
 * path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
 * You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
 * You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * 1 <= content.length <= 50
 * At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */

/**
 * 存储directory和file的数据结构最好用Map而不是List，这样可以O(1)检测
 */
public class FileSystem {
    class Directory {
        String name;
        Map<String, Directory> directories; // key is the name
        Map<String, String> files;
        public Directory(String name) {
            this.name = name;
            this.directories = new HashMap<>();
            this.files = new HashMap<>();
        }
    }

    Directory root;

    public FileSystem() {
        root = new Directory("/");
    }

    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        Directory dict = cd(path);
        if (dict == null) { // it might be a filePath
            Directory dict2 = cd(getDirectoryName(path));
            if (dict2 != null && dict2.files.containsKey(getFileName(path))) {
                res.add(getFileName(path));
            }
            return res;
        }
        for (String dictName : dict.directories.keySet()) {
            res.add(dictName);
        }
        for (String fileName : dict.files.keySet()) {
            res.add(fileName);
        }
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        if (cd(path) != null) { // directory already exists
            return;
        }

        Directory cur = root;
        if (!path.equals("/")) {
            String[] directories = path.split("/"); // "/a/b/c" -> [, a, b, c] the first is empty string
            for (int i = 1; i < directories.length; i++) {
                if (!cur.directories.containsKey(directories[i])) {
                    cur.directories.put(directories[i], new Directory(directories[i]));
                }
                cur = cur.directories.get(directories[i]);
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        Directory dict = cd(getDirectoryName(filePath));
        String fileName = getFileName(filePath);
        String currentContent = dict.files.get(fileName);
        if (currentContent == null) {
            currentContent = "";
        }
        dict.files.put(fileName, currentContent + content);
    }

    public String readContentFromFile(String filePath) {
        Directory dict = cd(getDirectoryName(filePath));
        return dict.files.get(getFileName(filePath));
    }

    // assume the path destination is always a directory
    private Directory cd(String path) {
        Directory cur = root;
        if (!path.equals("/")) {
            String[] directories = path.split("/"); // "/a/b/c" -> [, a, b, c] the first is empty string
            for (int i = 1; i < directories.length; i++) {
                cur = cur.directories.get(directories[i]);
                if (cur == null) break;
            }
        }
        return cur;
    }

    private String getDirectoryName(String filePath) {
        int i = filePath.length() - 1;
        while (i >= 0) {
            if (filePath.charAt(i) == '/') break;
            i--;
        }
        return filePath.substring(0, i);
    }

    private String getFileName(String filePath) {
        int i = filePath.length() - 1;
        while (i >= 0) {
            if (filePath.charAt(i) == '/') break;
            i--;
        }
        return filePath.substring(i + 1);
    }
}
