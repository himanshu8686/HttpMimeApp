package com.yash.httpmimeapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest {
    // Context context = null;

    String serviceUrl = "";
    private String json = null;
    Context mContext;

    //public static String BaseImageUrl = "http://internal.motherpod.org/orangecart-backend/index.php/Api/ItemImage/";
    public static String BaseImageUrl = "http://internal.motherpod.org/orangecart-merged-backend/index.php/Api/ItemImage/";

    // Production
    public String Baseurl = "http://internal.motherpod.org/orangecart-merged-backend/index.php/Api/";
    //public String Baseurl = "http://internal.motherpod.org/orangecart-backend/index.php/Api/";


    public HttpRequest(Context context) {
        super();
        mContext = context;
    }


    public String makeGetRequest(String mainurl) {
        String response = "";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(mainurl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            response = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        return response;
    }


    public String makeConnection(String serviceUrl, String entity) {
        String response = "", urlStr = "";
        String completeURL = "";
        try {
            urlStr = serviceUrl;
            HttpPost httpPost = new HttpPost(urlStr);
            StringEntity se = new StringEntity(entity, "UTF-8");
            httpPost.setEntity(se);
            httpPost.setHeader(HTTP.CONTENT_TYPE,
                    "application/json");
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            response = "Can't connect to server.";
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            response = "Can't connect to server.";
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            response = "Can't connect to server.";
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            response = "Can't connect to server.";
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            response = "Can't connect to server.";
            e.printStackTrace();
            return null;
        }

        return response;
    }


    public String getSubBrandList(String jsonData) {

        try {

            serviceUrl = Baseurl + "SubBrands";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "SubBrands");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getBrandItemList(String jsonData) {

        try {

            serviceUrl = Baseurl + "BrandItemList";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "BrandItemList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getItemLIst(String jsonData) {

        try {

            serviceUrl = Baseurl + "ItemList";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "ItemList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getSearchBrandandProduct(String jsonData) {

        try {

            serviceUrl = Baseurl + "SearchProductAndBrand";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "SearchProductAndBrand");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String CategoryAndSubcategoryItem(String jsonData) {

        try {

            serviceUrl = Baseurl + "CategoryAndSubcategoryItem";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "CategoryAndSubcategoryItem");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String GetProductDetails(String jsonData) {

        try {

            serviceUrl = Baseurl + "ItemDetailsWithPackaging";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "ItemDetailsWithPackaging");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String GetAllReview(String jsonData) {

        try {

            serviceUrl = Baseurl + "AllReview";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "AllReview");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String submitReviewRating(String jsonData) {

        try {

            serviceUrl = Baseurl + "submitFeedback";
            // serviceUrl = Baseurl + "submitReviewRating";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "submitFeedback");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String appFeedback(String jsonData) {

        try {

            serviceUrl = Baseurl + "Feedback";
            // serviceUrl = Baseurl + "submitReviewRating";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "Feedback");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String submitProductRating(String jsonData) {

        try {

            serviceUrl = Baseurl + "submitReviewRating";
            // serviceUrl = Baseurl + "submitReviewRating";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "submitReviewRating");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String myOrders(String jsonData) {

        try {

            serviceUrl = Baseurl + "MyOrders";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "MyOrders");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String ReturnOrder(String jsonData) {

        try {

            serviceUrl = Baseurl + "ReturnOrder";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "ReturnOrder");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String OrderDetails(String jsonData) {

        try {

            serviceUrl = Baseurl + "OrderDetails";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "OrderDetails");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String addToFavourite(String jsonData) {

        try {

            serviceUrl = Baseurl + "Item_Add_And_Remove_Favourite";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "Item_Add_And_Remove_Favourite");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getSearchResult(String jsonData) {

        try {

            serviceUrl = Baseurl + "Search";
            json = makeConnection(serviceUrl, jsonData);

            makeRequestedResponseAPI(jsonData, json, "Search");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String updateCart(String jsonData) {

        try {

            serviceUrl = Baseurl + "UpdateCart";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "UpdateCart");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String cartReview(String jsonData) {

        try {

            serviceUrl = Baseurl + "CartReview";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "CartReview");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getNotifications(String jsonData) {

        try {

            serviceUrl = Baseurl + "notification";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "notification");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String SubmitOrder(String jsonData) {

        try {

            serviceUrl = Baseurl + "SubmitOrder";
            json = makeConnection(serviceUrl, jsonData);



            makeRequestedResponseAPI(jsonData, json, "SubmitOrder");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String ApplyCoupndCode(String jsonData) {

        try {

            serviceUrl = Baseurl + "checkCouponCode";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "checkCouponCode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getSubCategory(String jsonData) {

        try {

            serviceUrl = Baseurl + "SubCategory";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "SubCategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String removeAndUpdateORder(String jsonData) {

        try {

            serviceUrl = Baseurl + "updateAndRemoveItem";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "updateAndRemoveItem");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String salesReturns(String jsonData) {

        try {

            serviceUrl = Baseurl + "salesReturns";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "salesReturns");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String CancelOrder(String jsonData) {

        try {

            serviceUrl = Baseurl + "CancelOrder";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "CancelOrder");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String AnimsDate() {
        try {

            serviceUrl = "http://10.0.2.2/anime.json";
           
            json = makeGetRequest(serviceUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getBrandItem() {

        try {

            serviceUrl = Baseurl + "Brands";
            json = makeGetRequest(serviceUrl);
            makeRequestedResponseAPI("", json, "Brands");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getBusinessCategory() {

        try {

            serviceUrl = Baseurl + "businessCategory";
            json = makeGetRequest(serviceUrl);
            makeRequestedResponseAPI("", json, "businessCategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getPrivacyPolicy(String jsonData) {

        try {

            serviceUrl = Baseurl + "AppContant";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "AppContant");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String signUp(String jsonData) {

        try {

            serviceUrl = Baseurl + "SignUp";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "SignUp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String updateProfileDetails(String jsonData) {

        try {

            serviceUrl = Baseurl + "updateProfileDetails";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "updateProfileDetails");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getContactUsDetail(String jsonData) {
        try {
            serviceUrl = Baseurl + "AppContant";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "AppContant");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getTradeOffer(String jsonData) {

        try {

            serviceUrl = Baseurl + "tradeOffer";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "tradeOffer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getBanners() {

        try {

            serviceUrl = Baseurl + "Banner";
            json = makeGetRequest(serviceUrl);
            makeRequestedResponseAPI("", json, "Banner");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getSubCategoryBrand(String jsonData) {

        try {

            serviceUrl = Baseurl + "AllBrandBySubcategory";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI("", json, "AllBrandBySubcategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getCancellationReasons() {

        try {

            serviceUrl = Baseurl + "Reasons";
            json = makeGetRequest(serviceUrl);
            makeRequestedResponseAPI("", json, "Reasons");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getReviewOptions() {

        try {

            serviceUrl = Baseurl + "ReviewOptions";
            json = makeGetRequest(serviceUrl);
            makeRequestedResponseAPI("", json, "Reasons");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getCategory() {

        try {

            serviceUrl = Baseurl + "Category";
            json = makeGetRequest(serviceUrl);
            makeRequestedResponseAPI("", json, "Category");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getHighDemand(String jsonData) {

        try {

            serviceUrl = Baseurl + "HeighDemandProduct";
            json = makeConnection(serviceUrl, jsonData);

            makeRequestedResponseAPI(jsonData, json, "HeighDemandProduct");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return json;
    }


    public String ForgotPin(String jsonData) {
        try {
            serviceUrl = Baseurl + "ForgotPin";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "ForgotPin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String RequestForProduct(String jsonData) {
        try {
            serviceUrl = Baseurl + "RequestForProduct";
            json = makeConnection(serviceUrl, jsonData);

            makeRequestedResponseAPI(jsonData, json, "RequestForProduct");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String MyWishlist(String jsonData) {
        try {
            serviceUrl = Baseurl + "MyWishlist";
            json = makeConnection(serviceUrl, jsonData);

            makeRequestedResponseAPI(jsonData, json, "MyWishlist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String MyPurcheslist(String jsonData) {
        try {
            serviceUrl = Baseurl + "MyPurcheslist";
            json = makeConnection(serviceUrl, jsonData);

            makeRequestedResponseAPI(jsonData, json, "MyPurcheslist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String CreatePin(String jsonData) {
        try {
            serviceUrl = Baseurl + "CreateNewPin";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "CreateNewPin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String SignUp(String jsonData) {
        try {
            serviceUrl = Baseurl + "SignUp";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "SignUp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String VerifyOtpNumber(String jsonData) {
        try {
            serviceUrl = Baseurl + "VerifyOtpNumber";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "VerifyOtpNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String getLoyaltyPoint(String jsonData) {
        try {
            serviceUrl = Baseurl + "MyLoyaltyPoints";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "MyLoyaltyPoints");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String AddNewStore(String jsonData) {
        try {
            serviceUrl = Baseurl + "AddNewStore";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "AddNewStore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getStore(String jsonData) {
        try {
            serviceUrl = Baseurl + "store";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "store");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String RemoveStore(String jsonData) {
        try {
            serviceUrl = Baseurl + "RemoveStore";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "RemoveStore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String StoreEnableAndDisable(String jsonData) {
        try {
            serviceUrl = Baseurl + "StoreEnableAndDisable";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "StoreEnableAndDisable");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String VerifyForgot(String jsonData) {
        try {
            serviceUrl = Baseurl + "Verify";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "Verify");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String AddTeamMember(String jsonData) {
        try {
            serviceUrl = Baseurl + "AddTeamMember";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "AddTeamMember");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String GetTeamMemberList(String jsonData) {
        try {
            serviceUrl = Baseurl + "TeamMemberList";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "TeamMemberList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    public String RemoveTeamMember(String jsonData) {
        try {
            serviceUrl = Baseurl + "RemoveTeamMember";
            json = makeConnection(serviceUrl, jsonData);
            makeRequestedResponseAPI(jsonData, json, "RemoveTeamMember");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    String LogUrl = "http://internal.motherpod.org/orangecart-merged-backend/index.php/Helper/logRequestResponseOfAPI";
    //String LogUrl = "http://internal.motherpod.org/orangecart-backend/index.php/Helper/logRequestResponseOfAPI";

    public void makeRequestedResponseAPI(String request, String response, String APINAME) {


        try {

            JSONObject sign_up_obj = new JSONObject();
            sign_up_obj.put("request", request);
            sign_up_obj.put("response", response);
            sign_up_obj.put("api", APINAME);
            String fake = makeConnection(LogUrl, sign_up_obj.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String Upload_pic(String selectedImagePath, String type) {

      /*  review_image
                documents
        banner
                brands
        category
                subcategory
      */

        String responsedata = "";
        try {
            FileBody filebodyVideo = new FileBody(new File(selectedImagePath));

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Baseurl + "upload?type=" + type);

            httppost.setHeader(new BasicHeader("Authorization", "Bearer "));
            httppost.setHeader(new BasicHeader("Accept", "application/json"));

           /* String AUTH_KEY = "YWRtaW46MTIzNA==";
            httppost.setHeader("Authorization", "Basic " + AUTH_KEY);
            httppost.setHeader("X-API-KEY", "123456");*/


            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("file", filebodyVideo);
            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            responsedata = EntityUtils.toString(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        makeRequestedResponseAPI(selectedImagePath, responsedata, "upload?type=" + type);
        return responsedata;
    }


    public String Upload_chat_image(Bitmap bitmap, String selectedImagePath) {


        String responsedata = "";
        try {

            File file_name = compressImage(bitmap, selectedImagePath);
            FileBody filebodyVideo = new FileBody(file_name);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Baseurl + "chatImageAudioUpload");
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("chatimgaudioUploader", filebodyVideo);
            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            responsedata = EntityUtils.toString(resEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responsedata;
    }


    public static File compressImage(Bitmap photo, String selectedImagePath) {

        File outputFile = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            FileOutputStream fos = new FileOutputStream(selectedImagePath);
            fos.write(byteArray);
            fos.close();
            outputFile = new File(selectedImagePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }

}
