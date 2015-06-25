package cn.godzilla.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.godzilla.model.SvnCmdLog;
import cn.godzilla.service.SvnCmdLogService;

@Controller
@RequestMapping("/svncmdlog")
public class SvnCmdLogController {
	
	private final Logger logger = LogManager.getLogger(SvnCmdLogController.class);
	
	@Autowired
	SvnCmdLogService service ;
	
	@RequestMapping("/insert")
	public ModelAndView insert(){
		
		logger.debug("*****SvnCmdLogController.insert*****");
		ModelAndView view = new ModelAndView();
		
		SvnCmdLog model = new SvnCmdLog();
		model.setCommands("test");
		service.insert(model);
		
		view.setViewName("index");
		return view;
	}

}