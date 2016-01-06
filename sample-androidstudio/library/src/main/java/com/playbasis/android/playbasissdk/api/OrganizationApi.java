package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Node;
import com.playbasis.android.playbasissdk.model.Organization;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TorIsHere on 1/6/2016 AD.
 */
public class OrganizationApi extends Api {

    public static final String TAG = "OrganizationApi";

    private static void getOrganizations(@NonNull Playbasis playbasis, String organizeId, String searchName, String sort, String order,
                                         Integer offset, Integer limit, final OnResult<ArrayList<Organization>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/organizes";
        List<NameValuePair> params = new ArrayList<>();
        if (organizeId != null) params.add(new BasicNameValuePair("id",organizeId));
        if (searchName != null) params.add(new BasicNameValuePair("search",searchName));
        if (sort != null) params.add(new BasicNameValuePair("sort",sort));
        if (order != null) params.add(new BasicNameValuePair("order",order));
        if (offset != null) params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        if (limit != null) params.add(new BasicNameValuePair("limit",String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ArrayList<Organization> organizations = new ArrayList<Organization>();
                try {
                    JSONArray jsonArray = result.getJSONArray("result");
                    for(int i=0;i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Organization organization = JsonHelper.FromJsonObject(jsonObject, Organization.class);

                        JSONObject parentObject = jsonObject.getJSONObject("parent");
                        organization.setParentId(parentObject.getString("id"));
                        organization.setParentName(parentObject.getString("name"));

                        organizations.add(organization);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(listener != null) listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if(listener != null) listener.onError(error);
            }
        });
    }

    public static void getOrganizationById(@NonNull Playbasis playbasis, @NonNull String organizeId, String sort, String order,
                                           Integer offset, Integer limit, final OnResult<ArrayList<Organization>> listener) {
        getOrganizations(playbasis, organizeId, null, sort, order, offset, limit, listener);
    }

    public static void getOrganizationByName(@NonNull Playbasis playbasis, @NonNull String searchName, String sort, String order,
                                             Integer offset, Integer limit, final OnResult<ArrayList<Organization>> listener) {
        getOrganizations(playbasis, null, searchName, sort, order, offset, limit, listener);
    }

    private static void getNodes(@NonNull Playbasis playbasis, String nodeId, String organizeId, String parentId, String searchName, String sort, String order,
                                 Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/nodes";
        List<NameValuePair> params = new ArrayList<>();
        if (nodeId != null) params.add(new BasicNameValuePair("id",nodeId));
        if (organizeId != null) params.add(new BasicNameValuePair("organize_id",organizeId));
        if (parentId != null) params.add(new BasicNameValuePair("parent_id",parentId));
        if (searchName != null) params.add(new BasicNameValuePair("search",searchName));
        if (sort != null) params.add(new BasicNameValuePair("sort",sort));
        if (order != null) params.add(new BasicNameValuePair("order",order));
        if (offset != null) params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        if (limit != null) params.add(new BasicNameValuePair("limit",String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ArrayList<Node> nodes = new ArrayList<Node>();
                try {
                    JSONArray jsonArray = result.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Node node = JsonHelper.FromJsonObject(jsonObject, Node.class);

                        JSONObject parentObject = jsonObject.getJSONObject("parent");
                        node.setParentId(parentObject.getString("id"));
                        node.setParentName(parentObject.getString("name"));

                        JSONObject organizeObject = jsonObject.getJSONObject("organize");
                        node.setOrganizeId(organizeObject.getString("id"));
                        node.setOrganizeName(organizeObject.getString("name"));

                        nodes.add(node);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (listener != null) listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void getNodesById(@NonNull Playbasis playbasis, @NonNull String nodeId, String sort, String order,
                                    Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, nodeId, null, null, null, sort, order, offset, limit, listener);
    }

    public static void getNodesByOrgId(@NonNull Playbasis playbasis, @NonNull String orgId, String sort, String order,
                                    Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null,  orgId, null, null, sort,  order, offset,  limit, listener);
    }

    public static void getNodesByParentId(@NonNull Playbasis playbasis, @NonNull String parentId, String sort, String order,
                                       Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, null, parentId, null, sort,  order, offset,  limit, listener);
    }

    public static void getNodesByName(@NonNull Playbasis playbasis, @NonNull String searchName, String sort, String order,
                                          Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, null, null, searchName, sort,  order, offset,  limit, listener);
    }

    public static void getNodesByOrgIdParentId(@NonNull Playbasis playbasis, @NonNull String orgId, @NonNull String parentId, String sort, String order,
                                      Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, orgId, parentId, null, sort,  order, offset,  limit, listener);
    }

    public static void getNodesByOrgIdName(@NonNull Playbasis playbasis, @NonNull String orgId, @NonNull String searchName, String sort, String order,
                                      Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, orgId, null, searchName, sort,  order, offset,  limit, listener);
    }

    public static void getNodesByParentIdName(@NonNull Playbasis playbasis, @NonNull String parentId, @NonNull String searchName, String sort, String order,
                                      Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, null, parentId, searchName, sort,  order, offset,  limit, listener);
    }

    public static void getNodesByOrgIdParentIdName(@NonNull Playbasis playbasis,@NonNull String orgId, @NonNull String parentId, @NonNull String searchName, String sort, String order,
                                              Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, orgId, parentId, searchName, sort,  order, offset,  limit, listener);
    }
}
