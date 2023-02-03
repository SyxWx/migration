package com.bme.syx.hbasetest;


import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HBaseConfig {
    @Value("${datasource.hbase.zookeeper.quorum}")
    private String zookeeper;

    @Value("${datasource.hbase.zookeeper.znode.parent}")
    private String parent;

    @Value("${datasource.hbase.table}")
    private String tableName;

    @Bean(name = "hbaseConnection")
    public Connection getConnection() throws IOException {
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", zookeeper);
        if (parent != null && !"".equals(parent)) {
            config.set("zookeeper.znode.parent", parent);
        }
        Connection connection = ConnectionFactory.createConnection(config);
        return connection;
    }

    @Bean(name = "hbaseTable")
    public Table getHbaseTable() throws IOException {
        Connection connection = getConnection();
        Table table = connection.getTable(TableName.valueOf(tableName));
        return table;
    }

}
