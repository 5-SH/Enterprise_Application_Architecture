package web_presentation.application_controller;

import java.util.HashMap;
import java.util.Map;

public class Registry {
  private static Registry soleInstance = new Registry();
  protected Map<Long, Asset> assets;
  protected Map<String, Map> events;

  public Registry() {
    assets = new HashMap<Long, Asset>();
    assets.put(Long.valueOf(1), new Asset(Long.valueOf(1), AssetStatus.LEASE));
    assets.put(Long.valueOf(2), new Asset(Long.valueOf(2), AssetStatus.REPAIR));
    assets.put(Long.valueOf(3), new Asset(Long.valueOf(3), AssetStatus.STOCK));

    events = new HashMap<String, Map>();
    Map r = new HashMap();
    r.put(AssetStatus.LEASE, new Response(ReturnAssetCommand.class, "returnAsset"));
    r.put(AssetStatus.REPAIR, new Response(ErrorCommand.class, "ApplicationError"));
    r.put(AssetStatus.STOCK, new Response(ErrorCommand.class, "ApplicationError"));
    events.put("RETURN", r);

    Map d = new HashMap();
    d.put(AssetStatus.LEASE, new Response(MovePageCommand.class, "movePage"));
    d.put(AssetStatus.REPAIR, new Response(ErrorCommand.class, "ApplicationError"));
    d.put(AssetStatus.STOCK, new Response(MovePageCommand.class, "movePage"));
    events.put("DAMAGE", d);
  }

  private static Registry getInstance() { return soleInstance; }

  public static void initialize() { soleInstance = new Registry(); }

  public static Asset getAssets(long id) { return getInstance().assets.get(id); }

  public static Map getEvents() { return getInstance().events; }
}
