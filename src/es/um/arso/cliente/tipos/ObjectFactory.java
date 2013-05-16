
package es.um.arso.cliente.tipos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.um.arso.cliente.tipos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BuscarPorTitulo_QNAME = new QName("http://server.arso.um.es/", "buscarPorTitulo");
    private final static QName _ObtenerInformacion_QNAME = new QName("http://server.arso.um.es/", "obtenerInformacion");
    private final static QName _ObtenerInformacionResponse_QNAME = new QName("http://server.arso.um.es/", "obtenerInformacionResponse");
    private final static QName _PeliculaException_QNAME = new QName("http://server.arso.um.es/", "PeliculaException");
    private final static QName _BuscarPorTituloResponse_QNAME = new QName("http://server.arso.um.es/", "buscarPorTituloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.um.arso.cliente.tipos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Languages }
     * 
     */
    public Languages createLanguages() {
        return new Languages();
    }

    /**
     * Create an instance of {@link PeliculaAmazon }
     * 
     */
    public PeliculaAmazon createPeliculaAmazon() {
        return new PeliculaAmazon();
    }

    /**
     * Create an instance of {@link Price }
     * 
     */
    public Price createPrice() {
        return new Price();
    }

    /**
     * Create an instance of {@link CriticasPrensa }
     * 
     */
    public CriticasPrensa createCriticasPrensa() {
        return new CriticasPrensa();
    }

    /**
     * Create an instance of {@link ObtenerInformacion }
     * 
     */
    public ObtenerInformacion createObtenerInformacion() {
        return new ObtenerInformacion();
    }

    /**
     * Create an instance of {@link CriticaUsuario }
     * 
     */
    public CriticaUsuario createCriticaUsuario() {
        return new CriticaUsuario();
    }

    /**
     * Create an instance of {@link Language }
     * 
     */
    public Language createLanguage() {
        return new Language();
    }

    /**
     * Create an instance of {@link DescripcionLibroAmazon }
     * 
     */
    public DescripcionLibroAmazon createDescripcionLibroAmazon() {
        return new DescripcionLibroAmazon();
    }

    /**
     * Create an instance of {@link Pelicula }
     * 
     */
    public Pelicula createPelicula() {
        return new Pelicula();
    }

    /**
     * Create an instance of {@link LibroAmazon }
     * 
     */
    public LibroAmazon createLibroAmazon() {
        return new LibroAmazon();
    }

    /**
     * Create an instance of {@link DescripcionPeliculaAmazon }
     * 
     */
    public DescripcionPeliculaAmazon createDescripcionPeliculaAmazon() {
        return new DescripcionPeliculaAmazon();
    }

    /**
     * Create an instance of {@link ObtenerInformacionResponse }
     * 
     */
    public ObtenerInformacionResponse createObtenerInformacionResponse() {
        return new ObtenerInformacionResponse();
    }

    /**
     * Create an instance of {@link PeliculaException }
     * 
     */
    public PeliculaException createPeliculaException() {
        return new PeliculaException();
    }

    /**
     * Create an instance of {@link CriticasUsuario }
     * 
     */
    public CriticasUsuario createCriticasUsuario() {
        return new CriticasUsuario();
    }

    /**
     * Create an instance of {@link BuscarPorTitulo }
     * 
     */
    public BuscarPorTitulo createBuscarPorTitulo() {
        return new BuscarPorTitulo();
    }

    /**
     * Create an instance of {@link CriticaPrensa }
     * 
     */
    public CriticaPrensa createCriticaPrensa() {
        return new CriticaPrensa();
    }

    /**
     * Create an instance of {@link DescripcionPelicula }
     * 
     */
    public DescripcionPelicula createDescripcionPelicula() {
        return new DescripcionPelicula();
    }

    /**
     * Create an instance of {@link BuscarPorTituloResponse }
     * 
     */
    public BuscarPorTituloResponse createBuscarPorTituloResponse() {
        return new BuscarPorTituloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarPorTitulo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.arso.um.es/", name = "buscarPorTitulo")
    public JAXBElement<BuscarPorTitulo> createBuscarPorTitulo(BuscarPorTitulo value) {
        return new JAXBElement<BuscarPorTitulo>(_BuscarPorTitulo_QNAME, BuscarPorTitulo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerInformacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.arso.um.es/", name = "obtenerInformacion")
    public JAXBElement<ObtenerInformacion> createObtenerInformacion(ObtenerInformacion value) {
        return new JAXBElement<ObtenerInformacion>(_ObtenerInformacion_QNAME, ObtenerInformacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerInformacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.arso.um.es/", name = "obtenerInformacionResponse")
    public JAXBElement<ObtenerInformacionResponse> createObtenerInformacionResponse(ObtenerInformacionResponse value) {
        return new JAXBElement<ObtenerInformacionResponse>(_ObtenerInformacionResponse_QNAME, ObtenerInformacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PeliculaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.arso.um.es/", name = "PeliculaException")
    public JAXBElement<PeliculaException> createPeliculaException(PeliculaException value) {
        return new JAXBElement<PeliculaException>(_PeliculaException_QNAME, PeliculaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarPorTituloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.arso.um.es/", name = "buscarPorTituloResponse")
    public JAXBElement<BuscarPorTituloResponse> createBuscarPorTituloResponse(BuscarPorTituloResponse value) {
        return new JAXBElement<BuscarPorTituloResponse>(_BuscarPorTituloResponse_QNAME, BuscarPorTituloResponse.class, null, value);
    }

}
