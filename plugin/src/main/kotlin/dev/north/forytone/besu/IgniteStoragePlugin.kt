package dev.north.forytone.besu

import dev.north.forytone.besu.storage.IgniteKeyValueStorageFactory
import org.apache.ignite.Ignite
import org.apache.ignite.Ignition
import org.apache.logging.log4j.LogManager
import org.hyperledger.besu.plugin.BesuContext
import org.hyperledger.besu.plugin.BesuPlugin
import org.hyperledger.besu.plugin.services.PicoCLIOptions
import org.hyperledger.besu.plugin.services.StorageService
import java.net.URI

class IgniteStoragePlugin : BesuPlugin {

  companion object {
    const val name = "ignite-storage"
    const val cliPrefix = "--plugin-$name-"

    private val log = LogManager.getLogger(IgniteStoragePlugin::class.java)
  }

  private val commandMixin = BesuCommandMixin()

  private lateinit var storageFactory: IgniteKeyValueStorageFactory

  override fun register(context: BesuContext) {

    // register cli

    val cliService = context
      .getService(PicoCLIOptions::class.java)
      .orElseThrow { Error("Could not find pico cli options service in plugin context") }

    cliService.addPicoCLIOptions(IgniteStoragePlugin.name, commandMixin)

    // register storage factory

    val storageService = context
      .getService(StorageService::class.java)
      .orElseThrow { Error("Could not find storage service in plugin context") }

    this.storageFactory = IgniteKeyValueStorageFactory(commandMixin)

    storageService
      .registerKeyValueStorage(storageFactory)
  }

  override fun start() {
    // nothing to do
  }

  override fun stop() {
  }

}
