# parse_csv usitility for java project
import xml.etree.ElementTree as etree

def read_xml(xml_file: str):
    '''
    Function will read and parse an xml file
    '''
    tree = etree.parse(xml_file)
    root = tree.getroot()
    for row in root:
        for item in row:
            if item.tag == "Mag":
                if float(item.text) < 6.0 and float(item.text) > 0.0:
                    print(item.tag, ":",  item.text)

    # print(StarID[0])
    # here you can grab them all dependent on for loop

    # pull if within magnitude rating
    # for rate in Magnitude:
    #     typed_rate = float(rate.text)
    #     if typed_rate < 6.0 and typed_rate > 0.0:
    #         print(typed_rate)


def main():
    '''
    Main Function
    '''
    path = '\\Users\River\Desktop\Senior_Project\Project\HelperFiles\Resources_for_Students\stars.xml'
    read_xml(path)


if __name__ == "__main__":
    main()