package dev.jxnnik.hazelcast.test;

import dev.jxnnik.hazelcast.api.HazelcastAPI;
import dev.jxnnik.hazelcast.api.HazelcastConnection;
import dev.jxnnik.hazelcast.test.listener.TaskListener;
import dev.jxnnik.hazelcast.test.serializer.TaskSerializer;
import dev.jxnnik.hazelcast.test.util.Task;
import lombok.SneakyThrows;

public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        new HazelcastConnection("test-system", "dev", 32, true, "45.142.114.48:5701");
        HazelcastAPI.getHazelcastAPI().getSerializerProvider().addSerializerConfig(Task.class, new TaskSerializer());
        HazelcastAPI.getHazelcastAPI().getCacheProvider().addListenerToMap("tasks", new TaskListener(), true);
        Thread.sleep(1000);
        HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getMap("tasks").put("task-1", new Task("task-1", 1));
        Thread.sleep(1000);
        HazelcastAPI.getHazelcastAPI().getHazelcastInstance().getMap("tasks").remove("task-1");
    }
}