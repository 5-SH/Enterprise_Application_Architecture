package distribution_patterns.remote_facade;

import distribution_patterns.data_transfer_object.AlbumDTO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RemoteClient {
  public static void main(String[] args) {
    RemoteClient client = new RemoteClient();
    Scanner scan = new Scanner(System.in);
    while (true) {
      String input = scan.nextLine();
      if (input.contains("exit")) break;


      String[] split = input.split(" ");
      System.out.println("USER INPUT \n" + "COMMAND : " +  split[0] + "\n" + "ID : " + split[1] + "\n");
      client.process(split[0], split[1]);
    }
  }

  public void process(String command, String id) {
    try {
      AlbumHome albumHome = new AlbumHome();
      AlbumService albumService = albumHome.create();

      switch (command) {
        case "getAlbum" :
          Map albumMap = albumService.getAlbum(id);
          AlbumDTO result = AlbumDTO.readMapReflect(albumMap);
          System.out.println(result);
          break;
        case "createAlbum" :
          Album inputAlbum = null;
          switch (id) {
            case "1" :
              Album album1 = new Album("kks album #1", new Artist("kks"));
              Track t_1_1 = new Track("track #1-1");

              t_1_1.addPerformers(new Artist("kks"));
              album1.addTrack(t_1_1);

              Track t_1_2 = new Track("track #1-2");
              t_1_2.addPerformers(new Artist("lyj"));
              album1.addTrack(t_1_2);

              Track t_1_3 = new Track("track #1-3");
              t_1_3.addPerformers(new Artist("kms"));
              album1.addTrack(t_1_3);
              inputAlbum = album1;
              break;
            case "2" :
              Album album2 = new Album("lyj album #2", new Artist("lyj"));
              Track t_2_1 = new Track("track #1-1");

              t_2_1.addPerformers(new Artist("kks"));
              t_2_1.addPerformers(new Artist("lyj"));
              t_2_1.addPerformers(new Artist("kms"));
              t_2_1.addPerformers(new Artist("jjh"));
              album2.addTrack(t_2_1);

              Track t_2_2 = new Track("track #1-2");
              t_2_2.addPerformers(new Artist("lyj"));
              t_2_2.addPerformers(new Artist("kks"));
              album2.addTrack(t_2_2);

              Track t_2_3 = new Track("track #1-3");
              t_2_3.addPerformers(new Artist("kms"));
              t_2_3.addPerformers(new Artist("jjh"));
              album2.addTrack(t_2_3);
              inputAlbum = album2;
          }
          albumService.createAlbum(id, inputAlbum);
          break;
        case "updateAlbum" :
          Album updateAlbum = new Album("update album", new Artist("jjh"));
          Track t_2_1 = new Track("track update-1");

          t_2_1.addPerformers(new Artist("jjh"));
          updateAlbum.addTrack(t_2_1);

          Track t_2_2 = new Track("track update-2");
          t_2_2.addPerformers(new Artist("jjh"));
          updateAlbum.addTrack(t_2_2);

          Track t_2_3 = new Track("track update-3");
          t_2_3.addPerformers(new Artist("jjh"));
          updateAlbum.addTrack(t_2_3);
          break;

      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
