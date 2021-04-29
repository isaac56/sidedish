package team16.sidedish.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team16.sidedish.dto.GithubEmailDTO;
import team16.sidedish.dto.GithubTokenDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private String GithubClientId = "Iv1.0905f186cda26835";
    private String GithubClientSecret = "3f3ac8c4e762bc8342c78a6b76c1e85da9298bb9";
    //client secret 값은 노출되면 안되므로, 서버 환경변수에 해당 값을 등록하여 코드에서 다음과 같이 불러와 사용할 수
    //private String clientSecret = System.getenv("GITHUB_CLIENT_SECRET")

    public GithubTokenDTO getAccessToken(String code) {
        String url = "https://github.com/login/oauth/access_token";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json"); //json 형식으로 응답 받음
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", GithubClientId);
        requestPayload.put("client_secret", GithubClientSecret);
        requestPayload.put("code", code);
        requestPayloads.setAll(requestPayload);

        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<GithubTokenDTO> response = new RestTemplate().postForEntity(url, request, GithubTokenDTO.class); //미리 정의해둔 GithubToken 클래스 형태로 Response Body를 파싱해서 담아서 리턴
        return response.getBody();
    }

    public GithubEmailDTO getEmailFromGithub(String accessToken) {
        String url = "https://api.github.com/user/emails";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json"); //json 형식으로 응답 받음
        header.put("Authorization", "Bearer " + accessToken);
        headers.setAll(header);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<GithubEmailDTO> emails = null;
        try {
            emails = objectMapper.readValue(response.getBody(), new TypeReference<List<GithubEmailDTO>>() {
            });
        } catch (IOException ex) {
            logger.debug(ex.getStackTrace().toString());
        }

        if (emails == null || emails.isEmpty()) {
            return null;
        }
        return emails.get(0);
    }
}
