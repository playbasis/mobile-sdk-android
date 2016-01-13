package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.CustomLeaderboard;
import com.playbasis.android.playbasissdk.model.CustomRankPeer;
import com.playbasis.android.playbasissdk.model.Leaderboard;
import com.playbasis.android.playbasissdk.model.Node;
import com.playbasis.android.playbasissdk.model.Organization;
import com.playbasis.android.playbasissdk.model.RankPeer;
import com.playbasis.android.playbasissdk.model.Sale;
import com.playbasis.android.playbasissdk.model.SaleReport;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
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
                    JSONArray jsonArray = result.getJSONArray("results");
                    for(int i=0;i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Organization organization = JsonHelper.FromJsonObject(jsonObject, Organization.class);

                        if(jsonObject.has("parent")) {
                            JSONObject parentObject = jsonObject.getJSONObject("parent");
                            organization.setParentId(parentObject.getString("id"));
                            organization.setParentName(parentObject.getString("name"));
                        }

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
                    JSONArray jsonArray = result.getJSONArray("results");
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
        getNodes(playbasis, null, null, parentId, null, sort, order, offset, limit, listener);
    }

    public static void getNodesByName(@NonNull Playbasis playbasis, @NonNull String searchName, String sort, String order,
                                          Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, null, null, searchName, sort, order, offset, limit, listener);
    }

    public static void getNodesByOrgIdParentId(@NonNull Playbasis playbasis, @NonNull String orgId, @NonNull String parentId, String sort, String order,
                                      Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, orgId, parentId, null, sort, order, offset, limit, listener);
    }

    public static void getNodesByOrgIdName(@NonNull Playbasis playbasis, @NonNull String orgId, @NonNull String searchName, String sort, String order,
                                      Integer offset, Integer limit, final OnResult<ArrayList<Node>> listener) {
        getNodes(playbasis, null, orgId, null, searchName, sort, order, offset, limit, listener);
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
     * @param page Select page to be reported, page 1 is the first page [default = first page]
     * @param limit limit per page to be reported [default = "20"]
     * @param listener Callback interface.
     */
    public static void getSaleBoardByNodeId(@NonNull Playbasis playbasis, String nodeId, int layer, String month, String year, String action, String parameter,
                                            final Integer page, final Integer limit, final OnResult<ArrayList<Sale>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/" + nodeId + "/saleBoard/" + layer + "/";

        List<NameValuePair> params = new ArrayList<>();
        if(month!=null)params.add(new BasicNameValuePair("month", String.valueOf(month)));
        if(year!=null)params.add(new BasicNameValuePair("year", String.valueOf(year)));
        if(page!=null)params.add(new BasicNameValuePair("page", String.valueOf(page)));
        if(limit!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
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
     * @param nodeId organization id to be ranked
     * @param action  Action name to be query
     * @param parameter Parameter of action to be query
     * @param page Select page to be reported, page 1 is the first page [default = first page]
     * @param limit number of rank in leaderboard to return
     * @param playerId player id to return his/her own rank
     * @param month optional, Select month to get sale report [default = current month]
     * @param year optional, Select year to get sale report [default = current year]
     * @param listener Callback interface.
     */
    public static void getRankPeerActionByNodeId(@NonNull Playbasis playbasis, String nodeId,String action, final String parameter, final Integer page,
                                                 Integer limit,String playerId, String month, String year,  final OnResult<CustomRankPeer> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/rankPeerByAccAction/" + nodeId + "/" + action + "/" + parameter + "/";

        List<NameValuePair> params = new ArrayList<>();
        if(month!=null)params.add(new BasicNameValuePair("month", String.valueOf(month)));
        if(year!=null)params.add(new BasicNameValuePair("year", String.valueOf(year)));
        if(page!=null)params.add(new BasicNameValuePair("page", String.valueOf(page)));
        if(limit!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        if(playerId!=null)params.add(new BasicNameValuePair("player_id", String.valueOf(playerId)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                CustomRankPeer rankPeer = new CustomRankPeer();
                ArrayList<CustomLeaderboard> leaderboards = new ArrayList<CustomLeaderboard>();
                try {

                    JSONArray leaderboardObj = result.getJSONArray("leaderboard");
                    JSONObject myrank = result.getJSONObject("my_rank");

                    for (int i = 0; i < leaderboardObj.length(); i++) {
                        JSONObject jsonObject = leaderboardObj.getJSONObject(i);
                        CustomLeaderboard leaderboard = JsonHelper.FromJsonObject(jsonObject, CustomLeaderboard.class);

                        int value = jsonObject.getInt(parameter);
                        int prev_value = jsonObject.getInt("previous_" + parameter);
                        double percentChanged = jsonObject.getDouble("percent_changed");

                        leaderboard.setPreviousValue(prev_value);
                        leaderboard.setRankedName(parameter);
                        leaderboard.setRankedValue(value);
                        leaderboard.setPercentChange(percentChanged);
                        leaderboards.add(leaderboard);

                    }

                    int rank = myrank.getInt("rank");
                    int rankedValue = myrank.getInt("ranked_value");

                    rankPeer = JsonHelper.FromJsonObject(myrank, CustomRankPeer.class);
                    rankPeer.setLeaderboards(leaderboards);
                    rankPeer.setRank(rank);
                    rankPeer.setRankedValue(rankedValue);


                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (listener != null) listener.onSuccess(rankPeer);
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }


    /**
     *  Find all child nodes under specific Node id.
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param layer optional, Layer of nodes under specific node to find [default = 0 (for finding all layer)]
     * @param listener Callback interface.
     */
    public static void getChildNodes(@NonNull Playbasis playbasis,@NonNull String nodeId, Integer layer, final OnResult<ArrayList<Node>> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/"+nodeId+"/getChildNode/"+layer+"/";
        List<NameValuePair> params = new ArrayList<>();
        if (nodeId != null) params.add(new BasicNameValuePair("node_id",nodeId));
        if (layer != null) params.add(new BasicNameValuePair("layer",String.valueOf(layer)));


        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ArrayList<Node> nodes = new ArrayList<Node>();
                try {
                    JSONArray jsonArray = result.getJSONArray("results");
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

    /**
     *  Sale report of specific Node in a month.
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param month optional, Select month to get sale report [default = current month]
     * @param year optional, Select year to get sale report [default = current year]
     * @param action optional, Action name to be query [default = sell]
     * @param parameter optional, Parameter of action to be query [default = amount]
     * @param listener Callback interface.
     */
    public static void saleReport(@NonNull Playbasis playbasis,@NonNull String nodeId, String month, String year, String action, String parameter, final OnResult<SaleReport> listener) {
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/"+nodeId+"/saleReport/";
        List<NameValuePair> params = new ArrayList<>();
        if (nodeId != null) params.add(new BasicNameValuePair("node_id",nodeId));
        if (month != null) params.add(new BasicNameValuePair("month",month));
        if (year != null) params.add(new BasicNameValuePair("year",year));
        if (action != null) params.add(new BasicNameValuePair("action",action));
        if (parameter != null) params.add(new BasicNameValuePair("parameter",parameter));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                SaleReport report = new SaleReport();
                try {

                    report.setAmount(result.getInt("amount"));
                    report.setPreviousAmount(result.getInt("previous_amount"));
                    report.setPercentChanged(result.getDouble("percent_changed"));

                    listener.onSuccess(report);
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
     *  Add player to specific Node.
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param playerId Player Id to add to Node.
     * @param listener Callback interface.
     */
    public static void addPlayerToNode(@NonNull Playbasis playbasis, @NonNull String nodeId, @NonNull String playerId,
                                       final OnResult<Boolean> listener){
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/"+nodeId+"/addPlayer/"+playerId+"/";
        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) listener.onSuccess(true);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Remove Player from specific Node
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param playerId Player Id to add to Node.
     * @param listener Callback interface.
     */
    public static void removePlayerFromNode(@NonNull Playbasis playbasis, @NonNull String nodeId, @NonNull String playerId,
                                            final OnResult<Boolean> listener){
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/"+nodeId+"/removePlayer/"+playerId+"/";
        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) listener.onSuccess(true);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Set player's organization role to specific Node
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param playerId Player Id to add to Node.
     * @param role Role name to set player's role.
     * @param listener Callback interface.
     */
    public static void setPlayerRole(@NonNull Playbasis playbasis, @NonNull String nodeId, @NonNull String playerId, @NonNull String role,
                                     final OnResult<Boolean> listener){
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/"+nodeId+"/setPlayerRole/"+playerId+"/";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("role",role));
        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) listener.onSuccess(true);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Unset player's organization role to specific Node
     * @param playbasis Playbasis object.
     * @param nodeId node id to query player list.
     * @param playerId Player Id to add to Node.
     * @param role Role name to set player's role.
     * @param listener Callback interface.
     */
    public static void unsetPlayerRole(@NonNull Playbasis playbasis, @NonNull String nodeId, @NonNull String playerId, @NonNull String role,
                                       final OnResult<Boolean> listener){
        String uri = playbasis.getUrl() + "/StoreOrg/nodes/"+nodeId+"/unsetPlayerRole/"+playerId+"/";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("role",role));
        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) listener.onSuccess(true);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void getSalesHistory(@NonNull Playbasis playbasis, @NonNull String nodeId, @NonNull final Integer count,
                                       Integer month, Integer year, String action, String parameter, final OnResult<ArrayList<SaleReport>> listener) {

        String uri = playbasis.getUrl() + "/StoreOrg/nodes/" + nodeId + "/saleHistory/" + count;
        final List<NameValuePair> params = new ArrayList<>();
        if (month != null) params.add(new BasicNameValuePair("month", String.valueOf(month)));
        if (year != null) params.add(new BasicNameValuePair("year",String.valueOf(year)));
        if (action != null) params.add(new BasicNameValuePair("action",action));
        if (parameter != null) params.add(new BasicNameValuePair("parameter",parameter));

        // We add count param in params in order to deal with return JSON
        params.add(new BasicNameValuePair("count", String.valueOf(count)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ArrayList<SaleReport> monthlySaleReports = new ArrayList<SaleReport>();

                Integer month = null;
                Integer year = null;
                Integer count = null;
                for (NameValuePair param : params) {
                    if (param.getName() == "count") {
                        count = Integer.parseInt(param.getValue());
                    }
                    if (param.getName() == "month") {
                        month = Integer.parseInt(param.getValue());

                    }
                    if (param.getName() == "year") {
                        year = Integer.parseInt(param.getValue());
                    }
                }

                Calendar c = Calendar.getInstance();
                if (month == null) {
                    // Calendar.JANUARY == 0
                    month = c.get(Calendar.MONTH) + 1;
                }
                if (year == null) {
                    year = c.get(Calendar.YEAR);
                }

                try {

                    for (int i = 0; i < count; i++) {
                        JSONObject yearJsonObject = result.getJSONObject(String.valueOf(year));
                        JSONObject monthJsonObject = yearJsonObject.getJSONObject(String.format("%02d", month));

                        SaleReport monthlySaleReport = JsonHelper.FromJsonObject(monthJsonObject, SaleReport.class);
                        monthlySaleReport.setMonth(String.format("%02d", month));
                        monthlySaleReport.setYear(String.valueOf(year));

                        monthlySaleReports.add(monthlySaleReport);

                        month = month - 1;
                        if (month < 1) {
                            month = 12;
                            year = year - 1;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (listener != null) listener.onSuccess(monthlySaleReports);
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
     * @param nodeId organization id to be ranked
     * @param rankBy name of point-based reward to rank players by
     * @param page Select page to be reported, page 1 is the first page [default = first page]
     * @param limit number of rank in leaderboard to return
     * @param role role to be filtered in organization
     * @param playerId player id to return his/her own rank
     * @param month Select month to get sale report [default = current month]
     * @param year Select year to get sale report [default = current year]
     * @param listener Callback interface.
     */
    public static void getRankPeer(@NonNull Playbasis playbasis, @NonNull String nodeId, @NonNull final String rankBy, final Integer page,
                                       final Integer limit, String role, String playerId, final Integer month,  final Integer year, final OnResult<RankPeer> listener) {

        String uri = playbasis.getUrl() + "/StoreOrg/rankPeer/" + nodeId + "/" + rankBy;
        final List<NameValuePair> params = new ArrayList<>();
        if (month != null) params.add(new BasicNameValuePair("month", String.valueOf(month)));
        if (year != null) params.add(new BasicNameValuePair("year",String.valueOf(year)));
        if (page != null) params.add(new BasicNameValuePair("page",String.valueOf(page)));
        if (limit != null) params.add(new BasicNameValuePair("limit",String.valueOf(limit)));
        if (role != null) params.add(new BasicNameValuePair("role",role));
        if (playerId != null) params.add(new BasicNameValuePair("player_id",playerId));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                RankPeer rankPeer = new RankPeer();
                ArrayList<Leaderboard> leaderboards = new ArrayList<Leaderboard>();
                try {

                    JSONArray leaderboardObj = result.getJSONArray("leaderboard");
                    JSONObject myrank = result.getJSONObject("my_rank");

                    for (int i = 0 ; i < leaderboardObj.length(); i++){
                        JSONObject jsonObject = leaderboardObj.getJSONObject(i);
                        Leaderboard leaderboard = JsonHelper.FromJsonObject(jsonObject, Leaderboard.class);

                        int value = jsonObject.getInt(rankBy);
                        int prev_value = jsonObject.getInt("previous_"+rankBy);
                        double percentChanged = jsonObject.getDouble("percent_changed");

                        leaderboard.setPreviousValue(prev_value);
                        leaderboard.setRankedName(rankBy);
                        leaderboard.setRankedValue(value);
                        leaderboard.setPercentChange(percentChanged);
                        leaderboards.add(leaderboard);

                    }

                    int rank = myrank.getInt("rank");
                    int rankedValue = myrank.getInt("ranked_value");

                    rankPeer  = JsonHelper.FromJsonObject(myrank, RankPeer.class);
                    rankPeer.setLeaderboards(leaderboards);
                    rankPeer.setRank(rank);
                    rankPeer.setRankedValue(rankedValue);

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if(listener != null) listener.onSuccess(rankPeer);
                }
            }

            @Override
            public void onError(HttpError error) {
                if(listener != null) listener.onError(error);
            }
        });
    }


}
