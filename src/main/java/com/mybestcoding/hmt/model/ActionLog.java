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
@Table(name = "hmt_action_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLog implements Serializable {
    private static final long serialVersionUID = 4329871130104660319L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String owner;
    private String description;
    @JsonFormat
    @Column(name = "created_time")
    private Date created;
}
