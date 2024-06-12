package com.econnect.econnect.basic;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
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

    public FTPClient connectFTP(){
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

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();//매우매우 중요!
            return this.ftp = ftp;
        }catch (IOException e){
            throw new RuntimeException("FPT Connection Exception!");
        }
    }

    public void init(){
        if(ftp == null) return;

        if(ftp.isConnected()){
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ftp = null;
            }
        }
    }


    public boolean uploadFileToFTP(MultipartFile file, String uid, String name){
        FTPClient ftp = connectFTP();
        try{
            if(!ftp.changeWorkingDirectory("/img/challenge_state/" + uid + "/")){
                ftp.makeDirectory("/img/challenge_state/" + uid + "/");
                ftp.changeWorkingDirectory("/img/challenge_state/" + uid + "/");
            }
            log.info("WorkingDirectory : {}", ftp.printWorkingDirectory());


            //log.info("fileBytes : {}", fileBytes.length);
            byte[] fileData = file.getBytes();

            return ftp.storeFile(name, new ByteArrayInputStream(fileData));
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            init();
        }
    }
}
