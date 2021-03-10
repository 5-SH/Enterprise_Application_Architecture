package object_relation.structure.foreign_key_mapping.collections_of_references;

import java.util.List;

public class Team extends DomainObject {
  private String name;
  private List<Player> players;

  public Team(Long id, String name, List<Player> players) {
    super(id);
    this.name = name;
    this.players = players;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Player getPlayer(Long id) {
    int idx = players.indexOf(id);
    if (idx > -1) {
      return players.get(idx);
    }
    return null;
  }

  public void addPlayer(Player player) {
    if (players.contains(player)) {
      throw new IllegalArgumentException("Player already exists");
    } else {
      players.add(player);
    }
  }

  public void setPlayer(List<Player> players) {
    this.players = players;
  }

  @Override
  public String toString() {
    return "Team{" +
      "name='" + name + '\'' +
      ", players=" + players +
      '}';
  }
}
