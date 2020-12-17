package common.marvel.helper.spring.config

import common.marvel.helper.global.Global
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author leo
 */
@Configuration
class CommonBeansConfig {

    @Bean
    fun objectMapper() = Global.objectMapper
}
