package org.datafetch.service.controllers;

import com.intuit.ipp.services.QueryResult;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import org.datafetch.service.utils.ReadPropertyFile;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class QBApiController {
    private static final String clientId = ReadPropertyFile.getProp().getProperty("clientId");
    private static final String clientSecret = ReadPropertyFile.getProp().getProperty("clientSecret");
    private static final String redirectUrl = ReadPropertyFile.getProp().getProperty("redirectUrl");

    static OAuth2Config oAuth2Config =
            new OAuth2Config.OAuth2ConfigBuilder(clientId,clientSecret).callDiscoveryAPI(Environment.SANDBOX).buildConfig();


    OAuth2PlatformClient client = new OAuth2PlatformClient(oAuth2Config);

    public static String generateOauthUrl() throws InvalidRequestException {
        String csrf = oAuth2Config.generateCSRFToken();

        List<Scope> scopes = new ArrayList<Scope>();

        scopes.add(Scope.Accounting);
        String url = oAuth2Config.prepareUrl(scopes,redirectUrl,csrf);

        return url;
    }

    public static OAuth2Config getOAuth2Config() {
        return oAuth2Config;
    }

    public Map<String, String> getBearerToken(String authCode) throws OAuthException {
        Map<String, String> response = new HashMap<>();

        try {

            BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUrl);
            response.put("token", bearerTokenResponse.getAccessToken());
            response.put("refreshToken", new String(bearerTokenResponse.getRefreshToken()));
            return response;

        } catch (OAuthException e) {
            throw new OAuthException("Error at Token Generation + " + e.fillInStackTrace());
        }

    }


    public Map<String, Object> refreshToken(String refreshToken) throws OAuthException {
        Map<String, Object> response = new HashMap<String, Object>();
        try {

            BearerTokenResponse bearerTokenResponse = client.refreshToken(refreshToken);
            response.put("token", bearerTokenResponse.getAccessToken());
            response.put("refreshToken", bearerTokenResponse.getRefreshToken());

            return response;

        } catch (OAuthException e) {
            throw new OAuthException("Error at Token Refresh + " + e.fillInStackTrace());
        }


    }

}
