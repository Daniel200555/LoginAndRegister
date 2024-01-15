package com.example.demo.service.connect;

import com.example.demo.exception.CreateDirectoryException;

import java.net.MalformedURLException;

public interface CreateDirectory {

    void create(String path) throws CreateDirectoryException, MalformedURLException;

}
