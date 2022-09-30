package cn.ltpcloud.day03.tx;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/10:34
 * @Description:
 */

/*
 * 业务层
 * */
public interface AccountService {
    // 转账业务
    /**
     * @param srcID 转出账户
     * @param destID 转入账户
     * */
    boolean transfer(Integer srcID,Integer destID,Integer amount);
}
