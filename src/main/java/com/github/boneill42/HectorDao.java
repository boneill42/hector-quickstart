package com.github.boneill42;

import java.util.Map;

import me.prettyprint.cassandra.serializers.CompositeSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.factory.HFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HectorDao {
    private static final Logger LOG = LoggerFactory.getLogger(HectorDao.class);
    private Cluster cluster;
    private Keyspace keyspace;

    public HectorDao(String host, String keyspaceName) {
        this.cluster = HFactory.getOrCreateCluster("MyCluster", new CassandraHostConfigurator(host));
        this.keyspace = HFactory.createKeyspace(keyspaceName, this.cluster);
    }

    /**
     * Writes columns.
     */
    public void write(String columnFamilyName, String rowKey, Map<String, String> columns) {
    }

    /**
     * Fetches an entire row.
     */
    public ColumnFamilyResult<String, Composite> readFishBlog(String columnFamilyName, String rowKey) {
        ColumnFamilyTemplate<String, Composite> template = new ThriftColumnFamilyTemplate<String, Composite>(
                this.keyspace, columnFamilyName, new StringSerializer(), new CompositeSerializer());
        return template.queryColumns(rowKey);
    }

}
