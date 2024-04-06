package com.Correncies_Converter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class Update {
    Scanner s = new Scanner(System.in);

    public String fvrt_cur_add(String base_url) throws IOException {
        String url = (base_url + "list?access_key=aea9454f1db92a8ba993023c14d7fe19");
        System.out.println("Enter Favorite Currency Name");
        String fvrt_c = s.nextLine().toUpperCase();
        api_Handler ap = new api_Handler();
        JSONObject jsonObject = ap.api_handling_meth(url);
        JSONObject js = (JSONObject) jsonObject.get("currencies");
        String a = (String) js.get(fvrt_c);
        return fvrt_c;
    }

    public String delet() {
        System.out.println("Enter the name of the currency to be deleted");
        String fvrt_c = s.nextLine().toUpperCase();
        return fvrt_c;
    }

}

class api_Handler {

    public JSONObject api_handling_meth(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        return jsonObject;
    }
}

class Converterr {

    public void Currency_convert_meth(String base_link) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("________________________________________");
        System.out.println("Currency Converter");
        System.out.println("________________________________________");
        System.out.println("Enter Currency name to convert from");
        String Frome_name = sc.nextLine().toUpperCase();
        System.out.println("Enter Currency name to convert to");
        String To_name = sc.nextLine().toUpperCase();
        System.out.println("Enter Amount to convert " + Frome_name + " - " + To_name);
        BigDecimal amount = sc.nextBigDecimal();
        System.out.println("________________________________________");
        String url = (base_link + "convert?access_key=aea9454f1db92a8ba993023c14d7fe19&from="
                + Frome_name + "&to=" + To_name + "&amount=" + amount);
        api_Handler ap = new api_Handler();
        JSONObject jsonObject = ap.api_handling_meth(url);
        System.out.println(amount + " " + Frome_name + " Converted to " + To_name + " = " + jsonObject.get("result"));
    }

    public void currency_list(String base_url) throws IOException {
        String url = (base_url + "list?access_key=aea9454f1db92a8ba993023c14d7fe19");
        api_Handler ap = new api_Handler();
        JSONObject jsonObject = ap.api_handling_meth(url);
        JSONObject js = (JSONObject) jsonObject.get("currencies");
        String a = (String) js.toString();
        System.out.println(a);
    }

}

public class Correnci_Converter {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String base_url = "http://api.exchangerate.host/";

        List<String> arr = new ArrayList<>();
        Converterr c = new Converterr();
        Update f = new Update();
        boolean sto = true;

        for (int i = 0; sto; i++) {
            System.out.println("________________________________________________");
            System.out.println("Currency Convert => 1");
            System.out.println("View All Currency => 2");
            System.out.println("Add favorite Currency => 3");
            System.out.println("Delete Currency in favorite list => 4");
            System.out.println("View favorite Currency List => 5");
            System.out.println("Exite press => 0");
            System.out.println("________________________________________________");

            System.out.println("Choose a option");
            int a = s.nextInt();
            try {
                if (a == 1) {
                    c.Currency_convert_meth(base_url);
                } else if (a == 2) {
                    c.currency_list(base_url);
                } else if (a == 3) {
                    String fvt_c = f.fvrt_cur_add(base_url);
                    try {
                        if (!arr.contains(fvt_c)) {
                            arr.add(fvt_c);
                            System.out.println("your favorite Currency " + fvt_c + " added sucssfully");
                        } else if (fvt_c == null) {
                            System.out.println("Currency Is Not Valid");
                        } else {
                            System.out.println("This Currency is already added in favorite list");
                        }
                    } catch (Exception e) {
                        System.out.println("something error");
                    }
                } else if (a == 4) {
                    String delet_curr = f.delet();
                    if (arr.contains(delet_curr)) {
                        arr.removeIf(e -> e.equals(delet_curr));
                        System.out.println(delet_curr + " Currency Remove From Favorite List");
                    } else {
                        System.out.println(delet_curr + " Currency Is Not Available In Favorite List");
                    }
                } else if (a == 5) {
                    System.out.println(arr);
                } else if (a == 0) {
                    sto = false;
                    System.out.println("You Exite This Program Sucessfully ");
                    System.out.println("Thank You For Using Currency Converter");
                } else {
                    System.out.println("Enter Right Number");
                }

            } catch (Exception e) {
                System.out.println("You enter Number/Value is not valid please enter right value ");
            }

        }

    }
}
