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
 * Created By lixinkai on 2020/12/30
 */
@Entity
@Table(name = "hmt_node_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeData implements Serializable {
    private static final long serialVersionUID = -8618357693910590553L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sign;
    @Column(name = "node_type")
    private String nodeType;
    @Column(name = "node_value")
    private String nodeValue;
    private String status;
    @JsonFormat
    @Column(name = "upload_time")
    private Date upload;

}
