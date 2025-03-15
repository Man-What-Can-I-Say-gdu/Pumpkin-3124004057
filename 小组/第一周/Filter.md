# Filter

过滤器，javaweb三大组件之一（servlet、filter、listener）

过滤器可以把对资源的请求拦截从而实现一些特殊的功能（通用操作：权限控制，统一编码处理，敏感字符处理）

## filter使用

1.定义类，实现Filter接口并重写其所有方法（导包导javax.servlet下的包）

public class FilterDemo implements Filter{

​	public void init(FilterConfig filterrConfig)//创建过滤器

​	public void doFilter(ServletRequest request)//主体逻辑

​	public void destroy(){  }//销毁过滤器

}

2.配置Filter拦截资源的路径：在类上定义@WebFilter注解

@WebFilter（”/*“）

public class FilterDemo implements Filter{   ...

3.在doFilter方法中输出一句话并放行

public void doFilter（ServletRequest request，...）{

​	/...代码需求.../

​	chain.doFilter(request,response);//放行指令

}

### filter执行流程

放行前逻辑（上文提及）

放行后逻辑：放行后访问对应资源，资源访问完成后会回到filter中，回到filter中后执行放行后逻辑

放行前-》放行-》资源访问-》放行后逻辑

### filter使用细节

#### 过滤器拦截路径配置

![1742013401345](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742013401345.png)

#### 过滤器链

放行前逻辑：先一次执行各种过滤器逻辑，在获取资源

放行后逻辑：逆着放行前逻辑执行

注解配置的Filter，优先按照过滤器类名（字符串）的自然排序

### 