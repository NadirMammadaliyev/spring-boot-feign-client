package client;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonDecoder;
import java.util.Map;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "rest-api-login",
        url = "${url.rest-api}",
        primary = false,
        configuration = RestApiLoginClient.FeignConfiguration.class
)
public interface RestApiLoginClient {

    @PostMapping(value = "app-user/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Map<String, ?> login(@RequestBody Map<String, ?> auth);

    class FeignConfiguration {

        ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;


        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public ErrorDecoder feignErrorDecoder() {
            return AnnotationErrorDecoder.builderFor(RequestInterceptor.class)
                    .withResponseBodyDecoder(new JacksonDecoder())
                    .build();
        }

        @Bean
        Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }

        @Bean
        Decoder feignFormDecoder() {
            return new SpringDecoder(messageConverters);
        }
    }

}

