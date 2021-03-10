package object_relation.structure.foreign_key_mapping.collections_of_references;

import java.util.Objects;

public class Player extends DomainObject {
  private String name;
  private long teamID;

  public Player(Long id, String name, long teamID) {
    super(id);
    this.name = name;
    this.teamID = teamID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getTeamID() {
    return teamID;
  }

  public void setTeamID(long teamID) {
    this.teamID = teamID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return getId() == player.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, teamID);
  }

  @Override
  public String toString() {
    return "Player{" +
      "name='" + name + '\'' +
      ", teamID=" + teamID +
      '}';
  }
}
