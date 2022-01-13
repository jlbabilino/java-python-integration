def palindrome(input):
    newStr = ""
    for i in range(0, len(input)):
        newStr = input[i:i + 1] + newStr
    return newStr