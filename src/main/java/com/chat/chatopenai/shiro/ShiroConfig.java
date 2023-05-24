package com.chat.chatopenai.shiro;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            DefaultWebSecurityManager securityManager, ShiroFilterChainDefinition chainDefinition) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 必须的设置。我们自定义的Realm此时已经被设置到securityManager中了
        factoryBean.setSecurityManager(securityManager);

        // 注册我们写的过滤器
        Map<String, Filter> filters = factoryBean.getFilters();
        filters.put("jwtAuth", new JwtFilter());

        factoryBean.setFilters(filters);

        // 设置请求的过滤规则。其中过滤规则中用到了我们注册的过滤器：jwtAuth
        factoryBean.setFilterChainDefinitionMap(chainDefinition.getFilterChainMap());

        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Authenticator authenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 所有的Realm都用这个全局缓存。不生效，需要在realm中设置缓存。原因暂时搞不懂。
        // securityManager.setCacheManager(new EhCacheManager());
        securityManager.setAuthenticator(authenticator);
        return securityManager;
    }

    /**
     * 设置请求的过滤规则
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // login不做认证，noSessionCreation的作用是用户在操作session时会抛异常
        chainDefinition.addPathDefinition("/auth/login", "noSessionCreation,anon");

        // 注意第2个参数的"jwtAuth"需要与上面的 filters.put("jwtAuth", new JwtAuthenticatingFilter()); 一致
        // 做用户认证，permissive参数的作用是当token无效时也允许请求访问，不会返回鉴权未通过的错误
        chainDefinition.addPathDefinition("/auth/logout", "noSessionCreation,jwtAuth[permissive]");
        // 默认进行用户鉴权
        chainDefinition.addPathDefinition("/**", "noSessionCreation,jwtAuth");
        return chainDefinition;
    }

    /**
     * 初始化Authenticator，将我们需要的Realm设置进去
     * Shiro会将Authenticator设置到SecurityManager里面
     */
    @Bean
    public Authenticator authenticator(@Qualifier("customerRealm") Realm customerRealm, @Qualifier("jwtRealm") Realm jwtRealm) {

        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(customerRealm, jwtRealm));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }


    /**
     * 自动配置类中有同名组件，如果只写@Bean，会出现歧义
     * @param ehCacheManager
     * @return
     */
    @Bean("customerRealm")
    public Realm loginRealm(EhCacheManager ehCacheManager) {
        CustomerRealm customerRealm = new CustomerRealm();

        // AuthenticatingRealm里面的isAuthenticationCachingEnabled()
        customerRealm.setCacheManager(ehCacheManager);
        // 这句话不能少！！！
        customerRealm.setCachingEnabled(true);
        // 认证缓存
        customerRealm.setAuthenticationCachingEnabled(true);
        // 授权缓存
        customerRealm.setAuthorizationCachingEnabled(true);

        return customerRealm;
    }

    @Bean("jwtRealm")
    public Realm jwtRealm(EhCacheManager ehCacheManager) {
        JwtRealm jwtRealm = new JwtRealm();

        jwtRealm.setCacheManager(ehCacheManager);
        // 这句话不能少！！！
        jwtRealm.setCachingEnabled(true);
        // 认证缓存
        jwtRealm.setAuthenticationCachingEnabled(true);
        // 授权缓存
        jwtRealm.setAuthorizationCachingEnabled(true);

        return jwtRealm;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，
     * 如果要完全禁用，要配合上过滤规则的noSessionCreation的Filter来实现
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * shiro的全局缓存管理器
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        return new EhCacheManager();
    }

}
