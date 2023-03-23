package dev.jxnnik.hazelcast.api.cache;

import com.hazelcast.collection.ItemListener;
import com.hazelcast.core.EntryListener;
import com.hazelcast.map.listener.MapListener;
import com.hazelcast.topic.MessageListener;

public interface ICacheProvider {

    void addListenerToMap(String map, MapListener listener, boolean includeValue);

    void addListenerToMultiMap(String multiMap, EntryListener listener, boolean includeValue);

    void addListenerToReplicated(String replicatedMap, EntryListener listener, boolean includeValue);

    void addListenerToTopic(String topic, MessageListener listener);

    void addListenerToReliableTopic(String reliableTopic, MessageListener listener);

    void addListenerToSet(String set, ItemListener listener, boolean includeValue);

    void addListenerToList(String list, ItemListener listener, boolean includeValue);

    void addListenerToQueue(String queue, ItemListener listener, boolean includeValue);
}