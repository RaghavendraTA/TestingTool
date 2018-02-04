import java.util.*;

public void compareDataSet(Vector<Vector<String>> leftData, Vector<Vector<String>> rightData, Long ExcelCounter)
{
    Collections.sort(leftData, new Comparator() {
        @Override
        public Boolean compare(Vector<String> A, Vector<String> B)
        {
            return A.get(7).compareTo(B.get(7))
        }
    });

    Collections.sort(rightData, new Comparator() {
        @Override
        public Boolean compare(Vector<String> A, Vector<String> B)
        {
            return A.get(7).compareTo(B.get(7))
        }
    });

    Vector<Integer> cells = new Vector<>();

    int index = 0, end = leftData.size() < rightData.size() ? leftData.size() : rightData.size();
    for (; index < end; index++) {

        int j = 3;
        Iterator<String> lefti = leftData.get(index).iterator();
        Iterator<String> righti = leftData.get(index).iterator();

        while (lefti.hasNext() && righti.hasNext()) {
            String ls = lefti.next();
            String rs = righti.next();
            if (ls.compareTo(rs) != 0) {
                cells.add(j);
            }
            j++;
        }

        while (lefti.hasNext()) {
            cells.add(j);
            lefti.next();
            j++;
        }

        while (righti.hasNext()) {
            cells.add(j);
            righti.next();
            j++;
        }

        // Add rows 0, 1, 2

        if (cells.size() > 0) {
            if (additional_info == 0 || additional_info == 2) {
                exg.push(leftData.get(index), ExcelCounter);
                exg.color(ExcelCounter, cells);
                ExcelCounter++;
            }
            if (additional_info == 1 || additional_info == 2) {
                exg.push(rightData.get(index), ExcelCounter);
                exg.color(ExcelCounter, cells);
                ExcelCounter++;
            }
        } else if (discrepancy == 1) {
            if (additional_info == 0 || additional_info == 2) {
                exg.push(leftData.get(index), ExcelCounter);
                ExcelCounter++;
            }
            if (additional_info == 1 || additional_info == 2) {
                exg.push(rightData.get(index), ExcelCounter);
                ExcelCounter++;
            }
        }

        cells.clear();
    }

    // What if additional rows still exist ?
    int leftEnd = end, rightEnd = end;

    while (leftEnd < leftData.size() && (additional_info == 0 || additional_info == 2)) {
        for (int j = 0; j < leftData.get(leftEnd).size(); j++) {
            cells.add(j + 3);
        }
        exg.push(leftData.get(leftEnd), ExcelCounter);
        exg.color(ExcelCounter, cells);
        ExcelCounter++;

        cells.clear();
        leftEnd++;
    }

    while (rightEnd < rightData.size() && (additional_info == 1 || additional_info == 2)) {
        for (int j = 0; j < rightData.get(rightEnd).size(); j++) {
            cells.add(j + 3);
        }
        exg.push(rightData.get(rightEnd), ExcelCounter);
        exg.color(ExcelCounter, cells);
        ExcelCounter++;

        cells.clear();
        rightEnd++;
    }

    cells.clear();
}
