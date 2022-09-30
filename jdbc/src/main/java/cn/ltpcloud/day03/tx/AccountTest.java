package cn.ltpcloud.day03.tx;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/10:52
 * @Description:
 */
public class AccountTest {
    public static void main(String[] args) {
        AccountDao dao = new AccountDaoImpl();
        AccountService service = new AccountServiceImpl(dao);

        service.transfer(1, 2, 100);
    }
}