package com.cyrus822.demo.ejb.simple.configurations;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import comps368.u6.basic.DemoBeanRemote;
import java.util.Properties;

@Configuration
public class SimpleEJBConfig {
    @Bean
    public DemoBeanRemote helloBean() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        Context context = new InitialContext(props);
        return (DemoBeanRemote) context
                .lookup("ejb/DemoBeanRemoteRef");
    }
}