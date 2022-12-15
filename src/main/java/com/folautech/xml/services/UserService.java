package com.folautech.xml.services;

import com.folautech.xml.model.User;

public interface UserService {

    String convertUserToXml(User user);
    User convertXmlToUser(String xml);
}
