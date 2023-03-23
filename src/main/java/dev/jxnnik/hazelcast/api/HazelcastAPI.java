package dev.jxnnik.hazelcast.api;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.client.config.SocketOptions;
import com.hazelcast.collection.IList;
import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ISet;
import com.hazelcast.collection.ItemListener;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.MapListener;
import com.hazelcast.multimap.MultiMap;
import com.hazelcast.nio.serialization.Serializer;
import com.hazelcast.replicatedmap.ReplicatedMap;
import com.hazelcast.ringbuffer.Ringbuffer;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.MessageListener;
import dev.jxnnik.hazelcast.api.cache.ICacheProvider;
import dev.jxnnik.hazelcast.api.serializer.ISerializerProvider;
import lombok.Getter;

@Getter
public abstract class HazelcastAPI {

    @Getter
    private static HazelcastAPI hazelcastAPI;
    private final HazelcastInstance hazelcastInstance;
    private final ICacheProvider cacheProvider;
    private final ISerializerProvider serializerProvider;

    public HazelcastAPI(String instanceName, String clusterName, int bufferSize, boolean tcpNoDelay, String... addresses) {
        hazelcastAPI = this;

        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig networkConfig = new ClientNetworkConfig();
        SocketOptions socketOptions = new SocketOptions();

        networkConfig.addAddress(addresses);
        socketOptions.setBufferSize(bufferSize);
        socketOptions.setTcpNoDelay(tcpNoDelay);

        clientConfig.setNetworkConfig(networkConfig);
        clientConfig.setInstanceName(instanceName);
        clientConfig.setClusterName(clusterName);
        clientConfig.getNetworkConfig().setSocketOptions(socketOptions);

        this.hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);

        this.cacheProvider = new ICacheProvider() {

            @Override
            public void addListenerToMap(String map, MapListener listener, boolean includeValue) {
                hazelcastInstance.getMap(map).addEntryListener(listener, includeValue);
            }

            @Override
            public void addListenerToMultiMap(String multiMap, EntryListener listener, boolean includeValue) {
                hazelcastInstance.getMultiMap(multiMap).addEntryListener(listener, includeValue);
            }

            @Override
            public void addListenerToReplicated(String replicatedMap, EntryListener listener, boolean includeValue) {
                hazelcastInstance.getReplicatedMap(replicatedMap).addEntryListener(listener, includeValue);
            }

            @Override
            public void addListenerToTopic(String topic, MessageListener messageListener) {
                hazelcastInstance.getTopic(topic).addMessageListener(messageListener);
            }

            @Override
            public void addListenerToReliableTopic(String topic, MessageListener messageListener) {
                hazelcastInstance.getReliableTopic(topic).addMessageListener(messageListener);
            }

            @Override
            public void addListenerToSet(String set, ItemListener listener, boolean includeValue) {
                hazelcastInstance.getSet(set).addItemListener(listener, includeValue);
            }

            @Override
            public void addListenerToList(String list, ItemListener listener, boolean includeValue) {
                hazelcastInstance.getList(list).addItemListener(listener, includeValue);
            }

            @Override
            public void addListenerToQueue(String queue, ItemListener listener, boolean includeValue) {
                hazelcastInstance.getQueue(queue).addItemListener(listener, includeValue);
            }
        };
        this.serializerProvider = new ISerializerProvider() {
            @Override
            public SerializationConfig getSerializationConfig() {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getConfig().getSerializationConfig();
            }

            @Override
            public void addSerializerConfig(Class typeClass, Serializer implementation) {
                SerializerConfig serializerConfig = new SerializerConfig();
                serializerConfig.setTypeClass(typeClass).setImplementation(implementation);

                getSerializationConfig().addSerializerConfig(serializerConfig);
            }
        };
    }
}