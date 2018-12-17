# 文档
各种文档  
数据库：  
docker命令：
docker run -p 3306:3306 --name mymysql -v $PWD/docker/conf:/etc/mysql/conf.d -v $PWD/docker/logs:/logs -v $PWD/docker/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6

显示数据库编码：
show variables like "%character%";
修改编码：
set character_set_client = utf8;
set character_set_server = utf8;
set character_set_connection = utf8;
set character_set_database = utf8;
set character_set_results = utf8;
set collation_connection = utf8_general_ci;
set collation_database = utf8_general_ci;
set collation_server = utf8_general_ci;

alter database app_relation character set utf8; 

如果还有乱码：在建表的时候添加：default charset utf8 collate utf8_unicode_ci;

JSONView插件