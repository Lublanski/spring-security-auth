package ai.gobots.authentication.infrastucture.configuration.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Resources {

    @Bean
    fun webResources(): WebProperties.Resources = WebProperties.Resources()
}
