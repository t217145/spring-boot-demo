package com.cyrus822.demo.redis.session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/session")
public class SessionController {

    private static final String SESSIONKEY = "myKey";
	
    @Value("${POD_NAME:unknown}")
    private String podName;

    @Value("${POD_IP:unknown}")
    private String podIp;	

    @GetMapping("/set/{value}")
    public String setSessionAttribute(HttpSession session, @PathVariable("value") String value, ModelMap map) {
        session.setAttribute(SESSIONKEY, value);
        map.addAttribute("value", value);
		map.addAttribute("pod", podName + ":" + podIp);
        return "index";
    }

    @GetMapping("/get")
    public String getSessionAttribute(HttpSession session, ModelMap map) {
        String value = (String) session.getAttribute(SESSIONKEY);
        value = (value == null) ? "No value" : value;
        map.addAttribute("value", value);
		map.addAttribute("pod", podName + ":" + podIp);
        return "index";
    }
}