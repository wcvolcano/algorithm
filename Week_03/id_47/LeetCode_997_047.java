/**
 * https://leetcode.com/problems/find-the-town-judge/
 *
 */
class Solution {
    public int findJudge(int N, int[][] trust) {
        if (trust.length == 0 && N==1) {
            return 1;
        }
        if (trust.length == 0) {
            return -1;
        }
        Graph graph = new Graph(N);
        boolean[] hasTrsut = new boolean[N];
        for (int i = 0; i < trust.length; ++i) {
            graph.add(trust[i][0]-1, trust[i][1]-1);
            hasTrsut[trust[i][0] - 1] = true;
        }

        for (int i = 0; i < N; ++i) {
            LinkedList<Integer> beTrust = graph.getReverseAdj(i);
            if (beTrust != null && beTrust.size() == N - 1 && hasTrsut[i] == false) {
                return i+1;
            }
        }
        return -1;
    }

    private class Graph {
        private int n;
        private LinkedList<Integer>[] reverseAdj;


        public Graph(int n) {
            this.n = n;
            this.reverseAdj = new LinkedList[n];
        }

        public void add(int a, int b) {
            if (reverseAdj[b] == null) {
                reverseAdj[b] = new LinkedList<>();
            }
            reverseAdj[b].add(a);
        }

        public LinkedList<Integer> getReverseAdj(int a) {
            return reverseAdj[a];
        }


    }
}