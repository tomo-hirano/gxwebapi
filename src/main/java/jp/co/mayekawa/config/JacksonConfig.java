package jp.co.mayekawa.config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * JSONの変換処理をカスタマイズするための設定クラス。
 */
@Configuration
public class JacksonConfig {

    /**
     * null値を空文字に変換するカスタムObjectMapper作成処理。
     * 
     * @return カスタムObjectMapper
     */
    @Bean
    @Qualifier("nullToEmptyMapper")
    public ObjectMapper nullToEmptyObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // LocalDateTime用のモジュール設定
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // LocalDateTimeを文字列形式でシリアライズ（配列形式を回避）
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));

        mapper.registerModule(javaTimeModule);

        // 配列形式を無効にする
        mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // null値を空文字に変換
        DefaultSerializerProvider.Impl provider = new DefaultSerializerProvider.Impl();
        provider.setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        });

        mapper.setSerializerProvider(provider);
        return mapper;
    }
}
