package dev.north.forytone.besu.storage

import dev.north.forytone.besu.BesuCommandMixin
import org.apache.ignite.Ignite
import org.apache.ignite.Ignition
import org.hyperledger.besu.plugin.services.BesuConfiguration
import org.hyperledger.besu.plugin.services.MetricsSystem
import org.hyperledger.besu.plugin.services.storage.KeyValueStorage
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageFactory
import org.hyperledger.besu.plugin.services.storage.SegmentIdentifier

class IgniteKeyValueStorageFactory(
  private val command: BesuCommandMixin
) : KeyValueStorageFactory {

  private val ignite: Ignite by lazy {
    // start ignite client
    Ignition.setClientMode(true)
    Ignition.start(command.configPath)
  }

  override fun getName(): String = "ignite-storage"

  override fun isSegmentIsolationSupported(): Boolean = true

  override fun create(
    segment: SegmentIdentifier,
    configuration: BesuConfiguration,
    metricsSystem: MetricsSystem
  ): KeyValueStorage = IgniteKeyValueStorage(
    ignite,
    segment.name
  )

  override fun close() {
    ignite.close()
  }

}
