## MDE Optimiser

This is the main repository for  [MDEOptimiser](http://mde-optimiser.github.io).


## Build status

| Branch       | Status                                     |
| -------------|:-----------------------------------------:|
| Develop      | [![Dev Build](https://github.com/mde-optimiser/mde_optimiser/actions/workflows/dev_merge.yml/badge.svg)](https://github.com/mde-optimiser/mde_optimiser/actions/workflows/dev_merge.yml) |
| Release      | [![Master Build](https://github.com/mde-optimiser/mde_optimiser/actions/workflows/master_merge.yml/badge.svg)](https://github.com/mde-optimiser/mde_optimiser/actions/workflows/master_merge.yml)  |

### Installation URLs

Requirements:

	Eclipse Version 2019-03
	Java 11

Install the version of MDEOptimiser you would like to
use from one of the Eclipse Update sites below.



| Version       | Update Site                               |
| ------------- |:-----------------------------------------:|
| Develop       | [http://mde-optimiser.github.io/mdeo_repo/src/composite/develop/](http://mde-optimiser.github.io/mdeo_repo/src/composite/develop/) |
| Release       | [http://mde-optimiser.github.io/mdeo_repo/src/composite/release/](http://mde-optimiser.github.io/mdeo_repo/src/composite/release/) |

http://mde-optimiser.github.io/mde_optimiser/downloads/p2/develop/



#### Development Version:

http://mde-optimiser.github.io/mde_optimiser/downloads/p2/develop/


## Development Environment Configuration

Checkout the code and initialise the local development environment using the provided ant build script found in `build/ant/`:
	
	1. git clone
	2. cd build/ant
	3. ant

Import the MDEOptimiser library projects from `build/maven/` as an existing maven project in your IDE.

To extend the Eclipse UI interface, using Eclipse and after importing the library projects, also import `interfaces/eclipse/src/` as an existing maven project.

## Build structure

The MDEOptimiser parent pom file is located in the build/maven directory.

The Eclipse plugin parent pom file is dependent on a successfull MDEOptimiser maven build. The pom file for the Eclipse UI is located in the interfaces/eclipse/src directory.

All Maven projects are organised in a single pom file. ANT is used to build these projects first, then the Eclipse interface is built by using the published artifacts from this build.

Build order:

1. Maven projects are compiled and deployed to a maven repository. Eclipse bundles are loaded as a maven repository from the repositories/output directory
2. The maven repository is converted to a P2 repository and the output is stored in repositories/output
3. The Eclipse projects are built. Maven artifacts are loaded as Eclipse bundles from the P2 repository.