class Solution {

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        Map<Integer, List<Integer>> map = new HashMap();

        List<Integer> res = new ArrayList();

        //fill map
        for (int cr[] : connections) {
            int u = cr[0], v = cr[1];
            map.computeIfAbsent(u, k -> new ArrayList()).add(v);
            map.computeIfAbsent(v, k -> new ArrayList()).add(u);
        }

        Map<Integer, Integer> visited = new HashMap();
        Map<Integer, Set<Integer>> powerGrids = new HashMap();
        for (int i = 1; i <= c; i++) {
            Set<Integer> temp = new TreeSet();
            if (!visited.containsKey(i)) {
                dfs(i, temp, map, visited, i);
            }
            powerGrids.put(i, temp);
        }

        int[] online = new int[c + 1];
        Arrays.fill(online, 1);

        for (int q[] : queries) {
            int qtype = q[0];
            int node = q[1];

            if (qtype == 2) {
                online[node] = 0;
                powerGrids.get(visited.get(node)).remove(node);
                continue;
            }

            if (online[node] == 1) {
                res.add(node);
                continue;
            }

            int temp = -1;
            Set<Integer> s = powerGrids.get(visited.get(node));
            for (int k : s) {
                if (online[k] == 1) {
                    temp = k;
                    break;
                }
            }
            res.add(temp);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public void dfs(int node, Set<Integer> current, Map<Integer, List<Integer>> map, Map<Integer, Integer> visited, int ind) {
        current.add(node);
        visited.put(node, ind);

        for (int v : map.getOrDefault(node, new ArrayList<>())) {
            if (!visited.containsKey(v)) dfs(v, current, map, visited, ind);
        }
    }
}
