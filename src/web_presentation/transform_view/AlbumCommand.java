package web_presentation.transform_view;

import web_presentation.front_controller.FrontCommand;
import web_presentation.page_controller.Album;

import javax.servlet.ServletException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class AlbumCommand extends FrontCommand {
  @Override
  public void process() throws ServletException, IOException {
    try {
      Album album = Album.findByTitle(request.getParameter("title"));
      if (album == null) {
        forward("/view/MissingAlbumError.jsp");
      } else {
        PrintWriter out = response.getWriter();
        Source xslt = new StreamSource(new File("src/web_presentation/transform_view/album.xsl"));
        Source xml = new StreamSource(new StringReader(album.toSampleXmlDocument()));

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);
        transformer.transform(xml, result);
        out.print(writer.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
