package com.endava.exam.service;

import java.io.IOException;
import java.io.Writer;


public interface CSVService {
    void writePurchases(String sortBy, Writer writer) throws IOException;
}
