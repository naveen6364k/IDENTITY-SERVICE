package com.innoclique.identity.service;

import com.innoclique.identity.repository.LanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    private LanguagesRepository languagesRepository;
}
