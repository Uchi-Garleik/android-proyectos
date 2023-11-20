package com.example.myapplication.utils;

import com.example.myapplication.addproducts.data.DataProducts;
import com.example.myapplication.listUsers.data.DataListUsers;
import com.example.myapplication.listproducts.data.DataListMyProducts;
import com.example.myapplication.loginuser.data.DataUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
                                         @Query("idUser") int idUser
                                         );
    @GET("MyServlet")
    Call<DataListMyProducts> getDataListProducts(@Query("ACTION") String action);

    @GET("MyServlet")
    Call<DataListMyProducts> getDataListProducts(@Query("ACTION") String action, @Query("nombre") String nombre);

    @GET("MyServlet")
    Call<DataListMyProducts> getDataListProducts(@Query("ACTION") String action, @Query("idUser") int idUser);

    @GET("MyServlet")
    Call<DataListUsers> getDataListUsers(@Query("ACTION") String action);

    @GET("MyServlet")
    Call<DataListUsers> getDataListUsers(@Query("ACTION") String action, @Query("FILTER") String filter);

    @GET("MyServlet")
    Call<DataUsers> getDataLoginUser(@Query("ACTION") String action, @Query("username") String username, @Query("password") String password);



//    Call<MyData> getDataUser(@Query("ACTION") String action);
    /*@GET("MyServlet")
      Call<MyData> getDataUser(@Query("ACTION") String action,
                               @Query("EMAIL") String email,
                               @Query("PASSWORD") String pass);
*/

//
//    @GET("MyServlet")
//    Call<DataMovies> getDataMovies2(@Query("ACTION") String action);

        /*
        @GET("MyServlet")
          Call<MyData> getMyData(@Query("id") String id);

        @GET("MyServlet/{id}")
        Call<MyData> getMyDataURL(@Path("id") String id);*/

        /*
        @FormUrlEncoded
        @POST("/login")
        Call<ApiResponse> login(@Field("username") String username, @Field("password") String password);
    */
}
