package com.conradcrates.farepilottechnicalchallenge;

import com.conradcrates.farepilottechnicalchallenge.backend.NetworkCallback;
import com.conradcrates.farepilottechnicalchallenge.backend.NetworkResponse;
import com.conradcrates.farepilottechnicalchallenge.backend.StubbedRestClient;
import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Conrad on 09/10/2017.
 */

public class StubbedRestClientTests {

    private StubbedRestClient client;

    @Before
    public void beforeTest(){
        client = new StubbedRestClient();
    }

    @Test
    public void newSessionReturnsUserId(){
        client.newSession("", "", new NetworkCallback() {
            @Override
            public void onSuccess(NetworkResponse response) {
                Assert.assertNotNull(response.getValue(NetworkResponseConstants.USER_ID));
            }

            @Override
            public void onFailure(NetworkResponse response) {
                Assert.fail();
            }
        });
    }

    @Test
    public void clientReturnsEmailFromSession(){
        final String email = "example@example.com";
        client.newSession(email, "", null);
        client.getUserDetails(new NetworkCallback() {
            @Override
            public void onSuccess(NetworkResponse response) {
                Assert.assertEquals(email, response.getValue(NetworkResponseConstants.EMAIL));
            }

            @Override
            public void onFailure(NetworkResponse response) {
                Assert.fail();
            }
        });
    }
}