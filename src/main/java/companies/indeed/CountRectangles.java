
/**
 * 
 * 
 *  [1,1,1,1,1],
    [1,1,0,0,1],
    [1,1,0,0,1],
    [1,1,1,1,1]
]
 * 
 * */

import java.util.*;

public class CountRectangles {

    private class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Rectangle {
        Point start, end;

        Rectangle(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "{(" + start.x + "," + start.y + "),(" + end.x + "," + end.y + ")}";
        }
    }

    public List<Rectangle> count(int[][] arr) {
        final int m = arr.length;
        final int n = arr[0].length;
 
        final List<Rectangle> rectangles = new ArrayList<>();

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (arr[i][j] == 0 && isStarting(i, j, arr)) {
                    rectangles.add(getCoordinates(i, j, arr));
                }
            }
        }

        return rectangles;
    }

    private boolean isStarting(int i, int j, int[][] arr) {
        // if top is 0 then it can't be starting
        if (i > 0 && arr[i-1][j] == 0) return false;

        // if left is zero then it can't be starting
        if (j > 0 && arr[i][j-1] == 0) return false;

        return true;
    }

    private Rectangle getCoordinates(int r, int c, int[][] arr) {

        Point start = new Point(r, c);

        int i = r, j = c;
        while (i < arr.length && arr[i][c] == 0) i++;
        while (j < arr[0].length && arr[r][j] == 0) j++;

        Point end = new Point(i-1, j-1);

        return new Rectangle(start, end);
    }

    public static void main(String... args) {

        int[][] arr =  {{0,1,1,1,1},
                        {1,1,0,0,1},
                        {0,1,0,0,1},
                        {0,1,1,1,1},
                        {1,0,1,1,1}};
        System.out.println(new CountRectangles().count(arr));
    }
}
