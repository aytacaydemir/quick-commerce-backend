package com.aytac.quickcommerceapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "attribute")
@Data
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
}
