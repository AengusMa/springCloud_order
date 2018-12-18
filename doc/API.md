
### 创建订单
http://localhost:8080/order/create

POST /order/create  
{
    "name":"张三",
    "phone":"18868822111",
    "address":"慕课网总部",
    "openid":"ew3euwhd7sjw9diwkq",
    "items":[
        {
            " productId":"1423113435324",
            " productQuantity":2
        }
    ]
}