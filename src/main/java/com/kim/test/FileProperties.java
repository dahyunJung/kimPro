package com.kim.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
@Getter @Setter
public class FileProperties {
    private String uploadDir;
}
