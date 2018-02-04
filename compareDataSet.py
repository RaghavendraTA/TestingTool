import operator


class ExcelGenerator:

    def push(self, alist, counter):
        print(alist, end=' -> ')

    def color(self, counter, cells):
        print(cells)


def compareDataSet(leftData, rightData, ExcelCounter):

    leftData.sort(key=operator.itemgetter(1))
    rightData.sort(key=operator.itemgetter(1))

    leftMap = {}
    rightMap = {}

    for item in leftData:
        leftMap[item[1]] = item
    for item in rightData:
        rightMap[item[1]] = item

    cells = list()

    # Remove
    additional_info = 2
    exg = ExcelGenerator()

    keys = set(leftMap.keys())
    keys = keys.intersection(rightMap.keys())

    for key in keys:

        lefti = list(leftMap[key])
        righti = list(rightMap[key])

        j = 0

        while len(lefti) > 0 and len(righti) > 0:
            ls = lefti.pop(0)
            rs = righti.pop(0)
            if (ls != rs):
                cells.append(j)
            j += 1

        cells += [i for i in range(j, j + len(lefti))]
        cells += [i for i in range(j, j + len(righti))]

        del lefti
        del righti

        if len(cells) > 0:
            if additional_info == 0 or additional_info == 2:
                exg.push(leftMap[key], ExcelCounter)
                exg.color(ExcelCounter, cells)
                ExcelCounter += 1
            if additional_info == 1 or additional_info == 2:
                exg.push(rightMap[key], ExcelCounter)
                exg.color(ExcelCounter, cells)
                ExcelCounter += 1
        elif False:
            if additional_info == 0 or additional_info == 2:
                exg.push(leftMap[key], ExcelCounter)
                ExcelCounter += 1
            if additional_info == 1 or additional_info == 2:
                exg.push(rightMap[key], ExcelCounter)
                ExcelCounter += 1

        del leftMap[key]
        del rightMap[key]

        cells.clear()

    if additional_info == 0 or additional_info == 2:
        for key in leftMap.keys():
            cells += [j + 3 for j in range(len(leftMap[key]))]
            exg.push(leftMap[key], ExcelCounter)
            exg.color(ExcelCounter, cells)
            ExcelCounter += 1
            cells.clear()

    if additional_info == 1 or additional_info == 2:
        for key in rightMap.keys():
            cells += [j + 3 for j in range(len(rightMap[key]))]
            exg.push(rightMap[key], ExcelCounter)
            exg.color(ExcelCounter, cells)
            ExcelCounter += 1
            cells.clear()


leftData = [
    ["ABC", 213, 431],
    ["ABC", 132, 435],
    ["ABC", 123, 432],
    ["RAJI", 333, 999]
]
rightData = [
    ["ABC", 132, 437],
    ["PQR", 123, 432],
    ["XYZ", 213, 438],
    ["RAGHU", 111, 999]
]

compareDataSet(leftData, rightData, 0)
