package com.chat.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Result {

    private String code;

    private String msg;

    private Object result;




}
