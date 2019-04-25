ii = {}

f = open("part-r-00000", "r")

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
    print('enter a term or \'q\' to quit')
    term = input()
    if term == 'q':
        break

    if term in ii:
        print(ii[term])


print('goodbye')
