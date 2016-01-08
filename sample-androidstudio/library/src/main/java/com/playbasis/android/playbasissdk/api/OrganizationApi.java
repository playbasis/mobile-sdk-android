package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.CustomRankPeer;
import com.playbasis.android.playbasissdk.model.Node;
import com.playbasis.android.playbasissdk.model.Organization;
import com.playbasis.android.playbasissdk.model.Sale;

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
                    listener.onSuccess(organizations);
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
                    listener.onSuccess(nodes);
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
    /**
     *  Returns list of players sorted which associate with given node id.
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param role optional, role to query player list
     * @param listener Callback interface.
     */
    public static void getPlayersByNodeId(@NonNull Playbasis playbasis, String nodeId, String role, final OnResult<ArrayList<String>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/players/" + nodeId + "/";
        List<NameValuePair> params = new ArrayList<>();
        if (role != null) params.add(new BasicNameValuePair("role",role));

        JsonArrayGET(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<String> playerIds = new ArrayList<String>();
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        String playerId = jsonObject.getString("player_id");

                        System.out.println("Player ID : " + playerId);
                        playerIds.add(playerId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (listener != null) listener.onError(new HttpError(e));
                } finally {
                    if (listener != null) listener.onSuccess(playerIds);
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
    /**
     *  Returns list of players sorted which associate with given node id.
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param layer optional, Layer of nodes under specific node to find [default = 0 (for finding all layer)]
     * @param month optional, Select month to get sale report [default = current month]
     * @param year optional, Select year to get sale report [default = current year]
     * @param action optional, Action name to be query [default = sell]
     * @param parameter optional, Parameter of action to be query [default = amount]
     * @param listener Callback interface.
     */
    public static void getSaleBoardByNodeId(@NonNull Playbasis playbasis, String nodeId, int layer, String month, String year, String action, String parameter, final OnResult<ArrayList<Sale>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/" + nodeId + "/saleBoard/" + layer + "/";

        List<NameValuePair> params = new ArrayList<>();
        if(month!=null)params.add(new BasicNameValuePair("month", String.valueOf(month)));
        if(year!=null)params.add(new BasicNameValuePair("year", String.valueOf(year)));
        if(action!=null){
            params.add(new BasicNameValuePair("action", String.valueOf(action)));
        }
        else{
            action = "sell";
        }
        if(parameter!=null){
            params.add(new BasicNameValuePair("parameter", String.valueOf(parameter)));
        }else{
            parameter = "amount";
        }

        final String finalParameter = parameter;
        JsonArrayGET(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<Sale> SaleBoard = new ArrayList<Sale>();
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        Sale sale = JsonHelper.FromJsonObject(jsonObject, Sale.class);

                        int currentValue = jsonObject.getInt(finalParameter);
                        int previousValue = jsonObject.getInt("previous_" + finalParameter);
                        double percentChanged = jsonObject.getDouble("percent_changed");

                        sale.setParameterName(finalParameter);
                        sale.setCurrentValue(currentValue);
                        sale.setPreviousValue(previousValue);
                        sale.setPercentChange(percentChanged);

                        SaleBoard.add(sale);
                    }
                    listener.onSuccess(SaleBoard);
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
    /**
     *  Returns Rank Peer of players by action and parameter which associate with given node id.
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param month optional, Select month to get sale report [default = current month]
     * @param year optional, Select year to get sale report [default = current year]
     * @param action optional, Action name to be query [default = sell]
     * @param parameter optional, Parameter of action to be query [default = amount]
     * @param listener Callback interface.
     */
    public static void getRankPeerActionByNodeId(@NonNull Playbasis playbasis, String nodeId,String action, String parameter, Integer limit, String month, String year,  final OnResult<ArrayList<CustomRankPeer>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/rankPeerByAccAction/" + nodeId + "/" + action + "/" + parameter + "/";

        List<NameValuePair> params = new ArrayList<>();
        if(month!=null)params.add(new BasicNameValuePair("month", String.valueOf(month)));
        if(year!=null)params.add(new BasicNameValuePair("year", String.valueOf(year)));
        if(limit!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));


        final String finalParameter = parameter;
        JsonArrayGET(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<CustomRankPeer> customRankPeers = new ArrayList<CustomRankPeer>();
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        CustomRankPeer customRankPeer = JsonHelper.FromJsonObject(jsonObject, CustomRankPeer.class);

                        int currentValue = jsonObject.getInt(finalParameter);
                        int previousValue = jsonObject.getInt("previous_" + finalParameter);
                        double percentChanged = jsonObject.getDouble("percent_changed");

                        customRankPeer.setCustomRankName(finalParameter);
                        customRankPeer.setCustomRankValue(currentValue);
                        customRankPeer.setPreviousValue(previousValue);
                        customRankPeer.setPercentChange(percentChanged);

                        customRankPeers.add(customRankPeer);
                        System.out.println("name : " + customRankPeer.getNodeName());
                        System.out.println(customRankPeer.getCustomRankName() + " : " + customRankPeer.getCustomRankValue());
                        System.out.println("previous_" + customRankPeer.getCustomRankName() + " : " + customRankPeer.getPreviousValue());
                        System.out.println("percent_changed : " + customRankPeer.getPercentChange());
                    }
                    listener.onSuccess(customRankPeers);
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
}
