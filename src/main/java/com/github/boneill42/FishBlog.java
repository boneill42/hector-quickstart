package com.github.boneill42;

import me.prettyprint.cassandra.serializers.LongSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.beans.Composite;

public class FishBlog {
    private Composite composite;

    public FishBlog(Composite composite) {
        this.composite = composite;
    }

    public long getWhen() {
        return composite.get(0, new LongSerializer());
    }

    public String getType() {
        return composite.get(1, new StringSerializer());
    }

    public String getField() {
        return composite.get(2, new StringSerializer());
    }
}
