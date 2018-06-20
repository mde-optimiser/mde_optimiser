# MDEOptimiser standalone build project

This project produces a standalone jar of MDEOptimiser that can be used to run
experiments using headless setups.

The jar requires that the mopt file, the input models, the metamodel and any
specified search operators are available in the basepath location.

The fitness function implementations must be added to the classpath of the JVM 
that runs the standalone jar.

