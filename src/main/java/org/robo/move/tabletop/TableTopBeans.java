package org.robo.move.tabletop;

import org.robo.move.apis.IRoboLocationValidator;
import org.robo.move.tabletop.validation.TableTopLocationValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableTopBeans {
	
	@Bean(name="tableTopValidator")
	public IRoboLocationValidator createTableTopLocationValidator() {
		int maxUnits = 5;
		return new TableTopLocationValidator(maxUnits);
	}
}
