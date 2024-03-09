package by.nnhorushko.archivedhibernateentity.config;

import by.nnhorushko.archivedhibernateentity.ArchiveFilterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArchivedEntityConfiguration {

    @Bean
    public ArchiveFilterAspect archiveFilterAspect() {
        return new ArchiveFilterAspect();
    }
}
