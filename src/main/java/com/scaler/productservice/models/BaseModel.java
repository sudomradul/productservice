package com.scaler.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass // dont create table for this in DB - this is just a super class for other tables/classes/models
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // increment strategy / others are - uuid, auto etc
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
}
