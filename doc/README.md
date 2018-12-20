# 文档

## 配置

### 数据库：  

- 创建docker容器命令：
        - docker pull mysql:5.6
        - docker run -p 3306:3306 --name order_mysql -v $PWD/docker/conf:/etc/mysql/conf.d -v $PWD/docker/logs:/logs -v $PWD/docker/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
        - docker run -p 3306:3306 --name order_mysql   -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
- 创建数据库命令：
        - CREATE DATABASE `springcloud_order` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
- 数据库编码问题：
        - 显示数据库编码：
  
show variables like "%character%";

        - 修改编码：  
set character_set_client = utf8;
set character_set_server = utf8;
set character_set_connection = utf8;
set character_set_database = utf8;
set character_set_results = utf8;
set collation_connection = utf8_general_ci;
set collation_database = utf8_general_ci;
set collation_server = utf8_general_ci;

    - 修改数据库编码：  
  
alter database app_relation character set utf8;
如果还有乱码：在建表的时候添加：default charset utf8 collate utf8_unicode_ci;

## 部署运行：

- 首先打包：
  
mvn clean package

- 然后在项目根目录下运行docker命令：  

docker run -p 8080:8080 -t order/product .
docker run -p 8761:8761 -t order/eureka .

## 问题

使用Feign的时候需要在启动类上添加@EnableFeignClients(basePackages = "com.mwl.product.client")，如果是跨项目，则basePackages是必须的否则会报错“not Autowired”

短轮询：利用ajax定期向服务器请求，无论数据是否更新立马返回数据，高并发情况下可能会对服务器和带宽造成压力；
长轮询：利用comet不断向服务器发起请求，服务器将请求暂时挂起，直到有新的数据的时候才返回，相对短轮询减少了请求次数；
SSE：服务端推送（Server Send Event），在客户端发起一次请求后会保持该连接，服务器端基于该连接持续向客户端发送数据，从HTML5开始加入。
Websocket：这是也是一种保持连接的技术，并且是双向的，从HTML5开始加入，并非完全基于HTTP，适合于频繁和较大流量的双向通讯场景

## docker

停止所有的container（容器），这样才能够删除其中的images：
docker stop $(docker ps -a -q) 或者 docker stop $(docker ps -aq)
要删除所有container（容器）
docker rm $(docker ps -a -q) 或者 docker rm $(docker ps -aq)
删除全部image（镜像）的话
docker rmi $(docker images -q)
强制删除全部image的话
docker rmi -f $(docker images -q)