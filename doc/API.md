
###  订单服务
- 创建订单
POST /order/create  
请求参数
{
    "name":"张三",
    "phone":"18868822111",
    "address":"地球",
    "openid":"ew3euwhd7sjw9diwkq",
    "items":[
        {
            " productId":"1423113435324",
            " productQuantity":2
        }
    ]
}
- 测试服务间通信
GET getProductMsg
### 商品服务
- 测试服务间通信
GET /msg
- 查询所有商品
GET /product/list
- 查询商品列表
POST /product/listForOrder
- 扣减库存
POST /product/decreaseStock