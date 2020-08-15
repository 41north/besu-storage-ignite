package dev.north.forytone.besu

import dev.north.forytone.besu.IgniteStoragePlugin.Companion.cliPrefix
import picocli.CommandLine

class BesuCommandMixin {

  @CommandLine.Option(
    names = ["$cliPrefix-config-path"],
    paramLabel = "<STRING>",
    defaultValue = "../etc/ignite/config.xml",
    description = ["Path for the ignite config file"]
  )
  var configPath: String = "../etc/ignite/config.xml"

}
