package com.example.demo.controller;

import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.IndexPaperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("indexpaper")
@CrossOrigin(origins = "*",maxAge = 3600)
public class IndexPaperController {
    @Autowired(required = false)
    private IndexPaperImpl indexPaperImpl;

    @RequestMapping("getList")
    private Message indexPaperGetList(){
        return indexPaperImpl.getList();
    }
}
