package com.poe.demoBean;

import com.poe.demoBean.business.Person;
import com.poe.demoBean.business.PersonService;
import com.poe.demoBean.business.PersonStoreInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoBeanApplicationTests {

	@Autowired
	PersonService personService;

	@Test
	void testBeanInjection() {
		Assertions.assertNotNull(personService.getStore());

		PersonStoreInterface store = personService.getStore();
		Person person = new Person("Alain","Delon");
		store.add(person);
		store.add(person);

		Assertions.assertEquals(1, store.size());
	}
}
