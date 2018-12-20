docker build -t test/nginx .
运行（不挂载共享目录）
docker run -d -p 80:80 --name test-nginx test/nginx


docker run -d -p 8080:80 --name test-nginx -v $PWD/website:/var/www/html/website test/nginx nginx

ro(只读)或rw(可写)来指定目录的读写状态
docker run -d -p 8080:80 --name test-nginx -v /e/workspace/docker/dockerbook/5/smaple/website:/var/www/html/website test/nginx nginx

/e/workspace/docker/dockerbook/5/smaple/website
#随机端口
docker run -d -P test/nginx

docker ps -a 

docker build --rm --tag centos_nginx:centos7 .

不挂载文件:
docker run -it -d -p 80:80 centos_nginx:centos7
 
挂载文件:
docker run \
--name centos_nginx \
-d -p 80:80 \
-v /home/nginx/html:/usr/share/nginx/html \
-v /home/nginx/log:/var/log/nginx \
-v /home/nginx/nginx.conf:/usr/local/nginx/nginx.conf:ro \
-v /home/nginx/conf.d:/usr/local/nginx/conf.d \
nginx

