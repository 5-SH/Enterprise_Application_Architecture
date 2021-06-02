package web_presentation.two_step_view;

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
        Source xslt1 = new StreamSource(new File("src/web_presentation/two_step_view/album1.xsl"));
        Source xml1 = new StreamSource(new StringReader(album.toSampleXmlDocument2()));

        StringWriter writer1 = new StringWriter();
        StreamResult result1 = new StreamResult(writer1);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer1 = factory.newTransformer(xslt1);
        transformer1.transform(xml1, result1);

        Source xslt2 = new StreamSource(new File("src/web_presentation/two_step_view/album2.xsl"));
        Source xml2 = new StreamSource(new StringReader(writer1.toString()));

        StringWriter writer2 = new StringWriter();
        StreamResult result2 = new StreamResult(writer2);

        Transformer transformer2 = factory.newTransformer(xslt2);
        transformer2.transform(xml2, result2);

        out.print(writer2.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
