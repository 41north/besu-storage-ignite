<h1 align="center">⚡ Besu Storage Ignite ⚡</h1>

<p align="center">Use Apache Ignite as your Key/Value store!</p>

## 💡 Introduction

From Apache's Ignite website:

> Apache Ignite® is a horizontally scalable, fault-tolerant distributed in-memory computing platform for building real-time applications that can process terabytes of data with in-memory speed.

This plugin allows to use [Apache Ignite](https://ignite.apache.org/) as the Key/Value storage mechanism for your Hyperledger Besu instance.

## 🙈 Usage

First, clone this repository:

```sh
git clone git@github.com:41north/besu-storage-ignite.git
```

Open IntelliJ, load the project and type the following in the terminal:

```sh
./gradlew generateIntellijRunConfigs
```

That will generate [Intellij's Run Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) from the [`intellij-run-configs.yaml`](./intellij-run-configs.yaml) file with several useful commands (feel free to customize it as necessary).

After the run configs are generated, next type the following in the terminal:

```sh
docker-compose up
```

That will start Apache Ignite. Next is to start Besu with one network by launching one of the following run config:

- `BESU | Dev > Run`
- `BESU | Ropsten > Run`
- `BESU | Mainnet > Run`

After that, you will see Key/Value entries being populated to Apache Ignite.

## 💻 Contribute

We welcome any kind of contribution or support to this project but before to do so:

* Make sure you have read the [contribution guide](/.github/CONTRIBUTING.md) for more details on how to submit a good PR (pull request).

Also, we are not only limited to technical contributions. Things that make us happy are:

* Add a [GitHub Star](https://github.com/41north/besu-storage-ignite/stargazers) to the project.
* Tweet about this project.
* Write a review or tutorial.

## Other Gradle plugins

We have published other Besu plugins:

- [Besu Exflo](https://github.com/41north/besu-exflo).
- [Besu Storage Replication](https://github.com/41north/besu-storage-replication).

Also, have a look at our [Awesome Besu](https://github.com/41north/awesome-besu) list to find more useful stuff!

## 📬 Get in touch

`Besu Storage Ignite` has been developed initially by [°41North](https://41north.dev). 

If you think this project would be useful for your use case and want to talk more about it you can reach out to us via 
our contact form or by sending an email to `hello@41north.dev`. We try to respond within 48 hours and look forward to hearing from you.

## ✍️ License

`Besu Storage Ignite` is free and open-source software licensed under the [Apache 2.0 License](./LICENSE).
