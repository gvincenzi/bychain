package org.byochain.model.repository.test;

import java.util.HashSet;
import java.util.Set;

import org.byochain.model.AppModel;
import org.byochain.model.entity.Role;
import org.byochain.model.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JUnit Test
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppModel.class })
@ActiveProfiles("test")
public class RoleRepositoryTest {
	@Autowired
	private RoleRepository serviceUnderTest;

	private static Set<Role> roles = new HashSet<>();

	@Before
	public void init() {
		if (roles.isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setRole("ROLE_ADMIN");
			
			Role roleUser = new Role();
			roleUser.setRole("ROLE_USER");
			
			roles.add(serviceUnderTest.save(roleAdmin));
			roles.add(serviceUnderTest.save(roleUser));
		}
	}
	
	@Test
	public void count() {
		Assert.assertEquals(roles.size(), serviceUnderTest.count());
	}

	@Test
	public void findById() {
		for (Role role : roles) {
			Assert.assertEquals(role, serviceUnderTest.findOne(role.getRoleId()));
		}
	}
	
	@Test
	public void findByName() {
		for (Role role : roles) {
			Assert.assertEquals(role, serviceUnderTest.find(role.getRole()));
		}
	}
}
