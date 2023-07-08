package Accounting.controller;

import Accounting.common.BaseContext;
import Accounting.common.R;
import Accounting.entity.IncomeType;
import Accounting.entity.SpendingCredential;
import Accounting.entity.SpendingType;
import Accounting.entity.User;
import Accounting.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private SpendingService spendingService;
    @Autowired
    private IncomeService incomeService;

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
        log.info("登陆函数,当前登录用户name:" + user.getUserName());
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
    public R<User> update(@RequestBody User user){
        log.info("进入用户更新函数："+ user);

        //查看是否存在用户名相同的用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());  //根据name查询用户， z主要判断用户名是否重复
        User user1 = userService.getOne(queryWrapper);

        //如果有用户存在，且用户名不是目前登录的用户名，则表示已经有用户存在，
        if(user1!=null && !user.getUserName().equals(user1.getUserName())){
            return R.error("用户名已存在！");
        }
        userService.updateById(user);
        return R.success(user);
    }

    //修改密码
    @PostMapping("/updatePassword")
    public R<String> updatePassword(@RequestBody User user,HttpSession session){
        log.info("进入修改密码函数，user:"+user);
        userService.updateById(user);
        return R.success("修改密码成功！");

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
    public R<List<String>> ItemList(@RequestParam String category,@RequestParam Long userId){
        log.info("进入category:"+category+" userId:"+userId);
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

    /**
     * 删除子类
     * @param bodyParams
     * @return
     */
    @DeleteMapping("/deleteItem")
    public R<String> deleteItem(@RequestBody Map<String,String> bodyParams){
        Long userId=Long.valueOf(bodyParams.get("userId"));
        String category = bodyParams.get("category");
        String itemName = bodyParams.get("itemName");
        log.info("接收的参数：userId="+ userId + " category="+category+" itemName="+itemName);
        if(category.equals("消费类型")){
            spendingTypeService.deleteSpendingType(userId,itemName);
            return R.success("删除 消费类型子类 成功！");
        }else if(category.equals("消费凭证")){
            spendingCredentialService.deleteSpendingCredential(userId,itemName);
            return R.success("删除 消费凭证子类 成功！");
        }else{
            incomeTypeService.deleteItem(userId,itemName);
            return R.success("删除 收入类型子类 成功！");
        }
    }

    /**
     * 添加子类
     */
    @PostMapping("addItem")
    public R<String> addItem(@RequestBody Map<String,String> bodyParams) {
        Long userId = Long.valueOf(bodyParams.get("userId"));
        Long id = Long.valueOf(bodyParams.get("id"));
        String category = bodyParams.get("category");
        String itemName = bodyParams.get("itemName");

        if (category.equals("消费类型")) {
            SpendingType spendingType = new SpendingType();
            LambdaQueryWrapper<SpendingType> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SpendingType::getUserId, userId);
            queryWrapper.eq(SpendingType::getSpendingTypeName, itemName);
            SpendingType temp = spendingTypeService.getOne(queryWrapper);

            if (temp != null) {
                return R.error("已存在该分类！");
            } else {
                spendingType.setId(id);
                spendingType.setUserId(userId);
                spendingType.setSpendingTypeName(itemName);
                spendingTypeService.save(spendingType);
                return R.success("添加成功");
            }

        }
        else if (category.equals("消费凭证")) {
            SpendingCredential spendingCredential = new SpendingCredential();
            LambdaQueryWrapper<SpendingCredential> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SpendingCredential::getUserId, userId);
            queryWrapper.eq(SpendingCredential::getSpendingCredentialName, itemName);
            SpendingCredential temp = spendingCredentialService.getOne(queryWrapper);

            if (temp != null) {
                return R.error("已存在该分类！");
            } else {
                spendingCredential.setId(id);
                spendingCredential.setUserId(userId);
                spendingCredential.setSpendingCredentialName(itemName);
                spendingCredentialService.save(spendingCredential);
                return R.success("添加成功");
            }
        }
        else {
            //收入类型
            IncomeType incomeType = new IncomeType();
            LambdaQueryWrapper<IncomeType> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(IncomeType::getUserId, userId);
            queryWrapper.eq(IncomeType::getIncomeTypeName, itemName);
            IncomeType temp = incomeTypeService.getOne(queryWrapper);

            if (temp != null) {
                return R.error("已存在该分类！");
            } else {
                incomeType.setId(id);
                incomeType.setUserId(userId);
                incomeType.setIncomeTypeName(itemName);
                incomeTypeService.save(incomeType);
                return R.success("添加成功");
            }

        }

    }


    /**
     * 统计消费、收入、余额（按月）
     * @param year
     * @param month
     * @param userId
     * @return
     */
    @GetMapping("/countMonthMoney")
    public R<Map<String,String>> countMonthMoney(@RequestParam String year,@RequestParam String month,@RequestParam String userId) {

        log.info("进入按月统计金额函数:" + year + "年" + month + "月" + userId);
        Long id = Long.valueOf(userId);
        BigDecimal spending_money = spendingService.countSpendingYearMonthMoney(year, month, id);
        BigDecimal income_money = incomeService.countIncomeYearMonthMoney(year, month, id);

        if (spending_money == null) {
            spending_money = new BigDecimal(0);
        }
        if (income_money == null) {
            income_money = new BigDecimal(0);
        }
        BigDecimal balance = income_money.subtract(spending_money);
        Map<String, String> map = new HashMap<>();
        map.put("spending", String.valueOf(spending_money));
        map.put("income", String.valueOf(income_money));
        map.put("balance", String.valueOf(balance));
        return R.success(map);
    }
}
