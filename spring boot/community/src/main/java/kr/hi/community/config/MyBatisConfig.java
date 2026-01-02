package kr.hi.community.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan ("kr.hi.community.dao")
public class MyBatisConfig {
	
	/* application.properties에 있는 spring.datasource로 시작하는
	 * 설정 정보를 읽어와서 작업을 하겠다.
	 */

	@ConfigurationProperties(prefix = "spring.datasource") //prefix 앞에 붙는 것들
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(
        	//classPath는 지정된 폴더 몇개가 있는데 그중 하나가 src/main/resources
        	//classPath:mappers는 src/main/resources/mappers를 의미
            new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml")
        );
         // TypeAlias 적용 -> 매퍼에서 resultType에 클래스를 간단히 적기 위해 적용
        sessionFactory.setTypeAliasesPackage("kr.hi.community.model.vo");  // 여기에 패키지 경로 지정
        return sessionFactory.getObject();
    }
}