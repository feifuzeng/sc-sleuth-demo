package com.feifz.scsleuthproviderb;

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
public class ScSleuthProviderBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScSleuthProviderBApplication.class, args);
    }

    private static final Logger LOG = Logger.getLogger(ScSleuthProviderBApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/b-hi")
    public String callHome(){
        LOG.log(Level.INFO, "calling trace sc-sleuth-provider-b  ");
        return restTemplate.getForObject("http://localhost:8988/a-hello", String.class);
    }
    @RequestMapping("/b-hello")
    public String info(){
        LOG.log(Level.INFO, "calling trace sc-sleuth-provider-b ");
        return "i'm sc-sleuth-provider-b";
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }


}
