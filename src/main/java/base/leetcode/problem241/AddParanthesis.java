package base.leetcode.problem241;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddParanthesis {
    private List<Integer> operands;
    private List<Character> operators;

    private class Container {
        private List<Integer> list;

        public Container(List<Integer> list) {
            this.list = list;
        }
    }
    private Container[][] dp;

    public List<Integer> diffWaysToCompute(String expression) {
        initialize(expression);
        dp = new Container[operands.size()][operands.size()];
        for (int i = 0; i < operands.size(); i++) {
            dp[i][i] = new Container(Arrays.asList(operands.get(i)));
        }
        for (int i = 0; i < operands.size() - 1; i++) {
            int el1 = operands.get(i);
            int el2 = operands.get(i+1);
            char op = operators.get(i);
            int result = performOperation(el1, el2, op);
            dp[i][i+1] = new Container(Arrays.asList(result));
        }
        for (int inc = 2; inc < operands.size(); inc++) {
            for (int i = 0; i < operands.size() - inc; i++) {
                List<Integer> results = new LinkedList<>();
                for (int j = 0; j < inc; j++) {
                    List<Integer> leftList = dp[i][i+j].list;
                    List<Integer> rightList = dp[i+j+1][i+inc].list;
                    for (Integer left : leftList) {
                        for (Integer right : rightList) {
                            Integer sum = performOperation(left, right, operators.get(i+j));
                            results.add(sum);
                        }
                    }
                }
                dp[i][i+inc] = new Container(results);
            }
        }
        return dp[0][operands.size()-1].list;
    }

    private Integer performOperation(Integer el1, Integer el2, char op) {
        if (op == '+') {
            return el1 + el2;
        } else if (op == '-') {
            return el1 - el2;
        } else if (op == '/') {
            return el1 / el2;
        } else if (op == '*') {
            return el1 * el2;
        }
        return el1;
    }

    private void initialize(String expression) {
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        char[] ary = expression.toCharArray();
        StringBuilder b = new StringBuilder();
        for (char c : ary) {
            if (c == '+' || c == '*' || c == '/' || c == '-') {
                operands.add(Integer.parseInt(b.toString()));
                operators.add(c);
                b = new StringBuilder();
            } else {
                b.append(c);
            }
        }
        operands.add(Integer.parseInt(b.toString()));
    }

    public static void main(String[] args) {
        AddParanthesis ap = new AddParanthesis();
        List<Integer> results = ap.diffWaysToCompute("2*3-4*5+6+71-4*3+2");
        System.out.println(results);
    }

}
