package Monitoring.Monitoring;

import Monitoring.Monitoring.infrastructure.PostgreSQL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootTest
@EnableSwagger2
class ApplicationTests extends PostgreSQL {

	@Test
	void contextLoads() {
	}

}
