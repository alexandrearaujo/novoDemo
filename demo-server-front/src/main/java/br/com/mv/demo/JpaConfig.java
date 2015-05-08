package br.com.mv.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
public class JpaConfig {
	
//	private static final String[] NO_PACKAGES = new String[0];

//	private ConfigurableListableBeanFactory beanFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JpaProperties jpaProperties;
	
	@Autowired(required = false)
	private JtaTransactionManager jtaTransactionManager;

//	@Override
	protected String[] getPackagesToScan() {
		// TODO Auto-generated method stub
		// return super.getPackagesToScan();
		List<String> packages = new ArrayList<String>();
		packages.add("org.springframework.data.jpa.domain.support");
		packages.add("br.com.mv.geral.model");
		packages.add("br.com.mv.modulo.model");
		packages.add("br.com.mv.demo");
		packages.add("br.com.mv.demo.model");
		// packages.addAll(Arrays.asList(super.getPackagesToScan()));

		return packages.toArray(new String[0]);
	}

//	@Bean
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//	}

//	@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//		return new JpaTransactionManager(emf);
//	}
	
	@Bean
	@ConditionalOnMissingBean(PlatformTransactionManager.class)
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public JpaVendorAdapter jpaVendorAdapter() {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(this.jpaProperties.isShowSql());
		adapter.setDatabase(this.jpaProperties.getDatabase());
		adapter.setDatabasePlatform(this.jpaProperties.getDatabasePlatform());
		adapter.setGenerateDdl(this.jpaProperties.isGenerateDdl());
		return adapter;
	}

//	@Bean
//	public JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//		jpaVendorAdapter.setDatabase(Database.H2);
//		jpaVendorAdapter.setGenerateDdl(true);
//		return jpaVendorAdapter;
//	}
	
//	@Bean
//	@Primary
//	@ConditionalOnMissingBean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//			EntityManagerFactoryBuilder factoryBuilder) {
//		Map<String, Object> vendorProperties = getVendorProperties();
//		customizeVendorProperties(vendorProperties);
//		return factoryBuilder.dataSource(this.dataSource).packages(getPackagesToScan())
//				.properties(vendorProperties).jta(isJta()).build();
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
		lemfb.setDataSource(this.dataSource);
		lemfb.setJpaVendorAdapter(jpaVendorAdapter());
		lemfb.setPackagesToScan(getPackagesToScan());
		return lemfb;
	}

//	protected abstract AbstractJpaVendorAdapter createJpaVendorAdapter();
	
//	protected abstract Map<String, Object> getVendorProperties();
	
	/**
	 * Customize vendor properties before they are used. Allows for post processing (for
	 * example to configure JTA specific settings).
	 * @param vendorProperties the vendor properties to customize
	 */
	protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
	}
	
//	protected String[] getPackagesToScan() {
//		if (AutoConfigurationPackages.has(this.beanFactory)) {
//			List<String> basePackages = AutoConfigurationPackages.get(this.beanFactory);
//			return basePackages.toArray(new String[basePackages.size()]);
//		}
//		return NO_PACKAGES;
//	}
	
	/**
	 * Returns if a JTA {@link PlatformTransactionManager} is being used.
	 * @return if a JTA transaction manager is being used
	 */
	protected final boolean isJta() {
		return (this.jtaTransactionManager != null);
	}
}
