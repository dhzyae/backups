package com.mouse.springbootinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @Value("${person.name}")
    private String name2;

    @Value("${address[0]")
    private String address1;

    @Value("${msg1}")
    private String msg1;
    @Value("${msg2}")
    private String msg2;

    @Autowired
    private Environment evn;

    @Autowired
    private Person person;

    @RequestMapping("/hello0")
    public String hello0() {
        System.out.println(name);
        System.out.println(name2);
        System.out.println(address1);
        System.out.println(msg1);
        System.out.println(msg2);
        System.out.println("--------------------------");
        System.out.println(evn.getProperty("person.name"));
        System.out.println("--------------------------");
        System.out.println(person);
        String[] address = person.getAddress();
        for (String s : address) {
            System.out.println(s);
        }
        return "Hello Spring Boot 0!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot 1!";
    }

}
