package Accounting.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * @ClassName:Spending
 * @Description:消费类
 * @Author:liumengying
 * @Date: 2023/3/17 9:25
 * Version v1.0
 */
@Data
public class Spending {
    private Long id;  //消费记录id
    private Long userId;  //关联用户id
    private BigDecimal spendingMoney; //消费金额
    private String spendingStores; //消费商家，允许null
    private Date spendingTime;  //消费时间
    private String spendingCredentialName;  //消费凭据id
    private String spendingTypeName;  //消费类型ID
}
