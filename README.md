# JavaWeb
课程设计题目 
在基于课程实验的基础之上，进一步完善网站的功能，并追加目前商业网站所必备的基于大数据的数据分析以及推荐系统。基于软件工程的流程，需要完成四份报告：

《需求分析报告》 ：根据选定的题目，任务清单，以及要达到的技术指标，确定满足开发需求所必须实现的功能，并选定整体技术路线，评估完成所需功能所需要的时间，指定整体开发规划；

《系统设计报告》 ： 根据所必须实现的功能，设计系统整体，以及所需的模块，确定详细的开发计划；

《系统实现报告》 ：依据开发计划，进行代码实现，并在报告之中列出全部代码，解释说明部署过程步骤；

《测试报告》：需要进行黑盒测试，功能测试，将测试通过的代码共享至公共的代码托管平台，并将该网站部署上线并提供验证测试。

课程设计要求 开发的网站至少包括三种类别的使用角色(用户， 销售人员， 管理者) 

  用户需要实现的基本功能

  新用户的自主 注册

  注册用户的登录，注销

  注册用户的浏览，查询，购买等操作流程（浏览/查询 添加至购物篮 付款 发送电子邮件确认发货）
  
  未登录用户浏览销售商品
  
  etc. 

销售人员需要实现的基本功能
  
  所负责销售商品的目录管理（商品类别的添加和删除）
  
  所负责销售商品类别信息的修改（销售价格，在库数目）
  
  所负责销售商品类别的销售状态信息的监控
  
  用户购买其所负责销售商品的 浏览/购买的 日志 记录
  
  etc. 

管理者需要实现的基本功能
  
  对销售人员ID的管理（销售人员的ID添加，删除）
  
  对销售人员ID的登录口令的重置
  
  对销售人员负责商品类别的销售业绩的查询和监控
  
  各个商品类别的销售统计报表， 销售状态，库存管理
  
  各个商品类别的销售业绩的查询和统计
  
  etc. 

需要收集记录的数据信息(大数据) 

用户
  
  登录以及退出的时间和IP地址
  
  访问的商品类别以及停留的时长(单位：秒)
  
  购买商品的记录（商品类别，购买日期，购买的单价以及数量）
  
  etc. 

销售人员 & 管理者
  
  登录以及退出的时间和IP地址
  
  操作日志(包括查询在内的所有操作记录：操作时间，内容，IP地址，账号，etc.) 

基于大数据的数据分析以及推荐系统
  
  用户画像（对用户的精准分类）；
  
  用户购买趋势预测与评估（推荐系统）；
  
  商品销售趋势预测与评估；
  
  销售异常的判别与实时监控
  
  etc. 

其他可选的附加功能
  
  反爬虫侦测和应对；
  
  数据的导入和导出；
  
  数据分析结果的在线可视化。
