package com.convertspeed.client;

import com.utils.JaxWsHandlerResolver;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class JAXBClient {
    public ConvertSpeedsSoap client;

    public JAXBClient() {
        try {

            URL url = new URL("http://www.webservicex.net/convertspeed.asmx?wsdl");

            QName qname = new QName("http://www.webserviceX.NET/", "ConvertSpeeds");

            Service service = Service.create(url, qname);

            service.setHandlerResolver(new JaxWsHandlerResolver());

            client = service.getPort(ConvertSpeedsSoap.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
