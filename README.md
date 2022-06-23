# module-02-backend-thinking

## Assignment 1️⃣

### Know you environment

- What is backend programming?

  Backend developers build code that allows a database and an application to communicate with one another. Backend developers take care and maintain the back-end of a website, Including databases, servers, and apps, and they control what you don't see.

- What are the differences between backend vs frontend programming?

  **Front and back end developers work on different sides of a website**

  Front end development is programming which focuses on the visual elements of a website or app that a user will interact with (the client side). Meanwhile, back end development focuses on the side of a website users can’t see (the server side).

- What do you need to be a backend engineer?

  Though requirements vary between jobs, back end developers will need to have a passing familiarity with, if not command of, several technical languages and programs. These essential back end developer skills include but are not limited to: Python, Java, SQL, NoSQL, and Git.

- Can we deliver our products without a backend?

  I believe that we can do this work without a backend, but the product is very simple (a static blog, for example).

- What is backendless?

  "Backendless is a software as a service and software application development company. The Backendless platform is a mobile and web app development platform that can be used to build mobile apps, web apps, websites, and automations. Backendless Consulting will build your app using our proprietary software and can bring your application to life up to 9 times faster than traditional development, saving you significant time and money. With the Backendless software, you can built faster, launch sooner, iterate easier, and save up to 90% compared to traditional development services."


- Linux vs Unix? Is Linux a Unix?
  - Linux: is a family of open-source Unix-like operating systems based on the Linux kernel, an operating system kernel first released on September 17, 1991, by Linus Torvalds Linux is typically packaged in a Linux distribution.
  - Unix:  is a family of multitasking, multiuser computer operating systems that derive from the original AT&T Unix. Unix was originally meant to be a convenient platform for programmers developing software to be run on it and on other systems, rather than for non-programmers. The system grew larger as the operating system started spreading in academic circles, and as users added their own tools to the system and shared them with colleagues.
  - Linux is not Unix, but it is a Unix-like operating system. Linux system is derived from Unix and it is a continuation of the basis of Unix design. Linux distributions are the most famous and healthiest example of the direct Unix derivatives. BSD (Berkley Software Distribution) is also an example of a Unix derivative.

- Is Ubuntu a Linux? How about CentOS? What are Linux distros?
   - Ubuntu: Ubuntu is a Linux distribution based on Debian and composed mostly of free and open-source software. Ubuntu is officially released in three editions: Desktop, Server, and Core for Internet of things devices and robots. All the editions can run on the computer alone, or in a virtual machine → Ubuntu is not Linux.
   - CentOS is a Linux distribution that provides a free and open-source community-supported computing platform, functionally compatible with its upstream source, Red Hat Enterprise Linux.
   - A Linux distribution (often abbreviated as distro) is an operating system made from a software collection that includes the Linux kernel and, often, a package management system. Linux users usually obtain their operating system by downloading one of the Linux distributions, which are available for a wide variety of systems ranging from embedded devices (for example, OpenWrt) and personal computers (for example, Linux Mint) to powerful supercomputers (for example, Rocks Cluster Distribution).

- Is macOS a Linux distro?

  Mac OS is based on a BSD code base, while Linux is an independent development of a unix-like system. This means that these systems are similar, but not binary compatible. Furthermore, Mac OS has lots of applications that are not open source and are build on libraries that are not open source, therefore, MacOS is not Unix.

- What is Linux file system? Everything is a file? What is file descriptor?
  - Linux file system is generally a built-in layer of a Linux operating system used to handle the data management of the storage. It helps to arrange the file on the disk storage. It manages the file name, file size, creation date, and much more information about a file.
  - The "Everything is a file" phrase defines the architecture of the operating system. It means that everything in the system from processes, files, directories, sockets, pipes, ... is represented by a file descriptor abstracted over the virtual filesystem layer in the kernel.
  - File descriptor: In Unix and Unix-like computer operating systems, a file descriptor is a unique identifier for a file or other input/output resource, such as a pipe or network socket. File descriptors typically have non-negative integer values, with negative values being reserved to indicate "no value" or error conditions.


