package com.mybestcoding.hmt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Email extends BaseModel {
    private static final long serialVersionUID = 7299245851667978960L;

    /**
     * 邮件id
     */
    private Integer id;

    /**
     * 发件人
     */
    private String from;

    /**
     * 收件人
     */
    private String to;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件地址
     */
    private String url;

}