package object_relation.structure.foreign_key_mapping.single_valued_reference;

public class Album extends DomainObject {
  private String title;
  private Artist artist;

  public Album(Long id, String title, Artist artist) {
    super(id);
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  @Override
  public String toString() {
    return "Album{" +
      "id='" + getId() + '\'' +
      "title='" + title + '\'' +
      ", artist=" + artist +
      '}';
  }
}
