package Accounting.mapper;

import Accounting.entity.SpendingCredential;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface SpendingCredentialMapper extends BaseMapper<SpendingCredential> {
    List<String> getSpendingCredential(Long userId);
    boolean deleteSpendingCredential(@Param("userId") Long userId, @Param("spendingCredentialName") String spendingCredentialName);

}
