package in.dhirajrajput.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Journal APP APIs")
                        .description("By Dhiraj Kumar"))
                        .servers(List.of(new Server().url("http://localhost:9090").description("localhost"),new Server().url("http://localhost:9091").description("prod")))
                        ;
    }
}
