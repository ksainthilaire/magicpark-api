package com.magicpark.api

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.io.InputStream


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@Configuration
class ApiApplication {
    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

fun main(args: Array<String>) {
    val file: InputStream = ClassPathResource("service-account-file.json").inputStream

    val options: FirebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(file))
        .build()

    if(FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options)
    }
    runApplication<ApiApplication>(*args)
}
