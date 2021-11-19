package com.brq.internet.projetojspbase;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import view.Inicio;

import javax.swing.*;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "com.brq.internet.projetojspbase" })
public class ProjetoJspBaseApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		new Inicio();	
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProjetoJspBaseApplication.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
