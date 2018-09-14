import numpy as np
import math
from datetime import datetime

# Hello World program in Python

def get_last_working_day_of_this_month():
    today = datetime.now().strftime('%Y-%m')
    next_month = np.datetime64(today) + np.timedelta64(1, 'M')
    last_day = next_month - np.timedelta64(1, 'D')
    return np.busday_offset(last_day, 0, roll='backward')

temp = get_last_working_day_of_this_month()
print(temp)
