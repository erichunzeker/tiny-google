import mmap
import re

ii = {}

f = open("data/mroutput/part-r-00000", "r")

while f:
    line = f.readline()
    if line == "":
        break
    key = line.split('\t')
    value = line.replace(key[0] + '\t', "")
    value = value.replace('{', "")
    value = value.replace('}', "")
    value = value.split(', ')

    values = []

    for val in value:
        val = val.split('=')
        if '\n' in val[1]:
            val[1] = val[1].split('\n')[0]
        values.append({"file" : val[0], "freq": val[1]})

    values = sorted(values, key=lambda k: k['freq'], reverse=True)

    ii[key[0]] = values
f.close()

while True:
    print('\nenter a term or \'q\' to quit\n')
    term = input()

    if term == 'q':
        break

    terms = term.split(" ")

    for term in terms:
        if term in ii:
            # print(ii[term])


            # f = open('docs/' + ii[term][0]['file'], 'r+b')
            for index in range(3):
                if len(ii[term]) <= index - 1:
                    break
                f = open('docs/' + ii[term][index - 1]['file'], "r")
                print('\n\n' + ii[term][index - 1]['file'] + '\n\n')
                searchlines = f.readlines()
                term = term.lower()
                # print(searchlines)
                f.close()
                for i, line in enumerate(searchlines):
                    if term in line.lower():
                        for l in searchlines[i:i+3]:
                            print('\n' + l + '\n')
                            break
                        break

            #f.close()



print('goodbye')
