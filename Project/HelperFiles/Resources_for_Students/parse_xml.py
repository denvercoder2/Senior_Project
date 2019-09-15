# parse_csv usitility for java project
import xml.etree.ElementTree as etree

def get_needed_stars(xml_file: str):
    '''
    Function will read and parse an xml file
    '''
    tree = etree.parse(xml_file)
    root = tree.getroot()
    for row in root:
        for items in row:
            if items.tag == "Mag":
                if float(items.text) < 6.0 and float(items.text) > 0.0:
                    print("StarID: ", row[0].text)
                    print(items.tag, ":",  items.text)
                    print("----------------------")


def get_planets(xml_file: str):
    '''
    Function will read in the planets xml file
    '''
    tree = etree.parse(xml_file)
    root = tree.getroot()
    for row in root:
        print("----------------------")
        for items in row:
            print(items.tag, items.text)




def main():
    '''
    Main Function
    '''
    path_stars = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\stars.xml'
    path_planets = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\planets.xml'
    get_needed_stars(path_stars)
    get_planets(path_planets)


if __name__ == "__main__":
    main()