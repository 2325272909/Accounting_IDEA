package Accounting.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:R
 * @Description:返回模板类
 * @Author:liumengying
 * @Date: 2023/3/5 10:15
 * Version v1.0
 */
@Data
public class R<T> implements Serializable {

    private Integer code;  //返回码：1为成功
    private String msg;  //错误信息
    private T data; //返回的数据


    public static <T> R<T> success(T object){
        R<T> r=new R<T>();
        r.data=object;
        r.code=1;
        r.msg="成功";
        return r;

    }

    public static <T> R<T> success(String msg){
        R<T> r=new R<T>();
        r.msg=msg;
        r.code=1;
        return r;
    }
    //创建错误返回结果
    public static <T> R<T> error(String msg){
        R<T> r=new R<T>();
        r.msg=msg;
        r.code=0;
        return r;
    }

}


