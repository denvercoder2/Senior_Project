'''
Moon algorithm in python
'''
import subprocess
def moon_phase(month, day, year):
    '''
    Moon phase 
    '''
    ages = [18, 0, 11, 22, 3, 14, 25, 6, 17, 28, 9, 20, 1, 12, 23, 4, 15, 26, 7]
    offsets = [-1, 1, 0, 1, 2, 3, 4, 5, 7, 7, 9, 9]
    description = ["New (totally dark)",
      "Waxing Crescent (increasing to full)",
      "In its First Quarter (increasing to full)",
      "Waxing Gibbous (increasing to full)",
      "Full (full light)",
      "Waning Gibbous (decreasing from full)",
      "In its Last Quarter (decreasing from full)",
      "Waning Crescent (decreasing from full)"]

    months = [" Jan ", " Feb ", " Mar ", " Apr ", " May ", " Jun ",
              " Jul ", " Aug ", " Sep ", " Oct ", " Nov ", " Dec "]
    if day == 31:
        day = 1

    days_into_phase = ((ages[(year + 1) % 19] +
                        ((day + offsets[month-1]) % 30) +
                        (year > 1900)) % 30)
    print("Days into phase: ", days_into_phase)
    print(13 * 16/59)

    index = int((days_into_phase + 2) * 16/59.0)
    # print(index)  # test
    if index > 7:
        index = 7

    status = description[index]
    # light should be 100% 15 days into phase
    light = int(2 * days_into_phase * 100/29)
    if light > 100:
        light = abs(light - 200)
    date = "%d%s%d" % (day, months[month-1], year)
    return date, status, light

def main():
    '''
    Main function
    '''
    month = 10
    day = 16
    year = 1996  # use yyyy format
    date, status, light = moon_phase(month, day, year)
    p = subprocess.Popen(["java", "MoonPhase"], stdin=subprocess.PIPE)
    p.stdin.write("First line\r\n")
    p.stdin.write("Second line\r\n")
    
    print("Moon Phase on given date: ", status)
    


if __name__ == "__main__":
    main()