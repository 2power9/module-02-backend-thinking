from sys import argv


def printUsage():
    print("Usage: python3 [--major] [--minor] [--patch] [filename]")
    exit(2)

def checkCmdLine():
    # check the command line arguments
    if len(argv) < 3: printUsage()
    for i in range(1, len(argv) - 1):
        if argv[i] not in ['--major', '--minor', '--patch']:
            printUsage()

def getVersion(line):
    posBeginVer = posEndVer = 0

    for posBeginVer in range(len(line)):
        if '0' <= line[posBeginVer] <= '9': break

    for i in range(posBeginVer + 1, len(line)):
        if not(line[i] == '.' or '0' <= line[i] <= '9'):
            posEndVer = i
            break

    version = line[posBeginVer:posEndVer]
    if len(version.split('.')) < 3: return ''
    return version

def bump(oldVersion):
    parts = list(map(int, oldVersion.split('.')))
    major, minor, patch = parts[0], parts[1], parts[2]
    for i in range(1, len(argv) - 1):
        if argv[i] == '--major':
            major += 1
            patch = minor = 0
        elif argv[i] == '--minor':
            minor += 1
            patch = 0
        elif argv[i] == '--patch': patch += 1

    newVersion = "%d.%d.%d" % (major, minor, patch)
    return newVersion

def processFile(filename):
    try:
        data = None
        # read file to find version
        with open(filename, 'r') as file:
            data = file.readlines()

            for i in range(len(data)):
                version = getVersion(data[i])
                if version != '':
                    data[i] = data[i].replace(version, bump(version))
                    break
        # write file with the new version
        with open(filename, 'w') as file:
            for line in data:
                file.writelines(line)

    except IOError:
        print("Error 404: file not found")
        return

if __name__ == '__main__':
    checkCmdLine()
    processFile(argv[-1])