package com.bookstore.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

@Service
public class GeoIpService {
    private final DatabaseReader dbReader;

    public GeoIpService() {
        try {
            InputStream database = getClass().getClassLoader().getResourceAsStream("GeoLite2-Country.mmdb");
            if (database == null) {
                throw new IOException("GeoLite2-Country.mmdb not found in classpath");
            }
            File tempFile = File.createTempFile("GeoLite2-Country", ".mmdb");
            tempFile.deleteOnExit();
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = database.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            this.dbReader = new DatabaseReader.Builder(tempFile).build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load GeoLite2-Country.mmdb", e);
        }
    }

    public String getCountryName(String ip) {
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = dbReader.country(ipAddress);
            if (response.getCountry() != null) {
                return response.getCountry().getName();
            }
        } catch (IOException | GeoIp2Exception e) {
            // log error or handle as needed
        }
        return null;
    }
}
