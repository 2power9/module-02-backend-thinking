from sys import argv
from re import compile


def printUsage():
    print("Usage: python3 [--major] [--minor] [--patch] [filename]")
    exit(2)


def checkCommandLine():
    # check the command line arguments
    if len(argv) < 3: printUsage()
    for i in range(1, len(argv) - 1):
        if argv[i] not in ['--major', '--minor', '--patch']:
            printUsage()


def isValidVersion(version):
    return len(list(version.split('.'))) >= 3


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
        elif argv[i] == '--patch':
            patch += 1

    newVersion = "%d.%d.%d" % (major, minor, patch)
    return newVersion


def bumpVersionInFile(filename):
    try:
        # read file to find version
        with open(filename, 'r') as file:
            text = ''.join(file.readlines())

        # find valid version
        version = ""
        regex = compile(r'([\d][.\d]+)')
        try: version = regex.search(text).group()
        except AttributeError or not isValidVersion(version):
            print("This file does not have version.")
            return

        # bump version in file
        text = text.replace(version, bump(version))

        # write file with the new version
        with open(filename, 'w') as file:
            file.write(text)

    except IOError:
        print("Error 404: file not found")
        return


if __name__ == '__main__':
    checkCommandLine()
    bumpVersionInFile(argv[-1])
