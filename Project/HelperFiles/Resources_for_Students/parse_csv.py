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

def write_CSV(adj_mag_list: list):
    '''
    Function will take a list and map a function to each element
    in said list to write to csv
    '''
    headers = []
    with open("adjusted_magnitude.csv", "w") as outfile:
        writer = csv.writer(outfile)
        writer.writerow(headers)
        # send items to a list of lists to write
        # each number per row
        writer.writerows(map(lambda x: [x], adj_mag_list))


def main():
    '''
    Main Function
    '''
    path = '/home/river/Desktop/Senior_Project/Project/HelperFiles/Resources_for_Students/hyg.csv'
    output = "Needed_Mag.csv"
    dataFrame = read_data(path) 
    # here we can select any header type and pull it
    mag_list = dataFrame['Mag'].values
    starId_List = dataFrame['StarID'].values
    adj_star_list = []
    adj_mag_list = []


    for magnitude in mag_list:
        if magnitude < 6.0 and magnitude > 0.0:
            adj_mag_list.append(magnitude)
            connected_id = adj_mag_list.index(magnitude)

    # sorted_mag = sorted(adj_mag_list)
    # for starID in starId_List:

    
    write_CSV(adj_mag_list)

    



if __name__ == "__main__":
    main()
