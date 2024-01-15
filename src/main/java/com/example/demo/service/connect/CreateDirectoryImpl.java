package com.example.demo.service.connect;

import com.example.demo.exception.CreateDirectoryException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


@Service
@Getter
public class CreateDirectoryImpl implements CreateDirectory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDirectoryImpl.class);

    private Environment environment;
    private String host;
    private String portS;
    private String link;

    public CreateDirectoryImpl(Environment environment) {
        this.environment = environment;
        this.host = environment.getProperty("ftp.host");
        this.portS = environment.getProperty("ftp.port");
    }

    @Override
    public void create(String path) throws CreateDirectoryException {
        this.link = ("http://"+ getHost() +":"+ getPort() +"/create/directory?path=" + path);
        LOGGER.info("URL");
        try {
            URL url = new URL(getLink());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            LOGGER.info("Connect");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200)
                throw new CreateDirectoryException("HttpResponseCode: " + responseCode + ", CANNOT CREATE DIRECTORY: " + path);
            else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext())
                    informationString.append(scanner.nextLine());
                scanner.close();
                System.out.println(informationString);
                LOGGER.info("Create directory: " + path + ", success!!!");
            }

        } catch (MalformedURLException e) {
            throw new CreateDirectoryException("Cannot create directory user, ERROR link!!!");
        } catch (IOException e) {
            throw new CreateDirectoryException("Cannot create directory user, ERROR connection!!!");
        }
    }

    public int getPort() {
        return Integer.parseInt(getPortS());
    }
}
