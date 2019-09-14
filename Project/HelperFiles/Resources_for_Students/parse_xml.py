# parse_csv usitility for java project
import xml.etree.ElementTree as etree

def read_xml(xml_file: str):
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
                    print("----------------")


def main():
    '''
    Main Function
    '''
    path = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\stars.xml'
    read_xml(path)


if __name__ == "__main__":
    main()