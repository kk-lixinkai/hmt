package com.mybestcoding.hmt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created By lixinkai on 2020/12/29
 */
@Entity
@Table(name = "hmt_warehouse")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse implements Serializable {
    private static final long serialVersionUID = 5993675187838284305L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "warehouse_type")
    private String warehouseType;
    @Column(name = "warehouse_key")
    private String warehouseKey;
    private String location;
    @JsonFormat
    @Column(name = "created_time")
    private Date created;
}