- How does Linux file permission work?
Blocking vs Non-blocking I/O?
Process vs Thread?

Multi-threading
Parallel vs Concurrent vs Asynchronous

Race condition, deadlock
Context switching
Mutex vs Semaphore




Memory layout: stack, heap
The Shell

Bash vs Zsh vs Terminal vs Sh vs...
Login shell vs Interactive shell
Variables

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
- Also, `major` is the largest change → if `major` is changed, `patch` and `minor` are returned into 0.
- Then, `patch` is the second-largest change → if `patch` is changed, `minor` is returned into 0.
- Finally, if version has more parts (> 3) → we just make change for the first 3 parts.

### Solution

Solution in directory `assignment4`

#### Demo

|![](https://i.imgur.com/a0Lqram.png)|
|:---:|
| Demo bump version |


## Assignment 5️⃣

### Requirement

Write a backend application for its elevator system.

### Analysis

Firstly, I split the requirement into 2 part: read requests from user and process elvelator move correctly.

  - To read requests from users: 
    
    I used Spring (basic) to create an API in local.
    
    Then, I packed the source code into `.jar` file and serve to Docker. 
    
    After that, I used VPS to create a virtual environment and pull that `.jar` file here.
    
    I used a load balancer to direct the public IP address into my server.

  - To process elevator:
    
    I used a Thread to make sure we can hear all requests.
    
    This algorithm is work for one cabin. 
    
    **Main idea:** elevator will serve the nearest passengers on the way up/down.
 

### Solution

Solution in directory `assignment5`

Documentation for API [here](https://documenter.getpostman.com/view/21496069/UzBjt8MN)

#### Demo

|![](https://i.imgur.com/EYBBH4P.png)|
|:---:|
| Demo elevator system |

##### Example query link 

<http://172.25.148.140/elevator?departure=8&destination=2>

Node: before test this link, please ping me to turn on my server. (I have to turn off to save my money :((( )


---

###### References

<https://www.simplilearn.com/tutorials/programming-tutorial/what-is-backend-development#:~:text=Backend%20developers%20build%20code%20that,what%20you%20don't%20see.>

<https://kenzie.snhu.edu/blog/front-end-vs-back-end-whats-the-difference/#:~:text=Front%20and%20back%20end%20developers,see%20(the%20server%20side).>

<https://bootcamp.cvn.columbia.edu/blog/back-end-developer-skills/#:~:text=Though%20requirements%20vary%20between%20jobs,SQL%2C%20NoSQL%2C%20and%20Git.>

<https://www.google.com/search?q=What+is+backendless%3F&oq=What+is+backendless%3F&aqs=chrome..69i57j0i546.971j0j4&sourceid=chrome&ie=UTF-8>

<https://www.softwaretestinghelp.com/unix-vs-linux/#:~:text=Linux%20is%20not%20Unix%2C%20but,example%20of%20a%20Unix%20derivative.>

<https://en.wikipedia.org/wiki/Linux>

<https://en.wikipedia.org/wiki/Ubuntu>

<https://en.wikipedia.org/wiki/CentOS>

<https://en.wikipedia.org/wiki/Linux_distribution>

<https://askubuntu.com/questions/11392/what-are-the-key-differences-between-mac-os-and-linux-that-prevent-application-c#:~:text=3%20Answers&text=Mac%20OS%20is%20based%20on,that%20are%20not%20open%20source.>

<https://unix.stackexchange.com/questions/225537/everything-is-a-file#:~:text=The%20%22Everything%20is%20a%20file%22%20phrase%20defines%20the%20architecture%20of,filesystem%20layer%20in%20the%20kernel.>

<https://www.javatpoint.com/linux-file-system#:~:text=What%20is%20the%20Linux%20File,more%20information%20about%20a%20file.>

<https://en.wikipedia.org/wiki/File_descriptor>

<https://www.howtogeek.com/67987/htg-explains-how-do-linux-file-permissions-work/>