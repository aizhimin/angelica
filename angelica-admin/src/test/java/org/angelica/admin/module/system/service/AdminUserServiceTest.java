package org.angelica.admin.module.system.service;

import org.angelica.admin.entity.system.AdminUser;
import org.angelica.admin.service.system.AdminUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserServiceTest {

	@Autowired
	private AdminUserService adminUserService;
	
	@Test
	public void testGetAdminUser() {
		Long id = 4L;
		AdminUser adminUser = adminUserService.getAdminUser(id);
		System.out.println(adminUser.toString());
	}
}
