# runEEmon

runEEmon is a simple Java command line tool that assists in downloading, installing, deploying, and running the following [Jakarta EE](https://jakarta.ee) and [MicroProfile](https://microprofile.io) runtimes (aka application servers):

 * [Wildfly](https://www.wildfly.org/)
 * [Payara](https://www.payara.fish/)
 * [TomEE](https://tomee.apache.org/)
 * [OpenLiberty](https://openliberty.io/)
 
According to the [OmniFaces Jakarta EE Survey 2020/2021](https://arjan-tijms.omnifaces.org/2021/02/jakarta-ee-survey-20202021-results.html), these are among the most widely used, freely available application servers.

Additional runtimes can be configured via Java property files in the */config/runtimes/* subdirectory. In particular, [GlassFish](https://glassfish.org/) will be added as soon as it runs on JDK 11 (in version 6.1.0).

See also [runEEmon (Cargo Edition)], an alternative version of runEEmon that is powered by Codehaus Cargo.

*Gotta run 'em all!*

## Getting started

runEEmon has been developed on Ubuntu Linux, and also tested (to some extent) on Windows 10.

To run the examples below on your machine, take the following preparations first:

```
git clone https://github.com/enji7/runeemon
cd runeemon
mvn compile
chmod u+x runeemon
```

[Ping](https://github.com/enji7/ping) is a simple Jakarta EE REST application that can be used for deployment tests.

## Usage examples

For Windows, replace `runeemon` with `runeemon.bat` in the commands below.

Download, extract and run WildFly (and also deploy a WAR that you may have placed into runEEmon's *autodeploy* directory):

 * `./runeemon start wildfly`

Download and extract Wildfly and Payara:

 * `./runeemon hatch wildfly payara`

Download and extract *all* supported runtimes:

 * `./runeemon hatch all`

Print info for TomEE and OpenLiberty:

 * `./runeemon info tomee openliberty`

## Usage

```
./runeemon <command> <space-separated runtime names, or 'all'>

Commands
--------
list               lists the names of all configured runtimes
fetch              downloads the given runtimes into the 'nest' folder (if not already done)
hatch              extracts the given runtimes into the 'zoo' folder (if not already done, fetch included)
deploy             deploys the WAR from the 'autodeploy' directory to the given runtimes (fetch and hatch included)
start-bg           starts the given runtime in the background (fetch, hatch and deploy included)
start-fg           starts the given runtime in the foreground (fetch, hatch and deploy included)
start              starts the given runtime in the background or foreground (fetch, hatch and deploy included)
stop               stops the given runtime after it has been started in the background
clean-deployments  removes the deployments (WARs) for the given runtimes
clean-zoo          removes the extracted files for the given runtimes
clean-nest         removes the downloads and extractions for the given runtimes
info               prints available information for the given runtimes
```

Pro tip: You can also put the runtime names before the actual command.

## Command Line Completion

On the Linux shell, use the following command to enable auto-completion for runEEmon's command line parameters (by pressing 'Tab'):

`source /path/to/completion.sh`

Add this command to `~/.bashrc` to enable auto-completion permanently for a single user.

## Directory Structure

| Directory | Content |
| --- | --- |
| autodeploy | WARs that shall be deployed (initially empty) |
| config/runtimes | one configuration file per supported runtime, and one template for adding new runtimes |
| nest | downloaded runtime zips (initially empty) |
| zoo | extracted runtimes (initially empty) |

## FAQ

### What about Jakarta EE & MicroProfile frameworks?

The following frameworks are currently not supported by runEEmon:

 * [Quarkus](https://quarkus.io/)
 * [Helidon](https://helidon.io/)
 * [Micronaut](https://micronaut.io)
 * [KumuluzEE](https://ee.kumuluz.com/)

This is due to the fact that these are no application servers in the traditional sense.
Some of the relevant differences (which might not all apply to each of them) are:

 * There is no download link. The required libraries for running an application are provided by the framework's specific Maven build.
 * It is not possible to deploy a standard WAR. Instead, the application has to be built using the framework's specific infrastructure.
 * The application is the entry point for starting up (instead of the server), which in turn pulls in the framework's specific infrastructure libraries.

### What about Codehaus Cargo?

[Codehaus Cargo](https://codehaus-cargo.github.io/cargo/Home.html) is a great project with a similar purpose. Compared to runEEmon, it comes with a wider range of application servers and neat integration features. If your requirements exceed occasional experiments, be sure to check it out.

See [runEEmon (Cargo Edition)](https://github.com/enji7/runeemon-cargo), an alternative version of runEEmon that is powered by Codehaus Cargo.

### How to pronounce runEEmon?

Like "runny mon", rhymes with "funny don".

### How can I express my runEEmon-induced euphoria?

Clicking the *Star* button in the upper right corner would be very much appreciated. And if you like my [blog](https://enji.systems), you can follow me on [Twitter](https://twitter.com/EnjiSystems).

