from sys import argv
from re import compile
from enum import IntEnum


class VERSION(IntEnum):
    MAJOR = 0
    MINOR = 1
    PATCH = 2
    NUM_PARAMS = 3


SPLIT_POINT = '.'


def printUsage():
    print("Usage: python3 [--major] [--minor] [--patch] [filename]")
    exit(2)


def checkCommandLine():
    # check the command line arguments
    needBump = [False for _ in range(VERSION.NUM_PARAMS)]
    arguments = {'--major': 0, '--minor': 1, '--patch': 2}
    if len(argv) < 3: printUsage()
    for i in range(1, len(argv) - 1):
        if argv[i] not in arguments:
            printUsage()
        needBump[arguments[argv[i]]] = True
    filename = argv[-1]
    return filename, needBump


def isValidVersion(version):
    return len(list(version.split(SPLIT_POINT))) >= VERSION.NUM_PARAMS


def bump(oldVersion, needBump):
    major, minor, patch = map(int, oldVersion.split(SPLIT_POINT))

    if needBump[VERSION.MAJOR]:
        major += 1
        patch = minor = 0

    if needBump[VERSION.MINOR]:
        minor += 1
        patch = 0

    if needBump[VERSION.PATCH]:
        patch += 1

    newVersion = ("%d" + SPLIT_POINT + "%d" + SPLIT_POINT + "%d") % (major, minor, patch)
    return newVersion


def findVersion(text):
    # find valid version in text
    regex = compile(r'([\d][.\d]+)')
    try:
        version = regex.search(text).group()
        if isValidVersion(version):
            return version
    except AttributeError:
        print('Can not find version!')

    return None


def bumpVersionInFile(filename, needBump):
    try:
        # read file to find version
        with open(filename, 'r') as file:
            text = ''.join(file.readlines())

        # bump version in file
        try:
            version = findVersion(text)
            text = text.replace(version, bump(version, needBump))
        except AttributeError:
            print("There is no version to replace")
            return

        # write file with the new version
        with open(filename, 'w') as file:
            file.write(text)

    except IOError:
        print("Error: file not found")
        return


if __name__ == '__main__':
    filename, needBump = checkCommandLine()
    bumpVersionInFile(filename, needBump)
