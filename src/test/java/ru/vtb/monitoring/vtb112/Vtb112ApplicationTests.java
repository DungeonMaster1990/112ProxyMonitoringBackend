package ru.vtb.monitoring.vtb112;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vtb.monitoring.vtb112.infrastructure.PostgreSQL;

@SpringBootTest
//@EnableSwagger2
class Vtb112ApplicationTests extends PostgreSQL {

	@Test
	void contextLoads() {
	}

}
