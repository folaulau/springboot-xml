package com.folautech.xml;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.folautech.xml.model.Address;
import com.folautech.xml.model.User;
import com.folautech.xml.services.UserService;

@SpringBootTest
public class UserServiceImpTests {

    @Autowired
    private UserService userService;

    @Test
    public void itShouldCovertObjectToXml() {
        User user = User.builder()
                .firstName("Folau")
                .lastName("Kaveinga")
                .email("folaukaveinga@gmail.com")
                .dob(new Date())
                .address(Address.builder().street("123 st").city("Lehi").state("CA").zipcode("12345").build())
                .build();

        System.out.println(user.toString());

        String xml = userService.convertUserToXml(user);

        System.out.println(xml);
        
        assertThat(xml).isNotNull();

    }

    @Test
    public void itShouldCovertXmlToObject() {

        String xml = "<User><firstName>Folau</firstName><lastName>Kaveinga</lastName><dob>2022-12-15</dob><email>folaukaveinga@gmail.com</email><address><street>123 st</street><street2/><city>Lehi</city><zipcode>12345</zipcode><state>CA</state></address></User>";

        System.out.println(xml);

        User user = userService.convertXmlToUser(xml);

        System.out.println(user.toString());
        
        assertThat(user).isNotNull();

    }
}
