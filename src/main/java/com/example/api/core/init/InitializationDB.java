package com.example.api.core.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: whydda
 * Date: 2020-01-15
 * Time: 오후 2:57
 */
@Slf4j
@Component
public class InitializationDB implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run(String... args) throws Exception {
                /*
        java 1.8에 업데이트된 try-resource 문법으로
        이와 같이 진행하면, try안에서 사용한 자원을 사용한 후 자동으로 제거(삭제)된다.
         */
        try (Connection connection = dataSource.getConnection()) {
            StringBuilder sql = new StringBuilder();

            // table 생성
            Statement statement2 = connection.createStatement();
            sql.setLength(0);
            sql.append("CREATE TABLE `oauth_client_details` (\n" +
                    "  `client_id` varchar(256) COLLATE utf8_unicode_ci NOT NULL,\n" +
                    "  `resource_ids` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `client_secret` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `scope` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `authorized_grant_types` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `web_server_redirect_uri` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `authorities` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `access_token_validity` int(11) DEFAULT NULL,\n" +
                    "  `refresh_token_validity` int(11) DEFAULT NULL,\n" +
                    "  `additional_information` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  `autoapprove` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`client_id`)\n" +
                    ")");
            statement2.executeUpdate(sql.toString());

            // table 생성
            Statement statement3 = connection.createStatement();
            sql.append("CREATE TABLE t_user (\n" +
                    "    user_id VARCHAR2(20) NOT NULL, /* 아이디 */\n" +
                    "    password VARCHAR2(20) NOT NULL, /* 패스워드 */\n" +
                    "    user_name VARCHAR2(50) NOT NULL /* 이름 */\n" +
                    ")");
            statement3.executeUpdate(sql.toString());

            jdbcTemplate.execute("INSERT INTO users VALUES ('whydda', '1234', '변진환')");
            jdbcTemplate.execute("NSERT INTO oauth_client_details\n" +
                    "(\n" +
                    "\tclient_id, \n" +
                    "\tclient_secret,\n" +
                    "\tresource_ids, \n" +
                    "\tscope, \n" +
                    "\tauthorized_grant_types, \n" +
                    "\tweb_server_redirect_uri, \n" +
                    "\tauthorities, \n" +
                    "\taccess_token_validity, \n" +
                    "\trefresh_token_validity, \n" +
                    "\tadditional_information, \n" +
                    "\tautoapprove\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(\n" +
                    "\t'client1',\n" +
                    "\t'client1pwd',\n" +
                    "\tnull, \n" +
                    "\t'read,write', \n" +
                    "\t'authorization_code,password, implicit, refresh_token',\n" +
                    "\tnull,\n" +
                    "\t'ROLE_YOUR_CLIENT',\n" +
                    "\t36000,\n" +
                    "\t2592000,\n" +
                    "\tnull,\n" +
                    "\tnull\n" +
                    ")");
        }

        log.info("테이블 등록 완료.");
    }
}
