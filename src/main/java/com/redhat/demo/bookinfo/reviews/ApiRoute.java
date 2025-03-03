package com.redhat.demo.bookinfo.reviews;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class ApiRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json)
                .bindingPackageScan("com.redhat.demo.bookinfo.reviews");
        
        
        rest().openApi().specification("reviews-api.json").missingOperation("ignore");
        

        
        from("direct:sampleOperationId")
                .removeHeaders("*")
                .to("rest-openapi:ratings-api.json#getProductRatings?host={{openapi.client.ratings.host}}");
        
    }
}
