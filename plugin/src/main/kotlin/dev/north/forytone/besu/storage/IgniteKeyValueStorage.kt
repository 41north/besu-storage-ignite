package dev.north.forytone.besu.storage

import com.google.common.collect.Streams.stream
import org.apache.ignite.Ignite
import org.apache.ignite.IgniteCache
import org.apache.ignite.cache.query.ScanQuery
import org.apache.ignite.transactions.Transaction
import org.hyperledger.besu.plugin.services.storage.KeyValueStorage
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageTransaction
import java.util.*
import java.util.function.Predicate
import java.util.stream.Stream
import javax.cache.Cache.Entry

private class IgniteKeyValueStorageTransaction(
  private val tx: Transaction,
  private val cache: IgniteCache<ByteArray, ByteArray?>
) : KeyValueStorageTransaction {

  override fun put(key: ByteArray, value: ByteArray?) {
    cache.put(key, value)
  }

  override fun remove(key: ByteArray) {
    cache.remove(key)
  }

  override fun rollback() {
    tx.rollback()
  }


  override fun commit() {
    tx.commit()
  }
}

class IgniteKeyValueStorage(
  private val ignite: Ignite,
  cacheName: String
) : KeyValueStorage {

  private val cache: IgniteCache<ByteArray, ByteArray?> = ignite.getOrCreateCache(cacheName)

  override fun startTransaction(): KeyValueStorageTransaction =
    ignite
      .transactions()
      .txStart()
      .let { tx -> IgniteKeyValueStorageTransaction(tx, cache) }

  override fun clear() {
    cache.clear()
  }

  override fun getAllKeysThat(returnCondition: Predicate<ByteArray>): Set<ByteArray> =
    // TODO is the return condition going to get serialised??
    cache
      .query(
        ScanQuery<ByteArray, ByteArray?> { key, _ -> returnCondition.test(key) },
        Entry<ByteArray, ByteArray?>::getKey
      ).toSet()

  override fun containsKey(key: ByteArray): Boolean = cache.containsKey(key)

  override fun get(key: ByteArray): Optional<ByteArray> = Optional.ofNullable(cache.get(key))

  override fun tryDelete(key: ByteArray): Boolean = cache.remove(key)

  override fun close() = cache.close()

  @Suppress("UnstableApiUsage")
  override fun streamKeys(): Stream<ByteArray> =
    cache
      .query(
        ScanQuery<ByteArray, ByteArray?>(null),
        Entry<ByteArray, ByteArray?>::getKey
      )
      .let{ cursor -> stream(cursor) }

}
