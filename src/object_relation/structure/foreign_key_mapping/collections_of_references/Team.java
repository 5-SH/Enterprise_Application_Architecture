package object_relation.structure.foreign_key_mapping.collections_of_references;

import java.util.List;
import java.util.stream.Collectors;

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

  public List getPlayers() {
    return players;
  }

  public Player getPlayer(long id) {
    List find = players.stream().filter(p -> p.getId() == id).collect(Collectors.toList());
    if (find.size() >= 0) {
      return (Player) find.get(0);
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

  public void removePlayer(Player player) {
    players.remove(player);
  }

  @Override
  public String toString() {
    return "Team{" +
      "name='" + name + '\'' +
      ", players=" + players +
      '}';
  }
}
