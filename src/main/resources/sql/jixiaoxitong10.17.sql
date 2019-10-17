/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.62 : Database - tcrljx
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tcrljx` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tcrljx`;

/*Table structure for table `kpi_performance_init` */

DROP TABLE IF EXISTS `kpi_performance_init`;

CREATE TABLE `kpi_performance_init` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `beikaohedanwei` varchar(255) DEFAULT NULL,
  `beizhu` longtext,
  `biaozhun` varchar(255) DEFAULT NULL,
  `caozuofu` varchar(255) DEFAULT NULL,
  `danwei` varchar(255) DEFAULT NULL,
  `kaohedanwei` varchar(255) DEFAULT NULL,
  `kaohejieguo` double DEFAULT NULL,
  `kaohexiangmu` varchar(255) DEFAULT NULL,
  `kaoheyuefen` varchar(255) DEFAULT NULL,
  `mubiaozhi` double DEFAULT NULL,
  `shijishi` double DEFAULT NULL,
  `zhouqi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;

/*Data for the table `kpi_performance_init` */

insert  into `kpi_performance_init`(`id`,`beikaohedanwei`,`beizhu`,`biaozhun`,`caozuofu`,`danwei`,`kaohedanwei`,`kaohejieguo`,`kaohexiangmu`,`kaoheyuefen`,`mubiaozhi`,`shijishi`,`zhouqi`) values (1,'铸轧分厂',NULL,'100','(y-x)*5.2','%','生产技术部',NULL,'scr生产计划完成率',NULL,28000,NULL,'月'),(2,'铸轧分厂',NULL,'100','(y-x)*3','%','生产技术部',123,'大拉生产计划完成率',NULL,8200,1,'月'),(3,'铸轧分厂',NULL,'204','(y-x)*16000','元/ t','财务管理部',NULL,'SCR3000成本',NULL,NULL,NULL,'季'),(4,'铸轧分厂',NULL,'196','(y-x)*12000','元/ t','财务管理部',NULL,'SCR4500成本',NULL,NULL,NULL,'季'),(5,'铸轧分厂',NULL,'125','(y-x)*8200','元/ t','财务管理部',NULL,'大拉责任成本',NULL,NULL,NULL,'季'),(6,'铸轧分厂',NULL,'0.22','(y-x)*1000','‰','品质管理部',NULL,'铜杆线退货率',NULL,NULL,NULL,'月'),(7,'铸轧分厂',NULL,'9','(y-x)*1000','起','品质管理部',NULL,'铜杆线责任投诉起数',NULL,NULL,NULL,'月'),(8,'铸轧分厂',NULL,'81','(y-x)*200','%','品质管理部',NULL,'铜杆一二级品率',NULL,NULL,NULL,'月'),(9,'铸轧分厂',NULL,'0.1','(y-x)*50','%','品质管理部',NULL,'转发',NULL,NULL,NULL,'月'),(10,'铸轧分厂',NULL,'0.23','(y-x)*200','%','品质管理部',NULL,'工艺废杆线',NULL,NULL,NULL,'月'),(11,'铸轧分厂',NULL,'12','1*-200','个','生产技术部',NULL,'万米粒子数',NULL,NULL,NULL,'月'),(12,'铸轧分厂',NULL,'0.17','(y-x)*200','%','品质管理部',NULL,'五级杆',NULL,NULL,NULL,'月'),(13,'铸轧分厂',NULL,'0','(x-y)*5','吨','市场营销部',NULL,'因设备、质量,供货不及时影响订单',NULL,NULL,NULL,'月'),(14,'铸轧分厂',NULL,'91','(y-x)*500','%','生产技术部',NULL,'SCR3000台效',NULL,NULL,NULL,'月'),(15,'铸轧分厂',NULL,'90','(y-x)*500','%','生产技术部',NULL,'SCR4500台效',NULL,NULL,NULL,'月'),(16,'铸轧分厂',NULL,'50','(y-x)*100','小时','设备管理部',NULL,'主要设备故障停机',NULL,NULL,NULL,'月'),(17,'铸轧分厂',NULL,'99.5','(y-x)*100','%','设备管理部',NULL,'司控设备完好率',NULL,NULL,NULL,'月'),(18,'铸轧分厂',NULL,'94.5','(y-x)*100','%','设备管理部',NULL,'预防维修实现率',NULL,NULL,NULL,'月'),(19,'铸轧分厂',NULL,'0','1*0','起','设备管理部',NULL,'重大设备事故',NULL,NULL,NULL,'月'),(20,'铸轧分厂',NULL,'44','(x-y)/x*200','m3/t','设备管理部',NULL,'SCR3000天然气',NULL,NULL,NULL,'季'),(21,'铸轧分厂',NULL,'41.4','(x-y)/x*200','m3/t','设备管理部',NULL,'SCR4500天然气',NULL,NULL,NULL,'季'),(22,'铸轧分厂',NULL,'64.4','(x-y)/x*200','度/t','设备管理部',NULL,'SCR3000电单耗',NULL,NULL,NULL,'季'),(23,'铸轧分厂',NULL,'69','(x-y)/x*200','度/t','设备管理部',NULL,'SCR4500电单耗',NULL,NULL,NULL,'季'),(24,'铸轧分厂',NULL,'146.3','(x-y)/x*200','度/t','设备管理部',NULL,'大拉电单耗',NULL,NULL,NULL,'季'),(25,'铸轧分厂',NULL,'0','(y-x)*500','次','生产技术部',NULL,'排产计划完成率',NULL,NULL,NULL,'月'),(26,'铸轧分厂',NULL,'0','(y-x)*200','吨','生产技术部',NULL,'金属物料零损失',NULL,NULL,NULL,'季'),(27,'铸轧分厂',NULL,'1','(y-x)*500','次','生产技术部',NULL,'工序平衡',NULL,NULL,NULL,'月'),(28,'铸轧分厂',NULL,'90','1*0','%','物资供应部',NULL,'采购计划使用率',NULL,NULL,NULL,'月'),(29,'铸轧分厂',NULL,'100','(y-x)*100','%','生产技术部',NULL,'材料、备件计划准确率',NULL,NULL,NULL,'月'),(30,'铸轧分厂',NULL,'980','(y-x)/x*50','元/吨','财务管理部',NULL,'废乳液站单位成本',NULL,NULL,NULL,'季'),(31,'线材分厂',NULL,'100','(y-x)*32.9','%','生产技术部',NULL,'生产计划完成率',NULL,2200,NULL,'月'),(32,'线材分厂',NULL,'单耗锁定成本','(y-x)*2200','元/t','品质管理部',NULL,'预算责任成本',NULL,NULL,NULL,'季'),(33,'线材分厂',NULL,'0.9','(y-x)*-2000','‰','品质管理部',NULL,'细线退货率',NULL,NULL,NULL,'月'),(34,'线材分厂',NULL,'5','(y-x)*1000','次','品质管理部',NULL,'细线投诉',NULL,NULL,NULL,'月'),(35,'线材分厂',NULL,'3','(y-x)*1000','‰','品质管理部',NULL,'上引杆线退货率',NULL,NULL,NULL,'月'),(36,'线材分厂',NULL,'2','(y-x)*1000','次','品质管理部',NULL,'上引杆线投诉',NULL,NULL,NULL,'月'),(37,'线材分厂',NULL,'1.5','(y-x)*200','%','品质管理部',NULL,'工艺废线量',NULL,NULL,NULL,'季'),(38,'线材分厂',NULL,'0','(y-x)*(-10)','吨','市场营销部',NULL,'因设备、质量,供货不及时影响订单',NULL,NULL,NULL,'月'),(39,'线材分厂',NULL,'15','(y-x)*100','小时','设备管理部',NULL,'主要设备故障停机',NULL,NULL,NULL,'月'),(40,'线材分厂',NULL,'99.5','(y-x)*100','%','设备管理部',NULL,'司控设备完好率',NULL,NULL,NULL,'月'),(41,'线材分厂',NULL,'94','(y-x)*100','%','设备管理部',NULL,'预防维修实现率',NULL,NULL,NULL,'月'),(42,'线材分厂',NULL,'0','1*0','起','设备管理部',NULL,'重大设备事故',NULL,NULL,NULL,'月'),(43,'线材分厂',NULL,'H44','(x-y)/x*200','度/t','设备管理部',NULL,'上引电单耗',NULL,NULL,NULL,'季'),(44,'线材分厂',NULL,'H45','(x-y)/x*200','度/t','设备管理部',NULL,'大拉电单耗',NULL,NULL,NULL,'季'),(45,'线材分厂',NULL,'H46','(x-y)/x*200','度/t','设备管理部',NULL,'中拉电单耗',NULL,NULL,NULL,'季'),(46,'线材分厂',NULL,'H47','(x-y)/x*200','度/t','设备管理部',NULL,'小拉电单耗',NULL,NULL,NULL,'季'),(47,'线材分厂',NULL,'H48','(x-y)/x*200','度/t','设备管理部',NULL,'双头拉电单耗',NULL,NULL,NULL,'季'),(48,'线材分厂',NULL,'H49','(x-y)/x*200','度/t','设备管理部',NULL,'镀锡电单耗',NULL,NULL,NULL,'季'),(49,'线材分厂',NULL,'H50','(x-y)/x*200','度/t','设备管理部',NULL,'复绕电单耗',NULL,NULL,NULL,'季'),(50,'线材分厂',NULL,'8.04','(x-y)/x*1000/4*3','kg/t','财务管理部',NULL,'锡单耗',NULL,NULL,NULL,'月'),(51,'线材分厂',NULL,'0','(y-x)*200','吨','生产技术部',NULL,'金属物料零损失',NULL,NULL,NULL,'季'),(52,'线材分厂',NULL,'1','(y-x)*500','次','生产技术部',NULL,'工序平衡',NULL,NULL,NULL,'月'),(53,'线材分厂',NULL,'90','0*0','%','物资供应部',NULL,'采购计划使用率',NULL,NULL,NULL,'月'),(54,'线材分厂',NULL,'500','1*-500','吨','生产技术部',NULL,'金属物料占用',NULL,NULL,NULL,'月'),(55,'线材分厂',NULL,'0','(y-x)*100','项','生产技术部',NULL,'材料、备件计划准确率',NULL,NULL,NULL,'月'),(56,'苏州分公司',NULL,'100','(y-x)/x*0.3125*35133','%','生产技术部',NULL,'生产计划完成率',NULL,833,NULL,'月'),(57,'苏州分公司',NULL,'494','(x-y)*833*0.1','元/t','财务管理部',NULL,'并线预算责任成本',NULL,NULL,NULL,'季'),(58,'苏州分公司',NULL,'226','(x-y)*833*0.1','元/t','财务管理部',NULL,'绞线预算责任成本',NULL,NULL,NULL,'季'),(59,'苏州分公司',NULL,'1','(y-x)*500','‰','品质管理部',NULL,'退货率',NULL,NULL,NULL,'月'),(60,'苏州分公司',NULL,'4','(y-x)*200','次','品质管理部',NULL,'投诉',NULL,NULL,NULL,'月'),(61,'苏州分公司',NULL,'1','(y-x)*500','%','品质管理部',NULL,'废线量',NULL,NULL,NULL,'月'),(62,'苏州分公司',NULL,'0','(y-x)*10','吨','市场营销部',NULL,'因设备、质量,供货不及时影响订单',NULL,NULL,NULL,'月'),(63,'苏州分公司',NULL,'25','(y-x)*50','小时/台','设备管理部',NULL,'多头拉设备故障停机时间',NULL,NULL,NULL,'月'),(64,'苏州分公司',NULL,'100','(y-x)*100','%','设备管理部',NULL,'主要设备完好率',NULL,NULL,NULL,'月'),(65,'苏州分公司',NULL,'520','(x-y)/x*20','度/t','设备管理部',NULL,'多头拉电单耗',NULL,NULL,NULL,'季'),(66,'苏州分公司',NULL,'194','(x-y)/x*20','度/t','设备管理部',NULL,'绞线电单耗',NULL,NULL,NULL,'季'),(67,'苏州分公司',NULL,'0','(y-x)*200','t','生产技术部',NULL,'金属物料平衡',NULL,NULL,NULL,'季'),(68,'苏州分公司',NULL,'260','1*-500','吨','生产技术部',NULL,'金属物料占用',NULL,NULL,NULL,'月'),(69,'苏州分公司',NULL,'0','(y-x)*100','天','市场营销部',NULL,'按期交货',NULL,NULL,NULL,'月'),(70,'市场营销部',NULL,'100','(y-x)/x*0.625*29695','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(71,'市场营销部',NULL,'100','(y-x)*100*100','%','财务管理部',NULL,'货款回笼率',NULL,NULL,NULL,'月'),(72,'市场营销部',NULL,'0','0/20*1000','万元','财务管理部',NULL,'期现结合原料运作效益',NULL,NULL,NULL,'年'),(73,'市场营销部',NULL,'100','(y-x)*5','%','铸轧分厂',NULL,'产品入库及时性',NULL,NULL,NULL,'月'),(74,'市场营销部',NULL,'0','(y-x)*200','吨','生产技术部',NULL,'金属物料供应',NULL,NULL,NULL,'月'),(75,'市场营销部',NULL,'50','(y-x)*1','吨','铸轧分厂',NULL,'电铜供应均衡',NULL,NULL,NULL,'月'),(76,'市场营销部',NULL,'0','(y-x)*100','次','铸轧分厂',NULL,'废旧物资处理不及时',NULL,NULL,NULL,'月'),(77,'市场营销部',NULL,'/','1*y','元','财务管理部',NULL,'预收货款',NULL,NULL,NULL,'月'),(78,'市场营销部',NULL,'430','1*-500','吨','生产技术部',NULL,'金属物料占用',NULL,NULL,NULL,'月'),(79,'市场营销部',NULL,'100','(y-x)*10','%','财务管理部',NULL,'细线销售计划完成率',NULL,NULL,NULL,'季'),(80,'市场营销部',NULL,'100','(y-x)*20','%','财务管理部',NULL,'并绞线销售计划完成率',NULL,NULL,NULL,'季'),(81,'市场营销部',NULL,'100','(y-x)*500','%','财务管理部',NULL,'微细线计划完成率',NULL,NULL,NULL,'季'),(82,'市场营销部',NULL,'100','1*-1000','%','财务管理部',NULL,'货款回笼',NULL,NULL,NULL,'月'),(83,'市场营销部',NULL,'100','(y-x)/10*1000','元','财务管理部',NULL,'加工加价盈利',NULL,NULL,NULL,'年'),(84,'市场营销部',NULL,'95','(y-x)*4','%','品质管理部',NULL,'P15线轴回收',NULL,NULL,NULL,'双月'),(85,'市场营销部',NULL,'95','(y-x)*4','%','品质管理部',NULL,'P10线轴回收',NULL,NULL,NULL,'双月'),(86,'市场营销部',NULL,'95','(y-x)*2','%','品质管理部',NULL,'小轴线轴回收',NULL,NULL,NULL,'双月'),(87,'市场营销部',NULL,'85','(y-x)*10','%','品质管理部',NULL,'可用木托盘回收',NULL,NULL,NULL,'双月'),(88,'市场营销部',NULL,'100','(y-x)*1000','%','品质管理部',NULL,'铁轴回收',NULL,NULL,NULL,'月'),(89,'市场营销部',NULL,'90','(y-x)*200','%','品质管理部',NULL,'线轴可用率',NULL,NULL,NULL,'双月'),(90,'市场营销部',NULL,'100','1*-100','%','财务管理部',NULL,'票据证明及时性',NULL,NULL,NULL,'月'),(91,'市场营销部',NULL,'0','1*0','t　','品质管理部',NULL,'退货',NULL,NULL,NULL,'月'),(92,'物资供应部',NULL,'100','(y-x)/x*0.625*14210','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(93,'物资供应部',NULL,'≤集团公司目标值','(y-x)/50*100','万元','财务管理部',NULL,'材料、备件储备资金占用',NULL,NULL,NULL,'月'),(94,'物资供应部',NULL,'98','(y-x)*200','%','铸轧分厂',NULL,'铸轧自购物资到货率',NULL,NULL,NULL,'月'),(95,'物资供应部',NULL,'98','(y-x)*200','%','线材分厂',NULL,'线材自购物资到货率',NULL,NULL,NULL,'月'),(96,'物资供应部',NULL,'100','(y-x)*200','%','铸轧分厂',NULL,'铸轧自购物资合格率',NULL,NULL,NULL,'月'),(97,'物资供应部',NULL,'100','(y-x)*200','%','线材分厂',NULL,'线材自购物资合格率',NULL,NULL,NULL,'月'),(98,'物资供应部',NULL,'0','(y-x)*100','起','铸轧分厂',NULL,'铸轧采购验收质量投诉',NULL,NULL,NULL,'月'),(99,'物资供应部',NULL,'0','(y-x)*100','起','线材分厂',NULL,'线材采购验收质量投诉',NULL,NULL,NULL,'月'),(100,'物资供应部',NULL,'0','(y-x)*1000','次','铸轧分厂',NULL,'供应影响生产、检修',NULL,NULL,NULL,'月'),(101,'物资供应部',NULL,'0','(y-x)*500','%','设备管理部',NULL,'固定资产投资项目核消',NULL,NULL,NULL,'月'),(102,'物资供应部',NULL,'0','(y-x)*200','%','铸轧分厂',NULL,'大宗物资验收',NULL,NULL,NULL,'月'),(103,'物资供应部',NULL,'0','(y-x)*100','件','设备管理部',NULL,'进口备件采购期',NULL,NULL,NULL,'月'),(104,'物资供应部',NULL,'0','(y-x)*100','%','设备管理部',NULL,'月修加工急件',NULL,NULL,NULL,'月'),(105,'物资供应部',NULL,'一个月','(y-x)*200','项','生产技术部',NULL,'新材料评审周期',NULL,NULL,NULL,'月'),(106,'物资供应部',NULL,'0','(y-x)*100','%','铸轧分厂',NULL,'代储物资验收',NULL,NULL,NULL,'月'),(107,'物资供应部',NULL,'0','(y-x)*100','%','铸轧分厂',NULL,'采购合同履行',NULL,NULL,NULL,'月'),(108,'物资供应部',NULL,'0','(y-x)*100','%','财务管理部',NULL,'账卡物相符情况',NULL,NULL,NULL,'季'),(109,'物资供应部',NULL,'5','(y-x)*200','%','财务管理部',NULL,'平均采购单价下降达成率',NULL,NULL,NULL,'年'),(110,'物资供应部',NULL,'公司确定','(y-x)*200','项','人力资源部',NULL,'降低单一采购项目',NULL,NULL,NULL,'年'),(111,'生产技术部',NULL,'100','(y-x)/x*0.625*9270','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(112,'生产技术部',NULL,'0','(y-x)*200','次','人力资源部',NULL,'生产计划管理',NULL,NULL,NULL,'月'),(113,'生产技术部',NULL,'≧4','(y-x)*100','起','人力资源部',NULL,'工艺技术管理',NULL,NULL,NULL,'月'),(114,'生产技术部',NULL,'0','0*1','起','人力资源部',NULL,'安全环保管理',NULL,NULL,NULL,'月'),(115,'生产技术部',NULL,'19.5','1*200','%','财务管理部',NULL,'简易包装量',NULL,NULL,NULL,'月'),(116,'生产技术部',NULL,'100','(y-x)*-100','%','人力资源部',NULL,'分厂材料计划准确率',NULL,NULL,NULL,'月'),(117,'生产技术部',NULL,'1','(y-x)*-200','起','人力资源部',NULL,'金属物料零损失考核',NULL,NULL,NULL,'月'),(118,'生产技术部',NULL,'0','(y-x)*-200','起','财务管理部',NULL,'统计报表',NULL,NULL,NULL,'月'),(119,'生产技术部',NULL,'≦3','(y-x)*-200','次','市场营销部',NULL,'技术评审',NULL,NULL,NULL,'月'),(120,'生产技术部',NULL,'0','(y-x)*-200','次','市场营销部',NULL,'交货期考核',NULL,NULL,NULL,'月'),(121,'生产技术部',NULL,'达标','(y-x)*-500','起','总经理办',NULL,'现场管理',NULL,NULL,NULL,'年'),(122,'生产技术部',NULL,'75','(y-x)*-200','分','品质管理部',NULL,'环境与职业安全健康体系',NULL,NULL,NULL,'年'),(123,'设备管理部',NULL,'100','(y-x)/x*0.625*9570','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(124,'设备管理部',NULL,'0','(y-x)*-200','起','铸轧分厂',NULL,'工程管理',NULL,NULL,NULL,'月'),(125,'设备管理部',NULL,'0','(y-x)*-200','起','线材分厂',NULL,'工程管理',NULL,NULL,NULL,'月'),(126,'设备管理部',NULL,'0','(y-x)*-200','起','人力资源部',NULL,'设备重大事故',NULL,NULL,NULL,'月'),(127,'设备管理部',NULL,'0','(y-x)*-100','起','总经理办',NULL,'信息化管理',NULL,NULL,NULL,'月'),(128,'设备管理部',NULL,'100','(y-x)*-200','%','铸轧分厂',NULL,'外协施工管理',NULL,NULL,NULL,'月'),(129,'设备管理部',NULL,'100','(y-x)*-100','%','人力资源部',NULL,'分厂备件计划准确率',NULL,NULL,NULL,'月'),(130,'设备管理部',NULL,'1','(y-x)*-200','次','人力资源部',NULL,'设备能源检查',NULL,NULL,NULL,'月'),(131,'设备管理部',NULL,'0','(y-x)*-200','项','财务管理部',NULL,'大修季度完成情况',NULL,NULL,NULL,'季'),(132,'设备管理部',NULL,'1','(y-x)*-200','起','人力资源部',NULL,'设备档案检查频次',NULL,NULL,NULL,'季'),(133,'设备管理部',NULL,'75','(y-x)*-200','分','品质管理部',NULL,'能源管理体系',NULL,NULL,NULL,'年'),(134,'设备管理部',NULL,'1','(y-x)*-200','次','铸轧分厂',NULL,'废旧设备处理',NULL,NULL,NULL,'年'),(135,'设备管理部',NULL,'73','(y-x)*-200','%','人力资源部',NULL,'技改项目完成率',NULL,NULL,NULL,'年'),(136,'设备管理部',NULL,'644','(y-x)/x*-200*100','万元','财务管理部',NULL,'大修费用控制',NULL,NULL,NULL,'年'),(137,'品质管理部',NULL,'100','(y-x)/x*0.625*38885','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(138,'品质管理部',NULL,'0','(y-x)*-200','起','市场营销部',NULL,'质量和计量异议处理',NULL,NULL,NULL,'月'),(139,'品质管理部',NULL,'0.25','(y-x)*400','‰','人力资源部',NULL,'综合退货率',NULL,NULL,NULL,'月'),(140,'品质管理部',NULL,'0','(y-x)*-200','起','铸轧分厂',NULL,'原材料及产品检验',NULL,NULL,NULL,'月'),(141,'品质管理部',NULL,'80','(y-x)*10','小时','铸轧分厂',NULL,'铁框回收',NULL,NULL,NULL,'月'),(142,'品质管理部',NULL,'19.5','1*200','%','财务管理部',NULL,'简易包装量',NULL,NULL,NULL,'月'),(143,'品质管理部',NULL,'65','(y-x)*200','%','铸轧分厂',NULL,'木托架回收率',NULL,NULL,NULL,'双月'),(144,'品质管理部',NULL,'50','(y-x)*10','%','铸轧分厂',NULL,'1.5米木托架回收可用率',NULL,NULL,NULL,'双月'),(145,'品质管理部',NULL,'5','(y-x)*10','%','铸轧分厂',NULL,'不可修复木托架回收率',NULL,NULL,NULL,'双月'),(146,'品质管理部',NULL,'100','(y-x)*200','%','市场营销部',NULL,'对应发货准确率',NULL,NULL,NULL,'月'),(147,'品质管理部',NULL,'0','(y-x)*200','次','市场营销部',NULL,'不合理退货',NULL,NULL,NULL,'月'),(148,'品质管理部',NULL,'100','(y-x)*5','%','铸轧分厂',NULL,'产品入库及时性',NULL,NULL,NULL,'月'),(149,'品质管理部',NULL,'75','(y-x)*200','分','人力资源部',NULL,'质量和计量体系管理',NULL,NULL,NULL,'年'),(150,'人力资源部',NULL,'100','(y-x)/x*0.625*3700','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(151,'人力资源部',NULL,'90','(y-x)*50','%','线材分厂',NULL,'线材分厂人力资源配置',NULL,NULL,NULL,'月'),(152,'人力资源部',NULL,'90','(y-x)*50','%','铸轧分厂',NULL,'铸轧分厂人力资源配置',NULL,NULL,NULL,'月'),(153,'人力资源部',NULL,'100','(y-x)*200','%','风控内审部',NULL,'劳动合同管理',NULL,NULL,NULL,'月'),(154,'人力资源部',NULL,'1','(y-x)*100','次','总经理办',NULL,'会议跟踪落实情况检查',NULL,NULL,NULL,'月'),(155,'人力资源部',NULL,'0','(y-x)*200','起','总经理办',NULL,'绩效管理',NULL,NULL,NULL,'月'),(156,'人力资源部',NULL,'0','(y-x)*200','次','财务管理部',NULL,'薪酬管理',NULL,NULL,NULL,'月'),(157,'人力资源部',NULL,'0','(y-x)*200','起','风控内审部',NULL,'社保管理差错',NULL,NULL,NULL,'月'),(158,'人力资源部',NULL,'1','(y-x)*100','次','总经理办',NULL,'培训实效性检查',NULL,NULL,NULL,'季'),(159,'人力资源部',NULL,'95','(y-x)*200','%','总经理办',NULL,'培训计划完成率',NULL,NULL,NULL,'年'),(160,'人力资源部',NULL,'合格','(y-x)*200','次','财务管理部',NULL,'福利企业管理',NULL,NULL,NULL,'年'),(161,'党群工作部',NULL,'100','(y-x)/x*0.625*7895','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(162,'党群工作部',NULL,'0','(y-x)*200','次','总经理办',NULL,'信访管理',NULL,NULL,NULL,'月'),(163,'党群工作部',NULL,'月底前','(y-x)*200','次','人力资源部',NULL,'《加工事业部通讯》出刊及时性',NULL,NULL,NULL,'月'),(164,'党群工作部',NULL,'1','(y-x)*200','篇','人力资源部',NULL,'对外宣传重要稿件',NULL,NULL,NULL,'月'),(165,'党群工作部',NULL,'1','(y-x)*100','次','人力资源部',NULL,'宣传稿件考核',NULL,NULL,NULL,'季'),(166,'党群工作部',NULL,'1','(y-x)*100','篇','人力资源部',NULL,'江铜E家亲稿件任务',NULL,NULL,NULL,'季'),(167,'党群工作部',NULL,'0','(y-x)*100','次','总经理办',NULL,'督办事项',NULL,NULL,NULL,'月'),(168,'党群工作部',NULL,'1','(y-x)*200','次','总经理办',NULL,'政治理论学习检查',NULL,NULL,NULL,'月'),(169,'党群工作部',NULL,'1','(y-x)*200','次','人力资源部',NULL,'文明办公',NULL,NULL,NULL,'月'),(170,'财务管理部',NULL,'100','(y-x)/x*0.625*12100','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(171,'财务管理部',NULL,'0','(y-x)*200','起','风控内审部',NULL,'全面预算管理工作推进',NULL,NULL,NULL,'月'),(172,'财务管理部',NULL,'0','(y-x)*100','次','市场营销部',NULL,'客户资信调查',NULL,NULL,NULL,'月'),(173,'财务管理部',NULL,'1','(y-x)*100','%','风控内审部',NULL,'资金的月度盘点',NULL,NULL,NULL,'月'),(174,'财务管理部',NULL,'0','(y-x)*200','起','风控内审部',NULL,'票据发生差错起数',NULL,NULL,NULL,'月'),(175,'财务管理部',NULL,'0','(y-x)*100','次','风控内审部',NULL,'成本核算管理',NULL,NULL,NULL,'月'),(176,'财务管理部',NULL,'100','(y-x)*200','%','风控内审部',NULL,'应收账款月度分析',NULL,NULL,NULL,'月'),(177,'财务管理部',NULL,'0','(y-x)*200','次','市场营销部',NULL,'货款到账通知',NULL,NULL,NULL,'月'),(178,'财务管理部',NULL,'1','(y-x)*200','次','风控内审部',NULL,'责任经费执行审核',NULL,NULL,NULL,'季'),(179,'财务管理部',NULL,'100','(y-x)*200','%','风控内审部',NULL,'书面对账',NULL,NULL,NULL,'年'),(180,'总经理办',NULL,'100','(y-x)/x*0.625*59635','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(181,'总经理办',NULL,'0','(y-x)*200','次','党群工作部',NULL,'决策督办推进季考核',NULL,NULL,NULL,'月'),(182,'总经理办',NULL,'0','(y-x)*200','起','人力资源部',NULL,'安保事件发生率',NULL,NULL,NULL,'月'),(183,'总经理办',NULL,'0','(y-x)*200','起','党群工作部',NULL,'后勤服务投诉',NULL,NULL,NULL,'月'),(184,'总经理办',NULL,'0','(y-x)*200','起','总经理办',NULL,'文件机要管理',NULL,NULL,NULL,'月'),(185,'总经理办',NULL,'0','(y-x)*200','起','人力资源部',NULL,'档案管理',NULL,NULL,NULL,'月'),(186,'总经理办',NULL,'100','(y-x)*200','%','铸轧分厂',NULL,'外来人员、车辆管理',NULL,NULL,NULL,'月'),(187,'总经理办',NULL,'0','(y-x)*200','项','人力资源部',NULL,'出门证管理',NULL,NULL,NULL,'月'),(188,'总经理办',NULL,'12','(y-x)/2*6','升/100km','财务管理部',NULL,'单台车平均油耗',NULL,NULL,NULL,'季度'),(189,'总经理办',NULL,'70','(y-x)*200','%','党群工作部',NULL,'食堂满意度',NULL,NULL,NULL,'半年'),(190,'总经理办',NULL,'90','(y-x)*40','%','人力资源部',NULL,'小车队满意度',NULL,NULL,NULL,'年'),(191,'总经理办',NULL,'5','(y-x)/2','万元','财务管理部',NULL,'维修保养费',NULL,NULL,NULL,'年'),(192,'风控内审部',NULL,'100','(y-x)/x*0.625*2665','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(193,'风控内审部',NULL,'0','(y-x)*200','起','设备管理部',NULL,'合同管理',NULL,NULL,NULL,'月'),(194,'风控内审部',NULL,'0','(y-x)*200','项','总经理办',NULL,'风险控制',NULL,NULL,NULL,'年'),(195,'风控内审部',NULL,'100','(y-x)*100','%','设备管理部',NULL,'工程审计',NULL,NULL,NULL,'月'),(196,'风控内审部',NULL,'0','(y-x)*200','次','市场营销部',NULL,'客户资信调查',NULL,NULL,NULL,'月'),(197,'风控内审部',NULL,'0','(y-x)*200','起','总经理办',NULL,'法律事务',NULL,NULL,NULL,'月'),(198,'风控内审部',NULL,'0','(y-x)*200','次','设备管理部',NULL,'招投标管理',NULL,NULL,NULL,'月'),(199,'风控内审部',NULL,'1','(y-x)*200','次','人力资源部',NULL,'内控季度检查',NULL,NULL,NULL,'季'),(200,'经贸部',NULL,'100','(y-x)/x*0.625*4875','%','生产技术部',NULL,'分厂生产计划完成率',NULL,NULL,NULL,'月'),(201,'经贸部',NULL,'100','(y-x)*2','%','财务管理部',NULL,'杂铜长单销量（金属量）',NULL,NULL,NULL,'季');

/*Table structure for table `kpi_performance_result` */

DROP TABLE IF EXISTS `kpi_performance_result`;

CREATE TABLE `kpi_performance_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `beikaohedanwei` varchar(255) DEFAULT NULL,
  `beizhu` longtext,
  `biaozhun` varchar(255) DEFAULT NULL,
  `caozuofu` varchar(255) DEFAULT NULL,
  `danwei` varchar(255) DEFAULT NULL,
  `kaohedanwei` varchar(255) DEFAULT NULL,
  `kaohejieguo` double DEFAULT NULL,
  `kaohexiangmu` varchar(255) DEFAULT NULL,
  `kaoheyuefen` varchar(255) DEFAULT NULL,
  `mubiaozhi` double DEFAULT NULL,
  `shijishi` double DEFAULT NULL,
  `zhouqi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `kpi_performance_result` */

insert  into `kpi_performance_result`(`id`,`beikaohedanwei`,`beizhu`,`biaozhun`,`caozuofu`,`danwei`,`kaohedanwei`,`kaohejieguo`,`kaohexiangmu`,`kaoheyuefen`,`mubiaozhi`,`shijishi`,`zhouqi`) values (1,'铸轧分厂','','100','(y-x)*5.2','%',NULL,-10400,'scr生产计划完成率','9月',28000,26000,'月'),(2,'铸轧分厂','1','100','(y-x)*3','%',NULL,900,'大拉生产计划完成率','9月',8200,8500,'月'),(3,'铸轧分厂','','204','(y-x)*16000','元/ t',NULL,-48000,'SCR3000成本','9月',204,201,'季'),(4,'铸轧分厂','','196','(y-x)*12000','元/ t',NULL,-12000,'SCR4500成本','9月',196,195,'季'),(5,'铸轧分厂','','125','(y-x)*8200','元/ t',NULL,0,'大拉责任成本','9月',125,125,'季');

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptName` varchar(55) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `sys_department` */

