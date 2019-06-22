package com.example.garminoauth10a;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;


public class GarminApi extends DefaultApi10a {

    private static final String AUTHORIZE_URL = "https://connectapi.garmin.com/oauth-service/oauth/request_token";
    private static final String REQUEST_TOKEN_RESOURCE = "https://connect.garmin.com/oauthConfirm?";
    private static final String ACCESS_TOKEN_RESOURCE = "https://connectapi.garmin.com/oauth-service/oauth/access_token";

    protected GarminApi() {
    }

    private static class InstanceHolder {
        private static final GarminApi INSTANCE = new GarminApi();
    }

    public static GarminApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_RESOURCE;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZE_URL;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return REQUEST_TOKEN_RESOURCE;
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return String.format(AUTHORIZE_URL, requestToken.getToken());
    }

    @Override
    public TokenExtractor<OAuth1AccessToken> getAccessTokenExtractor() {
        return OAuth1AccessGarminTokenExtractor.instance();
    }

    @Override
    public TokenExtractor<OAuth1RequestToken> getRequestTokenExtractor() {
        return OAuth1RequestGarminTokenExtractor.instance();
    }
}