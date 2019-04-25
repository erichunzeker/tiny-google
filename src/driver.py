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
        values.append({val[0]: val[1]})

    ii[key[0]] = values
f.close()

while True:
    print('enter a term')
    term = input()
    if term in ii:
        print(ii[term])

    if term == 'q':
        break
print('goodbye')
