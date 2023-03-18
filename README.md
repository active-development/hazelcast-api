# hazelcast-api
A small api to use hazelcast easier.

## Create connection to hazelcast cluster
```java
new HazelcastConnection(String instanceName, String clusterName, int bufferSize, boolean tcpNoDelay, String... addresses);
```

Example:
```java
new HazelcastConnection("test-system", "dev", 64, true, "127.0.0.1:5701");
```

## Adding a SerializerConfig
```java
HazelcastAPI.getHazelcastAPI().getSerializerProvider().addSerializerConfig(Class class, Serializer implementation);
```

Example:
```java
HazelcastAPI.getHazelcastAPI().getSerializerProvider().addSerializerConfig(Task.class, new TaskSerializer());
```

## Register listener to map
```java
HazelcastAPI.getHazelcastAPI().getCacheProvider().getCacheProvider().addListenerToMap(String map, MapListener listener, boolean includeValue);
```

Example:
```java
HazelcastAPI.getHazelcastAPI().getCacheProvider().getCacheProvider().addListenerToMap("tasks", new TaskListener(), true);
```****
