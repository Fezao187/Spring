package kristine.card_generator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class OpenAPIConfig {
    @Value("${kristine.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl(devUrl);
        server.setDescription("Dev URL.");

        Contact contact = new Contact();
        contact.setName("Katlego");
        contact.setUrl("https://github.com/Fezao187");

        Info info = new Info()
        .title("Card Generator")
                .version("1.0")
                .contact(contact)
                .description("This endpoint generates cards in a crud format.");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
