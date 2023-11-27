package com.example.myapplication.utils;

import com.example.myapplication.addproducts.data.DataProducts;
import com.example.myapplication.listUsers.data.DataListUsers;
import com.example.myapplication.listcategoriedproducts.data.DataListCategoriedProducts;
import com.example.myapplication.listproducts.data.DataListMyProducts;
import com.example.myapplication.listrateusers.data.DataListRateUsers;
import com.example.myapplication.loginuser.data.DataUsers;
import com.example.myapplication.rateusers.data.DataRateUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("MyServlet")
    Call<DataProducts> getDataAddProduct(@Query("ACTION") String action,
                                         @Query("nombre") String nombre,
                                         @Query("descripcion") String descripcion,
                                         @Query("categoria") String categoria,
                                         @Query("marca") String marca,
                                         @Query("talla") String talla,
                                         @Query("estado") String estado,
                                         @Query("precio") Double precio,
                                         @Query("moneda") String moneda,
                                         @Query("idUser") int idUser,
                                         @Query("image") String image);

    @GET("MyServlet")
    Call<DataRateUser> getDataRateUser(@Query("ACTION") String action, @Query("idRatedUser") int idRatedUser, @Query("idRatingUser") int idRatingUser, @Query("rating") double rating);

    @GET("MyServlet")
    Call<DataListRateUsers> getDataListRateUsers(@Query("ACTION") String action, @Query("idUser") int idUser);

    @GET("MyServlet")
    Call<DataListRateUsers> getDataListRateUsers(@Query("ACTION") String action, @Query("idUser") int idUser, @Query("filter") String filter);

    @GET("MyServlet")
    Call<DataListMyProducts> getDataListProducts(@Query("ACTION") String action);

    @GET("MyServlet")
    Call<DataListMyProducts> getDataListProducts(@Query("ACTION") String action, @Query("nombre") String nombre);

    @GET("MyServlet")
    Call<DataListMyProducts> getDataListProducts(@Query("ACTION") String action, @Query("idUser") int idUser);

    @GET("MyServlet")
    Call<DataListCategoriedProducts> getDataListCategoriedProducts(@Query("ACTION") String action, @Query("categoria") String categoria);

    @GET("MyServlet")
    Call<DataListUsers> getDataListUsers(@Query("ACTION") String action);

    @GET("MyServlet")
    Call<DataListUsers> getDataListUsers(@Query("ACTION") String action, @Query("FILTER") String filter);

    @GET("MyServlet")
    Call<DataUsers> getDataLoginUser(@Query("ACTION") String action, @Query("username") String username, @Query("password") String password);

    @Multipart
    @POST("MyServlet")
    Call<DataProducts> getDataAddProduct(@Query("ACTION") String action,
                                         @Query("nombre") String nombre,
                                         @Query("descripcion") String descripcion,
                                         @Query("categoria") String categoria,
                                         @Query("marca") String marca,
                                         @Query("talla") String talla,
                                         @Query("estado") String estado,
                                         @Query("precio") Double precio,
                                         @Query("moneda") String moneda,
                                         @Query("idUser") int idUser, @Part MultipartBody.Part image);
}
