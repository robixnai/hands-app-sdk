package br.com.rmoreira.sdk;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import br.com.rmoreira.sdk.helpers.SdkHelpers;
import br.com.rmoreira.sdk.models.GpsTrackerModel;
import br.com.rmoreira.sdk.network.routers.UserDataRouter;
import br.com.rmoreira.sdk.pojo.UserData;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserDataUnitTest {

    @Mock
    Context mMockContext;

    @Test
    public void network_isConnected() throws Exception {
        assertTrue(SdkHelpers.isNetworkAvailable(mMockContext));
    }

    @Test
    public void gps_isEnabled() throws Exception {
        assertTrue(new GpsTrackerModel().canGetLocation());
    }

    @Test
    public void getFileCacheDir_isNull() throws Exception {
        assertNull(SdkApplication.getFileCacheDir());
    }

    @Test
    public void getPage_isListUserData() throws Exception {
        assertEquals(List.class, UserDataRouter.getInstance().getPage(1));
    }

}