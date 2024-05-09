package com.example.jasypt.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}")
    private String passwordKey;

    @Bean
    public StringEncryptor jasyptStringEncryptor(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor(); // PBE라는 알고리즘을 따르는 encryptor(암호화기) 객체 생성
        SimpleStringPBEConfig config = new SimpleStringPBEConfig(); // encryptor 설정객체
        config.setPassword(passwordKey); // 암호화시 사용할 키
        config.setAlgorithm("PBEWithMD5AndDES");  // 암호화 알고리즘
        config.setKeyObtentionIterations(1000); // 반복할 해싱 횟수
        config.setPoolSize(1); // 인스턴스 pool
        config.setProviderName("SunJCE"); // Java Cryptography Extension (JCE)에서 사용되는 Provider를 설정하는 코드
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");     // 인코딩 방식
        encryptor.setConfig(config);
        return encryptor;
    }
}
