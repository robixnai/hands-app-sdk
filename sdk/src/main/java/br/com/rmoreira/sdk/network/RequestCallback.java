package br.com.rmoreira.sdk.network;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public abstract class RequestCallback <Progress, Result, Errors> {

    public void onPreExecute() {}

    public void onProgressUpdate(Progress... progresses) {}

    public abstract void onSuccess(Result result);

    public abstract void onFailure(Errors errors);

}
