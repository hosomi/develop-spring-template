package jp.template.ws.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientAppConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("jp.template.ws.user.wsdl");
		return marshaller;
	}
	@Bean
	public UserClient studentClient(Jaxb2Marshaller marshaller) {
		UserClient client = new UserClient();
		client.setDefaultUri("http://localhost:8080/ws/user.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
