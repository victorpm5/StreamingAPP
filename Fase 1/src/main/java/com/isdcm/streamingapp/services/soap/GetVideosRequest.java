
package com.isdcm.streamingapp.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="void" type="{http://spring.io/guides/gs-producing-web-service}void"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "_void"
})
@XmlRootElement(name = "getVideosRequest")
public class GetVideosRequest {

    @XmlElement(name = "void", required = true)
    protected Void _void;

    /**
     * Gets the value of the void property.
     * 
     * @return
     *     possible object is
     *     {@link Void }
     *     
     */
    public Void getVoid() {
        return _void;
    }

    /**
     * Sets the value of the void property.
     * 
     * @param value
     *     allowed object is
     *     {@link Void }
     *     
     */
    public void setVoid(Void value) {
        this._void = value;
    }

}
