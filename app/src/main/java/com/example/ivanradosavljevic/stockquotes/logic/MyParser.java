package com.example.ivanradosavljevic.stockquotes.logic;

import com.example.ivanradosavljevic.stockquotes.domain.Symbol;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class MyParser {
    String server_response;
    List<Symbol> list;

    public MyParser(String server_response) {
        this.server_response = server_response;
        list = new ArrayList<>();
    }

    public List<Symbol> getList() {

        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            domFactory.setIgnoringComments(true);
            domFactory.setIgnoringElementContentWhitespace(true);
            domFactory.setCoalescing(true);
            DocumentBuilder domBuilder = null;
            Document document = null;
            domBuilder = domFactory.newDocumentBuilder();
            document = domBuilder.parse(new ByteArrayInputStream(server_response.getBytes()));
            NodeList listSymbols = document.getElementsByTagName("SymbolList");
            for (int i = 0; i < listSymbols.getLength(); i++) {
                Element symbolListNode = (Element) listSymbols.item(i);
                NodeList symbols = symbolListNode.getElementsByTagName("Symbol");
                for (int j = 0; j < symbols.getLength(); j++) {
                    Element symbolNode = (Element) symbols.item(j);
                    Symbol symbol = new Symbol();
                    symbol.setId(symbolNode.getAttribute("id"));
                    symbol.setName(symbolNode.getAttribute("name"));
                    NodeList quotes = symbolNode.getElementsByTagName("Quote");
                    for (int k = 0; k < quotes.getLength(); k++) {
                        Element quoteNode = (Element) quotes.item(k);
                        symbol.setQuoteChangePercent(quoteNode.getAttribute("changePercent"));
                        symbol.setQuoteLast(quoteNode.getAttribute("last"));
                        symbol.setQuoteHigh(quoteNode.getAttribute("high"));
                        symbol.setQuoteChange(quoteNode.getAttribute("change"));
                        symbol.setQuoteLow(quoteNode.getAttribute("low"));
                        symbol.setQuoteOpen(quoteNode.getAttribute("open"));
                        symbol.setDateTime(quoteNode.getAttribute("dateTime"));
                        symbol.setQuoteVolume(quoteNode.getAttribute("volume"));
                        symbol.setQuoteBid(quoteNode.getAttribute("bid"));
                        symbol.setQuoteAsk(quoteNode.getAttribute("ask"));
                    }
                    list.add(symbol);
                }

            }
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return list;
    }
}
