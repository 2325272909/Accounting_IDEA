package Accounting.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * @ClassName:Income
 * @Description:收入实体类
 * @Author:liumengying
 * @Date: 2023/3/17 16:04
 * Version v1.0
 */
@Data
public class Income {
    private Long id;
    private Long userId;
    private String incomeTypeName;  //收入类型id
    private BigDecimal incomeMoney;  //收入金额
    private Date incomeTime;  //收入时间
}
