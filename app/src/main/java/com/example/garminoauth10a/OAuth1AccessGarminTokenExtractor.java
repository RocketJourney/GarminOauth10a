package com.example.garminoauth10a;

import com.github.scribejava.core.model.OAuth1AccessToken;

public class OAuth1AccessGarminTokenExtractor extends AbstractOauth1GarminTokenExtractor<OAuth1AccessToken> {

    protected OAuth1AccessGarminTokenExtractor() {
    }

    @Override
    protected OAuth1AccessToken createToken(String token, String secret, String response) {
        return new OAuth1AccessToken(token, secret, response);
    }

    private static class InstanceHolder {

        private static final OAuth1AccessGarminTokenExtractor INSTANCE = new OAuth1AccessGarminTokenExtractor();
    }

    public static OAuth1AccessGarminTokenExtractor instance() {
        return InstanceHolder.INSTANCE;
    }
}