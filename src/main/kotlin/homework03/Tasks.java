package homework03;

import java.util.Collections;
import java.util.List;

public class Tasks {
    public static int backpack(List<Integer> weight, List<Integer> cost, int maxWeight) {
        int[] bp = new int[maxWeight + 1];
        bp[0] = 0;
        int numItems = weight.size();
        int currentMaxWeight = 0;
        for (int k = 0; k < numItems; k++) {
            int curWeight = weight.get(k);
            int curCost = cost.get(k);
            if (currentMaxWeight < maxWeight) {
                currentMaxWeight += curWeight;
                if (currentMaxWeight > maxWeight) {
                    currentMaxWeight = maxWeight;
                }
            }
            for (int i = currentMaxWeight; i >= curWeight; i--) {
                if ((bp[i - curWeight] != 0 || i == curWeight)
                        && bp[i - curWeight] + curCost > bp[i]) {
                    bp[i] = bp[i - curWeight] + curCost;
                }
            }
        }
        return bp[maxWeight];
    }

    public static int minimalDiffDivide(List<Integer> array) {
        int[] m = new int[array.size()];
        int sum = 0;
        for(Integer a : array) {
            sum += a;
        }
        int maxSumOfArray = (sum + 1) / 2;
        m[0] = array.get(0);
        for(int i = 1; i < array.size(); i++) {
            int max = array.get(i);
            for(int j = i - 1; j >= 0; j--) {
                int currentEl = array.get(i);
                if (m[j] + currentEl <= maxSumOfArray && m[j] + currentEl > max) {
                    max = currentEl + m[j];
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

    public static int minimalScalarProduct(List<Integer> x, List<Integer> y) {
        Collections.sort(x);
        Collections.sort(y);
        if (x.size() > y.size()) {
            List<Integer> tempArray = x;
            x = y;
            y = tempArray;
        }
        int result = 0;
        int k = x.size() - 1;
        for(int i = 0; i < x.size(); i++, k--) {
            result += x.get(i) * y.get(k);
        }
        return result;
    }
}

