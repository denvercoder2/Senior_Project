'''
Scott Holley
CSV Data Helper Script
CS499: Senior Design
'''
import os
import csv

def get_planets(filename: str) -> list:
    '''
    Function: get_planets
    Parameters: Filename (String)
    Purpose: Reads in a filename(CSV) and return each line
    as a list
    '''
    with open(filename, "r") as infile:
        for line in csv.reader(infile):
            line = infile.readlines()

    return line


def main():
    '''
    Main function setup
    '''
    # This is not dynamic yet, you'll need to change it to your location of the file
    
    os.chdir("\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources for Students\\")
    lines = get_planets("Planets.csv")  
    for line in lines:
        print(line)



if __name__ == "__main__":
    main()