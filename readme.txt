jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8
在我的电脑上使用上述的url配置会报SSL的权限错误以及页面会报500的错误

正确的配置如下
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild
就不会出现上述的错误，能正确的访问到我们所需的资源