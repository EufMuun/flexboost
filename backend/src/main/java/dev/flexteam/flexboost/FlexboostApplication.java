package dev.flexteam.flexboost;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FlexboostApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FlexboostApplication.class);
		app.run();

//		@Bean
//		public WebMvcConfigurer corsConfigurer() {
//			return new WebMvcConfigurer() {
//				@Override
//				public void addCorsMappings(CorsRegistry registry) {
//					registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
//				}
//			};
//		}

	}

}
