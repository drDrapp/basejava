package ru.drdrapp.webapp.storage.serializers;

import ru.drdrapp.webapp.model.*;
import ru.drdrapp.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializer implements SerializationStrategy{
  private final XmlParser xmlParser;

    public XmlSerializer() {
        xmlParser = new XmlParser(
                Resume.class,
                Organization.class,
                Organization.Period.class,
                Link.class,
                TextSection.class,
                ListSection.class,
                OrganizationsSection.class
        );
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}