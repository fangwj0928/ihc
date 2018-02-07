package com.vijay.ihc.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class UserQueryCondition {

    private String username;

    @ApiModelProperty("用户年龄起始值")
    private Integer age;
    @ApiModelProperty("用户年龄结束值")
    private Integer ageTo;

    private Date birthday;

}
