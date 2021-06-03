package web_presentation.application_controller;

import java.util.HashMap;
import java.util.Map;

enum AssetStatus {
  STOCK,
  REPAIR,
  LEASE
}

public class AssetApplicationController implements ApplicationController {
  private Map events = new HashMap();

  @Override
  public DomainCommand getDomainCommand(String commandString, Map params) {
    Response response = getResponse(commandString, getAssetStatus(params));
    return response.getDomainCommand();
  }

  @Override
  public String getView(String commandString, Map params) {
    return getResponse(commandString, getAssetStatus(params)).getViewUrl();
  }

  private Response getResponse(String commandString, AssetStatus state) {
    return (Response) getResponseMap(commandString).get(state);
  }

  private Map getResponseMap(String key) {
    return (Map) events.get(key);
  }

  private AssetStatus getAssetStatus(Map params) {
    String id = getParam("assetID", params);
    Asset asset = Asset.find(id);
    return asset.getStatus();
  }

  private String getParam(String key, Map params) {
    return ((String[]) params.get(key))[0];
  }
}
