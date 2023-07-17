package com.hepsiemlak.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchBaseConfiguration extends AbstractCouchbaseConfiguration {
    @Value("${spring.couchbase.bootstrap-hosts}")
    String host;

    @Value("${spring.couchbase.username}")
    String userName;

    @Value("${spring.couchbase.bucket.name}")
    String bucketName;

    @Value("${spring.couchbase.password}")
    String password;

    @Override
    public String getConnectionString() {
        return host;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }
}
