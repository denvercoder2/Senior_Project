# parse_csv usitility for java project

import csv
import pandas as pd

'''
Scott Holley
File purpose: Read in and parse csv (Stars)
to make a new version of only data we need
so later on, java file can access each as an object
'''

def read_data(filename: str):
    '''
    Function will read in a csv 
    and store it into a pandas dataframe
    '''
    df = pd.read_csv(filename)

    return df


def main():
    '''
    Main Function
    '''
    path = '/home/river/Desktop/Senior_Project/Project/HelperFiles/Resources_for_Students/hyg.csv'
    output = "Needed_Mag.csv"
    dataFrame = read_data(path) 
    # here we can select any header type and pull it
    mag_list = dataFrame['Mag'].values
    adj_mag_list = []

    for element in mag_list:
        if element < 6.0 and element > 0.0:
            adj_mag_list.append(element)
    
    writeCSV(output, adj_mag_list)

    



if __name__ == "__main__":
    main()
