# parse_csv usitility for java project
import xml.etree.ElementTree as etree


class read_XML:

    def get_needed_stars(self, xml_file: str):
        '''
        Function will read and parse an xml file
        '''

        # Given that these are now accessable,
        # these items can be used as objects if needed
        tree = etree.parse(xml_file)
        root = tree.getroot()
        for row in root:
            for items in row:
                if items.tag == "Mag":
                    if float(items.text) < 6.0 and float(items.text) > 0.0:
                        print(row)
                        print("StarID: ", row[0].text)
                        print(items.tag, ":",  items.text)
                        print("----------------------")




    def getRowID(self, xml_file: str) -> list:
        '''
        Function will return a list of appropriate row ids
        '''
        tree = etree.parse(xml_file)
        root = tree.getroot()
        rowID_list = []
        for row in root:
            for items in row:
                if items.tag == "Mag":
                    if float(items.text) < 6.0 and float(items.text) > 0.0:
                        rowID_list.append(row)
                        print(rowID_list)

        return rowID_list


    def get_planets(self, xml_file: str) -> dict:
        '''
        Function will read in the planets xml file
        '''
        # Given that these are now accessable,
        # these items can be used as objects if needed
        tree = etree.parse(xml_file)
        root = tree.getroot()
        planets_dict = {}
        for row in root:
            print("-----------------")
            for items in row:
               print(items.tag, items.text)
                

        return planets_dict



def main():
    '''
    Main Function
    '''
    # create istance of class
    rxml = read_XML()
    # path to both xml files
    path_stars = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\stars.xml'
    path_planets = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\planets.xml'
    # use the parents version of method to execute
    rxml.get_needed_stars(path_stars)
    # planets = rxml.get_planets(path_planets)
    row_list = rxml.getRowID(path_stars)
    # stripped_list = rxml.stripData(row_list)
    




if __name__ == "__main__":
    main()