insert  into `sys_department`(`id`,`deptName`,`parentId`,`flag`) values (1,'铜材公司',0,NULL),(2,'总经理办',1,NULL),(3,'党群工作部',1,NULL),(4,'财务管理部',1,NULL),(5,'风控内审部',1,NULL),(6,'人力资源部',1,NULL),(7,'生产技术部',1,NULL),(8,'设备管理部',1,NULL),(9,'品质管理部',1,NULL),(10,'物资供应部',1,NULL),(11,'市场营销部',1,NULL),(12,'铸轧分厂',1,NULL),(13,'线材分厂',1,NULL),(14,'经贸部',1,NULL);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`parentid`,`name`,`css`,`href`,`type`,`permission`,`sort`) values (1,0,'用户管理','&#xe696;',NULL,1,NULL,1),(2,1,'用户查询','&#xe6b8;','/api/getPage?pageName=user/user-list',1,NULL,2),(3,2,'查询',NULL,NULL,2,'sys:user:query',100),(4,2,'新增',NULL,NULL,2,'sys:user:add',100),(5,2,'删除',NULL,NULL,2,'sys:user:del',100),(6,1,'修改密码','&#xe74e;','/api/getPage?pageName=user/user-change-password',1,'sys:user:password',4),(7,0,'系统设置','&#xe75f;',NULL,1,NULL,5),(8,7,'菜单','&#xe749;','/api/getPage?pageName=permission/permission-list',1,NULL,6),(9,8,'查询',NULL,NULL,2,'sys:menu:query',100),(10,8,'新增',NULL,NULL,2,'sys:menu:add',100),(11,8,'删除',NULL,NULL,2,'sys:menu:del',100),(12,7,'角色','&#xe753;','/api/getPage?pageName=role/role-list',1,NULL,7),(13,12,'查询',NULL,NULL,2,'sys:role:query',100),(14,12,'新增',NULL,NULL,2,'sys:role:add',100),(15,12,'删除',NULL,NULL,2,'sys:role:del',100),(16,0,'文件管理','&#xe735;','/api/getPage?pageName',1,NULL,8),(17,16,'查询',NULL,NULL,2,'sys:file:query',100),(18,16,'删除',NULL,NULL,2,'sys:file:del',100),(19,0,'数据源管理','&#xe722;','druid/index.html',1,NULL,9),(21,0,'接口swagger','&#xe723;','swagger-ui.html',1,NULL,10),(22,0,'代码生成','&#xe715;','/api/getPage?pageName',1,'generate:edit',11),(23,0,'日志查询','&#xe70c;','/api/getPage?pageName',1,'sys:log:query',13),(24,8,'修改',NULL,NULL,2,'sys:menu:edit',100),(25,12,'修改',NULL,NULL,2,'sys:role:edit',100),(26,2,'修改',NULL,NULL,2,'sys:user:edit',100);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `descirption` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`descirption`,`createtime`,`updatetime`) values (1,'ADMIN','管理员','2019-10-10 00:00:00','2019-10-11 00:00:00'),(2,'USER','用户','2019-10-10 00:00:00','2019-10-11 00:00:00'),(3,'TEACHER','教师','2019-10-10 00:00:00','2019-10-11 00:00:00'),(4,'test','测试','2019-10-10 00:00:00','2019-10-11 00:00:00'),(5,'student','学生','2019-10-10 00:00:00','2019-10-10 00:00:00'),(6,'student1','1111','2019-10-10 00:00:00','2019-10-10 00:00:00'),(7,'student2','222','2019-10-10 00:00:00','2019-10-10 00:00:00'),(8,'student3','3333','2019-10-10 00:00:00','2019-10-10 00:00:00'),(9,'student5','55555','2019-10-06 07:31:04','2019-10-06 07:31:04'),(11,'赵安然','仙女','2019-10-07 02:38:09','2019-10-07 02:38:09'),(12,'teacher_holder','一个仙女掌管者','2019-10-11 12:55:24','2019-10-11 12:55:24');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  KEY `FK_sys_role_pemisssion` (`permissionid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`roleid`,`permissionid`) values (8,1),(8,2),(8,3),(8,4),(8,5),(8,26),(8,6),(11,1),(11,2),(11,3),(11,4),(11,5),(11,26),(11,6),(11,7),(11,8),(11,9),(11,10),(11,11),(11,24),(11,12),(11,13),(11,14),(11,15),(11,25),(2,7),(3,7),(3,16),(3,17),(3,18),(12,1),(12,2),(12,3),(12,4),(12,5),(12,26),(12,6),(12,7),(12,8),(12,9),(12,10),(12,11),(12,24),(12,12),(12,13),(12,14),(12,15),(12,25),(12,16),(12,17),(12,18),(12,19),(12,21),(12,22),(12,23),(1,1),(1,2),(1,3),(1,4),(1,5),(1,26),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,25),(1,16),(1,17),(1,18),(1,19),(1,21),(1,22),(1,23);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  KEY `FK_sys_user_role` (`userid`),
  KEY `roleid` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`userid`,`roleid`) values (17,2),(19,4),(20,1),(1,1),(22,12),(23,1),(23,9),(23,11),(23,12),(24,1),(24,5),(24,9),(24,11),(24,12);

/*Table structure for table `sys_users` */

DROP TABLE IF EXISTS `sys_users`;

CREATE TABLE `sys_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `dept` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  `headimage` varchar(150) DEFAULT NULL,
  `gangweimingcheng` varchar(55) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `sys_users` */

insert  into `sys_users`(`id`,`username`,`password`,`phone`,`dept`,`email`,`headimage`,`gangweimingcheng`,`status`,`createtime`,`updatetime`,`birthday`,`sex`) values (1,'admin','$2a$10$e8uEJMBhbICkvVAndA.h1ufFIumA9tLYcYmEnkOhtXB3JVxrWmNe.','123456789','人力资源部','456@qq.com',NULL,'guanliyuan',1,NULL,NULL,'2019-01-12',NULL),(5,'liuqi','555',NULL,'铸轧分厂','456@qq.com',NULL,NULL,0,NULL,NULL,NULL,NULL),(6,'wangba','666',NULL,'线材分厂','456@qq.com',NULL,NULL,1,NULL,NULL,NULL,NULL),(7,'wangjiu','777',NULL,'市场营销部','456@qq.com',NULL,NULL,1,NULL,NULL,NULL,NULL),(8,'wangshi','222',NULL,'生产技术部','456@qq.com',NULL,NULL,0,NULL,NULL,NULL,NULL),(9,'wangshiyi','888',NULL,'设备管理部','456@qq.com',NULL,NULL,0,NULL,NULL,NULL,NULL),(10,'wangshier','555',NULL,'品质管理部','456@qq.com',NULL,NULL,1,NULL,NULL,NULL,NULL),(11,'wangshisan','111',NULL,'物资供应部','456@qq.com',NULL,NULL,1,NULL,NULL,NULL,NULL),(16,'4567','e10adc3949ba59abbe56e057f20f883e','13455555555','经贸部','56@qq.com',NULL,NULL,1,'2019-10-02 03:41:03','2019-10-02 03:41:03','2019-10-06',NULL),(17,'345','e10adc3949ba59abbe56e057f20f883e','13422222222','党群工作部','22@qq.com',NULL,NULL,1,'2019-10-02 03:46:29','2019-10-02 03:46:29','2019-10-07',NULL),(19,'users123','e10adc3949ba59abbe56e057f20f883e','13455555556','财务管理部','345@qq.com',NULL,NULL,1,'2019-10-03 11:58:08','2019-10-03 11:58:08','2019-10-01',NULL),(20,'测试123','e10adc3949ba59abbe56e057f20f883e','13455556666','风控内审部','67@qq.com',NULL,NULL,1,'2019-10-08 09:57:50','2019-10-08 09:57:50','2019-10-07',NULL),(22,'Amy','$2a$10$qhTuplHaDC/.PI.9/yUwo.Tt/nmq.fCqEhEd2ZWMwTHMJocnI4A8a','14874520935','总经理办','788@qq.com',NULL,NULL,1,'2019-10-11 12:52:56','2019-10-11 12:52:56','2009-07-26',NULL),(23,'user77788','$2a$10$NXvmkxTAE.pUW92aOmYnN.WbZ1CPGjn0Vof0lYMvWUj/FpMqIS336','12345678888','人力资源部','89@qq.com',NULL,NULL,1,'2019-10-12 10:49:01','2019-10-12 10:49:01','2019-10-04',1),(24,'user99999','$2a$10$cyRxAQcqquuuqfMLLj8QquR6ENLmM4Vh71Ua6i8oLzp9KnPFrCor6','12312312312','风控内审部','789@qq.com',NULL,NULL,1,'2019-10-13 03:40:57','2019-10-13 03:40:57','2019-09-30',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
