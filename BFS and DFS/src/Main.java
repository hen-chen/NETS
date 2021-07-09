import java.util.*;

public class Main {
    public static void main(String[] args) {
        Parse p = new Parse("./data");
        List<String[]> table = p.getTable();
        
        List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        int size = getNumNodes(table);
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> l = new ArrayList<Integer>();
            adjList.add(l);
        }
        for (String[] s : table) {
            Integer u = Integer.parseInt(s[0]);
            Integer v = Integer.parseInt(s[1]);
            ArrayList<Integer> curr = adjList.get(u.intValue());
            curr.add(v);
            adjList.set(u.intValue(), curr);
            
            ArrayList<Integer> currV = adjList.get(v.intValue());
            currV.add(u);
            adjList.set(v.intValue(), currV);
        }
        // Running BFS
        List<List<Integer>> traversal = breadthFirstSearch(adjList, new Integer(0), new Integer(0), 
                new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, false, false, false);
        
        // Running DFS
        List<Integer> traversalDepth = depthFirstSearch(adjList, new boolean[adjList.size()], 
                new Integer(0), new ArrayList<Integer>());

        // Running BFS for questions
        List<List<Integer>> resultA = breadthFirstSearch(adjList, new Integer(10), 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                true, false, false, false);
        List<List<Integer>> resultB = breadthFirstSearch(adjList, new Integer(10), 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, true, false, false);
        
        Integer first = (int) (Math.random() * 4039);
        Integer second = (int) (Math.random() * 4039);
        Integer third = (int) (Math.random() * 4039);
        Integer fourth = (int) (Math.random() * 4039);
        
        List<List<Integer>> resultC1 = breadthFirstSearch(adjList, first, 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, false, true, false);
        List<List<Integer>> resultC2 = breadthFirstSearch(adjList, second, 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, false, true, false);
        List<List<Integer>> resultC3 = breadthFirstSearch(adjList, third, 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, false, true, false);
        List<List<Integer>> resultC4 = breadthFirstSearch(adjList, fourth, 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, false, true, false);
        
        List<List<Integer>> resultD = breadthFirstSearch(adjList, new Integer(1985), 
                new Integer(1050), new boolean[adjList.size()], new ArrayList<List<Integer>>(), 
                false, false, false, true);
        
    }  
    /**
     * BFS
     * @param adjList
     * @param u - starting node
     * @param v - ending node
     * @param visited - array of nodes visited
     * @param traversed - list of nodes traversed
     * @param a - part a
     * @param b - part b
     * @param c - part c
     * @param d - part d
     * @return list of connected components.
     */
    static List<List<Integer>> breadthFirstSearch(List<ArrayList<Integer>> adjList, 
            Integer u, Integer v, boolean[] visited, List<List<Integer>> traversed, 
            boolean a, boolean b, boolean c, boolean d) {
        Deque<Integer> queue = new ArrayDeque<Integer>();
        List<Integer> connectedComp = new ArrayList<Integer>();
        
        int distance = -1;
        int counterNodes = 0;
        int numNodesInLevel;
        
        queue.add(u);
        visited[u.intValue()] = true;
        while (!queue.isEmpty()) {
            if (distance == 4 && d) {
                System.out.println("Part D:" + counterNodes + " Nodes");
                return null;
            }
            numNodesInLevel = queue.size();
            distance++;
            while (numNodesInLevel > 0) {
                Integer i = queue.removeFirst();
                connectedComp.add(i);
                counterNodes++;
                if (i.equals(v) && a) {
                    System.out.println("Part A:" + distance);
                    return null;
                }
                List<Integer> neighbors = adjList.get(i.intValue());
                for (Integer n : neighbors) {
                    if (!visited[n.intValue()]) {
                        visited[n.intValue()] = true;
                        queue.add(n);
                    }
                }
                numNodesInLevel--;
            }
        }
        if (counterNodes != adjList.size() && b) {
            System.out.println("Part B:Graph is not connected");
            return null;
        } else if (b) {
            System.out.println("Part B:Graph is connected");
            return null;
        }
        if (counterNodes != adjList.size() && c) {
            System.out.println("Part C:-1");
            return null;
        } else if (c) {
            System.out.println("Part C:" + distance);
            return null;
        }
        if (d) {
            System.out.println("Part D:" + counterNodes + " Nodes");
            return null;
        }
        if (a) {
            System.out.println("Part A:No path exists");
            return null;
        }
        traversed.add(connectedComp);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                traversed = breadthFirstSearch(adjList, new Integer(i), new Integer(0), visited, 
                        traversed, false, false, false, false);
                break;
            }
        }
        return traversed;
    }
    /**
     * DFS
     * @param adjList
     * @param visited - boolean array of visited nodes
     * @param src - source node
     * @param traversed - traversed list of nodes
     * @return a List of nodes in traversed order
     */
    static List<Integer> depthFirstSearch(List<ArrayList<Integer>> adjList, 
            boolean[] visited, Integer src, List<Integer> traversed) {
        List<Integer> neighbors = adjList.get(src.intValue());
        visited[src.intValue()] = true;
        traversed.add(src);
        
        for (Integer n : neighbors) {
            if (!visited[n.intValue()]) {
                visited[n.intValue()] = true;
                traversed = depthFirstSearch(adjList, visited, n, traversed);
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                traversed = depthFirstSearch(adjList, visited, new Integer(i), traversed);
                break;
            }
        }
        return traversed;
        
    }
    static int getNumNodes(List<String[]> table) {
        int max = 0;
        for (String[] s : table) {
            Integer u = Integer.parseInt(s[0]);
            Integer v = Integer.parseInt(s[1]);
            if (u.intValue() > max) {
                max = u.intValue();
            } 
            if (v.intValue() > max) {
                max = v.intValue();
            }
        }
        return max + 1; // assuming labels (0 to n - 1)
    }
}
