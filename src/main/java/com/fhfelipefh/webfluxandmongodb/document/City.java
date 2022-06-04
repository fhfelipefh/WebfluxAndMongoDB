package com.fhfelipefh.webfluxandmongodb.document;

import com.fhfelipefh.webfluxandmongodb.config.Generated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class City {

    @Id
    private String id;
    private String name;
    private String state;
    private String country;

}
