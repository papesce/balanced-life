package com.balance.life;

/**
 * this is just a test. delete with table2.jsp
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Test to see how to switch a view
 * @author Administrator
 *
 */
@Controller
public class TableController {
	
	@RequestMapping(value = "/add")
	public String request() {
		return "table2";
	}

}
