"""
Python Script to process a text file and generate a Excel sheet (xlsx) out of it

Per minute it can process 1.4 Lakh Records
It took 486 seconds for 10.77 Lakh Records 
"""

import xlsxwriter
import sys
from tkinter import *
from tkinter.filedialog import askopenfilename

def Write_A_WorkBook(FILEPATH, lbl):

    TO_SAVE = FILEPATH[:-4] + '.xlsx'

    fileCounter = 0
    MaxSize = 420000
    I = 0

    workbook = xlsxwriter.Workbook(TO_SAVE)
    worksheet = None
    Headers = None
    format = workbook.add_format({'bold': True, 'bg_color': 'yellow', 'border': 1})

    with open(FILEPATH, 'r') as f:

        for line in f:

            if I == 0: 
                fileCounter += 1
                worksheet = workbook.add_worksheet("QueryResult_" + str(fileCounter))
                if Headers is None:
                    Headers = line.split('|')
                for J in range(len(Headers)):
                    worksheet.write(I, J, Headers[J], format)
            else:
                strings = line.split('|')
                for J in range(len(strings)):
                    worksheet.write(I, J, strings[J])
            I = (I + 1) % MaxSize

    workbook.close()

    output = "File saved as ... " + TO_SAVE
    lbl.config(text=output)

#Invokes when file chooser selected.
def chooseFile(lbl):
    filename = askopenfilename()
    if filename[-4:] != '.txt':
        lbl.config(text="Invalid file !...")
    else:
        lbl.config(text="Processing ...")
        Write_A_WorkBook(filename, lbl)

#Tkinter window design code
root = Tk()

lbl = Label(root, text="Select a txt file...", font=("Helvetica", 15), wraplength = 700)
lbl.place(relx=0.5, rely=0.3, anchor=CENTER)


B1 = Button(root, text = "Choose file", borderwidth = 1, command = lambda: chooseFile(lbl))
B1.place(relx=0.5, rely=0.6, anchor=CENTER)

root.geometry("800x200")
root.resizable(width=False, height=False)
root.mainloop()
