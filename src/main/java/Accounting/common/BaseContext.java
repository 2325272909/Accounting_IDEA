package Accounting.common;

/**
 * @ClassName:BaseContex
 * @Description:基于基于ThreadLocal封装的工具类，用户保存和获取当前登录用户的ID
 * @Author:liumengying
 * @Date: 2023/3/24 20:21
 * Version v1.0
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    /**
     * 设置员工ID,并于当前处理线程绑定
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取员工ID，获取的是与当前线程绑定的用户ID
     * @return
     */
    public static  Long getCurrentId(){
        return threadLocal.get();
    }
}
