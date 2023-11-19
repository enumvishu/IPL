package IPL.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import IPL.DAO.ManagementDAO;
import IPL.DTO.Management;

//@Controller
@RestController // it will do the work of controller also response body also.
public class ManagementController {

	@Autowired
	ManagementDAO managementDAO;

	@RequestMapping("managementsignup")
	// @ResponseBody
	public ModelAndView save(@ModelAttribute Management management) {

		managementDAO.saveManagement(management);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("msg", "Management Account got Created");

		modelAndView.setViewName("index.jsp");

		return modelAndView;

	}

	@RequestMapping("managementlogin")
	// @ResponseBody
	public ModelAndView mamangementLogin(@RequestParam String username, @RequestParam String password) {
		Management management = managementDAO.managementLogin(username, password);

		ModelAndView modelAndView = new ModelAndView();
		if (management == null) {
			modelAndView.addObject("msg", "Invalid Username");
			modelAndView.setViewName("managementlogin.jsp");

		} else {
			if (management.getPassword().equals(password)) {
				modelAndView.addObject("msg", "Management Login Succesfully");
				modelAndView.setViewName("managementhome.jsp");
			} else {
				modelAndView.addObject("msg", "Entered Invalid Password");
				modelAndView.setViewName("managementlogin.jsp");
			}
		}

		return modelAndView;

	}

}
