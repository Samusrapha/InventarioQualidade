package br.com.greenowl.inventarioqualidadeflorestal.Database;

import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Entidades.Contato;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Raphael on 03/09/2016.
 */
public class CalculaNota {

    DecimalFormat decim = new DecimalFormat("0.000");


    public double sobrevivencia(ArrayList<Contato> contatos,String fazenda) {

        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++) {
            if (fazenda.equals(contatos.get(i).getFAZENDA())   ) {
                String valors = contatos.get(i).getSOBREVIVENCIA();
                String area = contatos.get(i).getAREA();
                double valued = Double.parseDouble(valors);
                double aread = Double.parseDouble(area);


                prod = valued * aread;
                sumprod += prod;
                areatotal += aread;
            }
        }
        return Double.parseDouble(decim.format(sumprod / areatotal));



    }


    public double densidade(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;



        for(int i = 0; i < contatos.size(); i++) {
            if (fazenda.equals(contatos.get(i).getFAZENDA())) {
                String valors = contatos.get(i).getDENSIDADE();
                String area = contatos.get(i).getAREA();
                double valued = Double.parseDouble(valors);
                double aread = Double.parseDouble(area);

                prod = valued * aread;
                sumprod += prod;
                areatotal += aread;
            }
        }
        return Double.parseDouble(decim.format(sumprod / areatotal));
    }


    public double homogeneidade(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
            if (fazenda.equals(contatos.get(i).getFAZENDA()))  {
                {
                    String valors = contatos.get(i).getHOMOGENEIDADE();
                    String area = contatos.get(i).getAREA();
                    double valued = Double.parseDouble(valors);
                    double aread = Double.parseDouble(area);


                    prod = valued * aread;
                    sumprod += prod;
                    areatotal += aread;
                }
        }
        return Double.parseDouble(decim.format(sumprod / areatotal));
    }


    public double aspecto(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
            if (fazenda.equals(contatos.get(i).getFAZENDA()))  {
                {
                    String valors = contatos.get(i).getASPECTO();
                    String area = contatos.get(i).getAREA();
                    double valued = Double.parseDouble(valors);
                    double aread = Double.parseDouble(area);


                    prod = valued * aread;
                    sumprod += prod;
                    areatotal += aread;
                }
            }
        return Double.parseDouble(decim.format(sumprod / areatotal));
    }


    public double mato(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
            if (fazenda.equals(contatos.get(i).getFAZENDA()))  {
                {
                    String valors = contatos.get(i).getMATO();
                    String area = contatos.get(i).getAREA();
                    double valued = Double.parseDouble(valors);
                    double aread = Double.parseDouble(area);


                    prod = valued * aread;
                    sumprod += prod;
                    areatotal += aread;
                }
            }
        return Double.parseDouble(decim.format(sumprod / areatotal));
    }


    public double formiga(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
            if (fazenda.equals(contatos.get(i).getFAZENDA()))  {
                {
                    String valors = contatos.get(i).getFORMIGA();
                    String area = contatos.get(i).getAREA();
                    double valued = Double.parseDouble(valors);
                    double aread = Double.parseDouble(area);


                    prod = valued * aread;
                    sumprod += prod;
                    areatotal += aread;
                }
            }


        return Double.parseDouble(decim.format(sumprod / areatotal));
    }


    public double pragas(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
            if (fazenda.equals(contatos.get(i).getFAZENDA()))  {
                {
                    String valors = contatos.get(i).getPRAGAS();
                    String area = contatos.get(i).getAREA();
                    double valued = Double.parseDouble(valors);
                    double aread = Double.parseDouble(area);


                    prod = valued * aread;
                    sumprod += prod;
                    areatotal += aread;
                }
        }
        return Double.parseDouble(decim.format(sumprod / areatotal));
    }



    public double conservacao(ArrayList<Contato> contatos,String fazenda) {
        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
            if (fazenda.equals(contatos.get(i).getFAZENDA()))  {
                {
                    String valors = contatos.get(i).getCONSERVACAO();
                    String area = contatos.get(i).getAREA();
                    double valued = Double.parseDouble(valors);
                    double aread = Double.parseDouble(area);


                    prod = valued * aread;
                    sumprod += prod;
                    areatotal += aread;
                }
            }
        return Double.parseDouble(decim.format(sumprod / areatotal));
    }



}