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
@Table(name = "hmt_device")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {
    private static final long serialVersionUID = -8704954808021527152L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device_type")
    private String deviceType;
    @Column(name = "device_key")
    private String deviceKey;
    @JsonFormat
    @Column(name = "created_time")
    private Date created;
    @JsonFormat
    @Column(name = "last_time")
    private Date last;
}
