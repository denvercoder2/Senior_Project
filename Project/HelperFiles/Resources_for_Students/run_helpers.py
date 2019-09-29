# Build tool for python
# Scott Holley
'''
Program designed to compile all helper scripts together to run at once
via thread manipulation. Can be added onto for every file we make
'''

import subprocess


# menu
user_choice = int(input("What would you like to run?\n1) ParsePlanets\n2) ParseStar\n"))
if user_choice == 1:
    print("Java Parse for Planets started . . .")
    # create the planet objects
    subprocess.call(['javac ', 'ParseCreatePlanet.java'])
    subprocess.call(['java ', 'ParseCreatePlanet'])
elif user_choice == 2:
    print("Java Parse for Stars started . . .")
    subprocess.call(['javac ', 'ParseCreateStars.java'])
    subprocess.call(['java ', 'ParseCreateStars'])
