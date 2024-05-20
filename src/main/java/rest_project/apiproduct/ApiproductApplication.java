package rest_project.apiproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import rest_project.apiproduct.model.Role;
import rest_project.apiproduct.model.ApplicationUser;
import rest_project.apiproduct.repository.RoleRepository;
import rest_project.apiproduct.repository.UserRepository;


@SpringBootApplication
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ApiproductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiproductApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}







// @SpringBootApplication
// public class ApiApplication {

//     public static void main(String[] args) {
//         SpringApplication.run(ApiApplication.class, args);
//     }
//    @Bean
// 	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
// 		return args ->{
// 			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
// 			Role adminRole = roleRepository.save(new Role("ADMIN"));
// 			roleRepository.save(new Role("USER"));

// 			Set<Role> roles = new HashSet<>();
// 			roles.add(adminRole);

// 			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles);

// 			userRepository.save(admin);
// 		};
// 	}
// }