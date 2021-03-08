package com.mybestcoding.hmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TerminalDevice extends BaseModel {
    private static final long serialVersionUID = -3953280884598284340L;

    /**
     * 终端设备id
     */
    private Integer id;

    /**
     * 工作状态
     */
    private String status;

}