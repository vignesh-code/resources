package com.coeus.models.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @Id
    private String id;

    @Field("created_date")
    private Date createdDate;

    @Field("modified_date")
    private Date modifiedDate;

}
