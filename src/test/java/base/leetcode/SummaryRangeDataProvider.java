package base.leetcode;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class SummaryRangeDataProvider {
    public static final String EXCEL_FILE = "docs/SummaryRanges.xlsx";
    public static final DataFormatter DATE_FORMATTER = new DataFormatter();

    public static class OperationSequence {
        private List<Operation> sequence;

        public OperationSequence() {
            sequence = new LinkedList<>();
        }

        public void addSequence(Operation operation) {
            this.sequence.add(operation);
        }

        public List<Operation> getSequence() {
            return sequence;
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            for (Operation operation : sequence) {
                b.append(operation.toString());
                b.append("\n");
            }
            return b.toString();
        }
    }

    public static class Operation {
        private int numberAdded;
        private int[][] intervals;

        public Operation(int numberAdded, int[][] intervals) {
            this.numberAdded = numberAdded;
            this.intervals = intervals;
        }

        public int getNumberAdded() {
            return numberAdded;
        }

        public int[][] getIntervals() {
            return intervals;
        }

        @Override
        public String toString() {
            return numberAdded + " : " + Arrays.deepToString(intervals);
        }
    }

    public static List<OperationSequence> readFromFile() {
        try {
            FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILE));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            Map<Integer, OperationSequence> map = new HashMap<>();
            int rowNumber = 2;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Integer testCase = getInteger(row.getCell(0));
                if (testCase != null) {
                    Integer number = getInteger(row.getCell(1));
                    if (number != null) {
                        try {
                            int[][] intervals = getIntervals(row.getCell(2));
                            Operation op = new Operation(number, intervals);
                            OperationSequence seq = map.getOrDefault(testCase, new OperationSequence());
                            seq.addSequence(op);
                            map.put(testCase, seq);
                        } catch (IllegalArgumentException e) {
                            System.err.println(rowNumber + ": " + e.getMessage());
                        }
                    } else {
                        System.err.println(rowNumber + ": Invalid number added");
                    }
                } else {
                    System.err.println(rowNumber + ": Invalid test case");
                }
            }

            List<OperationSequence> operationSequences = new LinkedList<>(map.values());
            return operationSequences;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int[][] getIntervals(Cell cell) throws IllegalArgumentException {
        String s = DATE_FORMATTER.formatCellValue(cell);
        List<int[]> list = new LinkedList<>();
        char[] x = s.toCharArray();
        boolean inside = false;

        int[] arr = null;
        StringBuilder num = new StringBuilder();
        for (char c : x) {
            if (c == '[') {
                arr = new int[2];
                num = new StringBuilder();
                inside = true;
            } else if (inside == true && c == ']') {
                Integer ele = parseInteger(num.toString());
                if (ele == null) {
                    throw new IllegalArgumentException("invalid num " + num.toString());
                }
                arr[1] = ele;
                list.add(arr);
                inside = false;
            } else if (inside == true && c == ',') {
                Integer ele = parseInteger(num.toString());
                if (ele == null) {
                    throw new IllegalArgumentException("invalid num " + num.toString());
                }
                arr[0] = ele;
                num = new StringBuilder();
            } else if (c == ' ') {
                continue;
            } else if (inside = true) {
                num.append(c);
            }
        }
        int[][] res = new int[list.size()][2];
        res = list.toArray(new int[1][2]);
        return res;
    }

    private static Integer parseInteger(String s) {
        try {
            Integer i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private static String getStringValue(Cell cell) {
        String s = DATE_FORMATTER.formatCellValue(cell);
        return s == null ? "" : s;
    }

    private static Integer getInteger(Cell cell) {
        String s = DATE_FORMATTER.formatCellValue(cell);
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            return null;
        }
    }


    @DataProvider(name="input")
    public Object[][] getProvider() {
        List<OperationSequence> sequences = SummaryRangeDataProvider.readFromFile();
        if (sequences == null) {
            return new Object[0][0];
        }
        Object[][] result = new Object[sequences.size()][1];
        int index = 0;
        for (OperationSequence sequence : sequences) {
            System.out.println(sequence);
            result[index][0] = sequence;
            index++;
        }
        return result;
    }

}
