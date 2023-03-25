package dev.jxnnik.hazelcast.api;

import com.hazelcast.client.LoadBalancer;
import com.hazelcast.client.config.*;
import com.hazelcast.config.SSLConfig;

import java.util.Properties;
import java.util.Set;

public class HazelcastConnectionBuilder {

    private final SocketOptions socketOptions;
    private final ClientConfig clientConfig;
    private final ClientNetworkConfig networkConfig;

    public HazelcastConnectionBuilder(String cluster, String... addresses) {
        this.socketOptions = new SocketOptions();
        this.clientConfig = new ClientConfig();
        this.networkConfig = new ClientNetworkConfig();

        clientConfig.setClusterName(cluster);
        networkConfig.addAddress(addresses);
    }

    public HazelcastConnectionBuilder setInstanceName(String instanceName) {
        clientConfig.setInstanceName(instanceName);
        return this;
    }

    public HazelcastConnectionBuilder set(ClassLoader classLoader) {
        clientConfig.setClassLoader(classLoader);
        return this;
    }

    public HazelcastConnectionBuilder setLabels(Set<String> labels) {
        clientConfig.setLabels(labels);
        return this;
    }

    public HazelcastConnectionBuilder setProperty(String name, String value) {
        clientConfig.setProperty(name, value);
        return this;
    }

    public HazelcastConnectionBuilder setProperties(Properties properties) {
        clientConfig.setProperties(properties);
        return this;
    }

    public HazelcastConnectionBuilder setBackupAckToClientEnabled(boolean backupAckToClientEnabled) {
        clientConfig.setBackupAckToClientEnabled(backupAckToClientEnabled);
        return this;
    }

    public HazelcastConnectionBuilder setSqlConfig(ClientSqlConfig clientSqlConfig) {
        clientConfig.setSqlConfig(clientSqlConfig);
        return this;
    }

    public HazelcastConnectionBuilder setLoadBalancer(LoadBalancer loadBalancer) {
        clientConfig.setLoadBalancer(loadBalancer);
        return this;
    }

    public HazelcastConnectionBuilder setLoadBalancerClassName(String className) {
        clientConfig.setLoadBalancerClassName(className);
        return this;
    }

    public HazelcastConnectionBuilder setTcpNoDelay(boolean tcpNoDelay) {
        socketOptions.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    public HazelcastConnectionBuilder setKeepAlive(boolean keepAlive) {
        socketOptions.setKeepAlive(keepAlive);
        return this;
    }

    public HazelcastConnectionBuilder setReuseAddress(boolean reuseAddress) {
        socketOptions.setReuseAddress(reuseAddress);
        return this;
    }

    public HazelcastConnectionBuilder setBufferSize(int bufferSize) {
        socketOptions.setBufferSize(bufferSize);
        return this;
    }

    public HazelcastConnectionBuilder setLingerSeconds(int lingerSeconds) {
        socketOptions.setLingerSeconds(lingerSeconds);
        return this;
    }

    public HazelcastConnectionBuilder addOutboundPort(int outboundPort) {
        networkConfig.addOutboundPort(outboundPort);
        return this;
    }

    public HazelcastConnectionBuilder setSSLConfig(SSLConfig sslConfig) {
        networkConfig.setSSLConfig(sslConfig);
        return this;
    }

    public HazelcastConnectionBuilder setSmartRouting(boolean smartRouting) {
        networkConfig.setSmartRouting(smartRouting);
        return this;
    }

    public HazelcastConnectionBuilder setRedoOperation(boolean redoOperation) {
        networkConfig.setRedoOperation(redoOperation);
        return this;
    }

    public HazelcastConnectionBuilder build() {
        new HazelcastAPI(clientConfig, networkConfig, socketOptions);
        return this;
    }
}