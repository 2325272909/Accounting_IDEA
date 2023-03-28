package Accounting.controller;

import Accounting.common.BaseContext;
import Accounting.common.R;
import Accounting.entity.User;
import Accounting.service.IncomeTypeService;
import Accounting.service.SpendingCredentialService;
import Accounting.service.SpendingTypeService;
import Accounting.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName:UserController
 * @Description:相应前端用户请求类
 * @Author:liumengying
 * @Date: 2023/3/5 10:03
 * Version v1.0
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpendingTypeService spendingTypeService;

    @Autowired
    private SpendingCredentialService spendingCredentialService;

    @Autowired
    private IncomeTypeService incomeTypeService;

    /**
     * 用户登录类
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody User user, HttpSession session){
        // 查询数据库
        final LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());  //根据用户名查询，用户名逻辑上唯一
        final User user1 = userService.getOne(queryWrapper);
        // 没有根据账号找到数据
        if (user1 == null) {
            return R.error("账号不存在");
        }
        // 密码比对
        if (!user1.getUserPassword().equals(user.getUserPassword())) {
            return R.error("密码错误");
        }
        // 登陆成功,保存userName,userId
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("userId",user.getId());
        log.info("当前登录用户name:" + user.getUserName());
        //System.out.println("实验，查看session保存的name:" + (String) session.getAttribute("userName"));
        return R.success(user1);

    }

    /**
     * 用户注册类
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        User user1 = userService.getOne(queryWrapper);
        if(user1!=null){
            return R.error("用户名已存在！");
        }else{
            userService.save(user);
            return R.success("注册成功!");
        }

    }

    /**
     * 用户编辑类,用户名不能相同，修改基本信息
     */
    @PostMapping("/update")
    public R<User> update(@RequestBody User user,HttpSession session){
        log.info("进入用户更新函数："+ user);
        Long id = (Long)session.getAttribute("userId");
        String name = (String)session.getAttribute("userName");  //目前登录的用户名
        log.info("用户name:"+name);
//        if(id==null){
//            return R.error("用户未登录！");   //其实这是一个悖论，用户登陆后才会进到更新界面，测试使用
//        }
//        user.setId(id);

        //查看是否存在用户名相同的用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());  //根据name查询用户， z主要判断用户名是否重复
        User user1 = userService.getOne(queryWrapper);

        //如果有用户存在，且用户名不是目前登录的用户名，则表示已经有用户存在，
        if(user1!=null && !user.getUserName().equals(name)){
            return R.error("用户名已存在！");
        }
        userService.updateById(user);
        session.setAttribute("userName",user.getUserName());
        session.setAttribute("userId",user.getId());
        return R.success(user);
    }

    //修改密码
    @PostMapping("/updatePassword")
    public R<String> updatePassword(@RequestBody User user,HttpSession session){
        Long id = (Long)session.getAttribute("userId");
        log.info("进入修改密码函数，获取的session中userId:"+id);
        if(Objects.equals(id, user.getId())){
            userService.updateById(user);
            session.setAttribute("userId",id);
            return R.success("修改密码成功！");

        }else{
            return R.error("用户未登录！");
        }

    }

    //退出
    @PostMapping("/loginout")
    public R<String> Loginout(HttpServletRequest request){
        log.info(""+request.getSession().getAttribute("userId"));
        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("userId");
        return R.success("退出成功");
    }

    /**
     * 根据传递的参数不同，读取不同的数据
     * @param category
     * @return
     */
    @GetMapping("/item/list")
    public R<List<String>> ItemList(@RequestParam String category,HttpSession session){
        Long userId =  (Long)session.getAttribute("userId");
        Long userId1 = BaseContext.getCurrentId();
        log.info("进入category:"+category);
        log.info("userId:"+userId+"  userId1:"+userId1);
//        userId= Long.valueOf("1");
       if(Objects.equals(category, "消费类型")){
         return R.success(spendingTypeService.getSpendingTypes(userId));
       }else if(Objects.equals(category, "消费凭证")){
           return R.success(spendingCredentialService.getSpendingCredential(userId));
       }else{
           //收入类型
           return R.success(incomeTypeService.getIncomeTypes(userId));
       }

    }

}
