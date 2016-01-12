package ro.prc.android.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import ro.delegato.core.entities.ChannelEntity;

public interface ApiCalls {

    @POST("/SimpleJspServletDB/cnp/")
    void saveCNP(Callback<ChannelEntity> callback);

    @GET("/SimpleJspServletDB/cnp/")
    void getCNP(Callback<List<ChannelEntity>> callback);

    @GET("http://openapi.ro/api/validate/cnp/")
    void getChannels(Callback<List<ChannelEntity>> callback);

}
