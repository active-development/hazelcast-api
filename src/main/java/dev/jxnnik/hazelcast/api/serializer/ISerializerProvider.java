package dev.jxnnik.hazelcast.api.serializer;

import com.hazelcast.config.SerializationConfig;
import com.hazelcast.nio.serialization.Serializer;

public interface ISerializerProvider {

    SerializationConfig getSerializationConfig();

    void addSerializerConfig(Class typeClass, Serializer implementation);
}