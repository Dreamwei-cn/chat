package com.chat.chatopenai.dto;

public class ResultUtls {

    public static Result success(){
        Result result = new Result();
        result.setCode("0");
        result.setMsg("success");
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setCode("1");
        result.setMsg("fail");
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setCode("1");
        result.setMsg(msg);
        return result;
    }
    public static Result success(String msg){
        Result result = new Result();
        result.setCode("0");
        result.setMsg(msg);
        return result;
    }

    public static Result data(Object obj){
        Result result = new Result();
        result.setCode("0");
        result.setMsg("success");
        result.setResult(obj);
        return result;
    }
}
