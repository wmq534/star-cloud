package com.github.requestserialid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/price")
public class PriceController {
	Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/modifyPrice")
	public @ResponseBody String modifyPrice(BaseRequest baseinfo, String dataXml) {
	  logger.info(baseinfo.getInnerSerialId());
	  System.out.println(baseinfo.getInnerSerialId());
	  return baseinfo.getInnerSerialId();
	}
	
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/add")
    public @ResponseBody String add(String innerSerialId, String dataXml) {
      logger.info(innerSerialId);
      System.out.println(innerSerialId);
      return innerSerialId;
    }
}