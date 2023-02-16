<<<<<<<< HEAD:BackEnd/maryfarm-calendar-service/src/main/java/com/ssafy/maryfarmcalendarservice/config/MongoDBConfig.java
<<<<<<<< HEAD:BackEnd/maryfarm-chat-service/src/main/java/com/ssafy/maryfarmchatservice/config/MongoDBConfig.java
package com.ssafy.maryfarmchatservice.config;
========
package com.ssafy.maryfarmcalendarservice.config;
>>>>>>>> maryfarm-calendar-service:BackEnd/maryfarm-calendar-service/src/main/java/com/ssafy/maryfarmcalendarservice/config/MongoDBConfig.java
========
package com.ssafy.maryfarmuserservice.config;
>>>>>>>> maryfarm-user-service:BackEnd/maryfarm-user-service/src/main/java/com/ssafy/maryfarmuserservice/config/MongoDBConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoDBConfig {
    @Bean
    public MappingMongoConverter mappingMongoConverter(
            MongoDatabaseFactory mongoDatabaseFactory,
            MongoMappingContext mongoMappingContext
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}
