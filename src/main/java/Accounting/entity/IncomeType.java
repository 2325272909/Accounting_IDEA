package Accounting.entity;

import lombok.Data;

/**
 * @ClassName:IncomeType
 * @Description:收入类型
 * @Author:liumengying
 * @Date: 2023/3/17 17:10
 * Version v1.0
 */
@Data
public class IncomeType {
    private Long id;
    private Long userId;
    private String IncomeTypeName;  //收入类型名
}
