package Accounting.mapper;

import Accounting.entity.SpendingCredential;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpendingCredentialMapper extends BaseMapper<SpendingCredential> {
    List<String> getSpendingCredential(Long userId);
}
