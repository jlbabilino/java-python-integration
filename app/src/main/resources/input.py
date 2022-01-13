from util import palindrome

val = input()
while (val != None):
    print(palindrome.palindrome(val))
    try:
        val = input()
    except:
        break