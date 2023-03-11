package dev.aknb.osavdouz;

import dev.aknb.osavdouz.config.RsaKeyProperties;
import dev.aknb.osavdouz.constants.ProjectConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(ProjectConfig.ENTITY_PACKAGE)
@EnableJpaRepositories(ProjectConfig.REPOSITORY_PACKAGE)
@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication(scanBasePackages = {ProjectConfig.BASE_PACKAGE})
public class OsavdouzApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsavdouzApplication.class, args);
    }
}
