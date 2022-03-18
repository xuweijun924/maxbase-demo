package com.maxnerva.maxbase.demo.common.swagger;


import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.maxnerva.maxbase.demo.common.properties.MaxbaseDemoProperties;
import com.maxnerva.maxbase.demo.common.properties.model.Swagger;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger 配置
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@EnableSwagger2WebMvc
@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final MaxbaseDemoProperties maxbaseDemoProperties;
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket createRestApi() {
        Swagger swagger = maxbaseDemoProperties.getSwagger();
        String groupName = swagger.getGroupName();
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swagger.getEnable())
                .apiInfo(apiInfo())
                .groupName(groupName)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
    }

    private ApiInfo apiInfo() {
        Swagger swagger = maxbaseDemoProperties.getSwagger();
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .contact(new Contact(swagger.getAuthor(), swagger.getAuthorUrl(), swagger.getAuthorEmail()))
                .version(swagger.getVersion())
                .build();
    }

}
