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
    private MLIspec mliSpec = null;

    public static void main(String[] args) throws Exception {
        new App().unmarshall();
    }

    private void unmarsklhall() throws Exception {
    }

    private MLIspec unmarshall() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/properties.xml"));
        URI uri = new URI(properties.getProperty("mli_input"));
        File file = new File(uri);

        FileInputStream fileInputStream = new FileInputStream(file);
        StreamSource streamSource = new StreamSource();
        streamSource.setInputStream(fileInputStream);

        mliSpec = JAXB.unmarshal(streamSource, MLIspec.class);
        LOG.info(mliSpec.toString());
        return mliSpec;
    }

    private void marshall() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/properties.xml"));
        URI uri = new URI(properties.getProperty("output"));
        File file = new File(uri);

        JAXBContext jaxbContext = JAXBContext.newInstance(MLIspec.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(mliSpec, file);
        jaxbMarshaller.marshal(mliSpec, System.out);

    }

}
