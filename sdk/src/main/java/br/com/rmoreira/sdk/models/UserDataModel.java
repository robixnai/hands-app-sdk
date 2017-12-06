package br.com.rmoreira.sdk.models;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import br.com.rmoreira.sdk.R;
import br.com.rmoreira.sdk.SdkApplication;
import br.com.rmoreira.sdk.network.RequestCallback;
import br.com.rmoreira.sdk.network.routers.UserDataRouter;
import br.com.rmoreira.sdk.pojo.UserData;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public class UserDataModel {

    private static UserDataRouter mRouter = UserDataRouter.getInstance();

    private RequestCallback mRequestCallback;

    public UserDataModel(RequestCallback requestCallback) {
        mRequestCallback = requestCallback;
    }

    @SuppressLint("StaticFieldLeak")
    public void getCount() {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected void onPreExecute() {
                mRequestCallback.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                Integer count = null;
                try {
                    count = mRouter.getCount();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return count;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                mRequestCallback.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Integer count) {
                if (count != null) {
                    mRequestCallback.onSuccess(count);
                } else {
                    mRequestCallback.onFailure(SdkApplication.getContext().getString(R.string.msg_network_get_error));
                }
            }

        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getAll() {
        new AsyncTask<Void, Void, List<UserData>>() {

            @Override
            protected void onPreExecute() {
                mRequestCallback.onPreExecute();
            }

            @Override
            protected List<UserData> doInBackground(Void... voids) {
                List<UserData> userDataList = null;
                try {
                    userDataList = mRouter.getAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return userDataList;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                mRequestCallback.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(List<UserData> userDataList) {
                if (userDataList != null) {
                    mRequestCallback.onSuccess(userDataList);
                } else {
                    mRequestCallback.onFailure(SdkApplication.getContext().getString(R.string.msg_network_get_error));
                }
            }

        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getPage(int page) {
        new AsyncTask<Integer, Void, List<UserData>>() {

            @Override
            protected void onPreExecute() {
                mRequestCallback.onPreExecute();
            }

            @Override
            protected List<UserData> doInBackground(Integer... page) {
                List<UserData> userDataList = null;
                try {
                    userDataList = mRouter.getPage(page[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return userDataList;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                mRequestCallback.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(List<UserData> userDataList) {
                if (userDataList != null) {
                    mRequestCallback.onSuccess(userDataList);
                } else {
                    mRequestCallback.onFailure(SdkApplication.getContext().getString(R.string.msg_network_get_error));
                }
            }

        }.execute(page);
    }

    @SuppressLint("StaticFieldLeak")
    public void getById(int id) {
        new AsyncTask<Integer, Void, UserData>() {

            @Override
            protected void onPreExecute() {
                mRequestCallback.onPreExecute();
            }

            @Override
            protected UserData doInBackground(Integer... id) {
                UserData userData = null;
                try {
                    userData = mRouter.getById(id[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return userData;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                mRequestCallback.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(UserData userData) {
                if (userData != null) {
                    mRequestCallback.onSuccess(userData);
                } else {
                    mRequestCallback.onFailure(SdkApplication.getContext().getString(R.string.msg_network_get_error));
                }
            }

        }.execute(id);
    }

    @SuppressLint("StaticFieldLeak")
    public void updateUserData(UserData userData) {
        new AsyncTask<Object, Void, UserData>() {

            @Override
            protected void onPreExecute() {
                mRequestCallback.onPreExecute();
            }

            @Override
            protected UserData doInBackground(Object... params) {
                UserData userDataResult = null;
                UserData userData = (UserData) params[0];
                try {
                    userDataResult = mRouter.updateUserData(userData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return userDataResult;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                mRequestCallback.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(UserData userData) {
                if (userData != null) {
                    mRequestCallback.onSuccess(userData);
                } else {
                    mRequestCallback.onFailure(SdkApplication.getContext().getString(R.string.msg_network_put_error));
                }
            }

        }.execute(userData);
    }

}
