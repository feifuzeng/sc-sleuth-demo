package com.feifz.scsleuthprovidera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

import brave.sampler.Sampler;

@SpringBootApplication
@RestController
public class ScSleuthProviderAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScSleuthProviderAApplication.class, args);
    }

    private static final Logger LOG = Logger.getLogger(ScSleuthProviderAApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/a-hi")
    public String callHome(){
        LOG.log(Level.INFO, "calling trace sc-sleuth-provider-a  ");
        return restTemplate.getForObject("http://localhost:8989/b-hello", String.class);
    }
    @RequestMapping("/a-hello")
    public String info(){
        LOG.log(Level.INFO, "calling trace sc-sleuth-provider-a ");

        return "i'm ssc-sleuth-provider-a";

    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
