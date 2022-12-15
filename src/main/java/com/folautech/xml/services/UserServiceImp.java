package com.folautech.xml.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.folautech.xml.model.User;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private XmlMapper xmlMapper;

    @PostConstruct
    public void init() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String convertUserToXml(User user) {
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            log.warn("JsonProcessingException, msg={}", e.getLocalizedMessage());
        }
        return xml;
    }

    @Override
    public User convertXmlToUser(String xml) {
        User user = null;
        try {
            user = xmlMapper.readValue(xml, User.class);
        } catch (JsonProcessingException e) {
            log.warn("JsonProcessingException, msg={}", e.getLocalizedMessage());
        }
        return user;
    }

}
