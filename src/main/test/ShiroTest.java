import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class ShiroTest {

    /**
     *Realm接口的简单实现，
     *配置的用户帐户和角色来支持身份验证和授权
     */
    SimpleAccountRealm simpleAccountRealm ;

    @Before
    public void setInfo(){
        //用户名和密码添加到shiro中

        simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("admin","123456");  // 数据库里面用户名密码ShiroTest
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

        //3.获取用户
        Subject subject = SecurityUtils.getSubject();

        //前端的用户名密码
//       AuthenticationToken token = ;
        UsernamePasswordToken token = new UsernamePasswordToken("admi","123456");


        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            System.out.println("用户名密码不正确");

        }catch (UnknownAccountException e){
            System.out.println("账号不存在");

        }catch (AuthenticationException e){
            System.out.println("登录失败");

        }

        boolean flag =  subject.isAuthenticated();

        System.out.println("登录flag："+ flag); //TRUE


        subject.logout();

        flag =  subject.isAuthenticated();
        System.out.println("登录flag2222："+ flag); //false



    }



}
