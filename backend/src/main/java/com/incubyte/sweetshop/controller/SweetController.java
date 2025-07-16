package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sweetshop/api/sweet")
public class SweetController {

    @Autowired
    private SweetService sweetService;
}
