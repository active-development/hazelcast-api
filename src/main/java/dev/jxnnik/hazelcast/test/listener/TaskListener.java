package dev.jxnnik.hazelcast.test.listener;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import dev.jxnnik.hazelcast.test.util.Task;

public class TaskListener implements EntryAddedListener<String, Task>, EntryRemovedListener<String, Task> {

    @Override
    public void entryAdded(EntryEvent<String, Task> event) {
        System.out.println("added");
    }

    @Override
    public void entryRemoved(EntryEvent<String, Task> event) {
        System.out.println("removed");
    }
}
