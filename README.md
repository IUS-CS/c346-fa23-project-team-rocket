[![License](https://badgen.net/badge/License/MIT/blue)](LICENSE)

# Life Simulator

Life Simulator is an interactive ecosystem simulation of the complex relationships between organisms in various 
ecosystems, including reproduction, death, hunger, and dynamic weather patterns. The ecosystem simulation is an 
innovative, immersive, and hands-on experience in which users can customize parameters like population sizes, species 
interactions, and weather patterns to simulate specific ecosystems and study the effects of different variables. The aim 
of Life Simulator is to bring the wonders of the natural world to users' fingertips and contribute to a greater 
understanding of our interconnected ecosystems. It's currently on version **0.6.0**.

<details>
<summary>Table of Contents</summary>

* [Installation](#installation)
  * [Dependencies](#dependencies)
  * [Building](#building)
* [Usage](#usage)
  * [Running](#running)
  * [Terminal Flags](#terminal-flags)
* [Testing](#testing)
* [Program Structure](#program-structure)
* [License](#license)

</details>

## Installation

### Dependencies

Make sure you have installed the dependencies:
* `java` 17
* `git`

### Building

To build this project:

1. Clone the source:

```sh
git clone https://github.com/IUS-CS/c346-fa23-project-team-rocket
```

2. Build with Gradle:

```sh
./gradlew build
```

## Usage

### Running

After building the program, run the following command to run it:

```sh
java -jar build/libs/animal-simulation-project-0.6.0.jar
```

### Terminal Flags

The flags won't be noticed if more than one argument is passed. As such the quotation marks are very important and allow 
the program to identify the flags properly. Terminal Flags are run in this program by string. As such they should be 
attached to the end of the run command like so:

> java -jar <path-to-jar> "terminal-flags-go-in-here"

Multiple flags can be used at the same time. However, per handler they are checked from left to right. As such if there 
are two grid_width flags, then only the rightmost one will matter. The following is an example of what using multiple 
flags might look like:

> java -jar <path-to-jar> "--Grass_count 10 --Rabbit_count 10 --grid_width 10 --grid_height 20"

We have 5 different terminal flags currently:

* --<organism-name>_count #

Example of Usage: --Rabbit_count 33
This flag spawns an initial amount of rabbits in the simulation, the value is capped to 9999999. Negative values don't 
work. It also only works with currently implemented animals, those being:

* Rabbit
* Fox


* --grid_width #

Example of Usage: --grid_width 53
This flag sets the initial grid width for the simulation, the value is capped to 9999999. Negative values don't work.

* --grid_height #
 
Example of Usage: --grid_height 63
This flag sets the initial grid height for the simulation, the value is capped to 9999999. Negative values don't work.

* --days_amount #

Example of Usage: --days_amount 4
This flag sets how many days the simulation will last, the value is capped to 9999999. Negative values don't work.

* --steps_per_day #

Example of Usage: --steps_per_day 10
This flag sets how many time steps will occur per simulation day, the value is capped to 9999999. Negative values don't 
work.

## Testing

The programs tests can be run by using the following command:

```sh
./gradlew cucumber
```

## Tutorial

For a more extensive walkthrough on how to use the program see [tutorial.md](doc/tutorial.md).

## Program Structure

For information about the program structure see [architecture.md](doc/architecture.md).

## License

This project is distributed under the terms of the MIT license. See [LICENSE](LICENSE) for details.
