# parse_csv usitility for java project

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
    and store it into a pandas datafram
    '''
    df = pd.read_csv(filename)

    df.head()



def main():
    '''
    Main Function
    '''
    path = '/home/river/Desktop/Senior_Project/Project/HelperFiles/Resources_for_Students/hyg.csv'
    read_data(path)


if __name__ == "__main__":
    main()