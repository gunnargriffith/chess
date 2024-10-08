# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```

## Diagram URL
https://sequencediagram.org/index.html?presentationMode=readOnly&shrinkToFit=true#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdKUAGJITgwamURkwHRhOnAUaYRnElknUG4lTlNA+BAIHEiFRsyXM0kgSFyFD8uE3RkM7RS9Rs4ylBQcDh8jqM1VUPGnTUk1SlHUoPUKHxgVKw4C+1LGiWmrWs06W622n1+h1g9W5U6Ai5lCJQpFQSKqJVYFPAmWFI6XGDXDp3SblVZPQN++oQADW6ErU32jsohfgyHM5QATE4nN0y0MxWMYFXHlNa6l6020C3Vgd0BxTF5fP4AtB2OSYAAZCDRJIBNIZLLdvJF4ol6p1JqtAzqBJoIei0azF5vDgHYsgwr5kvDrco5fB+Sx9AC5wFrKaooOUCAHjysL7oeqLorE2IJoYLphm6ZIUgatLlqOJpEuGFoctyvIGoKwowK+YjGJ2rosuU9GhqRbqdphrFEeKmEajhLGet6QYBkGIbMeakYctGMCxsG2iaDoTGCaSMDTrO6DsUynFJpeMHlBpjZafxelnECJbITy2a5pg-4gtBJRXAMvFjDWQaafO35Xg5hTZD2MD9oOvQuSOowLpOfRGXOEXgWYnCrt4fiBF4KDoHuB6+Mwx7pJkmD+ReRTUNe0gAKK7qV9Slc0LQPqoT7dNFJk+Wy9mGR5xloHZkEdo54IwPB9hZWJdadRhcpYQSqnukYKDcJk8kjTOY3aWaEaFJa0hzRShjyQ6jFmZJM2Ksqq1kWZ3EwCdKqmcmPVptd3UWb5RVOVdSo3S1ZkFWAfYDkOS4JZ4SUbpCtq7tCMAAOKjqyOWnvl57MI515Q5VNX2KOjUdXO7a-uZqbtaNuP2Wyl3ILEMOjKoS2eeNMECRxLEwOSYCLU1aAkTp0rSeUlExuJ2hCmEHOOs6U1M2pFNgFTaiwlza3kXzPIxsq0OjvtOiHdNVxTJj1PjJU-T6ygACS0jjE8J6ZAaFYTF8OgIKADa28B9tPCbAByo723sjQHEdF0TbrfQm6ohsVBSIAuDH-tiyoZltaWeuwxHUcxy4cf2XpP4h2HadgNHsfecVUB6T9f3BT0KcG+UkeFxncfLol66BNgPhQNg3DwLqmTq6MKS5WeOTI-pb03g0GNY8EOPoEOXujiXJR3c9RPLXO3SlqHo7e+FEHPQUl3CZksu051swL6M9NOomEvc2prPs7PnNnbpG0cjA-NyYL8jC+pz-x0mjIHWMBj4oFPrLCS00lZyRtP3FAmsVKSxmsbUc5s4qBz6vKLeJt0FL1EIne6zlcEW33qmHOPliFoNIXjcuSNK5Dm3qMPB8UVzA1bgESwc14LJBgAAKQgDyeBgRHbO0RiPMmlDKhVEpHeFoJtsbEznr0LuwAuFQDgBAeCUAL7UKXr1P8RD-5KOfCFVR6jNHaK+CQuKpMsGwRgAAK0EQ4UcsIBE8llqhDE19xbAOQXhNmokOYK3Ou-ZWvI9pC1oqLUyd81qBNPiQ0Jb92QRNtJA6JYQSGAMZvfGafgtAnzceYygljoC6OYdIFJPNwl8mwEUwwmTf4QAAGYs2oa-Hm9iFQfVyYQ1e71Tp2JRg9PpP5OwV0Cv9XogM2FrmSgELwaiuxelgMAbAXdCDxESIPBGP1JGlzrmVCqVUarGDxq1IhPQyFQTHv1EA3A8Dy36fE8M5QODbQWsE5UNSpJ1K2vNQwwA1ZRPkK8-x+SPlfP1DSEFCA-nrTSTIGF6k1bUUUnEyFCTZpAtPvCxFMDAU7TRQgDpowHRHS4sHIZn0GYDMJrSp6qZqVHKZRM769DplVzmZgIAA

