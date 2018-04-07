package homework03;

import java.util.Arrays;

public class Tasks {
    public static int backpack(int[] weight, int[] cost, int maxWeight) {
        int[] bp = new int[maxWeight + 1];
        bp[0] = 0;
        int numItems = weight.length;
        int currentMaxWeight = 0;
        for (int k = 0; k < numItems; k++) {
            if (currentMaxWeight < maxWeight) {
                currentMaxWeight += weight[k];
                if (currentMaxWeight > maxWeight) {
                    currentMaxWeight = maxWeight;
                }
            }
            for (int i = currentMaxWeight; i >= weight[k]; i--) {
                if ((bp[i - weight[k]] != 0 || i == weight[k])
                        && bp[i - weight[k]] + cost[k] > bp[i]) {
                    bp[i] = bp[i - weight[k]] + cost[k];
                }
            }
        }
        return bp[maxWeight];
    }

    public static int minimalDiffDivide(int[] array) {
        int[] m = new int[array.length];
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        int maxSumOfArray = (sum + 1) / 2;
        m[0] = array[0];
        for(int i = 1; i < array.length; i++) {
            int max = array[i];
            for(int j = i - 1; j >= 0; j--) {
                if (m[j] + array[i] <= maxSumOfArray && m[j] + array[i] > max) {
                    max = array[i] + m[j];
                }
            }
            m[i] = max;
        }
        int max = m[0];
        for(int i = 1; i < m.length; i++) {
            if (m[i] > max) {
                max = m[i];
            }
        }
        return Math.abs(sum - 2 * max);
    }

    public static int minimalScalarProduct(int[] x, int[] y) {
        Arrays.sort(x);
        Arrays.sort(y);
        if (x.length > y.length) {
            int[] tempArray = x;
            x = y;
            y = tempArray;
        }
        int result = 0;
        int k = x.length - 1;
        for(int i = 0; i < x.length; i++, k--) {
            result += x[i] * y[k];
        }
        return result;
    }
}