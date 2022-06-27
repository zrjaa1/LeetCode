package Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给一个安卓app的不同version的安装包，每个安装包有不同的安卓系统要求(只考虑major version)。example
 * v1: min 1.0 max 6.0
 * v2: min 3.0 max 7.0
 * 把安卓系统从 0.0 -> latest(面试官说是MAX_INTEGER)每个区间支持的不同安卓安装包找到，然后只返回所有区间
 * [0.0, 1.0)   [1.0, 3.0)  [3.0, 7.0)  [7.0, 8.0)  [8.0, latest) -> 然后这些interval作为result
 * none,        v1,         v1 + v2,    v2,         none          -> 不需要返回
 *
 * Source: https://www.1point3acres.com/bbs/thread-873959-1-1.html
 */

/**
 * 思路：先把input的[min, max] 转化为[min, max + 1), 这样可以避免很多edge case，然后再放入minHeap中，只要不添加[7, 7)这样的区间即可
 * 这道题也可以直接放入[min, max]，但很多edge case需要考虑，就不往下继续想了
 */
public class AndroidVersionMerge {
    static class App {
        int minVer;
        int maxVer;
        public App(int minVer, int maxVer) {
            this.minVer = minVer;
            this.maxVer = maxVer;
        }
    }

    static List<App> getDifferentVersionInterval(List<App> apps) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(0);
        heap.offer(Integer.MAX_VALUE);

        for (int i = 0; i < apps.size(); i++) {
            heap.offer(apps.get(i).minVer);
            heap.offer(apps.get(i).maxVer + 1);
        }

        List<App> res = new ArrayList<>();
        int start = -1;
        while (!heap.isEmpty()) {
            int end = heap.poll();
            while (!heap.isEmpty() && end == heap.peek()) {
                heap.poll();
            }
            if (start != -1 && start != end) {
                res.add(new App(start, end - 1));
            }
            start = end;
        }
        return res;
    }

    public static void main(String[] args) {
        List<App> input = new ArrayList<>();
        input.add(new App(1, 6));
        input.add(new App(3, 7));
        input.add(new App(3, 7));
        input.add(new App(7, 10));
        getDifferentVersionInterval(input);
    }
}
