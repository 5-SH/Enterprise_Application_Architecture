package basic.plugin;

public interface IdGenerator {
  public static final IdGenerator INSTANCE = (IdGenerator) PluginFactory.getPlugin(IdGenerator.class);

  public Long nextId();
}
