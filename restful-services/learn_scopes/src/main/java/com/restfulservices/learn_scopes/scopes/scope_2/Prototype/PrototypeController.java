package com.restfulservices.learn_scopes.scopes.scope_2.Prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrototypeController {

    @SuppressWarnings("unused")
    @Autowired
    private PrototypeBean bean1;

    @SuppressWarnings("unused")
    @Autowired
    private PrototypeBean bean2;

    @SuppressWarnings("unused")
    @Autowired
    private PrototypeBean bean3;

    // Output: => When you are making request, 2 different beans created of same
    // class
    // Prototype Bean Called
    // Prototype Bean Called
    // Hit this: http://localhost:8080/proto-req => and check
    @GetMapping("/proto-req")
    public void getProtoReq() {
        bean1.getMsg();
        bean2.getMsg();
        // bean3.getMsg(); => Third prototype bean obj will be created
    }

}
