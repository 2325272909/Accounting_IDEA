package Accounting.service.Impl;

import Accounting.entity.User;
import Accounting.mapper.UserMapper;
import Accounting.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName:UserImpl
 * @Description:继承接口类
 * @Author:liumengying
 * @Date: 2023/3/5 10:09
 * Version v1.0
 */
@Service
public class UserImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
