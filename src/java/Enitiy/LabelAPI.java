package Enitiy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;

public class LabelAPI {
    public static JSONArray getData(String pr, String pr_id, String token) {
        JSONArray jsonarr = new JSONArray();
        int count = 1;
        try {
            URI dumUri = new URI("https://gitlab.com/api/v4/" + pr + "/" + pr_id + "/issues");
            HttpClient client = HttpClientBuilder.create().build();
            while (true) {
                HttpUriRequest httpRequest = RequestBuilder.get()
                        .setUri(dumUri)
                        .addParameter("page", count + "")
                        .build();
                httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                httpRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                try {
                    HttpResponse respon = client.execute(httpRequest);
                    if (respon.getStatusLine().getStatusCode() != 200) {
                        return null;
                    }
                    String source = IOUtils.toString(respon.getEntity().getContent(), "UTF-8");
                    if (source.equals("[]")) {
                        break;
                    } else {
                        JSONArray jsonnew;
                        try {
                            jsonnew = new JSONArray(source);
                            for (int i = 0; i < jsonnew.length(); i++) {
                                jsonarr.put(jsonnew.get(i));
                            }
                            count++;
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return jsonarr;
    }

    public static JSONArray getDataLinks(String pr, String pr_id, String token, int sid, String links) {
        JSONArray jsonarr = new JSONArray();
        try {
            URI dumUri = new URI("https://gitlab.com/api/v4/" + pr + "/" + pr_id + "/labels/" + sid + "/" + links);
            HttpClient client = HttpClientBuilder.create().build();
            HttpUriRequest httpRequest = RequestBuilder.get()
                    .setUri(dumUri)
                    .build();
            httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            try {
                HttpResponse respon = client.execute(httpRequest);
                if (respon.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                String source = IOUtils.toString(respon.getEntity().getContent(), "UTF-8");
                if (source.equals("[]")) {
                    return null;
                } else {
                    try {
                        jsonarr = new JSONArray(source);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return jsonarr;
    }
    
    public static void main(String[] args) {
        
    }
}
