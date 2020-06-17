import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class ShiroAuthTest {

    /**
     *Realm接口的简单实现，
     *配置的用户帐户和角色来支持身份验证和授权
     */
    SimpleAccountRealm simpleAccountRealm ;

    @Before
    public void setInfo(){
        //初始数据。 从数据库查
        simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("admin","123456","1");
    }


    /**
     * shiro认证
     */
    @Test
    public void TestShiroAuth(){

        //1.创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager(simpleAccountRealm);

        //2.设置securityManager 环境
        SecurityUtils.setSecurityManager(securityManager);


        //3.从securityManager 获取用户
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");

        subject.login(token);

        if(subject.isAuthenticated()){
            System.out.println("----登录成功----");

            boolean flag = subject.hasRole("1");

            System.out.println("------hasRole flag---" +flag );

            subject.checkRole("9");



        }else{
            System.out.println("----登录失败----");

        }







    }



}
