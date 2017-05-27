package stockindexing.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan("stockindexing")
@MapperScan(value = { "stockindexing.dao" })
public class StockIndexingConfig extends WebSecurityConfigurerAdapter {
	private final Integer MAX_CONNECTION_SIZE = 50;

	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(MAX_CONNECTION_SIZE);
		return poolingHttpClientConnectionManager;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public BasicDataSource masterBasicDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource
				.setUrl("jdbc:mysql://localhost:3306/stock?allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setInitialSize(2);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(2);
		dataSource.setMaxWaitMillis(10000);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(2000000);
		dataSource.setValidationQuery("select 1");
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sessionFactory.setDataSource(masterBasicDataSource());
		return sessionFactory;
	}
}
