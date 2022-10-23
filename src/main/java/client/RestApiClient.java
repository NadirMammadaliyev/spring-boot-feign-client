package client;

import az.nadir.springbootfeignclient.config.TokenStorage;
import az.nadir.springbootfeignclient.model.AppUser;
import az.nadir.springbootfeignclient.util.TokenUtil;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        name = "rest-api",
        url = "${url.rest-api}",
        primary = false,
        configuration = RestApiClient.FeignConfiguration.class
)
public interface RestApiClient {

    @GetMapping("app-user/get")
    ResponseEntity<List<AppUser>> getAppUsers();

    class FeignConfiguration {

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
        public RequestInterceptor requestInterceptor(TokenStorage tokenStorage) {
            return template -> {
                tokenStorage.setToken();
                template.header(AUTHORIZATION, String.format("%s %s", BEARER, TokenUtil.getToken()));
            };
        }
    }
}
