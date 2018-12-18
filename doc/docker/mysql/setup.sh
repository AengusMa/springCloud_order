#!/bin/bash

set -e

#查看mysql服务的状态，方便调试，这条语句可以删除
echo `service mysql status`

echo '1.start mysql....'
#启动mysql
service mysql start
sleep 3
echo `service mysql status`

echo '2.import data....'
#导入数据
mysql < /mysql/schema.sql
echo '3.import data success....'

sleep 3
echo `service mysql status`

#重新设置mysql密码
echo '4.modify password....'
mysql < /mysql/privileges.sql
echo '5.modify password success....'

#sleep 3
echo `service mysql status`
echo `mysql start success`

tail -f /dev/null