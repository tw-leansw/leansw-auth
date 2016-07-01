package org.thoughtworks.lean.identity;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthServer.class)
@IntegrationTest("server.port=0")
public class ClientCredentialsProviderTests {


    @Rule
    public HttpTestUtils http = HttpTestUtils.standard();

    @Rule
    public OAuth2ContextSetup context = OAuth2ContextSetup.standard(http);

    private static String globalCheckTokenPath;
    private static String globalTokenKeyPath;

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private SecurityProperties security;

    @Autowired
    private ServerProperties server;

    /**
     * tests the check_token endpoint
     */
    @Test
    @Ignore
    @OAuth2ContextConfiguration(ClientCredentials.class)
    public void testCheckToken() throws Exception {
        OAuth2AccessToken token = context.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = new TestRestTemplate("my-client-with-secret", "secret").exchange(http
                        .getUrl(globalCheckTokenPath), HttpMethod.POST,
                new HttpEntity<String>("token=" + token.getValue(), headers), Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) response.getBody();
        assertTrue(map.containsKey(AccessTokenConverter.EXP));
        assertEquals("my-client-with-secret", map.get(AccessTokenConverter.CLIENT_ID));
    }

    @Test
    @Ignore
    public void testTokenKey() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = new TestRestTemplate("my-client-with-secret", "secret")
                .getForEntity(http.getUrl(globalTokenKeyPath), Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) response.getBody();
        assertTrue(map.containsKey("alg"));
    }

    protected static class ClientCredentials extends ClientCredentialsResourceDetails {
        public ClientCredentials(Object target) {
            setClientId("my-client-with-secret");
            setClientSecret("secret");
            setScope(Arrays.asList("read"));
            setId(getClientId());
        }
    }

    protected AccessTokenProvider createAccessTokenProvider() {
        return null;
    }

    @Value("/oauth/check_token")
    public void setCheckTokenPath(String tokenPath) {
        globalCheckTokenPath = tokenPath;
    }


    @Value("/oauth/token_key")
    public void setTokenKeyPath(String tokenKeyPath) {
        globalTokenKeyPath = tokenKeyPath;
    }

}