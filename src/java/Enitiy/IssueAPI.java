package Enitiy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IssueAPI {

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
//                        System.out.println(respon.getStatusLine().getStatusCode());
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
//                            try {
                                jsonarr.put(jsonnew.get(i));
//                            } catch (JSONException ex) {
//                                ex.printStackTrace();
//                            }
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

    public static JSONArray getDataLinks(String pr, String pr_id, String token, int iid, String links) {
        JSONArray jsonarr = new JSONArray();
        try {
            URI dumUri = new URI("https://gitlab.com/api/v4/" + pr + "/" + pr_id + "/issues/" + iid + "/" + links);
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
        System.out.println(getData("projects", "37581416", "glpat-Jco-Wu1xsAaycxsbFzby"));
        System.out.println(getDataLinks("projects", "37581416", "glpat-Jco-Wu1xsAaycxsbFzby", 5, "links"));
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet get = new HttpGet("https://gitlab.com/api/v4/projects/36006741/issues");
//        get.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + "glpat-eYTmC8TzcsAz76MrCDRU");
//        try {
//            HttpResponse respone = client.execute(get);
//            System.out.println(respone.getStatusLine().getStatusCode());
////            System.out.println(IOUtils.toString(respone.getEntity().getContent()));
//            try {
//                JSONArray json = new JSONArray(IOUtils.toString(respone.getEntity().getContent()));
////                System.out.println(json);
//                for (int i = 0; i < json.length(); i++){
//                    JSONObject jsonobj = json.getJSONObject(i);
//                    System.out.println(jsonobj.get("title"));
//                }
//            } catch (JSONException ex) {
//                ex.printStackTrace();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

}
