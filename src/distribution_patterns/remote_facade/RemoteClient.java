package distribution_patterns.remote_facade;

import distribution_patterns.data_transfer_object.AlbumDTO;

import java.util.HashMap;
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
          Map inputAlbumMap = new HashMap();
          switch (id) {
            case "1" :
              Map albumMap1 = new HashMap();
              albumMap1.put("artist", "kks");
              albumMap1.put("title", "kks album #1");

              Map track1_1 = new HashMap();
              track1_1.put("title", "track #1-1");
              track1_1.put("performers", new String[]{"kks"});

              Map track1_2 = new HashMap();
              track1_2.put("title", "track #1-2");
              track1_2.put("performers", new String[]{"lyj"});

              Map track1_3 = new HashMap();
              track1_3.put("title", "track #1-3");
              track1_3.put("performers", new String[]{"kms"});

              albumMap1.put("tracks", new Map[]{track1_1, track1_2, track1_3});

              inputAlbumMap = albumMap1;
              break;
            case "2" :
              Map albumMap2 = new HashMap();
              albumMap2.put("artist", "lyj");
              albumMap2.put("title", "lyj album #2");

              Map track2_1 = new HashMap();
              track2_1.put("title", "track #2-1");
              track2_1.put("performers", new String[]{"kks", "lyj", "kms", "jjh"});

              Map track2_2 = new HashMap();
              track2_2.put("title", "track #2-2");
              track2_2.put("performers", new String[]{"lyj", "kks"});

              Map track2_3 = new HashMap();
              track2_3.put("title", "track #2-3");
              track2_3.put("performers", new String[]{"kms", "jjh"});

              albumMap2.put("tracks", new Map[]{track2_1, track2_2, track2_3});

              inputAlbumMap = albumMap2;
          }
          albumService.createAlbum(id, inputAlbumMap);
          break;
        case "updateAlbum" :
          Map updateAlbumMap = new HashMap();
          updateAlbumMap.put("artist", "jjh");
          updateAlbumMap.put("title", "jjh update album");

          Map track2_1 = new HashMap();
          track2_1.put("title", "track update-1");
          track2_1.put("performers", new String[]{"kks", "lyj", "kms", "jjh"});

          Map track2_2 = new HashMap();
          track2_2.put("title", "track update-2");
          track2_2.put("performers", new String[]{"kks", "lyj", "kms", "jjh"});

          Map track2_3 = new HashMap();
          track2_3.put("title", "track update-3");
          track2_3.put("performers", new String[]{"kks", "lyj", "kms", "jjh"});

          updateAlbumMap.put("tracks", new Map[]{track2_1, track2_2, track2_3});
          albumService.updateAlbum(id, updateAlbumMap);
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
