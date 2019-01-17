package xjc_generated.code.driver;

import generated.Element;
import generated.MLIspec;
import generated.ObjectFactory;
import java.io.File;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();

    public static void main(String[] args) throws Exception {
        new App().readAndPrint();
    }

    public void readAndPrint() throws Exception {
        properties.loadFromXML(App.class.getResourceAsStream("/properties.xml"));
        URI uri = new URI(properties.getProperty("output"));
        File file = new File(uri);

        ObjectFactory objectFactory = new ObjectFactory();
        MLIspec mliSpec = objectFactory.createMLIspec();
        mliSpec.setDescription("new mli");
        Element value = new Element();
        value.setId("some other thing");
        mliSpec.setElement(value);
        mliSpec.setId("identification string");
        mliSpec.setTypename("newType");

        LOG.info(mliSpec.toString());

        JAXBContext jaxbContext = JAXBContext.newInstance();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(mliSpec, file);

    }

}
