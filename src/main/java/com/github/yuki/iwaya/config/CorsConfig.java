package com.github.yuki.iwaya.config;

import org.springframework.lang.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry){
                registry.addMapping("/**") // すべてのパスで
                        .allowedOrigins("*") // 任意のオリジンから
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 許可するメソッド
                        .allowedHeaders("*") // 任意のヘッダーを許可
                        .allowCredentials(false) // Cookieなどを使う場合はtrueに
                        .maxAge(3600); // プリフライトリクエストの有効時間（秒）
            }
        };
    }
}
