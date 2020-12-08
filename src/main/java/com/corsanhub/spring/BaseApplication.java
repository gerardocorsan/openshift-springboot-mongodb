package com.corsanhub.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SpringDataWebAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
//		HibernateJpaAutoConfiguration.class, })

@SpringBootApplication(exclude = { SpringDataWebAutoConfiguration.class, HibernateJpaAutoConfiguration.class, })
@EnableSwagger2
@EnableOpenApi
public class BaseApplication {

	private static Logger logger = LoggerFactory.getLogger(BaseApplication.class);

	@Value("${application.name}")
	private String applicationName;

	@Value("${application.version}")
	private String applicationVersion;

	private ApiInfo getApiInfo() {
		ApiInfo info = new ApiInfoBuilder().title("CORSAN " + applicationName + "	 API Info")
				.description("CORSAN " + applicationName + " API reference for developers").version(applicationVersion).build();
		return info;
	}

	@Bean
	public Docket api() {
		logger.info("Adding swagger support ...");
		Docket docket = new Docket(DocumentationType.OAS_30).groupName("public-api").apiInfo(getApiInfo()).select().paths(PathSelectors.any()).build();

		return docket;
	}

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(5);
		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		return threadPoolTaskScheduler;
	}

//	@Bean
//	public CommandLineRunner bootstrapData(BankAccountRepository repository) {
//		return (args) -> {
//			BigDecimal initialBalance = BigDecimal.valueOf(1000);
//			BankAccount bankAccount = new BankAccount(0L, initialBalance);
//			repository.save(bankAccount);
//		};
//	}

//	@Bean
//	public MongoClient mongoClient() {
//		return MongoClients.create();
//	}

//	@Bean
//	public MongoClientSettingsBuilderCustomizer clientSettingsBuilderCustomizer() {
//		return builder -> builder.streamFactoryFactory(NettyStreamFactoryFactory.builder().build());
//	}

//	@Bean
//	public SSLContext mongoSSLContext() throws GeneralSecurityException, IOException {
//		String trustStoreFile = "mongo.jks";
//		String trustStorePassword = "qwerty";
//		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//		try (InputStream in = new FileInputStream(trustStoreFile)) {
//			trustStore.load(in, trustStorePassword.toCharArray());
//		}
//		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//		trustManagerFactory.init(trustStore);
//
//		SSLContext sslContext = SSLContext.getInstance("TLS");
//		sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
//
//		return sslContext;
//	}

//	@Bean
//	public MongoClientSettingsBuilderCustomizer mongoSslCustomizer(SSLContext mongoSSLContext) {
//		return clientSettingsBuilder -> clientSettingsBuilder.applyToSslSettings(sslBuilder -> sslBuilder.context(mongoSSLContext));
//	}

//	@Bean
//	@ConditionalOnMissingBean(MongoClient.class)
//	public MongoClient mongo(MongoProperties properties, Environment environment, ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
//			ObjectProvider<MongoClientSettings> settings) {
//		return new MongoClientFactory(properties, environment, builderCustomizers.orderedStream().collect(Collectors.toList()))
//				.createMongoClient(settings.getIfAvailable());
//	}

	public static void main(String[] args) {
//		System.setProperty ("javax.net.ssl.trustStore", "src/main/resources/mongo.jks");
//		System.setProperty ("javax.net.ssl.trustStorePassword","qwerty");
		SpringApplication.run(BaseApplication.class, args);
	}

}
