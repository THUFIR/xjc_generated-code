package xjc_generated.code.driver;

import generated.MLIspec;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();
    //  private MLIspec mliSpec = null;

    public static void main(String[] args) throws Exception {
        new App().unmarshallThenMarshall();
    }

    private void unmarshallThenMarshall() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/properties.xml"));

        URI inputURI = new URI(properties.getProperty("mli_input"));
        File inputFile = new File(inputURI);
        MLIspec mliSpec = unmarshallMLI(inputFile);

        URI outputURI = new URI(properties.getProperty("output"));
        File outputFile = new File(outputURI);
        marshall(mliSpec, outputFile);
    }

    private MLIspec unmarshallMLI(File file) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(file);
        StreamSource streamSource = new StreamSource();
        streamSource.setInputStream(fileInputStream);

        MLIspec mliSpec = JAXB.unmarshal(streamSource, MLIspec.class);
        LOG.info(mliSpec.toString());
        return mliSpec;
    }

    private void marshall(generated.MLIspec mliSpec, File file) throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(MLIspec.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(mliSpec, file);
    //    jaxbMarshaller.marshal(mliSpec, System.out);

    }

}
