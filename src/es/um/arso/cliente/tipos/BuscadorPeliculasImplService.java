
package es.um.arso.cliente.tipos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "BuscadorPeliculasImplService", targetNamespace = "http://server.arso.um.es/", wsdlLocation = "http://localhost:8080/Entregable2/BuscadorPeliculas?wsdl")
public class BuscadorPeliculasImplService
    extends Service
{

    private final static URL BUSCADORPELICULASIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(es.um.arso.cliente.tipos.BuscadorPeliculasImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = es.um.arso.cliente.tipos.BuscadorPeliculasImplService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/Entregable2/BuscadorPeliculas?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/Entregable2/BuscadorPeliculas?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        BUSCADORPELICULASIMPLSERVICE_WSDL_LOCATION = url;
    }

    public BuscadorPeliculasImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BuscadorPeliculasImplService() {
        super(BUSCADORPELICULASIMPLSERVICE_WSDL_LOCATION, new QName("http://server.arso.um.es/", "BuscadorPeliculasImplService"));
    }

    /**
     * 
     * @return
     *     returns BuscadorPeliculas
     */
    @WebEndpoint(name = "BuscadorPeliculasImplPort")
    public BuscadorPeliculas getBuscadorPeliculasImplPort() {
        return super.getPort(new QName("http://server.arso.um.es/", "BuscadorPeliculasImplPort"), BuscadorPeliculas.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BuscadorPeliculas
     */
    @WebEndpoint(name = "BuscadorPeliculasImplPort")
    public BuscadorPeliculas getBuscadorPeliculasImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.arso.um.es/", "BuscadorPeliculasImplPort"), BuscadorPeliculas.class, features);
    }

}
