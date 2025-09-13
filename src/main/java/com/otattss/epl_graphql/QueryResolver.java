package com.otattss.epl_graphql;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class QueryResolver {

    @QueryMapping
    public String hello() {
        return "Hello from Java + GraphQL!";
    }
}
