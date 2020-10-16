package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemModel {

    @Id
    public String id;
    public String gameId;
    public String name;
    public long cost;
}
