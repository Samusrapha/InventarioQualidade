package br.com.greenowl.inventarioqualidadeflorestal.Database;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.widget.ArrayAdapter;

import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Entidades.Contato;
import br.com.greenowl.inventarioqualidadeflorestal.Util.DateUtil;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * This class helps open, create, and upgrade the database file.
 */
public  class Parse {
    public ArrayList<Contato> Importardados(Context fContext) {
        ArrayList<Contato> Lista = new ArrayList<Contato>();
//-----------------------------------------------------------------------------
        // initialize our input source variable
        InputSource inputSource = null;
        File xmlFile = new File(Environment.getExternalStorageDirectory()+"/talhoes.xml");
        FileInputStream xmlFileInputStream = null;
        try {
            xmlFileInputStream = new FileInputStream(
                    xmlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inputSource = new InputSource(xmlFileInputStream);
//---------------------------------------------------------------------------
// Get xml resource file
        Resources res = fContext.getResources();
        //Open xml file
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser _xml = factory.newPullParser();
            _xml.setInput(xmlFileInputStream,null);
            //Check for end of document
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Search for record tags
                if ((eventType == XmlPullParser.START_TAG) && (_xml.getName().equals("record"))) {
                    //Record tag found, now get values and insert record
                    String _fazenda = _xml.getAttributeValue(null, Contato.FAZENDA);
                    String _talhao = _xml.getAttributeValue(null, Contato.TALHAO);
                    String _material = _xml.getAttributeValue(null, Contato.MATERIAL);
                    String _area = _xml.getAttributeValue(null, Contato.AREA);
                    Date _data= DateUtil.getDate(2016,01,01);
                    String _sobrevivencia = _xml.getAttributeValue(null, Contato.SOBREVIVENCIA);
                    String _densidade = _xml.getAttributeValue(null, Contato.DENSIDADE);
                    String _homogeneidade = _xml.getAttributeValue(null, Contato.HOMOGENEIDADE);
                    String _aspecto = _xml.getAttributeValue(null, Contato.ASPECTO);
                    String _mato = _xml.getAttributeValue(null, Contato.MATO);
                    String _formiga = _xml.getAttributeValue(null, Contato.FORMIGA);
                    String _praga = _xml.getAttributeValue(null, Contato.PRAGAS);
                    String _conservacao = _xml.getAttributeValue(null, Contato.CONSERVACAO);
                    Contato contato = new Contato();

                    contato.setFAZENDA(_fazenda);
                    contato.setTALHAO(_talhao);
                    contato.setMATERIAL(_material);
                    contato.setAREA(_area);
                    contato.setDATA(_data);
                    contato.setSOBREVIVENCIA(_sobrevivencia);
                    contato.setDENSIDADE(_densidade);
                    contato.setHOMOGENEIDADE(_homogeneidade);
                    contato.setASPECTO(_aspecto);
                    contato.setMATO(_mato);
                    contato.setFORMIGA(_formiga);
                    contato.setPRAGAS(_praga);
                    contato.setCONSERVACAO(_conservacao);
                    Lista.add(contato);
                }
                eventType = _xml.next();
            }
        }
        //Catch errors
        catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return Lista;
            //Close the xml file
        }
    }
    public ArrayList<Contato> AdaptertoList (ArrayAdapter<Contato> contatos){
        ArrayList<Contato> Aresultado =  new ArrayList<Contato>();
        for (int i= 0 ; i<contatos.getCount();i++){
            Contato C = new Contato();
            C.setFAZENDA(contatos.getItem(i).getFAZENDA());
            C.setTALHAO(contatos.getItem(i).getTALHAO());
            C.setMATERIAL(contatos.getItem(i).getMATERIAL());
            C.setAREA(contatos.getItem(i).getAREA());
            C.setDATA(contatos.getItem(i).getDATA());
            C.setSOBREVIVENCIA(contatos.getItem(i).getSOBREVIVENCIA());
            C.setDENSIDADE(contatos.getItem(i).getDENSIDADE());
            C.setHOMOGENEIDADE(contatos.getItem(i).getHOMOGENEIDADE());
            C.setASPECTO(contatos.getItem(i).getASPECTO());
            C.setMATO(contatos.getItem(i).getMATO());
            C.setFORMIGA(contatos.getItem(i).getFORMIGA());
            C.setPRAGAS(contatos.getItem(i).getPRAGAS());
            C.setCONSERVACAO(contatos.getItem(i).getCONSERVACAO());
            Aresultado.add(C);
        }
        return Aresultado;
    }
    public void Exportarxml(ArrayList<Contato> contatos) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("talhoes");
            doc.appendChild(rootElement);
            for (int i =0; i<contatos.size();i++) {
                // staff elements
                Element staff = doc.createElement("record");
                rootElement.appendChild(staff);
                // set attribute to staff element

                Attr attr = doc.createAttribute("FAZENDA");
                attr.setValue(contatos.get(i).getFAZENDA());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("TALHAO");
                attr.setValue(contatos.get(i).getTALHAO());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("MATERIAL");
                attr.setValue(contatos.get(i).getMATERIAL());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("AREA");
                attr.setValue(contatos.get(i).getAREA());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("DATA");
                attr.setValue(contatos.get(i).getDATA().toString());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("SOBREVIVENCIA");
                attr.setValue(contatos.get(i).getSOBREVIVENCIA());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("DENSIDADE");
                attr.setValue(contatos.get(i).getDENSIDADE());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("HOMOGENEIDADE");
                attr.setValue(contatos.get(i).getHOMOGENEIDADE());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("ASPECTO");
                attr.setValue(contatos.get(i).getASPECTO());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("MATO");
                attr.setValue(contatos.get(i).getMATO());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("FORMIGA");
                attr.setValue(contatos.get(i).getFORMIGA());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("PRAGAS");
                attr.setValue(contatos.get(i).getPRAGAS());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("CONSERVACAO");
                attr.setValue(contatos.get(i).getCONSERVACAO());
                staff.setAttributeNode(attr);
            }
            // shorten way
            // staff.setAttribute("id", "1");
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Environment.getExternalStorageDirectory()+"/talhoesexp.xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
/*
    public double Calculaxml(ArrayList<Contato> contatos) {

        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
        {
         String valors = contatos.get(i).getSOBREVIVENCIA();
         String area = contatos.get(i).getAREA();
            double valued = Double.parseDouble(valors);
            double aread = Double.parseDouble(area);


            prod = valued*aread;
            sumprod += prod;
            areatotal+= aread;
        }
      return sumprod/areatotal;



        }*/
}