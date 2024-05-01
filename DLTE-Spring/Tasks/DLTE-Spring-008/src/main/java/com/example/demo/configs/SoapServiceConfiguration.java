package com.example.demo.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import java.util.Collections;

@EnableWs
@Configuration //This class is annotated with @Configuration to indicate that it contains bean definitions and configuration for the application.
public class SoapServiceConfiguration extends WsConfigurerAdapter {
//    //conversion xsd to wsdl
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/transactionsrepo/*");
    }

    //wsdl properties
    @Bean(name="transactions")
        public DefaultWsdl11Definition convertToWsdl1(XsdSchema xsdSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("TransactionsPort");
        defaultWsdl11Definition.setTargetNamespace("http://transactions.services");
        defaultWsdl11Definition.setLocationUri("/transactionsrepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }
    //identify the xsd
    @Bean
    public XsdSchema transactionsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("transactions2.xsd"));
       // return new SimpleXsdSchema(new ClassPathResource("\"C:\\Users\\xxshetur\\Desktop\\transactions.xsd\""));
//        String xsdFilePath ="C:/Users/xxshetur/Desktop/transactions.xsd";
//        FileSystemResource xsdFileResource = new FileSystemResource(xsdFilePath);
        //return new SimpleXsdSchema(xsdFileResource);
    }


//@Bean
//public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
//    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//    servlet.setApplicationContext(applicationContext);
//    return new ServletRegistrationBean<>(servlet, "/ws/*");
//}
//
//    @Bean(name = "transactions")
//    public SimpleWsdl11Definition transactionsWsdl11Definition() {
//        // Load existing WSDL file from classpath
//        return new SimpleWsdl11Definition(new ClassPathResource("transactions.wsdl"));
//    }



}
