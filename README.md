# springsecurity学习笔记  
### 参考文章  
SpringSide 3 中的安全框架  
详细介绍springsecurity早期版本的设计思路，对理解springsecurity有很大帮助  
http://www.blogjava.net/youxia/archive/2008/12/07/244883.html  
Spring boot+Spring Security 4配置整合实例  
https://blog.csdn.net/code__code/article/details/53885510  
springBoot+springSecurity 数据库动态管理用户、角色、权限（二）  
https://blog.csdn.net/u012373815/article/details/54633046  
### 图示参数  
![spring security的简单原理](other/image/spring security的简单原理.png)  
![springsecurity流程图](other/image/springsecurity流程图.png)  
### 版本推进  
说明：版本的推进代码请查看代码历史版本  
##### 最简单的demo版本  
该版本未配置用户自定义账户密码。账户密码为user/控制台随机账户密码  
![默认的密码](other/image/默认的密码.png)  
访问首页的时候不需要认证，访问欢迎页需要security认证  
##### 内存中实现登陆的demo版本  
有配置内存中的认证,可以配置多个用户，但是没有角色区分,认证账号密码是admin/123456  
访问首页的时候不需要认证，访问欢迎页需要security认证  
##### 内存中实现登陆的demo版本：增加角色控制  
增加了角色控制后，没登陆只能看到没有权限的页面，登陆的还会通过权限进行进一步的判定，角色不通过会被禁止访问  
admin用户有admin权限，账户/密码/权限分别是admin/123456/ADMIN  
zhangsan用户有user权限，账户/密码/权限分别是zhangsan/123456/USER  
如果有角色认证的话，需要ADMIN权限的地方张三是无法进入的  
![角色不相同的被禁止](other/image/角色不相同的被禁止.png)  
<font color=red>说明：以上都是简单版本，不适用于生产环境，但是可以拿来在小组中使用，仅小组成员通过</font>  
##### 数据库实现登陆的demo版本：增加角色控制  
实现从数据库中读取用户和角色，控制不同角色的用户的访问权限  
在数据库中，zhangsan是user用户，admin是admin角色  
未登录时能访问的页面包括："/","/login","/logout"  
登陆时能访问的页面包括："/hello"  
需要admin角色才能访问的页面包括："/roleAuth"  
# 接口  
访问首页{"","/","","$String"}  
访问欢迎页{"","/hello","","$String"}  
访问登陆页{"","/login","",""}  
访问注销页{"","/logout","","$String"}  
访问admin权限页{"","/roleAuth","","$String"}  
# 表结构  
user用户表  
![user](other/image/user.png)  
role角色表  
![role](other/image/role.png)  
user_role用户角色表  
![user_role](other/image/user_role.png)  
# 其他  
maven报错  
由于平时经常会去git上面下载一些demo来做参考，常常会通过maven仓库下载jar包，之前配置的阿里云的仓库，但是可能是因为公司的原因，导致这边的网速特别慢  
我手动访问了http://maven.aliyun.com/nexus/content/groups/public/ 仓库地址，发现需要好多秒才能打开，maven下载有时间判定，  
如果超过一定时间就不会下载了，所以我在网上找了几个maven地址，挑几个测试了下，修改后远程仓库后，发现瞬间快了很多，下面这些是地址，大家可以看着修改  
Jcenter http://jcenter.bintray.com/  
Jboss http://repository.jboss.org/nexus/content/groups/public/  
Maven Central http://repo2.maven.org/maven2/  
Ibiblio http://mirrors.ibiblio.org/pub/mirrors/maven2  
UK Maven http://uk.maven.org/maven2/  
OSCHINA http://maven.oschina.net/  
aliyun http://maven.aliyun.com/nexus/content/groups/public/  
maven报错的话，手动去找对应的jar包，删除掉所在的目录，然后mavenupdate一下就ok了  
![maven报错修复1](other/image/maven报错修复1.png)  
![maven报错修复2](other/image/maven报错修复2.png)  