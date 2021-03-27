package com.example.smartsoft.config;

import com.example.smartsoft.http.ValuteClientHttp;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {

    private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("windows-1251")
            .withMarshallerSchemaLocation("http://www.cbr.ru https://cbr.ru/StaticHtml/File/92172/ValCurs.xsd")
            .build();

    @Bean
    public Encoder feignEncoder() {
        return new JAXBEncoder(jaxbFactory);
    }

    @Bean
    public Decoder feignDecoder() {
        return  new JAXBDecoder(jaxbFactory);
    }

    private final String valuteCursUrl = "http://www.cbr.ru/scripts";

    @Bean
    public ValuteClientHttp valuteClient() {
        return Feign.builder()
//                .encoder(feignEncoder())
                .decoder(feignDecoder())
                .target(ValuteClientHttp.class, valuteCursUrl);
    }
}
