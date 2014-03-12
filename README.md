Tuning Fork
========

java pipe programming framework

[![Build Status](https://travis-ci.org/biscuit-team/TuningFork.png?branch=master)](https://travis-ci.org/biscuit-team/TuningFork)

## Usage ##

write a pipe script `grab.properties`
```properties
	url=http://www.renrendai.com/lend/loanList!json.action
	pages=@url|Get|JsonToXml|XPath root/data/totalPage/text()
	listUrls=@(url)?pageIndex={1-2}|StringGenerator
	jsonBodys=@listUrls|Get|JsonToXml|XPath root/data/loans/title/text()
```

use `PipeInterpreter` to evaluate this file

```java
	String script = IOUtils.toString(PipeInterpreterTest.class.getResourceAsStream(TEST_FILE_PATH));
	PipeInterpreter interpreter = new PipeInterpreterFactory().getPipeInterpreter();
	interpreter.evaluate(script);

	System.out.println(interpreter.getVariable("pages")[0]);

	System.out
			.println(Arrays.toString(interpreter.getVariable("listUrls")));
	System.out
			.println(Arrays.toString(interpreter.getVariable("jsonBodys")));
```

a possible output：

```
	22

    [http://www.renrendai.com/lend/loanList!json.action?pageIndex=1, http://www.renrendai.com/lend/loanList!json.action?pageIndex=2, http://www.renrendai.com/lend/loanList!json.action?pageIndex=3, http://www.renrendai.com/lend/loanList!json.action?pageIndex=4, http://www.renrendai.com/lend/loanList!json.action?pageIndex=5, http://www.renrendai.com/lend/loanList!json.action?pageIndex=6, http://www.renrendai.com/lend/loanList!json.action?pageIndex=7, http://www.renrendai.com/lend/loanList!json.action?pageIndex=8, http://www.renrendai.com/lend/loanList!json.action?pageIndex=10, http://www.renrendai.com/lend/loanList!json.action?pageIndex=11, http://www.renrendai.com/lend/loanList!json.action?pageIndex=12, http://www.renrendai.com/lend/loanList!json.action?pageIndex=13, http://www.renrendai.com/lend/loanList!json.action?pageIndex=9, http://www.renrendai.com/lend/loanList!json.action?pageIndex=14, http://www.renrendai.com/lend/loanList!json.action?pageIndex=15, http://www.renrendai.com/lend/loanList!json.action?pageIndex=16, http://www.renrendai.com/lend/loanList!json.action?pageIndex=18, http://www.renrendai.com/lend/loanList!json.action?pageIndex=17, http://www.renrendai.com/lend/loanList!json.action?pageIndex=19, http://www.renrendai.com/lend/loanList!json.action?pageIndex=20, http://www.renrendai.com/lend/loanList!json.action?pageIndex=21, http://www.renrendai.com/lend/loanList!json.action?pageIndex=22]

    [资金周转, 购买货物, 扩大经营, 资金周转, 日常生活消费, 资金周转, 客户贷款目的为资金周转, 值班员，装修, 用于资金周转, 该客户借款目的为购买原材料, 装修房子, 客户借款用于购车, 装修, 借款人借款目的为资金周转, 公司员工资金周转，保证按时还款，请支持！, 购买货物/原材料/设备, 购买货物, 进货, 进货（药品）, 购车, 企业扩大经营, 资金周转, 扩大生产, 店铺备货短期周转, 进材料, 扩大生产规模, 房屋装修, 资金周转, 家庭装修, 装修, 房屋装修, 购买货物, 资金周转, 购买私家车, 装修, 装修, 资金周转, 投资创业, 资金周转, 开发林木, 英才贷客户房屋装修, 法人，扩充店面, 资金周转, 客户此次贷款目的为装修房子, 购买股份, 资金周转, 扩大经营, 资金周转, 服装店进货, 公司法人扩大经营，保证按时还款，请支持！, 资金周转, 某兽药经销处法人，扩大经营, 用于投资生意，保证按时还款，请大家支持。, 日常生活消费, 购买原材料, 日常生活消费, 购车, 资金周转, 装修, 尚佳美厂长周转，保证按时还款，请支持！, 购买原材料, 给儿子装修房子, 房屋装修, 扩大经营, 客户借款用于扩大业务，请大家支持, 公司职员装修房子, 房屋装修, 装修, 事业单位员工想贷款投资经营望支持谢谢, 用于资金周转，保证按时还款，请大家支持。, 购车, 用于投资生意，保证按时还款，请大家支持。, 客户贷款想要装修房子，希望大家支持, 私企职员用于房屋装修，望支持，谢谢, 资金周转, 装修, 扩大生产经营, 资金周转, 爱人生意周转, 购车, 资金周转, 资金周转, 扩大经营, 资金周转, 资金周转, 资金周转, 资金周转, 备用金借款, 想与人合伙开个快餐厅, 夏季备货资金周转, 购房首付, 扩大生产, 学校教师个人购车，保证按时还款请大家支持, 私企经理想买车望支持谢谢, 公司法人用于进货，保证按时还款请大家支持, 该借款人用于房屋装修，保证按时还款，谢谢, 资金周转, 装修, 资金周转, 私企职员用于做生意，望支持，谢谢, 投资经营, 购买货物, 房屋装修, 装修, 资金周转, 资金周转, 装修房子，请大家支持！, 该借款人用于日常生活消费，保证按时还款！, 购房, 资金周转, 扩大经营, 保证按时还款, 装修款周转, 开发教育服务平台继续借款, 资金周转, 投资创业, 资金周转, 投资创业, 资金周转, 客户用于日常生活消费, 国企员工现住房装修, 私营业主资金周转, 投资开餐馆, 购买货物, 装修, 该借款人用于购买房屋及装修，保证按时还款, 资金周转, 购车, 航空公司地勤用于装修，保证按时还款。, 购车, 装修, 购车, 购买设备, 医院医生装修房子，保证按时还款请大家支持, 投资创业, 资金周转, 借款进货, 资金周转, 资金周转, 资金周转, 资金周转, 新房装修, 进货, 公司职员资金周转，保证按时还款，请支持！, 政府机关员工，进修提升自己。, 成都茶楼老板用于装修，望支持！, 资金周转, 装修, 购车, 日常生活消费, 购买货物/原材料/设备, 资金周转, 资金周转, 公司老板扩大生产，保证按时还款，请支持！, 资金周转, 此借款人此次借款用于购车，某大药房店长, 扩大经营, 扩大经营, 锦上添花！再创辉煌, 资金周转, 借款进货, 投资创业, 资金周转, 借款进货, 天猫进货，信誉良好, 短期周转, 淘宝店铺经营资金 短期周转, 消费贷款, 婚礼筹备, 借款, 家纺专卖店借款进货, 日常消费, 装修资金周转, 扩大经营, 购车, 春夏花费超支，借钱日常消费, 买车, 资金周转, 个体法人用于资金周转，望支持，谢谢, 扩大生产, 此次借款用于扩大经营，某传播公司法人, 资金周转, 公司职员装修, 装修房子，请大家支持！, 资金周转, 资金周转, 装修, 大学老师房屋装修，望支持！, 扩大经营, 资金周转, 某设备有限公司法人，购买货物, 此次借款用于扩大经营，化妆品商行经理, 此次借款用于资金周转，某食品厂经理, 装修, 装修, 日常生活消费, 装修, 资金周转, 某私营业主资金周转, 购买货物, 客户此次借款目的为资金周转, 装修, 餐馆老板扩大经营，望支持！, 房屋装修, 房屋装修, 装修, 资金周转, 公司装修, 资金周转, 资金周转, 装修, 资金周转, 茶行法人扩大经营，保证按时还款，请支持！, 某电梯集团股份有限公司组长，装修, 美容院老板扩大经营，望支持！, 调料行老板，扩大店面进货！, 购车, 周转, 扩大生产经营, 装修, 装修, 资金周转, 装修房子, 公司法人用于进货，保证按时还款请大家支持, 购车, 装修, 扩大生产, 购车, 资金周转, 资金周转, 客户资金周转，保证按时还款，请支持, 科长，资金周转, 该借款人用于房屋装修，保证按时还款，谢谢, 工程垫资, 扩大生产, 扩大经营, 装修, 资金周转, 资金周转, 资金周转, 国企员工想投资经营望支持谢谢, 公司法人资金周转, 资金周转, 实体公司，资金周转，求5万, 装修, 资金周转, 资金周转, 资金周转, 资金周转, 装修, 购买货物, 装修, 装修, 个体法人用于资金周转，望支持，谢谢, 购车，请大家支持！, 资金周转, 扩大经营规模, 装修, 资金周转, 扩大经营, 借款进货, 资金周转, 投资创业, 装修借款, 资金周转, 资金周转, 支付加工费, 购买货物/原材料/设备, 建筑行业法人用于资金周转，保证按时还款。, 资金周转, 扩大生产, 生意自己周转, 食品品牌代理资金周转借款, 淘宝五钻卖家旺季周转再次借款, 消费借款, 资金周转, 资金周转, 用于资金周转，保证按时还款，请大家支持。, 某路政管理局监察干事，购房, 扩大生产, 日常生活消费, 装修, 装修, 资金周转, 购车, 买车, 代理订房业务需要资金周转, 提高生活质量, 和朋友合伙做医疗器械代理, 第5次借款，保证偿还。, 借款进货, 日常生活消费, 资金周转, 资金周转, 资金周转, 资金周转, 装修, 扩大经营, 资金周转, 资金周转, 房屋装修, 装修房子, 私企员工想装修房子望支持谢谢, 建材商老板，盖库房囤货。, 购买原材料, 资金周转, 装修, 用于公司周转, 投资工程, 装修房子, 资金周转, 用于购买车辆，保证按时还款，请大家支持。, 资金周转, 装修, 借款人借款为装修店面, 资金周转, 合伙进货, 某加工厂销售经理，资金周转, 私企店长装修房子望支持谢谢, 购房, 资金周转, 资金周转, 房屋装修, 公司员工投资生意，保证按时还款，请支持！, 资金周转, 投资创业, 国企职员想装修房子望支持谢谢, 资金周转, 装修, 装修急用款, 家庭添置电器, 资金周转, 该借款人借款目的为购车, 装修, 装修, 装修, 借款人借款用于购入货物，保证按时还款！, 资金周转, 南新经理投资生意，保证按时还款，请支持！, 装修, 商用, 资金周转, 资金周转, 房屋装修, 公司 项目短期周转 多次还款, 用于业务拓展，资金周转, 资金周转, 资金周转, 购买东西, 客户借款用于买车，请大家支持, 此次借款用于购买货物，某学院工会主席, 此次借款用于收粮，个体老板, 用于投资生意，保证按时还款，请大家支持。, 投资经营, 饭店装修, 购买房屋, 借款用于装修，保证按时还款，请支持, 某政府机关科员资金周转, 装修, 该客户借款目的为装修, 资金周转, 装修, 购买物资原材料, 购车, 客户借款用于资金周转, 扩大生产, 购车, 资金周转, 资金周转, 资金周转, 经理，资金周转, 装修, 客户借款用于购车, 装修, 设备操作员贷款用于装修，保证按时还款。, 装修, 装修, 扩大生产经营, 装修, 公司法人资金周转，保证按时还款，请支持, 资金周转, 大额消费买车, 日常生活消费, 客户用于大额消费装修, 总经理，资金周转, 借款进货, 资金周转, 资金周转, 投资创业, 资金周转, 投资创业, 借款进货, 公司员工购车，保证按时还款请大家支持, 投资销售代理, 借款人借款用于房屋装修，保证按时还款！, 教育支出, 客户贷款想要装修房子，希望大家支持, 房子装修, 2皇冠卖家，备货周转, 购车, 购车, 房屋装修, 某设备有限公司销售主管，购买货物, 购买货物, 公司法人资金周转，保证按时还款，请支持！, 资金周转, 房屋装修, 资金周转, 资金周转, 购买货物, 用于装修房子，保证按时还款，请大家支持。, 资金周转, 买家具, 购车, 购车, 装修房子，请大家支持！, 房屋装修, 税务所财务装修，望支持！, 房屋装修, 电子维修老板，买小货车方便工作。, 装修, 房子装修, 石化职员投资生意，保证按时还款，请支持！, 资金周转, 购买家电, 资金周转, 扩大生产经营, 日常生活消费, 资金周转]\
```
