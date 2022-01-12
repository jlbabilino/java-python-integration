import sys

if (len(sys.argv) >= 2):
    arg = sys.argv[1]
    newStr = ""
    for i in range(0, len(arg)):
        newStr = arg[i:i + 1] + newStr
    print(newStr)