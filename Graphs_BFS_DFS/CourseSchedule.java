/*
Problem:
You have numCourses courses labeled 0..n-1 and a list of prerequisites
[ai, bi] meaning you must take bi before ai. Return true if you can finish
all courses (no impossible dependency cycle).

Approach:
We use Topological Sort with Kahn's algorithm (BFS). We build a graph of
course -> its dependents, and track the indegree (number of prerequisites)
of each course. We enqueue courses with indegree 0, then remove them and
lower the indegree of what they unlock. If we can remove all courses, the
graph is acyclic and we can finish.

Why this works:
A cycle would trap courses with indegree > 0 forever, so counting how many
courses we actually process tells us whether a cycle exists.

Time Complexity:
O(V + E) building the graph and processing each node/edge once.
Space Complexity:
O(V + E) for the adjacency list and indegree array.
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            adj.get(pre).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int taken = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            taken++;
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        return taken == numCourses;
    }
}
