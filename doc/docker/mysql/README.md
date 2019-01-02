## mysql容器
### 部署
- 部署
docker build -t test/mysql .  
- 查看是否部署成功
docker images  
## 运行
- 映射到13306端口
docker run --name test-mysql -d -p 3306:3306 test/mysql 
- 查看是否运行成功
    - 查看容器是否运行
    docker ps 
    - 查看docker日志：
    docker logs [容器名/容器ID]
    - 进入容器查看数据表是否建立成功
    docker exec -it 588a13ee1ebc  /bin/bash
    mysql -udocker -p12346
    use docker_mysql;
    select * from user;
## 注意
setup.sh文件的行分割符设置为“LF”


