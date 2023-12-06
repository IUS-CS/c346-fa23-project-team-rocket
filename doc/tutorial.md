# Tutorial

This document will walk you through installing, using, and configuring the program so that you can make use of all the functionality.

<details>
<summary>Table of Contents</summary>

* [Installation](#installation)
* [Running the Program](#running-the-program)
* [Configuring](#configuring)
  * [Flag Types](#flag-types)


</details>

## Installation

To install the program you will want to ensure you have both the following softwares installed:
* [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
* [git](https://github.com/git-guides/install-git) 


As we have not tested the software with alternatives or other versions of those softwares.
You can follow those links for further instruction related to those softwares.

To build this project from the terminal:

1. Clone the source into a directory that you wish for it to be located:

```shell
git clone https://github.com/IUS-CS/c346-fa23-project-team-rocket
```

2. Build with the gradle wrapper by running the following command within the newly made project directory:

```shell
./gradlew build
```

The resulting ```.jar``` file will be located within the ```/build/libs/``` directory within the project. With a name following the pattern ```animal-simulation-project-?.?.?.jar```

## Running the Program

To run the program either go into the project directory or, if you have moved the jar, go into the directory where you've moved it. 

You can then run the program by running the following terminal command:

```shell
java -jar ./build/libs/animal-simulation-project-?.?.?.jar
```

or, if you've moved the jar go into its directory within the terminal and run the following:

```shell
java -jar ./animal-simulation-project-?.?.?.jar
```

### Possible Errors

It's possible that your java version may cause issues or not be installed and therefore cause issues by not working with the ```java``` shell command.

You can check your java version by running the following command:

```shell
java --version
```

You can also invoke the java runner by using a path directly to it, if you need to keep a certain version of java tied to the ```java``` keyword.

## Configuring

The only current way of configuring the simulation is through terminal arguments. These arguments are passed via double quotations surrounding all of the arguments, with a double hyphen before each individual flag. Here's an example:

```shell
java -jar /path/to/animal-simulation-project-?.?.?.jar "--rabbit_count 20 --fox_count 15 --carrot_count 5 -- --grid_height 15 --grid_width 25 --days_amount 12 --steps_per_day"
```
If you have more then one version of the jar file installed, simply replace the '?' with the ones matching the version you wish to run.

We currently have 5 main different types of flags you can use to configure the simulation, although one of those types comes with 4 different subtypes.

### Flag Types

#### Animal Count Flag

The Animal count flag places a set amount of organisms around the map randomly if it can find an open spot. It works with all available organisms within the simulation.

The following is the format for the flag and should be placed between the double quotations:

```--<organism_name>_count #```

Replace the # with whatever number of that organism you want, and
you can swap out <organism_name> with any of the following organism names:
* Rabbit
* Fox
* Grass
* Carrot

#### Grid Width Flag

The grid width flag sets the width of the simulations grid.

The following is the format for the flag and should be placed between the double quotations:

```--grid_width #```

Where the # can be swapped with whatever number you want the width to be set too.
#### Grid Height Flag

The grid height flag sets the height of the simulations grid.

The following is the format for the flag and should be placed between the double quotations:

```--grid_height #```

Where the # can be swapped with whatever number you want the height to be set too.

#### Day Amount Flag

The Day amount flag sets how many 'days' the simulation will be run for, the default is 10.

The following is the format for the flag and should be placed between the double quotations:

```--days_amount #```

Where the # can be swapped with whatever number of days you want the simulation to be run for.

#### Time Steps Per Day Flag

The Time steps per day flag sets the number of timesteps that occur per day, the iterations of time used to determine if organisms should move and moves them if they can.

The following is the format for the flag and should be placed between the double quotations:

```--steps_per_day #```

Where the # can be swapped with whatever number of timesteps you want to occur per simulation day.