# runEEmon

*Gotta run 'em all!*

runEEmon is a simple Java command line tool that assists in downloading, installing, deploying, and running the following [Jakarta EE](https://jakarta.ee) and [MicroProfile](https://microprofile.io) runtimes (aka application servers):

 * [Wildfly](https://www.wildfly.org/)
 * [Payara](https://www.payara.fish/)
 * [GlassFish](https://glassfish.org/)
 * [TomEE](https://tomee.apache.org/)
 * [OpenLiberty](https://openliberty.io/)
 
According to the [Jakarta EE Survey 2020/2021](https://arjan-tijms.omnifaces.org/2021/02/jakarta-ee-survey-20202021-results.html), these are the most widely used, freely available application servers.

Additional runtimes can be configured via Java properties files in the *config* subdirectory.

## Getting started

To run the examples below on your machine, take the following preparations first:

```
git clone https://github.com/enji7/runeemon
cd runeemon
mvn compile
```

[Ping](https://github.com/enji7/ping) is a simple Jakarta EE REST application that can be used for deployment tests.

## Usage examples

If you are using Windows, replace `runeemon.sh` with `runeemon.bat` in the commands below.

Download, extract and run WildFly (and also deploy a WAR that you may have placed into runEEmon's *autodeploy* directory):

`./runeemon.sh start wildfly`

Download and extract Wildfly and Payara:

`./runeemon.sh hatch wildfly payara`

Download and extract *all* supported runtimes:

`./runeemon.sh hatch all`

## Usage

```
./runeemon.sh <command> <comma-separated runtime names, or 'all'>

Commands
--------
list               lists the names of all configured runtimes
fetch              downloads the given runtimes into the 'nest' folder (if not already done)
hatch              downloads and extracts the given runtimes into the 'zoo' folder (if not already done)
deploy             deploys the WAR from the autodeploy directory to the given runtimes (fetch and hatch included)
start-bg           starts the given runtime in the background (fetch, hatch and deploy included)
start-fg           starts the given runtime in the foreground (fetch, hatch and deploy included)
start              starts the given runtime in the background or foreground (fetch, hatch and deploy included)
stop               stops the given runtime after it has been started in the background
clean-nest         removes the downloads and extractions for the given runtimes
clean-zoo          removes the uncompressed files for the given runtimes
clean-deployments  removes the deployments (WARs) for the given runtimes
info               prints available information for the given runtimes
```

## Command Line Completion

for Linux shell: auto-completion for Runeemon command line parameters (by pressing 'tab')

`source ./completion.sh`

add to ~/.bashrc for a single user, or to /etc/bash_completion/ for all users

## Jakarta EE & MicroProfile Frameworks

 *   - Quarkus
 *   - Helidon
 *   - Micronaut
 *   - KumuluzEE

## Other

see also: [Jakarta EE application server cheat sheet](https://rieckpil.de/cheatsheet-java-jakarta-ee-application-servers/)

## TODO

 * add Glassfish
 * make it run under Windows as well
 * finish documentation
 * version update
 * publish
 * parallel versions (test & document)
 * make the shell script startable from anywhere
 