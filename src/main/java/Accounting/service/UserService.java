package Accounting.service;

import Accounting.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService extends IService<User> {
}
