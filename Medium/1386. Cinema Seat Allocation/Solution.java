class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col == 2 || col == 3 || col == 4 || col == 5) {
                map.put(row, map.getOrDefault(row, 0) | 1);
            }

            if (col == 4 || col == 5 || col == 6 || col == 7) {
                map.put(row, map.getOrDefault(row, 0) | 2);
            }

            if (col == 6 || col == 7 || col == 8 || col == 9) {
                map.put(row, map.getOrDefault(row, 0) | 4);
            }
        }

        int answer = (n - map.size()) * 2;

        for (int mask : map.values()) {
            if ((mask & 1) == 0 && (mask & 4) == 0) {
                answer += 2;
            } else if ((mask & 1) == 0 ||
                       (mask & 2) == 0 ||
                       (mask & 4) == 0) {
                answer++;
            }
        }

        return answer;
    }
}