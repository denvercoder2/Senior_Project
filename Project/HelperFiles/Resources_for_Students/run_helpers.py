# Build tool for python
# Scott Holley
'''
Program designed to compile all helper scripts together to run at once
via thread manipulation. Can be added onto for every file we make
'''

import subprocess
import platform


# menu
user_choice = int(input("What would you like to run?\n1) ParsePlanets\n2) ParseStar\n\n\nChoice?: "))
# different checks because javac is different between os (windows requires a space)
windows_comp = 'javac '
windows_build = 'java '
linux_comp = 'javac'
linux_build = 'java'


if platform.system() == 'Linux':
    print("Compiled on Linux . . .")
    if user_choice == 1:
        print("Java Parse for Planets started . . .")
        # create the planet objects
        subprocess.call([linux_comp, 'ParseCreatePlanet.java'])
        subprocess.call([linux_build, 'ParseCreatePlanet'])
    elif user_choice == 2:
        print("Java Parse for Stars started . . .")
        subprocess.call([linux_comp, 'ParseCreateStars.java'])
        subprocess.call([linux_build, 'ParseCreateStars'])

if platform.system() == 'Windows':
    print("Compiled on Windows . . .")
    if user_choice == 1:
        print("Java Parse for Planets started . . .")
        # create the planet objects
        subprocess.call([windows_comp, 'ParseCreatePlanet.java'])
        subprocess.call([windows_build, 'ParseCreatePlanet'])
    elif user_choice == 2:
        print("Java Parse for Stars started . . .")
        subprocess.call([windows_comp, 'ParseCreateStars.java'])
        subprocess.call([windows_build, 'ParseCreateStars'])