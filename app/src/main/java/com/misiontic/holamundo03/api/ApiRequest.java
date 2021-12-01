package com.misiontic.holamundo03.api;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.misiontic.holamundo03.model.Producto;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ApiRequest {
    
    
    public ArrayList<Producto> consultarProductos() {

        ArrayList<Producto> productoList = new ArrayList<Producto>();
        
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                URL productosEndpoint = null;

                try {
                    // Crea url
                    productosEndpoint = new URL("https://tienda-ca-api.herokuapp.com/api/pizzeria/productos");

                    // Crea conexion
                    HttpsURLConnection conexion = (HttpsURLConnection) productosEndpoint.openConnection();

                    // Headers
                    conexion.setRequestProperty("Dato", "Hola");

                    if (conexion.getResponseCode() == 200) {

                        InputStream responseBody = conexion.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);

                        JsonReader reader = new JsonReader(responseBodyReader);

                        String id = "";
                        String codigo = "";
                        String nombreProducto = "";
                        String desc_corta = "";
                        String desc_larga = "";
                        Double precio = 0.0;

                        String key = "";

                        reader.beginArray();
                        while (reader.hasNext()) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                key = reader.nextName();
                                if (key.equals("_id")) {
                                    id = reader.nextString();
                                } else if (key.equals("codigo")) {
                                    codigo = reader.nextString();
                                } else if (key.equals("nombre")) {
                                    nombreProducto = reader.nextString();
                                } else if (key.equals("desc_corta")) {
                                    desc_corta = reader.nextString();
                                } else if (key.equals("desc_larga")) {
                                    desc_larga = reader.nextString();
                                } else if (key.equals("precio")) {
                                    precio = reader.nextDouble();
                                } else {
                                    reader.skipValue();
                                }
                            }
                            reader.endObject();
                            productoList.add(new Producto(id, codigo,nombreProducto, desc_corta, desc_larga, precio));
                        }
                        reader.endArray();
                        reader.close();

                    } else {
                        // Mensaje
                    }
                    conexion.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return productoList;
    }
    
}
