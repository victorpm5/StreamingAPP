
package com.isdcm.streamingapp.services.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.isdcm.streamingapp.services.soap package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.isdcm.streamingapp.services.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FilterVideosByYearResponse }
     * 
     */
    public FilterVideosByYearResponse createFilterVideosByYearResponse() {
        return new FilterVideosByYearResponse();
    }

    /**
     * Create an instance of {@link Video }
     * 
     */
    public Video createVideo() {
        return new Video();
    }

    /**
     * Create an instance of {@link FilterVideosByAutorRequest }
     * 
     */
    public FilterVideosByAutorRequest createFilterVideosByAutorRequest() {
        return new FilterVideosByAutorRequest();
    }

    /**
     * Create an instance of {@link GetVideosResponse }
     * 
     */
    public GetVideosResponse createGetVideosResponse() {
        return new GetVideosResponse();
    }

    /**
     * Create an instance of {@link GetVideosRequest }
     * 
     */
    public GetVideosRequest createGetVideosRequest() {
        return new GetVideosRequest();
    }

    /**
     * Create an instance of {@link Void }
     * 
     */
    public Void createVoid() {
        return new Void();
    }

    /**
     * Create an instance of {@link FilterVideosByTitleResponse }
     * 
     */
    public FilterVideosByTitleResponse createFilterVideosByTitleResponse() {
        return new FilterVideosByTitleResponse();
    }

    /**
     * Create an instance of {@link FilterVideosByAutorResponse }
     * 
     */
    public FilterVideosByAutorResponse createFilterVideosByAutorResponse() {
        return new FilterVideosByAutorResponse();
    }

    /**
     * Create an instance of {@link FilterVideosByYearRequest }
     * 
     */
    public FilterVideosByYearRequest createFilterVideosByYearRequest() {
        return new FilterVideosByYearRequest();
    }

    /**
     * Create an instance of {@link FilterVideosByTitleRequest }
     * 
     */
    public FilterVideosByTitleRequest createFilterVideosByTitleRequest() {
        return new FilterVideosByTitleRequest();
    }

}
