package com.example.querybuilder_backend.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "query-builder")
public class QueryBuilderProperties {

    private Map<String, String> mapping;

}
