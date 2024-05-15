package com.econnect.econnect.carbon_footprint_calculation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rank {
    @Id
    private Integer rankId;

    private Integer standard;

    @Column(length = 255)
    private String rank;

    @Column(length = 255)
    private String imgPath;
}
