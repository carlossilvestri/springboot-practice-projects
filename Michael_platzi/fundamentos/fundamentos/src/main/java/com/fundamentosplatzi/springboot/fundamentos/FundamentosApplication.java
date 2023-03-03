package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(
			@Qualifier("componentTwoImplementation") ComponentDependency componentDependency,
			MyBean myBean,
			MyBeanWithDependency myBeanWithDependency,
			MyBeanWithProperties myBeanWithProperties,
			UserPojo userPojo,
			UserRepository userRepository,
			UserService userService
	){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ejemplosAnteriores();
		saveUserInDatabase();
		getInformatinoJpqlFromUser();
		saveWithErrorTransactional();
	}

	public void getInformatinoJpqlFromUser(){
		/*
		LOGGER.info("getInformatinoJpqlFromUser user by email is: " +
				userRepository.findByUserEmail("julie@gmail.com")
						.orElseThrow(() -> new RuntimeException("No se encontro el usuario"))
						);
		userRepository.findByNameAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		userRepository.findByName("John")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con findByName " + user));

		LOGGER.info("usuario por findByEmailAndName: " + userRepository.findByEmailAndName("daniela@gmail.com", "Daniela")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findByNameLike("%J%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike " + user));

		userRepository.findByNameOrEmail("user5", "user4@gmail.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));


		userRepository.findByBirthDateBetween(
				LocalDate.of(2021, 3, 1),
				LocalDate.of(2021, 4, 2))
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByBirthDateBetween " + user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLikeOrderByIdDesc " + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLikeOrderByIdDesc " + user));
	*/

		LOGGER.info("usuario por getAllByBirthDateAndEmail: " + userRepository.getAllByBirthDateAndEmail(
						LocalDate.of(2021, 7, 22),
						"daniela@gmail.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "testtransactional1@gmail.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "testtransactional1@gmail.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "testtransactional3@gmail.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "testtransactional4@gmail.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransactional(users);
		}catch(Exception e){
			LOGGER.error("Esta es una excepcion en saveTransactional " + e.getMessage());
		}
		userService.getAllUsers().stream().forEach(user -> LOGGER.info("Este es el usuario en el metodo trasactiona " + user));

	}
	private void saveUserInDatabase(){
		User user1 = new User("John", "john@gmail.com", LocalDate.of(2021, 3, 20));
		User user2 = new User("Julie", "julie@gmail.com", LocalDate.of(2021, 3, 21));
		User user3 = new User("Daniela", "daniela@gmail.com", LocalDate.of(2021, 7, 22));
		User user4 = new User("user4", "user4@gmail.com", LocalDate.of(2021, 7, 23));
		User user5 = new User("user5", "user5@gmail.com", LocalDate.of(2021, 11, 24));
		User user6 = new User("user6", "user6@gmail.com", LocalDate.of(2021, 2, 25));
		User user7 = new User("user7", "user7@gmail.com", LocalDate.of(2021, 3, 26));
		User user8 = new User("user8", "user8@gmail.com", LocalDate.of(2021, 4, 27));
		User user9  = new User("user9", "user9@gmail.com", LocalDate.of(2021, 5, 28));
		User user10  = new User("user10", "user10@gmail.com", LocalDate.of(2021, 6, 3));
		User user11  = new User("user11", "user11@gmail.com", LocalDate.of(2021, 7, 12));
		User user12  = new User("user12", "user12@gmail.com", LocalDate.of(2021, 9, 2));

		List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		users.stream().forEach(userRepository::save);


	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " " + userPojo.getPassword());
		LOGGER.error("Esto es un error");
	}
}
