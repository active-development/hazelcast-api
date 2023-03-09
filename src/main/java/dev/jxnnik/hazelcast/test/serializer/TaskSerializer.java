package dev.jxnnik.hazelcast.test.serializer;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import dev.jxnnik.hazelcast.test.util.Task;

import java.io.IOException;

public class TaskSerializer implements StreamSerializer<Task> {

    @Override
    public void write(ObjectDataOutput out, Task object) throws IOException {
        out.writeString(object.name());
        out.writeInt(object.id());
    }

    @Override
    public Task read(ObjectDataInput in) throws IOException {
        return new Task(in.readString(), in.readInt());
    }

    @Override
    public int getTypeId() {
        return 0;
    }
}
