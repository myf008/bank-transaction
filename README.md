# 银行交易管理系统

## 功能
- 创建、删除、修改交易
- 分页查询交易列表
- 缓存支持（内存缓存）
- 异常处理（重复流水ID、无效操作）

## 依赖说明
- **Lombok**: 简化实体类代码
- **Spring Cache**: 实现缓存机制
- **Spring Web**: RESTful API支持

## 运行环境
- Java 11
- Maven
- Docker (optional)

## 打包运行
1. 使用Maven构建：`mvn clean package`
2. 运行JAR文件：`java -jar target/*.jar`

## 容器运行
1. 克隆仓库后，执行 `mvn clean install` 构建项目。
2. 建议jdk基础镜像先本地下载好
3. 运行 `docker-compose up` 启动容器化服务。
或者：
1. docker build -t app . 
docker run -p 8080:8080 app

## API
1. POST /bank/transactions - 创建交易
2. GET /bank/transactions?page=0&size=10 - 分页获取交易
3. PUT /bank/transactions/{id} - 更新交易
4. DELETE /bank/transactions/{id} - 删除交易

## API测试
使用工具如 `curl` 或 Postman 测试API：
   curl -X POST -H "Content-Type: application/json" -d '{"statementId":"1","amount":100,"type":"DEPOSIT","description":"Salary"}' http://localhost:8080/bank/transactions

## 压力测试
可以使用JMeter或Gatling工具，通过模拟并发请求来验证性能表现。
