import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBatisPlusGenerator {
	
	public static void main(String[] args) {
		//1. 全局配置
		GlobalConfig config = new GlobalConfig();
		// 是否支持AR模式
		config.setActiveRecord(true)
				// 作者
				.setAuthor("zimeng")
				// 生成路径，最好使用绝对路径
				//TODO  TODO  TODO  TODO
				.setOutputDir("C:\\Users\\47219\\Desktop\\Temp")
				// 文件覆盖
				.setFileOverride(true)
				// 主键策略
				.setIdType(IdType.AUTO)
				
				.setDateType(DateType.ONLY_DATE)
				// 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
				.setServiceName("%sService")
				
				//实体类结尾名称
				.setEntityName("%sPO")
				
				//生成基本的resultMap
				.setBaseResultMap(true)
				
				//不使用AR模式
				.setActiveRecord(false)
				
				//生成基本的SQL片段
				.setBaseColumnList(true);
		
		//2. 数据源配置
		DataSourceConfig dsConfig = new DataSourceConfig();
		// 设置数据库类型
		dsConfig.setDbType(DbType.MYSQL)
				.setDriverName("com.mysql.cj.jdbc.Driver")
				//TODO  TODO  TODO  TODO
				.setUrl("jdbc:mysql://47.242.163.242:3306/hrcrm?user=root&password=86fa2d26e3345ec2cdde5585c19e8b0b")
				.setUsername("root")
				.setPassword("86fa2d26e3345ec2cdde5585c19e8b0b");
		
		//3. 策略配置globalConfiguration中
		StrategyConfig stConfig = new StrategyConfig();
		
		//全局大写命名
		stConfig.setCapitalMode(true)
				// 数据库表映射到实体的命名策略
				.setNaming(NamingStrategy.underline_to_camel)
				
				//使用lombok
				.setEntityLombokModel(true)
				
				//使用restcontroller注解
				.setRestControllerStyle(true)
				
				// 生成的表, 支持多表一起生成，以数组形式填写
				//TODO  TODO  TODO  TODO 两个方式，直接写，或者使用命令行输入
				.setInclude("deploy_info");
		//.setInclude(scanner("表名，多个英文逗号分割").split(","));
		
		//4. 包名策略配置
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setParent("com.trust")
				.setMapper("mapper")
				.setService("service")
				.setController("controller")
				.setEntity("domain.po")
				.setXml("mapper");
		//5. 整合配置
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(config)
				.setDataSource(dsConfig)
				.setStrategy(stConfig)
				.setPackageInfo(pkConfig);
		
		//6. 执行操作
		ag.execute();
		System.out.println("=======  Done 相关代码生成完毕  ========");
	}
}