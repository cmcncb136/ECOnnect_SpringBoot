package com.econnect.econnect.basic;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOError;
import java.io.IOException;


public class FTPService {
    private static FTPClient ftp = null;
    private static final Logger log = LoggerFactory.getLogger(FTPService.class);
    @Value("${ftp.server}")
    private String server;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    /* FTP 서버 연결
     */

    private FTPClient connectFTP(){
        if(ftp != null)
            return ftp;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("utf-8");

        try{
            ftp.connect(server, port);

            int replyCode = ftp.getReplyCode();
            log.info("replyCode : {}", replyCode);

            if(!FTPReply.isPositiveCompletion(replyCode))
                throw new RuntimeException("FTP server refused connection");

            if(!ftp.login(username, password))
                throw  new RuntimeException("FTP server login failed");

            return ftp;
        }catch (IOException e){
            throw new RuntimeException("FPT Connection Exception!");
        }
    }

    public boolean uploadFileToFTP(MultipartFile file, String uid, String name){
        FTPClient ftp = connectFTP();

        String remoteFilePath = uid + "/" + name;
        try{
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            byte[] fileBytes = file.getBytes();

            boolean rst =
                    ftp.storeFile(remoteFilePath, new ByteArrayInputStream(fileBytes));

            return rst;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
