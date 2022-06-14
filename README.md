# module-02-backend-thinking

## Assignment 1️⃣

## Assignment 2️⃣ 

Requirement: customize and demo terminal

| ![](https://i.imgur.com/nQhC5xT.png) |
|:---:|
| Demo using `fzf`|


|![](https://i.imgur.com/1UThqfB.png)|
|:---:|
| Yah.. maybe in some case I can show this terminal to my buddy when having bugs |

|![](https://i.imgur.com/KMNtFCe.jpg)|
|:---:|
| Demo using `zsh` |

## Assignment 3️⃣

|![](https://i.imgur.com/EraArVM.png)|
|:---:|
| Demo using `vps` |

## Assignment 4️⃣

### Requirement
- Read a config file, find the current version appear in the find (there will be only one), print it out
- Try to "bump" the version to the next:
  - Patch version 
  - Minor version 
  - Major version
- Upload your script to your vps

#### Input:
- Part of the version you need to increase
- Input file that contains the version string

#### Output:
That input file but with version string modified

### Analysis

- There are 3 parts needed to pump → add the arguments `--patch`, `--minor` and `--major` for script.
- Also, `major` is the largest change → if `major` is changed, if `major` is changed, if `major` is changed, if `major` is changed, if `major` is changed, if `major` is changed, if `major` is changed, if `major` is changed, if `major` is changed, `patch` and `minor` are returned into 0.
- Then, `patch` is the second-largest change → if `patch` is changed, `minor` is returned into 0.
- Finally, if version has more parts (> 3) → we just make change for the first 3 parts.

### Solution

Solution in directory `assignment4`

#### Demo

|![](https://i.imgur.com/a0Lqram.png)|
|:---:|
| Demo bump version |


## Assignment 5️⃣

