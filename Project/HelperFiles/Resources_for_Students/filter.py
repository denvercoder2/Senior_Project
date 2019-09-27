# python code to filter the xml file

'''
Code will read xml, and delete stars that don't fall within the
parameters we want
'''


import xml.etree.ElementTree as etree

def read_delete_XML(xml_file: str):
    '''
    Function will read xml and delete children
    of root that do not fall within given rules
    '''
    # Given that these are now accessable,
        # these items can be used as objects if needed
    tree = etree.parse(xml_file)
    root = tree.getroot()
    for row in root:
        for items in row:
            if items.tag == "Mag":
                if float(items.text) < 6.0 and float(items.text) > 0.0:
                    print("StarID: ", row[0].text)
                    print(items.tag, ":",  items.text)
                    print("----------------------")
                else:
                    print("no")



def main():
    '''
    Main function
    '''
    read_delete_XML('stars.xml')


if __name__ == "__main__":
    main()