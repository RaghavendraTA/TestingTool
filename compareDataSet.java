import java.util.*;

public void compareDataSet(Vector<Vector<String>> leftData, Vector<Vector<String>> rightData, Long ExcelCounter)
{
    Map<String, Vector<String>> leftMap = new HashMap<>();
    Map<String, Vector<String>> rightMap = new HashMap<>();

    for(Vector<String> v: leftData) {
        leftMap.put(v.get(7), v);
    }

    for(Vector<String> v: rightData) {
        rightMap.put(v.get(7), v);
    }

    Set<String> keys = new TreeSet<>(leftData.keySet());
    keys.retainAll(rightData.keySet());

    Vector<Integer> cells = new Vector<>();

    for(String key: keys) {

        int j = 3;
        Iterator<String> lefti = leftMap.get(key).iterator();
        Iterator<String> righti = rightMap.get(key).iterator();

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
                exg.push(leftMap.get(key), ExcelCounter);
                exg.color(ExcelCounter, cells);
                ExcelCounter++;
            }
            if (additional_info == 1 || additional_info == 2) {
                exg.push(rightMap.get(key), ExcelCounter);
                exg.color(ExcelCounter, cells);
                ExcelCounter++;
            }
        } else if (discrepancy == 1) {
            if (additional_info == 0 || additional_info == 2) {
                exg.push(leftMap.get(key), ExcelCounter);
                ExcelCounter++;
            }
            if (additional_info == 1 || additional_info == 2) {
                exg.push(rightMap.get(key), ExcelCounter);
                ExcelCounter++;
            }
        }

        cells.clear();
    }

    leftMap.removeAll(keys);
    rightMap.removeAll(keys);

    if(additional_info == 0 || additional_info == 2) {
        for(String key: leftMap.keySet()) {
            for (int j = 0; j < leftMap.get(key).size(); j++) {
                cells.add(j + 3);
            }
            exg.push(leftMap.get(key), ExcelCounter);
            exg.color(ExcelCounter, cells);
            ExcelCounter++;

            cells.clear();
        }
    }

    if(additional_info == 0 || additional_info == 2) {
        for (String key: rightMap.keys()) {
            for (int j = 0; j < rightMap.get(key).size(); j++) {
                cells.add(j + 3);
            }
            exg.push(rightMap.get(key), ExcelCounter);
            exg.color(ExcelCounter, cells);
            ExcelCounter++;

            cells.clear();
        }
    }

}
