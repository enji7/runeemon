# runEEmon

covers runtimes that can be freely downloaded (i.e., without login)

see also: [Jakarta EE application server cheat sheet](https://rieckpil.de/cheatsheet-java-jakarta-ee-application-servers/)

Gotta Catch 'Em All :)

## Usage

```

// no arguments: fetch extract info all

// downloads the given runtimes
fetch all
fetch <runtime>

// extract the given runtimes (and fetches them, if not available)
extract all
extract <runtime>

// removes the extracted files for the given runtime
clean all
clean <runtime>

// removes the downloaded and extracted files for the given runtime
purge all
purge <runtime>

// starts and stops the given runtime
start <runtime> (defaults to foreground, falls back to background)
start-fg <runtime>
start-bg <runtime>
stop <runtime>

// prints info for the given runtimes
info all
info <runtime>
```

## Making ping.war work

out-of-the-box:

 * Wildfly
 * Payara
 * TomEE

requiring configuration:

 * OpenLiberty
   * server.xml: add `<feature>javaee-8.0</feature>`

## Command Line Completion

for Linux shell: auto-completion for Runeemon command line parameters (by pressing 'tab')

`source ./completion.sh`

add to ~/.bashrc for a single user, or to /etc/bash_completion/ for all users


## Jakarta EE & MicroProfile Frameworks

 *   - Quarkus
 *   - Helidon
 *   - Micronaut
 *   - KumuluzEE

## TODO

 * finish documentation
 * license
 * version update
 * publish
 * parallel versions (test & document)
 * make it run under Windows as well
 * make the shell script startable from anywhere
 