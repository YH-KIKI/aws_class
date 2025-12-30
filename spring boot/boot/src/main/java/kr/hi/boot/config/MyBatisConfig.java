package kr.hi.boot.config;

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
@MapperScan ("kr.hi.boot.dao")
public class MyBatisConfig {

	@ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //Mapper들의 위치를 지정
        //Mapper는 실제 쿼리가 작성된 xml 파일
        sessionFactory.setMapperLocations(
        		// mappers는 src/main/resources 폴더에 mappers 폴더가 있고
        		// 그 안에 xml 파일들을 읽어서 Mapper로 작업
            new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml")
        );
         // TypeAlias 적용
        sessionFactory.setTypeAliasesPackage("kr.hi.boot.model.vo");  // 여기에 패키지 경로 지정
        return sessionFactory.getObject();
    }
}