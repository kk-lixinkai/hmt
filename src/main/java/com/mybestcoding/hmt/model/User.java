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
@Table(name = "hmt_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 6486069176251944510L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String tel;
    private String email;
    private String salt;
    @Column(name = "user_key")
    private String userKey;
    @JsonFormat
    @Column(name = "created_time")
    private Date created;
    @JsonFormat
    @Column(name = "last_login_time")
    private Date last;
    @JsonFormat
    @Column(name = "updated_time")
    private Date updated;
}
