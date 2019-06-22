package com.example.garminoauth10a;

import com.github.scribejava.core.model.OAuth1RequestToken;

public class OAuth1RequestGarminTokenExtractor extends AbstractOauth1GarminTokenExtractor<OAuth1RequestToken> {

    protected OAuth1RequestGarminTokenExtractor() {
    }

    @Override
    protected OAuth1RequestToken createToken(String token, String secret, String response) {
        return new OAuth1RequestToken(token, secret, response);
    }

    private static class InstanceHolder {

        private static final OAuth1RequestGarminTokenExtractor INSTANCE = new OAuth1RequestGarminTokenExtractor();
    }

    public static OAuth1RequestGarminTokenExtractor instance() {
        return InstanceHolder.INSTANCE;
    }
}
