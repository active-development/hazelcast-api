package dev.jxnnik.hazelcast.api;

public class HazelcastConnection extends HazelcastAPI {

    public HazelcastConnection(String instanceName, String clusterName, int bufferSize, boolean tcpNoDelay, String... addresses) {
        super(instanceName, clusterName, bufferSize, tcpNoDelay, addresses);
    }
}
