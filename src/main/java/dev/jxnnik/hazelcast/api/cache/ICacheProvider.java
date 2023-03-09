package dev.jxnnik.hazelcast.api.cache;

import com.hazelcast.collection.IList;
import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ISet;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.MapListener;
import com.hazelcast.multimap.MultiMap;
import com.hazelcast.replicatedmap.ReplicatedMap;
import com.hazelcast.ringbuffer.Ringbuffer;
import com.hazelcast.topic.ITopic;

public interface ICacheProvider {

    <E> IQueue<E> getQueue(String name);

    <E> ITopic<E> getTopic(String name);

    <E> ISet<E> getSet(String name);

    <E> IList<E> getList(String name);

    <K, V> IMap<K, V> getMap(String name);

    <K, V> ReplicatedMap<K, V> getReplicatedMap(String name);

    <K, V> MultiMap<K, V> getMultiMap(String name);

    <E> Ringbuffer<E> getRingbuffer(String name);

    <E> ITopic<E> getReliableTopic(String name);

    void addListenerToMap(String map, MapListener listener, boolean includeValue);
}