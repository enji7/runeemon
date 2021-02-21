# runEEmon

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
