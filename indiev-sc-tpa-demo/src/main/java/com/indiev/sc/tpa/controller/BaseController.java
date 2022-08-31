package com.indiev.sc.tpa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Murray Law
 */
@CrossOrigin(
//        originPatterns = {"*"},
        allowCredentials = "true",
        allowedHeaders = {"*"},
        methods = {})
public class BaseController {

}
