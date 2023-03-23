package dev.jxnnik.hazelcast.api;

import com.hazelcast.client.config.SocketOptions;

public class HazelcastConnection extends HazelcastAPI {

    public HazelcastConnection(String instanceName, String clusterName, int bufferSize, boolean tcpNoDelay, String... addresses) {
        super(instanceName, clusterName, bufferSize, tcpNoDelay, addresses);
    }

    public HazelcastConnection(String instanceName, String clusterName, String... addresses) {
        super(instanceName, clusterName, addresses);
    }

    public HazelcastConnection(String instanceName, String clusterName, SocketOptions socketOptions, String... addresses) {
        super(instanceName, clusterName, socketOptions, addresses);
    }
}
