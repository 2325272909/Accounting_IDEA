package Accounting.entity;

import lombok.Data;

/**
 * @ClassName:User
 * @Description:user实体类，对接数据库user表属性
 * @Author:liumengying
 * @Date: 2023/3/5 10:00
 * Version v1.0
 */
@Data
public class User {
    private Long id;   //用户id
    private String userName;
    private String userPassword;
    private String userPhone;
}
