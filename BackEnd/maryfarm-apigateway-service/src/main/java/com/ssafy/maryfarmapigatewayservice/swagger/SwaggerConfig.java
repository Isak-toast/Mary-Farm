package com.ssafy.maryfarmapigatewayservice.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    public static final String API_URI = "/v3/api-docs";
    private final RouteDefinitionLocator routeLocator;
    public SwaggerConfig(RouteDefinitionLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("1.0");
        return swaggerResource;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        routeLocator.getRouteDefinitions().subscribe(
                routeDefinition -> {
                    String resourceName = routeDefinition.getId();
                    String location =
                            routeDefinition
                                    .getPredicates()
                                    .get(0)
                                    .getArgs()
                                    .get("_genkey_0")
                                    .replace("/**", API_URI);
//                    String location = "https://" +
//                            routeDefinition.getPredicates().get(0).getArgs().get("_genkey_0")
//                                    .replace("/**", API_URI);
                    resources.add(
                            swaggerResource(resourceName, location)
                    );
                }
        );
        return resources;
    }
    
}
