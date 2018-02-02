import os
import functools
import xlrd

def get_dic_dir(rootdir):
    dir = {}
    rootdir = rootdir.rstrip(os.sep)
    start = rootdir.rfind(os.sep) + 1
    for path, dirs, files in os.walk(rootdir):
        folders = path[start:].split(os.sep)
        subdir = dict.fromkeys(files)
        parent = functools.reduce(dict.get, folders[:-1], dir)
        parent[folders[-1]] = subdir
    return dir

currentPath = None

def traverse(dirs, pathList, fe):
    global currentPath
    for key in dirs:
        if dirs[key] != None:
            traverse(dirs[key], pathList + [key], fe)
        elif key == fe:
            pathList += [key]
            currentPath = "/".join(pathList)
            return currentPath
    return currentPath

def findInput(alist):
    temp = ""
    for line in alist:
        if "input" in line.lstrip("--").strip().lower():
            temp += line + "\n"
    writeToExcel(fe, alist)

dirs = get_dic_dir("./")

workbook = xlrd.open_workbook("xyz.xlsx", 'r')
worksheet = workbook.sheet_by_index(0)

for i in range(worksheet.nrows):
    val = worksheet.cell(i, 5).value
    fe = traverse(dirs, [], val)
    with open(fe, 'r') as f:
        findInput(fe, f.readlines)
        
