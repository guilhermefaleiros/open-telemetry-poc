package com.guifaleiros.service2.persistence;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    private String id;
    private String name;
}
