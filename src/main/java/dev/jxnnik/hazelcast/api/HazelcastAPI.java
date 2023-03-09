package dev.jxnnik.hazelcast.api;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.client.config.SocketOptions;
import com.hazelcast.collection.IList;
import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ISet;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.MapListener;
import com.hazelcast.multimap.MultiMap;
import com.hazelcast.nio.serialization.Serializer;
import com.hazelcast.replicatedmap.ReplicatedMap;
import com.hazelcast.ringbuffer.Ringbuffer;
import com.hazelcast.topic.ITopic;
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
            public <E> IQueue<E> getQueue(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getQueue(name);
            }

            @Override
            public <E> ITopic<E> getTopic(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getTopic(name);
            }

            @Override
            public <E> ISet<E> getSet(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getSet(name);
            }

            @Override
            public <E> IList<E> getList(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getList(name);
            }

            @Override
            public <K, V> IMap<K, V> getMap(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getMap(name);
            }

            @Override
            public <K, V> ReplicatedMap<K, V> getReplicatedMap(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getReplicatedMap(name);
            }

            @Override
            public <K, V> MultiMap<K, V> getMultiMap(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getMultiMap(name);
            }

            @Override
            public <E> Ringbuffer<E> getRingbuffer(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getRingbuffer(name);
            }

            @Override
            public <E> ITopic<E> getReliableTopic(String name) {
                return HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getReliableTopic(name);
            }

            @Override
            public void addListenerToMap(String map, MapListener listener, boolean includeValue) {
                getMap(map).addEntryListener(listener, includeValue);
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