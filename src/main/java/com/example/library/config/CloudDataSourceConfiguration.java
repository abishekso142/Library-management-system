
package com.example.library.config;

import javax.sql.DataSource;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudDataSourceConfiguration extends AbstractCloudConfig
{

    public DataSource datasource()
    {
        return connectionFactory().dataSource();
    }
}
