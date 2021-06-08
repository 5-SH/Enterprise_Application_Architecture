package web_presentation.application_controller;

public class Asset extends DomainObject {
  private AssetStatus status;

  public Asset(Long id, AssetStatus status) {
    super(id);
    this.status = status;
  }

  public static Asset find(String id) {
    return Registry.getAssets(Long.valueOf(id));
  }

  public AssetStatus getStatus() {
    return this.status;
  }
}
