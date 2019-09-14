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

def make_list(dataframe) -> list:
    '''
    Function will take dataframe and convert to list
    '''
    new_list = []
    for item in dataframe:
        new_list.append(item)
    

    return new_list


def main():
    '''
    Main Function
    '''
    # sawp between os here: Add a conditional in the future if needed
    windows = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\hyg.csv'
    linux = '/home/river/Desktop/Senior_Project/Project/HelperFiles/Resources_for_Students/hyg.csv'
    output = "Needed_Mag.csv"
    dataFrame = read_data(windows) 
    # here we can select any header type and pull it
    mag_d_list = make_list(dataFrame['Mag'].values)
    starId_List = (dataFrame['StarID'].values)
    li = make_list(starId_List)
    with open ("Stars_Required.csv", "w") as outfile:
        writer = csv.writer(outfile)
        for item in mag_d_list:
            if item < 6.0 and item > 0.0:
                find_star = mag_d_list.index(item)
                adj_list = []
                adj_list.append(item)
                writer.writerow(adj_list)


if __name__ == "__main__":
    
    main()
